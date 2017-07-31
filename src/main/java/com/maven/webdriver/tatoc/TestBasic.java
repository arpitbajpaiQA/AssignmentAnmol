package com.maven.webdriver.tatoc;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.interactions.Actions;

public class TestBasic {
	 static WebElement element;
	 static WebDriver driver;
	public static void main(String[] args) throws InterruptedException {
    	System.setProperty("webdriver.chrome.driver","C:\\Users\\arpitbajpai\\workspace\\tatoc\\target\\chromedriver.exe");
    	System.setProperty("webdriver.gecko.driver","C:\\Users\\arpitbajpai\\workspace\\tatoc\\target\\geckodriver.exe");
        driver = new ChromeDriver();
        String url = "http://10.0.1.86/tatoc/basic/grid/gate";
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        driver.findElement(By.cssSelector("div.greenbox")).click();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
        //element = driver.findElement(By.cssSelector("div.thisclass"));
        driver.switchTo().frame("main");
        String box1 = driver.findElement(By.cssSelector("#answer")).getAttribute("class");
        driver.switchTo().frame("child");
        String box2 = driver.findElement(By.cssSelector("#answer")).getAttribute("class");
        driver.switchTo().defaultContent();
        while(!box1.equals(box2)){	
        	driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
        	driver.switchTo().frame("main");
        	driver.findElement(By.cssSelector("body > center > a:nth-child(7)")).click();	
        	driver.switchTo().frame("child");
            box2 = driver.findElement(By.cssSelector("#answer")).getAttribute("class");
        	driver.switchTo().defaultContent();
        }
        	driver.switchTo().defaultContent();
        	driver.switchTo().frame("main");
        	driver.findElement(By.cssSelector("body > center > a:nth-child(9)")).click();   	
        
	WebElement From = driver.findElement(By.cssSelector("#dragbox"));
	WebElement To = driver.findElement(By.cssSelector("#dropbox"));
	//Actions builder = new Actions(driver);
	 
// dragAndDrop = builder.clickAndHold(From).moveToElement(To).release(To.build());
	 
	(new Actions(driver)).dragAndDrop(From, To).perform();
    driver.findElement(By.cssSelector("body > div > div.page > a")).click();
    driver.findElement(By.cssSelector("body > div > div.page > a:nth-child(4)")).click();
    //driver.findElement(By.cssSelector("#name")).sendKeys("Arpit Bajpai");
    //driver.switchTo().alert().sendKeys("Text");
    //driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
    //driver.findElement(By.cssSelector("#submit")).click();
    //driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
    			
	
    String MainWindow=driver.getWindowHandle();		
    		
    // To handle all new opened window.				
        Set<String> s1=driver.getWindowHandles();		
    Iterator<String> i1=s1.iterator();		
    		
    while(i1.hasNext())			
    {		
        String ChildWindow=i1.next();		
        		
        if(!MainWindow.equalsIgnoreCase(ChildWindow))			
        {    		
             
                // Switching to Child window
                driver.switchTo().window(ChildWindow);		
                                                                                     
                                                                                         
                driver.findElement(By.cssSelector("#name")).sendKeys("Arpit Bajpai");			
                                       
                driver.findElement(By.cssSelector("#submit")).click();	
                             
		// Closing the Child Window.
                  		
        }		
}
    driver.switchTo().window(MainWindow);
    driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
    driver.findElement(By.cssSelector("body > div > div.page > a:nth-child(6)")).click();
    driver.findElement(By.cssSelector("body > div > div.page > a:nth-child(8)")).click();
    String token = driver.findElement(By.cssSelector("span[id='token']")).getText();
    token=token.split(":")[1];
   // System.out.println(token);
    Cookie ck = new Cookie("Token",token.trim());
    driver.manage().addCookie(ck);
    driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
    driver.findElement(By.cssSelector("a[onclick='gonext();']")).click();
}
}


