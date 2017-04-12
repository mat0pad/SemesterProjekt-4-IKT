package com.sem4ikt.uni.recipefinderchatbot.model;

import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anton on 08-04-2017.
 */

public class SearchThread implements Runnable {

    private List<RecipeModel> list;
    private String[] searchwords;

    SearchThread(List<RecipeModel> list,String[] searchwords)
    {
        this.list = list;
        this.searchwords = searchwords;
    }

    public void search()
    {
        List<RecipeModel> searchlist = new ArrayList<>();
        boolean containsword;

        for (RecipeModel recipe : list) {
            String title = recipe.getTitle();
            containsword = true;
            for (String word : searchwords) {
                //created to check if user had typed the first letter in lowercase
                String uppWord = "";
                if (!word.isEmpty())
                    uppWord = word.substring(0, 1).toUpperCase() + word.substring(1);
                if (!title.contains(word) && !title.contains(uppWord)) {
                    containsword = false;
                    break;
                }
            }
            if (containsword)
                searchlist.add(recipe);
        }
        this.list = searchlist;
    }

    public List<RecipeModel> getList()
    {
        return list;
    }

    @Override
    public void run() {

    }
}
