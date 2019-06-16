package test;

import org.testng.annotations.Test;

import base.BTest;
import common.ChangeOrderCode;
import common.ColumnStyle;
import common.EnvJsonFile;
import common.LabelStyle;
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
		  mainPage.mainMenu.clickMenu("产品结构模板变更管理");
		  Thread.sleep(2000);
		  
		  //create a new VCO
		  VCOPage vcoPage=new VCOPage(super.driver);
		  vcoPage.button.clickButton("新增");
		  Thread.sleep(1000);
		  String changeOrder=vcoPage.text.getValueFromTextBox();
		  System.out.println(changeOrder);
		  
		  vcoPage.button.clickButton("保存");
		  Thread.sleep(1000);
		  
		  //add the change content
		  vcoPage.tab.clickTab("变更内容");
		  Thread.sleep(1000);
		  vcoPage.button.clickButton("整版关联");
		  Thread.sleep(5000);
		  
		  //start approval process
		  super.startApprovalProcess();
		  
		  Map<String, String> testData=new HashMap<String, String>();
		  testData.put("ChangeOrder",changeOrder);
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
