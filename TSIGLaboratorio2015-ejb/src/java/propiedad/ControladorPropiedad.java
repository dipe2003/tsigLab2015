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
import usuario.ControladorUsuario;
import usuario.Usuario;

@Stateless
@ManagedBean
public class ControladorPropiedad {
    
    @EJB
    private ManejadorPropiedad mProp;
    @EJB
    private ControladorCaracteristica cCar;
    @EJB
    private ControladorUsuario cUsr;
    
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
     * @param EnAlquiler
     * @param EnVenta
     * @param IdUsuario
     * @return el id de la propiedad creada. -1 si no se pudo crear.
     */
    public int crearPropiedadCasa(int CantidadDormitorios, int CantidadBanios, String DireccionPropiedad, float PrecioPropiedad,
            float MetrosConstruidosPropiedad, float MetrosTerrenoPropiedad, int NumeroPadronPropiedad, List<Integer> Caracteristicas,
            boolean EnAlquiler, boolean EnVenta, int IdUsuario){
        Propiedad propiedad = new Inmueble(EnumTipoInmueble.Casa,CantidadDormitorios, CantidadBanios, DireccionPropiedad,
                PrecioPropiedad, MetrosConstruidosPropiedad, MetrosTerrenoPropiedad, NumeroPadronPropiedad,
                EnumEstadoPropiedad.Privada, cCar.ListarCaracteristicas(Caracteristicas), EnAlquiler, EnVenta);
        try{
            Usuario usr = cUsr.GetUsuario(IdUsuario);
            propiedad.setUsuarioPropiedad(usr);
            return mProp.CrearPropiedad(propiedad);
        }catch(NullPointerException ex){}
        return -1;
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
     * @param EnAlquiler
     * @param EnVenta
     * @param IdUsuario
     * @return el id de la propiedad creada. -1 si no se pudo crear.
     */
    public int crearPropiedadCasa(int CantidadDormitorios, int CantidadBanios, String DireccionPropiedad, float PrecioPropiedad,
            float MetrosConstruidosPropiedad, float MetrosTerrenoPropiedad, int NumeroPadronPropiedad,  boolean EnAlquiler, boolean EnVenta, int IdUsuario){
        Propiedad propiedad = new Inmueble(EnumTipoInmueble.Casa,CantidadDormitorios, CantidadBanios, DireccionPropiedad,
                PrecioPropiedad, MetrosConstruidosPropiedad, MetrosTerrenoPropiedad, NumeroPadronPropiedad,
                EnumEstadoPropiedad.Privada, EnAlquiler, EnVenta);
        try{
            Usuario usr = cUsr.GetUsuario(IdUsuario);
            propiedad.setUsuarioPropiedad(usr);
            return mProp.CrearPropiedad(propiedad);
        }catch(NullPointerException ex){}
        return -1;
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
     * @param EnAlquiler
     * @param EnVenta
     * @param IdUsuario
     * @return el id de la propiedad creada. -1 si no se pudo crear.
     */
    public int crearPropiedadApto(int CantidadDormitorios, int CantidadBanios, String DireccionPropiedad, float PrecioPropiedad,
            float MetrosConstruidosPropiedad, float MetrosTerrenoPropiedad, int NumeroPadronPropiedad, List<Integer> Caracteristicas,
            boolean EnAlquiler, boolean EnVenta, int IdUsuario){
        Propiedad propiedad = new Inmueble(EnumTipoInmueble.Apartamento,CantidadDormitorios, CantidadBanios, DireccionPropiedad,
                PrecioPropiedad, MetrosConstruidosPropiedad, MetrosTerrenoPropiedad, NumeroPadronPropiedad,
                EnumEstadoPropiedad.Privada, cCar.ListarCaracteristicas(Caracteristicas), EnAlquiler, EnVenta);
        try{
            Usuario usr = cUsr.GetUsuario(IdUsuario);
            propiedad.setUsuarioPropiedad(usr);
            return mProp.CrearPropiedad(propiedad);
        }catch(NullPointerException ex){}
        return -1;
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
     * @param EnAlquiler
     * @param EnVenta
     * @param IdUsuario
     * @return el id de la propiedad creada. -1 si no se pudo crear.
     */
    public int crearPropiedadApto(int CantidadDormitorios, int CantidadBanios, String DireccionPropiedad, float PrecioPropiedad,
            float MetrosConstruidosPropiedad, float MetrosTerrenoPropiedad, int NumeroPadronPropiedad,  boolean EnAlquiler, boolean EnVenta, int IdUsuario){
        Propiedad propiedad = new Inmueble(EnumTipoInmueble.Apartamento,CantidadDormitorios, CantidadBanios, DireccionPropiedad,
                PrecioPropiedad, MetrosConstruidosPropiedad, MetrosTerrenoPropiedad, NumeroPadronPropiedad,
                EnumEstadoPropiedad.Privada, EnAlquiler, EnVenta);
        try{
            Usuario usr = cUsr.GetUsuario(IdUsuario);
            propiedad.setUsuarioPropiedad(usr);
            return mProp.CrearPropiedad(propiedad);
        }catch(NullPointerException ex){}
        return -1;
    }
    
    /**
     * Crea una propiedad Terreno.
     * @param DireccionPropiedad
     * @param PrecioPropiedad
     * @param MetrosConstruidosPropiedad
     * @param MetrosTerrenoPropiedad
     * @param NumeroPadronPropiedad
     * @param Caracteristicas
     * @param EnAlquiler
     * @param EnVenta
     * @param IdUsuario
     * @return
     */
    public int crearPropiedadTerreno(String DireccionPropiedad, float PrecioPropiedad, float MetrosConstruidosPropiedad,float MetrosTerrenoPropiedad,
            int NumeroPadronPropiedad, List<Integer> Caracteristicas,  boolean EnAlquiler, boolean EnVenta, int IdUsuario){
        Propiedad propiedad = new Terreno(DireccionPropiedad, PrecioPropiedad, MetrosConstruidosPropiedad, MetrosTerrenoPropiedad,
                NumeroPadronPropiedad, EnumEstadoPropiedad.Privada, cCar.ListarCaracteristicas(Caracteristicas), EnAlquiler, EnVenta);
        try{
            Usuario usr = cUsr.GetUsuario(IdUsuario);
            propiedad.setUsuarioPropiedad(usr);
            return mProp.CrearPropiedad(propiedad);
        }catch(NullPointerException ex){}
        return -1;
    }
    /**
     * Crea una propiedad Terreno sin caracteristicas.
     * @param DireccionPropiedad
     * @param PrecioPropiedad
     * @param MetrosConstruidosPropiedad
     * @param MetrosTerrenoPropiedad
     * @param NumeroPadronPropiedad
     * @param EnAlquiler
     * @param EnVenta
     * @param IdUsuario
     * @return
     */
    public int crearPropiedadTerreno(String DireccionPropiedad, float PrecioPropiedad, float MetrosConstruidosPropiedad,float MetrosTerrenoPropiedad,
            int NumeroPadronPropiedad,  boolean EnAlquiler, boolean EnVenta, int IdUsuario){
        Propiedad propiedad = new Terreno(DireccionPropiedad, PrecioPropiedad, MetrosConstruidosPropiedad, MetrosTerrenoPropiedad,
                NumeroPadronPropiedad, EnumEstadoPropiedad.Privada, EnAlquiler, EnVenta);
        try{
            Usuario usr = cUsr.GetUsuario(IdUsuario);
            propiedad.setUsuarioPropiedad(usr);
            return mProp.CrearPropiedad(propiedad);
        }catch(NullPointerException ex){}
        return -1;
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
    
    /**
     * Devuelve el nombre de la calle mas cercana al punto ingresado.
     * @param CoordX
     * @param CoordY
     * @return
     */
    public String ObtenerCalleCercana(float CoordX, float CoordY){
        return mProp.ObtenerCalleCercana(CoordX, CoordY);
    }
    /**
     * Devuelve el numero de puerta del punto ingresado.
     * @param CoordX
     * @param CoordY
     * @return
     */
    public String ObtenerNumeroPuerta(float CoordX, float CoordY){
        return mProp.ObtenerNumeroPuerta(CoordX, CoordY);
    }
    
    /**
     * Devuelve la direccion (calle + numero) del punto ingresado.
     * @param CoordX
     * @param CoordY
     * @return
     */
    public String ObtenerDireccion(float CoordX, float CoordY){
        return ObtenerCalleCercana(CoordX, CoordY) + " " + ObtenerNumeroPuerta(CoordX, CoordY);
    }
    
    /**
     * Actualiza los datos de la propiedad en la base de datos con la nueva informacion.
     * @param datosPropiedad propiedad con los nuevos datos.
     * @param CoordX
     * @param CoordY
     * @return Retorna el id de la propiedad si se actualizo. Retorna -1 si no se actualizo.
     */
    public int ModificarPropiedad(Propiedad datosPropiedad, float CoordX, float CoordY){
        Propiedad dbPropiedad = mProp.GetPropiedad(datosPropiedad.getIdPropiedad());
        dbPropiedad.setCaracteristicas(datosPropiedad.getCaracteristicas());
        dbPropiedad.setDireccionPropiedad(datosPropiedad.getDireccionPropiedad());
        dbPropiedad.setEnAlquiler(datosPropiedad.isEnAlquiler());
        dbPropiedad.setEnVenta(datosPropiedad.isEnVenta());
        dbPropiedad.setEstadoPropiedad(datosPropiedad.getEstadoPropiedad());
        dbPropiedad.setMetrosConstruidosPropiedad(datosPropiedad.getMetrosConstruidosPropiedad());
        dbPropiedad.setMetrosTerrenoPropiedad(datosPropiedad.getMetrosTerrenoPropiedad());
        dbPropiedad.setNumeroPadronPropiedad(datosPropiedad.getNumeroPadronPropiedad());
        dbPropiedad.setPrecioPropiedad(datosPropiedad.getPrecioPropiedad());
        if (mProp.InsertarUbicacionPropiedad(datosPropiedad.getIdPropiedad(), CoordX, CoordY)!=-1) {
            return mProp.ActualizarPropiedad(dbPropiedad);
        }
        return -1;
    }
    
    /**
     * Actualiza el estado de la propiedad.
     * @param IdPropiedad
     * @param EstadoPropiedad
     * @return Retorna el id de la propiedad si se actualizo. Retorna -1 si no se actualizo.
     */
    public int CambiarEstadoPropiedad(int IdPropiedad, EnumEstadoPropiedad EstadoPropiedad){
        Propiedad dbPropiedad = mProp.GetPropiedad(IdPropiedad);
        dbPropiedad.setEstadoPropiedad(EstadoPropiedad);
        return mProp.ActualizarPropiedad(dbPropiedad);
    }
    
    /**
     * Lista todas las propiedades registradas en la base de datos.
     * @return Retorna una lista vacia si no hay propiedades registradas.
     */
    public List<Propiedad> ListarPropiedades(){
        return mProp.ListarPropiedades();
    }
    
    /**
     * Lista todos los terrenos registrados en la base de datos.
     * @return Retorna una lista vacia si no hay terrnos registrados.
     */
    public List<Propiedad> ListarTerrenos(){
        return mProp.ListarTerrenos();
    }
    
    /**
     * Lista todas las casas registradas en la base de datos.
     * @return Retorna una lista vacia si no hay casas registradas.
     */
    public List<Propiedad> ListarCasas(){
        return mProp.ListarCasas();
    }
    
    /**
     * Lista todos los apartamentos registrados en la base de datos.
     * @return Retorna una lista vacia si no hay apartamentos registrados.
     */
    public List<Propiedad> ListarApartamentos(){
        return mProp.ListarApartamentos();
    }
    
    /**
     * Lista todas las propiedades registradas por el usuario.
     * @param IdUsuario
     * @return Retorna una lista vacia si no hay propiedades registradas.
     */
    public List<Propiedad> ListarPropiedadesUsuario(int IdUsuario){
        return mProp.ListarPropiedadesUsuario(IdUsuario);
    }
}
