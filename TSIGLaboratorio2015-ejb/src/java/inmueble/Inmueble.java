
package inmueble;

import java.util.List;
import javax.persistence.Entity;
import propiedad.Propiedad;
import propiedad.caracteristica.Caracteristica;
import propiedad.enums.EnumEstadoPropiedad;
import propiedad.enums.EnumTipoInmueble;

@Entity
public class Inmueble extends Propiedad {
    private EnumTipoInmueble TipoInmueble;
    private int CantidadDormitorios;
    private int CantidadBanios;
    
    
    //  Constructores

    public Inmueble() {}

    public Inmueble(EnumTipoInmueble TipoInmueble, int CantidadDormitorios, int CantidadBanios, String DireccionPropiedad, 
            float PrecioPropiedad, float MetrosConstruidosPropiedad, float MetrosTerrenoPropiedad, int NumeroPadronPropiedad, 
            EnumEstadoPropiedad EstadoPropiedad, List<Caracteristica> Caracteristicas) {
        super(DireccionPropiedad, PrecioPropiedad, MetrosConstruidosPropiedad, MetrosTerrenoPropiedad, NumeroPadronPropiedad, 
                EstadoPropiedad, Caracteristicas);
        this.TipoInmueble = TipoInmueble;
        this.CantidadDormitorios = CantidadDormitorios;
        this.CantidadBanios = CantidadBanios;
    }
    
    
    //  Setters

    public void setTipoInmueble(EnumTipoInmueble tipoInmueble) {
        this.TipoInmueble = tipoInmueble;
    }

    public void setCantidadDormitorios(int CantidadDormitorios) {
        this.CantidadDormitorios = CantidadDormitorios;
    }

    public void setCantidadBanios(int CantidadBanios) {
        this.CantidadBanios = CantidadBanios;
    }
    
    
    //  Getters

    public EnumTipoInmueble getTipoInmueble() {
        return TipoInmueble;
    }

    public int getCantidadDormitorios() {
        return CantidadDormitorios;
    }

    public int getCantidadBanios() {
        return CantidadBanios;
    }
    
    
}
