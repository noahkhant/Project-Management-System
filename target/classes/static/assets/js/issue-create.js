document.addEventListener('DOMContentLoaded', function () {
    let createIssueModal = new bootstrap.Modal(document.getElementById('createrissueModal'));
    let viewModal = new bootstrap.Modal(document.getElementById('viewModal'));


    // Handle the click event on the "View" button in the first modal
    document.getElementById('view-btn').addEventListener('click', function () {
        viewModal.show();
    });

    document.getElementById('create-issue').addEventListener('click', function () {
        const url = '/get-category';
        fetch(url)
            .then(response => response.json())
            .then(data => {
                console.log('All department list: ', data);
                const selectElement = document.getElementById('issue-category-input');
                // Clear existing options
                selectElement.innerHTML = '';
                // Create and append options for each department
                data.forEach(category => {
                    const option = document.createElement('option');
                    option.value = category.id;
                    option.textContent = category.name;
                    selectElement.appendChild(option);
                });

                // Create an option for adding a new category
                const addOption = document.createElement('option');
                addOption.value = 'add_new_category';
                addOption.textContent = '+ Add New Category';
                selectElement.appendChild(addOption);
            })
            .catch(error => {
                console.log('Error: ', error);
            });

        createIssueModal.show();
    });

})


// Event listener for select change
    document.getElementById('issue-category-input').addEventListener('change', function() {
        let categoryModal = new bootstrap.Modal(document.getElementById('addIssueCategoryModal'));
        const selectedValue = this.value;
        if (selectedValue === 'add_new_category') {
            categoryModal.show();
            //addNewCategory();
            // Reset the select to its default value
            this.value = '';
        }
    });

    //Event Listener for Add issue category
    document.getElementById('addIssueCategoryForm').addEventListener('submit', function (event){
        event.preventDefault();
        console.log("submitting add form .....");
        let formData = {
            name: document.getElementById('issueCategoryName').value,
            description: document.getElementById('issueCategoryDescription').value
        };

        console.log(formData);

        let url = '/add-category';
        fetch( url ,{
            method: 'POST',
            headers: {
                'Content-type' : 'application/json'
            },
            body: JSON.stringify(formData),
        })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                // Show success modal using SweetAlert
                Swal.fire({
                    icon: 'success',
                    title: 'Success',
                    text: 'Your issue category has been successfully added.',
                    showConfirmButton: false,
                    timer: 2000 // Close after 2 seconds
                })
            })
            .catch(error => {
                console.error('Error adding new category:', error);
            });
    });



    // function addNewCategory() {
    //     const categoryName = prompt('Enter the name of the new category:');
    //     if (categoryName) {
    //         fetch('/add-category', {
    //             method: 'POST',
    //             headers: {
    //                 'Content-Type': 'application/json'
    //             },
    //             body: JSON.stringify({ name: categoryName })
    //         })
    //             .then(response => response.json())
    //             .then(data => {
    //                 // Refresh the list of categories
    //                 const selectElement = document.getElementById('issue-category-input');
    //                 selectElement.innerHTML = ''; // Clear existing options
    //                 data.forEach(category => {
    //                     const option = document.createElement('option');
    //                     option.value = category.id;
    //                     option.textContent = category.name;
    //                     selectElement.appendChild(option);
    //                 });
    //
    //                 // Create an option for adding a new category again
    //                 const addOption = document.createElement('option');
    //                 addOption.value = 'add_new_category';
    //                 addOption.textContent = '+ Add New Category';
    //                 selectElement.appendChild(addOption);
    //
    //                 console.log('New category added:', data);
    //             })
    //             .catch(error => {
    //                 console.error('Error adding new category:', error);
    //             });
    //     }
    // }
