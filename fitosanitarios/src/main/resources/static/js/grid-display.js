document.querySelectorAll('.filtro-fecha').forEach(filter => {
    filter.addEventListener('change', (e) => {
        window.location.pathname = `tratamientos/filtrado/${e.target.value}`
    })
});