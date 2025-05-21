document.addEventListener('DOMContentLoaded', () => {
  const rutasImagenes = [
    '/HTML_Indra/Images/evento_1.png',
    '/HTML_Indra/Images/evento_2.jpg',
    '/HTML_Indra/Images/evento_3.jpeg',
  ];

  let indiceActual = 1;

  const imgTags = document.querySelectorAll('.contenedor-imagenes img');
  const botonIzq = document.querySelector('.izq');
  const botonDer = document.querySelector('.der');

  function actualizarGaleria() {
    const total = rutasImagenes.length;
    const anterior = (indiceActual - 1 + total) % total;
    const siguiente = (indiceActual + 1) % total;

    imgTags[0].src = rutasImagenes[anterior];
    imgTags[1].src = rutasImagenes[indiceActual];
    imgTags[2].src = rutasImagenes[siguiente];
  }

  botonIzq.addEventListener('click', () => {
    indiceActual = (indiceActual - 1 + rutasImagenes.length) % rutasImagenes.length;
    actualizarGaleria();
  });

  botonDer.addEventListener('click', () => {
    indiceActual = (indiceActual + 1) % rutasImagenes.length;
    actualizarGaleria();
  });

  actualizarGaleria();

  // Redireccionar a "eventos.html" al hacer clic en cualquier botón de la galería
  botonIzq.addEventListener('dblclick', () => {
    window.location.href = "/eventos.html";
  });

  botonDer.addEventListener('dblclick', () => {
    window.location.href = "/eventos.html";
  });
});
