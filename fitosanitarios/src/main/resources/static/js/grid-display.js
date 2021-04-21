var opcionesDesplegadas = false;

document.querySelectorAll('.filtro-fecha').forEach(filter => {
    filter.addEventListener('change', (e) => {
        window.location.pathname = `tratamientos/filtrado/${e.target.value}`
    })
});

// document.querySelector('#opciones-filtro').addEventListener('click', eventHandler(this));

function eventHandler(status) {
    document.querySelector("#opciones").style.display = (status) ? 'flex' : 'none'
}