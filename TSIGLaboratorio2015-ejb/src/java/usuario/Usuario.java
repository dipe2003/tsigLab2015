
package usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import propiedad.Propiedad;

@Entity
public class Usuario implements Serializable{
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private int IdUsuario;
    @Column(unique = true)
    private String NicknameUsuario;
    private String PasswordUsuario;
    private String CorreoUsuario;
    @OneToMany(mappedBy = "UsuarioPropiedad")
    private List<Propiedad> Propiedades;
    
    //  Constructores

    public Usuario(String NicknameUsuario, String PasswordUsuario, String CorreoUsuario) {
        this.NicknameUsuario = NicknameUsuario;
        this.PasswordUsuario = PasswordUsuario;
        this.CorreoUsuario = CorreoUsuario;
        this.Propiedades = new ArrayList<>();
    }

    public Usuario() {}
    
        //  Getters

    public int getIdUsuario() {return IdUsuario;}
    public String getNicknameUsuario() {return NicknameUsuario;}
    public String getPasswordUsuario() {return PasswordUsuario;}
    public List<Propiedad> getPropiedades() {return Propiedades;}
    public String getCorreoUsuario() {return CorreoUsuario;}
    
    //  Setters

    public void setIdUsuario(int IdUsuario) {this.IdUsuario = IdUsuario;}
    public void setNicknameUsuario(String NicknameUsuario) {this.NicknameUsuario = NicknameUsuario;}
    public void setPasswordUsuario(String PasswordUsuario) {this.PasswordUsuario = PasswordUsuario;}
    public void setPropiedades(List<Propiedad> Propiedades) {this.Propiedades = Propiedades;}    
    public void setCorreoUsuario(String CorreoUsuario) {this.CorreoUsuario = CorreoUsuario;}
    
    //  Propiedades
    public void addPropiedad(Propiedad propiedad){
        this.Propiedades.add(propiedad);
        if (!propiedad.getUsuarioPropiedad().equals(this)) {
            propiedad.setUsuarioPropiedad(this);
        }
    }
    
    //  Otras
    public boolean esCorrectoPassword(String Password){return this.PasswordUsuario.equals(Password);}
    
}
