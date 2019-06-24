package test;

import org.testng.annotations.Test;

import base.BTest;
import common.DropDownListStyle;
import common.EnvJsonFile;
import common.LabelStyle;
import common.TextStyle;
import page.MainPage;
import page.PSPage;

import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class PSPublishEBOM extends BTest{
  @Test
  public void publishEBOMWithECO() {
	  try {
		  //start BOM
		  super.StartBOM(EnvJsonFile.BASICFILE, "integration");
		  Thread.sleep(5000);
		
		  //login BOM
		  super.LoginBOM();
		  Thread.sleep(5000);
		  
		  //open PS window
		  MainPage mainPage=new MainPage(super.driver);
		  mainPage.mainMenu.hoverMenu("变更管理");
		  Thread.sleep(2000);
		  mainPage.mainMenu.clickMenu("评审报告管理");
		  Thread.sleep(2000);
		  
		  PSPage psPage=new PSPage(super.driver);
		  
		  //create a new PS
		  psPage.button.clickButton("新增");
		  Thread.sleep(1000);
		  String changeOrder=psPage.text.getValueFromTextBox();
		  System.out.println(changeOrder);
		  
		  String labelId;
		  String prjectCode;
		  super.bcf.readJasonFile(EnvJsonFile.TESTDATA);
		  prjectCode=super.bcf.getProperty("ProjectCode");
		  
		  //select the vehicle mode code
		  labelId=psPage.otherElements.getLabelId(LabelStyle.GANTCOMBOBOX,"车型型号",1);
		  psPage.option.expandDropdownList(DropDownListStyle.GANTCOMBOBOX,labelId);
		  Thread.sleep(2000);
		  psPage.option.selectOption(prjectCode);
		  Thread.sleep(1000);
		  
		  //select the change type
		  labelId=psPage.otherElements.getLabelId(LabelStyle.GANTCODETYPECOMBOBOX,"变更类型",1);
		  psPage.option.expandDropdownList(DropDownListStyle.GANTCODETYPECOMBOBOX,labelId);
		  Thread.sleep(2000);
		  psPage.option.selectOption("仅BOM变更");
		  Thread.sleep(1000);
		  
		  //select the change source
		  labelId=psPage.otherElements.getLabelId(LabelStyle.GANTCODETYPECOMBOBOX,"变更来源",0);
		  System.out.println(labelId);
		  psPage.option.expandDropdownList(DropDownListStyle.GANTCODETYPECOMBOBOX,labelId);
		  Thread.sleep(2000);
		  psPage.option.selectOption("公司定义");
		  Thread.sleep(1000);
		  
		  //select the change source
		  labelId=psPage.otherElements.getLabelId(LabelStyle.GANTCODETYPECOMBOBOX,"评审阶段",0);
		  System.out.println(labelId);
		  psPage.option.expandDropdownList(DropDownListStyle.GANTCODETYPECOMBOBOX,labelId);
		  Thread.sleep(2000);
		  psPage.option.selectOption("试制前");
		  Thread.sleep(1000);
		  
		  String Id;
		  //input the change brief
		  Id=psPage.otherElements.getLabelId(LabelStyle.TEXTFIELD, "变更主题");
		  psPage.text.openTextBox(TextStyle.IDININPUT, Id, 1);
		  Thread.sleep(1000);
		  psPage.text.inputText("changeExt.changeTheme","EBOM_publish_" + changeOrder);
		  Thread.sleep(1000);
		  
		  //input the change reason
		  Id=psPage.otherElements.getLabelId(LabelStyle.TEXTAREAFIELD, "变更原因");
		  psPage.text.openTextBox(TextStyle.TEXTAREAFIELD, Id, 1);
		  Thread.sleep(1000);
		  psPage.text.inputText(TextStyle.TEXTAREAFIELD,"changeReason","EBOM_publish_reason_" + changeOrder);
		  Thread.sleep(1000);
		  
		  //input the change method
		  Id=psPage.otherElements.getLabelId(LabelStyle.TEXTAREAFIELD, "变更措施");
		  psPage.text.openTextBox(TextStyle.TEXTAREAFIELD, Id, 1);
		  Thread.sleep(1000);
		  psPage.text.inputText(TextStyle.TEXTAREAFIELD,"changeExt.changeMeasures","EBOM_publish_method_" + changeOrder);
		  Thread.sleep(1000);
		  
		  //save the ECO
		  psPage.button.clickButton("保存");
		  Thread.sleep(3000);
		  
		  
		  
		  
	  }catch(Exception e) {
		  e.printStackTrace();
		  Assert.assertEquals(false, true);
	  }
  }
  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

}
