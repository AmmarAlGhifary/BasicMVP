package com.blogspot.yourfavoritekaisar.basicmvp.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blogspot.yourfavoritekaisar.basicmvp.Login.LoginContract;
import com.blogspot.yourfavoritekaisar.basicmvp.Login.LoginPresenter;
import com.blogspot.yourfavoritekaisar.basicmvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.edtEmail)
    EditText edtEmail;

    private ProgressDialog progressDialog;
    // Membuat variable untuk dapat mengakses Login presenter
    private final LoginPresenter loginPresenter = new LoginPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnLogin)
    public void onViewClicked() {
        loginPresenter.doLogin(edtEmail.getText().toString(),edtPassword.getText().toString());
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();


    }

    @Override
    public void loginFailure(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void loginSuccess(String token) {
        Toast.makeText(this, token, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
        finish();

    }


}
