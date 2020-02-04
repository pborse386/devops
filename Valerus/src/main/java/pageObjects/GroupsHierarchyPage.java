package pageObjects;

import Utilities.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class GroupsHierarchyPage extends Page {
    @FindBy(id = "config-menu-network_entities-hierarchy_groups")
    WebElement groupsHierarchyButton;

    @FindBy(xpath = "//span[@class='item-text ng-binding'][@title='Groups Hierarchy']")
    WebElement groupsHierarchyButtonLandingPage;

    @FindBy(xpath = "//div[@class='row no-margin fullSize']//div[@class='vms-search-with-btn']//input")
    WebElement filterField;

    @FindBy(xpath = "//div[@class='panel-body']//dl/div[@name='main']/dd[contains(@class, 'vms-treeview-resource')]")
    List<WebElement> allResourceList;

    @FindBy(xpath = "//div[@class='panel-body']//dl/div[@name='main']/dd")
    List<WebElement> resourceList;

    @FindBy(xpath = "//div[@class='hierarchy-panel-buttons']//button[contains(text(), 'Add Group')]")
    WebElement addGroupButton;

    @FindBy(xpath = "//div[@class='hierarchy-panel-buttons']//button[contains(text(), 'Collapse All')]")
    WebElement collapseAllButton;

    @FindBy(xpath = "//div[@class='hierarchy-panel-buttons']//button[contains(text(), 'Expand All')]")
    WebElement expandAllButton;

    @FindBy(xpath = "//div[@class='hierarchy-panel-buttons']//button[contains(text(), 'Delete Selected')]")
    WebElement deleteSelectedButton;

    @FindBy(xpath = "//div[@class='vms-treeview-edit-buttons-container']//i[@title='Add Sub Group']")
    WebElement addSubGroupIcon;

    @FindBy(xpath = "//div[@class='vms-treeview-edit-buttons-container']//i[@title='Delete']")
    WebElement deleteIcon;

    @FindBy(xpath = "//div[@class='vms-treeview-edit-buttons-container']//i [@title='Rename']")
    WebElement renameIcon;

    @FindBy(xpath = "//div[contains(@class, 'vms-treeview-edit-buttons-container')]//i[@title='Cancel']")
    WebElement cancelRenameIcon;

    @FindBy(xpath = "//div[contains(@class, 'vms-treeview-edit-buttons-container')]//i[@title='Commit']")
    WebElement commitRenameIcon;

    @FindBy(xpath = "//dd//input[@class='vms-treeview-node-edit']")
    WebElement groupNameField;

    @FindBy(xpath = "//div[@id='tree-hierarchy-container']//dl/div[@name='main']/dd[contains(@class, 'vms-resource-group')]")
    List<WebElement> allResourceGroupList;

    @FindBy(xpath = "//div[@id='resizerContainer']//div[@class='vms-resource-group-view']//dl/div[@name='main']/dd[contains(@id, 'vms-treeview')][@style='padding-left: 2%;']")
    List<WebElement> ResourceGroupParentsList;

    @FindBy(xpath = "//div[@id='resizerContainer']//div[@class='vms-resource-group-view']//dl/div[@name='main']/dd[contains(@class, 'vms-resource-group')]")
    List<WebElement> groupsList;

    @FindBy(xpath = "//div[@id='resizerContainer']//div[@class='vms-resource-group-view']//dl/div[@name='main']/dd[contains(@class, 'vms-treeview-resource')]/span")
    List<WebElement> resourcesInGroupsList;

    @FindBy(xpath = "//div[@class ='vms-big-btn-footer bottom-separator-builder new-ui-panel-background']/button[contains(text(), 'Cancel')]")
    WebElement cancelButton;

    @FindBy(xpath = "//div[@class = 'vms-big-btn-footer bottom-separator-builder new-ui-panel-background']/button[contains(text(), 'Save')]")
    WebElement saveButton;

    public GroupsHierarchyPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void GoToGroupsHierarchyPage() throws InterruptedException {
        Thread.sleep(1000);
        try{GoToConfigurationPage();
            Thread.sleep(1000);

            ClickOnResourcesButton();
            waitUntilResourcesMenuIsExpanded();
            Thread.sleep(1000);
            ClickOnGroupsHierarchyButton();}
        catch(Exception e){
            takeScreenshot(driver, "Setup", "GoToGroupsHierarchyPage");
        }
    }

    public void GoToGroupsHierarchyPageFromLandingPage() throws InterruptedException {
        Thread.sleep(1000);
        GoToConfigurationPage();
        Thread.sleep(1000);
        ClickOnGroupsHierarchyButtonOnLandingPage();
    }

    public void ClickOnGroupsHierarchyButton() throws InterruptedException {
        boolean breakIt = true;
        int time = 0;
        while (true) {
            breakIt = true;
            try {
            	Thread.sleep(500);
            	JavaScriptClick(groupsHierarchyButton);
                Thread.sleep(500);

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
    
    
    public void ClickOnGroupsHierarchyButton1() throws InterruptedException {
    	
    	Thread.sleep(500);
    	JavaScriptClick(groupsHierarchyButton);
    	Thread.sleep(500);
    }
    
    

    public void ClickOnGroupsHierarchyButtonOnLandingPage() throws InterruptedException {
       waitUntilElementIsLoaded(groupsHierarchyButtonLandingPage);
       Thread.sleep(1000);
       JavaScriptClick(groupsHierarchyButtonLandingPage);
    }

    public void ClickOnAddGroupButton(){
    	JavaScriptClick(addGroupButton);
        waitUntilSaveIsEnabled();
    }

    public void ClickOnCollapseAllButton(){
        collapseAllButton.click();
    }

    public void ClickOnExpandAllButton(){
        JavaScriptClick(expandAllButton);
    }

    public void ClickOnDeleteSelectedButton(){
        try{
            new WebDriverWait(driver, 5).until(ExpectedConditions.attributeContains(deleteSelectedButton, "aria-disabled", "false"));
        }catch (Exception e){}
        JavaScriptClick(deleteSelectedButton);
    }

    public void ClickOnAddSubGroupIcon(){
    	JavaScriptClick(addSubGroupIcon);
    }

    public void ClickOnDeleteIcon(){
    	JavaScriptClick(deleteIcon);
    }

    public void ClickOnRenameIcon(){
    	JavaScriptClick(renameIcon);
    }

    public void ClickOnCancelrenamingIcon(){
    	JavaScriptClick(cancelRenameIcon);
    }

    public void ClickOnCommitRenamingButton(){
    	JavaScriptClick(commitRenameIcon);
    }

    public void InputIntoFilterField(String text) throws InterruptedException {
        waitUntilElementIsLoaded(filterField);
        setElementText(filterField, text);
        Thread.sleep(500);
    }

    public int GetResourcesSize(){
        return allResourceList.size();
    }

    public int GetResourcesInAvailableResourcesSize(){
        return resourceList.size();
    }

    public String GetResourceTextBYIndex(int index){
        return allResourceList.get(index).getText();
    }

    public String GetResourceIDByIndex(int index){
        return allResourceList.get(index).getAttribute("id");
    }

    public String GetResourceOrGroupIDByIndex(int index){
        return allResourceGroupList.get(index).getAttribute("id");
    }


    public String GetResourceTextInAvailableResourcesBYIndex(int index){
        return resourceList.get(index).getText();
    }


    public int GetResourceGroupsSize(){
        return allResourceGroupList.size();
    }

    public String GetResourceGroupTextBYIndex(int index){
        return allResourceGroupList.get(index).getText();
    }

    public void PressSaveButton() throws InterruptedException {
        WaitUntilDialogIsNotLocated();
        saveButton.click();
        try{
            new WebDriverWait(driver, 1).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='fade-in-panel']")));
            new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='fade-in-panel']")));
        }catch (Exception e){}
        Thread.sleep(1000);
    }

    public void PressCancelButton() throws InterruptedException {
        Thread.sleep(1000);
        waitUntilElementIsClickable(cancelButton);
        cancelButton.click();
        Thread.sleep(1000);
    }

    public void MoveToGroupResourceByIndex(int index){
        MoveToElement(allResourceGroupList.get(index));
    }

    public void ClickToResourceInGroupByIndex(int index){
        resourcesInGroupsList.get(index).click();
    }

    public void ClicknRenameIcon(){
        renameIcon.click();
    }

    public void InputIntoGroupField(String text){
        setElementText(groupNameField, text);
    }

    public void waitUntilSaveIsEnabled(){
        try{
            new WebDriverWait(driver, 30).until(ExpectedConditions.attributeContains(By.xpath("//div[@class = 'vms-big-btn-footer bottom-separator-builder new-ui-panel-background']/button[contains(text(), 'Save')]"), "aria-disabled", "false"));
        }catch(Exception e){}
    }

    public int GetGroupsSize(){
        return groupsList.size();
    }

    public String GetGroupNameByIndex(int index){
        return groupsList.get(index).getText();
    }

    public String GetGroupIDByIndex(int index){
        return groupsList.get(index).getAttribute("id");
    }

    public int GetGroupOrResourcesStyleByIndex(int index){
        String attribute= allResourceGroupList.get(index).getAttribute("style");
        int indFirst= attribute.indexOf(":")+2;
        int indLast = attribute.indexOf("%");
        String style = attribute.substring(indFirst, indLast);
        int padding = Integer.parseInt(style);
        return padding;
    }

    public void MoveToGroupByIndex(int index){
        MoveToElement(groupsList.get(index));
    }

    public boolean SaveIsEnabled(){
        return saveButton.getAttribute("aria-disabled").equals("false");
    }

    public void WaitUntilSaveButtonWillBeEnable(){
        try{
            new WebDriverWait(driver, 5).until(ExpectedConditions.attributeContains(saveButton, "aria-disabled", "false"));
        }catch (Exception e){}
    }

    public boolean CancelIsEnabled(){
        return cancelButton.getAttribute("aria-disabled").equals("false");
    }

    public int GetResourcesInGroupSize(){
        return resourcesInGroupsList.size();
    }

    public String GetResourceNameInGroupByIndex(int index){
        return resourcesInGroupsList.get(index).getText();
    }

    public void DragAndDropCameraToGroup(String idResource, String idGroup) throws Exception {
//        String camera = "div.panel-body div dl div dd.vms-treeview-resource:nth-child("+indexResource+")";
//        String group= "div#tree-hierarchy-container div dl div dd.vms-resource-group:nth-child("+indexGroup+")";
        String camera = "div#resourcesGridContainer div dl div dd#"+idResource+"";
        String group= "div#tree-hierarchy-container div dl div dd#"+idGroup+"";
        DragAndDrop(camera, group);
    }

    public void DragAndDropGroupToGroup(String idGroup1, String idGroup2) throws Exception {
//        String camera = "div.panel-body div dl div dd.vms-treeview-resource:nth-child("+indexResource+")";
//        String group= "div#tree-hierarchy-container div dl div dd.vms-resource-group:nth-child("+indexGroup+")";
        String group1 = "div#tree-hierarchy-container div dl div dd#"+idGroup1+"";
        String group2= "div#tree-hierarchy-container div dl div dd#"+idGroup2+"";
        DragAndDrop(group1, group2);
    }

    public String EEE(String idResource){
        WebElement el = driver.findElement(By.cssSelector("div#resourcesGridContainer div dl div dd#"+idResource+""));
        return  el.getText();
    }

    public String BBB(String idGroup){
        WebElement el = driver.findElement(By.cssSelector("div#tree-hierarchy-container div dl div dd#"+idGroup+""));
        return  el.getText();
    }
}
