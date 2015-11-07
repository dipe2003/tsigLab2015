var opts = {
    projection: 'EPSG:900913', 
    displayProjection: 'EPSG:4326', 
    numZoomLevels: 19
};

var mapIndex;
var arrayPopup = new Array();
var select_feature_control = new OpenLayers.Control.
            SelectFeature(
            Propiedades,{
                multiple: false,
        toggle: false,
        multipleKey: 'shiftKey'
    });

function initIndex() {
    
    mapIndex = new OpenLayers.Map('map_index', opts);
    
    //---------------------google map-----------------------------
    mapIndex.addLayer(google_hybrid);
    
    //---------------------Posicion y Zoom
    if(!mapIndex.getCenter()){
        mapIndex.zoomToExtent(new OpenLayers.Bounds(-6316547.1474847,-4076411.4051729,-6307011.6282075,-4073545.0166127));
    }
    
    //---------------------opcion cambiar capa
    mapIndex.addControl(new OpenLayers.Control.LayerSwitcher({}));
    
    //---------------------WFS - Propiedad-----------------------
    mapIndex.addLayer(Propiedades);
    
    //---------------------triger popup---------------------------
    mapIndex.addControl(select_feature_control);
    select_feature_control.activate();
    mapIndex.layers[1].events.register('featureselected', this, AbrirPopup);
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
            +'</br> <a href="InformacionPropiedad.xhtml?id='+ prop.idpropiedad+'">Mas Informacion</a>'
    ,
    null,
    true
            );
    if (arrayPopup.length>0){
        for(var index = 0; index < arrayPopup.length; index++) {
            mapIndex.removePopup(arrayPopup[index]);
        }
    }
    arrayPopup = new Array();
    mapIndex.addPopup(popup);
    arrayPopup.push(popup);
    select_feature_control.unselectAll();
}
