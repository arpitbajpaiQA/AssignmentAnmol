package com.maven.webdriver.tatoc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.Select;

public class TestAdvance {
	static WebElement element;
	 static WebDriver driver;
	 static Connection con;
	 static String index,name,passkey;
	public static void main(String[] args) throws  Exception {
   	System.setProperty("webdriver.chrome.driver","C:\\Users\\arpitbajpai\\workspace\\tatoc\\target\\chromedriver.exe");
   	System.setProperty("webdriver.gecko.driver","C:\\Users\\arpitbajpai\\workspace\\tatoc\\target\\geckodriver.exe");
       driver = new ChromeDriver();
       String url = "http://10.0.1.86/tatoc/advanced/hover/menu";
       driver.get(url);
       driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
       Thread.sleep(1000);
       WebElement element = driver.findElement(By.cssSelector(".menutitle"));

       Actions action = new Actions(driver);
       action.moveToElement(element).moveToElement(driver.findElement(By.cssSelector("body > div > div.page > div.menutop.m2 > span:nth-child(5)"))).click().build().perform();
      //action.moveToElement(element).build().perform();
      //element.findElement(By.cssSelector("body > div > div.page > div.menutop.m2 > span:nth-child(5)")).click();
      String str = driver.findElement(By.cssSelector("#symboldisplay")).getText();
      //Class.forName("com.mysql.jdbc.Driver");  
      con=DriverManager.getConnection("jdbc:mysql://10.0.1.86/tatoc","tatocuser","tatoc01");
      Statement stmt=con.createStatement();  
      ResultSet rs=stmt.executeQuery("Select * from identity;");
      while(rs.next()){
    	  if(str.toLowerCase().equals(rs.getString(2)))
    	  {
    		index = rs.getString(1);  		
    	  }
      }
      ResultSet rs2=stmt.executeQuery("Select * from credentials;");
      while(rs2.next()){
    	  if(index.equals(rs2.getString(1))){
    		  name = rs2.getString(2);
    		 passkey = rs2.getString(3); 
    		// System.out.println(name);
    		// System.out.println(passkey);
    	  }
      }
      driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
      driver.findElement(By.cssSelector("#name")).sendKeys(name);  
      driver.findElement(By.cssSelector("#passkey")).sendKeys(passkey);
  
      driver.findElement(By.cssSelector("#submit")).click();
     
}
}
