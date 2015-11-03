
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
import usuario.Usuario;

@Named
@ViewScoped
public class adminPropiedadesBean implements Serializable{
    @EJB
    private ControladorPropiedad cProp;
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
    private List<Propiedad> Propiedades;
    private String DirSeleccionada;
    private String[] TipoPropiedad;
    private String TipoSeleccionado;
    private String CoordX;
    private String CoordY;
    private List<Caracteristica> listaCaracteristica;
    private Map<Integer, Boolean> listChecked;
    
    //  Getters
    public List<Propiedad> getPropiedades() {return Propiedades;}
    public String[] getTipoPropiedad() {return TipoPropiedad;}
    public String getTipoSeleccionado() {return TipoSeleccionado;}
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
    public String getDirSeleccionada() {return DirSeleccionada;}
    
    //  Setters
    public void setPropiedades(List<Propiedad> Propiedades) {this.Propiedades = Propiedades;}
    public void setTipoPropiedad(String[] TipoPropiedad) {this.TipoPropiedad = TipoPropiedad;}
    public void setTipoSeleccionado(String TipoSeleccionado) {this.TipoSeleccionado = TipoSeleccionado;}
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
    public void setDirSeleccionada(String DirSeleccionada) {this.DirSeleccionada = DirSeleccionada;}
    
    //
    public void cambiarLista(){
        switch(TipoSeleccionado){
            case "Casas":
                Propiedades = cProp.ListarCasas();
                break;
            case "Apartamentos":
                Propiedades = cProp.ListarApartamentos();
                break;
            case"Terrenos":
                Propiedades = cProp.ListarTerrenos();
                break;
            default:
                Propiedades = cProp.ListarPropiedades();
                break;
        }
    }
    
    
    public void modificarPropiedad(){}
    
    @PostConstruct
    public void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        int idUsuario = ((Usuario)request.getSession().getAttribute("Usuario")).getIdUsuario();
        Propiedades = new ArrayList<>();
        Propiedades = cProp.ListarPropiedadesUsuario(idUsuario);
        this.TipoPropiedad = new String[]{"Todas", "Casas", "Apartamentos", "Terrenos" };
    }
    
    
}
