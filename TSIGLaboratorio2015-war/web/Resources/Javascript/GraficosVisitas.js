$(document).ready(function(){
    var ctx = document.getElementById("grafico").getContext("2d");
    var etiquetas = $('#filtro\\:labels').val().split(",");
    var visitas = $('#filtro\\:datosGraf').val();
    var arrDatos = visitas.split(",");
    var arrvisitas = new Array();
    for(i = 0; i < arrDatos.length; i++){
        arrvisitas[i] = parseInt(arrDatos[i]);
    }
    var datos ={
        labels:etiquetas,
        datasets:[{
                label: "Visitas",
                fillColor: "#A8CF45",
                strokeColor: "#9DC14E",
                highlightFill: "#076799",
                highlightStroke: "#6D93BE",
                data: arrvisitas
            }]
    };            
    var myBarChart = new Chart(ctx).Bar(datos);
});