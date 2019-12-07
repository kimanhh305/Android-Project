package com.example.DOAN.doan_android.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.DOAN.doan_android.R;
import com.example.DOAN.doan_android.activity.ChiTietActivity;
import com.example.DOAN.doan_android.model.Loaisp;
import com.squareup.picasso.Picasso;

import org.xml.sax.helpers.LocatorImpl;

import java.util.ArrayList;



public class LoaiSanPhamAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<Loaisp> arrLoaiSp=new ArrayList<>();

    public LoaiSanPhamAdapter(Context context, int layout, ArrayList<Loaisp> arrLoaiSp) {
        this.context = context;
        this.layout = layout;
        this.arrLoaiSp = arrLoaiSp;
    }

    @Override
    public int getCount() {
        return arrLoaiSp.size();
    }

    @Override
    public Object getItem(int i) {
        return arrLoaiSp.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        TextView textView;
        ImageView imageView;
    }
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if(view==null) {
            viewHolder= new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.dong_listview_loaisp, null);
            viewHolder.textView=view.findViewById(R.id.textviewloaisp);
            viewHolder.imageView=view.findViewById(R.id.imageviewloaisp);
            view.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) view.getTag();
        }
        Loaisp loaisp=(Loaisp) getItem(i);
        viewHolder.textView.setText(loaisp.getTenLoaiSanPham());
        Picasso.with(context).load(loaisp.getHinhAnhLoaiSanPham())
                .placeholder(R.drawable.dangtai)
                .error(R.drawable.loi)
                .into(viewHolder.imageView);
        return view;



//        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        view=layoutInflater.inflate(layout,null);
//        TextView tvTenloaisp=view.findViewById(R.id.textviewloaisp);
//        ImageView imgloaisp=view.findViewById(R.id.imageviewloaisp);
//        tvTenloaisp.setText(arrLoaiSp.get(i).getTenLoaiSanPham());
//        Picasso.with(context).load(arrLoaiSp.get(i).getHinhAnhLoaiSanPham())
//                .placeholder(R.drawable.dangtai)
//                .error(R.drawable.loi)
//                .into(imgloaisp);
////                .centerCrop()
////                .resize(150,150)
////                .into(imgloaisp);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("test1","t2");
//                Intent intent=new Intent(context, ChiTietActivity.class);
//                intent.putExtra("chitietsanpham",arrLoaiSp.get(i));
//                context.startActivity(intent);
//            }
//        });
//        return view;


    }

    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
