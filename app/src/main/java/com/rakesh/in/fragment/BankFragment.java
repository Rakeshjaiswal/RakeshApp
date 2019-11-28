package com.rakesh.in.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.rakesh.in.R;
import com.rakesh.in.adapter.ProductsAdapter;
import com.rakesh.in.model.Product;
import com.rakesh.in.network.ApiServicesProduct;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BankFragment extends Fragment {


    private ProductsAdapter adapter;

    @BindView(R.id.mGridView)
    GridView mGridView;
    List<Product> productList = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bank, container, false);

        ButterKnife.bind(this, view);
        getProductData();


        return view;


    }

    public void getProductData() {


        Call<List<Product>> call = ApiServicesProduct.getInstance().getProductList();
        call.enqueue(new Callback<List<Product>>() {

            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                productList = response.body();
                adapter = new ProductsAdapter(getActivity(), productList);
                mGridView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable throwable) {
                Toast.makeText(getActivity(), throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
