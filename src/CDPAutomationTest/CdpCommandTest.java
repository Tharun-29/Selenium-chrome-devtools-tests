package CDPAutomationTest;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

public class CdpCommandTest {

    public static void main(String[] args) throws InterruptedException {

        // Create a new instance of the ChromeDriver
        ChromeDriver driver = new ChromeDriver();

        // Get the DevTools from the ChromeDriver
        DevTools devTools = driver.getDevTools();

        // Create a new DevTools session
        devTools.createSession();

        // Define device metrics for mobile emulation
        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width", 375);              // Set the width of the emulated device
        deviceMetrics.put("height", 667);             // Set the height of the emulated device
        deviceMetrics.put("deviceScaleFactor", 50);   // Set the device scale factor
        deviceMetrics.put("mobile", true);            // Set to true to emulate a mobile device

        // Execute CDP command to override device metrics
        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);

        // Navigate to the Sauce Demo login page
        driver.get("https://www.saucedemo.com/");
        Thread.sleep(5000); // Wait for 5 seconds to ensure the page loads completely

        // Find and click the login button
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(5000); // Wait for 5 seconds to observe the click action

        // Close the browser
        driver.quit();
    }
}
