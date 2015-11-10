
function init() {
    
    CrearMapaBase();
    
    map.addLayer(ZonasCrecimiento);
    map.addLayer(PuntosInteres);
    
    map.addControl(select_feature_puntosinteres_control);
    select_feature_puntosinteres_control.activate();
    map.layers[2].events.register('featureselected', this, AbrirPopupPuntoInteres);
    
    CargarPropiedades();
    
    filtrarUnaPropiedad();
    
}

