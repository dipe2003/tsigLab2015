
package propiedad;

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
public class ManejadorPropiedad {
    @PersistenceContext(unitName = "TSIGLaboratorio2015-ejbPU")
    private EntityManager em ;
    
    public int CrearPropiedad(Propiedad propiedad){
        try{
            em.persist(propiedad);
            return propiedad.getIdPropiedad();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }        
    }
    
    public int ActualizarPropiedad(Propiedad propiedad){
        try{
            em.merge(propiedad);
            return propiedad.getIdPropiedad();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public int BorrarPropiedad(Propiedad propiedad){
        try{
            em.remove(propiedad);
            return 1;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public Propiedad GetPropiedad(int id){
        try{
            return em.find(Propiedad.class, id);
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public List<Propiedad> ListarPropiedades(){
        List<Propiedad> lista = new ArrayList<>();
        try{
            TypedQuery<Propiedad> query = em.createQuery("SELECT p FROM Propiedad p", Propiedad.class);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    
    public int InsertarUbicacionPropiedad(int IdPropiedad, int CoordX, int CoordY){
        Query query = em.createNativeQuery("INSERT INTO Propiedad(:idProp, the_geom) VALUES(2, ST_GeomFromText('POINT(:puntoX :puntoY)', 4326");
        query.setParameter("IdProp", IdPropiedad);
        query.setParameter("puntoX", CoordX);
        query.setParameter("puntoY", CoordY);
        try{
            return query.executeUpdate();
        }catch(Exception ex){}
        return -1;
    }
        
}
    
