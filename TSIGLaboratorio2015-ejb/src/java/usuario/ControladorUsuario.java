package usuario;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

@Stateless
@ManagedBean
public class ControladorUsuario {
    
    @EJB
    ManejadorUsuario mUsr;
    
    /**
     * Crea un Usuario y lo persiste.
     * @param PasswordUsuario
     * @param NicknameUsuario
     * @param CorreoUsuario
     * @return Devuelve un Usuario si fue creado, de lo contrario devuelve null.
     */
    public Usuario CrearUsuario(String NicknameUsuario, String PasswordUsuario, String CorreoUsuario){
        Usuario usr = new Usuario(NicknameUsuario, PasswordUsuario, CorreoUsuario);
        if (mUsr.CrearUsuario(usr)!=-1){
            return usr;
        }
        return null;
    }
    
    /**
     * Comprueba la existencia de un nickname en la base de datos
     * @param Nickname
     * @return 
     */
    public boolean ExisteNickname(String Nickname){
        return mUsr.BuscarNicknameUsuario(Nickname)!=0;
    }
    
    /**
     * Comprueba que el nickname y contraseña se correspondan con un usuario.
     * @param Nickname
     * @param Password
     * @return devuelve el id del usuario correspondiente.
     */
    public int ValidarLogin(String Nickname, String Password){
        return mUsr.ValidarLoginUsuario(Nickname, Password);
    }
    
    /**
     * Obtiene el usuario especificado por su id.
     * @param IdUsuario
     * @return 
     */
    public Usuario GetUsuario(int IdUsuario){
        return mUsr.GetUsuario(IdUsuario);
    }
    
    /**
     * Actualiza los datos del usuario registrado en la base de datos.
     * @param NicknameUsuario
     * @param PasswordUsuario
     * @param CorreoUsuario
     * @param IdUsuario
     * @return devuelve el id del usuario si se actualizo. Retorna -1 si no se pudo actualizar.
     */
    public int ActualizarDatosUsuario(String NicknameUsuario, String PasswordUsuario, String CorreoUsuario, int IdUsuario){
        try{
            Usuario usr = GetUsuario(IdUsuario);
            usr.setCorreoUsuario(CorreoUsuario);
            usr.setNicknameUsuario(NicknameUsuario);
            usr.setPasswordUsuario(PasswordUsuario);
            return mUsr.ActualizarUsuario(usr);
        }catch(NullPointerException ex){}
        return -1;
    }
    
}
