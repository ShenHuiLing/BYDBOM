package test;

import org.testng.annotations.Test;

import base.BTest;
import common.ColumnStyle;
import common.EnvJsonFile;
import common.LabelStyle;
import common.ListViewStyle;
import common.TableStyle;
import common.TextStyle;
import page.MainPage;
import page.PendingTaskPage;

import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class ApproveChangeOrder extends BTest {
  @Test()
  public void ApproveCO() {
	//start BOM
	  super.StartBOM(EnvJsonFile.BASICFILE, "integration");
	  try {
		  Thread.sleep(5000);
		  
		  //login BOM
		  super.LoginBOMAsApprover();
		  Thread.sleep(5000);
		  
		  //open pending task window
		  MainPage mainPage=new MainPage(super.driver);
		  mainPage.mainMenu.hoverMenu("个人中心");
		  Thread.sleep(2000);
		  mainPage.mainMenu.clickMenu("待处理任务");
		  Thread.sleep(5000);

		  //click the change order link and keep approving the order till the approver completes his task
		  PendingTaskPage pendingTaskPage=new PendingTaskPage(super.driver);
		  
		  super.bcf.readJasonFile(EnvJsonFile.TESTDATA);
		  while(pendingTaskPage.link.clickLinkByText(super.bcf.getProperty("ChangeOrder"))) {
			  Thread.sleep(2000);
			  
			  super.approveProcess();
			  
		  }
		  
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		Assert.assertEquals(false, true);
	}
	  
	  
  }
  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
	  super.close();
  }

}
