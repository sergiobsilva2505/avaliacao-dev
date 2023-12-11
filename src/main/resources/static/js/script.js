var exampleModal = document.getElementById('confirmarExclusao')
exampleModal.addEventListener('show.bs.modal', function (event) {
    var button = event.relatedTarget
    var recipient = button.getAttribute('data-bs-exameid')

    var btnExcluir = document.getElementById('excluir');
    btnExcluir.action = recipient;
    console.log(recipient)
});
