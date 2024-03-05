document.addEventListener("DOMContentLoaded", function (){
    getDepartment();
    getPositions();
    displayProfile();
})

function displayProfile() {
    // Fetch data from the server
    fetch('/user/profile', {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
        },
        credentials: 'include' // Include this option if your server uses session-based authentication
    })
        .then(response => {
            // Check if the response is successful
            if (!response.ok) {
                throw new Error('Failed to fetch user data');
            }
            // Parse the JSON response
            return response.json();
        })
        .then(data => {
            console.log('User data:', data);
            document.getElementById('current-id').value = data.id;
            document.getElementById('nameInput').value = data.name;
             document.getElementById('dobInput').value = data.dob;
            document.getElementById('phoneNumberInput').value = data.phone;
            document.getElementById('emailInput').value = data.email;
            // Auto-select department in dropdown
            const positionSelected = document.getElementById('editPosition');
            positionSelected.value = data.position.id; // Assuming data contains departmentId

            // Optionally, dispatch a change event on the select element to trigger any associated event listeners
            const event1 = new Event('change');
            positionSelected.dispatchEvent(event1);

            if (data.gender === 'male') {
                document.getElementById('maleRadio').checked = true;
            } else if (data.gender === 'female') {
                document.getElementById('femaleRadio').checked = true;
            }

            document.getElementById('editEducation').value = data.education;
             document.getElementById('addressInput').value = data.address;

            // Auto-select department in dropdown
            const departmentSelect = document.getElementById('editDepartment');
            departmentSelect.value = data.department.id; // Assuming data contains departmentId

            // Optionally, dispatch a change event on the select element to trigger any associated event listeners
            const event = new Event('change');
            departmentSelect.dispatchEvent(event);

            const userProfileImage = document.querySelector('.user-profile-image');
            userProfileImage.src = `/static/img/${data.profilePictureFileName}`; // Assuming data contains imageName
            userProfileImage.alt = data.name;

            document.getElementById('username').textContent = data.name;
            document.getElementById('position').textContent = data.position.positionName;
        })
        .catch(error => {
            console.error('Error fetching user data:', error);
        });
}

function getDepartment(){
    fetch('/departments', {
        method: 'GET',
    })
        .then(r => r.json())
        .then(data => {
            // Get a reference to the select element
            const departmentSelect = document.getElementById('editDepartment');

            // Clear any existing options
            departmentSelect.innerHTML = '';

            // Add options based on the data received
            data.forEach(department => {
                const option = document.createElement('option');
                option.value = department.id; // Assuming department object has an 'id' property
                option.textContent = department.name; // Assuming department object has a 'name' property
                departmentSelect.appendChild(option);
            });
        })
}
function getPositions(){
    fetch('/api/user/positions', {
        method: 'GET',
    })
        .then(r => r.json())
        .then(data => {
            // Get a reference to the select element
            const positionSelect = document.getElementById('editPosition');

            // Clear any existing options
            positionSelect.innerHTML = '';

            // Add options based on the data received
            data.forEach(position => {
                const option = document.createElement('option');
                option.value = position.id; // Assuming department object has an 'id' property
                option.textContent = position.positionName; // Assuming department object has a 'name' property
                positionSelect.appendChild(option);
            });
        })
}

// document.getElementById("updateUserButton").addEventListener('submit', function (event) {
//     event.preventDefault();
//     console.log("submitting add form .....");
//
//     let gender;
//     if (document.getElementById('maleRadio').checked) {
//         gender = document.getElementById('maleRadio').value;
//     } else if (document.getElementById('femaleRadio').checked) {
//         gender = document.getElementById('femaleRadio').value;
//     } else {
//         // Handle case where no radio button is selected
//         gender = ''; // Or whatever default value you want to assign
//     }
//
//     console.log("here");
//
//     let updateData = {
//         //creator: document.getElementById('project-creator-update').value,
//         name: document.getElementById('nameInput').value,
//         dob: document.getElementById('dobInput').value,
//         phone:  document.getElementById('phoneNumberInput').value,
//         email: document.getElementById('emailInput').value,
//         gender: gender,
//         position: {
//             id:document.getElementById('editPosition')
//         },
//         education: document.getElementById('editEducation').value,
//         address: document.getElementById('addressInput').value,
//         department: {
//             id: document.getElementById('editDepartment').value
//         },
//         profilePictureFileName: document.querySelector('.profile-img-file-input').value
//     };
//     console.log("updateData", updateData);
//     const id = document.getElementById('current-id').value;
//     const response =  fetch(`/edit-user/${id}`, {
//         method: 'PUT',
//         headers: {
//             'Content-Type': 'application/json',
//         },
//         body: JSON.stringify(updateData),
//     })
//         .then(response => response.json())
//         .then(data => {
//             console.log('Data received:', data);
//             displayProfile();
//         })
//         .catch(error => console.log("Error" + error));
// });


