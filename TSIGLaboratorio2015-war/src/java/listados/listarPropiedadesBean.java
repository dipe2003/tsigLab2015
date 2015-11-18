
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
import propiedad.caracteristica.Caracteristica;
import propiedad.caracteristica.ControladorCaracteristica;

@Named
@ViewScoped
public class listarPropiedadesBean implements Serializable{
    @EJB
    private ControladorPropiedad cProp;
    @EJB
    private ControladorCaracteristica cCar;
    
    private List<Integer> IdsPropiedades;
    private Map<String, Boolean> tipoPtoInteres;
    private List<String> tiposPunto;
    private String DistanciaMetros;
    private String ids;
    
    private List<Caracteristica> listaCaracteristica;
    private Map<Integer, Boolean> listCarChecked;
    
    //  Getters
    public Map<String, Boolean> getTipoPtoInteres() {return tipoPtoInteres;}
    public List<String> getTiposPunto() {return tiposPunto;}
    public List<Integer> getIdsPropiedades() {return IdsPropiedades;}
    public String getDistanciaMetros() {return DistanciaMetros;}
    public String getIds() {return ids;}
    public List<Caracteristica> getListaCaracteristica() {return listaCaracteristica;}
    public Map<Integer, Boolean> getListCarChecked() {return listCarChecked;}
    
    //  Setters
    public void setTipoPtoInteres(Map<String, Boolean> tipoPtoInteres) {this.tipoPtoInteres = tipoPtoInteres;}
    public void setTiposPunto(List<String> tiposPunto) {this.tiposPunto = tiposPunto;}
    public void setIdsPropiedades(List<Integer> IdsPropiedades) {this.IdsPropiedades = IdsPropiedades;}
    public void setDistanciaMetros(String DistanciaMetros) {this.DistanciaMetros = DistanciaMetros;}
    public void setIds(String ids) {this.ids = ids;}
    public void setListaCaracteristica(List<Caracteristica> listaCaracteristica) {this.listaCaracteristica = listaCaracteristica;}
    public void setListCarChecked(Map<Integer, Boolean> listCarChecked) {this.listCarChecked = listCarChecked;}
    
    public void buscarPropiedades(){
        int metros = 0;
        ids = "";
        try{
            metros = Integer.parseInt(DistanciaMetros);
            this.IdsPropiedades = cProp.GetPropiedadesCercanasPtoInteres(getPtosMarcados(), metros);
            for(int i=0; i < IdsPropiedades.size(); i++){
                ids += String.valueOf(IdsPropiedades.get(i));
                if(i+1< IdsPropiedades.size()) ids+=" ";
            }
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
    
    /**
     * Retorna la lista con las caracteristicas selaccionadas
     * @return Retorna la lista vacia en caso de ninguna seleccionada
     */
    private  List<Integer> getCaracteristicasMarcadas(){
        List<Integer> caracteristicasMarcadas = new ArrayList<>();
        for (Map.Entry e : listCarChecked.entrySet()) {
            boolean valor = (boolean)e.getValue();
            int Key = (int) e.getKey();
            if ( valor ) caracteristicasMarcadas.add((int)e.getKey());
        }
        return caracteristicasMarcadas;
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
        
        this.listaCaracteristica = cCar.listarCaracteristicas();
        listCarChecked = new HashMap<>();
        for (int i = 0; i < listaCaracteristica.size(); i++) {
            listCarChecked.put(listaCaracteristica.get(i).getIdCaracteristica(), Boolean.FALSE);
        }
    }
    
}
