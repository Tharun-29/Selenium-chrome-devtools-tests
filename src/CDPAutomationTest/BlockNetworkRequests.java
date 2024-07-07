package CDPAutomationTest;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v126.network.Network;

import com.google.common.collect.ImmutableList;

public class BlockNetworkRequests {

	public static void main(String[] args) {
		// Initialize ChromeDriver
		ChromeDriver driver = new ChromeDriver();

		// Create DevTools session
		DevTools devTools = driver.getDevTools();
		devTools.createSession();

		// Enable the network domain
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		
		// Block network requests for URLs ending with .jpg and .css
		devTools.send(Network.setBlockedURLs(ImmutableList.of("*.jpg", "*.css")));

		// Open the webpage
		driver.get("https://rahulshettyacademy.com/angularAppdemo");

		// Navigate through the webpage
		// Click on "Browse Products" link
		driver.findElement(By.xpath("//a[contains(text(),'Browse Products')]")).click();
		
		// Click on "Selenium" link
		driver.findElement(By.xpath("//a[contains(text(),'Selenium')]")).click();
		
		// Click on "Add to Cart" button
		driver.findElement(By.xpath("//button[contains(@class,'add-to-cart btn')]")).click();
		
		// Print the message that confirms the product is already added to the cart
		System.out.println(driver.findElement(By.xpath("//p[text()='This Product is already added to Cart']")).getText());
	}

}