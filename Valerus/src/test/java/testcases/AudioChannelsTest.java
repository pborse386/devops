package testcases;

import pageObjects.AudioChannelsPage;
import pageObjects.VideoChannelsPage;
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


public class AudioChannelsTest {
    public WebDriver driver;

    public AudioChannelsPage audioChannelsPage;
    public VideoChannelsPage videoChannelsPage;
    public String[] Servers;

    WebElement channel;
    ExtentReports report;
    ExtentTest logger;

    @BeforeClass(alwaysRun = true)
    public void setup() throws IOException, InterruptedException {
        

        /*  System.setProperty("webdriver.chrome.driver", WebDriverLocation+"\\chromedriver.exe");
      driver = new ChromeDriver();    //Works*/
       
       
    }

    @BeforeTest
    public void startReport(){
        report=new ExtentReports(System.getProperty("user.dir") +"/test-output/AudioChannels/AudioChannelReport.html", true);
    }

    @BeforeMethod(alwaysRun = true)
    public void GoToAudioChannelsPage() throws InterruptedException, IOException {
        
    	 String WebDriverLocation = System.getenv("WebDriver");
    	 System.setProperty("webdriver.ie.driver", WebDriverLocation+"\\IEDriverServer.exe");
         DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
         capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
         capabilities.setCapability(InternetExplorerDriver.IE_USE_PER_PROCESS_PROXY, true);
         capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
         capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
         driver = new InternetExplorerDriver(capabilities);  
                        
         
         
         audioChannelsPage = PageFactory.initElements(driver, AudioChannelsPage.class);
         videoChannelsPage = PageFactory.initElements(driver, VideoChannelsPage.class);
         Servers = audioChannelsPage.getServersList();
         driver.get("http://" + Servers[0]);
         driver.manage().window().maximize();
         audioChannelsPage.SignIn();

         audioChannelsPage.WaitUntilLoadingBlockAppears();
         audioChannelsPage.WaitUntilLoadingBlockDisappears();
         audioChannelsPage.GoToAudioChannelsPageFromLanding();
    	
    	
         
    	driver.get("http://" + Servers[0]);
        try{
            driver.switchTo().alert().accept();
        }catch(Exception a){}

        audioChannelsPage.WaitUntilLoadingBlockAppears();
        audioChannelsPage.WaitUntilLoadingBlockDisappears();
        audioChannelsPage.GoToAudioChannelsPage();

        channel = audioChannelsPage.SelectRandomChannel();
    }

    @Test
    public void SortAudioChannelsByName(){
        logger=report.startTest("SortAudioChannelsByName");

        logger.log(LogStatus.INFO,"Collapse alll channels ");
        if(!audioChannelsPage.ChannelsAreCollapsed()){
           audioChannelsPage.ClickOnExpendCollapsePlusButton();
       }

        logger.log(LogStatus.INFO,"Click on Name button");
        audioChannelsPage.ClickOnNameButton();

        logger.log(LogStatus.INFO,"Get first and last names before sort");
        int size = audioChannelsPage.GetVendorsSize();
        String firstName = audioChannelsPage.GetVendorText(0);
        String lastName = audioChannelsPage.GetVendorText(size-1);

        logger.log(LogStatus.INFO,"Click on Name button to sort names");
        audioChannelsPage.ClickOnNameButton();

        logger.log(LogStatus.INFO,"Get first and last names after sort");
        String firstNameAfterSort = audioChannelsPage.GetVendorText(0);
        String lastNameAfterSort = audioChannelsPage.GetVendorText(size-1);

        logger.log(LogStatus.INFO,"Check that first name after sort became last, and last became first");
        Assert.assertEquals(firstNameAfterSort, lastName);
        Assert.assertEquals(lastNameAfterSort, firstName);
    }

    @Test
    public void FilterAudioChannelsTest() throws InterruptedException {
        logger=report.startTest("FilterAudioChannelsTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"input name "+channelText+"into Filter Field");
        audioChannelsPage.FilterCamera(channelText);

        logger.log(LogStatus.INFO,"Check that only devices with inputed name are displayed ");
        int s = audioChannelsPage.GetChannelsListSize();
        for(int i = 0; i<s; i++){
            String n = audioChannelsPage.GetChannelText(i);
            Assert.assertTrue(n.contains(channelText) );
        }
    }

    @Test
    public void ChangeNameAndSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeNameAndSaveTest");
        String channelText = channel.getText();

        logger.log(LogStatus.INFO,"Click on the channel "+channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        String channelName = audioChannelsPage.GetNameText();
        String newName = channelName + "New";

        logger.log(LogStatus.INFO,"Change name from " + channelName + "to " + newName+ " for channel " + channelText);
        audioChannelsPage.InputIntoNameField(newName);

        logger.log(LogStatus.INFO,"Press Save button");
        audioChannelsPage.PressSaveButton();

        logger.log(LogStatus.INFO,"Check that dialog isn't appeared");
        Assert.assertFalse(audioChannelsPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that name is changed");
        String relevantName = audioChannelsPage.GetNameText();
        Assert.assertEquals(relevantName, newName);

        logger.log(LogStatus.INFO,"Refresh page");
        audioChannelsPage.Refresh();

        logger.log(LogStatus.INFO,"Click on the channel "+newName);
        audioChannelsPage.FindChannelByText(newName).click();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that name is changed after refresh");
        String nameAfterRefresh = audioChannelsPage.GetNameText();
        Assert.assertEquals(nameAfterRefresh, newName);
    }

   @Test
    public void ChangeVisibilityTest() throws InterruptedException {
       logger=report.startTest("ChangeVisibilityTest");
       String channelText = channel.getText();

       logger.log(LogStatus.INFO,"Click on the channel "+channelText);
       audioChannelsPage.ClickOnChannel(channelText);

       String visibility = audioChannelsPage.GetVisiblePosition();

       audioChannelsPage.SwitchVisibleToggler();

       logger.log(LogStatus.INFO,"Press Save button");
       audioChannelsPage.PressSaveButton();

       logger.log(LogStatus.INFO,"Check that dialog isn't appeared");
       Assert.assertFalse(audioChannelsPage.CheckThatModalIsOpen());

       String relevantVisibility = audioChannelsPage.GetVisiblePosition();
       if(visibility.equals("true")) Assert.assertEquals(relevantVisibility, "false");
       if(visibility.equals("false")) Assert.assertEquals(relevantVisibility, "true");

       logger.log(LogStatus.INFO,"Refresh page");
       audioChannelsPage.Refresh();
       Thread.sleep(3000);

       Thread.sleep(1000);
       logger.log(LogStatus.INFO,"Click on the channel "+channelText);
       audioChannelsPage.FindDeviceByName(channelText).click();
       Thread.sleep(1000);

       logger.log(LogStatus.INFO,"Check that name is changed after refresh");
       String visibilityAfterRefresh = audioChannelsPage.GetVisiblePosition();
       if(visibility.equals("true")) Assert.assertEquals(visibilityAfterRefresh, "false");
       if(visibility.equals("false")) Assert.assertEquals(visibilityAfterRefresh, "true");
   }

   //@Test
    public void AudioPlayerTest() throws InterruptedException {
       logger=report.startTest("AudioPlayerTest");
       String channelText = channel.getText();

       logger.log(LogStatus.INFO,"Click on the channel "+channelText);
       audioChannelsPage.ClickOnChannel(channelText);

       audioChannelsPage.PressOnPlayPlayer();

       Assert.assertTrue(audioChannelsPage.StopIsEnable());
       Assert.assertFalse(audioChannelsPage.PlayIsEnable());
   }

   @Test
    public void AddAudioResourcesInVideoChannelTest() throws InterruptedException {
       logger=report.startTest("AddAudioResourcesInVideoChannelTest");

       logger.log(LogStatus.INFO,"Go to VideoChannels ");
       videoChannelsPage.ClickOnVideoChannels();

       WebElement element = videoChannelsPage.SelectRandomDevice();
       String name = element.getText();
       logger.log(LogStatus.INFO,"Click on video channel "+name);
       element.click();

       logger.log(LogStatus.INFO,"Press Add audio Resources ");
       videoChannelsPage.PressAudioResourcesButton();

       int audioSize = videoChannelsPage.GetAudioSizeInDialog();
       int audioIndex = videoChannelsPage.GetRandomDigit(0, audioSize-1);
       logger.log(LogStatus.INFO,"Select Audio Channels with index "+audioIndex);
       videoChannelsPage.SelectRandomAudioChannel(audioIndex);

       videoChannelsPage.ApplyAddAudioChannels();
       Thread.sleep(1000);

       logger.log(LogStatus.INFO,"Press Save button");
       videoChannelsPage.PressSaveButton();

       logger.log(LogStatus.INFO,"Check that dialog isn't appeared");
       Assert.assertFalse(audioChannelsPage.CheckThatModalIsOpen());

       String addedAudioChannel = videoChannelsPage.GetAddedAudioResources(0);
       logger.log(LogStatus.INFO,"Go to Audio Channel");
       audioChannelsPage.ClickAudioChannelButon();

       logger.log(LogStatus.INFO,"Click on added audio channel "+addedAudioChannel);
       audioChannelsPage.ClickOnChannel(addedAudioChannel);

       logger.log(LogStatus.INFO,"Check that video channel "+name+" is displayed in Linked Video Resources of audio channel "+ addedAudioChannel);

       boolean flag = false;
       for(int i=0; i<audioChannelsPage.GetLinkedVideoResourcesSize(); i++){
           String videoResources = audioChannelsPage.GetLinkedVideoResourcesText(i);
           if(videoResources.equals(name)){
               flag = true;
               break;
           }
       }
       Assert.assertTrue(flag);
   }

   @Test
    public void ChangeProtocolTest() throws InterruptedException {
       logger=report.startTest("ChangeProtocolTest");

       String channelText = channel.getText();

       logger.log(LogStatus.INFO,"Click on the channel "+channelText);
       audioChannelsPage.ClickOnChannel(channelText);

       logger.log(LogStatus.INFO,"Go to Streams page ");
       audioChannelsPage.GoToStreamsPage();

       logger.log(LogStatus.INFO,"Search index of option, that not same to index of selected option");
       int index=0;
       String selectedOption = audioChannelsPage.GetSelectedOptionText();
       String optionByIndex=null;
       while(true){
           int size =audioChannelsPage.GetOptionsSize();
           int random = audioChannelsPage.GetRandomDigit(0, size);
           optionByIndex = audioChannelsPage.GetOptionTextByIndex(random);
           if(!optionByIndex.equals(selectedOption)){
               index = random;
               break;
           }
       }
       logger.log(LogStatus.INFO,"Select option wih index "+index);
       optionByIndex = audioChannelsPage.GetOptionTextByIndex(index);
       audioChannelsPage.SelectOptionByIndex(index);

       logger.log(LogStatus.INFO,"Press Save button");
       audioChannelsPage.PressSaveButton();

       logger.log(LogStatus.INFO,"Check that dialog isn't appeared");
       Assert.assertFalse(audioChannelsPage.CheckThatModalIsOpen());

       logger.log(LogStatus.INFO,"Check that change is saved");
       String relevantOption = audioChannelsPage.GetSelectedOptionText();
       Assert.assertEquals(relevantOption,optionByIndex );

       logger.log(LogStatus.INFO,"Refresh page");
       audioChannelsPage.Refresh();
//       Thread.sleep(3000);

       logger.log(LogStatus.INFO,"Go to Streams page ");
       audioChannelsPage.GoToStreamsPage();

       logger.log(LogStatus.INFO,"Click on the channel "+channelText);
       audioChannelsPage.ClickOnChannel(channelText);

       logger.log(LogStatus.INFO,"Check that protocol is changed after refresh");
       String relevantOptionAfterRefresh = audioChannelsPage.GetSelectedOptionText();
       Assert.assertEquals(relevantOptionAfterRefresh,optionByIndex );
   }

   //Recording
   @Test
   public void SwitchRecordingToggleTest() throws InterruptedException {
       logger=report.startTest("SwitchRecordingToggleTest");

       String channelText = channel.getText();
       logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
       audioChannelsPage.ClickOnChannel(channelText);

       logger.log(LogStatus.INFO,"Go to Recording Page");
       audioChannelsPage.GoToRecordingPage();

       boolean status= audioChannelsPage.RecordingToggleIsOn();
       logger.log(LogStatus.INFO,"Switch Recording");
       audioChannelsPage.SwitchRecordingToggle();

       logger.log(LogStatus.INFO,"Press Save button");
       audioChannelsPage.PressSaveButton();

       logger.log(LogStatus.INFO,"Check that dialog isn't appeared");
       Assert.assertFalse(audioChannelsPage.CheckThatModalIsOpen());

       boolean statusAfterSwitch= audioChannelsPage.RecordingToggleIsOn();
       logger.log(LogStatus.INFO,"Check that status after changed doesn't match status before");
       Assert.assertNotEquals(status,statusAfterSwitch );

       logger.log(LogStatus.INFO,"Refresh");
       audioChannelsPage.Refresh();

       logger.log(LogStatus.INFO,"Go to Recording Page");
       audioChannelsPage.GoToRecordingPage();

       logger.log(LogStatus.INFO,"Click on the device "+ channelText);
       audioChannelsPage.ClickOnChannel(channelText);

       logger.log(LogStatus.INFO,"Check that status after refresh matchs status before");
       boolean statusAfterRefresh= audioChannelsPage.RecordingToggleIsOn();
       Assert.assertEquals(status,!statusAfterRefresh );
   }

    @Test
    public void SwitchLimitRetentionToggleTest() throws InterruptedException {
        logger=report.startTest("SwitchLimitRetentionToggleTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Check Limit Retention Toggler is On or Off for device"+channelText);
        boolean status= audioChannelsPage.LimitRetentionToggleIsOn();

        logger.log(LogStatus.INFO,"Switch Limit Retention Toggler");
        audioChannelsPage.SwitchLimitRetentionToggle();

        logger.log(LogStatus.INFO,"Press Save button");
        audioChannelsPage.PressSaveButton();

        logger.log(LogStatus.INFO,"Check that dialog isn't appeared");
        Assert.assertFalse(audioChannelsPage.CheckThatModalIsOpen());

        boolean statusAfterSwitch= audioChannelsPage.LimitRetentionToggleIsOn();
        logger.log(LogStatus.INFO,"Check that Limit Retention status is changed");
        if(status ==true)Assert.assertFalse(statusAfterSwitch);
        if(status ==false)Assert.assertTrue(statusAfterSwitch);

        logger.log(LogStatus.INFO,"Refresh");
        audioChannelsPage.Refresh();

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Check that status after refresh matchs status before");
        boolean statusAfterRefresh= audioChannelsPage.LimitRetentionToggleIsOn();
        if(status ==true)Assert.assertFalse(statusAfterRefresh);
        if(status ==false)Assert.assertTrue(statusAfterRefresh);
    }

    @Test 
    public void SwitchRecordingIfLimitRetentionIsOnTest() throws InterruptedException {
        logger=report.startTest("SwitchRecordingIfLimitRetentionIsOnTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Check Limit Retention Toggler is On or Off for device"+channelText);
        boolean status= audioChannelsPage.LimitRetentionToggleIsOn();

        if(!status){
            logger.log(LogStatus.INFO,"Switch ON Limit Retention Toggler");
            audioChannelsPage.SwitchLimitRetentionToggle();

            logger.log(LogStatus.INFO,"Save changes");
            audioChannelsPage.PressSaveButton();
        }

        boolean recordStatusBefore= audioChannelsPage.RecordingToggleIsOn();
        logger.log(LogStatus.INFO,"Switch Recording Toggler");
        audioChannelsPage.SwitchRecordingToggle();

        logger.log(LogStatus.INFO,"Press Save button");
        audioChannelsPage.PressSaveButton();

        logger.log(LogStatus.INFO,"Check that dialog isn't appeared");
        Assert.assertFalse(audioChannelsPage.CheckThatModalIsOpen());

        boolean recordStatusAfter= audioChannelsPage.RecordingToggleIsOn();
        logger.log(LogStatus.INFO,"Check that recording status is changed");
        Assert.assertEquals(recordStatusBefore,!recordStatusAfter );

        logger.log(LogStatus.INFO,"Go to Streams page");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Check that Cancel and Save buttons are disabled");
        Assert.assertFalse(audioChannelsPage.CancelButtonIsEnable());
        Assert.assertFalse(audioChannelsPage.SaveButtonIsEnable());
    }

    @Test
    public void ChangeLimitRetentionValueTest() throws InterruptedException {
        logger=report.startTest("ChangeLimitRetentionValueTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        boolean status= audioChannelsPage.LimitRetentionToggleIsOn();
        if(status == false){
            logger.log(LogStatus.INFO,"Click on limit retention toggle-switch");
            audioChannelsPage.SwitchLimitRetentionToggle();
        }

        int count = audioChannelsPage.GetRandomDigit(0, 1000);
        logger.log(LogStatus.INFO,"Input value " + count+ "Into Limit Retention Field");
        audioChannelsPage.InputIntoLimitRetentionField(""+count);

        logger.log(LogStatus.INFO,"Change Limit Retention By Spinners");
        audioChannelsPage.ClickSpinnerUpLimitRetention();
        audioChannelsPage.ClickSpinnerUpLimitRetention();
        audioChannelsPage.ClickSpinnerDownLimitRetention();

        logger.log(LogStatus.INFO,"Press Save button");
        audioChannelsPage.PressSaveButton();

        logger.log(LogStatus.INFO,"Check that Save button is enabled");
        Assert.assertFalse(audioChannelsPage.SaveButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that dialog isn't appeared");
        Assert.assertFalse(audioChannelsPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that change is saved");
        int limRetention =Integer.parseInt(audioChannelsPage.GetLimitRetention());
        Assert.assertEquals(limRetention, count+2-1);

        logger.log(LogStatus.INFO,"Refresh page");
        audioChannelsPage.Refresh();

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Check that change is saved after refresh");
        int limRetentionAfterRefresh =Integer.parseInt(audioChannelsPage.GetLimitRetention());
        Assert.assertEquals(limRetentionAfterRefresh, limRetention);
    }

    @Test
    public void SelectScheduleTest() throws InterruptedException {
        logger=report.startTest("SelectScheduleTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Check Limit Retention Toggler is On or Off for device"+channelText);
        boolean status= audioChannelsPage.LimitRetentionToggleIsOn();

        if(status){
            logger.log(LogStatus.INFO,"Switch OFF Limit Retention Toggler");
            audioChannelsPage.SwitchLimitRetentionToggle();

            logger.log(LogStatus.INFO,"Save changes");
            audioChannelsPage.PressSaveButton();
        }
        audioChannelsPage.IfScheduleIsNotExistAddIt();

        logger.log(LogStatus.INFO,"Create new Schedule");
        audioChannelsPage.SelectSchedule(0, "New Schedule");
        String name = "NewName"+audioChannelsPage.GetRandomDigit(1, 1000);
        audioChannelsPage.InputIntoNameNewSchedule(name);
        audioChannelsPage.PresssaveNewSchedule();

        logger.log(LogStatus.INFO,"Select created schedule");
        audioChannelsPage.SelectSchedule(0, name);

        logger.log(LogStatus.INFO,"Save changes");
        audioChannelsPage.PressSaveButton();

        logger.log(LogStatus.INFO,"Check that dialog isn't appeared");
        Assert.assertFalse(audioChannelsPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that change is saved");
        String selectedOption =audioChannelsPage.GetSelectedScheduleOption(0);
        Assert.assertEquals(selectedOption, name);

//        logger.log(LogStatus.INFO,"Go to Streams page");
//        audioChannelsPage.GoToStreamsPage();
//
//        logger.log(LogStatus.INFO,"Go to Recording Page");
//        audioChannelsPage.GoToRecordingPage();
//
//        Assert.assertFalse(audioChannelsPage.CancelButtonIsEnable());
//        Assert.assertFalse(audioChannelsPage.SaveButtonIsEnable());

        logger.log(LogStatus.INFO,"Refresh page");
        audioChannelsPage.Refresh();

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Check that change is saved after refresh");
        String selectedOptionAfterRefresh =audioChannelsPage.GetSelectedScheduleOption(0);
        Assert.assertEquals(selectedOptionAfterRefresh, name);
    }

    @Test
    public void ChangeRecordingModeTest() throws InterruptedException {
        logger=report.startTest("ChangeRecordingModeTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Check Limit Retention Toggler is On or Off for device"+channelText);
        boolean status= audioChannelsPage.LimitRetentionToggleIsOn();

        if(status){
            logger.log(LogStatus.INFO,"Switch OFF Limit Retention Toggler");
            audioChannelsPage.SwitchLimitRetentionToggle();

            logger.log(LogStatus.INFO,"Save changes");
            audioChannelsPage.PressSaveButton();
        }
        audioChannelsPage.IfScheduleIsNotExistAddIt();

        logger.log(LogStatus.INFO,"Select recording Mode");
//        audioChannelsPage.SelectSchedule("24/7");
        int max = audioChannelsPage.CountOfOptionsInRecordingModeSelect(0);
        int index = 0;
        for(int i=0 ; i<=5; i++){
            String selectedMode =audioChannelsPage.GetSelectedRecordingModeOption(0);
            int random = audioChannelsPage.GetRandomDigit(0,max);
            String mode = audioChannelsPage.GetOptionsByIndex(0, random).getText();
            if(!selectedMode.equals(mode)) {
                index = random;
                break;
            }
        }
        audioChannelsPage.SelectRecordingModeByIndex(0, index);
        String mode = audioChannelsPage.GetOptionsByIndex(0, index).getText();

        logger.log(LogStatus.INFO,"Save changes");
        audioChannelsPage.PressSaveButton();

        logger.log(LogStatus.INFO,"Check that dialog isn't appeared");
        Assert.assertFalse(audioChannelsPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that change is saved");
        String selectedOption =audioChannelsPage.GetSelectedRecordingModeOption(0);
        Assert.assertEquals(selectedOption, mode);

        logger.log(LogStatus.INFO,"Refresh page");
        audioChannelsPage.Refresh();

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Check that change is saved after refresh");
        String selectedOptionAfterRefresh =audioChannelsPage.GetSelectedRecordingModeOption(0);
        Assert.assertEquals(selectedOptionAfterRefresh, mode);
    }

    @Test
    public void ChangePreEventTimeTest() throws InterruptedException {
        logger=report.startTest("ChangePreEventTimeTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        audioChannelsPage.IfScheduleIsNotExistAddIt();
        logger.log(LogStatus.INFO,"Select Event recording Mode");
        audioChannelsPage.SelectRecordingModeOption(0, "Event");

        logger.log(LogStatus.INFO,"Change pre event seconds");
        int random = audioChannelsPage.GetRandomDigit(0,14);
        audioChannelsPage.InputPreEventSeconds(""+random);
        audioChannelsPage.ClickOnPreEventUpSpinner();
        audioChannelsPage.ClickOnPreEventUpSpinner();
        audioChannelsPage.ClickOnPreEventDownSpinner();

        logger.log(LogStatus.INFO,"Press save button");
        audioChannelsPage.PressSaveButton();

        logger.log(LogStatus.INFO,"Check that dialog isn't appeared");
        Assert.assertFalse(audioChannelsPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that  pre event time is saved");
        String preEvent = audioChannelsPage.GetPreEventText();
        Assert.assertEquals(preEvent, ""+(random+2-1));

        logger.log(LogStatus.INFO,"Refresh page");
        audioChannelsPage.Refresh();
        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Check that pre event time is saved after refresh");
        String preEventAfterRefresh = audioChannelsPage.GetPreEventText();
        Assert.assertEquals(preEventAfterRefresh, ""+(random+2-1));
    }

    @Test
    public void ChangePostEventTimeTest() throws InterruptedException {
        logger=report.startTest("ChangePostEventTimeTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        audioChannelsPage.IfScheduleIsNotExistAddIt();

        logger.log(LogStatus.INFO,"Select Event recording Mode");
        audioChannelsPage.SelectRecordingModeOption(0, "Event");

        logger.log(LogStatus.INFO,"Change post event seconds");
        int postEvRandom = audioChannelsPage.GetRandomDigit(0,100);
        audioChannelsPage.InputPostEventSeconds(""+postEvRandom);
        audioChannelsPage.ClickOnPostEventUpSpinner();
        audioChannelsPage.ClickOnPostEventDownSpinner();
        audioChannelsPage.ClickOnPostEventDownSpinner();

        logger.log(LogStatus.INFO,"Press save button");
        audioChannelsPage.PressSaveButton();

        logger.log(LogStatus.INFO,"Check that dialog isn't appeared");
        Assert.assertFalse(audioChannelsPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that  post event time is saved");
        String postEvent = audioChannelsPage.GetPostEventText();
        Assert.assertEquals(postEvent, ""+(postEvRandom+1-2));

        logger.log(LogStatus.INFO,"Refresh page");
        audioChannelsPage.Refresh();
        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Check that parametrs are saved");
        String postEventAfterRefresh = audioChannelsPage.GetPostEventText();
        Assert.assertEquals(postEventAfterRefresh, ""+(postEvRandom+1-2));
    }

    @Test
    public void MultipleSelectAndDeleteSpecifyEventsTest() throws InterruptedException {
        logger=report.startTest("MultipleSelectAndDeleteSpecifyEventsTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

//        logger.log(LogStatus.INFO,"Check Limit Retention Toggler is On or Off for device"+channelText);
//        boolean status= audioChannelsPage.LimitRetentionToggleIsOn();
//
//        if(status){
//            logger.log(LogStatus.INFO,"Switch OFF Limit Retention Toggler");
//            audioChannelsPage.SwitchLimitRetentionToggle();
//
//            logger.log(LogStatus.INFO,"Save changes");
//            audioChannelsPage.PressSaveButton();
//        }

        audioChannelsPage.IfScheduleIsNotExistAddIt();
        logger.log(LogStatus.INFO,"Select Event recording Mode");
        audioChannelsPage.SelectRecordingModeOption(0, "Event");

        logger.log(LogStatus.INFO,"press on Specify Events button");
        audioChannelsPage.PressOnSpecifyEventsButton();

        logger.log(LogStatus.INFO,"Delete events if them are exist");
        int sizeSelectedEvents = audioChannelsPage.CountSelectedEventsInSpecifyEvents();
        for(int i=0; i<sizeSelectedEvents; i++){
            audioChannelsPage.DeleteSelectedEventInSpecifyEvents(0);
        }

        logger.log(LogStatus.INFO,"Click on Event Type field");
        audioChannelsPage.ClickOnEventTypeField();

        logger.log(LogStatus.INFO,"Select option randomaly");
        int random = audioChannelsPage.GetRandomDigit(1, audioChannelsPage.CountEventTypeOptions());
        String eventName = audioChannelsPage.GetEventTypeOptionByIndex(random);

        logger.log(LogStatus.INFO,"Select "+eventName + " option ");
        audioChannelsPage.SelectEventTypeOptionByIndex(random);

        if(eventName.equals("Tamper")) {
            int postOptionIndex = audioChannelsPage.GetRandomDigit(0, 4);
            String postOptionName = audioChannelsPage.GetEventTypeOptionByIndex(postOptionIndex);

            logger.log(LogStatus.INFO, "Select " + postOptionName + " post option ");
            audioChannelsPage.SelectEventTypePostOptionByIndex(postOptionIndex);
        }

        if(eventName.equals("Native Camera Analytics")){
            int postOptionIndex = audioChannelsPage.GetRandomDigit(4, 6);
            String postOptionName = audioChannelsPage.GetEventTypeOptionByIndex(postOptionIndex);

            logger.log(LogStatus.INFO, "Select " + postOptionName + " post option ");
            audioChannelsPage.SelectEventTypePostOptionByIndex(postOptionIndex);
        }

        Thread.sleep(1000);
        int size = audioChannelsPage.CountOfDevicesInSpecifyEventsWindow();
        if(size >1){
            int index = audioChannelsPage.GetRandomDigit(1, size-2);

            logger.log(LogStatus.INFO,"Select first and last device");
            audioChannelsPage.ClickOnDeviceInSpecifyEventsDevicesList(0);
            audioChannelsPage.ClickOnDeviceInSpecifyEventsDevicesList(size-1);

            logger.log(LogStatus.INFO,"Press on Apply and Close button");
            audioChannelsPage.PressOnApplyAndCloseButtonInSpecifyEvents();

            logger.log(LogStatus.INFO,"Check that two events are added in events list");
            int eventsSize = audioChannelsPage.GetSelectedEventsSize();
            Assert.assertEquals(eventsSize, 2);

            logger.log(LogStatus.INFO,"Press on Save button");
            audioChannelsPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that dialog isn't appeared");
            Assert.assertFalse(audioChannelsPage.CheckThatModalIsOpen());

            logger.log(LogStatus.INFO,"Check that two events are added in events list");
            int eventsSizeAfterSaving = audioChannelsPage.GetSelectedEventsSize();
            Assert.assertEquals(eventsSizeAfterSaving, 2);

            logger.log(LogStatus.INFO,"Delete first event");
            audioChannelsPage.ClickOnDeleteEventIcon(eventsSize-1);

            logger.log(LogStatus.INFO,"Check that event is removed");
            int eventsSizeAfterRemoving = audioChannelsPage.GetSelectedEventsSize();
            Assert.assertEquals(eventsSizeAfterRemoving, 1);

            logger.log(LogStatus.INFO,"Press on Save button");
            audioChannelsPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that event is removed");
            eventsSizeAfterRemoving = audioChannelsPage.GetSelectedEventsSize();
            Assert.assertEquals(eventsSizeAfterRemoving, 1);

            logger.log(LogStatus.INFO,"Refresh");
            audioChannelsPage.Refresh();

            logger.log(LogStatus.INFO,"Go to Recording Page");
            audioChannelsPage.GoToRecordingPage();

            logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
            audioChannelsPage.ClickOnChannel(channelText);

            logger.log(LogStatus.INFO,"Check that one event exist in list after refresh");
            int eventsSizeAfterRefresh = audioChannelsPage.GetSelectedEventsSize();
            Assert.assertEquals(eventsSizeAfterRefresh, 1);
        }
    }

    @Test
    public void SelectAllDevicesInSpecifyEventsTest() throws InterruptedException {
        logger=report.startTest("SelectAllDevicesInSpecifyEventsTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        audioChannelsPage.IfScheduleIsNotExistAddIt();
        logger.log(LogStatus.INFO,"select Event recording Mode");
        audioChannelsPage.SelectRecordingModeOption(0, "Event");

        logger.log(LogStatus.INFO,"press on Specify Events button");
        audioChannelsPage.PressOnSpecifyEventsButton();

        logger.log(LogStatus.INFO,"Delete events if them are exist");
        int sizeSelectedEvents = audioChannelsPage.CountSelectedEventsInSpecifyEvents();
        for(int i=0; i<sizeSelectedEvents; i++){
            audioChannelsPage.DeleteSelectedEventInSpecifyEvents(0);
        }

        logger.log(LogStatus.INFO,"Click on Event Type field");
        audioChannelsPage.ClickOnEventTypeField();

        logger.log(LogStatus.INFO,"Select option ");
        int optionsSize = audioChannelsPage.CountEventTypeOptions();
        int random = audioChannelsPage.GetRandomDigit(1, optionsSize);
        String eventName = audioChannelsPage.GetEventTypeOptionByIndex(random);

        logger.log(LogStatus.INFO,"Select "+eventName + " option ");
        audioChannelsPage.SelectEventTypeOptionByIndex(random);

        if(eventName.equals("Tamper")) {
            int postOptionIndex = audioChannelsPage.GetRandomDigit(0, 4);
            String postOptionName = audioChannelsPage.GetEventTypeOptionByIndex(postOptionIndex);

            logger.log(LogStatus.INFO, "Select " + postOptionName + " post option ");
            audioChannelsPage.SelectEventTypePostOptionByIndex(postOptionIndex);
        }

        if(eventName.equals("Native Camera Analytics")){
            int postOptionIndex = audioChannelsPage.GetRandomDigit(4, 6);
            String postOptionName = audioChannelsPage.GetEventTypeOptionByIndex(postOptionIndex);

            logger.log(LogStatus.INFO, "Select " + postOptionName + " post option ");
            audioChannelsPage.SelectEventTypePostOptionByIndex(postOptionIndex);
        }

        audioChannelsPage.ClickOnSelectAllButtonInSpecifyevents();

        logger.log(LogStatus.INFO,"Check that all devices are selected");
        int size = audioChannelsPage.CountOfDevicesInSpecifyEventsWindow();
        sizeSelectedEvents = audioChannelsPage.CountSelectedEventsInSpecifyEvents();
        if(size==19) Assert.assertTrue(sizeSelectedEvents>= size);
        if(size<19) Assert.assertEquals(sizeSelectedEvents, size);

        logger.log(LogStatus.INFO,"Press Apply button");
        audioChannelsPage.PressOnApplyButtonInSpecifyEvents();

        logger.log(LogStatus.INFO,"Check that Specify Events window is stayed");
        Assert.assertTrue(audioChannelsPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Press Close icon in dialog");
        audioChannelsPage.PressCloseDialogIcon();

        logger.log(LogStatus.INFO,"Check that events count in list is "+size);
        int eventsSize = audioChannelsPage.GetSelectedEventsSize();
        if(size==19) Assert.assertTrue(eventsSize>= size);
        if(size<19) Assert.assertEquals(eventsSize, size);

        logger.log(LogStatus.INFO,"Press on Save button");
        audioChannelsPage.PressSaveButton();

        logger.log(LogStatus.INFO,"Check that dialog isn't appeared");
        Assert.assertFalse(audioChannelsPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that events count in list is "+size);
        int evenSize = audioChannelsPage.GetSelectedEventsSize();
        if(size==19) Assert.assertTrue(evenSize>= size);
        if(size<19) Assert.assertEquals(eventsSize, size);    }

    @Test
    public void SelectAnyEventsInspecifyEventsTest() throws InterruptedException {
        logger=report.startTest("SelectAnyEventsInspecifyEventsTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();
        audioChannelsPage.IfScheduleIsNotExistAddIt();

        logger.log(LogStatus.INFO,"select Event recording Mode");
        audioChannelsPage.SelectRecordingModeOption(0, "Event");

        logger.log(LogStatus.INFO,"Press on Specify Events button");
        audioChannelsPage.PressOnSpecifyEventsButton();

        logger.log(LogStatus.INFO,"Delete events if them are exist");
        int sizeSelectedEvents = audioChannelsPage.CountSelectedEventsInSpecifyEvents();
        for(int i=0; i<sizeSelectedEvents; i++){
            audioChannelsPage.DeleteSelectedEventInSpecifyEvents(0);
        }

        logger.log(LogStatus.INFO,"Click on Event Type field");
        audioChannelsPage.ClickOnEventTypeField();

        int optionsSize = audioChannelsPage.CountEventTypeOptions();
        int random = audioChannelsPage.GetRandomDigit(1, optionsSize);
        String eventName = audioChannelsPage.GetEventTypeOptionByIndex(random);

        logger.log(LogStatus.INFO,"Select "+eventName + " option ");
        audioChannelsPage.SelectEventTypeOptionByIndex(random);

        if(eventName.equals("Tamper")) {
            int postOptionIndex = audioChannelsPage.GetRandomDigit(0, 4);
            String postOptionName = audioChannelsPage.GetEventTypeOptionByIndex(postOptionIndex);

            logger.log(LogStatus.INFO, "Select " + postOptionName + " post option ");
            audioChannelsPage.SelectEventTypePostOptionByIndex(postOptionIndex);
        }

        if(eventName.equals("Native Camera Analytics")){
            int postOptionIndex = audioChannelsPage.GetRandomDigit(4, 6);
            String postOptionName = audioChannelsPage.GetEventTypeOptionByIndex(postOptionIndex);

            logger.log(LogStatus.INFO, "Select " + postOptionName + " post option ");
            audioChannelsPage.SelectEventTypePostOptionByIndex(postOptionIndex);
        }

        logger.log(LogStatus.INFO,"Press on any radio button");
        audioChannelsPage.PressAnySelectedRadioButton();

        logger.log(LogStatus.INFO,"Check that count of selected events is one");
        sizeSelectedEvents = audioChannelsPage.CountSelectedEventsInSpecifyEvents();
        Assert.assertEquals(sizeSelectedEvents, 1);

        logger.log(LogStatus.INFO,"Press on Apply and Close button");
        audioChannelsPage.PressOnApplyAndCloseButtonInSpecifyEvents();

        logger.log(LogStatus.INFO,"Check that events count in list is 1");
        Thread.sleep(1000);
        int eventsSize = audioChannelsPage.GetSelectedEventsSize();
        Assert.assertEquals(eventsSize, 1);

        logger.log(LogStatus.INFO,"Press on Save button");
        audioChannelsPage.PressSaveButton();

        logger.log(LogStatus.INFO,"Check that dialog isn't appeared");
        Assert.assertFalse(audioChannelsPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that events count in list is 1");
        int evenSize = audioChannelsPage.GetSelectedEventsSize();
        Assert.assertEquals(evenSize, 1);
    }

    @Test
    public void CreateNewSchedulesPlansTest() throws InterruptedException {
        logger=report.startTest("CreateNewSchedulesPlanTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        int schedulePlans= audioChannelsPage.GetSizeSchedulePlans();
        int random = audioChannelsPage.GetRandomDigit(1, 4);

        int schedulesCount = audioChannelsPage.GetSchedulesCount(schedulePlans-1)-2;
        if(schedulePlans==1) schedulesCount = schedulesCount-1;

        if(schedulesCount<random) random = schedulesCount;

        for(int i=0; i<random; i++){
            audioChannelsPage.ClickOnPlusScheduleButtons();
            int indexSchedule= i+schedulePlans;
            schedulesCount = audioChannelsPage.GetSchedulesCount(indexSchedule)-2;

            int index = audioChannelsPage.GetRandomDigit(1, schedulesCount);
            logger.log(LogStatus.INFO,"Select schedule with index "+index);
            audioChannelsPage.SelectScheduleByIndex(indexSchedule, index);

            int ind = audioChannelsPage.GetRandomDigit(0, 2);
            logger.log(LogStatus.INFO,"Select recording mode with index "+ind);
            audioChannelsPage.SelectRecordingModeByIndex(indexSchedule, ind);
        }

        logger.log(LogStatus.INFO,"Press on Save button");
        audioChannelsPage.PressSaveButton();

        logger.log(LogStatus.INFO,"Check that dialog isn't appeared");
        Assert.assertFalse(audioChannelsPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that error message doesn't appear");
        Assert.assertFalse(audioChannelsPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that count of schedule plans is "+(schedulePlans+random));
        int schedulePlansCounts= audioChannelsPage.GetSizeSchedulePlans();
        Assert.assertEquals(schedulePlansCounts,schedulePlans+random );

        logger.log(LogStatus.INFO,"Refresh");
        audioChannelsPage.Refresh();

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Check that count of schedule plans is "+(schedulePlans+random));
        int schedulePlansCountsAfterRefresh= audioChannelsPage.GetSizeSchedulePlans();
        Assert.assertEquals(schedulePlansCountsAfterRefresh,schedulePlans+random );
    }

    @Test
    public void GetAudioChannelPropertiesAndApplyToOtherChannelTest() throws InterruptedException {
        logger=report.startTest("GetAudioChannelPropertiesAndApplyToOtherChannelTest");

        logger.log(LogStatus.INFO,"Go to Streams page ");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Get ranomaly channel from list");
        int size = audioChannelsPage.GetChannelsListSize();
        boolean flag = false;
        WebElement channel = null;
        for(int i=0; i<size; i++){
            WebElement element = audioChannelsPage.SelectRandomChannel();
            element.click();
            Thread.sleep(1000);
            logger.log(LogStatus.INFO,"If Apply to button enable by clicking on the channel "+ element.getText()+ " than set this channel");
            if(audioChannelsPage.ApplyToButtonIsEnabled()){
                channel = element;
                flag =true;
                break;
            }
        }
        if(flag){
            String channelText = channel.getText();
//            logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
//        audioChannelsPage.ClickOnChannel(channelText);

            logger.log(LogStatus.INFO,"Get properties from stream page for channel "+channelText );
            String protocol = audioChannelsPage.GetSelectedOptionText();

            logger.log(LogStatus.INFO,"Go to Recording Page");
            audioChannelsPage.GoToRecordingPage();

            logger.log(LogStatus.INFO,"Get properties from Recording page for channel "+channelText );
            boolean recordingStatus= audioChannelsPage.RecordingToggleIsOn();
            boolean limitRetentionStatus= audioChannelsPage.LimitRetentionToggleIsOn();

            String limitRetention = null;
            if(limitRetentionStatus) {
                limitRetention = audioChannelsPage.GetLimitRetention();
            }

            String schedule = audioChannelsPage.GetSelectedScheduleOption(0);
            String recordingMode = audioChannelsPage.GetSelectedRecordingModeOption(0);

            String preEvent=null;
            String postEvent=null;
            if(recordingMode.equals("Event")){
                 preEvent= audioChannelsPage.GetPreEventText();
                 postEvent= audioChannelsPage.GetPostEventText();
            }
            int scheduleCount = audioChannelsPage.GetSizeSchedulePlans();

            logger.log(LogStatus.INFO,"Click on Apply To button");
            audioChannelsPage.ClickOnApplyToButton();

            logger.log(LogStatus.INFO,"Click on audio channel in Apply To dialog");
            int audioChannelsize = audioChannelsPage.GetAudioChannelsSizeInApplyToDialog();
            int index = audioChannelsPage.GetRandomDigit(0,audioChannelsize );
            String channelText2 = audioChannelsPage.GetAudioChannelTextInApplyChannelsDialogByIndex(index);
            logger.log(LogStatus.INFO,"Click on audio channel "+channelText2+" in Apply To dialog");
            audioChannelsPage.ClickOnAudioChanelsInApplyChannelsDialogByIndex(index);

            logger.log(LogStatus.INFO,"Click on Apply button in Apply To Dialog");
            audioChannelsPage.ClickOnApplyButtonInApplyToDialog();

            logger.log(LogStatus.INFO,"Click on Close button in Apply To Dialog");
            audioChannelsPage.ClickOnCloseButtonInApplyToDialog();
            Thread.sleep(1000);

            logger.log(LogStatus.INFO,"Click on the channel "+ channelText2);
            audioChannelsPage.ClickOnChannel(channelText2);

            logger.log(LogStatus.INFO,"Go to Recording Page");
            audioChannelsPage.GoToRecordingPage();

            logger.log(LogStatus.INFO,"Get properties from Recording page for channel "+channelText2 );
            boolean recordingStatus2= audioChannelsPage.RecordingToggleIsOn();
            boolean limitRetentionStatus2 = audioChannelsPage.LimitRetentionToggleIsOn();

            String limitRetention2 = null;
            if(limitRetentionStatus2) {
                limitRetention2 = audioChannelsPage.GetLimitRetention();
            }

            String schedule2 = audioChannelsPage.GetSelectedScheduleOption(0);
            String recordingMode2 = audioChannelsPage.GetSelectedRecordingModeOption(0);

            String preEvent2=null;
            String postEvent2=null;
            if(recordingMode2.equals("Event")){
                preEvent2 = audioChannelsPage.GetPreEventText();
                postEvent2 = audioChannelsPage.GetPostEventText();
            }
            int scheduleCount2 = audioChannelsPage.GetSizeSchedulePlans();

            logger.log(LogStatus.INFO,"Go to Streams Page");
            audioChannelsPage.GoToStreamsPage();

            logger.log(LogStatus.INFO,"Get properties from Streams page for channel "+channelText2 );
            String protocol2 = audioChannelsPage.GetSelectedOptionText();

            logger.log(LogStatus.INFO,"Check that protocol for channel "+channelText2 + " is equals for channel "+ channelText);
            Assert.assertEquals(protocol, protocol2);

            logger.log(LogStatus.INFO,"Check that Recording status for channel "+channelText2 + " is equals for channel "+ channelText);
            Assert.assertEquals(recordingStatus, recordingStatus2);

            logger.log(LogStatus.INFO,"Check that Limit Retention status for channel "+channelText2 + " is equals for channel "+ channelText);
            Assert.assertEquals(limitRetentionStatus, limitRetentionStatus2);
            if(limitRetentionStatus2){
                logger.log(LogStatus.INFO,"Check that Limit Retention value for channel "+channelText2 + " is equals for channel "+ channelText);
                Assert.assertEquals(limitRetention, limitRetention2);
            }

            logger.log(LogStatus.INFO,"Check that Schedule for channel "+channelText2 + " is equals for channel "+ channelText);
            Assert.assertEquals(schedule, schedule2);

            logger.log(LogStatus.INFO,"Check that Recording Mode for channel "+channelText2 + " is equals for channel "+ channelText);
            Assert.assertEquals(recordingMode, recordingMode2);
            if(recordingMode2.equals("Event")){
                logger.log(LogStatus.INFO,"Check that preEvent value for channel "+channelText2 + " is equals for channel "+ channelText);
                Assert.assertEquals(preEvent, preEvent2);
                logger.log(LogStatus.INFO,"Check that postEvent value for channel "+channelText2 + " is equals for channel "+ channelText);
                Assert.assertEquals(postEvent, postEvent2);
            }

            logger.log(LogStatus.INFO,"Check that count of schedule plans for channel "+channelText2 + " is equals for channel "+ channelText);
            Assert.assertEquals(scheduleCount, scheduleCount2);
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
            String screenshot_path=audioChannelsPage.takeScreenshot(driver, "AudioChannels", result.getName());
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
