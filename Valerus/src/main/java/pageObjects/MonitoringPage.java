package pageObjects;

import Utilities.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;


public class MonitoringPage extends Page{

    @FindBy(xpath = "//div[@id='vms-layout-tabs-options']/span[@class='tab-btn'][1]")
    public WebElement changeLayoutButton;

    @FindBy(xpath = "//div[@class='vms-search-with-btn-out']/div[@class='vms-search-with-btn']/form/input")
    public WebElement filterField;

    @FindBy(xpath = "//div[@id='vms-layout-tabs-options']/span[@ng-click='export()']")
    public WebElement exportButton;

    @FindBy(xpath = "//div[@id='resourceContextmenuList']//div[contains(text(), 'Export')]")
    public WebElement exportButtonInContextMenu;

    @FindBy(xpath = "//div[@id='resourceContextmenuList']//div[contains(text(), 'Configuration')]")
    public WebElement configurationButtonInContextMenu;

    @FindBy(xpath = "//div[@id='resourceContextmenuList']//div[contains(text(), 'Thumbnail Search')]")
    public WebElement thumbnailSearchButtonInContextMenu;

    @FindBy(xpath = "//dd[contains(@class,'vms-resource-video-source') or contains(@class, 'vms-resource-mixed-video-source') or contains(@class, 'vms-resource-ptz-video-source')]/span[contains(text(), 'Camera')]")
    List<WebElement> camerasListLeftPanel;

    @FindBy(xpath = "//dd[contains(@class,'vms-resource-ptz-video-source')]/span[contains(text(), 'Camera')]")
    List<WebElement> ptzCamerasList;

    @FindBy(xpath = "//dd[contains(@class,'vms-tree-enabled')]/span[@class='vms-treeview-label'][@title!='Removed channels'][@title!='Unassociated']")
    List<WebElement> resourcesList;

    @FindBy(xpath = "//div[@class='vms-layout-view']/div[contains(@class, 'vms-view-empty-pane')]")
    List <WebElement> emptyLayoutViewList;

    @FindBy(xpath = "//div[@class='vms-layout-view']/div[contains(@class, 'vms-resource-type-VideoSource')]")
    List <WebElement> fullLayoutViewList;

    @FindBy(xpath = "//div[@id='vms-resources-groups-treeview']//div[@name='main']/dd[contains(@class, 'vms-resource-group')]")
    List <WebElement> groupsList;

    @FindBy(xpath = "//span[@class='vms-player-resource-title'][contains(text(), 'Camera')]")
    WebElement playerToolsViewList;

    //player tools
    @FindBy(xpath = "//span[@class='vms-player-btns']/span[@title='Thumbnail Search']")
    WebElement thumbnailSearchIcon;

    @FindBy(xpath = "//span[@class='vms-player-btns']/span[@title='Playback mode']")
    WebElement playbackIcon;

    @FindBy(xpath = "//span[@class='vms-player-btns']/span[@title='PTZ']")
    WebElement ptzIcon;

    @FindBy(xpath = "//span[contains(@class,'vms-player-btns')]/span[@title='To close, switch back to live']")
    WebElement closeReturnToLiveIcon;

    @FindBy(xpath = "//span[@class='vms-player-btns']/span[@title='Digital Zoom']")
    WebElement digitalZoomIcon;

    @FindBy(xpath = "//span[@class='vms-player-btns']/span[@title='Configure']")
    WebElement configureIcon;

    @FindBy(xpath = "//span[@class='vms-player-btns']/span[@title='Export']")
    WebElement exportIcon;

    @FindBy(xpath = "//span[@class='vms-player-btns']/span[@title='Unmask']")
    WebElement unmaskIcon;
    
   
    @FindBy(xpath="//*[@id=\"vms-main-content\"]/header/div[1]/div[2]/nav/span[1]/span[2]/a")
    WebElement clickOnMonitoirngbutton;

    private Object[] Servers;
    
    
    
    
    public void safeJavaScriptClick(WebElement element) throws Exception {
		try {
			if (element.isEnabled() && element.isDisplayed()) {
				System.out.println("Clicking on element with using java script click");

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			} else {
				System.out.println("Unable to click on element");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document "+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element was not found in DOM "+ e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to click on element "+ e.getStackTrace());
		}
	}
    
    

    public MonitoringPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean ElementIsExist(String DeviceName){
        return verifyElementIsPresent(FindElementByText(DeviceName));
    }

    public void FilterField(String text) {
        setElementText(filterField, text);
    }
    
    public void clickOnMonitoirngbutton() throws Exception {
    	
    	safeJavaScriptClick(clickOnMonitoirngbutton);
    }

    public boolean ExportButtonIsExist(){
        return verifyElementIsPresent(exportButton);
    }

    public void ContextClickOnResource(int index){
        waitUntilIsLoadedCustomTime(camerasListLeftPanel.get(index));
        WebElement resource = camerasListLeftPanel.get(index);
        Actions action = new Actions(driver).contextClick(resource);
        action.build().perform();
    }

    public boolean ConfigurationButtonInContextMenuIsExist(){
        return verifyElementIsPresent(configurationButtonInContextMenu);
    }

    public void MoveToPlayerToolsByIndex(){
       MoveToElement(playerToolsViewList);
    }

    public boolean PlayerTollsAreExist(){
        boolean flag = false;
        try{
            MoveToElement(playerToolsViewList);
            flag = true;
        }catch (Exception e){
            flag = false;
        }
        return flag;
    }

    public boolean ExportButtonInContextMenuIsExist(){
        return verifyElementIsPresent(exportButtonInContextMenu);
    }

    public boolean SearchButtonInContextMenuIsExist(){
        return verifyElementIsPresent(thumbnailSearchButtonInContextMenu);
    }

    public int GetCamerasListSize(){
        return camerasListLeftPanel.size();
    }

    public String GetCameraNameByIndex(int index){
        return camerasListLeftPanel.get(index).getText();
    }

    public int GetSizeEmptyViews(){
        return emptyLayoutViewList.size();
    }

    public int GetSizeFullViews(){
        return fullLayoutViewList.size();
    }

    public boolean ExportIconInPlayerToolIsExist(){
      return  verifyElementIsPresent(exportIcon);
    }

    public boolean UnMaskIconInPlayerToolIsExist(){
      return  verifyElementIsPresent(unmaskIcon);
    }

    public boolean ConfigurationIconInPlayerToolIsExist(){
      return  verifyElementIsPresent(configureIcon);
    }

    public boolean SearchIconInPlayerToolIsExist(){
        return  verifyElementIsPresent(thumbnailSearchIcon);
    }

    public boolean PlayBackIconInPlayerToolIsExist(){
        return  verifyElementIsPresent(playbackIcon);
    }

    public boolean CloseReturnToLiveIconInPlayerToolIsExist(){
        return  verifyElementIsPresent(closeReturnToLiveIcon);
    }

    public boolean PTZIconInPlayerToolIsExist(){
        return  verifyElementIsPresent(ptzIcon);
    }

    public void DragAndDropCameraToView(String indexView) throws Exception {
        String camera = "div dd[class*=-video-source] span";
        String view= "div.vms-layout-view div:nth-child("+indexView+")";
        DragAndDrop(camera, view);
    }

    public void ClickOnCameraByIndex(int index){
        camerasListLeftPanel.get(index).click();
    }

    public WebElement FindResourceByText(String text){
       WebElement camera = driver.findElement(By.xpath("//dd/span[@class='vms-treeview-label'][contains(text(), '"+text+"')]"));
       return camera;
    }

    public int GetResourcesListSize(){
        return resourcesList.size();
    }

    public String GetResourceNameByIndex(int index){
        return resourcesList.get(index).getText();
    }

    public int GetPTZResourcesListSize(){
        return ptzCamerasList.size();
    }

    public String GetPTZResourceNameByIndex(int index){
        return ptzCamerasList.get(index).getText();
    }

    public int GetGroupsListSize(){
        return groupsList.size();
    }

    public String GetGroupNameByIndex(int index){
        return  groupsList.get(index).getText();
    }

    public boolean VerifyThatGroupIsExist(String name){
        boolean flag = false;
        try{
            WebElement element = driver.findElement(By.xpath("//div[@id='vms-resources-groups-treeview']//div[@name='main']/dd[contains(@class, 'vms-resource-group')]/span[text()='"+name+"']"));
            flag = true;
        }
        catch (Exception e){
            flag = false;
        }
        return flag;
    }

    public boolean VerifyThatDeviceIsExist(String name){
        boolean flag = false;
        try{
            WebElement element = driver.findElement(By.xpath("//div[@id='vms-resources-groups-treeview'][not(@class='vms-resource-playback-video-source vms-tree-enabled vms-treeview-resource')]//div[@name='main']/dd[contains(@class, 'video-source')]/span[text()='"+name+"'][not(contains(@title, 'Removed'))]"));
            flag = true;
        }
        catch (Exception e){
            flag = false;
        }
        return flag;
    }

    public boolean VerifyThatViewIsExist(String name){
        boolean flag = false;
        try{
            WebElement element = driver.findElement(By.xpath("//div[@id='vms-resources-groups-treeview']//div[@name='main']/dd[contains(@class, 'vms-resource-view')]/span[text()='"+name+"']"));
            flag = true;
        }
        catch (Exception e){
            flag = false;
        }
        return flag;
    }

    public boolean VerifyThatTourIsExist(String name){
        boolean flag = false;
        try{
            WebElement element = driver.findElement(By.xpath("//div[@id='vms-resources-groups-treeview']//div[@name='main']/dd[contains(@class, 'vms-resource-tour')]/span[text()='"+name+"']"));
            flag = true;
        }
        catch (Exception e){
            flag = false;
        }
        return flag;
    }

    public boolean VerifyThatWebPageIsExist(String name){
        boolean flag = false;
        try{
            WebElement element = driver.findElement(By.xpath("//div[@id='vms-resources-groups-treeview']//div[@name='main']/dd[contains(@class, 'vms-resource-url')]/span[text()='"+name+"']"));
            flag = true;
        }
        catch (Exception e){
            flag = false;
        }
        return flag;
    }

    public boolean VerifyThatResourceIsExist(String name){
        boolean flag = false;
        try{
            WebElement element = driver.findElement(By.xpath("//div[@id='vms-resources-groups-treeview']//div[@name='main']/dd[@class='flex vms-tree-enabled vms-treeview-resource']/span[text()='"+name+"']"));
            flag = true;
        }
        catch (Exception e){
            flag = false;
        }
        return flag;
    }




}
