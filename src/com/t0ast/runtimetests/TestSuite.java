/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.t0ast.runtimetests;

import com.t0ast.utils.printing.Printer;
import com.t0ast.utils.eventsystem.Event;
import com.t0ast.utils.eventsystem.EventDistributor;
import com.t0ast.runtimetests.Tester.Test;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author T0astBread
 */
public class TestSuite
{
    private List<TestSuiteTest> tests;
    private Tester tester;
    private List<Tester.TestResult> resultsCache;
    private Printer resultsPrinter;
    private EventDistributor<ActionEvents> eventDistributor;

    public TestSuite(TestSuiteTest... tests)
    {
        this.tests = new LinkedList<>();
        this.tests.addAll(Arrays.asList(tests));
//        Arrays.stream(tests).forEach(this.tests::add);
        this.tester = new Tester();
        this.resultsCache = new LinkedList<>();
        this.eventDistributor = new EventDistributor<>();
    }
    
    public void addTest(TestSuiteTest test)
    {
        this.tests.add(test);
    }

    public List<TestSuiteTest> getTests()
    {
        return tests;
    }

    public Tester getTester()
    {
        return tester;
    }

    public EventDistributor<ActionEvents> getEventDistributor()
    {
        return eventDistributor;
    }
    
    public List<Tester.TestResult> run(Object... variables)
    {
        this.tests.forEach(t -> testFinished(this.tester.detailedTest(t, t.runs, variables)));
        return this.resultsCache;
    }
    
    private void testFinished(Tester.TestResult result)
    {
        this.resultsCache.add(result);
        if(this.resultsPrinter != null) this.resultsPrinter.println(result);
        this.eventDistributor.fire(new Event<>(this, ActionEvents.TEST_FINISHED, result));
    }
    
    public List<Tester.TestResult> runAllConfigs(Object[][] variables)
    {
        Arrays.stream(variables).forEach(this::run);
        return this.resultsCache;
    }
    
    public void runAll(List<TestSuiteTest> tests, Object... variables)
    {
        this.tests.clear();
        this.tests.addAll(tests);
        run(variables);
    }
    
    public void runAllTestsWithEveryConfig(List<TestSuiteTest> tests, Object[][] variables)
    {
        Arrays.stream(variables).forEach(vars -> runAll(tests, vars));
    }

    public Printer getResultsPrinter()
    {
        return resultsPrinter;
    }

    public void setResultsPrinter(Printer resultsPrinter)
    {
        this.resultsPrinter = resultsPrinter;
    }

    public List<Tester.TestResult> resultsCache()
    {
        return this.resultsCache;
    }
    
    public static class TestSuiteTest extends Test
    {
        public final int runs;

        public TestSuiteTest(String name, Consumer<Object[]> test, int runs)
        {
            super(name, test);
            this.runs = runs;
        }
    }
    
    public static enum ActionEvents
    {
        TEST_FINISHED
    }
}