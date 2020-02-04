package testcases;

import pageObjects.CamerasAndEncodersPage;
import pageObjects.NVRsPage;
import pageObjects.ToursPage;
import pageObjects.ViewsPage;
import pageObjects.WebPagesPage;
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


public class ToursTest {

    public WebDriver driver;
    MonitoringPage monitoringPage;
    ViewsPage viewsPage;
    ToursPage toursPage;
    CamerasAndEncodersPage camerasAndEncodersPage;
    WebPagesPage webPagesPage;
    public String[] Servers;
    ExtentReports report;
    ExtentTest logger;

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void setup(@Optional("ie")String browser) throws IOException, InterruptedException {
       /* String WebDriverLocation = System.getenv("WebDriver");

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
        toursPage = PageFactory.initElements(driver, ToursPage.class);
        monitoringPage = PageFactory.initElements(driver, MonitoringPage.class);
        viewsPage = PageFactory.initElements(driver, ViewsPage.class);
        webPagesPage = PageFactory.initElements(driver, WebPagesPage.class);
        Servers = toursPage.getServersList();

        driver.navigate().to("http://" + Servers[0]);
        toursPage.SignIn();

        toursPage.WaitUntilLoadingBlockAppears();
        toursPage.WaitUntilLoadingBlockDisappears();
        toursPage.GoToToursPageFromLanding();*/
    }

    @BeforeTest
    public void startReport(){
        report=new ExtentReports(System.getProperty("user.dir") +"/test-output/Tours/ToursReport.html", true);
    }

    @BeforeMethod(alwaysRun = true)
    public void GoToToursPage() throws InterruptedException, IOException {
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
        toursPage = PageFactory.initElements(driver, ToursPage.class);
        monitoringPage = PageFactory.initElements(driver, MonitoringPage.class);
        camerasAndEncodersPage = PageFactory.initElements(driver, CamerasAndEncodersPage.class);
        viewsPage = PageFactory.initElements(driver, ViewsPage.class);
        webPagesPage = PageFactory.initElements(driver, WebPagesPage.class);
        Servers = toursPage.getServersList();
        
        driver.navigate().to("http://" + Servers[0]);
        toursPage.SignIn();

        toursPage.WaitUntilLoadingBlockAppears();
        toursPage.WaitUntilLoadingBlockDisappears();
        toursPage.GoToToursPageFromLanding();
        
        
        

        driver.get("http://" + Servers[0]);
        try{
            driver.switchTo().alert().accept();
        }catch(Exception a){}

        toursPage.WaitUntilLoadingBlockAppears();
        toursPage.WaitUntilLoadingBlockDisappears();

        toursPage.GoToToursPage();
//        if(toursPage.CheckThatModalIsOpen()){
//        toursPage.ClickOnOkButton();
//        }
    }

    @Test
    public void AddNewTourWithAllParametersTest() throws Exception {
        logger=report.startTest("AddNewTourWithAllParametersTest");

        int toursCount = toursPage.GetToursSize();
        logger.log(LogStatus.INFO,"Click on New button");
        toursPage.ClickOnNewButton();
        Thread.sleep(1000);

        String newName = "TourName"+toursPage.GetRandomDigit(0, 10000);
        String remarks = "Remarks for "+newName+" tour";

        logger.log(LogStatus.INFO,"Input "+newName+" in Name field");
        toursPage.InputIntoNameField(newName);

        logger.log(LogStatus.INFO,"Input "+remarks+" in Remarks field");
        toursPage.InputIntoRemarksField(remarks);

        int viewSize = toursPage.GetViewsSize();
        if(viewSize>0){
            int count = toursPage.GetRandomDigit(1, viewSize);
            for(int i=0; i<count; i++){
                int index = toursPage.GetRandomDigit(0, viewSize);
                String viewName = toursPage.GetViewNameByIndex(index);
                logger.log(LogStatus.INFO,"Drag and drop view "+ viewName+" tour");
                WebElement view = toursPage.GetViewByIndex(index);
                toursPage.DragAndDropView(view);
                if(toursPage.CheckThatModalIsOpen()){
                    toursPage.ClickOnOkButton();
                }
            }

            int addedViews = toursPage.GetViewsToDisplaySize();
            if(addedViews>0){
                int viewsToDisplaySize = toursPage.GetViewsToDisplaySize();
                toursPage.WaitUntilSaveButtonWillBeEnable();

                logger.log(LogStatus.INFO,"Check that Save button is enabled");
                Assert.assertTrue(toursPage.SaveButtonIsEnabled());

                logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
                Assert.assertTrue(toursPage.CancelButtonIsEnabled());

                logger.log(LogStatus.INFO,"Press Save");
                toursPage.PressSaveButton();

                int viewsToDisplaySizeSaved = toursPage.GetViewsToDisplaySize();
                logger.log(LogStatus.INFO,"Check that views are added to tour");
                Assert.assertEquals(viewsToDisplaySizeSaved, viewsToDisplaySize);

                int toursCountAct = toursPage.GetToursSize();
                logger.log(LogStatus.INFO,"Check that tour " +newName+" is added");
                Assert.assertEquals(toursCountAct, toursCount+1);

                String nameAct = toursPage.GetTourName();
                logger.log(LogStatus.INFO,"Check that name is saved");
                Assert.assertEquals(nameAct, newName);

                String remarksAct = toursPage.GetRemarksText();
                logger.log(LogStatus.INFO,"Check that remarks is saved");
                Assert.assertEquals(remarksAct, remarks);

                boolean visibleAct = toursPage.VisibilityToggleIsOn();
                logger.log(LogStatus.INFO,"Check that visibility status is ON");
                Assert.assertTrue(visibleAct);

                logger.log(LogStatus.INFO,"Refresh page ");
                toursPage.Refresh();

                int toursCountActRefr = toursPage.GetToursSize();
                logger.log(LogStatus.INFO,"Check that new tour is added");
                Assert.assertEquals(toursCountActRefr, toursCountAct);

                logger.log(LogStatus.INFO,"Click on "+newName+" tour");
                toursPage.ClickOnTourByName(newName);
                Thread.sleep(1000);

                String nameRefr = toursPage.GetTourName();
                String remarksRefr = toursPage.GetRemarksText();
                int viewsToDisplaySizeRefr = toursPage.GetViewsToDisplaySize();
                boolean visibleRefr = toursPage.VisibilityToggleIsOn();

                logger.log(LogStatus.INFO,"Check that tour name is equals to inputed after refresh");
                Assert.assertEquals(nameRefr,newName );

                logger.log(LogStatus.INFO,"Check that tour remarks is equals to inputed after refresh");
                Assert.assertEquals(remarksRefr,remarks);

                logger.log(LogStatus.INFO,"Check that tour has "+viewsToDisplaySize+ "views after refresh");
                Assert.assertEquals(viewsToDisplaySizeRefr,viewsToDisplaySize);

                logger.log(LogStatus.INFO,"Check that visibility status is ON after refresh");
                Assert.assertTrue(visibleRefr);

                logger.log(LogStatus.INFO,"Go to Monitoring page");
                toursPage.GoToMonitoringPage();

                logger.log(LogStatus.INFO,"Input into filter field");
                monitoringPage.FilterField(newName);

                logger.log(LogStatus.INFO,"Check that new tour "+newName+ " is present in Monitoring Resources list");
                boolean tourIsExist = monitoringPage.VerifyThatResourceIsExist(newName);
                Assert.assertTrue(tourIsExist);
            }
        }
    }

    @Test
    public void AddNewTourTest() throws Exception {
        logger=report.startTest("AddNewTourTest");

        int toursCount = toursPage.GetToursSize();
        logger.log(LogStatus.INFO,"Click on New button");
        toursPage.ClickOnNewButton();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that Save button is disabled");
        Assert.assertFalse(viewsPage.SaveButtonIsEnabled());

        logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
        Assert.assertTrue(viewsPage.CancelButtonIsEnabled());

        int viewSize = toursPage.GetViewsSize();
        if(viewSize>0) {
            int index = toursPage.GetRandomDigit(0, viewSize);
            String viewName = toursPage.GetViewNameByIndex(index);
            logger.log(LogStatus.INFO, "Drag and drop view " + viewName + " tour");
            WebElement view = toursPage.GetViewByIndex(index);
            toursPage.DragAndDropView(view);

            if(toursPage.CheckThatModalIsOpen()){
                toursPage.ClickOnOkButton();
            }
            else{
                toursPage.WaitUntilSaveButtonWillBeEnable();
                logger.log(LogStatus.INFO,"Check that Save button is enabled");
                Assert.assertTrue(viewsPage.SaveButtonIsEnabled());

                logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
                Assert.assertTrue(viewsPage.CancelButtonIsEnabled());

                logger.log(LogStatus.INFO,"Press Save");
                toursPage.PressSaveButton();

                int toursCountAct = toursPage.GetToursSize();
                logger.log(LogStatus.INFO,"Check that tour is added");
                Assert.assertEquals(toursCountAct, toursCount+1);
            }
        }
    }

    @Test
    public void FilterToursTest() throws InterruptedException {
        logger=report.startTest("FilterToursTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Input "+tourName+" into filter field");
        toursPage.InputIntoFilterField(tourName);

        toursSize = toursPage.GetToursSize();
        for(int i=0; i<toursSize;i++){
            String name = toursPage.GetTourNameByIndex(i);
            logger.log(LogStatus.INFO,"Check that "+name+" contains "+ tourName);
            Assert.assertTrue(name.contains(tourName));
        }
    }

    @Test 
    public void FilterViewsTest() throws InterruptedException {
        logger=report.startTest("FilterViewsTest");

        logger.log(LogStatus.INFO,"Click on Available Views button");
        toursPage.ClickOnAvailibleViewsButton();

//        logger.log(LogStatus.INFO,"Click on Filter button");
//        toursPage.ClickOnViewFilterButton();

        int viewsSize = toursPage.GetViewsSize();
        if(viewsSize>0){
            int index = toursPage.GetRandomDigit(0, viewsSize);
            String viewName = toursPage.GetViewNameByIndex(index);

            logger.log(LogStatus.INFO,"Input "+viewName+" into filter field");
            toursPage.InputIntoViewsFilter(viewName);
            Thread.sleep(1000);

            viewsSize = toursPage.GetViewsSize();
            for(int i=0; i<viewsSize;i++){
                String name = toursPage.GetViewNameByIndex(i);
                logger.log(LogStatus.INFO,"Check that "+name+" contains "+ viewName);
                Assert.assertTrue(name.contains(viewName));
            }
        }
    }

    @Test
    public void ChangeNameTest() throws InterruptedException {
        logger=report.startTest("ChangeNameTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);

        int viewToDisplaySize = toursPage.GetViewsToDisplaySize();
        if(viewToDisplaySize>0){
            String name = toursPage.GetTourName();
            String newName = "NewTourName" + toursPage.GetRandomDigit(0, 10000);

            logger.log(LogStatus.INFO,"Input "+newName+" in name field");
            toursPage.InputIntoNameField(newName);

            boolean visible = toursPage.VisibilityToggleIsOn();
            if(!visible){
                logger.log(LogStatus.INFO,"Switch ON visible toggle switch");
                toursPage.ClickOnVisibilityToggleSwitch();
            }

            logger.log(LogStatus.INFO,"Press Save");
            toursPage.PressSaveButton();

            String nameAct =  toursPage.GetTourName();
            logger.log(LogStatus.INFO,"Check that name is changed");
            Assert.assertEquals(nameAct, newName);

            logger.log(LogStatus.INFO,"Refresh page ");
            toursPage.Refresh();

            logger.log(LogStatus.INFO,"Click on "+newName+" tour");
            toursPage.ClickOnTourByName(newName);
            Thread.sleep(1000);

            String nameRefr =  toursPage.GetTourName();
            logger.log(LogStatus.INFO,"Check that name is changed after refresh");
            Assert.assertEquals(nameRefr, newName);

            logger.log(LogStatus.INFO,"Go to Monitoring page");
            toursPage.GoToMonitoringPage();

            logger.log(LogStatus.INFO,"Input into filter field");
            monitoringPage.FilterField(newName);

            logger.log(LogStatus.INFO,"Check that new tour "+newName+ " is present in Monitoring Resources list");
            boolean tourIsExist = monitoringPage.VerifyThatResourceIsExist(newName);
            Assert.assertTrue(tourIsExist);
        }
    }

    @Test
    public void ChangeRemarksTest() throws InterruptedException {
        logger=report.startTest("ChangeRemarksTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);

        int viewToDisplaySize = toursPage.GetViewsToDisplaySize();
        if(viewToDisplaySize>0){
            String remarks = toursPage.GetRemarksText();
            String newRemarks = "NewRemarks for " + tourName;

            logger.log(LogStatus.INFO,"Input "+newRemarks+" in remarks field");
            toursPage.InputIntoRemarksField(newRemarks);

            logger.log(LogStatus.INFO,"Press Save");
            toursPage.PressSaveButton();

            String remarksAct =  toursPage.GetRemarksText();
            logger.log(LogStatus.INFO,"Check that remarks is changed");
            Assert.assertEquals(remarksAct, newRemarks);

            logger.log(LogStatus.INFO,"Refresh page ");
            toursPage.Refresh();

            logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
            toursPage.ClickOnTourByName(tourName);
            Thread.sleep(1000);

            String remarksRefr =  toursPage.GetRemarksText();
            logger.log(LogStatus.INFO,"Check that remarks is changed after refresh");
            Assert.assertEquals(remarksRefr, newRemarks);
        }
    }

    @Test
    public void ChangeVisibilityStatusTest() throws InterruptedException {
        logger=report.startTest("ChangeVisibilityStatusTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);

        int viewToDisplaySize = toursPage.GetViewsToDisplaySize();
        if(viewToDisplaySize>0){
            boolean visibilityStatus = toursPage.VisibilityToggleIsOn();
            logger.log(LogStatus.INFO,"Click on visibility status");
            toursPage.ClickOnVisibilityToggleSwitch();

            logger.log(LogStatus.INFO,"Press Save");
            toursPage.PressSaveButton();

            boolean visibilityStatusAct = toursPage.VisibilityToggleIsOn();
            logger.log(LogStatus.INFO,"Check that visibility status is changed");
            Assert.assertEquals(visibilityStatusAct, !visibilityStatus);

            logger.log(LogStatus.INFO,"Refresh page ");
            toursPage.Refresh();

            logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
            toursPage.ClickOnTourByName(tourName);
            Thread.sleep(1000);

            boolean visibilityStatusRefr = toursPage.VisibilityToggleIsOn();
            logger.log(LogStatus.INFO,"Check that visibility status is changed after refresh");
            Assert.assertEquals(visibilityStatusRefr, visibilityStatusAct);

            logger.log(LogStatus.INFO,"Go to Monitoring page");
            toursPage.GoToMonitoringPage();

            logger.log(LogStatus.INFO,"Input into filter field");
            monitoringPage.FilterField(tourName);

            if(visibilityStatusRefr){
                logger.log(LogStatus.INFO,"Check that new tour "+tourName+ " is present in Monitoring Resources list");
                boolean tourIsExist = monitoringPage.VerifyThatResourceIsExist(tourName);
                Assert.assertTrue(tourIsExist);
            }
            if(!visibilityStatusRefr){
                logger.log(LogStatus.INFO,"Check that new tour "+tourName+ " isn't present in Monitoring Resources list");
                boolean tourIsExist = monitoringPage.VerifyThatResourceIsExist(tourName);
                Assert.assertFalse(tourIsExist);
            }
        }
    }

    @Test
    public void AddViewTest() throws Exception {
        logger=report.startTest("AddViewTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Click on Available Views button");
        toursPage.ClickOnAvailibleViewsButton();

        int viewsToDisplaySize = toursPage.GetViewsToDisplaySize();
        int viewSize = toursPage.GetViewsSize();
        if(viewSize>0) {
            int viewInd = toursPage.GetRandomDigit(0, viewSize);
            String viewName = toursPage.GetViewNameByIndex(viewInd);
            logger.log(LogStatus.INFO, "Drag and drop view " + viewName + " to tour");
            WebElement view = toursPage.GetViewByIndex(viewInd);
            toursPage.DragAndDropView(view);
            toursPage.WaitUntilSaveButtonWillBeEnable();

            if(toursPage.CheckThatModalIsOpen()){
                toursPage.ClickOnOkButton();
            }
            else{
                logger.log(LogStatus.INFO, "Check that Save button is enabled");
                Assert.assertTrue(toursPage.SaveButtonIsEnabled());

                logger.log(LogStatus.INFO, "Check that Cancel button is enabled");
                Assert.assertTrue(toursPage.CancelButtonIsEnabled());

                logger.log(LogStatus.INFO, "Press Save");
                toursPage.PressSaveButton();

                int viewToDispalaySizeAct = toursPage.GetViewsToDisplaySize();
                logger.log(LogStatus.INFO, "Check that view is added");
                Assert.assertEquals(viewToDispalaySizeAct, viewsToDisplaySize + 1);

                logger.log(LogStatus.INFO, "Refresh page ");
                toursPage.Refresh();
                Thread.sleep(1000);

                logger.log(LogStatus.INFO, "Click on " + tourName + " tour");
                toursPage.ClickOnTourByName(tourName);
                Thread.sleep(1000);

                int viewToDisplaySizeRefr = toursPage.GetViewsToDisplaySize();
                logger.log(LogStatus.INFO, "Check that view is added after refresh");
                Assert.assertEquals(viewToDisplaySizeRefr, viewsToDisplaySize + 1);
            }
        }
    }

    @Test
    public void DeleteViewTest() throws InterruptedException {
        logger=report.startTest("DeleteViewTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);

        int viewSize = toursPage.GetViewsToDisplaySize();
        if(viewSize>1){
            int viewInd = toursPage.GetRandomDigit(0, viewSize);
            toursPage.ClickOnRemoveViewIconByIndex(viewInd);

            logger.log(LogStatus.INFO,"Check that Save button is enabled");
            Assert.assertTrue(toursPage.SaveButtonIsEnabled());

            logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
            Assert.assertTrue(toursPage.CancelButtonIsEnabled());

            logger.log(LogStatus.INFO,"Press Save");
            toursPage.PressSaveButton();

            int viewSizeAct = toursPage.GetViewsToDisplaySize();
            logger.log(LogStatus.INFO,"Check that view is removed");
            Assert.assertEquals(viewSizeAct, viewSize-1);

            logger.log(LogStatus.INFO,"Refresh page ");
            toursPage.Refresh();

            logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
            toursPage.ClickOnTourByName(tourName);
            Thread.sleep(1000);

            int viewSizeRefr = toursPage.GetViewsToDisplaySize();
            logger.log(LogStatus.INFO,"Check that view is removed after refresh");
            Assert.assertEquals(viewSizeRefr, viewSize-1);
        }
    }

    @Test
    public void DeleteAllViewsTest() throws InterruptedException {
        logger=report.startTest("DeleteAllViewsTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);

        int viewSize = toursPage.GetViewsToDisplaySize();
        if(viewSize>0){
            for(int i = 0; i<viewSize; i++){
                toursPage.ClickOnRemoveViewIconByIndex(0);
            }

            logger.log(LogStatus.INFO,"Check that Save button is disabled");
            Assert.assertFalse(toursPage.SaveButtonIsEnabled());

            logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
            Assert.assertTrue(toursPage.CancelButtonIsEnabled());
        }
    }

    @Test
    public void DeleteTourByButtonTest() throws InterruptedException {
        logger=report.startTest("DeleteTourByButtonTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Press Delete button");
        toursPage.ClickOnDeleteButton();

        logger.log(LogStatus.INFO,"Press Yes on dialog window");
        toursPage.ConfirmRemoving();

        int toursSizeAct = toursPage.GetToursSize();
        logger.log(LogStatus.INFO,"Check that tour is deleted");
        Assert.assertEquals(toursSizeAct, toursSize-1);

        logger.log(LogStatus.INFO,"Refresh page ");
        toursPage.Refresh();

        int toursSizeRefr = toursPage.GetToursSize();
        logger.log(LogStatus.INFO,"Check that tour is deleted after refresh");
        Assert.assertEquals(toursSizeRefr, toursSize-1);
    }

    @Test
    public void DeleteTourByButtonAndPressNoTest() throws InterruptedException {
        logger=report.startTest("DeleteTourByButtonAndPressNoTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Press Delete button");
        toursPage.ClickOnDeleteButton();

        logger.log(LogStatus.INFO,"Press No on dialog window");
        toursPage.CancelRemoving();

        int toursSizeAct = toursPage.GetToursSize();
        logger.log(LogStatus.INFO,"Check that tour isn't deleted");
        Assert.assertEquals(toursSizeAct, toursSize);

        logger.log(LogStatus.INFO,"Refresh page ");
        toursPage.Refresh();

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);
        Thread.sleep(1000);

        int toursSizeRefr = toursPage.GetToursSize();
        logger.log(LogStatus.INFO,"Check that tour isn't deleted after refresh");
        Assert.assertEquals(toursSizeRefr, toursSize);
    }

    @Test
    public void DeleteTourByIconTest() throws InterruptedException {
        logger=report.startTest("DeleteTourByIconTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Press Delete icon");
        toursPage.ClickOnDeleteIcon();

        logger.log(LogStatus.INFO,"Press Yes on dialog window");
        toursPage.ConfirmRemoving();

        int toursSizeAct = toursPage.GetToursSize();
        logger.log(LogStatus.INFO,"Check that tour is deleted");
        Assert.assertEquals(toursSizeAct, toursSize-1);

        logger.log(LogStatus.INFO,"Refresh page ");
        toursPage.Refresh();

        int toursSizeRefr = toursPage.GetToursSize();
        logger.log(LogStatus.INFO,"Check that tour is deleted after refresh");
        Assert.assertEquals(toursSizeRefr, toursSize-1);
    }

    @Test
    public void DeleteTourByIconAndPressNoTest() throws InterruptedException {
        logger=report.startTest("DeleteTourByIconAndPressNoTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Press Delete icon");
        toursPage.ClickOnDeleteIcon();

        logger.log(LogStatus.INFO,"Press No on dialog window");
        toursPage.CancelRemoving();

        int toursSizeAct = toursPage.GetToursSize();
        logger.log(LogStatus.INFO,"Check that tour isn't deleted");
        Assert.assertEquals(toursSizeAct, toursSize);

        logger.log(LogStatus.INFO,"Refresh page ");
        toursPage.Refresh();

        int toursSizeRefr = toursPage.GetToursSize();
        logger.log(LogStatus.INFO,"Check that tour isn't deleted after refresh");
        Assert.assertEquals(toursSizeRefr, toursSize);
    }

    @Test
    public void ChangeViewTimeTest() throws Exception {
        logger=report.startTest("ChangeViewTimeTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);
        Thread.sleep(1000);

        int viewTimeFieldsSize = toursPage.GetViewTimeField();
        if(viewTimeFieldsSize>0) {
            int fieldInd = toursPage.GetRandomDigit(0, viewTimeFieldsSize);
            String timeValue =toursPage.GetViewTimeValue(fieldInd);
            String time = ""+toursPage.GetRandomDigit(1, 99999);
            toursPage.InputIntoViewTimeField(fieldInd, time);
            toursPage.WaitUntilSaveButtonWillBeEnable();

            logger.log(LogStatus.INFO, "Check that Save button is enabled");
            Assert.assertTrue(toursPage.SaveButtonIsEnabled());

            logger.log(LogStatus.INFO, "Check that Cancel button is enabled");
            Assert.assertTrue(toursPage.CancelButtonIsEnabled());

            logger.log(LogStatus.INFO, "Press Save");
            toursPage.PressSaveButton();

            String timeValueAct =toursPage.GetViewTimeValue(fieldInd);
            logger.log(LogStatus.INFO, "Check that time value is changed");
            Assert.assertEquals(timeValueAct,time);

            logger.log(LogStatus.INFO,"Refresh page ");
            toursPage.Refresh();

            logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
            toursPage.ClickOnTourByName(tourName);
            Thread.sleep(1000);

            String timeValueRefr =toursPage.GetViewTimeValue(fieldInd);
            logger.log(LogStatus.INFO, "Check that time value is changed after refresh");
            Assert.assertEquals(timeValueRefr,time);
        }
    }

    @Test
    public void ChangeViewTimeToLessThanMinValueTest() throws Exception {
        logger=report.startTest("ChangeViewTimeToLessThanMinValueTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);
        Thread.sleep(1000);

        int viewTimeFieldsSize = toursPage.GetViewTimeField();
        if(viewTimeFieldsSize>0) {
            int fieldInd = toursPage.GetRandomDigit(0, viewTimeFieldsSize);
            String minVal = "1";

            logger.log(LogStatus.INFO, "Input value less than min");
            toursPage.InputIntoViewTimeField(fieldInd, "0");
            toursPage.WaitUntilSaveButtonWillBeEnable();

            logger.log(LogStatus.INFO, "Press Save");
            toursPage.PressSaveButton();

            String timeValueAct =toursPage.GetViewTimeValue(fieldInd);
            logger.log(LogStatus.INFO, "Check that time value is min "+minVal);
            Assert.assertEquals(timeValueAct,minVal);
        }
    }

    @Test
    public void ChangeViewTimeToMoreThanMaxValueTest() throws Exception {
        logger=report.startTest("ChangeViewTimeToMoreThanMaxValueTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);
        Thread.sleep(1000);

        int viewTimeFieldsSize = toursPage.GetViewTimeField();
        if(viewTimeFieldsSize>0) {
            int fieldInd = toursPage.GetRandomDigit(0, viewTimeFieldsSize);
            String maxVal ="99999";

            logger.log(LogStatus.INFO, "Input value less than min -0");
            toursPage.InputIntoViewTimeField(fieldInd, "100000");
            toursPage.WaitUntilSaveButtonWillBeEnable();

            logger.log(LogStatus.INFO, "Press Save");
            toursPage.PressSaveButton();

            String timeValueAct =toursPage.GetViewTimeValue(fieldInd);
            logger.log(LogStatus.INFO, "Check that time value is max "+maxVal);
            Assert.assertEquals(timeValueAct,maxVal);
        }
    }

    @Test
    public void ChangeViewTimeBySpinnersTest() throws Exception {
        logger=report.startTest("ChangeViewTimeBySpinnersTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);
        Thread.sleep(1000);

        int viewTimeFieldsSize = toursPage.GetViewTimeField();
        if(viewTimeFieldsSize>0) {
            int fieldInd = toursPage.GetRandomDigit(0, viewTimeFieldsSize);
            String timeValue =toursPage.GetViewTimeValue(fieldInd);
            int timeVal = Integer.parseInt(timeValue);
            int maxVal =99999;
            int minVal = 1;

            int maxTimes = maxVal - timeVal;
            if(maxTimes>20) maxTimes = 20;
            int UPtimes = toursPage.GetRandomDigit(0, maxTimes);

            logger.log(LogStatus.INFO,"Click on UP spinner "+UPtimes+" times");
            for(int i = 0 ; i<UPtimes; i++){
                toursPage.ClickOnTimeUPSpinnerByIndex(fieldInd);
            }

            timeVal = Integer.parseInt(toursPage.GetViewTimeValue(fieldInd));
            int minTimes = timeVal - minVal;
            if(minTimes>20) minTimes = 20;
            int DOWNtimes =  toursPage.GetRandomDigit(0, minTimes);

            logger.log(LogStatus.INFO,"Click on Down spinner "+DOWNtimes+" times");
            for(int i = 0 ; i<DOWNtimes; i++){
                toursPage.ClickOnTimeDownSpinnerByIndex(fieldInd);
            }

            toursPage.WaitUntilSaveButtonWillBeEnable();

            logger.log(LogStatus.INFO, "Check that Save button is enabled");
            Assert.assertTrue(toursPage.SaveButtonIsEnabled());

            logger.log(LogStatus.INFO, "Check that Cancel button is enabled");
            Assert.assertTrue(toursPage.CancelButtonIsEnabled());

            logger.log(LogStatus.INFO, "Press Save");
            toursPage.PressSaveButton();

            String timeValueAct =toursPage.GetViewTimeValue(fieldInd);
            logger.log(LogStatus.INFO, "Check that time value is changed");
            Assert.assertEquals(Integer.parseInt(timeValueAct),Integer.parseInt(timeValue)+UPtimes-DOWNtimes);

            logger.log(LogStatus.INFO,"Refresh page ");
            toursPage.Refresh();

            logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
            toursPage.ClickOnTourByName(tourName);
            Thread.sleep(1000);

            String timeValueRefr =toursPage.GetViewTimeValue(fieldInd);
            logger.log(LogStatus.INFO, "Check that time value is changed after refresh");
            Assert.assertEquals(Integer.parseInt(timeValueRefr),Integer.parseInt(timeValue)+UPtimes-DOWNtimes);
        }
    }

    @Test
    public void DeleteViewFromViewsPageTest() throws Exception {
        logger=report.startTest("DeleteViewFromViewsPageTest");

        int toursCount = toursPage.GetToursSize();
        logger.log(LogStatus.INFO,"Click on New button");
        toursPage.ClickOnNewButton();
        Thread.sleep(1000);

        String newName = "TourName"+toursPage.GetRandomDigit(0, 10000);
        logger.log(LogStatus.INFO,"Input "+newName+" in Name field");
        toursPage.InputIntoNameField(newName);

        logger.log(LogStatus.INFO,"Input  in Remarks field");
        toursPage.InputIntoRemarksField("Remarks");

        int viewSize = toursPage.GetViewsSize();
        if(viewSize>0) {
            int index = toursPage.GetRandomDigit(0, viewSize);
            String viewName = toursPage.GetViewNameByIndex(index);
            logger.log(LogStatus.INFO, "Drag and drop view " + viewName + " tour");
            WebElement view = toursPage.GetViewByIndex(index);
            toursPage.DragAndDropView(view);
            if(toursPage.CheckThatModalIsOpen()){
                toursPage.ClickOnOkButton();
            }
            else{
                toursPage.WaitUntilSaveButtonWillBeEnable();

                logger.log(LogStatus.INFO,"Press Save");
                toursPage.PressSaveButton();

                int toursCountAct = toursPage.GetToursSize();
                logger.log(LogStatus.INFO,"Check that tour is added");
                Assert.assertEquals(toursCountAct, toursCount+1);

                logger.log(LogStatus.INFO,"Go to Views page");
                viewsPage.ClickOnViewsButton();

                logger.log(LogStatus.INFO,"Click on view "+viewName);
                viewsPage.ClickOnViewByName(viewName);

                logger.log(LogStatus.INFO,"Press on Delete button ");
                viewsPage.ClickOnDeleteButton();

                logger.log(LogStatus.INFO,"Confirm removing ");
                viewsPage.ConfirmRemoving();
                viewsPage.WaitUntilDialogIsNotLocated();

                logger.log(LogStatus.INFO,"Go to Tours page");
                toursPage.ClickOnToursButton();

                logger.log(LogStatus.INFO,"Click on "+newName+" tour");
                toursPage.ClickOnTourByName(newName);
                Thread.sleep(1000);

                int viewToDispalaySize = toursPage.GetViewsToDisplaySize();
                logger.log(LogStatus.INFO, "Check that tour is empty");
                Assert.assertEquals(viewToDispalaySize, 0);
            }
        }
    }

    //@Test //Save button isn't enabled
    public void AddEmptyViewTest() throws Exception {
        logger=report.startTest("AddEmptyViewTest");

        int toursCount = toursPage.GetToursSize();
        logger.log(LogStatus.INFO,"Click on New button");
        toursPage.ClickOnNewButton();
        Thread.sleep(1000);

        String newName = "TourName"+toursPage.GetRandomDigit(0, 10000);
        logger.log(LogStatus.INFO,"Input "+newName+" in Name field");
        toursPage.InputIntoNameField(newName);

        logger.log(LogStatus.INFO,"Input  in Remarks field");
        toursPage.InputIntoRemarksField("Remarks");

        int viewSize = toursPage.GetViewsSize();
        if(viewSize>0) {
            int index = toursPage.GetRandomDigit(0, viewSize);
            String viewName = toursPage.GetViewNameByIndex(index);
            logger.log(LogStatus.INFO, "Drag and drop view " + viewName + " tour");
            WebElement view = toursPage.GetViewByIndex(index);
            toursPage.DragAndDropView(view);
            if(toursPage.CheckThatModalIsOpen()){
                toursPage.ClickOnOkButton();
            }
            else{
                toursPage.WaitUntilSaveButtonWillBeEnable();

                logger.log(LogStatus.INFO,"Press Save");
                toursPage.PressSaveButton();

                int toursCountAct = toursPage.GetToursSize();
                logger.log(LogStatus.INFO,"Check that tour is added");
                Assert.assertEquals(toursCountAct, toursCount+1);

                logger.log(LogStatus.INFO,"Go to Views page");
                viewsPage.ClickOnViewsButton();

                logger.log(LogStatus.INFO,"Click on view "+viewName);
                viewsPage.ClickOnViewByName(viewName);

                logger.log(LogStatus.INFO,"Press on Clear view button ");
                viewsPage.ClickOnClearViewButton();

                logger.log(LogStatus.INFO,"Press Save button");
                viewsPage.PressSaveButton();

                logger.log(LogStatus.INFO,"Go to Tours page");
                toursPage.ClickOnToursButton();

                logger.log(LogStatus.INFO,"Click on "+newName+" tour");
                toursPage.ClickOnTourByName(newName);
                Thread.sleep(1000);

                int viewToDispalaySize = toursPage.GetViewsToDisplaySize();
                logger.log(LogStatus.INFO, "Check that view is exist");
                Assert.assertEquals(viewToDispalaySize, 1);

                boolean visibilityStatus = toursPage.VisibilityToggleIsOn();
                logger.log(LogStatus.INFO,"Click on visibility status");
                toursPage.ClickOnVisibilityToggleSwitch();

                logger.log(LogStatus.INFO,"Press Save");
                toursPage.PressSaveButton();

                boolean visibilityStatusAct = toursPage.VisibilityToggleIsOn();
                logger.log(LogStatus.INFO,"Check that visibility status is changed");
                Assert.assertEquals(visibilityStatusAct, !visibilityStatus);

                logger.log(LogStatus.INFO,"Check that Save button is disabled");
                Assert.assertFalse(viewsPage.SaveButtonIsEnabled());

                logger.log(LogStatus.INFO,"Check that Cancel button is disabled");
                Assert.assertFalse(viewsPage.CancelButtonIsEnabled());
            }
        }
    }

    @Test
    public void NewTourWithEmptyViewTest() throws Exception {
        logger=report.startTest("NewTourWithEmptyViewTest");

        logger.log(LogStatus.INFO,"Go to Views page");
        viewsPage.ClickOnViewsButton();

        logger.log(LogStatus.INFO,"Click on New button");
        viewsPage.ClickOnNewButton();

        String viewName = "ViewName" + viewsPage.GetRandomDigit(0, 10000);
        logger.log(LogStatus.INFO,"Input name "+viewName+" in name field");
        viewsPage.InputIntoNameField(viewName);

        int layoutSize = viewsPage.GetLayoutSize();
        int random = viewsPage.GetRandomDigit(0, layoutSize);
        logger.log(LogStatus.INFO,"Select layout ");
        viewsPage.ClickOnLayoutByIndex(random);

        int viewSize = viewsPage.GetLayoutViewSize();
        int webPageSize = viewsPage.GetWebPageSize();
        if(webPageSize>0){
            int viewIndex = viewsPage.GetRandomDigit(0, viewSize);
            int webPageIndex = viewsPage.GetRandomDigit(0, webPageSize);
            String webPage = viewsPage.GetWebPageNameByIndex(webPageIndex);

            logger.log(LogStatus.INFO,"Drag " + webPage+" to layout with index "+viewIndex);
            String viewId = viewsPage.GetViewIDByIndex(viewIndex);
            String resourceId = viewsPage.GetWebPageIDByIndex(webPageIndex);
            viewsPage.DragAndDropCameraToView(resourceId , viewId);

            logger.log(LogStatus.INFO,"Press Save button");
            viewsPage.PressSaveButton();
            Thread.sleep(1000);

            int fullViewSize = viewsPage.GetFullViewsSize();
            logger.log(LogStatus.INFO,"Check that web page is dragged");
            Assert.assertEquals(fullViewSize,1);

            logger.log(LogStatus.INFO,"Go to Web Pages page");
            webPagesPage.ClickOnWebPagesButton();

            logger.log(LogStatus.INFO,"Click on web page "+webPage);
            webPagesPage.ClickOnWebPageByName(webPage);

            logger.log(LogStatus.INFO,"Remove Web page");
            webPagesPage.ClickOnDeleteButton();
            webPagesPage.ConfirmRemoving();
            Thread.sleep(1000);

            logger.log(LogStatus.INFO,"Go to Tours button");
            Thread.sleep(1000);
            toursPage.ClickOnToursButton();

//            logger.log(LogStatus.INFO,"Refresh page ");
//            toursPage.Refresh();

            int toursCount = toursPage.GetToursSize();

            logger.log(LogStatus.INFO,"Click on New button");
            toursPage.ClickOnNewButton();
            Thread.sleep(1000);

            String newTourName = "TourName"+toursPage.GetRandomDigit(0, 10000);
            logger.log(LogStatus.INFO,"Input "+newTourName+" in Name field");
            toursPage.InputIntoNameField(newTourName);

            logger.log(LogStatus.INFO, "Drag and drop view " + viewName + " tour");
            WebElement view = toursPage.GetViewByName(viewName);
            toursPage.DragAndDropView(view);
            if(toursPage.CheckThatModalIsOpen()){
                toursPage.ClickOnOkButton();
            }
            else {
                toursPage.WaitUntilSaveButtonWillBeEnable();

                logger.log(LogStatus.INFO,"Press Save");
                toursPage.PressSaveButton();

                logger.log(LogStatus.INFO,"Check that Save button is disabled");
                Assert.assertFalse(toursPage.SaveButtonIsEnabled());

                logger.log(LogStatus.INFO,"Check that Cancel button is disabled");
                Assert.assertFalse(toursPage.CancelButtonIsEnabled());

                int toursCountAct = toursPage.GetToursSize();
                logger.log(LogStatus.INFO,"Check that tour is added");
                Assert.assertEquals(toursCountAct, toursCount+1);

                logger.log(LogStatus.INFO,"Click on "+newTourName+" tour");
                toursPage.ClickOnTourByName(newTourName);
                Thread.sleep(1000);

                int viewToDispalaySize = toursPage.GetViewsToDisplaySize();
                logger.log(LogStatus.INFO, "Check that view is exist");
                Assert.assertEquals(viewToDispalaySize, 1);
            }
        }
  }

    //@Test
    public void DeleteResourceFromViewAndCheckInTourTest() throws Exception {
        logger=report.startTest("DeleteResourceFromViewAndCheckInTourTest");

        logger.log(LogStatus.INFO,"Go to Views page");
        viewsPage.ClickOnViewsButton();

        logger.log(LogStatus.INFO,"Click on New button");
        viewsPage.ClickOnNewButton();

        String viewName = "ViewName" + viewsPage.GetRandomDigit(0, 10000);
        logger.log(LogStatus.INFO,"Input name "+viewName+" in name field");
        viewsPage.InputIntoNameField(viewName);

        int layoutSize = viewsPage.GetLayoutSize();
        int random = viewsPage.GetRandomDigit(0, layoutSize);
        logger.log(LogStatus.INFO,"Select layout ");
        viewsPage.ClickOnLayoutByIndex(random);

        int viewSize = viewsPage.GetLayoutViewSize();
        int cameraSize = viewsPage.GetCamerasSize();
        if(cameraSize>0){
            int viewIndex = viewsPage.GetRandomDigit(0, viewSize);
            int resIndex = viewsPage.GetRandomDigit(0, cameraSize);
            String camera = viewsPage.GetCameraNameByIndex(resIndex);

            logger.log(LogStatus.INFO,"Drag " + camera+" to layout with index "+viewIndex);
            String viewId = viewsPage.GetViewIDByIndex(viewIndex);
            String resourceId = viewsPage.GetCameraIDByIndex(resIndex);
            viewsPage.DragAndDropCameraToView(resourceId , viewId);

            logger.log(LogStatus.INFO,"Press Save button");
            viewsPage.PressSaveButton();
            Thread.sleep(1000);

            int fullViewSize = viewsPage.GetFullViewsSize();
            logger.log(LogStatus.INFO,"Check that camera is dragged");
            Assert.assertEquals(fullViewSize,1);

            logger.log(LogStatus.INFO,"Get camera name");
            int ind = camera.indexOf("_");
            String cameraName = camera.substring(0, ind);

            logger.log(LogStatus.INFO,"Go to Tours button");
            toursPage.ClickOnToursButton();


            int toursCount = toursPage.GetToursSize();

            logger.log(LogStatus.INFO,"Click on New button");
            toursPage.ClickOnNewButton();
            Thread.sleep(1000);

            String newName = "TourName"+toursPage.GetRandomDigit(0, 10000);
            logger.log(LogStatus.INFO,"Input "+newName+" in Name field");
            toursPage.InputIntoNameField(newName);

            logger.log(LogStatus.INFO, "Drag and drop view " + viewName + " tour");
            WebElement view = toursPage.GetViewByName(viewName);
            toursPage.DragAndDropView(view);
            if(toursPage.CheckThatModalIsOpen()){
                toursPage.ClickOnOkButton();
            }
            else {
                toursPage.WaitUntilSaveButtonWillBeEnable();

                logger.log(LogStatus.INFO,"Check that Save button is enabled");
                Assert.assertTrue(toursPage.SaveButtonIsEnabled());

                logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
                Assert.assertTrue(toursPage.CancelButtonIsEnabled());

                logger.log(LogStatus.INFO,"Press Save");
                toursPage.PressSaveButton();

                int toursCountAct = toursPage.GetToursSize();
                logger.log(LogStatus.INFO,"Check that tour is added");
                Assert.assertEquals(toursCountAct, toursCount+1);

                logger.log(LogStatus.INFO,"Go to Cameras and Encoders page");
                camerasAndEncodersPage.ClickOnNetworkDeviceButton();
                camerasAndEncodersPage.ClickOnCameraAndEncoders();

                logger.log(LogStatus.INFO,"Click on camera "+cameraName);
                camerasAndEncodersPage.FindDeviceByText(cameraName).click();

                logger.log(LogStatus.INFO,"Remove Device");
                camerasAndEncodersPage.PressOnRemoveDeviceButton();
                camerasAndEncodersPage.ConfirmRemoving();

                logger.log(LogStatus.INFO,"Go to Tours button");
                toursPage.ClickOnResourcesButton();
                Thread.sleep(1000);
                toursPage.ClickOnToursButton();
            }
        }
    }

    @Test
    public void ChangeOrderForViewTest() throws Exception {
        logger=report.startTest("ChangeOrderForViewTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);
        Thread.sleep(1000);

        int viewSize = toursPage.GetViewsToDisplaySize();
        if(viewSize>1){
            int index1 = toursPage.GetRandomDigit(0, viewSize);
            int index2 =0;
            while(true){
                index2 = toursPage.GetRandomDigit(0, viewSize);
                if(index2!=index1) break;
            }
            String view1Name = toursPage.GetViewToDisplayNameByIndex(index1);
            String view2Name = toursPage.GetViewToDisplayNameByIndex(index2);
            WebElement view1 = toursPage.ReturnViewToDisplayByIndex(index1);
            WebElement view2 = toursPage.ReturnViewToDisplayByIndex(index2);

            logger.log(LogStatus.INFO,"Drag "+view1Name+" view to "+view2Name+"view");
            toursPage.DragAndDropViewToOtherPlace(view1, view2);

            logger.log(LogStatus.INFO,"Check that "+view1Name+" is moved");
            String viewNameNew =null;
            if(index1>index2) {
                  viewNameNew = toursPage.GetViewToDisplayNameByIndex(index2 + 1);
            }
            if(index1<index2) {
                 viewNameNew = toursPage.GetViewToDisplayNameByIndex(index2);
//                viewNameNew = toursPage.GetViewToDisplayNameByIndex(index2 - 1);
            }
            Assert.assertEquals(viewNameNew, view1Name);
        }
    }

    @AfterMethod
    public void afterMethod(ITestResult result){
        if(result.getStatus()==ITestResult.SUCCESS){
            logger.log(LogStatus.PASS, "Test is passed");
        }
        if(result.getStatus()==ITestResult.FAILURE){
            toursPage.takeScreenshot(driver, "Tours", result.getName());
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
        //driver.close();
       // driver.quit();
    }
}
