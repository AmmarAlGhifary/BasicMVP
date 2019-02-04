package com.blogspot.yourfavoritekaisar.basicmvp.model.Detail;

import android.os.Bundle;

import com.blogspot.yourfavoritekaisar.basicmvp.model.user.UserData;

public interface DetailContract {
    interface View{
        void showProgress();
        void hideProgress();
        void showDataSingleUser(UserData userData);
        void showFailureMessage(String msg);
    }

    interface Presenter{
        void getDataSingleUser(Bundle bundle);
    }
}
