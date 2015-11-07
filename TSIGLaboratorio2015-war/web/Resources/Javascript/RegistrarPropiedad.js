var opts = {
    projection: 'EPSG:900913', 
    displayProjection: 'EPSG:4326', 
    numZoomLevels: 19
};

var vector_layer;

function initRegistroPropiedad() {
    
    var mapRegistro = new OpenLayers.Map('map_registroPropiedad', opts);
    
    //---------------------google map-----------------------------
    // Hibrido
    var google_hybrid = new OpenLayers.Layer.Google(
            "San Jose",
    {type: google.maps.MapTypeId.HYBRID},{isBaseLayer:true}
            );
    mapRegistro.addLayer(google_hybrid);
    
    //---------------------Posicion y Zoom
    if(!mapRegistro.getCenter()){
        mapRegistro.zoomToExtent(new OpenLayers.Bounds(-6316547.1474847,-4076411.4051729,-6307011.6282075,-4073545.0166127));
    }
    
    //---------------------opcion cambiar capa
    mapRegistro.addControl(new OpenLayers.Control.LayerSwitcher({}));
    
    //------------------Agregar Coordenadas de un Punto------------------------
    //---------------------Capa auxiliar 
    vector_layer = new OpenLayers.Layer.Vector('Marcar Propiedades');
    mapRegistro.addLayer(vector_layer);
    //---------------------Agregar puntos
    var drawPoint = new OpenLayers.Control.DrawFeature(vector_layer, OpenLayers.Handler.Point);
    mapRegistro.addControl(drawPoint);
    drawPoint.activate();
    //---------------------trigger
    drawPoint.events.register('featureadded', vector_layer, AgregarPunto);
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
    $("#frmProp\\:registrarPropiedad").click();
}

