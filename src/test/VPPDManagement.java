package test;

import org.testng.annotations.Test;

import base.BTest;
import common.ColumnStyle;
import common.EnvJsonFile;
import common.ListViewStyle;
import common.TextStyle;
import page.MainPage;
import page.VPPDPage;

import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

/* Test: VPPD维护
 * 1. start BOM
 * 2. login BOM
 * 3. open VPPD window
 * 4. add node step by step
 */
public class VPPDManagement extends BTest {
  @Test
  public void AddVPPD() {
	  try {
		  
		  //start BOM
		  super.StartBOM(EnvJsonFile.BASICFILE, "integration");
		  Thread.sleep(10000);
		
		  //login BOM
		  super.LoginBOM();
		  Thread.sleep(10000);
		  
		  //open product structure window
		  MainPage mainPage=new MainPage(super.driver);
		  mainPage.mainMenu.hoverMenu("产品管理");
		  Thread.sleep(2000);
		  mainPage.mainMenu.clickMenu("产品结构模板");
		  Thread.sleep(2000);
		  
		  //select version to lookup VPPD
		  VPPDPage vppdPage=new VPPDPage(super.driver);
		  vppdPage.option.openComboxFromQuerySection("版本");
		  Thread.sleep(1000);
		  vppdPage.option.selectLastOption();
		  Thread.sleep(1000);
		  vppdPage.button.clickButton("查询");
		  Thread.sleep(2000);
		  
		  try {
		  vppdPage.button.clickButton("进入编辑");
		  Thread.sleep(1000);
		  }
		  catch(Exception e) {
			  //if there is no draft version, need to click prompt button then start editting
			  vppdPage.button.clickButton("升版");
			  Thread.sleep(2000);
			  vppdPage.option.openComboxFromQuerySection("版本");
			  Thread.sleep(1000);
			  vppdPage.option.selectLastOption();
			  Thread.sleep(1000);
			  vppdPage.button.clickButton("查询");
			  Thread.sleep(2000);
			  vppdPage.button.clickButton("进入编辑");
			  Thread.sleep(1000);
		  }
		  
		  vppdPage.option.clickCheckBox(0,ListViewStyle.GRIDVIEW);
		  
		  //add a node
		  vppdPage.button.clickButton("新增");
		  Thread.sleep(1000);
		  vppdPage.button.clickChildButton("新增子节点");
		  Thread.sleep(1000);
		  
		  //select the newly added node to edit its attributes
		  vppdPage.option.clickCheckBox(1, ListViewStyle.GRIDVIEW);
		  Thread.sleep(1000);
		  
		  //add the code
		  int columnId=Integer.parseInt(vppdPage.otherElements.getColumnId(ColumnStyle.GRIDCOLUMN,"编码"));
		  vppdPage.text.openTextBox(TextStyle.IDINTD, String.valueOf(columnId), 1);
		  Thread.sleep(1000);
		  vppdPage.text.inputText(TextStyle.TEXTFIELD,super.bcf.getTimeStamp());
		  
		  //add the simple code
		  vppdPage.text.openTextBox(TextStyle.IDINTD, String.valueOf(columnId+1), 1);
		  Thread.sleep(1000);
		  vppdPage.text.inputText(TextStyle.TEXTFIELD,super.bcf.getTimeStamp());
		  
		  //add the function position code
		  vppdPage.text.openTextBox(TextStyle.IDINTD, String.valueOf(columnId+2), 1);
		  Thread.sleep(1000);
		  vppdPage.text.inputText(TextStyle.TEXTFIELD,super.bcf.getTimeStamp());
		  
		  //add the Chinese description
		  vppdPage.text.openTextBox(TextStyle.IDINTD, String.valueOf(columnId-1), 1);
		  Thread.sleep(1000);
		  vppdPage.text.inputText(TextStyle.TEXTFIELD,super.bcf.getTimeStamp());
		  
		  //add the English description
		  vppdPage.text.openTextBox(TextStyle.IDINTD, String.valueOf(columnId+3), 1);
		  Thread.sleep(1000);
		  vppdPage.text.inputText(TextStyle.TEXTFIELD,super.bcf.getTimeStamp());
		  
		  //save the VPPD
		  vppdPage.button.clickButton("保存");
		  Thread.sleep(1000);
		  
		  Assert.assertEquals(vppdPage.otherElements.isEditFlagDisappeared(ListViewStyle.GRIDVIEW), true);
		  
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
