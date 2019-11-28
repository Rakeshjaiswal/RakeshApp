package com.rakesh.in.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.rakesh.in.R;
import com.rakesh.in.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ProductsAdapter extends BaseAdapter {

    private List<Product> spacecrafts;
    private Context context;

    public ProductsAdapter(Context context,List<Product> spacecrafts){
        this.context = context;
        this.spacecrafts = spacecrafts;
    }

    @Override
    public int getCount() {
        return spacecrafts.size();
    }

    @Override
    public Object getItem(int pos) {
        return spacecrafts.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view=LayoutInflater.from(context).inflate(R.layout.product_list_item,viewGroup,false);
        }

        TextView nameTxt = view.findViewById(R.id.name);
        TextView txtPropellant = view.findViewById(R.id.price);
        ImageView spacecraftImageView = view.findViewById(R.id.thumbnail);

        final Product thisSpacecraft= spacecrafts.get(position);

        nameTxt.setText(thisSpacecraft.getName());
        txtPropellant.setText(thisSpacecraft.getPropellant());

        if(thisSpacecraft.getImageURL() != null && thisSpacecraft.getImageURL().length()>0)
        {
            Picasso.get().load(thisSpacecraft.getImageURL()).placeholder(R.mipmap.ic_launcher).into(spacecraftImageView);
        }else {
            Toast.makeText(context, "Empty Image URL", Toast.LENGTH_LONG).show();
            Picasso.get().load(R.mipmap.ic_launcher).into(spacecraftImageView);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, thisSpacecraft.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}