package com.rakesh.in.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rakesh.in.R;
import com.rakesh.in.model.LoginRequest;
import com.rakesh.in.model.User;
import com.rakesh.in.network.ApiService;
import com.rakesh.in.pref.AppPref;
import com.wang.avi.AVLoadingIndicatorView;

import am.appwise.components.ni.NoInternetDialog;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.input_email)
    EditText inputEmail;

    @BindView(R.id.input_password)
    EditText inputPassword;

    @BindView(R.id.loader)
    AVLoadingIndicatorView loader;
    String email, password;

    NoInternetDialog noInternetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        noInternetDialog = new NoInternetDialog.Builder(LoginActivity.this).build();

        ButterKnife.bind(this);


    }

    @OnClick(R.id.btn_login)
    void onLoginClick() {
        email = inputEmail.getText().toString();
        password = inputPassword.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), getString(R.string.msg_enter_credentials), Toast.LENGTH_LONG).show();
            return;
        } else {
            userLogin();
        }
    }


    @OnClick(R.id.btn_create_account)
    void onCreateAccountClick() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


    public void userLogin() {
        loader.setVisibility(View.VISIBLE);
        LoginRequest request = new LoginRequest();
        request.email = email;
        request.password = password;
        Call<User> userCall = ApiService.getInstance().login(request);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                loader.setVisibility(View.INVISIBLE);
                if (!response.isSuccessful()) {
                    System.out.println("Error:" + response.errorBody());
                    Toast.makeText(LoginActivity.this, "Error:" + response.errorBody(), Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    AppPref.getInstance().saveAuthToken(response.body().token);
                    AppPref.getInstance().saveUserName(response.body().name);
                    Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();

                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                loader.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        noInternetDialog.onDestroy();
    }


}
