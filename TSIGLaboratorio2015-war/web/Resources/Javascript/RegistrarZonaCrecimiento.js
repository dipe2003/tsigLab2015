function init() {
    
    CrearMapaBase();
    
    map.addLayer(ZonasCrecimiento);
    
    CargarPropiedades();
    
    map.addLayer(vector_layer_zonas);
    
    map.addLayer(ZonasCrecimiento);
    
    map.addControl(drawPolygon);
    drawPolygon.activate(); 
    
    vector_layer_zonas.events.on({
    featuresadded: AgregarPoligono
    });

    drawPolygon.handler.callbacks.point = function(data) {
        if(vector_layer_zonas.features.length > 0) vector_layer_zonas.removeAllFeatures();
    };
}
