
package interfaz.registro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import propiedad.ControladorPropiedad;
import propiedad.caracteristica.Caracteristica;
import propiedad.caracteristica.ControladorCaracteristica;

@Named
@ViewScoped
public class RegistrarPropiedad implements Serializable {
    @EJB
    private ControladorCaracteristica cCar;
    @EJB
    private ControladorPropiedad cProp;
    
    private String TipoPropiedadSeleccionado;
    
    private String CoordX;
    private String CoordY;
    private String DireccionPropiedad;
    private float PrecioPropiedad;
    private float MetrosConstruidosPropiedad;
    private float MetrosTerrenoPropiedad;
    private int NumeroPadronPropiedad;
    
    //  Inmueble
    private int CantidadDormitorios;
    private int CantidadBanios;
    /**
     * Lista de Caracteristicas para utilizarse desde la pagina para registrar las caracteristicas del inmueble
     */    
    private Map<Integer, Boolean> listChecked;
    
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
    
    public Map<Integer, Boolean> getListChecked() {return listChecked;}
    
    /**
     * Retorna la lista con las caracteristicas selaccionadas
     * @return Retorna la lista vacia en caso de ninguna seleccionada
     */
    private  List<Integer> getCaracteristicasMarcadas(){
        List<Integer> caracteristicasMarcadas = new ArrayList<>();
        for (int i = 0; i < listChecked.size(); i++) {
            if (this.listChecked.get(i)) {
                caracteristicasMarcadas.add(i);
            }
        }
        return caracteristicasMarcadas;
    }
    
    //  Registro
    public String registrarPropiedad(){
        int id = -1;
        switch(this.TipoPropiedadSeleccionado){
            case "Casa":
                id = cProp.crearPropiedadCasa(CantidadDormitorios, CantidadBanios, DireccionPropiedad, PrecioPropiedad, MetrosConstruidosPropiedad,
                        MetrosTerrenoPropiedad, NumeroPadronPropiedad, getCaracteristicasMarcadas());
                break;
            case "Apartamento":
                id = cProp.crearPropiedadApto(CantidadDormitorios, CantidadBanios, DireccionPropiedad, PrecioPropiedad, MetrosConstruidosPropiedad,
                        MetrosTerrenoPropiedad, NumeroPadronPropiedad, getCaracteristicasMarcadas());
                break;
            case "Terreno":
                id = cProp.crearPropiedadTerreno(DireccionPropiedad, PrecioPropiedad, MetrosConstruidosPropiedad, MetrosTerrenoPropiedad, NumeroPadronPropiedad);
                break;
        }
        if (id!=-1) {
            cProp.InsertarUbicacionPropiedad(id, Integer.parseInt(CoordX), Integer.parseInt(this.CoordY));
            return "registrada";
        }
        return "";
    }
    
    @PostConstruct
    public void init(){
        
        /**
         * Llenar con las caracteristicas que estan registradas en la base de datos.
         */
        List<Caracteristica> caracteristicas = cCar.listarCaracteristicas();
        listChecked = new HashMap<>();
        for (int i = 0; i < caracteristicas.size(); i++) {
            listChecked.put(caracteristicas.get(i).getIdCaracteristica(), Boolean.FALSE);
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
