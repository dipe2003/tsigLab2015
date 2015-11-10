
package zonascrecimiento;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@ManagedBean
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ManejadorZonasCrecimiento {
    @PersistenceContext(unitName = "TSIGLaboratorio2015-ejbPU")
    private EntityManager em ;
    
    public int CrearZonaCrecimiento(ZonaCrecimiento zonaCrecimiento){
        try{
            em.persist(zonaCrecimiento);
            return zonaCrecimiento.getIdZonaCrecimiento();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }        
    }
    
    public int ActualizarZonaCrecimiento(ZonaCrecimiento zonaCrecimiento){
        try{
            em.merge(zonaCrecimiento);
            return zonaCrecimiento.getIdZonaCrecimiento();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public int BorrarZonaCrecimiento(ZonaCrecimiento zonaCrecimiento){
        try{
            em.remove(zonaCrecimiento);
            return 1;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public ZonaCrecimiento GetZonaCrecimiento(int id){
        try{
            return em.find(ZonaCrecimiento.class, id);
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public List<ZonaCrecimiento> ListarZonasCrecimiento(){
        List<ZonaCrecimiento> lista = new ArrayList<>();
        try{
            TypedQuery<ZonaCrecimiento> query = em.createQuery("SELECT z FROM ZonaCrecimiento z", ZonaCrecimiento.class);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    
    public int InsertarUbicacionZonaCrecimiento(String CoordenadasZonaCrecimiento, int IdZonaCrecimiento){
        Query query = em.createNativeQuery("UPDATE ZonaCrecimiento SET the_geom = ST_Transform(ST_GeomFromText('POLYGON((" +CoordenadasZonaCrecimiento+ " ))', '4326'), 32721) WHERE ZonaCrecimiento.idzonacrecimiento= "+IdZonaCrecimiento);
        try{
            return query.executeUpdate();
        }catch(Exception ex){
            System.out.println("Error:" + ex.getMessage());
        }
        return -1;
    }
    
    public int EliminarZonaCrecimiento(int IdZonaCrecimiento){
        Query query = em.createQuery("DELETE FROM ZonaCrecimiento z WHERE z.IdZonaCrecimiento= :idZona");
        try{
            query.setParameter("idZona", IdZonaCrecimiento);
            return query.executeUpdate();
        }catch(Exception ex){
            System.out.println("Error: " +ex.getMessage());
        }
        return -1;
    }
}
    
