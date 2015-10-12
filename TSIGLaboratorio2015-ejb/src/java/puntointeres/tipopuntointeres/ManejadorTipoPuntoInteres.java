
package puntointeres.tipopuntointeres;

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
public class ManejadorTipoPuntoInteres {
    @PersistenceContext(unitName = "TSIGLaboratorio2015-ejbPU")
    private EntityManager em ;
    
    public int CrearTipoPuntoInteres(TipoPuntoInteres tipoPuntoInteres){
        try{
            em.persist(tipoPuntoInteres);
            return tipoPuntoInteres.getIdTipoPuntoInteres();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }        
    }
    
    public int ActualizarTipoPuntoInteres(TipoPuntoInteres tipoPuntoInteres){
        try{
            em.merge(tipoPuntoInteres);
            return tipoPuntoInteres.getIdTipoPuntoInteres();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public int BorrarTipoPuntoInteres(TipoPuntoInteres tipoPuntoInteres){
        try{
            em.remove(tipoPuntoInteres);
            return 1;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public TipoPuntoInteres GetTipoPuntoInteres(int id){
        try{
            return em.find(TipoPuntoInteres.class, id);
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public List<TipoPuntoInteres> ListarTiposPuntosInteres(){
        List<TipoPuntoInteres> lista = new ArrayList<>();
        try{
            TypedQuery<TipoPuntoInteres> query = em.createQuery("SELECT pi FROM TipoPuntoInteres pi", TipoPuntoInteres.class);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
        
}
    
