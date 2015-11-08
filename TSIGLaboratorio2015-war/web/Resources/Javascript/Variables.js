var opts = {
    projection: 'EPSG:900913', 
    displayProjection: 'EPSG:4326', 
    numZoomLevels: 19
};

var map;

var filterStrategy = new OpenLayers.Strategy.Filter();

var Propiedades = new OpenLayers.Layer.Vector('Propiedades', {
    strategies: [new OpenLayers.Strategy.Fixed(), filterStrategy],
    protocol: new OpenLayers.Protocol.WFS({
        url: 'http://localhost:8080/geoserver/wfs',
        featureType: 'propiedad',
        featureNS: 'tsiglab2015',
        featurePrefix:'tsiglab2015',            
        geometryName: 'the_geom',
        srsName: new OpenLayers.Projection('EPSG:900913'),
        version: '1.1.0'
    }, {transistionEffect:'resize'}),
    preFeatureInsert: function(feature) {
        cargarIconos(feature);
    }
});

var google_hybrid = new OpenLayers.Layer.Google("San Jose",{type: google.maps.MapTypeId.HYBRID},{isBaseLayer:true});

var ZonasCrecimiento = new OpenLayers.Layer.Vector('Zonas de Crecimieto', {
    strategies: [new OpenLayers.Strategy.Fixed()],
    protocol: new OpenLayers.Protocol.WFS({
        url: 'http://localhost:8080/geoserver/wfs',
        featureType: 'zonacrecimiento',
        featureNS: 'tsiglab2015',
        featurePrefix:'tsiglab2015',            
        geometryName: 'the_geom',
        srsName: new OpenLayers.Projection('EPSG:900913'),
        version: '1.1.0'
    }, {transistionEffect:'resize'}),
    preFeatureInsert: function(feature) {
        cargarEstilo(feature);
    }
});

var select_feature_control = new OpenLayers.Control.
        SelectFeature(
        Propiedades,{
            multiple: false,
    toggle: false,
    multipleKey: 'shiftKey'
});

var control_panel = new OpenLayers.Control.Panel({});
    
var arrayPopup = new Array();

var select_feature_control = new OpenLayers.Control.
        SelectFeature(
        Propiedades,{
            multiple: false,
    toggle: false,
    multipleKey: 'shiftKey'
});

var DragPoint = new OpenLayers.Control.DragFeature(Propiedades, {
    onComplete: function(feature){
        VerInfoChDir(feature);
    }
});
    
var btnChDir = new OpenLayers.Control.Button({
    title: 'Cambiar Direcion',
    text:'CambiarDireccion',
    displayClass: 'olControlCustomButtonToggle',
    eventListeners: {
        'activate': function() {
            DragPoint.activate();
        },
        'deactivate': function() {
            DragPoint.deactivate();              
        },
    },
    type: OpenLayers.Control.TYPE_TOGGLE
});

var vector_layer = new OpenLayers.Layer.Vector('Marcar Propiedades');

var drawPoint = new OpenLayers.Control.DrawFeature(vector_layer, OpenLayers.Handler.Point);

var drawPolygon = new OpenLayers.Control.DrawFeature(vector_layer, OpenLayers.Handler.Polygon);