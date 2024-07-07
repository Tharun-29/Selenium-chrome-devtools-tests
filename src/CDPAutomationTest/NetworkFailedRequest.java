package CDPAutomationTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v126.fetch.Fetch;
import org.openqa.selenium.devtools.v126.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v126.network.model.ErrorReason;

public class NetworkFailedRequest {

    public static void main(String[] args) throws InterruptedException {
        // Set up ChromeDriver
        ChromeDriver driver = new ChromeDriver();

        // Create DevTools session
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        
        // Define request patterns to intercept
        Optional<List<RequestPattern>> patterns = Optional.of(Arrays.asList(
            new RequestPattern(
                Optional.of("*GetBook*"), // URL pattern to match
                Optional.empty(), // Resource type (optional)
                Optional.empty() // Interception stage (optional)
            )
        ));
        
        // Enable Fetch domain with the specified request patterns
        devTools.send(Fetch.enable(patterns, Optional.empty()));
        
        // Add listener for Fetch.requestPaused events
        devTools.addListener(Fetch.requestPaused(), request -> {
            // Fail the intercepted request
            devTools.send(Fetch.failRequest(request.getRequestId(), ErrorReason.FAILED));
        });
        
        // Navigate to the URL
        driver.get("https://rahulshettyacademy.com/angularAppdemo");
        
        // Find and click the button with the specified XPath
        driver.findElement(By.xpath("//button[@routerlink='/library']")).click();
        
        // Wait for 5 seconds to observe the result
        Thread.sleep(5000);
        
        // Close the browser
        driver.quit();
    }
}
