
function init() {
    
    CrearMapaBase();
    CargarPropiedades();
 
    map.addControl(select_feature_control);
    select_feature_control.activate();
    map.layers[1].events.register('featureselected', this, AbrirPopup);
}

