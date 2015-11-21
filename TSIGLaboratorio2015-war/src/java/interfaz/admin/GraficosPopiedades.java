
package interfaz.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import propiedad.ControladorPropiedad;

@Named
@ViewScoped
public class GraficosPopiedades implements Serializable{
    @EJB
    private ControladorPropiedad cProp;
    
    private int TotalPropiedades;
    private Map<String, Integer> PropiedadesVisitas;
    private String Labels;
    private String Datos;
    //  Getters
    public int getTotalPropiedades() {return TotalPropiedades;}    
    public Map<String, Integer> getPropiedadesVisitas() {return PropiedadesVisitas;}

    public String getLabels() {
        return Labels;
    }

    public String getDatos() {
        return Datos;
    }
    
    //  Setters
    public void setTotalPropiedades(int TotalPropiedades) {this.TotalPropiedades = TotalPropiedades;}
    public void setPropiedadesVisitas(Map<String, Integer> PropiedadesVisitas) {this.PropiedadesVisitas = PropiedadesVisitas;}

    public void setLabels(String Labels) {
        this.Labels = Labels;
    }

    public void setDatos(String Datos) {
        this.Datos = Datos;
    }
    
    @PostConstruct
    public void init(){
        Labels = "";
        Datos = "";
        PropiedadesVisitas = cProp.ListarMapPropiedad();
        List<String> labels = new ArrayList<>();
        List<Integer> datos = new ArrayList<>();
        for(Map.Entry set: PropiedadesVisitas.entrySet()){
            labels.add((String)set.getKey());
            datos.add((int)set.getValue());
        }
        
        for (int i = 0; i < labels.size(); i++) {
            Labels+= labels.get(i);
            Datos += String.valueOf(datos.get(i));
            if(i+1<labels.size()) {
                Labels+=",";
                Datos += ",";
            } 
        }

    }
    
}
