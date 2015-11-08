package interfaz.admin;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import zonascrecimiento.ControladorZonasCrecimiento;


@Named
@ManagedBean
@ViewScoped
public class AdminZonaCrecimiento implements Serializable{
    @EJB
    private ControladorZonasCrecimiento cZona;
    private String DemandaZonaCrecimiento;
    private String Coords;
    private int IdZonaCrecimiento;

    //Getters
    public String getDemandaZonaCrecimiento(){return DemandaZonaCrecimiento;}
    public String getCoords(){return Coords;}
    public int getIdZonaCrecimiento() {return IdZonaCrecimiento;}
    
    //Setters
    public void setDemandaZonaCrecimiento(String DemandaZonaCrecimiento){this.DemandaZonaCrecimiento = DemandaZonaCrecimiento;}
    public void setCoords(String Coords){this.Coords = Coords;}
    public void setIdZonaCrecimiento(int IdZonaCrecimiento) {this.IdZonaCrecimiento = IdZonaCrecimiento;}
    
    public void registrarZonaCrecimiento(){
        int IdZona = -1;
        if ((IdZona = cZona.crearZonaCrecimiento(DemandaZonaCrecimiento))!=-1) {
            cZona.insertarUbicacion(Coords, IdZona);
        }
    }
    
    public void eliminarZonaCrecimiento(){
        
    }
    
    public void init(){
        
    }
    
}
