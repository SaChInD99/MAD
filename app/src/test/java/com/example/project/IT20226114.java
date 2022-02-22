package com.example.project;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IT20226114 {

    private SalaryManage sal;

    @Before
    public void setup(){
        sal = new SalaryManage();
    }

    @Test
    public void totalSalary1(){

        //correct test case
        double salcal = sal.calctotsalary(8000,5,150);
        Assert.assertEquals(9400,salcal,1e-15);

    }

    @Test
    public void totalSalary2(){
        //correct test case
        double salcal = sal.calctotsalary(8000,5,150);
        Assert.assertEquals(8400,salcal,1e-15);

        }

    }
