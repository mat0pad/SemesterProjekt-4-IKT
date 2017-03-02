package com.sem4ikt.uni.recipefinderchatbot.Presenter;

/**
 * Created by mathiaslykkepedersen on 02/03/2017.
 */

abstract class BasePresenter<V> {

    protected V view = null;

    BasePresenter(V view){
        this.view = view;
    }

    protected void setView(V view){
        if(this.view == null)
            this.view = view;
    }

    protected void clearView(){
        this.view = null;
    }


}
