package android.ruben.com.expandablelistviewbutton.Model;

public class DetalleInfo {

    private String Secuencia;
    private String nombre;

    public DetalleInfo() {
    }

    public DetalleInfo(String secuencia, String nombre) {
        Secuencia = secuencia;
        this.nombre = nombre;
    }

    public String getSecuencia() {
        return Secuencia;
    }

    public void setSecuencia(String secuencia) {
        Secuencia = secuencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
