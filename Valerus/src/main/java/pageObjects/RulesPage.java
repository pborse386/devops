package pageObjects;

import Utilities.Page;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.Key;
import java.util.List;


public class RulesPage extends Page {
    @FindBy(id = "config-menu-network_entities-rules")
    WebElement rulesButton;

    @FindBy(xpath = "//span[@class='item-text ng-binding'][@title='Rules']")
    WebElement rulesButtonLandingPage;

    @FindBy(xpath = "//div[@class='ui-grid-canvas']/div[contains(@class, 'ui-grid-row ng-scope')]//div[contains(@class,'aview-item-in-list ng-binding ng-scope')]")
    List<WebElement> rulesList;

    @FindBy(xpath = "//div[@class='ui-grid-canvas']/div[contains(@class,'ui-grid-row ng-scope')]//button[contains(@class,'vms-grid-delete-button vicon-font v-trash ng-scope ng-isolate-scope')][@aria-hidden='false']")
    WebElement deleteIcon;

    @FindBy(xpath = "//div[@class='flex flex-between']/button[contains(text(), 'New')]")
    WebElement newButton;

    @FindBy(xpath = "//div[@class='flex flex-between']/button[contains(text(), 'Delete')]")
    WebElement deleteButton;

    @FindBy(xpath = "//div[@class='no-margin fullSize']//div[@class='vms-search-with-btn']//input")
    WebElement filterField;

    @FindBy(xpath = "//div[@class='configuration-ditails-form']//span[contains(text(), 'Name')]/../../input")
    WebElement nameField;

    @FindBy(xpath = "//div[@class='configuration-ditails-form']//span[contains(text(), 'Schedule')]/../../select")
    WebElement scheduleDropDownList;

    @FindBy(xpath = "//div[@class='configuration-ditails-form']//span[contains(text(), 'Event')]/../../button[contains(text(), 'Specify Events')]")
    WebElement specifyEventsButton;

    @FindBy(xpath = "//div[@class='configuration-ditails-form']//span[contains(text(), 'Event')]/../..//display-eventslist-configuration//span[@class='eventslist-title text-overflow ng-binding']")
    List<WebElement> eventsList;

    @FindBy(xpath = "//span/button[@class='vms-grid-delete-button delete-button-visible vicon-font v-trash']")
    List<WebElement> deleteEventIcon;

    @FindBy(id = "actionsContainer")
    WebElement onActionButton;

    @FindBy(xpath = "//span[contains(text(),'OFF Actions')]/..")
    WebElement offActionButton;

    @FindBy(xpath = "//div[contains(@class,'sortableActionsClassName')]/div[contains(@class,'ui-sortable-handle')]/button")
    WebElement plusONActionButton;

    @FindBy(xpath = "//div[contains(@class,'sortableOffActionsClassName')]/div[contains(@class,'ui-sortable-handle')]/button")
    WebElement plusOFFActionButton;

    @FindBy(xpath = "//div[contains(@class,'sortableActionsClassName')]/div[contains(@class,'ui-sortable-handle')]")
    WebElement plusONActionDiv;

    @FindBy(xpath = "//div[contains(@class,'sortableOffActionsClassName')]/div[contains(@class,'ui-sortable-handle')]")
    WebElement plusOFFActionDiv;

    @FindBy(xpath = "//div[contains(@class,'sortableActionsClassName')]//span[contains(text(), 'Action')]/../../select")
    List<WebElement> ActionsONDropDownList;

    @FindBy(xpath = "//div[contains(@class,'sortableActionsClassName')]/div[contains(@class,'vms-one-selected-row')]")
    List<WebElement> ActionsONList;

    @FindBy(xpath = "//div[contains(@class,'sortableOffActionsClassName')]/div[contains(@class,'vms-one-selected-row')]")
    List<WebElement> ActionsOFFList;

    @FindBy(xpath = "//div[contains(@class,'sortableActionsClassName')]/div[contains(@class,'vms-one-selected-row')]//span[@class='vms-grid-delete-button delete-button-visible vicon-font v-trash']")
    List<WebElement> ActionsONDeleteIconList;

    @FindBy(xpath = "//div[contains(@class,'sortableOffActionsClassName')]/div[contains(@class,'vms-one-selected-row')]//span[@class='vms-grid-delete-button delete-button-visible vicon-font v-trash']")
    List<WebElement> ActionsOFFDeleteIconList;

    @FindBy(xpath = "//div[contains(@class,'vms-one-selected-row ng-scope')]//span[@contains(text(), 'Action')]/../../select")
    WebElement resourcesActionsONDropDownList;

    @FindBy(xpath = "//div[contains(@class,'sortableOffActionsClassName')]//span[contains(text(), 'Action')]/../../select")
    List<WebElement> ActionsOFFDropDownList;

    @FindBy(xpath = "//div[contains(@class,'sortableActionsClassName')]//div[@class='dropdown-list-item-container toggle']")
    List<WebElement> VideoActionsONField;

    @FindBy(xpath = "//div[contains(@class,'sortableOffActionsClassName')]//div[@class='dropdown-list-item-container toggle']")
    List<WebElement> VideoActionsOffField;

    @FindBy(xpath = "//div[contains(@class,'sortableActionsClassName')]//div[@class='dropdown-menu-container ng-scope is-show']//div[@class='dropdown-list-item-container ng-scope flex']/div[@class='dropdown-list-item-text ng-binding']")
    List<WebElement> VideoActionsONList;

    @FindBy(xpath = "//div[contains(@class,'sortableOffActionsClassName')]//div[@class='dropdown-menu-container ng-scope is-show']//div[@class='dropdown-list-item-container ng-scope flex']/div[@class='dropdown-list-item-text ng-binding']")
    List<WebElement> VideoActionsOFFList;

    @FindBy(xpath = "//div[contains(@class,'sortableActionsClassName')]//span[contains(text(), 'View')]/../../..//select")
    List<WebElement> ViewActionsONDropDownList;

    @FindBy(xpath = "//div[contains(@class,'sortableOffActionsClassName')]//span[contains(text(), 'View')]/../../..//select")
    List<WebElement> ViewActionsOFFDropDownList;

    @FindBy(xpath = "//div[contains(@class,'sortableActionsClassName')]//span[contains(text(), 'Video')]/../../select[@ng-model='action.selectedPtzCamera']")
    List<WebElement> PresetVideoActionsONDropDownList;

    @FindBy(xpath = "//div[contains(@class,'sortableActionsClassName')]//span[contains(text(), 'Preset')]/../../select")
    List<WebElement> PresetActionsONDropDownList;

    @FindBy(xpath = "//div[contains(@class,'sortableOffActionsClassName')]//span[contains(text(), 'Video')]/../../select[@ng-model='action.selectedPtzCamera']")
    List<WebElement> PresetVideoActionsOFFDropDownList;

    @FindBy(xpath = "//div[contains(@class,'sortableOffActionsClassName')]//span[contains(text(), 'Preset')]/../../select")
    List<WebElement> PresetActionsOFFDropDownList;

    @FindBy(xpath = "//div[contains(@class,'sortableActionsClassName')]//span[contains(text(), 'Relay')]/../../select")
    List<WebElement> RelayActionsONDropDownList;

    @FindBy(xpath = "//div[contains(@class,'sortableOffActionsClassName')]//span[contains(text(), 'Relay')]/../../select")
    List<WebElement> RelayActionsOFFDropDownList;

    @FindBy(xpath = "//div[contains(@class,'sortableActionsClassName')]//span[contains(text(), 'State')]/../../select")
    List<WebElement> RelayStateActionsONDropDownList;

    @FindBy(xpath = "//div[contains(@class,'sortableOffActionsClassName')]//span[contains(text(), 'State')]/../../select")
    List<WebElement> RelayStateActionsOFFDropDownList;

    @FindBy(xpath = "//div[contains(@class,'sortableOffActionsClassName')]//span[contains(text(), 'Video')]/../../select[contains(@ng-model, 'action.selectedPtzCameraForTour')]")
    List<WebElement> VideoPTZActionsOFFDropDownList;

    @FindBy(xpath = "//div[contains(@class,'sortableOffActionsClassName')]//span[contains(text(), 'PTZ Tour')]/../../select")
    List<WebElement> PTZTourActionsOFFDropDownList;

    @FindBy(xpath = "//div[contains(@class,'sortableActionsClassName')]//span[contains(text(), 'Video')]/../../select[contains(@ng-model, 'action.selectedPtzCameraForTour')]")
    List<WebElement> VideoPTZActionsONDropDownList;

    @FindBy(xpath = "//div[contains(@class,'sortableActionsClassName')]//span[contains(text(), 'PTZ Tour')]/../../select")
    List<WebElement> PTZTourActionsONDropDownList;

    @FindBy(xpath = "//div[contains(@class,'sortableActionsClassName')]//span[contains(text(), 'Tour')]/../../select")
    List<WebElement> TourActionsONDropDownList;

    @FindBy(xpath = "//div[contains(@class,'sortableOffActionsClassName')]//span[contains(text(), 'Tour')]/../../select")
    List<WebElement> TourActionsOFFDropDownList;

    @FindBy(xpath = "//div[contains(@class,'sortableActionsClassName')]//span[contains(text(), 'Web Page')]/../../..//select")
    List<WebElement> WebPageActionsONDropDownList;

    @FindBy(xpath = "//div[contains(@class,'sortableOffActionsClassName')]//span[contains(text(), 'Web Page')]/../../..//select")
    List<WebElement> WebPageActionsOFFDropDownList;

    @FindBy(xpath = "//div[contains(@class,'sortableActionsClassName')]//span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']/input[@ng-model='action.delayMinutes']")
    List<WebElement> DelayMinActionONInput;

    @FindBy(xpath = "//div[contains(@class,'sortableActionsClassName')]//span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']/input[@ng-model='action.delaySeconds']")
    List<WebElement> DelaySecActionONInput;

    @FindBy(xpath = "//div[contains(@class,'sortableOffActionsClassName')]//span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']/input[@ng-model='action.delayMinutes']")
    List<WebElement> DelayMinActionOFFInput;

    @FindBy(xpath = "//div[contains(@class,'sortableOffActionsClassName')]//span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']/input[@ng-model='action.delaySeconds']")
    List<WebElement> DelaySecActionOFFInput;

    @FindBy(xpath = "//div[@class='vms-big-btn-footer ng-scope']/button[contains(text(), 'Cancel')]")
    WebElement cancelButton;

    @FindBy(xpath = "//div[@class='vms-big-btn-footer ng-scope']/button[contains(text(), 'Save')]")
    WebElement saveButton;

    public RulesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void GoToRulesPage() throws InterruptedException {
        Thread.sleep(1000);
        try{GoToConfigurationPage();
            Thread.sleep(1000);

            ClickOnResourcesButton();
            waitUntilResourcesMenuIsExpanded();
            Thread.sleep(1000);
            ClickOnRulesButton();}
        catch(Exception e){
            takeScreenshot(driver, "Setup", "GoToRulesPage");
        }
    }

    public void GoToRulesFromLandingPage() throws InterruptedException {
        Thread.sleep(1000);
        GoToConfigurationPage();
        Thread.sleep(1000);
        ClickOnRulesButtonFromLandingPage();
    }

    public void ClickOnRulesButton() throws InterruptedException {
        boolean breakIt = true;
        int time = 0;
        while (true) {
            breakIt = true;
            try {
                JavaScriptClick(rulesButton);
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

    public void ClickOnRulesButtonFromLandingPage() throws InterruptedException {
        waitUntilIsLoadedCustomTime(rulesButtonLandingPage);
        rulesButtonLandingPage.click();
    }

    public void ClickOnNewButton(){
        JavaScriptClick(newButton);
    }

    public void InputIntoNameField(String name){
        setElementText(nameField, name);
    }

    public String GetRuleName(){
        return nameField.getAttribute("value");
    }

    public void ClickOnDeleteButton(){
//        waitUntilElementIsClickable(deleteButton);
        deleteButton.sendKeys(Keys.ENTER);
    }

    public int GetRulesSize(){
        return rulesList.size();
    }

    public String GetRuleNameByIndex(int index){
        return rulesList.get(index).getText();
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

    public boolean SaveButtonIsEnabled(){
        return saveButton.isEnabled();
    }

    public boolean CancelButtonIsEnabled(){
        return cancelButton.getAttribute("aria-disabled").equals("false");
    }

    public void WaitUntilCancelWillBeEnabled(){
        try{
            new WebDriverWait(driver, 5).until(ExpectedConditions.attributeContains(cancelButton, "aria-disabled", "false"));
        }catch (Exception e){}
    }

    public void ClickOnDeleteIcon(){
        JavaScriptClick(deleteIcon);
    }

    public void WaitUntilSaveButtonWillBeEnable(){
        try{
            new WebDriverWait(driver, 5).until(ExpectedConditions.attributeContains(saveButton, "aria-disabled", "false"));
        }catch (Exception e){}
    }

    public void WaitUntilSaveButtonWillBeDisable(){
        try{
            new WebDriverWait(driver, 5).until(ExpectedConditions.attributeContains(saveButton, "aria-disabled", "true"));
        }catch (Exception e){}
    }

    public void ClickOnRuleByName(String name){
        WebElement rule = driver.findElement(By.xpath("//div[contains(@class,'ui-grid-row ng-scope')]//div[@title='"+name+"']"));

        while(true){
            String ruleName = GetRuleName();
            if(ruleName.equals(name)) break;
            JavaScriptClick(rule);
        }
    }

    public void ClickOnRuleByIndex(int index) {
        WebElement rule = driver.findElement(By.xpath("//div[@class='ui-grid-canvas']/div[contains(@class, 'ui-grid-row ng-scope')]["+(index+1)+"]"));
        JavaScriptClick(rule);
    }

    public void InputIntoFilterField(String tour){
        setElementText(filterField, tour);
    }

    public void SelectScheduleByName(String schedule) throws InterruptedException {
        Thread.sleep(1000);
        waitUntilIsLoadedCustomTime(scheduleDropDownList);
        new Select(scheduleDropDownList).selectByVisibleText(schedule);
//        driver.switchTo().window(driver.getWindowHandle());
    }

    public String GetSelectedScheduleOption(){
        WebElement element = new Select(scheduleDropDownList).getFirstSelectedOption();
        return element.getText();
    }

    public String GetScheduleTextByIndex(int index){
        String name = new Select(scheduleDropDownList).getOptions().get(index).getText();
        return name;
    }

    public void SelectScheduleByIndex(int index) throws InterruptedException {
        waitUntilIsLoadedCustomTime(scheduleDropDownList);
        new Select(scheduleDropDownList).selectByIndex(index);
//        driver.switchTo().window(driver.getWindowHandle());
    }

    public int GetSchedulesSize() throws InterruptedException {
        waitUntilIsLoadedCustomTime(scheduleDropDownList);
        int size=new Select(scheduleDropDownList).getOptions().size();
        return size;
    }

    public void ClickOnSpecifyEventsButton(){
        specifyEventsButton.click();
//        ((JavascriptExecutor)driver).executeScript("arguments[0].checked = true;", specifyEventsButton);
//        specifyEventsButton.click();
//        driver.switchTo().window(driver.getWindowHandle());
    }

    public void ClickOnPlusONActionButton(){
        if(!plusONActionDiv.isDisplayed()){
            onActionButton.click();
        }
        plusONActionButton.click();
    }

    public void ClickOnPlusOFFActionButton(){
        if(!plusOFFActionDiv.isDisplayed()){
            offActionButton.click();
        }
        plusOFFActionButton.click();
    }

    public int GetONActionsSize(int actionInd){
        return  new Select(ActionsONDropDownList.get(actionInd)).getOptions().size();
    }

    public void SelectONActionsByIndex(int actionInd, int index){
         new Select(ActionsONDropDownList.get(actionInd)).selectByIndex(index);
    }

    public void SelectONActionsByOption(int actionInd, String option){
        new Select(ActionsONDropDownList.get(actionInd)).selectByValue(option);
    }

    public String GetONActionsTextByIndex(int actionInd, int index){
        return new Select(ActionsONDropDownList.get(actionInd)).getOptions().get(index).getText();
    }

    public String GetSelectedONAction(int actionInd){
        return new Select(ActionsONDropDownList.get(actionInd)).getFirstSelectedOption().getText();
    }

    public void SelectOFFActionsByIndex(int actionInd, int index){
        new Select(ActionsOFFDropDownList.get(actionInd)).selectByIndex(index);
    }

    public void SelectOFFActionsByOption(int actionInd, String Option){
        new Select(ActionsOFFDropDownList.get(actionInd)).selectByValue(Option);
    }

    public String GetSelectedOFFAction(int actionInd){
        return new Select(ActionsOFFDropDownList.get(actionInd)).getFirstSelectedOption().getText();
    }

    public String GetOFFActionsTextByIndex(int actionInd, int index){
        return new Select(ActionsOFFDropDownList.get(actionInd)).getOptions().get(index).getText();
    }

    public int GetOFFActionsSize(int actionInd){
        return  new Select(ActionsOFFDropDownList.get(actionInd)).getOptions().size();
    }

    public int GetEventsSize(){
        return eventsList.size();
    }

    public void MoveToEventByIndex(int index){
        MoveToElement(eventsList.get(index));
    }

    public void ClickOnDeleteEventIconByIndex(int index) throws InterruptedException {
        waitUntilElementIsLoaded(deleteEventIcon.get(index));
        deleteEventIcon.get(index).click();
//        JavascriptExecutor executor = (JavascriptExecutor)driver;
//        executor.executeScript("arguments[0].click();", icons.get(index));
    }

    public int GetONActionSize(){
        return ActionsONList.size();
    }

    public int GetOFFActionSize(){
        return ActionsOFFList.size();
    }

    public int GetVideoActionONSelectsListSize(){
        return VideoActionsONField.size();
    }

    public void ClickOnVideoONActionField(int index){
        VideoActionsONField.get(index).click();
    }

    public void ClickOnVideoOFFActionField(int index){
        VideoActionsOffField.get(index).click();
    }


    public int GetViewActionONSelectsListSize(){
        return ViewActionsONDropDownList.size();
    }

    public int GetTourActionONSelectsListSize(){
        return TourActionsONDropDownList.size();
    }

    public int GetWebPageActionONSelectsListSize(){
        return WebPageActionsONDropDownList.size();
    }

    public int GetDelayActionsONListSize(){
        return DelayMinActionONInput.size();
    }

    public int GetDelayActionsOFFListSize(){
        return DelayMinActionOFFInput.size();
    }

    public int GetWebPageActionOFFSelectsListSize(){
        return WebPageActionsOFFDropDownList.size();
    }

    public int GetTourActionOFFSelectsListSize(){
        return TourActionsOFFDropDownList.size();
    }

    public int GetRelayActionONSelectsListSize(){
        return RelayActionsONDropDownList.size();
    }

    public int GetPresetActionONSelectsListSize(){
        return PresetActionsONDropDownList.size();
    }

    public int GetPTZTourActionONSelectsListSize(){
        return PTZTourActionsONDropDownList.size();
    }

    public int GetPTZTourActionOFFSelectsListSize(){
        return PTZTourActionsOFFDropDownList.size();
    }

    public int GetPresetActionOFFSelectsListSize(){
        return PresetActionsOFFDropDownList.size();
    }

    public int GetRelayActionOFFSelectsListSize(){
        return RelayActionsOFFDropDownList.size();
    }

    public int GetViewActionOFFSelectsListSize(){
        return ViewActionsOFFDropDownList.size();
    }

    public int GetVideoActionOFFSelectsListSize(){
        return VideoActionsOffField.size();
    }

    public int GetVideoActionONOptionsSize(int index){
        scroll(VideoActionsONList.get(index));
        return  VideoActionsONList.size();
    }

    public int GetVideoActionOFFOptionsSize(int index){
//        scroll(VideoActionsOFFList.get(0));
        return  VideoActionsOFFList.size();
    }

    public int GetViewActionONSelectOptionsSize(int index){
        return  new Select(ViewActionsONDropDownList.get(index)).getOptions().size();
    }

    public int GetTourActionONSelectOptionsSize(int index){
        return  new Select(TourActionsONDropDownList.get(index)).getOptions().size();
    }

    public int GetWebPageActionONSelectOptionsSize(int index){
        return  new Select(WebPageActionsONDropDownList.get(index)).getOptions().size();
    }

    public int GetWebPageActionOFFSelectOptionsSize(int index){
        return  new Select(WebPageActionsOFFDropDownList.get(index)).getOptions().size();
    }

    public int GetTourActionOFFSelectOptionsSize(int index){
        return  new Select(TourActionsOFFDropDownList.get(index)).getOptions().size();
    }

    public int GetRelayActionONSelectOptionsSize(int index){
        return  new Select(RelayActionsONDropDownList.get(index)).getOptions().size();
    }

    public int GetPresetActionONSelectOptionsSize(int index){
        return  new Select(PresetActionsONDropDownList.get(index)).getOptions().size();
    }

    public int GetVideoPTZActionONSelectOptionsSize(int index){
        return  new Select(VideoPTZActionsONDropDownList.get(index)).getOptions().size();
    }

    public int GetVideoPTZActionOFFSelectOptionsSize(int index){
        return  new Select(VideoPTZActionsOFFDropDownList.get(index)).getOptions().size();
    }

    public int GetPresetActionOFFSelectOptionsSize(int index){
        return  new Select(PresetActionsOFFDropDownList.get(index)).getOptions().size();
    }

    public int GetPresetVideoActionONSelectOptionsSize(int index){
        return  new Select(PresetVideoActionsONDropDownList.get(index)).getOptions().size();
    }

    public int GetPTZTourActionONSelectOptionsSize(int index){
        return  new Select(PTZTourActionsONDropDownList.get(index)).getOptions().size();
    }

    public int GetPTZTourActionOFFSelectOptionsSize(int index){
        return  new Select(PTZTourActionsOFFDropDownList.get(index)).getOptions().size();
    }

    public int GetPresetVideoActionOFFSelectOptionsSize(int index){
        return  new Select(PresetVideoActionsOFFDropDownList.get(index)).getOptions().size();
    }

    public int GetRelayActionOFFSelectOptionsSize(int index){
        return  new Select(RelayActionsOFFDropDownList.get(index)).getOptions().size();
    }

    public int GetRelayStateActionONSelectOptionsSize(int index){
        return  new Select(RelayStateActionsONDropDownList.get(index)).getOptions().size();
    }

    public int GetRelayStateActionOFFSelectOptionsSize(int index){
        return  new Select(RelayStateActionsOFFDropDownList.get(index)).getOptions().size();
    }

    public int GetViewActionOFFSelectOptionsSize(int index){
        return  new Select(ViewActionsOFFDropDownList.get(index)).getOptions().size();
    }

//    public int GetVideoActionOFFSelectOptionsSize(int index){
//        return  new Select(VideoActionsOFFDropDownList.get(index)).getOptions().size();
//    }

//    public String GetVideoActionONOptionByIndex(int vidIndex, int optIndex){
//        return  new Select(VideoActionsONDropDownList.get(vidIndex)).getOptions().get(optIndex).getText();
//    }

     public String GetVideoActionONOptionByIndex(int optIndex){
//         scroll(VideoActionsONList.get(optIndex));
         return  VideoActionsONList.get(optIndex).getText();
    }

    public String GetVideoActionOFFOptionByIndex(int optIndex){
//        scroll(VideoActionsOFFList.get(optIndex));
        return  VideoActionsOFFList.get(optIndex).getText();
    }

    public String GetViewActionONOptionByIndex(int viewIndex, int optIndex){
        return  new Select(ViewActionsONDropDownList.get(viewIndex)).getOptions().get(optIndex).getText();
    }

    public String GetTourActionONOptionByIndex(int viewIndex, int optIndex){
        return  new Select(TourActionsONDropDownList.get(viewIndex)).getOptions().get(optIndex).getText();
    }

    public String GetWebPageActionONOptionByIndex(int viewIndex, int optIndex){
        return  new Select(WebPageActionsONDropDownList.get(viewIndex)).getOptions().get(optIndex).getText();
    }

    public String GetWebPageActionOFFOptionByIndex(int viewIndex, int optIndex){
        return  new Select(WebPageActionsOFFDropDownList.get(viewIndex)).getOptions().get(optIndex).getText();
    }

    public String GetTourActionOFFOptionByIndex(int viewIndex, int optIndex){
        return  new Select(TourActionsOFFDropDownList.get(viewIndex)).getOptions().get(optIndex).getText();
    }

    public String GetRelayActionONOptionByIndex(int viewIndex, int optIndex){
        return  new Select(RelayActionsONDropDownList.get(viewIndex)).getOptions().get(optIndex).getText();
    }

    public String GetPresetActionONOptionByIndex(int viewIndex, int optIndex){
        return  new Select(PresetActionsONDropDownList.get(viewIndex)).getOptions().get(optIndex).getText();
    }

    public String GetVideoPTZActionONOptionByIndex(int viewIndex, int optIndex){
        return  new Select(VideoPTZActionsONDropDownList.get(viewIndex)).getOptions().get(optIndex).getText();
    }

    public String GetVideoPTZActionOFFOptionByIndex(int viewIndex, int optIndex){
        return  new Select(VideoPTZActionsOFFDropDownList.get(viewIndex)).getOptions().get(optIndex).getText();
    }

    public String GetPresetActionOFFOptionByIndex(int viewIndex, int optIndex){
        return  new Select(PresetActionsOFFDropDownList.get(viewIndex)).getOptions().get(optIndex).getAttribute("label");
    }

    public String GetPresetVideoActionONOptionByIndex(int viewIndex, int optIndex){
        return  new Select(PresetVideoActionsONDropDownList.get(viewIndex)).getOptions().get(optIndex).getText();
    }

    public String GetPTZTourVideoActionONOptionByIndex(int ptzIndex, int optIndex){
        return  new Select(PTZTourActionsONDropDownList.get(ptzIndex)).getOptions().get(optIndex).getText();
    }

    public String GetPTZTourVideoActionOFFOptionByIndex(int ptzIndex, int optIndex){
        return  new Select(PTZTourActionsOFFDropDownList.get(ptzIndex)).getOptions().get(optIndex).getText();
    }

    public String GetPresetVideoActionOFFOptionByIndex(int viewIndex, int optIndex){
        return  new Select(PresetVideoActionsOFFDropDownList.get(viewIndex)).getOptions().get(optIndex).getText();
    }

    public String GetRelayActionOFFOptionByIndex(int viewIndex, int optIndex){
        return  new Select(RelayActionsOFFDropDownList.get(viewIndex)).getOptions().get(optIndex).getText();
    }

    public String GetRelayStateActionONOptionByIndex(int viewIndex, int optIndex){
        return  new Select(RelayStateActionsONDropDownList.get(viewIndex)).getOptions().get(optIndex).getText();
    }

    public String GetRelayStateActionOFFOptionByIndex(int viewIndex, int optIndex){
        return  new Select(RelayStateActionsOFFDropDownList.get(viewIndex)).getOptions().get(optIndex).getText();
    }

    public String GetViewActionOFFOptionByIndex(int viewIndex, int optIndex){
        return  new Select(ViewActionsOFFDropDownList.get(viewIndex)).getOptions().get(optIndex).getText();
    }

//    public String GetVideoActionOFFOptionByIndex(int vidIndex, int optIndex){
//        return  new Select(VideoActionsOFFDropDownList.get(vidIndex)).getOptions().get(optIndex).getText();
//    }

//    public void SelectVideoActionONOptionByIndex(int vidIndex, int optIndex){
//        new Select(VideoActionsONDropDownList.get(vidIndex)).selectByIndex(optIndex);
//    }

    public void ClickOnVideoActionONByIndex(int index){
//        scroll(VideoActionsONList.get(index));
        VideoActionsONList.get(index).click();
    }

    public void ClickOnVideoActionOFFByIndex(int index){
//        scroll(VideoActionsOFFList.get(index));
        VideoActionsOFFList.get(index).click();
    }

    public String GetVideoActiONText(int index){
        return VideoActionsONField.get(index).getText();
    }

    public String GetVideoActiOFFText(int index){
        return VideoActionsOffField.get(index).getText();
    }

    public void SelectViewActionONOptionByIndex(int viewIndex, int optIndex){
        new Select(ViewActionsONDropDownList.get(viewIndex)).selectByIndex(optIndex);
    }

    public void SelectTourActionONOptionByIndex(int viewIndex, int optIndex){
        new Select(TourActionsONDropDownList.get(viewIndex)).selectByIndex(optIndex);
    }

    public void SelectWebPageActionONOptionByIndex(int viewIndex, int optIndex){
        new Select(WebPageActionsONDropDownList.get(viewIndex)).selectByIndex(optIndex);
    }

    public void SelectWebPageActionOFFOptionByIndex(int viewIndex, int optIndex){
        new Select(WebPageActionsOFFDropDownList.get(viewIndex)).selectByIndex(optIndex);
    }

    public void SelectTourActionOFFOptionByIndex(int viewIndex, int optIndex){
        new Select(TourActionsOFFDropDownList.get(viewIndex)).selectByIndex(optIndex);
    }

    public void SelectRelayActionONOptionByIndex(int viewIndex, int optIndex){
        new Select(RelayActionsONDropDownList.get(viewIndex)).selectByIndex(optIndex);
    }

    public void SelectPresetVideoActionONOptionByIndex(int viewIndex, int optIndex){
        new Select(PresetVideoActionsONDropDownList.get(viewIndex)).selectByIndex(optIndex);
    }

    public void SelectPTZTourVideoActionONOptionByIndex(int viewIndex, int optIndex){
        new Select(PTZTourActionsONDropDownList.get(viewIndex)).selectByIndex(optIndex);
    }

    public void SelectPTZTourVideoActionOFFOptionByIndex(int viewIndex, int optIndex){
        new Select(PTZTourActionsOFFDropDownList.get(viewIndex)).selectByIndex(optIndex);
    }

    public void SelectPresetVideoActionOFFOptionByIndex(int viewIndex, int optIndex){
        new Select(PresetVideoActionsOFFDropDownList.get(viewIndex)).selectByIndex(optIndex);
    }

    public void SelectPresetActionONOptionByIndex(int viewIndex, int optIndex){
        new Select(PresetActionsONDropDownList.get(viewIndex)).selectByIndex(optIndex);
    }

    public void SelectVideoPTZActionONOptionByIndex(int viewIndex, int optIndex){
        new Select(VideoPTZActionsONDropDownList.get(viewIndex)).selectByIndex(optIndex);
    }

    public void SelectVideoPTZActionOFFOptionByIndex(int viewIndex, int optIndex){
        new Select(VideoPTZActionsOFFDropDownList.get(viewIndex)).selectByIndex(optIndex);
    }

    public void SelectPresetActionOFFOptionByIndex(int viewIndex, int optIndex){
        new Select(PresetActionsOFFDropDownList.get(viewIndex)).selectByIndex(optIndex);
    }

    public void SelectRelayActionOFFOptionByIndex(int viewIndex, int optIndex){
        new Select(RelayActionsOFFDropDownList.get(viewIndex)).selectByIndex(optIndex);
    }

    public void SelectRelayStateActionONOptionByIndex(int viewIndex, int optIndex){
        new Select(RelayStateActionsONDropDownList.get(viewIndex)).selectByIndex(optIndex);
    }

    public void SelectRelayStateActionOFFOptionByIndex(int viewIndex, int optIndex){
        new Select(RelayStateActionsOFFDropDownList.get(viewIndex)).selectByIndex(optIndex);
    }

    public void SelectViewActionOFFOptionByIndex(int viewIndex, int optIndex){
        new Select(ViewActionsOFFDropDownList.get(viewIndex)).selectByIndex(optIndex);
    }

    public void SelectVideoActionOFFOptionByIndex(int vidIndex, int optIndex){
        scroll(VideoActionsOFFList.get(optIndex));
        new Select(VideoActionsOFFList.get(vidIndex)).selectByIndex(optIndex);
    }

//    public String GetVideoActionONSelectedOption(int vidIndex){
//        return  new Select(VideoActionsONDropDownList.get(vidIndex)).getFirstSelectedOption().getText();
//    }

    public String GetViewActionONSelectedOption(int vidIndex){
        return  new Select(ViewActionsONDropDownList.get(vidIndex)).getFirstSelectedOption().getText();
    }

    public String GetTourActionONSelectedOption(int vidIndex){
        return  new Select(TourActionsONDropDownList.get(vidIndex)).getFirstSelectedOption().getText();
    }

    public String GetWebPageActionONSelectedOption(int vidIndex){
        return  new Select(WebPageActionsONDropDownList.get(vidIndex)).getFirstSelectedOption().getText();
    }

    public String GetWebPageActionOFFSelectedOption(int vidIndex){
        return  new Select(WebPageActionsOFFDropDownList.get(vidIndex)).getFirstSelectedOption().getText();
    }

    public String GetTourActionOFFSelectedOption(int vidIndex){
        return  new Select(TourActionsOFFDropDownList.get(vidIndex)).getFirstSelectedOption().getText();
    }

    public String GetRelayActionONSelectedOption(int vidIndex){
        return  new Select(RelayActionsONDropDownList.get(vidIndex)).getFirstSelectedOption().getText();
    }

    public String GetPresetVideoActionONSelectedOption(int vidIndex){
        return  new Select(PresetVideoActionsONDropDownList.get(vidIndex)).getFirstSelectedOption().getText();
    }

    public String GetPTZVideoActionONSelectedOption(int vidIndex){
        return  new Select(VideoPTZActionsONDropDownList.get(vidIndex)).getFirstSelectedOption().getText();
    }

    public String GetPTZVideoActionOFFSelectedOption(int vidIndex){
        return  new Select(VideoPTZActionsOFFDropDownList.get(vidIndex)).getFirstSelectedOption().getText();
    }

    public String GetPTZTourActionONSelectedOption(int index){
        return  new Select(PTZTourActionsONDropDownList.get(index)).getFirstSelectedOption().getText();
    }

    public String GetPTZTourActionOFFSelectedOption(int index){
        return  new Select(PTZTourActionsOFFDropDownList.get(index)).getFirstSelectedOption().getText();
    }

    public String GetPresetVideoActionOFFSelectedOption(int vidIndex){
        return  new Select(PresetVideoActionsOFFDropDownList.get(vidIndex)).getFirstSelectedOption().getText();
    }

    public String GetRelayActionOFFSelectedOption(int vidIndex){
        return  new Select(RelayActionsOFFDropDownList.get(vidIndex)).getFirstSelectedOption().getText();
    }

    public String GetRelayStateActionONSelectedOption(int vidIndex){
        return  new Select(RelayStateActionsONDropDownList.get(vidIndex)).getFirstSelectedOption().getText();
    }

    public String GetPresetActionONSelectedOption(int vidIndex){
        return  new Select(PresetActionsONDropDownList.get(vidIndex)).getFirstSelectedOption().getText();
    }

    public String GetVideoPTZActionONSelectedOption(int vidIndex){
        return  new Select(VideoPTZActionsONDropDownList.get(vidIndex)).getFirstSelectedOption().getText();
    }

    public String GetVideoPTZActionOFFSelectedOption(int vidIndex){
        return  new Select(VideoPTZActionsOFFDropDownList.get(vidIndex)).getFirstSelectedOption().getText();
    }

    public String GetPresetActionOFFSelectedOption(int vidIndex){
        return  new Select(PresetActionsOFFDropDownList.get(vidIndex)).getFirstSelectedOption().getAttribute("label");
    }

    public String GetRelayStateActionOFFSelectedOption(int vidIndex){
        return  new Select(RelayStateActionsOFFDropDownList.get(vidIndex)).getFirstSelectedOption().getText();
    }

    public String GetViewActionOFFSelectedOption(int vidIndex){
        return  new Select(ViewActionsOFFDropDownList.get(vidIndex)).getFirstSelectedOption().getText();
    }

    public String GetVideoActionOFFSelectedOption(int vidIndex){
        scroll(VideoActionsOFFList.get(vidIndex));
        return  new Select(VideoActionsOFFList.get(vidIndex)).getFirstSelectedOption().getText();
    }

    public void InputIntoONActionMINField(int index, String min){
        setElementText(DelayMinActionONInput.get(index), min);
    }

    public void InputIntoONActionSECField(int index, String sec){
        setElementText(DelaySecActionONInput.get(index), sec);
    }

    public void InputIntoOFFActionMINField(int index, String min){
        setElementText(DelayMinActionOFFInput.get(index), min);
    }

    public void InputIntoOFFActionSECField(int index, String sec){
        setElementText(DelaySecActionOFFInput.get(index), sec);
    }

    public String GetONActionMINValue(int index){
        return DelayMinActionONInput.get(index).getAttribute("value");
    }

    public String GetONActionSECValue(int index){
        return DelaySecActionONInput.get(index).getAttribute("value");
    }

    public String GetOFFActionMINValue(int index){
        return DelayMinActionOFFInput.get(index).getAttribute("value");
    }

    public String GetOFFActionSECValue(int index){
        return DelaySecActionOFFInput.get(index).getAttribute("value");
    }

    public boolean GoToPresetOptionInONActionsIsEnabled(int index){
       List<WebElement> options =  new Select(ActionsONDropDownList.get(index)).getOptions();
       int ind=0;
       for(int i=0;i<options.size();i++){
           String value = options.get(i).getAttribute("value");
           if(value.equals("GOTO_PRESET")){
               ind = i;
               break;
           }
       }
       return options.get(ind).getAttribute("aria-disabled").equals("false");
    }

    public boolean GoToPresetOptionInOFFActionIsEnabled(int index){
        List<WebElement> options =  new Select(ActionsOFFDropDownList.get(index)).getOptions();
        int ind=0;
        for(int i=0;i<options.size();i++){
            String value = options.get(i).getAttribute("value");
            if(value.equals("GOTO_PRESET")){
                ind = i;
                break;
            }
        }
        return options.get(ind).getAttribute("aria-disabled").equals("false");
    }

    public boolean RunCameraToPTZOptionIsEnabled(int index){
        List<WebElement> options =  new Select(ActionsONDropDownList.get(index)).getOptions();
        int ind=0;
        for(int i=0;i<options.size();i++){
            String value = options.get(i).getAttribute("value");
            if(value.equals("RUN_PTZ_TOUR")){
                ind = i;
                break;
            }
        }
        return options.get(ind).getAttribute("aria-disabled").equals("false");
    }

    public boolean OptionONActionIsEnabledByIndex(int actionInd, int optionInd){
        WebElement option = new Select(ActionsONDropDownList.get(actionInd)).getOptions().get(optionInd);
        return option.getAttribute("aria-disabled").equals("false");
    }

    public boolean OptionOFFActionIsEnabledByIndex(int actionInd, int optionInd){
        WebElement option = new Select(ActionsOFFDropDownList.get(actionInd)).getOptions().get(optionInd);
        return option.getAttribute("aria-disabled").equals("false");
    }

    public void ClickOnDeleteActionONIconByIndex(int index){
        MoveToElement(ActionsONDropDownList.get(index));
        ActionsONDeleteIconList.get(index).click();
    }

    public void ClickOnDeleteActionOFFIconByIndex(int index){
        MoveToElement(ActionsOFFDropDownList.get(index));
        ActionsOFFDeleteIconList.get(index).click();
    }

    public boolean CheckOptionIsExistOnScheduleDropDownList(String schedule){
        boolean flag =true;
        try{
            new Select(scheduleDropDownList).selectByVisibleText(schedule);
        }catch (Exception e){
            flag =  false;
        }return flag;
    }
}
