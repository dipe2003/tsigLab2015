
package Usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    private String NicknameUsuario;
    private String PasswordUsuario;
    @OneToMany
    private List<Propiedad> Propiedades;
    
    //  Constructores

    public Usuario(String NicknameUsuario, String PasswordUsuario) {
        this.NicknameUsuario = NicknameUsuario;
        this.PasswordUsuario = PasswordUsuario;
        this.Propiedades = new ArrayList<>();
    }

    public Usuario() {}
    
    //  Setters

    public void setIdUsuario(int IdUsuario) {this.IdUsuario = IdUsuario;}
    public void setNicknameUsuario(String NicknameUsuario) {this.NicknameUsuario = NicknameUsuario;}
    public void setPasswordUsuario(String PasswordUsuario) {this.PasswordUsuario = PasswordUsuario;}
    public void setPropiedades(List<Propiedad> Propiedades) {this.Propiedades = Propiedades;}    
    
    //  Getters

    public int getIdUsuario() {
        return IdUsuario;
    }

    public String getNicknameUsuario() {
        return NicknameUsuario;
    }

    public String getPasswordUsuario() {
        return PasswordUsuario;
    }

    public List<Propiedad> getPropiedades() {
        return Propiedades;
    }
    
    //  Propiedades
    public void addPropiedad(Propiedad propiedad){this.Propiedades.add(propiedad);}
    
}
