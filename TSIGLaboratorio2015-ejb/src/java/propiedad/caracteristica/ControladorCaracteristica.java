package propiedad.caracteristica;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

@Stateless
@ManagedBean
public class ControladorCaracteristica {
    
    @EJB
    private ManejadorCaracteristica mCar;
    
    /**
     * Lista todas las caracteristicas registradas en la base de datos.
     * @return
     */
    public List<Caracteristica> listarCaracteristicas(){
        return mCar.ListarCaracteristicas();
    }
    
    /**
     * Lista todas las caracteristicas que conciden con las ids especificadas.
     * @param idsCaracteristicas
     * @return 
     */
    public List<Caracteristica> ListarCaracteristicas(List<Integer> idsCaracteristicas){
        return mCar.ListarCaracteristicas(idsCaracteristicas);
    }
    
    /**
     * Registra una caracteristica en la base de datos.
     * @param NombreCaracteristica
     * @return retorna el id de la caracteristica registrada. Retorna -1 si no se pudo registrar.
     */
    public int CrearCaracteristica(String NombreCaracteristica){
        Caracteristica caracteristica = new Caracteristica(NombreCaracteristica);
        return mCar.CrearCaracteristica(caracteristica);
    }
}
