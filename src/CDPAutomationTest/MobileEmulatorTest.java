package CDPAutomationTest;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

public class MobileEmulatorTest {

    public static void main(String[] args) throws InterruptedException {

        // Initialize a new instance of the ChromeDriver
        ChromeDriver driver = new ChromeDriver();

        // Get the DevTools from the ChromeDriver
        DevTools devTools = driver.getDevTools();

        // Create a new DevTools session
        devTools.createSession();

        // Send commands to CDP Methods -> CDP Methods will invoke and get access to Chrome DevTools
        // This command sets device metrics to emulate a mobile device
        devTools.send(org.openqa.selenium.devtools.v85.emulation.Emulation.setDeviceMetricsOverride(
            375, // Width of the emulated device screen
            667, // Height of the emulated device screen
            50,  // Device scale factor
            true, // Whether the device is a mobile device
            java.util.Optional.empty(), // Screen orientation (optional)
            java.util.Optional.empty(), // Mobile scale factor (optional)
            java.util.Optional.empty(), // Scale factor (optional)
            java.util.Optional.empty(), // Screen width (optional)
            java.util.Optional.empty(), // Screen height (optional)
            java.util.Optional.empty(), // Position X (optional)
            java.util.Optional.empty(), // Position Y (optional)
            java.util.Optional.empty()  // Page scale factor (optional)
        ));

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
