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



public class ThoiTrangNamAdapter extends BaseAdapter{
    Context context;
    ArrayList<SanPham> arrayThoiTrangNam;
    String link1 = "http://192.168.0.110:81/ShopThoiTrang/TTNam/";

    public ThoiTrangNamAdapter(Context context, ArrayList<SanPham> arrayThoiTrangNam) {
        this.context = context;
        this.arrayThoiTrangNam = arrayThoiTrangNam;
    }

    @Override
    public int getCount() {
        return arrayThoiTrangNam.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayThoiTrangNam.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txt_ttnam,txt_giattnam,txt_motattnam;
        public ImageView imgTTNam;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if(view==null)
        {
            viewHolder= new ViewHolder();
            LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.dong_thoitrang_nam,null);
            viewHolder.txt_ttnam=view.findViewById(R.id.tv_ctthoitrangnam);
            viewHolder.txt_giattnam=view.findViewById(R.id.tv_ctgiathoitrangnam);
            viewHolder.txt_motattnam=view.findViewById(R.id.tv_ctmotathoitrangnam);
            viewHolder.imgTTNam=view.findViewById(R.id.img_ctthoitrangnam);
            view.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder) view.getTag();
        }
        SanPham sanPham = (SanPham) getItem(i);
        viewHolder.txt_ttnam.setText(sanPham.getTensanpham());
        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        viewHolder.txt_giattnam.setText("Giá: " + decimalFormat.format(sanPham.getGiasanpham())+"Đ");
        viewHolder.txt_motattnam.setMaxLines(2);
        viewHolder.txt_motattnam.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txt_motattnam.setText(sanPham.getMotasanpham());
        Picasso.with(context).load(sanPham.getHinhanhsanpham())
                .placeholder(R.drawable.dangtai)
                .error(R.drawable.loi)
                .into(viewHolder.imgTTNam);
        /*Picasso.with(context).load(link1 + sanPham.getHinhanhsanpham())
                .placeholder(R.drawable.dangtai)
                .error(R.drawable.loi)
                .into(viewHolder.imgTTNam);*/
        return view;
    }

    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}


