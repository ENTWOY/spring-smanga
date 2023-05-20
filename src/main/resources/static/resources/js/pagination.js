/*
******************  
Paginacion(Mangas)
******************/ 
// seleccionar elemento y almacenar
const ulTag = document.querySelector('.pagination ul');
const dataManga = document.querySelectorAll('.dataManga');
// Número de mangas por página
const itemsPerPage = 12; 
const totalPages = Math.ceil(dataManga.length / itemsPerPage);

function showPage(page) {
  const startIndex = (page - 1) * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;

  for (let i = 0; i < dataManga.length; i++) {
    if (i >= startIndex && i < endIndex) {
      dataManga[i].style.display = 'block';
    } else {
      dataManga[i].style.display = 'none';
    }
  }
}

function createPagination(totalPages, paginaActual) {
  let liTag = '';
  let activeLi;

  if (paginaActual > 1) {
    liTag += `<li class="btn prev" onclick="createPagination(${totalPages}, ${paginaActual - 1})"><span><i class="fas fa-angle-left"></i> Anterior</span></li>`;
  }

  for (let page = 1; page <= totalPages; page++) {
    if (page == paginaActual) {
      activeLi = 'active';
    } else {
      activeLi = '';
    }
    liTag += `<li class="numb ${activeLi}" onclick="createPagination(${totalPages}, ${page})"><span>${page}</span></li>`;
  }

  if (paginaActual < totalPages) {
    liTag += `<li class="btn next" onclick="createPagination(${totalPages}, ${paginaActual + 1})"><span>Siguiente <i class="fas fa-angle-right"></i></span></li>`;
  }

  ulTag.innerHTML = liTag;

  showPage(paginaActual);
}
createPagination(totalPages, 1);
