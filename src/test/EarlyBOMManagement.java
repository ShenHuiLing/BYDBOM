package test;

import org.testng.annotations.Test;

import base.BTest;
import common.DropDownListStyle;
import common.EnvJsonFile;
import common.LabelStyle;
import common.ListViewStyle;
import common.TableStyle;
import common.TextStyle;
import page.EBOMPage;
import page.MainPage;

import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class EarlyBOMManagement extends BTest{
  @Test
  public void AddPartInEarlyBOM() {
	  try {
		  //start BOM
		  super.StartBOM(EnvJsonFile.BASICFILE, "integration");
		  Thread.sleep(10000);
		  
		  //login BOM
		  super.LoginBOM();
		  Thread.sleep(10000);
		  
		  //open EBOM window
		  MainPage mainPage=new MainPage(super.driver);
		  mainPage.mainMenu.hoverMenu("BOM����");
		  Thread.sleep(2000);
		  mainPage.mainMenu.hoverMenu("����BOM����");
		  Thread.sleep(2000);
		  mainPage.mainMenu.clickMenu("����BOM����");
		  Thread.sleep(10000);
		  
		  EBOMPage eBomPage=new EBOMPage(super.driver);
		  
		  //select project code and query the bom
		  String labelId=eBomPage.otherElements.getLabelId(LabelStyle.COMBO,"�����ͺ�");
		  super.bcf.readJasonFile(EnvJsonFile.TESTDATA);
		  String prjectCode=super.bcf.getProperty("ProjectCode");
		  eBomPage.option.expandDropdownList(DropDownListStyle.COMBO,labelId);
		  Thread.sleep(2000);
		  eBomPage.option.selectOption(prjectCode);
		  Thread.sleep(1000);
		  eBomPage.button.clickButton("��ѯ");
		  Thread.sleep(5000);
		  
		  eBomPage.option.clickCheckBox(0,ListViewStyle.GRIDVIEW);
		  
		  //add a part
		  eBomPage.button.clickButton("����༭");
		  Thread.sleep(1000);
		  eBomPage.button.clickButton("����");
		  Thread.sleep(1000);
		  eBomPage.button.clickChildButton("�����ӽڵ�");
		  Thread.sleep(1000);
		  
		  //from the part selector, choose a part
		  String Id;
		  Id=eBomPage.otherElements.getLabelId(LabelStyle.TEXTFIELD, "�����");
		  eBomPage.text.openTextBox(TextStyle.IDININPUT, Id, 1);
		  Thread.sleep(1000);
		  eBomPage.text.inputText(TextStyle.TEXTFIELD,super.bcf.getProperty("PartNum"));
		  Thread.sleep(1000);
		  eBomPage.button.clickButton("��ѯ",1);
		  Thread.sleep(1000);
		  String PopUpTableId=eBomPage.otherElements.getTableId(TableStyle.GRIDVIEW,2);
		  eBomPage.option.clickCheckBox(PopUpTableId, 1,1);
		  Thread.sleep(1000);
		  eBomPage.button.clickButton("ѡ��");
		  Thread.sleep(1000);
		  eBomPage.button.clickCloseButton(1);
		  Thread.sleep(1000);
		  
		  String mainDataTableId=eBomPage.otherElements.getTableId(TableStyle.GRIDVIEW,1);
		 
		  //input quantity
		 
		  Thread.sleep(1000);
		  eBomPage.text.openTextBox(mainDataTableId, 2, 1);
		  Thread.sleep(1000);
		  eBomPage.text.inputText(TextStyle.NUMBERFIELD, super.bcf.getTimeStamp().substring(4));
		  Thread.sleep(1000);
		  
		  
		  //check if there is functional position code. if no, select functional position code
		  if(eBomPage.text.isTextBoxEmpty(mainDataTableId, 2, 27)) {
			  eBomPage.text.openTextBox(mainDataTableId, 2, 27);
			  Thread.sleep(1000);
			  String MagnifyingGlassTableId=eBomPage.otherElements.getTableId(TableStyle.GANGTRIGGERFIELD, 2);
			  eBomPage.button.clickMagnifyingGlass(TableStyle.GANGTRIGGERFIELD, MagnifyingGlassTableId,1,2);
			  Thread.sleep(3000);
			  PopUpTableId=eBomPage.otherElements.getTableId(TableStyle.GRIDVIEW,2);
			  eBomPage.option.clickCheckBox(PopUpTableId, 2,1);
			  Thread.sleep(1000);
			  eBomPage.button.clickButton("ѡ��");
			  Thread.sleep(2000);
		  }
		  
		  
		  //select the assemble workshop
		  eBomPage.text.openTextBox(mainDataTableId, 2, 2);
		  Thread.sleep(1000);
		  eBomPage.option.expandDropdownList();
		  Thread.sleep(1000);
		  eBomPage.option.selectOption("��װ����");
		  Thread.sleep(1000);
		  
		  
		  //check if there is suggested source. If no, add it
		  if(eBomPage.text.isTextBoxEmpty(mainDataTableId, 2, 6)) {
			  eBomPage.text.openTextBox(mainDataTableId, 2, 6);
			  Thread.sleep(1000);
			  eBomPage.option.expandDropdownList();
			  Thread.sleep(1000);
			  eBomPage.option.selectOption("����-Ϳ");
			  Thread.sleep(1000);
		  }
		  
		  
		  //select development strategy
		  eBomPage.text.openTextBox(mainDataTableId, 2, 7);
		  Thread.sleep(1000);
		  eBomPage.option.expandDropdownList();
		  Thread.sleep(1000);
		  eBomPage.option.selectOption("�¿���");
		  Thread.sleep(2000);
		  eBomPage.text.openTextBox(mainDataTableId, 2, 12);
		  
		  //save
		  eBomPage.button.clickButton("����");
		  Thread.sleep(2000);
		  
		  Assert.assertEquals(eBomPage.otherElements.isEditFlagDisappeared(ListViewStyle.GRIDVIEW), true);
		  
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
	super.close();
  }

}