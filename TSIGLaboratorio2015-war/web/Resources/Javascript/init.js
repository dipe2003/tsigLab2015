var opts = {
    projection: 'EPSG:900913', 
    displayProjection: 'EPSG:4326', 
    numZoomLevels: 19
};

var vector_layer;
var filterStrategy;
var capa_wfs;
var vector_style_map;
var vector_style;
var map;
var select_feature_control

function init() {
    map = new OpenLayers.Map('map_element', opts);
    
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
    
    //---------------------opcion cambiar capa
    map.addControl(new OpenLayers.Control.LayerSwitcher({}));
    //---------------------WFS - Propiedad
    // crear estrategia de filtro
    filterStrategy = new OpenLayers.Strategy.Filter();
    capa_wfs = new OpenLayers.Layer.Vector('Propiedades', {
        
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
    //capa_wfs.styleMap = vector_style_map;
    
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
    
    select_feature_control = new OpenLayers.Control.
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
    $("#frmProp\\:registrarPropiedad").click();
}

var arrayPopup = new Array();

function VerInfo(event){
    var prop = event.feature.attributes;
    //var algo = prop.tipoinmueble;
    
    var popup = new OpenLayers.Popup.FramedCloud(
            "IdPopup",
    new OpenLayers.LonLat(event.feature.geometry.x,event.feature.geometry.y),
    null,
    '<div style="color:#FF0000">'+prop.direccionpropiedad + '</div>'
            +'</br> Tipo: '+ prop.dtype
            +'</br> En venta: '+ prop.enventa
            +'</br> En alquiler: '+ prop.enalquiler
    ,
    null,
    true
            );
    
    
    if (arrayPopup.length>0){
        for(var index = 0; index < arrayPopup.length; index++) {
            map.removePopup(arrayPopup[index]);
        }
    }
    arrayPopup = new Array();
    
    map.addPopup(popup);
    arrayPopup.push(popup);
    
    select_feature_control.unselectAll();
    
    //alert("info de la propiedad: " + prop.direccionpropiedad + " " + prop.dtype + " " + prop.preciopropiedad);
}

function filtrar(){
    
    //direccion
    var filterDireccion = new OpenLayers.Filter.Comparison({
        type: OpenLayers.Filter.Comparison.LIKE,
        property: 'direccionpropiedad',
        value: $("#direccion").val(),
    });
    
    //precio
    var attributePrecioDesde = $("#desde").val();
    if (attributePrecioDesde === "") attributePrecioDesde = 0;
    var filterDesde = new OpenLayers.Filter.Comparison({
        type: OpenLayers.Filter.Comparison.GREATER_THAN_OR_EQUAL_TO,
        property: 'preciopropiedad',
        value: attributePrecioDesde,
    });
    var attributePrecioHasta = $("#hasta").val();
    if (attributePrecioHasta === "") attributePrecioHasta = Number.MAX_VALUE;
    var filterHasta = new OpenLayers.Filter.Comparison({
        type: OpenLayers.Filter.Comparison.LESS_THAN_OR_EQUAL_TO,
        property: 'preciopropiedad',
        value: attributePrecioHasta,
    });
    
    //tipo
    var filterTipo = new OpenLayers.Filter.Comparison({
        type: OpenLayers.Filter.Comparison.LIKE,
        property: 'tipoinmueble',
        value: $("#tipo").val(),
    });
    
    //VentaAlquiler
    var attributeVentaAlquiler = $("#VentaAlquiler").val();
    if (attributeVentaAlquiler === ""){
        var parent_filter = new OpenLayers.Filter.Logical({
            type: OpenLayers.Filter.Logical.AND,
            filters: [filterDireccion, filterDesde, filterHasta, filterTipo]
        });
    }else{
        
        var alquiler = "FALSE";
        var venta = "FALSE";
        
        if (attributeVentaAlquiler === "Alquiler") {
            alquiler = "TRUE";
        }
        if (attributeVentaAlquiler === "Venta") {
            venta = "TRUE";
        }
        if (attributeVentaAlquiler === "AlquilerYVenta") {
            alquiler = "TRUE";
            venta = "TRUE";
        }
        
        var filterVenta = new OpenLayers.Filter.Comparison({
            type: OpenLayers.Filter.Comparison.LIKE,
            property: 'enventa',
            value: venta
        });
        
        var filterAlquiler = new OpenLayers.Filter.Comparison({
            type: OpenLayers.Filter.Comparison.LIKE,
            property: 'enalquiler',
            value: alquiler
        });
        
        var VentaAlquiler = new OpenLayers.Filter.Logical({
            type: OpenLayers.Filter.Logical.AND,
            filters: [filterVenta, filterAlquiler]
        });
        
        var parent_filter = new OpenLayers.Filter.Logical({
            type: OpenLayers.Filter.Logical.AND,
            filters: [filterDireccion, filterDesde, filterHasta, filterTipo, VentaAlquiler]
        });
    }
    
    filterStrategy.setFilter(parent_filter);
    filterStrategy.activate(); 
    capa_wfs.refresh({force: true});
    capa_wfs.redraw();
}

function cargarIconos(feature){
    //---------------------estilo--------------------------------
    // terreno
    var vector_style_terreno = new OpenLayers.Style({
        'pointRadius': 20,
        'externalGraphic': 'http://villawoodproperties.com.au/sites/www.villawoodproperties.com.au/files/basic_page/vw-5es-icon-3.png'
    });
    
    // casa
    var vector_style_casa = new OpenLayers.Style({
        'pointRadius': 20,
        'externalGraphic': 'http://investapr.com/wp-content/themes/realty/lib/images/map-marker/map-marker-red-fat.png'
    });
    
    // apartamento
    var vector_style_apartamento = new OpenLayers.Style({
        'pointRadius': 20,
        'externalGraphic': 'http://www.rootscsa.org/wp-content/uploads/2014/06/home_office_icon.png'
    });
    
    if (feature.attributes.tipoinmueble === "0"){ //casa
        vector_style_map = new OpenLayers.StyleMap({
            'default': vector_style_casa
        });
    }else if (feature.attributes.tipoinmueble === "1"){//apto
        vector_style_map = new OpenLayers.StyleMap({
            'default': vector_style_apartamento
        });
    }else{//terreno
        vector_style_map = new OpenLayers.StyleMap({
            'default': vector_style_terreno
        });
    }
    
    var style = $.extend({}, vector_style_map.createSymbolizer(feature), {
        strokeWidth: 5
    });
    feature.style = style;
    capa_wfs.drawFeature(feature);
}
