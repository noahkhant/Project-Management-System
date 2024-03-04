// Function to validate form fields
function validateForm() {
    const form = document.getElementById('formAuthor');
    if (!form.checkValidity()) {
        form.classList.add('was-validated');
        return false;
    }
    return true;
}

// Function to validate form and display confirmation modal
function validateAndConfirm() {
    // Perform your validation here
    if (validateForm()) {
        // Show success message
        Swal.fire({
            title: 'Success!',
            text: 'Your project has been created successfully.',
            icon: 'success',
            timer: 2000 // 2 seconds
        });
        showData();
    } else {
        // Show validation failure message
        Swal.fire({
            title: 'Validation Failed',
            text: 'Please correct the validation errors.',
            icon: 'error',
            timer: 2000 // 2 seconds
        });
    }
}



