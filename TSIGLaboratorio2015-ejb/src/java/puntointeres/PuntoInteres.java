
package puntointeres;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import puntointeres.tipopuntointeres.TipoPuntoInteres;

@Entity
public class PuntoInteres implements Serializable {
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private int IdPuntoInteres;
    private String NombrePuntoInteres;
    @OneToOne
    @JoinColumn(name="TipoPuntoInteres_Id", insertable=true, updatable=true)
    private TipoPuntoInteres TipoDePuntoInteres;
    
    //  Constructores

    public PuntoInteres() {
    }

    public PuntoInteres(String NombrePuntoInteres, TipoPuntoInteres TipoDePuntoInteres) {
        this.NombrePuntoInteres = NombrePuntoInteres;
        this.TipoDePuntoInteres = TipoDePuntoInteres;
    }
    
    //  Setters

    public void setIdPuntoInteres(int IdPuntoInteres) {this.IdPuntoInteres = IdPuntoInteres;}
    public void setNombrePuntoInteres(String NombrePuntoInteres) {this.NombrePuntoInteres = NombrePuntoInteres;}
    public void setTipoDePuntoInteres(TipoPuntoInteres TipoDePuntoInteres) {this.TipoDePuntoInteres = TipoDePuntoInteres;}        
    
    //  Getters

    public int getIdPuntoInteres() {return IdPuntoInteres;}
    public String getNombrePuntoInteres() {return NombrePuntoInteres;}
    public TipoPuntoInteres getTipoDePuntoInteres() {return TipoDePuntoInteres;}    
}
