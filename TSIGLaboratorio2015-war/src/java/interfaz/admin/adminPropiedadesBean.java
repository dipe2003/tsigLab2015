
package interfaz.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import propiedad.ControladorPropiedad;
import propiedad.Propiedad;
import propiedad.caracteristica.Caracteristica;
import propiedad.caracteristica.ControladorCaracteristica;
import propiedad.enums.EnumEstadoPropiedad;
import usuario.Usuario;

@Named
@ViewScoped
public class adminPropiedadesBean implements Serializable{
    @EJB
    private ControladorPropiedad cProp;
    @EJB
    private ControladorCaracteristica cCar;
    
    private String DireccionPropiedad;
    private float PrecioPropiedad;
    private float MetrosConstruidosPropiedad;
    private float MetrosTerrenoPropiedad;
    private int NumeroPadronPropiedad;
    private boolean EnAlquiler;
    private boolean EnVenta;
    //  Inmueble
    private int CantidadDormitorios;
    private int CantidadBanios;
    private List<String> Propiedades;
    private String CoordX;
    private String CoordY;
    private List<Caracteristica> listaCaracteristica;
    private Map<Integer, Boolean> listChecked;
    private List<String> listEstado;
    private String estadoSeleccionado;
    //Usuario
    private int IdUsuario;
    
    //  Getters
    public List<String> getPropiedades() {return Propiedades;}
    public String getDireccionPropiedad() {return DireccionPropiedad;}
    public float getPrecioPropiedad() {return PrecioPropiedad;}
    public float getMetrosConstruidosPropiedad() {return MetrosConstruidosPropiedad;}
    public float getMetrosTerrenoPropiedad() {return MetrosTerrenoPropiedad;}
    public int getNumeroPadronPropiedad() {return NumeroPadronPropiedad;}
    public boolean isEnAlquiler() {return EnAlquiler;}
    public boolean isEnVenta() {return EnVenta;}
    public int getCantidadDormitorios() {return CantidadDormitorios;}
    public int getCantidadBanios() {return CantidadBanios;}
    public String getCoordX() {return CoordX;}
    public String getCoordY() {return CoordY;}
    public List<Caracteristica> getListaCaracteristica() {return listaCaracteristica;}
    public Map<Integer, Boolean> getListChecked() {return listChecked;}
    public int getIdUsuario() {return IdUsuario;}
    public List<String> getListEstado() {return listEstado;}
    public String getEstadoSeleccionado() {return estadoSeleccionado;}
    
    //  Setters
    public void setPropiedades(List<String> Propiedades) {this.Propiedades = Propiedades;}
    public void setDireccionPropiedad(String DireccionPropiedad) {this.DireccionPropiedad = DireccionPropiedad;}
    public void setPrecioPropiedad(float PrecioPropiedad) {this.PrecioPropiedad = PrecioPropiedad;}
    public void setMetrosConstruidosPropiedad(float MetrosConstruidosPropiedad) {this.MetrosConstruidosPropiedad = MetrosConstruidosPropiedad;}
    public void setMetrosTerrenoPropiedad(float MetrosTerrenoPropiedad) {this.MetrosTerrenoPropiedad = MetrosTerrenoPropiedad;}
    public void setNumeroPadronPropiedad(int NumeroPadronPropiedad) {this.NumeroPadronPropiedad = NumeroPadronPropiedad;}
    public void setEnAlquiler(boolean EnAlquiler) {this.EnAlquiler = EnAlquiler;}
    public void setEnVenta(boolean EnVenta) {this.EnVenta = EnVenta;}
    public void setCantidadDormitorios(int CantidadDormitorios) {this.CantidadDormitorios = CantidadDormitorios;}
    public void setCantidadBanios(int CantidadBanios) {this.CantidadBanios = CantidadBanios;}
    public void setCoordX(String CoordX) {this.CoordX = CoordX;}
    public void setCoordY(String CoordY) {this.CoordY = CoordY;}
    public void setListaCaracteristica(List<Caracteristica> listaCaracteristica) {this.listaCaracteristica = listaCaracteristica;}
    public void setListChecked(Map<Integer, Boolean> listChecked) {this.listChecked = listChecked;}
    public void setIdUsuario(int IdUsuario) {this.IdUsuario = IdUsuario;}
    public void setListEstado(List<String> listEstado) {this.listEstado = listEstado;}
    public void setEstadoSeleccionado(String estadoSeleccionado) {this.estadoSeleccionado = estadoSeleccionado;}
    
    public void modificarPropiedad(){
        Propiedad prop = cProp.ObtenerPropiedadPorDireccion(DireccionPropiedad);
        prop.setDireccionPropiedad(DireccionPropiedad);
        prop.setEnAlquiler(EnAlquiler);
        prop.setEnVenta(EnVenta);
        prop.setEstadoPropiedad(EnumEstadoPropiedad.valueOf(estadoSeleccionado));
        prop.setMetrosConstruidosPropiedad(MetrosConstruidosPropiedad);
        prop.setMetrosTerrenoPropiedad(MetrosTerrenoPropiedad);
        prop.setPrecioPropiedad(PrecioPropiedad);
        //cProp.ModificarPropiedad(prop, coordX, coordY);
    }
    
    @PostConstruct
    public void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        this.IdUsuario = ((Usuario)request.getSession().getAttribute("Usuario")).getIdUsuario();
        List<Propiedad> props = cProp.ListarPropiedadesUsuario(IdUsuario);
        this.Propiedades = new ArrayList<>();
        for(Propiedad prop: props){
            this.Propiedades.add(prop.getDireccionPropiedad());
        }
        this.listaCaracteristica = cCar.listarCaracteristicas();
        listEstado = new ArrayList<>();
        for (int i = 0; i < EnumEstadoPropiedad.values().length; i++) {
            listEstado.add(EnumEstadoPropiedad.values()[i].toString());
        }
    }
    
    
}
