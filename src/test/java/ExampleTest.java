import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "Features",plugin={"json:build/reports/cucumber/json/test-results.json"}
)
public class ExampleTest {

}