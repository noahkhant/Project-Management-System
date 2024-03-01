let stompClient;
let issueId;
let messageList = [];
document.addEventListener('DOMContentLoaded',()=>{
    // Button to trigger modal
    var openChatBtn = document.getElementById('liveChat');
    // Modal
    var chatModal = new bootstrap.Modal(document.getElementById('chatModal'));
    var currentUser;


    async function fetchCurrentUser() {
        return await fetch('/api/user/current-user')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to fetch current user');
                }
                return response.json();
            })
            .then(data => {
                // Update UI with user data
                return data;
            })
            .catch(error => {
                console.error('Error fetching current user:', error);
                return Promise.reject(0);
            });
    }


    function chatListOnClick(event){
        let target = event.target;
        if (target.tagName === 'LI') {
            // Get the chat name
            let chatName = target.getAttribute('data-chat');
            issueId = target.getAttribute('data-issue-id');
            // Set modal title to chat name
            let chatModalLabel = document.getElementById('chatModalLabel');
            chatModalLabel.textContent = chatName;
            // Clear chat list
            let chatListItems = document.querySelectorAll('#chatList .list-group-item');
            chatListItems.forEach(function(item) {
                item.parentNode.removeChild(item);
            });
            // Show chat box
            let chatBox = document.getElementById('chatBox');
            chatBox.classList.remove('d-none');
            // Set chat box title
            let chatBoxTitle = document.getElementById('chatBoxTitle');
            chatBoxTitle.textContent = chatName;
            // Clear chat name in the modal body
            let chatBoxBody = document.getElementById('chatBoxTitle'); // This line was corrected
            chatBoxBody.textContent = ''; // This line was added to clear the content


            //fetch list of message
            fetchMessageList()
                .then(messages =>{
                    messageList.push(...messages);
                    showMessage(messageList);
                    stompClient.subscribe(`/topic/messages/${issueId}`, function(message) {
                        let messageData = JSON.parse(message.body).body; // Extract the message data from the payload
                        showMessage(messageData); // Pass the message data (list of messages) to showMessage
                    });
                    // Show modal
                    chatModal.show();
                })
                .catch(error => {
                    console.error('Error fetching message list:', error);
                });


        }
    }
    async function fetchMessageList(){
        return await fetch(`/get-messages/${issueId}`)
            .then(response => response.json())
            .then(messageList => {
                return messageList;
            })
            .catch(error => {
                console.log('Error: ', error);
                return Promise.reject(0);
            });
    }
    function renderChatList(){
        fetchCurrentUser()
            .then(currentUser=>{
                const userId = currentUser.id;
                fetch(`/get-issues/${userId}`)
                    .then(response => response.json())
                    .then(data => {
                        console.log('All issue list: ', data);
                        issueList = data;

                        let chatList = document.getElementById('chatList');
                        chatList.innerHTML = '';

                        // Generate new list items based on the data
                        issueList.forEach(function(issue) {
                            let li = document.createElement('li');
                            li.classList.add('list-group-item');
                            li.setAttribute('data-chat', issue.title);
                            li.setAttribute('data-issue-id', issue.id);
                            li.textContent = issue.title + " Group chat";
                            li.addEventListener("click",chatListOnClick);
                            chatList.appendChild(li);
                        });
                        // Render the data into the DataTable
                        //refreshDepartmentListTable(departmentList);
                    })
                    .catch(error => {
                        console.log('Error: ', error);
                    });
                chatModal.show();
            })
            .catch(err=>{
                console.log(err);
            })
    }

    // Show modal when button clicked
    openChatBtn.addEventListener('click', function() {
        renderChatList();
    });

    // Handle back to chat list button click
    document.getElementById('backToChatListBtn').addEventListener('click', function() {
        // // Clear chat list
        // let chatListItems = document.querySelectorAll('#chatBox');
        // chatListItems.forEach(function(item) {
        //     item.parentNode.removeChild(item);
        // });
        renderChatList();
    });


    //websocket connection
    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, () => {
        console.log('Connected to WebSocket');

    });

    function showMessage(messageList) {
        const messages = document.getElementById('messages');
        // Clear existing messages before appending new ones
        messages.innerHTML = '';

        // Check if messageList is an array
        if (!Array.isArray(messageList)) {
            console.error("messageList is not an array:", messageList);
            return;
        }

        messageList.forEach(function(message) {
            const li = document.createElement('li');

            const sender = message.sender || "Unknown";
            const content = message.content || "No content";

            const senderElement = document.createElement('div');
            senderElement.textContent = sender;
            senderElement.classList.add('sender');
            li.appendChild(senderElement);

            const contentElement = document.createElement('div');
            contentElement.textContent = content;
            contentElement.classList.add('content');
            li.appendChild(contentElement);

            if (sender === currentUser) {
                li.classList.add("own-message");
            } else {
                li.classList.add("other-message");
            }

            messages.appendChild(li);
        });


    }



});

function sendMessage() {
    const sender = document.getElementById('sender').value;
    const content = document.getElementById('content').value;
    const message = {sender, content};
    stompClient.send(`/app/chat/${issueId}`, {}, JSON.stringify(message));
}







