
package interfaz.usuario;

import inmueble.Inmueble;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import propiedad.ControladorPropiedad;
import propiedad.Propiedad;
import propiedad.caracteristica.Caracteristica;
import propiedad.caracteristica.ControladorCaracteristica;
import propiedad.enums.EnumTipoInmueble;
import terreno.Terreno;
import usuario.Usuario;

@Named
@ViewScoped
public class InformacionPropiedad implements Serializable{
    @EJB
    private ControladorPropiedad cProp;
    @EJB
    private ControladorCaracteristica cCar;
    @Inject
    private Login login;
    
    private String TipoPropiedad;
    private int idPropiedad;
    private String DireccionPropiedad;
    private float PrecioPropiedad;
    private float MetrosConstruidosPropiedad;
    private float MetrosTerrenoPropiedad;
    private int NumeroPadronPropiedad;
    private boolean EnAlquiler;
    private boolean EnVenta;
    private String CoordX;
    private String CoordY;
    private String EstadoPropiedad;
    private List<Caracteristica> listaCaracteristica;
    private Map<Integer, Boolean> listChecked;
    
    //  Inmueble
    private int CantidadDormitorios;
    private int CantidadBanios;
    
    //Usuario
    private int IdUsuario;
    private String NombreUsuario;
    private String CorreoUsuario;
    
    // puntos de interes
    private Map<String, Integer> PuntosInteres;
    
    //  Getters
    public String getTipoPropiedad() {return TipoPropiedad;}
    public int getIdPropiedad() {return idPropiedad;}
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
    public String getNombreUsuario() {return NombreUsuario;}
    public String getCorreoUsuario() {return CorreoUsuario;}
    public String getEstadoPropiedad() {return EstadoPropiedad;}
    public Map<String, Integer> getPuntosInteres() {return PuntosInteres;}
    
    //  Setter
    public void setTipoPropiedad(String TipoPropiedad) {this.TipoPropiedad = TipoPropiedad;}
    public void setIdPropiedad(int idPropiedad) {this.idPropiedad = idPropiedad;}
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
    public void setNombreUsuario(String NombreUsuario) {this.NombreUsuario = NombreUsuario;}
    public void setCorreoUsuario(String CorreoUsuario) {this.CorreoUsuario = CorreoUsuario;}
    public void setEstadoPropiedad(String EstadoPropiedad) {this.EstadoPropiedad = EstadoPropiedad;}
    public void setPuntosInteres(Map<String, Integer> PuntosInteres) {this.PuntosInteres = PuntosInteres;}
    
    @PostConstruct
    public void init(){
        this.listaCaracteristica = cCar.listarCaracteristicas();
        this.listChecked = new HashMap<>();
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        int IdPropiedad = Integer.parseInt(request.getParameter("id"));
        Propiedad propiedad = cProp.ObtenerPropiedadPorId(IdPropiedad);
        setTipoPropiedad(propiedad);
        setCaracteristicasMarcadas(propiedad);
        PuntosInteres = new HashMap<>();

        try{
            if (!this.TipoPropiedad.equals("Terreno")) {
                this.CantidadBanios =((Inmueble)propiedad).getCantidadBanios();
                this.CantidadDormitorios =((Inmueble)propiedad).getCantidadDormitorios();
            }
            this.DireccionPropiedad = propiedad.getDireccionPropiedad();
            this.EnAlquiler = propiedad.isEnAlquiler();
            this.EnVenta = propiedad.isEnVenta();
            this.MetrosConstruidosPropiedad = propiedad.getMetrosConstruidosPropiedad();
            this.MetrosTerrenoPropiedad = propiedad.getMetrosTerrenoPropiedad();
            this.PrecioPropiedad = propiedad.getPrecioPropiedad();
            Usuario usuario = propiedad.getUsuarioPropiedad();
            this.IdUsuario = usuario.getIdUsuario();
            this.NombreUsuario = usuario.getNicknameUsuario();
            this.CorreoUsuario = usuario.getCorreoUsuario();
            this.EstadoPropiedad = propiedad.getEstadoPropiedad().toString();
            
            PuntosInteres = cProp.GetDistanciasPuntosInteres(IdPropiedad);
            try{
                if(!login.getUsuarioLogueado())cProp.AgregarVisitaPropiedad(IdPropiedad);
            }catch(NullPointerException ex){}
            
        }catch(Exception ex){}
    }
    
    private void setCaracteristicasMarcadas(Propiedad propiedad){
        List<Caracteristica> caracteristicasMarcadas= propiedad.getCaracteristicas();
        for(Caracteristica car: listaCaracteristica){
            listChecked.put(car.getIdCaracteristica(), Boolean.FALSE);
        }
        for(Caracteristica car: caracteristicasMarcadas){
            listChecked.replace(car.getIdCaracteristica(), Boolean.TRUE);
        }
    }
    
    private void setTipoPropiedad(Propiedad propiedad){
        if (propiedad instanceof Terreno) {
            this.TipoPropiedad = "Terreno";
        }else{
            if (propiedad instanceof inmueble.Inmueble) {
                if (((Inmueble)propiedad).getTipoInmueble().equals(EnumTipoInmueble.Casa)) {
                    this.TipoPropiedad = "Casa";
                }else{
                    this.TipoPropiedad = "Apartamento";
                }
            }
        }
    }
    
}
