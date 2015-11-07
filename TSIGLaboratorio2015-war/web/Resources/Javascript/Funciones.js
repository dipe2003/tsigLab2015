
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
    
    Propiedades.refresh({force: true});
    Propiedades.redraw();
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
    
    var vector_style_map;
    
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
    Propiedades.drawFeature(feature);
}

function AbrirPopup(event){
    var prop = event.feature.attributes;
    var popup = new OpenLayers.Popup.FramedCloud(
            "IdPopup",
    new OpenLayers.LonLat(event.feature.geometry.x,event.feature.geometry.y),
    null,
    '<div style="color:#FF0000; font-size:15px; font-weight:600">'+prop.direccionpropiedad + '</div>'
            +'</br> Tipo: '+ prop.dtype
            +'</br> En venta: '+ prop.enventa
            +'</br> En alquiler: '+ prop.enalquiler
            +'</br> <a href="/InformacionPropiedad.xhtml?id='+ prop.idpropiedad+'">Mas Informacion</a>'
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

function VerInfo(event){
    VerInfoChDir(event.feature);
}

function VerInfoChDir(feature){
        var prop = feature.attributes;
    var desdeProjection = new OpenLayers.Projection("EPSG:900913");   
    var aProjection   = new OpenLayers.Projection("EPSG:4326");
    var punto = feature.geometry;
    var punto = feature.geometry.getBounds().getCenterLonLat().clone().transform(desdeProjection, aProjection);
    coord_x = punto.lon.toFixed(5);
    coord_y = punto.lat.toFixed(5);    
    $('#frmAdminPropiedad\\:coordx').val(coord_x);
    $('#frmAdminPropiedad\\:coordy').val(coord_y);
    $('#frmAdminPropiedad\\:inputDireccion').val(prop.direccionpropiedad);
    $('#frmAdminPropiedad\\:inputPrecio').val(prop.preciopropiedad);
    $('#frmAdminPropiedad\\:inputMetrosConstruidos').val(prop.metrosconstruidospropiedad);
    $('#frmAdminPropiedad\\:inputMetrosTerreno').val(prop.metrosterrenopropiedad);
    $('#frmAdminPropiedad\\:inputDormitorios').val(prop.cantidaddormitorios);
    $('#frmAdminPropiedad\\:inputBanios').val(prop.cantidadbanios);
    $('#frmAdminPropiedad\\:inputAlquiler').val(prop.enalquiler);
    $('#frmAdminPropiedad\\:inputVenta').val(prop.enventa);
    $('#frmAdminPropiedad\\:idprop').val(prop.idpropiedad);
    var boton = $('#frmAdminPropiedad\\:btnCambiarDir');
            boton.click();
}

function CrearMapaBase(){
    map = new OpenLayers.Map('map', opts);
    
    //---------------------google map-----------------------------
    map.addLayer(google_hybrid);
    
    //---------------------Posicion y Zoom
    if(!map.getCenter()){
        map.zoomToExtent(new OpenLayers.Bounds(-6316547.1474847,-4076411.4051729,-6307011.6282075,-4073545.0166127));
    }
    
    //---------------------opcion cambiar capa
    map.addControl(new OpenLayers.Control.LayerSwitcher({}));
}

function CargarPropiedades(){
    //---------------------WFS - Propiedad-----------------------
    map.addLayer(Propiedades);
    
}