
package interfaz.registro;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class RegistrarPropiedad implements Serializable {
    private float CoordX;
    private float CoordY;
    
    //  Setters

    public void setCoordX(float CoordX) {this.CoordX = CoordX;}
    public void setCoordY(float CoordY) {this.CoordY = CoordY;}
    
    //  Getters

    public float getCoordX() {return CoordX;}
    public float getCoordY() {return CoordY;}
    
    
    
    
}
