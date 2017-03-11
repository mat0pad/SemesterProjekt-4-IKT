package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by anton on 11-03-2017.
 */

public class AnswerModelUnitTest {

    private AnswerModel AnswerModel = null;

    @Before
    public void setUp() { AnswerModel = new AnswerModel();}

    @Test
    public void setAnswer()
    {
        String answer = "Coke isn't healthy";
        AnswerModel.setAnswer(answer);
        Assert.assertEquals(AnswerModel.getAnswer(),answer);
    }
    @Test
    public void setImage()
    {
        String imageUrl = "http://randomPic.jpg";
        AnswerModel.setImage(imageUrl);
        Assert.assertEquals(AnswerModel.getImage(), imageUrl);
    }
    @Test
    public void setType()
    {
        String type = "Test";
        AnswerModel.setType(type);
        Assert.assertEquals(AnswerModel.getType(),type);
    }

}


