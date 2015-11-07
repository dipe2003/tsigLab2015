package interfaz.usuario;


import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import propiedad.caracteristica.ControladorCaracteristica;
import usuario.ControladorUsuario;


@Named("login")
@SessionScoped
public class Login implements Serializable {
    private String Nickname;
    private String Password;
    private String Correo;
    private boolean UsuarioLogueado;
    
    @EJB
    private ControladorUsuario cUsr;
    @EJB
    private ControladorCaracteristica cCar;
    
    //  Constructor
    public Login() {}
    
    //  Setters
    public void setPassword(String Password) {this.Password = Password;}
    public void setUsuarioLogueado(boolean UsuarioLogueado) {this.UsuarioLogueado = UsuarioLogueado;}
    public void setCorreo(String Correo) {this.Correo = Correo;}
    public void setNickname(String Nickname) {this.Nickname = Nickname;}
    
    //  Getters
    public String getPassword() {return Password;}
    public String getNickname() {return Nickname;}
    public String getCorreo() {return Correo;}
    public boolean getUsuarioLogueado() {return UsuarioLogueado;}
    
    /**
     * Realiza el login del usuario.
     * @return
     */
    public String login(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        int idUsr;
        if ((idUsr=cUsr.ValidarLogin(Nickname, Password))!=-1) {
            request.getSession().setAttribute("Usuario", cUsr.GetUsuario(idUsr));
            this.UsuarioLogueado = true;
        }else{
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Los datos ingresados no son correctos");
            context.addMessage("login:msj", fm);
        }
       return "index.xhtml";
    }
    
    
    /**
     * Reliza el logout del usuario.
     * @return
     */
    public String logout(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        request.getSession().invalidate();
        this.UsuarioLogueado = false;
        return "index.xhtml";
    }
    
    public void editarDatos() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("registrarUsuario.xhtml?Opt=edit");
    }
     
    @PostConstruct
    public void Init(){
        String nick = "El Usuario";
        if (!cUsr.ExisteNickname(nick)) {
            cUsr.CrearUsuario("El Usuario", "1234", "vendoyvendo@bienes.com");
        }
        try{
            cCar.CrearCaracteristica("Piscina");
            cCar.CrearCaracteristica("Garage");
            cCar.CrearCaracteristica("Barbacoa");
            cCar.CrearCaracteristica("Fondo Amplio");
            cCar.CrearCaracteristica("Fondo Pequeño");
            cCar.CrearCaracteristica("Entrada de Servicio");
            cCar.CrearCaracteristica("2 Plantas");
        }catch(Exception ex){}
            
    }
    
}
