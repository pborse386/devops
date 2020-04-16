package pageObjects;

import Utilities.Page;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class AudioChannelsPage extends Page {

    @FindBy(id = "config-menu-network_entities-audioIn_channels")
    WebElement audioChannelsButton;

    @FindBy(xpath = "//span[@class='item-text ng-binding'][@title='Audio Channels']")
    WebElement audioChannelsButtonLandingPage;

    @FindBy(xpath = "//div[@class='ui-grid-top-panel']//div[@class='ui-grid-header-cell-wrapper']/div[@role='row']/div")
    WebElement plusMinusDeviceSquared;

    @FindBy(xpath = "//div[@class='ui-grid-header-cell-row']//div[@class='ng-scope']/div[@class='ui-grid-cell-contents']/span[text()='Name']/..")
    WebElement nameButton;

    @FindBy(xpath = "//div[@class='vms-list-header-with-search']/span//form/input")
    WebElement filterField;

    @FindBy(xpath = "//div[@class='fullSize']//div[@class ='vms-big-btn-footer bottom-separator-builder new-ui-panel-background']/button[contains(text(), 'Cancel')][@aria-hidden='false']")
    WebElement cancelButton;

    @FindBy(xpath = "//div[@class = 'vms-big-btn-footer bottom-separator-builder new-ui-panel-background']/button[contains(text(), 'Save')][@aria-hidden='false']")
    WebElement saveButton;

    //ChannelProperties
    @FindBy(xpath = "//ul[@class='nav nav-tabs']/li[1]")
    WebElement channelPropertiesButton;

    @FindBy(id = "inputName")
    WebElement nameField;

    @FindBy(xpath = "//ng-form//div[@class='configuration-ditails-data flex']/md-switch")
    WebElement visibleToggler;

    @FindBy(xpath = "//button[@title='Play']")
    WebElement playButton;

    @FindBy(xpath = "//button[@title='stop']")
    WebElement stopButton;

    @FindBy(xpath = "//div/label/span[contains(text(), 'Linked Video Resources')]/../../div//span[@class='ng-binding']")
    List<WebElement> linkedVideoResources;

    //Streams

    @FindBy(xpath = "//ul[@class='nav nav-tabs']/li[2]")
    WebElement streamsButton;

    @FindBy(xpath = "//select[@combo-default-value = 'audioInChannels.protocolTypes']")
    WebElement cameraStreamingDropDownList;

    //ApplyToDialog

    @FindBy(xpath = "//div[@class='vms-big-btn-footer bottom-separator-builder new-ui-panel-background']/button[@class='big-blue-button-new-ui pull-left'][@aria-hidden = 'false']")
    WebElement applyToButton;

    @FindBy(xpath = "//div[@class='panel-body fullSize']//div[@class='ui-grid-cell-contents ng-binding ng-scope']")
    List <WebElement> audioChannelsInApplyToDialog;

    @FindBy(xpath = "//div[@id='vms-export-container']/div[@class='modal-footer']/button[contains(text(), 'Apply')]")
    WebElement applyButtonInApplyToDialog;

    @FindBy(xpath = "//div[@id='vms-export-container']/div[@class='modal-footer']/button[contains(text(), 'Close')]")
    WebElement closeButtonInApplyToDialog;

    @FindBy(xpath = "//div[@id='vms-export-container']/div[@class='modal-footer']/button[contains(text(), 'Close')]")
    WebElement closeApplyToDialog;

    //Recording

    @FindBy(xpath = "//ul[@class='nav nav-tabs']/li[3]/div")
    WebElement recordingButton;

    @FindBy(xpath = "//ng-form/div[@class='configuration-ditails-form flex-between'][1]//md-switch")
    WebElement recordingOnOffToggler;

    @FindBy(xpath = "//ng-form/div[@class='configuration-ditails-form flex-between'][2]//md-switch")
    WebElement limitRetentionOnOffToggler;

    @FindBy(xpath = "//ng-form/div[@class='configuration-ditails-form flex-between']//span['ui-spinner ui-widget ui-corner-all ui-widget-header']/input")
    WebElement limitRetentionInputField;

    @FindBy(xpath = "//ng-form/div[@class='configuration-ditails-form flex-between']//span['ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-up ui-corner-tr']")
    WebElement limitRetentionUpSnipper;

    @FindBy(xpath = "//ng-form/div[@class='configuration-ditails-form flex-between']//span['ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-down ui-corner-br']")
    WebElement limitRetentionDownSnipper;

    @FindBy(xpath = "//div[@class='shceduleWrapperInChannels ng-scope']/div[@class = 'cursor-hand']/i")
    WebElement openSheduleButton;

    @FindBy(xpath = "//select[@ng-model = 'schedule.selectedSchedule']")
    List<WebElement> sheduleDropDownList;

    @FindBy(xpath = "//select[@ng-model = 'schedule.selectedEventType']")
    List<WebElement> recordingModeDropDownList;

    @FindBy(xpath = "//input[@ng-model='schedule.preEventSeconds']")
    WebElement preEventInputField;

    @FindBy(xpath = "//div[@ng-model='schedule.preEventSeconds']//span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-up ui-corner-tr']")
    WebElement preEventUpSpinner;

    @FindBy(xpath = "//div[@ng-model='schedule.preEventSeconds']//span['ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-down ui-corner-br']")
    WebElement preEventDownSpinner;

    @FindBy(xpath = "//div[@aria-hidden='false']/div[@class='configuration-ditails-form flex-between']//input[@ng-model='schedule.postEventSeconds']")
    WebElement postEventInputField;

    @FindBy(xpath = "//div[@aria-hidden='false']/div[@class='configuration-ditails-form flex-between']//div[@ng-model='schedule.postEventSeconds']/span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-up ui-corner-tr']")
    WebElement postEventUpSpinner;

    @FindBy(xpath = "//div[@aria-hidden='false']/div[@class='configuration-ditails-form flex-between']//div[@ng-model='schedule.postEventSeconds']//span['ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-down ui-corner-br']")
    WebElement postEventDownSpinner;

    @FindBy(xpath = "//select[@data-ng-model = 'schedule.normalStreamId']")
    WebElement streamDropDownList;

    @FindBy(xpath = "//select-wrapper[@text-on-label = 'schedule.boostedStream.title']/span")
    WebElement eventStream;

    @FindBy(xpath = "//button(text(), '+'")
    WebElement plusButton;

    @FindBy(xpath = "//div[@class='scheduleBlockWrpper']//div[@aria-hidden='false']/div[@class='configuration-ditails-form']//md-switch")
    WebElement motionOnOffToggler;

    @FindBy(xpath = "//div[@class='configuration-ditails-form']//span[@title='Specify Events']/..")
    WebElement specifyEventsButton;

    @FindBy(xpath = "//span[@title='Add Recording Schedule']/..")
    WebElement plusSheduleButton;

    @FindBy(xpath = "//div[@class='panel panel-primary vms-panel-primary fullSize']//div[@class='padding10']/span/input")
    WebElement nameSheduleField;

    @FindBy(xpath = "//div[@class = 'modal-footer']/button[contains(text(), 'Save')]")
    WebElement saveNewSheduleButton;

    @FindBy(xpath = "//div[@class='ui-grid-canvas']//div[@class='ui-grid-cell-contents ng-scope']/span[@class='flex ng-scope']/span[@class='text-overflow ng-binding']")
    List<WebElement> ChannelsList;

    @FindBy(xpath = "//div[@class='ui-grid-canvas']//div[@role='gridcell']/div[@class='ui-grid-cell-contents ng-scope']/span[@class='ng-binding ng-scope']")
    List<WebElement> VendorsList;

    @FindBy(xpath = "//div[@class='schedule-container']/div[@class='schedule-item-container ng-scope']/div[@class='configuration-recording-row-head flex']")
    List<WebElement> schedulePlansList;

    public AudioChannelsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void GoToAudioChannelsPage() throws InterruptedException {
        GoToConfigurationPage();
        ClickOnResourcesButton();
        waitUntilResourcesMenuIsExpanded();
        Thread.sleep(1000);
        ClickAudioChannelButon();
    }

    public void GoToAudioChannelsPageFromLanding() throws InterruptedException {
        Thread.sleep(1000);
        GoToConfigurationPage();
        Thread.sleep(1000);
        ClickAudioChannelButtonLandingPage();
    }

    public void ClickAudioChannelButon() throws InterruptedException {
        boolean breakIt = true;
        int time = 0;

        while (true) {
            breakIt = true;
            try {
                audioChannelsButton.click();
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

    public void ClickAudioChannelButtonLandingPage() throws InterruptedException {
       waitUntilIsLoadedCustomTime(audioChannelsButtonLandingPage);
       JavaScriptClick(audioChannelsButtonLandingPage);
    }

    public void InputIntoNameField(String name){
         waitUntilIsLoadedCustomTime(nameField);
        setElementText(nameField, name);
    }

    public String GetNameText(){
        waitUntilIsLoadedCustomTime(nameField);
        return  nameField.getAttribute("value");
    }

    public WebElement SelectRandomChannel(){
        waitUntilElementIsLoaded(plusMinusDeviceSquared);
//        if (ChannelsAreCollapsed()){
//            ClickOnExpendCollapsePlusButton();        }
        try{
            new WebDriverWait(driver, 60).until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//div[@class='ui-grid-canvas']//div[@class='ui-grid-cell-contents ng-scope']/span[@class='flex ng-scope']/span[@class='text-overflow ng-binding']"), 0));
        }catch(Exception e){}

        int max=GetChannelsListSize();
        int random =GetRandomDigit(0, max);
        WebElement device = ChannelsList.get(random);
        return device;
    }

    public boolean ChannelsAreCollapsed(){
      return (plusMinusDeviceSquared.getAttribute("class")).equals("ui-grid-tree-base-row-header-buttons ui-grid-icon-plus-squared");
    }

    public void ClickOnExpendCollapsePlusButton(){
        waitUntilElementIsClickable(plusMinusDeviceSquared);
        plusMinusDeviceSquared.click();
    }

    public int GetChannelsListSize(){
        return ChannelsList.size();
    }

    public String GetChannelText(int index){
        return ChannelsList.get(index).getText();
    }

    public void GoToStreamsPage(){
        streamsButton.click();
    }

    public boolean StreamsPageIsLoaded (){
        return verifyElementIsPresent(cameraStreamingDropDownList);
    }

    public void GoToRecordingPage(){
        JavaScriptClick(recordingButton);
    }

    public boolean RecordingPageIsLoaded(){
        return verifyElementIsPresent(recordingOnOffToggler);
    }

    public void GoToChannelPropertiesPage(){
        waitUntilElementIsLoaded(channelPropertiesButton);
        channelPropertiesButton.click();
    }

    public String GetVisiblePosition(){
        return visibleToggler.getAttribute("aria-checked");
    }

    public void SwitchVisibleToggler(){
        visibleToggler.click();
    }

    public void SwitchVisibleToOff(){
        if(visibleToggler.getAttribute("aria-checked").equals("true")){
            visibleToggler.click();
        }
    }

//    public void SwitchVisibleToOn() throws InterruptedException {
//        if(visibleOnOffToggler.getAttribute("aria-checked").equals("true")){
//            waitUntilElementIsClickable(visibleOnOffToggler);
//            visibleOnOffToggler.click();
//            PressSaveButton();
//            waitUntilElementIsLoaded(filterDevicesField);
//            Thread.sleep(1000);
//        }
//        visibleOnOffToggler.click();
//    }

    public WebElement FindDeviceByName(String text){
        WebElement element = null;
        try{
            element = driver.findElement(By.xpath("(//span/span[contains(text(), '" + text + "')] | //*[@value='" + text + "'])"));
        } catch (NoSuchElementException e) { }
        catch (ElementNotVisibleException e) { }

        return element;
    }

    public void ClickOnChannel(String channelText) throws InterruptedException {
//        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf( FindChannelByText(channelText)));
//        scroll(FindChannelByText(channelText));
        FindChannelByText(channelText).click();
        Thread.sleep(2000);
    }

    public WebElement FindChannelByText(String text){
        WebElement element = null;
        try{
            element = driver.findElement(By.xpath("//div[@class='ui-grid-cell-contents ng-scope']/span/span[contains(text(), '" + text + "')]"));
        } catch (NoSuchElementException e) { }
        catch (ElementNotVisibleException e) { }
        return element;
    }

    public void PressOnPlayPlayer(){
        playButton.click();
    }

    public void PressOnStopPlayer(){
        stopButton.click();
    }

    public boolean PlayIsEnable(){
        String enabled = playButton.getAttribute("aria-disabled");
        return enabled.contains("false");
    }

    public boolean StopIsEnable(){
        String enabled = stopButton.getAttribute("aria-disabled");
        return enabled.contains("false");
    }

    public String GetLinkedVideoResourcesText(int index){
        return linkedVideoResources.get(index).getText();
    }

    public int GetLinkedVideoResourcesSize(){
        return linkedVideoResources.size();
    }

    public void SelectOptionByIndex(int index){
        new Select(cameraStreamingDropDownList).selectByIndex(index);
    }

    public String GetSelectedOptionText(){
       return new Select(cameraStreamingDropDownList).getFirstSelectedOption().getText();
    }

    public String GetOptionTextByIndex(int index){
        return new Select(cameraStreamingDropDownList).getOptions().get(index).getText();
    }

    public int GetOptionsSize(){
        return new Select(cameraStreamingDropDownList).getOptions().size();
    }

    public void SelectRecordingModeByIndex(int selectIndex, int index) throws InterruptedException {
        Thread.sleep(1000);
        WebElement select = recordingModeDropDownList.get(selectIndex);
        waitUntilIsLoadedCustomTime(select);
        new Select(select).selectByIndex(index);
    }

    public int CountOfOptionsInRecordingModeSelect(int selectIndex){
        WebElement select = recordingModeDropDownList.get(selectIndex);
        List<WebElement> options = new Select(select).getOptions();
        return options.size();
    }

    public WebElement GetOptionsByIndex(int selectIndex, int index){
        WebElement select = recordingModeDropDownList.get(selectIndex);
        List<WebElement> options = new Select(select).getOptions();
        return options.get(index);
    }

    public String GetSelectedRecordingModeOption(int selectIndex){
        WebElement select = recordingModeDropDownList.get(selectIndex);
        WebElement element = new Select(select).getFirstSelectedOption();
        return element.getText();
    }

    public boolean RecordingToggleIsOn(){
        String cl = recordingOnOffToggler.getAttribute("class");
        if(cl.contains("md-checked")) return true;
        else return false;
    }

    public boolean LimitRetentionToggleIsOn(){
        waitUntilElementIsLoaded(limitRetentionOnOffToggler);
        String cl = limitRetentionOnOffToggler.getAttribute("class");
        if(cl.contains("md-checked")) return true;
        else return false;
    }

    public void SwitchRecordingToggle(){
        waitUntilIsLoadedCustomTime(recordingOnOffToggler);
        recordingOnOffToggler.click();
    }

    public void SelectRecordingModeOption(int selectIndex, String mode){
        WebElement select = recordingModeDropDownList.get(selectIndex);
        new Select(select).selectByVisibleText(mode);
    }

    public String GetSelectedRecModeOption(int selectIndex){
        WebElement select = recordingModeDropDownList.get(selectIndex);
        String option = new Select(select).getFirstSelectedOption().getAttribute("label");
        return option;
    }

    public void SwitchLimitRetentionToggle(){
        waitUntilIsLoadedCustomTime(limitRetentionOnOffToggler);
        limitRetentionOnOffToggler.click();
    }

    public void InputIntoLimitRetentionField(String text){
        waitUntilElementIsLoaded(limitRetentionInputField);
        setElementText(limitRetentionInputField, text);
    }

    public void ClickSpinnerUpLimitRetention(){
        limitRetentionUpSnipper.click();
    }

    public void ClickSpinnerDownLimitRetention(){
        limitRetentionDownSnipper.click();
    }

    public String GetLimitRetention(){
        return limitRetentionInputField.getAttribute("value");
    }

    public void IfScheduleIsNotExistAddIt() throws InterruptedException {
        if(!ScheduleIsExist(0)){
            ClickOnPlusScheduleButtons();
            SelectSchedule(0, "24/7");
        }
    }

    public void ClickOnPlusScheduleButtons(){
        scroll(plusSheduleButton);
        waitUntilElementIsLoaded(plusSheduleButton);
        plusSheduleButton.click();
    }

    public boolean ScheduleIsExist(int selectIndex){
        try{
            WebElement select =  driver.findElement(By.xpath( "//select[@ng-model = 'schedule.selectedSchedule']"));
            return true;
        }catch(Exception e){}
        return false;
    }

    public void SelectSchedule(int selectIndex, String schedule) throws InterruptedException {
        Thread.sleep(1000);
        WebElement select =  sheduleDropDownList.get(selectIndex);
        scroll(select);
        waitUntilIsLoadedCustomTime(select);
        new Select(select).selectByVisibleText(schedule);
        driver.switchTo().window(driver.getWindowHandle());
    }

    public void InputIntoNameNewSchedule(String name){
        setElementText(nameSheduleField, name);
    }

    public void PresssaveNewSchedule() throws InterruptedException {
        saveNewSheduleButton.click();
        WaitUntilDialogIsNotLocated();
        Thread.sleep(1000);
    }

    public String GetSelectedScheduleOption(int selectIndex){
        WebElement select =  sheduleDropDownList.get(selectIndex);
        WebElement element = new Select(select).getFirstSelectedOption();
        return element.getText();
    }

    public int GetSchedulesCount(int selectIndex){
        WebElement select =  sheduleDropDownList.get(selectIndex);
        int size = new Select(select).getOptions().size();
        return size;
    }

    public void SelectScheduleByIndex(int selectIndex, int index){
        WebElement select =  sheduleDropDownList.get(selectIndex);
        new Select(select).selectByIndex(index);
    }

    public void InputPreEventSeconds(String seconds){
        setElementText(preEventInputField, seconds);
    }

    public void ClickOnPreEventUpSpinner(){
        preEventUpSpinner.click();
    }

    public void ClickOnPreEventDownSpinner(){
        preEventDownSpinner.click();
    }

    public void InputPostEventSeconds(String seconds){
        setElementText(postEventInputField, seconds);
    }

    public void ClickOnPostEventUpSpinner(){
        postEventUpSpinner.click();
    }

    public void ClickOnPostEventDownSpinner(){
        postEventDownSpinner.click();
    }

    public String GetPreEventText(){
        return preEventInputField.getAttribute("value");
    }

    public String GetPostEventText(){
        return postEventInputField.getAttribute("value");
    }


    public void PressOnSpecifyEventsButton(){
//        waitUntilElementIsLoaded(specifyEventsButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].checked = true;", specifyEventsButton);

        scroll(specifyEventsButton);
        specifyEventsButton.click();
        driver.switchTo().window(driver.getWindowHandle());
    }

    public int GetSizeSchedulePlans(){
        return schedulePlansList.size();
    }

    public void ClickOnNameButton(){
        nameButton.click();
    }

    public int GetVendorsSize(){
        return VendorsList.size();
    }

    public String GetVendorText(int index){
        return VendorsList.get(index).getText();
    }

    public void FilterCamera(String text) throws InterruptedException {
        setElementText(filterField, text);
        Thread.sleep(1000);
    }

    public boolean ApplyToButtonIsEnabled(){
        boolean flag = false;
        String enabled = applyToButton.getAttribute("aria-disabled");
        if (enabled.equals("false")) flag = true;
        return flag;
    }

    public void ClickOnApplyToButton(){
        waitUntilElementIsLoaded(applyToButton);
        applyToButton.click();
    }

    public int GetAudioChannelsSizeInApplyToDialog(){
        return audioChannelsInApplyToDialog.size();
    }

    public void ClickOnAudioChanelsInApplyChannelsDialogByIndex(int index){
        audioChannelsInApplyToDialog.get(index).click();
    }

    public String GetAudioChannelTextInApplyChannelsDialogByIndex(int index){
       return audioChannelsInApplyToDialog.get(index).getText();
    }

    public void ClickOnApplyButtonInApplyToDialog(){
        applyButtonInApplyToDialog.click();
    }

    public void ClickOnCloseButtonInApplyToDialog(){
        closeButtonInApplyToDialog.click();
    }

    public void PressSaveButton() throws InterruptedException {
        WaitUntilDialogIsNotLocated();
//        WebElement save = driver.findElement(By.xpath("//div[@class = 'vms-big-btn-footer bottom-separator-builder new-ui-panel-background']/button[contains(text(), 'Save')][@aria-hidden='false'][@aria-disabled ='false']"));
        Thread.sleep(1000);
        JavaScriptClick(saveButton);;
        new WebDriverWait(driver, 60).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='fade-in-panel']")));
        Thread.sleep(1000);
    }

    public void PressCancelButton() throws InterruptedException {
        Thread.sleep(1000);
        cancelButton.click();
        Thread.sleep(1000);
    }

    public boolean CancelButtonIsEnable(){
        waitUntilIsLoadedCustomTime(cancelButton);
        String disabled = cancelButton.getAttribute("aria-disabled");
        if (disabled.equals("false")  ) return true;
        else{
            return false;
        }
    }

    public boolean SaveButtonIsEnable(){
        waitUntilIsLoadedCustomTime(saveButton);
        String disabled = saveButton.getAttribute("aria-disabled");
        if (disabled.equals("false")  ) return true;
        else{
            return false;
        }
    }

    public boolean CheckOptionIsExistOnScheduleDropDownList(String schedule){
        boolean flag =true;
        try{
            new Select(sheduleDropDownList.get(0)).selectByVisibleText(schedule);
        }catch (Exception e){
            flag =  false;
        }return flag;
    }

    public void WaitUntilSaveButtonWillBeEnable(){
        try{
            new WebDriverWait(driver, 5).until(ExpectedConditions.attributeContains(saveButton, "aria-disabled", "false"));
        }catch (Exception e){}
    }
}
