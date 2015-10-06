
package zonascrecimiento;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ZonasCrecimiento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int IdZonasCrecimiento;
    private String DemandaZonaCrecimiento;
    
    //  Constructor
    public ZonasCrecimiento(){}

    //  Setters
    
    public void setIdZonasCrecimiento(int IdZonasCrecimiento) {
        this.IdZonasCrecimiento = IdZonasCrecimiento;
    }

    public void setDemandaZonaCrecimiento(String DemandaZonaCrecimiento) {
        this.DemandaZonaCrecimiento = DemandaZonaCrecimiento;
    }
    
    //  Getters

    public int getIdZonasCrecimiento() {
        return IdZonasCrecimiento;
    }

    public String getDemandaZonaCrecimiento() {
        return DemandaZonaCrecimiento;
    }
        
    
}
