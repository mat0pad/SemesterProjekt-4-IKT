package com.sem4ikt.uni.recipefinderchatbot.presenter;

import com.sem4ikt.uni.recipefinderchatbot.presenter.interfaces.IFavoritesPresenter;
import com.sem4ikt.uni.recipefinderchatbot.view.IFavoritesView;

/**
 * Created by mathiaslykkepedersen on 27/03/2017.
 */

public class FavoritesPresenter extends BasePresenter<IFavoritesView> implements IFavoritesPresenter<IFavoritesView> {

    FavoritesPresenter(IFavoritesView view) {
        super(view);
    }
}
