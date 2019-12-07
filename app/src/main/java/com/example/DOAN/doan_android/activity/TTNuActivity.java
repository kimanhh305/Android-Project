package com.example.DOAN.doan_android.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.DOAN.doan_android.R;
import com.example.DOAN.doan_android.adapter.ThoiTrangNuAdapter;
import com.example.DOAN.doan_android.model.SanPham;
import com.example.DOAN.doan_android.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TTNuActivity extends AppCompatActivity {
    Toolbar toolbar;
    ListView listViewttnu;
    ThoiTrangNuAdapter thoiTrangNuAdapter;
    ArrayList<SanPham> arrayListTTNu;
    int idttn=0;
    int page=1;
    View footerview;
    boolean dangload=false;
    boolean hetdulieu=false;
    mHandler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thoitrang_nu);
        Anhxa();
        GetIdLoaisp();
        ActionToolbar();
        GetData(page);
        LoadDuLieu();
        clickctsp();
    }

    private void clickctsp() {
        listViewttnu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent= new Intent(TTNuActivity.this,CTSPActivity.class);
                Bundle bd= new Bundle();
                bd.putInt("id",arrayListTTNu.get(position).getId());
                bd.putString("tenchitiet",arrayListTTNu.get(position).getTensanpham());
                bd.putInt("giachitiet",arrayListTTNu.get(position).getGiasanpham());
                bd.putString("motachitiet",arrayListTTNu.get(position).getMotasanpham());
                bd.putString("hinhanhchitiet",arrayListTTNu.get(position).getHinhanhsanpham());
                bd.putInt("idsanpham",arrayListTTNu.get(position).getIdsanpham());
                intent.putExtra("chitietsanpham",bd);
                startActivity(intent);
            }
        });
    }


    private void LoadDuLieu() {
        listViewttnu.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount!=0 && dangload==false && hetdulieu==false)
                {
                    dangload=true;
                    ThreadData threadData= new ThreadData();
                    threadData.start();
                }
            }
        });
    }

    private void GetData(int Page) {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        String duongdan= Server.duongdanthoitrangnu + String.valueOf(Page);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id=0;
                String TenTTN="";
                int gia=0;
                String Hinhanh="";
                String mota="";
                int idspttn=0;
                if(response!=null && response.length()!=2)
                {
                    listViewttnu.removeFooterView(footerview);
                    try {
                        JSONArray jsonArray= new JSONArray(response);
                        for(int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            id=jsonObject.getInt("id");
                            TenTTN=jsonObject.getString("tensanpham");
                            gia=jsonObject.getInt("giasanpham");
                            Hinhanh=jsonObject.getString("hinhanhsanpham");
                            mota=jsonObject.getString("mota");
                            idspttn=jsonObject.getInt("idsanpham");
                            arrayListTTNu.add(new SanPham(id,TenTTN,gia,Hinhanh,mota,idspttn));
                            //Toast.makeText(TTNuActivity.this,id+"",Toast.LENGTH_SHORT).show();
                            thoiTrangNuAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    hetdulieu=true;
                    listViewttnu.removeFooterView(footerview);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> pa = new HashMap<String, String>();
                pa.put("idsanpham",String.valueOf(idttn));
                return pa;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void GetIdLoaisp() {
        idttn= getIntent().getIntExtra("idsanpham",-1);
        Log.d("giatriloaisp",idttn + "");
    }

    private void Anhxa() {
        toolbar=findViewById(R.id.toolbar_thoitrang_nu);
        listViewttnu=findViewById(R.id.lv_thoitrangnu);
        arrayListTTNu= new ArrayList<>();
        thoiTrangNuAdapter= new ThoiTrangNuAdapter(getApplicationContext(),arrayListTTNu);
        listViewttnu.setAdapter(thoiTrangNuAdapter);
        LayoutInflater layoutInflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview = layoutInflater.inflate(R.layout.progressbar,null);
        mHandler = new mHandler();
    }

    public class mHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    listViewttnu.addFooterView(footerview);
                    break;
                case 1:
                    GetData(++page);
                    dangload=false;
                    break;
            }
            super.handleMessage(msg);
        }
    }
    public class ThreadData extends Thread{
        @Override
        public void run() {
            mHandler.sendEmptyMessage(0);
            try{
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            Message message= mHandler.obtainMessage(1);
            mHandler.sendMessage(message);
            super.run();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_giohang:
                Intent intent= new Intent(getApplicationContext(),com.example.DOAN.doan_android.activity.GHang.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
