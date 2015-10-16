package propiedad;

import inmueble.Inmueble;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import propiedad.caracteristica.ControladorCaracteristica;
import propiedad.enums.EnumEstadoPropiedad;
import propiedad.enums.EnumTipoInmueble;
import terreno.Terreno;

@Stateless
@ManagedBean
public class ControladorPropiedad {
    
    @EJB
    private ManejadorPropiedad mProp;
    @EJB
    private ControladorCaracteristica cCar;
    
    /**
     * Crea una propiedad inmueble del tipo casa.
     * @param CantidadDormitorios
     * @param CantidadBanios
     * @param DireccionPropiedad
     * @param PrecioPropiedad
     * @param MetrosConstruidosPropiedad
     * @param MetrosTerrenoPropiedad
     * @param NumeroPadronPropiedad
     * @param Caracteristicas
     * @return el id de la propiedad creada. -1 si no se pudo crear.
     */
   public int crearPropiedadCasa(int CantidadDormitorios, int CantidadBanios, String DireccionPropiedad, float PrecioPropiedad, 
           float MetrosConstruidosPropiedad, float MetrosTerrenoPropiedad, int NumeroPadronPropiedad, List<Integer> Caracteristicas){
       Propiedad propiedad = new Inmueble(EnumTipoInmueble.Casa,CantidadDormitorios, CantidadBanios, DireccionPropiedad, 
            PrecioPropiedad, MetrosConstruidosPropiedad, MetrosTerrenoPropiedad, NumeroPadronPropiedad, 
            EnumEstadoPropiedad.Privada, cCar.ListarCaracteristicas(Caracteristicas));
       return mProp.CrearPropiedad(propiedad);               
   }
    /**
     * Crea una propiedad inmueble del tipo casa sin caracteristicas.
     * @param CantidadDormitorios
     * @param CantidadBanios
     * @param DireccionPropiedad
     * @param PrecioPropiedad
     * @param MetrosConstruidosPropiedad
     * @param MetrosTerrenoPropiedad
     * @param NumeroPadronPropiedad
     * @return el id de la propiedad creada. -1 si no se pudo crear.
     */
   public int crearPropiedadCasa(int CantidadDormitorios, int CantidadBanios, String DireccionPropiedad, float PrecioPropiedad, 
           float MetrosConstruidosPropiedad, float MetrosTerrenoPropiedad, int NumeroPadronPropiedad){
       Propiedad propiedad = new Inmueble(EnumTipoInmueble.Casa,CantidadDormitorios, CantidadBanios, DireccionPropiedad, 
            PrecioPropiedad, MetrosConstruidosPropiedad, MetrosTerrenoPropiedad, NumeroPadronPropiedad, 
            EnumEstadoPropiedad.Privada);
       return mProp.CrearPropiedad(propiedad);               
   }
   
   /**
    * Crea una propiedad inmueble del tipo apartamento.
    * @param CantidadDormitorios
    * @param CantidadBanios
    * @param DireccionPropiedad
    * @param PrecioPropiedad
    * @param MetrosConstruidosPropiedad
    * @param MetrosTerrenoPropiedad
    * @param NumeroPadronPropiedad
    * @param Caracteristicas
    * @return el id de la propiedad creada. -1 si no se pudo crear.
    */
   public int crearPropiedadApto(int CantidadDormitorios, int CantidadBanios, String DireccionPropiedad, float PrecioPropiedad, 
           float MetrosConstruidosPropiedad, float MetrosTerrenoPropiedad, int NumeroPadronPropiedad, List<Integer> Caracteristicas){
       Propiedad propiedad = new Inmueble(EnumTipoInmueble.Apartamento,CantidadDormitorios, CantidadBanios, DireccionPropiedad, 
            PrecioPropiedad, MetrosConstruidosPropiedad, MetrosTerrenoPropiedad, NumeroPadronPropiedad, 
            EnumEstadoPropiedad.Privada, cCar.ListarCaracteristicas(Caracteristicas));
       return mProp.CrearPropiedad(propiedad);       
   }
   /**
    * Crea una propiedad inmueble del tipo apartamento sin caracteristicas.
    * @param CantidadDormitorios
    * @param CantidadBanios
    * @param DireccionPropiedad
    * @param PrecioPropiedad
    * @param MetrosConstruidosPropiedad
    * @param MetrosTerrenoPropiedad
    * @param NumeroPadronPropiedad
    * @return el id de la propiedad creada. -1 si no se pudo crear.
    */
   public int crearPropiedadApto(int CantidadDormitorios, int CantidadBanios, String DireccionPropiedad, float PrecioPropiedad, 
           float MetrosConstruidosPropiedad, float MetrosTerrenoPropiedad, int NumeroPadronPropiedad){
       Propiedad propiedad = new Inmueble(EnumTipoInmueble.Apartamento,CantidadDormitorios, CantidadBanios, DireccionPropiedad, 
            PrecioPropiedad, MetrosConstruidosPropiedad, MetrosTerrenoPropiedad, NumeroPadronPropiedad, 
            EnumEstadoPropiedad.Privada);
       return mProp.CrearPropiedad(propiedad);       
   }
   
   /**
    * Crea una propiedad Terreno.
    * @param DireccionPropiedad
    * @param PrecioPropiedad
    * @param MetrosConstruidosPropiedad
    * @param MetrosTerrenoPropiedad
    * @param NumeroPadronPropiedad
    * @param Caracteristicas
    * @return 
    */
   public int crearPropiedadTerreno(String DireccionPropiedad, float PrecioPropiedad, float MetrosConstruidosPropiedad,float MetrosTerrenoPropiedad, 
           int NumeroPadronPropiedad, List<Integer> Caracteristicas){
       Propiedad propiedad = new Terreno(DireccionPropiedad, PrecioPropiedad, MetrosConstruidosPropiedad, MetrosTerrenoPropiedad, 
               NumeroPadronPropiedad, EnumEstadoPropiedad.Privada, cCar.ListarCaracteristicas(Caracteristicas));
       return mProp.CrearPropiedad(propiedad);
   }
   /**
    * Crea una propiedad Terreno sin caracteristicas.
    * @param DireccionPropiedad
    * @param PrecioPropiedad
    * @param MetrosConstruidosPropiedad
    * @param MetrosTerrenoPropiedad
    * @param NumeroPadronPropiedad
    * @return 
    */
   public int crearPropiedadTerreno(String DireccionPropiedad, float PrecioPropiedad, float MetrosConstruidosPropiedad,float MetrosTerrenoPropiedad, 
           int NumeroPadronPropiedad){
       Propiedad propiedad = new Terreno(DireccionPropiedad, PrecioPropiedad, MetrosConstruidosPropiedad, MetrosTerrenoPropiedad, 
               NumeroPadronPropiedad, EnumEstadoPropiedad.Privada);
       return mProp.CrearPropiedad(propiedad);
   }
   
   /**
    * Agrega la ubicacion de una propiedad.
    * @param IdPropiedad
    * @param CoordX
    * @param CoordY
    * @return devuelve el id de la propiedad si se agrego correctamente. Retorna -1 si no se agrego.
    */
   public int InsertarUbicacionPropiedad(int IdPropiedad, float CoordX, float CoordY){
       return mProp.InsertarUbicacionPropiedad(IdPropiedad, CoordX, CoordY);
   }
}
