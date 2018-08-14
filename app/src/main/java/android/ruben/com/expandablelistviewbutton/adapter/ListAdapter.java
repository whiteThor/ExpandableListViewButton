package android.ruben.com.expandablelistviewbutton.adapter;

import android.content.Context;
import android.ruben.com.expandablelistviewbutton.Model.DetalleInfo;
import android.ruben.com.expandablelistviewbutton.Model.GrupoInfo;
import android.ruben.com.expandablelistviewbutton.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<GrupoInfo>mGrupoInfos ;

    public ListAdapter(Context context, List<GrupoInfo> grupoInfos) {
        mContext = context;
        mGrupoInfos = grupoInfos;
    }

    @Override
    public int getGroupCount() {
        return mGrupoInfos.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mGrupoInfos.get(groupPosition).getDetalleInfoList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGrupoInfos.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mGrupoInfos.get(groupPosition).getDetalleInfoList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {

        GrupoInfo grupoInfo = (GrupoInfo) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.group_row, parent, false);
        }

        TextView textView = view.findViewById(R.id.header);

        textView.setText(grupoInfo.getNombre());

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {

        DetalleInfo detalleInfo = (DetalleInfo) getChild(groupPosition, childPosition);

        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            view = layoutInflater.inflate(R.layout.child_row, parent, false);
        }

        TextView textSecuencia = view.findViewById(R.id.secuence);
        TextView textChildItem = view.findViewById(R.id.childItem);
        textSecuencia.setText(detalleInfo.getSecuencia());
        textChildItem.setText(detalleInfo.getNombre());


        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
