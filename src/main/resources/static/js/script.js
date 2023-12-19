const modal = document.getElementById('confirmarExclusao')

modal.addEventListener('show.bs.modal', function (event) {
    const button = event.relatedTarget
    const recipient = button.getAttribute('data-bs-objectid')

    const bntDelete = document.getElementById('excluir');
    bntDelete.action = recipient;
});
