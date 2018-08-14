package android.ruben.com.expandablelistviewbutton.Model;


import java.util.ArrayList;
import java.util.List;

public class GrupoInfo {
    private String mNombre;
    private List<DetalleInfo> mDetalleInfoList = new ArrayList<DetalleInfo>();

    public GrupoInfo() {
    }

    public GrupoInfo(String nombre, List<DetalleInfo> detalleInfoList) {
        mNombre = nombre;
        mDetalleInfoList = detalleInfoList;
    }

    public String getNombre() {
        return mNombre;
    }

    public void setNombre(String nombre) {
        mNombre = nombre;
    }

    public List<DetalleInfo> getDetalleInfoList() {
        return mDetalleInfoList;
    }

    public void setDetalleInfoList(List<DetalleInfo> detalleInfoList) {
        mDetalleInfoList = detalleInfoList;
    }
}
