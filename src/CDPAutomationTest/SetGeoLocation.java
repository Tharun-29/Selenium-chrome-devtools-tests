package CDPAutomationTest;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

public class SetGeoLocation {

    public static void main(String[] args) throws InterruptedException {
        // Initialize ChromeDriver
        ChromeDriver driver = new ChromeDriver();

        // Initialize DevTools session
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Set geolocation coordinates
        Map<String, Object> coordinates = new HashMap<>();
        coordinates.put("latitude", 40.4168);
        coordinates.put("longitude", -3.7038);
        coordinates.put("accuracy", 1);
        driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);

        // Navigate to Google
        driver.get("https://www.google.com/");

        // Wait for page to load
        Thread.sleep(5000); // Adjust as needed

        // Perform search
        driver.findElement(By.name("q")).sendKeys("netflix", Keys.ENTER);

        // Click on the first search result
        driver.findElement(By.xpath("(//h3[@class='LC20lb MBeuO DKV0Md'])[1]")).click();

        // Get and print the title
        String title = driver.findElement(By.xpath("//h1")).getText();
        System.out.println("Title of the page: " + title);

        // Close the browser
        driver.quit();
    }

}
