import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by haroonnaderi on 6/16/17.
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features"
)
public class RunnerTest {
}
