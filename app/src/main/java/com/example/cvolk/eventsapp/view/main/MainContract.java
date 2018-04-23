package com.example.cvolk.eventsapp.view.main;

import android.location.Location;

import com.example.cvolk.eventsapp.model.Pagination;
import com.example.cvolk.eventsapp.view.base.BasePresenter;
import com.example.cvolk.eventsapp.view.base.BaseView;

public interface MainContract {

    interface View extends BaseView {

        void onCheckPermission(boolean isEnabled);

        void onGetLocation(Location location);

        void onGetEvents(Pagination pagination);
    }

    interface Presenter extends BasePresenter<View> {

        void checkPermission();

        void getLocation();

        void getEvents();
    }
}
