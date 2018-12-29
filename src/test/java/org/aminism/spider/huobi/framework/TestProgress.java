package org.aminism.spider.huobi.framework;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

/**
 * Created by zlyang on 2017/6/26.
 */
public class TestProgress extends TestWatcher {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";


    @Override
    protected void starting(Description description) {
        System.out.println("\n" + ANSI_GREEN + "***************[Test Case starting] "
                + description.getTestClass().getSimpleName() + "." + description.getMethodName()
                + "()***************\n" + ANSI_RESET);
    }

    @Override
    protected void finished(Description description) {
        System.out.println("\n" + ANSI_GREEN + "***************[Test Case finished] "
                + description.getTestClass().getSimpleName() + "." + description.getMethodName()
                + "()***************\n" + ANSI_RESET);
    }

}
