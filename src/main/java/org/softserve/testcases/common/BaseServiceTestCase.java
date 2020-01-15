package org.softserve.testcases.common;


import java.lang.reflect.Method;
import org.assertj.core.api.SoftAssertions;
import org.softserve.utils.PropertiesLoader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class BaseServiceTestCase {

    protected SoftAssertions softAssert;
    private Method testMethod;
    private String testName;

    @BeforeSuite(alwaysRun = true)
    public void setupSuite() {
        loadProperties();
    }

    @BeforeMethod(alwaysRun = true)
    public void prepare() {
        softAssert = new SoftAssertions();
    }

    @BeforeMethod(alwaysRun = true)
    public void prepareBeforeMethod(Method testMethod) {
        this.testMethod = testMethod;
        this.testName = testMethod.getAnnotation(Test.class).testName();
    }

    public String getTestCaseName() {
        return this.testName;
    }

    protected void loadProperties() { PropertiesLoader.loadProjectProperties();}

}
