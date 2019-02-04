package com.blogspot.yourfavoritekaisar.basicmvp.Login;

import android.telecom.Call;
import android.util.Log;

import com.blogspot.yourfavoritekaisar.basicmvp.model.login.LoginBody;
import com.blogspot.yourfavoritekaisar.basicmvp.model.login.LoginResponse;
import com.blogspot.yourfavoritekaisar.basicmvp.model.network.ApiClient;
import com.blogspot.yourfavoritekaisar.basicmvp.model.network.ApiInterface;

import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter {


    // TODO 1 Menyiapkan variable yang dibutuhkan
    // Membuat object apiInterface untuk mensettling baseURL retrofit
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        // Membuat object LoginContract View
    private final LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void doLogin(String email, String password) {
    // Mengecek email dan password apakah ada isinya
    if (email == null || email.isEmpty()){
        view.loginFailure("Email Kosong");
        return;

        }

        if (password == null || password.isEmpty()){
            view.loginFailure("Password Kosong");
        }

        // Menampilkan progress agar memberitahu ada proses yang sedang berjalan
        view.showProgress();

        // Memasukkan data email dan password ke dalam LoginBody
        LoginBody loginBody = new LoginBody();
        loginBody.setEmail(email);
        loginBody.setPassword(password);

        // Mengesekusi data ke server
        // Membuat object untuk mengirim loginbody
        retrofit2.Call<LoginResponse> call = apiInterface.postLogin(loginBody);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(retrofit2.Call<LoginResponse> call, Response<LoginResponse> response) {
                // View menutup progress dialog
                view.hideProgress();

                // Mencek response apakah ada isinya
                if (response.body() != null){
                    // Mengambul data response body dan memasukkan ke dalam class model LoginResponse
                    LoginResponse loginResponse = response.body();
                    // Mencek isi token apakah ada isinya? agar tidak forcelose apabila null
                    if (loginResponse.getToken() != null){
                        // Login succes mengirimkan token dan diminta view untuk berpindah halaman
                        view.loginSuccess(loginResponse.getToken());
                    }else {
                        view.loginFailure("User tidak terdaftar");
                    }
                }else {
                    view.loginFailure(response.message());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<LoginResponse> call, Throwable t) {
                // Menutup progress dialog
                view.hideProgress();
                // Menampilkan pesan
                view.loginFailure(t.getMessage());

            }
        });

    }
}