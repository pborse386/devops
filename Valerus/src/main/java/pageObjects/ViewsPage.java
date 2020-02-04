package pageObjects;

import Utilities.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class ViewsPage extends Page {

    @FindBy(xpath = "//*[@id='config-menu-network_entities-views' or @title='Views']")
//    @FindBy(id = "config-menu-network_entities-views")
    WebElement viewsButton;

    @FindBy(xpath = "//div[@class='ui-grid-canvas']/div[contains(@class, 'ui-grid-row ng-scope')]//button[@class='vms-grid-delete-button vicon-font v-trash ng-scope ng-isolate-scope']")
    WebElement deleteIcon;

    @FindBy(xpath = "//div[@class='ui-grid-canvas']/div[contains(@class, 'ui-grid-row ng-scope')]//div[@class='aview-item-in-list ng-binding ng-scope']")
    List<WebElement> viewsList;

    @FindBy(xpath = "//div[@class='vms-pick-layout-dialog ng-scope ng-isolate-scope active']//div[@class='vms-layout-pane-demonstrator ng-scope ui-droppable']")
    List<WebElement> layoutList;

    @FindBy(xpath = "//div[@class='display-resource-layout-config ng-scope ng-isolate-scope active']/div[@class='inner-layout-container  ng-scope']/div[contains(@class, 'vms-display-layout-pane ui-droppable ng-isolate-scope')]")
    List<WebElement> viewLayoutList;
    
    @FindBy(xpath = "//dd[contains(@class,'vms-display-resource-container main') or contains(@class,'vms-view-container monitoring-preview-container') or contains(@class, 'vms-layout-selection-dialog')] ")
    List<WebElement> viewLayoutListm;

    @FindBy(xpath = "//div[@class='display-resource-layout-config ng-scope ng-isolate-scope active']/div/div[@class='vms-display-layout-pane ui-droppable ng-isolate-scope']/div[contains(@class,'view-configuration-container-area')]")
    List <WebElement> fullLayoutViewList;

    @FindBy(xpath = "//div[@class='vms-panel-primary fullSize ng-scope']//div[contains(@class, 'active')]/div[contains(@class,'inner-layout-container')]/div[@class='vms-display-layout-pane ui-droppable ng-isolate-scope']")
    List <WebElement> paneViewList;

    @FindBy(xpath = "//div[@class='big-panel views-display-panel fullSize']//dd[contains(@class , 'vms-tree-enabled')]/span[not(contains(@title, 'Audio Channels'))]/..")
    List<WebElement> resourcesList;

    @FindBy(xpath = "//dd[contains(@class,'vms-tree-enabled')]/span[@class='vms-treeview-label'][@title!='Removed channels'][@title!='Unassociated']")
    List<WebElement> resourcesList1;
    
    
    @FindBy(xpath = "//div[@class='big-panel views-display-panel fullSize']//dd[contains(@class , 'video-source')]")
    List<WebElement> camerasList;

    @FindBy(xpath = "//div[@class='big-panel views-display-panel fullSize']//dd[contains(@class , 'vms-resource-url')]")
    List<WebElement> webPageList;

    @FindBy(xpath = "//div[@class='display-resource-layout-config ng-scope ng-isolate-scope active']/audio-sources-view[@class='ui-droppable audioCont0 ng-scope ng-isolate-scope']/div")
    List<WebElement> audioSourceViewList;

    @FindBy(xpath = "//div[@class='collapsed-panel']//div")
    WebElement availableResourcesButton;

    @FindBy(xpath = "//div[@class='flex flex-between']/button[contains(text(), 'New')]")
    WebElement newButton;

    @FindBy(xpath = "//div[@class='flex flex-between']/button[contains(text(), 'Delete')]")
    WebElement deleteButton;

    @FindBy(xpath = "//div[@class='vms-newui-background']//div[@class='vms-search-with-btn']//input")
    WebElement filterField;

    @FindBy(xpath = "//div[@class='availible-resources-list']//div[@class='vms-search-with-btn-out']//input[@id='vms-monitoring-filter-input']")
    WebElement resourcesFilterField;

    @FindBy(xpath = "//form//div[@class='config-form-fields border-builder']/input")
    WebElement nameField;

    @FindBy(xpath = "//div[@class='config-form-fields border-builder']/textarea")
    WebElement remarksField;

    @FindBy(xpath = "//div[@class='views-fields-wrapper']//md-switch")
    WebElement visibilityToggleSwitch;

    @FindBy(xpath = "//button[@title='Change Layout']")
    WebElement changeLayoutButton;
    
    @FindBy(xpath = "//div[@id='vms-layout-tabs-options']/span[@class='tab-btn'][1]")
    public WebElement changeLayoutButton1;

    @FindBy(xpath = "//button[text()='Clear View']")
    WebElement clearViewButton;
    
    @FindBy(xpath = "//*[@id=\"vms-layout-tabs-options\"]/span[2]/span[2]")
	  
    WebElement stopAll;

    @FindBy(xpath = "//div[@class='vms-panel-body-bottom restoreButtonsFooter pushright']/button[contains(text(), 'Cancel')]")
    WebElement cancelButton;

    @FindBy(xpath = "//div[@class='vms-panel-body-bottom restoreButtonsFooter pushright']/button[contains(text(), 'Save')]")
    WebElement saveButton;

    @FindBy(xpath = "//div[@class='vms-pick-layout-dialog ng-isolate-scope active']//div[contains(@class, 'vms-layout-pane-demonstrator ng-scope ui-droppable')]")
    List<WebElement> layoutListInDialog;
    
    @FindBy(xpath = "//div[@class='vms-display-resource-container main']//div[contains(@class, 'vms-layout-pane-demonstrator')]")
    List<WebElement> layoutListInDialog1;
    
    
    @FindBy(xpath = "//*[@class='vms-layout-tab']")
    WebElement addviewsign;
    
    @FindBy(xpath = "//div[@class='vms-layout-view']/div[contains(@class, 'vms-view-empty-pane')]")
    List <WebElement> emptyLayoutViewList;
    
    
    
            

    public ViewsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void GoToViewsPage() throws InterruptedException {
        Thread.sleep(1000);
        try{GoToConfigurationPage();
            Thread.sleep(1000);

            ClickOnResourcesButton();
            waitUntilResourcesMenuIsExpanded();
            Thread.sleep(1000);
            ClickOnViewsButton();}
        catch(Exception e){
            takeScreenshot(driver, "Setup", "GoToWebPagesPage");
        }
    }

    public void GoToViewsPageFromLanding() throws InterruptedException {
        Thread.sleep(1000);
        try{GoToConfigurationPage();
            Thread.sleep(1000);
            ClickOnViewsButton();}
        catch(Exception e){
            takeScreenshot(driver, "Setup", "GoToViewsPage");
        }
    }

    
    public void ClickOnAddViewSign() throws InterruptedException {
    	
    	  JavaScriptClick(addviewsign);

          Thread.sleep(1000);
    	
    	
    }
    
    
    public void ClickOnViewsButton() throws InterruptedException {
        boolean breakIt = true;
        int time = 0;
        while (true) {
            breakIt = true;
            try {
                JavaScriptClick(viewsButton);
//                viewsButton.click();
                Thread.sleep(1000);

            } catch (Exception e) {
                if (e.getMessage().contains("element not visible")) {
                    if(!ResourcesMenuIsOpen()){
                       ClickOnResourcesButton();
                        waitUntilResourcesMenuIsExpanded();
                    }
                    breakIt = false;
                }
            }
            time++;
            if (breakIt || time>10) {
                break;
            }
        }
        Thread.sleep(1000);
    }

    public void ClickOnNewButton(){
        newButton.click();
    }

    public void InputIntoNameField(String name){
        setElementText(nameField, name);
    }

    public String GetViewName(){
        return nameField.getAttribute("value");
    }

    public void InputIntoRemarksField(String remark){
        setElementText(remarksField, remark);
    }

    public String GetRemarksText(){
        return remarksField.getAttribute("value");
    }

    public void ClickOnVisibilityToggleSwitch(){
    	JavaScriptClick(visibilityToggleSwitch);
    }

    public boolean VisibilityToggleIsOn(){
        return visibilityToggleSwitch.getAttribute("aria-checked").equals("true");
    }

    public void InputIntoViewsFilter(String text) throws InterruptedException {
        setElementText(filterField, text);
        Thread.sleep(1000);
    }

//    public void ClickOnFilterButton(){
//        filterButton.click();
//    }

    public void ClickOnAvailibleResourcesButton(){
    	JavaScriptClick(availableResourcesButton);
    }

    public void ClickOnDeleteButton(){
    	JavaScriptClick(deleteButton);
    }

    public void ClickOnChangeLayoutButton(){
    	JavaScriptClick(changeLayoutButton);
    }
    
   
    
    
    public void ClickOnChangeLayoutButton1() throws InterruptedException{
             
        JavaScriptClick(changeLayoutButton1);
        Thread.sleep(2000);
        
    }

    public void ClickOnClearViewButton() throws InterruptedException {
        WaitUntilClearViewButtonWillBeEnabled();
        Thread.sleep(2000);
        JavaScriptClick(clearViewButton);
//        clearViewButton.click();
//        ClickOnElement(clearViewButton);
    }
    
    public void clickOnStopAllbutton() throws InterruptedException {
    	
   	    Thread.sleep(1000);
   	 
   	    JavaScriptClick(stopAll);
   	    	 
   }
    
    
    
    

    public void WaitUntilClearViewButtonWillBeEnabled(){
        try {
            new WebDriverWait(driver, 30).until(ExpectedConditions.attributeContains(clearViewButton, "aria-disabled", "false"));
        } catch (Exception e){}
    }

    public boolean ClearViewButtonIsEnabled(){
        return clearViewButton.getAttribute("aria-disabled").equals("false");
    }

    public int GetLayoutSize(){ return layoutList.size();}

    public void ClickOnLayoutByIndex(int index){
    	JavaScriptClick(layoutList.get(index));
    }

    public void DragAndDropCameraToView(String deviceID, String viewID) throws Exception {
        String camera = "dd#"+deviceID+" span";
        String view= "div#"+viewID+"";
        DragAndDrop(camera, view);
    }

    public int GetLayoutViewSize(){
        return viewLayoutList.size();
    }
    
    public int GetLayoutViewSize1(){
        return viewLayoutListm.size();
    }

    public String GetViewIDByIndex(int index){
        return viewLayoutList.get(index).getAttribute("id");
    }
    
    public int GetSizeEmptyViews(){
        return emptyLayoutViewList.size();
    }
    
    public String GetViewIDByIndex1(int index){
        return emptyLayoutViewList.get(index).getAttribute("id");
    }
    

    public int GetResourcesSize(){
        return resourcesList.size();
    }
    
    public int GetResourcesSize1(){
        return resourcesList1.size();
    }


    public String GetResourcesIDByIndex(int index){
        return resourcesList.get(index).getAttribute("id");
    }
    
        
    public String GetResourcesIDByIndex1(int index){
        return resourcesList1.get(index).getAttribute("id");
    }

    public String GetResourcesNameByIndex(int index){
        return resourcesList.get(index).getText();
    }
    public String GetResourcesNameByIndex1(int index){
        return resourcesList1.get(index).getText();
    }

    public int GetCamerasSize(){
        return camerasList.size();
    }

    public int GetWebPageSize(){
        return webPageList.size();
    }

    public String GetCameraIDByIndex(int index){
        return camerasList.get(index).getAttribute("id");
    }

    public String GetWebPageIDByIndex(int index){
        return webPageList.get(index).getAttribute("id");
    }

    public String GetCameraNameByIndex(int index){
        return camerasList.get(index).getText();
    }

    public String GetWebPageNameByIndex(int index){
        return webPageList.get(index).getText();
    }

    public int GetFullViewsSize(){
        return  fullLayoutViewList.size();
    }

    public int GetAudioViewsSize(){
        return audioSourceViewList.size();
    }

    public void PressSaveButton() throws InterruptedException {
        waitUntilElementIsClickable(saveButton);
        JavaScriptClick(saveButton);
        new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='fade-in-panel']")));
    }

    public void PressCancelButton() throws InterruptedException {
        waitUntilElementIsClickable(cancelButton);
        JavaScriptClick(cancelButton);
    }

    public void ClickOnViewByName(String name){
        WebElement view = driver.findElement(By.xpath("//div[@class='ui-grid-canvas']//div[@title='"+name+"']"));
        JavaScriptClick(view);
    }

    public void WaitUntilViewPageIsLoaded(){
        waitUntilElementIsLoaded(changeLayoutButton);
    }

    public int GetViewsSize(){
        return viewsList.size();
    }

    public void ClickOnViewByIndex(int index) throws InterruptedException {
    	JavaScriptClick(viewsList.get(index));
        Thread.sleep(1000);
    }

    public String GetViewNameByIndex(int index){
        return viewsList.get(index).getText();
    }

    public boolean SaveButtonIsEnabled(){
        return saveButton.isEnabled();
    }

    public boolean CancelButtonIsEnabled(){
        return  cancelButton.isEnabled();
    }

    public void InputIntoResourcesFilterField(String name){
        setElementText(resourcesFilterField, name);
    }

    public int GetSizeLayoutInDialog(){
        return layoutListInDialog.size();
    }
    
    public int GetSizeLayoutInDialog1(){
        return layoutListInDialog.size();
    }

    public void ClickOnLayoutInDialogByIndex(int index) throws InterruptedException {
//        driver.switchTo().window(driver.getWindowHandle());
        Thread.sleep(1000);
//        element.click();
        MoveToElement(layoutListInDialog.get(index));
//        Actions actions = new Actions(driver);
//        actions.moveToElement(element);
//        actions.click();
//        actions.build().perform();

//        MoveToElement(element);
//        element.sendKeys(Keys.ENTER);
//        layoutListInDialog.get(index).click();
 //       layoutListInDialog.get(index).sendKeys(Keys.ENTER);
//        ClickOnElement(layoutListInDialog.get(index));
        JavaScriptClick(layoutListInDialog.get(index));
    }

    public void ClickOnLayoutInDialogByIndex1(int index) throws InterruptedException {
//      driver.switchTo().window(driver.getWindowHandle());
      Thread.sleep(1000);
//      element.click();
      MoveToElement(layoutListInDialog1.get(index));
//      Actions actions = new Actions(driver);
//      actions.moveToElement(element);
//      actions.click();
//      actions.build().perform();

//      MoveToElement(element);
//      element.sendKeys(Keys.ENTER);
//      layoutListInDialog.get(index).click();
 //     layoutListInDialog1.get(index).sendKeys(Keys.ENTER);
//      ClickOnElement(layoutListInDialog.get(index));
   JavaScriptClick(layoutListInDialog1.get(index));
  }

    
    
    
    
    
    public int GetPanesSizeInLayoutInDialogByIndex(int index){
        int row=0;
        int item = 0;
        if(index>=1 && index<=9){
            item = index;
            row=1;
        }
        if(index>=10 && index<=12){
            item = index-9;
            row=2;
        }
        if(index>=13 && index<=15){
            item = index-12;
            row=3;
        }
        if(index>=16 && index<=20){
            item = index-15;
            row=4;
        }

        List<WebElement> panes = driver.findElements(By.xpath("//div[@class='vms-pick-layout-dialog ng-isolate-scope active']/div[@class='vms-layout-group-row ng-scope']["+row+"]/div[contains(@class, 'vms-layout-pane-demonstrator ng-scope ui-droppable')]["+item+"]/div[@class='vms-layout-pane-demonstrator-item-container']/div"));
        return panes.size();
    }

    public int GetPaneViewListSize(){
        return paneViewList.size();
    }

    public void ClickOnDeleteIcon(){
        JavaScriptClick(deleteIcon);
    }

    public void WaitUntilSaveButtonWillBeEnable(){
        try{
            new WebDriverWait(driver, 5).until(ExpectedConditions.attributeContains(saveButton, "aria-disabled", "false"));
        }catch (Exception e){}
    }
}
