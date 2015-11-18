
package listados;

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

@Named
@ViewScoped
public class listarPropiedadesBean implements Serializable{
    @EJB
    private ControladorPropiedad cProp;
    
    private List<Integer> IdsPropiedades;
    private Map<String, Boolean> tipoPtoInteres;
    private List<String> tiposPunto;
    private String DistanciaMetros;

    
    //  Getters
    public Map<String, Boolean> getTipoPtoInteres() {return tipoPtoInteres;}
    public List<String> getTiposPunto() {return tiposPunto;}
    public List<Integer> getIdsPropiedades() {return IdsPropiedades;}
    public String getDistanciaMetros() {return DistanciaMetros;}
    
    //  Setters
    public void setTipoPtoInteres(Map<String, Boolean> tipoPtoInteres) {this.tipoPtoInteres = tipoPtoInteres;}
    public void setTiposPunto(List<String> tiposPunto) {this.tiposPunto = tiposPunto;}
    public void setIdsPropiedades(List<Integer> IdsPropiedades) {this.IdsPropiedades = IdsPropiedades;}
    public void setDistanciaMetros(String DistanciaMetros) {this.DistanciaMetros = DistanciaMetros;}
    
    public void buscarPropiedades(){
        int metros = 0;
        try{
            metros = Integer.parseInt(DistanciaMetros);
            this.IdsPropiedades = cProp.GetPropiedadesCercanasPtoInteres(getPtosMarcados(), metros);
            
        }catch(NumberFormatException ex){}
        
    }

    
    private  List<String> getPtosMarcados(){
        List<String> marcados = new ArrayList<>();
        for (Map.Entry e : tipoPtoInteres.entrySet()) {
            boolean valor = (boolean)e.getValue();
            String Key = (String) e.getKey();
            if ( valor ) marcados.add((String)e.getKey());
        }
        return marcados;
    }
    
    @PostConstruct
    public void init(){        
        this.tipoPtoInteres = new HashMap<>();
        tipoPtoInteres.put("medica", Boolean.FALSE);
        tipoPtoInteres.put("combustible", Boolean.FALSE);
        tipoPtoInteres.put("super", Boolean.FALSE);
        this.tiposPunto = new ArrayList<>();
        tiposPunto.add("medica");
        tiposPunto.add("combustible");
        tiposPunto.add("super");
    }
    
    
}
