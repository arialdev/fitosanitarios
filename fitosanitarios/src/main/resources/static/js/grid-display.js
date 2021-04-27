const opciones = document.querySelector("#opciones");

document.querySelectorAll('.filtro-fecha').forEach(filter => {
    filter.addEventListener('change', (e) => {
        window.location.pathname = (e.target.value) ? `tratamientos/filtrado/${e.target.value}` : `tratamientos`
    })
});

function eventHandler(status) {
    if (status) {
        opciones.classList.add('mostrar-opciones');
    }
    else {
        opciones.removeAttribute("style");  //elimina el estilo hardcoreado desde thymeleaf
        opciones.classList.remove('mostrar-opciones');
    }
}