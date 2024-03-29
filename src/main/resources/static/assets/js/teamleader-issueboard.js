
const todos = document.querySelectorAll(".todo");
const all_status = document.querySelectorAll(".status");
let draggableTodo = null;

todos.forEach((todo) => {
    todo.addEventListener("dragstart", dragStart);
    todo.addEventListener("dragend", dragEnd);
});

function dragStart() {
    draggableTodo = this;
    setTimeout(() => {
        this.style.display = "none";
    }, 0);
     console.log("dragStart");
}

function dragEnd() {
    draggableTodo = null;
    setTimeout(() => {
        this.style.display = "block";
    }, 0);
//  console.log("dragEnd");
}

all_status.forEach((status) => {
    status.addEventListener("dragover", dragOver);
    status.addEventListener("dragenter", dragEnter);
    status.addEventListener("dragleave", dragLeave);
    status.addEventListener("drop", dragDrop);
   // status.addEventListener("drop", (e) => dragDrop(e, status));
});

function dragOver(e) {
    e.preventDefault();
}

function dragEnter() {
    this.style.border = "1px dashed #ccc";
    //console.log("dragEnter");
}

function dragLeave() {
    this.style.border = "none";
    // console.log("dragLeave");
}

function dragDrop() {
    this.style.border = "none";

    // Check if tasks-wrapper div already exists
    let existingTasksWrapper = this.querySelector('.tasks-wrapper');
    // Assuming you have a reference to the HTML element
    const element = draggableTodo.querySelector('.tasks-box.todo');
    const issueStatus = element.dataset.id;
    console.log("Issue Status:", issueStatus);


    let text = this.querySelector('.textlist');
    let textContent = text.textContent.trim();
    let status = textContent.split(' ')[0];
    // Log the extracted status
    console.log("Status:", status);
    if(issueStatus=="PENDING"){
        if(status=="COMPLETED"){
            if (existingTasksWrapper) {
                existingTasksWrapper.appendChild(draggableTodo);
                let currentText=draggableTodo.getElementsByClassName('currentIssueId').item(0);
                console.log("currentId:",currentText.innerHTML);
                let currentIssueId=currentText.innerHTML;
                updateIssueStatus(currentIssueId, status).then(()=>{
                    console.log("It is  ok Status Update!");
                });
            }

        }else{
            console.log("Dropping is unable!");
        }
    }else {
        console.log("Dropping is not allowed!");
    }

}

async function updateIssueStatus(issueId, newStatus) {
    try {
        // Assuming you have an endpoint on the server to handle the update
        const response = await fetch(`/team-leader-issue/${issueId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ status: newStatus }),
        });

        if (response.ok) {
            console.log(`AssignIssue ${issueId} status updated to ${newStatus}`);
        } else {
            console.error(`Failed to update AssignIssue ${issueId} status`);
        }
    } catch (error) {
        console.error('Error updating AssignIssue status:', error);
    }
}

