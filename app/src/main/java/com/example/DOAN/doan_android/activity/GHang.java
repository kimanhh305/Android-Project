package com.example.DOAN.doan_android.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.DOAN.doan_android.R;
import com.example.DOAN.doan_android.adapter.GioHangAdapter;

import java.text.DecimalFormat;

public class GHang extends AppCompatActivity {
    ListView lvgiohang;
    TextView tv_thongbao;
    static TextView tv_tongtien;
    Button btnThanhtoan, btnTieptucmua;
    Toolbar toolbargiohang;
    public static GioHangAdapter gioHangAdapter;
    //ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        Anhxa();
        ActionToolbar();
        KiemTraDulieu();
        DoDuLieu();
        NhanXoaDuLieu();
        ClickButton();
    }

    private void ClickButton() {
        btnTieptucmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(GHang.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btnThanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.mangGioHang.size()>0){
                    Intent intent=new Intent(GHang.this,ThongTinActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(GHang.this, "Giỏ hàng của bạn chưa có sản phẩm", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void NhanXoaDuLieu() {
        lvgiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder= new AlertDialog.Builder(GHang.this);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc chắn xóa");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(MainActivity.mangGioHang.size()<=0)
                        {
                            tv_thongbao.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            MainActivity.mangGioHang.remove(position);
                            gioHangAdapter.notifyDataSetChanged();
                            DoDuLieu();
                            if(MainActivity.mangGioHang.size()<=0)
                            {
                                tv_thongbao.setVisibility(View.VISIBLE);
                            }
                            else
                            {
                                tv_thongbao.setVisibility(View.INVISIBLE);
                                gioHangAdapter.notifyDataSetChanged();
                                DoDuLieu();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gioHangAdapter.notifyDataSetChanged();
                        DoDuLieu();
                    }
                });
                builder.show();
                return true;
            }
        });
    }


    public static void DoDuLieu() {
        long tongtien = 0;
        for (int i = 0; i < MainActivity.mangGioHang.size(); i++) {
            tongtien += MainActivity.mangGioHang.get(i).getGiasp();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tv_tongtien.setText(decimalFormat.format(tongtien) + "Đ");
    }

    private void KiemTraDulieu() {
        if (MainActivity.mangGioHang.size() <= 0) {
            gioHangAdapter.notifyDataSetChanged();
            tv_thongbao.setVisibility(View.VISIBLE);
            lvgiohang.setVisibility(View.INVISIBLE);
        } else {
            gioHangAdapter.notifyDataSetChanged();
            tv_thongbao.setVisibility(View.INVISIBLE);
            lvgiohang.setVisibility(View.VISIBLE);
        }
    }


    private void ActionToolbar() {
        setSupportActionBar(toolbargiohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Anhxa() {
        lvgiohang = findViewById(R.id.lv_giohang);
        tv_thongbao = findViewById(R.id.tv_thongbao);
        tv_tongtien = findViewById(R.id.tv_tongtien);
        btnThanhtoan = findViewById(R.id.btn_thanhtoangiohang);
        btnTieptucmua = findViewById(R.id.btn_tieptucmuahang);
        toolbargiohang = findViewById(R.id.toolbar_giohang);
        gioHangAdapter = new GioHangAdapter(GHang.this, MainActivity.mangGioHang);
//        gioHangAdapter= new GioHangAdapter(GHang.this);
        lvgiohang.setAdapter(gioHangAdapter);
    }

}