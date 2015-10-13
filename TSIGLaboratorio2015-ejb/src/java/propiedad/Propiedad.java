
package propiedad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import propiedad.caracteristica.Caracteristica;
import propiedad.enums.EnumEstadoPropiedad;

@Entity
public class Propiedad implements Serializable{
   @Id@GeneratedValue(strategy = GenerationType.AUTO) 
   private int IdPropiedad;
   private String DireccionPropiedad;
   private float PrecioPropiedad;
   private float MetrosConstruidosPropiedad;
   private float MetrosTerrenoPropiedad;
   private int NumeroPadronPropiedad;
   private EnumEstadoPropiedad EstadoPropiedad;
   @OneToMany
   private List<Caracteristica> Caracteristicas;
   
   
   //   Constructores

    public Propiedad() {}

    public Propiedad(String DireccionPropiedad, float PrecioPropiedad, float MetrosConstruidosPropiedad, float MetrosTerrenoPropiedad, 
            int NumeroPadronPropiedad, EnumEstadoPropiedad EstadoPropiedad, List<Caracteristica> Caracteristicas) {
        this.DireccionPropiedad = DireccionPropiedad;
        this.PrecioPropiedad = PrecioPropiedad;
        this.MetrosConstruidosPropiedad = MetrosConstruidosPropiedad;
        this.MetrosTerrenoPropiedad = MetrosTerrenoPropiedad;
        this.NumeroPadronPropiedad = NumeroPadronPropiedad;
        this.EstadoPropiedad = EstadoPropiedad;
        this.Caracteristicas = Caracteristicas;
    }

    public Propiedad(String DireccionPropiedad, float PrecioPropiedad, float MetrosConstruidosPropiedad, float MetrosTerrenoPropiedad, 
            int NumeroPadronPropiedad, EnumEstadoPropiedad EstadoPropiedad) {
        this.DireccionPropiedad = DireccionPropiedad;
        this.PrecioPropiedad = PrecioPropiedad;
        this.MetrosConstruidosPropiedad = MetrosConstruidosPropiedad;
        this.MetrosTerrenoPropiedad = MetrosTerrenoPropiedad;
        this.NumeroPadronPropiedad = NumeroPadronPropiedad;
        this.EstadoPropiedad = EstadoPropiedad;
    }
    
    
    
    //  Setters

    public void setIdPropiedad(int IdPropiedad) {
        this.IdPropiedad = IdPropiedad;
    }

    public void setDireccionPropiedad(String DireccionPropiedad) {
        this.DireccionPropiedad = DireccionPropiedad;
    }

    public void setPrecioPropiedad(float PrecioPropiedad) {
        this.PrecioPropiedad = PrecioPropiedad;
    }

    public void setMetrosConstruidosPropiedad(float MetrosConstruidosPropiedad) {
        this.MetrosConstruidosPropiedad = MetrosConstruidosPropiedad;
    }

    public void setMetrosTerrenoPropiedad(float MetrosTerrenoPropiedad) {
        this.MetrosTerrenoPropiedad = MetrosTerrenoPropiedad;
    }

    public void setNumeroPadronPropiedad(int NumeroPadronPropiedad) {
        this.NumeroPadronPropiedad = NumeroPadronPropiedad;
    }

    public void setEstadoPropiedad(EnumEstadoPropiedad EstadoPropiedad) {
        this.EstadoPropiedad = EstadoPropiedad;
    }

    public void setCaracteristicas(List<Caracteristica> Caracteristicas) {
        this.Caracteristicas = Caracteristicas;
    }
    
    
    
    //  Getters

    public int getIdPropiedad() {
        return IdPropiedad;
    }

    public String getDireccionPropiedad() {
        return DireccionPropiedad;
    }

    public float getPrecioPropiedad() {
        return PrecioPropiedad;
    }

    public float getMetrosConstruidosPropiedad() {
        return MetrosConstruidosPropiedad;
    }

    public float getMetrosTerrenoPropiedad() {
        return MetrosTerrenoPropiedad;
    }

    public int getNumeroPadronPropiedad() {
        return NumeroPadronPropiedad;
    }

    public EnumEstadoPropiedad getEstadoPropiedad() {
        return EstadoPropiedad;
    }

    public List<Caracteristica> getCaracteristicas() {
        return Caracteristicas;
    }
    
    //  Caracteristicas
    public void addCaracteristica(Caracteristica caracteristica){this.Caracteristicas.add(caracteristica);}
   
}
