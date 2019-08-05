package test;

import org.testng.annotations.Test;

import base.BTest;
import common.EnvJsonFile;
import common.TableStyle;
import page.MainPage;
import page.PlanningConfigurationPage;

import org.testng.annotations.BeforeTest;

import java.io.IOException;

import org.testng.annotations.AfterTest;

/**
 * 1. add a new planning configuration car
 * 2. select basic care type and configuration car type
 * 3. save
 * @author alans
 *
 */
public class PlanningConfigurationCarMaintain extends BTest{
  @Test
  public void AddNewPlanningConfigurationCar() throws IOException{
	  try {
		  //start BOM
		  super.StartBOM(EnvJsonFile.BASICFILE, "local");
		  Thread.sleep(10000);
		  //login BOM
		  super.LoginBOM();
		  Thread.sleep(10000);
		  
		  //open planning configuration car window
		  logger.info("open planning configuration management window");
		  MainPage mainPage=new MainPage(super.driver);
		  mainPage.mainMenu.hoverMenu("���ù���");
		  Thread.sleep(2000);
		  mainPage.mainMenu.hoverMenu("�滮����");
		  Thread.sleep(2000);
		  mainPage.mainMenu.clickMenu("�滮���ó���");
		  Thread.sleep(5000);
		  
		  PlanningConfigurationPage planConfigPage=new PlanningConfigurationPage(super.driver);
		  
		  //start editing
		  logger.info("start editing");
		  planConfigPage.button.clickButton("����༭");
		  Thread.sleep(1000);
		  
		  planConfigPage.button.clickButton("���");
		  Thread.sleep(1000);
		  
		  String projectCode;
		  super.bcf.readJasonFile(EnvJsonFile.TESTDATA);
		  projectCode=super.bcf.getProperty("ProjectCode");
		  projectCode="AT-CODE-1562561870";
		  
		  String tableId;
		  tableId=planConfigPage.otherElements.getTableId(TableStyle.GRIDVIEW, 0);
		  System.out.println(tableId);
		  
		  planConfigPage.text.openTextBox(tableId, 1, 4);
		  Thread.sleep(1000);
		  planConfigPage.option.expandDropdownList();
		  Thread.sleep(1000);
		  planConfigPage.option.selectOption(projectCode);
		  Thread.sleep(1000);
		  
		  planConfigPage.text.openTextBox(tableId, 1, 5);
		  Thread.sleep(1000);
		  planConfigPage.text.inputText("planVehicleName", 1, "PVN_"+bcf.getTimeStamp());
		  Thread.sleep(1000);
		  
		  String Id;
		  //Id=planConfigPage.otherElements.getTableId(TableStyle.GANTCODETYPECOMBOBOX, 0);
		  
	  }catch(Exception e) {
		  e.printStackTrace();
	  }
	  
  }
  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

}
