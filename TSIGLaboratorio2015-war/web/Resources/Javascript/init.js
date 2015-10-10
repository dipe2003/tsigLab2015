
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
    map.addLayer(wms);
    //var vector_layer = new OpenLayers.Layer.Vector('Basic Vector Layer');
    
   // map.addLayer(vector_layer);
    //map.addControl(new  OpenLayers.Control.EditingToolbar(vector_layer));
    map.addControl(new OpenLayers.Control.LayerSwitcher({}));
    
    var capa_wfs = new OpenLayers.Layer.Vector('Capa WFS', {
        strategies: [new OpenLayers.Strategy.BBOX()],
        protocol: new OpenLayers.Protocol.WFS({
            url: 'http://localhost:8080/geoserver/wfs',
            featurePrefix:'tsiglab2015',
            featureType: 'departamento',
            featureNS: 'tsig2015',
            geometryName: 'the_geom',
            srsName: new OpenLayers.Projection('EPSG:900913'),
            version: '1.0.0'
        })
    });
    
    map.addLayer(capa_wfs);
    
        if(!map.getCenter()){
        map.zoomToMaxExtent();
    }
    
}