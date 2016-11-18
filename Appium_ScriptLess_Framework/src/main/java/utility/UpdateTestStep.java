package utility;

import com.jacob.com.Dispatch;

import atu.alm.wrapper.ALMServiceWrapper;
import atu.alm.wrapper.ITestCase;
import atu.alm.wrapper.ITestCaseRun;
import atu.alm.wrapper.classes.Run;
import atu.alm.wrapper.classes.RunFactory;
import atu.alm.wrapper.classes.Step;
import atu.alm.wrapper.classes.StepFactory;
import atu.alm.wrapper.classes.TSTest;
import atu.alm.wrapper.classes.TSTestFactory;
import atu.alm.wrapper.classes.TestSet;
import atu.alm.wrapper.classes.TestSetFolder;
import atu.alm.wrapper.classes.TestSetTreeManager;
import atu.alm.wrapper.collection.ListWrapper;
import atu.alm.wrapper.enums.StatusAs;
import atu.alm.wrapper.exceptions.ALMServiceException;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class UpdateTestStep extends ALMServiceWrapper

{
	
	Dispatch stepFactory;
	
	public static void main(String args[]) throws Exception
	{
		
		UpdateTestStep wrapper = new UpdateTestStep(
				"http://inmbzp4181.in.dst.ibm.com:8888/qcbin/");
		//http://inmbzp4181.in.dst.ibm.com:8888/qcbin/start_a.jsp
		
		boolean b=wrapper.connect("sayom", "sayom", "mobile_testing", "APPIUM_ALM_Integration");
		
		System.out.println("The Connection is:"+b);
		
		//ITestCaseRun loginRun = wrapper.createNewRun(t, "Run 1",StatusAs.PASSED);
		//ITestCase t=wrapper.updateResult_TestStep(loginRun,"Appium-Automation","TestSet1", 2, "To Test Login", StatusAs.PASSED);
	
		

		
	}


	
	public UpdateTestStep(String url) {
		super(url);
		// TODO Auto-generated constructor stub
	}

	public Step updateItem()
	  {
	    Dispatch step = Dispatch.call(this.stepFactory, "UpdateItem", new Object[] { "Null" }).toDispatch();
	    
	    return new Step(step);
	  }
	
	/*
	 public ITestCaseRun createNewRun1(String tsTest, String runName, StatusAs as)
	  {
		 

		//    RunFactory runFactory = (ITestCase)tsTest.getRunFactory();
		 
	    //RunFactory runFactory =(ITestCase) tsTest;
	    
	    Run run = runFactory.addItem();
	    run.setStatus(as);
	    run.setName(runName);
	    run.post();
	    return run;
	  }
	 */
	
	public ITestCase updateResult_TestStep(ITestCaseRun run,String testSetFolderPath, String testSetName, int testSetID, String tcName, StatusAs as)
		    throws ALMServiceException
		  {
		    TestSetTreeManager testSetTreeManager = getAlmObj().getTestSetTreeManager();
		    
		    TestSetFolder testSetFolder = testSetTreeManager.getNodeByPath(testSetFolderPath);
		    
		    
		    TestSet testSet = testSetFolder.findTestSet(testSetName, testSetID);
		    TSTestFactory tsTestFactory = testSet.getTSTestFactory();
		    ListWrapper<TSTest> listWrapper = tsTestFactory.getNewList();
		    for (TSTest tsTest : listWrapper) {
		      if (tcName.equals(tsTest.getTestName()))
		      {
		        tsTest.putStatus(as);
		        tsTest.post();
		        
		        StepFactory stepFactory = run.getStepFactory();
		    
		        Step step = updateItem();
		        
		        System.out.println("STEP IS:"+step);
		        
		        return tsTest;
		      }
		    }
		    throw new ALMServiceException("The Given Test Name \"" + tcName + "\" Not Found");
		  }
		  
}
