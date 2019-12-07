package com.example.DOAN.doan_android.model;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.DOAN.doan_android.R;
import com.example.DOAN.doan_android.activity.CTSPActivity;
import com.example.DOAN.doan_android.activity.ChiTietActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;



public class
SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.itemHolder>{
    Context context;
    ArrayList<SanPham> arrayListSanpham;
    String link = "http://192.168.0.110:81/ShopThoiTrang/image/";

    public SanPhamAdapter(Context context, ArrayList<SanPham> arrayListSanpham) {
        this.context = context;
        this.arrayListSanpham = arrayListSanpham;
    }

    @Override
    public itemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_sanphammoinhat,null);
        itemHolder itemHolder=new itemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(itemHolder holder, int position) {
        SanPham sanPham= arrayListSanpham.get(position);
        holder.tvTensanpham.setText(sanPham.getTensanpham());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        holder.tvGiasanpham.setText("Giá :"+decimalFormat.format(sanPham.getGiasanpham())+" Đ");
       Picasso.with(context).load(sanPham.getHinhanhsanpham())
                .placeholder(R.drawable.dangtai)
               .error(R.drawable.loi)
               .into(holder.imgHinhAnhSanpham);

       /* Picasso.with(context).load(link + sanPham.getHinhanhsanpham())
                .placeholder(R.drawable.dangtai)
                .error(R.drawable.loi)
                .into(holder.imgHinhAnhSanpham);*/
    }

    @Override
    public int getItemCount() {
        return arrayListSanpham.size();
    }



    public class itemHolder extends RecyclerView.ViewHolder{
        public ImageView imgHinhAnhSanpham;
        public TextView tvTensanpham;
        public TextView tvGiasanpham;

        public itemHolder(final View itemView) {
            super(itemView);
            imgHinhAnhSanpham=itemView.findViewById(R.id.img_sanpham);
            tvTensanpham=itemView.findViewById(R.id.tv_tensanpham);
            tvGiasanpham=itemView.findViewById(R.id.tv_giasanpham);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, CTSPActivity.class);

                    Bundle bd=new Bundle();
                    bd.putInt("id",arrayListSanpham.get(getPosition()).getId());
                    bd.putString("tenchitiet",arrayListSanpham.get(getPosition()).getTensanpham());
                    bd.putInt("giachitiet",arrayListSanpham.get(getPosition()).getGiasanpham());
                    bd.putString("motachitiet",arrayListSanpham.get(getPosition()).getMotasanpham());
                    bd.putString("hinhanhchitiet",arrayListSanpham.get(getPosition()).getHinhanhsanpham());
                    bd.putInt("idsanpham",arrayListSanpham.get(getPosition()).getIdsanpham());
                    intent.putExtra("chitietsanpham",bd);
                    context.startActivity(intent);

                }
            });
        }
    }
}

