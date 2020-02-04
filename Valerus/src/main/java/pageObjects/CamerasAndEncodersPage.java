package pageObjects;

import Utilities.Page;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.lang.model.element.Element;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;
import static jdk.nashorn.internal.objects.NativeString.substring;
import static org.apache.xalan.xsltc.compiler.util.Util.println;


public class CamerasAndEncodersPage extends Page {

    //fields
    @FindBy(id = "config-menu-devices-cameras_encoders")
     WebElement camerasAndEncodersButton;

    @FindBy(xpath = "//*[@title='Cameras and Devices'][@class='item-text ng-binding']")
    WebElement camerasAndEncodersButtonLandingPage;

    //Central panel
    @FindBy(xpath="//div[@class='ui-grid-viewport ng-isolate-scope']//div[@class='ui-grid-cell-contents ng-scope']/div[@role='button']/i")
    WebElement plusMinusNVRsSquared;

    @FindBy(xpath="//div[@class='fullSize ng-scope']/span")
    WebElement NoNVRsHaveBeenAddedToTheSystemTest;

    @FindBy(xpath="//div[@class='vms-search-with-btn']/form/input[@type='search']")
     WebElement filterDevicesCentralPanelField;

    @FindBy(xpath="//div[@class='ng-scope sortable']//span[contains(text(), 'IP Address')]/..")
    WebElement IPAdressButton;

    @FindBy(xpath="//div[@class='ng-scope sortable']//span[contains(text(), 'Name')]/..")
    WebElement nameButton;

    @FindBy(xpath="//div[@class='ng-scope sortable']//span[contains(text(), 'Vendor')]/..")
    WebElement vendorButton;

    @FindBy(xpath="//div[@class='ng-scope sortable']//span[contains(text(), 'Model')]/..")
    WebElement modelButton;

    @FindBy(xpath="//div[@class='ng-scope sortable']//span[contains(text(), 'Version')]/..")
    WebElement versionButton;

    @FindBy(xpath = "//section[@class='cameras-encoders-details-container fade-out-on-save ng-scope']/div[@class='flex configuration-details-bottom-buttons-container ng-scope']/button[@title='Cancel']")
    WebElement cancelSettingButton;

    @FindBy(xpath = "//section[@class='cameras-encoders-details-container fade-out-on-save ng-scope']/div[@class='flex configuration-details-bottom-buttons-container ng-scope']/button[@title='Save'][@aria-hidden='false']")
    WebElement saveSettingButton;

    //AddDiscoveredDevices/pop-up window
    @FindBy(xpath="//div[@id='vms-nvr']//div[@class='flex congiguration-top-main-buttons-container']/button[@title='Add Discovered Devices']")
     WebElement addDiscoveredDevicesButton;

    @FindBy(xpath="//div[@class='col-lg-4 fullSize']//input[@type='search']")
     WebElement filterNVRsPopUpWindowField;

    @FindBy(xpath="//div[@class='col-lg-8 fullSize']//input[@type='search']")
     WebElement filterDiscoveredDevicesPopUpWindowField;

    @FindBy(xpath="//div[@id='addDevicesScreen']//div[@class='ui-grid-cell-contents ng-scope']//div[@class='ui-grid-tree-base-row-header-buttons ng-scope ui-grid-tree-base-header']/i")
     List <WebElement> plusMinusDiscoveredDEvicesSquared;

    @FindBy(xpath="//div[@class='vms-popup-btn-container']/button[contains(text(), 'Close')]")
     WebElement closeAddDiscoveredDevicesWindowButton;

    @FindBy(xpath="//div[@class='vms-popup-btn-container']/button[@title='Add to NVR and close']")
     WebElement addToNVRAndCloseButton;

    @FindBy(xpath="//div[@class='vms-popup-btn-container']/button[@title='Add to NVR']")
     WebElement addToNVRButton;

    @FindBy(xpath = "//div[@class='vms-modal-popup-header']/button[@title='Refresh']")
    WebElement refreshDiscoveredIcon;

    @FindBy(xpath="//div[@class='vms-popup-btn-container']/button[text()='Close']")
     WebElement closeSuccessfulyAddedButton;

//    @FindBy(xpath="//div[@class='ng-isolate-scope pull-right top-right-icon-align'][@role='button']")
//    WebElement closeDialogIcon;

    @FindBy(xpath="//div[@id='vms-configurationScreen']/div[text()='Communication Failure']")
    WebElement communicationFailureDialog;

    @FindBy(xpath="//div[@id='vms-configurationScreen']/div[text()='Device Not Authorized']")
    WebElement deviceNotAuthorizedDialog;

    @FindBy(xpath="//div[@class='vms-popup-btn-container']//span[@title='Back']/..")
    WebElement backToDiscoveredNVRsButton;

    @FindBy(xpath="//div[@class='col-lg-4 fullSize']//div[@class='ng-scope sortable']//span[contains(text(), 'IP Address')]/..")
    WebElement sortNVRsByIPAdressSnipper;

    @FindBy(xpath="//div[@class='col-lg-8 fullSize']//div[@class='ng-scope sortable']//span[contains(text(), 'IP Address')]/..")
    WebElement sortDiscoveredDevicesByIPAdressSnipper;

    @FindBy(xpath="//div[@class='col-lg-8 fullSize']//div[@class='ng-scope sortable']//span[contains(text(), 'Model')]/..")
    WebElement sortDiscoveredDevicesByModelSnipper;

    @FindBy(xpath="//div[@class='col-lg-8 fullSize']//span[@class='vms-configuration-panel-title']/span/span[@class='vms-device-count-inline vms-selected-color ng-binding'][1]")
    WebElement countDiscoveredDevicesicon;

    @FindBy(xpath="//div[contains(text(), 'IP already in use')]")
    WebElement ipAlreadyInUseNotification;

    //AddDeviceManually/Pop-Up window CreateDevice
    @FindBy(xpath="//div[@id='vms-nvr']//div[@class='flex congiguration-top-main-buttons-container']/button[@title='Add Device Manually']")
     WebElement addDeviceManuallyButton;

    @FindBy(id="deviceType")
     WebElement protocolDropDownList;

    @FindBy(id="selectedNVR")
     WebElement SelectNVRDropDownList;

    @FindBy(id="inputAddress")
     WebElement IPAdressCreateDeviceField;

    @FindBy(xpath="//div[@name='port']//input")
     WebElement portCreateDeviceField;

    @FindBy(xpath="//div[@name='port']//a[@class='ui-spinner-button ui-spinner-up ui-corner-tr']")
     WebElement portUpSpinner;

    @FindBy(xpath="//div[@name='port']//a[@class='ui-spinner-button ui-spinner-down ui-corner-br']")
     WebElement portDownSpinner;

    @FindBy(id="inputUserName")
     WebElement userNameCreateDeviceField;

    @FindBy(id="inputPasswords")
     WebElement passwordCreateDeviceField;

    @FindBy(xpath="//div[@class='vms-popup-btn-container']//button[@title='Cancel']")
     WebElement cancelCreateDeviceButton;

    @FindBy(xpath="//div[@class='vms-popup-btn-container']//button[@title='Apply and close']")
     WebElement applyAndCloseCreateDeviceButton;

    @FindBy(xpath="//div[@class='vms-popup-btn-container']//button[@title='Apply']")
     WebElement applyCreateDeviceButton;

    //RemoveDevices/Pop-up window
    @FindBy(xpath="//div[@id='vms-nvr']//div[@class='flex congiguration-top-main-buttons-container']/button[@title='Remove Devices']")
     WebElement RemoveDevicesButton;

    @FindBy(xpath="//div[@class='vms-popup-btn-container']//button[contains(text(), 'Close')]")
     WebElement closeDeleteDevicesProgressButton;

    //Right menu/Properties
    @FindBy(xpath="//div[@class='vms-panel-body-child']//div[@class='new-ui-details-header bottom-separator-builder ng-scope']/span[contains('Properties')]/..")
    WebElement propertiesDropDownButton;

    @FindBy(id="inputName")
    WebElement namePropertiesField;

    @FindBy(xpath = "//ng-form/div[@class='view-details-container flex-between'][4]/div[@class='view-details-value-container flex-between']/div[@class='ng-binding']")
    WebElement vendorPropertiesField;

    @FindBy(id="inputAddress")
    WebElement ipAddressPropertiesField;

    @FindBy(xpath="//span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']/input")
    WebElement portPropertiesField;

    @FindBy(xpath="//span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-up ui-corner-tr']")
    WebElement portUpPropertiesSpinner;

    @FindBy(xpath="//span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-down ui-corner-br']")
     WebElement portDownPropertiesSpinner;

//    @FindBy(xpath = "//div[@class='configuration-ditails-form']/div/md-switch//div[@class='md-bar']")
    @FindBy(xpath = "//div[@class = 'view-details-container flex-between']//div[@class='view-details-label-container flex']/div[@class='view-details-label-toggle-switch-container']/md-switch/div[@class='md-container']")
    WebElement userNameOnOffToggler;

    @FindBy(id="inputUserName")
     WebElement userNamePropertiesField;

    @FindBy(id="inputPasswords")
     WebElement passwordPropertiesField;

    @FindBy(xpath="//div[@class='cameras-encoders-details-btn-container flex ng-scope']/button[contains(text(),'Cancel')]")
     WebElement cancelPropertiesButton;

    @FindBy(xpath="//div[@class='cameras-encoders-details-btn-container flex ng-scope']/button[contains(text(),'Save')][@aria-hidden='false']")
    WebElement savePropertiesButton;

    @FindBy(xpath="//div[@class='ui-grid-canvas']//div[@class='ng-isolate-scope']/div[2]//span[@class='ng-binding ng-scope'][not(@role)]")
    List<WebElement> devicesListCentralPanel;

    @FindBy(xpath="//div[@class='ui-grid-canvas']/div[@class='ui-grid-row ng-scope']/div[@class='ng-isolate-scope']/div[2]//div[@class='ui-grid-selection-row-header-buttons ui-grid-icon-ok ng-scope'][@role='button']")
    List<WebElement> VDevicesListCentralPanel;

    @FindBy(xpath="//div[@class='ui-grid-row ng-scope' or @class='ui-grid-row ng-scope ui-grid-row-selected']/div[@class='ng-isolate-scope']/div[3]/div[@class='ui-grid-cell-contents ng-binding ng-scope']")
    List<WebElement> devicesNameListCentralPanel;

    @FindBy(xpath="//div[@class='ui-grid-row ng-scope' or @class='ui-grid-row ng-scope ui-grid-row-selected']/div[@class='ng-isolate-scope']/div[4]/div[@class='ui-grid-cell-contents ng-binding ng-scope']")
    List<WebElement> deviceVendorListCentralPanel;

    @FindBy(xpath="//div[@class='ui-grid-row ng-scope' or @class='ui-grid-row ng-scope ui-grid-row-selected']/div[@class='ng-isolate-scope']/div[5]/div[@class='ui-grid-cell-contents ng-binding ng-scope']")
    List<WebElement> deviceModelListCentralPanel;

    @FindBy(xpath="//div[@class='ui-grid-row ng-scope' or @class='ui-grid-row ng-scope ui-grid-row-selected']/div[@class='ng-isolate-scope']/div[6]/div[@class='ui-grid-cell-contents ng-binding ng-scope']")
    List<WebElement> deviceVersionListCentralPanel;

    @FindBy(xpath="//div[@class='col-lg-8 fullSize']//div[@class='ui-grid-canvas']/div[@class='ui-grid-row ng-scope']//span[@class='ng-scope']/span[@class='ng-binding ng-scope'][not(@role)]")
    List<WebElement> devicesListAddDiscoveredDevicesWindow;

    @FindBy(xpath="//div[@class='col-lg-8 fullSize']//div[@class='ui-grid-canvas']/div[@class='ui-grid-row ng-scope']/div[@class='ng-isolate-scope']/div[3]/div")
    List<WebElement> modelListAddDiscoveredDevicesWindow;

    @FindBy(xpath="//div[@class='col-lg-8 fullSize']//div[@class='ui-grid-viewport ng-isolate-scope']//div[@class='ui-grid-row ng-scope']//div[@class='ui-grid-disable-selection ng-scope']/div[@class='ui-grid-cell-contents']/div")
    List<WebElement> VListAddDiscoveredDevicesWindow;

    @FindBy(xpath="//div[@class='col-lg-4 fullSize']//div[@class='ui-grid-canvas']/div[@class='ui-grid-row ng-scope']//div[@class='ui-grid-cell-contents ng-scope']/span")
    List<WebElement> NVRsList;

    public CamerasAndEncodersPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //Methods
    public void goToCamerasAndEncodersPage() throws InterruptedException, IOException {
        GoToConfigurationPage();
        ClickOnCameraAndEncoders();
    }

    public void goToCamerasAndEncodersPageFromLandingPage() throws InterruptedException, IOException {
        GoToConfigurationPage();
        ClickOnCameraAndEncodersLanding();
    }

    public void ClickOnCameraAndEncoders(){
        waitUntilElementIsLoaded(camerasAndEncodersButton);
        JavaScriptClick(camerasAndEncodersButton);
        waitUntilElementIsLoaded(RemoveDevicesButton);
    }

    public void ClickOnCameraAndEncodersLanding(){
        waitUntilElementIsLoaded(camerasAndEncodersButtonLandingPage);
        JavaScriptClick(camerasAndEncodersButtonLandingPage);
        waitUntilElementIsLoaded(RemoveDevicesButton);
    }

    public WebElement FindNVRByText(String textIP){
        //String xPathEx= "//*[contains(text()," +  textIP  +")]";
        WebElement element = null;
        try{
            element = driver.findElement(By.xpath("//div[@class='col-lg-4 fullSize']//div[@class='ui-grid-canvas']/div[contains(@class,'ui-grid-row ng-scope')]//div[@class='ui-grid-cell-contents ng-scope']/span[contains(text(), '" + textIP + "')]"));
        } catch (NoSuchElementException e) { }
        catch (ElementNotVisibleException e) { }
        return element;
    }

    public String CheckStatusDevice(String IpText){
       WebElement status = driver.findElement(By.xpath("//span[contains(@title, '" + IpText + "')][@class='ng-binding ng-scope']/../../../div[last()]/div/*"));
       if (status.getTagName().equals("i")) return "V";
        else {
            return status.getText();
       }
    }

    public void SelectNVR(String NVRAddress){
        waitUntilElementIsClickable(FindNVRByText(NVRAddress));
        FindNVRByText(NVRAddress).click();
    }

    public void WaitUntilDiscoveredDevicesAreLoaded(){
        try{
            new WebDriverWait(driver, 45).until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//div[@class='col-lg-8 fullSize']//div[@class='ui-grid-viewport ng-isolate-scope']//div[@class='ui-grid-row ng-scope']/div[@class='ng-isolate-scope']//div[@class='ui-grid-cell-contents ng-scope']"), 4));
        }catch (Exception e){}
    }

    public void SelectDevice() throws InterruptedException {
        Thread.sleep(1000);
        int size = devicesListAddDiscoveredDevicesWindow.size()-1;
        int random =GetRandomDigit(0,size);
        scroll(devicesListAddDiscoveredDevicesWindow.get(random));
        devicesListAddDiscoveredDevicesWindow.get(random).click();
    }

    public int GetDiscoveredDevicesSize(){
        return devicesListAddDiscoveredDevicesWindow.size();
    }

    public void ClickOnDiscoveredDevicesByIndex(int index){
    	JavaScriptClick(devicesListAddDiscoveredDevicesWindow.get(index));
    }

    public void OpenDiscoveredDeviceListIfItIsClose(){
        boolean flag = false;
        try{
            flag=verifyElementIsPresent(plusMinusDiscoveredDEvicesSquared.get(0));
        }catch (Exception e){}
        if(flag){
            if(plusMinusDiscoveredDEvicesSquared.get(0).getAttribute("class").equals("ui-grid-icon-plus-squared")){
                plusMinusDiscoveredDEvicesSquared.get(0).click();
            }
        }
    }

    public int GetDiscoveredDEvicesSquaredSize (){
        return  plusMinusDiscoveredDEvicesSquared.size();
    }

    public void ClickOnDiscoveredDevicesSquaredByIndex(int index){
        try {
           List<WebElement> squared = driver.findElements(By.xpath("//div[@id='addDevicesScreen']//div[@class='ui-grid-cell-contents ng-scope']//div[@class='ui-grid-tree-base-row-header-buttons ng-scope ui-grid-tree-base-header']/i"));
            scroll(squared.get(index));
            squared.get(index).click();}
        catch(org.openqa.selenium.StaleElementReferenceException ex)        {
            List<WebElement> squared = driver.findElements(By.xpath("//div[@id='addDevicesScreen']//div[@class='ui-grid-cell-contents ng-scope']//div[@class='ui-grid-tree-base-row-header-buttons ng-scope ui-grid-tree-base-header']/i"));
            scroll(squared.get(index));
            JavaScriptClick(squared.get(index));
        }
//        scroll(plusMinusDiscoveredDEvicesSquared.get(index));
//        plusMinusDiscoveredDEvicesSquared.get(index).click();
    }

    public void FilterNVRs(String adress) throws InterruptedException {
        setElementText(filterNVRsPopUpWindowField, adress);
        Thread.sleep(1000);
    }

    public int CountOfdevice() throws InterruptedException {
        Thread.sleep(1000);
        int countDevice = devicesListCentralPanel.size();
        return countDevice;
    }

    public void SortNVRsByIPAdress(){
    	JavaScriptClick(sortNVRsByIPAdressSnipper);
    }

    public void SortDiscoveredDevicesByIPAdress(){
    	JavaScriptClick(sortDiscoveredDevicesByIPAdressSnipper);
    }

    public void SortDiscoveredDevicesByModel(){
    	JavaScriptClick(sortDiscoveredDevicesByModelSnipper);
    }

    public void FilterDevices(String NVRAddress) throws InterruptedException {
        setElementText(filterDiscoveredDevicesPopUpWindowField, NVRAddress);
        Thread.sleep(1000);
    }

    public int CountDiscoveredDevices(){
        String text = countDiscoveredDevicesicon.getText();
        int index1 = text.indexOf("(")+1;
        int index2 = text.indexOf(")");
        int count = Integer.parseInt(text.substring(index1,index2));
        return count;
    }

    public WebElement ReturnSelectedDevice() throws InterruptedException {
//        if(PlusMinusDiscoveredDEvicesIcon.getAttribute("class").equals("ui-grid-icon-plus-squared")){
//            PlusMinusDiscoveredDEvicesIcon.click();
//        }
        Thread.sleep(1000);
        int size = devicesListAddDiscoveredDevicesWindow.size()-1;
        int random =GetRandomDigit(0,size);
        return devicesListAddDiscoveredDevicesWindow.get(random);
    }

    public void SelectNVRsInCreateDeviceWindow(String NVRAddress){
        new Select(driver.findElement(By.id("inputNVRId"))).
                selectByVisibleText(NVRAddress);
    }

    public WebElement ReturnSelectedDeviceInCentralPanel() throws InterruptedException {
        if(NVRSquaredIsCollapsed()){
            ClickOnNVRSquared();
        }
        Thread.sleep(1000);
        int size = devicesListCentralPanel.size()-1;
        int random =GetRandomDigit(0,size);
        return devicesListCentralPanel.get(random);
    }

    public boolean NVRSquaredIsCollapsed(){
        waitUntilIsLoadedCustomTime(plusMinusNVRsSquared);
        return plusMinusNVRsSquared.getAttribute("class").equals("ui-grid-icon-plus-squared");
    }

    public void ClickOnNVRSquared(){
    	JavaScriptClick(plusMinusNVRsSquared);
    }

    public void ClickOnIPAddressByIndex(int index){
    	JavaScriptClick(devicesListCentralPanel.get(index));
    }

    public WebElement FindDeviceByText(String textIP){
        WebElement element= null;
        try{
             element = driver.findElement(By.xpath("//span[@title='"+textIP+"']"));
        }catch (Exception e){}
        return element;
    }

    public boolean DeviceInDiscoveredDevicesIsExist(String textIP){
        boolean flag =false;
        String text = textIP+" :";
        try{
            WebElement element = driver.findElement(By.xpath("//div[@class='col-lg-8 fullSize']//span[contains(text(), '" + text + "')]"));
            flag = true;
        }catch (Exception e){}
        return flag;
    }

    //press on button
    public void PressOnSaveButton() throws InterruptedException {
        waitUntilElementIsClickable(saveSettingButton);
        JavaScriptClick(saveSettingButton);
        new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='fade-in-panel']")));
        Thread.sleep(2000);
    }

    public void PressOnCancelButton(){
        waitUntilElementIsClickable(cancelSettingButton);
        JavaScriptClick(cancelSettingButton);
    }

    public void PressOnRemoveDeviceButton(){
    	JavaScriptClick(RemoveDevicesButton);
    }

    public void PressOnSpinnerUpInCreateDeviceWindow() throws InterruptedException {
    	JavaScriptClick(portUpSpinner);
        Thread.sleep(500);
    }

    public void PressOnSpinnerDownInCreateDeviceWindow(){
    	JavaScriptClick(portDownSpinner);
    }

    public void PressAddDiscoveredDevices(){
        waitUntilElementIsLoaded(addDiscoveredDevicesButton);
        waitUntilElementIsClickable(addDiscoveredDevicesButton);
        JavaScriptClick(addDiscoveredDevicesButton);
    }

    public void PressAddDeviceManuallyButton() throws InterruptedException {
        waitUntilElementIsClickable(addDeviceManuallyButton);
        waitUntilElementIsLoaded(addDeviceManuallyButton);
        Thread.sleep(2000);
        JavaScriptClick(addDeviceManuallyButton);
        try{
            new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUserName")));
        }catch(Exception e){}
    }

    public void PressApplyAndCloseInAddDeviceManually(){
    	JavaScriptClick(applyAndCloseCreateDeviceButton);
    }

    public void PressApplyInAddDeviceManually(){
    	JavaScriptClick(applyCreateDeviceButton);
    }

    public void PressCancelInAddDeviceManually(){
    	JavaScriptClick(cancelCreateDeviceButton);
    }

    public void PressAddToNVRAndClose() throws InterruptedException {
        waitUntilElementIsClickable(addToNVRAndCloseButton);
        JavaScriptClick(addToNVRAndCloseButton);
        Thread.sleep(4000);
    }

    public void PressCloseAddDiscoveredDevicesWindow() throws InterruptedException {
    	JavaScriptClick(closeAddDiscoveredDevicesWindowButton);
    }

    public void PressAddToNVR() throws InterruptedException {
        waitUntilElementIsClickable(addToNVRButton);
        JavaScriptClick(addToNVRButton);
    }

    public void PressCloseAfterAdded() throws InterruptedException {
        WaitUntilDialogIsLocated();
        waitUntilIsLoadedCustomTime(closeSuccessfulyAddedButton);
        waitUntilElementIsClickable(closeSuccessfulyAddedButton);
        JavaScriptClick(closeSuccessfulyAddedButton);
        WaitUntilDialogIsNotLocated();
    }

//    public void PressCloseDialogIcon() throws InterruptedException {
//        WaitUntilDialogIsLocated();
//        waitUntilIsLoadedCustomTime(closeDialogIcon);
//        closeDialogIcon.click();
//        WaitUntilDialogIsNotLocated();
//    }

    public void PressBackAfterAdded() throws InterruptedException {
        try{
            new WebDriverWait(driver, 10).until(ExpectedConditions.attributeContains(backToDiscoveredNVRsButton, "aria-disabled", "false"));
        }catch (Exception e){}
        JavaScriptClick(backToDiscoveredNVRsButton);
        Thread.sleep(1000);
    }

    public void PressOnIPAdressButton(){
    	JavaScriptClick(IPAdressButton);
    }

    public void PressOnNameButton(){
    	JavaScriptClick(nameButton);
    }

    public void PressOnVendorButton(){
    	JavaScriptClick(vendorButton);
    }

    public void PressOnModelButton(){
    	JavaScriptClick( modelButton);
    }

    public void PressOnVersionButton(){
    	JavaScriptClick(versionButton);
    }

    public void ClickOnToggleUserName() throws InterruptedException {
        waitUntilElementIsClickable(userNameOnOffToggler);
        waitUntilElementIsLoaded(userNameOnOffToggler);
        JavaScriptClick(userNameOnOffToggler);
    }

    public void PressOnSpinnerDown() throws InterruptedException {
    	JavaScriptClick(portDownPropertiesSpinner);
        Thread.sleep(300);
    }

    public void PressOnSpinnerUP() throws InterruptedException {
    	JavaScriptClick(portUpPropertiesSpinner);
        Thread.sleep(300);
    }

    //inputs
    public void InputIntoNameField(String name){
        setElementText(namePropertiesField, name);
    }

    public void InputIntoIPAdressPropertiesField(String ip){
        setElementText(ipAddressPropertiesField, ip);
    }

    public void InputIntoPortPropertiesField(String port){
        setElementText(portPropertiesField, port);
    }

    public void InputIntoUserNamePropertiesField(String userName){setElementText(userNamePropertiesField, userName);    }

    public void InputIntoPasswordPropertiesField(String password){setElementText(passwordPropertiesField, password);    }

    public void InputIntoIPAdressField(String IPAddress){
        waitUntilElementIsLoaded(IPAdressCreateDeviceField);
        while (true){
            setElementText(IPAdressCreateDeviceField, IPAddress);
            String text = IPAdressCreateDeviceField.getAttribute("value");
            if(text.equals(IPAddress)) break;
        }
    }

    public void InputIntoUserNameField(String userName){
        setElementText(userNameCreateDeviceField, userName);
    }

    public void InputIntoPasswordField(String password){
        setElementText(passwordCreateDeviceField, password);
    }

    public void InputIntoPortField(String port) throws InterruptedException {
        waitUntilElementIsClickable(portCreateDeviceField);
        setElementText(portCreateDeviceField, port);
        Thread.sleep(1000);
    }

    public void WaitUntilSaveButtonIsAvaliable(){
        try{
            new WebDriverWait(driver, 10).until(ExpectedConditions.attributeContains( (By.xpath("//div[@class='fullSize new-ui-panel-background ng-scope']//div[@class='flex configuration-details-bottom-buttons-container ng-scope']/button[@title='Save'][@aria-hidden='false']")), "aria-disabled", "false"));
        }catch(Exception e){}
    }

    public void FilterDevicesCentralPanel(String text){
        setElementText(filterDevicesCentralPanelField, text);
    }

    //getters
    public String GetPortFromProperties(){
        return portPropertiesField.getAttribute("value");
    }

    public String GetUserNameFromProperties(){
        return userNamePropertiesField.getAttribute("value");
    }

    public String GetPortFromCreateDeviceWindow(){
        return portCreateDeviceField.getAttribute("value");
    }

    public String GetVendorFromProperties(){
        return vendorPropertiesField.getText();
    }

    public String GetNameFromProperties(){
        return namePropertiesField.getAttribute("value");
    }


    public String GetIPAddressFromProperties(){
        return ipAddressPropertiesField.getAttribute("value");
    }

    public int GetCountDevicesOfNVR(){
        List<WebElement> countOfDeviceOfNVR =driver.findElements(By.xpath("//div[@class='ng-isolate-scope']//div[@class='ui-grid-cell-contents ng-scope']/span[@class='ng-scope']"));
        String nvr = countOfDeviceOfNVR.get(0).getText();
        int firstIndex=nvr.indexOf("(");
        int lastIndex=nvr.indexOf(")");
        int count = Integer.parseInt(nvr.substring(firstIndex+1, lastIndex));
        return count;
    }

    //waits
    public void WaitUntilDeviceIsAdded(int count){
        try{
            new WebDriverWait(driver, 40).until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@class='ui-grid-canvas']//div[@class='ng-isolate-scope']/div[2]//span[@class='ng-binding ng-scope'][not(@role)]"), count));
        }catch(Exception  e){}
    }

    public void WaitUntilDialogWindowIsClosed(){
        try{
            new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='modal-dialog']")));
        }catch(Exception  e){}
    }

    public void waitUntilStatusIsText(String IPAdress, String text)  {
        try {
            new WebDriverWait(driver, 40).until(ExpectedConditions.textToBe(By.xpath("//span[contains(@title, '" + IPAdress+ "')][@class='ng-binding ng-scope']/../../../div[last()]/div/div"), text));
        } catch (Exception e) {}
    }

    public void waitUntilStatusIsV(String IPAdress)  {
        try {
            new WebDriverWait(driver, 100).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@title, '" + IPAdress + "')][@class='ng-binding ng-scope']/../../../div[last()]/div/i")));
        } catch (Exception e) {  }
    }

    //checks
    public boolean IpAlreadyInUseNotificationExist(){
        return verifyElementIsPresent(ipAlreadyInUseNotification);
    }

    public boolean CancelButtonIsEnable(){
        waitUntilIsLoadedCustomTime(cancelSettingButton);
        String disabled = cancelSettingButton.getAttribute("aria-disabled");
        if (disabled.equals("false")  ) return true;
        else{
            return false;
        }
    }

    public boolean SaveButtonIsEnable(){
        waitUntilIsLoadedCustomTime(saveSettingButton);
        String disabled = saveSettingButton.getAttribute("aria-disabled");
        if (disabled.equals("false")  ) return true;
        else{
            return false;
        }
    }

    public String GetDeviceWithStatusV(String NVRAddress) throws InterruptedException {//methods select device with status Online and if device not exist - add it
        WebElement element=null;
        int max = CountOfdevice();
        String name = null;
        if(max>0){
            for(int i=0; i<max; i++){
                WebElement ElemenetIndexI=devicesListCentralPanel.get(i);
                String StatusV = CheckStatusDevice(ElemenetIndexI.getText());
                if(StatusV.equals("V")){
                    element=ElemenetIndexI;
                    break;
                }
                if(i==max-1 && element==null){
//                String newDevice = AddDevice(NVRAddress);
//                waitUntilStatusIsV(newDevice);
//                max++;
//                i=0;
                    return null;
                }
            }
            name= element.getText();
        }
        return name;
    }

    public int GetSizeNVRsList(){
        return NVRsList.size();
    }

    public String GetTextNVRsList(int index){
        return NVRsList.get(index).getText();
    }

    public int GetSizeDiscoveredDevicesList(){
        return devicesListAddDiscoveredDevicesWindow.size();
    }

    public String GetTextDiscoveredDevicesList(int index){
        return devicesListAddDiscoveredDevicesWindow.get(index).getText();
    }

    public WebElement GetDiscoveredDevicesList(int index){
        return devicesListAddDiscoveredDevicesWindow.get(index);
    }

    public void ClickOnVDiscovereDevices(int index){
        MoveToElement(VListAddDiscoveredDevicesWindow.get(index));
        waitUntilElementIsLoaded(VListAddDiscoveredDevicesWindow.get(index));
        JavaScriptClick(VListAddDiscoveredDevicesWindow.get(index));
    }

    public int GetSizeVDiscoveredDevicesList(){
        return VListAddDiscoveredDevicesWindow.size();
    }

    public String GetModelDevicesList(int index){
        return modelListAddDiscoveredDevicesWindow.get(index).getText();
    }

    public int GetSizeModelsDevicesList(){
        return modelListAddDiscoveredDevicesWindow.size();
    }

    public WebElement GetElementModelDevicesList(int index){
        return modelListAddDiscoveredDevicesWindow.get(index);
    }

    public String GetTextDeviceListCentralPanel(int index){
        return devicesListCentralPanel.get(index).getText();
    }

    public String GetNameDevicesList(int index){
        return devicesNameListCentralPanel.get(index).getText();
    }

    public String GetVendorDevicesList(int index){
        return deviceVendorListCentralPanel.get(index).getText();
    }

    public String GetDeviceModelList(int index){
        return deviceModelListCentralPanel.get(index).getText();
    }

    public String GetDeviceVersionList(int index){
        return deviceVersionListCentralPanel.get(index).getText();
    }

    public void WaitUntilCreateDeviceManuallyLayoutIsExist(){
        try{
            new WebDriverWait(driver, 30).until(ExpectedConditions.attributeContains(protocolDropDownList, "aria-disabled", "false"));
        }catch(Exception e){}
    }

    public boolean NoNVRsHaveBeenAddedToTheSystem(){
        return verifyElementIsPresent(NoNVRsHaveBeenAddedToTheSystemTest);
    }

    public void WaitUntilDeviceIsAddedInDiscovered(String IPAdress) throws InterruptedException {
        for(int i=0; i<4; i++){
            if(DeviceInDiscoveredDevicesIsExist(IPAdress)){
                break;
            }
            refreshDiscoveredIcon.click();
            new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='col-lg-8 fullSize']//div[@class='ui-grid-canvas']")));
            OpenDiscoveredDeviceListIfItIsClose();
        }
    }

    public int GetVDevicesSize(){
        return VDevicesListCentralPanel.size();
    }

    public void ClickOnVDeviceByIndex(int index){
        VDevicesListCentralPanel.get(index).click();
    }

    public boolean CommunicationFailureDialogIsExist() throws InterruptedException {
        try{
            new WebDriverWait(driver, 20).until(ExpectedConditions.attributeContains( (By.xpath("//body")), "class", "modal-open"));
        }catch(Exception e){}
        Thread.sleep(1000);
        return verifyElementIsPresent(communicationFailureDialog);
    }

    public boolean DeviceNotAuthorizedDialogIsExist(){
        try{
            new WebDriverWait(driver, 20).until(ExpectedConditions.attributeContains( (By.xpath("//body")), "class", "modal-open"));
        }catch(Exception e){}
        return verifyElementIsPresent(deviceNotAuthorizedDialog);
    }


}
