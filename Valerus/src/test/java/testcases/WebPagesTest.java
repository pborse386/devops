package testcases;

import pageObjects.WebPagesPage;
import pageObjects.MonitoringPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class WebPagesTest {
    public WebDriver driver;
//    public VideoChannelsPage videoChannelsPage;
//    public LoginPage loginPage;
    public MonitoringPage monitoringPage;
    public WebPagesPage webPagesPage;
    public String[] Servers;
    ExtentReports report;
    ExtentTest logger;

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void setup(@Optional("ie")String browser) throws IOException, InterruptedException {
      /*  String WebDriverLocation = System.getenv("WebDriver");

        if(browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", WebDriverLocation+"\\chromedriver.exe");
            driver = new ChromeDriver();
        }else if (browser.equalsIgnoreCase("ie")) {
            System.setProperty("webdriver.ie.driver", WebDriverLocation+"\\IEDriverServer.exe");
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            capabilities.setCapability(InternetExplorerDriver.IE_USE_PER_PROCESS_PROXY, true);
//            capabilities.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
            capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
            capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
//            capabilities.setCapability("nativeEvents", false);
//            driver = WebDriverFactory.getDriver(DesiredCapabilities.internetExplorer());
            driver = new InternetExplorerDriver(capabilities);
        }
        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
        monitoringPage = PageFactory.initElements(driver, MonitoringPage.class);
        webPagesPage = PageFactory.initElements(driver, WebPagesPage.class);
        Servers = webPagesPage.getServersList();

        driver.navigate().to("http://" + Servers[0]);
        webPagesPage.SignIn();

        webPagesPage.WaitUntilLoadingBlockAppears();
        webPagesPage.WaitUntilLoadingBlockDisappears();
        webPagesPage.GoToWebPagesPageFromLanding();*/
    }

    @BeforeTest
    public void startReport(){
        report=new ExtentReports(System.getProperty("user.dir") +"/test-output/WebPages/WebPagesReport.html", true);
    }

    @BeforeMethod(alwaysRun = true)
    public void GoToWebPagesPage() throws InterruptedException, IOException {
    	
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
         monitoringPage = PageFactory.initElements(driver, MonitoringPage.class);
         webPagesPage = PageFactory.initElements(driver, WebPagesPage.class);
         Servers = webPagesPage.getServersList();

         driver.navigate().to("http://" + Servers[0]);
         webPagesPage.SignIn();

         webPagesPage.WaitUntilLoadingBlockAppears();
         webPagesPage.WaitUntilLoadingBlockDisappears();
         webPagesPage.GoToWebPagesPageFromLanding();
    	
    	
      /*  driver.navigate().to("http://" + Servers[0]);
        try{
            driver.switchTo().alert().accept();
        }catch(Exception a){}

        webPagesPage.WaitUntilLoadingBlockAppears();
        webPagesPage.WaitUntilLoadingBlockDisappears();
        webPagesPage.GoToWebPagesPage();*/
    }

    @Test  
    public void AddNewWebPageTest() throws InterruptedException {
        logger=report.startTest("AddNewWebPageTest");

        logger.log(LogStatus.INFO,"Click on new button");
        webPagesPage.ClickOnNewButton();

        logger.log(LogStatus.INFO,"Check that Save button is disabled");
        Assert.assertFalse(webPagesPage.SaveButtonIsEnabled());

        logger.log(LogStatus.INFO,"Check that Cancel button is enabled"); 
        Assert.assertTrue(webPagesPage.CancelButtonIsEnabled());
    }

    @Test (enabled =false)
    public void AddNewWebPageInputParametersTest() throws InterruptedException {
        logger=report.startTest("AddNewWebPageInputParametersTest");

        int webPageSize = webPagesPage.GetWebPagesSize();

        logger.log(LogStatus.INFO,"Click on new button");
        webPagesPage.ClickOnNewButton();

        logger.log(LogStatus.INFO,"Input into URL field");
        String url = "http://www.abpmaza.com";
        webPagesPage.InputIntoURLField(url);

        logger.log(LogStatus.INFO,"Check that Save button is enabled");
        Assert.assertTrue(webPagesPage.SaveButtonIsEnabled());

        logger.log(LogStatus.INFO,"Input into name field");
        String name = "NewWebPage"+webPagesPage.GetRandomDigit(0, 90000);
        webPagesPage.InputIntoNameField(name);

        logger.log(LogStatus.INFO,"Input into remarks field");
        String remarks = "Remarks for web page "+name;
        webPagesPage.InputIntoRemarksField(remarks);

        logger.log(LogStatus.INFO,"Click on refresh time toggle switch");
        webPagesPage.ClickOnRefreshTimeSwitch();

        logger.log(LogStatus.INFO,"Input into refresh time field");
        String time = ""+webPagesPage.GetRandomDigit(1, 60);  
        webPagesPage.InputIntoRefreshTimeField(time);

        logger.log(LogStatus.INFO,"Press Save button");
        Thread.sleep(1000);
        webPagesPage.PressSaveButton();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that web page is added");
        int webPageSizeAct = webPagesPage.GetWebPagesSize();
        Assert.assertEquals(webPageSizeAct, webPageSize+1);

        logger.log(LogStatus.INFO,"Check that url is saved");
        String urlAct = webPagesPage.GetWebPageURl();
        Assert.assertEquals(urlAct, url);

        logger.log(LogStatus.INFO,"Check that name is saved");
        String nameAct = webPagesPage.GetWebPageName();
        Assert.assertEquals(nameAct, name);

        logger.log(LogStatus.INFO,"Check that remarks is saved");
        String remarksAct = webPagesPage.GetWebPageRemarks();
        Assert.assertEquals(remarksAct, remarks);

        logger.log(LogStatus.INFO,"Check that refresh time status is ON");
        boolean refreshTimeStatus = webPagesPage.RefreshTimeToggleIsOn();
        Assert.assertTrue(refreshTimeStatus);

        logger.log(LogStatus.INFO,"Check that refresh time value is saved");
        String refreshTime = webPagesPage.GetRefreshTimeValue();
        Assert.assertEquals(refreshTime, time);

        logger.log(LogStatus.INFO,"Refresh page ");
        webPagesPage.Refresh();

        logger.log(LogStatus.INFO,"Click on "+name+" web page");
        webPagesPage.ClickOnWebPageByName(name);
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that web page is added after refresh");
        int webPageSizeRefr = webPagesPage.GetWebPagesSize();
        Assert.assertEquals(webPageSizeRefr, webPageSize+1);

        logger.log(LogStatus.INFO,"Check that url is saved after refresh");
        String urlRefr = webPagesPage.GetWebPageURl();
        Assert.assertEquals(urlRefr, url);

        logger.log(LogStatus.INFO,"Check that name is saved after refresh");
        String nameRefr = webPagesPage.GetWebPageName();
        Assert.assertEquals(nameRefr, name);

        logger.log(LogStatus.INFO,"Check that remarks is saved after refresh");
        String remarksRefr = webPagesPage.GetWebPageRemarks();
        Assert.assertEquals(remarksRefr, remarks);

        logger.log(LogStatus.INFO,"Check that refresh time status is ON after refresh");
        boolean refreshTimeStatusRefr = webPagesPage.RefreshTimeToggleIsOn();
        Assert.assertTrue(refreshTimeStatusRefr);

        logger.log(LogStatus.INFO,"Check that refresh time value is saved after refresh");
        String refreshTimeRefr = webPagesPage.GetRefreshTimeValue();
        Assert.assertEquals(refreshTimeRefr, time);

        logger.log(LogStatus.INFO,"Go to Monitoring page");
        webPagesPage.GoToMonitoringPage();

        logger.log(LogStatus.INFO,"Input into filter field");
        monitoringPage.FilterField(name);

        logger.log(LogStatus.INFO,"Check that new web page is present in Monitoring Resources list");
        boolean pageIsExist = monitoringPage.VerifyThatResourceIsExist(name);
        Assert.assertTrue(pageIsExist);
    }

    @Test
    public void ChangeNameTest() throws InterruptedException {
        logger=report.startTest("ChangeNameTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        if(webPageSize>0){
            int index = webPagesPage.GetRandomDigit(0, webPageSize);
            String name = webPagesPage.GetWebPageNameByIndex(index);

            logger.log(LogStatus.INFO,"Click on web page "+name);
            webPagesPage.ClickOnWebPageByIndex(index);

            boolean visibility = webPagesPage.VisibilityToggleIsOn();
            if(!visibility){
                logger.log(LogStatus.INFO,"Click on visibility toggle switch");
                webPagesPage.ClickOnVisibilitySwitch();
            }

            String newName = "NewName"+webPagesPage.GetRandomDigit(0, 9000);
            logger.log(LogStatus.INFO,"Input" +name+" into name field");
            webPagesPage.InputIntoNameField(newName);

            logger.log(LogStatus.INFO,"Press Save button");
            Thread.sleep(1000);
            webPagesPage.PressSaveButton();
            Thread.sleep(1000);

            logger.log(LogStatus.INFO,"Check that name is saved");
            String nameAct = webPagesPage.GetWebPageName();
            Assert.assertEquals(nameAct, newName);

            logger.log(LogStatus.INFO,"Refresh page ");
            webPagesPage.Refresh();

            logger.log(LogStatus.INFO,"Click on "+name+" web page");
            webPagesPage.ClickOnWebPageByName(newName);
            Thread.sleep(1000);

            logger.log(LogStatus.INFO,"Check that name is saved after refresh");
            String nameRefr = webPagesPage.GetWebPageName();
            Assert.assertEquals(nameRefr, newName);

            logger.log(LogStatus.INFO,"Go to Monitoring page");
            webPagesPage.GoToMonitoringPage();

            logger.log(LogStatus.INFO,"Input into filter field");
            monitoringPage.FilterField(newName);

            logger.log(LogStatus.INFO,"Check that web page " +newName+ " is present in Monitoring Resources list");
            boolean pageIsExist = monitoringPage.VerifyThatResourceIsExist(newName);
            Assert.assertTrue(pageIsExist);
        }
    }

    @Test
    public void ChangeRemarksTest() throws InterruptedException {
        logger=report.startTest("ChangeRemarksTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        if(webPageSize>0){
            int index = webPagesPage.GetRandomDigit(0, webPageSize);
            String name = webPagesPage.GetWebPageNameByIndex(index);

            logger.log(LogStatus.INFO,"Click on web page "+name);
            webPagesPage.ClickOnWebPageByIndex(index);

            String newName = "NewName"+webPagesPage.GetRandomDigit(0, 6000);
            logger.log(LogStatus.INFO,"Input" +name+" into name field");
            webPagesPage.InputIntoNameField(newName);

            String remarks = webPagesPage.GetWebPageRemarks();
            String newRemarks = "NewRemarks for web page "+name;
            webPagesPage.InputIntoRemarksField(newRemarks);

            logger.log(LogStatus.INFO,"Press Save button");
            webPagesPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that remarks is saved");
            String remarksAct = webPagesPage.GetWebPageRemarks();
            Assert.assertEquals(remarksAct, newRemarks);

            logger.log(LogStatus.INFO,"Refresh page ");
            webPagesPage.Refresh();

            logger.log(LogStatus.INFO,"Click on "+newName+" web page");
            webPagesPage.ClickOnWebPageByName(newName);
            Thread.sleep(1000);

            logger.log(LogStatus.INFO,"Check that name is saved after refresh");
            String remarksRefr = webPagesPage.GetWebPageRemarks();
            Assert.assertEquals(remarksRefr, newRemarks);
        }
    }

    @Test
    public void ChangeVisibilityStatusTest() throws InterruptedException {
        logger=report.startTest("ChangeVisibilityStatusTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        if(webPageSize>0){
            int index = webPagesPage.GetRandomDigit(0, webPageSize);
            String name = webPagesPage.GetWebPageNameByIndex(index);

            logger.log(LogStatus.INFO,"Click on web page "+name);
            webPagesPage.ClickOnWebPageByIndex(index);

            String newName = "NewName"+webPagesPage.GetRandomDigit(1000, 6000);
            logger.log(LogStatus.INFO,"Input" +name+" into name field");
            webPagesPage.InputIntoNameField(newName);

            boolean visibility = webPagesPage.VisibilityToggleIsOn();
            logger.log(LogStatus.INFO,"Click on visibility status");
            webPagesPage.ClickOnVisibilitySwitch();

            logger.log(LogStatus.INFO,"Press Save button");
            webPagesPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that visibility status change is saved");
            boolean visibilityAct = webPagesPage.VisibilityToggleIsOn();
            Assert.assertEquals(visibilityAct, !visibility);
           // Assert.assertEquals(visibilityAct, visibility);

            logger.log(LogStatus.INFO,"Refresh page ");
            webPagesPage.Refresh();

            logger.log(LogStatus.INFO,"Click on "+newName+" web page");
            webPagesPage.ClickOnWebPageByName(newName);
            Thread.sleep(1000);

            logger.log(LogStatus.INFO,"Check that visibility status change is saved after refresh");
            boolean visibilityRefr = webPagesPage.VisibilityToggleIsOn();
            Assert.assertEquals(visibilityRefr, !visibility);

            logger.log(LogStatus.INFO,"Go to Monitoring page");
            webPagesPage.GoToMonitoringPage();

            logger.log(LogStatus.INFO,"Input into filter field");
            monitoringPage.FilterField(newName);

            if(visibilityAct){
                logger.log(LogStatus.INFO,"Check that web page " +newName+ " is present in Monitoring Resources list");
                boolean pageIsExist = monitoringPage.VerifyThatResourceIsExist(newName);
                Assert.assertTrue(pageIsExist);
            }

            if(!visibilityAct){
                logger.log(LogStatus.INFO,"Check that web page " +newName+ " isn't present in Monitoring Resources list");
                boolean pageIsExist = monitoringPage.VerifyThatResourceIsExist(newName);
                Assert.assertFalse(pageIsExist);
            }
        }
    }

    @Test
    public void ChangeRefreshTimeStatusTest() throws InterruptedException {
        logger=report.startTest("ChangeRefreshTimeStatusTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        if(webPageSize>0){
            int index = webPagesPage.GetRandomDigit(0, webPageSize);
            String name = webPagesPage.GetWebPageNameByIndex(index);

            logger.log(LogStatus.INFO,"Click on web page "+name);
            webPagesPage.ClickOnWebPageByIndex(index);

            String newName = "NewName"+webPagesPage.GetRandomDigit(1000, 10000);
            logger.log(LogStatus.INFO,"Input" +name+" into name field");
            webPagesPage.InputIntoNameField(newName);

            boolean refreshTime = webPagesPage.RefreshTimeToggleIsOn();
            logger.log(LogStatus.INFO,"Click on refresh time status");
            webPagesPage.ClickOnRefreshTimeSwitch();

            logger.log(LogStatus.INFO,"Press Save button");
            webPagesPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that refresh time status change is saved");
            boolean refreshTimeAct = webPagesPage.RefreshTimeToggleIsOn();
            Assert.assertEquals(refreshTimeAct, !refreshTime);
           // Assert.assertEquals(refreshTimeAct, refreshTime);

            logger.log(LogStatus.INFO,"Refresh page ");
            webPagesPage.Refresh();

            logger.log(LogStatus.INFO,"Click on "+name+" web page");
            webPagesPage.ClickOnWebPageByName(newName);
            Thread.sleep(1000);

            logger.log(LogStatus.INFO,"Check that refresh time status change is saved after refresh");
            boolean refreshTimeRefr = webPagesPage.RefreshTimeToggleIsOn();
            Assert.assertEquals(refreshTimeRefr, !refreshTime);
        }
    }

    @Test
    public void ChangeRefreshTimeValueTest() throws InterruptedException {
        logger=report.startTest("ChangeRefreshTimeValueTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        if(webPageSize>0){
            int index = webPagesPage.GetRandomDigit(0, webPageSize);
            String name = webPagesPage.GetWebPageNameByIndex(index);

            logger.log(LogStatus.INFO,"Click on web page "+name);
            webPagesPage.ClickOnWebPageByIndex(index);

            String newName = "NewNameFor"+webPagesPage.GetRandomDigit(0,10000);
            logger.log(LogStatus.INFO,"Input" +name+" into name field");
            webPagesPage.InputIntoNameField(newName);

            boolean refreshTime = webPagesPage.RefreshTimeToggleIsOn();
            if(!refreshTime){
                logger.log(LogStatus.INFO,"Click on refresh time status");
                webPagesPage.ClickOnRefreshTimeSwitch();
            }
            String refreshTimeVal = webPagesPage.GetRefreshTimeValue();

            logger.log(LogStatus.INFO,"Input into refresh time field");
            String time = ""+webPagesPage.GetRandomDigit(1, 120);  //9483 Fresh time more than 59 isn't saved
            webPagesPage.InputIntoRefreshTimeField(time);
            webPagesPage.WaitUntilSaveButtonWillBeEnable();
            Thread.sleep(1000);

            logger.log(LogStatus.INFO,"Press Save button");
            webPagesPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that refresh time value change is saved");
            String refreshTimeAct = webPagesPage.GetRefreshTimeValue();
            Assert.assertEquals(refreshTimeAct, time);

            logger.log(LogStatus.INFO,"Refresh page ");
            webPagesPage.Refresh();

            logger.log(LogStatus.INFO,"Click on "+newName+" web page");
            webPagesPage.ClickOnWebPageByName(newName);
            Thread.sleep(1000);

            logger.log(LogStatus.INFO,"Check that refresh time value change is saved afetr refresh");
            String refreshTimeRefr = webPagesPage.GetRefreshTimeValue();
            Assert.assertEquals(refreshTimeRefr, time);
        }
    }

    @Test
    public void ChangeRefreshTimeValueBySpinnerTest() throws InterruptedException {
        logger=report.startTest("ChangeRefreshTimeValueBySpinnerTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        if(webPageSize>0){
            int index = webPagesPage.GetRandomDigit(0, webPageSize);
            String name = webPagesPage.GetWebPageNameByIndex(index);

            logger.log(LogStatus.INFO,"Click on web page "+name);
            webPagesPage.ClickOnWebPageByIndex(index);

            boolean refreshTime = webPagesPage.RefreshTimeToggleIsOn();
            if(!refreshTime){
                logger.log(LogStatus.INFO,"Click on refresh time status");
                webPagesPage.ClickOnRefreshTimeSwitch();
            }
            String refreshTimeVal = webPagesPage.GetRefreshTimeValue();

            int max = 60-Integer.parseInt(refreshTimeVal); //9483 must be 120, not 60
            int time = webPagesPage.GetRandomDigit(0, max);

            logger.log(LogStatus.INFO,"Click on spinner UP "+time+" times");
            for (int i = 0 ;i<time; i++){
                webPagesPage.ClickOnFreshTimeUpSpinner();
            }
            webPagesPage.WaitUntilSaveButtonWillBeEnable();

            logger.log(LogStatus.INFO,"Press Save button");
            webPagesPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that refresh time value change is saved");
            String refreshTimeAct1 = webPagesPage.GetRefreshTimeValue();
            Assert.assertEquals(Integer.parseInt(refreshTimeAct1), Integer.parseInt(refreshTimeVal)+time);

            int randomDown = webPagesPage.GetRandomDigit(0, Integer.parseInt(refreshTimeAct1));
            logger.log(LogStatus.INFO,"Click on spinner Down "+randomDown+" times");
            for (int i = 0 ;i<randomDown; i++){
                webPagesPage.ClickOnFreshTimeDownSpinner();
            }
            webPagesPage.WaitUntilSaveButtonWillBeEnable();

            logger.log(LogStatus.INFO,"Press Save button");
            webPagesPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that refresh time value change is saved");
            String refreshTimeAct2 = webPagesPage.GetRefreshTimeValue();
            Assert.assertEquals(Integer.parseInt(refreshTimeAct2), Integer.parseInt(refreshTimeAct1)-randomDown);
        }
    }

    @Test
    public void ChangeURLTest() throws InterruptedException {
        logger=report.startTest("ChangeURLTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        if(webPageSize>0){
            int index = webPagesPage.GetRandomDigit(0, webPageSize);
            String name = webPagesPage.GetWebPageNameByIndex(index);

            logger.log(LogStatus.INFO,"Click on web page "+name);
            webPagesPage.ClickOnWebPageByIndex(index);

            String url = webPagesPage.GetWebPageURl();
            String newURL = "http://NewURL"+webPagesPage.GetRandomDigit(0,1000)+".com";

            String newName = "WebPageWithUrl"+newURL;
            logger.log(LogStatus.INFO,"Input" +name+" into name field");
            webPagesPage.InputIntoNameField(newName);

            logger.log(LogStatus.INFO,"Input "+newURL+" into URL field");
            webPagesPage.InputIntoURLField(newURL);
            webPagesPage.WaitUntilSaveButtonWillBeEnable();

            logger.log(LogStatus.INFO,"Press Save button");
            webPagesPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that URL change is saved");
            String urlAct = webPagesPage.GetWebPageURl();
            Assert.assertEquals(urlAct, newURL);

            logger.log(LogStatus.INFO,"Refresh page ");
            webPagesPage.Refresh();

            logger.log(LogStatus.INFO,"Click on "+newName+" web page");
            webPagesPage.ClickOnWebPageByName(newName);
            Thread.sleep(1000);

            logger.log(LogStatus.INFO,"Check that URL change is saved after refresh");
            String urlRefr = webPagesPage.GetWebPageURl();
            Assert.assertEquals(urlRefr, newURL);
        }
    }

    @Test
    public void DeleteURLTest() throws InterruptedException {
        logger=report.startTest("DeleteURLTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        if(webPageSize>0){
            int index = webPagesPage.GetRandomDigit(0, webPageSize);
            String name = webPagesPage.GetWebPageNameByIndex(index);

            logger.log(LogStatus.INFO,"Click on web page "+name);
            webPagesPage.ClickOnWebPageByIndex(index);

            String url = webPagesPage.GetWebPageURl();
            String newURL = "http://NewURL"+webPagesPage.GetRandomDigit(0,1000)+".com";

            logger.log(LogStatus.INFO,"Delete URL");
            webPagesPage.ClickOnDeleteUrlIcon();

            logger.log(LogStatus.INFO,"Check that Save button is disabled");
            Assert.assertFalse(webPagesPage.SaveButtonIsEnabled());

            logger.log(LogStatus.INFO,"Check that Cancel button is enabled"); ///failed
            Assert.assertTrue(webPagesPage.CancelButtonIsEnabled());

            logger.log(LogStatus.INFO,"Input "+newURL+" into URL field");
            webPagesPage.InputIntoURLField(newURL);
            webPagesPage.WaitUntilSaveButtonWillBeEnable();

            logger.log(LogStatus.INFO,"Check that Save button is enabled");
            Assert.assertTrue(webPagesPage.SaveButtonIsEnabled());

            logger.log(LogStatus.INFO,"Check that Cancel button is enabled"); ///failed
            Assert.assertTrue(webPagesPage.CancelButtonIsEnabled());

            logger.log(LogStatus.INFO,"Press Save button");
            webPagesPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that URL change is saved");
            String urlAct = webPagesPage.GetWebPageURl();
            Assert.assertEquals(urlAct, newURL);
        }
    }

    @Test
    public void WebPagesFilterTest() throws InterruptedException {
        logger=report.startTest("WebPagesFilterTest");

        int webPagesSize = webPagesPage.GetWebPagesSize();
        if(webPagesSize>0){
            int index = webPagesPage.GetRandomDigit(0, webPagesSize);
            String webPageName = webPagesPage.GetWebPageNameByIndex(index);

            //    logger.log(LogStatus.INFO,"Click on filter button");
            //    webPagesPage.ClickOnFilterButton();

            logger.log(LogStatus.INFO,"Input "+webPageName+" into filter field");
            webPagesPage.InputIntoFilterFiled(webPageName);
            Thread.sleep(1000);

            webPagesSize = webPagesPage.GetWebPagesSize();
            for(int i=0; i<webPagesSize;i++){
                String name = webPagesPage.GetWebPageNameByIndex(i);
                logger.log(LogStatus.INFO,"Check that "+name+" contains "+ webPageName);
                Assert.assertTrue(name.contains(webPageName));
            }
        }
    }

    @Test
    public void DeleteWebPageByButtonTest() throws InterruptedException {
        logger=report.startTest("DeleteWebPageByButtonTest");

        int webPagesSize = webPagesPage.GetWebPagesSize();

        if(webPagesSize>0){
            int index = webPagesPage.GetRandomDigit(0, webPagesSize);
            String name = webPagesPage.GetWebPageNameByIndex(index);

            logger.log(LogStatus.INFO,"Click on web page "+name);
            webPagesPage.ClickOnWebPageByIndex(index);

            logger.log(LogStatus.INFO,"Press on Delete button ");
            webPagesPage.ClickOnDeleteButton();

            logger.log(LogStatus.INFO,"Confirm removing ");
            webPagesPage.ConfirmRemoving();
            webPagesPage.WaitUntilDialogIsNotLocated();

            int webPagesSizeAct = webPagesPage.GetWebPagesSize();
            logger.log(LogStatus.INFO,"Check that web page is removed");
            Assert.assertEquals(webPagesSizeAct, webPagesSize-1);

            logger.log(LogStatus.INFO,"Refresh page ");
            webPagesPage.Refresh();

            int webPagesSizeRefr = webPagesPage.GetWebPagesSize();
            logger.log(LogStatus.INFO,"Check that web page is removed after refesh");
            Assert.assertEquals(webPagesSizeRefr, webPagesSize-1);

            logger.log(LogStatus.INFO,"Go to Monitoring page");
            webPagesPage.GoToMonitoringPage();

            logger.log(LogStatus.INFO,"Input into filter field");
            monitoringPage.FilterField(name);

            logger.log(LogStatus.INFO,"Check that web page " +name+ " isn't present in Monitoring Resources list");
            boolean pageIsExist = monitoringPage.VerifyThatWebPageIsExist(name);
            Assert.assertFalse(pageIsExist);
        }
    }

    @Test
    public void CancelWebPageRemovingByButtonTest() throws InterruptedException {
        logger=report.startTest("CancelRemovingWebPageByButtonTest");

        int webPagesSize = webPagesPage.GetWebPagesSize();

        if(webPagesSize>0){
            int index = webPagesPage.GetRandomDigit(0, webPagesSize);
            String name = webPagesPage.GetWebPageNameByIndex(index);

            logger.log(LogStatus.INFO,"Click on web page "+name);
            webPagesPage.ClickOnWebPageByIndex(index);

            logger.log(LogStatus.INFO,"Press on Delete button ");
            webPagesPage.ClickOnDeleteButton();

            logger.log(LogStatus.INFO,"Cancel removing ");
            webPagesPage.CancelRemoving();
            webPagesPage.WaitUntilDialogIsNotLocated();

            int webPagesSizeAct = webPagesPage.GetWebPagesSize();
            logger.log(LogStatus.INFO,"Check that web page isn't removed");
            Assert.assertEquals(webPagesSizeAct, webPagesSize);

            logger.log(LogStatus.INFO,"Refresh page ");
            webPagesPage.Refresh();

            int webPagesSizeRefr = webPagesPage.GetWebPagesSize();
            logger.log(LogStatus.INFO,"Check that web page isn't removed after refesh");
            Assert.assertEquals(webPagesSizeRefr, webPagesSize);
        }
    }

    @Test
    public void DeleteWebPageByIconTest() throws InterruptedException {
        logger=report.startTest("DeleteWebPageByIconTest");

        int webPagesSize = webPagesPage.GetWebPagesSize();

        if(webPagesSize>0) {
            int index = webPagesPage.GetRandomDigit(0, webPagesSize);
            String name = webPagesPage.GetWebPageNameByIndex(index);

            logger.log(LogStatus.INFO, "Click on web page " + name);
            webPagesPage.ClickOnWebPageByIndex(index);

            logger.log(LogStatus.INFO, "Press on Delete icon");
            webPagesPage.ClickOnDeleteIcon();

            logger.log(LogStatus.INFO, "Confirm removing ");
            webPagesPage.ConfirmRemoving();
            webPagesPage.WaitUntilDialogIsNotLocated();

            int webPagesSizeAct = webPagesPage.GetWebPagesSize();
            logger.log(LogStatus.INFO, "Check that web page is removed");
            Assert.assertEquals(webPagesSizeAct, webPagesSize - 1);

            logger.log(LogStatus.INFO, "Refresh page ");
            webPagesPage.Refresh();

            int webPagesSizeRefr = webPagesPage.GetWebPagesSize();
            logger.log(LogStatus.INFO, "Check that web page is removed after refesh");
            Assert.assertEquals(webPagesSizeRefr, webPagesSize - 1);

            logger.log(LogStatus.INFO,"Go to Monitoring page");
            webPagesPage.GoToMonitoringPage();

            logger.log(LogStatus.INFO,"Input into filter field");
            monitoringPage.FilterField(name);

            logger.log(LogStatus.INFO,"Check that web page " +name+ " isn't present in Monitoring Resources list");
            boolean pageIsExist = monitoringPage.VerifyThatWebPageIsExist(name);
            Assert.assertFalse(pageIsExist);
        }
    }

    @Test
    public void CancelWebPageRemovingByIconTest() throws InterruptedException {
        logger=report.startTest("CancelWebPageRemovingByIconTest");

        int webPagesSize = webPagesPage.GetWebPagesSize();

        if(webPagesSize>0) {
            int index = webPagesPage.GetRandomDigit(0, webPagesSize);
            String name = webPagesPage.GetWebPageNameByIndex(index);

            logger.log(LogStatus.INFO, "Click on web page " + name);
            webPagesPage.ClickOnWebPageByIndex(index);

            logger.log(LogStatus.INFO, "Press on Delete icon");
            webPagesPage.ClickOnDeleteIcon();

            logger.log(LogStatus.INFO, "Cancel removing ");
            webPagesPage.CancelRemoving();
            webPagesPage.WaitUntilDialogIsNotLocated();

            int webPagesSizeAct = webPagesPage.GetWebPagesSize();
            logger.log(LogStatus.INFO, "Check that web page isn't removed");
            Assert.assertEquals(webPagesSizeAct, webPagesSize);

            logger.log(LogStatus.INFO, "Refresh page ");
            webPagesPage.Refresh();

            int webPagesSizeRefr = webPagesPage.GetWebPagesSize();
            logger.log(LogStatus.INFO, "Check that web page isn't removed after refesh");
            Assert.assertEquals(webPagesSizeRefr, webPagesSize);
        }
    }

    @AfterMethod
    public void afterMethod(ITestResult result){
        if(result.getStatus()==ITestResult.SUCCESS){
            logger.log(LogStatus.PASS, "Test is passed");
        }
        if(result.getStatus()==ITestResult.FAILURE){
            webPagesPage.takeScreenshot(driver, "WebPages", result.getName());
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
