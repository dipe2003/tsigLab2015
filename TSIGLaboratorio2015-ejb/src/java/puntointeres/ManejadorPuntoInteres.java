
package puntointeres;

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
public class ManejadorPuntoInteres {
    @PersistenceContext(unitName = "TSIGLaboratorio2015-ejbPU")
    private EntityManager em ;
    
    public int CrearPuntoInteres(PuntoInteres puntoInteres){
        try{
            em.persist(puntoInteres);
            return puntoInteres.getIdPuntoInteres();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }        
    }
    
    public int ActualizarPuntoInteres(PuntoInteres puntoInteres){
        try{
            em.merge(puntoInteres);
            return puntoInteres.getIdPuntoInteres();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public int BorrarPuntoInteres(PuntoInteres puntoInteres){
        try{
            em.remove(puntoInteres);
            return 1;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public PuntoInteres GetPuntoInteres(int id){
        try{
            return em.find(PuntoInteres.class, id);
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public List<PuntoInteres> ListarPuntosInteres(){
        List<PuntoInteres> lista = new ArrayList<>();
        try{
            TypedQuery<PuntoInteres> query = em.createQuery("SELECT pi FROM PuntoInteres pi", PuntoInteres.class);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
        
}
    
