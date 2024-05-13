<?php
// listaModelo.php
if (isset($_GET['idMarca'])) {
    $idMarca = $_GET['idMarca'];
    // Aquí puedes realizar acciones basadas en el $idMarca recibido
    echo "ID de Marca recibido: " . $idMarca;
} else {
    echo "No se recibió el ID de Marca.";
}
?>
