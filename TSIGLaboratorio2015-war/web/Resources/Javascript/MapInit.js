//---------------------WFS - Propiedad-----------------------
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
    
//---------------------google map-----------------------------
var google_hybrid = new OpenLayers.Layer.Google(
        "San Jose",
{type: google.maps.MapTypeId.HYBRID},{isBaseLayer:true}
        );

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
