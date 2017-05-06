import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author Nikita Fomichev fomichev.n@raidix.com
 *         Date: 06-May-17
 */
public class Test {

    private List<List<String>> resultTable = new ArrayList<>();
    private Map<String,Map<String,Long>> resultsTable = new HashMap<>();

    @DataProvider(name = "Drivers")
    public static Object[][] drivers() {
        return new Object[][]{
                {DriverProvider.FIREFOX},
                {DriverProvider.CHROME},
                {DriverProvider.CHROME_HEADLESS},
                {DriverProvider.PHATNOMJS},
                {DriverProvider.HTMLUNIT}
        };
    }

    // This test will run 4 times since we have 5 parameters defined
    @org.testng.annotations.Test(dataProvider = "Drivers")
    public void test(DriverProvider driverProvider) {
        WebDriver driver = driverProvider.getDriver();

        System.out.println(parseDriverName(driver.toString()));
        driver.quit();
    }


    private <T> void timeCounter(Supplier<T> toCalc) {
        long startTime = System.currentTimeMillis();
        toCalc.get();
        long endTime = startTime - System.currentTimeMillis();
    }

    private String parseDriverName(String driverToString){
        return driverToString.split(":")[0];
    }

}