package test;

import org.testng.annotations.Test;

import base.BTest;
import common.EnvJsonFile;
import common.LabelStyle;
import common.ListViewStyle;
import common.TableStyle;
import common.TextStyle;
import page.MainPage;
import page.MaterialPage;

import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class MaterialApply extends BTest{
  @Test
  public void ApplyMaterialNum() {
try {
		  
		  //start BOM
		  super.StartBOM(EnvJsonFile.BASICFILE, "integration");
		  Thread.sleep(10000);
		  
		  		
		  //login BOM
		  super.LoginBOM();
		  Thread.sleep(10000);
		  
		  //open material library window
		  MainPage mainPage=new MainPage(super.driver);
		  mainPage.mainMenu.hoverMenu("物料管理");
		  Thread.sleep(2000);
		  mainPage.mainMenu.clickMenu("物料库");
		  Thread.sleep(5000);
		  
		  MaterialPage materialPage=new MaterialPage(super.driver);
		  //create a new material application form
		  materialPage.button.clickButton("进入编辑");
		  Thread.sleep(1000);
		  materialPage.button.clickButton("新增");
		  Thread.sleep(1000);
		  materialPage.button.clickChildButton("添加零件");
		  Thread.sleep(1000);
		  
		  super.bcf.readJasonFile(EnvJsonFile.TESTDATA);
		  
		  String materialApplicationTableId;
		  String magnifyingGlassTableId;
		  String PopUpTableId;
		  materialApplicationTableId=materialPage.otherElements.getTableId(TableStyle.GRIDVIEW, 0);
		  
		  //select part
		  materialPage.text.openTextBox(materialApplicationTableId, 1, 4);
		  Thread.sleep(1000);
		  magnifyingGlassTableId=materialPage.otherElements.getTableId(TableStyle.GANGTRIGGERFIELD, 1);
		  materialPage.button.clickMagnifyingGlass(TableStyle.GANGTRIGGERFIELD, magnifyingGlassTableId, 1, 2);
		  Thread.sleep(2000);
		  String Id;
		  Id=materialPage.otherElements.getLabelId(LabelStyle.TEXTFIELD, "零件号");
		  materialPage.text.openTextBox(TextStyle.IDININPUT, Id, 1);
		  materialPage.text.openTextBox(TextStyle.IDININPUT, Id, 1);
		  materialPage.text.inputText(TextStyle.TEXTFIELD,super.bcf.getProperty("PartNum"));
		  Thread.sleep(1000);
		  materialPage.button.clickButton("查询",1);
		  Thread.sleep(1000);
		  PopUpTableId=materialPage.otherElements.getTableId(TableStyle.GRIDVIEW,2);
		  materialPage.option.clickCheckBox(PopUpTableId, 1,1);
		  Thread.sleep(1000);
		  materialPage.button.clickButton("选择");
		  Thread.sleep(1000);
		  
		  materialApplicationTableId=materialPage.otherElements.getTableId(TableStyle.GRIDVIEW, 1);
		  //input requested plant
		  materialPage.text.openTextBox(materialApplicationTableId, 1, 7);
		  Thread.sleep(1000);
		  materialPage.text.inputText(TextStyle.TEXTFIELD,super.bcf.getTimeStamp().substring(4));
		  Thread.sleep(1000);
		  
		  //input experts group
		  materialPage.text.openTextBox(materialApplicationTableId, 1, 8);
		  Thread.sleep(1000);
		  materialPage.text.inputText(TextStyle.TEXTFIELD,super.bcf.getTimeStamp().substring(4));
		  Thread.sleep(1000);
		  
		  //select operation mode
		  materialPage.text.openTextBox(materialApplicationTableId, 1, 15);
		  Thread.sleep(1000);
		  materialPage.option.expandDropdownList();
		  Thread.sleep(1000);
		  materialPage.option.selectOption("BS");
		  Thread.sleep(1000);
		  
		  
		  //input material group
		  materialPage.text.openTextBox(materialApplicationTableId, 1, 17);
		  Thread.sleep(1000);
		  materialPage.text.inputText(TextStyle.TEXTFIELD,super.bcf.getTimeStamp().substring(4));
		  Thread.sleep(1000);
		  
		  
		  //input tax code
		  materialPage.text.openTextBox(materialApplicationTableId, 1, 26);
		  Thread.sleep(1000);
		  String tax_id=super.bcf.getTimeStamp().substring(4);
		  materialPage.text.inputText(TextStyle.TEXTFIELD,tax_id);
		  Thread.sleep(1000);
		  
		  //save the material application order
		  materialPage.button.clickButton("保存");
		  Thread.sleep(2000);
		  Assert.assertEquals(materialPage.otherElements.isEditFlagDisappeared(ListViewStyle.GRIDVIEW), true);
		  
		  //update the material application record in DB and assign a material number to the part
		  super.bcf.connectDB(EnvJsonFile.BASICFILE, "integration");
		  String sql="update CUST.CUST_MATERIAL_INFO\r\n" + 
					"set \r\n" + 
					"CF_STATUS='APPLY_COMPLETE',\r\n" + 
					"CF_NO='P'||(select to_char(sysdate,'yyyymmddhh24miss') from dual),\r\n" + 
					"MAT_NO=(select to_char(sysdate,'yyyymmddhh24miss') from dual),\r\n" + 
					"mat_desc='mat_desc' || (select to_char(sysdate,'yyyymmddhh24miss') from dual),\r\n" + 
					"mat_endesc='mat_endesc' || (select to_char(sysdate,'yyyymmddhh24miss') from dual),\r\n" + 
					"ACTIVE_STATUS='CURRENT'\r\n" + 
					"where tax_id='" + tax_id + "'";
		  super.bcf.updateData(sql);
		  super.bcf.closeDB();
		  
		  
		  
			  	  
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
