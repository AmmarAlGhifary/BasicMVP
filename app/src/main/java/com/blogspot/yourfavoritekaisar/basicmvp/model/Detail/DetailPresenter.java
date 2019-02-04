package com.blogspot.yourfavoritekaisar.basicmvp.model.Detail;

import android.os.Bundle;

import com.blogspot.yourfavoritekaisar.basicmvp.model.network.ApiClient;
import com.blogspot.yourfavoritekaisar.basicmvp.model.network.ApiInterface;
import com.blogspot.yourfavoritekaisar.basicmvp.model.user.SingleUserResponse;
import com.blogspot.yourfavoritekaisar.basicmvp.model.utils.constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPresenter implements DetailContract.Presenter {
    private final DetailContract.View view;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    private int id;

    public DetailPresenter(DetailContract.View view) {
        this.view = view;
    }

    @Override
    public void getDataSingleUser(Bundle bundle) {
        // TODO 2 Mencek bundle apakah ada isinya
        if (bundle != null){
            id = bundle.getInt(constant.KEY_ID);

            // Mengambil data dengan id
            // Tampilkan progress dialog
            view.showProgress();

            Call<SingleUserResponse> call = apiInterface.getDataSingleUser(id);
            call.enqueue(new Callback<SingleUserResponse>() {
                @Override
                public void onResponse(Call<SingleUserResponse> call, Response<SingleUserResponse> response) {
                    // Menutup proggres dialog
                    view.hideProgress();

                    if (response.body() != null){
                        // Memasukkan response body ke dalam SingleUserResponse
                        SingleUserResponse singleUserResponse = response.body();
                        // mencek apakah singleUseRespone dara ada Isinya?
                        if (singleUserResponse.getData() != null){
                            // Mengirimkan data ke dalam single user ke view untuk di tampilkan
                            view.showDataSingleUser(singleUserResponse.getData());
                        }
                    }
                }

                @Override
                public void onFailure(Call<SingleUserResponse> call, Throwable t) {
                    view.hideProgress();
                    view.showFailureMessage(t.getMessage());

                }
            });
        }

    }
}
