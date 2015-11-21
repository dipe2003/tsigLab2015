
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
import propiedad.Propiedad;

@Named
@ViewScoped
public class GraficosPopiedades implements Serializable{
    @EJB
    private ControladorPropiedad cProp;
    
    private int TotalPropiedades;
    private List<Propiedad> PropiedadesVisitas;
    private String Labels;
    private String Datos;
    //  Getters
    public int getTotalPropiedades() {return TotalPropiedades;}    
    public List<Propiedad> getPropiedadesVisitas() {return PropiedadesVisitas;}

    public String getLabels() {
        return Labels;
    }

    public String getDatos() {
        return Datos;
    }
    
    //  Setters
    public void setTotalPropiedades(int TotalPropiedades) {this.TotalPropiedades = TotalPropiedades;}
    public void setPropiedadesVisitas(List<Propiedad> PropiedadesVisitas) {this.PropiedadesVisitas = PropiedadesVisitas;}

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
        PropiedadesVisitas = cProp.ListarPropiedadesRanking(5);
        List<String> labels = new ArrayList<>();
        List<Integer> datos = new ArrayList<>();
        for (int i = 0; i < PropiedadesVisitas.size(); i++) {
            labels.add(PropiedadesVisitas.get(i).getDireccionPropiedad());
            datos.add(PropiedadesVisitas.get(i).getVisitasPropiedad());
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
