
var opts = {projection: 'EPSG:900913', displayProjection: '4326'};

var vector_layer;

function init() {
    
    var map = new OpenLayers.Map('map_element', opts);
    
    //---------------------google map-----------------------------
    //---------------------Satelital
    var google_hybrid = new OpenLayers.Layer.Google(
            "Stelital",
    {type: google.maps.MapTypeId.SATELLITE},{isBaseLayer:true}
            );
    map.addLayer(google_hybrid);
    //---------------------Calles
    //    var google_road = new OpenLayers.Layer.Google(
    //            "Carretera",
    //    {type: google.maps.MapTypeId.ROADMAP},{isBaseLayer:true}
    //            ); 
    //    map.addLayer(google_road);
    //---------------------Hibrido
    //    var google_hybrid = new OpenLayers.Layer.Google(
    //            "Hibrido",
    //    {type: google.maps.MapTypeId.HYBRID},{isBaseLayer:true}
    //            );
    //     map.addLayer(google_hybrid);
    
    //---------------------ajustar pantalla-----------------------
    //---------------------Posicion y Zoom
    if(!map.getCenter()){
        map.zoomToMaxExtent();
    }
    //---------------------opcion cambiar capa
    map.addControl(new OpenLayers.Control.LayerSwitcher({}));
    //---------------------seleccion figura geometrica- 
    // map.addControl(new  OpenLayers.Control.EditingToolbar(vector_layer)); 
    
    //---------------------mostrar capa---------------------------
    //---------------------WMS - Departamento 
    //    var wms = new OpenLayers.Layer.WMS(
    //            'OpenLayers WMS',
    //    'http://localhost:8080/geoserver/wms/',
    //    {layers: 'departamento', transparent: true},
    //    {isBaseLayer: false, opacity: 0.5}
    //            );
    //    map.addLayer(wms);
    //---------------------WFS - Departamento
    //    var capa_wfs = new OpenLayers.Layer.Vector('Capa WFS', {
    //        strategies: [new OpenLayers.Strategy.Fixed()],
    //        protocol: new OpenLayers.Protocol.WFS({
    //            url: 'http://localhost:8080/geoserver/wfs',
    //            featureType: 'departamento',
    //            featureNS: 'tsiglab2015',
    //            featurePrefix:'tsiglab2015',            
    //            geometryName: 'the_geom',
    //            srsName: new OpenLayers.Projection('EPSG:900913'),
    //            version: '1.1.0'
    //        })
    //    });
    //    map.addLayer(capa_wfs);
    
    
    //------------------Agregar Coordenadas de un Punto------------------------
    //---------------------Capa auxiliar 
    var vector_layer = new OpenLayers.Layer.Vector('Basic Vector Layer');
    map.addLayer(vector_layer);
    //---------------------Agregat puntos
    var drawPoint = new OpenLayers.Control.DrawFeature(vector_layer, OpenLayers.Handler.Point);
    map.addControl(drawPoint);
    drawPoint.activate();
    //---------------------triger
    drawPoint.events.register('featureadded', vector_layer, AgregarPunto);
}

function AgregarPunto(ev){
    var punto = ev.feature.geometry;
    $('#formulario\\:coordx').val(punto.x);
    $('#formulario\\:coordy').val(punto.y);
//    if (vector_layer.features.length>1){
//        vector_layer.removeFeatures(vector_layer.features[0]);
//    }
}