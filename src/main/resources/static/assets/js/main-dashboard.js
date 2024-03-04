//Number Active Projects
fetch('/projects/count/active')
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        // Update project count on the frontend
        document.getElementById('projectCount').textContent = data;
        document.getElementById('active-projects').textContent = data;
    })
    .catch(error => console.error('Error fetching project count:', error));

//All Projects
fetch('/project/count/all')
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        // Update project count on the frontend
        document.getElementById('all-projects').textContent = data;
    })
    .catch(error => console.error('Error fetching project count:', error));

//Inactive projects
fetch('/projects/count/inactive')
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        // Update project count on the frontend
        document.getElementById('inactive-projects').textContent = data;
    })
    .catch(error => console.error('Error fetching project count:', error));


//Projects from you!
document.addEventListener('DOMContentLoaded', function() {
    projectsOfCurrentUser();
});
function projectsOfCurrentUser(){
    const url = `/projects-for-current-user`;
    fetch(url, {
        method: 'GET',
        credentials: 'include',
        headers: {
            'Accept': 'application/json',
        }
    })
        .then(response => response.json())
        .then(data => {
            console.log("data from main-dashboard"+data);
            displayProjectsOfCurrentUser();
        })
        .catch(error => {
            console.error('Error fetching user data:', error);
        });
}
function displayProjectsOfCurrentUser() {
    const tableBody = document.getElementById('projectTableBody');
    tableBody.innerHTML = '';

    projects.forEach(project => {
        const row = document.createElement('tr');
        row.innerHTML = `
            
                <td class="fw-medium">${project.title}</td>
                <td>
                    <div class="d-flex align-items-center">
                        <div class="flex-shrink-0 me-1 text-muted fs-13">${project.progress}%</div>
                        <div class="progress progress-sm bg-primary-subtle flex-grow-1" style="width: 68%;">
                            <div class="progress-bar bg-primary rounded" role="progressbar" style="width: ${project.progress}%" aria-valuenow="${project.progress}" aria-valuemin="0" aria-valuemax="100"></div>
                        </div>
                    </div>
                </td>
                <td><span class="badge bg-warning-subtle text-warning">${project.status}</span></td>
                <td class="text-muted">${project.planStartDate}</td>
                <td class="text-muted">${project.planEndDate}</td>
            `;

        tableBody.appendChild(row);
    });
}




