
package terreno;

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
public class ManejadorTerreno {
    @PersistenceContext(unitName = "TSIGLaboratorio2015-ejbPU")
    private EntityManager em ;
    
    public int CrearTerreno(Terreno terreno){
        try{
            em.persist(terreno);
            return terreno.getIdPropiedad();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }        
    }
    
    public int ActualizarTerreno(Terreno terreno){
        try{
            em.merge(terreno);
            return terreno.getIdPropiedad();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public int BorrarTerreno(Terreno terreno){
        try{
            em.remove(terreno);
            return 1;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public Terreno GetTerreno(int id){
        try{
            return em.find(Terreno.class, id);
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public List<Terreno> ListarTerrenos(){
        List<Terreno> lista = new ArrayList<>();
        try{
            TypedQuery<Terreno> query = em.createQuery("SELECT t FROM Terreno t", Terreno.class);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
        
}
    
