package com.example.project;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IT20119812 {

    private expenseActivity expense;

    @Before
    public void setup(){
        expense = new expenseActivity();
    }

    @Test
    public void totalExpense(){

        //correct test case
        double expen = expense.calctotProfit(4000,1000);
        Assert.assertEquals(3000,expen,1e-15);

    }

    @Test
    public void totalExpense1(){

        //correct test case
        double expen = expense.calctotProfit(4000,1000);
        Assert.assertEquals(2000,expen,1e-15);

    }
}
