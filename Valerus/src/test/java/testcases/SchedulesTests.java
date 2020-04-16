package testcases;

import pageObjects.*;
import pageObjects.MonitoringPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;


public class SchedulesTests {
    WebDriver driver;
    SchedulesPage schedulesPage;
    ViewsPage viewsPage;
    VideoChannelsPage videoChannelsPage;
    AudioChannelsPage audioChannelsPage;
    RulesPage rulesPage;
    String[] Servers;
    ExtentReports report;
    ExtentTest logger;

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void setup(@Optional("ie")String browser) throws IOException, InterruptedException {
        
    	
    /*	String WebDriverLocation = System.getenv("WebDriver");

        if(browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", WebDriverLocation+"\\chromedriver.exe");
            driver = new ChromeDriver();
        }else if (browser.equalsIgnoreCase("ie")) {
            System.setProperty("webdriver.ie.driver", WebDriverLocation+"\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }
        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
        rulesPage = PageFactory.initElements(driver, RulesPage.class);
        schedulesPage = PageFactory.initElements(driver, SchedulesPage.class);
        viewsPage = PageFactory.initElements(driver, ViewsPage.class);
        videoChannelsPage = PageFactory.initElements(driver, VideoChannelsPage.class);
        audioChannelsPage = PageFactory.initElements(driver, AudioChannelsPage.class);
        Servers = rulesPage.getServersList();

        driver.navigate().to("http://" + Servers[0]);
        rulesPage.SignIn();

        schedulesPage.WaitUntilLoadingBlockAppears();
        schedulesPage.WaitUntilLoadingBlockDisappears();
        schedulesPage.GoToSchedulesFromLandingPage();   */
    }

    @BeforeTest
    public void startReport(){
        report=new ExtentReports(System.getProperty("user.dir") +"/test-output/Schedules/ScheduleReport.html", true);
    }

    @BeforeMethod(alwaysRun = true)
    public void GoToSchedulesPage() throws InterruptedException, IOException {
        
    	
    	
    	 String WebDriverLocation = System.getenv("WebDriver");
    	 System.setProperty("webdriver.ie.driver", WebDriverLocation+"\\IEDriverServer.exe");
         DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
         capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
         capabilities.setCapability(InternetExplorerDriver.IE_USE_PER_PROCESS_PROXY, true);
         capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
         capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
         driver = new InternetExplorerDriver(capabilities);  
    	
    	
         driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
         driver.manage().window().maximize();
         rulesPage = PageFactory.initElements(driver, RulesPage.class);
         schedulesPage = PageFactory.initElements(driver, SchedulesPage.class);
         viewsPage = PageFactory.initElements(driver, ViewsPage.class);
         videoChannelsPage = PageFactory.initElements(driver, VideoChannelsPage.class);
         audioChannelsPage = PageFactory.initElements(driver, AudioChannelsPage.class);
         Servers = rulesPage.getServersList();

         driver.navigate().to("http://" + Servers[0]);
         rulesPage.SignIn();

         schedulesPage.WaitUntilLoadingBlockAppears();
         schedulesPage.WaitUntilLoadingBlockDisappears();
         schedulesPage.GoToSchedulesFromLandingPage();
         
         
         
    	
    	driver.get("http://" + Servers[0]);
        try{
            driver.switchTo().alert().accept();
        }catch(Exception a){}

        schedulesPage.WaitUntilLoadingBlockAppears();
        schedulesPage.WaitUntilLoadingBlockDisappears();
        schedulesPage.GoToSchedulesPage();
    }

    @Test
    public void AddNewScheduleTest() throws InterruptedException {
        logger=report.startTest("AddNewScheduleTest");

        int schedulesCount = schedulesPage.GetSchedulesCount();

        logger.log(LogStatus.INFO,"Click on New button");
        schedulesPage.ClickOnNewButton();

        logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
        Assert.assertTrue(schedulesPage.CancelButtonIsEnabled());

        logger.log(LogStatus.INFO,"Check that Save button is enabled");
        Assert.assertTrue(schedulesPage.SaveButtonIsEnabled());

        logger.log(LogStatus.INFO,"Press on Save button");
        schedulesPage.PressSaveButton();

        int schedulesCountAct = schedulesPage.GetSchedulesCount();
        logger.log(LogStatus.INFO,"Check that schedule is added");
        Assert.assertEquals(schedulesCountAct, schedulesCount+1);

        logger.log(LogStatus.INFO,"Refresh");
        schedulesPage.Refresh();

        int schedulesCountRefr = schedulesPage.GetSchedulesCount();
        logger.log(LogStatus.INFO,"Check that schedule is added after refresh");
        Assert.assertEquals(schedulesCountRefr, schedulesCount+1);
    }

    @Test
    public void AddNewScheduleWithAllParametersTest() throws InterruptedException {
        logger=report.startTest("AddNewScheduleWithAllParametersTest");

        int schedulesCount = schedulesPage.GetSchedulesCount();

        logger.log(LogStatus.INFO,"Click on New button");
        schedulesPage.ClickOnNewButton();

        String scheduleName = "NewSchedule"+schedulesPage.GetRandomDigit(0,10000);
        String scheduleDescription = "Description for schedule "+scheduleName;

        logger.log(LogStatus.INFO,"Input into name field");
        schedulesPage.InputInNameField(scheduleName);

        logger.log(LogStatus.INFO,"Input into schedule field");
        schedulesPage.InputInDescriptionField(scheduleDescription);

        int optionsCount = schedulesPage.GetRepeatOptionsCount();
        int randOptionInd = schedulesPage.GetRandomDigit(0, optionsCount);
        String optionName = schedulesPage.GetRepeatOptionNameByIndex(randOptionInd);

        logger.log(LogStatus.INFO,"Select repeat option "+optionName);
        schedulesPage.SelectOptionByIndex(randOptionInd);
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
        Assert.assertTrue(schedulesPage.CancelButtonIsEnabled());

        logger.log(LogStatus.INFO,"Check that Save button is enabled");
        Assert.assertTrue(schedulesPage.SaveButtonIsEnabled());

        logger.log(LogStatus.INFO,"Press on Save button");
        schedulesPage.PressSaveButton();

        int schedulesCountAct = schedulesPage.GetSchedulesCount();
        logger.log(LogStatus.INFO,"Check that schedule is added");
        Assert.assertEquals(schedulesCountAct, schedulesCount+1);

        String scheduleNameAct = schedulesPage.GetScheduleNameFromProperties();
        logger.log(LogStatus.INFO,"Check that schedule name is "+scheduleName);
        Assert.assertEquals(scheduleNameAct, scheduleName);

        String scheduleDescriptionAct = schedulesPage.GetScheduleDescriptionFromProperties();
        logger.log(LogStatus.INFO,"Check that schedule description is "+scheduleDescription);
        Assert.assertEquals(scheduleDescriptionAct, scheduleDescription);

        String repeatOptionAct = schedulesPage.GetRepeat();
        logger.log(LogStatus.INFO,"Check that schedule repeat option is "+optionName);
        Assert.assertEquals(repeatOptionAct, optionName);

        logger.log(LogStatus.INFO,"Refresh");
        schedulesPage.Refresh();

        int schedulesCountRefr = schedulesPage.GetSchedulesCount();
        logger.log(LogStatus.INFO,"Check that schedule is added after refresh");
        Assert.assertEquals(schedulesCountRefr, schedulesCount+1);

        logger.log(LogStatus.INFO,"Click on schedule "+scheduleName);
        schedulesPage.ClickOnScheduleByName(scheduleName);

        String scheduleNameRefr = schedulesPage.GetScheduleNameFromProperties();
        logger.log(LogStatus.INFO,"Check that schedule name is "+scheduleName+" after refresh");
        Assert.assertEquals(scheduleNameRefr, scheduleName);

        String scheduleDescriptionRefr = schedulesPage.GetScheduleDescriptionFromProperties();
        logger.log(LogStatus.INFO,"Check that schedule description is "+scheduleDescription+" after refresh");
        Assert.assertEquals(scheduleDescriptionRefr, scheduleDescription);

        String repeatOptionRefr = schedulesPage.GetRepeat();
        logger.log(LogStatus.INFO,"Check that schedule repeat option is "+optionName+" after refresh");
        Assert.assertEquals(repeatOptionRefr, optionName);
    }

    @Test
    public void FilterSchedulesTest() throws InterruptedException {
        logger=report.startTest("FilterSchedulesTest");

        int size = schedulesPage.GetSchedulesCount();
        if(size>0){
            int index = schedulesPage.GetRandomDigit(0, size);
            String name = schedulesPage.GetScheduleNameByIndex(index);

            logger.log(LogStatus.INFO,"Input "+name+" into filter field");
            schedulesPage.InputIntoFilterField(name);
            Thread.sleep(1000);

            size = schedulesPage.GetSchedulesCount();
            for(int i=0; i<size; i++){
                String schedule = schedulesPage.GetScheduleNameByIndex(i);

                logger.log(LogStatus.INFO,"Check that filtered schedule name contains inputed");
                Assert.assertTrue(schedule.contains(name));
            }
        }
    }

    @Test
    public void DeleteScheduleByIconTest() throws InterruptedException {
        logger=report.startTest("DeleteScheduleByIconTest");

        int size = schedulesPage.GetSchedulesCount();
        if(size>0){
            int index = schedulesPage.GetRandomDigit(0,size);
            String name = schedulesPage.GetScheduleNameByIndex(index);

            logger.log(LogStatus.INFO,"Move to schedule "+name);
            schedulesPage.MoveToScheduleByIndex(index);

            logger.log(LogStatus.INFO,"Click on Delete icon");
            schedulesPage.ClickOnDeleteIconByIndex(index);

            logger.log(LogStatus.INFO,"Click on Yes button in Delete dialog");
            schedulesPage.ConfirmRemoving();

            boolean ErrorDialog = schedulesPage.ErrorOnSchedulesExists();
            if(!ErrorDialog){
                int sizeAct = schedulesPage.GetSchedulesCount();
                logger.log(LogStatus.INFO,"Check that schedule "+name+" is deleted");
                Assert.assertEquals(sizeAct,size-1);

                logger.log(LogStatus.INFO,"Check that Save button is disabled");
                Assert.assertFalse(schedulesPage.SaveButtonIsEnabled());

                logger.log(LogStatus.INFO,"Check that Cancel button is disabled");
                Assert.assertFalse(schedulesPage.CancelButtonIsEnabled());
            }
        }
    }

    @Test
    public void DeleteScheduleByIconAndClickNoTest() throws InterruptedException {
        logger=report.startTest("DeleteScheduleByIconAndClickNoTest");

        int size = schedulesPage.GetSchedulesCount();
        if(size>0){
            int index = schedulesPage.GetRandomDigit(0,size);
            String name = schedulesPage.GetScheduleNameByIndex(index);

            logger.log(LogStatus.INFO,"Move to schedule "+name);
            schedulesPage.MoveToScheduleByIndex(index);

            logger.log(LogStatus.INFO,"Click on Delete icon");
            schedulesPage.ClickOnDeleteIconByIndex(index);

            logger.log(LogStatus.INFO,"Click on No button in Delete dialog");
            schedulesPage.CancelRemoving();

            int sizeAct = schedulesPage.GetSchedulesCount();
            logger.log(LogStatus.INFO,"Check that schedule "+name+" isn't deleted");
            Assert.assertEquals(sizeAct,size);

            logger.log(LogStatus.INFO,"Check that Save button is disabled");
            Assert.assertFalse(schedulesPage.SaveButtonIsEnabled());

            logger.log(LogStatus.INFO,"Check that Cancel button is disabled");
            Assert.assertFalse(schedulesPage.CancelButtonIsEnabled());
        }
    }

    @Test
    public void DeleteScheduleByButtonTest() throws InterruptedException {
        logger=report.startTest("DeleteScheduleByButtonTest");

        int size = schedulesPage.GetSchedulesCount();
        if(size>0){
            int index = schedulesPage.GetRandomDigit(0,size);
            String name = schedulesPage.GetScheduleNameByIndex(index);

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

            logger.log(LogStatus.INFO,"Click on Delete button");
            schedulesPage.ClickOnDeleteButton();

            logger.log(LogStatus.INFO,"Click on Yes button in Delete dialog");
            schedulesPage.ConfirmRemoving();

            boolean ErrorDialog = schedulesPage.ErrorOnSchedulesExists();
            if(!ErrorDialog){
                int sizeAct = schedulesPage.GetSchedulesCount();
                logger.log(LogStatus.INFO,"Check that schedule "+name+" is deleted");
                Assert.assertEquals(sizeAct,size-1);

                logger.log(LogStatus.INFO,"Check that Save button is disabled");
                Assert.assertFalse(schedulesPage.SaveButtonIsEnabled());

                logger.log(LogStatus.INFO,"Check that Cancel button is disabled");
                Assert.assertFalse(schedulesPage.CancelButtonIsEnabled());
            }
        }
    }

    @Test
    public void DeleteScheduleByButtonAndClickNOTest() throws InterruptedException {
        logger=report.startTest("DeleteScheduleByButtonAndClickNOTest");

        int size = schedulesPage.GetSchedulesCount();
        if(size>0){
            int index = schedulesPage.GetRandomDigit(0,size);
            String name = schedulesPage.GetScheduleNameByIndex(index);

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

            logger.log(LogStatus.INFO,"Click on Delete button");
            schedulesPage.ClickOnDeleteButton();

            logger.log(LogStatus.INFO,"Click on No button in Delete dialog");
            schedulesPage.CancelRemoving();

            int sizeAct = schedulesPage.GetSchedulesCount();
            logger.log(LogStatus.INFO,"Check that schedule "+name+" isn't deleted");
            Assert.assertEquals(sizeAct,size);

            logger.log(LogStatus.INFO,"Check that Save button is disabled");
            Assert.assertFalse(schedulesPage.SaveButtonIsEnabled());

            logger.log(LogStatus.INFO,"Check that Cancel button is disabled");
            Assert.assertFalse(schedulesPage.CancelButtonIsEnabled());
        }
    }

    @Test
    public void ChangeNameTest() throws InterruptedException {
        logger=report.startTest("ChangeNameTest");

        int size = schedulesPage.GetSchedulesCount();
        if(size>0){
            int index = schedulesPage.GetRandomDigit(0,size);
            String name = schedulesPage.GetScheduleNameByIndex(index);

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

            String nameRand = "NewName"+schedulesPage.GetRandomDigit(0,50000);
            logger.log(LogStatus.INFO,"Input into name field "+nameRand);
            schedulesPage.InputInNameField(nameRand);

            logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
            Assert.assertTrue(schedulesPage.CancelButtonIsEnabled());

            logger.log(LogStatus.INFO,"Check that Save button is enabled");
            Assert.assertTrue(schedulesPage.SaveButtonIsEnabled());

            logger.log(LogStatus.INFO,"Press on Save button");
            schedulesPage.PressSaveButton();

            String nameAct = schedulesPage.GetScheduleNameFromProperties();
            logger.log(LogStatus.INFO,"Check that name is changed");
            Assert.assertEquals(nameAct,nameRand);

            logger.log(LogStatus.INFO,"Refresh");
            schedulesPage.Refresh();

            logger.log(LogStatus.INFO,"Click on schedule "+nameRand);
            schedulesPage.ClickOnScheduleByName(nameRand);

            String nameRefr = schedulesPage.GetScheduleNameFromProperties();
            logger.log(LogStatus.INFO,"Check that name is changed after refresh");
            Assert.assertEquals(nameRefr,nameRand);
        }
    }

    @Test
    public void ChangeDescriptionTest() throws InterruptedException {
        logger=report.startTest("ChangeDescriptionTest");

        int size = schedulesPage.GetSchedulesCount();
        if(size>0){
            int index = schedulesPage.GetRandomDigit(0,size);
            String name = schedulesPage.GetScheduleNameByIndex(index);

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

            String description = schedulesPage.GetDescription();
            String descriptionRand = "Description"+schedulesPage.GetRandomDigit(0,50000);
            logger.log(LogStatus.INFO,"Input into description field "+descriptionRand);
            schedulesPage.InputInDescriptionField(descriptionRand);

            logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
            Assert.assertTrue(schedulesPage.CancelButtonIsEnabled());

            logger.log(LogStatus.INFO,"Check that Save button is enabled");
            Assert.assertTrue(schedulesPage.SaveButtonIsEnabled());

            logger.log(LogStatus.INFO,"Press on Save button");
            schedulesPage.PressSaveButton();

            String descriptionAct = schedulesPage.GetDescription();
            logger.log(LogStatus.INFO,"Check that description is changed");
            Assert.assertEquals(descriptionAct,descriptionRand);

            logger.log(LogStatus.INFO,"Refresh");
            schedulesPage.Refresh();

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

            String descriptionRefr = schedulesPage.GetDescription();
            logger.log(LogStatus.INFO,"Check that description is changed after refresh");
            Assert.assertEquals(descriptionRefr,descriptionRand);
        }
    }

    @Test
    public void ChangeRepeatOptionTest() throws InterruptedException {
        logger=report.startTest("ChangeRepeatOptionTest");

        int size = schedulesPage.GetSchedulesCount();
        if(size>0){
            int index = schedulesPage.GetRandomDigit(0,size);
            String name = schedulesPage.GetScheduleNameByIndex(index);

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

            String repeatSelected = schedulesPage.GetRepeat();
            int optionCount = schedulesPage.GetRepeatOptionsCount();
            int optionIndex = 0;
            int random = 0;
            String option =null;

            while(true){
               random = schedulesPage.GetRandomDigit(0, optionCount);
               option = schedulesPage.GetRepeatOptionNameByIndex(random);
               if(!option.equals(repeatSelected)){
                   optionIndex=random;
                   break;
               }
            }
            logger.log(LogStatus.INFO,"Select "+option+"  repeat option");
            schedulesPage.SelectOptionByIndex(optionIndex);
            schedulesPage.WaitUntilSaveButtonWillBeEnable();

            logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
            Assert.assertTrue(schedulesPage.CancelButtonIsEnabled());

            logger.log(LogStatus.INFO,"Check that Save button is enabled");
            Assert.assertTrue(schedulesPage.SaveButtonIsEnabled());

            logger.log(LogStatus.INFO,"Press on Save button");
            schedulesPage.PressSaveButton();

            String optionAct = schedulesPage.GetRepeat();
            logger.log(LogStatus.INFO,"Check that repeat option is changed");
            Assert.assertEquals(optionAct,option);

            logger.log(LogStatus.INFO,"Refresh");
            schedulesPage.Refresh();

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

            String optionRefr = schedulesPage.GetRepeat();
            logger.log(LogStatus.INFO,"Check that repeat option is changed after refresh");
            Assert.assertEquals(optionRefr,option);
        }
    }

    @Test
    public void ChangePeriodNameTest() throws InterruptedException {
        logger=report.startTest("ChangePeriodNameTest");

        int size = schedulesPage.GetSchedulesCount();
        if(size>0){
            int index = schedulesPage.GetRandomDigit(0,size);
            String name = schedulesPage.GetScheduleNameByIndex(index);

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

            int periodCount = schedulesPage.CountPeriod();
            if(periodCount==1){
                int rand = schedulesPage.GetRandomDigit(1, 5);
                for(int i = 0; i<rand; i++){
                    logger.log(LogStatus.INFO,"Click on Plus period button");
                    schedulesPage.ClickOnAddSchedulePeriodButton();
                }
                logger.log(LogStatus.INFO,"Press on Save button");
                schedulesPage.PressSaveButton();
            }

            periodCount = schedulesPage.CountPeriod();
            int periodIndex = schedulesPage.GetRandomDigit(0, periodCount);
            String periodName = "NewPeriodName" + schedulesPage.GetRandomDigit(0, 100);

            logger.log(LogStatus.INFO,"Input "+periodName+" in period name field");
            schedulesPage.InputPeriodName(periodIndex, periodName);

            logger.log(LogStatus.INFO,"Press on Save button");
            schedulesPage.PressSaveButton();

            String periodNameAct = schedulesPage.GetPeriodName(periodIndex);
            logger.log(LogStatus.INFO,"Check that period name is changed");
            Assert.assertEquals(periodNameAct, periodName);

            logger.log(LogStatus.INFO,"Refresh");
            schedulesPage.Refresh();

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

            String periodNamerefr = schedulesPage.GetPeriodName(periodIndex);
            logger.log(LogStatus.INFO,"Check that period name is changed after refresh");
            Assert.assertEquals(periodNamerefr, periodName);
        }
    }

    @Test
    public void ChangeWeeklyDaysTest() throws InterruptedException {
        logger=report.startTest("ChangeWeeklyDaysTest");

        int size = schedulesPage.GetSchedulesCount();
        if(size>0){
            int scheduleIndex = schedulesPage.GetRandomDigit(0,size);
            String name = schedulesPage.GetScheduleNameByIndex(scheduleIndex);

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

            String repeatSelected = schedulesPage.GetRepeat();
            String optionValue = "string:Weekly";

            if(!repeatSelected.equals("Weekly")){
                logger.log(LogStatus.INFO,"Select "+optionValue+"  repeat option");
                schedulesPage.SelectRepeatByValue(optionValue);

                logger.log(LogStatus.INFO,"Press on Save button");
                schedulesPage.PressSaveButton();
            }

            int periodCount = schedulesPage.CountPeriod();
            if(periodCount==1){
                int rand = schedulesPage.GetRandomDigit(1, 5);
                for(int i = 0; i<rand; i++){
                    logger.log(LogStatus.INFO,"Click on Plus period button");
                    schedulesPage.ClickOnAddSchedulePeriodButton();
                }
                logger.log(LogStatus.INFO,"Press on Save button");
                schedulesPage.PressSaveButton();
            }

            int daysCount = schedulesPage.GetCountDaysCheckBox();
            boolean checked = false;

            ArrayList indexes = new ArrayList();
            int time = schedulesPage.GetRandomDigit(1, daysCount);
            int index=0;

            for(int i=0; i<time; i++){
                index = schedulesPage.GetRandomDigit(0,daysCount );
                checked = schedulesPage.DayIsSelected(index);
                if(!checked){
                    schedulesPage.ClickOnDayCheckBoxByIndex(index);
                    indexes.add(index);
                }
            }
            logger.log(LogStatus.INFO,"Press on Save button");
            schedulesPage.PressSaveButton();

            boolean checkedAct = false;

            logger.log(LogStatus.INFO,"Check that checkbox select is saved ");
            for(int i=0; i<indexes.size(); i++){
                int ind = (int) indexes.get(i);
                checkedAct = schedulesPage.DayIsSelected(ind);
                Assert.assertTrue(checkedAct);
            }

            logger.log(LogStatus.INFO,"Refresh");
            schedulesPage.Refresh();

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

            boolean checkedRefr = false;
            logger.log(LogStatus.INFO,"Check that checkbox select is saved after refresh ");
            for(int i=0; i<indexes.size(); i++){
                int ind = (int) indexes.get(i);
                checkedRefr = schedulesPage.DayIsSelected(ind);
                Assert.assertTrue(checkedRefr);
            }
        }
    }

    @Test
    public void ChangeStartTimeTest() throws InterruptedException {
        logger=report.startTest("ChangeStartTimeTest");

        int size = schedulesPage.GetSchedulesCount();
        if(size>0){
            int scheduleIndex = schedulesPage.GetRandomDigit(0,size);
            String name = schedulesPage.GetScheduleNameByIndex(scheduleIndex);

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

//            String repeatSelected = schedulesPage.GetRepeat();
//            String optionValue = "string:Weekly";
//
//            if(!repeatSelected.equals("Weekly")){
//                logger.log(LogStatus.INFO,"Select "+optionValue+"  repeat option");
//                schedulesPage.SelectRepeatByValue(optionValue);
//
//                logger.log(LogStatus.INFO,"Press on Save button");
//                schedulesPage.PressSaveButton();
//            }

            int periodCount = schedulesPage.CountPeriod();
            if(periodCount==1){
                int rand = schedulesPage.GetRandomDigit(1, 5);
                for(int i = 0; i<rand; i++){
                    logger.log(LogStatus.INFO,"Click on Plus period button");
                    schedulesPage.ClickOnAddSchedulePeriodButton();
                }
                logger.log(LogStatus.INFO,"Press on Save button");
                schedulesPage.PressSaveButton();
            }

            periodCount = schedulesPage.CountPeriod();
            int periodIndex = schedulesPage.GetRandomDigit(0, periodCount);

            int hours = schedulesPage.GetRandomDigit(0,24);
            int minutes = schedulesPage.GetRandomDigit(0,60);

            logger.log(LogStatus.INFO,"Input hours in start time");
            schedulesPage.PressStartTime(periodIndex);
            schedulesPage.InputHours(""+hours);

            logger.log(LogStatus.INFO,"Input minutes in start time");
            schedulesPage.InputMinutes(""+minutes);
            schedulesPage.ClickOnPeriodField(periodIndex);

            int durationHours =schedulesPage.GetDurationHours(periodIndex);
            int durationMinutes =schedulesPage.GetDurationMinutes(periodIndex);
            int endTimeHours = schedulesPage.GetEndTimeHours(periodIndex);
            int endTimeMinutes = schedulesPage.GetEndTimeMinutes(periodIndex);

            int durationHoursCalc = endTimeHours-hours;
            if(durationHoursCalc<0) durationHoursCalc = 24 -(-durationHoursCalc);

            int durationMinCalc = endTimeMinutes-minutes;
            if(durationMinCalc<0){
                durationMinCalc = 60 - (-durationMinCalc);
                durationHoursCalc = durationHoursCalc-1;
            }

            logger.log(LogStatus.INFO,"Check that duration is difference between start time and end time");
            Assert.assertEquals(durationHours,durationHoursCalc);
            Assert.assertEquals(durationMinutes,durationMinCalc);

            logger.log(LogStatus.INFO,"Press on Save button");
            schedulesPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that start time is changed");
            int startTimeHoursAct = schedulesPage.GetStartTimeHours(periodIndex);
            int startTimeMinutesAct = schedulesPage.GetStartTimeMinutes(periodIndex);
            Assert.assertEquals(startTimeHoursAct, hours);
            Assert.assertEquals(startTimeMinutesAct, minutes);

            int durationHoursAct =schedulesPage.GetDurationHours(periodIndex);
            int durationMinutesAct =schedulesPage.GetDurationMinutes(periodIndex);
            int endTimeHoursAct = schedulesPage.GetEndTimeHours(periodIndex);
            int endTimeMinutesAct = schedulesPage.GetEndTimeMinutes(periodIndex);

            logger.log(LogStatus.INFO,"Check that duration is difference between start time and end time");
            Assert.assertEquals(durationHoursAct,durationHoursCalc);
            Assert.assertEquals(durationMinutesAct,durationMinCalc);

            logger.log(LogStatus.INFO,"Refresh");
            schedulesPage.Refresh();

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

            logger.log(LogStatus.INFO,"Check that start time is changed after refresh");
            int startTimeHoursRefr = schedulesPage.GetStartTimeHours(periodIndex);
            int startTimeMinutesRefr = schedulesPage.GetStartTimeMinutes(periodIndex);
            Assert.assertEquals(startTimeHoursRefr, hours);
            Assert.assertEquals(startTimeMinutesRefr, minutes);

            int durationHoursRefr =schedulesPage.GetDurationHours(periodIndex);
            int durationMinutesRefr =schedulesPage.GetDurationMinutes(periodIndex);
            int endTimeHoursRefr = schedulesPage.GetEndTimeHours(periodIndex);
            int endTimeMinutesRefr = schedulesPage.GetEndTimeMinutes(periodIndex);

            logger.log(LogStatus.INFO,"Check that duration is difference between start time and end time after refresh");
            Assert.assertEquals(durationHoursRefr,durationHoursCalc);
            Assert.assertEquals(durationMinutesRefr,durationMinCalc);
        }
    }

    @Test
    public void ChangeStartTimeBySpinnerTest() throws InterruptedException {
        logger=report.startTest("ChangeStartTimeBySpinnerTest");

        int size = schedulesPage.GetSchedulesCount();
        if(size>0){
            int scheduleIndex = schedulesPage.GetRandomDigit(0,size);
            String name = schedulesPage.GetScheduleNameByIndex(scheduleIndex);

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

//            String repeatSelected = schedulesPage.GetRepeat();
//            String optionValue = "string:Weekly";
//
//            if(!repeatSelected.equals("Weekly")){
//                logger.log(LogStatus.INFO,"Select "+optionValue+"  repeat option");
//                schedulesPage.SelectRepeatByValue(optionValue);
//
//                logger.log(LogStatus.INFO,"Press on Save button");
//                schedulesPage.PressSaveButton();
//            }

            int periodCount = schedulesPage.CountPeriod();
            if(periodCount==1){
                int rand = schedulesPage.GetRandomDigit(1, 5);
                for(int i = 0; i<rand; i++){
                    logger.log(LogStatus.INFO,"Click on Plus period button");
                    schedulesPage.ClickOnAddSchedulePeriodButton();
                }
                logger.log(LogStatus.INFO,"Press on Save button");
                schedulesPage.PressSaveButton();
            }

            periodCount = schedulesPage.CountPeriod();
            int periodIndex = schedulesPage.GetRandomDigit(0, periodCount);

            int startTimeHours = schedulesPage.GetStartTimeHours(periodIndex);
            int startTimeMinutes = schedulesPage.GetStartTimeMinutes(periodIndex);

            int maxHours = 24;
            int minHours = 0;
            int maxMinutes = 60;
            int minMinutes = 0;

            int randHoursUPTime = schedulesPage.GetRandomDigit(1, maxHours-startTimeHours);
            int randMinutesUPTime = schedulesPage.GetRandomDigit(1, maxMinutes-startTimeMinutes);

            logger.log(LogStatus.INFO,"Click on start time field ");
            schedulesPage.PressStartTime(periodIndex);

            logger.log(LogStatus.INFO,"Click on Spinner to increase hours "+randHoursUPTime+" times");
            for(int i=0; i<randHoursUPTime; i++){
                schedulesPage.ClickOnSpinnerHoursUP();
            }

            int hours = startTimeHours+randHoursUPTime;
            int randHoursDownTime = schedulesPage.GetRandomDigit(1, hours-minHours);

            logger.log(LogStatus.INFO,"Click on Spinner to decrease hours "+randHoursDownTime+" times");
            for(int i=0; i<randHoursDownTime; i++){
                schedulesPage.ClickOnSpinnerHoursDown();
            }

            logger.log(LogStatus.INFO,"Click on Spinner to increase minutes "+randMinutesUPTime+" times");
            for(int i=0; i<randMinutesUPTime; i++){
                schedulesPage.ClickOnSpinnerMinutesUP();
            }

            int minutes = startTimeMinutes+randMinutesUPTime;
            int randMinutesDownTime = schedulesPage.GetRandomDigit(1, minutes-minMinutes);

            logger.log(LogStatus.INFO,"Click on Spinner to decrease minutes "+randHoursDownTime+" times");
            for(int i=0; i<randMinutesDownTime; i++){
                schedulesPage.ClickOnSpinnerMinutesDown();
            }

            logger.log(LogStatus.INFO,"Press on Save button");
            schedulesPage.PressSaveButton();

            int hoursStartTime = startTimeHours +randHoursUPTime-randHoursDownTime;
            int minutesStartTime = startTimeMinutes +randMinutesUPTime-randMinutesDownTime;

            int hoursAct = schedulesPage.GetStartTimeHours(periodIndex);
            int minutesAct = schedulesPage.GetStartTimeMinutes(periodIndex);

            Assert.assertEquals(hoursAct, hoursStartTime);
            Assert.assertEquals(minutesAct, minutesStartTime);

            int durationHours =schedulesPage.GetDurationHours(periodIndex);
            int durationMinutes =schedulesPage.GetDurationMinutes(periodIndex);
            int endTimeHours = schedulesPage.GetEndTimeHours(periodIndex);
            int endTimeMinutes = schedulesPage.GetEndTimeMinutes(periodIndex);

            logger.log(LogStatus.INFO,"Check that duration is difference between start time and end time");
            int durationHoursCalc = endTimeHours-hoursAct;
            int durationMinCalc = endTimeMinutes-minutesAct;
            if(durationHoursCalc<0) durationHoursCalc = 24 -durationHoursCalc;
            if(durationHoursCalc<0){
                durationMinCalc = 60 -durationMinCalc;
                durationHoursCalc = durationHoursCalc-1;
            }

            Assert.assertEquals(durationHours,durationHoursCalc);
            Assert.assertEquals(durationMinutes,durationMinCalc);

            logger.log(LogStatus.INFO,"Refresh");
            schedulesPage.Refresh();

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

            logger.log(LogStatus.INFO,"Check that start time is changed after refresh");
            int startTimeHoursRefr = schedulesPage.GetStartTimeHours(periodIndex);
            int startTimeMinutesRefr = schedulesPage.GetStartTimeMinutes(periodIndex);
            Assert.assertEquals(startTimeHoursRefr, hoursStartTime);
            Assert.assertEquals(startTimeMinutesRefr, minutesStartTime);

            int durationHoursRefr =schedulesPage.GetDurationHours(periodIndex);
            int durationMinutesRefr =schedulesPage.GetDurationMinutes(periodIndex);
            int endTimeHoursRefr = schedulesPage.GetEndTimeHours(periodIndex);
            int endTimeMinutesRefr = schedulesPage.GetEndTimeMinutes(periodIndex);

            logger.log(LogStatus.INFO,"Check that duration is difference between start time and end time after refresh");
            Assert.assertEquals(durationHoursRefr,durationHours);
            Assert.assertEquals(durationMinutesRefr,durationMinutes);
        }
    }

    @Test
    public void ChangeEndTimeTest() throws InterruptedException {
        logger=report.startTest("ChangeEndTimeTest");

        int size = schedulesPage.GetSchedulesCount();
        if(size>0){
            int scheduleIndex = schedulesPage.GetRandomDigit(0,size);
            String name = schedulesPage.GetScheduleNameByIndex(scheduleIndex);

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

//            String repeatSelected = schedulesPage.GetRepeat();
//            String optionValue = "string:Weekly";
//
//            if(!repeatSelected.equals("Weekly")){
//                logger.log(LogStatus.INFO,"Select "+optionValue+"  repeat option");
//                schedulesPage.SelectRepeatByValue(optionValue);
//
//                logger.log(LogStatus.INFO,"Press on Save button");
//                schedulesPage.PressSaveButton();
//            }

            int periodCount = schedulesPage.CountPeriod();
            if(periodCount==1){
                int rand = schedulesPage.GetRandomDigit(1, 5);
                for(int i = 0; i<rand; i++){
                    logger.log(LogStatus.INFO,"Click on Plus period button");
                    schedulesPage.ClickOnAddSchedulePeriodButton();
                }
                logger.log(LogStatus.INFO,"Press on Save button");
                schedulesPage.PressSaveButton();
            }

            periodCount = schedulesPage.CountPeriod();
            int periodIndex = schedulesPage.GetRandomDigit(0, periodCount);

            int hours = schedulesPage.GetRandomDigit(0,24);
            int minutes = schedulesPage.GetRandomDigit(0,60);

            logger.log(LogStatus.INFO,"Click on End time field");
            schedulesPage.PressEndTime(periodIndex);

            logger.log(LogStatus.INFO,"Input hours in end time");
            schedulesPage.InputHours(""+hours);

            logger.log(LogStatus.INFO,"Input minutes in start time");
            schedulesPage.InputMinutes(""+minutes);
            schedulesPage.ClickOnPeriodField(periodIndex);

            int durationHours =schedulesPage.GetDurationHours(periodIndex);
            int durationMinutes =schedulesPage.GetDurationMinutes(periodIndex);

            int endTimeHoursAct = schedulesPage.GetEndTimeHours(periodIndex);
            int endTimeMinutesAct = schedulesPage.GetEndTimeMinutes(periodIndex);
            int startTimeHoursAct = schedulesPage.GetStartTimeHours(periodIndex);
            int startTimeMinutesAct = schedulesPage.GetStartTimeMinutes(periodIndex);

            logger.log(LogStatus.INFO,"Check that hours duration is difference between start time and end time");
            int hoursDiff = endTimeHoursAct-startTimeHoursAct;
            int minDiff = endTimeMinutesAct-startTimeMinutesAct;

            if(hoursDiff<0) hoursDiff = 24+hoursDiff;
            if(minDiff<0){
                minDiff= 60+minDiff;
                hoursDiff = hoursDiff-1;
            }

            logger.log(LogStatus.INFO,"Check that minutes duration is difference between start time and end time");
            Assert.assertEquals(durationHours,hoursDiff );
            Assert.assertEquals(durationMinutes,minDiff );

            logger.log(LogStatus.INFO,"Press on Save button");
            schedulesPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that end time is changed");
            endTimeHoursAct = schedulesPage.GetEndTimeHours(periodIndex);
            endTimeMinutesAct = schedulesPage.GetEndTimeMinutes(periodIndex);
            Assert.assertEquals(endTimeHoursAct, hours);
            Assert.assertEquals(endTimeMinutesAct, minutes);

            int durationHoursAct =schedulesPage.GetDurationHours(periodIndex);
            int durationMinutesAct =schedulesPage.GetDurationMinutes(periodIndex);
            startTimeHoursAct = schedulesPage.GetStartTimeHours(periodIndex);
            startTimeMinutesAct = schedulesPage.GetStartTimeMinutes(periodIndex);

            int durationHoursCalc = endTimeHoursAct-startTimeHoursAct;
            int durationMinutCalc = endTimeMinutesAct-startTimeMinutesAct;
            if(durationHoursCalc<0) durationHoursCalc = 24+durationHoursCalc;
            if(durationMinutCalc<0){
                durationMinutCalc = 60+durationMinutCalc;
                durationHoursCalc = durationHoursCalc-1;
            }

            logger.log(LogStatus.INFO,"Check that duration is difference between start time and end time");
            Assert.assertEquals(durationHoursAct,durationHoursCalc);
            Assert.assertEquals(durationMinutesAct,durationMinutCalc);

            logger.log(LogStatus.INFO,"Refresh");
            schedulesPage.Refresh();

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

            logger.log(LogStatus.INFO,"Check that end time is changed after refresh");
            int endTimeHoursRefr = schedulesPage.GetEndTimeHours(periodIndex);
            int endTimeMinutesRefr = schedulesPage.GetEndTimeMinutes(periodIndex);
            Assert.assertEquals(endTimeHoursRefr, hours);
            Assert.assertEquals(endTimeMinutesRefr, minutes);

            int durationHoursRefr =schedulesPage.GetDurationHours(periodIndex);
            int durationMinutesRefr =schedulesPage.GetDurationMinutes(periodIndex);

            logger.log(LogStatus.INFO,"Check that duration is difference between start time and end time after refresh");
            Assert.assertEquals(durationHoursRefr,durationHoursAct);
            Assert.assertEquals(durationMinutesRefr,durationMinutesAct);
        }
    }

    @Test
    public void ChangeEndTimeBySpinnerTest() throws InterruptedException {
        logger=report.startTest("ChangeEndTimeBySpinnerTest");

        int size = schedulesPage.GetSchedulesCount();
        if(size>0){
            int scheduleIndex = schedulesPage.GetRandomDigit(0,size);
            String name = schedulesPage.GetScheduleNameByIndex(scheduleIndex);

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

//            String repeatSelected = schedulesPage.GetRepeat();
//            String optionValue = "string:Weekly";
//
//            if(!repeatSelected.equals("Weekly")){
//                logger.log(LogStatus.INFO,"Select "+optionValue+"  repeat option");
//                schedulesPage.SelectRepeatByValue(optionValue);
//
//                logger.log(LogStatus.INFO,"Press on Save button");
//                schedulesPage.PressSaveButton();
//            }

            int periodCount = schedulesPage.CountPeriod();
            if(periodCount==1){
                int rand = schedulesPage.GetRandomDigit(1, 5);
                for(int i = 0; i<rand; i++){
                    logger.log(LogStatus.INFO,"Click on Plus period button");
                    schedulesPage.ClickOnAddSchedulePeriodButton();
                }
                logger.log(LogStatus.INFO,"Press on Save button");
                schedulesPage.PressSaveButton();
            }

            periodCount = schedulesPage.CountPeriod();
            int periodIndex = schedulesPage.GetRandomDigit(0, periodCount);

            int endTimeHours = schedulesPage.GetEndTimeHours(periodIndex);
            int endTimeMinutes = schedulesPage.GetEndTimeMinutes(periodIndex);

            int maxHours = 24;
            int minHours = 0;
            int maxMinutes = 60;
            int minMinutes = 0;

            int randHoursUPTime = schedulesPage.GetRandomDigit(1, maxHours-endTimeHours);
            int randMinutesUPTime = schedulesPage.GetRandomDigit(1, maxMinutes-endTimeMinutes);

            logger.log(LogStatus.INFO,"Click on end time field ");
            schedulesPage.PressEndTime(periodIndex);

            logger.log(LogStatus.INFO,"Click on Spinner to increase hours "+randHoursUPTime+" times");
            for(int i=0; i<randHoursUPTime; i++){
                schedulesPage.ClickOnSpinnerHoursUP();
            }

            int hours = endTimeHours+randHoursUPTime;
            int randHoursDownTime = schedulesPage.GetRandomDigit(1, hours-minHours);

            logger.log(LogStatus.INFO,"Click on Spinner to decrease hours "+randHoursDownTime+" times");
            for(int i=0; i<randHoursDownTime; i++){
                schedulesPage.ClickOnSpinnerHoursDown();
            }

            logger.log(LogStatus.INFO,"Click on Spinner to increase minutes "+randMinutesUPTime+" times");
            for(int i=0; i<randMinutesUPTime; i++){
                schedulesPage.ClickOnSpinnerMinutesUP();
            }

            int minutes = endTimeMinutes+randMinutesUPTime;
            int randMinutesDownTime = schedulesPage.GetRandomDigit(1, minutes-minMinutes);

            logger.log(LogStatus.INFO,"Click on Spinner to decrease minutes "+randHoursDownTime+" times");
            for(int i=0; i<randMinutesDownTime; i++){
                schedulesPage.ClickOnSpinnerMinutesDown();
            }

            logger.log(LogStatus.INFO,"Press on Save button");
            schedulesPage.PressSaveButton();

            int hoursEndTime = endTimeHours +randHoursUPTime-randHoursDownTime;
            int minutesEndTime = endTimeMinutes +randMinutesUPTime-randMinutesDownTime;

            int hoursAct = schedulesPage.GetEndTimeHours(periodIndex);
            int minutesAct = schedulesPage.GetEndTimeMinutes(periodIndex);

            logger.log(LogStatus.INFO,"Check that end time is changed");
            Assert.assertEquals(hoursAct, hoursEndTime);
            Assert.assertEquals(minutesAct, minutesEndTime);

            int durationHours =schedulesPage.GetDurationHours(periodIndex);
            int durationMinutes =schedulesPage.GetDurationMinutes(periodIndex);
            int startTimeHours = schedulesPage.GetStartTimeHours(periodIndex);
            int startTimeMinutes = schedulesPage.GetStartTimeMinutes(periodIndex);

            logger.log(LogStatus.INFO,"Check that duration is difference between start time and end time");
            int minDiff = minutesAct-startTimeMinutes;
            int hoursDiff = hoursAct -startTimeHours;
            if(hoursDiff<0) hoursDiff = 24 -(-hoursDiff);
            if(minDiff<0){
                minDiff = 60 -(-minDiff);
                hoursDiff = hoursDiff-1;
            }

            if(durationHours<24) Assert.assertEquals(durationHours,hoursDiff);
            Assert.assertEquals(durationMinutes,minDiff);

            logger.log(LogStatus.INFO,"Refresh");
            schedulesPage.Refresh();

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

            logger.log(LogStatus.INFO,"Check that end time is changed after refresh");
            int endTimeHoursRefr = schedulesPage.GetEndTimeHours(periodIndex);
            int endTimeMinutesRefr = schedulesPage.GetEndTimeMinutes(periodIndex);
            Assert.assertEquals(endTimeHoursRefr, hoursEndTime);
            Assert.assertEquals(endTimeMinutesRefr, minutesEndTime);

            int durationHoursRefr =schedulesPage.GetDurationHours(periodIndex);
            int durationMinutesRefr =schedulesPage.GetDurationMinutes(periodIndex);

            logger.log(LogStatus.INFO,"Check that duration isn't change after refresh");
            if(durationHours<24) Assert.assertEquals(durationHoursRefr,durationHours);
            Assert.assertEquals(durationMinutesRefr,durationMinutes);
        }
    }

    @Test
    public void ChangeDurationTest() throws InterruptedException {
        logger=report.startTest("ChangeDurationTest");

        int size = schedulesPage.GetSchedulesCount();
        if(size>0){
            int scheduleIndex = schedulesPage.GetRandomDigit(0,size);
            String name = schedulesPage.GetScheduleNameByIndex(scheduleIndex);

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

//            String repeatSelected = schedulesPage.GetRepeat();
//            String optionValue = "string:Weekly";
//
//            if(!repeatSelected.equals("Weekly")){
//                logger.log(LogStatus.INFO,"Select "+optionValue+"  repeat option");
//                schedulesPage.SelectRepeatByValue(optionValue);
//
//                logger.log(LogStatus.INFO,"Press on Save button");
//                schedulesPage.PressSaveButton();
//            }

            int periodCount = schedulesPage.CountPeriod();
            if(periodCount==1){
                int rand = schedulesPage.GetRandomDigit(1, 5);
                for(int i = 0; i<rand; i++){
                    logger.log(LogStatus.INFO,"Click on Plus period button");
                    schedulesPage.ClickOnAddSchedulePeriodButton();
                }
                logger.log(LogStatus.INFO,"Press on Save button");
                schedulesPage.PressSaveButton();
            }

            periodCount = schedulesPage.CountPeriod();
            int periodIndex = schedulesPage.GetRandomDigit(0, periodCount);

            int durHoursRand = schedulesPage.GetRandomDigit(0, 1000);
            int durMinutesRand = schedulesPage.GetRandomDigit(0, 60);

            logger.log(LogStatus.INFO,"Input in duration hours field");
            schedulesPage.InputDurationHours(periodIndex, ""+durHoursRand);

            logger.log(LogStatus.INFO,"Input in duration minutes field");
            schedulesPage.InputDurationMinutes(periodIndex, ""+durMinutesRand);

            logger.log(LogStatus.INFO,"Press on Save button");
            schedulesPage.PressSaveButton();

            int durationHoursAct =schedulesPage.GetDurationHours(periodIndex);
            int durationMinutesAct =schedulesPage.GetDurationMinutes(periodIndex);

            logger.log(LogStatus.INFO,"Check that duration is change");
            Assert.assertEquals(durationHoursAct, durHoursRand);
            Assert.assertEquals(durationMinutesAct, durMinutesRand);

            int endTimeHoursAct = schedulesPage.GetEndTimeHours(periodIndex);
            int endTimeMinutesAct = schedulesPage.GetEndTimeMinutes(periodIndex);
            int startTimeHoursAct = schedulesPage.GetStartTimeHours(periodIndex);
            int startTimeMinutesAct = schedulesPage.GetStartTimeMinutes(periodIndex);

            logger.log(LogStatus.INFO,"Check that hours duration is difference between start time and end time");
            int durationHoursMod = durationHoursAct%24;
            int hoursDiff = endTimeHoursAct-startTimeHoursAct;
            int minDiff = endTimeMinutesAct-startTimeMinutesAct;
            int durHoursCalc = 0;
            int durMinutCalc = 0;

            if(hoursDiff>0) durHoursCalc = endTimeHoursAct-startTimeHoursAct;
            if(hoursDiff<0) durHoursCalc = 24 - (startTimeHoursAct - endTimeHoursAct);

            logger.log(LogStatus.INFO,"Check that minutes duration is difference between start time and end time");
            if(minDiff>0) durMinutCalc = endTimeMinutesAct-startTimeMinutesAct;
            if(minDiff<0) {
                durMinutCalc = 60 - (startTimeMinutesAct - endTimeMinutesAct);
                durHoursCalc = durHoursCalc - 1;
            }

            Assert.assertEquals(durationHoursMod,durHoursCalc );
            Assert.assertEquals(durationMinutesAct,durMinutCalc );
        }
    }

    @Test
    public void ChangeDurationBySpinnersTest() throws InterruptedException {
        logger=report.startTest("ChangeDurationBySpinnersTest");

        int size = schedulesPage.GetSchedulesCount();
        if(size>0){
            int scheduleIndex = schedulesPage.GetRandomDigit(0,size);
            String name = schedulesPage.GetScheduleNameByIndex(scheduleIndex);

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

//            String repeatSelected = schedulesPage.GetRepeat();
//            String optionValue = "string:SpecificDate";
//
//            if(!repeatSelected.equals("Never")){
//                logger.log(LogStatus.INFO,"Select "+optionValue+"  repeat option");
//                schedulesPage.SelectRepeatByValue(optionValue);
//
//                logger.log(LogStatus.INFO,"Press on Save button");
//                schedulesPage.PressSaveButton();
//            }

            int periodCount = schedulesPage.CountPeriod();
            if(periodCount==1){
                int rand = schedulesPage.GetRandomDigit(1, 5);
                for(int i = 0; i<rand; i++){
                    logger.log(LogStatus.INFO,"Click on Plus period button");
                    schedulesPage.ClickOnAddSchedulePeriodButton();
                }
                logger.log(LogStatus.INFO,"Press on Save button");
                schedulesPage.PressSaveButton();
            }

            periodCount = schedulesPage.CountPeriod();
            int periodIndex = schedulesPage.GetRandomDigit(0, periodCount);

            int durationHours =schedulesPage.GetDurationHours(periodIndex);
            int durationMinutes =schedulesPage.GetDurationMinutes(periodIndex);

            int durHoursMax = 1000;
            int durMinMax = 59;

            int durHoursDif = durHoursMax - durationHours ;
            int durMinutesDif = durMinMax - durationMinutes;

            if(durHoursDif>20) durHoursDif = 20;
            int durHoursRandUP = schedulesPage.GetRandomDigit(1, durHoursDif);
            int durMinutesRandUP = schedulesPage.GetRandomDigit(1, durMinutesDif);

            logger.log(LogStatus.INFO,"Click on spinner to increase duration hours");
            for(int i =0; i<durHoursRandUP; i++){
                schedulesPage.ClickOnDurationHoursUpSpinner(periodIndex);
            }

            int durHoursRandDown = schedulesPage.GetRandomDigit(1, durationHours+durHoursRandUP);
            if(durHoursRandDown>20) durHoursRandDown = schedulesPage.GetRandomDigit(1, 20);
            logger.log(LogStatus.INFO,"Click on spinner to deacrese duration hours");
            for(int i =0; i<durHoursRandDown; i++){
                schedulesPage.ClickOnDurationHoursDownSpinner(periodIndex);
            }

            logger.log(LogStatus.INFO,"Click on spinner to increase duration minutes");
            for(int i =0; i<durMinutesRandUP; i++){
                schedulesPage.ClickOnDurationMinutesUpSpinner(periodIndex);
            }

            int durMinutesRandDown = schedulesPage.GetRandomDigit(1, durationMinutes+durMinutesRandUP);
            logger.log(LogStatus.INFO,"Click on spinner to deacrese duration minutes");
            for(int i =0; i<durMinutesRandDown; i++){
                schedulesPage.ClickOnDurationMinutesDownSpinner(periodIndex);
            }

            logger.log(LogStatus.INFO,"Press on Save button");
            schedulesPage.PressSaveButton();

            int durationHoursExpected =durationHours+durHoursRandUP-durHoursRandDown;
            int durationMinutesExpected =durationMinutes+durMinutesRandUP-durMinutesRandDown;

            int durationHoursAct =schedulesPage.GetDurationHours(periodIndex);
            int durationMinutesAct =schedulesPage.GetDurationMinutes(periodIndex);

            logger.log(LogStatus.INFO,"Check that duration is change");
            Assert.assertEquals(durationHoursAct, durationHoursExpected);
            Assert.assertEquals(durationMinutesAct, durationMinutesExpected);

            int endTimeHoursAct = schedulesPage.GetEndTimeHours(periodIndex);
            int endTimeMinutesAct = schedulesPage.GetEndTimeMinutes(periodIndex);
            int startTimeHoursAct = schedulesPage.GetStartTimeHours(periodIndex);
            int startTimeMinutesAct = schedulesPage.GetStartTimeMinutes(periodIndex);

            logger.log(LogStatus.INFO,"Check that hours duration is difference between start time and end time");
            int durationHoursMod = durationHoursAct%24;
            int hoursDiff = endTimeHoursAct-startTimeHoursAct;
            int minDiff = endTimeMinutesAct-startTimeMinutesAct;
            int durHoursCalc = 0;
            int durMinutCalc = 0;

            if(hoursDiff>0) durHoursCalc = endTimeHoursAct-startTimeHoursAct;
            if(hoursDiff<0) durHoursCalc = 24 - (startTimeHoursAct - endTimeHoursAct);

            logger.log(LogStatus.INFO,"Check that minutes duration is difference between start time and end time");
            if(minDiff>0) durMinutCalc = endTimeMinutesAct-startTimeMinutesAct;
            if(minDiff<0) {
                durMinutCalc = 60 - (startTimeMinutesAct - endTimeMinutesAct);
                durHoursCalc = durHoursCalc - 1;
            }

            Assert.assertEquals(durationHoursMod,durHoursCalc );
            Assert.assertEquals(durationMinutesAct,durMinutCalc );
        }
    }

    @Test
    public void ChangeMonthlyModeTest() throws InterruptedException {
        logger = report.startTest("ChangeMonthlyModeTest");

        int size = schedulesPage.GetSchedulesCount();
        if (size > 0) {
            int scheduleIndex = schedulesPage.GetRandomDigit(0, size);
            String name = schedulesPage.GetScheduleNameByIndex(scheduleIndex);

            logger.log(LogStatus.INFO, "Click on schedule " + name);
            schedulesPage.ClickOnScheduleByName(name);

            String repeatSelected = schedulesPage.GetRepeat();
            String optionValue = "string:Monthly";

            if(!repeatSelected.equals("Monthly")){
                logger.log(LogStatus.INFO,"Select "+optionValue+"  repeat option");
                schedulesPage.SelectRepeatByValue(optionValue);

                logger.log(LogStatus.INFO,"Press on Save button");
                schedulesPage.PressSaveButton();
            }

            int periodCount = schedulesPage.CountPeriod();
            if (periodCount == 1) {
                int rand = schedulesPage.GetRandomDigit(1, 5);
                for (int i = 0; i < rand; i++) {
                    logger.log(LogStatus.INFO, "Click on Plus period button");
                    schedulesPage.ClickOnAddSchedulePeriodButton();
                }
                logger.log(LogStatus.INFO, "Press on Save button");
                schedulesPage.PressSaveButton();
            }

            periodCount = schedulesPage.CountPeriod();
            int periodIndex = schedulesPage.GetRandomDigit(0, periodCount);

            boolean dayRadioButton = schedulesPage.DayRadioButtonIsSelected(periodIndex);

            if(dayRadioButton){
                logger.log(LogStatus.INFO, "Click on day of month radio button ");
                schedulesPage.ClickOnDayOfMonthRadioButton(periodIndex);
            }

            if(!dayRadioButton){
                logger.log(LogStatus.INFO, "Click on day radio button ");
                schedulesPage.ClickOnDayRadioButton(periodIndex);
            }

            logger.log(LogStatus.INFO, "Press on Save button");
            schedulesPage.PressSaveButton();

            boolean dayRadioButtonAct = schedulesPage.DayRadioButtonIsSelected(periodIndex);
            boolean dayOfMonthRadioButtonAct = schedulesPage.DayOfMonthRadioButtonIsSelected(periodIndex);

            if(dayRadioButton){
                logger.log(LogStatus.INFO,"Check that day radio button isn't selected");
                Assert.assertFalse(dayRadioButtonAct);

                logger.log(LogStatus.INFO,"Check that day of Month radio button is selected");
                Assert.assertTrue(dayOfMonthRadioButtonAct);
            }
            if(!dayRadioButton){
                logger.log(LogStatus.INFO,"Check that day radio button is selected");
                Assert.assertTrue(dayRadioButtonAct);

                logger.log(LogStatus.INFO,"Check that day of Month radio button isn't selected");
                Assert.assertFalse(dayOfMonthRadioButtonAct);
            }

            logger.log(LogStatus.INFO,"Refresh");
            schedulesPage.Refresh();

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

            boolean dayRadioButtonRefr = schedulesPage.DayRadioButtonIsSelected(periodIndex);
            boolean dayOfMonthRadioButtonRefr = schedulesPage.DayOfMonthRadioButtonIsSelected(periodIndex);

            if(dayRadioButton){
                logger.log(LogStatus.INFO,"Check that day radio button isn't selected");
                Assert.assertFalse(dayRadioButtonRefr);

                logger.log(LogStatus.INFO,"Check that day of Month radio button is selected");
                Assert.assertTrue(dayOfMonthRadioButtonRefr);
            }
            if(!dayRadioButton){
                logger.log(LogStatus.INFO,"Check that day radio button is selected");
                Assert.assertTrue(dayRadioButtonRefr);

                logger.log(LogStatus.INFO,"Check that day of Month radio button isn't selected");
                Assert.assertFalse(dayOfMonthRadioButtonRefr);
            }
        }
    }

    @Test
    public void CheckThatUncheckedModeElementsAreDisabledTest() throws InterruptedException {
        logger = report.startTest("ChangeThatUncheckedModeElementsAreDisabledTest");

        int size = schedulesPage.GetSchedulesCount();
        if (size > 0) {
            int scheduleIndex = schedulesPage.GetRandomDigit(0, size);
            String name = schedulesPage.GetScheduleNameByIndex(scheduleIndex);

            logger.log(LogStatus.INFO, "Click on schedule " + name);
            schedulesPage.ClickOnScheduleByName(name);

            String repeatSelected = schedulesPage.GetRepeat();
            String optionValue = "string:Monthly";

            if(!repeatSelected.equals("Monthly")){
                logger.log(LogStatus.INFO,"Select "+optionValue+"  repeat option");
                schedulesPage.SelectRepeatByValue(optionValue);

                logger.log(LogStatus.INFO,"Press on Save button");
                schedulesPage.PressSaveButton();
            }

            int periodCount = schedulesPage.CountPeriod();
            int periodIndex = schedulesPage.GetRandomDigit(0, periodCount);

            boolean dayRadioButton = schedulesPage.DayRadioButtonIsSelected(periodIndex);

            if(dayRadioButton){
                logger.log(LogStatus.INFO, "Check that count of days field is enabled");
                Assert.assertTrue(schedulesPage.CountOfDaysFieldIsEnabled(periodIndex));

                logger.log(LogStatus.INFO, "Check that number of day drop-down list is disabled");
                Assert.assertFalse(schedulesPage.NumberOfDaysDropDownListIsEnabled(periodIndex));

                logger.log(LogStatus.INFO, "Check that day of week drop-down list is disabled");
                Assert.assertFalse(schedulesPage.DaysWeekSelectIsEnabled(periodIndex));

                logger.log(LogStatus.INFO, "Click on day of month radio button ");
                schedulesPage.ClickOnDayOfMonthRadioButton(periodIndex);
            }

            if(!dayRadioButton){
                logger.log(LogStatus.INFO, "Check that count of days field is disabled");
                Assert.assertFalse(schedulesPage.CountOfDaysFieldIsEnabled(periodIndex));

                logger.log(LogStatus.INFO, "Check that number of day drop-down list is enabled");
                Assert.assertTrue(schedulesPage.NumberOfDaysDropDownListIsEnabled(periodIndex));

                logger.log(LogStatus.INFO, "Check that day of week drop-down list is enabled");
                Assert.assertTrue(schedulesPage.DaysWeekSelectIsEnabled(periodIndex));

                logger.log(LogStatus.INFO, "Click on day radio button ");
                schedulesPage.ClickOnDayRadioButton(periodIndex);
            }

            logger.log(LogStatus.INFO, "Press on Save button");
            schedulesPage.PressSaveButton();

            if(dayRadioButton){
                logger.log(LogStatus.INFO, "Check that count of days field is disabled");
                Assert.assertFalse(schedulesPage.CountOfDaysFieldIsEnabled(periodIndex));

                logger.log(LogStatus.INFO, "Check that number of day drop-down list is enabled");
                Assert.assertTrue(schedulesPage.NumberOfDaysDropDownListIsEnabled(periodIndex));

                logger.log(LogStatus.INFO, "Check that day of week drop-down list is enabled");
                Assert.assertTrue(schedulesPage.DaysWeekSelectIsEnabled(periodIndex));
            }

            if(!dayRadioButton){
                logger.log(LogStatus.INFO, "Check that count of days field is enabled");
                Assert.assertTrue(schedulesPage.CountOfDaysFieldIsEnabled(periodIndex));

                logger.log(LogStatus.INFO, "Check that number of day drop-down list is disabled");
                Assert.assertFalse(schedulesPage.NumberOfDaysDropDownListIsEnabled(periodIndex));

                logger.log(LogStatus.INFO, "Check that day of week drop-down list is disabled");
                Assert.assertFalse(schedulesPage.DaysWeekSelectIsEnabled(periodIndex));
            }
        }
    }

    @Test
    public void ChangeMonthlyCountOfDayTest() throws InterruptedException {
        logger = report.startTest("ChangeMonthlyCountOfDayTest");

        int size = schedulesPage.GetSchedulesCount();
        if (size > 0) {
            int scheduleIndex = schedulesPage.GetRandomDigit(0, size);
            String name = schedulesPage.GetScheduleNameByIndex(scheduleIndex);

            logger.log(LogStatus.INFO, "Click on schedule " + name);
            schedulesPage.ClickOnScheduleByName(name);

            String repeatSelected = schedulesPage.GetRepeat();
            String optionValue = "string:Monthly";

            if(!repeatSelected.equals("Monthly")){
                logger.log(LogStatus.INFO,"Select "+optionValue+"  repeat option");
                schedulesPage.SelectRepeatByValue(optionValue);

                logger.log(LogStatus.INFO,"Press on Save button");
                schedulesPage.PressSaveButton();
            }

            int periodCount = schedulesPage.CountPeriod();
            if (periodCount == 1) {
                int rand = schedulesPage.GetRandomDigit(1, 5);
                for (int i = 0; i < rand; i++) {
                    logger.log(LogStatus.INFO, "Click on Plus period button");
                    schedulesPage.ClickOnAddSchedulePeriodButton();
                }
                logger.log(LogStatus.INFO, "Press on Save button");
                schedulesPage.PressSaveButton();
            }

            periodCount = schedulesPage.CountPeriod();
            int periodIndex = schedulesPage.GetRandomDigit(0, periodCount);

            boolean dayRadioButton = schedulesPage.DayRadioButtonIsSelected(periodIndex);
            if(!dayRadioButton){
                logger.log(LogStatus.INFO, "Click on day of month radio button ");
                schedulesPage.ClickOnDayRadioButton(periodIndex);
            }

            int daysRand = schedulesPage.GetRandomDigit(1, 31);
            logger.log(LogStatus.INFO, "Input in days field "+daysRand );
            schedulesPage.InputInDaysField(periodIndex, ""+daysRand);

            logger.log(LogStatus.INFO, "Press on Save button");
            schedulesPage.PressSaveButton();

            int daysAct = Integer.parseInt(schedulesPage.GetCountOfDays(periodIndex));
            logger.log(LogStatus.INFO,"Check that number of days is changed");
            Assert.assertEquals(daysAct, daysRand);

            logger.log(LogStatus.INFO,"Refresh");
            schedulesPage.Refresh();

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

            int daysRefr = Integer.parseInt(schedulesPage.GetCountOfDays(periodIndex));
            logger.log(LogStatus.INFO,"Check that number of days is changed after refresh");
            Assert.assertEquals(daysRefr, daysRand);
        }
    }

    @Test
    public void ChangeMonthlyCountOfDayBySpinnersTest() throws InterruptedException {
        logger = report.startTest("ChangeMonthlyCountOfDayBySpinnersTest");

        int size = schedulesPage.GetSchedulesCount();
        if (size > 0) {
            int scheduleIndex = schedulesPage.GetRandomDigit(0, size);
            String name = schedulesPage.GetScheduleNameByIndex(scheduleIndex);

            logger.log(LogStatus.INFO, "Click on schedule " + name);
            schedulesPage.ClickOnScheduleByName(name);

            String repeatSelected = schedulesPage.GetRepeat();
            String optionValue = "string:Monthly";

            if(!repeatSelected.equals("Monthly")){
                logger.log(LogStatus.INFO,"Select "+optionValue+"  repeat option");
                schedulesPage.SelectRepeatByValue(optionValue);

                logger.log(LogStatus.INFO,"Press on Save button");
                schedulesPage.PressSaveButton();
            }

            int periodCount = schedulesPage.CountPeriod();
            if (periodCount == 1) {
                int rand = schedulesPage.GetRandomDigit(1, 5);
                for (int i = 0; i < rand; i++) {
                    logger.log(LogStatus.INFO, "Click on Plus period button");
                    schedulesPage.ClickOnAddSchedulePeriodButton();
                }
                logger.log(LogStatus.INFO, "Press on Save button");
                schedulesPage.PressSaveButton();
            }

            periodCount = schedulesPage.CountPeriod();
            int periodIndex = schedulesPage.GetRandomDigit(0, periodCount);

            boolean dayRadioButton = schedulesPage.DayRadioButtonIsSelected(periodIndex);
            if(!dayRadioButton){
                logger.log(LogStatus.INFO, "Click on day of month radio button ");
                schedulesPage.ClickOnDayRadioButton(periodIndex);
            }

            int days = Integer.parseInt(schedulesPage.GetCountOfDays(periodIndex));
            int max = 31;
            int timesUP = schedulesPage.GetRandomDigit(0, max-days);
            int timeDown = schedulesPage.GetRandomDigit(0, days+timesUP-1);

            logger.log(LogStatus.INFO, "Click on spinner to increase days count "+timesUP+" times" );
            for(int i=0; i<timesUP;i++){
                schedulesPage.ClickOnDaysUpSpinner(periodIndex);
            }

            logger.log(LogStatus.INFO, "Click on spinner to decrease days count "+timeDown+" times" );
            for(int i=0; i<timeDown;i++){
                schedulesPage.ClickOnDaysDownSpinner(periodIndex);
            }

            logger.log(LogStatus.INFO, "Press on Save button");
            schedulesPage.PressSaveButton();

            int daysAct = Integer.parseInt(schedulesPage.GetCountOfDays(periodIndex));
            int daysExpected = days+timesUP-timeDown;
            logger.log(LogStatus.INFO,"Check that number of days is changed");
            Assert.assertEquals(daysAct, daysExpected);

            logger.log(LogStatus.INFO,"Refresh");
            schedulesPage.Refresh();

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

            int daysRefr = Integer.parseInt(schedulesPage.GetCountOfDays(periodIndex));
            logger.log(LogStatus.INFO,"Check that number of days is changed");
            Assert.assertEquals(daysRefr, daysExpected);
        }
    }

    @Test
    public void ChangeMonthlyNumberOfDayTest() throws InterruptedException {
        logger = report.startTest("ChangeMonthlyNumberOfDayTest");

        int size = schedulesPage.GetSchedulesCount();
        if (size > 0) {
            int scheduleIndex = schedulesPage.GetRandomDigit(0, size);
            String name = schedulesPage.GetScheduleNameByIndex(scheduleIndex);

            logger.log(LogStatus.INFO, "Click on schedule " + name);
            schedulesPage.ClickOnScheduleByName(name);

            String repeatSelected = schedulesPage.GetRepeat();
            String optionValue = "string:Monthly";

            if(!repeatSelected.equals("Monthly")){
                logger.log(LogStatus.INFO,"Select "+optionValue+"  repeat option");
                schedulesPage.SelectRepeatByValue(optionValue);

                logger.log(LogStatus.INFO,"Press on Save button");
                schedulesPage.PressSaveButton();
            }

            int periodCount = schedulesPage.CountPeriod();
            if (periodCount == 1) {
                int rand = schedulesPage.GetRandomDigit(1, 5);
                for (int i = 0; i < rand; i++) {
                    logger.log(LogStatus.INFO, "Click on Plus period button");
                    schedulesPage.ClickOnAddSchedulePeriodButton();
                }
                logger.log(LogStatus.INFO, "Press on Save button");
                schedulesPage.PressSaveButton();
            }

            periodCount = schedulesPage.CountPeriod();
            int periodIndex = schedulesPage.GetRandomDigit(0, periodCount);

            boolean dayOfMonthkRadioButton = schedulesPage.DayOfMonthRadioButtonIsSelected(periodIndex);
            if(!dayOfMonthkRadioButton){
                logger.log(LogStatus.INFO, "Click on day of month radio button");
                schedulesPage.ClickOnDayOfMonthRadioButton(periodIndex);
            }

            int optionsSize = schedulesPage.GetCountOfOptionsInNumberDaysSelect(periodIndex);
            int optionIndex = schedulesPage.GetRandomDigit(0, optionsSize);
            String optionName = schedulesPage.GetTextNumDayByIndex(periodIndex, optionIndex);

            logger.log(LogStatus.INFO, "Select number of day "+optionName );
            schedulesPage.SetOptionsByIndexNumberDays(periodIndex, optionIndex);

            logger.log(LogStatus.INFO, "Press on Save button");
            schedulesPage.PressSaveButton();

            String optionAct = schedulesPage.GetTextSelectedOptionNumberDays(periodIndex);
            logger.log(LogStatus.INFO,"Check that number of day is changed");
            Assert.assertEquals(optionAct, optionName);

            logger.log(LogStatus.INFO,"Refresh");
            schedulesPage.Refresh();

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

            String optionRefr = schedulesPage.GetTextSelectedOptionNumberDays(periodIndex);
            logger.log(LogStatus.INFO,"Check that number of day is changed after refresh");
            Assert.assertEquals(optionRefr, optionName);
        }
    }

    @Test
    public void ChangeMonthlyDayOfWeekTest() throws InterruptedException {
        logger = report.startTest("ChangeMonthlyDayOfWeekTest");

        int size = schedulesPage.GetSchedulesCount();
        if (size > 0) {
            int scheduleIndex = schedulesPage.GetRandomDigit(0, size);
            String name = schedulesPage.GetScheduleNameByIndex(scheduleIndex);

            logger.log(LogStatus.INFO, "Click on schedule " + name);
            schedulesPage.ClickOnScheduleByName(name);

            String repeatSelected = schedulesPage.GetRepeat();
            String optionValue = "string:Monthly";

            if(!repeatSelected.equals("Monthly")){
                logger.log(LogStatus.INFO,"Select "+optionValue+"  repeat option");
                schedulesPage.SelectRepeatByValue(optionValue);

                logger.log(LogStatus.INFO,"Press on Save button");
                schedulesPage.PressSaveButton();
            }

            int periodCount = schedulesPage.CountPeriod();
            if (periodCount == 1) {
                int rand = schedulesPage.GetRandomDigit(1, 5);
                for (int i = 0; i < rand; i++) {
                    logger.log(LogStatus.INFO, "Click on Plus period button");
                    schedulesPage.ClickOnAddSchedulePeriodButton();
                }
                logger.log(LogStatus.INFO, "Press on Save button");
                schedulesPage.PressSaveButton();
            }

            periodCount = schedulesPage.CountPeriod();
            int periodIndex = schedulesPage.GetRandomDigit(0, periodCount);

            boolean dayOfMonthkRadioButton = schedulesPage.DayOfMonthRadioButtonIsSelected(periodIndex);
            if(!dayOfMonthkRadioButton){
                logger.log(LogStatus.INFO, "Click on day of month radio button");
                schedulesPage.ClickOnDayOfMonthRadioButton(periodIndex);
            }

            int optionsSize = schedulesPage.GetCountOfOptionsInDaysWeekSelect(periodIndex);
            int optionIndex = schedulesPage.GetRandomDigit(0, optionsSize);
            String optionName = schedulesPage.GetTextDayWeekByIndex(periodIndex, optionIndex);

            logger.log(LogStatus.INFO, "Select day of week "+optionName );
            schedulesPage.SetOptionsByIndexDaysWeek(periodIndex, optionIndex);

            logger.log(LogStatus.INFO, "Press on Save button");
            schedulesPage.PressSaveButton();

            String optionAct = schedulesPage.GetTextSelectedOptionDayWeek(periodIndex);
            logger.log(LogStatus.INFO,"Check that day od week is changed");
            Assert.assertEquals(optionAct, optionName);

            logger.log(LogStatus.INFO,"Refresh");
            schedulesPage.Refresh();

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

            String optionRefr = schedulesPage.GetTextSelectedOptionDayWeek(periodIndex);
            logger.log(LogStatus.INFO,"Check that day od week is changed after refresh");
            Assert.assertEquals(optionRefr, optionName);
        }
    }

    @Test
    public void ChangeYearlyModeTest() throws InterruptedException {
        logger = report.startTest("ChangeYearlyModeTest");

        int size = schedulesPage.GetSchedulesCount();
        if (size > 0) {
            int scheduleIndex = schedulesPage.GetRandomDigit(0, size);
            String name = schedulesPage.GetScheduleNameByIndex(scheduleIndex);

            logger.log(LogStatus.INFO, "Click on schedule " + name);
            schedulesPage.ClickOnScheduleByName(name);

            String repeatSelected = schedulesPage.GetRepeat();
            String optionValue = "string:Yearly";

            if(!repeatSelected.equals("Yearly")){
                logger.log(LogStatus.INFO,"Select "+optionValue+"  repeat option");
                schedulesPage.SelectRepeatByValue(optionValue);

                logger.log(LogStatus.INFO,"Press on Save button");
                schedulesPage.PressSaveButton();
            }

            int periodCount = schedulesPage.CountPeriod();
            if (periodCount == 1) {
                int rand = schedulesPage.GetRandomDigit(1, 5);
                for (int i = 0; i < rand; i++) {
                    logger.log(LogStatus.INFO, "Click on Plus period button");
                    schedulesPage.ClickOnAddSchedulePeriodButton();
                }
                logger.log(LogStatus.INFO, "Press on Save button");
                schedulesPage.PressSaveButton();
            }

            periodCount = schedulesPage.CountPeriod();
            int periodIndex = schedulesPage.GetRandomDigit(0, periodCount);

            boolean everyRadioButton = schedulesPage.EveryRadioButtonIsSelected(periodIndex);

            if(!everyRadioButton){
                logger.log(LogStatus.INFO, "Click on every radio button ");
                schedulesPage.ClickOnEveryRadioButton(periodIndex);
            }

            if(everyRadioButton){
                logger.log(LogStatus.INFO, "Click on yearly radio button ");
                schedulesPage.ClickOnYearlyRadioButton(periodIndex);
            }

            logger.log(LogStatus.INFO, "Press on Save button");
            schedulesPage.PressSaveButton();

            boolean everyRadioButtonAct = schedulesPage.EveryRadioButtonIsSelected(periodIndex);
            boolean yearlyRadioButtonAct = schedulesPage.YearlyRadioButtonIsSelected(periodIndex);

            if(everyRadioButton){
                logger.log(LogStatus.INFO,"Check that every radio button isn't selected");
                Assert.assertFalse(everyRadioButtonAct);

                logger.log(LogStatus.INFO,"Check that yearly radio button is selected");
                Assert.assertTrue(yearlyRadioButtonAct);
            }
            if(!everyRadioButton){
                logger.log(LogStatus.INFO,"Check that every radio button is selected");
                Assert.assertTrue(everyRadioButtonAct);

                logger.log(LogStatus.INFO,"Check that yearly radio button isn't selected");
                Assert.assertFalse(yearlyRadioButtonAct);
            }

            logger.log(LogStatus.INFO,"Refresh");
            schedulesPage.Refresh();

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

            boolean everyRadioButtonRefr = schedulesPage.EveryRadioButtonIsSelected(periodIndex);
            boolean yearlyRadioButtonRefr = schedulesPage.YearlyRadioButtonIsSelected(periodIndex);

            if(everyRadioButton){
                logger.log(LogStatus.INFO,"Check that every radio button isn't selected");
                Assert.assertFalse(everyRadioButtonRefr);

                logger.log(LogStatus.INFO,"Check that yearly radio button is selected");
                Assert.assertTrue(yearlyRadioButtonRefr);
            }
            if(!everyRadioButton){
                logger.log(LogStatus.INFO,"Check that every radio button is selected");
                Assert.assertTrue(everyRadioButtonRefr);

                logger.log(LogStatus.INFO,"Check that yearly radio button isn't selected");
                Assert.assertFalse(yearlyRadioButtonRefr);
            }
        }
    }

    @Test
    public void ChangeThatUncheckedModeElementsAreDisabledInYearlyModeTest() throws InterruptedException {
        logger = report.startTest("ChangeThatUncheckedModeElementsAreDisabledInYearlyModeTest");

        int size = schedulesPage.GetSchedulesCount();
        if (size > 0) {
            int scheduleIndex = schedulesPage.GetRandomDigit(0, size);
            String name = schedulesPage.GetScheduleNameByIndex(scheduleIndex);

            logger.log(LogStatus.INFO, "Click on schedule " + name);
            schedulesPage.ClickOnScheduleByName(name);

            String repeatSelected = schedulesPage.GetRepeat();
            String optionValue = "string:Yearly";

            if(!repeatSelected.equals("Yearly")){
                logger.log(LogStatus.INFO,"Select "+optionValue+"  repeat option");
                schedulesPage.SelectRepeatByValue(optionValue);

                logger.log(LogStatus.INFO,"Press on Save button");
                schedulesPage.PressSaveButton();
            }

            int periodCount = schedulesPage.CountPeriod();
            int periodIndex = schedulesPage.GetRandomDigit(0, periodCount);

            boolean everyRadioButton = schedulesPage.EveryRadioButtonIsSelected(periodIndex);

            if(everyRadioButton){
                logger.log(LogStatus.INFO,"Check that month in Every mode is enabled");
                Assert.assertTrue(schedulesPage.MonthSelectIsEnabled(periodIndex));

                logger.log(LogStatus.INFO,"Check that days field in Every mode is enabled");
                Assert.assertTrue(schedulesPage.CountDaysInMonthFieldIsEnabled(periodIndex));

                logger.log(LogStatus.INFO,"Check that number of day drop down list in Yearly mode is disabled");
                Assert.assertFalse(schedulesPage.NumberOfDayInMonthDropDownIsEnabled(periodIndex));

                logger.log(LogStatus.INFO,"Check that day of week drop down list in Yearly mode is disabled");
                Assert.assertFalse(schedulesPage.DayOfWeekDropDownInMonthIsEnabled(periodIndex));

                logger.log(LogStatus.INFO,"Check that month drop down list in Yearly mode is disabled");
                Assert.assertFalse(schedulesPage.MonthOfYearDropDownIsEnabled(periodIndex));

                logger.log(LogStatus.INFO, "Click on yearly radio button ");
                schedulesPage.ClickOnYearlyRadioButton(periodIndex);
            }

            if(!everyRadioButton){
                logger.log(LogStatus.INFO,"Check that month in Every mode is disabled");
                Assert.assertFalse(schedulesPage.MonthSelectIsEnabled(periodIndex));

                logger.log(LogStatus.INFO,"Check that days field in Every mode is disabled");
                Assert.assertFalse(schedulesPage.CountDaysInMonthFieldIsEnabled(periodIndex));

                logger.log(LogStatus.INFO,"Check that number of day drop down list in Yearly mode is enabled");
                Assert.assertTrue(schedulesPage.NumberOfDayInMonthDropDownIsEnabled(periodIndex));

                logger.log(LogStatus.INFO,"Check that day of week drop down list in Yearly mode is enabled");
                Assert.assertTrue(schedulesPage.DayOfWeekDropDownInMonthIsEnabled(periodIndex));

                logger.log(LogStatus.INFO,"Check that month drop down list in Yearly mode is enabled");
                Assert.assertTrue(schedulesPage.MonthOfYearDropDownIsEnabled(periodIndex));

                logger.log(LogStatus.INFO, "Click on Every radio button ");
                schedulesPage.ClickOnEveryRadioButton(periodIndex);
            }

            logger.log(LogStatus.INFO, "Press on Save button");
            schedulesPage.PressSaveButton();

            if(!everyRadioButton){
                logger.log(LogStatus.INFO,"Check that month in Every mode is enabled");
                Assert.assertTrue(schedulesPage.MonthSelectIsEnabled(periodIndex));

                logger.log(LogStatus.INFO,"Check that days field in Every mode is enabled");
                Assert.assertTrue(schedulesPage.CountDaysInMonthFieldIsEnabled(periodIndex));

                logger.log(LogStatus.INFO,"Check that number of day drop down list in Yearly mode is disabled");
                Assert.assertFalse(schedulesPage.NumberOfDayInMonthDropDownIsEnabled(periodIndex));

                logger.log(LogStatus.INFO,"Check that day of week drop down list in Yearly mode is disabled");
                Assert.assertFalse(schedulesPage.DayOfWeekDropDownInMonthIsEnabled(periodIndex));

                logger.log(LogStatus.INFO,"Check that month drop down list in Yearly mode is disabled");
                Assert.assertFalse(schedulesPage.MonthOfYearDropDownIsEnabled(periodIndex));
            }

            if(everyRadioButton){
                logger.log(LogStatus.INFO,"Check that month in Every mode is disabled");
                Assert.assertFalse(schedulesPage.MonthSelectIsEnabled(periodIndex));

                logger.log(LogStatus.INFO,"Check that days field in Every mode is disabled");
                Assert.assertFalse(schedulesPage.CountDaysInMonthFieldIsEnabled(periodIndex));

                logger.log(LogStatus.INFO,"Check that number of day drop down list in Yearly mode is enabled");
                Assert.assertTrue(schedulesPage.NumberOfDayInMonthDropDownIsEnabled(periodIndex));

                logger.log(LogStatus.INFO,"Check that day of week drop down list in Yearly mode is enabled");
                Assert.assertTrue(schedulesPage.DayOfWeekDropDownInMonthIsEnabled(periodIndex));

                logger.log(LogStatus.INFO,"Check that month drop down list in Yearly mode is enabled");
                Assert.assertTrue(schedulesPage.MonthOfYearDropDownIsEnabled(periodIndex));
            }
        }
    }

    @Test//Bug
    public void ChangeEveryMonthInYearlyModeTest() throws InterruptedException {
        logger = report.startTest("ChangeEveryMonthInYearlyModeTest");

        int size = schedulesPage.GetSchedulesCount();
        if (size > 0) {
            int scheduleIndex = schedulesPage.GetRandomDigit(0, size);
            String name = schedulesPage.GetScheduleNameByIndex(scheduleIndex);

            logger.log(LogStatus.INFO, "Click on schedule " + name);
            schedulesPage.ClickOnScheduleByName(name);

            String repeatSelected = schedulesPage.GetRepeat();
            String optionValue = "string:Yearly";

            if(!repeatSelected.equals("Yearly")){
                logger.log(LogStatus.INFO,"Select "+optionValue+"  repeat option");
                schedulesPage.SelectRepeatByValue(optionValue);

                logger.log(LogStatus.INFO,"Press on Save button");
                schedulesPage.PressSaveButton();
            }

            int periodCount = schedulesPage.CountPeriod();
            if (periodCount == 1) {
                int rand = schedulesPage.GetRandomDigit(1, 5);
                for (int i = 0; i < rand; i++) {
                    logger.log(LogStatus.INFO, "Click on Plus period button");
                    schedulesPage.ClickOnAddSchedulePeriodButton();
                }
                logger.log(LogStatus.INFO, "Press on Save button");
                schedulesPage.PressSaveButton();
            }

            periodCount = schedulesPage.CountPeriod();
            int periodIndex = schedulesPage.GetRandomDigit(0, periodCount);

            boolean everyRadioButton = schedulesPage.EveryRadioButtonIsSelected(periodIndex);
            if(!everyRadioButton){
                logger.log(LogStatus.INFO, "Click on every radio button ");
                schedulesPage.ClickOnEveryRadioButton(periodIndex);

                logger.log(LogStatus.INFO, "Press on Save button");
                schedulesPage.PressSaveButton();
            }

            String month = schedulesPage.GetMonth(periodIndex);
            int optionSize = schedulesPage.GetMonthOPtionSize(periodIndex);
            int optionIndex= 0;
            String optionName = null;

            while(true){
                optionIndex = schedulesPage.GetRandomDigit(0, optionSize);
                optionName = schedulesPage.GetMonthOptionByIndex(periodIndex, optionIndex);
                if(!optionName.equals(month)) break;
            }

            logger.log(LogStatus.INFO, "Select "+optionName+" month");
            schedulesPage.SelectMonthOptionByIndex(periodIndex, optionIndex);

            logger.log(LogStatus.INFO, "Press on Save button");
            schedulesPage.PressSaveButton();

            String optionAct = schedulesPage.GetMonth(periodIndex);
            logger.log(LogStatus.INFO, "Check that month option is changed");
            Assert.assertEquals(optionAct, optionName);

            logger.log(LogStatus.INFO,"Refresh");
            schedulesPage.Refresh();

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

            String optionRefr = schedulesPage.GetMonth(periodIndex);
            logger.log(LogStatus.INFO, "Check that month option is changed after refresh");
            Assert.assertEquals(optionRefr, optionName);
        }
    }

    @Test
    public void ChangeDayOfMonthInYearlyModeTest() throws InterruptedException {
        logger = report.startTest("ChangeDayOfMonthInYearlyModeTest");

        int size = schedulesPage.GetSchedulesCount();
        if (size > 0) {
            int scheduleIndex = schedulesPage.GetRandomDigit(0, size);
            String name = schedulesPage.GetScheduleNameByIndex(scheduleIndex);

            logger.log(LogStatus.INFO, "Click on schedule " + name);
            schedulesPage.ClickOnScheduleByName(name);

            String repeatSelected = schedulesPage.GetRepeat();
            String optionValue = "string:Yearly";

            if(!repeatSelected.equals("Yearly")){
                logger.log(LogStatus.INFO,"Select "+optionValue+"  repeat option");
                schedulesPage.SelectRepeatByValue(optionValue);

                logger.log(LogStatus.INFO,"Press on Save button");
                schedulesPage.PressSaveButton();
            }

            int periodCount = schedulesPage.CountPeriod();
            if (periodCount == 1) {
                int rand = schedulesPage.GetRandomDigit(1, 5);
                for (int i = 0; i < rand; i++) {
                    logger.log(LogStatus.INFO, "Click on Plus period button");
                    schedulesPage.ClickOnAddSchedulePeriodButton();
                }
                logger.log(LogStatus.INFO, "Press on Save button");
                schedulesPage.PressSaveButton();
            }

            periodCount = schedulesPage.CountPeriod();
            int periodIndex = schedulesPage.GetRandomDigit(0, periodCount);

            boolean yearlyRadioButton = schedulesPage.YearlyRadioButtonIsSelected(periodIndex);
            if(!yearlyRadioButton){
                logger.log(LogStatus.INFO, "Click on yearly radio button ");
                schedulesPage.ClickOnYearlyRadioButton(periodIndex);
            }

            String dayNumber = schedulesPage.GetNumberOfDaysYearly(periodIndex);
            int optionSize = schedulesPage.GetNumberOfDayInMonthOPtionSize(periodIndex);
            int optionIndex= 0;
            String optionName = null;

            while(true){
                optionIndex = schedulesPage.GetRandomDigit(0, optionSize);
                optionName = schedulesPage.GetNumberOfDayOptionByIndex(periodIndex, optionIndex);
                if(!optionName.equals(dayNumber)) break;
            }

            logger.log(LogStatus.INFO, "Select "+optionName+" number of day");
            schedulesPage.SelectNumberOfDayOptionByIndex(periodIndex, optionIndex);

            logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
            Assert.assertTrue(schedulesPage.CancelButtonIsEnabled());

            logger.log(LogStatus.INFO,"Check that Save button is enabled");
            Assert.assertTrue(schedulesPage.SaveButtonIsEnabled());

            logger.log(LogStatus.INFO, "Press on Save button");
            schedulesPage.PressSaveButton();

            String optionAct = schedulesPage.GetNumberOfDaysYearly(periodIndex);
            logger.log(LogStatus.INFO, "Check that number of day option is changed");
            Assert.assertEquals(optionAct, optionName);

            logger.log(LogStatus.INFO,"Refresh");
            schedulesPage.Refresh();

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

            String optionRefr = schedulesPage.GetNumberOfDaysYearly(periodIndex);
            logger.log(LogStatus.INFO, "Check that number of day option is changed after refresh");
            Assert.assertEquals(optionRefr, optionName);
        }
    }

    @Test
    public void ChangeDayOfWeekInMonthInYearlyModeTest() throws InterruptedException {
        logger = report.startTest("ChangeDayOfWeekInMonthInYearlyModeTest");

        int size = schedulesPage.GetSchedulesCount();
        if (size > 0) {
            int scheduleIndex = schedulesPage.GetRandomDigit(0, size);
            String name = schedulesPage.GetScheduleNameByIndex(scheduleIndex);

            logger.log(LogStatus.INFO, "Click on schedule " + name);
            schedulesPage.ClickOnScheduleByName(name);

            String repeatSelected = schedulesPage.GetRepeat();
            String optionValue = "string:Yearly";

            if(!repeatSelected.equals("Yearly")){
                logger.log(LogStatus.INFO,"Select "+optionValue+"  repeat option");
                schedulesPage.SelectRepeatByValue(optionValue);

                logger.log(LogStatus.INFO,"Press on Save button");
                schedulesPage.PressSaveButton();
            }

            int periodCount = schedulesPage.CountPeriod();
            if (periodCount == 1) {
                int rand = schedulesPage.GetRandomDigit(1, 5);
                for (int i = 0; i < rand; i++) {
                    logger.log(LogStatus.INFO, "Click on Plus period button");
                    schedulesPage.ClickOnAddSchedulePeriodButton();
                }
                logger.log(LogStatus.INFO, "Press on Save button");
                schedulesPage.PressSaveButton();
            }

            periodCount = schedulesPage.CountPeriod();
            int periodIndex = schedulesPage.GetRandomDigit(0, periodCount);

            boolean yearlyRadioButton = schedulesPage.YearlyRadioButtonIsSelected(periodIndex);

            if(!yearlyRadioButton){
                logger.log(LogStatus.INFO, "Click on yearly radio button ");
                schedulesPage.ClickOnYearlyRadioButton(periodIndex);
            }

            String dayOfWeek = schedulesPage.GetDayOfWeekYearly(periodIndex);
            int optionSize = schedulesPage.GetDayOfWeekInMonthOPtionSize(periodIndex);
            int optionIndex= 0;
            String optionName = null;

            while(true){
                optionIndex = schedulesPage.GetRandomDigit(0, optionSize);
                optionName = schedulesPage.GetDayOfWeekOptionByIndex(periodIndex, optionIndex);
                if(!optionName.equals(dayOfWeek)) break;
            }

            logger.log(LogStatus.INFO, "Select "+optionName+" number of day");
            schedulesPage.SelectDayOfWeekOptionByIndex(periodIndex, optionIndex);

            logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
            Assert.assertTrue(schedulesPage.CancelButtonIsEnabled());

            logger.log(LogStatus.INFO,"Check that Save button is enabled");
            Assert.assertTrue(schedulesPage.SaveButtonIsEnabled());

            logger.log(LogStatus.INFO, "Press on Save button");
            schedulesPage.PressSaveButton();

            String optionAct = schedulesPage.GetDayOfWeekYearly(periodIndex);
            logger.log(LogStatus.INFO, "Check that day of week option is changed");
            Assert.assertEquals(optionAct, optionName);

            logger.log(LogStatus.INFO,"Refresh");
            schedulesPage.Refresh();

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

            String optionRefr = schedulesPage.GetDayOfWeekYearly(periodIndex);
            logger.log(LogStatus.INFO, "Check that day of week option is changed after refresh");
            Assert.assertEquals(optionRefr, optionName);
        }
    }

    @Test
    public void ChangeMonthInYearlyModeTest() throws InterruptedException {
        logger = report.startTest("ChangeMonthInYearlyModeTest");

        int size = schedulesPage.GetSchedulesCount();
        if (size > 0) {
            int scheduleIndex = schedulesPage.GetRandomDigit(0, size);
            String name = schedulesPage.GetScheduleNameByIndex(scheduleIndex);

            logger.log(LogStatus.INFO, "Click on schedule " + name);
            schedulesPage.ClickOnScheduleByName(name);

            String repeatSelected = schedulesPage.GetRepeat();
            String optionValue = "string:Yearly";

            if(!repeatSelected.equals("Yearly")){
                logger.log(LogStatus.INFO,"Select "+optionValue+"  repeat option");
                schedulesPage.SelectRepeatByValue(optionValue);

                logger.log(LogStatus.INFO,"Press on Save button");
                schedulesPage.PressSaveButton();
            }

            int periodCount = schedulesPage.CountPeriod();
            if (periodCount == 1) {
                int rand = schedulesPage.GetRandomDigit(1, 5);
                for (int i = 0; i < rand; i++) {
                    logger.log(LogStatus.INFO, "Click on Plus period button");
                    schedulesPage.ClickOnAddSchedulePeriodButton();
                }
                logger.log(LogStatus.INFO, "Press on Save button");
                schedulesPage.PressSaveButton();
            }

            periodCount = schedulesPage.CountPeriod();
            int periodIndex = schedulesPage.GetRandomDigit(0, periodCount);

            boolean yearlyRadioButton = schedulesPage.YearlyRadioButtonIsSelected(periodIndex);

            if(!yearlyRadioButton){
                logger.log(LogStatus.INFO, "Click on yearly radio button ");
                schedulesPage.ClickOnYearlyRadioButton(periodIndex);
            }

            String month = schedulesPage.GetMonthYearly(periodIndex);
            int optionSize = schedulesPage.CountMonthsYearlyOption(periodIndex);
            int optionIndex= 0;
            String optionName = null;

            while(true){
                optionIndex = schedulesPage.GetRandomDigit(0, optionSize);
                optionName = schedulesPage.GetTextFromOptionMonthYearlyByIndex(periodIndex, optionIndex);
                if(!optionName.equals(month)) break;
            }

            logger.log(LogStatus.INFO, "Select "+optionName+" number of day");
            schedulesPage.SelectMonthYearlyByIndex(periodIndex, optionIndex);

            logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
            Assert.assertTrue(schedulesPage.CancelButtonIsEnabled());

            logger.log(LogStatus.INFO,"Check that Save button is enabled");
            Assert.assertTrue(schedulesPage.SaveButtonIsEnabled());

            logger.log(LogStatus.INFO, "Press on Save button");
            schedulesPage.PressSaveButton();

            String optionAct = schedulesPage.GetMonthYearly(periodIndex);
            logger.log(LogStatus.INFO, "Check that month option is changed");
            Assert.assertEquals(optionAct, optionName);

            logger.log(LogStatus.INFO,"Refresh");
            schedulesPage.Refresh();

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

            String optionRefr = schedulesPage.GetMonthYearly(periodIndex);
            logger.log(LogStatus.INFO, "Check that month option is changed after refresh");
            Assert.assertEquals(optionRefr, optionName);
        }
    }

    @Test
    public void ChangeDayOfMonthInYearlyTest() throws InterruptedException {
        logger = report.startTest("ChangeDayOfMonthInYearlyTest");

        int size = schedulesPage.GetSchedulesCount();
        if (size > 0) {
            int scheduleIndex = schedulesPage.GetRandomDigit(0, size);
            String name = schedulesPage.GetScheduleNameByIndex(scheduleIndex);

            logger.log(LogStatus.INFO, "Click on schedule " + name);
            schedulesPage.ClickOnScheduleByName(name);

            String repeatSelected = schedulesPage.GetRepeat();
            String optionValue = "string:Yearly";

            if(!repeatSelected.equals("Yearly")){
                logger.log(LogStatus.INFO,"Select "+optionValue+"  repeat option");
                schedulesPage.SelectRepeatByValue(optionValue);

                logger.log(LogStatus.INFO,"Press on Save button");
                schedulesPage.PressSaveButton();
            }

            int periodCount = schedulesPage.CountPeriod();
            if (periodCount == 1) {
                int rand = schedulesPage.GetRandomDigit(1, 5);
                for (int i = 0; i < rand; i++) {
                    logger.log(LogStatus.INFO, "Click on Plus period button");
                    schedulesPage.ClickOnAddSchedulePeriodButton();
                }
                logger.log(LogStatus.INFO, "Press on Save button");
                schedulesPage.PressSaveButton();
            }

            periodCount = schedulesPage.CountPeriod();
            int periodIndex = schedulesPage.GetRandomDigit(0, periodCount);

            boolean everyRadioButton = schedulesPage.EveryRadioButtonIsSelected(periodIndex);
            if(!everyRadioButton){
                logger.log(LogStatus.INFO, "Click on every radio button ");
                schedulesPage.ClickOnEveryRadioButton(periodIndex);

                logger.log(LogStatus.INFO, "Press on Save button");
                schedulesPage.PressSaveButton();
            }

            int daysRand = schedulesPage.GetRandomDigit(1, 31);
            logger.log(LogStatus.INFO, "Input in days field "+daysRand );
            schedulesPage.InputInCountDaysInMonth(periodIndex, ""+daysRand);
            schedulesPage.WaitUntilSaveButtonWillBeEnable();

            logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
            Assert.assertTrue(schedulesPage.CancelButtonIsEnabled());

            logger.log(LogStatus.INFO,"Check that Save button is enabled");
            Assert.assertTrue(schedulesPage.SaveButtonIsEnabled());

            logger.log(LogStatus.INFO, "Press on Save button");
            schedulesPage.PressSaveButton();

            int daysAct = Integer.parseInt(schedulesPage.GetCountDaysInMonth(periodIndex));
            logger.log(LogStatus.INFO,"Check that day of month is changed");
            Assert.assertEquals(daysAct, daysRand);

            logger.log(LogStatus.INFO,"Refresh");
            schedulesPage.Refresh();

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

            int daysRefr = Integer.parseInt(schedulesPage.GetCountDaysInMonth(periodIndex));
            logger.log(LogStatus.INFO,"Check that day of month is changed after refresh");
            Assert.assertEquals(daysRefr, daysRand);
        }
    }

    @Test
    public void ChangeDayOfMonthInYearlyBySpinnersTest() throws InterruptedException {
        logger = report.startTest("ChangeDayOfMonthInYearlyBySpinnersTest");

        int size = schedulesPage.GetSchedulesCount();
        if (size > 0) {
            int scheduleIndex = schedulesPage.GetRandomDigit(0, size);
            String name = schedulesPage.GetScheduleNameByIndex(scheduleIndex);

            logger.log(LogStatus.INFO, "Click on schedule " + name);
            schedulesPage.ClickOnScheduleByName(name);

            String repeatSelected = schedulesPage.GetRepeat();
            String optionValue = "string:Yearly";

            if(!repeatSelected.equals("Yearly")){
                logger.log(LogStatus.INFO,"Select "+optionValue+"  repeat option");
                schedulesPage.SelectRepeatByValue(optionValue);

                logger.log(LogStatus.INFO,"Press on Save button");
                schedulesPage.PressSaveButton();
            }

            int periodCount = schedulesPage.CountPeriod();
            if (periodCount == 1) {
                int rand = schedulesPage.GetRandomDigit(1, 5);
                for (int i = 0; i < rand; i++) {
                    logger.log(LogStatus.INFO, "Click on Plus period button");
                    schedulesPage.ClickOnAddSchedulePeriodButton();
                }
                logger.log(LogStatus.INFO, "Press on Save button");
                schedulesPage.PressSaveButton();
            }

            periodCount = schedulesPage.CountPeriod();
            int periodIndex = schedulesPage.GetRandomDigit(0, periodCount);

            boolean everyRadioButton = schedulesPage.EveryRadioButtonIsSelected(periodIndex);
            if(!everyRadioButton){
                logger.log(LogStatus.INFO, "Click on every radio button ");
                schedulesPage.ClickOnEveryRadioButton(periodIndex);

                logger.log(LogStatus.INFO, "Press on Save button");
                schedulesPage.PressSaveButton();
            }

            int days = Integer.parseInt(schedulesPage.GetCountDaysInMonth(periodIndex));
            int max = 31;
            int min = 1;
            int timesUP = schedulesPage.GetRandomDigit(0, max-days);
            int timeDown = schedulesPage.GetRandomDigit(0, days+timesUP-min);

            logger.log(LogStatus.INFO, "Click on spinner to increase days count "+timesUP+" times" );
            for(int i=0; i<timesUP;i++){
                schedulesPage.ClickDaysOfMonthUpSpinner(periodIndex);
            }

            logger.log(LogStatus.INFO, "Click on spinner to decrease days count "+timeDown+" times" );
            for(int i=0; i<timeDown;i++){
                schedulesPage.ClickDaysOfMonthDownSpinner(periodIndex);
            }

            logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
            Assert.assertTrue(schedulesPage.CancelButtonIsEnabled());

            logger.log(LogStatus.INFO,"Check that Save button is enabled");
            Assert.assertTrue(schedulesPage.SaveButtonIsEnabled());

            logger.log(LogStatus.INFO, "Press on Save button");
            schedulesPage.PressSaveButton();

            int daysAct = Integer.parseInt(schedulesPage.GetCountDaysInMonth(periodIndex));
            int daysExpected = days+timesUP-timeDown;
            logger.log(LogStatus.INFO,"Check that number of days is changed");
            Assert.assertEquals(daysAct, daysExpected);

            logger.log(LogStatus.INFO,"Refresh");
            schedulesPage.Refresh();

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

            int daysRefr = Integer.parseInt(schedulesPage.GetCountDaysInMonth(periodIndex));
            logger.log(LogStatus.INFO,"Check that number of days is changed");
            Assert.assertEquals(daysRefr, daysExpected);
        }
    }

    @Test
    public void ChangeStartDateInNeverModeTest() throws InterruptedException {
        logger = report.startTest("ChangeStartDateInNeverModeTest");

        int size = schedulesPage.GetSchedulesCount();
        if (size > 0) {
            int scheduleIndex = schedulesPage.GetRandomDigit(0, size);
            String name = schedulesPage.GetScheduleNameByIndex(scheduleIndex);

            logger.log(LogStatus.INFO, "Click on schedule " + name);
            schedulesPage.ClickOnScheduleByName(name);

            String repeatSelected = schedulesPage.GetRepeat();
            String optionValue = "string:SpecificDate";

            if(!repeatSelected.equals("Never")){
                logger.log(LogStatus.INFO,"Select "+optionValue+"  repeat option");
                schedulesPage.SelectRepeatByValue(optionValue);

                logger.log(LogStatus.INFO,"Press on Save button");
                schedulesPage.PressSaveButton();
            }

            int periodCount = schedulesPage.CountPeriod();
            if (periodCount == 1) {
                int rand = schedulesPage.GetRandomDigit(1, 5);
                for (int i = 0; i < rand; i++) {
                    logger.log(LogStatus.INFO, "Click on Plus period button");
                    schedulesPage.ClickOnAddSchedulePeriodButton();
                }
                logger.log(LogStatus.INFO, "Press on Save button");
                schedulesPage.PressSaveButton();
            }

            periodCount = schedulesPage.CountPeriod();
            int periodIndex = schedulesPage.GetRandomDigit(0, periodCount);

            logger.log(LogStatus.INFO,"Open calendar");
            schedulesPage.ClickOnTheStartDate(periodIndex);

            logger.log(LogStatus.INFO,"Open Calendar");
            schedulesPage.ClickOnDateButtonInCalendar();
            schedulesPage.ClickOnYearButton();

            logger.log(LogStatus.INFO,"Select Year");
            int yearsSize = schedulesPage.GetYearsListSize();
            int randomYear = schedulesPage.GetRandomDigit(0, yearsSize);
            schedulesPage.ClickOnYearByIndex(randomYear);

            logger.log(LogStatus.INFO,"Select Month");
            int monthSize = schedulesPage.GetMonthListSize();
            int randomMonth = schedulesPage.GetRandomDigit(0, monthSize);
            schedulesPage.ClickOnMonthByIndex(randomMonth);

            logger.log(LogStatus.INFO,"Select Day");
            int daysSize = schedulesPage.GetDaysListSize();
            int randomDays = schedulesPage.GetRandomDigit(0, daysSize);
            schedulesPage.ClickOnDaysByIndex(randomDays);

            logger.log(LogStatus.INFO,"Get start date");
            String startDate = schedulesPage.GetStartDate(periodIndex);

            logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
            Assert.assertTrue(schedulesPage.CancelButtonIsEnabled());

            logger.log(LogStatus.INFO,"Check that Save button is enabled");
            Assert.assertTrue(schedulesPage.SaveButtonIsEnabled());

            logger.log(LogStatus.INFO, "Press on Save button");
            schedulesPage.PressSaveButton();

            String startDateAct = schedulesPage.GetStartDate(periodIndex);
            logger.log(LogStatus.INFO,"Check that start time is changed");
            Assert.assertEquals(startDateAct, startDate);

            logger.log(LogStatus.INFO,"Refresh");
            schedulesPage.Refresh();

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

            String startDateRefr = schedulesPage.GetStartDate(periodIndex);
            logger.log(LogStatus.INFO,"Check that start time is changed after refresh");
            Assert.assertEquals(startDateRefr, startDate);
        }
    }

    @Test
    public void ChangeEndDateInNeverModeTest() throws InterruptedException {
        logger = report.startTest("ChangeEndDateInNeverModeTest");

        int size = schedulesPage.GetSchedulesCount();
        if (size > 0) {
            int scheduleIndex = schedulesPage.GetRandomDigit(0, size);
            String name = schedulesPage.GetScheduleNameByIndex(scheduleIndex);

            logger.log(LogStatus.INFO, "Click on schedule " + name);
            schedulesPage.ClickOnScheduleByName(name);

            String repeatSelected = schedulesPage.GetRepeat();
            String optionValue = "string:SpecificDate";

            if(!repeatSelected.equals("Never")){
                logger.log(LogStatus.INFO,"Select "+optionValue+"  repeat option");
                schedulesPage.SelectRepeatByValue(optionValue);

                logger.log(LogStatus.INFO,"Press on Save button");
                schedulesPage.PressSaveButton();
            }

            int periodCount = schedulesPage.CountPeriod();
            if (periodCount == 1) {
                int rand = schedulesPage.GetRandomDigit(1, 5);
                for (int i = 0; i < rand; i++) {
                    logger.log(LogStatus.INFO, "Click on Plus period button");
                    schedulesPage.ClickOnAddSchedulePeriodButton();
                }
                logger.log(LogStatus.INFO, "Press on Save button");
                schedulesPage.PressSaveButton();
            }

            periodCount = schedulesPage.CountPeriod();
            int periodIndex = schedulesPage.GetRandomDigit(0, periodCount);

            logger.log(LogStatus.INFO,"Open calendar");
            schedulesPage.ClickOnTheEndDate(periodIndex);

            logger.log(LogStatus.INFO,"Open Calendar");
            schedulesPage.ClickOnDateButtonInCalendar();
            schedulesPage.ClickOnYearButton();

            logger.log(LogStatus.INFO,"Select Year");
            int yearsSize = schedulesPage.GetYearsListSize();
            int randomYear = schedulesPage.GetRandomDigit(0, yearsSize);
            schedulesPage.ClickOnYearByIndex(randomYear);

            logger.log(LogStatus.INFO,"Select Month");
            int monthSize = schedulesPage.GetMonthListSize();
            int randomMonth = schedulesPage.GetRandomDigit(0, monthSize);
            schedulesPage.ClickOnMonthByIndex(randomMonth);

            logger.log(LogStatus.INFO,"Select Day");
            int daysSize = schedulesPage.GetDaysListSize();
            int randomDays = schedulesPage.GetRandomDigit(0, daysSize);
            schedulesPage.ClickOnDaysByIndex(randomDays);

            logger.log(LogStatus.INFO,"Get end date");
            String endDate = schedulesPage.GetEndDate(periodIndex);

            logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
            Assert.assertTrue(schedulesPage.CancelButtonIsEnabled());

            logger.log(LogStatus.INFO,"Check that Save button is enabled");
            Assert.assertTrue(schedulesPage.SaveButtonIsEnabled());

            logger.log(LogStatus.INFO, "Press on Save button");
            schedulesPage.PressSaveButton();

            String endDateAct = schedulesPage.GetEndDate(periodIndex);
            logger.log(LogStatus.INFO,"Check that end time is changed");
            Assert.assertEquals(endDateAct, endDate);

            logger.log(LogStatus.INFO,"Refresh");
            schedulesPage.Refresh();

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

            String endDateRefr = schedulesPage.GetEndDate(periodIndex);
            logger.log(LogStatus.INFO,"Check that end time is changed after refresh");
            Assert.assertEquals(endDateRefr, endDate);
        }
    }

    @Test
    public void ChangeDateAndCheckDurationInNeverModeTest() throws InterruptedException {
        logger = report.startTest("ChangeDateAndCheckDurationInNeverModeTest");

        int size = schedulesPage.GetSchedulesCount();
        if (size > 0) {
            int scheduleIndex = schedulesPage.GetRandomDigit(0, size);
            String name = schedulesPage.GetScheduleNameByIndex(scheduleIndex);

            logger.log(LogStatus.INFO, "Click on schedule " + name);
            schedulesPage.ClickOnScheduleByName(name);

            String repeatSelected = schedulesPage.GetRepeat();
            String optionValue = "string:SpecificDate";

            if(!repeatSelected.equals("Never")){
                logger.log(LogStatus.INFO,"Select "+optionValue+"  repeat option");
                schedulesPage.SelectRepeatByValue(optionValue);

                logger.log(LogStatus.INFO,"Press on Save button");
                schedulesPage.PressSaveButton();
            }

            int periodCount = schedulesPage.CountPeriod();
            if (periodCount == 1) {
                int rand = schedulesPage.GetRandomDigit(1, 5);
                for (int i = 0; i < rand; i++) {
                    logger.log(LogStatus.INFO, "Click on Plus period button");
                    schedulesPage.ClickOnAddSchedulePeriodButton();
                }
                logger.log(LogStatus.INFO, "Press on Save button");
                schedulesPage.PressSaveButton();
            }

            periodCount = schedulesPage.CountPeriod();
            int periodIndex = schedulesPage.GetRandomDigit(0, periodCount);

            int hoursDuration = schedulesPage.GetDurationHours(periodIndex);

            logger.log(LogStatus.INFO,"Open calendar to set start time");
            schedulesPage.ClickOnTheStartDate(periodIndex);

            logger.log(LogStatus.INFO,"Click on Today button");
            schedulesPage.ClickOnTodayButtonInCalendar();

            logger.log(LogStatus.INFO,"Open calendar to set end time");
            schedulesPage.ClickOnTheEndDate(periodIndex);

            logger.log(LogStatus.INFO,"Click on tomorrow day ");
            schedulesPage.SelectNextDateInEndDate();

            logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
            Assert.assertTrue(schedulesPage.CancelButtonIsEnabled());

            logger.log(LogStatus.INFO,"Check that Save button is enabled");
            Assert.assertTrue(schedulesPage.SaveButtonIsEnabled());

            logger.log(LogStatus.INFO, "Press on Save button");
            schedulesPage.PressSaveButton();

            int hoursDurationAct = schedulesPage.GetDurationHours(periodIndex);
            logger.log(LogStatus.INFO,"Check that duration is changed");
            Assert.assertEquals(hoursDurationAct, hoursDuration+24);

            logger.log(LogStatus.INFO,"Refresh");
            schedulesPage.Refresh();

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

            int hoursDurationRefr = schedulesPage.GetDurationHours(periodIndex);
            logger.log(LogStatus.INFO,"Check that duration is changed");
            Assert.assertEquals(hoursDurationRefr, hoursDuration+24);
        }
    }

       @Test
    public void AddSomePeriodsToScheduleTest() throws InterruptedException {
        logger = report.startTest("AddSomePeriodsToScheduleTest");

        int size = schedulesPage.GetSchedulesCount();
        if (size > 0) {
            int scheduleIndex = schedulesPage.GetRandomDigit(0, size);
            String name = schedulesPage.GetScheduleNameByIndex(scheduleIndex);

            logger.log(LogStatus.INFO, "Click on schedule " + name);
            schedulesPage.ClickOnScheduleByName(name);

            int periodCount = schedulesPage.CountPeriod();

            int rand = schedulesPage.GetRandomDigit(1, 7);
            for (int i = 0; i < rand; i++) {
                logger.log(LogStatus.INFO, "Click on Plus period button");
                schedulesPage.ClickOnAddSchedulePeriodButton();
            }

            logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
            Assert.assertTrue(schedulesPage.CancelButtonIsEnabled());

            logger.log(LogStatus.INFO,"Check that Save button is enabled");
            Assert.assertTrue(schedulesPage.SaveButtonIsEnabled());

            logger.log(LogStatus.INFO, "Press on Save button");
            schedulesPage.PressSaveButton();

            int periodCountAct = schedulesPage.CountPeriod();
            logger.log(LogStatus.INFO,"Check that "+rand+" periods are added to schedule " +name);
            Assert.assertEquals(periodCountAct, periodCount+rand);

            logger.log(LogStatus.INFO,"Refresh");
            schedulesPage.Refresh();

            logger.log(LogStatus.INFO,"Click on schedule "+name);
            schedulesPage.ClickOnScheduleByName(name);

            int periodCountRefr = schedulesPage.CountPeriod();
            logger.log(LogStatus.INFO,"Check that "+rand+" periods are added to schedule " +name+ "after refresh");
            Assert.assertEquals(periodCountRefr, periodCount+rand);
        }
    }

    @Test
    public void DeleteSomePeriodsTest() throws InterruptedException {
        logger = report.startTest("DeleteSomePeriodsTest");

        int size = schedulesPage.GetSchedulesCount();
        if (size > 0) {
            int scheduleIndex = schedulesPage.GetRandomDigit(0, size);
            String name = schedulesPage.GetScheduleNameByIndex(scheduleIndex);

            logger.log(LogStatus.INFO, "Click on schedule " + name);
            schedulesPage.ClickOnScheduleByName(name);

            int periodCount = schedulesPage.CountPeriod();
            if(periodCount>1){
                int rand = schedulesPage.GetRandomDigit(1, periodCount);
                int count = periodCount;
                for (int i = 0; i < rand; i++) {
                    int index = schedulesPage.GetRandomDigit(0,count);
                    logger.log(LogStatus.INFO, "Move to period with index"+ index);
                    schedulesPage.MoveToPeriodByIndex(index);

                    logger.log(LogStatus.INFO, "Click on delete icon");
                    schedulesPage.ClickOnDeletePeriodIcon();
                    count--;
                }

                logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
                Assert.assertTrue(schedulesPage.CancelButtonIsEnabled());

                logger.log(LogStatus.INFO,"Check that Save button is enabled");
                Assert.assertTrue(schedulesPage.SaveButtonIsEnabled());

                logger.log(LogStatus.INFO, "Press on Save button");
                schedulesPage.PressSaveButton();

                int periodCountAct = schedulesPage.CountPeriod();
                logger.log(LogStatus.INFO,"Check that "+rand+" periods are removed from schedule " +name);
                Assert.assertEquals(periodCountAct, periodCount-rand);

                logger.log(LogStatus.INFO,"Refresh");
                schedulesPage.Refresh();

                logger.log(LogStatus.INFO,"Click on schedule "+name);
                schedulesPage.ClickOnScheduleByName(name);

                int periodCountRefr = schedulesPage.CountPeriod();
                logger.log(LogStatus.INFO,"Check that "+rand+" periods are removed from schedule " +name+ "after refresh");
                Assert.assertEquals(periodCountRefr, periodCount-rand);
            }
            else{
                logger.log(LogStatus.INFO,"There is only one period");
            }
        }
    }

    @Test
    public void DeleteAllPeriodsTest() throws InterruptedException {
        logger = report.startTest("DeleteAllPeriodsTest");

        int size = schedulesPage.GetSchedulesCount();
        if (size > 0) {
            int scheduleIndex = schedulesPage.GetRandomDigit(0, size);
            String name = schedulesPage.GetScheduleNameByIndex(scheduleIndex);

            logger.log(LogStatus.INFO, "Click on schedule " + name);
            schedulesPage.ClickOnScheduleByName(name);

            int periodCount = schedulesPage.CountPeriod();
            if(periodCount>1){
                for (int i = 0; i < periodCount-1; i++) {
                    logger.log(LogStatus.INFO, "Move to period with index"+ 0);
                    schedulesPage.MoveToPeriodByIndex(0);

                    logger.log(LogStatus.INFO, "Click on delete icon");
                    schedulesPage.ClickOnDeletePeriodIcon();
                }
            }

            logger.log(LogStatus.INFO, "Move to period with index"+ 0);
            schedulesPage.MoveToPeriodByIndex(0);

            logger.log(LogStatus.INFO, "Check that delete icon doesn't appear for last period");
            Assert.assertFalse(schedulesPage.DeletePeriodIconIsExist());
        }
    }

    @Test
    public void AddNewScheduleAndCheckInVideoChannelsTest() throws InterruptedException {
        logger=report.startTest("AddNewScheduleAndCheckInVideoChannelsTest");

        logger.log(LogStatus.INFO,"Click on New button");
        schedulesPage.ClickOnNewButton();

        String scheduleName = "NewSchedule"+schedulesPage.GetRandomDigit(0,10000);
        logger.log(LogStatus.INFO,"Input into name field");
        schedulesPage.InputInNameField(scheduleName);

        logger.log(LogStatus.INFO,"Press on Save button");
        schedulesPage.PressSaveButton();

        logger.log(LogStatus.INFO,"Click on Video Channels button");
        videoChannelsPage.ClickOnVideoChannels();

        logger.log(LogStatus.INFO,"Click on Recording button");
        videoChannelsPage.GoToRecording();

        logger.log(LogStatus.INFO,"Check that schedule "+scheduleName+" is exists in schedule drop-down list");
        Assert.assertTrue(videoChannelsPage.CheckOptionIsExistOnScheduleDropDownList(scheduleName));
    }

    @Test
    public void AddNewScheduleAndCheckInAudioChannelsTest() throws InterruptedException {
        logger=report.startTest("AddNewScheduleAndCheckInAudioChannelsTest");

        logger.log(LogStatus.INFO,"Click on New button");
        schedulesPage.ClickOnNewButton();

        String scheduleName = "NewSchedule"+schedulesPage.GetRandomDigit(0,10000);
        logger.log(LogStatus.INFO,"Input into name field");
        schedulesPage.InputInNameField(scheduleName);

        logger.log(LogStatus.INFO,"Press on Save button");
        schedulesPage.PressSaveButton();

        logger.log(LogStatus.INFO,"Click on Audio Channels button");
        audioChannelsPage.ClickAudioChannelButon();

        logger.log(LogStatus.INFO,"Click on Recording button");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Check that schedule "+scheduleName+" is exists in schedule drop-down list");
        Assert.assertTrue(audioChannelsPage.CheckOptionIsExistOnScheduleDropDownList(scheduleName));
    }

    @Test
    public void AddNewScheduleAndCheckInRulesTest() throws InterruptedException {
        logger=report.startTest("AddNewScheduleAndCheckInRulesTest");

        logger.log(LogStatus.INFO,"Click on New button");
        schedulesPage.ClickOnNewButton();

        String scheduleName = "NewSchedule"+schedulesPage.GetRandomDigit(0,10000);
        logger.log(LogStatus.INFO,"Input into name field");
        schedulesPage.InputInNameField(scheduleName);

        logger.log(LogStatus.INFO,"Press on Save button");
        schedulesPage.PressSaveButton();

        logger.log(LogStatus.INFO,"Click on Rules button");
        rulesPage.ClickOnRulesButton();

        logger.log(LogStatus.INFO,"Check that schedule "+scheduleName+" is exists in schedule drop-down list");
        Assert.assertTrue(rulesPage.CheckOptionIsExistOnScheduleDropDownList(scheduleName));
    }


    @Test
    public void DeleteScheduleByButtonAndCheckInVideoChannelsTest() throws InterruptedException {
        logger=report.startTest("DeleteScheduleByButtonAndCheckInVideoChannelsTest");

        int size = schedulesPage.GetSchedulesCount();
        if(size>0){
            int index = schedulesPage.GetRandomDigit(0,size);
            String scheduleName = schedulesPage.GetScheduleNameByIndex(index);

            logger.log(LogStatus.INFO,"Click on schedule "+scheduleName);
            schedulesPage.ClickOnScheduleByName(scheduleName);

            logger.log(LogStatus.INFO,"Click on Delete button");
            schedulesPage.ClickOnDeleteButton();

            logger.log(LogStatus.INFO,"Click on Yes button in Delete dialog");
            schedulesPage.ConfirmRemoving();

            boolean ErrorDialog = schedulesPage.ErrorOnSchedulesExists();
            if(!ErrorDialog){
                logger.log(LogStatus.INFO,"Click on Video Channels button");
                videoChannelsPage.ClickOnVideoChannels();

                logger.log(LogStatus.INFO,"Click on Recording button");
                videoChannelsPage.GoToRecording();

                logger.log(LogStatus.INFO,"Check that schedule "+scheduleName+" isn't exists in schedule drop-down list");
                Assert.assertFalse(videoChannelsPage.CheckOptionIsExistOnScheduleDropDownList(scheduleName));
            }
        }
    }

    @Test
    public void DeleteScheduleByButtonAndCheckInAudioChannelsTest() throws InterruptedException {
        logger=report.startTest("DeleteScheduleByButtonAndCheckInAudioChannelsTest");

        int size = schedulesPage.GetSchedulesCount();
        if(size>0){
            int index = schedulesPage.GetRandomDigit(0,size);
            String scheduleName = schedulesPage.GetScheduleNameByIndex(index);

            logger.log(LogStatus.INFO,"Click on schedule "+scheduleName);
            schedulesPage.ClickOnScheduleByName(scheduleName);

            logger.log(LogStatus.INFO,"Click on Delete button");
            schedulesPage.ClickOnDeleteButton();

            logger.log(LogStatus.INFO,"Click on Yes button in Delete dialog");
            schedulesPage.ConfirmRemoving();

            boolean ErrorDialog = schedulesPage.ErrorOnSchedulesExists();
            if(!ErrorDialog){
                logger.log(LogStatus.INFO,"Click on Audio Channels button");
                audioChannelsPage.ClickAudioChannelButon();

                logger.log(LogStatus.INFO,"Click on Recording button");
                audioChannelsPage.GoToRecordingPage();

                logger.log(LogStatus.INFO,"Check that schedule "+scheduleName+" isn't exists in schedule drop-down list");
                Assert.assertFalse(audioChannelsPage.CheckOptionIsExistOnScheduleDropDownList(scheduleName));
            }
        }
    }

    @Test
    public void DeleteScheduleByButtonAndCheckInRulesTest() throws InterruptedException {
        logger=report.startTest("DeleteScheduleByButtonAndCheckInRulesTest");

        int size = schedulesPage.GetSchedulesCount();
        if(size>0){
            int index = schedulesPage.GetRandomDigit(0,size);
            String scheduleName = schedulesPage.GetScheduleNameByIndex(index);

            logger.log(LogStatus.INFO,"Click on schedule "+scheduleName);
            schedulesPage.ClickOnScheduleByName(scheduleName);

            logger.log(LogStatus.INFO,"Click on Delete button");
            schedulesPage.ClickOnDeleteButton();

            logger.log(LogStatus.INFO,"Click on Yes button in Delete dialog");
            schedulesPage.ConfirmRemoving();

            boolean ErrorDialog = schedulesPage.ErrorOnSchedulesExists();
            if(!ErrorDialog){
                logger.log(LogStatus.INFO,"Click on Rules button");
                rulesPage.ClickOnRulesButton();

                logger.log(LogStatus.INFO,"Check that schedule "+scheduleName+" isn't exists in schedule drop-down list");
                Assert.assertFalse(rulesPage.CheckOptionIsExistOnScheduleDropDownList(scheduleName));
            }
        }
    }

    @AfterMethod
    public void afterMethod(ITestResult result){
        if(result.getStatus()==ITestResult.SUCCESS){
            logger.log(LogStatus.PASS, "Test is passed");
        }
        if(result.getStatus()==ITestResult.FAILURE){
            rulesPage.takeScreenshot(driver, "Schedules", result.getName());
            logger.log(LogStatus.FAIL, result.getThrowable());
            logger.log(LogStatus.FAIL, "Screenshot is below:"+logger.addScreenCapture("./"+result.getName()+".png"));
        }
        report.endTest(logger);
        driver.close();
        driver.quit();
    }

    @AfterClass
    public void endReport(){
        report.flush();
        report.close();
       
    }
}
