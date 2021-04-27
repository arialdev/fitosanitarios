document.querySelectorAll("form input[type='number'].plazo").forEach((plazo) => {
    plazo.addEventListener('change', () => {
        if (!plazo.value || plazo.value < 0) plazo.value = 0;
    })
});

document.querySelectorAll("form.update-form input:not([type='button']):not([type='submit']), select").forEach((input) => {
    input.setAttribute('data-origVal', input.value);
    input.addEventListener('change', (e) => {
        let actualValue = e.target.value;
        if (actualValue !== input.getAttribute('data-origVal'))
            input.classList.add("modified");
        else input.classList.remove("modified");
    })
});