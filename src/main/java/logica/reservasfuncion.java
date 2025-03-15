package logica;

import java.util.Date;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;


@Entity
public class reservasfuncion implements Serializable {
    @Id
    @GeneratedValue (strategy=GenerationType.AUTO)
    private int id;
    private String nombre;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    private String espacio;
    private int duracion;

    public reservasfuncion() {
    }

    public reservasfuncion(String nombre, Date fecha, String espacio, int duracion) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.espacio = espacio;
        this.duracion = duracion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEspacio() {
        return espacio;
    }

    public void setEspacio(String espacio) {
        this.espacio = espacio;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
 
    
    
    
}
