
package interfaz.usuario;

import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import usuario.ControladorUsuario;
import usuario.Usuario;

@Named
@ViewScoped
public class RegistroUsuario implements Serializable{
    @EJB
    private ControladorUsuario cUsr;
    
    private String Opt;
    private int IdUsuario;
    
    private String CorreoUsuario;
    private String NicknameUsuario;
    private String PasswordUsuario;
    private String RepeticionPasswordUsuario;
    private String PasswordActual;
    private String PasswordNuevo;    
    
    //  Getters
    public String getCorreoUsuario() {return CorreoUsuario;}
    public String getNicknameUsuario() {return NicknameUsuario;}
    public String getPasswordUsuario() {return PasswordUsuario;}
    public String getRepeticionPasswordUsuario() {return RepeticionPasswordUsuario;}
    public String getOpt() {return Opt;}
    
    // editar
    public String getPasswordActual() {return PasswordActual;}
    public String getPasswordNuevo() {return PasswordNuevo;}    
    public int getIdUsuario() {return IdUsuario;}
    
    //  Setters
    public void setCorreoUsuario(String CorreoUsuario) {this.CorreoUsuario = CorreoUsuario;}
    public void setNicknameUsuario(String NicknameUsuario) {this.NicknameUsuario = NicknameUsuario;}
    public void setPasswordUsuario(String PasswordUsuario) {this.PasswordUsuario = PasswordUsuario;}
    public void setRepeticionPasswordUsuario(String RepeticionPasswordUsuario) {this.RepeticionPasswordUsuario = RepeticionPasswordUsuario;}
    public void setOpt(String Opt) {this.Opt = Opt;}    
    
    //  editar
    public void setPasswordNuevo(String PasswordNuevo) {this.PasswordNuevo = PasswordNuevo;}
    public void setPasswordActual(String PasswordActual){this.PasswordActual = PasswordActual;}
    public void setIdUsuario(int IdUsuario) {this.IdUsuario = IdUsuario;}    
    
    public int comprobarPass() {
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
    
    public void editarUsuario() throws IOException{
        if(this.PasswordActual.equals(this.PasswordUsuario)){
            if (cUsr.ActualizarDatosUsuario(NicknameUsuario, PasswordNuevo, CorreoUsuario, IdUsuario)!=-1) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            }
        }
        FacesContext.getCurrentInstance().addMessage("frmUsr:btnEdit", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Las contraseñas no son correctas."));
    }
    
    @PostConstruct
    public void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        this.Opt = (String)(request.getParameter("Opt"));
        try{
            if (!Opt.isEmpty()) {
                Usuario usr = ((Usuario)request.getSession().getAttribute("Usuario"));
                this.PasswordUsuario = usr.getPasswordUsuario();
                this.CorreoUsuario = usr.getCorreoUsuario();
                this.NicknameUsuario = usr.getNicknameUsuario();
                this.IdUsuario = usr.getIdUsuario();
            }
        }catch(NullPointerException ex){
            this.Opt = "no";
        }        
        
    }
}