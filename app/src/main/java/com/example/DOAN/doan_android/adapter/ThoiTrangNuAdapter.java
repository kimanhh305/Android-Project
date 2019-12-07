package com.example.DOAN.doan_android.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.DOAN.doan_android.R;
import com.example.DOAN.doan_android.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class ThoiTrangNuAdapter extends BaseAdapter{
    Context context;
    ArrayList<SanPham> arrayThoiTrangNu;
    String link2 = "http://192.168.0.110:81/ShopThoiTrang/TTNu/";

    public ThoiTrangNuAdapter(Context context, ArrayList<SanPham> arrayThoiTrangNu) {
        this.context = context;
        this.arrayThoiTrangNu = arrayThoiTrangNu;
    }

    @Override
    public int getCount() {
        return arrayThoiTrangNu.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayThoiTrangNu.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txt_ttn,txt_gia,txt_mota;
        public ImageView imgTTN;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
           ViewHolder viewHolder=null;
           if(view==null)
           {
               viewHolder= new ViewHolder();
               LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
               view=layoutInflater.inflate(R.layout.dong_thoitrang_nu,null);
               viewHolder.txt_ttn=view.findViewById(R.id.tv_ctthoitrangnu);
               viewHolder.txt_gia=view.findViewById(R.id.tv_ctgiathoitrangnu);
               viewHolder.txt_mota=view.findViewById(R.id.tv_ctmotathoitrangnu);
               viewHolder.imgTTN=view.findViewById(R.id.img_ctthoitrangnu);
               view.setTag(viewHolder);
           }
           else {
               viewHolder=(ViewHolder) view.getTag();
           }
           SanPham sanPham = (SanPham) getItem(i);
           viewHolder.txt_ttn.setText(sanPham.getTensanpham());
           DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
           viewHolder.txt_gia.setText("Giá: " + decimalFormat.format(sanPham.getGiasanpham())+"Đ");
           viewHolder.txt_mota.setMaxLines(2);
           viewHolder.txt_mota.setEllipsize(TextUtils.TruncateAt.END);
           viewHolder.txt_mota.setText(sanPham.getMotasanpham());
           Picasso.with(context).load(sanPham.getHinhanhsanpham())
                   .placeholder(R.drawable.dangtai)
                   .error(R.drawable.loi)
                   .into(viewHolder.imgTTN);
       /* Picasso.with(context).load(link2 + sanPham.getHinhanhsanpham())
                .placeholder(R.drawable.dangtai)
                .error(R.drawable.loi)
                .into(viewHolder.imgTTN);*/
           return view;
    }

    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
