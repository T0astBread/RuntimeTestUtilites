/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.t0ast.runtimetests;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 *
 * @author T0astBread
 */
public class Tester
{
    public TestResult detailedTest(Test test, int runs, Object... variables)
    {
        return new TestResult(test.name, variables, runTest(test, runs, variables), runs);
    }
    
    public long runTest(Test test, int runs, Object... variables)
    {
        long atStart = System.currentTimeMillis();
        for(int i = 0; i < runs; i++)
        {
            test.run(variables);
        }
        return Math.abs(System.currentTimeMillis() - atStart);
    }
    
    public static class Test
    {
        public final String name;
        public final Consumer<Object[]> test;

        public Test(String name, Consumer<Object[]> test)
        {
            this.name = name;
            this.test = test;
        }
        
        public void run(Object... variables)
        {
            this.test.accept(variables);
        }
    }
    
    public class TestResult
    {
        public final String testName;
        public final String[] variables;
        public final long totalTime;
        public final int runs;
        public final double averageTimePerRun;
        
        public TestResult(String testName, Object[] variables, long totalTime, int runs)
        {
            this(testName, Arrays.stream(variables).map(Object::getClass).map(Class::getName).toArray(String[]::new), totalTime, runs);
        }
        
        public TestResult(String testName, String[] variables, long totalTime, int runs)
        {
            this.testName = testName;
            this.variables = variables;
            this.totalTime = totalTime;
            this.runs = runs;
            this.averageTimePerRun = 1d*totalTime/runs;
        }

        @Override
        public String toString()
        {
            StringBuilder b = new StringBuilder(this.testName).append(":\n");
            for(int i = 0; i < this.variables.length; i++)
            {
                b.append(this.variables[i]);
                if(i != this.variables.length - 1) b.append(", ");
            }
            
            return b.append("Total time: ").append(this.totalTime).append("ms\n")
            .append("Runs: ").append(this.runs)
            .append("\nAverage time per run: ").append(this.averageTimePerRun).append("ms\n")
            .toString();
        }
    }
}
