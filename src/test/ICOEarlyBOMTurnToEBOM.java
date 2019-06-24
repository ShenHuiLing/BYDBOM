package test;

import org.testng.annotations.Test;

import base.BTest;
import common.DropDownListStyle;
import common.EnvJsonFile;
import common.LabelStyle;
import page.BOMPublishPage;
import page.MainPage;

import org.testng.annotations.BeforeTest;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class ICOEarlyBOMTurnToEBOM extends BTest{
  @Test
  public void earLyBOMTurnToEBOM() {
	  try {
		  //start BOM
		  super.StartBOM(EnvJsonFile.BASICFILE, "integration");
		  Thread.sleep(10000);
		
		  //login BOM
		  super.LoginBOM();
		  Thread.sleep(10000);
		  
		  //open ICO window
		  MainPage mainPage=new MainPage(super.driver);
		  mainPage.mainMenu.hoverMenu("�������");
		  Thread.sleep(2000);
		  mainPage.mainMenu.clickMenu("BOM���ݷ�������");
		  Thread.sleep(2000);
		  
		  BOMPublishPage bomPublishPage=new BOMPublishPage(super.driver);
		  
		  //create a new ICO
		  bomPublishPage.button.clickButton("����");
		  Thread.sleep(1000);
		  String changeOrder=bomPublishPage.text.getValueFromTextBox();
		  System.out.println(changeOrder);
		  
		  String labelId;
		  String prjectCode;
		  
		  //select vehicle mode code
		  labelId=bomPublishPage.otherElements.getLabelId(LabelStyle.GANTCOMBOBOX,"�����ͺ�",1);
		  super.bcf.readJasonFile(EnvJsonFile.TESTDATA);
		  prjectCode=super.bcf.getProperty("ProjectCode");
		  bomPublishPage.option.expandDropdownList(DropDownListStyle.GANTCOMBOBOX,labelId);
		  Thread.sleep(2000);
		  bomPublishPage.option.selectOption(prjectCode);
		  Thread.sleep(1000);
		  
		  //select type
		  labelId=bomPublishPage.otherElements.getLabelId(LabelStyle.GANTCODETYPECOMBOBOX,"����",1);
		  bomPublishPage.option.expandDropdownList(DropDownListStyle.GANTCODETYPECOMBOBOX,labelId);
		  Thread.sleep(2000);
		  bomPublishPage.option.selectOption("����BOMת����BOM");
		  Thread.sleep(1000);
		  
		  bomPublishPage.button.clickButton("����");
		  Thread.sleep(3000);
		  
		  //add the change content
		  bomPublishPage.tab.clickTab("����BOMת����BOM");
		  Thread.sleep(1000);
		  bomPublishPage.button.clickButton("����");
		  Thread.sleep(5000);
		  
		  //start approval process
		  super.startApprovalProcess();
		  
		  Map<String, String> testData=new HashMap<String, String>();
		  testData.put("ChangeOrder",changeOrder);
		  super.bcf.writeJasonFile(EnvJsonFile.TESTDATA, testData);
		  
	  }catch(Exception e){
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
