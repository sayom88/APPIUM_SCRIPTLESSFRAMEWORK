package executionEngine;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import config.*;
import utility.ExcelUtils;
import utility.*;

 
public class DriverScript {
	
	public  Properties OR;
	public  ActionKeywords actionKeywords;
	
	//public static String sActionKeyword;
	public  String sPageObject;
	public  String sTestCaseDesc;
	public  String sTestStepDesc;
	public  String sObjectRef;
	public  String sInputParams;
	public  String sKeyword;
	public  String sObjectType;
	
	public  Method method[];
	
	public  int iTestCase;
	public  int iTestStep;
	public  int iTestLastStep;
	
	public  String sTestCaseID;
	public  String sTestStepID;
	public  String sRunMode;
	public  String sData;
	public  boolean bResult;
	public  String sDevice_ID;
	public  String sOS_Version;
	public  String sappPackage;
	public  String sappActivity;
	public  String sDevice_ID_TestCase;
	public  String splatformName;
	public  String sbundleID;
	public  String fileLocdir;
	public String splatform;
	public String suser;
	public String spassword;
	public String surl;
	public String sdatasheetloc;
    public String sscreenshotsloc;
    public String sappType;
    public String sappurl;
    public String sappbrowser;
	//public String[] keywords=new String[]{"Launch","Tap","Swipe","Enter"};
	
	//public RemoteWebDriver driver;
	//AppiumDriver<WebElement> driver1;
	//DesiredCapabilities capabilities=new DesiredCapabilities();
	
	public DriverScript() throws NoSuchMethodException, SecurityException{
	actionKeywords = new ActionKeywords();
	method = actionKeywords.getClass().getMethods();
	
	}
	
	
	@BeforeTest
	@Parameters({"device_ID","platformName","os_Version","bundleID","appPackage","appActivity","platform","user","password","url","datasheetloc","screenshotsloc","appType","appurl","appbrowser"})
	public void setupDriver(String device_ID,String platformName,String os_Version,String bundleID,String appPackage,String appActivity,String platform,String user,String password,String url,String datasheetloc,String screenshotsloc,String appType,String appurl,String appbrowser) throws MalformedURLException,NoSuchMethodException, SecurityException
	{
		try {
			sDevice_ID=device_ID;
			splatformName=platformName;
			sOS_Version=os_Version;
			sbundleID=bundleID;
			sappPackage=appPackage;
			sappActivity=appActivity;
			splatform= platform;
			suser= user;
			spassword= password;
			surl= url;
			sdatasheetloc=datasheetloc;
			sscreenshotsloc=screenshotsloc;
			sappType=appType;
            sappurl=appurl;
            sappbrowser=appbrowser;
			actionKeywords.setMethod(sDevice_ID,splatformName,sOS_Version,sbundleID,sappPackage,sappActivity,splatform,suser,spassword,surl,sdatasheetloc,sscreenshotsloc,sappType,sappurl,sappbrowser);
			
			
			//actionKeywords = new ActionKeywords();
			//method = actionKeywords.getClass().getMethods();
			
			//main_method();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@Test	
    public void main_method() throws Exception {
		 long threadId = Thread.currentThread().getId();
    	//System.out.println(threadId+"::"+Constants.Path_TestData);
    	
    	ExcelUtils.setExcelFile(sdatasheetloc);
    	DOMConfigurator.configure("log4j.xml");
    	/*
    	String Path_OR = Constants.Path_OR;
		FileInputStream fs = new FileInputStream(Path_OR);
		OR= new Properties(System.getProperties());
		OR.load(fs);
		*/
    	
    	 TreeMap m = new TreeMap();
		  
	    	int iTotalTestCases = ExcelUtils.getRowCount(Constants.Sheet_TestCases);
			
	    	for(int iTestcase=1;iTestcase<iTotalTestCases;iTestcase++)
			
			{
				bResult = true;
				
				sTestCaseID = ExcelUtils.getCellData(iTestcase, Constants.Col_TestCaseID, Constants.Sheet_TestCases); 
				
				sDevice_ID_TestCase= ExcelUtils.getCellData(iTestcase, Constants.Col_DeviceID, Constants.Sheet_TestCases);
				
				System.out.println(threadId+"::"+sTestCaseID);
				sRunMode = ExcelUtils.getCellData(iTestcase, Constants.Col_RunMode,Constants.Sheet_TestCases);
				
				System.out.println(threadId+"::"+sRunMode);
				//sTestDesc = ExcelUtils.getCellData(iTestcase, Constants.Col_TestStepDesc, Constants.Sheet_TestCases);
				
				//sRunMode = ExcelUtils.getCellData(iTestcase, Constants.Col_RunMode,Constants.Sheet_TestCases);
				
				if ((sRunMode.equals("Yes") && (sDevice_ID_TestCase.equals(sDevice_ID))))
				{
					Log.startTestCase(sTestCaseID);
					
					
					   DateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH mm ss");
				       Date dateobj = new Date();
				       String date=df.format(dateobj).toString();
				       
				//	  fileLocdir=Constants.Test_Execution_Screenshot_Loc+df.format(dateobj)+"\\"+sTestCaseID;
				       fileLocdir=sscreenshotsloc+df.format(dateobj)+"\\"+sTestCaseID;
				    
					  new File(fileLocdir).mkdirs();
					
					
					iTestCase = ExcelUtils.getRowContains(sTestCaseID, Constants.Col_TestCaseID, Constants.Sheet_TestCases);
					
					//To Get The Test Case Description
					sTestCaseDesc = ExcelUtils.getCellData(iTestCase, Constants.Col_TestCaseDesc,Constants.Sheet_TestCases);
		    		System.out.println(threadId+"::"+"Test Case Description Is:"+sTestStepDesc);
		    		
		    	m.put("TC_DESC", sTestCaseDesc);
		    		
				//	sDevice_ID= ExcelUtils.getCellData(iTestStep, Constants.Col_DeviceID,Constants.Sheet_TestCases);
					
					iTestStep = ExcelUtils.getRowContains(sTestCaseID, Constants.Col_TestCaseID, Constants.Sheet_TestSteps);
					iTestLastStep = ExcelUtils.getTestStepsCount(Constants.Sheet_TestSteps, sTestCaseID, iTestStep);
					
					System.out.println(threadId+"::"+iTestStep);
					
					System.out.println(threadId+"::"+iTestLastStep);
					
					bResult=true;
					
					for (;iTestStep<iTestLastStep;iTestStep++)
					{
						
						//To Get The Test Step ID
						sTestStepID=ExcelUtils.getCellData(iTestStep, Constants.Col_TestScenarioID,Constants.Sheet_TestSteps);
						System.out.println(threadId+"::"+"sTestStepID:"+sTestStepID);
						
						//To Get The Test Step Description
						sTestStepDesc = ExcelUtils.getCellData(iTestStep, Constants.Col_TestStepDesc,Constants.Sheet_TestSteps);
			    		System.out.println(threadId+"::"+"Test Step Description Is:"+sTestStepDesc);
			    	
			    		
			    		
			    		//To Get The Object Reference 
			    		sObjectRef = ExcelUtils.getCellData(iTestStep, Constants.Col_ObjectRef, Constants.Sheet_TestSteps);
			    		System.out.println(threadId+"::"+"Object Reference Is:"+sObjectRef);
			    		
			    		
			    		//To Get The Input Parameters
			    		sInputParams = ExcelUtils.getCellData(iTestStep, Constants.Col_InputParams, Constants.Sheet_TestSteps);
			    		System.out.println(threadId+"::"+"Input Parameter Is:"+sInputParams);
			    		
			    		//To Get The Keyword for each Test Step
						sKeyword = ExcelUtils.getCellData(iTestStep, Constants.Col_Keyword,Constants.Sheet_TestSteps);
			    		System.out.println(threadId+"::"+"Keyword Is:"+sKeyword);
			    	
			      		
			    		//To Get The ObjectType for each Test Step
			    		sObjectType = ExcelUtils.getCellData(iTestStep, Constants.Col_ObjectType,Constants.Sheet_TestSteps);
			    		System.out.println(threadId+"::"+"ObjectType Is:"+sObjectType);
			    		
			    		
			    		//execute_Actions();
			    		for(int i=0;i<method.length;i++){
			    			
			    			if(method[i].getName().equals(sKeyword)){
			    			//	System.out.println(x);
			    				
			    				actionKeywords.setMethod_Screencapture(fileLocdir, sTestStepID);
			    				
			    				method[i].invoke(actionKeywords,sObjectRef,sInputParams,sObjectType,bResult);
			    				
			    				
			    				Thread.sleep(1000);
			    				
			    				if(bResult==true){
			    					ExcelUtils.setCellData(Constants.KEYWORD_PASS, iTestStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps,sdatasheetloc);
			    					//m.put("TS_STATUS", Constants.KEYWORD_PASS);
			    					m.put("TS_DESC_"+sTestStepID, sTestStepDesc+"::"+Constants.KEYWORD_PASS);
			    					
			    					break;
			    				}else{
			    					ExcelUtils.setCellData(Constants.KEYWORD_FAIL, iTestStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps,sdatasheetloc);
			    					//m.put("TS_STATUS", Constants.KEYWORD_PASS);
			    					//ActionKeywords.closeBrowser("","");
			    					m.put("TS_DESC_"+sTestStepID, sTestStepDesc+"::"+Constants.KEYWORD_FAIL);
			    					break;
			    					}
			    				}
			    			}
			    		
			    		Thread.sleep(1000);
			    		
			    		/*
			    THIS PIECE OF CODE IS FOR INTELLIGENT KEYWORD SEARCH FROM TEST STEP
			    
			    		 for(int i=0;i<keywords.length;i++)
			    		 {
					  
					    	   if(sTestDesc.contains(keywords[i]))
					    	   {
					    		    
					    		   if(keywords[i].equals("Launch"))
					    		   {
					    			   
					    								    				  
					    				  execute_Actions(keywords[i]);
					    			   
						           }
					    		   
					    		   if(keywords[i].equals("Tap"))
					    		   {
					    			   	  
					    			   execute_Actions(keywords[i]);
					    			   
						           }
					    		   
					    		   if(keywords[i].equals("Swipe"))
					    		   {
					    			   	  
					    			   execute_Actions(keywords[i]);
					    			   
						           }
					    		   
					    		   if(keywords[i].equals("Enter"))
					    		   {
					    			   
					    			   execute_Actions(keywords[i]);
					    		   }
					    		   
					    	   }
					        }  
			    		 
			    		 */
			    		
					}
					
					
					
			    		 if(bResult==false){
								ExcelUtils.setCellData(Constants.KEYWORD_FAIL,iTestcase,Constants.Col_Result,Constants.Sheet_TestCases,sdatasheetloc);
								m.put("TC_STATUS", Constants.KEYWORD_FAIL);
								//new SeleniumALMIntegrationExample().updateResults(Constants.KEYWORD_FAIL);
								//new SeleniumALMIntegrationExample().updateResults1(m);
								Log.endTestCase(sTestCaseID);
								break;
								}			
			    	
					
					
					if(bResult==true){
						ExcelUtils.setCellData(Constants.KEYWORD_PASS,iTestcase,Constants.Col_Result,Constants.Sheet_TestCases,sdatasheetloc);
						m.put("TC_STATUS", Constants.KEYWORD_PASS);
						//new SeleniumALMIntegrationExample().updateResults(Constants.KEYWORD_PASS);
						//new SeleniumALMIntegrationExample().updateResults1(m);
						Log.endTestCase(sTestCaseID);	
							}		
					
				}
			} //end of for loop
    		}	
     
    
	/*
    private  void execute_Actions() throws Exception {
    	
		for(int i=0;i<method.length;i++){
			
			if(method[i].getName().equals(sKeyword)){
			//	System.out.println(x);
				method[i].invoke(actionKeywords,sObjectRef,sInputParams,driver);
				
	
				if(bResult==true){
					ExcelUtils.setCellData(Constants.KEYWORD_PASS, iTestStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);
					break;
				}else{
					ExcelUtils.setCellData(Constants.KEYWORD_FAIL, iTestStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);
					//ActionKeywords.closeBrowser("","");
					break;
					}
				}
			}
     }
  
   */
	
    /*
     
       THIS METHOD IS FOR INTELLIGENT KEYWORD SEARCH FROM TEST STEP
       
     private static void execute_Actions(String keyword) throws Exception 
       {
	
    
    	 
		for(int i=0;i<method.length;i++)
		   {
			
			if(method[i].getName().equals(keyword))
			    {
			       
			    method[i].invoke(keyword,sObjectRef,sInputParams);
				Thread.sleep(2000);
				
				if(bResult==true)
				{
					ExcelUtils.setCellData(Constants.KEYWORD_PASS, iTestStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);
					break;
				}
			  else
			      {
			   
					ExcelUtils.setCellData(Constants.KEYWORD_FAIL, iTestStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);
					//ActionKeywords.closeBrowser("","");
					break;
					}
					
				}
			} //end of for loop
     }
     
     */
    
}