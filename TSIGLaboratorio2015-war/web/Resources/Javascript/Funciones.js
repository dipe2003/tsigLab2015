//------------------------------------get url parameter----------------------------------------------------
var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
            sURLVariables = sPageURL.split('&'),
            sParameterName,
            i;
        
    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');
            
        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};
//---------------------------------------------------------------------------------------------------------

function filtrarUnaPropiedad(){
    var id = getUrlParameter('id');
    var Propiedad = new OpenLayers.Filter.Comparison({
        type: OpenLayers.Filter.Comparison.LIKE,
        property: 'idpropiedad',
        value: id,
    });
    filterStrategy.setFilter(Propiedad);
    filterStrategy.activate(); 
    Propiedades.refresh({force: true});
    Propiedades.redraw();
}

function filtrar(FiltroPorIds){
    var Publica = new OpenLayers.Filter.Comparison({
        type: OpenLayers.Filter.Comparison.LIKE,
        property: 'estadopropiedad',
        value: '1',
    });
    var Reservada = new OpenLayers.Filter.Comparison({
        type: OpenLayers.Filter.Comparison.LIKE,
        property: 'estadopropiedad',
        value: '3',
    });
    var PublicaOReservada = new OpenLayers.Filter.Logical({
        type: OpenLayers.Filter.Logical.OR,
        filters: [Publica, Reservada]
    });
    var filterDireccion = new OpenLayers.Filter.Comparison({
        type: OpenLayers.Filter.Comparison.LIKE,
        property: 'direccionpropiedad',
        value: $("#direccion").val(),
    });
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
    var filterTipo = new OpenLayers.Filter.Comparison({
        type: OpenLayers.Filter.Comparison.LIKE,
        property: 'tipoinmueble',
        value: $("#tipo").val(),
    });
    var attributeVentaAlquiler = $("#VentaAlquiler").val();
    var filtros = [filterDireccion, filterDesde, filterHasta, filterTipo,PublicaOReservada];
    if (attributeVentaAlquiler !== ""){
        var alquiler = "FALSE";
        var venta = "FALSE";
        if (attributeVentaAlquiler === "Alquiler") alquiler = "TRUE";
        if (attributeVentaAlquiler === "Venta") venta = "TRUE";
        if (attributeVentaAlquiler === "AlquilerYVenta") { alquiler = "TRUE"; venta = "TRUE";}
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
        filtros.push(VentaAlquiler);
    }
    
    if ( typeof FiltroPorIds !== "undefined") filtros.push(FiltroPorIds);
    
    var parent_filter = new OpenLayers.Filter.Logical({
        type: OpenLayers.Filter.Logical.AND,
        filters: filtros
    });
    
    filterStrategy.setFilter(parent_filter);
    filterStrategy.activate(); 
    Propiedades.refresh({force: true});
    Propiedades.redraw();
}

function cargarIconos(feature){
    var imagen;
    if (feature.attributes.tipoinmueble === "0"){ //casa
        imagen = 'https://cdn4.iconfinder.com/data/icons/SHINE7/general/400/home.png';
    }else if (feature.attributes.tipoinmueble === "1"){//apto
        imagen = 'http://www.vetmed.vt.edu/information-systems/images/OfficeBuilding.png';
    }else{//terreno
        imagen = 'http://villawoodproperties.com.au/sites/www.villawoodproperties.com.au/files/basic_page/vw-5es-icon-3.png';
    }
    var vector_style = new OpenLayers.Style({'pointRadius': 20});
    var vector_style_map = new OpenLayers.StyleMap({'default': vector_style});
    var style = $.extend({}, vector_style_map.createSymbolizer(feature), { externalGraphic: imagen });
    feature.style = style;
    Propiedades.drawFeature(feature);
}

function AbrirPopup(event){
    var prop = event.feature.attributes;
    var negocio = "";
    if(prop.enventa === 'true') negocio += '</br> En Venta';
    if(prop.enalquiler === 'true') negocio += '</br> En Alquiler'
    var popup = new OpenLayers.Popup.FramedCloud("IdPopup",new OpenLayers.LonLat(event.feature.geometry.x,event.feature.geometry.y),null,
    '<div style="color:#FF0000; font-size:15px; font-weight:600">'+prop.direccionpropiedad + '</div>'
            +'</br> Tipo: '+ prop.dtype
            + negocio
            +'</br> <a href="InformacionPropiedad.xhtml?id='+ prop.idpropiedad+'">Mas Informacion</a>'
    ,null,true);
    if (arrayPopup.length>0){
        for(var index = 0; index < arrayPopup.length; index++) {map.removePopup(arrayPopup[index]);}
    }
    arrayPopup = new Array();
    map.addPopup(popup);
    arrayPopup.push(popup);
    select_feature_control.unselectAll();
}
function AbrirPopupPuntoInteres(event){
    var punto = event.feature.attributes;
    var nombre = punto.nombre.replace(/\s+/g, '');
    var distancia = $('#'+nombre).val().toString() + ' m';
    var popup = new OpenLayers.Popup.FramedCloud(
            "IdPopup",
    new OpenLayers.LonLat(event.feature.geometry.x,event.feature.geometry.y),
    null,
    '<div style="color:#FF0000; font-size:15px; font-weight:600">'+punto.nombre + '</div>'
            +'</br> Tipo: '+ punto.tipo
            +'</br> Distancia: ' + distancia
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
    select_feature_puntosinteres_control.unselectAll();
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
    if (vector_layer.features.length>1) vector_layer.removeFeatures(vector_layer.features[0]);
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
    map.addLayer(google_hybrid);
    if(!map.getCenter()){
        map.zoomToExtent(new OpenLayers.Bounds(-6316547.1474847,-4076411.4051729,-6307011.6282075,-4073545.0166127));
    }
    map.addControl(new OpenLayers.Control.LayerSwitcher({}));
}

function CargarPropiedades(){
    map.addLayer(Propiedades);
}

function AgregarPoligono(event){
    var vertices = event.features[0].geometry.getVertices();
    var desdeProjection = new OpenLayers.Projection("EPSG:900913");   
    var aProjection   = new OpenLayers.Projection("EPSG:4326");
    var strVertices = "";
    for (var a in vertices){
        vertices[a] = vertices[a].getBounds().getCenterLonLat().transform(desdeProjection, aProjection);
        strVertices += vertices[a].lon + " " + vertices[a].lat +",";
    }
    strVertices += vertices[0].lon + " " + vertices[0].lat;
    $('#frmZona\\:inputCoords').val(strVertices);
}
function SeleccionarPoligono(event){
    var vertices = event.feature.geometry.getVertices();
    var desdeProjection = new OpenLayers.Projection("EPSG:900913");   
    var aProjection   = new OpenLayers.Projection("EPSG:4326");
    var strVertices = "";
    for (var i = 0; i < vertices.length; i++) {
        var centro = vertices[i].getBounds().getCenterLonLat().clone();
        centro = centro.transform(desdeProjection, aProjection);
        strVertices += centro.lon + " " + centro.lat + ",";
    }
    var centro = vertices[0].getBounds().getCenterLonLat().clone();
    centro = centro.transform(desdeProjection, aProjection);
    strVertices += centro.lon + " " + centro.lat;
    $('#frmAdminZona\\:inputCoordsZona').val(strVertices);
    $('#frmAdminZona\\:inputIdZonaCrecimiento').val(event.feature.attributes.idzonacrecimiento);
}

function cargarEstilo(feature){
    var vector_style_alta = new OpenLayers.Style({'fillColor': 'red','fillOpacity': .2,'strokeColor': '#7F0002','strokeWidth': 3});
    var vector_style_media = new OpenLayers.Style({'fillColor': 'yellow','fillOpacity': .2,'strokeColor': '#ffc100','strokeWidth': 3});
    var vector_style_baja = new OpenLayers.Style({'fillColor': 'green','fillOpacity': .2,'strokeColor': '#A8CF45','strokeWidth': 3});
    var vector_style_map;
    if (feature.attributes.demandazonacrecimiento === "Alta"){ //casa
        vector_style_map = new OpenLayers.StyleMap({'default': vector_style_alta});
    }else if (feature.attributes.demandazonacrecimiento === "Media"){//apto
        vector_style_map = new OpenLayers.StyleMap({'default': vector_style_media});
    }else{//terreno
        vector_style_map = new OpenLayers.StyleMap({'default': vector_style_baja});
    }
    var style = $.extend({}, vector_style_map.createSymbolizer(feature), {
        strokeWidth: 2
    });
    feature.style = style;
    ZonasCrecimiento.drawFeature(feature);
}

function filtrarPropiedadesDeUsuario(){
    var id = $("#frmAdminPropiedad\\:idusr").val();
    var Propiedad = new OpenLayers.Filter.Comparison({
        type: OpenLayers.Filter.Comparison.EQUAL_TO ,
        property: 'usuariopropiedad_idusuario',
        value: id,
    });
    filterStrategy.setFilter(Propiedad);
    filterStrategy.activate(); 
    Propiedades.refresh({force: true});
    Propiedades.redraw();
}

function cargarIconosPuntosInteres(feature){
    var imagen;
    if (feature.attributes.tipo === "medica"){
        imagen = 'http://icons.iconarchive.com/icons/icons-land/gis-gps-map/256/Hospital-icon.png';        
    }else if (feature.attributes.tipo === "combustible"){// 
        imagen = 'http://www.veryicon.com/icon/ico/Transport/GIS%20GPS%20MAP/GasStation.ico';       
    }else{
        imagen = 'http://stroy.dammir.ru/attachments/Image/Kommerciya.png';
    }
    var vector_style = new OpenLayers.Style({'pointRadius': 20});
    var vector_style_map = new OpenLayers.StyleMap({'default': vector_style});
    var style = $.extend({}, vector_style_map.createSymbolizer(feature), { externalGraphic: imagen });
    feature.style = style;
    PuntosInteres.drawFeature(feature);
}
