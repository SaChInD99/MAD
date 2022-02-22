package com.example.project;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IT20178154 {

    private Bill bill;

    @Before
    public void setup(){
        bill = new Bill();
    }

    @Test
    public void totRoomCharge(){
        //correct test case
        double roomCharge = bill.calcPrice(5000,2);
        Assert.assertEquals(10000,roomCharge,1e-15);
    }
}
