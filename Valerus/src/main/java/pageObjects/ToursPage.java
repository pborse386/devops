package pageObjects;

import Utilities.Page;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by nastya on 9/4/2017.
 */
public class ToursPage extends Page {
//    @FindBy(id = "config-menu-network_entities-tours")
    @FindBy(xpath = "//*[@id='config-menu-network_entities-tours' or @title='Tours']")
    WebElement toursButton;

    @FindBy(xpath = "//div[@class='ui-grid-canvas']/div[@class='ui-grid-row ng-scope ui-grid-row-selected']//button[@class='vms-grid-delete-button vicon-font v-trash ng-scope ng-isolate-scope']")
    WebElement deleteIcon;

    @FindBy(xpath = "//div[@class='ui-grid-canvas']/div[contains(@class, 'ui-grid-row ng-scope')]//div[contains(@class,'view-item-in-list ng-binding ng-scope')]")
    List<WebElement> toursList;

    @FindBy(xpath = "//div[contains(@class, 'vms-resources-accordion-panel ng-scope ng-isolate-scope')]/div[contains(@class,'vms-resources-accordion-subpanel')]/span[contains(@class , 'vms-resources-accordion-subpanel-title')]")
    List<WebElement> viewsList;

    @FindBy(xpath = "//div[@class='border-builder']//div[contains(@class,'view-row tour ng-scope')]/span[@class='view-item-in-list tour ng-binding']")
    List<WebElement> viewsToDisplayList;

    @FindBy(xpath = "//div[@class='border-builder']//div[contains(@class,'view-row tour ng-scope')]//span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']/input")
    List<WebElement> viewsToDisplayTimeFieldList;

    @FindBy(xpath = "//div[@class='border-builder']//div[contains(@class,'view-row tour ng-scope')]//span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-up ui-corner-tr']")
    List<WebElement> viewsToDisplayTimeUPSpinnerList;

    @FindBy(xpath = "//div[@class='border-builder']//div[contains(@class,'view-row tour ng-scope')]//span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-down ui-corner-br']")
    List<WebElement> viewsToDisplayTimeDownSpinnerList;

    @FindBy(xpath = "//div[@class='border-builder']//div[contains(@class,'view-row tour ng-scope')]/button[contains(@class,'font-icon-trash vicon-font v-trash')]")
    List<WebElement> viewsToDisplayRemoveIconList;

    @FindBy(xpath = "//collapsable[@class='collapsable-panel border-builder']")
    WebElement availableViewsButton;

    @FindBy(xpath = "//div[@class='flex flex-between']/button[contains(text(), 'New')]")
    WebElement newButton;

    @FindBy(xpath = "//div[@class='flex flex-between']/button[contains(text(), 'Delete')]")
    WebElement deleteButton;

    @FindBy(xpath = "//div[@id='toursControllerWrapper']//div[@class='vms-search-with-btn']//input")
    WebElement filterField;

    @FindBy(xpath = "//div[@class='availible-resources-list']//div[@class='input-group']/div[@class='input-group-addon']/button")
    WebElement viewFilterButton;

    @FindBy(xpath = "//div[@class='availible-resources-list']//div[@class='vms-search-with-btn']//input")
    WebElement viewsFilterField;

    @FindBy(xpath = "//form//div[@class='config-form-fields border-builder']/input")
    WebElement nameField;

    @FindBy(xpath = "//div[@class='config-form-fields border-builder']/textarea")
    WebElement remarksField;

    @FindBy(xpath = "//div[@class='views-fields-wrapper']//div[@class='config-form-fields border-builder']//md-switch")
    WebElement visibilityToggleSwitch;

    @FindBy(xpath = "//div[@class='vms-panel-body-bottom restoreButtonsFooter pushright']/button[contains(text(), 'Cancel')]")
    WebElement cancelButton;

    @FindBy(xpath = "//div[@class='vms-panel-body-bottom restoreButtonsFooter pushright']/button[contains(text(), 'Save')]")
    WebElement saveButton;

    @FindBy(xpath = "//div[@class='border-builder']/div[contains(@class,'ui-droppable')]")
    WebElement containerForDrag;

    public ToursPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void GoToToursPage() throws InterruptedException {
        Thread.sleep(1000);
        try{GoToConfigurationPage();
            Thread.sleep(1000);

            ClickOnResourcesButton();
            waitUntilResourcesMenuIsExpanded();
            Thread.sleep(1000);
            ClickOnToursButton();}
        catch(Exception e){
            takeScreenshot(driver, "Setup", "GoToWebPagesPage");
        }
    }

    public void GoToToursPageFromLanding() throws InterruptedException {
        Thread.sleep(1000);
        try {
            GoToConfigurationPage();
            Thread.sleep(1000);
            ClickOnToursButton();
        }catch(Exception e){
            takeScreenshot(driver, "Setup", "GoToToursPage");
        }
    }

    public void ClickOnToursButton() throws InterruptedException {
        boolean breakIt = true;
        int time = 0;
        while (true) {
            breakIt = true;
            try {
                JavaScriptClick(toursButton);
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
        JavaScriptClick(newButton);
    }

    public void InputIntoNameField(String name){
        setElementText(nameField, name);
    }

    public String GetTourName(){
        return nameField.getAttribute("value");
    }

    public void InputIntoRemarksField(String remark){
        setElementText(remarksField, remark);
    }

    public String GetRemarksText(){
        return remarksField.getAttribute("value");
    }

    public void ClickOnVisibilityToggleSwitch(){
        visibilityToggleSwitch.click();
    }

    public boolean VisibilityToggleIsOn(){
        return visibilityToggleSwitch.getAttribute("aria-checked").equals("true");
    }

    public void InputIntoViewsFilter(String text){
        waitUntilElementIsLoaded(viewsFilterField);
        setElementText(viewsFilterField, text);
    }

    public void ClickOnViewFilterButton(){
        viewFilterButton.click();
    }

    public void ClickOnAvailibleViewsButton(){
        availableViewsButton.click();
    }

    public void ClickOnDeleteButton(){
        waitUntilElementIsClickable(deleteButton);
        deleteButton.click();
    }

    public int GetViewsSize(){
        return viewsList.size();
    }

    public String GetViewsIDByIndex(int index){
        return viewsList.get(index).getAttribute("id");
    }

    public String GetViewsNameByIndex(int index){
        return viewsList.get(index).getText();
    }

    public void PressSaveButton() throws InterruptedException {
        waitUntilElementIsClickable(saveButton);
        saveButton.click();
        new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='fade-in-panel']")));
    }

    public void PressCancelButton() throws InterruptedException {
        waitUntilElementIsClickable(cancelButton);
        ClickOnElement(cancelButton);
    }

    public boolean SaveButtonIsEnabled(){
        return saveButton.isEnabled();
    }

    public boolean CancelButtonIsEnabled(){
        return  cancelButton.isEnabled();
    }

    public void InputIntoViewsFilterField(String name){
        setElementText(viewsFilterField, name);
    }

    public void ClickOnDeleteIcon(){
        waitUntilElementIsLoaded(deleteIcon);
        JavaScriptClick(deleteIcon);
    }

    public int GetToursSize(){ return toursList.size();}

    public void DragAndDropView(WebElement view) throws Exception {
        new Actions(driver).dragAndDrop(view, containerForDrag).perform();
    }

    public void DragAndDropViewToOtherPlace(WebElement view1, WebElement view2) throws Exception {
        new Actions(driver).dragAndDrop(view1, view2).perform();
    }

    public void ClickOnViewByIndex(int index){
        ClickOnElement(viewsList.get(index));
    }

    public String GetViewNameByIndex(int index){
        return viewsList.get(index).getText();
    }

    public WebElement GetViewByIndex(int index){
        return viewsList.get(index);
    }

    public WebElement GetViewByName(String name){
        WebElement view = null;
        for(int i=0; i<viewsList.size(); i++){
            if(GetViewNameByIndex(i).equals(name)){
                view = viewsList.get(i);
                break;
            }
        }
        return view;
    }

    public int GetViewsToDisplaySize(){
        return viewsToDisplayList.size();
    }

    public String GetViewToDisplayNameByIndex(int index){
        return viewsToDisplayList.get(index).getText();
    }

    public WebElement ReturnViewToDisplayByIndex(int index){
        return  viewsToDisplayList.get(index);
    }


    public void ClickOnViewToDisplayByIndex(int index){
        ClickOnElement(viewsToDisplayList.get(index));
    }

    public void ClickOnTourByName(String name){
        WebElement tour = driver.findElement(By.xpath("//div[contains(@class,'ui-grid-row ng-scope')]//div[text()='"+name+"']"));
        while(true){
            String tourName = GetTourName();
            if(tourName.equals(name)) break;
            tour.click();
        }
    }

    public void ClickOnTourByIndex(int index) {
        WebElement tour = driver.findElement(By.xpath("//div[@class='ui-grid-canvas']/div[contains(@class, 'ui-grid-row ng-scope')]["+(index+1)+"]/../../.."));
        JavaScriptClick(tour);
    }

    public String GetTourNameByIndex(int index){
        return toursList.get(index).getText();
    }

    public void InputIntoFilterField(String tour) throws InterruptedException {
      setElementText(filterField, tour);
      Thread.sleep(1000);
    }

    public void ClickOnRemoveViewIconByIndex(int index){
        JavaScriptClick(viewsToDisplayRemoveIconList.get(index));
    }

    public void WaitUntilSaveButtonWillBeEnable(){
        try{
            new WebDriverWait(driver, 5).until(ExpectedConditions.attributeContains(saveButton, "aria-disabled", "false"));
        }catch (Exception e){}
    }

    public int GetViewTimeField(){
        return viewsToDisplayTimeFieldList.size();
    }

    public void InputIntoViewTimeField(int index,String time){
        viewsToDisplayTimeFieldList.get(index).clear();
        viewsToDisplayTimeFieldList.get(index).sendKeys(time);
    }

    public String GetViewTimeValue(int index){
        return viewsToDisplayTimeFieldList.get(index).getAttribute("value");
    }

    public void ClickOnTimeUPSpinnerByIndex(int index){
        ClickOnElement(viewsToDisplayTimeUPSpinnerList.get(index));
    }

    public void ClickOnTimeDownSpinnerByIndex(int index){
        ClickOnElement(viewsToDisplayTimeDownSpinnerList.get(index));
    }
}
