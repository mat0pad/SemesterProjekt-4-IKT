package com.sem4ikt.uni.recipefinderchatbot.model;

import android.support.annotation.VisibleForTesting;
import android.util.Log;

import com.sem4ikt.uni.recipefinderchatbot.model.interfaces.ISearchModel;
import com.sem4ikt.uni.recipefinderchatbot.model.spoonacular.RecipeModel;

import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by anton on 06-04-2017.
 */

public class SearchModel implements ISearchModel {

    @Override
    public List<RecipeModel> searchRecipe(List<RecipeModel> list, String search) {

        List<RecipeModel> searchList = new ArrayList<>();
        String[] searchwords = search.split("\\s+");


        return search(list,searchwords);
    }



    private List<RecipeModel> search(List<RecipeModel> list, String[] searchwords) {
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
        return searchlist;
    }



}