// Function to validate form fields
function validateForm() {
    const form = document.getElementById('formAuthor');
    if (!form.checkValidity()) {
        form.classList.add('was-validated');
        return false;
    }
    return true;
}

document.getElementById('confirmCreate').addEventListener('click', function() {
    // Execute showData() and close modal if the form is valid
    showData();
    var confirmationModal = new bootstrap.Modal(document.getElementById('confirmationModal'));
    confirmationModal.hide();
});

// Function to validate form and display confirmation modal
function validateAndConfirm() {
    if (validateForm()) {
        // Show the confirmation modal
        if(document.getElementById('createButton').hasAttribute('data-bs-dismiss')){
            var confirmationModal = new bootstrap.Modal(document.getElementById('confirmationModal'));
            confirmationModal.show();
        }
    } else {
        // If validation fails, prevent the modal from being dismissed by removing the data-bs-dismiss attribute
        document.getElementById('createButton').removeAttribute('data-bs-dismiss');
        document.getElementById('createButton').setAttribute('data-bs-dismiss', 'modal');
    }
}


