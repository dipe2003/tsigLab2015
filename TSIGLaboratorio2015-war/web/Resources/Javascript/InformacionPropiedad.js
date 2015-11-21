$(document).ready(function(){
    
    $(".imagen").error(function(){
        $(this).attr('src', './Resources/Images/brokenimage.jpg');
    });

});

function init() {
    
    CrearMapaBase();
    
 
    map.addLayer(PuntosInteres);
    
    map.addControl(select_feature_puntosinteres_control);
    select_feature_puntosinteres_control.activate();
    map.layers[1].events.register('featureselected', this, AbrirPopupPuntoInteres);
    
    CargarPropiedades();
    
    filtrarUnaPropiedad();
    
}

