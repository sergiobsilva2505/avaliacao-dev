var exampleModal = document.getElementById('confirmarExclusao')
exampleModal.addEventListener('show.bs.modal', function (event) {
    var button = event.relatedTarget
    var recipient = button.getAttribute('data-bs-objectid')

    var btnExcluir = document.getElementById('excluir');
    btnExcluir.action = recipient;
});
