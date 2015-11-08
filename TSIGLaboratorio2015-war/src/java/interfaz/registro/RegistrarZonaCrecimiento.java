package interfaz.registro;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import zonascrecimiento.ControladorZonasCrecimiento;


@Named
@ManagedBean
@ViewScoped
public class RegistrarZonaCrecimiento implements Serializable{
    @EJB
    private ControladorZonasCrecimiento cZona;
    private String DemandaZonaCrecimiento;
    private String Coords;

    //Getters
    public String getDemandaZonaCrecimiento(){return DemandaZonaCrecimiento;}
    public String getCoords(){return Coords;}
    
    //Setters
    public void setDemandaZonaCrecimiento(String DemandaZonaCrecimiento){this.DemandaZonaCrecimiento = DemandaZonaCrecimiento;}
    public void setCoords(String Coords){this.Coords = Coords;}
    
    public void registrarZonaCrecimiento(){
        int IdZona = -1;
        if ((IdZona = cZona.crearZonaCrecimiento(DemandaZonaCrecimiento))!=-1) {
            cZona.insertarUbicacion(Coords, IdZona);
        }
    }
    
}
