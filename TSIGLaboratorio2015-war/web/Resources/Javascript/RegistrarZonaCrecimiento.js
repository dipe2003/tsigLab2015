function init() {
    
    CrearMapaBase();
    CargarPropiedades();
    
    map.addLayer(vector_layer);
    
    map.addLayer(ZonasCrecimiento);
    
    map.addControl(drawPolygon);
    drawPolygon.activate(); 
    
    vector_layer.events.on({
    featuresadded: AgregarPlogono
    });

    drawPolygon.handler.callbacks.point = function(data) {
        if(vector_layer.features.length > 0) vector_layer.removeAllFeatures();
    };
    
}