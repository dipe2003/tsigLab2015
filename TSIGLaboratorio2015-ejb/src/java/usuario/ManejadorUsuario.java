
package usuario;

import java.sql.SQLException;
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
public class ManejadorUsuario {
    @PersistenceContext(unitName = "TSIGLaboratorio2015-ejbPU")
    private EntityManager em ;
    
    public int CrearUsuario(Usuario usuario){
        try{
            em.persist(usuario);
            return usuario.getIdUsuario();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }        
    }
    
    public int ActualizarUsuario(Usuario usuario){
        try{
            em.merge(usuario);
            return usuario.getIdUsuario();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public int BorrarUsuario(Usuario usuario){
        try{
            em.remove(usuario);
            return 1;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public Usuario GetUsuario(int id){
        try{
            return em.find(Usuario.class, id);
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public List<Usuario> ListarUsuarios(){
        List<Usuario> lista = new ArrayList<>();
        try{
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    
    public int BuscarNicknameUsuario(String Nickname){
        try{
            Query query = em.createQuery("SELECT u.IdUsuario FROM Usuario u WHERE u.NicknameUsuario= :Nickname");
            query.setParameter("Nickname", Nickname);
            return (int)query.getSingleResult();
        }catch(Exception ex){}
        return -1;
    }
    
    public int ValidarLoginUsuario(String Nickname, String Password){
        try{
            Query query = em.createQuery("SELECT u.IdUsuario FROM Usuario u WHERE u.NicknameUsuario= :Nickname AND u.PasswordUsuario= :Pass");
            query.setParameter("Nickname", Nickname);
            query.setParameter("Pass", Password);
            return (int)query.getSingleResult();
        }catch(Exception ex){}
        return -1;
    }
        
}
    
