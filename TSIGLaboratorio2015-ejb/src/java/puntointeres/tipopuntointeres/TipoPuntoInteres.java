
package puntointeres.tipopuntointeres;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoPuntoInteres implements Serializable{
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private int IdTipoPuntoInteres;
    @Column(unique=true)
    private String NombreTipoPuntoInteres;
    
    //  Constructores

    public TipoPuntoInteres() {
    }

    public TipoPuntoInteres(int IdTipoPuntoInteres, String NombreTipoPuntoInteres) {
        this.IdTipoPuntoInteres = IdTipoPuntoInteres;
        this.NombreTipoPuntoInteres = NombreTipoPuntoInteres;
    }
    
    //  Setters

    public void setIdTipoPuntoInteres(int IdTipoPuntoInteres) {
        this.IdTipoPuntoInteres = IdTipoPuntoInteres;
    }

    public void setNombreTipoPuntoInteres(String NombreTipoPuntoInteres) {
        this.NombreTipoPuntoInteres = NombreTipoPuntoInteres;
    }
    
    //  Getters

    public int getIdTipoPuntoInteres() {
        return IdTipoPuntoInteres;
    }

    public String getNombreTipoPuntoInteres() {
        return NombreTipoPuntoInteres;
    }
    
    
}
