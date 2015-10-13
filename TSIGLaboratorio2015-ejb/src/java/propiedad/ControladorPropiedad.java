package propiedad;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

@Stateless
@ManagedBean
public class ControladorPropiedad {
    
    @EJB
    private ManejadorPropiedad mProp;
    
   
    
}
