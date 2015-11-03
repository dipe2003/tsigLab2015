var opts = {
    projection: 'EPSG:900913', 
    displayProjection: 'EPSG:4326', 
    numZoomLevels: 19
};

var vector_layer;

function init() {
    var map = new OpenLayers.Map('map_element', opts);
    
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
    
    //------------------Agregar Coordenadas de un Punto------------------------
    //---------------------Capa auxiliar 
    vector_layer = new OpenLayers.Layer.Vector('Marcar Propiedades');
    map.addLayer(vector_layer);
    //---------------------Agregat puntos
    var drawPoint = new OpenLayers.Control.DrawFeature(vector_layer, OpenLayers.Handler.Point);
    map.addControl(drawPoint);
    drawPoint.activate();
    //---------------------trigger
    drawPoint.events.register('featureadded', vector_layer, AgregarPunto);
    //---------------------Posicion y Zoom
    if(!map.getCenter()){
        map.zoomToExtent(new OpenLayers.Bounds(-6316547.1474847,-4076411.4051729,-6307011.6282075,-4073545.0166127));
    }
    
    var select_feature_control = new OpenLayers.Control.
            SelectFeature(
            capa_wfs,
    {
        multiple: false,
        toggle: false,
        multipleKey: 'shiftKey'
    }
            );
    map.addControl(select_feature_control);
    select_feature_control.activate();
    map.layers[2].events.register('featureselected', this, VerInfo);
}

function AgregarPunto(ev){
    var desdeProjection = new OpenLayers.Projection("EPSG:900913");   
    var aProjection   = new OpenLayers.Projection("EPSG:4326");
    var punto = ev.feature.geometry;
    var punto = ev.feature.geometry.getBounds().getCenterLonLat().clone().transform(desdeProjection, aProjection);
    coord_x = punto.lon.toFixed(5);
    coord_y = punto.lat.toFixed(5);
    
    $('#frmProp\\:coordx').val(coord_x);
    $('#frmProp\\:coordy').val(coord_y);
    
    if (vector_layer.features.length>1){
        vector_layer.removeFeatures(vector_layer.features[0]);
    }    
}

function VerInfo(event){
    var prop = event.feature.attributes;
    alert("info de la propiedad: " + prop.direccionpropiedad + " " + prop.idpropiedad + " " + prop.preciopropiedad);
}