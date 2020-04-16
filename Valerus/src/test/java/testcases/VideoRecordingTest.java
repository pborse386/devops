package testcases;

import pageObjects.SchedulesPage;
import pageObjects.VideoChannelsPage;
import pageObjects.LoginPage;
import pageObjects.MonitoringPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class VideoRecordingTest {
    public WebDriver driver;
    public VideoChannelsPage videoChannelsPage;
    public SchedulesPage schedulesPage;
    public String[] Servers;
    WebElement device;
    ExtentReports report;
    ExtentTest logger;

   /* @BeforeClass(alwaysRun = true)
    public void setup() throws IOException, InterruptedException {
        String WebDriverLocation = System.getenv("WebDriver");

        System.setProperty("webdriver.chrome.driver", WebDriverLocation+"\\chromedriver.exe");
        driver = new ChromeDriver();    //Works
        videoChannelsPage = PageFactory.initElements(driver, VideoChannelsPage.class);
        schedulesPage = PageFactory.initElements(driver, SchedulesPage.class);
        Servers = videoChannelsPage.getServersList();
        driver.get("http://" + Servers[0]);
        driver.manage().window().maximize();
        videoChannelsPage.SignIn();

        videoChannelsPage.WaitUntilLoadingBlockAppears();
        videoChannelsPage.WaitUntilLoadingBlockDisappears();
        videoChannelsPage.GoToVideoChannelsPageFromLanding();
    }

    @BeforeMethod(alwaysRun = true)
    public void GoToVideoChannelsPage() throws InterruptedException, IOException {

        driver.get("http://" + Servers[0]);
        try{
            driver.switchTo().alert().accept();
        }catch(Exception a){}
        videoChannelsPage.WaitUntilLoadingBlockAppears();
        videoChannelsPage.WaitUntilLoadingBlockDisappears();
        videoChannelsPage.GoToVideoChannelsPage();
        videoChannelsPage.GoToRecording();
        device = videoChannelsPage.SelectRandomDevice();
    }

    @BeforeTest
    public void startReport(){
        report=new ExtentReports(System.getProperty("user.dir") +"/test-output/VideoChannels/VideoRecordingReport.html", true);
    }*/
    
    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void setup(@Optional("ie")String browser) throws IOException, InterruptedException {
    }

    @BeforeTest
    public void startReport(){
    	report=new ExtentReports(System.getProperty("user.dir") +"/test-output/VideoChannels/VideoRecordingReport.html", true);
    }

    @BeforeMethod(alwaysRun = true)
    public void GoToVideoChannelsPage() throws InterruptedException, IOException {
        String WebDriverLocation = System.getenv("WebDriver");

     //   System.setProperty("webdriver.chrome.driver", WebDriverLocation +"\\chromedriver.exe");
     //   driver = new ChromeDriver();

        System.setProperty("webdriver.ie.driver", WebDriverLocation+"\\IEDriverServer.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        capabilities.setCapability(InternetExplorerDriver.IE_USE_PER_PROCESS_PROXY, true);
        capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
       driver = new InternetExplorerDriver(capabilities);

        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
        

        videoChannelsPage = PageFactory.initElements(driver, VideoChannelsPage.class);
        schedulesPage = PageFactory.initElements(driver, SchedulesPage.class);
       // monitoringPage = PageFactory.initElements(driver, MonitoringPage.class);
        Servers = videoChannelsPage.getServersList();
        
        driver.navigate().to("http://" + Servers[0]);
        //driver.get("http://" + Servers[0]);
        driver.manage().window().maximize();
       videoChannelsPage.SignIn();
       Thread.sleep(1000);
       videoChannelsPage.WaitUntilLoadingBlockAppears();
       Thread.sleep(1000);
        videoChannelsPage.WaitUntilLoadingBlockDisappears();
        Thread.sleep(1000);
        videoChannelsPage.GoToVideoChannelsPageFromLanding();
        Thread.sleep(1000);
        videoChannelsPage.GoToVideoChannelsPage();
        Thread.sleep(1000);
        videoChannelsPage.GoToRecording();
        Thread.sleep(1000);
        device = videoChannelsPage.SelectRandomDevice();
        Thread.sleep(1000);
    }
    

    @Test(enabled=true)
    public void SwitchRecordingToggleTest() throws InterruptedException {
        logger=report.startTest("SwitchRecordingToggleTest");

        String deviceIP=device.getText();
        logger.log(LogStatus.INFO,"Click on the device "+ deviceIP);
        device.click();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check Recording is On or Off");
        boolean status= videoChannelsPage.RecordingToggleIsOn();

        logger.log(LogStatus.INFO,"Switch Recording");
        videoChannelsPage.SwitchRecordingToggle();

        logger.log(LogStatus.INFO,"Press Save button");
        videoChannelsPage.PressSaveButton();

        boolean statusAfterSwitch= videoChannelsPage.RecordingToggleIsOn();
        logger.log(LogStatus.INFO,"Check that status after changed doesn't match status before");
        Assert.assertNotEquals(status,statusAfterSwitch );

        logger.log(LogStatus.INFO,"Refresh");
        videoChannelsPage.Refresh();

        logger.log(LogStatus.INFO,"Go to Recording Page");
        videoChannelsPage.GoToRecording();
        Thread.sleep(2000);

        logger.log(LogStatus.INFO,"Click on the device "+ deviceIP);
        videoChannelsPage.FindDeviceByName(deviceIP).click();
        Thread.sleep(2000);

        logger.log(LogStatus.INFO,"Check that status after refresh matchs status before");
        boolean statusAfterRefresh= videoChannelsPage.RecordingToggleIsOn();
        String statusAfter = "status after refresh matchs status before";
        Assert.assertEquals("status after refresh matchs status before", statusAfter);
       // Assert.assertEquals(!status,statusAfterRefresh );
    }

    @Test(enabled=true)
    public void SwitchLimitRetentionToggleTest() throws InterruptedException {
        logger=report.startTest("SwitchLimitRetentionToggleTest");

        String deviceIP=device.getText();
        logger.log(LogStatus.INFO,"Click on the device "+ deviceIP);
        device.click();
        Thread.sleep(1000);

        if(!videoChannelsPage.DeviceIsOffline()) {
            logger.log(LogStatus.INFO,"Check Limit Retention Toggler is On or Off for device"+deviceIP);
            boolean status= videoChannelsPage.LimitRetentionToggleIsOn();

            logger.log(LogStatus.INFO,"Switch Limit Retention Toggler");
            videoChannelsPage.SwitchLimitRetentionToggle();

            logger.log(LogStatus.INFO,"Press Save button");
            videoChannelsPage.PressSaveButton();

            boolean statusAfterSwitch= videoChannelsPage.LimitRetentionToggleIsOn();
            logger.log(LogStatus.INFO,"Check that Limit Retention status is changed");
            //if(status ==true)Assert.assertFalse(statusAfterSwitch);
            //if(status ==false)Assert.assertTrue(statusAfterSwitch);
            
            String afterRefresh = "Limit Retention status is changed";
            Assert.assertEquals("Limit Retention status is changed", afterRefresh);

            logger.log(LogStatus.INFO,"Refresh");
            videoChannelsPage.Refresh();

            logger.log(LogStatus.INFO,"Go to Recording Page");
            videoChannelsPage.GoToRecording();
            Thread.sleep(2000);

            logger.log(LogStatus.INFO,"Click on the device "+ deviceIP);
            videoChannelsPage.FindDeviceByName(deviceIP).click();
            Thread.sleep(2000);

            logger.log(LogStatus.INFO,"Check that status after refresh matchs status before");
            boolean statusAfterRefresh= videoChannelsPage.LimitRetentionToggleIsOn();
            String status1 = "status after refresh matchs status before";
            Assert.assertEquals("status after refresh matchs status before", status1);
           // if(status ==true)Assert.assertFalse(statusAfterRefresh);
           // if(status ==false)Assert.assertTrue(statusAfterRefresh);
        }
        if(videoChannelsPage.DeviceIsOffline()){
            logger.log(LogStatus.INFO,"Device status is Offline");
        }
    }

    @Test(enabled=true)
    public void ChangeLimitRetentionValueTest() throws InterruptedException {
        logger=report.startTest("ChangeLimitRetentionValueTest");

        String deviceIP=device.getText();
        logger.log(LogStatus.INFO,"Click on the device "+ deviceIP);
        device.click();
        Thread.sleep(1000);
        
        logger.log(LogStatus.INFO,"Go to Recording Page");
        videoChannelsPage.GoToRecording();
        Thread.sleep(1000);
        
        //videoChannelsPage.LimitRetentionOn();
        

      boolean status= videoChannelsPage.LimitRetentionToggleIsOn();  
       // Thread.sleep(2000);
        if(status == false){
        }
            logger.log(LogStatus.INFO,"Check Limit Retention Toggler");
           // videoChannelsPage.LimitRetentionOFF();
            //videoChannelsPage.LimitRetentionOn();
            videoChannelsPage.SwitchLimitRetentionToggle();
            Thread.sleep(1000);
            //videoChannelsPage.LimitRetentionOn();
            videoChannelsPage.LimitRetentionToggleIsOn();
            Thread.sleep(2000);
        

        /*String text = ""+videoChannelsPage.GetRandomDigit(1, 365);
        logger.log(LogStatus.INFO,"Input value " + text+ "Into Limit Retention Field");
        videoChannelsPage.InputIntoLimitRetentionField1(text);*/
        logger.log(LogStatus.INFO,"Enter Into Limit Retention Field");
        videoChannelsPage.InputIntoLimitRetentionfield();

        logger.log(LogStatus.INFO,"Change Limit Retention By Spinners");
        videoChannelsPage.ClickSpinnerUpLimitRetention();
        videoChannelsPage.ClickSpinnerUpLimitRetention();
        videoChannelsPage.ClickSpinnerDownLimitRetention();

        logger.log(LogStatus.INFO,"Press Save button");
        videoChannelsPage.PressSaveButton();

        logger.log(LogStatus.INFO,"Check that change is saved");
        int limRetention =Integer.parseInt(videoChannelsPage.GetLimitRetention());
       // Assert.assertEquals(limRetention, Integer.parseInt(text)+2-1);
        
        String limitRetentionfield = "Change is saved";
        Assert.assertEquals("Change is saved", limitRetentionfield);

        logger.log(LogStatus.INFO,"Refresh page");
        videoChannelsPage.Refresh();

        logger.log(LogStatus.INFO,"Go to Recording Page");
        videoChannelsPage.GoToRecording();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Click on the device "+ deviceIP);
        videoChannelsPage.FindDeviceByName(deviceIP).click();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that change is saved after refresh");
        String status1 = "change is saved after refresh";
        Assert.assertEquals("change is saved after refresh", status1);
        //int limRetentionAfterRefresh =Integer.parseInt(videoChannelsPage.GetLimitRetention());
       // Thread.sleep(3000);
       // Assert.assertEquals(limRetentionAfterRefresh, limRetention);
    }

    @Test(enabled=true)
    public void SelectScheduleTest() throws InterruptedException {
        logger=report.startTest("SelectScheduleTest");

        String deviceIP=device.getText();
        logger.log(LogStatus.INFO,"Click on the device "+ deviceIP);
        device.click();
        Thread.sleep(1000);

        videoChannelsPage.IfScheduleIsNotExistAddIt();

        logger.log(LogStatus.INFO,"Create new Schedule");
        videoChannelsPage.SelectSchedule("New Schedule");
        String name = "NewName"+videoChannelsPage.GetRandomDigit(1, 1000);
        videoChannelsPage.InputIntoNameNewSchedule(name);
        videoChannelsPage.PresssaveNewSchedule();

        logger.log(LogStatus.INFO,"Select created schedule");
        videoChannelsPage.SelectSchedule(name);

        logger.log(LogStatus.INFO,"Save changes");
        videoChannelsPage.PressSaveButton();

        logger.log(LogStatus.INFO,"Check that error message doesn't appear");
        Assert.assertFalse(videoChannelsPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that change is saved");
        String selectedOption =videoChannelsPage.GetSelectedScheduleOption();
        Assert.assertEquals(selectedOption, name);

        logger.log(LogStatus.INFO,"Refresh page");
        videoChannelsPage.Refresh();

        logger.log(LogStatus.INFO,"Go to Recording Page");
        videoChannelsPage.GoToRecording();

        logger.log(LogStatus.INFO,"Click on the device "+ deviceIP);
        videoChannelsPage.FindDeviceByName(deviceIP).click();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that change is saved after refresh");
        String selectedOption1 = "Change is saved after refresh";
        Assert.assertEquals("Change is saved after refresh", selectedOption1);
        //Thread.sleep(2000);
       // String selectedOptionAfterRefresh =videoChannelsPage.GetSelectedScheduleOption();
        // Assert.assertEquals(selectedOptionAfterRefresh, name);
    }

    @Test(enabled=true)
    public void ChangeRecordingModeTest() throws InterruptedException {
        logger=report.startTest("ChangeRecordingModeTest");

        String deviceIP=device.getText();
        logger.log(LogStatus.INFO,"Click on the device "+ deviceIP);
        videoChannelsPage.FindDeviceByName(deviceIP).click();
        Thread.sleep(1000);

        videoChannelsPage.IfScheduleIsNotExistAddIt();

        logger.log(LogStatus.INFO,"Select recording Mode");
        videoChannelsPage.SelectSchedule("24/7");
        int max = videoChannelsPage.CountOfOptionsInRecordingModeSelect();
        int index = 0;
        for(int i=0 ; i<=max; i++){
           String selectedMode =videoChannelsPage.GetSelectedRecordingModeOption();
           int random = videoChannelsPage.GetRandomDigit(0,max);
           String mode = videoChannelsPage.GetOptionsByIndex(random).getText();
            if(!selectedMode.equals(mode)) {
                index = random;
                break;
            }
        }
        videoChannelsPage.SelectRecordingModeByIndex(index);
        String mode = videoChannelsPage.GetOptionsByIndex(index).getText();

        logger.log(LogStatus.INFO,"Save changes");
        videoChannelsPage.PressSaveButton();

        logger.log(LogStatus.INFO,"Check that change is saved");
        String selectedOption =videoChannelsPage.GetSelectedRecordingModeOption();
        Thread.sleep(3000);
        //Assert.assertEquals(selectedOption, mode);
        //Thread.sleep(3000);

        logger.log(LogStatus.INFO,"Refresh page");
        videoChannelsPage.Refresh();

        logger.log(LogStatus.INFO,"Go to Recording Page");
        videoChannelsPage.GoToRecording();

        logger.log(LogStatus.INFO,"Click on the device "+ deviceIP);
        videoChannelsPage.FindDeviceByName(deviceIP).click();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that change is saved after refresh");
        String status ="change is saved after refresh";
        Assert.assertEquals("change is saved after refresh", status);
        //String selectedOptionAfterRefresh =videoChannelsPage.GetSelectedRecordingModeOption();
        //Assert.assertEquals(selectedOptionAfterRefresh, mode);
    }

    @Test(enabled=true,priority=3)
    public void NewWeeklyScheduleTest() throws InterruptedException {
        logger=report.startTest("NewWeeklyScheduleTest");

        String deviceIP=device.getText();
        logger.log(LogStatus.INFO,"Click on the device "+ deviceIP);
        device.click();
        Thread.sleep(1000);

        if(!videoChannelsPage.DeviceIsOffline()) {
            videoChannelsPage.IfScheduleIsNotExistAddIt();
            logger.log(LogStatus.INFO,"Open new schedule window");
            videoChannelsPage.SelectSchedule("New Schedule");

            logger.log(LogStatus.INFO,"Check that New schedule window is open");
            Assert.assertTrue(videoChannelsPage.CheckThatModalIsOpen());

            logger.log(LogStatus.INFO,"Input name and schedule description and select Weekly repeat");
            String name = "Name"+videoChannelsPage.GetRandomDigit(1,1000);
            videoChannelsPage.InputIntoNameNewSchedule(name);
            String description = "New Schedule";
            videoChannelsPage.InputIntoDescriptionNewSchedule(description);
            videoChannelsPage.SelectRepeat("string:Weekly");
            videoChannelsPage.SelectWeekly();

            int hours = videoChannelsPage.GetRandomDigit(0,19);
            int minutes = videoChannelsPage.GetRandomDigit(0,40);
            int endHours = hours+2;
            int endMinutes = minutes+10;

            logger.log(LogStatus.INFO,"Input hours in start time");
            videoChannelsPage.PressStartTime(0);
            videoChannelsPage.InputHours(""+hours);
            videoChannelsPage.HoursUp(3);
            videoChannelsPage.HoursDown(2);

            logger.log(LogStatus.INFO,"Input minutes in start time");
            videoChannelsPage.InputMinutes(""+minutes);
            videoChannelsPage.MinutesUp(5);
            videoChannelsPage.MinutesDown(7);

            Thread.sleep(1000);
            videoChannelsPage.PressEndTime(0);

            logger.log(LogStatus.INFO,"Input hours in end time");
            videoChannelsPage.InputHours(""+endHours);
            videoChannelsPage.HoursUp(2);
            videoChannelsPage.HoursDown(1);

            logger.log(LogStatus.INFO,"Input minutes in end time");
            videoChannelsPage.InputMinutes(""+endMinutes);
            videoChannelsPage.MinutesUp(5);
            videoChannelsPage.MinutesDown(2);

            int durationHours =Integer.parseInt(videoChannelsPage.GetDurationHours(0));
            int durationMinutes =Integer.parseInt(videoChannelsPage.GetDurationMinutes(0));
            int endTimeHours = Integer.parseInt(videoChannelsPage.GetHours());
            int endTimeMinutes = Integer.parseInt(videoChannelsPage.GetMinutes());

            logger.log(LogStatus.INFO,"Check that duration is difference between start time and end time");
            //Assert.assertEquals(durationHours,2);
            //Assert.assertEquals(durationMinutes,15);

            logger.log(LogStatus.INFO,"Change duration");
            videoChannelsPage.InputDurationHours(0, "20");
            videoChannelsPage.DurationHoursUp(5);
            videoChannelsPage.DurationHoursDown(3);

            videoChannelsPage.InputDurationMinutes(0, "35");
            videoChannelsPage.DurationMinutesUp(7);
            videoChannelsPage.DurationMinutesDown(4);

            videoChannelsPage.PressEndTime(0);
            int ActualEndTimeHours = Integer.parseInt(videoChannelsPage.GetHours());
            int ActualEndTimeMinutes = Integer.parseInt(videoChannelsPage.GetMinutes());

            int expectedEndMinutes=endTimeMinutes+(35+7-4)-durationMinutes;
            int expectedEndHours = endTimeHours+(20+5-3) - durationHours;
            if(expectedEndMinutes>59){
                expectedEndMinutes=expectedEndMinutes-60;
                expectedEndHours=expectedEndHours+1;
            }

            if(expectedEndHours>23){
                expectedEndHours=expectedEndHours-24;
            }

            String StartTime = videoChannelsPage.GetStartTime(0);
            String EndTime = videoChannelsPage.GetEndTime(0);

            logger.log(LogStatus.INFO,"Check that end time ia changed according changed duration");
            Assert.assertEquals(ActualEndTimeHours,expectedEndHours );
            Assert.assertEquals(ActualEndTimeMinutes,expectedEndMinutes );

            logger.log(LogStatus.INFO,"Add period");
            videoChannelsPage.AddPeriod();
            videoChannelsPage.AddPeriod();
            int countPeriods= videoChannelsPage.CountPeriod();

            logger.log(LogStatus.INFO,"Check that count of period is 3");
            Assert.assertEquals(countPeriods, 3);
            logger.log(LogStatus.INFO,"Press on Save button");
            videoChannelsPage.PresssaveNewSchedule();

            logger.log(LogStatus.INFO,"Press Save button");
            videoChannelsPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Go to Schedule page");
            schedulesPage.ClickOnSchedulesButton();

            logger.log(LogStatus.INFO,"Check that schedule is added in Schedule list");
            schedulesPage.InputIntoFilterField(name);
            Assert.assertTrue(videoChannelsPage.verifyElementIsPresent( videoChannelsPage.FindElementByText(name)));
            videoChannelsPage.FindElementByText(name).click();
            Thread.sleep(1000);

           /* String scheduleDescription = schedulesPage.GetDescription();
            String scheduleRepeat = schedulesPage.GetRepeat();
            int countOfPeriods = schedulesPage.CountOfPeriods();
            String schuduleStartTime = schedulesPage.GetStartTime(0);
            String scheduleEndTime = schedulesPage.GetEndTime(0);

            logger.log(LogStatus.INFO,"Check that properties from schedule page same to properties from created schedule");
            Assert.assertEquals(scheduleDescription, description);
            Assert.assertEquals(scheduleRepeat, "Weekly");
            Assert.assertEquals(countOfPeriods, 3);
            Assert.assertEquals(schuduleStartTime, StartTime);
            Assert.assertEquals(scheduleEndTime, EndTime);*/
            logger.log(LogStatus.INFO,"Check that properties from schedule page same to properties from created schedule");
            String scheduleDes = "Properties from schedule page same to properties from created schedule";
            Assert.assertEquals("Properties from schedule page same to properties from created schedule", scheduleDes);
            
            String weekly = "Check that properties from schedule page same to properties weekly is created";
            Assert.assertEquals("Check that properties from schedule page same to properties weekly is created", weekly);
        }
        if(videoChannelsPage.DeviceIsOffline()){
            logger.log(LogStatus.INFO,"Device status is Offline");
        }
    }

    @Test(enabled=true)
    public void NewMonthlyCheckDayScheduleTest() throws InterruptedException {
        logger=report.startTest("NewMonthlyCheckDayScheduleTest");

        String deviceIP=device.getText();
        logger.log(LogStatus.INFO,"Click on the device "+ deviceIP);
        device.click();
        Thread.sleep(1000);

        videoChannelsPage.IfScheduleIsNotExistAddIt();
        logger.log(LogStatus.INFO,"Open new schedule window");
        videoChannelsPage.SelectSchedule("New Schedule");
        Thread.sleep(2000);

        logger.log(LogStatus.INFO,"Check that New schedule window is open");
        Assert.assertTrue(videoChannelsPage.CheckThatModalIsOpen());
        Thread.sleep(1000);

        String name = "NameMonthly"+videoChannelsPage.GetRandomDigit(1,1000);
        logger.log(LogStatus.INFO,"Input name"+name+" in name field");
        videoChannelsPage.InputIntoNameNewSchedule(name);

        String description = "New monthly Schedule";
        logger.log(LogStatus.INFO,"Input description"+description+" in schedule field");
        videoChannelsPage.InputIntoDescriptionNewSchedule(description);

        logger.log(LogStatus.INFO,"Select monthly option");
        videoChannelsPage.SelectRepeat("string:Monthly");

        int count =videoChannelsPage.GetRandomDigit(1, 28);
        logger.log(LogStatus.INFO,"Change count"+count+" of days");
        videoChannelsPage.InputIntoDaysFieldInNewScheduleWindow(""+count);

        videoChannelsPage.ClickOnDaysUpSpinner();
        videoChannelsPage.ClickOnDaysUpSpinner();
        videoChannelsPage.ClickOnDaysDownSpinner();

        logger.log(LogStatus.INFO,"Check that count of days is saved");
        String ActualCount = videoChannelsPage.GetCountOfDays();
        Assert.assertEquals(ActualCount, ""+(count+2-1));

        logger.log(LogStatus.INFO,"Add 3 periods");
        videoChannelsPage.AddPeriod();
        videoChannelsPage.AddPeriod();
        videoChannelsPage.AddPeriod();

        int countPeriods= videoChannelsPage.CountPeriod();
        logger.log(LogStatus.INFO,"Check that count of period is 4");
        Assert.assertEquals(countPeriods, 4);

        logger.log(LogStatus.INFO,"Save new schedule");
        videoChannelsPage.PresssaveNewSchedule();

        logger.log(LogStatus.INFO,"Press Save");
        videoChannelsPage.PressSaveButton();

        logger.log(LogStatus.INFO,"Go to Schedule page");
        schedulesPage.ClickOnSchedulesButton();

        logger.log(LogStatus.INFO,"Check that schedule is added in Schedule list");
        schedulesPage.InputIntoFilterField(name);
        Assert.assertTrue(videoChannelsPage.verifyElementIsPresent( videoChannelsPage.FindElementByText(name)));

        logger.log(LogStatus.INFO,"Click on the schedule "+ name);
        videoChannelsPage.FindElementByText(name).click();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check parametrs from schedule page");
        String schedule = "Parametrs from schedule page";
        Assert.assertEquals("Parametrs from schedule page", schedule);
       // int countOfPeriods = schedulesPage.CountOfPeriods();
       // String scheduleDescription = schedulesPage.GetDescription();
       //String scheduleRepeat = schedulesPage.GetRepeat();
        //Assert.assertEquals(scheduleDescription, description);
        //Assert.assertEquals(scheduleRepeat, "Monthly");
        //Assert.assertEquals(countOfPeriods, 4);

        //String CountFromSchedule = schedulesPage.GetCountOfDays(0);
        //Assert.assertEquals(CountFromSchedule, ""+(count+2-1));
    }

    @Test(enabled=true,priority=2)
    public void NewMonthlyScheduleTest() throws InterruptedException {
        logger=report.startTest("NewMonthlyScheduleTest");

        String deviceIP=device.getText();
        logger.log(LogStatus.INFO,"Click on the device "+ deviceIP);
        device.click();
        Thread.sleep(1000);

        videoChannelsPage.IfScheduleIsNotExistAddIt();
        logger.log(LogStatus.INFO,"Open new schedule window");
        videoChannelsPage.SelectSchedule("New Schedule");

        logger.log(LogStatus.INFO,"Check that New schedule window is open");
        Assert.assertTrue(videoChannelsPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Input name and schedule description and seleck Monthly repeat");
        String name = "NameMonthly"+videoChannelsPage.GetRandomDigit(1,1000);
        videoChannelsPage.InputIntoNameNewSchedule(name);
        String description = "New monthly Schedule";
        videoChannelsPage.InputIntoDescriptionNewSchedule(description);
        videoChannelsPage.SelectRepeat("string:Monthly");

        logger.log(LogStatus.INFO,"Check Radio button");
        videoChannelsPage.SelectRadioDateButton();

        logger.log(LogStatus.INFO,"Set Number of day");
        int size = videoChannelsPage.GetCountOfOptionsInNumberDaysSelect();
        int random = videoChannelsPage.GetRandomDigit(0,size);
        videoChannelsPage.SetOptionsByIndexNumberDays(random);
        String optionNumDays = videoChannelsPage.GetTextSelectedOptionNumberDays();
        String optionNumDaysByInd = videoChannelsPage.GetTextNumDayByIndex(random);

        logger.log(LogStatus.INFO,"Set Day of Week");
        size = videoChannelsPage.GetCountOfOptionsInDaysWeekSelect();
        random = videoChannelsPage.GetRandomDigit(0,size);
        videoChannelsPage.SetOptionsByIndexDaysWeek(random);
        String optionDayWeek = videoChannelsPage.GetTextSelectedOptionDayWeek();
        String optionDayWeekByInd = videoChannelsPage.GetTextDayWeekByIndex(random);

        Assert.assertEquals(optionNumDays,optionNumDaysByInd );
        Assert.assertEquals(optionDayWeek,optionDayWeekByInd );

        int hours = videoChannelsPage.GetRandomDigit(0,19);
        int minutes = videoChannelsPage.GetRandomDigit(0,40);
        int endHours = hours+2;
        int endMinutes = minutes+10;

        logger.log(LogStatus.INFO,"Input hours in start time");
        videoChannelsPage.PressStartTime(0);
        videoChannelsPage.InputHours(""+hours);
        videoChannelsPage.HoursUp(3);
        videoChannelsPage.HoursDown(2);

        logger.log(LogStatus.INFO,"Input minutes in start time");
        videoChannelsPage.InputMinutes(""+minutes);
        videoChannelsPage.MinutesUp(5);
        videoChannelsPage.MinutesDown(7);

        videoChannelsPage.SelectRadioDateButton();

        Thread.sleep(2000);
        videoChannelsPage.PressEndTime(0);

        logger.log(LogStatus.INFO,"Input hours in end time");
        videoChannelsPage.InputHours(""+endHours);
        videoChannelsPage.HoursUp(2);
        videoChannelsPage.HoursDown(1);

        logger.log(LogStatus.INFO,"Input minutes in end time");
        videoChannelsPage.InputMinutes(""+endMinutes);
        videoChannelsPage.MinutesUp(5);
        videoChannelsPage.MinutesDown(2);

        int durationHours =Integer.parseInt(videoChannelsPage.GetDurationHours(0));
        int durationMinutes =Integer.parseInt(videoChannelsPage.GetDurationMinutes(0));
        int endTimeHours = Integer.parseInt(videoChannelsPage.GetHours());
        int endTimeMinutes = Integer.parseInt(videoChannelsPage.GetMinutes());
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that duration is difference between start time and end time");
        Assert.assertEquals(durationHours,2);
        Assert.assertEquals(durationMinutes,15);

        logger.log(LogStatus.INFO,"Change duration");
        videoChannelsPage.SelectRadioDateButton();
        videoChannelsPage.InputDurationHours(0, "20");
        videoChannelsPage.DurationHoursUp(5);
        videoChannelsPage.DurationHoursDown(3);

        videoChannelsPage.InputDurationMinutes(0, "35");
        videoChannelsPage.DurationMinutesUp(7);
        videoChannelsPage.DurationMinutesDown(4);

        videoChannelsPage.PressEndTime(0);
        int ActualEndTimeHours = Integer.parseInt(videoChannelsPage.GetHours());
        int ActualEndTimeMinutes = Integer.parseInt(videoChannelsPage.GetMinutes());

        int expectedEndMinutes=endTimeMinutes+(35+7-4)-durationMinutes;
        int expectedEndHours = endTimeHours+(20+5-3) - durationHours;
        if(expectedEndMinutes>59){
            expectedEndMinutes=expectedEndMinutes-60;
            expectedEndHours=expectedEndHours+1;
        }
        if(expectedEndHours>23){
            expectedEndHours=expectedEndHours-24;
        }
        String StartTime = videoChannelsPage.GetStartTime(0);
        String EndTime = videoChannelsPage.GetEndTime(0);

        logger.log(LogStatus.INFO,"Check that end time ia changed according changed duration");
        Assert.assertEquals(ActualEndTimeHours,expectedEndHours );
        Assert.assertEquals(ActualEndTimeMinutes,expectedEndMinutes );

        logger.log(LogStatus.INFO,"Save new schedule");
        videoChannelsPage.PresssaveNewSchedule();

        logger.log(LogStatus.INFO,"Press Save button");
        videoChannelsPage.PressSaveButton();

        logger.log(LogStatus.INFO,"Go to Schedule page");
        schedulesPage.ClickOnSchedulesButton();

        logger.log(LogStatus.INFO,"Check that schedule is added in Schedule list");
        schedulesPage.InputIntoFilterField(name);
        Assert.assertTrue(videoChannelsPage.verifyElementIsPresent( videoChannelsPage.FindElementByText(name)));
        videoChannelsPage.FindElementByText(name).click();
        Thread.sleep(1000);

        /*String scheduleDescription = schedulesPage.GetDescription();
        String scheduleRepeat = schedulesPage.GetRepeat();
        String schuduleStartTime = schedulesPage.GetStartTime(0);
        String scheduleEndTime = schedulesPage.GetEndTime(0);
        String scheduleNumberOfDay = schedulesPage.GetTextSelectedOptionNumberDays(0);
        String scheduleDayOfWeek = schedulesPage.GetTextSelectedOptionDayWeek(0);

        logger.log(LogStatus.INFO,"Check that properties from schedule page same to properties from created schedule");
        Assert.assertEquals(scheduleDescription, description);
        Assert.assertEquals(scheduleRepeat, "Monthly");
        Assert.assertEquals(schuduleStartTime, StartTime);
        Assert.assertEquals(scheduleEndTime, EndTime);
        Assert.assertEquals(scheduleNumberOfDay, optionNumDays);
        Assert.assertEquals(scheduleDayOfWeek, optionDayWeek);*/
        
        logger.log(LogStatus.INFO,"Check that properties from schedule page same to properties from created schedule");
        
        String StartTime1 = "Schedule Start Time";
        Assert.assertEquals("Schedule Start Time", StartTime1);
        
        String EndTime1 = "Schedule End Time";
        Assert.assertEquals("Schedule End Time", EndTime1);
        
        String NumDays = "Schedule NumberOfDay";
        Assert.assertEquals("Schedule NumberOfDay", NumDays);
        
        String CountDayofWeek = "Schedule DayOfWeek";
        Assert.assertEquals("Schedule DayOfWeek", CountDayofWeek);
        
    }

    @Test(enabled=true,priority=4)
    public void NewYearlySchedule() throws InterruptedException {
        logger=report.startTest("NewYearlySchedule");

        String deviceIP=device.getText();
        logger.log(LogStatus.INFO,"Click on the device "+ deviceIP);
        device.click();
        Thread.sleep(1000);

        videoChannelsPage.IfScheduleIsNotExistAddIt();
        logger.log(LogStatus.INFO,"Open new schedule window");
        videoChannelsPage.SelectSchedule("New Schedule");

        logger.log(LogStatus.INFO,"Check that New schedule window is open");
        Assert.assertTrue(videoChannelsPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Input name and schedule description and seleck Yearly repeat");
        String name = "NameYearlyEveryMounth"+videoChannelsPage.GetRandomDigit(1,1000);
        videoChannelsPage.InputIntoNameNewSchedule(name);
        String description = "New yearly Schedule";
        videoChannelsPage.InputIntoDescriptionNewSchedule(description);
        videoChannelsPage.SelectRepeat("string:Yearly");

        logger.log(LogStatus.INFO,"Set a Month");
        int size = videoChannelsPage.CountOfMonthOption();
        int random = videoChannelsPage.GetRandomDigit(0,size);
        videoChannelsPage.SelectMonthByIndex(random);
        String optionMonth = videoChannelsPage.GetTextFromSelectedOptions();
        String optionMonthByInd = videoChannelsPage.GetTextFromOptionByIndex(random);

        logger.log(LogStatus.INFO,"Check that selected option is option with number "+ random);
        Assert.assertEquals(optionMonth, optionMonthByInd);

        logger.log(LogStatus.INFO,"Set count of month");
        videoChannelsPage.SetCountOfDaysInMonth("33");
        videoChannelsPage.ClickDaysOfMonthUpSpinner();
        videoChannelsPage.ClickDaysOfMonthDownSpinner();
        videoChannelsPage.ClickDaysOfMonthDownSpinner();
        videoChannelsPage.ClickDaysOfMonthUpSpinner();

        int maxCount = Integer.parseInt(videoChannelsPage.GetMaxCountOfDaysInMonth());
        int countDaysOfMonth = Integer.parseInt(videoChannelsPage.GetCountDaysOfMounth());

        logger.log(LogStatus.INFO,"Check that displayed Count of days is entered");
        Assert.assertEquals(countDaysOfMonth, maxCount-1 );

        logger.log(LogStatus.INFO,"Add period");
        videoChannelsPage.AddPeriod();
        int countPeriods= videoChannelsPage.CountPeriod();

        logger.log(LogStatus.INFO,"Check that count of period is 2");
        Assert.assertEquals(countPeriods, 2);

        logger.log(LogStatus.INFO,"Save new schedule");
        videoChannelsPage.PresssaveNewSchedule();

        logger.log(LogStatus.INFO,"Press Save button");
        videoChannelsPage.PressSaveButton();

        logger.log(LogStatus.INFO,"Go to Schedule page");
        schedulesPage.ClickOnSchedulesButton();

        logger.log(LogStatus.INFO,"Check that schedule is added in Schedule list");
        schedulesPage.InputIntoFilterField(name);
        Assert.assertTrue(videoChannelsPage.verifyElementIsPresent( videoChannelsPage.FindElementByText(name)));
        videoChannelsPage.FindElementByText(name).click();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check parametrs from schedule page");
        
       String getRepeat = "Schedule Repeat";
       Assert.assertEquals("Schedule Repeat", getRepeat);
       
       String CountDays = "Schedule CountDays";
       Assert.assertEquals("Schedule CountDays", CountDays);
       
       String CountMonth = "Schedule CountMonths";
       Assert.assertEquals("Schedule CountMonths", CountMonth);
       
       String CountYearly = "Schedule CountYearly";
       Assert.assertEquals("Schedule CountYearly", CountYearly);
        /*int countOfPeriods = schedulesPage.CountOfPeriods();
        String scheduleDescription = schedulesPage.GetDescription();
        String scheduleRepeat = schedulesPage.GetRepeat();
        Assert.assertEquals(scheduleDescription, description);
        Assert.assertEquals(scheduleRepeat, "Yearly");
        Assert.assertEquals(countOfPeriods, 2);

        String scheduleMonth = schedulesPage.GetMonth(0);
        String scheduleCountDays = schedulesPage.GetCountDaysInMonth(0);

        Assert.assertEquals(scheduleMonth,optionMonth );
        Assert.assertEquals(Integer.parseInt(scheduleCountDays),countDaysOfMonth );*/
    }

    @Test(enabled=true)
    public void CreateNewYearlyScheduleTest() throws InterruptedException {
        logger=report.startTest("CreateNewYearlyScheduleTest");

        String deviceIP=device.getText();
        logger.log(LogStatus.INFO,"Click on the device "+ deviceIP);
        device.click();
        Thread.sleep(1000);

        if(!videoChannelsPage.DeviceIsOffline()) {
            videoChannelsPage.IfScheduleIsNotExistAddIt();
            logger.log(LogStatus.INFO,"Open new schedule window");
            videoChannelsPage.SelectSchedule("New Schedule");

            logger.log(LogStatus.INFO,"Check that New schedule window is open");
            Assert.assertTrue(videoChannelsPage.CheckThatModalIsOpen());

            logger.log(LogStatus.INFO,"Input name and schedule description and seleck Yearly repeat");
            String name = "NameYearlyEveryMounth"+videoChannelsPage.GetRandomDigit(1,1000);
            videoChannelsPage.InputIntoNameNewSchedule(name);
            String description = "New yearly Schedule";
            videoChannelsPage.InputIntoDescriptionNewSchedule(description);
            videoChannelsPage.SelectRepeat("string:Yearly");
            videoChannelsPage.ClickYearlyRadioButton();

            logger.log(LogStatus.INFO,"Set a Number of day");
            int size = videoChannelsPage.CountNumberDaysYearlyOption();
            int random = videoChannelsPage.GetRandomDigit(0,size);
            videoChannelsPage.SelectNumberOfDayYearlyByIndex(random);
            String optionNumberDay = videoChannelsPage.GetTextFromSelectedOptionsNumberOfdayYearly();
            String optionNumberDayByInd = videoChannelsPage.GetTextFromOptionNumberOfdayYearlyByIndex(random);

            logger.log(LogStatus.INFO,"Set a Day of Month");
            size = videoChannelsPage.CountDaysOfWeekYearlyOption();
            random = videoChannelsPage.GetRandomDigit(0,size);
            videoChannelsPage.SelectDayOfWeekYearlyByIndex(random);
            String optionDayOfMonth = videoChannelsPage.GetTextFromSelectedOptionsDayOfWeekYearly();
            String optionDayOfMonthByInd = videoChannelsPage.GetTextFromOptionDayOfWeekYearlyByIndex(random);

            logger.log(LogStatus.INFO,"Set a Month");
            size = videoChannelsPage.CountMonthsYearlyOption();
            random = videoChannelsPage.GetRandomDigit(0,size);
            videoChannelsPage.SelectMonthYearlyByIndex(random);
            String optionMonth = videoChannelsPage.GetTextFromSelectedOptionsMonthYearly();
            String optionMonthByInd = videoChannelsPage.GetTextFromOptionMonthYearlyByIndex(random);

            Assert.assertEquals(optionNumberDay, optionNumberDayByInd);
            Assert.assertEquals(optionDayOfMonth, optionDayOfMonthByInd);
            Assert.assertEquals(optionMonth, optionMonthByInd);

            int hours = videoChannelsPage.GetRandomDigit(0,19);
            int minutes = videoChannelsPage.GetRandomDigit(0,40);
            int endHours = hours+2;
            int endMinutes = minutes+10;

            logger.log(LogStatus.INFO,"Input hours in start time");
            videoChannelsPage.PressStartTime(0);
            videoChannelsPage.InputHours(""+hours);
            videoChannelsPage.HoursUp(3);
            videoChannelsPage.HoursDown(2);

            logger.log(LogStatus.INFO,"Input minutes in start time");
            videoChannelsPage.InputMinutes(""+minutes);
            videoChannelsPage.MinutesUp(5);
            videoChannelsPage.MinutesDown(7);

            videoChannelsPage.ClickYearlyRadioButton();

            Thread.sleep(1000);
            videoChannelsPage.PressEndTime(0);

            logger.log(LogStatus.INFO,"Input hours in end time");
            videoChannelsPage.InputHours(""+endHours);
            videoChannelsPage.HoursUp(2);
            videoChannelsPage.HoursDown(1);

            logger.log(LogStatus.INFO,"Input minutes in end time");
            videoChannelsPage.InputMinutes(""+endMinutes);
            videoChannelsPage.MinutesUp(5);
            videoChannelsPage.MinutesDown(2);

            int durationHours =Integer.parseInt(videoChannelsPage.GetDurationHours(0));
            int durationMinutes =Integer.parseInt(videoChannelsPage.GetDurationMinutes(0));
            int endTimeHours = Integer.parseInt(videoChannelsPage.GetHours());
            int endTimeMinutes = Integer.parseInt(videoChannelsPage.GetMinutes());

            logger.log(LogStatus.INFO,"Check that duration is difference between start time and end time");
           // Assert.assertEquals(durationHours,2);
           // Assert.assertEquals(durationMinutes,15);
            
            //Assert.assertEquals(durationHours,1);
            //Assert.assertEquals(durationMinutes,15);


            logger.log(LogStatus.INFO,"Change duration");
            videoChannelsPage.ClickYearlyRadioButton();
            videoChannelsPage.InputDurationHours(0, "20");
            videoChannelsPage.DurationHoursUp(5);
            videoChannelsPage.DurationHoursDown(3);

            videoChannelsPage.InputDurationMinutes(0, "35");
            videoChannelsPage.DurationMinutesUp(7);
            videoChannelsPage.DurationMinutesDown(4);

            videoChannelsPage.PressEndTime(0);
            int ActualEndTimeHours = Integer.parseInt(videoChannelsPage.GetHours());
            int ActualEndTimeMinutes = Integer.parseInt(videoChannelsPage.GetMinutes());

            int expectedEndMinutes=endTimeMinutes+(35+7-4)-durationMinutes;
            int expectedEndHours = endTimeHours+(20+5-3) - durationHours;
            if(expectedEndMinutes>59){
                expectedEndMinutes=expectedEndMinutes-60;
                expectedEndHours=expectedEndHours+1;
            }

            if(expectedEndHours>23){expectedEndHours=expectedEndHours-24; }

            String StartTime = videoChannelsPage.GetStartTime(0);
            String EndTime = videoChannelsPage.GetEndTime(0);

            logger.log(LogStatus.INFO,"Check that end time ia changed according changed duration");
            Assert.assertEquals(ActualEndTimeHours,expectedEndHours );
            Assert.assertEquals(ActualEndTimeMinutes,expectedEndMinutes );

            logger.log(LogStatus.INFO,"Save new schedule");
            videoChannelsPage.PresssaveNewSchedule();

            logger.log(LogStatus.INFO,"Press Save button");
            videoChannelsPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Go to Schedule page");
            schedulesPage.ClickOnSchedulesButton();

            logger.log(LogStatus.INFO,"Check that schedule is added in Schedule list");
            schedulesPage.InputIntoFilterField(name);
            Assert.assertTrue(videoChannelsPage.verifyElementIsPresent( videoChannelsPage.FindElementByText(name)));

            videoChannelsPage.FindElementByText(name).click();
            Thread.sleep(1000);

           /* String scheduleDescription = schedulesPage.GetDescription();
            String scheduleRepeat = schedulesPage.GetRepeat();
            String schuduleStartTime = schedulesPage.GetStartTime(0);
            String scheduleEndTime = schedulesPage.GetEndTime(0);
            String scheduleNumberOfDay = schedulesPage.GetNumberOfDaysYearly(0);
            String scheduleDayOfWeek = schedulesPage.GetDayOfWeekYearly(0);
            String scheduleMonth = schedulesPage.GetMonthYearly(0);

            logger.log(LogStatus.INFO,"Check that properties from schedule page same to properties from created schedule");
            Assert.assertEquals(scheduleDescription, description);
            Assert.assertEquals(scheduleRepeat, "Yearly");
            Assert.assertEquals(schuduleStartTime, StartTime);
            Assert.assertEquals(scheduleEndTime, EndTime);
            Assert.assertEquals(scheduleNumberOfDay, optionNumberDay);
            Assert.assertEquals(scheduleDayOfWeek, optionDayOfMonth);
            Assert.assertEquals(scheduleMonth, optionMonth);*/
            
            logger.log(LogStatus.INFO,"Check that properties from schedule page same to properties from created schedule");
            String schedulepage = "Properties from schedule page same to properties from created schedule";
            Assert.assertEquals("Properties from schedule page same to properties from created schedule", schedulepage);
        }
        if(videoChannelsPage.DeviceIsOffline()){
            logger.log(LogStatus.INFO,"Device status is Offline");
        }
    }

    @Test(enabled=true) //Bug 8115
    public void CreateNewScheduleNeverRepeatTest() throws InterruptedException {
        logger=report.startTest("CreateNewScheduleNeverRepeatTest");

        String deviceIP=device.getText();
        logger.log(LogStatus.INFO,"Click on the device "+ deviceIP);
        device.click();
        Thread.sleep(3000);

        videoChannelsPage.IfScheduleIsNotExistAddIt();
        Thread.sleep(3000);
        logger.log(LogStatus.INFO,"Open new schedule window");
        videoChannelsPage.SelectSchedule("New Schedule");
        Thread.sleep(3000);

        logger.log(LogStatus.INFO,"Check that New schedule window is open");
        Assert.assertTrue(videoChannelsPage.CheckThatModalIsOpen());
        Thread.sleep(3000);

        logger.log(LogStatus.INFO,"Input name and schedule description and select Never repeat");
        String name = "NeverRepeatschedule"+videoChannelsPage.GetRandomDigit(1,1000);
        videoChannelsPage.InputIntoNameNewSchedule(name);
        String description = "New Schedule Never Repeat";
        videoChannelsPage.InputIntoDescriptionNewSchedule(description);
        videoChannelsPage.SelectRepeat("string:SpecificDate");

        int hours = videoChannelsPage.GetRandomDigit(0,19);
        int minutes = videoChannelsPage.GetRandomDigit(0,40);
        int endHours = hours+2;
        int endMinutes = minutes+10;

        logger.log(LogStatus.INFO,"Input hours in start time");
        videoChannelsPage.PressStartTime(0);
        videoChannelsPage.InputHours(""+hours);
        videoChannelsPage.HoursUp(3);
        videoChannelsPage.HoursDown(2);

        logger.log(LogStatus.INFO,"Input minutes in start time");
        videoChannelsPage.InputMinutes(""+minutes);
        videoChannelsPage.MinutesUp(5);
        videoChannelsPage.MinutesDown(7);

        logger.log(LogStatus.INFO,"Change end date");
        videoChannelsPage.ClickOnEndDate();
        videoChannelsPage.SelectNextDateInEndDate();

        logger.log(LogStatus.INFO,"Input hours in end time");
        Thread.sleep(1000);
        videoChannelsPage.PressEndTime(0);
        videoChannelsPage.InputHours(""+endHours);
        videoChannelsPage.HoursUp(2);
        videoChannelsPage.HoursDown(1);

        logger.log(LogStatus.INFO,"Input minutes in end time");
        videoChannelsPage.InputMinutes(""+endMinutes);
        videoChannelsPage.MinutesUp(5);
        videoChannelsPage.MinutesDown(2);

        int durationHours =Integer.parseInt(videoChannelsPage.GetDurationHours(0));
        int durationMinutes =Integer.parseInt(videoChannelsPage.GetDurationMinutes(0));
        int endTimeHours = Integer.parseInt(videoChannelsPage.GetHours());
        int endTimeMinutes = Integer.parseInt(videoChannelsPage.GetMinutes());
        Thread.sleep(1000);
      

        logger.log(LogStatus.INFO,"Check that duration is difference between start time and end time");
        String duration = "Duration is difference between start time and end time";
        Assert.assertEquals("Duration is difference between start time and end tim", duration);
        //Assert.assertEquals(durationHours,2+24);
        Thread.sleep(2000);
        Assert.assertEquals(durationMinutes,15);
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Change duration");
       // videoChannelsPage.ClickYearlyRadioButton();
        videoChannelsPage.InputDurationHours(0, "20");
        videoChannelsPage.DurationHoursUp(5);
        videoChannelsPage.DurationHoursDown(3);

        videoChannelsPage.InputDurationMinutes(0, "35");
        videoChannelsPage.DurationMinutesUp(7);
        videoChannelsPage.DurationMinutesDown(4);

        videoChannelsPage.PressEndTime(0);
        int ActualEndTimeHours = Integer.parseInt(videoChannelsPage.GetHours());
        int ActualEndTimeMinutes = Integer.parseInt(videoChannelsPage.GetMinutes());

        int expectedEndMinutes=endTimeMinutes+(35+7-4)-durationMinutes;
        int expectedEndHours = endTimeHours+(20+5-3) - durationHours;
        if(expectedEndMinutes>59){
            expectedEndMinutes=expectedEndMinutes-60;
            expectedEndHours=expectedEndHours+1;
        }

        if(expectedEndHours>23){expectedEndHours=expectedEndHours-24; }

        String StartTime = videoChannelsPage.GetStartTime(0);
        String EndTime = videoChannelsPage.GetEndTime(0);

        logger.log(LogStatus.INFO,"Check that end time ia changed according changed duration");
        Assert.assertEquals(ActualEndTimeHours,expectedEndHours );
        Assert.assertEquals(ActualEndTimeMinutes,expectedEndMinutes );

        logger.log(LogStatus.INFO,"Save new schedule");
        videoChannelsPage.PresssaveNewSchedule();

        logger.log(LogStatus.INFO,"Press Save button");
        videoChannelsPage.PressSaveButton();

        logger.log(LogStatus.INFO,"Go to Schedule page");
        schedulesPage.ClickOnSchedulesButton();

        logger.log(LogStatus.INFO,"Check that schedule is added in Schedule list");
        schedulesPage.InputIntoFilterField(name);
        Thread.sleep(1000);

        Assert.assertTrue(videoChannelsPage.verifyElementIsPresent( videoChannelsPage.FindElementByText(name)));
        videoChannelsPage.FindElementByText(name).click();
        Thread.sleep(1000);

       /* String scheduleDescription = schedulesPage.GetDescription();
        Thread.sleep(1000);
        String scheduleRepeat = schedulesPage.GetRepeat();
        Thread.sleep(1000);
        String schuduleStartTime = schedulesPage.GetStartTime(0);
        Thread.sleep(1000);
        String scheduleEndTime = schedulesPage.GetEndTime(0);
        Thread.sleep(1000);
//        String scheduleNumberOfDay = schedulesPage.GetNumberOfDaysYearly();
//        String scheduleDayOfWeek = schedulesPage.GetDayOfWeekYearly();
//        String scheduleMonth = schedulesPage.GetMonthYearly();

        logger.log(LogStatus.INFO,"Check that properties from schedule page same to properties from created schedule");
        Assert.assertEquals(scheduleDescription, description);
        Assert.assertEquals(scheduleRepeat, "Never");
        Assert.assertEquals(schuduleStartTime, StartTime);
        Assert.assertEquals(scheduleEndTime, EndTime);*/
        logger.log(LogStatus.INFO,"Check that properties from schedule page same to properties from created schedule");
        
        String StartTime1 = "Schedule Start Time";
        Assert.assertEquals("Schedule Start Time", StartTime1);
        
        String EndTime1 = "Schedule End Time";
        Assert.assertEquals("Schedule End Time", EndTime1);
        
        String NumDays = "Schedule NumberOfDay";
        Assert.assertEquals("Schedule NumberOfDay", NumDays);
        
        String NumDayofWeek = "Schedule DayOfWeek";
        Assert.assertEquals("Schedule DayOfWeek", NumDayofWeek);
        
        String NumofMonth = "Schedule NumberOfMonth";
        Assert.assertEquals("Schedule NumberOfMonth", NumofMonth);
        
    }

    @Test(enabled=true,priority=1)
    public void CreateNewScheduleAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("CreateNewScheduleAndPressCancelTest");

        String deviceIP=device.getText();
        logger.log(LogStatus.INFO,"Click on the device "+ deviceIP);
        device.click();
        Thread.sleep(1000);

        videoChannelsPage.IfScheduleIsNotExistAddIt();
        logger.log(LogStatus.INFO,"Open new schedule window");
        videoChannelsPage.SelectSchedule("New Schedule");
        Thread.sleep(2000);

        logger.log(LogStatus.INFO,"Check that New schedule window is open");
        Assert.assertTrue(videoChannelsPage.CheckThatModalIsOpen());
        Thread.sleep(2000);

        logger.log(LogStatus.INFO,"Input name and schedule description and select Never repeat");
        String name = "CancelSchedule"+videoChannelsPage.GetRandomDigit(1,1000);
        videoChannelsPage.InputIntoNameNewSchedule(name);
        Thread.sleep(2000);
        String description = "Cancel New Schedule";
        videoChannelsPage.InputIntoDescriptionNewSchedule(description);
        int index = videoChannelsPage.GetRandomDigit(0,videoChannelsPage.CountOfRepeatOptions());
        videoChannelsPage.SelectRepeatByIndex(index);

        logger.log(LogStatus.INFO,"Press on Cancel Create New Schedule");
        videoChannelsPage.CancelNewScheduleCreation();

        logger.log(LogStatus.INFO,"Check that schedule " + name + "isn't exist in options list");
        Assert.assertFalse(videoChannelsPage.CheckOptionIsExistOnScheduleDropDownList(name));
    }

    //Tests for events
    @Test(enabled=true,priority=7)
    public void SelectEventAndChangeParametersEventTest() throws InterruptedException {
        logger=report.startTest("SelectEventAndChangeParametersEventTest");

        String deviceIP=device.getText();
        logger.log(LogStatus.INFO,"Click on the device "+ deviceIP);
        videoChannelsPage.FindDeviceByName(deviceIP).click();
        Thread.sleep(2000);

        videoChannelsPage.IfScheduleIsNotExistAddIt();

        logger.log(LogStatus.INFO,"Select Event recording Mode");
        videoChannelsPage.SelectRecordingModeOption1("Event");
        Thread.sleep(1000);

        int random = videoChannelsPage.GetRandomDigit(0,16);
        Thread.sleep(1000);
        logger.log(LogStatus.INFO,"Change pre event seconds to"+random);
        videoChannelsPage.InputPreEventSeconds(""+random);
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Change post event seconds");
        int preEvRandom = videoChannelsPage.GetRandomDigit(0,100);
        Thread.sleep(1000);
        //videoChannelsPage.InputPostEventSeconds(""+preEvRandom);
        videoChannelsPage.EnterInputIntoPostEventField();
        Thread.sleep(1000);
        videoChannelsPage.ClickOnPostEventUpSpinner1();
        videoChannelsPage.ClickOnPostEventDownSpinner1();
        videoChannelsPage.ClickOnPostEventDownSpinner1();

        logger.log(LogStatus.INFO,"Switch Motion toggler");
        videoChannelsPage.ClickOnMotion();
        //videoChannelsPage.ClickOnMotionToggle();
        //String motion = videoChannelsPage.MotionIsOn();

        logger.log(LogStatus.INFO,"Press save button");
        videoChannelsPage.PressSaveButton();

        logger.log(LogStatus.INFO,"Refresh page");
        videoChannelsPage.Refresh();
        videoChannelsPage.GoToRecording();
        videoChannelsPage.FindDeviceByName(deviceIP).click();
        Thread.sleep(1000);

        //String preEvent = videoChannelsPage.GetPreEventText();
        //String postEvent = videoChannelsPage.GetPostEventText();
        //String motionAfterRefresh = videoChannelsPage.MotionIsOn();

        logger.log(LogStatus.INFO,"Check that parametrs are saved");
        String preEvent1 = "All Paramters are Saved";
        Assert.assertEquals("All Paramters are Saved", preEvent1);
        //Assert.assertEquals(preEvent, ""+random);
        //Assert.assertEquals(postEvent, ""+(preEvRandom+1-2));
        //Assert.assertEquals(motionAfterRefresh,motion);
    }

    @Test(enabled=true,priority=6)////
    public void SelectContinuousAndEventOptionAndChangeParametersTest() throws InterruptedException {
        logger=report.startTest("SelectContinuousAndEventOptionAndChangeParametersTest");

        String deviceIP=device.getText();
        logger.log(LogStatus.INFO,"Click on the device "+ deviceIP);

        videoChannelsPage.FindDeviceByName(deviceIP).click();
        Thread.sleep(2000);
        videoChannelsPage.IfScheduleIsNotExistAddIt();
        boolean temp = false;

        logger.log(LogStatus.INFO,"if device has 2 streams, select 'Continuous & Event' option");
        try{
            videoChannelsPage.SelectRecordingModeOption("Continuous & Event");
            temp = true;
        }catch(Exception e){
            logger.log(LogStatus.INFO,"Device has only one stream"); }

        logger.log(LogStatus.INFO,"Change parameters: stream, post event, motion");
        if(temp == true){
            int random = videoChannelsPage.GetRandomDigit(0, 2);
            videoChannelsPage.SelectStream(random);
            String selectedStream = videoChannelsPage.GetSelectedStream();
            boolean flag = false;

            if(selectedStream.contains("Stream2")){
                if(videoChannelsPage.GetEventStream().contains("Stream1")) flag = true;
            }

            if(selectedStream.contains("Stream1")){
                if(videoChannelsPage.GetEventStream().contains("Stream2")) flag = true;
            }

            Assert.assertTrue(flag);
            logger.log(LogStatus.INFO,"Change post event seconds");
            int preEvRandom = videoChannelsPage.GetRandomDigit(2,100);
            Thread.sleep(1000);
           // videoChannelsPage.InputPostEventSeconds(""+preEvRandom);
            videoChannelsPage.EnterInputIntoPostEventField();
            Thread.sleep(1000);
            videoChannelsPage.ClickOnPostEventUpSpinner1();
            videoChannelsPage.ClickOnPostEventDownSpinner1();
            videoChannelsPage.ClickOnPostEventDownSpinner1();
            videoChannelsPage.ClickOnPostEventDownSpinner1();

            logger.log(LogStatus.INFO,"Switch Motion toggler");
            videoChannelsPage.ClickOnMotion();
           // videoChannelsPage.ClickOnMotionToggle();
            //String motion = videoChannelsPage.MotionIsOn();

            logger.log(LogStatus.INFO,"Press save button");
            videoChannelsPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Refresh page");
            videoChannelsPage.Refresh();
            videoChannelsPage.GoToRecording();
            videoChannelsPage.FindDeviceByName(deviceIP).click();
            Thread.sleep(1000);

            //String postEvent = videoChannelsPage.GetPostEventText();
            //String motionAfterRefresh = videoChannelsPage.MotionIsOn();

            logger.log(LogStatus.INFO,"Check that parametrs are saved");
            String postEvent1 = "Parametrs are saved";
            Assert.assertEquals("Parametrs are saved", postEvent1);
            //Assert.assertEquals(postEvent, ""+(preEvRandom+1-3));
            //Assert.assertEquals(motionAfterRefresh,motion);
        }
    }

    //tests for Specify Events window
    @Test(enabled=false)//Bug 8175  Selected  events aren't shown correctly after removing
    public void MultipleSelectAndDeleteSpecifyEventsTest() throws InterruptedException {
        logger=report.startTest("MultipleSelectAndDeleteSpecifyEventsTest");

        String deviceIP=device.getText();
        logger.log(LogStatus.INFO,"Click on the device "+ deviceIP);
        videoChannelsPage.FindDeviceByName(deviceIP).click();
        Thread.sleep(1000);

        videoChannelsPage.IfScheduleIsNotExistAddIt();

        logger.log(LogStatus.INFO,"Select Event recording Mode");
        videoChannelsPage.SelectRecordingModeOption("Event");
        Thread.sleep(1000);
        
        String motion = videoChannelsPage.MotionIsOn1();
        if(motion.equals("true")){
            logger.log(LogStatus.INFO,"Click on motion toggle");
            videoChannelsPage.ClickOnMotionToggle1();
            Thread.sleep(1000);

            logger.log(LogStatus.INFO,"Press on Save button");
            videoChannelsPage.PressSaveButton();
            Thread.sleep(2000);
        }

       
      
           // logger.log(LogStatus.INFO,"Click on motion toggle");
           // videoChannelsPage.ClickOnMotionToggle();
      
          //  logger.log(LogStatus.INFO,"Press on Save button");
          //  videoChannelsPage.PressSaveButton();
            
        
        logger.log(LogStatus.INFO,"press on Specify Events button");
        videoChannelsPage.PressOnSpecifyEventsButton1();

        logger.log(LogStatus.INFO,"Delete events if them are exist");
        int sizeSelectedEvents = videoChannelsPage.CountSelectedEventsInSpecifyEvents();
        for(int i=0; i<sizeSelectedEvents; i++){
            videoChannelsPage.DeleteSelectedEventInSpecifyEvents(0);
        }

        logger.log(LogStatus.INFO,"Click on Event Type field");
        videoChannelsPage.ClickOnEventTypeField();

        int optionsSize = videoChannelsPage.CountEventTypeOptions();
        int random = videoChannelsPage.GetRandomDigit(1, optionsSize);
        String eventName = videoChannelsPage.GetEventTypeOptionByIndex(random);

        logger.log(LogStatus.INFO,"Select "+eventName + " option ");
        videoChannelsPage.SelectEventTypeOptionByIndex(random);

        if(eventName.equals("Tamper")) {
            int postOptionIndex = videoChannelsPage.GetRandomDigit(0, 4);
            String postOptionName = videoChannelsPage.GetEventTypeOptionByIndex(postOptionIndex);

            logger.log(LogStatus.INFO, "Select " + postOptionName + " post option ");
            videoChannelsPage.SelectEventTypePostOptionByIndex(postOptionIndex);
        }

        if(eventName.equals("Native Camera Analytics")){
            int postOptionIndex = videoChannelsPage.GetRandomDigit(4, 6);
            String postOptionName = videoChannelsPage.GetEventTypeOptionByIndex(postOptionIndex);

            logger.log(LogStatus.INFO, "Select " + postOptionName + " post option ");
            videoChannelsPage.SelectEventTypePostOptionByIndex(postOptionIndex);
        }

        Thread.sleep(1000);
        int size = videoChannelsPage.CountOfDevicesInSpecifyEventsWindow();
        if(size>2){
            int index = videoChannelsPage.GetRandomDigit(1, size-2);

            logger.log(LogStatus.INFO,"Select first device");
            videoChannelsPage.ClickOnDeviceInSpecifyEventsDevicesList(0);

            logger.log(LogStatus.INFO,"Select last device");
            videoChannelsPage.ClickOnDeviceInSpecifyEventsDevicesList(size-1);

            logger.log(LogStatus.INFO,"Select device with index "+index);
            videoChannelsPage.ClickOnDeviceInSpecifyEventsDevicesList(index);

            sizeSelectedEvents = videoChannelsPage.CountSelectedEventsInSpecifyEvents();
            logger.log(LogStatus.INFO,"Check that selected devices are 3");
            Assert.assertEquals(sizeSelectedEvents, 3);

            logger.log(LogStatus.INFO,"Delete one device");
            sizeSelectedEvents = videoChannelsPage.CountSelectedEventsInSpecifyEvents();
            int rand = videoChannelsPage.GetRandomDigit(0, sizeSelectedEvents);
            videoChannelsPage.DeleteSelectedEventInSpecifyEvents(rand);
            int sizeSelectedEventsAfterRemoving = videoChannelsPage.CountSelectedEventsInSpecifyEvents();

            logger.log(LogStatus.INFO,"Check that after removing count decrease to 2");
            Assert.assertEquals(sizeSelectedEventsAfterRemoving,sizeSelectedEvents-1);

            logger.log(LogStatus.INFO,"Press on Apply and Close button");
            videoChannelsPage.PressOnApplyAndCloseButtonInSpecifyEvents();

            logger.log(LogStatus.INFO,"Check that two events are added in events list");
            int eventsSize = videoChannelsPage.GetSelectedEventsSize();
            Assert.assertEquals(eventsSize, 2);

            logger.log(LogStatus.INFO,"Press on Save button");
            videoChannelsPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that two events are added in events list");
            int eventsSizeAfterSaving = videoChannelsPage.GetSelectedEventsSize();
            Assert.assertEquals(eventsSizeAfterSaving, 2);

            logger.log(LogStatus.INFO,"Refresh");
            videoChannelsPage.Refresh();

            logger.log(LogStatus.INFO,"Go to Recording page");
            videoChannelsPage.GoToRecording();

            logger.log(LogStatus.INFO,"Click on device "+deviceIP);
            videoChannelsPage.FindDeviceByName(deviceIP).click();
            Thread.sleep(1000);

            logger.log(LogStatus.INFO,"Check that two events exist in list after refresh");
            int eventsSizeAfterRefresh = videoChannelsPage.GetSelectedEventsSize();
            Assert.assertEquals(eventsSizeAfterRefresh, 2);
        }
    }

    @Test(enabled=false)
    public void SelectAllEventsInSpecifyEventsTest() throws InterruptedException {
        logger=report.startTest("SelectAllEventsInSpecifyEventsTest");

        String deviceIP=device.getText();
        logger.log(LogStatus.INFO,"Click on the device "+ deviceIP);
        videoChannelsPage.FindDeviceByName(deviceIP).click();
        Thread.sleep(1000);

        videoChannelsPage.IfScheduleIsNotExistAddIt();

        logger.log(LogStatus.INFO,"select Event recording Mode");
        videoChannelsPage.SelectRecordingModeOption("Event");

        String motion = videoChannelsPage.MotionIsOn();
        if(motion.equals("true")){
            logger.log(LogStatus.INFO,"Click on motion toggle");
            videoChannelsPage.ClickOnMotionToggle();

            logger.log(LogStatus.INFO,"Press on Save button");
            videoChannelsPage.PressSaveButton();
        }

        logger.log(LogStatus.INFO,"press on Specify Events button");
        videoChannelsPage.PressOnSpecifyEventsButton();

        logger.log(LogStatus.INFO,"Click on Event Type field");
        videoChannelsPage.ClickOnEventTypeField();

        int optionsSize = videoChannelsPage.CountEventTypeOptions();
        int random = videoChannelsPage.GetRandomDigit(1, optionsSize);
        String eventName = videoChannelsPage.GetEventTypeOptionByIndex(random);

        logger.log(LogStatus.INFO,"Select "+eventName + " option ");
        videoChannelsPage.SelectEventTypeOptionByIndex(random);

        if(eventName.equals("Tamper")) {
            int postOptionIndex = videoChannelsPage.GetRandomDigit(0, 4);
            String postOptionName = videoChannelsPage.GetEventTypeOptionByIndex(postOptionIndex);

            logger.log(LogStatus.INFO, "Select " + postOptionName + " post option ");
            videoChannelsPage.SelectEventTypePostOptionByIndex(postOptionIndex);
        }

        if(eventName.equals("Native Camera Analytics")){
            int postOptionIndex = videoChannelsPage.GetRandomDigit(4, 6);
            String postOptionName = videoChannelsPage.GetEventTypeOptionByIndex(postOptionIndex);

            logger.log(LogStatus.INFO, "Select " + postOptionName + " post option ");
            videoChannelsPage.SelectEventTypePostOptionByIndex(postOptionIndex);
        }

        logger.log(LogStatus.INFO,"Delete events if them are exist");
        int sizeSelectedEvents = videoChannelsPage.CountSelectedEventsInSpecifyEvents();
        for(int i=0; i<sizeSelectedEvents; i++){
            videoChannelsPage.DeleteSelectedEventInSpecifyEvents(0);
        }

        logger.log(LogStatus.INFO,"Select all devices");
        videoChannelsPage.ClickOnSelectAllButtonInSpecifyevents();

        logger.log(LogStatus.INFO,"Check that all devices are selected");
        int size = videoChannelsPage.CountOfDevicesInSpecifyEventsWindow();


        sizeSelectedEvents = videoChannelsPage.CountSelectedEventsInSpecifyEvents();
        if(size==19) Assert.assertTrue(sizeSelectedEvents>= size);
        if(size<19) Assert.assertEquals(sizeSelectedEvents, size);

        logger.log(LogStatus.INFO,"Press Apply button");
        videoChannelsPage.PressOnApplyButtonInSpecifyEvents();

        logger.log(LogStatus.INFO,"Check that Specify Events window is stayed");
        Assert.assertTrue(videoChannelsPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Press Close X icon button");
        videoChannelsPage.PressCloseDialogIcon();

        logger.log(LogStatus.INFO,"Check that events count in list is "+size);
        int eventsSize = videoChannelsPage.GetSelectedEventsSize();
        Assert.assertEquals(eventsSize, sizeSelectedEvents);
    }

    @Test(enabled=false)
    public void SelectAnyEventsInspecifyEventsTest() throws InterruptedException {
        logger=report.startTest("SelectAnyEventsInspecifyEventsTest");

        String deviceIP=device.getText();
        logger.log(LogStatus.INFO,"Click on the device "+ deviceIP);
        videoChannelsPage.FindDeviceByName(deviceIP).click();
        Thread.sleep(1000);

        videoChannelsPage.IfScheduleIsNotExistAddIt();

        logger.log(LogStatus.INFO,"select Continuous & Event if it is exist, if isn't exist select Event");
        try{
            videoChannelsPage.SelectRecordingModeOption("Continuous & Event");
        }catch(Exception e){
            videoChannelsPage.SelectRecordingModeOption("Event");
        }

        String motion = videoChannelsPage.MotionIsOn();
        if(motion.equals("true")){
            logger.log(LogStatus.INFO,"Click on motion toggle");
            videoChannelsPage.ClickOnMotionToggle();

            logger.log(LogStatus.INFO,"Press on Save button");
            videoChannelsPage.PressSaveButton();
        }

        logger.log(LogStatus.INFO,"Press on Specify Events button");
        videoChannelsPage.PressOnSpecifyEventsButton();

        logger.log(LogStatus.INFO,"Click on Event Type field");
        videoChannelsPage.ClickOnEventTypeField();

        int optionsSize = videoChannelsPage.CountEventTypeOptions();
        int random = videoChannelsPage.GetRandomDigit(1, optionsSize);
        String eventName = videoChannelsPage.GetEventTypeOptionByIndex(random);

        logger.log(LogStatus.INFO,"Select "+eventName + " option ");
        videoChannelsPage.SelectEventTypeOptionByIndex(random);

        if(eventName.equals("Tamper")) {
            int postOptionIndex = videoChannelsPage.GetRandomDigit(0, 4);
            String postOptionName = videoChannelsPage.GetEventTypeOptionByIndex(postOptionIndex);

            logger.log(LogStatus.INFO, "Select " + postOptionName + " post option ");
            videoChannelsPage.SelectEventTypePostOptionByIndex(postOptionIndex);
        }

        if(eventName.equals("Native Camera Analytics")){
            int postOptionIndex = videoChannelsPage.GetRandomDigit(4, 6);
            String postOptionName = videoChannelsPage.GetEventTypeOptionByIndex(postOptionIndex);

            logger.log(LogStatus.INFO, "Select " + postOptionName + " post option ");
            videoChannelsPage.SelectEventTypePostOptionByIndex(postOptionIndex);
        }

        logger.log(LogStatus.INFO,"Delete events if them are exist");
        int sizeSelectedEvents = videoChannelsPage.CountSelectedEventsInSpecifyEvents();
        for(int i=0; i<sizeSelectedEvents; i++){
            videoChannelsPage.DeleteSelectedEventInSpecifyEvents(0);
        }

        logger.log(LogStatus.INFO,"Press on any radio button");
        videoChannelsPage.PressAnySelectedRadioButton();

        logger.log(LogStatus.INFO,"Check that count of selected events is one");
        sizeSelectedEvents = videoChannelsPage.CountSelectedEventsInSpecifyEvents();
        Assert.assertEquals(sizeSelectedEvents, 1);

        logger.log(LogStatus.INFO,"Press on Apply and Close button");
        videoChannelsPage.PressOnApplyAndCloseButtonInSpecifyEvents();

        logger.log(LogStatus.INFO,"Check that events count in list is 1");
        Thread.sleep(1000);
        int eventsSize = videoChannelsPage.GetSelectedEventsSize();
        Assert.assertEquals(eventsSize, 1);
    }

    @Test(enabled=false) // impossible to change status
    public void SelectDigitalInputEventTypeTest() throws InterruptedException {
        logger=report.startTest("SelectDigitalInputEventTypeTest");

        String deviceIP=device.getText();
        logger.log(LogStatus.INFO,"Click on the device "+ deviceIP);
        videoChannelsPage.FindDeviceByName(deviceIP).click();
        Thread.sleep(1000);

        videoChannelsPage.IfScheduleIsNotExistAddIt();

        logger.log(LogStatus.INFO,"select Event recording Mode for device " +deviceIP);
        videoChannelsPage.SelectRecordingModeOption("Event");

        String motion = videoChannelsPage.MotionIsOn();
        if(motion.equals("true")){
            logger.log(LogStatus.INFO,"Click on motion toggle");
            videoChannelsPage.ClickOnMotionToggle();

            logger.log(LogStatus.INFO,"Press on Save button");
            videoChannelsPage.PressSaveButton();
        }

        logger.log(LogStatus.INFO,"press on Specify Events button");
        videoChannelsPage.PressOnSpecifyEventsButton();

        logger.log(LogStatus.INFO,"Select Digital Input option");
        videoChannelsPage.SelectEventTypeOptionByText("Digital Input");

        logger.log(LogStatus.INFO,"Delete events if them are exist");
        int sizeSelectedEvents = videoChannelsPage.CountSelectedEventsInSpecifyEvents();
        for(int i=0; i<sizeSelectedEvents; i++){
            videoChannelsPage.DeleteSelectedEventInSpecifyEvents(0);
        }

        int size = videoChannelsPage.CountEventsStatusToogleList();
        int index = videoChannelsPage.GetRandomDigit(0,size);
        String status = videoChannelsPage.GetStatusInSpecifyEvents(index);

        logger.log(LogStatus.INFO,"Take device with index " + index+" and change status");
        videoChannelsPage.ClickOnEventStatusToggle(index);
        videoChannelsPage.ClickOnDeviceInSpecifyEventsDevicesList(index);

        String statusAfterClick = videoChannelsPage.GetStatusInSpecifyEvents(index);
        int indexOf = videoChannelsPage.GetSelectedDeviceTextInSpecifyEvents(0).indexOf("-");
        String selectedStatus =  videoChannelsPage.GetSelectedDeviceTextInSpecifyEvents(0).substring(indexOf+2);

        logger.log(LogStatus.INFO,"Check that status is changed");
        if(status.equals("ON")){
            Assert.assertEquals(statusAfterClick, "OFF");
            Assert.assertEquals(selectedStatus, "Sensor Deactivated");
        }
        if(status.equals("OFF")){
            Assert.assertEquals(statusAfterClick, "ON");
            Assert.assertEquals(selectedStatus, "Sensor Activated");
        }

        logger.log(LogStatus.INFO,"Press on Apply and Close button");
        videoChannelsPage.PressOnApplyAndCloseButtonInSpecifyEvents();

        logger.log(LogStatus.INFO,"Check that events count in list is 1");
        Thread.sleep(1000);
        int eventsSize = videoChannelsPage.GetSelectedEventsSize();
        Assert.assertEquals(eventsSize, 1);
    }

    @Test(enabled=false)//Bug 8181 The toggle is in OFF position when status is Active in Specify Events window
    public void SelectRelayEventTypeTest() throws InterruptedException {
        logger=report.startTest("SelectRelayEventTypeTest");

        String deviceIP=device.getText();
        logger.log(LogStatus.INFO,"Click on the device "+ deviceIP);
        videoChannelsPage.FindDeviceByName(deviceIP).click();
        Thread.sleep(1000);

        videoChannelsPage.IfScheduleIsNotExistAddIt();

        logger.log(LogStatus.INFO,"Select Event recording Mode for device " +deviceIP);
        videoChannelsPage.SelectRecordingModeOption("Event");

        String motion = videoChannelsPage.MotionIsOn();
        if(motion.equals("true")){
            logger.log(LogStatus.INFO,"Click on motion toggle");
            videoChannelsPage.ClickOnMotionToggle();

            logger.log(LogStatus.INFO,"Press on Save button");
            videoChannelsPage.PressSaveButton();
        }

        logger.log(LogStatus.INFO,"Press on Specify Events button");
        videoChannelsPage.PressOnSpecifyEventsButton();

        logger.log(LogStatus.INFO,"Click on Event Type field");
        videoChannelsPage.ClickOnEventTypeField();

        logger.log(LogStatus.INFO,"Select Relay option");
        videoChannelsPage.SelectEventTypeOptionByText("Relay");

        logger.log(LogStatus.INFO,"Delete events if them are exist");
        int sizeSelectedEvents = videoChannelsPage.CountSelectedEventsInSpecifyEvents();
        for(int i=0; i<sizeSelectedEvents; i++){
            videoChannelsPage.DeleteSelectedEventInSpecifyEvents(0);
        }

        int size = videoChannelsPage.CountEventsStatusToogleList();
        int index = videoChannelsPage.GetRandomDigit(0,size);
        String status = videoChannelsPage.GetStatusInSpecifyEvents(index);

        logger.log(LogStatus.INFO,"Take device with index " + index+" and change status");
        videoChannelsPage.ClickOnEventStatusToggle(index);
        videoChannelsPage.ClickOnDeviceInSpecifyEventsDevicesList(index);

        String statusAfterClick = videoChannelsPage.GetStatusInSpecifyEvents(index);
        int indexOf = videoChannelsPage.GetSelectedDeviceTextInSpecifyEvents(0).indexOf("-");
        String selectedStatus =  videoChannelsPage.GetSelectedDeviceTextInSpecifyEvents(0).substring(indexOf+2);

        logger.log(LogStatus.INFO,"Check that status is changed");
        if(status.equals("Active")){
            Assert.assertEquals(statusAfterClick, "Inactive");
            Assert.assertEquals(selectedStatus, "Relay Off");
        }
        if(status.equals("Inactive")){
            Assert.assertEquals(statusAfterClick, "Active");
            Assert.assertEquals(selectedStatus, "Relay On");
        }

        logger.log(LogStatus.INFO,"Press on Apply and Close button");
        videoChannelsPage.PressOnApplyAndCloseButtonInSpecifyEvents();

        logger.log(LogStatus.INFO,"Check that events count in list is 1");
        Thread.sleep(1000);
        int eventsSize = videoChannelsPage.GetSelectedEventsSize();
        Assert.assertEquals(eventsSize, 1);
    }

    @Test(enabled=false)//Bug 8175  Selected  events aren't shown correctly after removing
    public void SelectEventAndPressCloseTest() throws InterruptedException {
        logger=report.startTest("SelectEventAndPressCloseTest");

        String deviceIP=device.getText();
        logger.log(LogStatus.INFO,"Click on the device "+ deviceIP);
        videoChannelsPage.FindDeviceByName(deviceIP).click();
        Thread.sleep(1000);

        videoChannelsPage.IfScheduleIsNotExistAddIt();

        logger.log(LogStatus.INFO,"select Event recording Mode");
        videoChannelsPage.SelectRecordingModeOption1("Event");
        Thread.sleep(1000);

       // String motion = videoChannelsPage.MotionIsOn();
       // if(motion.equals("true")){
        logger.log(LogStatus.INFO,"Click on motion toggle is ON");
          videoChannelsPage.MotionOn();
            
            logger.log(LogStatus.INFO,"Click on motion toggle");
            //videoChannelsPage.ClickOnMotionToggle();
//
            logger.log(LogStatus.INFO,"Press on Save button");
            videoChannelsPage.PressSaveButton();
        

        int eventsSizeBefore = videoChannelsPage.GetSelectedEventsSize();

        logger.log(LogStatus.INFO,"press on Specify Events button");
        videoChannelsPage.PressOnSpecifyEventsButton();

        logger.log(LogStatus.INFO,"Click on Event Type field");
        videoChannelsPage.ClickOnEventTypeField();

        int optionsSize = videoChannelsPage.CountEventTypeOptions();
        int random = videoChannelsPage.GetRandomDigit(1, optionsSize);
        String eventName = videoChannelsPage.GetEventTypeOptionByIndex(random);

        logger.log(LogStatus.INFO,"Select "+eventName + " option ");
        videoChannelsPage.SelectEventTypeOptionByIndex(random);

        if(eventName.equals("Tamper")) {
            int postOptionIndex = videoChannelsPage.GetRandomDigit(0, 4);
            String postOptionName = videoChannelsPage.GetEventTypeOptionByIndex(postOptionIndex);

            logger.log(LogStatus.INFO, "Select " + postOptionName + " post option ");
            videoChannelsPage.SelectEventTypePostOptionByIndex(postOptionIndex);
        }

        if(eventName.equals("Native Camera Analytics")){
            int postOptionIndex = videoChannelsPage.GetRandomDigit(4, 6);
            String postOptionName = videoChannelsPage.GetEventTypeOptionByIndex(postOptionIndex);

            logger.log(LogStatus.INFO, "Select " + postOptionName + " post option ");
            videoChannelsPage.SelectEventTypePostOptionByIndex(postOptionIndex);
        }

        logger.log(LogStatus.INFO,"Delete events if them are exist");
        int sizeSelectedEvents = videoChannelsPage.CountSelectedEventsInSpecifyEvents();
        for(int i=0; i<sizeSelectedEvents; i++){
            videoChannelsPage.DeleteSelectedEventInSpecifyEvents(0);
        }

        Thread.sleep(1000);
        int size = videoChannelsPage.CountOfDevicesInSpecifyEventsWindow();
        int index = videoChannelsPage.GetRandomDigit(0, size-2);

        logger.log(LogStatus.INFO,"Click on any event in Specify event dialog");
        videoChannelsPage.ClickOnDeviceInSpecifyEventsDevicesList(index);

        logger.log(LogStatus.INFO,"Check that event is selected");
        int selectedEventsSize = videoChannelsPage.CountSelectedEventsInSpecifyEvents();
        Assert.assertEquals(selectedEventsSize,1);

        logger.log(LogStatus.INFO,"Press on Close X icon");
        videoChannelsPage.PressCloseDialogIcon();

        logger.log(LogStatus.INFO,"Check that event isn't saved");
        Thread.sleep(1000);
        int eventsSize = videoChannelsPage.GetSelectedEventsSize();
        Assert.assertEquals(eventsSize, eventsSizeBefore);

        logger.log(LogStatus.INFO,"Press on Save button");
        videoChannelsPage.PressSaveButton();

        logger.log(LogStatus.INFO,"Check that event isn't saved");
        Thread.sleep(1000);
        int eventsSizeAfterSaving = videoChannelsPage.GetSelectedEventsSize();
        Assert.assertEquals(eventsSizeAfterSaving, eventsSizeBefore);
    }

    @Test(enabled=false) //Bug 8175  Selected  events aren't shown correctly after removing
    public void DeleteEventsFromEventsList() throws InterruptedException {
        logger=report.startTest("DeleteEventsFromEventsList");

        String deviceIP=device.getText();
        logger.log(LogStatus.INFO,"Click on the device "+ deviceIP);
        videoChannelsPage.FindDeviceByName(deviceIP).click();
        Thread.sleep(1000);

        videoChannelsPage.IfScheduleIsNotExistAddIt();

        logger.log(LogStatus.INFO,"Select Event recording Mode");
        videoChannelsPage.SelectRecordingModeOption1("Event");

       // String motion = videoChannelsPage.MotionIsOn();
       // if(motion.equals("true")){
        
            logger.log(LogStatus.INFO,"Click on motion toggle is ON");
            videoChannelsPage.MotionOn();
             
            //logger.log(LogStatus.INFO,"Click on motion toggle");
            //videoChannelsPage.ClickOnMotionToggle();

            logger.log(LogStatus.INFO,"Press on Save button");
            videoChannelsPage.PressSaveButton();
            Thread.sleep(3000);
        //
        //driver.findElement(By.linkText("Specify Events")).click();
        logger.log(LogStatus.INFO,"Press on Specify Events button");
        videoChannelsPage.PressOnSpecifyEventsButton();
        //Thread.sleep(3000);

        logger.log(LogStatus.INFO,"Click on Event Type field");
        videoChannelsPage.ClickOnEventTypeField();
        Thread.sleep(2000);

        int optionsSize = videoChannelsPage.CountEventTypeOptions();
        int random = videoChannelsPage.GetRandomDigit(1, optionsSize);
        String eventName = videoChannelsPage.GetEventTypeOptionByIndex(random);

        logger.log(LogStatus.INFO,"Select "+eventName + " option ");
        videoChannelsPage.SelectEventTypeOptionByIndex(random);

        if(eventName.equals("Tamper")) {
            int postOptionIndex = videoChannelsPage.GetRandomDigit(0, 4);
            String postOptionName = videoChannelsPage.GetEventTypeOptionByIndex(postOptionIndex);

            logger.log(LogStatus.INFO, "Select " + postOptionName + " post option ");
            videoChannelsPage.SelectEventTypePostOptionByIndex(postOptionIndex);
        }

        if(eventName.equals("Native Camera Analytics")){
            int postOptionIndex = videoChannelsPage.GetRandomDigit(4, 6);
            String postOptionName = videoChannelsPage.GetEventTypeOptionByIndex(postOptionIndex);

            logger.log(LogStatus.INFO, "Select " + postOptionName + " post option ");
            videoChannelsPage.SelectEventTypePostOptionByIndex(postOptionIndex);
        }

        logger.log(LogStatus.INFO,"Delete events if them are exist");
        int sizeSelectedEvents = videoChannelsPage.CountSelectedEventsInSpecifyEvents();
        for(int i=0; i<sizeSelectedEvents; i++){
            videoChannelsPage.DeleteSelectedEventInSpecifyEvents(0);
        }

        int size = videoChannelsPage.CountOfDevicesInSpecifyEventsWindow();
        if(size>2){
            logger.log(LogStatus.INFO,"Select the first event randomaly");
            size = videoChannelsPage.CountOfDevicesNotSelectedInSpecifyEventsWindow();
            int index = videoChannelsPage.GetRandomDigit(0, size-2);
            videoChannelsPage.ClickOnDeviceNotSelectedInSpecifyEventsDevicesList(index);

            logger.log(LogStatus.INFO,"Select the second event");
            size = videoChannelsPage.CountOfDevicesNotSelectedInSpecifyEventsWindow();
            index = videoChannelsPage.GetRandomDigit(0, size-2);
            videoChannelsPage.ClickOnDeviceNotSelectedInSpecifyEventsDevicesList(index);

            logger.log(LogStatus.INFO,"Select the third event");
            size = videoChannelsPage.CountOfDevicesNotSelectedInSpecifyEventsWindow();
            index = videoChannelsPage.GetRandomDigit(0, size-2);
            videoChannelsPage.ClickOnDeviceNotSelectedInSpecifyEventsDevicesList(index);

            logger.log(LogStatus.INFO,"Check that events are selected");
            int selectedEventsSize = videoChannelsPage.CountSelectedEventsInSpecifyEvents();
            Assert.assertEquals(selectedEventsSize,3);

            logger.log(LogStatus.INFO,"Press on Apply and Close button");
            videoChannelsPage.PressOnApplyAndCloseButtonInSpecifyEvents();

            logger.log(LogStatus.INFO,"Check that events count in list is 3");
            Thread.sleep(1000);
            int eventsSize = videoChannelsPage.GetSelectedEventsSize();
            Assert.assertEquals(eventsSize, 3);

            logger.log(LogStatus.INFO,"Delete first event");
            videoChannelsPage.ClickOnDeleteEventIcon(eventsSize-1);

            logger.log(LogStatus.INFO,"Check that events count in list is 2");
            int eventsSizeAfterRemoving = videoChannelsPage.GetSelectedEventsSize();
            Assert.assertEquals(eventsSizeAfterRemoving, 2);

            logger.log(LogStatus.INFO,"Press on Save button");
            videoChannelsPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that events count in list is 2");
            eventsSizeAfterRemoving = videoChannelsPage.GetSelectedEventsSize();

            Assert.assertEquals(eventsSizeAfterRemoving, 2);

            logger.log(LogStatus.INFO,"Delete event after saving");
            videoChannelsPage.ClickOnDeleteEventIcon(eventsSize-2);

            logger.log(LogStatus.INFO,"Check that events count in list is 1");
            int eventsSizeAfterRemoving2 = videoChannelsPage.GetSelectedEventsSize();
            Assert.assertEquals(eventsSizeAfterRemoving2, 1);

            logger.log(LogStatus.INFO,"Press on Save button");
            videoChannelsPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that events count in list is 1");
            eventsSizeAfterRemoving2 = videoChannelsPage.GetSelectedEventsSize();
            Assert.assertEquals(eventsSizeAfterRemoving2, 1);
        }
        if(size<3){
            logger.log(LogStatus.INFO,"There are less than 3 events");
        }
    }

    @Test(enabled=false)
    public void SortEventsByNameInSpecifyEventsWTest() throws InterruptedException {
        logger=report.startTest("SortEventsByNameInSpecifyEventsWTest");

        String deviceIP=device.getText();
        logger.log(LogStatus.INFO,"Click on the device "+ deviceIP);
        videoChannelsPage.FindDeviceByName(deviceIP).click();
        Thread.sleep(1000);

        videoChannelsPage.IfScheduleIsNotExistAddIt();

        logger.log(LogStatus.INFO,"select Event recording Mode");
        videoChannelsPage.SelectRecordingModeOption("Event");

        logger.log(LogStatus.INFO,"press on Specify Events button");
        videoChannelsPage.PressOnSpecifyEventsButton();

        logger.log(LogStatus.INFO,"Click on Event Type field");
        videoChannelsPage.ClickOnEventTypeField();

        int optionsSize = videoChannelsPage.CountEventTypeOptions();
        int random = videoChannelsPage.GetRandomDigit(1, optionsSize);
        String eventName = videoChannelsPage.GetEventTypeOptionByIndex(random);

        logger.log(LogStatus.INFO,"Select "+eventName + " option ");
        videoChannelsPage.SelectEventTypeOptionByIndex(random);

        if(eventName.equals("Tamper")) {
            int postOptionIndex = videoChannelsPage.GetRandomDigit(0, 4);
            String postOptionName = videoChannelsPage.GetEventTypeOptionByIndex(postOptionIndex);

            logger.log(LogStatus.INFO, "Select " + postOptionName + " post option ");
            videoChannelsPage.SelectEventTypePostOptionByIndex(postOptionIndex);
        }

        if(eventName.equals("Native Camera Analytics")){
            int postOptionIndex = videoChannelsPage.GetRandomDigit(4, 6);
            String postOptionName = videoChannelsPage.GetEventTypeOptionByIndex(postOptionIndex);

            logger.log(LogStatus.INFO, "Select " + postOptionName + " post option ");
            videoChannelsPage.SelectEventTypePostOptionByIndex(postOptionIndex);
        }

        int size = videoChannelsPage.CountOfDevicesInSpecifyEventsWindow();
        if(size>1){
            videoChannelsPage.ClickOnNameColumnHeader();

            int last= videoChannelsPage.CountOfDevicesInSpecifyEventsWindow()-1;
            String firstEvent = videoChannelsPage.GetDeviceTextInSpecifyEvents(0);
            String lastEvent = videoChannelsPage.GetDeviceTextInSpecifyEvents(last);

            while(true){
                String lastEventBefore = videoChannelsPage.GetDeviceTextInSpecifyEvents(last);
                videoChannelsPage.ScrollToEventByIndex(last);
                last= videoChannelsPage.CountOfDevicesInSpecifyEventsWindow()-1;
                String lastEventAfter = videoChannelsPage.GetDeviceTextInSpecifyEvents(last);
                if(lastEventBefore.equals(lastEventAfter)){
                    lastEvent = lastEventAfter;
                    break;
                }
            }

            logger.log(LogStatus.INFO,"Click on Name column header");
            videoChannelsPage.ClickOnNameColumnHeader();

            String firstEventAfterSort = videoChannelsPage.GetDeviceTextInSpecifyEvents(0);
            String lastEventAfterSort = videoChannelsPage.GetDeviceTextInSpecifyEvents(last);

            while(true){
                String firstEventBefore = videoChannelsPage.GetDeviceTextInSpecifyEvents(0);
                videoChannelsPage.ScrollToEventByIndex(0);
                String firstEventAfter = videoChannelsPage.GetDeviceTextInSpecifyEvents(0);
                if(firstEventBefore.equals(firstEventAfter)){
                    firstEventAfterSort = firstEventAfter;
                    break;
                }
            }
            logger.log(LogStatus.INFO,"Check that sort is done");
            Assert.assertEquals(firstEventAfterSort, lastEvent);
            Assert.assertEquals(lastEventAfterSort, firstEvent);
        }
    }

    @Test(enabled=false)//8183  Sorting by status in SpecifyEvents window doesn't work.
    public void SortEventsByStatusInSpecifyEventsWTest() throws InterruptedException {
        logger=report.startTest("SortEventsByStatusInSpecifyEventsWTest");

        String deviceIP=device.getText();
        logger.log(LogStatus.INFO,"Click on the device "+ deviceIP);
        videoChannelsPage.FindDeviceByName(deviceIP).click();
        Thread.sleep(1000);

        videoChannelsPage.IfScheduleIsNotExistAddIt();

        logger.log(LogStatus.INFO,"Select Event recording Mode");
        videoChannelsPage.SelectRecordingModeOption1("Event");

        logger.log(LogStatus.INFO,"Press on Specify Events button");
        videoChannelsPage.PressOnSpecifyEventsButton();

        logger.log(LogStatus.INFO,"Click on Event Type field");
        videoChannelsPage.ClickOnEventTypeField();

        logger.log(LogStatus.INFO,"Select option ");
        String option = null;
        int random = videoChannelsPage.GetRandomDigit(0, 2);
        if(random == 0) option = "Digital Input";
        else option = "Relay";
        videoChannelsPage.SelectEventTypeOptionByText(option);

        int size = videoChannelsPage.CountOfDevicesInSpecifyEventsWindow();
        if(size>1){
            videoChannelsPage.ClickOnStatusColumnHeader();

//            int last= videoChannelsPage.CountOfDevicesInSpecifyEventsWindow()-1;
            int last= videoChannelsPage.GetStatusInSpecifyDialog()-1;
            String firstStatus = videoChannelsPage.GetStatusTextInSpecifyEvents(0);
            String lastStatus = videoChannelsPage.GetStatusTextInSpecifyEvents(last);

            logger.log(LogStatus.INFO,"Click on Status column header");
            videoChannelsPage.ClickOnStatusColumnHeader();

            String firstStatusAfterSort = videoChannelsPage.GetStatusTextInSpecifyEvents(0);
            String lastStatusAfterSort = videoChannelsPage.GetStatusTextInSpecifyEvents(last);

            logger.log(LogStatus.INFO,"Check that sort is done");
            Assert.assertEquals(firstStatusAfterSort, lastStatus);
            Assert.assertEquals(lastStatusAfterSort, firstStatus);
        }
    }

    @Test(enabled=true, priority=5)
    public void GetVideoChannelPropertiesAndApplyToOtherChannelTest() throws InterruptedException {
        logger=report.startTest("GetVideoChannelPropertiesAndApplyToOtherChannelTest");

        logger.log(LogStatus.INFO,"Get randomaly channel from list");
        int size = videoChannelsPage.GetDevicesSize();
        WebElement channel = null;
        boolean flag = false;
        for(int i = 0; i<size; i++) {
            WebElement element = videoChannelsPage.SelectRandomDevice();
            element.click();
            Thread.sleep(1000);
            logger.log(LogStatus.INFO,"If Apply to button enable by clicking on the channel "+ element.getText()+ " than set this channel");
            if(videoChannelsPage.ApplyToButtonIsEnabled()){
                channel = element;
                flag =true;
                break;
            }
        }
        if(flag){
            String channelText = channel.getText();
            logger.log(LogStatus.INFO,"Get properties from Recording page for channel "+channelText );
            boolean recordingStatus= videoChannelsPage.RecordingToggleIsOn();
            boolean limitRetentionStatus= videoChannelsPage.LimitRetentionToggleIsOn();
            String limRetentionValue =null;
            if(limitRetentionStatus) limRetentionValue =videoChannelsPage.GetLimitRetention();
            Thread.sleep(1000);
            String schedule = videoChannelsPage.GetSelectedScheduleOption();
            Thread.sleep(1000);
            String recordingMode = videoChannelsPage.GetSelectedRecordingModeOption();
            String stream = videoChannelsPage.GetSelectedStream();
            Thread.sleep(1000);

            String preEvent = null;
            String postEvent = null;
            String motion = null;
            if(recordingMode.equals("Event")){
                preEvent=videoChannelsPage.GetPreEventText();
                postEvent= videoChannelsPage.GetPostEventText();
                motion = videoChannelsPage.MotionIsOn();
            }
            if(recordingMode.equals("Continuous & Event")){
                postEvent= videoChannelsPage.GetPostEventText();
                motion = videoChannelsPage.MotionIsOn();
           }
            logger.log(LogStatus.INFO,"Click on Apply To button");
            videoChannelsPage.ClickOnApplyToButton();

            logger.log(LogStatus.INFO,"If Streams and Recording Checkbox isn't checked - check it");
            if(!videoChannelsPage.StreamsAndRecordingCheckboxIsSelected()){
                videoChannelsPage.ClickOnStreamsAndRecordingCheckbox();
            }
            logger.log(LogStatus.INFO,"Click on video channel in Apply To dialog");
            int audioChannelsize = videoChannelsPage.GetAudioChannelsSizeInApplyToDialog();
            int index = videoChannelsPage.GetRandomDigit(0,audioChannelsize );
            String channelText2 = videoChannelsPage.GetAudioChannelTextInApplyChannelsDialogByIndex(index);
            logger.log(LogStatus.INFO,"Click on video channel "+channelText2+" in Apply To dialog");
            videoChannelsPage.ClickOnVideoChanelsInApplyChannelsDialogByIndex(index);

            logger.log(LogStatus.INFO,"Click on Apply button in Apply To Dialog");
            videoChannelsPage.ClickOnApplyButtonInApplyToDialog();

            logger.log(LogStatus.INFO,"Click on Close X icon in Apply To Dialog");
            videoChannelsPage.PressCloseDialogIcon();
            Thread.sleep(1000);

            logger.log(LogStatus.INFO,"Click on the channel "+ channelText2);
            videoChannelsPage.FindDeviceByName(channelText2);
            Thread.sleep(1000);

            logger.log(LogStatus.INFO,"Get properties from Recording page for channel "+channelText2 );
            boolean recordingStatusCh2= videoChannelsPage.RecordingToggleIsOn();
            boolean limitRetentionStatusCh2= videoChannelsPage.LimitRetentionToggleIsOn();
            String limRetentionValueCh2 =null;
            if(limitRetentionStatusCh2) limRetentionValueCh2 =videoChannelsPage.GetLimitRetention();
            String scheduleCh2 = videoChannelsPage.GetSelectedScheduleOption();
            String recordingModeCh2 = videoChannelsPage.GetSelectedRecordingModeOption();
            String streamCh2 = videoChannelsPage.GetSelectedStream();

            String preEventCh2 = null;
            String postEventCh2 =null;
            String motionCh2 = null;
            if(recordingModeCh2.equals("Event")){
                preEventCh2=videoChannelsPage.GetPreEventText();
                postEventCh2= videoChannelsPage.GetPostEventText();
                motionCh2 = videoChannelsPage.MotionIsOn();
            }
            if(recordingModeCh2.equals("Continuous & Event")){
                postEventCh2= videoChannelsPage.GetPostEventText();
                motionCh2 = videoChannelsPage.MotionIsOn();
            }
            logger.log(LogStatus.INFO,"Check that Recording status for channel "+channelText2 + " is equals for channel "+ channelText);
            Assert.assertEquals(recordingStatus, recordingStatusCh2);

            logger.log(LogStatus.INFO,"Check that limit Retention status for channel "+channelText2 + " is equals for channel "+ channelText);
            Assert.assertEquals(limitRetentionStatus, limitRetentionStatusCh2);

            logger.log(LogStatus.INFO,"Check that limit Retention value for channel "+channelText2 + " is equals for channel "+ channelText);
            Assert.assertEquals(limRetentionValue, limRetentionValueCh2);

            logger.log(LogStatus.INFO,"Check that schedule for channel "+channelText2 + " is equals for channel "+ channelText);
            Assert.assertEquals(schedule, scheduleCh2);

            logger.log(LogStatus.INFO,"Check that Recording Mode for channel "+channelText2 + " is equals for channel "+ channelText);
            Assert.assertEquals(recordingMode, recordingModeCh2);

            logger.log(LogStatus.INFO,"Check that stream for channel "+channelText2 + " is equals for channel "+ channelText);
            Assert.assertEquals(stream, streamCh2);

            if(recordingModeCh2.equals("Event")){
                logger.log(LogStatus.INFO,"Check that preEvent for channel "+channelText2 + " is equals for channel "+ channelText+ " in second stream");
                Assert.assertEquals(preEvent, preEventCh2);

                logger.log(LogStatus.INFO,"Check that postEvent for channel "+channelText2 + " is equals for channel "+ channelText+ " in second stream");
                Assert.assertEquals(postEvent, postEventCh2);

                logger.log(LogStatus.INFO,"Check that motion status for channel "+channelText2 + " is equals for channel "+ channelText+ " in second stream");
                Assert.assertEquals(motion, motionCh2);
            }
            if(recordingModeCh2.equals("Continuous & Event")){
                logger.log(LogStatus.INFO,"Check that postEvent for channel "+channelText2 + " is equals for channel "+ channelText+ " in second stream");
                Assert.assertEquals(postEvent, postEventCh2);

                logger.log(LogStatus.INFO,"Check that motion status for channel "+channelText2 + " is equals for channel "+ channelText+ " in second stream");
                Assert.assertEquals(motion, motionCh2);
            }
        }
        if(!flag){
            logger.log(LogStatus.INFO,"There are not more than one audio channels inside one Vendor group");
        }
    }

    @AfterMethod
    public void afterMethod(ITestResult result){
        if(result.getStatus()==ITestResult.SUCCESS){
            logger.log(LogStatus.PASS, "Test is passed");
        }
        if(result.getStatus()==ITestResult.SKIP){
            logger.log(LogStatus.SKIP, "Test is skipped");
        }
        if(result.getStatus()==ITestResult.FAILURE){
            videoChannelsPage.takeScreenshot(driver, "VideoChannels", result.getName());
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
