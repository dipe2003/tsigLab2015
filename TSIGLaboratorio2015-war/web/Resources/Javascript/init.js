
var opts = {projection: 'EPSG:900913', displayProjection: '4326'};

           
function init() {
    
    var wms = new OpenLayers.Layer.WMS(
            'OpenLayers WMS',
    'http://localhost:8080/geoserver/wms/',
    {layers: 'departamento', transparent: true},
    {isBaseLayer: false, opacity: 0.5}
            );
    
    map = new OpenLayers.Map('map_element', opts);
    var google_hybrid = new OpenLayers.Layer.Google(
            "Stelital",
    {type: google.maps.MapTypeId.SATELLITE},{isBaseLayer:true}
            );
    map.addLayer(google_hybrid);
    var google_road = new OpenLayers.Layer.Google(
            "Carretera",
    {type: google.maps.MapTypeId.ROADMAP},{isBaseLayer:true}
            );
    map.addLayer(google_road);
    
    var google_hybrid = new OpenLayers.Layer.Google(
            "Hibrido",
    {type: google.maps.MapTypeId.HYBRID},{isBaseLayer:true}
            );
    map.addLayer(google_hybrid);
    
    var vector_layer = new OpenLayers.Layer.Vector('Basic Vector Layer');
    
    map.addLayers([wms, vector_layer]);
    map.addControl(new  OpenLayers.Control.EditingToolbar(vector_layer));
    map.addControl(new OpenLayers.Control.LayerSwitcher({}));
    if(!map.getCenter()){
        map.zoomToMaxExtent();
    }
}