var opts = {
    projection: 'EPSG:900913', 
    displayProjection: 'EPSG:4326', 
    numZoomLevels: 19
};

function init_admin() {
    var map = new OpenLayers.Map('map_element_admin', opts);
    
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
    
    var DragPoint = new OpenLayers.Control.DragFeature(capa_wfs, {
        onComplete: function(feature){
            VerInfoChDir(feature);
        }
    });

    map.addControl(DragPoint);
    
    var control_panel = new OpenLayers.Control.Panel({});
    map.addControl(control_panel);
    control_panel.moveTo(new OpenLayers.Pixel(0,0));
    
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
    control_panel.addControls([btnChDir]);
    map.addControl(control_panel);
    
}

function VerInfo(event){
    var prop = event.feature.attributes;
    var desdeProjection = new OpenLayers.Projection("EPSG:900913");   
    var aProjection   = new OpenLayers.Projection("EPSG:4326");
    var punto = event.feature.geometry;
    var punto = event.feature.geometry.getBounds().getCenterLonLat().clone().transform(desdeProjection, aProjection);
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
    //$('#frmAdminPropiedad\\:btnCargar').click();
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
    //$('#frmAdminPropiedad\\:btnCargar').click();
}
