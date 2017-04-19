/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.t0ast.runtimetests;

import com.t0ast.utils.printing.GsonArrayPrinter;
import java.util.List;

/**
 *
 * @author T0astBread
 */
public class JsonTestSuite extends TestSuite
{
    private GsonArrayPrinter printer;
    
    public JsonTestSuite(TestSuiteTest... tests)
    {
        super(tests);
        this.printer = new GsonArrayPrinter();
        setResultsPrinter(this.printer);
    }

    /**
     * ONLY use this method for testing if you wanna have a working JSON output
     * @param tests
     * @param variables 
     */
    @Override
    public void runAllTestsWithEveryConfig(List<TestSuiteTest> tests, Object[][] variables)
    {
        this.printer.startArray();
        super.runAllTestsWithEveryConfig(tests, variables);
        System.out.println(this.printer.endArray());
//        print();
    }
    
    @Deprecated
    @Override
    public void addTest(TestSuiteTest test)
    {
        super.addTest(test); //To change body of generated methods, choose Tools | Templates.
    }

    @Deprecated
    @Override
    public List<Tester.TestResult> run(Object... variables)
    {
        return super.run(variables); //To change body of generated methods, choose Tools | Templates.
    }

    @Deprecated
    @Override
    public void runAll(List<TestSuiteTest> tests, Object... variables)
    {
        super.runAll(tests, variables); //To change body of generated methods, choose Tools | Templates.
    }

    @Deprecated
    @Override
    public List<Tester.TestResult> runAllConfigs(Object[][] variables)
    {
        return super.runAllConfigs(variables); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void print()
    {
        System.out.println(this.printer.getBuffer().toString());
    }
}
