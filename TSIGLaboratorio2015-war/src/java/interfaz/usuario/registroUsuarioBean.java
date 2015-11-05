
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
public class registroUsuarioBean implements Serializable{
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
    
    public void comprobarPass(){
        if (!PasswordUsuario.equals(RepeticionPasswordUsuario)) {
            FacesContext.getCurrentInstance().addMessage("frmUsr:repPass", new FacesMessage("Error", "Las contraseñas no coinciden"));
        }
    }
    
    public void comprobarNickname(){
        if(cUsr.ExisteNickname(NicknameUsuario)) {
            FacesContext.getCurrentInstance().addMessage("frmUsr:errNick", new FacesMessage("Error", "El nickname ya esta registrado."));
        }
    }
    
    public void registrarUsuario(){
        if(cUsr.CrearUsuario(NicknameUsuario, PasswordUsuario, CorreoUsuario)==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "No se pudo registrar"));
        }        
    }
    
    public void regUsr() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("registrarUsuario.xhtml");
    }
    
    
}
