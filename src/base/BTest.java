package base;



import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import common.BCommonFunction;
import common.EnvJsonFile;
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
				  BCommonFunction bcf=new BCommonFunction();
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
				  BCommonFunction bcf=new BCommonFunction();
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
	
	public void close()
	{
		this.driver.quit();
	}
	
	
}

