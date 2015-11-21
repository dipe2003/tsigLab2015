
package propiedad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import propiedad.caracteristica.Caracteristica;
import propiedad.enums.EnumEstadoPropiedad;
import usuario.Usuario;

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
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Caracteristica> Caracteristicas;
    private boolean EnAlquiler;
    private boolean EnVenta;
    @ManyToOne
    private Usuario UsuarioPropiedad;
    private int VisitasPropiedad;
    
    //   Constructores
    
    public Propiedad() {}
    
    public Propiedad(String DireccionPropiedad, float PrecioPropiedad, float MetrosConstruidosPropiedad, float MetrosTerrenoPropiedad,
            int NumeroPadronPropiedad, EnumEstadoPropiedad EstadoPropiedad, List<Caracteristica> Caracteristicas, boolean EnAlquiler, boolean EnVenta) {
        this.DireccionPropiedad = DireccionPropiedad;
        this.PrecioPropiedad = PrecioPropiedad;
        this.MetrosConstruidosPropiedad = MetrosConstruidosPropiedad;
        this.MetrosTerrenoPropiedad = MetrosTerrenoPropiedad;
        this.NumeroPadronPropiedad = NumeroPadronPropiedad;
        this.EstadoPropiedad = EstadoPropiedad;
        this.Caracteristicas = Caracteristicas;
        this.EnAlquiler = EnAlquiler;
        this.EnVenta = EnVenta;
    }
    
    public Propiedad(String DireccionPropiedad, float PrecioPropiedad, float MetrosConstruidosPropiedad, float MetrosTerrenoPropiedad,
            int NumeroPadronPropiedad, EnumEstadoPropiedad EstadoPropiedad, boolean EnAlquiler, boolean EnVenta) {
        this.DireccionPropiedad = DireccionPropiedad;
        this.PrecioPropiedad = PrecioPropiedad;
        this.MetrosConstruidosPropiedad = MetrosConstruidosPropiedad;
        this.MetrosTerrenoPropiedad = MetrosTerrenoPropiedad;
        this.NumeroPadronPropiedad = NumeroPadronPropiedad;
        this.EstadoPropiedad = EstadoPropiedad;
        this.EnAlquiler = EnAlquiler;
        this.EnVenta = EnVenta;
    }   
    
    //  Getters    
    public int getIdPropiedad() {return IdPropiedad;}
    public String getDireccionPropiedad() {return DireccionPropiedad;}
    public float getPrecioPropiedad() {return PrecioPropiedad;}
    public float getMetrosConstruidosPropiedad() {return MetrosConstruidosPropiedad;}
    public float getMetrosTerrenoPropiedad() {return MetrosTerrenoPropiedad;}
    public int getNumeroPadronPropiedad() {return NumeroPadronPropiedad;}
    public EnumEstadoPropiedad getEstadoPropiedad() {return EstadoPropiedad;}
    public List<Caracteristica> getCaracteristicas() {return Caracteristicas;}
    public boolean isEnAlquiler() {return EnAlquiler;}
    public boolean isEnVenta() {return EnVenta;}
    public Usuario getUsuarioPropiedad() {return UsuarioPropiedad;}
    public int getVisitasPropiedad() {return VisitasPropiedad;}   
    
    //  Setters    
    public void setIdPropiedad(int IdPropiedad) {this.IdPropiedad = IdPropiedad;}
    public void setDireccionPropiedad(String DireccionPropiedad) {this.DireccionPropiedad = DireccionPropiedad;}
    public void setPrecioPropiedad(float PrecioPropiedad) {this.PrecioPropiedad = PrecioPropiedad;}
    public void setMetrosConstruidosPropiedad(float MetrosConstruidosPropiedad) {this.MetrosConstruidosPropiedad = MetrosConstruidosPropiedad;}
    public void setMetrosTerrenoPropiedad(float MetrosTerrenoPropiedad) {this.MetrosTerrenoPropiedad = MetrosTerrenoPropiedad;}
    public void setNumeroPadronPropiedad(int NumeroPadronPropiedad) {this.NumeroPadronPropiedad = NumeroPadronPropiedad;}
    public void setEstadoPropiedad(EnumEstadoPropiedad EstadoPropiedad) {this.EstadoPropiedad = EstadoPropiedad;}
    public void setCaracteristicas(List<Caracteristica> Caracteristicas) {this.Caracteristicas = Caracteristicas;}
    public void setEnAlquiler(boolean EnAlquiler) {this.EnAlquiler = EnAlquiler;}
    public void setEnVenta(boolean EnVenta) {this.EnVenta = EnVenta;}
    public void setUsuarioPropiedad(Usuario UsuarioPropiedad) {
        this.UsuarioPropiedad = UsuarioPropiedad;
        if (!UsuarioPropiedad.getPropiedades().contains(this)) {
            UsuarioPropiedad.getPropiedades().add(this);
        }
    }
    public void setVisitasPropiedad(int VisitasPropiedad) {this.VisitasPropiedad = VisitasPropiedad;}    
    
    //  Caracteristicas
    public void addCaracteristica(Caracteristica caracteristica){this.Caracteristicas.add(caracteristica);}

    //  Visitas
    public void agregarVisita(){
        this.VisitasPropiedad++;
    }
}
