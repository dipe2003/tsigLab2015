
function init() {
    
    CrearMapaBase();
    
    
    CargarPropiedades();
    
    map.addControl(select_feature_control);
    select_feature_control.activate();
    map.layers[1].events.register('featureselected', this, AbrirPopup);
    
    map.addLayer(PuntosInteres);
    
    filtrar();
    
}

function FiltroAvanzado(data){
    if (data.status === "success"){
        
        var idsPropiedad = $( "#filtro\\:ids" ).val();
        var res = idsPropiedad.split(" ");
        
        var arrayPopup = new Array();
        
        for (var i = 0; i < res.length; i++) {
            var idPropiedad = new OpenLayers.Filter.Comparison({
                type: OpenLayers.Filter.Comparison.EQUAL_TO,
                property: 'idpropiedad',
                value: res[i],
            });
            arrayPopup.push(idPropiedad);
        }
        
        var FiltroPorIds = new OpenLayers.Filter.Logical({
            type: OpenLayers.Filter.Logical.OR,
            filters: arrayPopup
        });
        
        filtrar(FiltroPorIds);
        
    }
}