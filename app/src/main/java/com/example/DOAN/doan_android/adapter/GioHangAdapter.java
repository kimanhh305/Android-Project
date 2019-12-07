package com.example.DOAN.doan_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.DOAN.doan_android.R;
import com.example.DOAN.doan_android.activity.GHang;
import com.example.DOAN.doan_android.activity.MainActivity;
import com.example.DOAN.doan_android.model.GioHang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;



public class GioHangAdapter extends BaseAdapter {
    Context context;
    ArrayList<GioHang> arryGioHang;

//    public GioHangAdapter(Context context) {
//        this.context = context;
//        this.arryGioHang = new ArrayList<>();
//    }
    public GioHangAdapter(Context context, ArrayList<GioHang> arryGioHang) {
        this.context = context;
        this.arryGioHang = arryGioHang;
    }

    @Override
    public int getCount() {
        return arryGioHang.size();
    }

    @Override
    public Object getItem(int i) {
        return arryGioHang.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        public TextView txt_tenGH,txt_Gia;
        public ImageView img_hinh;
        public Button btnminus,btnvalues,btnplus;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if(view==null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view =layoutInflater.inflate(R.layout.dong_giohang,null);
            viewHolder.txt_tenGH=view.findViewById(R.id.tv_tengiohang);
            viewHolder.txt_Gia=view.findViewById(R.id.tv_giagiohang);
            viewHolder.img_hinh=view.findViewById(R.id.img_giohang);
            viewHolder.btnminus=view.findViewById(R.id.btn_minus);
            viewHolder.btnvalues=view.findViewById(R.id.btn_values);
            viewHolder.btnplus=view.findViewById(R.id.btn_plus);
            view.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder) view.getTag();
        }
        GioHang gioHang= (GioHang) getItem(i);
        viewHolder.txt_tenGH.setText(gioHang.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txt_Gia.setText(decimalFormat.format(gioHang.getGiasp()) + " Đ");
        Picasso.with(context).load(gioHang.getHinhsp())
                .placeholder(R.drawable.dangtai)
                .error(R.drawable.loi)
                .into(viewHolder.img_hinh);
        viewHolder.btnvalues.setText(gioHang.getSoluongsp() + "");
        int sl=Integer.parseInt(viewHolder.btnvalues.getText().toString());
        if(sl>=10){
            viewHolder.btnplus.setVisibility(View.INVISIBLE);
            viewHolder.btnminus.setVisibility(View.VISIBLE);
        }else if(sl<=1){
            viewHolder.btnminus.setVisibility(View.INVISIBLE);
        }else if(sl>=1){
            viewHolder.btnminus.setVisibility(View.VISIBLE);
            viewHolder.btnplus.setVisibility(View.VISIBLE);
        }
        final ViewHolder finalViewHolder = viewHolder;
        //final ViewHolder finalViewHolder1 = viewHolder;
        //final ViewHolder finalViewHolder2 = viewHolder;
        //final ViewHolder finalViewHolder3 = viewHolder;
        viewHolder.btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoinhat = Integer.parseInt(finalViewHolder.btnvalues.getText().toString())+1;
                int slhientai = MainActivity.mangGioHang.get(i).getSoluongsp();
                long giahientai = MainActivity.mangGioHang.get(i).getGiasp();
                MainActivity.mangGioHang.get(i).setSoluongsp(slmoinhat);
                long giamoinhat=(giahientai*slmoinhat)/slhientai;
                MainActivity.mangGioHang.get(i).setGiasp(giamoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.txt_Gia.setText(decimalFormat.format(giamoinhat) + " Đ");
                GHang.DoDuLieu();
                if(slmoinhat>9)
                {
                    finalViewHolder.btnplus.setVisibility(View.INVISIBLE);
                    finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnvalues.setText(String.valueOf(slmoinhat));
                }
                else
                {
                    finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnplus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnvalues.setText(String.valueOf(slmoinhat));
                }
            }
        });
        viewHolder.btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoinhat = Integer.parseInt(finalViewHolder.btnvalues.getText().toString())-1;
                int slhientai = MainActivity.mangGioHang.get(i).getSoluongsp();
                long giahientai = MainActivity.mangGioHang.get(i).getGiasp();
                MainActivity.mangGioHang.get(i).setSoluongsp(slmoinhat);
                long giamoinhat=(giahientai*slmoinhat)/slhientai;
                MainActivity.mangGioHang.get(i).setGiasp(giamoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.txt_Gia.setText(decimalFormat.format(giamoinhat) + " Đ");
                GHang.DoDuLieu();
                if(slmoinhat<2)
                {
                    finalViewHolder.btnminus.setVisibility(View.INVISIBLE);
                    finalViewHolder.btnplus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnvalues.setText(String.valueOf(slmoinhat));
                }
                else
                {
                    finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnplus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnvalues.setText(String.valueOf(slmoinhat));
                }
            }
        });
        return view;

    }

    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
