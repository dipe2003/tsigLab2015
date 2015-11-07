function init() {
    
    CrearMapaBase();
    CargarPropiedades();
    
    map.addControl(select_feature_control);
    select_feature_control.activate();
    map.layers[1].events.register('featureselected', this, VerInfo);

    map.addControl(DragPoint);
    
    map.addControl(control_panel);
    control_panel.moveTo(new OpenLayers.Pixel(500,0));
    
    control_panel.addControls([btnChDir]);
    map.addControl(control_panel);
}
