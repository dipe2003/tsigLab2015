
package inmueble;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@ManagedBean
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ManejadorInmueble {
    @PersistenceContext(unitName = "TSIGLaboratorio2015-ejbPU")
    private EntityManager em ;
    
    public int CrearInmueble(Inmueble inmueble){
        try{
            em.persist(inmueble);
            return inmueble.getIdPropiedad();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }        
    }
    
    public int ActualizarInmueble(Inmueble inmueble){
        try{
            em.merge(inmueble);
            return inmueble.getIdPropiedad();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public int BorrarInmueble(Inmueble inmueble){
        try{
            em.remove(inmueble);
            return 1;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public Inmueble GetInmueble(int id){
        try{
            return em.find(Inmueble.class, id);
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public List<Inmueble> ListarInmuebles(){
        List<Inmueble> lista = new ArrayList<>();
        try{
            TypedQuery<Inmueble> query = em.createQuery("SELECT i FROM Inmueble i", Inmueble.class);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
        
}
    
