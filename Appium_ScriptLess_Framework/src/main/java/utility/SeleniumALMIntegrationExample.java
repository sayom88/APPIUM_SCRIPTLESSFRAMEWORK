package utility;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jacob.com.LibraryLoader;

import atu.alm.wrapper.ALMServiceWrapper;
import atu.alm.wrapper.ITestCase;
import atu.alm.wrapper.ITestCaseRun;
import atu.alm.wrapper.ITestSet;
import atu.alm.wrapper.enums.StatusAs;
import atu.alm.wrapper.exceptions.ALMServiceException;


public class SeleniumALMIntegrationExample {
	WebDriver driver;
	public String TC_STATUS_VALUE;
	public String TC_DESC_VALUE;
	

/*
	@BeforeClass
	public void init() {
	System.setProperty("webdriver.chrome.driver", "C://ECLIPSE_WORKSPACE//chromedriver.exe");
				//driver = new ChromeDriver();
				driver=new ChromeDriver();
		//driver = new FirefoxDriver();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	*/
	
	@Test
	public void login() throws InterruptedException, ALMServiceException {
	/*
		// Open Wordpress App
		
		driver.get("http://demo.opensourcecms.com/wordpress/wp-login.php");
		// Enter UserName
		Thread.sleep(3000);
		driver.findElement(By.id("user_login")).clear();
		driver.findElement(By.id("user_login")).sendKeys("admin");
		// Enter Password
		driver.findElement(By.id("user_pass")).clear();
		driver.findElement(By.id("user_pass")).sendKeys("demo123");
		// Click on Submit button
		driver.findElement(By.id("wp-submit")).submit();
		*/
		
		 System.setProperty("jacob.dll.path", "C:\\Users\\IBM_ADMIN\\Downloads\\ALM_ServiceWrapper_Example\\ALM_ServiceWrapper_Example\\jacob-1.17-M2-x86.dll");

		LibraryLoader.loadJacobLibrary();
		 
		updateResults("PASSED");
		System.out.println("Done Updating Results");
	}

	public void updateResults1(TreeMap m) throws ALMServiceException {
		
	
		Iterator entries = m.entrySet().iterator();
		while (entries.hasNext()) 
		  {
			  Entry thisEntry = (Entry) entries.next();
			  Object key = thisEntry.getKey();
			  Object value = thisEntry.getValue();
			 
			 System.out.println("Key:"+key +"::::"+"Value:"+value);
			  
			  if(key.equals("TC_STATUS"))
			  {
				  TC_STATUS_VALUE=value.toString();
			  }
			  
			  if(key.equals("TC_DESC"))
			  {
				  TC_DESC_VALUE=value.toString();
			  }
			  
			  
			}
		
		
		 System.setProperty("jacob.dll.path", "C:\\Users\\IBM_ADMIN\\Downloads\\ALM_ServiceWrapper_Example\\ALM_ServiceWrapper_Example\\jacob-1.17-M2-x86.dll");

			LibraryLoader.loadJacobLibrary();
			 
			
	ALMServiceWrapper wrapper = new ALMServiceWrapper("http://inmbzp4181.in.dst.ibm.com:8888/qcbin/");
	
	System.out.println("The Wrapper is:"+wrapper);
	
	boolean b=wrapper.connect("sayom", "sayom", "MOBILE_TESTING", "Appium_ALM_Integration");
	
	System.out.println("The Connection is:"+b);
	
	
		
	  if(TC_STATUS_VALUE.equals("PASSED"))
	   {
		ITestCase loginTest = wrapper.updateResult("Appium-Automation", "TestSet1", 2, TC_DESC_VALUE, StatusAs.PASSED);
		
		ITestCaseRun loginRun = wrapper.createNewRun(loginTest, "Run 1",StatusAs.PASSED);
		
		
		//Map<String, String> treeMap = new TreeMap<String, String>(m);
		
		
		 Set s = m.entrySet();
		    Iterator it = s.iterator();
		    while ( it.hasNext() ) 
		    
		    {
		       Map.Entry entry = (Map.Entry) it.next();
		       String key = (String) entry.getKey();
		       String value = (String) entry.getValue();
		
			  if(key.toString().contains("TS_DESC_"))
				  
			  {
				String[] test_step= value.toString().split("::");
	
				  if(test_step[1].equals("PASSED"))
				  {
		wrapper.addStep(loginRun, test_step[0], StatusAs.PASSED,test_step[0], test_step[0], test_step[1]);
				  }
				  else
				  {
		wrapper.addStep(loginRun, test_step[0], StatusAs.FAILED,test_step[0], test_step[0], test_step[1]);	  
				  }
				  wrapper.newAttachment("C:\\ECLIPSE_WORKSPACE\\GITHUBREPOSITORY\\Appium_ScriptLess_Framework\\src\\main\\java\\dataEngine\\DataEngine.xlsx", "My Attachment Description",loginRun);
			  }
		  }
		
	   }
	   else  if(TC_STATUS_VALUE.equals("FAILED"))  // IF TEST CASE STATUS IS FAILED
	   {
			
		   ITestCase loginTest = wrapper.updateResult("Appium-Automation", "TestSet1", 2, TC_DESC_VALUE, StatusAs.FAILED);
			
			ITestCaseRun loginRun = wrapper.createNewRun(loginTest, "Run 1",StatusAs.FAILED);
			
			//Map<String, String> treeMap = new TreeMap<String, String>(m);
			
			 	Set s = m.entrySet();
			    Iterator it = s.iterator();
			    while ( it.hasNext() ) 
			    
			    {
			       Map.Entry entry = (Map.Entry) it.next();
			       String key = (String) entry.getKey();
			       String value = (String) entry.getValue();
		
				  if(key.toString().contains("TS_DESC_"))
					  
				  {
					String[] test_step= value.toString().split("::");
		
					  if(test_step[1].equals("PASSED"))
					  {
			wrapper.addStep(loginRun, test_step[0], StatusAs.PASSED,test_step[0], test_step[0], test_step[1]);
					  }
					  else
					  {
			wrapper.addStep(loginRun, test_step[0], StatusAs.FAILED,test_step[0], test_step[0], test_step[1]);	  
					  }
	wrapper.newAttachment("C:\\ECLIPSE_WORKSPACE\\GITHUBREPOSITORY\\Appium_ScriptLess_Framework\\src\\main\\java\\dataEngine\\DataEngine.xlsx", "My Attachment Description",loginRun); 
				  }
			  }	
	   }
		   
	
	
		 
	}
	
	public void updateResults(String status) throws ALMServiceException {
		try
		{
			
			 System.setProperty("jacob.dll.path", "C:\\Users\\IBM_ADMIN\\Downloads\\ALM_ServiceWrapper_Example\\ALM_ServiceWrapper_Example\\jacob-1.17-M2-x86.dll");

				LibraryLoader.loadJacobLibrary();
				 
				
		ALMServiceWrapper wrapper = new ALMServiceWrapper("http://inmbzp4181.in.dst.ibm.com:8888/qcbin/");
		
		System.out.println("The Wrapper is:"+wrapper);
			
				//"http://inmbzp4181.in.dst.ibm.com:8888/qcbin/");
		//"http://inmbzp4181.in.dst.ibm.com:8888/qcbin/start_a.jsp");
		
		boolean b=wrapper.connect("sayom", "sayom", "MOBILE_TESTING", "Appium_ALM_Integration");
		
		System.out.println("The Connection is:"+b);
		
		//ITestSet t= wrapper.getTestSet("Appium-Automation","TestSet1", 2);
		
		//wrapper.getTestSetName();
		
		//ITestCase t1=(ITestCase)"To Test Login";
		
	   if(status.equals("PASSED"))
	   {
	//	ITestCase t=wrapper.updateResult("Appium-Automation","TestSet1", 2, "To Test Login", StatusAs.PASSED);
		//ITestSet testSet = wrapper.getTestSet("Appium-Automation", "TestSet1", 2);
	//	wrapper.newAttachment("C:\\ECLIPSE_WORKSPACE\\GITHUBREPOSITORY\\Appium_ScriptLess_Framework\\src\\main\\java\\dataEngine\\DataEngine.xlsx", "My Attachment Description",testSet);
		 // wrapper.newAttachment("C:\\ECLIPSE_WORKSPACE\\GITHUBREPOSITORY\\Appium_ScriptLess_Framework\\src\\main\\java\\dataEngine\\DataEngine.xlsx", "My Attachment Description",t);
		
		ITestCase loginTest = wrapper.updateResult("Appium-Automation", "TestSet1", 2,"To search Tesco Stores via Postcode from the Tesco Mobile App", StatusAs.PASSED);
		wrapper.newAttachment("C:\\ECLIPSE_WORKSPACE\\GITHUBREPOSITORY\\Appium_ScriptLess_Framework\\src\\main\\java\\dataEngine\\DataEngine.xlsx", "My Attachment Description",loginTest);
	   }
	   else
	   {
		   //ITestCase t=wrapper.updateResult("Appium-Automation","TestSet1", 2, "To Test Login", StatusAs.FAILED);
		   //ITestSet testSet = wrapper.getTestSet("Appium-Automation", "TestSet1", 2);
		   //wrapper.newAttachment("C:\\ECLIPSE_WORKSPACE\\GITHUBREPOSITORY\\Appium_ScriptLess_Framework\\src\\main\\java\\dataEngine\\DataEngine.xlsx", "My Attachment Description",testSet);
		   //wrapper.newAttachment("C:\\ECLIPSE_WORKSPACE\\GITHUBREPOSITORY\\Appium_ScriptLess_Framework\\src\\main\\java\\dataEngine\\DataEngine.xlsx", "My Attachment Description",t);
		   
		   ITestCase loginTest = wrapper.updateResult("Appium-Automation", "TestSet1", 2,"To search Tesco Stores via Postcode from the Tesco Mobile App", StatusAs.FAILED);
			wrapper.newAttachment("C:\\ECLIPSE_WORKSPACE\\GITHUBREPOSITORY\\Appium_ScriptLess_Framework\\src\\main\\java\\dataEngine\\DataEngine.xlsx", "My Attachment Description",loginTest);
	   }
		   
		ITestSet testSet = wrapper.getTestSet("Appium-Automation", "TestSet1", 2);
		
	
		
		System.out.println(wrapper.getTestSetName());
		
		System.out.println("The Test Set is:"+testSet);
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		/*
		
		ITestCaseRun loginRun = wrapper.createNewRun(t, "Run 1",StatusAs.PASSED);
		
		wrapper.addStep(loginRun, "Enter username", StatusAs.PASSED,
				"Enters username", "enter", "enter");
		wrapper.addStep(loginRun, "Enter password", StatusAs.PASSED,
				"Enters password", "enter", "enter");
		wrapper.addStep(loginRun, "Click Login", StatusAs.PASSED,
				"Enters username", "", "");
		
		*/
		
		//System.out.println("The Test Case is:"+t);
		
		
		//wrapper.close();
	}

	
	/*
	@AfterClass
	public void close() {
		driver.quit();
	}
	
	*/
	
	
	//wrapper.createNewRun(wrapper.updateResult(TestSetFolder, TestSet, Integer.parseInt(CycleID), TestCaseName,nResult),"Run "+GetCurrentDateTime(),nResult);
	
	//wrapper.addStep(wrapper.createNewRun(wrapper.updateResult(TestSetFolder, TestSet, Integer.parseInt(CycleID), TestCaseName,nResult),TestCaseName,nResult),"abc", nResult, "aa", "aa", "aa");
}
