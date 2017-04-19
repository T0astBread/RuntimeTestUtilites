/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.t0ast.testutilities;

import com.t0ast.runtimetests.TestSuite;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 *
 * @author T0astBread
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSuiteTest
{
    static TestSuite suite;
    private static boolean testWasRun;
    
    @BeforeClass
    public static void setupClass()
    {
        suite = new TestSuite();
    }

    /**
     * Test of addTest method, of class TestSuite.
     */
    @Test
    public void A_testAddTest()
    {
        suite.addTest(new TestSuite.TestSuiteTest("SampleTest", vars -> succeed(), 10));
        Assert.assertEquals(1, suite.getTests().size());
    }
    
    private void succeed()
    {
        testWasRun = true;
    }

    /**
     * Test of run method, of class TestSuite.
     */
    @Test
    public void B_testRun()
    {
        suite.run();
        Assert.assertEquals(true, testWasRun);
    }

    /**
     * Test of resultsCache method, of class TestSuite.
     */
    @Test
    public void C_testResultsCache()
    {
        Assert.assertEquals(1, suite.resultsCache().size());
    }
    
}
