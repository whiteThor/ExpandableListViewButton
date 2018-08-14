package android.ruben.com.expandablelistviewbutton;

import android.os.Bundle;
import android.ruben.com.expandablelistviewbutton.Model.DetalleInfo;
import android.ruben.com.expandablelistviewbutton.Model.GrupoInfo;
import android.ruben.com.expandablelistviewbutton.adapter.ListAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<GrupoInfo> mGrupoInfos = new ArrayList<GrupoInfo>();
    private LinkedHashMap<String, GrupoInfo> mLinkedHashMap = new LinkedHashMap<String, GrupoInfo>();

    private ListAdapter mListAdapter;

    private ExpandableListView mListView;
    private ExpandableListView.OnChildClickListener myListItemClicked = new ExpandableListView.OnChildClickListener() {
        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

            GrupoInfo headerInfo = mGrupoInfos.get(groupPosition);
            DetalleInfo detailInfo = headerInfo.getDetalleInfoList().get(childPosition);


            Toast.makeText(getBaseContext(), "Clicked on Detail " + headerInfo.getNombre()
                    + "/" + detailInfo.getNombre(), Toast.LENGTH_LONG).show();
            return false;
        }
    };
    private ExpandableListView.OnGroupClickListener myListGroupClicked = new ExpandableListView.OnGroupClickListener() {
        @Override
        public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

            GrupoInfo headerInfo = mGrupoInfos.get(groupPosition);

            Toast.makeText(getBaseContext(), "Child on Header " + headerInfo.getNombre(),
                    Toast.LENGTH_LONG).show();
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinnerDep = findViewById(R.id.departamento);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.dept_array, android.R.layout.simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDep.setAdapter(arrayAdapter);

        loadData();

        mListView = findViewById(R.id.myList);

        mListAdapter = new ListAdapter(this, mGrupoInfos);

        mListView.setAdapter(mListAdapter);

        expandAll();

        Button button = findViewById(R.id.add);

        button.setOnClickListener(this);


        mListView.setOnChildClickListener(myListItemClicked);

        mListView.setOnGroupClickListener(myListGroupClicked);

    }

    private void loadData() {

        addProduct("Apparel", "Activewear");
        addProduct("Apparel", "Jackets");
        addProduct("Apparel", "Shorts");

        addProduct("Beauty", "Fragrances");
        addProduct("Beauty", "Makeup");

    }

    public void expandAll() {
        int count = mListAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            mListView.expandGroup(i);
        }
    }

    public void collapseAll() {
        int count = mListAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            mListView.collapseGroup(i);
        }

    }

    //here we maintain our products in various departments
    private int addProduct(String department, String product) {

        int groupPosition = 0;

        //check the hash map if the group already exists
        GrupoInfo headerInfo = mLinkedHashMap.get(department);
        //add the group if doesn't exists
        if (headerInfo == null) {
            headerInfo = new GrupoInfo();
            headerInfo.setNombre(department);
            mLinkedHashMap.put(department, headerInfo);
            mGrupoInfos.add(headerInfo);
        }

        //get the children for the group
        ArrayList<DetalleInfo> productList = (ArrayList<DetalleInfo>) headerInfo.getDetalleInfoList();
        //size of the children list
        int listSize = productList != null ? productList.size() : 1;
        //add to the counter
        listSize++;

        //create a new child and add that to the group
        DetalleInfo detailInfo = new DetalleInfo();
        detailInfo.setSecuencia(String.valueOf(listSize));
        detailInfo.setNombre(product);
        productList.add(detailInfo);
        headerInfo.setDetalleInfoList(productList);

        //find the group position inside the list
        groupPosition = mGrupoInfos.indexOf(headerInfo);
        return groupPosition;
    }


    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.add) {

            Spinner spinner = findViewById(R.id.departamento);
            String departamento = spinner.getSelectedItem().toString();

            EditText editText = findViewById(R.id.product);
            String producto = editText.getText().toString();
            editText.setText("");

            int pos = addProduct(departamento, producto);
            mListAdapter.notifyDataSetChanged();

            collapseAll();

            mListView.expandGroup(pos);
            mListView.setSelectedGroup(pos);

        }

    }
}
