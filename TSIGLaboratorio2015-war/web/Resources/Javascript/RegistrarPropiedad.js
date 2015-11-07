
function init() {
    
    CrearMapaBase();
    
    //------------------Agregar Coordenadas de un Punto------------------------
    map.addLayer(vector_layer);
    map.addControl(drawPoint);
    drawPoint.activate();
    drawPoint.events.register('featureadded', vector_layer, AgregarPunto);
}
