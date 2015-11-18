function init() {
    
    CrearMapaBase();
    map.addLayer(ZonasCrecimiento);
    CargarPropiedades();
 
    map.addControl(modifyPolygon);
    modifyPolygon.activate();
    modifyPolygon.mode |= OpenLayers.Control.ModifyFeature.DRAG;
    map.layers[1].events.register('beforefeaturemodified', this, SeleccionarPoligono);
    map.layers[1].events.register('featuremodified', this, SeleccionarPoligono);
}