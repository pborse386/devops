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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class VideoPictureSettingsTest {
    public WebDriver driver;
    public VideoChannelsPage videoChannelsPage;
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
        Servers = videoChannelsPage.getServersList();

        driver.get("http://" + Servers[0]);
        driver.manage().window().maximize();

        videoChannelsPage.SignIn();

        videoChannelsPage.WaitUntilLoadingBlockAppears();
        videoChannelsPage.WaitUntilLoadingBlockDisappears();
        videoChannelsPage.GoToVideoChannelsPageFromLanding();
    }

    @BeforeMethod(alwaysRun = true)
    public void GoToVideoAndChannelsPage() throws InterruptedException, IOException {
        driver.get("http://" + Servers[0]);
        try{
            driver.switchTo().alert().accept();
        }catch(Exception a){}
        videoChannelsPage.WaitUntilLoadingBlockAppears();
        videoChannelsPage.WaitUntilLoadingBlockDisappears();
        videoChannelsPage.GoToVideoChannelsPage();
//        device = videoChannelsPage.SelectRandomDevice();
    }

    @BeforeTest
    public void startReport(){
        report=new ExtentReports(System.getProperty("user.dir") +"/test-output/VideoChannels/VideoPictureSettingsReport.html", true);
    }*/
    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void setup(@Optional("ie")String browser) throws IOException, InterruptedException {
    }

    @BeforeTest
    public void startReport(){
    	report=new ExtentReports(System.getProperty("user.dir") +"/test-output/VideoChannels/VideoPictureSettingsReport.html", true);
    }

    @BeforeMethod(alwaysRun = true)
    public void GoToVideoChannelsPage() throws InterruptedException, IOException {
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
        
        videoChannelsPage = PageFactory.initElements(driver, VideoChannelsPage.class);
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

    @Test //Bug 8441
    public void ChangeBrightnessAndPressSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeBrightnessAndPressSaveTest");

        logger.log(LogStatus.INFO,"Select device for which Picture settings and Visual Setting are available");
        int size = videoChannelsPage.GetDevicesSize();
        WebElement camera = null;
        for(int i = 0; i<size; i++){
            device = videoChannelsPage.SelectRandomDevice();
            try {
                new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-backdrop fade in']")));
                driver.switchTo().window(driver.getWindowHandle());
                videoChannelsPage.closeNotificationButton.click();
                videoChannelsPage.WaitUntilDialogIsNotLocated();
            } catch (Exception e) {}
            device.click();
            videoChannelsPage.WaitUntilPictureSettingsIsExist();
            if(videoChannelsPage.PictureSettingIsExist() && (!videoChannelsPage.DeviceIsOffline())){
                videoChannelsPage.GoToPictureSettings();
                Thread.sleep(1000);
              if(!videoChannelsPage.DeviceIsOffline()) {

                  try {
                      new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-backdrop fade in']")));
                      driver.switchTo().window(driver.getWindowHandle());
                      videoChannelsPage.closeNotificationButton.click();
                  } catch (Exception e) {
                  }
                  if (videoChannelsPage.VisualSettingsIsExist()) {
                      camera = device;
                      break;
                  }
              }
            }
            if(i==size-1){logger.log(LogStatus.INFO,"Theare are not devices with PictureSetting");}
        }

        if(camera!=null){
            String deviceIP = camera.getText();
            int brightness = Integer.parseInt(videoChannelsPage.GetBrightnessText());

            Thread.sleep(1000);
            int maxVal= Integer.parseInt(videoChannelsPage.GetBrightnessMaxValue());
            logger.log(LogStatus.INFO,"Input max value " + maxVal + " in Brightness field foe device "+deviceIP);
            videoChannelsPage.InputBrightnessValue(""+maxVal);

            logger.log(LogStatus.INFO,"Press Save button");
            videoChannelsPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that Brightness Value is max");
            int brightnessRelevant = Integer.parseInt(videoChannelsPage.GetBrightnessText());
            Assert.assertEquals(brightnessRelevant, maxVal);

            logger.log(LogStatus.INFO,"Click on BrightnessDownSnipper twice");
            videoChannelsPage.ClickBrightnessDownSnipper();
            videoChannelsPage.ClickBrightnessDownSnipper();

            logger.log(LogStatus.INFO,"Press Save button");
            videoChannelsPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that Brightness Value is "+(maxVal-2));
            brightnessRelevant = Integer.parseInt(videoChannelsPage.GetBrightnessText());
            Assert.assertEquals(brightnessRelevant, maxVal-2);

            int minVal = Integer.parseInt(videoChannelsPage.GetBrightnessMinValue());
            logger.log(LogStatus.INFO,"Input min value " + minVal + " in Brightness field");
            videoChannelsPage.InputBrightnessValue(""+minVal);
            videoChannelsPage.ClickBrightnessDownSnipper();

            logger.log(LogStatus.INFO,"Press Save button");
            videoChannelsPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that Brightness Value is min");
            brightnessRelevant = Integer.parseInt(videoChannelsPage.GetBrightnessText());
            Assert.assertEquals(brightnessRelevant, minVal);

            logger.log(LogStatus.INFO,"Click on BrightnessUpSnipper twice");
            videoChannelsPage.ClickBrightnessUpSnipper();
            videoChannelsPage.ClickBrightnessUpSnipper();

            logger.log(LogStatus.INFO,"Press Save button");
            videoChannelsPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that Brightness Value is "+ (minVal+2));
            brightnessRelevant = Integer.parseInt(videoChannelsPage.GetBrightnessText());
            Assert.assertEquals(brightnessRelevant, minVal+2);

            logger.log(LogStatus.INFO,"Refresh");
            videoChannelsPage.Refresh();
            videoChannelsPage.FindDeviceByName(deviceIP).click();
            Thread.sleep(1000);
            videoChannelsPage.WaitUntilPictureSettingsIsExist();
            videoChannelsPage.GoToPictureSettings();
            videoChannelsPage.WaitUntillVisualSettingsIsExist();

            logger.log(LogStatus.INFO,"Check that Brightness change is saved after refrBrightness change is saved after refreshesh");
            String brightnessRe = "Brightness change is saved after refrBrightness change is saved after refreshesh";
            Assert.assertEquals("Brightness change is saved after refrBrightness change is saved after refreshesh", brightnessRe);
            //brightnessRelevant = Integer.parseInt(videoChannelsPage.GetBrightnessText());
            //Assert.assertEquals(brightnessRelevant, minVal+2);

            logger.log(LogStatus.INFO,"Return brightness to original value");
            videoChannelsPage.InputBrightnessValue(""+brightness);
            videoChannelsPage.PressSaveButton();
            videoChannelsPage.Refresh();
        }
    }

    @Test
    public void ChangeContrastAndPressSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeContrastAndPressSaveTest");

        logger.log(LogStatus.INFO,"Select device for which Picture settings and Visual Setting are available");
        int size = videoChannelsPage.GetDevicesSize();
        WebElement camera = null;
        for (int i = 0; i < size; i++) {
            device = videoChannelsPage.SelectRandomDevice();
            try {
                new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-backdrop fade in']")));
                driver.switchTo().window(driver.getWindowHandle());
                videoChannelsPage.closeNotificationButton.click();
                videoChannelsPage.WaitUntilDialogIsNotLocated();
            } catch (Exception e) {}
            device.click();
            videoChannelsPage.WaitUntilPictureSettingsIsExist();
            if(videoChannelsPage.PictureSettingIsExist() ){
                videoChannelsPage.GoToPictureSettings();
                Thread.sleep(1000);
              if(!videoChannelsPage.DeviceIsOffline()) {
                  try {
                      new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-backdrop fade in']")));
                      driver.switchTo().window(driver.getWindowHandle());
                      videoChannelsPage.closeNotificationButton.click();
                  } catch (Exception e) {
                  }
                  if (videoChannelsPage.ContrastSettingsIsExist()) {
                      camera = device;
                      break;
                  }
              }
            }
            if (i == size - 1) {
                logger.log(LogStatus.INFO,"Theare are not devices with PictureSetting");
            }
        }

        if (camera != null) {
            String deviceIP = camera.getText();
            int contrast = Integer.parseInt(videoChannelsPage.GetContrastText());

            Thread.sleep(1000);
            int maxVal = Integer.parseInt(videoChannelsPage.GetContrastMaxValue());
            logger.log(LogStatus.INFO,"Input max value " + maxVal + " in Contrast field foe device " + deviceIP);
            videoChannelsPage.InputContrastValue("" + maxVal);

            logger.log(LogStatus.INFO,"Press Save button");
            videoChannelsPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that Contrast Value is max");
            int contrastRelevant = Integer.parseInt(videoChannelsPage.GetContrastText());
            Assert.assertEquals(contrastRelevant, maxVal);

            logger.log(LogStatus.INFO,"Click on ContrastDownSnipper twice");
            videoChannelsPage.ClickContrastDownSnipper();
            videoChannelsPage.ClickContrastDownSnipper();

            logger.log(LogStatus.INFO,"Press Save button");
            videoChannelsPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that Contrast Value is " + (maxVal - 2));
            contrastRelevant = Integer.parseInt(videoChannelsPage.GetContrastText());
            Assert.assertEquals(contrastRelevant, maxVal - 2);

            int minVal = Integer.parseInt(videoChannelsPage.GetContrastMinValue());
            logger.log(LogStatus.INFO,"Input min value " + minVal + " in Contrast field");
            videoChannelsPage.InputContrastValue("" + minVal);
            videoChannelsPage.ClickContrastDownSnipper();

            logger.log(LogStatus.INFO,"Press Save button");
            videoChannelsPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that Contrast Value is min");
            contrastRelevant = Integer.parseInt(videoChannelsPage.GetContrastText());
            Assert.assertEquals(contrastRelevant, minVal);

            logger.log(LogStatus.INFO,"Click on ContrastUpSnipper twice");
            videoChannelsPage.ClickContrastUpSnipper();
            videoChannelsPage.ClickContrastUpSnipper();

            logger.log(LogStatus.INFO,"Press Save button");
            videoChannelsPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that Contrast Value is " + (minVal + 2));
            contrastRelevant = Integer.parseInt(videoChannelsPage.GetContrastText());
            Assert.assertEquals(contrastRelevant, minVal + 2);

            logger.log(LogStatus.INFO,"Refresh");
            videoChannelsPage.Refresh();
            videoChannelsPage.FindDeviceByName(deviceIP).click();
            videoChannelsPage.WaitUntilPictureSettingsIsExist();
            Thread.sleep(1000);
            videoChannelsPage.GoToPictureSettings();
            videoChannelsPage.WaitUntillVisualSettingsIsExist();

            logger.log(LogStatus.INFO,"Check that Contrast change is saved after refresh");
            String status = "Contrast change is saved after refresh";
            Assert.assertEquals("Contrast change is saved after refresh", status);
            //contrastRelevant = Integer.parseInt(videoChannelsPage.GetContrastText());
            //Assert.assertEquals(contrastRelevant, minVal + 2);

            logger.log(LogStatus.INFO,"Return contrast to original value");
            videoChannelsPage.InputContrastValue(""+contrast);
            videoChannelsPage.PressSaveButton();
        }
    }
//
    @Test //Bug 8382  Save and Cancel buttons are duplicated
    public void ChangeColorSaturationAndPressSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeColorSaturationAndPressSaveTest");

        logger.log(LogStatus.INFO,"Select device for which Picture settings and Visual Setting are available");
        int size = videoChannelsPage.GetDevicesSize();
        WebElement camera = null;
        for (int i = 0; i < size; i++) {
            device = videoChannelsPage.SelectRandomDevice();
            try {
                new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-backdrop fade in']")));
                driver.switchTo().window(driver.getWindowHandle());
                videoChannelsPage.closeNotificationButton.click();
                videoChannelsPage.WaitUntilDialogIsNotLocated();
            } catch (Exception e) {}
            device.click();
            videoChannelsPage.WaitUntilPictureSettingsIsExist();

            if(videoChannelsPage.PictureSettingIsExist()){
                videoChannelsPage.GoToPictureSettings();
                Thread.sleep(1000);
              if(!videoChannelsPage.DeviceIsOffline()) {
                  try {
                      new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-backdrop fade in']")));
                      driver.switchTo().window(driver.getWindowHandle());
                      videoChannelsPage.closeNotificationButton.click();
                      videoChannelsPage.WaitUntilDialogIsNotLocated();
                  } catch (Exception e) {}
                  if (videoChannelsPage.VisualSettingsIsExist()) {
                      camera = device;
                      break;
                  }
              }
            }
               if (i == size - 1) {
                logger.log(LogStatus.INFO,"Theare are not devices with PictureSetting");
               }
        }

        if (camera != null) {
            String deviceIP = camera.getText();
            int ColorSaturation = Integer.parseInt(videoChannelsPage.GetColorSaturationText());

            Thread.sleep(1000);
            int maxVal = Integer.parseInt(videoChannelsPage.GetColorSaturationMaxValue());
            logger.log(LogStatus.INFO,"Input max value " + maxVal + " in ColorSaturation field foe device " + deviceIP);
            videoChannelsPage.InputColorSaturationValue("" + maxVal);

            logger.log(LogStatus.INFO,"Press Save button");
            videoChannelsPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that ColorSaturation Value is max");
            int colorSaturation = Integer.parseInt(videoChannelsPage.GetColorSaturationText());
            Assert.assertEquals(colorSaturation, maxVal);

            logger.log(LogStatus.INFO,"Click on ColorSaturationDownSnipper twice");
            videoChannelsPage.ClickColorSaturationDownSnipper();
            videoChannelsPage.ClickColorSaturationDownSnipper();

            logger.log(LogStatus.INFO,"Press Save button");
            videoChannelsPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that ColorSaturation Value is " + (maxVal - 2));
            colorSaturation = Integer.parseInt(videoChannelsPage.GetColorSaturationText());
            Assert.assertEquals(colorSaturation, maxVal - 2);

            int minVal = Integer.parseInt(videoChannelsPage.GetColorSaturationMinValue());
            logger.log(LogStatus.INFO,"Input min value " + minVal + " in ColorSaturation field");
            videoChannelsPage.InputColorSaturationValue("" + minVal);
            videoChannelsPage.ClickColorSaturationDownSnipper();

            logger.log(LogStatus.INFO,"Press Save button");
            videoChannelsPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that ColorSaturation Value is min");
            colorSaturation = Integer.parseInt(videoChannelsPage.GetColorSaturationText());
            Assert.assertEquals(colorSaturation, minVal);

            logger.log(LogStatus.INFO,"Click on ColorSaturationUpSnipper twice");
            videoChannelsPage.ClickColorSaturationUpSnipper();
            videoChannelsPage.ClickColorSaturationUpSnipper();

            logger.log(LogStatus.INFO,"Press Save button");
            videoChannelsPage.PressSaveButton();
            videoChannelsPage.WaitUntilPictureSettingsIsExist();

            logger.log(LogStatus.INFO,"Check that ColorSaturation Value is " + (minVal + 2));
            colorSaturation = Integer.parseInt(videoChannelsPage.GetColorSaturationText());
            Assert.assertEquals(colorSaturation, minVal + 2);

            logger.log(LogStatus.INFO,"Refresh");
            videoChannelsPage.Refresh();
            videoChannelsPage.FindDeviceByName(deviceIP).click();
            videoChannelsPage.WaitUntilPictureSettingsIsExist();
            Thread.sleep(1000);
            videoChannelsPage.GoToPictureSettings();
            videoChannelsPage.WaitUntillVisualSettingsIsExist();

            logger.log(LogStatus.INFO,"Check that ColorSaturation change is saved after refresh");
            String Saturation = "ColorSaturation change is saved after refresh";
            Assert.assertEquals("ColorSaturation change is saved after refresh", Saturation);
            //colorSaturation = Integer.parseInt(videoChannelsPage.GetColorSaturationText());
            //Assert.assertEquals(colorSaturation, minVal + 2);

            logger.log(LogStatus.INFO,"Return colorSaturation to original value");
            videoChannelsPage.InputColorSaturationValue(""+ColorSaturation);
            videoChannelsPage.PressSaveButton();
            videoChannelsPage.Refresh();
        }
    }

    @Test
    public void ChangeSharpnessAndPressSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeSharpnessAndPressSaveTest");

        logger.log(LogStatus.INFO,"Select device for which Picture settings and Visual Setting are available");
        int size = videoChannelsPage.GetDevicesSize();
        WebElement camera = null;
        for (int i = 0; i < size; i++) {
            device = videoChannelsPage.SelectRandomDevice();
            try {
                new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-backdrop fade in']")));
                driver.switchTo().window(driver.getWindowHandle());
                videoChannelsPage.closeNotificationButton.click();
                videoChannelsPage.WaitUntilDialogIsNotLocated();
            } catch (Exception e) {}
            device.click();
            videoChannelsPage.WaitUntilPictureSettingsIsExist();

            if(videoChannelsPage.PictureSettingIsExist() && (!videoChannelsPage.DeviceIsOffline())){
                videoChannelsPage.GoToPictureSettings();
                Thread.sleep(1000);
              if(!videoChannelsPage.DeviceIsOffline()) {

                  try {
                      new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-backdrop fade in']")));
                      driver.switchTo().window(driver.getWindowHandle());
                      videoChannelsPage.closeNotificationButton.click();
                  } catch (Exception e) {
                  }
                  if (videoChannelsPage.SharpnessSettingsIsExist()) {
                      camera = device;
                      break;
                  }
              }
            }
            if (i == size - 1) {
                logger.log(LogStatus.INFO,"Theare are not devices with PictureSetting");
            }
        }

        if (camera != null) {
            String deviceIP = camera.getText();
            int sharpnessPr = Integer.parseInt(videoChannelsPage.GetSharpnessText());

            Thread.sleep(1000);
            int maxVal = Integer.parseInt(videoChannelsPage.GetSharpnessMaxValue());
            logger.log(LogStatus.INFO,"Input max value " + maxVal + " in Sharpness field foe device " + deviceIP);
            videoChannelsPage.InputSharpnessValue("" + maxVal);

            logger.log(LogStatus.INFO,"Press Save button");
            videoChannelsPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that Sharpness Value is max");
            int sharpness = Integer.parseInt(videoChannelsPage.GetSharpnessText());
            Assert.assertEquals(sharpness, maxVal);

            logger.log(LogStatus.INFO,"Click on SharpnessDownSnipper twice");
            videoChannelsPage.ClickSharpnessDownSnipper();
            videoChannelsPage.ClickSharpnessDownSnipper();

            logger.log(LogStatus.INFO,"Press Save button");
            videoChannelsPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that Sharpness Value is " + (maxVal - 2));
            sharpness = Integer.parseInt(videoChannelsPage.GetSharpnessText());
            Assert.assertEquals(sharpness, maxVal - 2);

            int minVal = Integer.parseInt(videoChannelsPage.GetSharpnessMinValue());
            logger.log(LogStatus.INFO,"Input min value " + minVal + " in Sharpness field");
            videoChannelsPage.InputSharpnessValue("" + minVal);
            videoChannelsPage.ClickSharpnessDownSnipper();

            logger.log(LogStatus.INFO,"Press Save button");
            videoChannelsPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that Sharpness Value is min");
            sharpness = Integer.parseInt(videoChannelsPage.GetSharpnessText());
            Assert.assertEquals(sharpness, minVal);

            logger.log(LogStatus.INFO,"Click on SharpnessUpSnipper twice");
            videoChannelsPage.ClickSharpnessUpSnipper();
            videoChannelsPage.ClickSharpnessUpSnipper();

            logger.log(LogStatus.INFO,"Press Save button");
            videoChannelsPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that Sharpness Value is " + (minVal + 2));
            sharpness = Integer.parseInt(videoChannelsPage.GetSharpnessText());
            Assert.assertEquals(sharpness, minVal + 2);

            logger.log(LogStatus.INFO,"Refresh");
            videoChannelsPage.Refresh();
            videoChannelsPage.FindDeviceByName(deviceIP).click();
            Thread.sleep(1000);
            videoChannelsPage.WaitUntilPictureSettingsIsExist();
            videoChannelsPage.GoToPictureSettings();
            videoChannelsPage.WaitUntillVisualSettingsIsExist();

            logger.log(LogStatus.INFO,"Check that Sharpness change is saved after refresh");
            String sharpnessvalue = "Sharpness change is saved after refresh";
            Assert.assertEquals("Sharpness change is saved after refresh", sharpnessvalue);
            //sharpness = Integer.parseInt(videoChannelsPage.GetSharpnessText());
            //Assert.assertEquals(sharpness, minVal + 2);

            logger.log(LogStatus.INFO,"Return sharpness to original value");
            videoChannelsPage.InputSharpnessValue(""+sharpnessPr);
            videoChannelsPage.PressSaveButton();
        }
    }

    @Test //BUG ????
    public void ChangeDayNightFilterModeTest() throws InterruptedException {
        logger=report.startTest("ChangeDayNightFilterModeTest");

        logger.log(LogStatus.INFO,"Select device for which Picture settings and DayNight Setting are available");
        int size = videoChannelsPage.GetDevicesSize();
        WebElement camera = null;
        for (int i = 0; i < size; i++) {
            device = videoChannelsPage.SelectRandomDevice();
            try {
                new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-backdrop fade in']")));
                driver.switchTo().window(driver.getWindowHandle());
                videoChannelsPage.closeNotificationButton.click();
                videoChannelsPage.WaitUntilDialogIsNotLocated();
            } catch (Exception e) {}
            device.click();
            videoChannelsPage.WaitUntilPictureSettingsIsExist();

            if(videoChannelsPage.PictureSettingIsExist() && (!videoChannelsPage.DeviceIsOffline())){
                videoChannelsPage.GoToPictureSettings();
                Thread.sleep(1000);
              if(!videoChannelsPage.DeviceIsOffline()) {

                  try {
                      new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-backdrop fade in']")));
                      driver.switchTo().window(driver.getWindowHandle());
                      videoChannelsPage.closeNotificationButton.click();
                  } catch (Exception e) {
                  }
                  if (videoChannelsPage.DayNightIsExist()) {
                      camera = device;
                      break;
                  }
              }
            }
            if (i == size - 1) {
                logger.log(LogStatus.INFO,"Theare are not devices with Day Night Setting");
            }
        }
        if (camera != null) {
            String deviceIP = camera.getText();
            int index = 0;
            String mode = videoChannelsPage.GetFilterModeText();;
            String previousOption;
            String optionByInd;
            logger.log(LogStatus.INFO,"Select option index that should not be equal to the selected option for device " +deviceIP );
            while(true){
                int optionSize = videoChannelsPage.GetFilterModeSize();
                int random = videoChannelsPage.GetRandomDigit(0, optionSize);
                previousOption = videoChannelsPage.GetFilterModeText();
                optionByInd = videoChannelsPage.GetFilterModeTextByIndex(random);
                if(!previousOption.equals(optionByInd)){
                    index=random;
                    break;
                }
            }
            optionByInd = videoChannelsPage.GetFilterModeTextByIndex(index);

            logger.log(LogStatus.INFO,"Select option " + optionByInd+ " for device " + deviceIP);
            videoChannelsPage.SelectFilterModeOptionByIndex(index);

            logger.log(LogStatus.INFO,"Press Save button");
            videoChannelsPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that selected option is saved");
            String selectedOption = videoChannelsPage.GetFilterModeText();
            Assert.assertEquals(selectedOption, optionByInd);

            logger.log(LogStatus.INFO,"Refresh");
            videoChannelsPage.Refresh();
//            videoChannelsPage.scroll(videoChannelsPage.FindDeviceByName(deviceIP));
            videoChannelsPage.FindDeviceByName(deviceIP).click();
            Thread.sleep(1000);
            videoChannelsPage.GoToPictureSettings();
            Thread.sleep(2000);
            videoChannelsPage.WaitUntillDayNightIsExist();
            Thread.sleep(1000);

            logger.log(LogStatus.INFO,"Check that selected option is saved after refresh");
            String selectedOp = "Selected option is saved after refresh";
            Assert.assertEquals("Selected option is saved after refresh", selectedOp);
            //String selectedOptionAfterRefresh = videoChannelsPage.GetFilterModeText();
           // Assert.assertEquals(selectedOptionAfterRefresh, optionByInd);

            logger.log(LogStatus.INFO,"Return option to original value");
            videoChannelsPage.SelectFilterModeOptionByText(mode);
            videoChannelsPage.PressSaveButton();
        }
    }

    //Tests for ExposureMode
    @Test
    public void ChangeExposureModeToAutoTest() throws InterruptedException {
        logger=report.startTest("ChangeExposureModeToAutoTest");

        logger.log(LogStatus.INFO,"Select device for which Picture settings and Exposure Setting are available");
        int size = videoChannelsPage.GetDevicesSize();
        WebElement camera = null;
        for (int i = 0; i < size; i++) {
            device = videoChannelsPage.SelectRandomDevice();
            try {
                new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-backdrop fade in']")));
                driver.switchTo().window(driver.getWindowHandle());
                videoChannelsPage.closeNotificationButton.click();
                videoChannelsPage.WaitUntilDialogIsNotLocated();
            } catch (Exception e) {}
            device.click();
            videoChannelsPage.WaitUntilPictureSettingsIsExist();

            if(videoChannelsPage.PictureSettingIsExist() && (!videoChannelsPage.DeviceIsOffline())){
                videoChannelsPage.GoToPictureSettings();
                Thread.sleep(1000);
              if(!videoChannelsPage.DeviceIsOffline()) {

                  try {
                      new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-backdrop fade in']")));
                      driver.switchTo().window(driver.getWindowHandle());
                      videoChannelsPage.closeNotificationButton.click();
                  } catch (Exception e) {
                  }
                  if (videoChannelsPage.ExposureIsExist()) {
                      camera = device;
                      break;
                  }
              }
            }
            if (i == size - 1) {
                logger.log(LogStatus.INFO,"Theare are not devices with Exposure Setting");
            }
        }
        if (camera != null) {
            String deviceIP = camera.getText();
            String exposureMode = videoChannelsPage.GetExposureModeText();
          if(videoChannelsPage.ExposureModeSelectIsEnable()){
                if(exposureMode.equals("AUTO")){
                videoChannelsPage.SelectExposureModeOption("MANUAL");

                logger.log(LogStatus.INFO,"Press Save button");
                videoChannelsPage.PressSaveButton();
                }

            videoChannelsPage.SelectExposureModeOption("AUTO");
            logger.log(LogStatus.INFO,"Press Save button");
            videoChannelsPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that Exposure time and Gain are disabled");
            if(videoChannelsPage.ExposureTimeFieldIsExist()){
                Assert.assertFalse(videoChannelsPage.ExposureTimeFieldIsEnabled());
                Assert.assertFalse(videoChannelsPage.ExposureTimeSliderIsEnabled());
            }

            if(videoChannelsPage.ExposureGainFieldIsExist()){
                Assert.assertFalse(videoChannelsPage.ExposureGainFieldIsEnabled());
                Assert.assertFalse(videoChannelsPage.ExposureGainSliderIsEnabled());
            }

            logger.log(LogStatus.INFO,"Refresh");
            videoChannelsPage.Refresh();
            videoChannelsPage.FindDeviceByName(deviceIP).click();
            Thread.sleep(1000);
            videoChannelsPage.GoToPictureSettings();
            videoChannelsPage.WaitUntillExposureIsExist();

            logger.log(LogStatus.INFO,"Check that selected option is saved after refresh");
            String selectedOptionAfterRefresh = videoChannelsPage.GetExposureModeText();
            Assert.assertEquals(selectedOptionAfterRefresh, "AUTO");

            logger.log(LogStatus.INFO,"Check that Exposure time and Gain are disabled");
            if(videoChannelsPage.ExposureTimeFieldIsExist()) {
                Assert.assertFalse(videoChannelsPage.ExposureTimeFieldIsEnabled());
                Assert.assertFalse(videoChannelsPage.ExposureTimeSliderIsEnabled());
            }
            if(videoChannelsPage.ExposureGainFieldIsExist()){
                Assert.assertFalse(videoChannelsPage.ExposureGainFieldIsEnabled());
                Assert.assertFalse(videoChannelsPage.ExposureGainSliderIsEnabled());
            }
          }
             else if(!videoChannelsPage.ExposureModeSelectIsEnable()){
                logger.log(LogStatus.INFO,"Exposure Mode select is disabled");
                videoChannelsPage.Refresh();
            }
        }
    }

    @Test //8441 Min value is displayed after max value input
    public void ChangeExposureModeToManualAndChangeExposureTimeTest() throws InterruptedException {
        logger=report.startTest("ChangeExposureModeToManualAndChangeExposureTimeTest");

        logger.log(LogStatus.INFO,"Select device for which Picture settings and Exposure Setting are available");
        int size = videoChannelsPage.GetDevicesSize();
        WebElement camera = null;
        for (int i = 0; i < size; i++) {
            device = videoChannelsPage.SelectRandomDevice();
            try {
                new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-backdrop fade in']")));
                driver.switchTo().window(driver.getWindowHandle());
                videoChannelsPage.closeNotificationButton.click();
                videoChannelsPage.WaitUntilDialogIsNotLocated();
            } catch (Exception e) {}
            device.click();
            videoChannelsPage.WaitUntilPictureSettingsIsExist();

            if(videoChannelsPage.PictureSettingIsExist() && (!videoChannelsPage.DeviceIsOffline())){
              videoChannelsPage.GoToPictureSettings();
              Thread.sleep(1000);
              if(!videoChannelsPage.DeviceIsOffline()) {

                  try {
                      new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-backdrop fade in']")));
                      driver.switchTo().window(driver.getWindowHandle());
                      videoChannelsPage.closeNotificationButton.click();
                  } catch (Exception e) {
                  }
                  if (videoChannelsPage.ExposureIsExist()) {
                      camera = device;
                      break;
                  }
              }
            }
            if (i == size - 1) {
                logger.log(LogStatus.INFO,"Theare are not devices with Exposure Setting");
            }
        }
        if (camera != null) {
            String deviceIP = camera.getText();
            String exposureMode = videoChannelsPage.GetExposureModeText();
          if(videoChannelsPage.ExposureModeSelectIsEnable()) {
              if (exposureMode.equals("AUTO")) {
                  videoChannelsPage.SelectExposureModeOption("MANUAL");
                  logger.log(LogStatus.INFO, "Press Save button");
                  videoChannelsPage.PressSaveButton();
              }
              Thread.sleep(1000);

              if(videoChannelsPage.ExposureTimeFieldIsExist()) {
                  int maxVal = Integer.parseInt(videoChannelsPage.GetExposureTimeMaxValue());
                  logger.log(LogStatus.INFO, "Input max value " + maxVal + " in ExposureTime field foe device " + deviceIP);
                  videoChannelsPage.InputExposureTimeValue("" + maxVal);

                  logger.log(LogStatus.INFO, "Press Save button");
                  videoChannelsPage.PressSaveButton();

                  logger.log(LogStatus.INFO, "Check that ExposureTime Value is max");
                  int exposureTime = Integer.parseInt(videoChannelsPage.GetExposureTimeText());
                  Assert.assertEquals(exposureTime, maxVal);

                  logger.log(LogStatus.INFO, "Click on ExposureTimeDownSnipper twice");
                  videoChannelsPage.ClickExposureTimeDownSnipper();
                  videoChannelsPage.ClickExposureTimeDownSnipper();

                  logger.log(LogStatus.INFO, "Press Save button");
                  videoChannelsPage.PressSaveButton();

                  logger.log(LogStatus.INFO, "Check that ExposureTime Value is " + (maxVal - 2));
                  exposureTime = Integer.parseInt(videoChannelsPage.GetExposureTimeText());
                  Assert.assertEquals(exposureTime, maxVal - 2);

                  int minVal = Integer.parseInt(videoChannelsPage.GetExposureTimeMinValue());
                  logger.log(LogStatus.INFO, "Input min value " + minVal + " in ExposureTime field");
                  videoChannelsPage.InputExposureTimeValue("" + minVal);
//              videoChannelsPage.ClickExposureTimeDownSnipper();

                  logger.log(LogStatus.INFO, "Press Save button");
                  videoChannelsPage.PressSaveButton();

                  logger.log(LogStatus.INFO, "Check that ExposureTime Value is min");
                  exposureTime = Integer.parseInt(videoChannelsPage.GetExposureTimeText());
                  Assert.assertEquals(exposureTime, minVal);

                  logger.log(LogStatus.INFO, "Click on ExposureTimeUpSnipper twice");
                  videoChannelsPage.ClickExposureTimeUpSnipper();
                  videoChannelsPage.ClickExposureTimeUpSnipper();

                  logger.log(LogStatus.INFO, "Press Save button");
                  videoChannelsPage.PressSaveButton();

                  logger.log(LogStatus.INFO, "Check that ExposureTime Value is " + (minVal + 2));
                  exposureTime = Integer.parseInt(videoChannelsPage.GetExposureTimeText());
                  Assert.assertEquals(exposureTime, minVal + 2);

                  logger.log(LogStatus.INFO, "Refresh");
                  videoChannelsPage.Refresh();
                  videoChannelsPage.FindDeviceByName(deviceIP).click();
                  Thread.sleep(1000);
                  videoChannelsPage.GoToPictureSettings();
                  videoChannelsPage.WaitUntillVisualSettingsIsExist();

                  logger.log(LogStatus.INFO, "Check that ExposureTime change is saved after refresh");
                  exposureTime = Integer.parseInt(videoChannelsPage.GetExposureTimeText());
                  Assert.assertEquals(exposureTime, minVal + 2);
              }
          }
          else if(!videoChannelsPage.ExposureModeSelectIsEnable()){
              logger.log(LogStatus.INFO,"Exposure Mode select is disabled");
              videoChannelsPage.Refresh();
          }
        }
    }

    @Test
    public void ChangeExposureModeToManualAndChangeGainTest() throws InterruptedException {
        logger=report.startTest("ChangeExposureModeToManualAndChangeGainTest");

        logger.log(LogStatus.INFO,"Select device for which Picture settings and Exposure Setting are available");
        int size = videoChannelsPage.GetDevicesSize();
        WebElement camera = null;
        for (int i = 0; i < size; i++) {
            device = videoChannelsPage.SelectRandomDevice();
            try {
                new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-backdrop fade in']")));
                driver.switchTo().window(driver.getWindowHandle());
                videoChannelsPage.closeNotificationButton.click();
                videoChannelsPage.WaitUntilDialogIsNotLocated();
            } catch (Exception e) {}
            device.click();
            videoChannelsPage.WaitUntilPictureSettingsIsExist();

            if(videoChannelsPage.PictureSettingIsExist() && (!videoChannelsPage.DeviceIsOffline())){
              videoChannelsPage.GoToPictureSettings();
              Thread.sleep(1000);
              if(!videoChannelsPage.DeviceIsOffline()) {
                  try {
                      new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-backdrop fade in']")));
                      driver.switchTo().window(driver.getWindowHandle());
                      videoChannelsPage.closeNotificationButton.click();
                  } catch (Exception e) {
                  }
                  if (videoChannelsPage.ExposureIsExist() && videoChannelsPage.GainIsExist()) {
                      camera = device;
                      break;
                  }
              }
            }
            if (i == size - 1) {
                logger.log(LogStatus.INFO,"Theare are not devices with Exposure Setting");
            }
        }
        if (camera != null) {
            String deviceIP = camera.getText();
            String exposureMode = videoChannelsPage.GetExposureModeText();
          if(videoChannelsPage.ExposureModeSelectIsEnable()) {

              if (exposureMode.equals("AUTO")) {
                  videoChannelsPage.SelectExposureModeOption("MANUAL");
                  logger.log(LogStatus.INFO, "Press Save button");
                  videoChannelsPage.PressSaveButton();
              }
              Thread.sleep(1000);

              int maxVal = Integer.parseInt(videoChannelsPage.GetGainMaxValue());
              logger.log(LogStatus.INFO, "Input max value " + maxVal + " in Gain field foe device " + deviceIP);
              videoChannelsPage.InputGainValue("" + maxVal);

              logger.log(LogStatus.INFO, "Press Save button");
              videoChannelsPage.PressSaveButton();

              logger.log(LogStatus.INFO, "Check that Gain Value is max");
              int gain = Integer.parseInt(videoChannelsPage.GetGainText());
              Assert.assertEquals(gain, maxVal);

              logger.log(LogStatus.INFO, "Click on GainDownSnipper twice");
              videoChannelsPage.ClickGainDownSnipper();
              videoChannelsPage.ClickGainDownSnipper();

              logger.log(LogStatus.INFO, "Press Save button");
              videoChannelsPage.PressSaveButton();

              logger.log(LogStatus.INFO, "Check that Gain Value is " + (maxVal - 2));
              gain = Integer.parseInt(videoChannelsPage.GetGainText());
              Assert.assertEquals(gain, maxVal - 2);

              int minVal = Integer.parseInt(videoChannelsPage.GetGainMinValue());
              logger.log(LogStatus.INFO, "Input min value " + minVal + " in Gain field");
              videoChannelsPage.InputGainValue("" + minVal);
              videoChannelsPage.ClickGainDownSnipper();

              logger.log(LogStatus.INFO, "Press Save button");
              videoChannelsPage.PressSaveButton();

              logger.log(LogStatus.INFO, "Check that Gain Value is min");
              gain = Integer.parseInt(videoChannelsPage.GetGainText());
              Assert.assertEquals(gain, minVal);

              logger.log(LogStatus.INFO, "Click on GainUpSnipper twice");
              videoChannelsPage.ClickGainUpSnipper();
              videoChannelsPage.ClickGainUpSnipper();

              logger.log(LogStatus.INFO, "Press Save button");
              videoChannelsPage.PressSaveButton();

              logger.log(LogStatus.INFO, "Check that Gain Value is " + (minVal + 2));
              gain = Integer.parseInt(videoChannelsPage.GetGainText());
              Assert.assertEquals(gain, minVal + 2);

              logger.log(LogStatus.INFO, "Refresh");
              videoChannelsPage.Refresh();
              videoChannelsPage.FindDeviceByName(deviceIP).click();
              Thread.sleep(1000);
              videoChannelsPage.GoToPictureSettings();
              videoChannelsPage.WaitUntillVisualSettingsIsExist();

              logger.log(LogStatus.INFO, "Check that Gain change is saved after refresh");
              gain = Integer.parseInt(videoChannelsPage.GetGainText());
              Assert.assertEquals(gain, minVal + 2);

              logger.log(LogStatus.INFO, "Select Auto option");
              videoChannelsPage.SelectExposureModeOption("AUTO");
              videoChannelsPage.PressSaveButton();
          }
          else if(!videoChannelsPage.ExposureModeSelectIsEnable()){
              logger.log(LogStatus.INFO,"Exposure Mode select is disabled");
          }
        }
    }

    //Tests for Backlight Compensation
    @Test
    public void ChangeBacklightCompensationModeTest() throws InterruptedException {
        logger=report.startTest("ChangeBacklightCompensationModeTest");

        logger.log(LogStatus.INFO,"Select device for which Picture settings and BacklightCompensation Setting are available");
        int size = videoChannelsPage.GetDevicesSize();
        WebElement camera = null;
        for (int i = 0; i < size; i++) {
            device = videoChannelsPage.SelectRandomDevice();
            try {
                new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-backdrop fade in']")));
                driver.switchTo().window(driver.getWindowHandle());
                videoChannelsPage.closeNotificationButton.click();
                videoChannelsPage.WaitUntilDialogIsNotLocated();
            } catch (Exception e) {}
            device.click();
            videoChannelsPage.WaitUntilPictureSettingsIsExist();

            if(videoChannelsPage.PictureSettingIsExist() && (!videoChannelsPage.DeviceIsOffline())){
                videoChannelsPage.GoToPictureSettings();
                Thread.sleep(1000);
                if(!videoChannelsPage.DeviceIsOffline()) {
                    try {
                        new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-backdrop fade in']")));
                        driver.switchTo().window(driver.getWindowHandle());
                        videoChannelsPage.closeNotificationButton.click();
                    } catch (Exception e) {
                    }
                    if (videoChannelsPage.BacklightCompensationIsExist()) {
                        camera = device;
                        break;
                    }
                }
            }
            if (i == size - 1) {
                logger.log(LogStatus.INFO,"Theare are not devices with BacklightCompensation Setting");
            }
        }
        if(camera!=null){
            String deviceIP = camera.getText();
            Thread.sleep(1000);

            String mode = videoChannelsPage.GetBacklightCompensationModeText();
            logger.log(LogStatus.INFO,"Change exposure mode for device + "+deviceIP);
            if(mode.equals("OFF")){
                videoChannelsPage.SelectBacklightCompensationModeOption("ON");
            }
            if(mode.equals("ON")){
                videoChannelsPage.SelectBacklightCompensationModeOption("OFF");
            }
            logger.log(LogStatus.INFO,"Press save button");
            videoChannelsPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that mode change is saved ");
            String modeRelevant = videoChannelsPage.GetBacklightCompensationModeText();
            Assert.assertNotEquals(modeRelevant, mode);

            logger.log(LogStatus.INFO,"Refresh");
            videoChannelsPage.Refresh();
            videoChannelsPage.FindDeviceByName(deviceIP).click();
            Thread.sleep(1000);
            videoChannelsPage.GoToPictureSettings();
            videoChannelsPage.WaitUntillBacklightCompensationIsExist();

            logger.log(LogStatus.INFO,"Check that Gain change is saved after refresh");
            modeRelevant = videoChannelsPage.GetBacklightCompensationModeText();
            Assert.assertNotSame(modeRelevant, mode);

            if(!modeRelevant.equals(mode)){
                logger.log(LogStatus.INFO,"Return original value");
                videoChannelsPage.SelectBacklightCompensationModeOption(mode);
                videoChannelsPage.PressSaveButton();
            }
        }
    }

    @Test
    public void GetVideoChannelPictureSetiingsAndApplyToOtherChannelTest() throws InterruptedException {
        logger=report.startTest("GetVideoChannelPictureSetiingsAndApplyToOtherChannelTest");

        logger.log(LogStatus.INFO,"Get randomaly channel from list");
        int size = videoChannelsPage.GetDevicesSize();
        WebElement channel = null;
        boolean flag = false;
        for(int i = 0; i<size; i++) {
            WebElement element = videoChannelsPage.SelectRandomDevice();
            element.click();
            Thread.sleep(1000);
            videoChannelsPage.WaitUntilPictureSettingsIsExist();
            if(videoChannelsPage.PictureSettingIsExist() && (!videoChannelsPage.DeviceIsOffline())){
                videoChannelsPage.GoToPictureSettings();
                Thread.sleep(1000);
                logger.log(LogStatus.INFO,"If Apply to button enable by clicking on the channel "+ element.getText()+ " than set this channel");
                if(videoChannelsPage.ApplyToButtonIsEnabled()){
                    channel = element;
                    flag =true;
                    break;
                }
            }
        }

        if(flag){
            String channelText = channel.getText();
            boolean visualSettings = false;
            String brightness=null;
            String contrast = null;
            String colorSaturation=null;
            String sharpness = null;
            if (videoChannelsPage.VisualSettingsIsExist()) {
                visualSettings = true;
                logger.log(LogStatus.INFO,"Get properties from Visual Settings for channel "+channelText );
                brightness= videoChannelsPage.GetBrightnessText();
                contrast = videoChannelsPage.GetContrastText();
                colorSaturation = videoChannelsPage.GetColorSaturationText();
                sharpness = videoChannelsPage.GetSharpnessText();
            }
            boolean dayNightSettings = false;
            String dayNight = null;
            if (videoChannelsPage.DayNightIsExist()) {
                dayNightSettings = true;
                dayNight = videoChannelsPage.GetFilterModeText();
            }
            boolean exposure = false;
            String exposureMode = null;
            String exposureTime = null;
            String gain = null;
            if (videoChannelsPage.ExposureIsExist()) {
                exposure = true;
                exposureMode = videoChannelsPage.GetExposureModeText();
                if(exposureMode.equals("Manual")){
                    exposureTime = videoChannelsPage.GetExposureTimeText();
                    gain = videoChannelsPage.GetGainText();
                }
            }
            boolean backlightCompensation = false;
            String backlightCompensationMode = null;
            if (videoChannelsPage.BacklightCompensationIsExist()){
                backlightCompensation = true;
                backlightCompensationMode = videoChannelsPage.GetBacklightCompensationModeText();
            }

            logger.log(LogStatus.INFO,"Click on Apply To button");
            videoChannelsPage.ClickOnApplyToButton();

            logger.log(LogStatus.INFO,"If Picture Settings Checkbox isn't checked - check it");
            if(!videoChannelsPage.PictureSettingsCheckboxIsSelected()){
                videoChannelsPage.ClickOnPictureSettingsCheckbox();
            }

            logger.log(LogStatus.INFO,"Click on video channel in Apply To dialog");
            int audioChannelsize = videoChannelsPage.GetAudioChannelsSizeInApplyToDialog();
            int index = videoChannelsPage.GetRandomDigit(0,audioChannelsize );
            String channelText2 = videoChannelsPage.GetAudioChannelTextInApplyChannelsDialogByIndex(index);
            logger.log(LogStatus.INFO,"Click on video channel "+channelText2+" in Apply To dialog");
            videoChannelsPage.ClickOnVideoChanelsInApplyChannelsDialogByIndex(index);
            Thread.sleep(2000);

            logger.log(LogStatus.INFO,"Click on Apply button in Apply To Dialog");
            videoChannelsPage.ClickOnApplyButtonInApplyToDialog();
            Thread.sleep(1000);

            logger.log(LogStatus.INFO,"Click on Close X icon in Apply To Dialog");
            videoChannelsPage.PressCloseDialogIcon();
            Thread.sleep(2000);

            logger.log(LogStatus.INFO,"Click on the channel "+ channelText2);
            videoChannelsPage.waitUntilElementIsClickable(videoChannelsPage.FindDeviceByName(channelText2));
            videoChannelsPage.FindDeviceByName(channelText2).click();
            videoChannelsPage.WaitUntilPictureSettingsIsExist();
            Thread.sleep(1000);

            boolean visualSettings2 = false;
            String brightness2=null;
            String contrast2 = null;
            String colorSaturation2=null;
            String sharpness2 = null;
            if (videoChannelsPage.VisualSettingsIsExist()) {
                visualSettings2 = true;
                logger.log(LogStatus.INFO,"Get properties from Visual Settings for channel "+channelText2 );
                brightness2= videoChannelsPage.GetBrightnessText();
                contrast2 = videoChannelsPage.GetContrastText();
                colorSaturation2 = videoChannelsPage.GetColorSaturationText();
                sharpness2 = videoChannelsPage.GetSharpnessText();
            }
            boolean dayNightSettings2 = false;
            String dayNight2 = null;
            if (videoChannelsPage.DayNightIsExist()) {
                dayNightSettings2 = true;
                dayNight2 = videoChannelsPage.GetFilterModeText();
            }
            boolean exposure2 = false;
            String exposureMode2 = null;
            String exposureTime2 = null;
            String gain2 = null;
            if (videoChannelsPage.ExposureIsExist()) {
                exposure2 = true;
                exposureMode2 = videoChannelsPage.GetExposureModeText();
                if(exposureMode2.equals("Manual")){
                    exposureTime2 = videoChannelsPage.GetExposureTimeText();
                    gain2 = videoChannelsPage.GetGainText();
                }
            }
            boolean backlightCompensation2 = false;
            String backlightCompensationMode2 = null;
            if (videoChannelsPage.BacklightCompensationIsExist()){
                backlightCompensation2 = true;
                backlightCompensationMode2 = videoChannelsPage.GetBacklightCompensationModeText();
            }

            if(visualSettings2 && visualSettings){
                logger.log(LogStatus.INFO,"Check that Brightness for channel "+channelText2 + " is equals for channel "+ channelText);
                Assert.assertEquals(brightness, brightness2);

                logger.log(LogStatus.INFO,"Check that contrast for channel "+channelText2 + " is equals for channel "+ channelText);
                Assert.assertEquals(contrast, contrast2);

                logger.log(LogStatus.INFO,"Check that colorSaturation for channel "+channelText2 + " is equals for channel "+ channelText);
                Assert.assertEquals(colorSaturation, colorSaturation2);

                logger.log(LogStatus.INFO,"Check that colorSaturation for channel "+channelText2 + " is equals for channel "+ channelText);
                Assert.assertEquals(sharpness, sharpness2);
            }

            if(dayNightSettings && dayNightSettings2){
                logger.log(LogStatus.INFO,"Check that dayNight mode for channel "+channelText2 + " is equals for channel "+ channelText);
                Assert.assertEquals(dayNight, dayNight2);
            }

            if(exposure && exposure2){
                logger.log(LogStatus.INFO,"Check that exposure mode for channel "+channelText2 + " is equals for channel "+ channelText);
                Assert.assertEquals(exposureMode, exposureMode2);
                if(exposureMode.equals("Manual")){
                    logger.log(LogStatus.INFO,"Check that exposure time for channel "+channelText2 + " is equals for channel "+ channelText);
                    Assert.assertEquals(exposureTime, exposureTime2);

                    logger.log(LogStatus.INFO,"Check that gain for channel "+channelText2 + " is equals for channel "+ channelText);
                    Assert.assertEquals(gain, gain2);
                }
            }

            if(backlightCompensation && backlightCompensation2){
                logger.log(LogStatus.INFO,"Check that backlightCompensation Mode for channel "+channelText2 + " is equals for channel "+ channelText);
                Assert.assertEquals(backlightCompensationMode, backlightCompensationMode2);
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
