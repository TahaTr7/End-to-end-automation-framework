package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.DataProvider;

/**
 * This class is to run parallel tests.
 */
public abstract class AbstractTestNGCucumberParallelTests extends AbstractTestNGCucumberTests {

    /**
     * This is to get all the scenarios.
     */
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}
