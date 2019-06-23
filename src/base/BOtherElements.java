package base;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import common.ChangeOrderCode;
import common.ColumnStyle;
import common.LabelStyle;
import common.ListViewStyle;
import common.TableStyle;

/**
 * cover: row, column
 * @author alans
 *
 */
public class BOtherElements {
	protected List<WebElement> elementList;
	protected Actions action;
	protected WebDriver driver;
	
	public BOtherElements(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
		action=new Actions(this.driver);
	}
	
	/**
	 * click the row by the specific text
	 * @param text
	 */
	public void clickRowByText(ListViewStyle ls, String tdIndex, String text) {
		String xPath="";
		if(ls==ListViewStyle.GRIDVIEW) {
			xPath="//tr[contains(@id, 'gridview')]/td[" + tdIndex + "]/div[contains(@class, 'x-grid-cell-inner')]";
		}
		if(ls==ListViewStyle.TREEVIEW) {
			xPath="//tr[contains(@id, 'treeview')]/td[" + tdIndex + "]/div[contains(@class, 'x-grid-cell-inner')]";
		}
		elementList=driver.findElements(By.xpath(xPath));
		int i;
		for(i=0;i<elementList.size();i++)
			{	
				System.out.println(elementList.get(i).getText());
			    if(elementList.get(i).getText().contains(text)){
			    	elementList.get(i).click();
				    break;
			    }
			}
	}
	
	public void clickRowByText(TableStyle ts, String tableId, String tdIndex, String text) {
		String xPath="";
		if(ts==TableStyle.GRIDVIEW) {
			xPath="//table[contains(@id, 'gridview') and contains(@id, '" + tableId + "')]/tbody/tr/td[" + tdIndex + "]";
		}
		elementList=driver.findElements(By.xpath(xPath));
		int i;
		for(i=0;i<elementList.size();i++)
			{	
				System.out.println(elementList.get(i).getText());
			    if(elementList.get(i).getText().contains(text)){
			    	elementList.get(i).click();
				    break;
			    }
			}
	}
	
	/**
	 * cover: change order page, pending task page
	 * get the column Id to for further further element xPath definition
	 * @param PageName 
	 * PageName.APPROVALPAGE - change order page
	 * PageName.MAINDATASECTION - VPPD page
	 * @param node
	 * @return column ID
	 */
	public String getColumnId(ColumnStyle cs, String node)
	{
		String temp="";
		String xPath="";
		String searchString="";
		if(cs==ColumnStyle.GANTLINKCOLUMN)
			searchString="gantlinkcolumn-";
		else if(cs==ColumnStyle.GRIDCOLUMN)
			searchString="gridcolumn-";
	
		xPath="//span[contains(@id, '" + searchString + "') and contains(@class, 'column-header')]";
		elementList=this.driver.findElements(By.xpath(xPath));
		int i;
		for(i=0;i<elementList.size();i++){	
			if(elementList.get(i).getText().equalsIgnoreCase(node)){
				temp=String.valueOf(elementList.get(i).getAttribute("id"));
				int start=temp.indexOf(searchString)+searchString.length();
				int end=temp.length()-"textEl".length()-1;
				temp=temp.substring(start, end);
				break;
			}			  	
		}
		return temp;
	}
	

	/**
	 * get the latest change order number
	 * @param codeStyle: some page need to use 'changeCode' as element recognition, some uses 'gantlinkcolumn'
	 * the value can be 'ECO', 'VCO'...
	 * @return change order number
	 */
	public String getLatestChangeOrder(ChangeOrderCode coc) {
		String xPath;
		if(coc==ChangeOrderCode.VCO) {
			xPath="//a[contains(@class, 'linkcolumn') and contains(@onclick, 'gantlinkcolumn')]";
		}
		else {
			xPath="//a[contains(@class, 'linkcolumn') and contains(@onclick, 'changeCode')]";
		}
		elementList=driver.findElements(By.xpath(xPath));
		String result="";
		if(elementList.size()>0) {
			result=elementList.get(0).getText();
		}
		return result;
	}
	
	/**
	 * cover: product spectrum page, VPPD page
	 * judge if there is still the editing flag "+", if yes, not saving successfully, otherwise, saving successfully
	 * @return true=non-existing, false=existing
	 */
	public boolean isEditFlagDisappeared(ListViewStyle lvs) {
		boolean result=false;
		String xPath;
		if(lvs==ListViewStyle.TREEVIEW)
			xPath="//*[contains(@id, 'treeview') and contains(@id, 'record')]/td[2]/div/font";
		else
			xPath="//*[contains(@id, 'gridview') and contains(@id, 'record')]/td[2]/div/font";
		try {
			this.driver.findElement(By.xpath(xPath));
		}
		catch(Exception e) {
			result=true;
		}
		return result;
	}
	
	/**
	 * cover: eBom page
	 * get the label ID per label name
	 * @param LabelStyle ls
	 * COMBO: the label xPath contains "combo-"
	 * TEXTFIELD: the label xPath contains "textfield-"
	 * GANTCODETYPECOMBOBOX: the label xPath contains "gantcodetypecombobox-"
	 * @param labelName
	 * @return label ID
	 */
	public String getLabelId(LabelStyle ls, String labelName) {
		String Id="";
		String temp;
		String xPath="";
		String searchString="";
		if(ls==LabelStyle.COMBO) 
			searchString="combo-";
		else if(ls==LabelStyle.TEXTFIELD)
			searchString="textfield-";
		else if(ls==LabelStyle.GANTCODETYPECOMBOBOX)
			searchString="gantcodetypecombobox-";
		else if(ls==LabelStyle.GANTCOMBOBOX)
			searchString="gantcombobox-";
		
		xPath="//label[contains(@id, '" + searchString + "')]";
		
		elementList=this.driver.findElements(By.xpath(xPath));
		
				for(int i=0;i<elementList.size();i++)
						  {
						  	if(elementList.get(i).getText().contains(labelName))
						  	{
								temp=String.valueOf(elementList.get(i).getAttribute("id"));
								int start=temp.indexOf(searchString)+searchString.length();
								int end=temp.indexOf("-labelEl");
								Id=temp.substring(start, end);
						  		break;
						  	}
						  }
		return Id;
	}
	
	/**
	 * when there are multiple labels with the same name, use this method to get the label ID
	 * @param ls: label style to adapt different label elements 
	 * @param labelName
	 * @param index: there are multiple labels with the same name, use index to indicate which one should be returned
	 * @return the id of label element
	 */
	public String getLabelId(LabelStyle ls, String labelName, int index) {
		String Id="";
		String temp;
		String xPath="";
		String searchString="";
		List<String> IdList=new ArrayList<String>();
		if(ls==LabelStyle.COMBO) 
			searchString="combo-";
		else if(ls==LabelStyle.TEXTFIELD)
			searchString="textfield-";
		else if(ls==LabelStyle.GANTCODETYPECOMBOBOX)
			searchString="gantcodetypecombobox-";
		else if(ls==LabelStyle.GANTCOMBOBOX)
			searchString="gantcombobox-";
		
		xPath="//label[contains(@id, '" + searchString + "')]";
		
		elementList=this.driver.findElements(By.xpath(xPath));
		
		for(int i=0;i<elementList.size();i++)
		{
		  	if(elementList.get(i).getText().contains(labelName))
			  	{
					temp=String.valueOf(elementList.get(i).getAttribute("id"));
					int start=temp.indexOf(searchString)+searchString.length();
					int end=temp.indexOf("-labelEl");
					IdList.add(temp.substring(start, end));
			  	}
		}
		return IdList.get(index);
	}
	
	/**
	 * get the table ID
	 * @param index: when there are several tables, index is the key to decide which one should be returned
	 * @return Id
	 */
	public String getTableId(TableStyle ts, int index) {
		String Id="";
		String temp;
		String xPath="";
		String searchString="";
		
		if(ts==TableStyle.GRIDVIEW) {
			searchString="gridview-";
			xPath="//table[contains(@id, '" + searchString + "')]";
			elementList=this.driver.findElements(By.xpath(xPath));
			temp=String.valueOf(elementList.get(index).getAttribute("id"));
			int start=temp.indexOf(searchString)+searchString.length();
			int end=temp.indexOf("-table");
			Id=temp.substring(start, end);
		}
		else if(ts==TableStyle.GANGTRIGGERFIELD) {
			searchString="ganttriggerfield-";
			xPath="//table[contains(@id, '" + searchString + "') and contains(@id, 'triggerWrap')]";
			elementList=this.driver.findElements(By.xpath(xPath));
			temp=String.valueOf(elementList.get(index).getAttribute("id"));
			int start=temp.indexOf(searchString)+searchString.length();
			int end=temp.indexOf("-triggerWrap");
			Id=temp.substring(start, end);
		}
		else if(ts==TableStyle.WORKFLOWTASKOWNERTRIGGERFIELD) {
			searchString="workflowTaskOwnerTriggerField-";
			xPath="//table[contains(@id, '" + searchString + "') and contains(@id, 'triggerWrap')]";
			elementList=this.driver.findElements(By.xpath(xPath));
			temp=String.valueOf(elementList.get(index).getAttribute("id"));
			int start=temp.indexOf(searchString)+searchString.length();
			int end=temp.indexOf("-triggerWrap");
			Id=temp.substring(start, end);
		}
		else if(ts==TableStyle.COMBO) {
			searchString="combo-";
			xPath="//table[contains(@id, '" + searchString + "') and contains(@id, 'triggerWrap')]";
			elementList=this.driver.findElements(By.xpath(xPath));
			temp=String.valueOf(elementList.get(index).getAttribute("id"));
			int start=temp.indexOf(searchString)+searchString.length();
			int end=temp.indexOf("-triggerWrap");
			Id=temp.substring(start, end);
		}
		else if(ts==TableStyle.GANTCODETYPECOMBOBOX) {
			searchString="gantcodetypecombobox-";
			xPath="//table[contains(@id, '" + searchString + "') and contains(@id, 'triggerWrap')]";
			elementList=this.driver.findElements(By.xpath(xPath));
			temp=String.valueOf(elementList.get(index).getAttribute("id"));
			int start=temp.indexOf(searchString)+searchString.length();
			int end=temp.indexOf("-triggerWrap");
			Id=temp.substring(start, end);
		}
		else if(ts==TableStyle.USERTRIGGERFIELD) {
			searchString="userTriggerField-";
			xPath="//table[contains(@id, '" + searchString + "') and contains(@id, 'triggerWrap')]";
			elementList=this.driver.findElements(By.xpath(xPath));
			temp=String.valueOf(elementList.get(index).getAttribute("id"));
			int start=temp.indexOf(searchString)+searchString.length();
			int end=temp.indexOf("-triggerWrap");
			Id=temp.substring(start, end);
		}
		
		return Id;
	}
	
	/**
	 * 
	 * @return the approver table Id
	 */
	public String getApproverTableId() {
		String xPath="//span[contains(@id, 'panel') and contains(@id, 'header_hd-textEl')]";
		elementList=this.driver.findElements(By.xpath(xPath));
		String id="";
		int i;
		for(i=0;i<elementList.size();i++)
			{
				if(elementList.get(i).getText().equals("后续任务待办人:")){
					id=elementList.get(i).getAttribute("id");
					break;
				}
			}
		
		xPath="//span[contains(@id, '" + id + "')]/../../../../../following-sibling::div[1]/div/div[2]/div/table";
		WebElement element=this.driver.findElement(By.xpath(xPath));
		return element.getAttribute("id");
	}
	
	/**
	 * 
	 * @return total rows in the table
	 */
	public int getTableRowCount(TableStyle ts, String tableId) {
		int count=0;
		String xPath="";
		if(ts==TableStyle.GRIDVIEW) {
			xPath="//table[contains(@id, '" + tableId + "') and contains(@id, 'gridview')]/tbody/tr";
			elementList=this.driver.findElements(By.xpath(xPath));	
			count=elementList.size();
		}
		
		return count;
	}
	
	public int getTableColCount(TableStyle ts, String tableId) {
		int count=0;
		String xPath="";
		if(ts==TableStyle.GRIDVIEW) {
			xPath="//table[contains(@id, '" + tableId + "') and contains(@id, 'gridview')]/tbody/tr[1]/td";
			elementList=this.driver.findElements(By.xpath(xPath));	
			count=elementList.size();
		}
		
		return count;
	}
	
	public String getWindowTitle() {
		String xPath="//span[contains(@id, 'gantdetailwindow') and contains(@id, 'header') and contains(@id, 'textEl') and contains(@class, 'window-header')]";
		WebElement element=this.driver.findElement(By.xpath(xPath));
		return element.getText();
	}
	
}
