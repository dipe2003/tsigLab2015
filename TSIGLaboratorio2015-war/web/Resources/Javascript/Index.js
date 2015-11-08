
function init() {
    
    CrearMapaBase();
    
    map.addLayer(ZonasCrecimiento);
    
    CargarPropiedades();

    map.addControl(select_feature_control);
    select_feature_control.activate();
    map.layers[2].events.register('featureselected', this, AbrirPopup);
    
    filtrar();
    
}

