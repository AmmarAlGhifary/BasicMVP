package com.blogspot.yourfavoritekaisar.basicmvp.main;

import com.blogspot.yourfavoritekaisar.basicmvp.model.network.ApiClient;
import com.blogspot.yourfavoritekaisar.basicmvp.model.network.ApiInterface;
import com.blogspot.yourfavoritekaisar.basicmvp.model.user.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainContract.Presenter {

    // Membuat variable dan konstruktor untuk menerima context dari MainActivty
    private final MainContract.View view;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void getDataListUser() {
        // Menampilkan progress dialog
        view.showProgress();

        // Membuat object call untuk mensetting endpoint dan parameter yang di butuhkan
        Call<UserResponse> call = apiInterface.getDataUser(12);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                view.hideProgress();

                if (response.body() != null){
                    UserResponse userResponse = response.body();
                    if (userResponse.getUserDataList() != null){
                        view.showDataListUser(userResponse.getUserDataList());
                    }
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                view.hideProgress();
                view.showFailureMessage(t.getMessage());

            }
        });


    }
}
