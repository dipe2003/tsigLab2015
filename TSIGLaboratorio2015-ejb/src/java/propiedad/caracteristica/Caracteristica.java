
package propiedad.caracteristica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Caracteristica implements Serializable{
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private int IdCaracteristica;
    private String NombreCaracteristica;
    
    //  Constructores

    public Caracteristica(String NombreCaracteristica) {this.NombreCaracteristica = NombreCaracteristica;}
    public Caracteristica() {}
    
    //  Setters

    public void setIdCaracteristica(int IdCaracteristica) {this.IdCaracteristica = IdCaracteristica;}
    public void setNombreCaracteristica(String NombreCaracteristica) {this.NombreCaracteristica = NombreCaracteristica;}

 // Getters

    public int getIdCaracteristica() {return IdCaracteristica;}
    public String getNombreCaracteristica() {return NombreCaracteristica;}    
    
}
