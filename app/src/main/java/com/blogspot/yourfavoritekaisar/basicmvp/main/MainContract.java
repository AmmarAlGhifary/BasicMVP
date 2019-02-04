package com.blogspot.yourfavoritekaisar.basicmvp.main;

import com.blogspot.yourfavoritekaisar.basicmvp.model.user.UserData;

import java.util.List;

public interface MainContract {
    interface View{
        void showProgress();
        void hideProgress();
        // Menampilkan data list view
        void showDataListUser(List<UserData> userDataList);

        // Menampilkan pesan gagal
        void showFailureMessage(String msg);

    }

    interface Presenter{
        // Membuat method untuk mengambil data dari API
        void getDataListUser();

    }
}
