
package interfaz.admin;

import inmueble.Inmueble;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
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
import propiedad.enums.EnumTipoInmueble;
import terreno.Terreno;
import usuario.Usuario;

@Named
@ViewScoped
public class AdminPropiedades implements Serializable{
    @EJB
    private ControladorPropiedad cProp;
    @EJB
    private ControladorCaracteristica cCar;
    private Propiedad propiedad;
    
    private String TipoPropiedad;
    private String idPropiedad;
    private String DireccionPropiedad;
    private float PrecioPropiedad;
    private float MetrosConstruidosPropiedad;
    private float MetrosTerrenoPropiedad;
    private int NumeroPadronPropiedad;
    private boolean EnAlquiler;
    private boolean EnVenta;
    private int TotalVisitas;
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
    public Propiedad getPropiedad() {return propiedad;}
    public String getIdPropiedad() {return idPropiedad;}
    public String getTipoPropiedad() {return TipoPropiedad;}
    public int getTotalVisitas() {return TotalVisitas;}
    
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
    public void setPropiedad(Propiedad propiedad) {this.propiedad = propiedad;}
    public void setIdPropiedad(String idPropiedad) {this.idPropiedad = idPropiedad;}
    public void setTipoPropiedad(String TipoPropiedad) {this.TipoPropiedad = TipoPropiedad;}
    public void setTotalVisitas(int TotalVisitas) {this.TotalVisitas = TotalVisitas;}
    
    public void modificarPropiedad(){
        propiedad.setDireccionPropiedad(DireccionPropiedad);
        propiedad.setEnAlquiler(EnAlquiler);
        propiedad.setEnVenta(EnVenta);
        propiedad.setEstadoPropiedad(EnumEstadoPropiedad.valueOf(estadoSeleccionado));
        propiedad.setMetrosConstruidosPropiedad(MetrosConstruidosPropiedad);
        propiedad.setMetrosTerrenoPropiedad(MetrosTerrenoPropiedad);
        propiedad.setPrecioPropiedad(PrecioPropiedad);
        List<Caracteristica> cars = cCar.ListarCaracteristicas(getCaracteristicasMarcadas());
        propiedad.setCaracteristicas(cars);
        propiedad.setEstadoPropiedad(EnumEstadoPropiedad.valueOf(estadoSeleccionado));
        if(!(propiedad instanceof Terreno)){
            ((Inmueble)propiedad).setCantidadBanios(CantidadBanios);
            ((Inmueble)propiedad).setCantidadDormitorios(CantidadDormitorios);            
        }
        cProp.ModificarPropiedad(propiedad, Float.parseFloat(CoordX), Float.parseFloat(CoordY));
    }
    
    public void actualizarDatosBean(String IdPropiedad){
        this.propiedad = cProp.ObtenerPropiedadPorId(Integer.parseInt(IdPropiedad));
        this.EnVenta = propiedad.isEnVenta();
        this.EnAlquiler = propiedad.isEnAlquiler();
        this.estadoSeleccionado = propiedad.getEstadoPropiedad().name();
        this.TotalVisitas = propiedad.getVisitasPropiedad();
        setTipoPropiedad();
        setCaracteristicasMarcadas();
        this.DireccionPropiedad = propiedad.getDireccionPropiedad();
    }
    public void cambiarDir(String IdPropiedad){
        this.propiedad = cProp.ObtenerPropiedadPorId(Integer.parseInt(IdPropiedad));
        this.EnVenta = propiedad.isEnVenta();
        this.EnAlquiler = propiedad.isEnAlquiler();
        this.estadoSeleccionado = propiedad.getEstadoPropiedad().name();
        this.TotalVisitas = propiedad.getVisitasPropiedad();
        setTipoPropiedad();
        setCaracteristicasMarcadas();
        String dir = cProp.ObtenerDireccion(Float.parseFloat(CoordX), Float.parseFloat(CoordY));
        this.DireccionPropiedad = dir;
    }
    
    private void setTipoPropiedad(){
        if (propiedad instanceof Terreno) {
            this.TipoPropiedad = "Terreno";
        }else{
            if (propiedad instanceof inmueble.Inmueble) {
                if (((Inmueble)propiedad).getTipoInmueble().equals(EnumTipoInmueble.Casa)) {
                    this.TipoPropiedad = "Casa";
                }else{
                    this.TipoPropiedad = "Apartamento";//
                }
            }
        }
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
        this.listChecked = new HashMap<>();
    }
    
     /**
     * Retorna la lista con las caracteristicas selaccionadas
     * @return Retorna la lista vacia en caso de ninguna seleccionada
     */
    private  List<Integer> getCaracteristicasMarcadas(){
        List<Integer> caracteristicasMarcadas = new ArrayList<>();
        for (Map.Entry e : listChecked.entrySet()) {
            boolean valor = (boolean)e.getValue();
            int Key = (int) e.getKey();
            if ( valor ) caracteristicasMarcadas.add((int)e.getKey());
        }
        return caracteristicasMarcadas;
    }
    
    private void setCaracteristicasMarcadas(){
        List<Caracteristica> caracteristicasMarcadas= propiedad.getCaracteristicas();
        for(Caracteristica car: listaCaracteristica){
            listChecked.put(car.getIdCaracteristica(), Boolean.FALSE);
        }
        for(Caracteristica car: caracteristicasMarcadas){
            listChecked.replace(car.getIdCaracteristica(), Boolean.TRUE);
        }
    }
}
