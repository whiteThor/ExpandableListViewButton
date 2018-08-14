package android.ruben.com.expandablelistviewbutton.adapter;

import android.content.Context;
import android.ruben.com.expandablelistviewbutton.Model.GrupoInfo;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

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
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
