package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by anton on 11-03-2017.
 */

public class InstructionsModelTest {
    private InstructionsModel instructionsModel = null;

    @Before
    public void setUp(){instructionsModel = new InstructionsModel();}

    @Test
    public void setImage()
    {
        instructionsModel.setName("testname");
        Assert.assertEquals(instructionsModel.getName(),"testname");
    }

    @Test
    public void setSteps()
    {
        List<StepModel> list = new ArrayList<>();
        list.add(new StepModel());
        list.add(new StepModel());
        instructionsModel.setSteps(list);
        Assert.assertEquals(instructionsModel.getSteps(),list);
    }
}
