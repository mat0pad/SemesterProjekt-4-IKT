package com.sem4ikt.uni.recipefinderchatbot.model.spoonacular;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mathiaslykkepedersen on 11/03/2017.
 */

public class StepModelUnitTest {

    private StepModel stepModel = null;

    @Before
    public void setUp() {
        stepModel = new StepModel();
    }

    @Test
    public void setNumber(){

        stepModel.setNumber(1);

        Assert.assertEquals((int) stepModel.getNumber(), 1);
    }

    @Test
    public void setStep(){

        stepModel.setStep("test");

        Assert.assertEquals(stepModel.getStep(), "test");
    }

    @Test
    public void setEquipment(){

        List<SummaryModel> list = new ArrayList<>();

        SummaryModel sm = new SummaryModel();

        sm.setId(123);

        list.add(sm);

        stepModel.setEquipment(list);

        Assert.assertEquals( stepModel.getEquipment(), list);
    }

    @Test
    public void setEquioment(){

        List<SummaryModel> list = new ArrayList<>();

        SummaryModel sm = new SummaryModel();

        sm.setId(123);

        list.add(sm);

        stepModel.setIngredients(list);

        Assert.assertEquals( stepModel.getIngredients(), list);
    }
}
