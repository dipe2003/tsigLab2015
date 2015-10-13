package propiedad;

import inmueble.Inmueble;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import propiedad.caracteristica.ControladorCaracteristica;
import propiedad.enums.EnumEstadoPropiedad;
import propiedad.enums.EnumTipoInmueble;

@Stateless
@ManagedBean
public class ControladorPropiedad {
    
    @EJB
    private ManejadorPropiedad mProp;
    @EJB
    private ControladorCaracteristica cCar;
    
    /**
     * Crea una propiedad del tipo casa.
     * @param CantidadDormitorios
     * @param CantidadBanios
     * @param DireccionPropiedad
     * @param PrecioPropiedad
     * @param MetrosConstruidosPropiedad
     * @param MetrosTerrenoPropiedad
     * @param NumeroPadronPropiedad
     * @param EstadoPropiedad
     * @param Caracteristicas
     * @return el id de la propiedad creada. -1 si no se pudo crear.
     */
   public int crearPropiedadCasa(int CantidadDormitorios, int CantidadBanios, String DireccionPropiedad, float PrecioPropiedad, 
           float MetrosConstruidosPropiedad, float MetrosTerrenoPropiedad, int NumeroPadronPropiedad, 
            EnumEstadoPropiedad EstadoPropiedad, List<Integer> Caracteristicas){
       Propiedad propiedad = new Inmueble(EnumTipoInmueble.Casa,CantidadDormitorios, CantidadBanios, DireccionPropiedad, 
            PrecioPropiedad, MetrosConstruidosPropiedad, MetrosTerrenoPropiedad, NumeroPadronPropiedad, 
            EnumEstadoPropiedad.Privada, cCar.ListarCaracteristicas(Caracteristicas));
       return mProp.CrearPropiedad(propiedad);               
   }
   
   /**
    * Crea una propiedad del tipo apartamento.
    * @param CantidadDormitorios
    * @param CantidadBanios
    * @param DireccionPropiedad
    * @param PrecioPropiedad
    * @param MetrosConstruidosPropiedad
    * @param MetrosTerrenoPropiedad
    * @param NumeroPadronPropiedad
    * @param EstadoPropiedad
    * @param Caracteristicas
    * @return el id de la propiedad creada. -1 si no se pudo crear.
    */
   public int crearPropiedadApto(int CantidadDormitorios, int CantidadBanios, String DireccionPropiedad, float PrecioPropiedad, 
           float MetrosConstruidosPropiedad, float MetrosTerrenoPropiedad, int NumeroPadronPropiedad, 
            EnumEstadoPropiedad EstadoPropiedad, List<Integer> Caracteristicas){
       Propiedad propiedad = new Inmueble(EnumTipoInmueble.Apartamento,CantidadDormitorios, CantidadBanios, DireccionPropiedad, 
            PrecioPropiedad, MetrosConstruidosPropiedad, MetrosTerrenoPropiedad, NumeroPadronPropiedad, 
            EnumEstadoPropiedad.Privada, cCar.ListarCaracteristicas(Caracteristicas));
       return mProp.CrearPropiedad(propiedad);       
   }
    
}
