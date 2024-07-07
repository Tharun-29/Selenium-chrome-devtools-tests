package CDPAutomationTest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v126.network.Network;
import org.openqa.selenium.devtools.v126.network.model.ConnectionType;
import org.openqa.selenium.devtools.v126.network.model.LoadingFailed;

import java.util.Optional;

public class NetworkSpeed {

	public static void main(String[] args) {
		// Initialize ChromeDriver
		ChromeDriver driver = new ChromeDriver();

		// Create DevTools session
		DevTools devTools = driver.getDevTools();
		devTools.createSession();

		// Enable the network domain
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

		// Emulate network conditions
		// Parameters: offline (true/false), latency (ms), download throughput (bytes/s), upload throughput (bytes/s), connection type
		devTools.send(Network.emulateNetworkConditions(true, 3000, 20000, 100000, Optional.of(ConnectionType.WIFI), 
			java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty()));
		
		// Add listener for loading failed events
		devTools.addListener(Network.loadingFailed(), LoadingFailed -> {
			System.out.println(LoadingFailed.getErrorText());
			System.out.println(LoadingFailed.getTimestamp());
			System.out.println(LoadingFailed.getBlockedReason());
		});
		
		// Record start time
		long startTime = System.currentTimeMillis();
		
		// Navigate to Google
        driver.get("https://www.google.com/");

        // Perform search
        driver.findElement(By.name("q")).sendKeys("netflix", Keys.ENTER);

        // Click on the first search result
        driver.findElement(By.xpath("(//h3[@class='LC20lb MBeuO DKV0Md'])[1]")).click();

        // Get and print the title of the page
        String title = driver.findElement(By.xpath("//h1")).getText();
        System.out.println("Title of the page: " + title);

		// Record end time
		long endTime = System.currentTimeMillis();
		
		// Print the time taken to perform the actions
		System.out.println(endTime - startTime);
		
		// Close the browser
		driver.close();
	}

}
