package test;

import org.testng.annotations.Test;

import base.BTest;
import common.EnvJsonFile;
import common.ListViewStyle;
import common.TextStyle;
import page.MainPage;
import page.ProductSpectrumPage;

import org.testng.annotations.BeforeTest;


import org.testng.Assert;
import org.testng.annotations.AfterTest;

/* Test: 产品型谱维护功能
 * 1. start BOM
 * 2. login BOM
 * 3. open product spectrum window
 * 4. add node step by step
 */
public class ProductSpectrum extends BTest {
  @Test
  public void ProductSpectrumManagement() {
	  try {
		  //start BOM
		  super.StartBOM(EnvJsonFile.BASICFILE, "integration");
		  Thread.sleep(5000);
		  
		  //login BOM
		  super.LoginBOM();
		  Thread.sleep(10000);
		  
		  //open product spectrum window
		  MainPage mainPage=new MainPage(super.driver);
		  mainPage.mainMenu.hoverMenu("产品管理");
		  Thread.sleep(2000);
		  mainPage.mainMenu.clickMenu("产品型谱管理");
		  Thread.sleep(5000);
		  
		  //edit product spectrum
		  ProductSpectrumPage productSpectrumPage=new ProductSpectrumPage(super.driver);
		  productSpectrumPage.option.clickCheckBox(0,ListViewStyle.TREEVIEW);
		  productSpectrumPage.button.clickButton("进入编辑");
		  Thread.sleep(1000);
		  
		  //add car series
		  productSpectrumPage.button.clickButton("新增");
		  Thread.sleep(1000);
		  productSpectrumPage.button.clickChildButton("新增子节点");
		  Thread.sleep(1000);
		  
		  productSpectrumPage.text.openTextBox(TextStyle.IDINTR, "nodeCode", 1);
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText(TextStyle.TEXTFIELD,"AT-code-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.text.openTextBox(TextStyle.IDINTR, "nodeName", 1);
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText(TextStyle.TEXTFIELD,"AT-name-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.text.openTextBox(TextStyle.IDINTR, "description", 1);
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText(TextStyle.TEXTFIELD,"AT-description-"+super.bcf.getTimeStamp());
		  
		  //add car type
		  productSpectrumPage.button.clickButton("新增");
		  Thread.sleep(1000);
		  productSpectrumPage.button.clickChildButton("新增子节点");
		  Thread.sleep(1000);

		  productSpectrumPage.text.openTextBox(TextStyle.IDINTR, "nodeCode", 1);
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText(TextStyle.TEXTFIELD,super.bcf.getTimeStamp().substring(4));
		  
		  productSpectrumPage.text.openTextBox(TextStyle.IDINTR, "nodeName", 1);
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText(TextStyle.TEXTFIELD,"AT-name-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.text.openTextBox(TextStyle.IDINTR, "description", 1);
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText(TextStyle.TEXTFIELD,"AT-description-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.text.openTextBox(TextStyle.IDINTR, "fuelType", 1);
		  Thread.sleep(1000);
		  productSpectrumPage.option.expandDropdownList();
		  Thread.sleep(1000);
		  productSpectrumPage.option.selectOption("燃油");
		  
		  productSpectrumPage.text.openTextBox(TextStyle.IDINTR, "upgrading", 1);
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText(TextStyle.TEXTFIELD,"AT-upgrading-"+super.bcf.getTimeStamp());
		  
		  //add expected go-live year
		  productSpectrumPage.button.clickButton("新增");
		  Thread.sleep(1000);
		  productSpectrumPage.button.clickChildButton("新增子节点");
		  Thread.sleep(1000);
		  
		  productSpectrumPage.text.openTextBox(TextStyle.IDINTR, "nodeCode", 1);
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText(TextStyle.TEXTFIELD,"AT-code-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.text.openTextBox(TextStyle.IDINTR, "nodeName", 1);
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText(TextStyle.TEXTFIELD,"AT-name-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.text.openTextBox(TextStyle.IDINTR, "description", 1);
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText(TextStyle.TEXTFIELD,"AT-description-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.text.openTextBox(TextStyle.IDINTR, "status", 1);
		  Thread.sleep(1000);
		  productSpectrumPage.option.expandDropdownList();
		  Thread.sleep(1000);
		  productSpectrumPage.option.selectOption("规划");
		  Thread.sleep(1000);
		  
		  productSpectrumPage.text.openTextBox(TextStyle.IDINTR, "firstPlant", 1);
		  Thread.sleep(1000);
		  productSpectrumPage.option.expandDropdownList();
		  Thread.sleep(1000);
		  productSpectrumPage.option.SelectAllCheckboxOption();
		  
		  
		  //add power configuration
		  productSpectrumPage.button.clickButton("新增");
		  Thread.sleep(1000);
		  productSpectrumPage.button.clickChildButton("新增子节点");
		  Thread.sleep(1000);
		  
		  productSpectrumPage.text.openTextBox(TextStyle.IDINTR, "nodeCode", 1);
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText(TextStyle.TEXTFIELD,super.bcf.getTimeStamp().substring(4));
		  
		  productSpectrumPage.text.openTextBox(TextStyle.IDINTR, "nodeName", 1);
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText(TextStyle.TEXTFIELD,"AT-name-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.text.openTextBox(TextStyle.IDINTR, "description", 1);
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText(TextStyle.TEXTFIELD,"AT-description-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.text.openTextBox(TextStyle.IDINTR, "saleMarket", 1);
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText(TextStyle.TEXTFIELD,"AT-saleMarket-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.text.openTextBox(TextStyle.IDINTR, "dynamicConfig", 1);
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText(TextStyle.TEXTFIELD,"AT-dynamicConfig-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.text.openTextBox(TextStyle.IDINTR, "status", 1);
		  Thread.sleep(1000);
		  productSpectrumPage.option.expandDropdownList();
		  Thread.sleep(1000);
		  productSpectrumPage.option.selectOption("规划");
		  

		  
		  //add basic configuration
		  productSpectrumPage.button.clickButton("新增");
		  Thread.sleep(1000);
		  productSpectrumPage.button.clickChildButton("新增子节点");
		  Thread.sleep(1000);
		  
		  productSpectrumPage.text.openTextBox(TextStyle.IDINTR, "nodeCode", 1);
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText(TextStyle.TEXTFIELD,"AT-code-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.text.openTextBox(TextStyle.IDINTR, "nodeName", 1);
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText(TextStyle.TEXTFIELD,"AT-name-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.text.openTextBox(TextStyle.IDINTR, "description", 1);
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText(TextStyle.TEXTFIELD,"AT-description-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.text.openTextBox(TextStyle.IDINTR, "status", 1);
		  Thread.sleep(1000);
		  productSpectrumPage.option.expandDropdownList();
		  Thread.sleep(1000);
		  productSpectrumPage.option.selectOption("规划");
		  
		  productSpectrumPage.text.openTextBox(TextStyle.IDINTR, "configLevel", 1);
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText(TextStyle.TEXTFIELD,"AT-configLevel-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.text.openTextBox(TextStyle.IDINTR, "announcementCode", 1);
		  Thread.sleep(1000);
		  productSpectrumPage.text.inputText(TextStyle.TEXTFIELD,"AT-announcementCode-"+super.bcf.getTimeStamp());
		  
		  productSpectrumPage.button.clickButton("保存");
		  Thread.sleep(2000);
		  
		  Assert.assertEquals(productSpectrumPage.otherElements.isEditFlagDisappeared(ListViewStyle.TREEVIEW), true);
		  
		  		  
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
