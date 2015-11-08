package zonascrecimiento;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

@Named
@Stateless
public class ControladorZonasCrecimiento implements Serializable{
    @EJB
    private ManejadorZonasCrecimiento mZona;

    public int crearZonaCrecimiento(String demandaZonaCrecimiento){
        ZonaCrecimiento zona = new ZonaCrecimiento(demandaZonaCrecimiento);
        return mZona.CrearZonaCrecimiento(zona);
    }
    
    /**
     * Inserta las coordenadas de cada punto que forma la zona de crecimiento.
     * @param CordenadasPoligono
     * @param IdZonaCrecimiento
     * @return Retorna el id de la zona de crecimiento. Retorna -1 si no se pudo insertar.
     */
    public int insertarUbicacion(String CordenadasPoligono, int IdZonaCrecimiento){
        return mZona.InsertarUbicacionZonaCrecimiento(CordenadasPoligono, IdZonaCrecimiento);
    }
}
