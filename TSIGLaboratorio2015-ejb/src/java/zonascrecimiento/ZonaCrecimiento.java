
package zonascrecimiento;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ZonaCrecimiento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int IdZonaCrecimiento;
    private String DemandaZonaCrecimiento;
    
    //  Constructor
    public ZonaCrecimiento(){}

    //  Setters
    
    public void setIdZonaCrecimiento(int IdZonaCrecimiento) {
        this.IdZonaCrecimiento = IdZonaCrecimiento;
    }

    public void setDemandaZonaCrecimiento(String DemandaZonaCrecimiento) {
        this.DemandaZonaCrecimiento = DemandaZonaCrecimiento;
    }
    
    //  Getters

    public int getIdZonaCrecimiento() {
        return IdZonaCrecimiento;
    }

    public String getDemandaZonaCrecimiento() {
        return DemandaZonaCrecimiento;
    }
        
    
}
