package zonascrecimiento;

import javax.ejb.EJB;


public class ControladorZonasCrecimiento{
    @EJB
    private ManejadorZonasCrecimiento mZona;

    public int crearZonaCrecimiento(String demandaZonaCrecimiento){
        ZonaCrecimiento zona = new ZonaCrecimiento(demandaZonaCrecimiento);
        return mZona.CrearZonaCrecimiento(zona);
    }
}
