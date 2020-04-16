package testcases;

import pageObjects.AudioChannelsPage;
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


public class AudioChannelsElementsTest {
    public WebDriver driver;

    public AudioChannelsPage audioChannelsPage;
    // public MonitoringPage monitoringPage;
    public String[] Servers;

    WebElement channel;
    ExtentReports report;
    ExtentTest logger;

    @BeforeClass(alwaysRun = true)
    public void setup() throws IOException, InterruptedException {
        
       // System.setProperty("webdriver.chrome.driver", WebDriverLocation+"\\chromedriver.exe");
       // driver = new ChromeDriver();    //Works
        
        
        
         /*    System.setProperty("webdriver.ie.driver", WebDriverLocation+"\\IEDriverServer.exe");
             DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
             capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
             capabilities.setCapability(InternetExplorerDriver.IE_USE_PER_PROCESS_PROXY, true);
             capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
             capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
             driver = new InternetExplorerDriver(capabilities);  
         
        
                
        audioChannelsPage = PageFactory.initElements(driver, AudioChannelsPage.class);
        //   monitoringPage = PageFactory.initElements(driver, MonitoringPage.class);
        Servers = audioChannelsPage.getServersList();
        driver.get("http://" + Servers[0]);
        driver.manage().window().maximize();
        audioChannelsPage.SignIn();

        audioChannelsPage.WaitUntilLoadingBlockAppears();
        audioChannelsPage.WaitUntilLoadingBlockDisappears();
        audioChannelsPage.GoToAudioChannelsPageFromLanding();*/
    }

    @BeforeTest
    public void startReport(){
        report=new ExtentReports(System.getProperty("user.dir") +"/test-output/AudioChannels/AudioChannelElementsReport.html", true);
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
      //   monitoringPage = PageFactory.initElements(driver, MonitoringPage.class);
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
    public void ChangeNameAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeNameAndPressCancelTest");
        String channelText = channel.getText();

        logger.log(LogStatus.INFO,"Click on the channel "+channelText);
        audioChannelsPage.FindChannelByText(channelText).click();
        Thread.sleep(1000);

        String channelName = audioChannelsPage.GetNameText();
        String newName = channelName + "New";

        logger.log(LogStatus.INFO,"Change name from " + channelName + "to " + newName+ " for channel " + channelText);
        audioChannelsPage.InputIntoNameField(newName);
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Press Cancel button");
        audioChannelsPage.PressCancelButton();

        logger.log(LogStatus.INFO,"Check that changes is canceled");
        String relevantName = audioChannelsPage.GetNameText();
        Assert.assertEquals(relevantName, channelName);

        logger.log(LogStatus.INFO,"Go to the Streams page");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Check that Streams page is loaded");
        Assert.assertTrue(audioChannelsPage.StreamsPageIsLoaded());
    }

    @Test
    public void ChangeNameExitAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeNameExitAndPressCancelTest");
        String channelText = channel.getText();

        logger.log(LogStatus.INFO,"Click on the channel "+channelText);
        audioChannelsPage.FindChannelByText(channelText).click();
        Thread.sleep(1000);

        String channelName = audioChannelsPage.GetNameText();
        String newName = channelName + "New";

        logger.log(LogStatus.INFO,"Change name from " + channelName + "to " + newName+ " for channel " + channelText);
        audioChannelsPage.InputIntoNameField(newName);
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to the Streams page");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Click Cancel on unsaved changes window");
        audioChannelsPage.CancelUnsavedChanges();

        logger.log(LogStatus.INFO,"Check that changes is stayed");
        String relevantName = audioChannelsPage.GetNameText();
        Assert.assertEquals(relevantName, newName);
    }

    @Test 
    public void ChangeNameExitAndPressDonotSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeNameExitAndPressDonotSaveTest");
        String channelText = channel.getText();

        logger.log(LogStatus.INFO,"Click on the channel "+channelText);
        audioChannelsPage.FindDeviceByName(channelText).click();
        Thread.sleep(1000);

        String channelName = audioChannelsPage.GetNameText();
        String newName = channelName + "New";

        logger.log(LogStatus.INFO,"Change name from " + channelName + "to " + newName+ " for channel " + channelText);
        audioChannelsPage.InputIntoNameField(newName);
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

         int channelsSize = audioChannelsPage.GetChannelsListSize();
         if(channelsSize>1){
             logger.log(LogStatus.INFO,"Click on other channel");
             while(true){
                 String newChannel = audioChannelsPage.SelectRandomChannel().getText();
                 if(!newChannel.equals(channelText)){
                     audioChannelsPage.ClickOnChannel(newChannel);
                     break;
                 }
             }

             logger.log(LogStatus.INFO,"Click Do Not Save on unsaved changes window");
             audioChannelsPage.PressDontSaveUnsavedChanges();

             logger.log(LogStatus.INFO,"Click on the channel "+channelText);
             audioChannelsPage.FindDeviceByName(channelText).click();
             Thread.sleep(1000);

             logger.log(LogStatus.INFO,"Check that changes isn't saved");
             String relevantName = audioChannelsPage.GetNameText();
             Assert.assertEquals(relevantName, channelName);
         }
    }

    @Test
    public void ChangeNameExitAndPressSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeNameExitAndPressSaveTest");
        String channelText = channel.getText();

        logger.log(LogStatus.INFO,"Click on the channel "+channelText);
        audioChannelsPage.scroll(audioChannelsPage.FindChannelByText(channelText));
        audioChannelsPage.FindChannelByText(channelText).click();
        Thread.sleep(1000);

        String channelName = audioChannelsPage.GetNameText();
        String newName = channelName + "New";

        logger.log(LogStatus.INFO,"Change name from " + channelName + "to " + newName+ " for channel " + channelText);
        audioChannelsPage.InputIntoNameField(newName);
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to the Streams page");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Click Save on unsaved changes window");
        audioChannelsPage.PressSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Check that Streams page is loaded");
        Assert.assertTrue(audioChannelsPage.StreamsPageIsLoaded());

        logger.log(LogStatus.INFO,"Go to Channel Properties page");
        audioChannelsPage.GoToChannelPropertiesPage();

        logger.log(LogStatus.INFO,"Check that changes isn't saved");
        String relevantName = audioChannelsPage.GetNameText();
        Assert.assertEquals(relevantName, newName);

        logger.log(LogStatus.INFO,"Refresh page");
        audioChannelsPage.Refresh();

        try{
            driver.switchTo().alert().accept();
        }catch(Exception a){}
        audioChannelsPage.WaitUntilEndRefresh();

        logger.log(LogStatus.INFO,"Click on the channel "+channelText);
        audioChannelsPage.FindChannelByText(channelText).click();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that changes is saved after refresh stayed");
        String relevantNameAfterRefresh = audioChannelsPage.GetNameText();
        Assert.assertEquals(relevantNameAfterRefresh, newName);
    }

    @Test
    public void ChangeVisibilityAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeVisibilityAndPressCancelTest");
        String channelText = channel.getText();

        logger.log(LogStatus.INFO,"Click on the channel "+channelText);
        audioChannelsPage.ClickOnChannel(channelText);
        String visibility = audioChannelsPage.GetVisiblePosition();

        logger.log(LogStatus.INFO,"Click on Visibile toggle-switch");
        audioChannelsPage.SwitchVisibleToggler();
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Press Cancel button");
        audioChannelsPage.PressCancelButton();

        logger.log(LogStatus.INFO,"Check that changes is canceled");
        String relevantVisibility = audioChannelsPage.GetVisiblePosition();
        Assert.assertEquals(relevantVisibility, visibility);

        logger.log(LogStatus.INFO,"Go to the Streams page");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Check that Streams page is loaded");
        Assert.assertTrue(audioChannelsPage.StreamsPageIsLoaded());
    }

    @Test
    public void ChangeVisibilityExitAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeVisibilityExitAndPressCancelTest");
        String channelText = channel.getText();

        logger.log(LogStatus.INFO,"Click on the channel "+channelText);
        audioChannelsPage.ClickOnChannel(channelText);
        String visibility = audioChannelsPage.GetVisiblePosition();

        logger.log(LogStatus.INFO,"Click on Visibile toggle-switch");
        audioChannelsPage.SwitchVisibleToggler();
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to the Streams page");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Click Cancel on unsaved changes window");
        audioChannelsPage.CancelUnsavedChanges();

        logger.log(LogStatus.INFO,"Check that changes is stayed");
        String relevantVisibility = audioChannelsPage.GetVisiblePosition();
        if(visibility.equals("true")) Assert.assertEquals(relevantVisibility, "false");
        if(visibility.equals("false")) Assert.assertEquals(relevantVisibility, "true");
    }

    @Test
    public void ChangeVisibilityExitAndPressDoNotSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeVisibilityExitAndPressDoNotSaveTest");
        String channelText = channel.getText();

        logger.log(LogStatus.INFO,"Click on the channel "+channelText);
        audioChannelsPage.ClickOnChannel(channelText);
        String visibility = audioChannelsPage.GetVisiblePosition();

        logger.log(LogStatus.INFO,"Click on Visibile toggle-switch");
        audioChannelsPage.SwitchVisibleToggler();
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to the Streams page");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Click Do Not Save on unsaved changes window");
        audioChannelsPage.PressDontSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to the Channel Properties page");
        audioChannelsPage.GoToChannelPropertiesPage();

        logger.log(LogStatus.INFO,"Click on the channel "+channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Check that changes isn't saved");
        String relevantVisibility = audioChannelsPage.GetVisiblePosition();
        Assert.assertEquals(relevantVisibility, visibility);
    }

    @Test
    public void ChangeVisibilityExitAndPressSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeVisibilityExitAndPressSaveTest");
        String channelText = channel.getText();

        logger.log(LogStatus.INFO,"Click on the channel "+channelText);
        audioChannelsPage.ClickOnChannel(channelText);
        String visibility = audioChannelsPage.GetVisiblePosition();

        logger.log(LogStatus.INFO,"Click on Visibile toggle-switch");
        audioChannelsPage.SwitchVisibleToggler();
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to the Streams page");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Click Save on unsaved changes window");
        audioChannelsPage.PressSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to the Channel Properties page");
        audioChannelsPage.GoToChannelPropertiesPage();

        logger.log(LogStatus.INFO,"Click on the channel "+channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Check that changes is saved");
        String relevantVisibility = audioChannelsPage.GetVisiblePosition();
        if(visibility.equals("true")) Assert.assertEquals(relevantVisibility, "false");
        if(visibility.equals("false")) Assert.assertEquals(relevantVisibility, "true");
    }

    //Streams
    @Test
    public void ChangeProtocolAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeProtocolAndPressCancelTest");

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
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Press Cancel button");
        audioChannelsPage.PressCancelButton();

        logger.log(LogStatus.INFO,"Check that changes is canceled");
        String relevantOption = audioChannelsPage.GetSelectedOptionText();
        Assert.assertEquals(relevantOption, selectedOption);

        logger.log(LogStatus.INFO,"Go to the Recording page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Check that Recording page is loaded");
        Assert.assertTrue(audioChannelsPage.RecordingPageIsLoaded());
    }

    @Test
    public void ChangeProtocolExitAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeProtocolExitAndPressCancelTest");

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
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to the Recording page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Click Cancel on unsaved changes window");
        audioChannelsPage.CancelUnsavedChanges();

        logger.log(LogStatus.INFO,"Check that changes is stayed");
        String relevantOption = audioChannelsPage.GetSelectedOptionText();
        Assert.assertEquals(relevantOption, optionByIndex);
    }

    @Test
    public void ChangeProtocolExitAndPressDoNotSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeProtocolExitAndPressDoNotSaveTest");

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
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to the Recording page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Click Do Not Save on unsaved changes window");
        audioChannelsPage.PressDontSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Check that Recording page is loaded");
        Assert.assertTrue(audioChannelsPage.RecordingPageIsLoaded());

        logger.log(LogStatus.INFO,"Go to the Streams page");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Click on the channel "+channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Check that changes isn't saved");
        String relevantOption = audioChannelsPage.GetSelectedOptionText();
        Assert.assertEquals(relevantOption, selectedOption);
    }

    @Test 
    public void ChangeProtocolExitAndPressSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeProtocolExitAndPressSaveTest");

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
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to the Recording page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Click Save on unsaved changes window");
        audioChannelsPage.PressSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Check that Recording page is loaded");
        Assert.assertTrue(audioChannelsPage.RecordingPageIsLoaded());

        logger.log(LogStatus.INFO,"Go to the Streams page");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Click on the channel "+channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Check that changes is saved");
        String relevantOption = audioChannelsPage.GetSelectedOptionText();
        Assert.assertEquals(relevantOption, optionByIndex);
    }

    //Recording
    @Test
    public void SwitchRecordingToggleAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("SwitchRecordingToggleAndPressCancelTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the device "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Check Recording is On or Off");
        boolean status= audioChannelsPage.RecordingToggleIsOn();

        logger.log(LogStatus.INFO,"Switch Recording");
        audioChannelsPage.SwitchRecordingToggle();
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Press Cancel button");
        audioChannelsPage.PressCancelButton();

        boolean statusAfterSwitch= audioChannelsPage.RecordingToggleIsOn();
        logger.log(LogStatus.INFO,"Check that status after changed same to status before");
        Assert.assertEquals(status,statusAfterSwitch );

        logger.log(LogStatus.INFO,"Go to the Streams page");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Check that Streams page is loaded");
        Assert.assertTrue(audioChannelsPage.StreamsPageIsLoaded());
    }

    @Test
    public void SwitchRecordingToggleExitAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("SwitchRecordingToggleExitAndPressCancelTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the device "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Check Recording is On or Off");
        boolean status= audioChannelsPage.RecordingToggleIsOn();

        logger.log(LogStatus.INFO,"Switch Recording");
        audioChannelsPage.SwitchRecordingToggle();
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to Streams page ");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Press Cancel on Unsaved changes dialog ");
        audioChannelsPage.CancelUnsavedChanges();

        logger.log(LogStatus.INFO,"Check that Streams page isn't loaded");
        Assert.assertFalse(audioChannelsPage.StreamsPageIsLoaded());

        logger.log(LogStatus.INFO,"Click on the device "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        boolean statusAfterSwitch= audioChannelsPage.RecordingToggleIsOn();
        logger.log(LogStatus.INFO,"Check that status after changed same to status before");
        if(status) Assert.assertFalse(statusAfterSwitch);
        if(!status) Assert.assertTrue(statusAfterSwitch);
    }

    @Test
    public void SwitchRecordingToggleExitAndPressDonotSaveTest() throws InterruptedException {
        logger=report.startTest("SwitchRecordingToggleExitAndPressDonotSaveTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the device "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Check Recording is On or Off");
        boolean status= audioChannelsPage.RecordingToggleIsOn();

        logger.log(LogStatus.INFO,"Switch Recording");
        audioChannelsPage.SwitchRecordingToggle();
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        int channelsSize= audioChannelsPage.GetChannelsListSize();
        if(channelsSize>1){
            logger.log(LogStatus.INFO,"Click on other channel");
            while(true){
                String newChannel = audioChannelsPage.SelectRandomChannel().getText();
                if(!newChannel.equals(channelText)){
                    audioChannelsPage.ClickOnChannel(newChannel);
                    break;
                }
            }
            logger.log(LogStatus.INFO,"Press do not Save on the Unsaved changes dialog");
            audioChannelsPage.PressDontSaveUnsavedChanges();
            Thread.sleep(1000);

//        logger.log(LogStatus.INFO,"Go to Recording page");
//        audioChannelsPage.GoToRecordingPage();

            logger.log(LogStatus.INFO,"Click on the device "+ channelText);
            audioChannelsPage.ClickOnChannel(channelText);

            boolean statusAfterSwitch= audioChannelsPage.RecordingToggleIsOn();
            logger.log(LogStatus.INFO,"Check that status after changed same to status before");
            Assert.assertEquals(status,statusAfterSwitch );
        }
    }

    @Test
    public void SwitchRecordingToggleExitAndPressSaveTest() throws InterruptedException {
        logger=report.startTest("SwitchRecordingToggleExitAndPressSaveTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the device "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Check Recording is On or Off");
        boolean status= audioChannelsPage.RecordingToggleIsOn();

        logger.log(LogStatus.INFO,"Switch Recording");
        audioChannelsPage.SwitchRecordingToggle();
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        int channelsSize= audioChannelsPage.GetChannelsListSize();
        if(channelsSize>1){
            logger.log(LogStatus.INFO,"Click on other channel");
            while(true){
                String newChannel = audioChannelsPage.SelectRandomChannel().getText();
                if(!newChannel.equals(channelText)){
                    audioChannelsPage.ClickOnChannel(newChannel);
                    break;
                }
            }
            logger.log(LogStatus.INFO,"Press Save on the Unsaved changes dialog");
            audioChannelsPage.PressSaveUnsavedChanges();
            Thread.sleep(1000);

            logger.log(LogStatus.INFO,"Click on the device "+ channelText);
            audioChannelsPage.ClickOnChannel(channelText);

            boolean statusAfterSwitch= audioChannelsPage.RecordingToggleIsOn();
            logger.log(LogStatus.INFO,"Check that Recording status is changed");
            if(status) Assert.assertFalse(statusAfterSwitch);
            if(!status) Assert.assertTrue(statusAfterSwitch);
        }
    }

    @Test
    public void SwitchLimitRetentionToggleAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("SwitchLimitRetentionToggleAndPressCancelTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Check Limit Retention Toggler is On or Off for device"+channelText);
        boolean status= audioChannelsPage.LimitRetentionToggleIsOn();

        logger.log(LogStatus.INFO,"Switch Limit Retention Toggler");
        audioChannelsPage.SwitchLimitRetentionToggle();
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Press Cancel");
        audioChannelsPage.PressCancelButton();

        boolean statusAfterSwitch= audioChannelsPage.LimitRetentionToggleIsOn();
        logger.log(LogStatus.INFO,"Check that status isn't changed");
        Assert.assertEquals(status,statusAfterSwitch );

        logger.log(LogStatus.INFO,"Go to the Streams page");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Check that Streams page is loaded");
        Assert.assertTrue(audioChannelsPage.StreamsPageIsLoaded());
    }

    @Test
    public void SwitchLimitRetentionToggleExitAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("SwitchLimitRetentionToggleExitAndPressCancelTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Check Limit Retention Toggler is On or Off for device"+channelText);
        boolean status= audioChannelsPage.LimitRetentionToggleIsOn();

        logger.log(LogStatus.INFO,"Switch Limit Retention Toggler");
        audioChannelsPage.SwitchLimitRetentionToggle();
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to Streams page");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Press Cancel on the Unsaved changes dialog");
        audioChannelsPage.CancelUnsavedChanges();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that Streams page isn't loaded");
        Assert.assertFalse(audioChannelsPage.StreamsPageIsLoaded());

        boolean statusAfterSwitch= audioChannelsPage.LimitRetentionToggleIsOn();
        logger.log(LogStatus.INFO,"Check that status after changed same to status before");
        Assert.assertEquals(status,!statusAfterSwitch);
    }

    @Test
    public void SwitchLimitRetentionToggleExitAndPressDonotSaveTest() throws InterruptedException {
        logger=report.startTest("SwitchLimitRetentionToggleExitAndPressDonotSaveTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Check Limit Retention Toggler is On or Off for device"+channelText);
        boolean status= audioChannelsPage.LimitRetentionToggleIsOn();

        logger.log(LogStatus.INFO,"Switch Limit Retention Toggler");
        audioChannelsPage.SwitchLimitRetentionToggle();
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to Channel Properties page");
        audioChannelsPage.GoToChannelPropertiesPage();

        logger.log(LogStatus.INFO,"Press do not Save on the Unsaved changes dialog");
        audioChannelsPage.PressDontSaveUnsavedChanges();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Go to Recording page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        boolean statusAfterSwitch= audioChannelsPage.LimitRetentionToggleIsOn();
        logger.log(LogStatus.INFO,"Check that Retention limit status isn't changed");
        Assert.assertEquals(status,statusAfterSwitch );
    }

    @Test
    public void SwitchLimitRetentionToggleExitAndPressSaveTest() throws InterruptedException {
        logger=report.startTest("SwitchLimitRetentionToggleExitAndPressSaveTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Check Limit Retention Toggler is On or Off for device"+channelText);
        boolean status= audioChannelsPage.LimitRetentionToggleIsOn();

        logger.log(LogStatus.INFO,"Switch Limit Retention Toggler");
        audioChannelsPage.SwitchLimitRetentionToggle();
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to Channel Properties page");
        audioChannelsPage.GoToChannelPropertiesPage();

        logger.log(LogStatus.INFO,"Press Save on the Unsaved changes dialog");
        audioChannelsPage.PressSaveUnsavedChanges();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Go to Recording page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        boolean statusAfterSwitch= audioChannelsPage.LimitRetentionToggleIsOn();
        logger.log(LogStatus.INFO,"Check that Retention limit status is changed");
        Assert.assertEquals(status, !statusAfterSwitch);
    }

    @Test //Bug 8892  The value in Limit retention field is 1 after cancel changes //8902 Unsaved changes dialog appears when Limit Retention  is ON
    public void ChangeLimitRetentionAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeLimitRetentionAndPressCancelTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        boolean status= audioChannelsPage.LimitRetentionToggleIsOn();

        if(!status){
            logger.log(LogStatus.INFO,"Click om Limit Retention toggle-switch for "+channelText);
            audioChannelsPage.SwitchLimitRetentionToggle();

            logger.log(LogStatus.INFO,"Press Save button");
            audioChannelsPage.PressSaveButton();
        }
        int count = audioChannelsPage.GetRandomDigit(0, 1000);
        logger.log(LogStatus.INFO,"Input value " + count+ "Into Limit Retention Field");
        int limRetention =Integer.parseInt(audioChannelsPage.GetLimitRetention());
        audioChannelsPage.InputIntoLimitRetentionField(""+count);

//        logger.log(LogStatus.INFO,"Change Limit Retention By Spinners");
//        audioChannelsPage.ClickSpinnerUpLimitRetention();
//        audioChannelsPage.ClickSpinnerUpLimitRetention();
//        audioChannelsPage.ClickSpinnerDownLimitRetention();
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Press Cancel button");
        audioChannelsPage.PressCancelButton();

        logger.log(LogStatus.INFO,"Check that change isn't saved");
        int limRetentionAfterChanged =Integer.parseInt(audioChannelsPage.GetLimitRetention());
        Assert.assertEquals(limRetentionAfterChanged, limRetention);

        logger.log(LogStatus.INFO,"Go to the Streams page");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Check that Streams page is loaded");
        Assert.assertTrue(audioChannelsPage.StreamsPageIsLoaded());
    }

    @Test 
    public void ChangeLimitRetentionExitAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeLimitRetentionExitAndPressCancelTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        boolean status= audioChannelsPage.LimitRetentionToggleIsOn();
        if(!status){
            logger.log(LogStatus.INFO,"Click om Limit Retention toggle-switch for "+channelText);
            audioChannelsPage.SwitchLimitRetentionToggle();

            logger.log(LogStatus.INFO,"Press Save button");
            audioChannelsPage.PressSaveButton();
        }
        int count = audioChannelsPage.GetRandomDigit(0, 1000);
        logger.log(LogStatus.INFO,"Input value " + count+ "Into Limit Retention Field");

        int limRetention =Integer.parseInt(audioChannelsPage.GetLimitRetention());
        audioChannelsPage.InputIntoLimitRetentionField(""+count);

        logger.log(LogStatus.INFO,"Change Limit Retention By Spinners");
        audioChannelsPage.ClickSpinnerUpLimitRetention();
        audioChannelsPage.ClickSpinnerUpLimitRetention();
        audioChannelsPage.ClickSpinnerDownLimitRetention();
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to Channel Properties page");
        audioChannelsPage.GoToChannelPropertiesPage();

        logger.log(LogStatus.INFO,"Press Cancel on the Unsaved changes dialog");
        audioChannelsPage.CancelUnsavedChanges();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that change is stayed");
        int limRetentionAfterChanged =Integer.parseInt(audioChannelsPage.GetLimitRetention());
        Assert.assertEquals(limRetentionAfterChanged, count+2-1);
    }

    @Test 
    public void ChangeLimitRetentionExitAndPressDoNotSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeLimitRetentionExitAndPressDoNotSaveTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        boolean status= audioChannelsPage.LimitRetentionToggleIsOn();
        if(!status){
            logger.log(LogStatus.INFO,"Click om Limit Retention toggle-switch for "+channelText);
            audioChannelsPage.SwitchLimitRetentionToggle();

            logger.log(LogStatus.INFO,"Press Save button");
            audioChannelsPage.PressSaveButton();
        }
        int count = audioChannelsPage.GetRandomDigit(0, 1000);
        logger.log(LogStatus.INFO,"Input value " + count+ "Into Limit Retention Field");

        int limRetention =Integer.parseInt(audioChannelsPage.GetLimitRetention());
        audioChannelsPage.InputIntoLimitRetentionField(""+count);

        logger.log(LogStatus.INFO,"Change Limit Retention By Spinners");
        audioChannelsPage.ClickSpinnerUpLimitRetention();
        audioChannelsPage.ClickSpinnerUpLimitRetention();
        audioChannelsPage.ClickSpinnerDownLimitRetention();
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to Channel Properties page");
        audioChannelsPage.GoToChannelPropertiesPage();

        logger.log(LogStatus.INFO,"Press do not Save on the Unsaved changes dialog");
        audioChannelsPage.PressDontSaveUnsavedChanges();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Go to Recording page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Check that change isn't saved");
        int limRetentionAfterChanged =Integer.parseInt(audioChannelsPage.GetLimitRetention());
        Assert.assertEquals(limRetentionAfterChanged, limRetention);
    }

    @Test 
    public void ChangeLimitRetentionExitAndPressSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeLimitRetentionExitAndPressSaveTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        boolean status= audioChannelsPage.LimitRetentionToggleIsOn();
        if(!status){
            logger.log(LogStatus.INFO,"Click om Limit Retention toggle-switch for "+channelText);
            audioChannelsPage.SwitchLimitRetentionToggle();

            logger.log(LogStatus.INFO,"Press Save button");
            audioChannelsPage.PressSaveButton();
        }
        int count = audioChannelsPage.GetRandomDigit(0, 1000);
        logger.log(LogStatus.INFO,"Input value " + count+ "Into Limit Retention Field");

        int limRetention =Integer.parseInt(audioChannelsPage.GetLimitRetention());
        audioChannelsPage.InputIntoLimitRetentionField(""+count);

        logger.log(LogStatus.INFO,"Change Limit Retention By Spinners");
        audioChannelsPage.ClickSpinnerUpLimitRetention();
        audioChannelsPage.ClickSpinnerUpLimitRetention();
        audioChannelsPage.ClickSpinnerDownLimitRetention();
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to Channel Properties page");
        audioChannelsPage.GoToChannelPropertiesPage();

        logger.log(LogStatus.INFO,"Press Save on the Unsaved changes dialog");
        audioChannelsPage.PressSaveUnsavedChanges();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Go to Recording page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Check that change is saved");
       // int limRetentionAfterChanged =Integer.parseInt(audioChannelsPage.GetLimitRetention());
        String limRetentionAfterChanged =audioChannelsPage.GetLimitRetention();
        Assert.assertEquals(limRetentionAfterChanged, ""+(count+2-1));
    }

    @Test
    public void SelectScheduleAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("SelectScheduleAndPressCancelTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        audioChannelsPage.IfScheduleIsNotExistAddIt();

        logger.log(LogStatus.INFO,"Create new Schedule");
        audioChannelsPage.SelectSchedule(0, "New Schedule");
        String name = "AudioChannel"+audioChannelsPage.GetRandomDigit(1, 1000);
        audioChannelsPage.InputIntoNameNewSchedule(name);
        audioChannelsPage.PresssaveNewSchedule();

        logger.log(LogStatus.INFO,"Select created schedule");
        audioChannelsPage.SelectSchedule(0, name);
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Cancel changes");
        audioChannelsPage.PressCancelButton();

        if(audioChannelsPage.ScheduleIsExist(0)){
            logger.log(LogStatus.INFO,"Check that change isn't saved");
            String selectedOption =audioChannelsPage.GetSelectedScheduleOption(0);
            Assert.assertNotEquals(selectedOption, name);
        }

        logger.log(LogStatus.INFO,"Go to the Streams page");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Check that Streams page is loaded");
        Assert.assertTrue(audioChannelsPage.StreamsPageIsLoaded());


//        logger.log(LogStatus.INFO,"Refresh page");
//        audioChannelsPage.Refresh();
//
//        logger.log(LogStatus.INFO,"Go to Recording Page");
//        audioChannelsPage.GoToRecordingPage();
//        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
//        audioChannelsPage.ClickOnChannel(channelText);
//
//        if(audioChannelsPage.ScheduleIsExist()){
//            logger.log(LogStatus.INFO,"Check that change isn't saved after refresh");
//            String selectedOptionAfterRefresh =audioChannelsPage.GetSelectedScheduleOption();
//            Assert.assertNotEquals(selectedOptionAfterRefresh, name);
//        }
    }

    @Test
    public void SelectScheduleExitAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("SelectScheduleExitAndPressCancelTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();
        audioChannelsPage.IfScheduleIsNotExistAddIt();

        logger.log(LogStatus.INFO,"Create new Schedule");
        audioChannelsPage.SelectSchedule(0, "New Schedule");
        String name = "AudioChannel"+audioChannelsPage.GetRandomDigit(1, 1000);
        audioChannelsPage.InputIntoNameNewSchedule(name);
        audioChannelsPage.PresssaveNewSchedule();

        logger.log(LogStatus.INFO,"Select created schedule");
        audioChannelsPage.SelectSchedule(0, name);
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to Channel Properties page");
        audioChannelsPage.GoToChannelPropertiesPage();

        logger.log(LogStatus.INFO,"Press Cancel on the Unsaved changes dialog");
        audioChannelsPage.CancelUnsavedChanges();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that change is stayed");
        String selectedOption =audioChannelsPage.GetSelectedScheduleOption(0);
        Assert.assertEquals(selectedOption, name);
    }

    @Test
    public void SelectScheduleAndPressDoNotSaveTest() throws InterruptedException {
        logger=report.startTest("SelectScheduleAndPressDoNotSaveTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        audioChannelsPage.IfScheduleIsNotExistAddIt();

        logger.log(LogStatus.INFO,"Create new Schedule");
        audioChannelsPage.SelectSchedule(0, "New Schedule");
        String name = "AudioChannel"+audioChannelsPage.GetRandomDigit(1, 1000);
        audioChannelsPage.InputIntoNameNewSchedule(name);
        audioChannelsPage.PresssaveNewSchedule();

        logger.log(LogStatus.INFO,"Select created schedule");
        audioChannelsPage.SelectSchedule(0, name);
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to Channel Properties page");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Press do not Save on the Unsaved changes dialog");
        audioChannelsPage.PressDontSaveUnsavedChanges();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that Streams page is loaded");
        Assert.assertTrue(audioChannelsPage.StreamsPageIsLoaded());

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        if(audioChannelsPage.ScheduleIsExist(0)){
            logger.log(LogStatus.INFO,"Check that change isn't saved");
            String selectedOption =audioChannelsPage.GetSelectedScheduleOption(0);
            Assert.assertNotEquals(selectedOption, name);
        }

        logger.log(LogStatus.INFO,"Refresh page");
        audioChannelsPage.Refresh();

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        if(audioChannelsPage.ScheduleIsExist(0)){
            logger.log(LogStatus.INFO,"Check that change isn't saved after refresh");
            String selectedOptionAfterRefresh =audioChannelsPage.GetSelectedScheduleOption(0);
            Assert.assertNotEquals(selectedOptionAfterRefresh, name);
        }
    }

    @Test
    public void SelectScheduleAndPressSaveTest() throws InterruptedException {
        logger=report.startTest("SelectScheduleAndPressSaveTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        audioChannelsPage.IfScheduleIsNotExistAddIt();

        logger.log(LogStatus.INFO,"Create new Schedule");
        audioChannelsPage.SelectSchedule(0, "New Schedule");
        String name = "AudioChannel"+audioChannelsPage.GetRandomDigit(1, 1000);
        audioChannelsPage.InputIntoNameNewSchedule(name);
        audioChannelsPage.PresssaveNewSchedule();

        logger.log(LogStatus.INFO,"Select created schedule");
        audioChannelsPage.SelectSchedule(0, name);
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to Channel Properties page");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Press do not Save on the Unsaved changes dialog");
        audioChannelsPage.PressSaveUnsavedChanges();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that Channel Properties page is loaded");
        Assert.assertTrue(audioChannelsPage.StreamsPageIsLoaded());

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        if(audioChannelsPage.ScheduleIsExist(0)){
            logger.log(LogStatus.INFO,"Check that change is saved");
            String selectedOption =audioChannelsPage.GetSelectedScheduleOption(0);
            Assert.assertEquals(selectedOption, name);
        }

        logger.log(LogStatus.INFO,"Refresh page");
        audioChannelsPage.Refresh();
        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        if(audioChannelsPage.ScheduleIsExist(0)){
            logger.log(LogStatus.INFO,"Check that change is saved after refresh");
            String selectedOptionAfterRefresh =audioChannelsPage.GetSelectedScheduleOption(0);
            Assert.assertEquals(selectedOptionAfterRefresh, name);
        }
    }

    @Test
    public void SelectRecordingModeAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("SelectRecordingModeAndPressCancelTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();
        audioChannelsPage.IfScheduleIsNotExistAddIt();

        int max = audioChannelsPage.CountOfOptionsInRecordingModeSelect(0);
        String option =audioChannelsPage.GetSelectedRecordingModeOption(0);

        int index = 0;
        for(int i=0 ; i<=max; i++){
            String selectedMode =audioChannelsPage.GetSelectedRecordingModeOption(0);
            int random = audioChannelsPage.GetRandomDigit(0,max);
            String mode = audioChannelsPage.GetOptionsByIndex(0, random).getText();
            if(!selectedMode.equals(mode)) {
                index = random;
                break;
            }
        }
        String mode = audioChannelsPage.GetOptionsByIndex(0, index).getText();
        logger.log(LogStatus.INFO,"Select recording Mode "+mode);
        audioChannelsPage.SelectRecordingModeByIndex(0, index);

        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();
        logger.log(LogStatus.INFO,"Cancel changes");
        audioChannelsPage.PressCancelButton();

        if(audioChannelsPage.ScheduleIsExist(0)){
            logger.log(LogStatus.INFO,"Check that change isn't saved");
            String selectedOption =audioChannelsPage.GetSelectedRecordingModeOption(0);
            Assert.assertEquals(selectedOption, option);
        }

        logger.log(LogStatus.INFO,"Go to the Streams page");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Check that Streams page is loaded");
        Assert.assertTrue(audioChannelsPage.StreamsPageIsLoaded());
    }

    @Test
    public void SelectRecordingModeExitAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("SelectRecordingModeExitAndPressCancelTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        int max = audioChannelsPage.CountOfOptionsInRecordingModeSelect(0);
        String option =audioChannelsPage.GetSelectedRecordingModeOption(0);

        int index = 0;
        for(int i=0 ; i<=max; i++){
            String selectedMode =audioChannelsPage.GetSelectedRecordingModeOption(0);
            int random = audioChannelsPage.GetRandomDigit(0,max);
            String mode = audioChannelsPage.GetOptionsByIndex(0, random).getText();
            if(!selectedMode.equals(mode)) {
                index = random;
                break;
            }
        }
        String mode = audioChannelsPage.GetOptionsByIndex(0, index).getText();
        logger.log(LogStatus.INFO,"Select recording Mode "+mode);
        audioChannelsPage.SelectRecordingModeByIndex(0, index);

        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();
        logger.log(LogStatus.INFO,"Go to Channel Properties page");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Press Cancel on the Unsaved changes dialog");
        audioChannelsPage.CancelUnsavedChanges();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that change is stayed");
        String selectedOption =audioChannelsPage.GetSelectedRecordingModeOption(0);
        Assert.assertEquals(selectedOption, mode);
    }

    @Test
    public void SelectRecordingModeExitAndPressDoNotSaveTest() throws InterruptedException {
        logger=report.startTest("SelectRecordingModeExitAndPressDoNotSaveTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();
        audioChannelsPage.IfScheduleIsNotExistAddIt();

        int max = audioChannelsPage.CountOfOptionsInRecordingModeSelect(0);
        String option =audioChannelsPage.GetSelectedRecordingModeOption(0);

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
        String mode = audioChannelsPage.GetOptionsByIndex(0, index).getText();
        logger.log(LogStatus.INFO,"Select recording Mode "+mode);
        audioChannelsPage.SelectRecordingModeByIndex(0, index);

        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();
        logger.log(LogStatus.INFO,"Go to Streams Properties page");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Press do not Save on the Unsaved changes dialog");
        audioChannelsPage.PressDontSaveUnsavedChanges();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that Channel Properties page is loaded");
        Assert.assertTrue(audioChannelsPage.StreamsPageIsLoaded());

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        if(audioChannelsPage.ScheduleIsExist(0)){
            logger.log(LogStatus.INFO,"Check that change isn't saved");
            String selectedOption =audioChannelsPage.GetSelectedRecordingModeOption(0);
            Assert.assertEquals(selectedOption, option);
        }

        logger.log(LogStatus.INFO,"Refresh page");
        audioChannelsPage.Refresh();

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        if(audioChannelsPage.ScheduleIsExist(0)){
            logger.log(LogStatus.INFO,"Check that change isn't saved after refresh");
            String selectedOptionAfterRefresh =audioChannelsPage.GetSelectedRecordingModeOption(0);
            Assert.assertEquals(selectedOptionAfterRefresh, option);
        }
    }

    @Test 
    public void SelectRecordingModeExitAndPressSaveTest() throws InterruptedException {
        logger=report.startTest("SelectRecordingModeExitAndPressSaveTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        audioChannelsPage.IfScheduleIsNotExistAddIt();

        int max = audioChannelsPage.CountOfOptionsInRecordingModeSelect(0);
        String option =audioChannelsPage.GetSelectedRecordingModeOption(0);

        int index = 0;
        for(int i=0 ; i<=max; i++){
            String selectedMode =audioChannelsPage.GetSelectedRecordingModeOption(0);
            int random = audioChannelsPage.GetRandomDigit(0,max);
            String mode = audioChannelsPage.GetOptionsByIndex(0, random).getText();
            if(!selectedMode.equals(mode)) {
                index = random;
                break;
            }
        }

        String mode = audioChannelsPage.GetOptionsByIndex(0, index).getText();
        logger.log(LogStatus.INFO,"Select recording Mode "+mode);
        audioChannelsPage.SelectRecordingModeByIndex(0, index);

        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to Streams Properties page");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Press Save on the Unsaved changes dialog");
        audioChannelsPage.PressSaveUnsavedChanges();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that Channel Properties page is loaded");
        Assert.assertTrue(audioChannelsPage.StreamsPageIsLoaded());

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        if(audioChannelsPage.ScheduleIsExist(0)){
            logger.log(LogStatus.INFO,"Check that change is saved");
            String selectedOption =audioChannelsPage.GetSelectedRecordingModeOption(0);
            Assert.assertEquals(selectedOption, mode);
        }

        logger.log(LogStatus.INFO,"Refresh page");
        audioChannelsPage.Refresh();

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        if(audioChannelsPage.ScheduleIsExist(0)){
            logger.log(LogStatus.INFO,"Check that change is saved after refresh");
            String selectedOptionAfterRefresh =audioChannelsPage.GetSelectedRecordingModeOption(0);
            Assert.assertEquals(selectedOptionAfterRefresh, mode);
        }
    }

    @Test
    public void ChangePreEventTimeAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("ChangePreEventTimeAndPressCancelTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        audioChannelsPage.IfScheduleIsNotExistAddIt();
        String selectedOption = audioChannelsPage.GetSelectedRecModeOption(0);

        if(!selectedOption.equals("Event")){
             logger.log(LogStatus.INFO,"Select Event recording Mode");
            audioChannelsPage.SelectRecordingModeOption(0,"Event");
        }

        String preEvent = audioChannelsPage.GetPreEventText();
        int value = 0;
        while(true){
            int random = audioChannelsPage.GetRandomDigit(0,15);
            if(random!=Integer.parseInt(preEvent)){
                value = random;
                break;
            }
        }
        logger.log(LogStatus.INFO,"Change pre event seconds to"+value);
        audioChannelsPage.InputPreEventSeconds(""+value);
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Press cancel button");
        audioChannelsPage.PressCancelButton();

        if(selectedOption.equals("Continuous")){
            String option = audioChannelsPage.GetSelectedRecModeOption(0);
            Assert.assertEquals(option,"Continuous" );
        }

        if(selectedOption.equals("Event")){
            logger.log(LogStatus.INFO,"Check that pre event time change is canceled");
            String preEventRelevent = audioChannelsPage.GetPreEventText();
            Assert.assertEquals(preEventRelevent, preEvent);
        }

        logger.log(LogStatus.INFO,"Go to the Streams page");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Check that Streams page is loaded");
        Assert.assertTrue(audioChannelsPage.StreamsPageIsLoaded());
    }

    @Test
    public void ChangePreEventTimeExitAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("ChangePreEventTimeExitAndPressCancelTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        audioChannelsPage.IfScheduleIsNotExistAddIt();
        String selectedOption = audioChannelsPage.GetSelectedRecModeOption(0);

        if(!selectedOption.equals("Event")){
            logger.log(LogStatus.INFO,"Select Event recording Mode");
            audioChannelsPage.SelectRecordingModeOption(0, "Event");
        }

        String preEvent = audioChannelsPage.GetPreEventText();
        int value = 0;
        while(true){
            int random = audioChannelsPage.GetRandomDigit(0,15);
            if(random!=Integer.parseInt(preEvent)){
                value = random;
                break;
            }
        }
        logger.log(LogStatus.INFO,"Change pre event seconds to"+value);
        audioChannelsPage.InputPreEventSeconds(""+value);
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to Streams page");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Click cancel on unsaved changes button");
        audioChannelsPage.CancelUnsavedChanges();

        String option = audioChannelsPage.GetSelectedRecModeOption(0);
        Assert.assertEquals(option,"Event");

        logger.log(LogStatus.INFO,"Check that pre event time change is stayed");
        String preEventRelevent = audioChannelsPage.GetPreEventText();
        Assert.assertEquals(preEventRelevent, ""+value);
    }

    @Test
    public void ChangePreEventTimeExitAndPressDoNotSaveTest() throws InterruptedException {
        logger=report.startTest("ChangePreEventTimeExitAndPressDoNotSaveTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        audioChannelsPage.IfScheduleIsNotExistAddIt();
        String selectedOption = audioChannelsPage.GetSelectedRecModeOption(0);

        if(!selectedOption.equals("Event")){
            logger.log(LogStatus.INFO,"Select Event recording Mode");
            audioChannelsPage.SelectRecordingModeOption(0, "Event");
        }

        String preEvent = audioChannelsPage.GetPreEventText();
        int value = 0;
        while(true){
            int random = audioChannelsPage.GetRandomDigit(0,15);
            if(random!=Integer.parseInt(preEvent)){
                value = random;
                break;
            }
        }
        logger.log(LogStatus.INFO,"Change pre event seconds to"+value);
        audioChannelsPage.InputPreEventSeconds(""+value);
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to Streams page");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Check that Unsaved changes dialog is exist");
        Assert.assertTrue(audioChannelsPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Click Do Not Save on unsaved changes button");
        audioChannelsPage.PressDontSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        if(selectedOption.equals("Continuous")){
            String option = audioChannelsPage.GetSelectedRecModeOption(0);
            Assert.assertEquals(option,"Continuous" );
        }
        if(selectedOption.equals("Event")){
            logger.log(LogStatus.INFO,"Check that pre event time change isn't saved");
            String preEventRelevent = audioChannelsPage.GetPreEventText();
            Assert.assertEquals(preEventRelevent, preEvent);
        }
    }

    @Test
    public void ChangePreEventTimeExitAndPressSaveTest() throws InterruptedException {
        logger=report.startTest("ChangePreEventTimeExitAndPressSaveTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        audioChannelsPage.IfScheduleIsNotExistAddIt();
        String selectedOption = audioChannelsPage.GetSelectedRecModeOption(0);

        if(!selectedOption.equals("Event")){
            logger.log(LogStatus.INFO,"Select Event recording Mode");
            audioChannelsPage.SelectRecordingModeOption(0, "Event");
        }

        String preEvent = audioChannelsPage.GetPreEventText();
        logger.log(LogStatus.INFO,"Change pre event seconds");
        int value = 0;
        while(true){
            int random = audioChannelsPage.GetRandomDigit(0,15);
            if(random!=Integer.parseInt(preEvent)){
                value = random;
                break;
            }
        }
        audioChannelsPage.InputPreEventSeconds(""+value);
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to Streams page");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Click Save on unsaved changes button");
        audioChannelsPage.PressSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Check that pre event time change is saved");
        String option = audioChannelsPage.GetSelectedRecModeOption(0);
        Assert.assertEquals(option,"Event" );

        String preEventRelevent = audioChannelsPage.GetPreEventText();
        Assert.assertEquals(preEventRelevent, ""+value);
    }

    @Test
    public void ChangePostEventTimeAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("ChangePostEventTimeAndPressCancelTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        audioChannelsPage.IfScheduleIsNotExistAddIt();
        String selectedOption = audioChannelsPage.GetSelectedRecModeOption(0);

        if(!selectedOption.equals("Event")){
            logger.log(LogStatus.INFO,"Select Event recording Mode");
            audioChannelsPage.SelectRecordingModeOption(0, "Event");
        }

        String postEvent = audioChannelsPage.GetPostEventText();
        int randPostEvent = 0;
        int random = 0;
        while(true){
            random = audioChannelsPage.GetRandomDigit(0,100);
            if(random!= Integer.parseInt(postEvent)){
                randPostEvent = random;
                break;
            }
        }

        logger.log(LogStatus.INFO,"Change post event seconds");
        audioChannelsPage.InputPostEventSeconds(""+randPostEvent);
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Press cancel button");
        audioChannelsPage.PressCancelButton();

        if(selectedOption.equals("Continuous")){
            String option = audioChannelsPage.GetSelectedRecModeOption(0);
            Assert.assertEquals(option,"Continuous" );
        }

        if(selectedOption.equals("Event")){
            logger.log(LogStatus.INFO,"Check that post event time change is canceled");
            String postEventRelevant = audioChannelsPage.GetPostEventText();
            Assert.assertEquals(postEventRelevant, postEvent);
        }

        logger.log(LogStatus.INFO,"Go to the Streams page");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Check that Streams page is loaded");
        Assert.assertTrue(audioChannelsPage.StreamsPageIsLoaded());
    }

    @Test
    public void ChangePostEventTimeExitAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("ChangePostEventTimeExitAndPressCancelTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        audioChannelsPage.IfScheduleIsNotExistAddIt();
        String selectedOption = audioChannelsPage.GetSelectedRecModeOption(0);

        if(!selectedOption.equals("Event")){
            logger.log(LogStatus.INFO,"Select Event recording Mode");
            audioChannelsPage.SelectRecordingModeOption(0, "Event");
        }

        String preEvent = audioChannelsPage.GetPreEventText();

        String postEvent = audioChannelsPage.GetPostEventText();
        int randPostEvent = 0;
        int random = 0;
        while(true){
            random = audioChannelsPage.GetRandomDigit(0,100);
            if(random!= Integer.parseInt(postEvent)){
                randPostEvent = random;
                break;
            }
        }
        logger.log(LogStatus.INFO,"Change post event seconds");
        audioChannelsPage.InputPostEventSeconds(""+randPostEvent);
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to Streams page");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Click cancel on unsaved changes button");
        audioChannelsPage.CancelUnsavedChanges();

        String option = audioChannelsPage.GetSelectedRecModeOption(0);
        Assert.assertEquals(option,"Event");

        logger.log(LogStatus.INFO,"Check that pre event time change is stayed");
        String postEventRelevent = audioChannelsPage.GetPostEventText();
        Assert.assertEquals(postEventRelevent, ""+randPostEvent);
    }

    @Test
    public void ChangePostEventTimeExitAndPressDoNotSaveTest() throws InterruptedException {
        logger=report.startTest("ChangePostEventTimeExitAndPressDoNotSaveTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        audioChannelsPage.IfScheduleIsNotExistAddIt();
        String selectedOption = audioChannelsPage.GetSelectedRecModeOption(0);

        if(!selectedOption.equals("Event")){
            logger.log(LogStatus.INFO,"Select Event recording Mode");
            audioChannelsPage.SelectRecordingModeOption(0, "Event");
        }

        String postEvent = audioChannelsPage.GetPostEventText();
        int randPostEvent = 0;
        int random = 0;
        while(true){
            random = audioChannelsPage.GetRandomDigit(0,100);
            if(random!= Integer.parseInt(postEvent)){
                randPostEvent = random;
                break;
            }
        }
        logger.log(LogStatus.INFO,"Change post event seconds");
        audioChannelsPage.InputPostEventSeconds(""+randPostEvent);
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to Streams page");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Click Do Not Save on unsaved changes button");
        audioChannelsPage.PressDontSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        if(selectedOption.equals("Continuous")){
            String option = audioChannelsPage.GetSelectedRecModeOption(0);
            Assert.assertEquals(option,"Continuous" );
        }

        if(selectedOption.equals("Event")){
            logger.log(LogStatus.INFO,"Check that post event time change isn't saved");
            String postEventRelevent = audioChannelsPage.GetPostEventText();
            Assert.assertEquals(postEventRelevent, postEvent);
        }
    }

    @Test 
    public void ChangePostEventTimeExitAndPressSaveTest() throws InterruptedException {
        logger=report.startTest("ChangePostEventTimeExitAndPressSaveTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        audioChannelsPage.IfScheduleIsNotExistAddIt();
        String selectedOption = audioChannelsPage.GetSelectedRecModeOption(0);

        if(!selectedOption.equals("Event")){
            logger.log(LogStatus.INFO,"Select Event recording Mode");
            audioChannelsPage.SelectRecordingModeOption(0, "Event");
        }

        String postEvent = audioChannelsPage.GetPostEventText();
        int randPostEvent = 0;
        int random = 0;
        while(true){
            random = audioChannelsPage.GetRandomDigit(0,100);
            if(random!= Integer.parseInt(postEvent)){
                randPostEvent = random;
                break;
            }
        }
        logger.log(LogStatus.INFO,"Change post event seconds");
        audioChannelsPage.InputPostEventSeconds(""+randPostEvent);
        audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to Streams page");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Click Save on unsaved changes button");
        audioChannelsPage.PressSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Check that post event time change is saved");
        String option = audioChannelsPage.GetSelectedRecModeOption(0);
        Assert.assertEquals(option,"Event" );

        String postEventRelevent = audioChannelsPage.GetPostEventText();
        Assert.assertEquals(postEventRelevent, ""+random);
    }

    @Test  
    public void SelectAnyEventsAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("SelectAnyEventsAndPressCancelTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();
        audioChannelsPage.IfScheduleIsNotExistAddIt();

        logger.log(LogStatus.INFO,"select Event recording Mode");
        audioChannelsPage.SelectRecordingModeOption(0, "Event");

        int evenSizeBefore = audioChannelsPage.GetSelectedEventsSize();

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

        logger.log(LogStatus.INFO,"Press on Cancel button");
        audioChannelsPage.PressCancelButton();

        logger.log(LogStatus.INFO,"Check that events count in list is 0");
        int evenSize = audioChannelsPage.GetSelectedEventsSize();
        Assert.assertEquals(evenSize, evenSizeBefore);

        logger.log(LogStatus.INFO,"Go to the Streams page");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Check that Streams page is loaded");
        Assert.assertTrue(audioChannelsPage.StreamsPageIsLoaded());
    }

    @Test  
    public void SelectAnyEventsExitAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("SelectAnyEventsExitAndPressCancelTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();
        audioChannelsPage.IfScheduleIsNotExistAddIt();

        logger.log(LogStatus.INFO,"select Event recording Mode");
        audioChannelsPage.SelectRecordingModeOption(0,"Event");

        int evenSizeBefore = audioChannelsPage.GetSelectedEventsSize();

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

        logger.log(LogStatus.INFO,"Go to the Streams page");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Press on Cancel button in unsaved changes dialog");
        audioChannelsPage.CancelUnsavedChanges();

        logger.log(LogStatus.INFO,"Check that events count in list is 1");
        int evenSize = audioChannelsPage.GetSelectedEventsSize();
        Assert.assertEquals(evenSize, 1);

        logger.log(LogStatus.INFO,"Check that Save and Cancel buttons are enabled");
        Assert.assertTrue(audioChannelsPage.CancelButtonIsEnable());
        Assert.assertTrue(audioChannelsPage.SaveButtonIsEnable());
    }

    @Test  
    public void SelectAnyEventsExitAndPressDoNotSaveTest() throws InterruptedException {
        logger=report.startTest("SelectAnyEventsExitAndPressDoNotSaveTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();
        audioChannelsPage.IfScheduleIsNotExistAddIt();

        logger.log(LogStatus.INFO,"select Event recording Mode");
        audioChannelsPage.SelectRecordingModeOption(0, "Event");

        int evenSizeBefore = audioChannelsPage.GetSelectedEventsSize();

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

        logger.log(LogStatus.INFO,"Go to Streams page");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Click Do Not Save on unsaved changes button");
        audioChannelsPage.PressDontSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Check that events count in list is "+evenSizeBefore);
        int evenSize = audioChannelsPage.GetSelectedEventsSize();
        Assert.assertEquals(evenSize, evenSizeBefore);
    }

    @Test
    public void SelectAnyEventsExitAndPressSaveTest() throws InterruptedException {
        logger=report.startTest("SelectAnyEventsExitAndPressSaveTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();
        audioChannelsPage.IfScheduleIsNotExistAddIt();

        logger.log(LogStatus.INFO,"select Event recording Mode");
        audioChannelsPage.SelectRecordingModeOption(0, "Event");

        int evenSizeBefore = audioChannelsPage.GetSelectedEventsSize();

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

        logger.log(LogStatus.INFO,"Go to Streams page");
        audioChannelsPage.GoToStreamsPage();

        logger.log(LogStatus.INFO,"Click Save on unsaved changes button");
        audioChannelsPage.PressSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Check that events count in list is 1");
        int evenSize = audioChannelsPage.GetSelectedEventsSize();
        Assert.assertEquals(evenSize, 1);
    }

    @Test
    public void SelectTwoEventsDeleteAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("SelectTwoEventsDeleteAndPressCancelTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        audioChannelsPage.IfScheduleIsNotExistAddIt();

        logger.log(LogStatus.INFO,"Select Event recording Mode");
        audioChannelsPage.SelectRecordingModeOption(0, "Event");

        int evenSizeBefore = audioChannelsPage.GetSelectedEventsSize();

        logger.log(LogStatus.INFO,"Press on Specify Events button");
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

        Thread.sleep(1000);
        int size = audioChannelsPage.CountOfDevicesInSpecifyEventsWindow();
        if(size>1){
            logger.log(LogStatus.INFO,"Select first and last device");
            audioChannelsPage.ClickOnDeviceInSpecifyEventsDevicesList(0);
            audioChannelsPage.ClickOnDeviceInSpecifyEventsDevicesList(size-1);

            logger.log(LogStatus.INFO,"Press on Apply and Close button");
            audioChannelsPage.PressOnApplyAndCloseButtonInSpecifyEvents();

            logger.log(LogStatus.INFO,"Check that two events are added in events list");
            int eventsSize = audioChannelsPage.GetSelectedEventsSize();
            Assert.assertEquals(eventsSize, 2);

            logger.log(LogStatus.INFO,"Delete first event");
            audioChannelsPage.ClickOnDeleteEventIcon(eventsSize-1);

            logger.log(LogStatus.INFO,"Check that event is removed");
            int eventsSizeAfterRemoving = audioChannelsPage.GetSelectedEventsSize();
            Assert.assertEquals(eventsSizeAfterRemoving, eventsSize-1);

            logger.log(LogStatus.INFO,"Press on Cancel button");
            audioChannelsPage.PressCancelButton();

            logger.log(LogStatus.INFO,"Check that event removing is canceled");
            eventsSizeAfterRemoving = audioChannelsPage.GetSelectedEventsSize();
            Assert.assertEquals(eventsSizeAfterRemoving, evenSizeBefore);

            logger.log(LogStatus.INFO,"Go to the Streams page");
            audioChannelsPage.GoToStreamsPage();

            logger.log(LogStatus.INFO,"Check that Streams page is loaded");
            Assert.assertTrue(audioChannelsPage.StreamsPageIsLoaded());
        }
    }

    @Test
    public void SelectTwoEventsDeleteExitAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("SelectTwoEventsDeleteExitAndPressCancelTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

//        logger.log(LogStatus.INFO,"Check Limit Retention Toggler is On or Off for device"+channelText); // due to bug
//        boolean status= audioChannelsPage.LimitRetentionToggleIsOn();

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

        int evenSizeBefore = audioChannelsPage.GetSelectedEventsSize();

        logger.log(LogStatus.INFO,"press on Specify Events button");
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

        Thread.sleep(1000);
        int size = audioChannelsPage.CountOfDevicesInSpecifyEventsWindow();
        if (size>1){
            logger.log(LogStatus.INFO,"Select first and last device");
            audioChannelsPage.ClickOnDeviceInSpecifyEventsDevicesList(0);
            audioChannelsPage.ClickOnDeviceInSpecifyEventsDevicesList(size-1);

            logger.log(LogStatus.INFO,"Press on Apply and Close button");
            audioChannelsPage.PressOnApplyAndCloseButtonInSpecifyEvents();

            logger.log(LogStatus.INFO,"Press on Save button");
            audioChannelsPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that two events are added in events list");
            int eventsSize = audioChannelsPage.GetSelectedEventsSize();
            Assert.assertEquals(eventsSize, 2);

            logger.log(LogStatus.INFO,"Delete first event");
            audioChannelsPage.ClickOnDeleteEventIcon(eventsSize-1);

            logger.log(LogStatus.INFO,"Check that event is removed");
            int eventsSizeAfterRemoving = audioChannelsPage.GetSelectedEventsSize();
            Assert.assertEquals(eventsSizeAfterRemoving, eventsSize-1);

            logger.log(LogStatus.INFO,"Go to the Streams page");
            audioChannelsPage.GoToStreamsPage();

            logger.log(LogStatus.INFO,"Press on Cancel button in unsaved changes dialog");
            audioChannelsPage.CancelUnsavedChanges();

            logger.log(LogStatus.INFO,"Check that events count in list is "+(evenSizeBefore+1));
            int evenSize = audioChannelsPage.GetSelectedEventsSize();
            Assert.assertEquals(evenSize, eventsSize-1);

            logger.log(LogStatus.INFO,"Check that Save and Cancel buttons are enabled");
            Assert.assertTrue(audioChannelsPage.CancelButtonIsEnable());
            Assert.assertTrue(audioChannelsPage.SaveButtonIsEnable());
        }
    }

    @Test
    public void SelectTwoEventsDeleteExitAndPressDoNotSaveTest() throws InterruptedException {
        logger=report.startTest("SelectTwoEventsDeleteExitAndPressDoNotSaveTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        audioChannelsPage.IfScheduleIsNotExistAddIt();

        logger.log(LogStatus.INFO,"Select Event recording Mode");
        audioChannelsPage.SelectRecordingModeOption(0, "Event");

        int evenSizeBefore = audioChannelsPage.GetSelectedEventsSize();

        logger.log(LogStatus.INFO,"press on Specify Events button");
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

        Thread.sleep(1000);
        int size = audioChannelsPage.CountOfDevicesInSpecifyEventsWindow();
        if(size>1){
            logger.log(LogStatus.INFO,"Select first device");
            audioChannelsPage.ClickOnDeviceInSpecifyEventsDevicesList(0);

            logger.log(LogStatus.INFO,"Select last device");
            audioChannelsPage.ClickOnDeviceInSpecifyEventsDevicesList(size-1);

            logger.log(LogStatus.INFO,"Press on Apply and Close button");
            audioChannelsPage.PressOnApplyAndCloseButtonInSpecifyEvents();

//        logger.log(LogStatus.INFO,"Press on Save button");
//        audioChannelsPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that two events are added in events list");
            int eventsSize = audioChannelsPage.GetSelectedEventsSize();
            Assert.assertEquals(eventsSize, 2);

            logger.log(LogStatus.INFO,"Delete first event");
            audioChannelsPage.ClickOnDeleteEventIcon(eventsSize-1);

            logger.log(LogStatus.INFO,"Check that event is removed");
            int eventsSizeAfterRemoving = audioChannelsPage.GetSelectedEventsSize();
            Assert.assertEquals(eventsSizeAfterRemoving, eventsSize-1);

            logger.log(LogStatus.INFO,"Go to the Streams page");
            audioChannelsPage.GoToStreamsPage();

            logger.log(LogStatus.INFO,"Click Do Not Save on unsaved changes button");
            audioChannelsPage.PressDontSaveUnsavedChanges();

            logger.log(LogStatus.INFO,"Go to Recording Page");
            audioChannelsPage.GoToRecordingPage();

            logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
            audioChannelsPage.ClickOnChannel(channelText);

            logger.log(LogStatus.INFO,"Check that events count in list is "+(evenSizeBefore+2));
            int evenSize = audioChannelsPage.GetSelectedEventsSize();
            Assert.assertEquals(evenSize, evenSizeBefore);
        }
    }

    @Test
    public void SelectTwoEventsDeleteExitAndPressSaveTest() throws InterruptedException {
        logger=report.startTest("SelectTwoEventsDeleteExitAndPressSaveTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        audioChannelsPage.IfScheduleIsNotExistAddIt();

        logger.log(LogStatus.INFO,"Select Event recording Mode");
        audioChannelsPage.SelectRecordingModeOption(0, "Event");

        int evenSizeBefore = audioChannelsPage.GetSelectedEventsSize();

        logger.log(LogStatus.INFO,"press on Specify Events button");
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

        Thread.sleep(1000);
        int size = audioChannelsPage.CountOfDevicesInSpecifyEventsWindow();
        if(size>1){
            logger.log(LogStatus.INFO,"Select first device");
            audioChannelsPage.ClickOnDeviceInSpecifyEventsDevicesList(0);

            logger.log(LogStatus.INFO,"Select last device");
            audioChannelsPage.ClickOnDeviceInSpecifyEventsDevicesList(size-1);

            logger.log(LogStatus.INFO,"Press on Apply and Close button");
            audioChannelsPage.PressOnApplyAndCloseButtonInSpecifyEvents();

            logger.log(LogStatus.INFO,"Check that two events are added in events list");
            int eventsSize = audioChannelsPage.GetSelectedEventsSize();
            Assert.assertEquals(eventsSize, 2);

            logger.log(LogStatus.INFO,"Delete first event");
            audioChannelsPage.ClickOnDeleteEventIcon(eventsSize-1);

            logger.log(LogStatus.INFO,"Check that event is removed");
            int eventsSizeAfterRemoving = audioChannelsPage.GetSelectedEventsSize();
            Assert.assertEquals(eventsSizeAfterRemoving, eventsSize-1);

            logger.log(LogStatus.INFO,"Go to the Streams page");
            audioChannelsPage.GoToStreamsPage();

            logger.log(LogStatus.INFO,"Click Save on unsaved changes button");
            audioChannelsPage.PressSaveUnsavedChanges();

            logger.log(LogStatus.INFO,"Go to Recording Page");
            audioChannelsPage.GoToRecordingPage();

            logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
            audioChannelsPage.ClickOnChannel(channelText);

            logger.log(LogStatus.INFO,"Check that events count in list is "+(evenSizeBefore+1));
            int evenSize = audioChannelsPage.GetSelectedEventsSize();
            Assert.assertEquals(evenSize, 1);
        }
    }

    @Test
    public void CreateNewSchedulePlanAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("CreateNewSchedulePlanAndPressCancelTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        int schedulePlans= audioChannelsPage.GetSizeSchedulePlans();

        logger.log(LogStatus.INFO,"Click on the Plus button to add new schedule plan");
        audioChannelsPage.ClickOnPlusScheduleButtons();

        logger.log(LogStatus.INFO,"Select schedule in new added plan");
        int schedulesCount = audioChannelsPage.GetSchedulesCount(schedulePlans);
        if(schedulesCount>2){
            int index = audioChannelsPage.GetRandomDigit(1, schedulesCount-1);
            audioChannelsPage.SelectScheduleByIndex(schedulePlans, index);

            logger.log(LogStatus.INFO,"Select resording mode in new added plan");
            int ind = audioChannelsPage.GetRandomDigit(0, 2);
            audioChannelsPage.SelectRecordingModeByIndex(schedulePlans, ind);

            logger.log(LogStatus.INFO,"Press on Cancel button");
            audioChannelsPage.PressCancelButton();

            logger.log(LogStatus.INFO,"Check that adding of new schedule plan is canceled");
            int schedulePlansCounts= audioChannelsPage.GetSizeSchedulePlans();
            Assert.assertEquals(schedulePlansCounts,schedulePlans);
        }
    }

    @Test
    public void CreateNewSchedulePlanExitAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("CreateNewSchedulePlanExitAndPressCancelTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        int schedulePlans= audioChannelsPage.GetSizeSchedulePlans();

        logger.log(LogStatus.INFO,"Click on the Plus button to add new schedule plan");
        audioChannelsPage.ClickOnPlusScheduleButtons();

        logger.log(LogStatus.INFO,"Select schedule in new added plan");
        int schedulesCount = audioChannelsPage.GetSchedulesCount(schedulePlans);
        if(schedulesCount>2){
            int index = audioChannelsPage.GetRandomDigit(1, schedulesCount-1);
            audioChannelsPage.SelectScheduleByIndex(schedulePlans, index);

            logger.log(LogStatus.INFO,"Select recording mode in new added plan");
            int ind = audioChannelsPage.GetRandomDigit(0, 2);
            audioChannelsPage.SelectRecordingModeByIndex(schedulePlans, ind);
            audioChannelsPage.WaitUntilSaveButtonWillBeEnable();

            logger.log(LogStatus.INFO,"Go to the Streams page");
            audioChannelsPage.GoToStreamsPage();

            logger.log(LogStatus.INFO,"Press on Cancel button in unsaved changes dialog");
            audioChannelsPage.CancelUnsavedChanges();

            logger.log(LogStatus.INFO,"Check that adding of new schedule plan is stayed");
            int schedulePlansCounts= audioChannelsPage.GetSizeSchedulePlans();
            Assert.assertEquals(schedulePlansCounts, schedulePlans+1);

            logger.log(LogStatus.INFO,"Check that Save and Cancel buttons are enabled");
            Assert.assertTrue(audioChannelsPage.CancelButtonIsEnable());
            Assert.assertTrue(audioChannelsPage.SaveButtonIsEnable());
        }
    }

    @Test
    public void CreateNewSchedulePlanExitAndPressDoNotSaveTest() throws InterruptedException {
        logger=report.startTest("CreateNewSchedulePlanExitAndPressDoNotSaveTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        int schedulePlans= audioChannelsPage.GetSizeSchedulePlans();

        logger.log(LogStatus.INFO,"Click on the Plus button to add new schedule plan");
        audioChannelsPage.ClickOnPlusScheduleButtons();

        logger.log(LogStatus.INFO,"Select schedule in new added plan");
        int schedulesCount = audioChannelsPage.GetSchedulesCount(schedulePlans);
        if(schedulesCount>2){
            int index = audioChannelsPage.GetRandomDigit(1, schedulesCount-1);
            audioChannelsPage.SelectScheduleByIndex(schedulePlans, index);

            logger.log(LogStatus.INFO,"Select recording mode in new added plan");
            int ind = audioChannelsPage.GetRandomDigit(0, 2);
            audioChannelsPage.SelectRecordingModeByIndex(schedulePlans, ind);

            logger.log(LogStatus.INFO,"Go to the Streams page");
            audioChannelsPage.GoToStreamsPage();

            logger.log(LogStatus.INFO,"Click Do Not Save on unsaved changes button");
            audioChannelsPage.PressDontSaveUnsavedChanges();

            logger.log(LogStatus.INFO,"Go to Recording Page");
            audioChannelsPage.GoToRecordingPage();

            logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
            audioChannelsPage.ClickOnChannel(channelText);

            logger.log(LogStatus.INFO,"Check that adding of new schedule plan isn't saved");
            int schedulePlansCounts= audioChannelsPage.GetSizeSchedulePlans();
            Assert.assertEquals(schedulePlansCounts, schedulePlans);
        }
    }

    @Test
    public void CreateNewSchedulePlanExitAndPressSaveTest() throws InterruptedException {
        logger=report.startTest("CreateNewSchedulePlanExitAndPressSaveTest");

        String channelText = channel.getText();
        logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
        audioChannelsPage.ClickOnChannel(channelText);

        logger.log(LogStatus.INFO,"Go to Recording Page");
        audioChannelsPage.GoToRecordingPage();

        int schedulePlans= audioChannelsPage.GetSizeSchedulePlans();

        logger.log(LogStatus.INFO,"Click on the Plus button to add new schedule plan");
        audioChannelsPage.ClickOnPlusScheduleButtons();

        logger.log(LogStatus.INFO,"Select schedule in new added plan");
        int schedulesCount = audioChannelsPage.GetSchedulesCount(schedulePlans);
        if(schedulesCount>2){ int index = audioChannelsPage.GetRandomDigit(1, schedulesCount-1);
            audioChannelsPage.SelectScheduleByIndex(schedulePlans, index);

            logger.log(LogStatus.INFO,"Select recording mode in new added plan");
            int ind = audioChannelsPage.GetRandomDigit(0, 2);
            audioChannelsPage.SelectRecordingModeByIndex(schedulePlans, ind);

            logger.log(LogStatus.INFO,"Go to the Streams page");
            audioChannelsPage.GoToStreamsPage();

            logger.log(LogStatus.INFO,"Click Save on unsaved changes button");
            audioChannelsPage.PressSaveUnsavedChanges();

            logger.log(LogStatus.INFO,"Go to Recording Page");
            audioChannelsPage.GoToRecordingPage();

            logger.log(LogStatus.INFO,"Click on the channel "+ channelText);
            audioChannelsPage.ClickOnChannel(channelText);

            logger.log(LogStatus.INFO,"Check that adding of new schedule plan is saved");
            int schedulePlansCounts= audioChannelsPage.GetSizeSchedulePlans();
            Assert.assertEquals(schedulePlansCounts, schedulePlans+1);
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
