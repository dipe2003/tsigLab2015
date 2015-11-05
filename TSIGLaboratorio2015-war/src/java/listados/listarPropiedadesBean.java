
package listados;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import propiedad.ControladorPropiedad;
import propiedad.Propiedad;

@Named
@ViewScoped
public class listarPropiedadesBean implements Serializable{
    @EJB
    private ControladorPropiedad cProp;
    
    private List<Propiedad> Propiedades;
    private String[] TipoPropiedad;
    private String TipoSeleccionado;
    
    //  Getters
    public List<Propiedad> getPropiedades() {return Propiedades;}
    public String[] getTipoPropiedad() {return TipoPropiedad;}
    public String getTipoSeleccionado() {return TipoSeleccionado;}
    
    //  Setters
    public void setPropiedades(List<Propiedad> Propiedades) {this.Propiedades = Propiedades;}
    public void setTipoPropiedad(String[] TipoPropiedad) {this.TipoPropiedad = TipoPropiedad;}
    public void setTipoSeleccionado(String TipoSeleccionado) {this.TipoSeleccionado = TipoSeleccionado;}
    
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
    
    @PostConstruct
    public void init(){
        Propiedades = new ArrayList<>();
        Propiedades = cProp.ListarPropiedades();
        this.TipoPropiedad = new String[]{"Todas", "Casas", "Apartamentos", "Terrenos" };
    }
    
    
}