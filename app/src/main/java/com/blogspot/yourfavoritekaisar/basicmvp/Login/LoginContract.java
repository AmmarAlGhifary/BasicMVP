package com.blogspot.yourfavoritekaisar.basicmvp.Login;

public interface LoginContract {
    // Membuat interface method untuk yang dibutuhkan pada view / interaksi dengan user
    interface View{
        // Menampilkan dan menutup progress loading dialog
        void showProgress();
        void hideProgress();

        // Menampilkan dan melakukan sesuatu pada saat server merespon berhasil ataupun gagal
        void loginFailure(String msg);
        void loginSuccess(String token);

    }

    // Membuat Interface untuk method yang ditubuhkan pada Presenter / Mediator dengan model(Bisnis Logic)
    interface Presenter{

        // Method untuk mengeksekusi bisnis logic untuk login contoh pengecekan data dan pengiriman
        // data ke internet
        void doLogin(String email, String password);


    }
}
