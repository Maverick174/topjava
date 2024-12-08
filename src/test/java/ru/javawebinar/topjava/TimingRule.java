package ru.javawebinar.topjava;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.HashMap;
import java.util.Map;

public class TimingRule implements TestRule {
    private final Map<String, Long> testDurations = new HashMap<>();

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                long startTime = System.currentTimeMillis();
                try {
                    base.evaluate();
                } finally {
                    long duration = System.currentTimeMillis() - startTime;
                    testDurations.put(description.getMethodName(), duration);
                }
            }
        };
    }

    public void printSummary() {
        System.out.println("Test Execution Summary:");
        testDurations.forEach((name, duration) ->
                System.out.println(name + " - " + duration + " ms"));
    }
}
