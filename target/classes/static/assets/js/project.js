<!--Category-->
var categories = ["Designing", "Development"];

// Populate categories dropdown
var categoriesDropdown = document.getElementById("project-category-input");
categories.forEach(function (category) {
    const option = document.createElement("option");
    option.value = category;
    option.text = category;
    categoriesDropdown.add(option);
});

// Create a new script element
var script = document.createElement('script');

// Set the src attribute to the jQuery CDN URL
script.src = 'https://code.jquery.com/jquery-3.6.4.min.js';

// Append the script element to the document body
document.body.appendChild(script);

<!--Project creation-->
<!--Getting Users-->
document.addEventListener('DOMContentLoaded', function() {
    // Call userGetter to fetch and display a user list for the default department
    departmentGetter();
});

function userGetter() {
    const departmentId = document.getElementById('project-department-input').value;
    const url = `/members-selector/${departmentId}`;

    fetch(url, {
        method: 'GET',
        credentials: 'include',
        headers: {
            'Accept': 'application/json',
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            // Display the users returned by the server in the member list
            populateMemberList(data);
        })
        .catch(error => {
            console.error('Error fetching user data:', error);
        });
}


// Helper function to group user IDs by department ID
function groupByDepartment(userIds) {
    const grouped = {};
    userIds.forEach(userId => {
        const departmentId = userId.department.id;
        if (!grouped[departmentId]) {
            grouped[departmentId] = [];
        }
        grouped[departmentId].push(userId);
    });
    return grouped;
}


function populateMemberList(dto) {
    const memberListContainer = document.getElementById('team-member-list');
    // Clear existing member list content
    memberListContainer.innerHTML = '';

    if (!Array.isArray(dto)) {
        console.error('DTO is not an array:', dto);
        return;
    }

    // Loop through the DTOs and create HTML elements for each DTO
    dto.forEach(user => {
        const listItem = document.createElement('li');
        listItem.innerHTML = `
            <div class="form-check d-flex align-items-center justify-content-between">
                <div>
                    <input class="form-check-input  me-3" type="checkbox" value="${user.id}">
                    <label class="form-check-label d-flex align-items-center" for="user-${user.id}">
                        <span class="flex-shrink-0">
                            <img src="assets/images/${user.photo}" alt="" class="avatar-xxs rounded-circle" />
                        </span>
                        <span class="flex-grow-1 ms-2">${user.name}</span>
                        <span class="flex-grow-1 ms-2">${user.position}</span>
                        <div class="flex-shrink-0 ms-4 additional-content">
                            <ul class="list-inline tasks-list-menu mb-0">
                                <li class="list-inline-item">
                                    <a href="issue_member_details.html"><button class="btn btn-sm btn-light" id="view-btn">View</button></a>
                                </li>
                            </ul>
                        </div>
                    </label>
                </div>
            </div>
        `;
        memberListContainer.appendChild(listItem);
    });
}

<!--Getting Department-->
function departmentGetter() {
    const url = '/departments-selector';
    fetch(url, {
        method: 'GET',
        credentials: 'include',
        headers: {
            'Accept': 'application/json',
        }
    })
        .then(response => response.json())
        .then(data => {
            const selectElement = document.getElementById('project-department-input');
            // Clear existing options
            selectElement.innerHTML = '';
            // Create and append options for each department
            data.forEach((department, index) => {
                const option = document.createElement('option');
                console.log(department.id);
                console.log(department.name)
                option.value = department.id;
                option.textContent = department.name;
                selectElement.appendChild(option);
                if (index === 0) {
                    option.selected = true;
                }
            });
            userGetter();
        })
        .catch(error => console.error("Error:", error));
}

function stripHtmlTags(html) {
    const doc = new DOMParser().parseFromString(html, 'text/html');
    return doc.body.textContent || "";
}

 function showData() {

    document.getElementById("formAuthor").addEventListener("submit", async function (event) {
        event.preventDefault();
        console.log("submitting form...");

        const selectedUserIds = [];
        document.querySelectorAll('.form-check-input').forEach(checkbox => {

            if (checkbox.checked && checkbox.value !== "dark") {
                selectedUserIds.push(checkbox.value);
            }
        });
        console.log(selectedUserIds);

        let editorData = window.ckeditor.getData();
        let plainTextData = stripHtmlTags(editorData);
        console.log("here");

        let formData = {
            creator: document.getElementById('project-creator-name').value,
            objective: document.getElementById('project-objective-input').value,
            title: document.getElementById('project-title-input').value,
            category: document.getElementById('project-category-input').value,
            description: plainTextData,
            priority: document.getElementById('choices-priority-input').value,
            planStartDate: document.getElementById('datepicker-start-date-input').value,
            planEndDate: document.getElementById('datepicker-end-date-input').value,
            status: document.getElementById('project-status-input').value,
            department: {
                id: document.getElementById('project-department-input').value
            },
            isActive: true
        };
        console.log(formData);
        console.log("this");

       let url = "/add-project";
        fetch(url, {
            method: 'Post',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ ...formData, userIds: selectedUserIds}),
        })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                // location.reload();
            })
            .catch(error => console.log("Error" + error));
    });
}