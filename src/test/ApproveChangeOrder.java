package test;

import org.testng.annotations.Test;

import base.BTest;
import common.ColumnStyle;
import common.EnvJsonFile;
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
		  Thread.sleep(10000);
		  
		  //login BOM
		  super.LoginBOMAsApprover();
		  Thread.sleep(10000);
		  
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
			  
			  //click the approval tab
			  pendingTaskPage.tab.clickTab("流程审批");
			  Thread.sleep(2000);
			  
			  //check the approval option
			  pendingTaskPage.otherElements.clickRowByText("批准");
			  Thread.sleep(1000);
			  
			  //assign next approvers 
			  String columnId=pendingTaskPage.otherElements.getColumnId(ColumnStyle.GANTLINKCOLUMN,"任务名称");
			  int approverCount=pendingTaskPage.link.getLinkCount(columnId);
			  
			  if(approverCount!=0) {
				  columnId=pendingTaskPage.otherElements.getColumnId(ColumnStyle.GRIDCOLUMN,"操作候选人");
				  
				  for (int i=0;i<approverCount;i++) {
					  pendingTaskPage.text.openTextBox(TextStyle.IDINTD, columnId, i);
					  Thread.sleep(1000);
					  String tableId=pendingTaskPage.otherElements.getTableId(TableStyle.WORKFLOWTASKOWNERTRIGGERFIELD, 0);
		
					  pendingTaskPage.button.clickMagnifyingGlass(TableStyle.WORKFLOWTASKOWNERTRIGGERFIELD,tableId, i+1,2);
					  Thread.sleep(1000);
					  super.bcf.readJasonFile(EnvJsonFile.TESTFILE);
					  pendingTaskPage.option.clickCheckBoxOption(super.bcf.getProperty("approver"));
					  Thread.sleep(1000);
					  pendingTaskPage.button.clickButton(">>");
					  Thread.sleep(1000);
					  pendingTaskPage.button.clickButton("确定");
					  Thread.sleep(1000);
				  }
			  }
			  
			  
			  //click the approve button
			  pendingTaskPage.button.clickButton("执行操作");
			  Thread.sleep(1000);
			  pendingTaskPage.button.clickButton("是");
			  Thread.sleep(3000);
			   
		  }
		  
	} catch (InterruptedException e) {
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
