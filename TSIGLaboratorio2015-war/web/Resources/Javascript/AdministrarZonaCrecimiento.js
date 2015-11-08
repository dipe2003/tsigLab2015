function init() {
    
    CrearMapaBase();
    CargarPropiedades();
        
    map.addLayer(ZonasCrecimiento);
 
    map.addControl(modifyPolygon);
    modifyPolygon.activate();
    modifyPolygon.mode |= OpenLayers.Control.ModifyFeature.DRAG;
    map.layers[2].events.register('beforefeaturemodified', this, SeleccionarPoligono);
    map.layers[2].events.register('featuremodified', this, SeleccionarPoligono);
}