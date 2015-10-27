
package terreno;

import java.util.List;
import javax.persistence.Entity;
import propiedad.Propiedad;
import propiedad.caracteristica.Caracteristica;
import propiedad.enums.EnumEstadoPropiedad;

@Entity
public class Terreno extends Propiedad{

    //  Constructores
    
    public Terreno() {}

    public Terreno(String DireccionPropiedad, float PrecioPropiedad, float MetrosConstruidosPropiedad, float MetrosTerrenoPropiedad, int NumeroPadronPropiedad, 
            EnumEstadoPropiedad EstadoPropiedad, List<Caracteristica> Caracteristicas,  boolean EnAlquiler, boolean EnVenta) {
        super(DireccionPropiedad, PrecioPropiedad, MetrosConstruidosPropiedad, MetrosTerrenoPropiedad, NumeroPadronPropiedad, EstadoPropiedad, Caracteristicas,
                 EnAlquiler, EnVenta);
    }
    public Terreno(String DireccionPropiedad, float PrecioPropiedad, float MetrosConstruidosPropiedad, float MetrosTerrenoPropiedad, int NumeroPadronPropiedad, 
            EnumEstadoPropiedad EstadoPropiedad,  boolean EnAlquiler, boolean EnVenta) {
        super(DireccionPropiedad, PrecioPropiedad, MetrosConstruidosPropiedad, MetrosTerrenoPropiedad, NumeroPadronPropiedad, EstadoPropiedad,
                 EnAlquiler, EnVenta);
    }
    
    
}
