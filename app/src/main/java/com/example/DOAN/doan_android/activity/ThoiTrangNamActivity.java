package com.example.DOAN.doan_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.DOAN.doan_android.R;
import com.example.DOAN.doan_android.adapter.ThoiTrangNamAdapter;
import com.example.DOAN.doan_android.model.SanPham;
import com.example.DOAN.doan_android.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ThoiTrangNamActivity extends AppCompatActivity{
    Toolbar toolbarThoitrangnam;
    ListView lvThoitrangnam;
    ThoiTrangNamAdapter thoiTrangNamAdapter;
    ArrayList<SanPham> mangttnam;
    int idLapTop=0;
    int dodai=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lhe);
        anhXa();
        getIdLaptop();
        actionToolbar();
        readJsonData();
        clickListView();
    }

    private void clickListView() {
        lvThoitrangnam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(ThoiTrangNamActivity.this,ChiTietActivity.class);
                intent.putExtra("chitietsanpham",mangttnam.get(i));
                startActivity(intent);
            }
        });
    }

    private void actionToolbar() {
        setSupportActionBar(toolbarThoitrangnam);
        getSupportActionBar().setTitle("Tên sản phẩm");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarThoitrangnam.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void readJsonData() {
        RequestQueue requestQueue= Volley.newRequestQueue(ThoiTrangNamActivity.this);
        String duongdan= Server.duongdanthoitrangnam;
        StringRequest str=new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    int id=0;
                    String tensp="";
                    int giasp=0;
                    String hinhanhsp="",motasp="";
                    int idsp=0;
                    JSONArray jsonArray=new JSONArray(response);
                    dodai=jsonArray.length();
                    for(int i=0;i<dodai;i++){
                        JSONObject object=jsonArray.getJSONObject(i);
                        id=object.getInt("id");
                        tensp=object.getString("tensanpham");
                        giasp=object.getInt("giasanpham");
                        hinhanhsp=object.getString("hinhanhsanpham");
                        motasp=object.getString("mota");
                        idsp=object.getInt("idsanpham");
                        SanPham sanPham=new SanPham(id,tensp,giasp,hinhanhsp,motasp,idsp);
                        mangttnam.add(sanPham);
                        thoiTrangNamAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ThoiTrangNamActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>param=new HashMap<>();
                param.put("idsanpham", String.valueOf(idLapTop));
                return param;
            }
        };
        requestQueue.add(str);
    }

    private void getIdLaptop() {
        idLapTop=getIntent().getIntExtra("id",-1);
    }

    private void anhXa() {
        toolbarThoitrangnam= (Toolbar) findViewById(R.id.toolbar_thoitrangnam);
        lvThoitrangnam= (ListView) findViewById(R.id.lv_thoitrangnam);
        mangttnam=new ArrayList<>();
        thoiTrangNamAdapter=new ThoiTrangNamAdapter(ThoiTrangNamActivity.this,mangttnam);
        lvThoitrangnam.setAdapter(thoiTrangNamAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_giohang){
            Intent intent=new Intent(ThoiTrangNamActivity.this,GioHangActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}

