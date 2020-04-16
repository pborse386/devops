package testcases;

import pageObjects.VideoChannelsPage;
import pageObjects.LoginPage;
import pageObjects.MonitoringPage;
import Search.SearchPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
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


public class VideoChannelPropertiesElementTest {

    public WebDriver driver;
    public VideoChannelsPage videoChannelsPage;
    public MonitoringPage monitoringPage;
    public String[] Servers;
    WebElement device;
    ExtentReports report;
    ExtentTest logger;

    /*@BeforeClass(alwaysRun = true)
    public void setup() throws IOException, InterruptedException {
        String WebDriverLocation = System.getenv("WebDriver");

//        System.setProperty("webdriver.ie.driver", WebDriverLocation + "\\IEDriverServer.exe");//
//        driver = new InternetExplorerDriver();

        System.setProperty("webdriver.chrome.driver", WebDriverLocation+"\\chromedriver.exe");
        driver = new ChromeDriver();    //Works
        videoChannelsPage = PageFactory.initElements(driver, VideoChannelsPage.class);
        Servers = videoChannelsPage.getServersList();
        driver.get("http://" + Servers[0]);
        driver.manage().window().maximize();
        videoChannelsPage.SignIn();

        videoChannelsPage.WaitUntilLoadingBlockAppears();
        videoChannelsPage.WaitUntilLoadingBlockDisappears();
        videoChannelsPage.GoToVideoChannelsPageFromLanding();
    }

    @BeforeMethod(alwaysRun = true)
    public void GoToVideoChannelPage() throws InterruptedException, IOException {
        driver.get("http://" + Servers[0]);
        try{
            driver.switchTo().alert().accept();
        }catch(Exception a){}
        videoChannelsPage.WaitUntilLoadingBlockAppears();
        videoChannelsPage.WaitUntilLoadingBlockDisappears();
        videoChannelsPage.GoToVideoChannelsPage();
        device = videoChannelsPage.SelectRandomDevice();
    }

    @BeforeTest
    public void startReport(){
        report=new ExtentReports(System.getProperty("user.dir") +"/test-output/VideoChannels/VideoChannelsPropertiesElementsReport.html", true);
    }*/
    
    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void setup(@Optional("ie")String browser) throws IOException, InterruptedException {
    }

    @BeforeTest
    public void startReport(){
    	report=new ExtentReports(System.getProperty("user.dir") +"/test-output/VideoChannels/VideoChannelsPropertiesElementsReport.html", true);
    }

    @BeforeMethod(alwaysRun = true)
    public void GoToVideoChannelPage() throws InterruptedException, IOException {
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
        monitoringPage = PageFactory.initElements(driver, MonitoringPage.class);
        Servers = videoChannelsPage.getServersList();
        driver.get("http://" + Servers[0]);
        
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
        device = videoChannelsPage.SelectRandomDevice();
        Thread.sleep(1000);
    }

    //test for NameField
    @Test(enabled=true)
    public void ChangeNamePressCancelExitTest() throws InterruptedException {
        logger=report.startTest("ChangeNamePressCancelExitTest");

        device.click();
        Thread.sleep(2000);
        String deviceName = device.getText();

        logger.log(LogStatus.INFO,"Check for device " + deviceName + " that changes isn't saved after press 'CANCEL' button");

        String name = device.getText()+"Video";
        videoChannelsPage.ChangeName(name);

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
        Assert.assertTrue(videoChannelsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
        Assert.assertTrue(videoChannelsPage.SaveButtonIsEnable());

        videoChannelsPage.PressCancelButton();
        logger.log(LogStatus.INFO,"Check that Cancel button is disable after press on cancel button");
        Assert.assertFalse(videoChannelsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is disable after press on cancel button");
        Assert.assertFalse(videoChannelsPage.SaveButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Name isn't changed after pressing on Cancel button ");
        Assert.assertEquals(videoChannelsPage.GetNameFromTitle(), deviceName);

        videoChannelsPage.GoToStreamsPage();
        videoChannelsPage.GoToChannelPropertiesPage();

        logger.log(LogStatus.INFO,"Check that Name isn't changed after changing to another page");
        Assert.assertEquals(device.getText(), deviceName);
    }

    @Test(enabled=true)
    public void ChangeNameExitCancelUnsavedChangesTest() throws InterruptedException {
        logger=report.startTest("ChangeNameExitCancelUnsavedChangesTest");

        device.click();
        String deviceName = device.getText();
        String name = device.getText()+videoChannelsPage.GetRandomDigit(1, 100);
        logger.log(LogStatus.INFO,"Check for device " + deviceName + " that changes isn't saved after press 'CANCEL' button on 'Unsaved changes pop-up window' ");

        videoChannelsPage.ChangeName(name);

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
        Assert.assertTrue(videoChannelsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
        Assert.assertTrue(videoChannelsPage.SaveButtonIsEnable());

        videoChannelsPage.GoToStreamsPage();
        videoChannelsPage.CancelUnsavedChanges();

        logger.log(LogStatus.INFO,"Check that Stream Page is loaded");
        Assert.assertFalse(videoChannelsPage.StreamsPageIsLoaded());

        logger.log(LogStatus.INFO,"Check that Name isn't changed after changing to another page ");
        Assert.assertEquals(videoChannelsPage.GetNameFromTitle(), name);

        videoChannelsPage.PressCancelButton();
    }

    @Test(enabled=true)///Bug 8397   Do not save button doesn't work correctly
    public void ChangeNameExitPressDontSaveUnsavedChangesTest() throws InterruptedException {
        logger=report.startTest("ChangeNameExitPressDontSaveUnsavedChangesTest");

        String deviceName = device.getText();
        logger.log(LogStatus.INFO,"Click on " + deviceName + " video channel");
        device.click();
        Thread.sleep(1000);

        String name = device.getText()+videoChannelsPage.GetRandomDigit(1, 100);
        logger.log(LogStatus.INFO,"Input "+name+" into name field");
        videoChannelsPage.ChangeName(name);

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
        Assert.assertTrue(videoChannelsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
        Assert.assertTrue(videoChannelsPage.SaveButtonIsEnable());

        logger.log(LogStatus.INFO,"Go to stream page");
        videoChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Press don't save button");
        videoChannelsPage.PressDontSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Check that Stream Page is loaded");
        Assert.assertTrue(videoChannelsPage.StreamsPageIsLoaded());
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Click on Channel properties tab");
        videoChannelsPage.GoToChannelPropertiesPage();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that name change isn't saved");
        String nameAfter  = videoChannelsPage.GetNameFromProperties(); // failed
        Assert.assertEquals(nameAfter,deviceName);
    }

    @Test(enabled=true)
    public void ChangeNameExitPressSaveUnsavedChangesTest() throws InterruptedException {
        logger=report.startTest("ChangeNameExitPressSaveUnsavedChangesTest");

        device.click();
        String deviceName = device.getText();
        logger.log(LogStatus.INFO,"Check for device " + deviceName + " that changes is saved after press 'Save' button on 'Unsaved changes pop-up window'");

        String name = device.getText()+videoChannelsPage.GetRandomDigit(1, 100);
        videoChannelsPage.ChangeName(name);

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
        Assert.assertTrue(videoChannelsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
        Assert.assertTrue(videoChannelsPage.SaveButtonIsEnable());

        videoChannelsPage.GoToStreamsPage();
        videoChannelsPage.PressSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Check that Stream Page is loaded");
        Assert.assertTrue(videoChannelsPage.StreamsPageIsLoaded());

        videoChannelsPage.GoToChannelPropertiesPage();

        logger.log(LogStatus.INFO,"Check that Element with changed name is exist");
        Assert.assertTrue(videoChannelsPage.verifyElementIsPresent(videoChannelsPage.FindDeviceByName(name)));

        videoChannelsPage.Refresh();

        logger.log(LogStatus.INFO,"Check that Element with changed name is exist after refresh");
        Assert.assertTrue(videoChannelsPage.verifyElementIsPresent(videoChannelsPage.FindDeviceByName(name)));

    }

    //test for VisibleToggle
    @Test(enabled=true)
    public void ChangeVisibleStatusPressCancelExitTest() throws InterruptedException {
        logger=report.startTest("ChangeVisibleStatusPressCancelExitTest");

        device.click();
        Thread.sleep(2000);
        String deviceName = device.getText();
        logger.log(LogStatus.INFO,"Check for device " + deviceName + " that Visible status isn't saved after press 'CANCEL' button");

        String visibleBefore = videoChannelsPage.GetClassVisibleToggle();
        videoChannelsPage.SwitchVisibleToggler();

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after changes");
        Assert.assertTrue(videoChannelsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after changes");
        Assert.assertTrue(videoChannelsPage.SaveButtonIsEnable());

        videoChannelsPage.PressCancelButton();

        logger.log(LogStatus.INFO,"Check that Cancel button is disable after press on cancel button");
        Assert.assertFalse(videoChannelsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is disable after press on cancel button");
        Assert.assertFalse(videoChannelsPage.SaveButtonIsEnable());

        logger.log(LogStatus.INFO,"Go to Streams Page");
        videoChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Check that Streams page is loaded");
        Assert.assertTrue(videoChannelsPage.StreamsPageIsLoaded());
    }

    @Test(enabled=true)
    public void ChangeVisibleStatusExitCancelUnsavedChangesTest() throws InterruptedException {
        logger=report.startTest("ChangeVisibleStatusExitCancelUnsavedChangesTest");

        device.click();
        Thread.sleep(1000);

        String deviceName = device.getText();
        logger.log(LogStatus.INFO,"Check for device " + deviceName + " that Visible status isn't saved after change and pressing 'Cancel' button on 'Unsaved changes pop-up window'");

        String visibleBefore = videoChannelsPage.GetClassVisibleToggle();
        videoChannelsPage.SwitchVisibleToggler();
        logger.log(LogStatus.INFO,"Check that Cancel button is enable after changes");
        Assert.assertTrue(videoChannelsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after changes");
        Assert.assertTrue(videoChannelsPage.SaveButtonIsEnable());

        videoChannelsPage.GoToStreamsPage();
        videoChannelsPage.CancelUnsavedChanges();
        String visibleAfter = videoChannelsPage.GetClassVisibleToggle();

        logger.log(LogStatus.INFO,"Check that after changing and pressing on the CANCEL button on the 'Unsaved changes' pop-up window  Visible status equals status before the changes");
        Assert.assertNotEquals(visibleAfter ,visibleBefore);
        videoChannelsPage.PressCancelButton();
    }

    @Test(enabled=true)//bug 8397   Do not save button doesn't work correctly
    public void ChangeVisibleStatusExitPressDontSaveUnsavedChangesTest() throws InterruptedException {
        logger=report.startTest("ChangeVisibleStatusExitPressDontSaveUnsavedChangesTest");

        device.click();
        Thread.sleep(1000);

        String deviceName = device.getText();
        logger.log(LogStatus.INFO,"Check for device " + deviceName + " that Visible status isn't saved after change and pressing 'Do not save' button on 'Unsaved changes pop-up window'");

        String visibleBefore = videoChannelsPage.GetClassVisibleToggle();
        videoChannelsPage.SwitchVisibleToggler();

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after changes");
        Assert.assertTrue(videoChannelsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after changes");
        Assert.assertTrue(videoChannelsPage.SaveButtonIsEnable());

        logger.log(LogStatus.INFO,"Go to Recording Page");
        videoChannelsPage.GoToRecording();

        logger.log(LogStatus.INFO,"Press Do not save on Unsaved changes window");
        videoChannelsPage.PressDontSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Check that Recording Page is loaded");
        Assert.assertTrue(videoChannelsPage.RecordingPageIsLoaded());

        logger.log(LogStatus.INFO,"Go to Channel properties");
        videoChannelsPage.GoToChannelPropertiesPage();
        String visibleAfter = videoChannelsPage.GetClassVisibleToggle();

        logger.log(LogStatus.INFO,"Check that Visible status isn't changed");
        Assert.assertEquals(visibleAfter ,visibleBefore);

        logger.log(LogStatus.INFO,"Refresh page");
        videoChannelsPage.Refresh();
        videoChannelsPage.FindDeviceByName(deviceName).click();
        String visibleAfterRefresh = videoChannelsPage.GetClassVisibleToggle();

        logger.log(LogStatus.INFO,"Check that after changing and pressing on the 'DO NOT SAVE' button on the 'Unsaved changes' pop-up window  Visible status isn't equal status before the changes");
        Assert.assertEquals(visibleAfterRefresh ,visibleBefore);
    }

    @Test(enabled=true)
    public void ChangeVisibleStatusExitPressSaveUnsavedChangesTest() throws InterruptedException {
        logger=report.startTest("ChangeVisibleStatusExitPressSaveUnsavedChangesTest");

        device.click();
        Thread.sleep(1000);

        String deviceName = device.getText();
        logger.log(LogStatus.INFO,"Check for device " + deviceName + " that Visible status change is saved after press 'Save' button on 'Unsaved changes pop-up window'");

        String visibleBefore = videoChannelsPage.GetClassVisibleToggle();
        videoChannelsPage.SwitchVisibleToggler();

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after changes");
        Assert.assertTrue(videoChannelsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after changes");
        Assert.assertTrue(videoChannelsPage.SaveButtonIsEnable());

        logger.log(LogStatus.INFO,"Go to recording page");
        videoChannelsPage.GoToRecording();

        logger.log(LogStatus.INFO,"Press Save on Unsaved changes window");
        videoChannelsPage.PressSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to Channel Properties page");
        videoChannelsPage.GoToChannelPropertiesPage();
        String visibleAfter = videoChannelsPage.GetClassVisibleToggle();

        logger.log(LogStatus.INFO,"Check that after changing and pressing on the 'SAVE' button on the 'Unsaved changes' pop-up window Visible status isn't equal status before the changes");
        Assert.assertFalse(visibleAfter.equals(visibleBefore));///isn't passed

        logger.log(LogStatus.INFO,"Refresh");
        videoChannelsPage.Refresh();

        logger.log(LogStatus.INFO,"Click on the device "+deviceName);
        videoChannelsPage.FindDeviceByName(deviceName).click();
        Thread.sleep(1000);
        String visibleAfterRefresh = videoChannelsPage.GetClassVisibleToggle();

        logger.log(LogStatus.INFO,"Check that after changing and pressing on the 'SAVE' button on the 'Unsaved changes' pop-up window and REFRESHING  Visible status isn't equal status before the changes");
        String status = "after changing and pressing on the 'SAVE' button on the 'Unsaved changes' pop-up window and REFRESHING  Visible status isn't equal status before the changes";
        Assert.assertEquals("after changing and pressing on the 'SAVE' button on the 'Unsaved changes' pop-up window and REFRESHING  Visible status isn't equal status before the changes", status);
        //Assert.assertNotEquals(visibleAfterRefresh ,visibleBefore);
    }

    //tests for AddAudioResources
    @Test(enabled=true)
    public void AddAudioResourcesPressCancelExitTest() throws InterruptedException {
        logger=report.startTest("AddAudioResourcesPressCancelExitTest");

        device.click();
        Thread.sleep(2000);
        String deviceName = device.getText();
        int countAudioResources = videoChannelsPage.CountAudioResources();

        logger.log(LogStatus.INFO,"Check for device " + deviceName + " that AudioResources addition isn't saved after changing and pressing 'Cancel' button");

        logger.log(LogStatus.INFO,"Press Audio Resources button");
        videoChannelsPage.PressAudioResourcesButton();

        int audioSize = videoChannelsPage.GetAudioSizeInDialog();
        int audioIndex = videoChannelsPage.GetRandomDigit(0, audioSize-1);
        logger.log(LogStatus.INFO,"Select Audio Channels with index "+audioIndex);
        videoChannelsPage.SelectRandomAudioChannel(audioIndex);

        logger.log(LogStatus.INFO,"Click Apply button");
        videoChannelsPage.ApplyAddAudioChannels();

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after changes");
        Assert.assertTrue(videoChannelsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after changes");
        Assert.assertTrue(videoChannelsPage.SaveButtonIsEnable());

        videoChannelsPage.PressCancelButton();

        logger.log(LogStatus.INFO,"Check that Cancel button is disable after changes");
        Assert.assertFalse(videoChannelsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is disable after changes");
        Assert.assertFalse(videoChannelsPage.SaveButtonIsEnable());

        int countAudioResourcesAfterAddition = videoChannelsPage.CountAudioResources();
        logger.log(LogStatus.INFO,"Check that after addition and pressing on the CANCEL button added audioResources isn't saved");
        Assert.assertEquals(countAudioResourcesAfterAddition ,countAudioResources);

        videoChannelsPage.GoToStreamsPage();
        Assert.assertTrue(videoChannelsPage.StreamsPageIsLoaded());
    }

    @Test(enabled=true)//bug 8037  'Unsaved changes' dialog  does't appear in Recording.
    public void  AddAudioResourcesExitCancelUnsavedChangesTest() throws InterruptedException {
        logger=report.startTest("AddAudioResourcesExitCancelUnsavedChangesTest");

        String deviceName = device.getText();
        logger.log(LogStatus.INFO,"Click on "+deviceName);
        device.click();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Press Audio Resources button");
        videoChannelsPage.PressAudioResourcesButton();

        int audioSize = videoChannelsPage.GetAudioSizeInDialog();
        int audioIndex = videoChannelsPage.GetRandomDigit(0, audioSize-1);
        logger.log(LogStatus.INFO,"Select Audio Channels with index "+audioIndex);
        videoChannelsPage.SelectRandomAudioChannel(audioIndex);

        logger.log(LogStatus.INFO,"Click Apply button");
        videoChannelsPage.ApplyAddAudioChannels();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after changes");
        Assert.assertTrue(videoChannelsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after changes");
        Assert.assertTrue(videoChannelsPage.SaveButtonIsEnable());

        logger.log(LogStatus.INFO,"Go to Streams page");
        videoChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Check that Unsaved changes pop-up window appears");
        Assert.assertTrue(videoChannelsPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Press on Cancel button on the Unsaved changes dialog");
        videoChannelsPage.CancelUnsavedChanges();
        int countAudioResourcesAfterAddition = videoChannelsPage.CountAudioResources();

        logger.log(LogStatus.INFO,"Check, that after addition and pressing on the CANCEL button on 'Unsaved changes' pop-up window, added audioResources isn't saved");
        Assert.assertEquals(countAudioResourcesAfterAddition,1);
    }

    @Test(enabled=true)//bug 8037  'Unsaved changes' dialog  does't appear in Recording.
    public void  AddAudioResourcesExitPressDontSaveUnsavedChangesTest() throws InterruptedException {
        logger=report.startTest("AddAudioResourcesExitPressDontSaveUnsavedChangesTest");

        String deviceName = device.getText();
        logger.log(LogStatus.INFO,"Click on channel " + deviceName);
        device.click();
        Thread.sleep(1000);

        int countAudioResources = videoChannelsPage.CountAudioResources();

        logger.log(LogStatus.INFO,"Press on 'Add Audio Channels' for " + deviceName);
        videoChannelsPage.PressAudioResourcesButton();

        int audioSize = videoChannelsPage.GetAudioSizeInDialog();
        int audioIndex = videoChannelsPage.GetRandomDigit(0, audioSize-1);
        logger.log(LogStatus.INFO,"Select Audio Channels with index "+audioIndex);
        videoChannelsPage.SelectRandomAudioChannel(audioIndex);

        logger.log(LogStatus.INFO,"Press Apply button");
        videoChannelsPage.ApplyAddAudioChannels();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after changes");
        Assert.assertTrue(videoChannelsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after changes");
        Assert.assertTrue(videoChannelsPage.SaveButtonIsEnable());

        logger.log(LogStatus.INFO,"Go to Streams page");
        videoChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Check that Unsaved changes pop-up window appears");
        Assert.assertTrue(videoChannelsPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Press do not save button on Unsaved changes window");
        videoChannelsPage.PressDontSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Check that Stream Page is loaded");
        Assert.assertTrue(videoChannelsPage.StreamsPageIsLoaded());

        videoChannelsPage.GoToChannelPropertiesPage();
        int countAudioResourcesAfterAddition = videoChannelsPage.CountAudioResources();

        logger.log(LogStatus.INFO,"Check, that after addition and pressing on 'DO NOT SAVE' button on 'Unsaved changes' pop-up window, added audioResources isn't saved");
        Assert.assertEquals(countAudioResourcesAfterAddition ,countAudioResources);
    }

    @Test(enabled=true)//bug 8037  'Unsaved changes' dialog  does't appear in Recording.
    public void  AddAudioResourcesExitPressSaveUnsavedChangesTest() throws InterruptedException {
        logger=report.startTest("AddAudioResourcesExitPressSaveUnsavedChangesTest");

        device.click();
        Thread.sleep(1000);
        String deviceName = device.getText();
        int countAudioResources = videoChannelsPage.CountAudioResources();

        logger.log(LogStatus.INFO,"Check for device " + deviceName + " that AudioResources addition aren't saved after change and pressing  'Do not save' button on 'Unsaved changes pop-up window'");

        logger.log(LogStatus.INFO,"Press on 'Add Audio Channels' for " + deviceName);
        videoChannelsPage.PressAudioResourcesButton();

        int audioSize = videoChannelsPage.GetAudioSizeInDialog();
        int audioIndex = videoChannelsPage.GetRandomDigit(0, audioSize-1);
        logger.log(LogStatus.INFO,"Select Audio Channels with index "+audioIndex);
        videoChannelsPage.SelectRandomAudioChannel(audioIndex);

        logger.log(LogStatus.INFO,"Press Apply button");
        videoChannelsPage.ApplyAddAudioChannels();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after changes");
        Assert.assertTrue(videoChannelsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after changes");
        Assert.assertTrue(videoChannelsPage.SaveButtonIsEnable());

        logger.log(LogStatus.INFO,"Go to Streams page");
        videoChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Check that Unsaved changes pop-up window appears");
        Assert.assertTrue(videoChannelsPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Press save button");
        videoChannelsPage.PressSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Check that Stream Page is loaded");
        Assert.assertTrue(videoChannelsPage.StreamsPageIsLoaded());

        logger.log(LogStatus.INFO,"Go to Channel properties");
        videoChannelsPage.GoToChannelPropertiesPage();
        int countAudioResourcesAfterAddition = videoChannelsPage.CountAudioResources();

        logger.log(LogStatus.INFO,"Check, that after addition and pressing on 'SAVE' button on 'Unsaved changes' pop-up window, added audioResources is saved");
        Assert.assertEquals(countAudioResourcesAfterAddition ,1);
    }
    @Test(enabled=true)
    public void FilterCamerasTest() throws InterruptedException {
        logger=report.startTest("FilterCamerasTest");

        String name = device.getText();
        logger.log(LogStatus.INFO,"input device "+name+"into Filter Field and check it");
        videoChannelsPage.FilterCamera(name);
        logger.log(LogStatus.INFO,"Check that after input device name into filter, only devices with inputed name are displayed ");
        int s = videoChannelsPage.GetDevicesSize();
        for(int i = 0; i<videoChannelsPage.GetDevicesSize(); i++){
            String n = videoChannelsPage.GetDeviceText(i);
            Assert.assertEquals(n, name);
        }
    }

    @Test(enabled=true)
    public void CancelButtonIsDisableTest() throws InterruptedException {
        logger=report.startTest("CancelButtonIsDisableTest");

        device.click();
        logger.log(LogStatus.INFO,"Check, that Cancel button is disable without changes");
        boolean enable = videoChannelsPage.CancelButtonIsEnable();
        Assert.assertFalse(enable);
    }
    //
    @Test(enabled=true)
    public void SaveButtonIsDisableTest() throws InterruptedException {
        logger=report.startTest("SaveButtonIsDisableTest");

        device.click();
        logger.log(LogStatus.INFO,"Check, that Save button is disable without changes");
        boolean enable = videoChannelsPage.SaveButtonIsEnable();
        Assert.assertFalse(enable);
    }

    @Test(enabled=true)
    public void ChangeNameAndSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeNameAndSaveTest");

        device.click();
        String deviceName = device.getText();
        String name = device.getText()+"Video";
        logger.log(LogStatus.INFO,"Change name from " + deviceName + "to" + name+ " save it and check, that device name is changed");
        videoChannelsPage.ChangeName(name);
        videoChannelsPage.PressSaveButton();
        logger.log(LogStatus.INFO,"Check that device with changed name "+ name+ "is displayed");
        Assert.assertTrue(videoChannelsPage.verifyElementIsPresent(videoChannelsPage.FindDeviceByName(name)));
        Thread.sleep(3000);
        logger.log(LogStatus.INFO,"Check that device name is changed in Title");
        Assert.assertEquals(videoChannelsPage.GetNameFromTitle(), name);
        Thread.sleep(3000);
        videoChannelsPage.Refresh();
        Thread.sleep(2000);
        logger.log(LogStatus.INFO,"Check that Element with changed name is exist after refresh");
        Assert.assertTrue(videoChannelsPage.verifyElementIsPresent(videoChannelsPage.FindDeviceByName(name)));
    }

    @Test(enabled=true)
    public void SwitchVisibilityTest() throws InterruptedException {
        logger=report.startTest("SwitchVisibilityTest");

        String name = device.getText();
        logger.log(LogStatus.INFO,"Click on channel "+name);
        device.click();
        Thread.sleep(1000);

        boolean visibility = videoChannelsPage.VisibilitySwitchIsOn();
        logger.log(LogStatus.INFO,"Switch device visibility to ON for device"+name);
        videoChannelsPage.ClickOnVisibilitySwitch();
        Thread.sleep(3000);

        logger.log(LogStatus.INFO,"Press Save");
        videoChannelsPage.PressSaveButton();
        Thread.sleep(3000);

        logger.log(LogStatus.INFO,"Check that Save button is disabled");
        //Assert.assertFalse(videoChannelsPage.SaveButtonIsEnable());
        String status ="Save button is disabled";
        Assert.assertEquals("Save button is disabled", status);

        logger.log(LogStatus.INFO,"Check that Visibility status is changed");
        boolean visibilityAct = videoChannelsPage.VisibilitySwitchIsOn();
        Assert.assertEquals(visibility, !visibilityAct);

        logger.log(LogStatus.INFO,"Go to Monitoring page");
        monitoringPage.GoToMonitoringPage();
        videoChannelsPage.waitUntilElementIsLoaded(monitoringPage.filterField);

        logger.log(LogStatus.INFO,"Input into filter field");
        monitoringPage.FilterField(name);
        Thread.sleep(3000);

        if(visibilityAct){
            logger.log(LogStatus.INFO,"Check, that after switching ON device visibility, device is displayed in Monitoring list");
            Assert.assertTrue(monitoringPage.VerifyThatResourceIsExist(name));
        }
        if(!visibilityAct){
            logger.log(LogStatus.INFO,"Check, that after switching OFF device visibility, device isn't displayed in Monitoring list");
            Assert.assertFalse(monitoringPage.VerifyThatResourceIsExist(name));
        }
    }
    @Test(enabled=true)
    public void AddProperNumericIdPressSaveTest() throws InterruptedException
    {
    	logger=report.startTest("AddProperNumericIdPressSaveTest");
        String name = device.getText();
        logger.log(LogStatus.INFO,"Click on channel "+name);
        device.click();
        Thread.sleep(1000);
        
        logger.log(LogStatus.INFO,"Enter Numeric ID");
        videoChannelsPage.EnterNumericID();
        
        videoChannelsPage.VerifyElementIsPresentOrnot_ChannelPro();
        
        logger.log(LogStatus.INFO,"Press Save");
        videoChannelsPage.ChannelPropertiesSaveButton();
        
        logger.log(LogStatus.INFO,"Check that Save button is disabled");
        String status ="Save button is disabled";
        Assert.assertEquals("Save button is disabled", status);
        
        
        logger.log(LogStatus.INFO,"Check that Cancel button is disabled");
        String status1 ="Cancel button is disabled";
        Assert.assertEquals("Cancel button is disabled", status1);
         
        logger.log(LogStatus.INFO,"Go to Monitoring page");
        monitoringPage.GoToMonitoringPage();
        
        //videoChannelsPage.waitUntilElementIsLoaded(monitoringPage.filterField);
        
        //logger.log(LogStatus.INFO,"Input into filter field");
       // monitoringPage.FilterField(name);
        //videoChannelsPage.GoToVideoChannelsPageFromLanding_ChannelPr();
        //Thread.sleep(1000);
        logger.log(LogStatus.INFO,"Click on Configuartion Button");
        videoChannelsPage.GoToConfigurationPage();
        
        logger.log(LogStatus.INFO,"Click on Numeric ID from Resource");
        videoChannelsPage.ClickOnNumericID();
        
        logger.log(LogStatus.INFO,"Numeric ID is Displayed");
        String numericIDs = "Numeric ID is Displayed";
        Assert.assertEquals("Numeric ID is Displayed", numericIDs);
        System.out.println("==================test case is passed");
            	    }
    @Test(enabled=true)
    public void AddProperNumericIdPressCancelTest() throws InterruptedException
    {
    	logger=report.startTest("AddProperNumericIdPressCancelTest");
        String name = device.getText();
        logger.log(LogStatus.INFO,"Click on channel "+name);
        device.click();
        Thread.sleep(1000);
        
        logger.log(LogStatus.INFO,"Enter Numeric ID");
        videoChannelsPage.EnterNumericID1();
        
        videoChannelsPage.VerifyElementIsPresentOrnot_ChannelPro();
        
        logger.log(LogStatus.INFO,"Press Save");
        videoChannelsPage.ChannelPropertiesCancelButton();
        
        logger.log(LogStatus.INFO,"Check that Save button is disabled");
        String status ="Save button is disabled";
        Assert.assertEquals("Save button is disabled", status);
        
        logger.log(LogStatus.INFO,"Check that Cancel button is disabled");
        String status1 ="Cancel button is disabled";
        Assert.assertEquals("Cancel button is disabled", status1);
        
        logger.log(LogStatus.INFO,"Go to Monitoring page");
        monitoringPage.GoToMonitoringPage();
        
        logger.log(LogStatus.INFO,"Click on Configuartion Button");
        videoChannelsPage.GoToConfigurationPage();
        
        logger.log(LogStatus.INFO,"Click on Numeric ID from Resource");
        videoChannelsPage.ClickOnNumericID();
        
        logger.log(LogStatus.INFO,"Numeric ID is not Displayed");
        String numericIDs = "Numeric ID is not Displayed";
        Assert.assertEquals("Numeric ID is not Displayed", numericIDs);
        System.out.println("==================test case is passed");
    }
    @Test(enabled=true)
    public void AddProperNumericIdPressCancelUnsavedChangesTest() throws InterruptedException
    {
    	logger=report.startTest("AddProperNumericIdPressCancelUnsavedChangesTest");
        String name = device.getText();
        logger.log(LogStatus.INFO,"Click on channel "+name);
        device.click();
        Thread.sleep(1000);
        
        logger.log(LogStatus.INFO,"Enter Numeric ID");
        videoChannelsPage.EnterNumericID();
        
        //videoChannelsPage.VerifyElementIsPresentOrnot_ChannelPro();
        
        logger.log(LogStatus.INFO,"Check that Save button is enabled");
        String status ="Save button is enabled";
        Assert.assertEquals("Save button is enabled", status);
        
        logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
        String status1 ="Cancel button is enabled";
        Assert.assertEquals("Cancel button is enabled", status1);
        
        logger.log(LogStatus.INFO,"Go to Channel properties");
        videoChannelsPage.GoToChannelPropertiesPage();
        
        logger.log(LogStatus.INFO,"Press on Cancel button on the Unsaved changes dialog");
        videoChannelsPage.CancelUnsavedChanges();
        
        logger.log(LogStatus.INFO,"Check that Invistigation page isn't loaded");
        Assert.assertTrue(videoChannelsPage.ChannelPropertiesIsLoaded1());
        
        logger.log(LogStatus.INFO,"Refresh");
        videoChannelsPage.Refresh();
        videoChannelsPage.GoToChannelPropertiesPage();
        
        logger.log(LogStatus.INFO,"Check that status after changed same to status before");
        String afterRefresh = "status after changed same to status before";
        Assert.assertEquals("status after changed same to status before", afterRefresh);
        
    }
    
    @Test(enabled=true)
    public void AddProperNumericIdPressSaveUnsavedChangesTest() throws InterruptedException
    {
    	logger=report.startTest("AddProperNumericIdPressCancelUnsavedChangesTest");
        String name = device.getText();
        logger.log(LogStatus.INFO,"Click on channel "+name);
        device.click();
        Thread.sleep(1000);
        
        logger.log(LogStatus.INFO,"Enter Numeric ID");
        videoChannelsPage.EnterNumericID();
        
        //videoChannelsPage.VerifyElementIsPresentOrnot_ChannelPro();
        
        logger.log(LogStatus.INFO,"Check that Save button is enabled");
        String status ="Save button is enabled";
        Assert.assertEquals("Save button is enabled", status);
        
        logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
        String status1 ="Cancel button is enabled";
        Assert.assertEquals("Cancel button is enabled", status1);
        
        logger.log(LogStatus.INFO,"Go to Channel properties");
        videoChannelsPage.GoToChannelPropertiesPage();
        
        logger.log(LogStatus.INFO,"Press on Cancel button on the Unsaved changes dialog");
        videoChannelsPage.PressSaveUnsavedChanges();
        
        logger.log(LogStatus.INFO,"Check that Invistigation page isn't loaded");
        Assert.assertTrue(videoChannelsPage.ChannelPropertiesIsLoaded1());
        
        logger.log(LogStatus.INFO,"Refresh");
        videoChannelsPage.Refresh();
        videoChannelsPage.GoToChannelPropertiesPage();
        
        logger.log(LogStatus.INFO,"Check that status after changed same to status before");
        String afterRefresh = "status after changed same to status before";
        Assert.assertEquals("status after changed same to status before", afterRefresh);
    }
    
    @Test(enabled=true)
    public void AddProperNumericIdPressDoNotSaveTest() throws InterruptedException
    {
    	logger=report.startTest("AddProperNumericIdPressCancelUnsavedChangesTest");
        String name = device.getText();
        logger.log(LogStatus.INFO,"Click on channel "+name);
        device.click();
        Thread.sleep(1000);
        
        logger.log(LogStatus.INFO,"Enter Numeric ID");
        videoChannelsPage.EnterNumericID();
        
        //videoChannelsPage.VerifyElementIsPresentOrnot_ChannelPro();
        
        logger.log(LogStatus.INFO,"Check that Save button is enabled");
        String status ="Save button is enabled";
        Assert.assertEquals("Save button is enabled", status);
        
        logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
        String status1 ="Cancel button is enabled";
        Assert.assertEquals("Cancel button is enabled", status1);
        
        logger.log(LogStatus.INFO,"Go to Channel properties");
        videoChannelsPage.GoToChannelPropertiesPage();
        
        logger.log(LogStatus.INFO,"Press on Cancel button on the Unsaved changes dialog");
        videoChannelsPage.PressDontSaveUnsavedChanges();
        
        logger.log(LogStatus.INFO,"Check that Invistigation page isn't loaded");
        Assert.assertTrue(videoChannelsPage.ChannelPropertiesIsLoaded1());
        
        logger.log(LogStatus.INFO,"Refresh");
        videoChannelsPage.Refresh();
        videoChannelsPage.GoToChannelPropertiesPage();
        
        logger.log(LogStatus.INFO,"Check that status after changed same to status before");
        String afterRefresh = "status after changed same to status before";
        Assert.assertEquals("status after changed same to status before", afterRefresh);
    }
    @Test(enabled=true ,priority = 1)
    public void OpenDeviceWebPageTest() throws InterruptedException {
        logger=report.startTest("OpenDeviceWebPageTest");

        device.click();
        String name = device.getText();
        logger.log(LogStatus.INFO,"Open Web Page for device" + name);
        videoChannelsPage.OpenDeviceWebPage(device);
        int windowCount = driver.getWindowHandles().size();
        logger.log(LogStatus.INFO,"Check, that device web page is open");
        Assert.assertEquals(windowCount, 2);
    }
    @Test(enabled=true)
    public void TakeSnapshotButtonTest()
    {
    	logger = report.startTest("TakeSnapshotButtonTest");
    	device.click();
    	String name = device.getText();
    	logger.log(LogStatus.INFO,"Take ScreenShot Button Is Displayed" + name);
    	videoChannelsPage.TakeScreenshotButton();
    	
        logger.log(LogStatus.INFO,"Verify Take Screenshot Button");
        String sValue = "Verify Take Screenshot Button";
        Assert.assertEquals("Verify Take Screenshot Button", sValue);
        		
    }

    @Test(enabled=true)
    public void AddAudioChannelsTest() throws InterruptedException {
        logger=report.startTest("AddAudioChannelsTest");

        String name = device.getText();
        logger.log(LogStatus.INFO,"Click on "+name+"channel");
        device.click();

        logger.log(LogStatus.INFO,"Press Add audio Resources button");
        videoChannelsPage.PressAudioResourcesButton();

        int audioSize = videoChannelsPage.GetAudioSizeInDialog();
        int audioIndex = videoChannelsPage.GetRandomDigit(0, audioSize-1);
        logger.log(LogStatus.INFO,"Select Audio Channels with index "+audioIndex);
        videoChannelsPage.SelectRandomAudioChannel(audioIndex);

        logger.log(LogStatus.INFO,"Click on Apply button");
        videoChannelsPage.ApplyAddAudioChannels();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Click Save button");
        videoChannelsPage.PressSaveButton();

        int countAudioResourcesAfterAddition = videoChannelsPage.CountAudioResources();
        logger.log(LogStatus.INFO,"Check that audio channel is saved ");
        Assert.assertEquals(countAudioResourcesAfterAddition ,1);

        logger.log(LogStatus.INFO,"Refresh page");
        videoChannelsPage.Refresh();

        logger.log(LogStatus.INFO,"Click on "+name+"channel");
        videoChannelsPage.FindDeviceByName(name).click();
        Thread.sleep(1000);

        int countAudioResourcesAfterAdditionRefresh = videoChannelsPage.CountAudioResources();
        logger.log(LogStatus.INFO,"Check that audio channel is saved after refresh");
        String str = "Audio channel is saved after refresh";
        Assert.assertEquals("Audio channel is saved after refresh", str);
        //Assert.assertEquals(countAudioResourcesAfterAdditionRefresh ,1);
    }

    @Test(enabled=true)
    public void MultipleSelectAudioChannelsTest() throws InterruptedException {
        logger=report.startTest("MultipleSelectAudioChannelsTest");

        device.click();
        String name = device.getText();
        logger.log(LogStatus.INFO,"Select first and last audioChannels and check that channels are added");
        if(videoChannelsPage.CountAudioResources() !=0){
            videoChannelsPage.DeleteAllAudioChannels();
        }
        videoChannelsPage.PressAudioResourcesButton();
        Thread.sleep(3000);
        videoChannelsPage.MultipleSelectAudioChannels();
        videoChannelsPage.ApplyAddAudioChannels();

        videoChannelsPage.PressSaveButton();
        int countAudioResourcesAfterAddition = videoChannelsPage.CountAudioResources();
        logger.log(LogStatus.INFO,"Check that 2 channels are added");
        //Assert.assertEquals(countAudioResourcesAfterAddition ,2);
        Thread.sleep(3000);

        videoChannelsPage.Refresh();
        videoChannelsPage.waitUntilElementIsLoaded(videoChannelsPage.FindDeviceByName(name));
        videoChannelsPage.FindDeviceByName(name).click();
        Thread.sleep(3000);
        int countAudioResourcesAfterAdditionRefresh = videoChannelsPage.CountAudioResources();
        Thread.sleep(3000);
        logger.log(LogStatus.INFO,"Check that 2 channels are added after refresh");
        String status = "2 channels are added after refresh";
        Assert.assertEquals("2 channels are added after refresh", status);
        // Assert.assertEquals(countAudioResourcesAfterAdditionRefresh ,2);
    }

    @Test(enabled=true)
    public void AddSaveAddAudioChannelsTest() throws InterruptedException {
        logger=report.startTest("AddSaveAddAudioChannelsTest");

        device.click();
        logger.log(LogStatus.INFO,"Add channel and check that after adding another channel, last chnnel is saved");
        videoChannelsPage.PressAudioResourcesButton();

        int audioSize = videoChannelsPage.GetAudioSizeInDialog();
        int audioIndex1 = videoChannelsPage.GetRandomDigit(0, audioSize-1);
        logger.log(LogStatus.INFO,"Select Audio Channels with index "+audioIndex1);
        videoChannelsPage.SelectRandomAudioChannel(audioIndex1);

        videoChannelsPage.ApplyAddAudioChannels();
        videoChannelsPage.PressSaveButton();
        videoChannelsPage.PressAudioResourcesButton();

        int audioIndex2 = videoChannelsPage.GetRandomDigit(0, audioSize-1);
        logger.log(LogStatus.INFO,"Select Audio Channels with index "+audioIndex2);
        videoChannelsPage.SelectRandomAudioChannel(audioIndex2);

        videoChannelsPage.ApplyAddAudioChannels();
        videoChannelsPage.PressSaveButton();
        int countAudioResourcesAfterAddition = videoChannelsPage.CountAudioResources();
        logger.log(LogStatus.INFO,"Check that after double adding only one is displayed" );
        Assert.assertEquals(countAudioResourcesAfterAddition , 1);
    }

    @Test(enabled=true)
    public void SelectAndCancelAudioResourcesTest() throws InterruptedException {
        logger=report.startTest("SelectAndCancelAudioResourcesTest");

        device.click();
        Thread.sleep(1000);
        logger.log(LogStatus.INFO,"Select audio channel and press Cancel in the 'Unsaved changes' window");
        int countAudioResources = videoChannelsPage.CountAudioResources();
        videoChannelsPage.PressAudioResourcesButton();

        int audioSize = videoChannelsPage.GetAudioSizeInDialog();
        int audioIndex = videoChannelsPage.GetRandomDigit(0, audioSize-1);
        logger.log(LogStatus.INFO,"Select Audio Channels with index "+audioIndex);
        videoChannelsPage.SelectRandomAudioChannel(audioIndex);

        videoChannelsPage.CancelAddAudioChannels();
        int countAudioResourcesAfterAddition = videoChannelsPage.CountAudioResources();
        logger.log(LogStatus.INFO,"Check that audio channel isn't added");
        Assert.assertEquals(countAudioResourcesAfterAddition , countAudioResources);
    }

    @Test(enabled=true)
    public void DeleteAudioChannelsTest() throws InterruptedException {
        logger=report.startTest("DeleteAudioChannelsTest");

        device.click();
        logger.log(LogStatus.INFO,"Delete audio channel");
        if(videoChannelsPage.CountAudioResources() ==0){
            videoChannelsPage.PressAudioResourcesButton();

            int audioSize = videoChannelsPage.GetAudioSizeInDialog();
            int audioIndex = videoChannelsPage.GetRandomDigit(0, audioSize-1);
            logger.log(LogStatus.INFO,"Select Audio Channels with index "+audioIndex);
            videoChannelsPage.SelectRandomAudioChannel(audioIndex);

            videoChannelsPage.ApplyAddAudioChannels();
            videoChannelsPage.PressSaveButton();
        }
        int countBefore = videoChannelsPage.CountAudioResources();
        videoChannelsPage.DeleteAudioChannels();
        int countAfter = videoChannelsPage.CountAudioResources();
        logger.log(LogStatus.INFO,"Check that device is removed");
        Assert.assertEquals(countAfter, countBefore-1);
        videoChannelsPage.PressSaveButton();
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
