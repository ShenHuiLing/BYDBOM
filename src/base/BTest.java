package base;



import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import common.BCommonFunction;
import common.ColumnStyle;
import common.EnvJsonFile;
import common.LabelStyle;
import common.ListViewStyle;
import common.TableStyle;
import common.TextStyle;
import page.ApprovalPage;
import page.LoginPage;




public class BTest {
	protected WebDriver driver;
	protected BCommonFunction bcf;
	
	public BTest() {
		this.bcf=new BCommonFunction();
	}
	
	public void StartBOM(EnvJsonFile enj, String Env) {
		bcf.readJasonFile(enj);
		String envURL=bcf.getProperty(Env);
		String driverPath=bcf.getProperty("chormedriver");
		
		
		try {
			  ChromeDriverService service = new ChromeDriverService.Builder()
					  .usingDriverExecutable(new File(driverPath))
					  .usingAnyFreePort()
					  .build();
			  service.start();
			  
			  driver=new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
			  
			  driver.get(envURL);
			  driver.manage().window().maximize();
			  driver.manage().window().fullscreen();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void LoginBOM() {
		 try {
				  LoginPage lPage=new LoginPage(this.driver);
				  bcf.readJasonFile(EnvJsonFile.TESTFILE);
				  String username=bcf.getProperty("username");
				  String pwd=bcf.getProperty("pwd");
				  lPage.InputUserName(username);
				  lPage.InputPwd(pwd);
				  Thread.sleep(1000);
				  lPage.clickLoginButton();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void LoginBOMAsApprover() {
		 try {
				  LoginPage lPage=new LoginPage(this.driver);
				  bcf.readJasonFile(EnvJsonFile.TESTFILE);
				  String username=bcf.getProperty("approver");
				  String pwd=bcf.getProperty("approverpwd");
				  lPage.InputUserName(username);
				  lPage.InputPwd(pwd);
				  Thread.sleep(1000);
				  lPage.clickLoginButton();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * select approver process
	 * @param: approver
	 * @param: tableId, this is the table Id for approver list
	 */
	public void selectApprover(String approver, String tableId) {
		try {
			  ApprovalPage approvalPage=new ApprovalPage(this.driver);
			  //judge if there are candidate approvers to be selected, if column count greater than 2 then continue to select approver
			  int approverColCount=approvalPage.otherElements.getTableColCount(TableStyle.GRIDVIEW, tableId);
			  if(approverColCount>2) {
				  int approverCount=approvalPage.otherElements.getTableRowCount(TableStyle.GRIDVIEW, tableId);
				  System.out.println("approverCount:" + approverCount);
				  
				  for (int i=0;i<approverCount;i++) {
					  //check if there is already approver as expected
					  String tempApprover=approvalPage.text.getValueFromTextBox(TableStyle.GRIDVIEW, tableId, i+1, 3);
					  System.out.println("Candidated Approver:" + tempApprover);
					  
					  //if the existing approver is not matching with the approver data, need to re-choose approver
					  //if the node is an optional approver, will skip
					  if(!tempApprover.equals(approver) && !tempApprover.contains("请决定此节点是否参与审批")) {
						  approvalPage.text.openTextBox(tableId, i+1, 3);
						  Thread.sleep(1000);
						  
						  //remove all existing approver
						  approvalPage.option.checkAllSelectedApprover();
						  approvalPage.button.clickButton("<<");
						  Thread.sleep(1000);
						  
						  //input login name in query box
						  approvalPage.text.inputText("userLoginName",approver);
						  Thread.sleep(1000);
						  //switch the query range to "all"
						  String labelId=approvalPage.otherElements.getLabelId(LabelStyle.COMBO, "审批人员范围");
						  approvalPage.option.expandDropdownList(labelId);
						  approvalPage.option.selectOption("全部");
						  //click query button
						  approvalPage.button.clickQueryApproverButton();
						  Thread.sleep(1000);
						 
						  approvalPage.option.clickCheckBoxOption(approver);
						  Thread.sleep(1000);
						  
						  approvalPage.button.clickButton(">>");
						  Thread.sleep(1000);
						  approvalPage.button.clickButton("确定");
						  Thread.sleep(1000);
					  }
				  }
				
			  }
			  
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * start approveal process, including switch to approval tab, select approver and start approval
	 */
	public void startApprovalProcess() {
		try {
				ApprovalPage approvalPage=new ApprovalPage(this.driver);
				//click the approval tab
				approvalPage.tab.clickTab("流程审批");
				Thread.sleep(2000);
				
				//get the approver from the test file and pass this parameter to selectApprover function
				bcf.readJasonFile(EnvJsonFile.TESTFILE);
				String approver=bcf.getProperty("approver");
				
				//get the approval table ID and pass this parameter to selectApprover function
				String tableId=approvalPage.otherElements.getTableId(TableStyle.GRIDVIEW, 2);
				
				this.selectApprover(approver, tableId);
				
				approvalPage.button.clickButton("启动审批流程");
				Thread.sleep(5000);
				
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * approve process, including switch to approval tab, check the approval check box
	 * re-select all following approvers and click approval confirmation button
	 */
	public void approveProcess() throws Exception {
		try {
				ApprovalPage approvalPage=new ApprovalPage(this.driver);
				
				//click the approval tab
				approvalPage.tab.clickTab("流程审批");
				Thread.sleep(2000);
				
				//check the approval check box
				approvalPage.option.selectApprovalOption();
				//get the approver from the test file and pass this parameter to selectApprover function
				bcf.readJasonFile(EnvJsonFile.TESTFILE);
				String approver=bcf.getProperty("approver");
			
				//get the approval table ID and pass this parameter to selectApprover function
				String tableId=approvalPage.otherElements.getApproverTableId();
				selectApprover(approver, tableId);
				
				//click the approve button
				approvalPage.button.clickButton("执行操作");
				Thread.sleep(1000);
				approvalPage.button.clickButton("是");
				Thread.sleep(3000);
				
		}catch (Exception e){
			throw e;
		}
		  
	}
	
	public void close()
	{
		this.driver.quit();
	}
	
	
}

