
package interfaz.registro;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import propiedad.ControladorPropiedad;
import propiedad.caracteristica.Caracteristica;
import propiedad.caracteristica.ControladorCaracteristica;
import usuario.Usuario;

@Named
@ManagedBean
@ViewScoped
public class RegistrarPropiedad implements Serializable {
    @EJB
    private ControladorCaracteristica cCar;
    @EJB
    private ControladorPropiedad cProp;
    @EJB
    private FileUpload fUp;
    
    private boolean Exito;
    
    private String[] TipoPropiedades;
    private String TipoPropiedadSeleccionado;
    private String CoordX;
    private String CoordY;
    private String DireccionPropiedad;
    private float PrecioPropiedad;
    private float MetrosConstruidosPropiedad;
    private float MetrosTerrenoPropiedad;
    private int NumeroPadronPropiedad;
    private List<Caracteristica> listaCaracteristica;
    private boolean EnAlquiler;
    private boolean EnVenta;
    private Part PartImagenUno;
    private Part PartImagenDos;
    private Part PartImagenTres;
//  Inmueble
    private int CantidadDormitorios;
    private int CantidadBanios;
    /**
     * Lista de Caracteristicas para utilizarse desde la pagina para registrar las caracteristicas del inmueble
     */
    private Map<Integer, Boolean> listChecked;
    
    //  Getters
    public String getTipoPropiedadSeleccionado() {return TipoPropiedadSeleccionado;}
    public String getCoordX() {return CoordX;}
    public String getCoordY() {return CoordY;}
    public String getDireccionPropiedad() {return DireccionPropiedad;}
    public float getPrecioPropiedad() {return PrecioPropiedad;}
    public float getMetrosConstruidosPropiedad() {return MetrosConstruidosPropiedad;}
    public float getMetrosTerrenoPropiedad() {return MetrosTerrenoPropiedad;}
    public int getNumeroPadronPropiedad() {return NumeroPadronPropiedad;}
    public int getCantidadDormitorios() {return CantidadDormitorios;}
    public int getCantidadBanios() {return CantidadBanios;}
    public List<Caracteristica> getListaCaracteristica() {return listaCaracteristica;}
    public Map<Integer, Boolean> getListChecked() {return listChecked;}
    public boolean isEnAlquiler() {return EnAlquiler;}
    public boolean isEnVenta() {return EnVenta;}
    public String[] getTipoPropiedades() {return TipoPropiedades;}
    public Part getPartImagenUno() {return PartImagenUno;}
    public Part getPartImagenDos() {return PartImagenDos;}
    public Part getPartImagenTres() {return PartImagenTres;}
    public boolean isExito() {return Exito;}
    
    //  Setters
    public void setTipoPropiedadSeleccionado(String TipoPropiedadSeleccionado) {this.TipoPropiedadSeleccionado = TipoPropiedadSeleccionado;}
    public void setCoordX(String CoordX) {this.CoordX = CoordX;}
    public void setCoordY(String CoordY) {this.CoordY = CoordY;}
    public void setDireccionPropiedad(String DireccionPropiedad) {this.DireccionPropiedad = DireccionPropiedad;}
    public void setPrecioPropiedad(float PrecioPropiedad) {this.PrecioPropiedad = PrecioPropiedad;}
    public void setMetrosConstruidosPropiedad(float MetrosConstruidosPropiedad) {this.MetrosConstruidosPropiedad = MetrosConstruidosPropiedad;}
    public void setMetrosTerrenoPropiedad(float MetrosTerrenoPropiedad) {this.MetrosTerrenoPropiedad = MetrosTerrenoPropiedad;}
    public void setNumeroPadronPropiedad(int NumeroPadronPropiedad) {this.NumeroPadronPropiedad = NumeroPadronPropiedad;}
    public void setCantidadDormitorios(int CantidadDormitorios) {this.CantidadDormitorios = CantidadDormitorios;}
    public void setCantidadBanios(int CantidadBanios) {this.CantidadBanios = CantidadBanios;}
    public void setListChecked(Map<Integer, Boolean> listChecked) {this.listChecked = listChecked;}
    public void setListaCaracteristica(List<Caracteristica> listaCaracteristica) {this.listaCaracteristica = listaCaracteristica;}
    public void setEnAlquiler(boolean EnAlquiler) {this.EnAlquiler = EnAlquiler;}
    public void setEnVenta(boolean EnVenta) {this.EnVenta = EnVenta;}
    public void setTipoPropiedades(String[] TipoPropiedades) {this.TipoPropiedades = TipoPropiedades;}
    public void setPartImagenUno(Part PartImagenUno) {this.PartImagenUno = PartImagenUno;}
    public void setPartImagenDos(Part PartImagenDos) {this.PartImagenDos = PartImagenDos;}
    public void setPartImagenTres(Part PartImagenTres) {this.PartImagenTres = PartImagenTres;}
    public void setExito(boolean Exito) {this.Exito = Exito;}
    
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
    
    public void obtenerDireccion(String coordX,String coordY){
        String Direccion = "";
        try{
            Direccion = cProp.ObtenerDireccion(Float.parseFloat(CoordX), Float.parseFloat(this.CoordY));
            this.DireccionPropiedad = Direccion;
        }catch(NullPointerException | NumberFormatException e){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("frmProp:inputDireccion", new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "No se pudo ubicar la direccion"));
        }
    }
    
    //  Registro
    public void registrarPropiedad() throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String url = context.getExternalContext().getRequestContextPath();
        int idUsuario = ((Usuario)request.getSession().getAttribute("Usuario")).getIdUsuario();
        int id = -1;
        switch(this.TipoPropiedadSeleccionado){
            case "Casa":
                id = cProp.crearPropiedadCasa(CantidadDormitorios, CantidadBanios, DireccionPropiedad, PrecioPropiedad, MetrosConstruidosPropiedad,
                        MetrosTerrenoPropiedad, NumeroPadronPropiedad, getCaracteristicasMarcadas(), this.EnAlquiler, this.EnVenta, idUsuario);
                break;
            case "Apartamento":
                id = cProp.crearPropiedadApto(CantidadDormitorios, CantidadBanios, DireccionPropiedad, PrecioPropiedad, MetrosConstruidosPropiedad,
                        MetrosTerrenoPropiedad, NumeroPadronPropiedad, getCaracteristicasMarcadas(), EnAlquiler, EnVenta, idUsuario);
                break;
            case "Terreno":
                id = cProp.crearPropiedadTerreno(DireccionPropiedad, PrecioPropiedad, MetrosConstruidosPropiedad, MetrosTerrenoPropiedad, NumeroPadronPropiedad,
                        getCaracteristicasMarcadas(), EnAlquiler, EnVenta, idUsuario);
                break;
        }
        if (id!=-1) {
            cProp.InsertarUbicacionPropiedad(id, Float.parseFloat(CoordX), Float.parseFloat(this.CoordY));
            String imagenuno = fUp.guardarArchivo(String.valueOf(id), PartImagenUno, "uno");
            String imagendos = fUp.guardarArchivo(String.valueOf(id), PartImagenDos, "dos");
            String imagentres = fUp.guardarArchivo(String.valueOf(id), PartImagenTres, "tres");
            cProp.SetearImagenes(imagenuno,imagendos,imagentres,id);
            Exito = true;
        }
    }
    
    @PostConstruct
    public void init(){
        
        /**
         * Llenar con las caracteristicas que estan registradas en la base de datos.
         */
        this.listaCaracteristica = cCar.listarCaracteristicas();
        listChecked = new HashMap<>();
        for (int i = 0; i < listaCaracteristica.size(); i++) {
            listChecked.put(listaCaracteristica.get(i).getIdCaracteristica(), Boolean.FALSE);
        }
        this.TipoPropiedades = new String[]{"Casa", "Apartamento", "Terreno" };
        Exito = false;
    }
    
    
}
