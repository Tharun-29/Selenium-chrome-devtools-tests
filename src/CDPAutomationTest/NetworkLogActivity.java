package CDPAutomationTest;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v126.network.Network;
import org.openqa.selenium.devtools.v126.network.model.Request;
import org.openqa.selenium.devtools.v126.network.model.Response;

public class NetworkLogActivity {

    public static void main(String[] args) {

        // Set up ChromeDriver
        ChromeDriver driver = new ChromeDriver();

        // Create DevTools session
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Enable network events
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Add listeners for network events
        // Request Sent
        devTools.addListener(Network.requestWillBeSent(), request -> {
            Request req = request.getRequest();
            System.out.println("Request URL: " + req.getUrl());
            System.out.println("Request Method: " + req.getMethod());
        });

        // Response Received
        devTools.addListener(Network.responseReceived(), response -> {
            Response res = response.getResponse();
            
            if(res.getStatus().toString().startsWith("4")) {
            	System.out.println(res.getUrl()+" is Failing with status code "+res.getStatus());
            }
            //System.out.println("Response URL: " + res.getUrl());
            System.out.println("Response Status: " + res.getStatus());
           // System.out.println("Response Status Text: " + res.getStatusText());
            System.out.println("Response Time: " + res.getResponseTime());
        });

        // Perform actions on the page
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Wait to ensure all requests and responses are captured
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Close the driver
        driver.quit();
    }
}
