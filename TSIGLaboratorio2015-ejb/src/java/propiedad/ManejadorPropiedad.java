
package propiedad;

import inmueble.Inmueble;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import propiedad.enums.EnumTipoInmueble;
import terreno.Terreno;

@ManagedBean
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ManejadorPropiedad {
    @PersistenceContext(unitName = "TSIGLaboratorio2015-ejbPU")
    private EntityManager em ;
    
    public int CrearPropiedad(Propiedad propiedad){
        try{
            em.persist(propiedad);
            return propiedad.getIdPropiedad();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public int ActualizarPropiedad(Propiedad propiedad){
        try{
            em.merge(propiedad);
            return propiedad.getIdPropiedad();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public int BorrarPropiedad(Propiedad propiedad){
        try{
            em.remove(propiedad);
            return 1;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public Propiedad GetPropiedad(int id){
        try{
            return em.find(Propiedad.class, id);
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public List<Propiedad> ListarPropiedades(){
        List<Propiedad> lista = new ArrayList<>();
        try{
            TypedQuery<Propiedad> query = em.createQuery("SELECT p FROM Propiedad p", Propiedad.class);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    
    public List<Propiedad> ListarPropiedadesUsuario(int IdUsuario){
        List<Propiedad> lista = new ArrayList<>();
        try{
            TypedQuery<Propiedad> query = em.createQuery("SELECT p FROM Propiedad p, Usuario u WHERE p MEMBER OF u.Propiedades AND u.IdUsuario= :idUsuario", Propiedad.class);
            query.setParameter("idUsuario", IdUsuario);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    
    public List<Propiedad> ListarTerrenos(){
        List<Propiedad> terrenos = new ArrayList<>();
        try{
            TypedQuery<Propiedad> query = em.createQuery("SELECT t FROM Terreno t", Propiedad.class);
            List<Propiedad> lista;
            lista = query.getResultList();
            for(Propiedad prop : lista){
                if(prop instanceof Terreno) terrenos.add(prop);
            }
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return terrenos;
    }
    
    public List<Propiedad> ListarTerrenosUsuario(int IdUsuario){
        List<Propiedad> lista = new ArrayList<>();
        try{
            TypedQuery<Propiedad> query = em.createQuery("SELECT t FROM Terreno t, Usuario u WHERE t MEMBER OF u.Propiedades AND u.IdUsuario= :idUsuario", Propiedad.class);
            query.setParameter("idUsuario", IdUsuario);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    
    public List<Propiedad> ListarCasas(){
        List<Propiedad> casas = new ArrayList<>();
        try{
            TypedQuery<Propiedad> query = em.createQuery("SELECT i FROM Inmueble i", Propiedad.class);
            List<Propiedad> lista;
            lista = query.getResultList();
            for(Propiedad prop : lista){
                if(((Inmueble) prop).getTipoInmueble()==EnumTipoInmueble.Casa) casas.add(prop);
            }
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return casas;
    }
    
    public List<Propiedad> ListarCasasUsuario(int IdUsuario){
        List<Propiedad> casas = new ArrayList<>();
        try{
            TypedQuery<Propiedad> query = em.createQuery("SELECT i FROM Inmueble i, Usuario u WHERE i MEMBER OF u.Propiedades AND u.IdUsuario= :idUsuario", Propiedad.class);
            query.setParameter("idUsuario", IdUsuario);
            List<Propiedad> lista;
            lista = query.getResultList();
            for(Propiedad prop: lista){
                if(((Inmueble)prop).getTipoInmueble()==EnumTipoInmueble.Casa) casas.add(prop);
            }
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return casas;
    }
    public List<Propiedad> ListarApartamentos(){
        List<Propiedad> apartamentos = new ArrayList<>();
        try{
            TypedQuery<Propiedad> query = em.createQuery("SELECT i FROM Inmueble i", Propiedad.class);
            List<Propiedad> lista;
            lista = query.getResultList();
            for(Propiedad prop : lista){
                if(((Inmueble) prop).getTipoInmueble()==EnumTipoInmueble.Apartamento) apartamentos.add(prop);
            }
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return apartamentos;
    }
    
    public List<Propiedad> ListarApartamentosUsuario(int IdUsuario){
        List<Propiedad> apartamentos = new ArrayList<>();
        try{
            TypedQuery<Propiedad> query = em.createQuery("SELECT i FROM Inmueble i, Usuario u WHERE i MEMBER OF u.Propiedades AND u.IdUsuario= :idUsuario", Propiedad.class);
            query.setParameter("idUsuario", IdUsuario);
            List<Propiedad> lista;
            lista = query.getResultList();
            for(Propiedad prop: lista){
                if(((Inmueble)prop).getTipoInmueble()==EnumTipoInmueble.Apartamento) apartamentos.add(prop);
            }
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return apartamentos;
    }
    public int InsertarUbicacionPropiedad(int IdPropiedad, float CoordX, float CoordY){
        Query query = em.createNativeQuery("UPDATE Propiedad SET the_geom = ST_Transform(ST_GeomFromText('POINT(" +CoordX + " " + CoordY+ " )', '4326'), 32721) WHERE Propiedad.idpropiedad= "+IdPropiedad);
        try{
            return query.executeUpdate();
        }catch(Exception ex){
            System.out.println("Error:" + ex.getMessage());
        }
        return -1;
    }
    
    public String ObtenerCalleCercana(float CoordX, float CoordY){
        Query query = em.createNativeQuery("SELECT c.nombre_ine FROM calles c WHERE ST_DWithin(ST_Transform(ST_GeomFromText('POINT("+CoordX + " " + CoordY + ")', '4326'), 32721), c.the_geom, '25') ORDER BY ST_Distance(ST_Transform(ST_GeomFromText('POINT("+CoordX + " " + CoordY + ")', '4326'), 32721), c.the_geom) ASC");
        try{
            return (String) query.getResultList().get(0);
        }catch(Exception ex){
            System.out.println("Error: "+ ex.getMessage());
        }
        return "";
    }
    
    public String ObtenerNumeroPuerta(float CoordX, float CoordY){
        Query query = em.createNativeQuery("SELECT n.nro FROM numero_puertas n WHERE ST_DWithin(ST_Transform(ST_GeomFromText('POINT("+CoordX + " " + CoordY + ")', '4326'), 32721), n.the_geom, '25') ORDER BY ST_Distance(ST_Transform(ST_GeomFromText('POINT("+CoordX + " " + CoordY + ")', '4326'), 32721), n.the_geom) ASC");
        try{
            return (String) query.getResultList().get(0);
        }catch(Exception ex){
            System.out.println("Error: "+ ex.getMessage());
        }
        return "";
    }
    
    public Propiedad GetPropiedad(String DireccionPropiedad){
        try{
            TypedQuery<Propiedad> query = em.createQuery("SELECT p FROM Propiedad p WHERE p.DireccionPropiedad= :dirProp", Propiedad.class);
            query.setParameter("dirProp", DireccionPropiedad);
            return (Propiedad) query.getSingleResult();
        }catch(Exception ex){}
        return null;
    }
    
    public List<Propiedad> GetPropiedadCercanasPtoInteres(String TipoPuntoInteres, int MetrosDistancia){
        List<Propiedad> lista = new ArrayList<>();
        try{
            Query query = em.createNativeQuery("SELECT * FROM Propiedad p, puntosinteres pi WHERE ST_DWithin(p.the_geom, pi.the_geom, "+MetrosDistancia+" AND pi.tipo= " + TipoPuntoInteres + " ORDER BY p.idpropiedad ASC");
            lista = query.getResultList();
        }catch(Exception ex){}
        return lista;
    }
    
    public List<Integer> GetPropiedadesCercanasPtoInteres(List<String> TiposPuntoInteres, int MetrosDistancia){
        List<Integer> lista = new ArrayList<>();
        String coleccion = "";
        for (int i = 0; i < TiposPuntoInteres.size(); i++) {
            coleccion = coleccion +"'"+TiposPuntoInteres.get(i)+"'";
            if(i+1<TiposPuntoInteres.size())coleccion += ", ";
        }
        try{
            Query query = em.createNativeQuery("SELECT DISTINCT p.idpropiedad FROM Propiedad p, puntosinteres pi WHERE ST_DWithin(p.the_geom, pi.the_geom, "+MetrosDistancia+") AND pi.tipo IN (" + coleccion + ") ORDER BY p.idpropiedad ASC");
            lista = query.getResultList();
        }catch(Exception ex){}
        return lista;
    }
    
    public List<String> GetPuntosInteresCercanoPropiedad(int IdPropiedad, int MetrosDistancia){
        List<String> lista = new ArrayList<>();
        try{
            Query query = em.createNativeQuery("SELECT * FROM Propiedad p, puntosinteres pi WHERE ST_DWithin(p.the_geom, pi.the_geom, "+MetrosDistancia+" AND p.idpropiedad= " + IdPropiedad);
            lista = query.getResultList();
        }catch(Exception ex){}
        return lista;
    }
    
    public Map<String, Integer> GetDistanciasPuntosInteres(int IdPropiedad){
        Map<String, Integer> distancias = new HashMap<>();
        try{
            Query query = em.createNativeQuery("SELECT ST_DISTANCE(p.the_geom, pi.the_geom) FROM propiedad p, puntosinteres pi WHERE p.idpropiedad="+IdPropiedad+ " ORDER BY pi.nombre");
            List<Double> listDistancias = query.getResultList();
            
            query = em.createNativeQuery("SELECT pi.nombre FROM puntosinteres pi ORDER BY pi.nombre ASC");
            List<String> listNombres = query.getResultList();
            
            for (int i = 0; i < listDistancias.size(); i++) {
                distancias.put(listNombres.get(i).replaceAll(" ", ""), listDistancias.get(i).intValue());
            }
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return distancias;
    }
    
    public List<Integer> GetPropiedadesPorCaracteristicas(List<Integer> IdsCaracteristica){
        List<Integer> lista = new ArrayList<>();
        try{
            Query query = em.createQuery("SELECT DISTINCT p.IdPropiedad FROM Propiedad p, Caracteristica c WHERE c.IdCaracteristica IN (:idsCaracteristicas) AND c MEMBER p.Caracteristicas");
            query.setParameter("idsCaracteristicas", IdsCaracteristica);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());
        }
        return lista;
    }
    
     public int SetearImagenes(String imagenuno,String imagendos,String imagentres, int id){
        Query query = em.createNativeQuery(""
                + "INSERT INTO imagenes(\"idPropiedad\", location) VALUES ("+id+",'"+imagenuno+"');"
                + "INSERT INTO imagenes(\"idPropiedad\", location) VALUES ("+id+",'"+imagendos+"');"
                + "INSERT INTO imagenes(\"idPropiedad\", location) VALUES ("+id+",'"+imagentres+"')");
        try{
            return query.executeUpdate();
        }catch(Exception ex){
            System.out.println("Error:" + ex.getMessage());
        }
        return -1;
    }
     
     public List<String> GetImagenesPropiedad(int IdPropiedad){
        List<String> lista = new ArrayList<>();
        try{
            Query query = em.createNativeQuery("SELECT imagenes.\"location\" FROM public.imagenes WHERE imagenes.\"idPropiedad\" = " + IdPropiedad);
            lista = query.getResultList();
        }catch(Exception ex){}
        return lista;
    }
    
}

