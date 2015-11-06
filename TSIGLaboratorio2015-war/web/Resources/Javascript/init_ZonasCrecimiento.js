var opts = {
    projection: 'EPSG:900913', 
    displayProjection: 'EPSG:4326', 
    numZoomLevels: 19
};

function init_ZonasCrecimiento() {
    var map = new OpenLayers.Map('map_element_zonas', opts);
    
    //---------------------google map-----------------------------
    // Hibrido
    var google_hybrid = new OpenLayers.Layer.Google(
            "San Jose",
    {type: google.maps.MapTypeId.HYBRID},{isBaseLayer:true}
            );
    map.addLayer(google_hybrid);
    
    // Satelite
    var google_sat = new OpenLayers.Layer.Google(
            "San Jose Satelital",
    {type: google.maps.MapTypeId.SATELLITE},{isBaseLayer:true}
            );
    map.addLayer(google_sat);
    
    //---------------------estilo--------------------------------
    var vector_style = new OpenLayers.Style({
        'fillColor': 'blue',
        'fillOpacity': .8,
        'strokeColor': '#aaee77',
        'strokeWidth': 3,
        'pointRadius': 8
    });
    var vector_style_map = new OpenLayers.StyleMap({
        'default': vector_style
    });
    
    
    //---------------------opcion cambiar capa
    map.addControl(new OpenLayers.Control.LayerSwitcher({}));
    //---------------------WFS - Propiedad
    var capa_wfs = new OpenLayers.Layer.Vector('Propiedades', {
        
        strategies: [new OpenLayers.Strategy.Fixed()],
        protocol: new OpenLayers.Protocol.WFS({
            url: 'http://localhost:8080/geoserver/wfs',
            featureType: 'propiedad',
            featureNS: 'tsiglab2015',
            featurePrefix:'tsiglab2015',            
            geometryName: 'the_geom',
            srsName: new OpenLayers.Projection('EPSG:900913'),
            version: '1.1.0'
        }, {transistionEffect:'resize'})
    });
    capa_wfs.styleMap = vector_style_map;
    map.addLayer(capa_wfs);
    
    //---------------------Posicion y Zoom
    if(!map.getCenter()){
        map.zoomToExtent(new OpenLayers.Bounds(-6316547.1474847,-4076411.4051729,-6307011.6282075,-4073545.0166127));
    }
    
    //---------------------Capa auxiliar 
    vector_layer = new OpenLayers.Layer.Vector('Marcar Propiedades');
    map.addLayer(vector_layer);
        
    //---------------------Dibujar poligonos
    
    var drawPolygon = new OpenLayers.Control.DrawFeature(vector_layer, OpenLayers.Handler.Polygon);
    map.addControl(drawPolygon);
    drawPolygon.activate(); 
    
    vector_layer.events.on({
    featuresadded: onFeaturesAdded
});
}

function onFeaturesAdded(event){
    var bounds = event.features[0].geometry.getBounds();
    var answer = "bottom: " + bounds.bottom  + "\n";
    answer += "left: " + bounds.left  + "\n";
    answer += "right: " + bounds.right  + "\n";
    answer += "top: " + bounds.top  + "\n";
    alert(answer);
}

