package pageObjects;

import Utilities.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class WebPagesPage extends Page {

    @FindBy(id = "config-menu-network_entities-web_sites")
    WebElement webPagesButton;

    @FindBy(xpath = "//span[@class='item-text ng-binding'][@title='Web Pages']")
    WebElement webPagesButtonLandingPage;

    @FindBy(xpath = "//div[@class='ui-grid-canvas']/div[contains(@class, 'ui-grid-row ng-scope')]//div[@class='ui-grid-cell-contents ng-binding ng-scope']")
    List<WebElement> webPagesList;

    @FindBy(xpath = "//div[@class='ui-grid-row ng-scope ui-grid-row-selected']//button[@class='vms-grid-delete-button vicon-font v-trash ng-scope ng-isolate-scope']")
    WebElement deleteIcon;

    @FindBy(xpath = "//button[@class='button-new-ui height-38 button-horizontal-margin-2'][contains(text(), 'New')]")
    WebElement newButton;

    @FindBy(xpath = "//button[@class='button-new-ui height-38 button-horizontal-margin-2'][contains(text(), 'Delete')]")
    WebElement deleteButton;

    @FindBy(xpath = "//div[@class='fullSize border-builder small-panel no-animation']//div[@class='vms-search-with-btn']//input")
    WebElement filterField;

    @FindBy(xpath = "//form//div[@class='config-form-fields border-builder']/input")
    WebElement nameField;

    @FindBy(xpath = "//div[@class='config-form-fields border-builder']//label[@title='Visible']/../md-switch")
    WebElement visibilityToggleSwitch;

    @FindBy(xpath = "//div[@class='config-form-fields border-builder']//*[@title='minutes']//md-switch")
    WebElement refreshTimeToggleSwitch;

    @FindBy(xpath = "//div[@class='config-form-fields border-builder']//*[@title='minutes']//input")
    WebElement refreshTimeField;

    @FindBy(xpath = "//div[@class='config-form-fields border-builder']//*[@title='minutes']//a[@class='ui-spinner-button ui-spinner-up ui-corner-tr']")
    WebElement refreshTimeUpSpinner;

    @FindBy(xpath = "//div[@class='config-form-fields border-builder']//*[@title='minutes']//a[@class='ui-spinner-button ui-spinner-down ui-corner-br']")
    WebElement refreshTimeDownSpinner;

    @FindBy(xpath = "//div[@class='config-form-fields border-builder']/textarea")
    WebElement remarksField;

    @FindBy(xpath = "//div[@class='config-form-fields border-builder']/label[@title='URL']/../input")
    WebElement urlField;

    @FindBy(xpath = "//div[@class='config-form-fields border-builder']/label[@title='URL']/../span[2]")
    WebElement navigateIcon;

    @FindBy(xpath = "//div[@class='config-form-fields border-builder']/label[@title='URL']/..//span[@title='Clear']")
    WebElement deleteURLIcon;

    @FindBy(xpath = "//div[@class='bottom-separator-builder vms-bottom-btn-container ng-scope']/button[contains(text(), 'Cancel')]")
    WebElement cancelButton;

    @FindBy(xpath = "//div[@class='bottom-separator-builder vms-bottom-btn-container ng-scope']/button[contains(text(), 'Save')]")
    WebElement saveButton;
    
    @FindBy(linkText = "Configuration")
    WebElement configurationButton1;

    public WebPagesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void GoToWebPagesPage() throws InterruptedException {
        Thread.sleep(1000);
        try{GoToConfigurationPage();
            Thread.sleep(1000);

            ClickOnResourcesButton();
            waitUntilResourcesMenuIsExpanded();
            Thread.sleep(1000);
            ClickOnWebPagesButton();}
        catch(Exception e){
            takeScreenshot(driver, "Setup", "GoToWebPagesPage");
        }
    }

    public void GoToWebPagesPageFromLanding() throws InterruptedException {
        Thread.sleep(100);
        GoToConfigurationPage();
        Thread.sleep(100);
        ClickOnWebPagesButtonLandingPage();
    }
    
    
    public void clickOnConfiguration() {
    	configurationButton1.click();
    }
    

    public void ClickOnWebPagesButton() throws InterruptedException {
        boolean breakIt = true;
        int time = 0;
        while (true) {
            breakIt = true;
            try {
                webPagesButton.click();
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

    public void ClickOnWebPagesButtonLandingPage() throws InterruptedException {
        waitUntilIsLoadedCustomTime(webPagesButtonLandingPage);
        Thread.sleep(500);
        JavaScriptClick(webPagesButtonLandingPage);
    }

    public void ClickOnNewButton(){
    	JavaScriptClick(newButton);
    }
    
    public void JavaScriptClick(WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void PressCancelButton() throws InterruptedException {
        waitUntilElementIsClickable(cancelButton);
        JavaScriptClick(cancelButton);
    }

    public boolean CancelButtonIsEnabled(){
        return  cancelButton.isEnabled();
    }

    public void PressSaveButton() throws InterruptedException {
        waitUntilElementIsClickable(saveButton);
        JavaScriptClick(saveButton);
        new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='fade-in-panel']")));
    }

    public boolean SaveButtonIsEnabled(){
        return saveButton.isEnabled();
    }

    public int GetWebPagesSize(){
        int size = 0;
        try{
            new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfAllElements(webPagesList));
            size = webPagesList.size();
        }catch (Exception e){}
        return size;
    }

    public String GetWebPageNameByIndex(int index){
        return webPagesList.get(index).getText();
    }

    public void ClickOnWebPageByIndex(int index) throws InterruptedException {
        webPagesList.get(index).click();
        Thread.sleep(1000);
    }

    public void ClickOnWebPageByName(String name){
        WebElement page = driver.findElement(By.xpath("//div[@class='ui-grid-cell-contents ng-binding ng-scope'][contains(text(), '"+name+"')]"));
        page.click();
    }

    public void InputIntoNameField(String name){
        setElementText(nameField, name);
    }

    public String GetWebPageName(){
        return nameField.getAttribute("value");
    }

    public void InputIntoRemarksField(String remark){
        setElementText(remarksField, remark);
    }

    public String GetWebPageRemarks(){
        return remarksField.getAttribute("value");
    }

    public void InputIntoURLField(String remark){
        setElementText(urlField, remark);
    }

    public String GetWebPageURl(){
        return urlField.getAttribute("value");
    }

    public void ClickOnDeleteUrlIcon(){
        deleteURLIcon.click();
    }

    public void ClickOnVisibilitySwitch(){
        visibilityToggleSwitch.click();
    }

    public boolean VisibilityToggleIsOn(){
        return visibilityToggleSwitch.getAttribute("aria-checked").equals("true");
    }

    public void ClickOnRefreshTimeSwitch(){
        refreshTimeToggleSwitch.click();
    }

    public boolean RefreshTimeToggleIsOn(){
        return refreshTimeToggleSwitch.getAttribute("aria-checked").equals("true");
    }

    public void InputIntoRefreshTimeField(String time){
        setElementText(refreshTimeField, time);
    }

    public String GetRefreshTimeValue(){
        return refreshTimeField.getAttribute("value");
    }

    public void ClickOnFreshTimeUpSpinner(){
        refreshTimeUpSpinner.click();
    }

    public void ClickOnFreshTimeDownSpinner(){
        refreshTimeDownSpinner.click();
    }

    public void ClickOnDeleteButton(){
        deleteButton.click();
    }

    public void InputIntoFilterFiled(String text){
        setElementText(filterField, text);
    }

    public void WaitUntilSaveButtonWillBeEnable(){
        try{
            new WebDriverWait(driver, 5).until(ExpectedConditions.attributeContains(saveButton, "aria-disabled", "false"));
        }catch (Exception e){}
    }

    public void ClickOnDeleteIcon(){
        JavaScriptClick(deleteIcon);
    }
}
