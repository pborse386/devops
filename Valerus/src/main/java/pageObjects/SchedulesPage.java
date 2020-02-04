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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.lang.model.element.Element;
import java.util.List;

/**
 * Created by nastya on 3/27/2017.
 */
public class SchedulesPage extends Page {
    @FindBy(id = "config-menu-network_entities-scheduler")
    WebElement schedulesButton;

    @FindBy(xpath = "//span[@class='item-text ng-binding'][@title='Schedules']")
    WebElement schedulesButtonLandingPage;

    @FindBy(xpath = "//div[@class='fullSize avms-configuration-central-panel']//div[@class='vms-search-with-btn']//input")
    WebElement filterField;

    @FindBy(xpath = "//div[@class='small-panel fullSize']//button[contains(text(), 'New')]")
    WebElement newButton;

    @FindBy(xpath = "//div[@class='small-panel fullSize']//button[contains(text(), 'Delete')]")
    WebElement deleteButton;

    @FindBy(xpath = "//div[@class='ui-grid-canvas']/div[contains(@class,'ui-grid-row ng-scope')]//button[contains(@class,'vms-grid-delete-button')]")
    List<WebElement> deleteIcon;

    @FindBy(xpath = "//div[@class='garbageInSchedules'][contains(@style, 'block')]/button[@class='vms-grid-delete-button delete-button-visible vicon-font v-trash']")
    WebElement deletePeriodIcon;

    @FindBy(xpath = "//div[@class='ui-grid-contents-wrapper']//div[@class='ui-grid-viewport ng-isolate-scope']//div[@class='ui-grid-canvas']//div[@class='ui-grid-cell-contents ng-binding ng-scope']")
    List<WebElement> schedulesList;

    @FindBy(xpath = "//div[@class='fullSize padding-left-right-15 bottom-separator-builder']/div[@class='configuration-ditails-form']//span[contains(text(),'Name')]/../..//input")
    WebElement nameField;

    @FindBy(xpath = "//div[@class='fullSize padding-left-right-15 bottom-separator-builder']/div[@class='configuration-ditails-form']//span[contains(text(),'Description')]/../..//input")
    WebElement descriptionField;

    @FindBy(xpath = "//div[@class='fullSize padding-left-right-15 bottom-separator-builder']/div[@class='configuration-ditails-form']//select")
    WebElement repeatSelect;

    @FindBy(xpath = "//button[@class='button-new-ui height-32']")
    WebElement addSchedulePeriodButton;

    @FindBy(xpath="//div[@ng-repeat='period in schedulerController.selectedSchedule.periods']//div[@class='period-header ng-binding']")
    List<WebElement> periodNumber;

    @FindBy(xpath = "//div[@class='vms-big-btn-footer bottom-separator-builder new-ui-panel-background']/button[contains(text(), 'Cancel')]")
    WebElement cancelButton;

    @FindBy(xpath = "//div[@class='vms-big-btn-footer bottom-separator-builder new-ui-panel-background']/button[contains(text(), 'Save')]")
    WebElement saveButton;

    @FindBy(xpath = "//div[@class='modal-footer modal-footer-confirm']/button[contains(text(), 'yes') or contains(text(), 'Yes')]")
    WebElement yesButton;

    @FindBy(xpath = "//div[@class='modal-footer modal-footer-confirm']/button[contains(text(), 'no') or contains(text(), 'No')]")
    WebElement noButton;

    //Weekly
    @FindBy(xpath="//div[@class='remove-float']//input[@ng-model='period.name']")
    List<WebElement> periodName;

    @FindBy(xpath="//timepicker-pop[@input-time='period.startTime']//input[@ng-model='inputTime']")
    List<WebElement> startTimeField;

    @FindBy(xpath="//timepicker-pop[@input-time='period.endTime']//input[@ng-model='inputTime']")
    List<WebElement> endTimeField;

    @FindBy(xpath="//div[@class='configuration-ditails-form']//label[@class='control control--checkbox']/input[@type='checkbox']")
    List<WebElement> daysCheckBox;

    @FindBy(xpath="//div[@class='configuration-ditails-form']//label[@class='control control--checkbox']/div[@class='control__indicator']")
    List<WebElement> daysCheckBoxButton;

    @FindBy(xpath = "//input[@ng-model='period.duration.hours']")
    List<WebElement> durationHoursField;

    @FindBy(xpath = "//input[@ng-model='period.duration.minutes']")
    List<WebElement> durationMinutesField;

    @FindBy(xpath = "//div[@ng-model='period.duration.hours']//a[@class='ui-spinner-button ui-spinner-up ui-corner-tr']")
    List<WebElement> durationHoursUpSpinner;

    @FindBy(xpath = "//div[@ng-model='period.duration.hours']//a[@class='ui-spinner-button ui-spinner-down ui-corner-br']")
    List<WebElement>  durationHoursDownSpinner;

    @FindBy(xpath = "//div[@ng-model='period.duration.minutes']//a[@class='ui-spinner-button ui-spinner-up ui-corner-tr']")
    List<WebElement>  durationMinutesUpSpinner;

    @FindBy(xpath = "//div[@ng-model='period.duration.minutes']//a[@class='ui-spinner-button ui-spinner-down ui-corner-br']")
    List<WebElement>  durationMinutesDownSpinner;

    //Monthly
    @FindBy(xpath = "//span[contains(@class,'ui-spinner ui-widget ui-corner-all ui-widget-header')]//input[@ng-model='period.monthlyDate']")
    List<WebElement> countDaysField;

    @FindBy(xpath = "//div[@ng-model='period.monthlyDate']//span['ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-up ui-corner-tr']")
    List<WebElement> daysUpSnipper;

    @FindBy(xpath = "//div[@ng-model='period.monthlyDate']//span['ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-down ui-corner-br']")
    List<WebElement> daysDownSnipper;

    @FindBy(xpath = "//div[@class='export-list ng-scope']/form/input[@type='radio'][@value='specificDate']")
    List<WebElement> dayRadioButton;

    @FindBy(xpath = "//div[@class='configuration-ditails-form']/input[@type='radio'][@value='custom']")
    List<WebElement> dayOfMonthRadioButton;

    @FindBy(id = "numDayMonthly")
    List<WebElement> numberOfDaysMonthlySelect;

    @FindBy(id = "dayWeekMonthly")
    List<WebElement> dayWeekMonthlySelect;

    //Yearly

    @FindBy(id = "monthYearly")
    List<WebElement> monthSelect;

    @FindBy(xpath = "//div[@class='configuration-ditails-form']//input[@ng-model='period.yearlyDayInMonth']")
    List<WebElement> countDaysofMonthField;

    @FindBy(id = "numdayyearly")
    List<WebElement> numberDayYearlySelect;

    @FindBy(id = "dayWeekYearly")
    List<WebElement> dayOfWeekYearlySelect;

    @FindBy(id = "monthTheYearly")
    List<WebElement> monthYearlySelect;

    @FindBy(xpath = "//timepicker-pop//div[@class='input-group-btn open']//input[@ng-model='hours']")
    WebElement timeHoursField;

    @FindBy(xpath = "//timepicker-pop//div[@class='input-group-btn open']//input[@ng-model='minutes']")
    WebElement timeMinutesField;

    @FindBy(xpath = "//div[@class='input-group-btn open']//a[@ng-click='incrementHours()']")
    WebElement hoursUpSpinner;

    @FindBy(xpath = "//div[@class='input-group-btn open']//a[@ng-click='decrementHours()']")
    WebElement hoursDownSpinner;

    @FindBy(xpath = "//div[@class='input-group-btn open']//a[@ng-click='incrementMinutes()']")
    WebElement minutesUpSpinner;

    @FindBy(xpath = "//div[@class='input-group-btn open']//a[@ng-click='decrementMinutes()']")
    WebElement minutesDownSpinner;

    @FindBy(id = "monthYearly")
    WebElement monthOfYearSelect;

    @FindBy(xpath = "//div[@class='configuration-ditails-form']//input[@ng-model='period.yearlyDayInMonth']")
    WebElement countDaysInMonthField;

    @FindBy(xpath = "//div[@ng-model='period.yearlyDayInMonth']//span['ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-up ui-corner-tr']")
    List<WebElement> monthUpSnipper;

    @FindBy(xpath = "//div[@ng-model='period.yearlyDayInMonth']//span['ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-down ui-corner-br']")
    List<WebElement> monthDownSnipper;

    @FindBy(xpath = "//div[@class='configuration-ditails-form']/input[@type='radio'][@value='every']")
    List<WebElement> everyRadioButton;

    @FindBy(xpath = "//div[@class='configuration-ditails-form']/input[@type='radio'][@value='the']")
    List<WebElement> yearlyRadioButton;

    // Never repeat
    @FindBy(xpath = "//input[@ng-model='period.startDate']")
    List<WebElement> startTimeDateField;

    @FindBy(xpath = "//input[@ng-model='period.endDate']")
    List<WebElement> endTimeDateField;

    @FindBy(xpath = "//div[@class='remove-float']//div[@class='configuration-ditails-form side-by-side-spinner']/ul")
    List<WebElement> startTimeDateTable;

    @FindBy(xpath = "//div[@class='remove-float']//div[@class='configuration-ditails-form']/ul")
    List<WebElement> endTimeDateTable;

    @FindBy(xpath = "//div[@class='remove-float']//div[@class='configuration-ditails-form side-by-side-spinner']/ul//table/tbody//td/button[@aria-disabled='false']/span[@class='ng-binding text-info']")
    WebElement startTimeSelectedDateCell;

    @FindBy(xpath = "//div[@class='configuration-ditails-form side-by-side-spinner']/ul//table/tbody//td/button[@aria-disabled='false']/span[@class='ng-binding'][1]")
    WebElement startTimeNextDateCell;

    @FindBy(xpath = "//div[@class='configuration-ditails-form']//ul//table/tbody//td/button[@aria-disabled='false'][@class='btn btn-default btn-sm']/span[@class='ng-binding'][1]")
    WebElement endTimeNextDateCell;

    //Calendar
    @FindBy(xpath = "//*[@aria-hidden='false']//section/span[@class ='input-group-btn']/button[@class='btn btn-default']")
    WebElement calendarButton;

    @FindBy(xpath = "//table[@class='uib-daypicker']/thead//button[@class='btn btn-default btn-sm uib-title']")
    WebElement dateButton;

    @FindBy(xpath = "//table[@class='uib-monthpicker']/thead//button[@class='btn btn-default btn-sm uib-title']")
    WebElement yearButton;

    @FindBy(xpath = "//table[@class='uib-monthpicker']/tbody//td[@class='uib-month text-center ng-scope']//button[@class='btn btn-default'][@aria-disabled='false']")
    List<WebElement> monthsList;

    @FindBy(xpath = "//table[@class='uib-yearpicker']/tbody//td[@class='uib-year text-center ng-scope']//button[@class='btn btn-default'][@aria-disabled='false']")
    List<WebElement> yearthList;

    @FindBy(xpath = "//table[@class='uib-daypicker']/tbody//td[@class='uib-day text-center ng-scope']//button[@class='btn btn-default btn-sm'][@aria-disabled='false']")
    List<WebElement> daysList;

    @FindBy(xpath = "//span[@class='btn-group pull-left']/button[contains(text(), 'Today')]")
    WebElement todayButton;

    @FindBy(xpath = "//div[contains(text(), 'The schedule you are trying to delete is currently active')]/../..//button[contains(text(), 'Ok')]")
    WebElement errorInSchedule;


    public SchedulesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void GoToSchedulesPage() throws InterruptedException {
        Thread.sleep(1000);
        try{GoToConfigurationPage();
            Thread.sleep(1000);

            ClickOnResourcesButton();
            waitUntilResourcesMenuIsExpanded();
            Thread.sleep(1000);
            ClickOnSchedulesButton();}
        catch(Exception e){
            takeScreenshot(driver, "Setup", "GoToSchedulesPage");
        }
    }

    public void GoToSchedulesFromLandingPage() throws InterruptedException {
        Thread.sleep(1000);
        GoToConfigurationPage();
        Thread.sleep(1000);
        ClickOnSchedulesButtonFromLandingPage();
    }

    public boolean ErrorOnSchedulesExists(){
        return verifyElementIsPresent(errorInSchedule);
    }

    public void ClickOnSchedulesButton() throws InterruptedException {
//        Thread.sleep(2000);
    //    GoToConfigurationPage();
  //      new WebDriverWait(driver, 30).until(ExpectedConditions.attributeContains(configurationButton, "class", "vms-header-navigation-button vms-nav-button-state-configuration active" ));
      //  waitUntilElementIsLoaded(resourcesButton);
      //  resourcesButton.click();
        waitUntilElementIsLoaded(schedulesButton);
        JavaScriptClick(schedulesButton);
        Thread.sleep(1000);
    }

    public void ClickOnSchedulesButtonFromLandingPage() throws InterruptedException {
        waitUntilElementIsLoaded(schedulesButtonLandingPage);
        JavaScriptClick(schedulesButtonLandingPage);
        Thread.sleep(1000);
    }

    public String GetDescription(){
      return descriptionField.getAttribute("value");
    }

    public String GetRepeat(){
       return new Select(repeatSelect).getFirstSelectedOption().getText();
    }

    public int GetRepeatOptionsCount(){
        return new Select(repeatSelect).getOptions().size();
    }

    public String GetRepeatOptionNameByIndex(int index){
        return new Select(repeatSelect).getOptions().get(index).getText();
    }

    public void SelectOptionByIndex(int index){
        new Select(repeatSelect).selectByIndex(index);
    }

    public void SelectRepeatByValue(String repeat){
        new Select(repeatSelect).selectByValue(repeat);
    }

    public String GetStartTime(int i){
        return startTimeField.get(i).getAttribute("title");
    }

    public String GetEndTime(int i){
        return endTimeField.get(i).getAttribute("title");
    }

    public int CountOfPeriods(){
        return periodNumber.size();
    }

    public String GetCountOfDays(int index){
        return  countDaysField.get(index).getAttribute("value");
    }

    public boolean CountOfDaysFieldIsEnabled(int index){
        return  countDaysField.get(index).isEnabled();
    }

    //Yearly

    public String GetMonth(int index){
        return new Select(monthSelect.get(index)).getFirstSelectedOption().getText();
    }

    public boolean MonthSelectIsEnabled(int index){
        return monthSelect.get(index).isEnabled();
    }

    public int GetMonthOPtionSize(int index){
        return new Select(monthSelect.get(index)).getOptions().size();
    }

    public String GetMonthOptionByIndex(int periodIndex, int index){
        return new Select(monthSelect.get(periodIndex)).getOptions().get(index).getText();
    }

    public void SelectMonthOptionByIndex(int periodIndex, int index){
        new Select(monthSelect.get(periodIndex)).selectByIndex(index);
    }

    public String GetCountDaysInMonth(int index){
        return countDaysofMonthField.get(index).getAttribute("value");
    }

    public boolean CountDaysInMonthFieldIsEnabled(int index){
        return countDaysofMonthField.get(index).isEnabled();
    }

    public void InputInCountDaysInMonth(int index, String days){
        setElementText(countDaysofMonthField.get(index), days);
    }

    public int GetNumberOfDayInMonthOPtionSize(int index){
        return new Select(numberDayYearlySelect.get(index)).getOptions().size();
    }

    public boolean NumberOfDayInMonthDropDownIsEnabled(int index){
        return numberDayYearlySelect.get(index).isEnabled();
    }

    public String GetNumberOfDayOptionByIndex(int periodIndex, int index){
        return new Select(numberDayYearlySelect.get(periodIndex)).getOptions().get(index).getText();
    }

    public void SelectNumberOfDayOptionByIndex(int periodIndex, int index){
        new Select(numberDayYearlySelect.get(periodIndex)).selectByIndex(index);
    }

    public String GetNumberOfDaysYearly(int index){
        return new Select(numberDayYearlySelect.get(index)).getFirstSelectedOption().getText();
    }

    public int GetDayOfWeekInMonthOPtionSize(int index){
        return new Select(dayOfWeekYearlySelect.get(index)).getOptions().size();
    }

    public boolean DayOfWeekDropDownInMonthIsEnabled(int index){
        return dayOfWeekYearlySelect.get(index).isEnabled();
    }

    public String GetDayOfWeekOptionByIndex(int periodIndex, int index){
        return new Select(dayOfWeekYearlySelect.get(periodIndex)).getOptions().get(index).getText();
    }

    public void SelectDayOfWeekOptionByIndex(int periodIndex, int index){
        new Select(dayOfWeekYearlySelect.get(periodIndex)).selectByIndex(index);
    }

    public String GetDayOfWeekYearly(int index){
        return new Select(dayOfWeekYearlySelect.get(index)).getFirstSelectedOption().getText();
    }

    public String GetMonthYearly(int index){
        return new Select(monthYearlySelect.get(index)).getFirstSelectedOption().getText();
    }

    public boolean MonthOfYearDropDownIsEnabled(int index){
        return monthYearlySelect.get(index).isEnabled();
    }

    public boolean SchedulesPageIsLoaded(){
        return verifyElementIsPresent(newButton);
    }

    public void InputIntoFilterField(String text){
//        filterButton.click();
        setElementText(filterField, text);
    }

    public void ClickOnNewButton(){
        newButton.click();
    }

    public void PressSaveButton(){
        waitUntilElementIsClickable(saveButton);
        JavaScriptClick(saveButton);
        new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='fade-in-panel']")));
    }

    public void PressCancelButton(){
        waitUntilElementIsClickable(cancelButton);
        JavaScriptClick(cancelButton);
    }

    public boolean SaveButtonIsEnabled(){
        return saveButton.isEnabled();
    }

    public boolean CancelButtonIsEnabled(){
        return cancelButton.isEnabled();
    }

    public int GetSchedulesCount(){
        return  schedulesList.size();
    }

    public void InputInNameField(String name){
        setElementText(nameField, name);
    }

    public String GetScheduleNameFromProperties(){
        return nameField.getAttribute("value");
    }

    public void InputInDescriptionField(String name){
        setElementText(descriptionField, name);
    }

    public String GetScheduleDescriptionFromProperties(){
        return descriptionField.getAttribute("value");
    }

    public void ClickOnScheduleByName(String name){
        WebElement schedule = driver.findElement(By.xpath("//div[contains(@class,'ui-grid-row ng-scope')]//div[contains(text(), '"+name+"')]"));

        while(true){
            String scheduleName = GetScheduleNameFromProperties();
            if(scheduleName.equals(name)) break;
            JavaScriptClick(schedule);
        }
    }

    public void ClickOnScheduleByIndex(int index) {
        WebElement schedule = driver.findElement(By.xpath("//div[@class='ui-grid-canvas']/div[contains(@class, 'ui-grid-row ng-scope')]["+(index+1)+"]"));
        ClickOnElement(schedule);
    }

    public String GetScheduleNameByIndex(int index){
        return schedulesList.get(index).getText();
    }

    public void ClickOnDeleteIconByIndex(int index){
        deleteIcon.get(index).click();
    }

    public void MoveToScheduleByIndex(int index){
        MoveToElement(schedulesList.get(index));
    }

    public void ClickOnDeleteButton(){
        deleteButton.click();
    }

    public String GetTextNumDayByIndex(int ind, int index){
        List<WebElement> options = new Select(numberOfDaysMonthlySelect.get(ind)).getOptions();
        return options.get(index).getText();
    }

    public int GetCountOfOptionsInNumberDaysSelect(int ind){
        List<WebElement> options = new Select(numberOfDaysMonthlySelect.get(ind)).getOptions();
        return options.size();
    }

    public int GetCountOfOptionsInDaysWeekSelect(int periodInd){
        List<WebElement> options = new Select(dayWeekMonthlySelect.get(periodInd)).getOptions();
        return options.size();
    }

    public boolean DaysWeekSelectIsEnabled(int periodInd){
       return dayWeekMonthlySelect.get(periodInd).isEnabled();
    }

    public void SetOptionsByIndexDaysWeek(int periodInd, int index){
        new Select(dayWeekMonthlySelect.get(periodInd)).selectByIndex(index);
//        List<WebElement> options = new Select(dayWeekMonthlySelect).getOptions();
//        return options.get(index);
    }

    public String GetTextSelectedOptionDayWeek(int periodInd){
        WebElement element = new Select(dayWeekMonthlySelect.get(periodInd)).getFirstSelectedOption();
        return element.getText();
    }

    public String GetTextDayWeekByIndex(int periodInd, int index){
        List<WebElement> options = new Select(dayWeekMonthlySelect.get(periodInd)).getOptions();
        return options.get(index).getText();
    }

    public void SetOptionsByIndexNumberDays(int ind, int index){
        new Select(numberOfDaysMonthlySelect.get(ind)).selectByIndex(index);
    }

    public boolean NumberOfDaysDropDownListIsEnabled(int ind){
        return numberOfDaysMonthlySelect.get(ind).isEnabled();
    }

    public String GetTextSelectedOptionNumberDays(int ind){
        WebElement element = new Select(numberOfDaysMonthlySelect.get(ind)).getFirstSelectedOption();
        return element.getText();
    }


    public void SelectWeekly(){
        List<WebElement> days= driver.findElements(By.id("cbWeekly"));
//        new WebDriverWait(driver, 30).until(ExpectedConditions.numberOfElementsToBe(By.id("cbWeekly"), 7));
        int max = days.size()-1;
        int random = GetRandomDigit(0, max);
        days.get(0).click();
        days.get(random).click();
    }

    public boolean DayIsSelected(int index){
        return daysCheckBox.get(index).isSelected();
    }

    public void ClickOnDayCheckBoxByIndex(int index){
        daysCheckBoxButton.get(index).click();
    }

    public int GetCountDaysCheckBox(){
        return daysCheckBox.size();
    }

    public void PressEndTime(int i){
        endTimeField.get(i).click();
    }

    public void PressStartTime(int i){
        startTimeField.get(i).click();
    }

    public void InputHours(String hours) throws InterruptedException {
        setElementText(timeHoursField, hours);
        Thread.sleep(1000);
    }

    public void InputMinutes( String minutes) throws InterruptedException {
        setElementText(timeMinutesField, minutes);
        Thread.sleep(1000);
    }

    public String GetHours(){
        return timeHoursField.getAttribute("value");
    }

    public int GetEndTimeHours(int index){
        String time = endTimeField.get(index).getAttribute("value");
        int separator = time.indexOf(":");
        String hours = time.substring(0,separator);
        return Integer.parseInt(hours);
    }

    public int GetEndTimeMinutes(int index){
        String time = endTimeField.get(index).getAttribute("value");
        int separator = time.indexOf(":");
        String minutes = time.substring(separator+1);
        return Integer.parseInt(minutes);
    }


    public int GetStartTimeHours(int index){
        String time = startTimeField.get(index).getAttribute("value");
        int separator = time.indexOf(":");
        String hours = time.substring(0,separator);
        return Integer.parseInt(hours);
    }

    public int GetStartTimeMinutes(int index){
        String time = startTimeField.get(index).getAttribute("value");
        int separator = time.indexOf(":");
        String minutes = time.substring(separator+1);
        return Integer.parseInt(minutes);
    }

    public String  GetMinutes(){
        return timeMinutesField.getAttribute("value");
    }

    public void ClickOnAddSchedulePeriodButton(){
        addSchedulePeriodButton.click();
    }

    public int GetDurationHours(int i){
        return Integer.parseInt(durationHoursField.get(i).getAttribute("value"));
    }

    public int GetDurationMinutes(int i){
        return Integer.parseInt(durationMinutesField.get(i).getAttribute("value"));
    }

    public void InputDurationHours(int i, String hours){
        setElementText(durationHoursField.get(i), hours);
    }

    public void InputDurationMinutes(int i, String minutes){
        setElementText(durationMinutesField.get(i), minutes);
    }

    public void ClickOnDurationHoursUpSpinner(int index){
        waitUntilElementIsLoaded(durationHoursUpSpinner.get(index));
        durationHoursUpSpinner.get(index).click();
    }

    public void ClickOnDurationHoursDownSpinner(int index){
        waitUntilElementIsLoaded(durationHoursDownSpinner.get(index));
        durationHoursDownSpinner.get(index).click();
    }

    public void ClickOnDurationMinutesUpSpinner(int index){
        waitUntilElementIsLoaded(durationMinutesUpSpinner.get(index));
        durationMinutesUpSpinner.get(index).click();
    }

    public void ClickOnDurationMinutesDownSpinner(int index){
        waitUntilElementIsLoaded(durationMinutesDownSpinner.get(index));
        durationMinutesDownSpinner.get(index).click();
    }

    public void ClickOnSpinnerHoursUP(){
        hoursUpSpinner.click();
    }

    public void ClickOnSpinnerHoursDown(){
        hoursDownSpinner.click();
    }

    public void ClickOnSpinnerMinutesUP( ){
        minutesUpSpinner.click();
    }

    public void ClickOnSpinnerMinutesDown(){
        minutesDownSpinner.click();
    }

    public int CountPeriod(){
        return periodNumber.size();
    }

    public void MoveToPeriodByIndex(int index) throws InterruptedException {
        Actions builder = new Actions(driver);
        builder.moveToElement(periodNumber.get(index)).build().perform();
    }

    public void InputInDaysField(int index, String count){
        setElementText(countDaysField.get(index), count);
    }

    public void ClickOnDaysUpSpinner(int index){
        daysUpSnipper.get(index).click();
    }

    public void ClickOnDaysDownSpinner(int index){
        daysDownSnipper.get(index).click();
    }

    public void ClickOnDayOfMonthRadioButton(int index){
        dayOfMonthRadioButton.get(index).click();
    }

    public boolean DayOfMonthRadioButtonIsSelected(int index){
        return dayOfMonthRadioButton.get(index).isSelected();
    }

    public void ClickOnDayRadioButton(int index){
        dayRadioButton.get(index).click();
    }

    public boolean DayRadioButtonIsSelected(int index){
        return dayRadioButton.get(index).isSelected();
    }

    public void ClickOnEveryRadioButton(int index){
        ClickOnElement(everyRadioButton.get(index));
    }

    public boolean EveryRadioButtonIsSelected(int index){
        return everyRadioButton.get(index).isSelected();
    }

    public void SelectMonthByIndex(int index){
        new Select(monthOfYearSelect).selectByIndex(index);
    }

    public int CountOfMonthOption(){
        List<WebElement> options= new Select(monthOfYearSelect).getOptions();
        return options.size();
    }

    public String GetTextFromSelectedOptions(){
        String option = new Select(monthOfYearSelect).getFirstSelectedOption().getText();
        return option;
    }

    public String GetTextFromOptionByIndex(int index){
        List<WebElement> options = new Select(monthOfYearSelect).getOptions();
        return options.get(index).getText();
    }

    public void SetCountOfDaysInMonth(String count){
        setElementText(countDaysInMonthField, count);
    }

    public void ClickDaysOfMonthUpSpinner(int index){
        monthUpSnipper.get(index).click();
    }

    public void ClickDaysOfMonthDownSpinner(int index){
        monthDownSnipper.get(index).click();
    }

    //Yearly

    public void SelectMonthYearlyByIndex(int periodIndex, int index){
        new Select(monthYearlySelect.get(periodIndex)).selectByIndex(index);
    }

    public int CountMonthsYearlyOption(int index){
        List<WebElement> options= new Select(monthYearlySelect.get(index)).getOptions();
        return options.size();
    }

    public String GetTextFromOptionMonthYearlyByIndex(int periodIndex, int index){
        List<WebElement> options = new Select(monthYearlySelect.get(periodIndex)).getOptions();
        return options.get(index).getText();
    }

    public void ClickOnTheStartDate(int index){
        JavaScriptClick(startTimeDateField.get(index));
//        startTimeDateField.get(index).sendKeys(Keys.ENTER);
//        startTimeDateField.get(index).click();
    }

    public void SelectNextDateInStartDate(){
        startTimeNextDateCell.click();
    }

    public void ClickOnTheEndDate(int index) throws InterruptedException {
        JavaScriptClick(endTimeDateField.get(index));
    }

    public void SelectNextDateInEndDate(){
        endTimeNextDateCell.click();
    }

    public void ClickOnYearlyRadioButton(int periodIndex){
        ClickOnElement(yearlyRadioButton.get(periodIndex));
    }

    public boolean YearlyRadioButtonIsSelected(int periodIndex){
       return yearlyRadioButton.get(periodIndex).isSelected();
    }

    public void InputPeriodName(int index, String name){
        setElementText(periodName.get(index), name);
    }

    public String GetPeriodName(int index){
        return periodName.get(index).getAttribute("value");
    }

    public void ClickOnPeriodField(int index){
        periodName.get(index).click();
    }

    public void ClickOnDateButtonInCalendar(){
//        dateButton.sendKeys(Keys.ENTER);
           JavaScriptClick(dateButton);
//        ClickOnElement(dateButton);
    }

    public void ClickOnYearButton(){
        JavaScriptClick(yearButton);
//        yearButton.click();
    }

    public int GetYearsListSize(){
        return yearthList.size();
    }

    public void ClickOnYearByIndex(int index){
        yearthList.get(index).click();
    }

    public int GetMonthListSize(){
        return monthsList.size();
    }

    public void ClickOnMonthByIndex(int index){
        monthsList.get(index).click();
    }

    public int GetDaysListSize(){
        return daysList.size();
    }


    public void ClickOnDaysByIndex(int index){
        daysList.get(index).click();
    }

    public String GetStartDate(int index){
        return startTimeDateField.get(index).getAttribute("value");
    }

    public String GetEndDate(int index){
        return endTimeDateField.get(index).getAttribute("value");
    }

    public void ClickOnTodayButtonInCalendar(){
        todayButton.click();
    }

    public void ClickOnDeletePeriodIcon(){
       JavaScriptClick(deletePeriodIcon);
    }

    public boolean DeletePeriodIconIsExist(){
        return verifyElementIsPresent(deletePeriodIcon);
    }






}
