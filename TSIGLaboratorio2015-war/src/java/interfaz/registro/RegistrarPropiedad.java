
package interfaz.registro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import propiedad.caracteristica.Caracteristica;
import propiedad.caracteristica.ControladorCaracteristica;
import propiedad.enums.EnumTipoInmueble;

@Named
@ViewScoped
public class RegistrarPropiedad implements Serializable {
    @EJB 
    private ControladorCaracteristica cCar;
    
    private List<String> TipoPropiedad;
    private String TipoPropiedadSeleccionado;
    
    private String CoordX;
    private String CoordY;
    private String DireccionPropiedad;
    private float PrecioPropiedad;
    private float MetrosConstruidosPropiedad;
    private float MetrosTerrenoPropiedad;
    private int NumeroPadronPropiedad;
    
    private List<String> strEnumTipoInmueble;
    private String strEnumTipoInmuebleSeleccionado;
    
    //  Inmueble
    private EnumTipoInmueble TipoInmueble;
    private int CantidadDormitorios;
    private int CantidadBanios;
    /**
     * Lista de Caracteristicas para utilizarse desde la pagina para registrar las caracteristicas del inmueble
     */
    private static List<BeanCaracteristica> ListaCaracteristicas;
    
      
    //  Setters
    public void setTipoPropiedadSeleccionado(String TipoPropiedadSeleccionado) {this.TipoPropiedadSeleccionado = TipoPropiedadSeleccionado;}
    public void setTipoPropiedad(List<String> TipoPropiedad) {this.TipoPropiedad = TipoPropiedad;}
    public void setCoordX(String CoordX) {this.CoordX = CoordX;}
    public void setCoordY(String CoordY) {this.CoordY = CoordY;}
    public void setDireccionPropiedad(String DireccionPropiedad) {this.DireccionPropiedad = DireccionPropiedad;}
    public void setPrecioPropiedad(float PrecioPropiedad) {this.PrecioPropiedad = PrecioPropiedad;}
    public void setMetrosConstruidosPropiedad(float MetrosConstruidosPropiedad) {this.MetrosConstruidosPropiedad = MetrosConstruidosPropiedad;}
    public void setMetrosTerrenoPropiedad(float MetrosTerrenoPropiedad) {this.MetrosTerrenoPropiedad = MetrosTerrenoPropiedad;}
    public void setNumeroPadronPropiedad(int NumeroPadronPropiedad) {this.NumeroPadronPropiedad = NumeroPadronPropiedad;}
    
    public void setCantidadDormitorios(int CantidadDormitorios) {this.CantidadDormitorios = CantidadDormitorios;}
    public void setCantidadBanios(int CantidadBanios) {this.CantidadBanios = CantidadBanios;}
    
    public void setStrEnumTipoInmueble(List<String> strEnumTipoInmueble) {this.strEnumTipoInmueble = strEnumTipoInmueble;}
    public void setStrEnumTipoInmuebleSeleccionado(String strEnumTipoInmuebleSeleccionado) {
        this.strEnumTipoInmuebleSeleccionado = strEnumTipoInmuebleSeleccionado;
        this.TipoInmueble = EnumTipoInmueble.valueOf(strEnumTipoInmuebleSeleccionado);
    }
    
    public static void setListaCaracteristicas(List<BeanCaracteristica> ListaCaracteristicas) {RegistrarPropiedad.ListaCaracteristicas = ListaCaracteristicas;}
    
     
    //  Getters
    public String getTipoPropiedadSeleccionado() {return TipoPropiedadSeleccionado;}
    public List<String> getTipoPropiedad() {return TipoPropiedad;}
    public String getCoordX() {return CoordX;}
    public String getCoordY() {return CoordY;}
    public String getDireccionPropiedad() {return DireccionPropiedad;}
    public float getPrecioPropiedad() {return PrecioPropiedad;}
    public float getMetrosConstruidosPropiedad() {return MetrosConstruidosPropiedad;}
    public float getMetrosTerrenoPropiedad() {return MetrosTerrenoPropiedad;}
    public int getNumeroPadronPropiedad() {return NumeroPadronPropiedad;}
    
    public int getCantidadDormitorios() {return CantidadDormitorios;}
    public int getCantidadBanios() {return CantidadBanios;}
    
    public List<String> getStrEnumTipoInmueble() {return strEnumTipoInmueble;}
    public String getStrEnumTipoInmuebleSeleccionado() {return strEnumTipoInmuebleSeleccionado;}
    
    public static List<BeanCaracteristica> getListaCaracteristicas() {return ListaCaracteristicas;}
    
    @PostConstruct
    public void init(){
        this.TipoPropiedad = new ArrayList<>();
        this.TipoPropiedad.add("Innmueble");
        this.TipoPropiedad.add("Terreno");
        
        /**
         * Llenar con las caracteristicas que estan registradas en la base de datos.
        */
        RegistrarPropiedad.ListaCaracteristicas = new ArrayList<>();
        List<Caracteristica> caracteristicas = cCar.listarCaracteristicas();
        for (int i = 0; i < caracteristicas.size(); i++) {
            ListaCaracteristicas.add(new BeanCaracteristica(caracteristicas.get(i).getIdCaracteristica(), caracteristicas.get(i).getNombreCaracteristica()));
        }
        
    }
    
    
    //  inner classBean de Caracteristicas
    
    public static class BeanCaracteristica{
        private int IdCaracteristica;
        private String NombreCaracteristica;
        
        //  Constructor
        public BeanCaracteristica(int IdCaracteristica, String NombreCaracteristica) {
            this.IdCaracteristica = IdCaracteristica;
            this.NombreCaracteristica = NombreCaracteristica;
        }
        //  Getters & Setters
        
        public int getIdCaracteristica() {return IdCaracteristica;}
        public void setIdCaracteristica(int IdCaracteristica) {this.IdCaracteristica = IdCaracteristica;}
        public String getNombreCaracteristica() {return NombreCaracteristica;}
        public void setNombreCaracteristica(String NombreCaracteristica) {this.NombreCaracteristica = NombreCaracteristica;}
        
        
    }
    
    
}
