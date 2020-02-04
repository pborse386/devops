package pageObjects;

import Utilities.Page;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;
import javax.swing.text.Document;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.openqa.grid.common.SeleniumProtocol.Selenium;


public class NVRsPage extends Page {

    //fields
    @FindBy(id = "config-menu-devices-nvrs")
    WebElement NVRsButton;

    @FindBy(xpath = "//*[@title='NVRs'][@class='item-text ng-binding']")
    WebElement NVRsButtonLandingPage;
    
    @FindBy(xpath = "//*[@id=\\\"avms-configuration-container\\\"]/div/div/ui-view/div/div/div[1]/div[2]/div[2]/span[1]/span]")
    WebElement NVRsButtonLandingPage1;
    

    @FindBy(xpath = "//div[@id='panel-heading']//h4[@class='panel-title']//div[@class='accordion-toggle'][@role='button']")
    WebElement networkDevicesAccordionToggle;

    //central panel

    @FindBy(xpath = "//button[@id = 'configuration-nvr-add-unassociated-btn']/span[2]")
    WebElement countDiscoveredNVRsSpan;

    @FindBy(xpath = "//div[@id='vms-nvr']//div[@class='vms-search-with-btn']//input")
    WebElement filterField;

    @FindBy(xpath = "//div[@class='ng-scope sortable']//span[contains(text(), 'IP Address')]/..")
    WebElement sortByIPAdressSpinner;

    @FindBy(xpath = "//div[@class='ng-scope sortable']//span[contains(text(), 'Name')]/..")
    WebElement sortByNameButton;

    @FindBy(xpath = "//div[@class='ng-scope sortable']//span[contains(text(), 'Version')]/..")
    WebElement sortByVersionButton;

    //discovered NVRs window
    @FindBy(xpath="//button[@title='Add Discovered NVRs']")
    WebElement addDiscoveredNVRsButton;

    @FindBy(xpath = "//div[@class = 'ui-grid-header-cell-row']//i")
    WebElement sortDiscoveredNVRsSpinner;

    @FindBy(xpath = "//form[@class='ng-pristine ng-valid']/input")
    WebElement filterFieldInsideDiscoveredNVRsWindow;

    @FindBy(xpath = "//div[@id='unassosiated_nvrs']//div[@class='vms-popup-btn-container']/button[1]")
    WebElement applyButtonDiscoveredNVRsWindow;

    @FindBy(xpath = "//div[@id='unassosiated_nvrs']//div[@class='vms-popup-btn-container']/button[2]")
    WebElement applyAndCloseButtonDiscoveredNVRsWindow;

    
    @FindBy(xpath="//*[@id=\"unassosiated_nvrs\"]/div[1]/div/span")
   // @FindBy(xpath = "//div[@id='unassosiated_nvrs']//div[@class='vms-popup-btn-container']/button[3]")
    WebElement cancelButtonDiscoveredNVRsWindow;

    @FindBy(xpath = "//div[@class='vms-modal-popup-header']/button[@class='vms-configuration-btn-refresh pull-left btn btn-vms top-left-icon-align']")
    WebElement refreshDiscoveredNVRsWindow;

    //AddNVRManually
    @FindBy(xpath="//button[@title='Add NVR Manually']")
    WebElement addNVRManuallyButton;

    @FindBy(xpath="//div[@id='vms-child-layer']//input[@id='inputAddress']")
    WebElement IpAdressAddNVRManuallyInput;

    @FindBy(xpath = "//div[@class='vms-modal-popup-content']//input[@role='spinbutton']")
    WebElement portAddNVRManuallyInput;

    @FindBy(xpath = "//a[@class='ui-spinner-button ui-spinner-up ui-corner-tr']")
    WebElement portUPAddNVRManuallySpinner;

    @FindBy(xpath = "//a[@class='ui-spinner-button ui-spinner-down ui-corner-br']")
    WebElement portDownAddNVRManuallySpinner;

    @FindBy(xpath = "//div[@id='vms-child-layer']//div[@class='configuration-ditails-form']/md-switch")
    WebElement securedAddNVRManuallySwitch;

    @FindBy(id = "inputUserName")
    WebElement UserNameAddNVRManuallyInput;

    @FindBy(id = "inputPasswords")
    WebElement PasswordAddNVRManuallyInput;

   
    @FindBy(xpath="//*[@id=\"vms-child-layer\"]/div/div/div/div[1]/div/span")
    
    //@FindBy(xpath = "//button[@title='Close']") changes made
    WebElement closeAddNVRManuallyWindowButton;
    
    

    @FindBy(xpath = "//button[@title='Apply and close']")
    WebElement applyAndCloseAddNVRManuallyWindowButton;

    @FindBy(xpath = "//div[@class='vms-popup-btn-container']/button[1]")
    WebElement applyAddNVRManuallyWindowButton;

    @FindBy(xpath = "//div[@class='fullSize vms-newui-background devices-new-ui addNVR ng-scope']/div/div[@class='ng-binding']")
    WebElement ipAlreadyInUseNotification;

    //RemoveNVRsButton
    @FindBy(xpath="//button[@title='Remove NVRs']")
    WebElement removeNVRsButton;

    //RightPanel

    //Properties

    @FindBy(xpath = "//div[@class='bottom-separator-builder new-ui-details-header ng-scope']//i[@class='glyphicon glyphicon-chevron-down']")
    WebElement propertiesToDropDownMenu;

    @FindBy(id= "inputName")
    public WebElement inputNameField;

    @FindBy(id = "inputAddress")
    public WebElement inputIPAdressField;

    @FindBy(xpath = "//span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']//input")
    WebElement inputPortField;

    @FindBy(xpath = "//span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']//input[@class='form-control ng-pristine ng-valid ng-scope ui-spinner-input ng-touched']")
    WebElement inputPortAfterChangedField;

    @FindBy(xpath = "//div[@class='nvr-details-container']//span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']//a[@class='ui-spinner-button ui-spinner-up ui-corner-tr']")
    WebElement portUpSpinner;

    @FindBy(xpath = "//div[@class='nvr-details-container']//span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-down ui-corner-br']")
    WebElement portDownSpinner;

    @FindBy(xpath = "//ng-form/div[@class='view-details-container flex-between'][7]//p[@class='ng-binding']")
    WebElement macAdressField;

    @FindBy(xpath = "//ng-form/div[@class='view-details-container flex-between'][8]//p[@class='ng-binding']")
    WebElement versionAdressField;

    
    //@FindBy(xpath="//*[@id=\"vms-nvr\"]/div[2]/div/div/div[1]/div[2]/div/ng-form/div[6]/div[2]/md-switch/div[1]/div[1]")
    @FindBy(xpath = "//div[@class='view-details-value-container flex-between']/md-switch")
    WebElement securedSwitch;

    @FindBy(xpath = "//div[@class='view-details-label-toggle-switch-container']/md-switch/div[@class='md-container']")
    WebElement userNameOnOffSwitch;

    @FindBy(id = "inputUserName")
    WebElement inputUserNameField;

    @FindBy(id = "inputPasswords")
    WebElement inputPasswordField;

    //Storage Definitions
    @FindBy(xpath = "//div[@class='nvr-details-main-container ng-scope']/div[@class='nvr-details-toggle-container flex']//i")
    WebElement storageDefinitionsToDropDownMenu;

    @FindBy(xpath = "//button[@class='blue-button-new-ui pull-right ng-isolate-scope'][title='Assign All Drives']")
    WebElement assignAllDrivesButton;

    @FindBy(xpath = "//div[@class='nvr-details-storage-definitions-properiy-toggle-switch-container']//md-switch")
    public WebElement StorageOnOffSwitch;

    @FindBy(xpath = "//div[@class='nvr-details-storage-definitions-properiy-vidget-capacity-spinner-container']//span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-up ui-corner-tr']")
    WebElement storageUpSpinner;

    @FindBy(xpath = "//div[@class='nvr-details-storage-definitions-properiy-vidget-capacity-spinner-container']//span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-down ui-corner-br']")
    WebElement storageDownSpinner;

    @FindBy(xpath = "//div[@class='nvr-storage-definitions-container is-short']//div[@class='vms-slider-container ng-scope']//span[@class='ui-slider-handle ui-state-default ui-corner-all']")
    WebElement storageSliderHandle;

    @FindBy(xpath = "//div[@class='nvr-details-storage-definitions-properiy-vidget-capacity-spinner-container']//span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']/input")
    WebElement storageInput;

    @FindBy(xpath = "//div[@class='new-ui-panel-background vms-bottom-btn-container-new']//button[@class='big-blue-button-new-ui ng-isolate-scope'][@title='Change NVR Settings']")
    WebElement remoteSEttingButton;

    @FindBy(xpath = "//div[@id='vms-export-container']//button[@ng-click='confirmModalCtrl.yes()']")
    WebElement confirmSwitchOffStorageButton;

    @FindBy(xpath = "//div[@id='vms-export-container']//button[contains(text(), 'No')]")
    WebElement cancelSwitchOffStorageButton;

    @FindBy(xpath = "//div[@class='nvr-details-right-panel ng-scope']/div[@class='flex configuration-details-bottom-buttons-container ng-scope']/button[2]")
    WebElement cancelSettingButton;

    @FindBy(xpath = "//div[@class='nvr-details-right-panel ng-scope']/div[@class='flex configuration-details-bottom-buttons-container ng-scope']/button[3]")
    WebElement saveSettingButton;

    @FindBy(xpath = "//div[@class='ui-grid-contents-wrapper']//div[@class='ui-grid-viewport ng-isolate-scope']/div[@class='ui-grid-canvas']//span[@class='ui-grid-cell-contents ng-binding ng-scope']")
    List<WebElement> NVRsListCentralPanel;

    @FindBy(xpath = "//div[@id='unassosiated_nvrs']//div[@class='ui-grid-contents-wrapper']//div[@class='ui-grid-canvas']/div[@class='ui-grid-row ng-scope']/div[@class='ng-isolate-scope'][@role='row']/div[1]//span[@class='text-overflow ng-binding']")
    List<WebElement> NVRsDiscoveredNVRsWindowList;

    @FindBy(xpath = "//div[@id='unassosiated_nvrs']//div[@class='ui-grid-canvas']/div[@class='ui-grid-row ng-scope ui-grid-row-selected']//span[@title='Select IP Address']")
    WebElement selectIPAddressIcon;

    @FindBy(xpath = "//div[@id='unassosiated_nvrs']//div[@class='ui-grid-viewport ng-isolate-scope']//div[@class='ng-isolate-scope'][@role='row']//div[@class='select-resourse-toggle']/div[contains(text(), '10.10.')][@class!='select-resourse-text ng-binding']")
    List<WebElement> NVRsFilteredDiscoveredNVRsWindowList;

    @FindBy(xpath = "//div[@class='ui-grid-viewport ng-isolate-scope']/div[@class='ui-grid-canvas']/div[@class='ui-grid-row ng-scope']//div[@class='ui-grid-cell-contents']/div[@role='button']")
    List<WebElement> VNVRsDiscoveredNVRsWindowList;

    @FindBy(xpath = "//div[@class='ui-grid-canvas']//div[@class='ng-isolate-scope']/div[2]/div")
    List<WebElement> nameCentralPanelList;

    @FindBy(xpath = "//div[@class='ui-grid-canvas']//div[@class='ng-isolate-scope']/div[3]/div")
    List<WebElement> versionCentralPanelList;

    //Constructor
    public NVRsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //Methods
    public void goToNVRsPage() throws InterruptedException  {
        Thread.sleep(1000);
      //  try{
            GoToConfigurationPage();
            Thread.sleep(1000);
//            waitUntilElementIsLoaded(NVRsButton);
            ClickOnNVRButton();
    //}
    //    catch(Exception e){
     //       takeScreenshot(driver, "Setup", "GoToVideoChannelsPage"+GetRandomDigit(0, 100));
   //     }
    }

    public void goToNVRsPageFromLandingPage() throws InterruptedException, IOException {
        Thread.sleep(1000);
        GoToConfigurationPage();
        Thread.sleep(2000);
        ClickOnNVRButtonOnLandingPage();
    }
    
    
    public void goToNVRsPageFromLandingPage1() throws Exception {
        Thread.sleep(1000);
        GoToConfigurationPage();
        Thread.sleep(1000);
        ClickOnNVRButtonOnLandingPage1();
    }

    public void ClickOnNVRButton() throws InterruptedException  {
        boolean breakIt = true;
        int time = 0;
        while (true) {
            breakIt = true;
          //  try {
              
            
               NVRsButton.click();
            
           
                Thread.sleep(500);
          //  } catch (Exception e) {
          //      if (e.getMessage().contains("Unable to find element")) {
          //          breakIt = false;
          //      }
         //   }
            time++;
            if (breakIt || time>10) {
                break;
            }
        }
        Thread.sleep(500);
    }

    public void ClickOnNVRButtonOnLandingPage() throws InterruptedException {
        boolean breakIt = true;
        int time = 0;
        while (true) {
            breakIt = true;

            try {
				safeJavaScriptClick(NVRsButtonLandingPage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            Thread.sleep(500);

            time++;
            if (breakIt || time>10) {
                break;
            }
        }
        Thread.sleep(500);
    }
    
    public void ClickOnNVRButtonOnLandingPage1() throws Exception {
        boolean breakIt = true;
        int time = 0;
        while (true) {
            breakIt = true;

            safeJavaScriptClick(NVRsButtonLandingPage1);
            Thread.sleep(500);

            time++;
            if (breakIt || time>10) {
                break;
            }
        }
        Thread.sleep(500);
    }
    
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
    
    

    public boolean StorageDefDropDownIsClosed(){
         return  storageDefinitionsToDropDownMenu.getAttribute("class").equals("glyphicon glyphicon-chevron-right");
    }

    public void ClickOnStorageDefinDropDownMenu() throws InterruptedException {
        waitUntilElementIsLoaded(storageDefinitionsToDropDownMenu);
        storageDefinitionsToDropDownMenu.click();
        Thread.sleep(1000);
    }

    public void CheckAndSwitchOnStorageDefinition() throws InterruptedException {
        waitUntilElementIsLoaded(StorageOnOffSwitch);
        if(!SwitchStorageDefinitionsIsOn()) {
            ClickOnStorageSwitch();
            SaveChangesButton();
        }
    }

    public void ClickOnStorageSwitch(){
        waitUntilElementIsLoaded(StorageOnOffSwitch);

        boolean breakIt = true;
        while (true){
            breakIt = true;
            try {
            	JavaScriptClick(StorageOnOffSwitch);
            } catch (Exception e) {
                if (e.getMessage().contains("element is not attached")) {
                    breakIt = false;
                }
            }
            if (breakIt) {
                break;
            }
        }
    }

    public boolean SwitchStorageDefinitionsIsOn(){
        return StorageOnOffSwitch.getAttribute("aria-checked").contains("true");
    }

    public void InsertIntoStorageDefinitionsField(String number){
        waitUntilElementIsLoaded(StorageOnOffSwitch);
        setElementText(storageInput, number);
    }

    public void ConfirmSwitchOffStorageDifinitions() throws InterruptedException {
        WaitUntilDialogIsLocated();
        waitUntilElementIsLoaded(confirmSwitchOffStorageButton);
        driver.switchTo().window(driver.getWindowHandle());
        Thread.sleep(1000);
        JavaScriptClick(confirmSwitchOffStorageButton);
//        WaitUntilDialogIsNotLocated();
    }

    public void CancelSwitchOffStorageDifinitions(){
        driver.switchTo().window(driver.getWindowHandle());
        waitUntilIsLoadedCustomTime(cancelSwitchOffStorageButton);
        JavaScriptClick(cancelSwitchOffStorageButton);
    }

    public void ChangeName(String name){
        setElementText(inputNameField, name);
    }

    public void ChangeIPAdress(String ip){
    	JavaScriptClick(inputIPAdressField);
    	inputIPAdressField.clear();
        inputIPAdressField.sendKeys(ip);
//        setElementText(inputIPAdressField, ip);
    }

    public void ChangePort(String port) throws InterruptedException {
        Thread.sleep(1000);
        waitUntilElementIsClickable(inputPortField);
        setElementText(inputPortField, port);
    }

    public void SaveChangesButton() throws InterruptedException {
//        waitUntilElementIsClickable(saveSettingButton);
        new WebDriverWait(driver, 15).until(ExpectedConditions.attributeContains(saveSettingButton, "aria-disabled", "false"));
        JavaScriptClick(saveSettingButton);
        Thread.sleep(2000);
    }

    public void CancelChangesButton(){
        new WebDriverWait(driver, 15).until(ExpectedConditions.attributeContains(cancelSettingButton, "aria-disabled", "false"));
        JavaScriptClick(cancelSettingButton);
    }

    public WebElement FindNVRByText(String textIP){
        WebElement element = null;
        try{
            element = driver.findElement(By.xpath("(//span[contains(text(), '" + textIP + "')][@class='ui-grid-cell-contents ng-binding ng-scope'])"));
        } catch (NoSuchElementException e) { }
        catch (ElementNotVisibleException e) { }
        return element;
    }

    public void ClickOnNvrByText(String textIP){
        JavaScriptClick(FindNVRByText(textIP));
    }

    public String GetNVRName(String NVRtext){
//       WebElement nameField = driver.findElement(By.xpath("//span[contains(text(), '" + NVRtext + "')]" + "/../../div[2]/div"));
       return inputNameField.getAttribute("value");
    }

    public String GetIPAdress(String NVRtext){
        //        WebElement ipField = driver.findElement(By.xpath("//span[contains(text(), '" + NVRtext + "')]" + "/../../div[1]/span"));
        return inputIPAdressField.getAttribute("value");
    }

    public String GetStatus(String IpText){
        WebElement  status;
        status = driver.findElement(By.xpath("(//*[contains(text(), '" + IpText + "')] | //*[@value='" + IpText + "'])" +
                "/../../div[4]/div/span/*"));
        String tag = status.getTagName();
        if (tag.equals("i")){return "V"; }
        else    return status.getText();
    }

    public String CheckStatusNoStorageSettings(String IpText){
        WebElement noStorageDefinition = driver.findElement(By.xpath("//span[contains(text(), '" + IpText + "')][@class='ui-grid-cell-contents ng-binding ng-scope']/../../div[4]//span[contains(text(), ' No Storage Settings')]"));

        try{if(noStorageDefinition.getAttribute("aria-hidden").equals("false")){
            return "No Storage Settings";
             }
        }catch (Exception e){}

        return "";
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

    public void WaitUntilSaveButtonIsAvaliable(){
        try{
            new WebDriverWait(driver, 10).until(ExpectedConditions.attributeContains( (By.xpath("//div[@class='nvr-details-right-panel ng-scope']/div[@class='nvr-details-btn-container flex ng-scope']/button[3]")), "aria-disabled", "false"));
        }catch(Exception e){}
    }

    public String GetNameFromProperties(){
        return inputNameField.getAttribute("value");
    }


    public String GetIPAdressFromProperties(){
         return inputIPAdressField.getAttribute("value");
    }

    public String GetPortFromProperties(){
         return inputPortField.getAttribute("aria-valuenow");
    }

    public String GetPortFromAddManuallyWindow(){return portAddNVRManuallyInput.getAttribute("aria-valuenow");}

    public String GetMACAdressFromProperties(){
        return macAdressField.getText();
    }

    public String GetVersionFromProperties(){
        return versionAdressField.getText();
    }

    public String GetUserNameFromProperties(){
        return inputUserNameField.getAttribute("value");
    }

     public String GetStorageFromStorageProperties() throws InterruptedException {
        String value = null;
        try{
            value = storageInput.getAttribute("value");
        }catch (StaleElementReferenceException e){
            Thread.sleep(1000);
            value = storageInput.getAttribute("value");
        }
        return value;
    }

    public void PressOnRemoveIcon(String IPAdress){
        WebElement remove = driver.findElement(By.xpath("//span[contains(text(), '" + IPAdress  + "')]/../../div[5]/button"));
        JavaScriptClick(remove);//remove.click();
    }

    public void PressOnRemoveNVRsByButton() throws InterruptedException {
        waitUntilElementIsLoaded(removeNVRsButton);
        waitUntilElementIsClickable(removeNVRsButton);
        //Thread.sleep(2000);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",removeNVRsButton);
        removeNVRsButton.click();
        try{
            new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='dialog']") ));//????????
        }catch(Exception e){}
    }

    public void ConfirmRemoveNVRsByButton() throws InterruptedException {
        int count = NVRsCount();
                                  
         driver.switchTo().window(driver.getWindowHandle());
        ConfirmRemoving();
        try{        new WebDriverWait(driver, 40).until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@class='ui-grid-contents-wrapper']//div[@class='ui-grid-viewport ng-isolate-scope']/div[@class='ui-grid-canvas']//span[@class='ui-grid-cell-contents ng-binding ng-scope']"), count-1));
        }catch(Exception e){}
    }

    public void WaitUntilCountOfNVRsIs(int count){
        try{
            new WebDriverWait(driver, 10).until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@class='ui-grid-contents-wrapper']//div[@class='ui-grid-viewport ng-isolate-scope']/div[@class='ui-grid-canvas']//span[@class='ui-grid-cell-contents ng-binding ng-scope']"), count));
        }catch(Exception e){}
    }

    public void CancelRemoveNVRsByButton() throws InterruptedException{
        driver.switchTo().window(driver.getWindowHandle());
        CancelRemoving();
    }

//    public void AddDiscoveredNVRsButton() throws InterruptedException {
//        waitUntilElementIsLoaded(addDiscoveredNVRsButton);
//        waitUntilElementIsClickable(addDiscoveredNVRsButton);
//        addDiscoveredNVRsButton.click();
//        new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated (By.xpath("//div[@id='unassosiated_nvrs']//div[@class='ui-grid-contents-wrapper']//div[@class='ui-grid-canvas']/div[@class='ui-grid-row ng-scope']")));
//    }

    public WebElement GetDiscoveredNVRsList(int index){
        return NVRsDiscoveredNVRsWindowList.get(index);
    }


    public void ClickOnAddDiscoveredNVRSButton(){
        boolean flag = false;
        while(!flag){
        	JavaScriptClick(addDiscoveredNVRsButton);
            if(CheckThatModalIsOpen()) flag = true;
        }
        try{
            new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated (By.xpath("//div[@id='unassosiated_nvrs']//div[@class='ui-grid-contents-wrapper']//div[@class='ui-grid-canvas']/div[@class='ui-grid-row ng-scope']")));
        }catch (Exception e){}
    }

    public void SelectDiscoveredNVRs(String IpText){
    	JavaScriptClick(FindElementByText(IpText));
    }

    public void ApplyAndCloseButtonDiscovereNVRs() throws InterruptedException {
        waitUntilElementIsClickable(applyAndCloseButtonDiscoveredNVRsWindow);
        JavaScriptClick(applyAndCloseButtonDiscoveredNVRsWindow);
        Thread.sleep(3000);
    }
    public void ApplyButtonDiscovereNVRs() throws InterruptedException {
        applyButtonDiscoveredNVRsWindow.click();
        new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated (By.xpath("//div[@id='unassosiated_nvrs']//div[@class='ui-grid-contents-wrapper']//div[@class='ui-grid-canvas']/div[@class='ui-grid-row ng-scope']")));
    }

    public void CancelButtonDiscovereNVRs() throws InterruptedException {
        waitUntilIsLoadedCustomTime(cancelButtonDiscoveredNVRsWindow);
        waitUntilElementIsClickable(cancelButtonDiscoveredNVRsWindow);
        JavaScriptClick(cancelButtonDiscoveredNVRsWindow);
    }

    public void waitUntilStatusIsV(String IPAdress)  {
        try {
            new WebDriverWait(driver, 35).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), '" + IPAdress + "')][@class='ui-grid-cell-contents ng-binding ng-scope']/../../div[4]/div/span/i")));
        } catch (ElementNotVisibleException e) {
        }catch (TimeoutException e) { }
    }

    public void waitUntilNoStorageSettingIsExist(String IPAdress) throws InterruptedException {
        WebElement noStorageDefinition = driver.findElement(By.xpath("//span[contains(text(), '" + IPAdress + "')][@class='ui-grid-cell-contents ng-binding ng-scope']/../../div[4]//span[contains(text(), ' No Storage Settings')]"));
        try {
            new WebDriverWait(driver, 120).until(ExpectedConditions.attributeContains(noStorageDefinition,"aria-hidden", "false"));
        }catch (Exception e) { }
    }

    public void waitUntilNoStorageSettingisNotExist(String IPAdress)  {
        WebElement noStorageDefinition = driver.findElement(By.xpath("//span[contains(text(), '" + IPAdress + "')][@class='ui-grid-cell-contents ng-binding ng-scope']/../../div[4]/span[contains(text(), ' No Storage Settings')]"));
        try {
            new WebDriverWait(driver, 15).until(ExpectedConditions.attributeContains(noStorageDefinition,"aria-hidden", "true"));
        }catch (Exception e) { }
    }

    public void waitUntilStatusIsText(String IPAdress, String text)  {
        try {
            new WebDriverWait(driver, 40).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), '" + IPAdress + "')][@class='ui-grid-cell-contents ng-binding ng-scope']/../../div[4]//span[contains(text(), '"+ text +"')]")));
        } catch (Exception e) {}
    }

    public int NVRsCount() throws InterruptedException {
        Thread.sleep(2000);
        int size = NVRsListCentralPanel.size();
        return size;
    }

    public int getDiscoveredNVRsCountFromButton(){
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(countDiscoveredNVRsSpan));
        int lastIndex = countDiscoveredNVRsSpan.getText().length()-1;
        int count = Integer.parseInt(countDiscoveredNVRsSpan.getText().substring(1,lastIndex));
        return count;
    }

    public void WaitUntilRemovedNVRWillBeAddedToDiscoveredNVRsList(){
       try{
           int count = getDiscoveredNVRsCountFromButton();
           new WebDriverWait(driver, 30).until(ExpectedConditions.textToBePresentInElement(countDiscoveredNVRsSpan, "("+(count+1)+")" ));
       }catch(Exception e){}

    }

    public void WaitUntilCountOfDiscoveredNVRsIsVIsible(String IPAdress) throws InterruptedException {
        for(int i=0; i<4; i++){
            if(verifyElementIsPresent(FindElementByText(IPAdress))){
                break;
            }
            refreshDiscoveredNVRsWindow.click();
            new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class = 'ui-grid-canvas']")));
        }
    }

    public void WaitUntilSelectIpAddressDialogIsClosed() throws InterruptedException {
        try{
            new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id = 'vms-child-layer']//span[text()='Select IP Address']")));
        }catch(Exception e){}
    }

    public void InputIntoFilter(String IpText) throws InterruptedException {
        waitUntilIsLoadedCustomTime(filterField);
        filterField.sendKeys(IpText);
        Thread.sleep(1000);
    }

    public void FilterDiscoveredNVRs(String IpText) throws InterruptedException {
        filterFieldInsideDiscoveredNVRsWindow.sendKeys(IpText);
        Thread.sleep(1000);
    }

    public boolean DiscoveredNVRsWindowIsOpen(){
        return verifyElementIsPresent(refreshDiscoveredNVRsWindow);
    }

    public boolean AddNVRManuallyWindowIsOpen(){
        return verifyElementIsPresent(closeAddNVRManuallyWindowButton);
    }

    public void SortDiscoveredNVRs(){
        sortDiscoveredNVRsSpinner.click();
    }

    public void SortNVRsByIPAdress(){sortByIPAdressSpinner.click();}

    public void SortNVRsByName(){
        sortByNameButton.click();
    }

    public void SortNVRsByVersion(){
        sortByVersionButton.click();
    }

    public boolean ApplyButtonIsEnable(){
        String enabled = applyButtonDiscoveredNVRsWindow.getAttribute("aria-disabled");
        if (enabled.equals("false")  ) return true;
        else{
            return false;
        }
    }

    public boolean ApplyAndCloseButtonIsEnable(){
        String enabled = applyAndCloseButtonDiscoveredNVRsWindow.getAttribute("aria-disabled");
        if (enabled.equals("false")  ) return true;
        else{
            return false;
        }
    }

    public void PressAddNVRManuallyButton() throws InterruptedException {
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@ng-click = 'nvrs.createNVRModal()']") ));
//        Thread.sleep(2000);
        JavaScriptClick(addNVRManuallyButton);
        WaitUntilDialogIsLocated();
        waitUntilElementIsLoaded(IpAdressAddNVRManuallyInput);
    }

    public void InputIntoIPAdressField(String IPAdress){
        waitUntilIsLoadedCustomTime(IpAdressAddNVRManuallyInput);
        while(true){
            setElementText(IpAdressAddNVRManuallyInput, IPAdress) ;
            String text = IpAdressAddNVRManuallyInput.getAttribute("value");
            if(text.equals(IPAdress)) break;
        }
    }

    public void InputIntoPortField(String port) throws InterruptedException {
        waitUntilElementIsClickable(portAddNVRManuallyInput);
        setElementText(portAddNVRManuallyInput, port);
        Thread.sleep(1000);
    }

    public void InputIntoUserNameField(String admin){
        setElementText (UserNameAddNVRManuallyInput, admin);
    }

    public void InputIntoPasswordField(String password){
        setElementText (PasswordAddNVRManuallyInput, password);
    }

    public void PressApplyAndCloseButtonInAddNVRManually(){
    	JavaScriptClick(applyAndCloseAddNVRManuallyWindowButton);
    }

    public void PressApplyButtonInAddNVRManually(){
    	JavaScriptClick(applyAddNVRManuallyWindowButton);
    }

    public void PressCloseButtonInAddNVRManually(){
    	JavaScriptClick(closeAddNVRManuallyWindowButton);
    }

    public boolean VerifyIpAlreadyInUseNotification(){
       return verifyElementIsPresent(ipAlreadyInUseNotification);
    }

    public boolean ApplyButtonInAddNVRManuallyIsEnable(){
        String enabled = applyAddNVRManuallyWindowButton.getAttribute("aria-disabled");
        if (enabled.equals("false")  ) return true;
        else{
            return false;
        }
    }

    public boolean ApplyAndCloseButtonInAddNVRManuallyIsEnable(){
        String enabled = applyAndCloseAddNVRManuallyWindowButton.getAttribute("aria-disabled");
        if (enabled.equals("false")  ) return true;
        else{
            return false;
        }
    }

    public void IncreasePortInAddNVRManuallyWindow(){
    	JavaScriptClick(portUPAddNVRManuallySpinner);
    }

    public void DecreasePortInAddNVRManuallyWindow(){
    	JavaScriptClick(portDownAddNVRManuallySpinner);
    }

    public void ClickOnPortUpSpinner() throws InterruptedException {
    	JavaScriptClick(portUpSpinner);
        Thread.sleep(500);

    }
    public void ClickOnPortDownSpinner() throws InterruptedException {
    	JavaScriptClick(portDownSpinner);
        Thread.sleep(500);
    }

    public void OpenUserName() throws InterruptedException {
        waitUntilElementIsClickable(userNameOnOffSwitch);
        waitUntilElementIsLoaded(userNameOnOffSwitch);
        JavaScriptClick(userNameOnOffSwitch);
        Thread.sleep(1000);
    }

    public void ChangeUserName(String UserName) throws InterruptedException {
        try{setElementText(inputUserNameField, UserName);
        }catch(org.openqa.selenium.InvalidElementStateException e){
            OpenUserName();
            setElementText(inputUserNameField, UserName);
        }
    }

    public void ChangePassword(String password) throws InterruptedException {
        waitUntilElementIsLoaded(inputPasswordField);
        setElementText(inputPasswordField, password);
        Thread.sleep(1000);
    }

   public int GetMaxGB(){
       int max=0;
       waitUntilElementIsClickable(storageInput);
       boolean breakIt = true;
        while (true) {
           breakIt = true;
           try {
               max = Integer.parseInt(storageInput.getAttribute("aria-valuemax"));
           } catch (Exception e) {
               if (e.getMessage().contains("element is not attached")) {
                   breakIt = false;
               }
           }
           if (breakIt) {
               break;
           }
        }
        return max;
   }

    public int GetMinGB(){
        int min = 0;
        waitUntilElementIsClickable(storageInput);
        boolean breakIt = true;
        while (true) {
            breakIt = true;
            try {
                min = Integer.parseInt(storageInput.getAttribute("aria-valuemin"));
            } catch (Exception e) {
                if (e.getMessage().contains("element is not attached")) {
                    breakIt = false;
                }
            }
            if (breakIt) {
                break;
            }
        }
        return min;
   }

    public  boolean NVRsPageIsLoaded(){
        boolean flag = false;
        try{
            flag = addDiscoveredNVRsButton.isDisplayed();
        }catch (Exception e){}
        return flag;
    }

    public void ClickOnStorageUpSpinner(){
        boolean breakIt = true;
        while (true) {
            breakIt = true;
            try {
                storageUpSpinner.click();
            } catch (Exception e) {
                if (e.getMessage().contains("element is not attached")) {
                    breakIt = false;
                }
            }
            if (breakIt) {
                break;
            }
        }
    }

    public void ClickOnStorageDownSpinner(){
        boolean breakIt = true;
        while (true) {
            breakIt = true;
            try {
                storageDownSpinner.click();
            } catch (Exception e) {
                if (e.getMessage().contains("element is not attached")) {
                    breakIt = false;
                }
            }
            if (breakIt) {
                break;
            }
        }
    }

    public void IfStatusIsNotVThanDeleteNVR(String NVRAdress) throws InterruptedException {
       try{
           if(verifyElementIsPresent(FindNVRByText(NVRAdress))){
               if(!(GetStatus(NVRAdress).equals("V"))){
                   FindElementByText(NVRAdress).click();
                   Thread.sleep(1000);
                   PressOnRemoveNVRsByButton();
                   ConfirmRemoveNVRsByButton();
               }
           }
       }catch(Exception e){}
    }

    public void AddDiscoveredNVRs(String NVRAdress) throws InterruptedException {
        ClickOnAddDiscoveredNVRSButton();
        WaitUntilCountOfDiscoveredNVRsIsVIsible(NVRAdress);
//        Thread.sleep(1000);
        JavaScriptClick(FindElementByText(NVRAdress));
        Thread.sleep(1000);
        ApplyAndCloseButtonDiscovereNVRs();
    }

    public void AddDiscoveredSecuredNVRs(String NVRAdress) throws InterruptedException {
        ClickOnAddDiscoveredNVRSButton();
        WaitUntilCountOfDiscoveredNVRsIsVIsible(NVRAdress);
//        Thread.sleep(3000);
        JavaScriptClick(FindElementByText(NVRAdress));
        Thread.sleep(1000);

        ClickOnSelectIPAddressIcon();

        FindElementByText(NVRAdress+":443").click();
        WaitUntilSelectIpAddressDialogIsClosed();
        ApplyAndCloseButtonDiscovereNVRs();
    }

    public void IfNVRIsNotExistAddIt(String NVRAdress) throws InterruptedException {
        if(!verifyElementIsPresent(FindNVRByText(NVRAdress))){
            AddDiscoveredNVRs(NVRAdress);
            FindNVRByText(NVRAdress).click();
            waitUntilStatusIsV(NVRAdress);
            WaitUntilStorrageSettingIsExist();
            Thread.sleep(2000);
        }
    }

    public void WaitUntilStorrageSettingIsExist(){
        try{
            waitUntilElementIsLoaded(StorageOnOffSwitch);
        }catch(Exception e){}
    }

    public void ClickOnDiscoveredNVRs(int index){
        NVRsDiscoveredNVRsWindowList.get(index).click();
    }

    public String GetTextDiscoveredNVR(int index){
        return NVRsDiscoveredNVRsWindowList.get(index).getText();
    }

    public void ScrollToDeviceByIndex(int index){
        scroll(NVRsDiscoveredNVRsWindowList.get(index));
    }

    public int GetSizeDiscoveredNVRsList(){
        return NVRsDiscoveredNVRsWindowList.size();
    }

    public String GetTextFilteredDiscoveredNVR(int index){
        return NVRsFilteredDiscoveredNVRsWindowList.get(index).getText();
    }

    public int GetSizeFilteredDiscoveredNVRsList(){
        return NVRsFilteredDiscoveredNVRsWindowList.size();
    }

    public void ClickOnVDiscoveredNVRs(int index){
        scroll(VNVRsDiscoveredNVRsWindowList.get(index));
        VNVRsDiscoveredNVRsWindowList.get(index).click();
    }

    public int GetSizeVDiscoveredNVRsList(){
        return VNVRsDiscoveredNVRsWindowList.size();
    }

    public WebElement GetNVRFromCentralPanel(int index){
        return NVRsListCentralPanel.get(index);
    }

    public int GetSizeNVRsList(){
        return NVRsListCentralPanel.size();
    }

    public String GetNameNVR(int index){
        return nameCentralPanelList.get(index).getText();
    }

    public String GetVersionNVR(int index){
        return versionCentralPanelList.get(index).getText();
    }

    public void ClickOnSecuredSwitch(){
        JavaScriptClick(securedSwitch);
    }

    public String GetSecuredStatus(){
        String status = "OFF";
        String secured = securedSwitch.getAttribute("aria-checked");
        if(secured.equals("true")) status = "ON";
        return status;
    }

    public void ClickOnSecuredSwitchInAddNVRManuallyDialog(){
        securedAddNVRManuallySwitch.click();
    }

    public String GetSecuredStatusInAddNVRManuallyDialog(){
        String status = "OFF";
        String secured = securedAddNVRManuallySwitch.getAttribute("aria-checked");
        if(secured.equals("true")) status = "ON";
        return status;
    }

    public boolean IPAdressFieldIsExist(){
        return verifyElementIsPresent(inputIPAdressField);
    }

    public void ClickOnSelectIPAddressIcon(){
        selectIPAddressIcon.click();
    }

  }
