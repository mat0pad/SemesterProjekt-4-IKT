package com.sem4ikt.uni.recipefinderchatbot.presenter;

/**
 * Created by mathiaslykkepedersen on 02/03/2017.
 */

abstract class BasePresenter<V> implements IBasePresenter<V>{

    protected V view = null;

    BasePresenter(V view){
        this.view = view;
    }

    public void setView(V view){
        if(this.view == null)
            this.view = view;
    }

    public void clearView(){
        this.view = null;
    }

    public V getView() { return view; }


}
