package test;

import org.testng.annotations.Test;

import base.BTest;
import common.DropDownListStyle;
import common.EnvJsonFile;
import common.LabelStyle;
import common.TableStyle;
import common.TextStyle;
import page.MainPage;
import page.ProjectMemberPage;

import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class AddProjectMember extends BTest{
  @Test
  public void addProjectMember() {
	  try {
		//start BOM
		  super.StartBOM(EnvJsonFile.BASICFILE, "integration");
		  Thread.sleep(10000);
		  
		  		
		  //login BOM
		  String username=super.LoginBOM();
		  Thread.sleep(10000);
		  
		  //open project member window
		  MainPage mainPage=new MainPage(super.driver);
		  mainPage.mainMenu.hoverMenu("�������ݹ���");
		  Thread.sleep(2000);
		  mainPage.mainMenu.clickMenu("��Ŀ��Ա����");
		  Thread.sleep(2000);
		  
		  ProjectMemberPage projectMemberPage=new ProjectMemberPage(super.driver);
		  
		  
		  //select vehicle mode code
		
		  String labelId=projectMemberPage.otherElements.getLabelId(LabelStyle.COMBO,"�����ͺ�");
		  super.bcf.readJasonFile(EnvJsonFile.TESTDATA);
		  String prjectCode=super.bcf.getProperty("ProjectCode");
		  projectMemberPage.option.expandDropdownList(DropDownListStyle.COMBO,labelId);
		  Thread.sleep(2000);
		  projectMemberPage.option.selectOption(prjectCode);
		  Thread.sleep(1000);
		  projectMemberPage.button.clickButton("��ѯ");
		  Thread.sleep(1000);
		  
		  //add a member
		  projectMemberPage.button.clickButton("����༭");
		  Thread.sleep(1000);
		  
		  this.addMemberByRole(projectMemberPage, "��Ʒ����ʦ", username);
		  this.addMemberByRole(projectMemberPage, "���ù���ʦ", username);
		  
	  }catch(Exception e) {
		  e.printStackTrace();
		  Assert.assertEquals(false, true);
	  }
	  
  }
  
  private void addMemberByRole(ProjectMemberPage projectMemberPage, String role, String username) throws Exception {
	  
	  try {
		  projectMemberPage.button.clickButton("���");
		  Thread.sleep(1000);
		  
		  String projectMemberTableId;
		  
		  //set the role as "product engineer" for the new member
		  projectMemberTableId=projectMemberPage.otherElements.getTableId(TableStyle.GRIDVIEW, 0);
		  //select the base belong to
		  projectMemberPage.text.openTextBox(projectMemberTableId, 1, 4);
		  Thread.sleep(1000);
		  projectMemberPage.option.expandDropdownList();
		  Thread.sleep(1000);
		  projectMemberPage.text.inputText("roleCode",1,role);
		  Thread.sleep(1000);
		  projectMemberPage.option.selectOption(role);
		  Thread.sleep(2000);
		  
		  //select the user
		  String PopUpTableId;
		  String MagnifyingGlassTableId;
		  
		  projectMemberPage.text.openTextBox(projectMemberTableId, 1, 7);
		  Thread.sleep(1000);
		  MagnifyingGlassTableId=projectMemberPage.otherElements.getTableId(TableStyle.USERTRIGGERFIELD, 1);
		  projectMemberPage.button.clickMagnifyingGlass(TableStyle.USERTRIGGERFIELD, MagnifyingGlassTableId,1,2);
		  Thread.sleep(1000);
		  String Id;
		  Id=projectMemberPage.otherElements.getLabelId(LabelStyle.TEXTFIELD, "��Ա����");
		  projectMemberPage.text.openTextBox(TextStyle.IDININPUT, Id, 1);
		  projectMemberPage.text.inputText(TextStyle.TEXTFIELD,username);
		  Thread.sleep(1000);
		  projectMemberPage.button.clickButton("��ѯ",1);
		  Thread.sleep(1000);
		  PopUpTableId=projectMemberPage.otherElements.getTableId(TableStyle.GRIDVIEW,1);
		  projectMemberPage.option.clickCheckBox(PopUpTableId, 1,1);
		  Thread.sleep(1000);
		  projectMemberPage.button.clickButton("ȷ��");
		  Thread.sleep(1000);
		  
		  //save the project member
		  projectMemberPage.button.clickButton("����");
		  Thread.sleep(1000);
	  }catch(Exception e) {
		  e.printStackTrace();
		  throw e;
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
