package com.example.DOAN.doan_android.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.DOAN.doan_android.R;
import com.example.DOAN.doan_android.adapter.GioHangAdapter;
import com.example.DOAN.doan_android.model.GioHang;
import com.example.DOAN.doan_android.model.SanPham;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;



public class CTSPActivity extends AppCompatActivity {
    GioHangAdapter gioHangAdapter;
    TextView txt_tensp, txt_giasp, txt_motactsp;
    ImageView img;
    Button btnDatMua;
    Spinner spinner;
    int id = 0;
    String tenchitiet = "";
    int giachitiet = 0;
    String motachitiet = "", hinhanhchitiet = "";
    int idsanpham = 0;
    Toolbar toolbarChiTiet;
    int soluong;
    long giamoi;

    ArrayList<SanPham> arrayListsp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);

        txt_tensp =  findViewById(R.id.tv_tenchitietsanpham);
        img =  findViewById(R.id.img_chitietsanpham);
        txt_giasp = findViewById(R.id.tv_giachitietsanpham);
        txt_motactsp = findViewById(R.id.tv_motachitietsanpham);

        spinner=  findViewById(R.id.spinner);
        btnDatMua= findViewById(R.id.btn_datmua);
        gioHangAdapter=new GioHangAdapter(CTSPActivity.this,MainActivity.mangGioHang);


        //arrayListGH= new ArrayList<>();


        //ActionToolbar();
        LayDuLieu();
        eventSpinner();
        addEvent();


    }




    private void LayDuLieu() {
        Bundle bd = getIntent().getBundleExtra("chitietsanpham");
        txt_tensp.setText(bd.get("tenchitiet").toString());

        hinhanhchitiet = bd.getString("hinhanhchitiet");
        id=bd.getInt("id");
        tenchitiet=bd.getString("tenchitiet");
        giachitiet=bd.getInt("giachitiet");
        soluong=bd.getInt("soluong");



        load_hinh(bd.getString("hinhanhchitiet"));
        txt_giasp.setText(bd.get("giachitiet").toString() + "Đ");
        txt_motactsp.setText(bd.get("motachitiet").toString());
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarChiTiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChiTiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    void load_hinh(String diachi) {
        URL url = null;
        try {
            url = new URL(diachi);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.connect();
            int resCode = httpConn.getResponseCode();
            if (resCode == HttpURLConnection.HTTP_OK) {
                InputStream in = httpConn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(in);
                img.setImageBitmap(bitmap);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    private void addEvent() {
        btnDatMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.mangGioHang.size() > 0) {
                    int sl = Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exsist = false;
                    for (int i = 0; i < MainActivity.mangGioHang.size(); i++) {
                        if (MainActivity.mangGioHang.get(i).getIdsp() == id) {
                            MainActivity.mangGioHang.get(i).setSoluongsp(MainActivity.mangGioHang.get(i).getSoluongsp() + sl);
                            if (MainActivity.mangGioHang.get(i).getSoluongsp() >= 10) {
                                MainActivity.mangGioHang.get(i).setSoluongsp(10);
                            }
                            MainActivity.mangGioHang.get(i).setGiasp(giachitiet * sl + MainActivity.mangGioHang.get(i).getGiasp());
                            exsist = true;
                        }
                    }
                    if (exsist == false) {
                            soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                            giamoi = soluong * giachitiet;
                            MainActivity.mangGioHang.add(new GioHang(id, tenchitiet, giamoi, hinhanhchitiet, soluong));
                            gioHangAdapter.notifyDataSetChanged();
                    }
                }
                else {
                         soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                         giamoi = soluong * giachitiet;
                         MainActivity.mangGioHang.add(new GioHang(id, tenchitiet, giamoi, hinhanhchitiet, soluong));
                         gioHangAdapter.notifyDataSetChanged();
                }
                Intent intent= new Intent(CTSPActivity.this,GHang.class);
                //Toast.makeText(CTSPActivity.this,giachitiet+"",Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }
        });
    }

    private void eventSpinner() {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int i = 1; i <= 10; i++) {
            arr.add(i);
        }
        ArrayAdapter adapter = new ArrayAdapter(CTSPActivity.this, android.R.layout.simple_spinner_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
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