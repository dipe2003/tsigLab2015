
package interfaz.usuario;

import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import usuario.ControladorUsuario;

@Named
@ViewScoped
public class RegistroUsuario implements Serializable{
    @EJB
    private ControladorUsuario cUsr;
    
    private String CorreoUsuario;
    private String NicknameUsuario;
    private String PasswordUsuario;
    private String RepeticionPasswordUsuario;
    
    //  Getters
    public String getCorreoUsuario() {return CorreoUsuario;}
    public String getNicknameUsuario() {return NicknameUsuario;}
    public String getPasswordUsuario() {return PasswordUsuario;}
    public String getRepeticionPasswordUsuario() {return RepeticionPasswordUsuario;}
    
    //  Setters
    public void setCorreoUsuario(String CorreoUsuario) {this.CorreoUsuario = CorreoUsuario;}
    public void setNicknameUsuario(String NicknameUsuario) {this.NicknameUsuario = NicknameUsuario;}
    public void setPasswordUsuario(String PasswordUsuario) {this.PasswordUsuario = PasswordUsuario;}
    public void setRepeticionPasswordUsuario(String RepeticionPasswordUsuario) {this.RepeticionPasswordUsuario = RepeticionPasswordUsuario;}
    
    public int comprobarPass(){
        if (!PasswordUsuario.equals(RepeticionPasswordUsuario)) {
            return -1;
        }
        return 0;
    }
    
    public int comprobarNickname(){
        if(cUsr.ExisteNickname(NicknameUsuario)) {
            return -1;
        }
        return 0;
    }
    
    public void registrarUsuario(){
        String msj = "";
        if (comprobarPass()==-1) {
            msj = "Las copntraseñas no coinciden";
        }else{
            if (comprobarNickname()==-1) {
                msj = "El Nick ya está registrado.";
            }else{
                if(cUsr.CrearUsuario(NicknameUsuario, PasswordUsuario, CorreoUsuario)==null){
                    msj = "No se pudo registrar.";
                }
            }            
        }
        FacesContext.getCurrentInstance().addMessage("frmUsr:errReg", new FacesMessage("Error", msj));
    }
}