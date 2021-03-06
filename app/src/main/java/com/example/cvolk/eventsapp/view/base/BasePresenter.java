package com.example.cvolk.eventsapp.view.base;

public interface BasePresenter<V extends BaseView> {

    void attachView(V view);

    void detachView();

}
