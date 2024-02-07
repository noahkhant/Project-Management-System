const inputs = document.querySelectorAll("input");
const button = document.querySelector("button");

// iterate over all inputs
inputs.forEach((input, index1) => {
    input.addEventListener("input", (e) => {
        const currentInput = input,
            nextInput = input.nextElementSibling,
            prevInput = input.previousElementSibling;

        if (currentInput.value.length > 0) {
            if (nextInput) {
                nextInput.removeAttribute("disabled");
                nextInput.focus();
            }
        }

        if (e.data === null && prevInput) {
            input.value = "";
            prevInput.focus();
        }

        if (!inputs[3].disabled && inputs[3].value !== "") {
            button.classList.add("active");
            return;
        }

        button.classList.remove("active");
    });
});

// focus the first input which index is 0 on window load
window.addEventListener("load", () => inputs[0].focus());
