package test;

import org.testng.annotations.Test;

import base.BTest;
import common.ChangeOrderCode;
import common.ColumnStyle;
import common.EnvJsonFile;
import common.TableStyle;
import common.TextStyle;
import page.MainPage;
import page.VCOPage;

import org.testng.annotations.BeforeTest;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class VCOPublishVPPD extends BTest {
  @Test
  public void SubmitVCO() {
	  try
	  {
		  //start BOM
		  super.StartBOM(EnvJsonFile.BASICFILE, "integration");
		  Thread.sleep(10000);
		
		  //login BOM
		  super.LoginBOM();
		  Thread.sleep(10000);
		  
		  //open VCO window
		  MainPage mainPage=new MainPage(super.driver);
		  mainPage.mainMenu.hoverMenu("变更管理");
		  Thread.sleep(2000);
		  mainPage.mainMenu.clickMenu("产品结构变更管理");
		  Thread.sleep(2000);
		  
		  //create a new VCO
		  VCOPage vcoPage=new VCOPage(super.driver);
		  vcoPage.button.clickButton("新增");
		  Thread.sleep(1000);
		  
		  vcoPage.button.clickButton("保存");
		  Thread.sleep(1000);
		  
		  //add the change content
		  vcoPage.tab.clickTab("变更内容");
		  Thread.sleep(1000);
		  vcoPage.button.clickButton("整版关联");
		  Thread.sleep(1000);
		  
		  //add approver
		  vcoPage.tab.clickTab("流程审批");
		  Thread.sleep(1000);
		  String columnId=vcoPage.otherElements.getColumnId(ColumnStyle.GANTLINKCOLUMN,"审批任务名称");
		  int approverCount=vcoPage.link.getLinkCount(columnId);
		  
		  columnId=vcoPage.otherElements.getColumnId(ColumnStyle.GRIDCOLUMN,"操作候选人");
		  
		  for (int i=0;i<approverCount;i++) {
			  vcoPage.text.openTextBox(TextStyle.IDINTD, columnId, i);
			  Thread.sleep(1000);
			  String tableId=vcoPage.otherElements.getTableId(TableStyle.WORKFLOWTASKOWNERTRIGGERFIELD, 0);
			  vcoPage.button.clickMagnifyingGlass(TableStyle.WORKFLOWTASKOWNERTRIGGERFIELD,tableId, i+1,2);
			  Thread.sleep(1000);
			  super.bcf.readJasonFile(EnvJsonFile.TESTFILE);
			  vcoPage.option.clickCheckBoxOption(super.bcf.getProperty("approver"));
			  Thread.sleep(1000);
			  vcoPage.button.clickButton(">>");
			  Thread.sleep(1000);
			  vcoPage.button.clickButton("确定");
			  Thread.sleep(1000);
		  }
			  
		  //start approval process
		  vcoPage.button.clickButton("启动审批流程");
		  Thread.sleep(5000);
		  
		  Map<String, String> testData=new HashMap<String, String>();
		  testData.put("ChangeOrder",vcoPage.otherElements.getLatestChangeOrder(ChangeOrderCode.VCO));
		  super.bcf.writeJasonFile(EnvJsonFile.TESTDATA, testData);
		  
	  }
	  catch(Exception e) {
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
