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
    
    /**
     * Eliminar la zona de crecimiento especificada por su id.
     * @param IdZonaCrecimiento
     * @return
     */
    public int EliminarZonaCrecimiento(int IdZonaCrecimiento){
        return mZona.EliminarZonaCrecimiento(IdZonaCrecimiento);
    }
    
    /**
     * Acutlizar los datos de una zona de crecimiento.
     * @param IdZonaCrecimiento
     * @param DemandaZonaCrecimiento
     * @param CoordenadasPoligono
     * @return
     */
    public int ActualizarZonaCrecimiento(int IdZonaCrecimiento, String DemandaZonaCrecimiento, String CoordenadasPoligono){
        try{
            ZonaCrecimiento zona = mZona.GetZonaCrecimiento(IdZonaCrecimiento);
            zona.setDemandaZonaCrecimiento(DemandaZonaCrecimiento);
            mZona.ActualizarZonaCrecimiento(zona);
            return mZona.InsertarUbicacionZonaCrecimiento(CoordenadasPoligono, IdZonaCrecimiento);
        }catch(NullPointerException ex){}
        return -1;
    }
}
