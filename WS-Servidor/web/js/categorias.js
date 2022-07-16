function categoriaGet() {
    $.ajax({
        url: "http://localhost:8080/Clase15WS_Tarea/web/json/categorias/"+document.getElementById("id").value,
        dataType: "json",
        type: "get",
        success: function (data) {
            var body = "<ul><li>ID: ";
            body += data.id;
            body += "</li><li>Nombre: ";
            body += data.nombre;
            body += "</li><li>Descripción: ";
            body += data.descripcion;
            body += "</li></ul>";
            $("#resultado").html(body);
        }
    });
}


function categoriaSel() {
    $.ajax({
        url: "http://localhost:8080/Clase15WS_Tarea/web/json/categorias",
        dataType: "json",
        type: "get",
        success: function (data) {
            var body = "<ul>";
            for (var i = 0; i < data.length; i++) {
                body += "<li>ID: ";
                body += data[i].id;
                body += "</li><li>Nombre: ";
                body += data[i].nombre;
                body += "</li><li>Descripción: ";
                body += data[i].descripcion;
                body += "</li>";
            }
            body += "</ul>";
            $("#resultado").html(body);
        }
    });
}