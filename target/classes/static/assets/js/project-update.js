<!--These codes are for the project Update-->

let projectList = [];

function memberPlaces(users, project) {
    const memberListContainer = document.getElementById('team-member-list-update');
    // Clear existing member list content
    memberListContainer.innerHTML = '';

    if (!Array.isArray(users)) {
        console.error('User data is not an array:', users);
        return;
    }

    users.forEach(user => {
        const isChecked = project && project.users.some(projUser => projUser.id === user.id);
        const listItem = document.createElement('li');
        listItem.innerHTML = `
            <div class="form-check d-flex align-items-center justify-content-between">
                <div>
                    <input class="form-check-input me-3" type="checkbox" value="${user.id}" ${isChecked ? 'checked' : ''}>
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

function department(){

    const url = `/departments`;
    fetch(url, {
        method: 'GET',
        credentials: 'include',
        headers: {
            'Accept': 'application/json',
        }
    }).then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
        .then(data => {
            // Handle the department data here
            console.log(data);
            const selectElement = document.getElementById('project-department-update');
            selectElement.innerHTML = '';
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
        })
        .catch(error => {
            console.error('Error fetching department data:', error);
        });
}

// Create a function to fetch users and populate the member list
document.addEventListener('DOMContentLoaded', () => {

    // Make an AJAX request to fetch data from the Rest API endpoint
    fetch('/show-projects-for-update')
        .then(response => response.json())
        .then(data => {
            console.log('All project list: ', data);
            projectList = data;
        })
        .catch(error => {
            console.log('Error: ', error);
        });
});

function displayEditProject(id) {
    const project = projectList.find(project => project.id === id);
    console.log("Project Edit ", project);

    // Function to fetch departments data
    function fetchDepartmentsAndSetSelectedDepartment(project) {
        fetch('/departments', {
            method: 'GET'
        })
            .then(response => response.json())
            .then(departmentsData => {
                // Check if a project is found
                if (project) {
                    // Populate modal fields with project data
                    document.getElementById('project-title-update').value = project.title;
                    document.getElementById('project-objective-update').value = project.objective;
                    document.getElementById('project-creator-update').value = project.creator;
                    document.getElementById('project-category-update').value = project.category;
                    document.getElementById('project-status-update').value = project.status;
                    document.getElementById('choices-priority-update').value = project.priority;
                    document.getElementById('datepicker-start-date-update').value = project.planStartDate;
                    document.getElementById('datepicker-end-date-update').value = project.planEndDate;

                    window.ckeditorUpdate.setData(project.description);

                    // Check if departmentsData is defined and is an array
                    if (Array.isArray(departmentsData)) {
                        // Set the selected department based on project's department id
                        const selectElement = document.getElementById('project-department-update');
                        const relatedDepartment = departmentsData.find(department => department.id === project.department.id);

                        if (relatedDepartment) {
                            selectElement.value = relatedDepartment.name;

                            getUser(relatedDepartment.id, project);
                        } else {
                            console.log('Related department not found.');
                        }
                    } else {
                        console.log('departmentsData is not an array or is undefined/null.');
                    }

                    // Show the modal
                    const projectEditModal = document.getElementById('updateModal');
                    if (projectEditModal) {
                        projectEditModal.classList.add('show');
                        projectEditModal.style.display = 'block';
                    } else {
                        console.log("Project Edit Modal not found");
                    }
                } else {
                    console.log("Project not found for ID:", id);
                }
            })
            .catch(error => console.error("Error fetching departments:", error));
    }

    // Call the function to fetch department data
    department();

    // Call the function to fetch departments data and set selected department
    fetchDepartmentsAndSetSelectedDepartment(project);
}


function getUser(departmentId, project) {
    const url = `/user-for-project-update/${departmentId}`;

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
            console.log("data from getUser" + data);
            memberPlaces(data, project);
            return data;
        })
        .catch(error => {
            console.error('Error fetching user data:', error);
        });
}

<!--The End-->

// // Event listener for the delete project button in the confirmation modal
// document.getElementById('remove-project').addEventListener('click', async function() {
//     try {
//         const projectId = [[${project.id}]];
//         // Retrieve the project data based on the project ID
//         const project = projectList.find(project => project.id === projectId);
//         await handleProjectDeletion(project);
//         // Optionally, perform any additional actions after the project is deleted
//     } catch (error) {
//         console.error('Error deleting project:', error);
//     }
// });
// // Function to handle project deletion
// async function handleProjectDeletion(project) {
//     try {
//         // Update the project's active status to false
//         project.isActive = false;
//         // Send an HTTP request to update the project data on the server
//         const response = await fetch(`/projects/${project.id}`, {
//             method: 'PUT',
//             headers: {
//                 'Content-Type': 'application/json'
//             },
//             body: JSON.stringify(project)
//         });
//         if (!response.ok) {
//             throw new Error('Failed to delete project');
//         }
//         console.log('Project deleted successfully');
//         // Optionally, perform any additional actions after the project is deleted
//     } catch (error) {
//         console.error('Error deleting project:', error);
//         throw error; // Propagate the error to the caller
//     }










