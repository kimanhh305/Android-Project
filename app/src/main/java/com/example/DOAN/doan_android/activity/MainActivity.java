package com.example.DOAN.doan_android.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.DOAN.doan_android.R;
import com.example.DOAN.doan_android.adapter.LoaiSanPhamAdapter;
import com.example.DOAN.doan_android.model.GioHang;
import com.example.DOAN.doan_android.model.Loaisp;
import com.example.DOAN.doan_android.model.SanPham;
import com.example.DOAN.doan_android.model.SanPhamAdapter;
import com.example.DOAN.doan_android.ultil.CheckConnection;
import com.example.DOAN.doan_android.ultil.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ArrayList<Loaisp> mangloaisp;
    ArrayList<SanPham> mangSanpham;
    SanPhamAdapter sanphamAdapter;
    LoaiSanPhamAdapter loaispAdapter;
    DrawerLayout drawerLayout;
    Animation in, out;
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewManHinhChinh;
    NavigationView navigationView;
    ListView lvManHinhChinh;
    int id = 0;
    String tenloaisp = "";
    String hinhanhloaisp = "";
    public static ArrayList<GioHang> mangGioHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();

       if(CheckConnection.haveNetworkConnection(getApplicationContext()))
        {
            actionBar();
            addEEventViewFlipper();
            getdulieuloaisp();
            getdulieuSpmoinhat();
            catchOnItemListView();

        }else
        {
            CheckConnection.showToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối!!!");
            finish();
        }

    }

    private void catchOnItemListView() {
        lvManHinhChinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:{
                        Intent intent= new Intent(MainActivity.this,MainActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    }
                    case 1:{
                        Intent intent= new Intent(MainActivity.this,TTNuActivity.class);
                        intent.putExtra("idsanpham",mangloaisp.get(i).getId());
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    }
                    case 2:{
                        Intent intent= new Intent(MainActivity.this,TTNamActivity.class);
                        intent.putExtra("idsanpham",mangloaisp.get(i).getId());
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    }
                    case 3:{
                        Intent intent= new Intent(MainActivity.this,LHeActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    }
                    case 4:{
                        Intent intent= new Intent(MainActivity.this,ThongTinActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    }
                }
            }
        });
    }

    private void getdulieuloaisp() {
         RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
         JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdanloaisp, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!=null) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject object = response.getJSONObject(i);
                            id = object.getInt("id");
                            tenloaisp = object.getString("tenloaisp");
                            hinhanhloaisp = object.getString("hinhanh");
                            mangloaisp.add(new Loaisp(id, tenloaisp, hinhanhloaisp));
                            loaispAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    mangloaisp.add(3, new Loaisp(0, "Liên Hệ", "https://voip24h.vn/wp-content/uploads/2019/03/phone-png-mb-phone-icon-256.png"));
                    mangloaisp.add(4, new Loaisp(0, "Thông Tin", "http://cdn1.iconfinder.com/data/icons/Pretty_office_icon_part_2/128/personal-information.png"));

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.showToast_Short(getApplicationContext(),error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    private void getdulieuSpmoinhat() {
        RequestQueue request = Volley.newRequestQueue(MainActivity.this);
        JsonArrayRequest json = new JsonArrayRequest(Server.duongdansanphammoinhat,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String id = "", gia = "";
                String tensanham = "";
                String mota = "", hinhanh = "";
                String idsp = "";
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        id = object.getString("id");
                        tensanham = object.getString("tensanpham");
                        gia = object.getString("giasanpham");
                        hinhanh = object.getString("hinhanhsanpham");
                        mota = object.getString("mota");
                        idsp = object.getString("idsanpham");
                        mangSanpham.add(new SanPham(Integer.parseInt(id), tensanham, Integer.parseInt(gia), hinhanh, mota, Integer.parseInt(idsp)));
                        Log.i("APPAPI",hinhanh);

                        sanphamAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("kiemtra", error.toString());
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        request.add(json);
    }
    private void actionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Trang Chính");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void anhXa() {
        in = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_in_right);
        out = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_out_right);
        toolbar = (Toolbar) findViewById(R.id.toolbarTrangChinh);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
        recyclerViewManHinhChinh = (RecyclerView) findViewById(R.id.recyscle);
        navigationView = (NavigationView) findViewById(R.id.navigation);
        lvManHinhChinh = (ListView) findViewById(R.id.listviewMHC);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mangloaisp = new ArrayList<Loaisp>();
        mangloaisp.add(new Loaisp(0, "Trang Chính", "http://icons.iconarchive.com/icons/graphicloads/100-flat/128/home-icon.png"));
        loaispAdapter = new LoaiSanPhamAdapter(MainActivity.this, R.layout.dong_listview_loaisp, mangloaisp);
        lvManHinhChinh.setAdapter(loaispAdapter);
        mangSanpham = new ArrayList<SanPham>();
        sanphamAdapter = new SanPhamAdapter(MainActivity.this, mangSanpham);
        recyclerViewManHinhChinh.setHasFixedSize(true);
        recyclerViewManHinhChinh.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        recyclerViewManHinhChinh.setAdapter(sanphamAdapter);
        if (mangGioHang != null) {

        } else {
            mangGioHang = new ArrayList<>();
        }

    }

    private void addEEventViewFlipper() {
        ArrayList<String> arrQuangCao = new ArrayList<>();
        arrQuangCao.add("http://kinhnghiembanhang.vn/wp-content/uploads/2017/07/thietkenoithatshopthoitrang_1.jpg");
        arrQuangCao.add("https://tkshop.vn/wp-content/uploads/2018/07/Thie%CC%82%CC%81t-ke%CC%82%CC%81-cu%CC%9B%CC%89a-ha%CC%80ng-tho%CC%9B%CC%80i-trang-nam-Leo-Vatino.jpg");
        arrQuangCao.add("https://cosp.com.vn/images/stores/2017/11/23/thi_cong_shop_thoi_trang-redep1.jpg");
        arrQuangCao.add("https://blog.maybanhang.net/hs-fs/hubfs/Mai%20Ph%C6%B0%C6%A1ng/Thoi%20trang/kinh-nghiem-mo-shop-thoi-trangg.jpg?width=600&name=kinh-nghiem-mo-shop-thoi-trangg.jpg");
        for (int i = 0; i < arrQuangCao.size(); i++) {
            ImageView imageView = new ImageView(MainActivity.this);
            Picasso.with(MainActivity.this).load(arrQuangCao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(2000);
        viewFlipper.setInAnimation(in);
        viewFlipper.setOutAnimation(out);
        viewFlipper.setAutoStart(true);
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
    public class readJson extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder=new StringBuilder();
            try {
                URL url=new URL(strings[0]);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                InputStream inputStream=httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String line="";
                while ((line=bufferedReader.readLine())!=null){
                    stringBuilder.append(line);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            try {
                JSONArray jsonArray=new JSONArray(s);
                String id = "", gia = "";
                String tensanham = "";
                String mota = "", hinhanh = "";
                String idsp = "";
                Log.d("kiemtra",jsonArray.toString()+"chn doi");
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject object = jsonArray.getJSONObject(i);
                        id = object.getString("id");
                        tensanham = object.getString("tensanpham");
                        gia = object.getString("giasanpham");
                        hinhanh = object.getString("hinhanhsanpham");
                        mota = object.getString("mota");
                        idsp = object.getString("idsanpham");
                        mangSanpham.add(new SanPham(Integer.parseInt(id), tensanham, Integer.parseInt(gia), hinhanh, mota, Integer.parseInt(idsp)));
                        sanphamAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
