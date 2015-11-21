
function init() {
    
    CrearMapaBase();
    
    //------------------Agregar Coordenadas de un Punto------------------------
    map.addLayer(vector_layer);
    map.addControl(drawPoint);
    drawPoint.activate();
    drawPoint.events.register('featureadded', vector_layer, AgregarPunto);
     
    var tiempo=0;
    $('.imagen').hide().each(function() {
        $(this).delay(tiempo).fadeIn('slow');
        tiempo += 200;
    });
    
    $("#ImagenUno").attr('src', './Resources/Images/brokenimage.jpg');
    $("#frmProp\\:imagenPropiedaduno").change(function(){
        if (this.files && this.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#ImagenUno').attr('src', e.target.result);
            }
            reader.readAsDataURL(this.files[0]);
        }
    });
    $("#ImagenUno").error(function(){
        $(this).attr('src', './Resources/Images/brokenimage.jpg');
    });
    
    $("#ImagenDos").attr('src', './Resources/Images/brokenimage.jpg');
    $("#frmProp\\:imagenPropiedaddos").change(function(){
        if (this.files && this.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#ImagenDos').attr('src', e.target.result);
            }
            reader.readAsDataURL(this.files[0]);
        }
    });
    $("#ImagenDos").error(function(){
        $(this).attr('src', './Resources/Images/brokenimage.jpg');
    });
    
    $("#ImagenTres").attr('src', './Resources/Images/brokenimage.jpg');
    $("#frmProp\\:imagenPropiedadtres").change(function(){
        if (this.files && this.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#ImagenTres').attr('src', e.target.result);
            }
            reader.readAsDataURL(this.files[0]);
        }
    });
    $("#ImagenTres").error(function(){
        $(this).attr('src', './Resources/Images/brokenimage.jpg');
    });
    
   
    
    $("#frmProp\\:inputDireccion").val("");
    $("#frmProp\\:inputPrecio").val("");
    $("#frmProp\\:inputMconstruidos").val("");
    $("#frmProp\\:inputMterreno").val("");
    $("#frmProp\\:inputDormitorios").val("");
    $("#frmProp\\:inputBanios").val("");
    
}
