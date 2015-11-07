package interfaz.registro;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named
@ManagedBean
@ViewScoped
public class RegistrarZonaCrecimiento implements Serializable{
    
    private String DemandaZonaCrecimiento;
    private String Coords;

    //Getters
    public String getDemandaZonaCrecimiento(){return DemandaZonaCrecimiento;}
    public String getCoords(){return Coords;}
    
    //Setters
    public void setDemandaZonaCrecimiento(String DemandaZonaCrecimiento){this.DemandaZonaCrecimiento = DemandaZonaCrecimiento;}
    public void setCoords(String Coords){this.Coords = Coords;}
    
}
