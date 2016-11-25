package config;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import executionEngine.DriverScript;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import utility.*;
import config.*;

public class ActionKeywords {
	
	public RemoteWebDriver driver;
	
	public DesiredCapabilities capabilities=new DesiredCapabilities();

	public String browserName = "mobileOS";
	public DesiredCapabilities capabilities_cloud = new DesiredCapabilities(browserName, "", Platform.ANY);

	public String adeviceid;
	public String aosversion;
	public String aappPack;
	public String aappAct;
	public String aplatformName;
	public String abundleID;
	public String afileLocdir;
	public String aTestStepID;
	public String aplatform;
	public String auser;
	public String apassword;
	public String aurl;
	public String adatasheetloc;
	public String ascreenshotsloc;
	public String aappType;
	public String aappurl;
	public String aappbrowser;
	public ArrayList contexts=new ArrayList();
	
	// public IWebDriver driver1;
		
	public void setMethod(String device,String platformName,String os_version,String bundleID,String appPack,String appAct,String platform,String user,String password,String url,String datasheetloc,String screenshotsloc,String appType,String appurl,String appbrowser) throws Exception
	{
		
		adeviceid=device;
        aplatformName=platformName;
		aosversion=os_version;
        abundleID=bundleID;
		aappPack=appPack;
		aappAct=appAct;
		aplatform=platform;
		auser=user;
		apassword=password;
		aurl=url;
		adatasheetloc=datasheetloc;
		ascreenshotsloc=screenshotsloc;
		aappType=appType;
		aappurl=appurl;
		aappbrowser=appbrowser;
	}
	
	public void setMethod_Screencapture(String fileLocdir,String sTestStepID) throws Exception
	{
		
		afileLocdir=fileLocdir;
		aTestStepID=sTestStepID;
		
	}
	
	
	public void setupCapabilities() throws MalformedURLException

	{
		
		System.out.println("Into setupCapabilities()::");
		System.out.println(adeviceid);
		System.out.println(aplatformName);
		System.out.println(aosversion);
		System.out.println(abundleID);
		System.out.println(aappPack);
		System.out.println(aappAct);
		
		if(aplatform.equalsIgnoreCase("PERFECTO") && aappType.equalsIgnoreCase("WEB"))
		{
		 capabilities_cloud.setCapability("deviceName", adeviceid);
		 capabilities_cloud.setCapability("platformName", aplatformName);
		 capabilities_cloud.setCapability("automationName","Appium");
		 capabilities_cloud.setCapability("platformVersion", aosversion);
		}
		
		else
		{
		 capabilities.setCapability("deviceName", adeviceid);
		 capabilities.setCapability("platformName", aplatformName);
		 capabilities.setCapability("automationName","Appium");
		 capabilities.setCapability("platformVersion", aosversion);
		}
		
		
		
		 
		//cap.setCapability(MobileCapabilityType.APP, "/Users/ibm/Library/Developer/Xcode/DerivedData/TestApp-fixmfzmpklbymjctcckekkvpbgec/Build/Products/Debug-iphoneos/TestApp.app");
		
	}

		
		public void Launch(String objectref, String inputparam,String objectType,boolean bResult){
			try{
				Log.info("Launch Mobile App");
				  
				
				System.out.println("...................Launch Mobile App Event Started........");
				
				
				if((aappType.equalsIgnoreCase("NATIVE") || aappType.equalsIgnoreCase("HYBRID") ) && aplatform.equalsIgnoreCase("PERFECTO") && aplatformName.equalsIgnoreCase("Andriod"))
				{
					
					  
					setupCapabilities();
					capabilities.setCapability("user",auser);
					capabilities.setCapability("password", apassword);
					capabilities.setCapability("appPackage",aappPack);
					capabilities.setCapability("appActivity",aappAct);
					    
					System.out.println("Into Device ID:"+adeviceid);
					driver = new AndroidDriver(new URL(aurl), capabilities);
					 driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
					 Thread.sleep(2000);
					 captureScreenShot(driver);
					 
					  Set contextNames = ((AppiumDriver) driver).getContextHandles();
				     	 Iterator iterator =  contextNames.iterator(); 
				     	   while (iterator.hasNext()){
				     		   String contextName=iterator.next().toString();
				     		  contexts.add(contextName);
				     	//System.out.println(contextName);
				     	   }
				}
				
				if ((aappType.equalsIgnoreCase("NATIVE") || aappType.equalsIgnoreCase("HYBRID") ) && aplatform.equalsIgnoreCase("PERFECTO") && aplatformName.equalsIgnoreCase("iOS"))
				{
					
					  
					setupCapabilities();
					capabilities.setCapability("user",auser);
					capabilities.setCapability("password", apassword);
					capabilities.setCapability("bundleId",abundleID);  
					System.out.println("Into Device ID:"+adeviceid);
					driver =new IOSDriver(new URL(aurl), capabilities);
					 driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
					 Thread.sleep(2000);
					 captureScreenShot(driver);
					 
					 Set contextNames = ((AppiumDriver) driver).getContextHandles();
			     	 Iterator iterator =  contextNames.iterator(); 
			     	   while (iterator.hasNext()){
			     		   String contextName=iterator.next().toString();
			     		  contexts.add(contextName);
			     	System.out.println(contextName);
			     	   }
				}
				
				if ((aappType.equalsIgnoreCase("NATIVE") || aappType.equalsIgnoreCase("HYBRID") ) && aplatform.equalsIgnoreCase("GRID") && aplatformName.equalsIgnoreCase("Andriod"))
				{
					
					  
					setupCapabilities();
					capabilities.setCapability("appPackage",aappPack);
					capabilities.setCapability("appActivity",aappAct);
					    
					System.out.println("Into Device ID:"+adeviceid);
					driver = new AndroidDriver(new URL(aurl), capabilities);
					//System.out.println("INTO SAYOM'S PHYSICAL ANDROID DEVICE::"+driver);
					driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
					 Thread.sleep(2000);
					 captureScreenShot(driver);
					 
					 Set contextNames = ((AppiumDriver) driver).getContextHandles();
			     	 Iterator iterator =  contextNames.iterator(); 
			     	   while (iterator.hasNext()){
			     		   String contextName=iterator.next().toString();
			     		  contexts.add(contextName);
			     	//System.out.println(contextName);
			     	   }
				}
				
				if((aappType.equalsIgnoreCase("NATIVE") || aappType.equalsIgnoreCase("HYBRID") ) && aplatform.equalsIgnoreCase("GRID") && aplatformName.equalsIgnoreCase("iOS"))
				{
					
					  
					setupCapabilities();
					capabilities.setCapability("bundleId",abundleID);
					    
					System.out.println("Into Device ID:"+adeviceid);
					driver = new IOSDriver<WebElement>(new URL(aurl), capabilities);
					driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
					 Thread.sleep(2000);
					 captureScreenShot(driver);
					 
					 Set contextNames = ((AppiumDriver) driver).getContextHandles();
			     	 Iterator iterator =  contextNames.iterator(); 
			     	   while (iterator.hasNext()){
			     		   String contextName=iterator.next().toString();
			     		  contexts.add(contextName);
			     	//System.out.println(contextName);
			     	   }
				}
				
				if(aappType.equalsIgnoreCase("WEB") && aplatform.equalsIgnoreCase("GRID") && aplatformName.equalsIgnoreCase("Andriod"))
				{
					
					  
					setupCapabilities();
					capabilities.setCapability("browserName",aappbrowser);
					
					System.out.println("Into Device ID:"+adeviceid);
					System.out.println("Into Device ID:"+aplatformName);
					System.out.println("Into Device ID:"+aurl);
					
					//      capabilities.setCapability("deviceName",adeviceid);
						//  capabilities.setCapability("platformName",aplatformName);	  
						 // capabilities.setCapability("automationName","Appium");
						
							 
					//System.out.println("Into Device ID:"+adeviceid);
					driver = new AndroidDriver(new URL(aurl), capabilities);
					driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
					 
					driver.get(aappurl);
					
					Thread.sleep(2000);
					 captureScreenShot(driver);
					 
					 Set contextNames = ((AppiumDriver) driver).getContextHandles();
			     	 Iterator iterator =  contextNames.iterator(); 
			     	   while (iterator.hasNext()){
			     		   String contextName=iterator.next().toString();
			     		  contexts.add(contextName);
			     	//System.out.println(contextName);
			     	   }
			     	   
			     	  
				}
				

				if(aappType.equalsIgnoreCase("WEB") && aplatform.equalsIgnoreCase("PERFECTO") && aplatformName.equalsIgnoreCase("Andriod"))
				{
					
					  
					setupCapabilities();
					capabilities_cloud.setCapability("user",auser);
					capabilities_cloud.setCapability("password", apassword);
					
					System.out.println("Into Device ID:"+adeviceid);
			
					driver = new AndroidDriver(new URL(aurl), capabilities_cloud);
					 driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
					 
					 driver.navigate().to(aappurl);
					 //((AppiumDriver) driver).rotate(ScreenOrientation.LANDSCAPE);
					 
					 Thread.sleep(2000);
					 captureScreenShot(driver);
					 
					  Set contextNames = ((AppiumDriver) driver).getContextHandles();
				     	 Iterator iterator =  contextNames.iterator(); 
				     	   while (iterator.hasNext()){
				     		   String contextName=iterator.next().toString();
				     		  contexts.add(contextName);
				     	//System.out.println(contextName);
				     	   }
			     	  
				}
				
				

				if(aappType.equalsIgnoreCase("WEB") && aplatform.equalsIgnoreCase("PERFECTO") && aplatformName.equalsIgnoreCase("iOS"))
				{
					
					  
					setupCapabilities();
					capabilities_cloud.setCapability("user",auser);
					capabilities_cloud.setCapability("password", apassword);
					
					System.out.println("Into Device ID:"+adeviceid);
			
					driver = new IOSDriver(new URL(aurl), capabilities_cloud);
					 driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
					
					  driver.executeScript("mobile:browser:open");
					 driver.navigate().to(aappurl);
					 //((AppiumDriver) driver).rotate(ScreenOrientation.LANDSCAPE);
					 
					 Thread.sleep(2000);
					 captureScreenShot(driver);
					 
					  Set contextNames = ((AppiumDriver) driver).getContextHandles();
				     	 Iterator iterator =  contextNames.iterator(); 
				     	   while (iterator.hasNext()){
				     		   String contextName=iterator.next().toString();
				     		  contexts.add(contextName);
				     	//System.out.println(contextName);
				     	   }
			     	  
				}
				
				/*
				
				//GIRISH's iOS SIMULATOR
				if(adeviceid.equalsIgnoreCase("f8d12622201004e1aa40f1e317c4e34e87530896"))
			{
				
				setupCapabilities();
				capabilities.setCapability("bundleId",abundleID);
				capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
				capabilities.setCapability("realDeviceLogger", "idevicesyslog"); 
				System.out.println("Into Device ID:"+adeviceid);
				driver = new IOSDriver<WebElement>(new URL("http://9.126.42.149:5556/wd/hub"),capabilities);
				System.out.println("Girish's Device ID:"+driver);
				driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
				 Thread.sleep(8000);
			}
			
				
			
			//TESTDROID(BLUEMIX) - ANDRIOD DEVICE
			if(adeviceid.equalsIgnoreCase("LGGooleNexus"))
			{
				System.out.println("Into Device ID:"+adeviceid);
				
				adeviceid="LG Google Nexus 5 6.0 -EU";
				 final String TARGET_APP_PATH = "Tesco.apk";
				 final String TESTDROID_SERVER = "http://appium.testdroid.com";
				 String testdroid_apikey ="dXsp565vvK79tqjJx5nGbOR6DeK3qmZK";
				 String fileUUID = uploadFile(TARGET_APP_PATH, TESTDROID_SERVER, testdroid_apikey);

			        capabilities.setCapability("platformName", "Android");
			        capabilities.setCapability("testdroid_target", "Android");
			        capabilities.setCapability("deviceName", "Android Device");
			        
			        capabilities.setCapability("testdroid_apiKey", testdroid_apikey);
			        
			        capabilities.setCapability("testdroid_project", "LocalAppium");
			        capabilities.setCapability("testdroid_testrun", "Android Scriptless Framework Run1: ");
			        
			        // See available devices at: https://cloud.testdroid.com/#public/devices
			        capabilities.setCapability("testdroid_device",adeviceid); // Freemium device
			        capabilities.setCapability("testdroid_app", fileUUID); //to use existing app using "latest" as fileUUID

			        // Optional
			        //capabilities.setCapability("testdroid_description", "Appium project description");
			        //capabilities.setCapability("platformVersion", "4.4.2");
			        //capabilities.setCapability("appPackage", "com.tesco.grocery.view");
			        //capabilities.setCapability("appActivity", "md50c9efa6bac9366ed33bd663294b9f0f7.TabSplashScreenActivity");
			        
			        System.out.println("Capabilities:" + capabilities.toString());

			        System.out.println("Creating Appium session, this may take couple minutes..");
			        
			        driver = new AndroidDriver(new URL(TESTDROID_SERVER+"/wd/hub"), capabilities);
			       
				System.out.println("INTO TESTDRIOD(BLUEMIX) ANDROID DEVICE::"+driver);
				
				 driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
				 Thread.sleep(2000);
				 captureScreenShot(driver);
			}
			
			
						//driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);				  
				
    			    System.out.println("...............Launch Event Completed........");
    			    
				//Thread.sleep(2000);
				
				*/
				
			 }catch(Exception e){
	 			Log.error("Not able to click --- " + e.getMessage());
	 			bResult = false;
	         	}
			}
		
	
		//driver.rotate(ScreenOrientation.LANDSCAPE);
	
	public void Tap(String objectref, String inputparam,String objectType,boolean bResult)
	 {
		try{
			
			Thread.sleep(1000);
			System.out.println("Into Tap() :: Driver Is:"+driver);
			System.out.println("Into Tap() :: Object Reference Is:"+objectref);
			System.out.println("Into Tap() :: Input Parameter Is:"+inputparam);
			System.out.println("Into Tap() :: ObjectType Is:"+objectType);
			
			Log.info("Tapping on element "+ objectref);
			
			System.out.println("...................Tap Event Started........");
			
			
			 Iterator iterator =  contexts.iterator(); 
			  while (iterator.hasNext())
			  {
	     	 String contextName=iterator.next().toString();
	     	System.out.println(contextName);
	     	if (contextName.contains(objectType))
	     	  {
	     	((AppiumDriver) driver).context(contextName);
	     	  break;
	     	  }
	     	}
			
			  System.out.println("Current Context of Driver Is:"+((AppiumDriver) driver).getContext());
		
			
			  /*
			if(objectref.equals("//*[@id='locality']") || objectref.equals("//*[@id='findStores']"))
			{
				 ((AppiumDriver) driver).context("WEBVIEW_1");
			}
			*/
			
			
			  ((AppiumDriver)driver).findElement(By.xpath(objectref)).click();
			
		
			System.out.println("...............Tap Event Completed........");
			
			
			Thread.sleep(2000);
			
			captureScreenShot(driver);
			
		 }catch(Exception e){
 			Log.error("Not able to click --- " + e.getMessage());
 			bResult = false;
         	}
		}
	
	
	public void Enter(String objectref, String inputparam,String objectType,boolean bResult){
		try{
			
			Thread.sleep(1000);
			
			System.out.println("Into Enter() :: Driver Is:"+driver);
			System.out.println("Into Enter() :: Object Reference Is:"+objectref);
			System.out.println("Into Enter() :: Input Parameter Is:"+inputparam);
			System.out.println("Into Enter() :: ObjectType Is:"+objectType);
			
			Log.info("Entering the text in " + objectref);
			
	System.out.println("...................Enter Event Started........");
			
	//driver.findElement(By.xpath(OR.getProperty(objectref))).sendKeys(inputparam);

	/*
	if(objectref.equals("//*[@id='locality']") || objectref.equals("//*[@id='findStores']"))
	{
		 ((AppiumDriver) driver).context("WEBVIEW_1");
	}
	*/
	
	Iterator iterator =  contexts.iterator(); 
	  while (iterator.hasNext())
	  {
	 String contextName=iterator.next().toString();
	System.out.println(contextName);
	if (contextName.contains(objectType))
	  {
	((AppiumDriver) driver).context(contextName);
	  break;
	  }
	}

	  System.out.println("Current Context of Driver Is:"+((AppiumDriver) driver).getContext());
	  
	  if(aplatformName.equalsIgnoreCase("Andriod"))
	  {
	  ((AppiumDriver)driver).findElementByXPath(objectref);
	  ((AppiumDriver)driver).getKeyboard().sendKeys(inputparam);
	  }
	  else
	  {
	 driver.findElement(By.xpath(objectref)).sendKeys(inputparam);
	  }
	  
	//  ((AppiumDriver)driver).navigate().back();
	 
			System.out.println("...............Enter Event Completed........");
	
			Thread.sleep(1000);
			
			captureScreenShot(driver);
			Thread.sleep(2000);
			
			
		 }catch(Exception e){
			 e.printStackTrace();
			 Log.error("Not able to Enter UserName --- " + e.getMessage());
			 bResult = false;
		 	}
		}
	

	
	
	public void captureScreenShot(RemoteWebDriver driver) throws Exception{
		// Take screenshot and store as a file format             
		 File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);   
		 
			System.out.println("src::"+src);
			
		try {
		// now copy the  screenshot to desired location using copyFile method
			
			String destfileloc=afileLocdir+"\\";
			
			System.out.println("destfileloc::"+destfileloc);
			
		FileUtils.copyFile(src, new File(destfileloc+aTestStepID+".png"));
		} catch (IOException e)
		 
		{
		  System.out.println(e.getMessage());
		 }
		  }
	
	
	

	
	
	}