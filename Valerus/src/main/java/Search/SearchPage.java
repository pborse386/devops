package Search;

import Utilities.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchPage extends Page {
    @FindBy(xpath = "//nav[@class='search-navigation-container']/ul/li[2]/a")
    WebElement museumSearchButton;

    @FindBy(xpath = "//h2[contains(text(), 'Museum Search')]/..//div[@class='dropdown-list-item-text ng-binding no-select']")
    WebElement cameraSelect;

    @FindBy(xpath = "//div[@class='search-form-date-container outline-none']/div[@class='search-form-datetime-value ng-binding'][1]")
    WebElement startTimeField;

    @FindBy(xpath = "//div[@class='search-form-date-container outline-none']/div[@class='search-form-datetime-value ng-binding'][2]")
    WebElement endTimeField;

    @FindBy(xpath = "//div[@class='dateTimePickers']/div[@class='dateAndTime'][1]//td[@class='form-group uib-time hours']/input[@ng-model='hours']")
    WebElement startTimeHoursField;

    @FindBy(xpath = "//div[@class='dateTimePickers']/div[@class='dateAndTime'][1]//td[@class='form-group uib-time minutes']/input[@ng-model='minutes']")
    WebElement startTimeMinutesField;

    @FindBy(xpath = "//div[@class='dateTimePickers']/div[@class='dateAndTime'][1]//td[@class='form-group uib-time seconds']/input[@ng-model='seconds']")
    WebElement startTimeSecondsField;

    @FindBy(xpath = "//div[@class='dateTimePickers']/div[@class='dateAndTime'][2]//td[@class='form-group uib-time hours']/input[@ng-model='hours']")
    WebElement endTimeHoursField;

    @FindBy(xpath = "//div[@class='dateTimePickers']/div[@class='dateAndTime'][2]//td[@class='form-group uib-time minutes']/input[@ng-model='minutes']")
    WebElement endTimeMinutesField;

    @FindBy(xpath = "//div[@class='dateTimePickers']/div[@class='dateAndTime'][2]//td[@class='form-group uib-time seconds']/input[@ng-model='seconds']")
    WebElement endTimeSecondsField;


    @FindBy(xpath = "//button[@class='search-form-button blue-button-new-ui']")
    WebElement nextButton;

    @FindBy(xpath = "//button[@title='Return To Search']")
    WebElement returnSearchButton;

    @FindBy(xpath = "//div[@title='Select All']")
    WebElement selectAllButton;

    @FindBy(xpath = "//button[@class='museum-search-button blue-button-new-ui']")
    WebElement searchButton;

    @FindBy(xpath = "//div[@class = 'museum-results-list-container']/div[@ng-show='!completed && !inprocess']")
    WebElement nextResultButton;

    @FindBy(xpath = "//div[@class='museum-results-list-container']/div[@class='museum-results-item-container ng-scope']//div[@class='museum-datetime-value text-overflow ng-binding']")
    List<WebElement> resultList;

    @FindBy(xpath = "//button[contains(text(), 'Apply and close')]")
    WebElement applyAndClosetButton;

    @FindBy(xpath = "//div[@class='museum-top-left-container']/div[2]")
    WebElement foundResultField;

    @FindBy(xpath = "//h2[contains(text(), 'Thumbnail Search')]")
    public WebElement thumbnailSearchTitle;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public boolean SearchPageIsLoaded () throws InterruptedException {
      //  waitUntilIsLoadedCustomTime(thumbnailSearchTitle);
        return verifyElementIsPresent(thumbnailSearchTitle);
    }

    public void GoToMuseumSearch() throws InterruptedException {

        waitUntilElementIsLoaded(museumSearchButton);
        museumSearchButton.click();
        boolean flag = MuseumSearchIsLoaded();
        while(!flag){
            museumSearchButton.click();
            flag = MuseumSearchIsLoaded();
        }
    }

    public void ClickOnCameraSelected(){
        cameraSelect.click();
    }

    public void ClickOnNextButton(){
        nextButton.click();
    }

    public void ClickOnSelectAllButton(){
        waitUntilElementIsLoaded(selectAllButton);
        selectAllButton.click();
    }

    public void ClickOnStartTime(){
        startTimeField.click();
    }

    public void ClickOnSearchButton(){
        waitUntilElementIsClickable(searchButton);
        searchButton.click();
    }

    public void ClickOnNextResultButton() throws InterruptedException {
        WaitUntilNextResultButtonIsPresent();
        MoveToElement(nextResultButton);
        nextResultButton.click();
    }

    public boolean NextResultButtonIsPresenT(){
//        nextResultButton.

        boolean flag = false;
        MoveToElement(nextResultButton);

//        WebElement parent = nextResultButton.findElement(By.xpath("/.."));
        String cl = nextResultButton.getAttribute("aria-hidden");
        if(cl.equals("false")) flag =  true;
        return flag;
    }

    public void WaitUntilNextResultButtonIsPresent() throws InterruptedException {
        int i = 0;
        while(true){
//            MoveToElement(nextResultButton);

            Thread.sleep(1000);
            if(NextResultButtonIsPresenT()) break;
            i++;
            if(i>40) break;
        }
//            MoveToElement(nextResultButton);      //   new WebDriverWait(driver, 45).until(ExpectedConditions.visibilityOf(nextResultButton));//
//            new WebDriverWait(driver, 45).until(ExpectedConditions.attributeContains(nextResultButton, "aria-hidden", "false"));
    }

    public int ResultSize(){
        return resultList.size();
    }

    public String GetText(int index){
        return resultList.get(index).getText();
    }

    public void SelectDate(String text){
        WebElement date = driver.findElement(By.xpath("//td/button/span[contains(text(),"+text+")]"));
        date.click();
    }

    public void InputStartTimeHours(String hours){
        setElementText(startTimeHoursField, hours);
    }

    public void InputStartTimeMinutes(String minutes){
        setElementText(startTimeMinutesField, minutes);
    }

    public void InputStartTimeSeconds(String seconds){
        setElementText(startTimeSecondsField, seconds);
    }

    public void InputEndTimeHours(String hours){
        setElementText(endTimeHoursField, hours);
    }

    public void InputEndTimeMinutes(String minutes){
        setElementText(endTimeMinutesField, minutes);
    }

    public void InputEndTimeSeconds(String seconds){
        setElementText(endTimeSecondsField, seconds);
    }

    public void ClickOnEndTime(){
        endTimeField.click();
    }

    public void ClickOnApplyAndCloseButton(){
        applyAndClosetButton.click();
    }

    public void WaitUntilResultsIsLoaded() throws InterruptedException {
        int i = 0;
        while(true){
            Thread.sleep(1000);
            if(foundResultField.getAttribute("aria-disabled").equals("false")) break;
            i++;
            if(i>40) break;
        }
//       new WebDriverWait(driver, 90).until(ExpectedConditions.attributeContains(foundResultField, "class", "museum-search-add-results-container outline-none flex-end disabled"));
    }

    public void ClickOnReturnToSearchButton(){
        returnSearchButton.click();
    }

    public boolean MuseumSearchIsLoaded(){
        boolean flag = false;
        try{
            WebElement el = driver.findElement(By.xpath("//h2[contains(text(), 'Museum Search')]"));
            flag = true;
        }catch (Exception e){
            flag = false;
        }
        return flag;
    }
}
