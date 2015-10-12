
package propiedad.caracteristica;

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
public class ManejadorCaracteristica {
    @PersistenceContext(unitName = "TSIGLaboratorio2015-ejbPU")
    private EntityManager em ;
    
    public int CrearCaracteristica(Caracteristica caracteristica){
        try{
            em.persist(caracteristica);
            return caracteristica.getIdCaracteristica();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }        
    }
    
    public int ActualizarCaracteristica(Caracteristica caracteristica){
        try{
            em.merge(caracteristica);
            return caracteristica.getIdCaracteristica();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public int BorrarCaracteristica(Caracteristica caracteristica){
        try{
            em.remove(caracteristica);
            return 1;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public Caracteristica GetCaracteristica(int id){
        try{
            return em.find(Caracteristica.class, id);
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public List<Caracteristica> ListarCaracteristicas(){
        List<Caracteristica> lista = new ArrayList<>();
        try{
            TypedQuery<Caracteristica> query = em.createQuery("SELECT c FROM Caracteristica c", Caracteristica.class);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
        
}
    
