// Function to validate form fields
function validateForm() {
    const form = document.getElementById('formAuthor');
    if (!form.checkValidity()) {
        form.classList.add('was-validated');
        return false;
    }
    return true;
}

function validateAndConfirm() {
    if (validateForm()) {
        showData();
    } else {
        console.log("error to create");
    }
}


