package com.example.DOAN.doan_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.DOAN.doan_android.R;
import com.example.DOAN.doan_android.ultil.CheckConnection;
import com.example.DOAN.doan_android.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ThongTinActivity extends AppCompatActivity {
    EditText edttenkhachhang,edtemail,edtsdt;
    Button btnXacNhan,btnTroVe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khach_hang);
        anhXa();
        ClickButton();
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if(CheckConnection.haveNetworkConnection(getApplicationContext()))
        {
            EvenButton();
        }else
        {
            CheckConnection.showToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
        }
    }

    private void EvenButton() {
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ten=edttenkhachhang.getText().toString().trim();
                final String sdt=edtsdt.getText().toString().trim();
                final String email=edtemail.getText().toString().trim();
                if(ten.length()>0 && sdt.length() >0 && email.length()>0)
                {
                    RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest= new StringRequest(Request.Method.POST, Server.duongdandonhang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String madonhang) {
                            Log.d("madonhang",madonhang);
                            if(Integer.parseInt(madonhang)>0)
                            {
                                RequestQueue request=Volley.newRequestQueue(getApplicationContext());
                                StringRequest stringRe = new StringRequest(Request.Method.POST, Server.duongdanchitietdonhang, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if(response.equals("1"))
                                        {
                                            MainActivity.mangGioHang.clear();
                                            CheckConnection.showToast_Short(getApplicationContext(),"Bạn đã thêm dữ liệu giỏ hàng thành công!");
                                           // Toast.makeText(getApplicationContext(),"Bạn đã thêm dữ liệu thành công",Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                            startActivity(intent);
                                            CheckConnection.showToast_Short(getApplicationContext(),"Mời bạn tiếp tục mua hàng!");
                                            //Toast.makeText(getApplicationContext(),"Mời bạn tiếp tục mua hàng",Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {
                                            CheckConnection.showToast_Short(getApplicationContext(),"Bị lỗi dữ liệu!");
                                            //Toast.makeText(getApplicationContext(),"Bị lỗi dữ liệu",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray= new JSONArray();
                                        for(int i=0;i<MainActivity.mangGioHang.size();i++)
                                        {
                                            JSONObject jsonObject= new JSONObject();
                                            try {
                                                jsonObject.put("madonhang",madonhang);
                                                jsonObject.put("masanpham",MainActivity.mangGioHang.get(i).getIdsp());
                                                jsonObject.put("tensanpham",MainActivity.mangGioHang.get(i).getTensp());
                                                jsonObject.put("giasanpham",MainActivity.mangGioHang.get(i).getGiasp());
                                                jsonObject.put("soluongsanpham",MainActivity.mangGioHang.get(i).getSoluongsp());
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            jsonArray.put(jsonObject);
                                        }
                                        HashMap<String,String> hashMap= new HashMap<String, String>();
                                        hashMap.put("json",jsonArray.toString());
                                        return hashMap;
                                    }
                                };
                                request.add(stringRe);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap= new HashMap<String, String>();
                            hashMap.put("tenkhachhang",ten);
                            hashMap.put("sodienthoai",sdt);
                            hashMap.put("email",email);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }
                else
                {
                    CheckConnection.showToast_Short(getApplicationContext(),"Kiểm tra lại dữ liệu!");
                    //Toast.makeText(getApplicationContext(),"Kiểm tra lại dữ liệu!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void ClickButton() {
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ten=edttenkhachhang.getText().toString().trim();
                final String sdt=edtsdt.getText().toString().trim();
                final String email=edtemail.getText().toString().trim();
                if(ten.length()>0 && sdt.length() >0 && email.length()>0)
                {
                    RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest= new StringRequest(Request.Method.POST, Server.duongdandonhang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String madonhang) {
                            Log.d("madonhang",madonhang);
                           if(Integer.parseInt(madonhang)>0)
                            {
                                RequestQueue queue=Volley.newRequestQueue(getApplicationContext());
                                StringRequest stringRe = new StringRequest(Request.Method.POST, Server.duongdanchitietdonhang, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if(response.equals("1"))
                                        {
                                            MainActivity.mangGioHang.clear();
                                            CheckConnection.showToast_Short(getApplicationContext(),"Bạn đã thêm dữ liệu giỏ hàng thành công!");
                                            //Toast.makeText(getApplicationContext(),"Bạn đã thêm dữ liệu thành công",Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                            startActivity(intent);
                                            CheckConnection.showToast_Short(getApplicationContext(),"Mời bạn tiếp tục mua hàng!");
                                            //Toast.makeText(getApplicationContext(),"Mời bạn tiếp tục mua hàng",Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {
                                            CheckConnection.showToast_Short(getApplicationContext(),"Bị lỗi dữ liệu!");
                                            //Toast.makeText(getApplicationContext(),"Bị lỗi dữ liệu",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray= new JSONArray();
                                        for(int i=0;i<MainActivity.mangGioHang.size();i++)
                                        {
                                            JSONObject jsonObject= new JSONObject();
                                            try {
                                                jsonObject.put("madonhang",madonhang);
                                                jsonObject.put("masanpham",MainActivity.mangGioHang.get(i).getIdsp());
                                                jsonObject.put("tensanpham",MainActivity.mangGioHang.get(i).getTensp());
                                                jsonObject.put("giasanpham",MainActivity.mangGioHang.get(i).getGiasp());
                                                jsonObject.put("soluongsanpham",MainActivity.mangGioHang.get(i).getSoluongsp());
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            jsonArray.put(jsonObject);
                                        }
                                        HashMap<String,String> hashMap= new HashMap<String, String>();
                                        hashMap.put("json",jsonArray.toString());
                                        return hashMap;
                                    }
                                };
                                queue.add(stringRe);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap= new HashMap<String, String>();
                            hashMap.put("tenkhachhang",ten);
                            hashMap.put("sodienthoai",sdt);
                            hashMap.put("email",email);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }
                else
                {
                    CheckConnection.showToast_Short(getApplicationContext(),"Kiểm tra lại dữ liệu!");
                }
            }
        });
    }

    private void anhXa() {
        edttenkhachhang= findViewById(R.id.edt_tenkhachhang);
        edtemail=  findViewById(R.id.edt_email);
        edtsdt=  findViewById(R.id.edt_sdt);
        btnTroVe=  findViewById(R.id.btn_trove);
        btnXacNhan= findViewById(R.id.btn_xacnhan);
    }
}
