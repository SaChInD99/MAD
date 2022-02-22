package com.example.project;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IT20120702 {

    private billguide bill;
    private Bill bill2;
    @Before
    public void setup(){
        bill = new billguide();
        bill2 = new Bill();
    }

    @Test
    public void testGuideBill(){
        double res = bill.calcPrice(1000,2);
        assertEquals(2000,res,1e-15);
        double res1 = bill.calcPrice(1000,-1);
        assertEquals(1000,res1,1e-15);
    }

    @Test
    public void testQty(){
        int res = bill2.qty(-1);
        assertEquals(-1,res);
        int res1 = bill2.qty(0);
        assertEquals(0,res1);
        int res2 = bill2.qty(-100);
        assertEquals(-1,res2);
    }
}
