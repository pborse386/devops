package Utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.io.*;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import static java.util.concurrent.TimeUnit.SECONDS;



public abstract class Page {
    @FindBy(id = "txtLogin")
    WebElement userNameField;

    @FindBy(name = "password")
    WebElement passwordField;

    @FindBy(id = "txtCurrentPassword")
    WebElement currentPasswordField;

    @FindBy(id = "txtVerifyPassword")
    WebElement verifyNewPasswordField;

    @FindBy(id = "txtNewPassword")
    WebElement newPasswordField;

    @FindBy(id = "submitBtn")
    WebElement loginButton;

    @FindBy(id = "config-menu-Network Devices")
    WebElement networkDeviceButton;

    //Notification
    @FindBy(xpath = "//div[@id='vms-export-container']//button[contains(text(), 'Close')]")
    public WebElement closeNotificationButton;

    @FindBy(tagName= "md-switch")
    WebElement PreviousStateOnOffToggleElement;

    @FindBy(linkText = "Configuration")
    WebElement configurationButton;

    @FindBy(xpath = "//a[contains(text(), 'Search')]")
    WebElement searchButton;

    @FindBy(xpath = "//a[@title='Dashboard']")
    WebElement dashboardButton;

    @FindBy(xpath = "//a[contains(text(), 'Monitoring')]")
    WebElement monitoringButton;

    @FindBy(xpath = "//section[@id='vms-resources-panel']//div[@class='vms-search-with-btn']//input")
    WebElement filterField;

    //Unsaved changes window
    @FindBy(xpath = "//div[@class = 'modal-footer-confirm']/button[contains(text(), 'Save')]")
    WebElement saveUnsavedChangesButton;

    @FindBy(xpath = "//div[@class = 'modal-footer-confirm']/button[contains(text(), 'Do not save')]")
    WebElement doNotSaveUnsavedChangesButton;

    @FindBy(xpath = "//div[@class = 'modal-footer-confirm']/button[contains(text(), 'Cancel')]")
    WebElement cancelUnsavedChangesButton;

    //Specify Events window
    @FindBy(xpath = "//div[@role='columnheader']/div[@role='button']/span[contains(text(), 'Name')]")
    WebElement nameColumnHeader;

    @FindBy(xpath = "//div[@role='columnheader']/div[@role='button']/span[contains(text(), 'Status')]")
    WebElement statusColumnHeader;

    @FindBy(xpath = "//div[@class='dropdown-custom-button']")
    WebElement eventTypeSelectField;

    @FindBy(xpath = "//nav[@id='primary_nav_wrap']/ul/li[@class='ng-scope']/a/span[@class='ng-binding']")
    java.util.List<WebElement> enabledEventTypeOptions;

    @FindBy(xpath = "//nav[@id='primary_nav_wrap']/ul/li[@class='ng-scope']/ul/li[@class='ng-scope']/a/span[@class='ng-binding']")
    java.util.List<WebElement> enabledEventTypePostOptions;

    @FindBy(xpath = "//div[@class='ui-grid-contents-wrapper']//div[@role='rowgroup'][@class='ui-grid-viewport ng-isolate-scope']/div[@class='ui-grid-canvas']/div/div[@role='row'][@class='ng-isolate-scope']/div[@role='rowheader']/div/div[@class='ui-grid-cell-contents']/div[@role='button']")
    java.util.List<WebElement> devicesVInSpecifyEventsWindowList;

    @FindBy(xpath = "//div[@class='ui-grid-viewport ng-isolate-scope']/div[@class='ui-grid-canvas']//div[@role='row'][@class='ng-isolate-scope']/div[@role='gridcell']/div[@class='ui-grid-cell-contents ng-binding ng-scope']")
    java.util.List<WebElement> devicesInSpecifyEventsWindowList;

    @FindBy(xpath = "//div[@class='ui-grid-viewport ng-isolate-scope']/div[@class='ui-grid-canvas']//div[@role='row'][@class='ng-isolate-scope']/div[@role='gridcell']//md-switch//span[contains(@style, 'block')]")
    java.util.List<WebElement> statusInSpecifyEventsWindowList;

    @FindBy(xpath = "//div[@class='ui-grid-top-panel']")
    WebElement selectAllDevicesInSpecifyEventsV;

    @FindBy(xpath = "//div[@class='summaryModalEventsConfig eventFontColor event-outer-elem']/div/span")
    java.util.List<WebElement> selectedEventsInSpecifyEventsV;

    @FindBy(xpath = "//div[@class='summaryModalEventsConfig eventFontColor event-outer-elem']/div/button[@class='vms-grid-delete-button delete-button-visible vicon-font v-trash']")
    java.util.List<WebElement> deleteEventsIconInSpecifyEventsV;

    @FindBy(xpath = "//div[@id='vms-configurationScreen']//span[@class='v-align-top']/../input[@type='radio']")
    WebElement selectedRadioButton;

    @FindBy(xpath = "//div[@id='vms-configurationScreen']//label[@class='v-align-top']/../input[@type='radio']")
    WebElement anySelectedRadioButton;

    @FindBy(xpath = "//div[@class='fullSize ng-scope']/div[@class='modal-footer']/button[contains(text(), 'Apply and close')]")
    WebElement applyAndCloseInSpecifyEventsButton;

    @FindBy(xpath = "//div[@class='fullSize ng-scope']/div[@class='modal-footer']/button[contains(text(), 'Apply')][1]")
    WebElement applyInSpecifyEventsButton;

    @FindBy(xpath = "//div[@class='fullSize ng-scope']/div[@class='modal-footer']/button[contains(text(), 'Close')]")
    WebElement closeInSpecifyEventsButton;

    @FindBy(xpath = "//div[@class='ui-grid-canvas']/div/div[@class='ng-isolate-scope']//div/md-switch")
    java.util.List<WebElement> eventStatusTogglesList;

    @FindBy(xpath = "//div[@class='ui-grid-canvas']/div/div[@class='ng-isolate-scope']//div[@data-type='relay']/md-switch//div[@class='md-label']/span[contains(@style, 'block')]")
    java.util.List<WebElement> eventStatusList;

    @FindBy(xpath = "//div[@aria-hidden='false']/div/display-eventslist-configuration/div/span[contains(text(), '-')]")
    java.util.List<WebElement> selectedEventsList;

    @FindBy(xpath = "//div[@aria-hidden='false']/div/display-eventslist-configuration/div/span/..//button")
    java.util.List<WebElement> deleteIconSelectedEventsList;

    @FindBy(xpath = "//div[@class='ui-grid-contents-wrapper']//div[@role='rowgroup'][@class='ui-grid-viewport ng-isolate-scope']/div[@class='ui-grid-canvas']/div/div[@role='row'][@class='ng-isolate-scope']/div[@role='rowheader']/div/div[@class='ui-grid-cell-contents']/div[@class='ui-grid-selection-row-header-buttons ui-grid-icon-ok ng-scope']")
    java.util.List<WebElement> devicesVNotSelectedInSpecifyEventsWindowList;

    @FindBy(xpath = "//div[@id='config-menu-Resources'][@title = 'Resources']")
    WebElement resourcesButton;

    @FindBy(xpath = "//div[@class='fullSize ng-scope']//div[@class ='vms-big-btn-footer bottom-separator-builder new-ui-panel-background']/button[contains(text(), 'Cancel')][contains(@style,'display: block')]")
    public WebElement cancelButton;

    @FindBy(xpath = "//div[@class = 'vms-big-btn-footer bottom-separator-builder new-ui-panel-background']/button[contains(text(), 'Save')][contains(@style,'display: block')]")
    public WebElement saveButton;

    @FindBy(xpath = "//div[@id='config-menu-User Management'][@title ='User Management']")
    WebElement userManagementButton;

    @FindBy(xpath = "//button[@class='btn btn-default dropdown-toggle']/span[@role='button']")
    WebElement userTopPanelButton;

    @FindBy(xpath = "//button[@id='vms-header-user-selector']/span[1]")
    WebElement userTopPanelName;

    @FindBy(xpath = "//div[@id='vms-userDefine-container']/div[@class='header-user-menu-item'][2]")
    WebElement logOutTopPanelButton;

    @FindBy(xpath = "//div[@class='message-container error-message-container-div']/div[contains(text(), 'Wrong user name or password')]")
     WebElement wrongUserNameOrPasswordNotification;

    @FindBy(xpath = "//div[@class='message-container error-message-container-div']/div[contains(text(), 'Account Expired')]")
    WebElement accountExpiredNotification;

    @FindBy(xpath = "//div[@id='message']/div[contains(text(), 'Enter New Password')]")
    WebElement enterNewPasswordNotification;

    @FindBy(xpath = "//div[@class='message-container error-message-container-div']/div[contains(text(), 'User without an associated group')]")
    WebElement userWithoutAssociatedGroupNotification;

   // @FindBy(xpath = "//div[@class='modal-footer-confirm']/button[text()='Yes' or text()='YES' or text()='yes']")
    //WebElement yesButton;
    
    @FindBy(xpath = "//*[@id=\"vms-configurationScreen\"]/div[2]/button[1]")
    WebElement yesButton;

   // @FindBy(xpath = "//div[@class='modal-footer-confirm']/button[text()='No' or text()='NO' or text()='no']")
   // WebElement noButton;
    
    @FindBy(xpath = "//*[@id=\"vms-configurationScreen\"]/div[2]/button[2]")
    WebElement noButton;

    @FindBy(xpath = "//div[@class='modal-footer-confirm']/button[text()='Ok']")
    WebElement okButton;

    @FindBy(xpath="//div[@class='ng-isolate-scope pull-right top-right-icon-align'][@role='button']")
    WebElement closeDialogIcon;

    public static WebDriver driver;

    public Page(WebDriver driver) {this.driver = driver; }

    public void ClickOnUserTopPanelButton(){
        waitUntilElementIsClickable(userTopPanelButton);
        MoveToElement(userTopPanelButton);
        userTopPanelButton.click();
    }

    public void ClickOnLogOUTButton() throws InterruptedException {
       for(int i = 0 ;i<10; i++){
           WaitUntilDialogIsNotLocated();
//           waitUntilIsLoadedCustomTime(logOutTopPanelButton);
           try{
              logOutTopPanelButton.click();
               try{
                   driver.switchTo().alert().accept();
               }catch(Exception a){}

//              break;
           }catch (NoSuchElementException e){
               try{
                   ClickOnUserTopPanelButton();
                   waitUntilIsLoadedCustomTime(logOutTopPanelButton);
                   logOutTopPanelButton.click();
               }catch(Exception a){}
           }
           waitUntilIsLoadedCustomTime(userNameField);
           if(LogInPageIsLoaded()) break;
       }
    }

    public void InputPasswordInNewPassword(String password){
        setElementText(newPasswordField, password);
    }

    public void InputPasswordInVerifyNewPassword(String password){
        setElementText(verifyNewPasswordField, password);
    }

    public void InputPasswordInCurrentPassword(String password){
        setElementText(currentPasswordField, password);
    }

    public boolean WrongUserNameOrPasswordNotificationIsPresent(){
       return verifyElementIsPresent(wrongUserNameOrPasswordNotification);
    }

    public boolean AccountExpiredNotificationIsPresent(){
        return verifyElementIsPresent(accountExpiredNotification);
    }

    public boolean EnterNewPasswordNotificationIsPresent(){
        return verifyElementIsPresent(enterNewPasswordNotification);
    }

    public boolean UserWithoutAssociateGroupNotificationIsPresent(){
        return verifyElementIsPresent(userWithoutAssociatedGroupNotification);
    }

    public void GoToConfigurationPage() throws InterruptedException {
        Thread.sleep(3000);
        WaitUntilDialogIsNotLocated();
        waitUntilElementIsClickable(configurationButton);
        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
//        Thread.sleep(3000);
 //       driver.findElement(By.linkText("Configuration")).sendKeys(Keys.ENTER);
//        configurationButton.click();

        try {
        	 safeJavaScriptClick(configurationButton);
                Thread.sleep(2000);
            } catch (Exception e) {
                takeScreenshot(driver, "Setup", "GoToConfiguration");
            }        Thread.sleep(1000);
//        try{
//            new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='panel-group']")));
//        }catch(Exception e){}
//        waitUntilNetworkDevicesMenuIsExpanded();
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
    
    
    
    

    public void GoToSearchPage() throws InterruptedException {
        waitUntilIsLoadedCustomTime(searchButton);
        Thread.sleep(2000);
        searchButton.click();
    }

    public void GoToMonitoringPage(){
        waitUntilElementIsLoaded(monitoringButton);
        monitoringButton.click();
    }

    public boolean MonitoringPageIsLoaded(){
        waitUntilIsLoadedCustomTime(filterField);
        return verifyElementIsPresent(filterField);
    }

    public void PressSaveButton() throws InterruptedException {
        WaitUntilDialogIsNotLocated();
        Thread.sleep(1000);
        saveButton.click();
        Thread.sleep(1000);
        WaitUntilPAnelIsActive();
    }

    public void WaitUntilPAnelIsActive(){
        try{
            new WebDriverWait(driver, 80).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='fade-in-panel']")));
        }catch (Exception e){}
    }

    public void PressCancelButton() throws InterruptedException {
        Thread.sleep(1000);
        waitUntilElementIsClickable(cancelButton);
        cancelButton.click();
        Thread.sleep(1000);
    }

    public void PressSaveUnsavedChanges() throws InterruptedException {
        WaitUntilDialogIsLocated();
        driver.switchTo().window(driver.getWindowHandle());
        waitUntilIsLoadedCustomTime(saveUnsavedChangesButton);
        waitUntilElementIsLoaded(saveUnsavedChangesButton);
        waitUntilElementIsClickable(saveUnsavedChangesButton);
        saveUnsavedChangesButton.click();
        WaitUntilDialogIsNotLocated();
        Thread.sleep(1000);
    }

    public void PressDontSaveUnsavedChanges() throws InterruptedException {
        WaitUntilDialogIsLocated();
        driver.switchTo().window(driver.getWindowHandle());
        waitUntilElementIsLoaded(doNotSaveUnsavedChangesButton);
        doNotSaveUnsavedChangesButton.click();
        WaitUntilDialogIsNotLocated();
        Thread.sleep(1000);
    }

    public void CancelUnsavedChanges() throws InterruptedException {
        WaitUntilDialogIsLocated();
        driver.switchTo().window(driver.getWindowHandle());
        waitUntilElementIsLoaded(cancelUnsavedChangesButton);
        cancelUnsavedChangesButton.click();
        WaitUntilDialogIsNotLocated();
        Thread.sleep(1000);
    }

    public boolean SaveUnsavedChangesButtonIsEnabled(){
        WaitUntilDialogIsLocated();
        driver.switchTo().window(driver.getWindowHandle());
        waitUntilIsLoadedCustomTime(saveUnsavedChangesButton);
        return saveUnsavedChangesButton.isEnabled();
    }

    public boolean CancelUnsavedChangesButtonIsEnabled(){
        WaitUntilDialogIsLocated();
        driver.switchTo().window(driver.getWindowHandle());
        waitUntilIsLoadedCustomTime(cancelUnsavedChangesButton);
        return cancelUnsavedChangesButton.isEnabled();
    }

    public boolean DoNotSaveUnsavedChangesButtonIsEnabled(){
        WaitUntilDialogIsLocated();
        driver.switchTo().window(driver.getWindowHandle());
        waitUntilIsLoadedCustomTime(doNotSaveUnsavedChangesButton);
        return doNotSaveUnsavedChangesButton.isEnabled();
    }

    public void SignIn() throws IOException, InterruptedException {
        try{
            InputInUserNameField("admin");
            InputInPasswordField("1234");
            boolean isChecked = "true".equals(PreviousStateOnOffToggleElement.getAttribute("aria-checked"));
            if(isChecked){
                PreviousStateOnOffToggleElement.click();
            }
            ClickOnLoginInButton();
            WaitUntilLoadingBlockAppears();
            WaitUntilLoadingBlockDisappears();
            WaitUntilDialogIsLocated();
            if(CheckThatModalIsOpen()){
                ClickOnCloseInViconPlayerDownloadDialog();
            }
        }catch(Exception e){
            takeScreenshot(driver, "Setup", "LogInToSystem");
        }
    }

    public void ClickOnCloseInViconPlayerDownloadDialog() throws InterruptedException {
        driver.switchTo().window(driver.getWindowHandle());
        waitUntilElementIsLoaded(closeNotificationButton);
        JavaScriptClick(closeNotificationButton);
        Thread.sleep(1000);
        WaitUntilDialogIsNotLocated();
    }

    public void InputInUserNameField(String userName){
        waitUntilIsLoadedCustomTime(userNameField);
        userNameField.sendKeys(userName);
    }

    public boolean LogInPageIsLoaded(){
        boolean flag = verifyElementIsPresent(userNameField);
        return flag;
    }

    public void InputInPasswordField(String password){
        waitUntilElementIsLoaded(passwordField);
        setElementText(passwordField, password);
    }

    public void ClickOnLoginInButton(){
        loginButton.click();
    }

    public void WaitUntilLoadingBlockAppears(){
        try{
            new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("loadingBlock")));
            WebElement loadingBlock = driver.findElement(By.id("loadingBlock"));
            new WebDriverWait(driver, 60).until(ExpectedConditions.attributeToBe(loadingBlock, "class", "vms-loading-block"));
        }catch(Exception e){}
    }

    public void WaitUntilLoadingBlockDisappears(){
         try{
             WebElement loadingBlock = driver.findElement(By.id("loadingBlock"));
             new WebDriverWait(driver, 60).until(ExpectedConditions.attributeContains(loadingBlock, "class", "ng-cloak"));
         }catch(Exception e){}
    }

    public void WaitUntilDialogIsLocated(){
       try{
          new WebDriverWait(driver, 3).until(ExpectedConditions.attributeContains( (By.xpath("//body")), "class", "modal-open"));
       }catch(Exception e){}
    }

    public void WaitUntilDialogIsNotLocated(){
        try{
            new WebDriverWait(driver, 10).until(ExpectedConditions.attributeToBe( (By.xpath("//body")), "class", "ltr"));
        }catch(Exception e){}
    }

    public WebElement FindElementByText(String textIP){
        //String xPathEx= "//*[contains(text()," +  textIP  +")]";
        WebElement element = null;
       try{
           element = driver.findElement(By.xpath("(//*[contains(text(), '" + textIP + "')] | //*[@value='" + textIP + "'])"));
       } catch (NoSuchElementException e) { }
       catch (ElementNotVisibleException e) { }
        return element;
    }

    public void setElementText(WebElement element, String text) {
        try{
        	JavaScriptClick(element);
        	JavaScriptClick(element);
        	
        }catch(Exception e){ }
        element.clear();
        element.sendKeys(text);
    }

    public void waitUntilIsLoadedCustomTime(WebElement element) {
       int timeCount = 1;
       while(true){
           timeCount ++;
           if(verifyElementIsPresent(element)){break;}
           if (timeCount>50){break;}
       }
    }

    public boolean verifyElementIsPresent(WebElement element) {
        try {
            element.getTagName();
            return true;
        } catch (Exception e){
            return false;
        }
     }

    public int countOfElementsByXpath(String xPath){
        int count = driver.findElements(By.xpath(xPath)).size();
        return count;
    }

    public void waitUntilElementIsLoaded(WebElement element)  {
        try {
            new WebDriverWait(driver, 40).until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e){}
    }

    public void waitUntilElementIsClickable(WebElement element)  {

        try {
            new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e){}
    }

    public String[] getServersList(){
        String WebDriverLocation = System.getenv("WebDriver");
        int i = 0;
        String[] line = new String[10];
        try (BufferedReader br = new BufferedReader(new FileReader(WebDriverLocation +"\\Servers.txt"))){
        while ((line[i] = br.readLine()) != null) {
            i++;
           }
        }
        catch (Exception e){}
        finally {
            return line;
        }
    }

    public String[] getCamerasList(){
        String WebDriverLocation = System.getenv("WebDriver");
        int i = 0;
        String[] line = new String[8];
        try (BufferedReader br = new BufferedReader(new FileReader(WebDriverLocation +"\\Cameras.txt"))){
            while ((line[i] = br.readLine()) != null) {
                i++;
            }
        }
        catch (Exception e){}
        finally {
            return line;
        }
    }

    public void scroll(WebElement element){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",element);
    }

    public void MoveToElement(WebElement element){
        Actions builder = new Actions(driver);
        builder.moveToElement(element).build().perform();
    }

    public int GetRandomDigit(int min, int max){
        int random =min + (int) (Math.random()*(max-min));
        return random;
    }

    public void Refresh() throws InterruptedException {
        driver.navigate().refresh();
        try{
            driver.switchTo().alert().accept();
        }catch(Exception a){}
        WaitUntilEndRefresh();
        Thread.sleep(2000);
    }

    public void WaitUntilEndRefresh(){
        try{
            new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id = 'vms-resources-groups-treeview']")));
        }catch(Exception e){}
    }

    public boolean CheckThatModalIsOpen(){
        return driver.findElement(By.xpath("//body")).getAttribute("class").contains("modal-open");
    }

    public static String takeScreenshot(WebDriver webDriver, String folderName, String name)  {
        //  String file = new File("./test-output/").getAbsolutePath();
//        String path ="\\..\\..\\..\\..\\test-output\\" +suiteName+"\\" + name+".png";
        // String path =file+File.separator +suiteName+File.separator+ name+".png";
//        String path ="C:\\Users\\nastya\\IdeaProjects\\ValerusAutoTest\\test-output"+File.separator+folderName+File.separator+name+".png";
        String path =System.getProperty("user.dir") +File.separator+"test-output"+File.separator +folderName+File.separator+name+".png";
        // String path =("./"+name+".jpg");
        try {
            File scrFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
            File target = new File(path);
            FileUtils.copyFile(scrFile, target);
        } catch (Exception e) {
//            LOG.log(Level.SEVERE, e.getMessage(), e);
        }return path;
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

    public void WaitUntilSaveButtonWillBeEnable(){
        try{
            new WebDriverWait(driver, 5).until(ExpectedConditions.attributeContains(saveButton, "aria-disabled", "false"));
        }catch (Exception e){}
    }

    public void waitUntilResourcesMenuIsExpanded(){
        WebElement resourcesMenu = driver.findElement(By.xpath("//div[@class='panel-group']/div[2]"));
        try{
            new WebDriverWait(driver, 5).until(ExpectedConditions.attributeContains(resourcesMenu, "class", "panel-open"));
        }catch (Exception e){}
    }

    public void waitUntilUserManagementMenuIsExpanded(){
        WebElement resourcesMenu = driver.findElement(By.xpath("//div[@class='panel-group']/div[4]"));
        try{
            new WebDriverWait(driver, 5).until(ExpectedConditions.attributeContains(resourcesMenu, "class", "panel-open"));
        }catch (Exception e){}
    }

    public void waitUntilNetworkDevicesMenuIsExpanded(){
        try{
            WebElement menu = driver.findElement(By.xpath("//div[@class='panel-group']/div[1]"));
            new WebDriverWait(driver, 5).until(ExpectedConditions.attributeContains(menu, "class", "panel-open"));
        }catch (NoSuchElementException e){
            takeScreenshot(driver, "Setup", "WaitUntilPanelIsOpen");
        }
    }

    public boolean ResourcesMenuIsOpen(){
        WebElement resourcesMenu = driver.findElement(By.xpath("//div[@class='panel-group']/div[3]"));
        return resourcesMenu.getAttribute("class").contains("panel-open");
    }

    public boolean UserManagementMenuIsOpen(){
        WebElement resourcesMenu = driver.findElement(By.xpath("//div[@class='panel-group']/div[4]"));
        return resourcesMenu.getAttribute("class").contains("panel-open");
    }

    public boolean UserIsLoggedIn(){
       return verifyElementIsPresent(configurationButton);
    }

    public boolean DashboardIsExists(){
        return verifyElementIsPresent(dashboardButton);
    }

    public boolean UserManagementIsExists(){
        WebElement element = driver.findElement(By.xpath("//div[@id='config-menu-User Management'][@title ='User Management']/../../../../.."));
        return element.getAttribute("aria-hidden").equals("false");
    }

    public boolean CofigurationIsExists(){
        return verifyElementIsPresent(configurationButton);
    }

    public boolean SearchButtonIsExists(){
        return verifyElementIsPresent(searchButton);
    }

    public void ClickOnUserManagement(){
        waitUntilElementIsLoaded(userManagementButton);
        JavaScriptClick(userManagementButton);
    }

    public boolean CofigurationPageIsLoaded(){
        return verifyElementIsPresent(userManagementButton);
    }

    public void DragAndDrop(String source, String target) throws InterruptedException, IOException {
        try {
            String basePath = new File("").getAbsolutePath();

            //http://stackoverflow.com/questions/29381233/how-to-simulate-html5-drag-and-drop-in-selenium-webdriver
            //https://gist.github.com/rcorreia/2362544
            final String JQUERY_LOAD_SCRIPT = (basePath + "/src/test/resources/jquery_load_helper.js");
            String jQueryLoader = readFile(JQUERY_LOAD_SCRIPT);

            driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeAsyncScript(
                    jQueryLoader /* , http://localhost:8080/jquery-1.7.2.js */);

            // ready to rock
            js.executeScript("jQuery(function($) { " + " $('input[name=\"q\"]').val('bada-bing').closest('form').submit(); "
                    + " }); ");
            //http://stackoverflow.com/questions/29381233/how-to-simulate-html5-drag-and-drop-in-selenium-webdriver
            //"where jquery_load_helper.js contains:"
            String filePath = basePath + "/src/test/resources/drag_and_drop_helper.js";

            //JQuery can ONLY work with id and css , xpath does NOT work with it.
            //String source =  "//section[@id='wrapper']/article/ul/li[4]/a";

            StringBuffer buffer = new StringBuffer();
            String line;
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null)
                buffer.append(line);

            String javaScript = buffer.toString();

            javaScript = javaScript + "$('" + source + "').simulateDragDrop({ dropTarget: '" + target + "'});";
            ((JavascriptExecutor) driver).executeScript(javaScript);

            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String readFile(String file) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(file);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
    }

    public void ClickOnNetworkDeviceButton(){
        waitUntilElementIsClickable(networkDeviceButton);
        JavaScriptClick(networkDeviceButton);
    }

    public String GetUserFromTopPanel(){
        return userTopPanelName.getText();
    }

    public void ClickOnElement(WebElement element){
        element.sendKeys(Keys.ENTER);
        element.click();
    }

    public void JavaScriptClick(WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void ConfirmRemoving() throws InterruptedException{
        WaitUntilDialogIsLocated();
        Thread.sleep(500);
        JavaScriptClick(yesButton); 
        //yesButton.click();
        WaitUntilDialogIsNotLocated();
    }

    public void CancelRemoving() throws InterruptedException{
    	Thread.sleep(500);
    	JavaScriptClick(noButton);
    	//noButton.click();
    }

    public void ClickOnOkButton(){
    	JavaScriptClick(okButton);
    }

    //Methods for Specify Events dialog

    public int CountEventTypeOptions(){
        return enabledEventTypeOptions.size();
    }

    public void SelectEventTypeOptionByIndex(int index){
    	JavaScriptClick(enabledEventTypeOptions.get(index));
    }

    public int CountEventTypePostOptions(){
        return enabledEventTypePostOptions.size();
    }

    public void SelectEventTypePostOptionByIndex(int index){
    	JavaScriptClick(enabledEventTypePostOptions.get(index));
    }

    public int CountOfDevicesInSpecifyEventsWindow(){
        return devicesVInSpecifyEventsWindowList.size();
    }

    public String GetDeviceTextInSpecifyEvents(int index){
        return devicesInSpecifyEventsWindowList.get(index).getText();
    }

    public String GetStatusTextInSpecifyEvents(int index){
        return statusInSpecifyEventsWindowList.get(index).getText();
    }

    public int GetStatusInSpecifyDialog(){
        return statusInSpecifyEventsWindowList.size();
    }

    public void ClickOnEventTypeField(){
    	JavaScriptClick(eventTypeSelectField);
    }

    public void ClickOnDeviceInSpecifyEventsDevicesList(int index) throws InterruptedException {
        scroll(devicesVInSpecifyEventsWindowList.get(index));
        JavaScriptClick( devicesVInSpecifyEventsWindowList.get(index));
        Thread.sleep(1000);
    }

    public void ClickOnSelectAllButtonInSpecifyevents(){
    	JavaScriptClick( selectAllDevicesInSpecifyEventsV);
    }

    public int CountSelectedEventsInSpecifyEvents(){
        return selectedEventsInSpecifyEventsV.size();
    }

    public String GetSelectedDeviceTextInSpecifyEvents(int index){
        return selectedEventsInSpecifyEventsV.get(index).getText();
    }

    public void DeleteSelectedEventInSpecifyEvents(int index){
    	JavaScriptClick(deleteEventsIconInSpecifyEventsV.get(index));
    }

    public void PressOnApplyAndCloseButtonInSpecifyEvents() throws InterruptedException {
    	JavaScriptClick(applyAndCloseInSpecifyEventsButton);
        Thread.sleep(1000);
    }

    public void PressOnApplyButtonInSpecifyEvents(){
    	JavaScriptClick(applyInSpecifyEventsButton);
    }

    public void PressOnCloseButtonInSpecifyEvents(){
    	JavaScriptClick(closeInSpecifyEventsButton);
    }

    public void PressSelectedRadioButton(){
    	JavaScriptClick(selectedRadioButton);
    }

    public void PressAnySelectedRadioButton(){
    	JavaScriptClick(anySelectedRadioButton);
    }

    public int CountEventsStatusToogleList(){
        return eventStatusTogglesList.size();
    }

    public void ClickOnEventStatusToggle(int index){
        eventStatusTogglesList.get(index).click();
    }

    public String GetStatusInSpecifyEvents(int index){
        return eventStatusList.get(index).getText();
    }

    public int GetSelectedEventsSize(){
        return selectedEventsList.size();
    }

    public void ClickOnDeleteEventIcon(int index) throws InterruptedException {
        Actions build = new Actions(driver);
        scroll(selectedEventsList.get(index));
        build.moveToElement(selectedEventsList.get(index)).build().perform();
        waitUntilElementIsLoaded(deleteIconSelectedEventsList.get(index));
        Thread.sleep(1000);
        JavaScriptClick(deleteIconSelectedEventsList.get(index));
        Thread.sleep(1000);
    }

    public void ClickOnNameColumnHeader(){
    	JavaScriptClick(nameColumnHeader);
    }

    public void ClickOnStatusColumnHeader(){
    	JavaScriptClick(statusColumnHeader);
    }

    public int CountOfDevicesNotSelectedInSpecifyEventsWindow(){
        return devicesVNotSelectedInSpecifyEventsWindowList.size();
    }

    public void ClickOnDeviceNotSelectedInSpecifyEventsDevicesList(int index) throws InterruptedException {
        scroll(devicesVNotSelectedInSpecifyEventsWindowList.get(index));
        devicesVNotSelectedInSpecifyEventsWindowList.get(index).click();
        Thread.sleep(1000);
    }

    public String GetEventTypeOptionByIndex(int index){
        return enabledEventTypeOptions.get(index).getText();
    }

    public String GetEventTypePostOptionByIndex(int index){
        return enabledEventTypePostOptions.get(index).getText();
    }

    public void SelectEventTypeOptionByText(String text){
        WebElement option = driver.findElement(By.xpath("//nav[@id='primary_nav_wrap']/ul/li//span[@class='ng-binding'][contains(text(), '"+text+"')]"));
        option.click();
    }

    public void ScrollToEventByIndex(int index){
        scroll(devicesInSpecifyEventsWindowList.get(index));
    }

    public void ClickOnResourcesButton(){
        waitUntilElementIsLoaded(resourcesButton);
        boolean resourcesMenuIsOpen = ResourcesMenuIsOpen();
        if(!resourcesMenuIsOpen){
            JavaScriptClick(resourcesButton);
        }
    }

    public void PressCloseDialogIcon() throws InterruptedException {
        WaitUntilDialogIsLocated();
        waitUntilIsLoadedCustomTime(closeDialogIcon);
        JavaScriptClick(closeDialogIcon);
        WaitUntilDialogIsNotLocated();
    }
}
