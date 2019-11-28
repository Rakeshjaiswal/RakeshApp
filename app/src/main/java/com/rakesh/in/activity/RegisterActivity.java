package com.rakesh.in.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rakesh.in.R;
import com.rakesh.in.model.RegisterRequest;
import com.rakesh.in.model.User;
import com.rakesh.in.network.ApiService;
import com.wang.avi.AVLoadingIndicatorView;

import am.appwise.components.ni.NoInternetDialog;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.input_name)
    EditText inputName;

    @BindView(R.id.input_email)
    EditText inputEmail;

    @BindView(R.id.input_password)
    EditText inputPassword;

    @BindView(R.id.loader)
    AVLoadingIndicatorView loader;

    String name, email, password;
    NoInternetDialog noInternetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        noInternetDialog = new NoInternetDialog.Builder(RegisterActivity.this).build();

        ButterKnife.bind(this);

    }


    @OnClick(R.id.btn_register)
    void onRegisterClick() {
        name = inputName.getText().toString();
        email = inputEmail.getText().toString();
        password = inputPassword.getText().toString();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), getString(R.string.msg_fill_the_form), Toast.LENGTH_LONG).show();
            return;
        } else {
            getRegister();

        }

    }

    @OnClick(R.id.btn_login_account)
    void onCreateAccountClick() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


    public void getRegister() {

        loader.setVisibility(View.VISIBLE);
        RegisterRequest request = new RegisterRequest();
        request.name = name;
        request.email = email;
        request.password = password;
        request.confirmPassword = password;
        Call<User> userCall = ApiService.getInstance().register(request);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                loader.setVisibility(View.INVISIBLE);
                if (!response.isSuccessful()) {
                    System.out.println("Error:" + response.errorBody());
                    Toast.makeText(RegisterActivity.this, "Error:" + response.errorBody(), Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    launchLogin();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                loader.setVisibility(View.INVISIBLE);
            }
        });

    }

    public void launchLogin() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        noInternetDialog.onDestroy();
    }
}
