document.querySelectorAll("form input, select").forEach((input) => {
    console.log(input)
    input.setAttribute('data-origVal', input.value);

    input.addEventListener('change', (e) => {
        let actualValue = e.target.value;
        if (actualValue != input.getAttribute('data-origVal'))
            input.classList.add("modified");
        else input.classList.remove("modified");
    })
});