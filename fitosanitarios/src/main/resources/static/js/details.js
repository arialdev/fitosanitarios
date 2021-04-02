document.querySelectorAll("form input").forEach((input) => {
    input.setAttribute('data-origVal', input.value);

    input.addEventListener('change', (e) => {
        let actualValue = e.target.value;
        console.log(input.getAttribute('data-origVal'));
        if (actualValue != input.getAttribute('data-origVal'))
            input.classList.add("modified");
        else input.classList.remove("modified");
    })
});

console.log(document.querySelector('#zona-input').getAttribute('data-origVal'))