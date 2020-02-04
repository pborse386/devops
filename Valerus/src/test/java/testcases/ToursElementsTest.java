package testcases;

import pageObjects.CamerasAndEncodersPage;
import pageObjects.ToursPage;
import pageObjects.ViewsPage;
import pageObjects.MonitoringPage;
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


public class ToursElementsTest {

    public WebDriver driver;
    public MonitoringPage monitoringPage;
    public ViewsPage viewsPage;
    ToursPage toursPage;
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
        Servers = toursPage.getServersList();

        driver.navigate().to("http://" + Servers[0]);
        toursPage.SignIn();

        toursPage.WaitUntilLoadingBlockAppears();
        toursPage.WaitUntilLoadingBlockDisappears();
        toursPage.GoToToursPageFromLanding();*/
    }

    @BeforeTest
    public void startReport(){
        report=new ExtentReports(System.getProperty("user.dir") +"/test-output/Tours/ToursElementsReport.html", true);
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
        viewsPage = PageFactory.initElements(driver, ViewsPage.class);
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

       /* if(toursPage.CheckThatModalIsOpen()){
            toursPage.ClickOnOkButton();
        }*/
    }

    @Test
    public void AddNewTourWithoutViewAndCancelTest() throws InterruptedException {
        logger=report.startTest("AddNewTourWithoutViewAndCancelTest");

        int toursCount = toursPage.GetToursSize();
        logger.log(LogStatus.INFO,"Click on New button");
        toursPage.ClickOnNewButton();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Click Cancel");
        toursPage.PressCancelButton();

        int toursCountAct = toursPage.GetToursSize();
        logger.log(LogStatus.INFO,"Check that tour is added");
        Assert.assertEquals(toursCountAct, toursCount);

        logger.log(LogStatus.INFO,"Check that Save button is disabled");
        Assert.assertFalse(viewsPage.SaveButtonIsEnabled());

        logger.log(LogStatus.INFO,"Check that Cancel button is disabled");
        Assert.assertFalse(viewsPage.CancelButtonIsEnabled());
    }

    @Test
    public void AddNewTourWithoutViewExitAndCancelTest() throws InterruptedException {
        logger=report.startTest("AddNewTourWithoutViewExitAndCancelTest");

        int toursCount = toursPage.GetToursSize();
        logger.log(LogStatus.INFO,"Click on New button");
        toursPage.ClickOnNewButton();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Go to View page");
        viewsPage.ClickOnViewsButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(viewsPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that Cancel Unsaved changes button is enabled");
        Assert.assertTrue(viewsPage.CancelUnsavedChangesButtonIsEnabled());

        logger.log(LogStatus.INFO,"Click on Cancel button in Unsaved changed window");
        viewsPage.CancelUnsavedChanges();

        int toursCountAct = toursPage.GetToursSize();
        logger.log(LogStatus.INFO,"Check that tour is added");
        Assert.assertEquals(toursCountAct, toursCount+1);

        logger.log(LogStatus.INFO,"Check that Save button is disabled");
        Assert.assertFalse(viewsPage.SaveButtonIsEnabled());

        logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
        Assert.assertTrue(viewsPage.CancelButtonIsEnabled());
    }

    @Test
    public void AddNewTourWithoutViewExitAndDoNotSaveTest() throws InterruptedException {
        logger=report.startTest("AddNewTourWithoutViewExitAndDoNotSaveTest");

        int toursCount = toursPage.GetToursSize();
        logger.log(LogStatus.INFO,"Click on New button");
        toursPage.ClickOnNewButton();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Go to View page");
        viewsPage.ClickOnViewsButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(toursPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that Cancel Unsaved changes button is enabled");
        Assert.assertTrue(toursPage.CancelUnsavedChangesButtonIsEnabled());

        logger.log(LogStatus.INFO,"Check that Do not Save Unsaved changes button is enabled");
        Assert.assertTrue(toursPage.DoNotSaveUnsavedChangesButtonIsEnabled());

        logger.log(LogStatus.INFO,"Click on Do Not Save button in Unsaved changed window");
        toursPage.PressDontSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to Tour page");
        toursPage.ClickOnToursButton();

        int toursCountAct = toursPage.GetToursSize();
        logger.log(LogStatus.INFO,"Check that tour isn't added");
        Assert.assertEquals(toursCountAct, toursCount);
    }

    @Test 
    public void AddNewTourWithoutViewExitAndSaveTest() throws InterruptedException {
        logger=report.startTest("AddNewTourWithoutViewExitAndSaveTest");

        logger.log(LogStatus.INFO,"Click on New button");
        toursPage.ClickOnNewButton();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Go to View page");
        viewsPage.ClickOnViewsButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(toursPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that Save Unsaved changes button is disabled"); ///failed
        Assert.assertFalse(toursPage.SaveUnsavedChangesButtonIsEnabled());
    }

    @Test
    public void AddNewTourAndCancelTest() throws Exception {
        logger=report.startTest("AddNewTourAndCancelTest");

        int toursCount = toursPage.GetToursSize();
        logger.log(LogStatus.INFO,"Click on New button");
        toursPage.ClickOnNewButton();
        Thread.sleep(1000);

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

                logger.log(LogStatus.INFO,"Check that Save button is enabled");
                Assert.assertTrue(viewsPage.SaveButtonIsEnabled());

                logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
                Assert.assertTrue(viewsPage.CancelButtonIsEnabled());

                logger.log(LogStatus.INFO,"Press Cancel");
                toursPage.PressCancelButton();

                int toursCountAct = toursPage.GetToursSize();
                logger.log(LogStatus.INFO,"Check that tour isn't added");
                Assert.assertEquals(toursCountAct, toursCount);
            }
        }
    }

    @Test
    public void AddNewTourExitAndCancelTest() throws Exception {
        logger=report.startTest("AddNewTourExitAndCancelTest");

        int toursCount = toursPage.GetToursSize();
        logger.log(LogStatus.INFO,"Click on New button");
        toursPage.ClickOnNewButton();
        Thread.sleep(1000);

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

                logger.log(LogStatus.INFO,"Go to View page");
                viewsPage.ClickOnViewsButton();

                logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
                Assert.assertTrue(viewsPage.CheckThatModalIsOpen());

                logger.log(LogStatus.INFO,"Check that Cancel Unsaved changes button is enabled");
                Assert.assertTrue(viewsPage.CancelUnsavedChangesButtonIsEnabled());

                logger.log(LogStatus.INFO,"Click on Cancel button in Unsaved changed window");
                viewsPage.CancelUnsavedChanges();

                int toursCountAct = toursPage.GetToursSize();
                logger.log(LogStatus.INFO,"Check that tour is added");
                Assert.assertEquals(toursCountAct, toursCount+1);

                logger.log(LogStatus.INFO,"Check that Save button is enabled");
                Assert.assertTrue(viewsPage.SaveButtonIsEnabled());

                logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
                Assert.assertTrue(viewsPage.CancelButtonIsEnabled());
            }
        }
    }

    @Test
    public void AddNewTourExitAndDoNotSaveTest() throws Exception {
        logger=report.startTest("AddNewTourExitAndCancelTest");

        int toursCount = toursPage.GetToursSize();
        logger.log(LogStatus.INFO,"Click on New button");
        toursPage.ClickOnNewButton();
        Thread.sleep(1000);

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
                logger.log(LogStatus.INFO,"Go to View page");
                viewsPage.ClickOnViewsButton();

                logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
                Assert.assertTrue(toursPage.CheckThatModalIsOpen());

                logger.log(LogStatus.INFO,"Check that Cancel Unsaved changes button is enabled");
                Assert.assertTrue(toursPage.CancelUnsavedChangesButtonIsEnabled());

                logger.log(LogStatus.INFO,"Check that Do not Save Unsaved changes button is enabled");
                Assert.assertTrue(toursPage.DoNotSaveUnsavedChangesButtonIsEnabled());

                logger.log(LogStatus.INFO,"Click on Do Not Save button in Unsaved changed window");
                toursPage.PressDontSaveUnsavedChanges();

                logger.log(LogStatus.INFO,"Go to Tour page");
                toursPage.ClickOnToursButton();

                int toursCountAct = toursPage.GetToursSize();
                logger.log(LogStatus.INFO,"Check that tour isn't added");
                Assert.assertEquals(toursCountAct, toursCount);
            }
        }
    }

    @Test
    public void AddNewTourExitAndSaveTest() throws Exception {
        logger=report.startTest("AddNewTourExitAndSaveTest");

        int toursCount = toursPage.GetToursSize();
        logger.log(LogStatus.INFO,"Click on New button");
        toursPage.ClickOnNewButton();
        Thread.sleep(1000);

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

                logger.log(LogStatus.INFO,"Go to View page");
                viewsPage.ClickOnViewsButton();

                logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
                Assert.assertTrue(toursPage.CheckThatModalIsOpen());

                logger.log(LogStatus.INFO,"Check that Save Unsaved changes button is enabled");
                Assert.assertTrue(toursPage.SaveUnsavedChangesButtonIsEnabled());

                logger.log(LogStatus.INFO,"Click on Save button in Unsaved changed window");
                toursPage.PressSaveUnsavedChanges();

                logger.log(LogStatus.INFO,"Go to Tour page");
                toursPage.ClickOnToursButton();

                int toursCountAct = toursPage.GetToursSize();
                logger.log(LogStatus.INFO,"Check that tour is added");
                Assert.assertEquals(toursCountAct, toursCount+1);
            }
        }
    }

    @Test
    public void ChangeNameAndCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeNameAndCancelTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);
        Thread.sleep(1000);

        String name = toursPage.GetTourName();
        String newName = "NewTourName" + toursPage.GetRandomDigit(0, 10000);

        logger.log(LogStatus.INFO,"Input "+newName+" in name field");
        toursPage.InputIntoNameField(newName);

        logger.log(LogStatus.INFO,"Press Cancel");
        toursPage.PressCancelButton();

        String nameAct =  toursPage.GetTourName();
        logger.log(LogStatus.INFO,"Check that name isn't changed");
        Assert.assertEquals(nameAct, name);

        logger.log(LogStatus.INFO,"Check that Save button is disabled");
        Assert.assertFalse(viewsPage.SaveButtonIsEnabled());

        logger.log(LogStatus.INFO,"Check that Cancel button is disabled");
        Assert.assertFalse(viewsPage.CancelButtonIsEnabled());
    }

    @Test
    public void ChangeNameExitAndCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeNameExitAndCancelTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);
        Thread.sleep(1000);

        int viewToDisplaySize = toursPage.GetViewsToDisplaySize();
        logger.log(LogStatus.INFO,"Views to display is "+viewToDisplaySize);

        String newName = "NewTourName" + toursPage.GetRandomDigit(0, 10000);
        logger.log(LogStatus.INFO,"Input "+newName+" in name field");
        toursPage.InputIntoNameField(newName);

        logger.log(LogStatus.INFO,"Go to View page");
        viewsPage.ClickOnViewsButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(viewsPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that Cancel Unsaved changes button is enabled");
        Assert.assertTrue(viewsPage.CancelUnsavedChangesButtonIsEnabled());

        logger.log(LogStatus.INFO,"Click on Cancel button in Unsaved changed window");
        viewsPage.CancelUnsavedChanges();

        String nameAct =  toursPage.GetTourName();
        logger.log(LogStatus.INFO,"Check that name change is staied");
        Assert.assertEquals(nameAct, newName);

        if(viewToDisplaySize>0){
            logger.log(LogStatus.INFO,"Check that Save button is enabled");
            Assert.assertTrue(viewsPage.SaveButtonIsEnabled());
        }

        logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
        Assert.assertTrue(viewsPage.CancelButtonIsEnabled());
    }

    @Test
    public void ChangeNameExitAndDoNotSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeNameExitAndDoNotSaveTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);
        Thread.sleep(1000);

        String name = toursPage.GetTourName();
        String newName = "NewTourName" + toursPage.GetRandomDigit(0, 10000);

        logger.log(LogStatus.INFO,"Input "+newName+" in name field");
        toursPage.InputIntoNameField(newName);

        logger.log(LogStatus.INFO,"Go to View page");
        viewsPage.ClickOnViewsButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(toursPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that Cancel Unsaved changes button is enabled");
        Assert.assertTrue(toursPage.CancelUnsavedChangesButtonIsEnabled());

        logger.log(LogStatus.INFO,"Check that Do not Save Unsaved changes button is enabled");
        Assert.assertTrue(toursPage.DoNotSaveUnsavedChangesButtonIsEnabled());

        logger.log(LogStatus.INFO,"Click on Do Not Save button in Unsaved changed window");
        toursPage.PressDontSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to Tour page");
        toursPage.ClickOnToursButton();

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);
        Thread.sleep(1000);

        String nameAct =  toursPage.GetTourName();
        logger.log(LogStatus.INFO,"Check that name change isn't saved");
        Assert.assertEquals(nameAct, name);
    }

    @Test
    public void ChangeNameExitAndSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeNameExitAndSaveTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);
        Thread.sleep(1000);

        int viewToDisplaySize = toursPage.GetViewsToDisplaySize();
        logger.log(LogStatus.INFO,"Views to display is "+viewToDisplaySize);

        if(viewToDisplaySize>0){
            String newName = "NewTourName" + toursPage.GetRandomDigit(0, 10000);
            logger.log(LogStatus.INFO,"Input "+newName+" in name field");
            toursPage.InputIntoNameField(newName);

            logger.log(LogStatus.INFO,"Go to View page");
            viewsPage.ClickOnViewsButton();

            logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
            Assert.assertTrue(toursPage.CheckThatModalIsOpen());

            logger.log(LogStatus.INFO,"Check that Cancel Unsaved changes button is enabled");
            Assert.assertTrue(toursPage.CancelUnsavedChangesButtonIsEnabled());

            logger.log(LogStatus.INFO,"Check that Save Unsaved changes button is enabled");
            Assert.assertTrue(toursPage.SaveUnsavedChangesButtonIsEnabled());

            logger.log(LogStatus.INFO,"Click on Save button in Unsaved changed window");
            toursPage.PressSaveUnsavedChanges();

            logger.log(LogStatus.INFO,"Go to Tour page");
            toursPage.ClickOnToursButton();

            logger.log(LogStatus.INFO,"Click on "+newName+" tour");
            toursPage.ClickOnTourByName(newName);

            String nameAct =  toursPage.GetTourName();
            logger.log(LogStatus.INFO,"Check that name change is saved");
            Assert.assertEquals(nameAct, newName);
        }
    }

    @Test
    public void ChangeRemarksAndCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeRemarksAndCancelTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);
        Thread.sleep(1000);

        String remarks = toursPage.GetRemarksText();
        String newRemarks = "NewRemarks" +toursPage.GetRandomDigit(0,5000)+ " for " + tourName;

        logger.log(LogStatus.INFO,"Input "+newRemarks+" in remarks field");
        toursPage.InputIntoRemarksField(newRemarks);

        logger.log(LogStatus.INFO,"Press Cancel");
        toursPage.PressCancelButton();

        String remarksAct =  toursPage.GetRemarksText();
        logger.log(LogStatus.INFO,"Check that remarks isn't changed");
        Assert.assertEquals(remarksAct, remarks);

        logger.log(LogStatus.INFO,"Check that Save button is disabled");
        Assert.assertFalse(viewsPage.SaveButtonIsEnabled());

        logger.log(LogStatus.INFO,"Check that Cancel button is disabled");
        Assert.assertFalse(viewsPage.CancelButtonIsEnabled());
    }

    @Test
    public void ChangeRemarksExitAndCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeRemarksExitAndCancelTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);
        Thread.sleep(1000);

        int viewToDisplaySize = toursPage.GetViewsToDisplaySize();
        String newRemarks = "NewRemarks" +toursPage.GetRandomDigit(0,5000)+ " for " + tourName;
        logger.log(LogStatus.INFO,"Input "+newRemarks+" in remarks field");
        toursPage.InputIntoRemarksField(newRemarks);

        logger.log(LogStatus.INFO,"Go to View page");
        viewsPage.ClickOnViewsButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(viewsPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that Cancel Unsaved changes button is enabled");
        Assert.assertTrue(viewsPage.CancelUnsavedChangesButtonIsEnabled());

        logger.log(LogStatus.INFO,"Click on Cancel button in Unsaved changed window");
        viewsPage.CancelUnsavedChanges();

        String remarksAct =  toursPage.GetRemarksText();
        logger.log(LogStatus.INFO,"Check that remarks change is staied");
        Assert.assertEquals(remarksAct, newRemarks);

        if(viewToDisplaySize>0){
            logger.log(LogStatus.INFO,"Check that Save button is enabled");
            Assert.assertTrue(viewsPage.SaveButtonIsEnabled());
        }
        logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
        Assert.assertTrue(viewsPage.CancelButtonIsEnabled());
    }

    @Test
    public void ChangeRemarksExitAndDoNotSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeRemarksExitAndDoNotSaveTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);
        Thread.sleep(1000);

        String remarks = toursPage.GetRemarksText();
        String newRemarks = "NewRemarks" +toursPage.GetRandomDigit(0,5000)+ " for " + tourName;

        logger.log(LogStatus.INFO,"Input "+newRemarks+" in remarks field");
        toursPage.InputIntoRemarksField(newRemarks);

        logger.log(LogStatus.INFO,"Go to View page");
        viewsPage.ClickOnViewsButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(toursPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that Do not Save Unsaved changes button is enabled");
        Assert.assertTrue(toursPage.DoNotSaveUnsavedChangesButtonIsEnabled());

        logger.log(LogStatus.INFO,"Click on Do Not Save button in Unsaved changed window");
        toursPage.PressDontSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to Tour page");
        toursPage.ClickOnToursButton();

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);
        Thread.sleep(1000);

        String remarksAct =  toursPage.GetRemarksText();
        logger.log(LogStatus.INFO,"Check that remarks change isn't saved");
        Assert.assertEquals(remarksAct, remarks);
    }

    @Test
    public void ChangeRemarksExitAndSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeRemarksExitAndSaveTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);
        Thread.sleep(1000);

        int viewToDisplaySize = toursPage.GetViewsToDisplaySize();
        logger.log(LogStatus.INFO,"View to display size is "+viewToDisplaySize);

        if(viewToDisplaySize==0){
            logger.log(LogStatus.INFO,"Check that Save button is disabled");
            Assert.assertFalse(viewsPage.SaveButtonIsEnabled());
        }
        if(viewToDisplaySize>0){
            String newRemarks = "NewRemarks" +toursPage.GetRandomDigit(0,5000)+ " for " + tourName;
            logger.log(LogStatus.INFO,"Input "+newRemarks+" in remarks field");
            toursPage.InputIntoRemarksField(newRemarks);
            toursPage.WaitUntilSaveButtonWillBeEnable();

            logger.log(LogStatus.INFO,"Go to View page");
            viewsPage.ClickOnViewsButton();

            logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
            Assert.assertTrue(toursPage.CheckThatModalIsOpen());

            logger.log(LogStatus.INFO,"Check that Save Unsaved changes button is enabled");
            Assert.assertTrue(toursPage.SaveUnsavedChangesButtonIsEnabled());

            logger.log(LogStatus.INFO,"Click on Save button in Unsaved changed window");
            toursPage.PressSaveUnsavedChanges();

            logger.log(LogStatus.INFO,"Go to Tour page");
            toursPage.ClickOnToursButton();

            logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
            toursPage.ClickOnTourByName(tourName);
            Thread.sleep(1000);

            String remarksAct =  toursPage.GetRemarksText();
            logger.log(LogStatus.INFO,"Check that remarks change is saved");
            Assert.assertEquals(remarksAct, newRemarks);
        }
    }

    @Test
    public void ChangeVisibilityStatusAndCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeVisibilityStatusAndCancelTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);
        Thread.sleep(1000);

        boolean visibilityStatus = toursPage.VisibilityToggleIsOn();
        logger.log(LogStatus.INFO,"Click on visibility status");
        toursPage.ClickOnVisibilityToggleSwitch();

        logger.log(LogStatus.INFO,"Press Cancel");
        toursPage.PressCancelButton();

        boolean visibilityStatusAct = toursPage.VisibilityToggleIsOn();
        logger.log(LogStatus.INFO,"Check that visibility status isn't changed");
        Assert.assertEquals(visibilityStatusAct, visibilityStatus);

        logger.log(LogStatus.INFO,"Check that Cancel button is disabled");
        Assert.assertFalse(viewsPage.CancelButtonIsEnabled());
    }

    @Test
    public void ChangeVisibilityStatusExitAndCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeVisibilityStatusExitAndCancelTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);
        Thread.sleep(1000);

        boolean visibilityStatus = toursPage.VisibilityToggleIsOn();
        logger.log(LogStatus.INFO,"Click on visibility status");
        toursPage.ClickOnVisibilityToggleSwitch();

        logger.log(LogStatus.INFO,"Go to View page");
        viewsPage.ClickOnViewsButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(viewsPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that Cancel Unsaved changes button is enabled");
        Assert.assertTrue(viewsPage.CancelUnsavedChangesButtonIsEnabled());

        logger.log(LogStatus.INFO,"Click on Cancel button in Unsaved changed window");
        viewsPage.CancelUnsavedChanges();

        boolean visibilityStatusAct = toursPage.VisibilityToggleIsOn();
        logger.log(LogStatus.INFO,"Check that visibility status change is remained");
        Assert.assertEquals(visibilityStatusAct, !visibilityStatus);

        logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
        Assert.assertTrue(viewsPage.CancelButtonIsEnabled());
    }

    @Test
    public void ChangeVisibilityStatusExitAndDoNotSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeVisibilityStatusExitAndDoNotSaveTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);
        Thread.sleep(1000);

        boolean visibilityStatus = toursPage.VisibilityToggleIsOn();
        logger.log(LogStatus.INFO,"Click on visibility status");
        toursPage.ClickOnVisibilityToggleSwitch();

        logger.log(LogStatus.INFO,"Go to View page");
        viewsPage.ClickOnViewsButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(toursPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that Do not Save Unsaved changes button is enabled");
        Assert.assertTrue(toursPage.DoNotSaveUnsavedChangesButtonIsEnabled());

        logger.log(LogStatus.INFO,"Click on Do Not Save button in Unsaved changed window");
        toursPage.PressDontSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to Tour page");
        toursPage.ClickOnToursButton();

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);
        Thread.sleep(1000);

        boolean visibilityStatusAct = toursPage.VisibilityToggleIsOn();
        logger.log(LogStatus.INFO,"Check that visibility status change isn't saved");
        Assert.assertEquals(visibilityStatusAct, visibilityStatus);
    }

    @Test
    public void ChangeVisibilityStatusExitAndSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeVisibilityStatusExitAndSaveTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);
        Thread.sleep(1000);

        int viewToDisplaySize = toursPage.GetViewsToDisplaySize();
        logger.log(LogStatus.INFO,"View to display size is "+viewToDisplaySize);

        if(viewToDisplaySize>0){
            boolean visibilityStatus = toursPage.VisibilityToggleIsOn();
            logger.log(LogStatus.INFO,"Click on visibility status");
            toursPage.ClickOnVisibilityToggleSwitch();

            logger.log(LogStatus.INFO,"Go to View page");
            viewsPage.ClickOnViewsButton();

            logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
            Assert.assertTrue(toursPage.CheckThatModalIsOpen());

            logger.log(LogStatus.INFO,"Check that Save Unsaved changes button is enabled");
            Assert.assertTrue(toursPage.SaveUnsavedChangesButtonIsEnabled());

            logger.log(LogStatus.INFO,"Click on Save button in Unsaved changed window");
            toursPage.PressSaveUnsavedChanges();

            logger.log(LogStatus.INFO,"Go to Tour page");
            toursPage.ClickOnToursButton();

            logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
            toursPage.ClickOnTourByName(tourName);
            Thread.sleep(1000);

            boolean visibilityStatusAct = toursPage.VisibilityToggleIsOn();
            logger.log(LogStatus.INFO,"Check that visibility status change is saved");
            Assert.assertEquals(visibilityStatusAct, !visibilityStatus);
        }
    }

    @Test
    public void DeleteAllViewsAndCancelTest() throws InterruptedException {
        logger=report.startTest("DeleteAllViewsAndCancelTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);
        Thread.sleep(1000);

        int viewSize = toursPage.GetViewsToDisplaySize();
        if(viewSize>0){
            for(int i = 0; i<viewSize; i++){
                toursPage.ClickOnRemoveViewIconByIndex(0);
            }

            logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
            Assert.assertTrue(toursPage.CancelButtonIsEnabled());

            logger.log(LogStatus.INFO,"Press Cancel");
            toursPage.PressCancelButton();

            int viewSizeAct = toursPage.GetViewsToDisplaySize();
            logger.log(LogStatus.INFO,"Check that views aren't removed");
            Assert.assertEquals(viewSizeAct, viewSize);

            logger.log(LogStatus.INFO,"Check that Save button is disabled");
            Assert.assertFalse(viewsPage.SaveButtonIsEnabled());

            logger.log(LogStatus.INFO,"Check that Cancel button is disabled");
            Assert.assertFalse(viewsPage.CancelButtonIsEnabled());
        }
    }

    @Test
    public void DeleteAllViewsExitAndCancelTest() throws InterruptedException {
        logger=report.startTest("DeleteAllViewsExitAndCancelTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);
        Thread.sleep(1000);

        int viewSize = toursPage.GetViewsToDisplaySize();
        if(viewSize>0){
            for(int i = 0; i<viewSize; i++){
                toursPage.ClickOnRemoveViewIconByIndex(0);
            }

            logger.log(LogStatus.INFO,"Go to View page");
            viewsPage.ClickOnViewsButton();

            logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
            Assert.assertTrue(viewsPage.CheckThatModalIsOpen());

            logger.log(LogStatus.INFO,"Check that Cancel Unsaved changes button is enabled");
            Assert.assertTrue(viewsPage.CancelUnsavedChangesButtonIsEnabled());

            logger.log(LogStatus.INFO,"Click on Cancel button in Unsaved changed window");
            viewsPage.CancelUnsavedChanges();

            int viewSizeAct = toursPage.GetViewsToDisplaySize();
            logger.log(LogStatus.INFO,"Check that views removing is remained");
            Assert.assertEquals(viewSizeAct, 0);

            logger.log(LogStatus.INFO,"Check that Save button is disabled");
            Assert.assertFalse(viewsPage.SaveButtonIsEnabled());

            logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
            Assert.assertTrue(viewsPage.CancelButtonIsEnabled());
        }
    }

    @Test
    public void DeleteAllViewsExitAndDoNotSaveTest() throws InterruptedException {
        logger=report.startTest("DeleteAllViewsExitAndDoNotSaveTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByIndex(index);

        int viewSize = toursPage.GetViewsToDisplaySize();
        if(viewSize>0){
            for(int i = 0; i<viewSize; i++){
                toursPage.ClickOnRemoveViewIconByIndex(0);
            }

            logger.log(LogStatus.INFO,"Go to View page");
            viewsPage.ClickOnViewsButton();

            logger.log(LogStatus.INFO,"Check that Do not Save Unsaved changes button is enabled");
            Assert.assertTrue(toursPage.DoNotSaveUnsavedChangesButtonIsEnabled());

            logger.log(LogStatus.INFO,"Click on Do Not Save button in Unsaved changed window");
            toursPage.PressDontSaveUnsavedChanges();

            logger.log(LogStatus.INFO,"Go to Tour page");
            toursPage.ClickOnToursButton();

            logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
            toursPage.ClickOnTourByIndex(index);

            int viewSizeAct = toursPage.GetViewsToDisplaySize();
            logger.log(LogStatus.INFO,"Check that views removing isn't saved");
            Assert.assertEquals(viewSizeAct, viewSize);
        }
    }

    @Test  //bug
    public void DeleteAllViewsExitAndSaveTest() throws InterruptedException {
        logger=report.startTest("DeleteAllViewsExitAndSaveTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByIndex(index);

        int viewSize = toursPage.GetViewsToDisplaySize();
        logger.log(LogStatus.INFO,"Delete all "+viewSize+" views");
        if(viewSize>0){
            for(int i = 0; i<viewSize; i++){
                toursPage.ClickOnRemoveViewIconByIndex(0);
            }

            logger.log(LogStatus.INFO,"Go to View page");
            viewsPage.ClickOnViewsButton();

            logger.log(LogStatus.INFO,"Check that Save Unsaved changes button is disabled");
            Assert.assertFalse(toursPage.SaveUnsavedChangesButtonIsEnabled());
        }
    }

    @Test
    public void DeleteViewAndCancelTest() throws InterruptedException {
        logger=report.startTest("DeleteViewAndCancelTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);
        Thread.sleep(1000);

        int viewSize = toursPage.GetViewsToDisplaySize();
        if(viewSize>0){
            int viewInd = toursPage.GetRandomDigit(0, viewSize);
            toursPage.ClickOnRemoveViewIconByIndex(viewInd);

            logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
            Assert.assertTrue(toursPage.CancelButtonIsEnabled());

            logger.log(LogStatus.INFO,"Press Cancel");
            toursPage.PressCancelButton();

            int viewSizeAct = toursPage.GetViewsToDisplaySize();
            logger.log(LogStatus.INFO,"Check that view isn't removed");
            Assert.assertEquals(viewSizeAct, viewSize);

            logger.log(LogStatus.INFO,"Check that Save button is disabled");
            Assert.assertFalse(viewsPage.SaveButtonIsEnabled());

            logger.log(LogStatus.INFO,"Check that Cancel button is disabled");
            Assert.assertFalse(viewsPage.CancelButtonIsEnabled());
        }
    }

    @Test
    public void DeleteViewExitAndCancelTest() throws InterruptedException {
        logger=report.startTest("DeleteViewExitAndCancelTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByIndex(index);

        int viewSize = toursPage.GetViewsToDisplaySize();
        if(viewSize>1){
            int viewInd = toursPage.GetRandomDigit(0, viewSize);
            toursPage.ClickOnRemoveViewIconByIndex(viewInd);

            logger.log(LogStatus.INFO,"Go to View page");
            viewsPage.ClickOnViewsButton();

            logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
            Assert.assertTrue(viewsPage.CheckThatModalIsOpen());

            logger.log(LogStatus.INFO,"Check that Cancel Unsaved changes button is enabled");
            Assert.assertTrue(viewsPage.CancelUnsavedChangesButtonIsEnabled());

            logger.log(LogStatus.INFO,"Click on Cancel button in Unsaved changed window");
            viewsPage.CancelUnsavedChanges();

            int viewSizeAct = toursPage.GetViewsToDisplaySize();
            logger.log(LogStatus.INFO,"Check that view removing is remained");
            Assert.assertEquals(viewSizeAct, viewSize-1);

            logger.log(LogStatus.INFO,"Check that Save button is enabled");
            Assert.assertTrue(viewsPage.SaveButtonIsEnabled());

            logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
            Assert.assertTrue(viewsPage.CancelButtonIsEnabled());
        }
    }

    @Test
    public void DeleteViewExitAndDoNotSaveTest() throws InterruptedException {
        logger=report.startTest("DeleteViewExitAndDoNotSaveTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByIndex(index);

        int viewSize = toursPage.GetViewsToDisplaySize();
        if(viewSize>0){
            int viewInd = toursPage.GetRandomDigit(0, viewSize);
            toursPage.ClickOnRemoveViewIconByIndex(viewInd);

            logger.log(LogStatus.INFO,"Go to View page");
            viewsPage.ClickOnViewsButton();

            logger.log(LogStatus.INFO,"Check that Do not Save Unsaved changes button is enabled");
            Assert.assertTrue(toursPage.DoNotSaveUnsavedChangesButtonIsEnabled());

            logger.log(LogStatus.INFO,"Click on Do Not Save button in Unsaved changed window");
            toursPage.PressDontSaveUnsavedChanges();

            logger.log(LogStatus.INFO,"Go to Tour page");
            toursPage.ClickOnToursButton();

            logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
            toursPage.ClickOnTourByIndex(index);

            int viewSizeAct = toursPage.GetViewsToDisplaySize();
            logger.log(LogStatus.INFO,"Check that view removing isn't saved");
            Assert.assertEquals(viewSizeAct, viewSize);
        }
    }

    @Test
    public void DeleteViewExitAndSaveTest() throws InterruptedException {
        logger=report.startTest("DeleteViewExitAndSaveTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByName(tourName);
        Thread.sleep(1000);

        int viewSize = toursPage.GetViewsToDisplaySize();
        if(viewSize>1){
            int viewInd = toursPage.GetRandomDigit(0, viewSize);
            toursPage.ClickOnRemoveViewIconByIndex(viewInd);

            logger.log(LogStatus.INFO,"Go to View page");
            viewsPage.ClickOnViewsButton();

            logger.log(LogStatus.INFO,"Check that Save Unsaved changes button is enabled");
            Assert.assertTrue(toursPage.SaveUnsavedChangesButtonIsEnabled());

            logger.log(LogStatus.INFO,"Click on Save button in Unsaved changed window");
            toursPage.PressSaveUnsavedChanges();

            logger.log(LogStatus.INFO,"Go to Tour page");
            toursPage.ClickOnToursButton();

            logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
            toursPage.ClickOnTourByName(tourName);
            Thread.sleep(1000);

            int viewSizeAct = toursPage.GetViewsToDisplaySize();
            logger.log(LogStatus.INFO,"Check that view removing is saved");
            Assert.assertEquals(viewSizeAct, viewSize-1);
        }
    }

    @Test
    public void AddViewAndCancelTest() throws Exception {
        logger=report.startTest("AddViewAndCancelTest");

        int toursSize = toursPage.GetToursSize();
        int index = toursPage.GetRandomDigit(0, toursSize);
        String tourName = toursPage.GetTourNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
        toursPage.ClickOnTourByIndex(index);
        Thread.sleep(1000);
        Thread.sleep(10);

        logger.log(LogStatus.INFO,"Click on Available Views button");
        toursPage.ClickOnAvailibleViewsButton();

        int viewsToDisplaySize = toursPage.GetViewsToDisplaySize();
        int viewSize = toursPage.GetViewsSize();
        if(viewSize>0) {
            int viewInd = toursPage.GetRandomDigit(0, viewSize);
            String viewName = toursPage.GetViewNameByIndex(viewInd);
            logger.log(LogStatus.INFO, "Drag and drop view " + viewName + " tour");
            WebElement view = toursPage.GetViewByIndex(viewInd);
            toursPage.DragAndDropView(view);
            if(toursPage.CheckThatModalIsOpen()){
                toursPage.ClickOnOkButton();
            }
            else{
                toursPage.WaitUntilSaveButtonWillBeEnable();

                logger.log(LogStatus.INFO, "Check that Cancel button is enabled");
                Assert.assertTrue(toursPage.CancelButtonIsEnabled());

                logger.log(LogStatus.INFO, "Press Cancel");
                toursPage.PressCancelButton();

                int viewToDispalaySizeAct = toursPage.GetViewsToDisplaySize();
                logger.log(LogStatus.INFO, "Check that view isn't added");
                Assert.assertEquals(viewToDispalaySizeAct, viewsToDisplaySize );

                logger.log(LogStatus.INFO,"Check that Save button is disabled");
                Assert.assertFalse(viewsPage.SaveButtonIsEnabled());

                logger.log(LogStatus.INFO,"Check that Cancel button is disabled");
                Assert.assertFalse(viewsPage.CancelButtonIsEnabled());
            }
        }
    }

    @Test
    public void AddViewExitAndCancelTest() throws Exception {
        logger=report.startTest("AddViewExitAndCancelTest");

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
            logger.log(LogStatus.INFO, "Drag and drop view " + viewName + " tour");
            WebElement view = toursPage.GetViewByIndex(viewInd);
            toursPage.DragAndDropView(view);
            if(toursPage.CheckThatModalIsOpen()){
                toursPage.ClickOnOkButton();
            }
            else{
                toursPage.WaitUntilSaveButtonWillBeEnable();

                logger.log(LogStatus.INFO,"Go to View page");
                viewsPage.ClickOnViewsButton();

                logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
                Assert.assertTrue(viewsPage.CheckThatModalIsOpen());

                logger.log(LogStatus.INFO,"Check that Cancel Unsaved changes button is enabled");
                Assert.assertTrue(viewsPage.CancelUnsavedChangesButtonIsEnabled());

                logger.log(LogStatus.INFO,"Click on Cancel button in Unsaved changed window");
                viewsPage.CancelUnsavedChanges();

                int viewToDispalaySizeAct = toursPage.GetViewsToDisplaySize();
                logger.log(LogStatus.INFO, "Check that view adding is remained");
                Assert.assertEquals(viewToDispalaySizeAct, viewsToDisplaySize+1);

                logger.log(LogStatus.INFO,"Check that Save button is enabled");
                Assert.assertTrue(viewsPage.SaveButtonIsEnabled());

                logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
                Assert.assertTrue(viewsPage.CancelButtonIsEnabled());
            }
        }
    }

    @Test
    public void AddViewExitAndDoNotSaveTest() throws Exception {
        logger=report.startTest("AddViewExitAndDoNotSaveTest");

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
            logger.log(LogStatus.INFO, "Drag and drop view " + viewName + " tour");
            WebElement view = toursPage.GetViewByIndex(viewInd);
            toursPage.DragAndDropView(view);
            if(toursPage.CheckThatModalIsOpen()){
                toursPage.ClickOnOkButton();
            }
            else{
                toursPage.WaitUntilSaveButtonWillBeEnable();

                logger.log(LogStatus.INFO,"Go to View page");
                viewsPage.ClickOnViewsButton();

                logger.log(LogStatus.INFO,"Check that Do not Save Unsaved changes button is enabled");
                Assert.assertTrue(toursPage.DoNotSaveUnsavedChangesButtonIsEnabled());

                logger.log(LogStatus.INFO,"Click on Do Not Save button in Unsaved changed window");
                toursPage.PressDontSaveUnsavedChanges();

                logger.log(LogStatus.INFO,"Go to Tour page");
                toursPage.ClickOnToursButton();

                logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
                toursPage.ClickOnTourByName(tourName);
                Thread.sleep(1000);

                int viewToDispalaySizeAct = toursPage.GetViewsToDisplaySize();
                logger.log(LogStatus.INFO, "Check that view adding isn't saved");
                Assert.assertEquals(viewToDispalaySizeAct, viewsToDisplaySize);
            }
        }
    }

    @Test
    public void AddViewExitAndSaveTest() throws Exception {
        logger=report.startTest("AddViewExitAndSaveTest");

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
            logger.log(LogStatus.INFO, "Drag and drop view " + viewName + " tour");
            WebElement view = toursPage.GetViewByIndex(viewInd);
            toursPage.DragAndDropView(view);
            if(toursPage.CheckThatModalIsOpen()){
                toursPage.ClickOnOkButton();
            }
            else{
                toursPage.WaitUntilSaveButtonWillBeEnable();

                logger.log(LogStatus.INFO,"Go to View page");
                viewsPage.ClickOnViewsButton();

                logger.log(LogStatus.INFO,"Check that Save Unsaved changes button is enabled");
                Assert.assertTrue(toursPage.SaveUnsavedChangesButtonIsEnabled());

                logger.log(LogStatus.INFO,"Click on Save button in Unsaved changed window");
                toursPage.PressSaveUnsavedChanges();

                logger.log(LogStatus.INFO,"Go to Tour page");
                toursPage.ClickOnToursButton();

                logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
                toursPage.ClickOnTourByName(tourName);
                Thread.sleep(1000);

                int viewToDispalaySizeAct = toursPage.GetViewsToDisplaySize();
                logger.log(LogStatus.INFO, "Check that view adding is saved");
                Assert.assertEquals(viewToDispalaySizeAct, viewsToDisplaySize+1);
            }
        }
    }

    @Test
    public void ChangeViewTimeAndCancelTest() throws Exception {
        logger=report.startTest("ChangeViewTimeAndCancelTest");

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

            logger.log(LogStatus.INFO, "Press Cancel");
            toursPage.PressCancelButton();

            String timeValueAct =toursPage.GetViewTimeValue(fieldInd);
            logger.log(LogStatus.INFO, "Check that time value isn't changed");
            Assert.assertEquals(timeValueAct,timeValue);

            logger.log(LogStatus.INFO,"Check that Save button is disabled");
            Assert.assertFalse(viewsPage.SaveButtonIsEnabled());

            logger.log(LogStatus.INFO,"Check that Cancel button is disabled");
            Assert.assertFalse(viewsPage.CancelButtonIsEnabled());
        }
    }

    @Test
    public void ChangeViewTimeExitAndCancelTest() throws Exception {
        logger=report.startTest("ChangeViewTimeExitAndCancelTest");

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

            logger.log(LogStatus.INFO,"Go to View page");
            viewsPage.ClickOnViewsButton();

            logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
            Assert.assertTrue(viewsPage.CheckThatModalIsOpen());

            logger.log(LogStatus.INFO,"Check that Cancel Unsaved changes button is enabled");
            Assert.assertTrue(viewsPage.CancelUnsavedChangesButtonIsEnabled());

            logger.log(LogStatus.INFO,"Click on Cancel button in Unsaved changed window");
            viewsPage.CancelUnsavedChanges();

            String timeValueAct =toursPage.GetViewTimeValue(fieldInd);
            logger.log(LogStatus.INFO, "Check that time change is remained");
            Assert.assertEquals(timeValueAct,time);

            logger.log(LogStatus.INFO,"Check that Save button is enabled");
            Assert.assertTrue(viewsPage.SaveButtonIsEnabled());

            logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
            Assert.assertTrue(viewsPage.CancelButtonIsEnabled());
        }
    }

    @Test
    public void ChangeViewTimeExitAndDoNotSaveTest() throws Exception {
        logger=report.startTest("ChangeViewTimeExitAndDoNotSaveTest");

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

            logger.log(LogStatus.INFO,"Go to View page");
            viewsPage.ClickOnViewsButton();

            logger.log(LogStatus.INFO,"Check that Do not Save Unsaved changes button is enabled");
            Assert.assertTrue(toursPage.DoNotSaveUnsavedChangesButtonIsEnabled());

            logger.log(LogStatus.INFO,"Click on Do Not Save button in Unsaved changed window");
            toursPage.PressDontSaveUnsavedChanges();

            logger.log(LogStatus.INFO,"Go to Tour page");
            toursPage.ClickOnToursButton();

            logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
            toursPage.ClickOnTourByName(tourName);
            Thread.sleep(1000);

            String timeValueAct =toursPage.GetViewTimeValue(fieldInd);
            logger.log(LogStatus.INFO, "Check that time change isn't saved");
            Assert.assertEquals(timeValueAct,timeValue);
        }
    }

    @Test
    public void ChangeViewTimeExitAndSaveTest() throws Exception {
        logger=report.startTest("ChangeViewTimeExitAndSaveTest");

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

            logger.log(LogStatus.INFO,"Go to View page");
            viewsPage.ClickOnViewsButton();

            logger.log(LogStatus.INFO,"Check that Save Unsaved changes button is enabled");
            Assert.assertTrue(toursPage.SaveUnsavedChangesButtonIsEnabled());

            logger.log(LogStatus.INFO,"Click on Save button in Unsaved changed window");
            toursPage.PressSaveUnsavedChanges();

            logger.log(LogStatus.INFO,"Go to Tour page");
            toursPage.ClickOnToursButton();

            logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
            toursPage.ClickOnTourByName(tourName);
            Thread.sleep(1000);

            String timeValueAct =toursPage.GetViewTimeValue(fieldInd);
            logger.log(LogStatus.INFO, "Check that time change is saved");
            Assert.assertEquals(timeValueAct,time);
        }
    }

    @Test
    public void ChangeViewTimeBySpinnersExitAndCancelTest() throws Exception {
        logger=report.startTest("ChangeViewTimeBySpinnersExitAndCancelTest");

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
            logger.log(LogStatus.INFO,"Go to View page");
            viewsPage.ClickOnViewsButton();

            logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
            Assert.assertTrue(viewsPage.CheckThatModalIsOpen());

            logger.log(LogStatus.INFO,"Check that Cancel Unsaved changes button is enabled");
            Assert.assertTrue(viewsPage.CancelUnsavedChangesButtonIsEnabled());

            logger.log(LogStatus.INFO,"Click on Cancel button in Unsaved changed window");
            viewsPage.CancelUnsavedChanges();

            String timeValueAct =toursPage.GetViewTimeValue(fieldInd);
            logger.log(LogStatus.INFO, "Check that time value change is remained");
            Assert.assertEquals(Integer.parseInt(timeValueAct),Integer.parseInt(timeValue)+UPtimes-DOWNtimes);

            logger.log(LogStatus.INFO,"Check that Save button is enabled");
            Assert.assertTrue(viewsPage.SaveButtonIsEnabled());

            logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
            Assert.assertTrue(viewsPage.CancelButtonIsEnabled());
        }
    }

    @Test
    public void ChangeViewTimeBySpinnersAndCancelTest() throws Exception {
        logger=report.startTest("ChangeViewTimeBySpinnersAndCancelTest");

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
            logger.log(LogStatus.INFO, "Check that Cancel button is enabled");
            Assert.assertTrue(toursPage.CancelButtonIsEnabled());

            logger.log(LogStatus.INFO, "Press Cancel");
            Thread.sleep(1000);
            toursPage.PressCancelButton();

            String timeValueAct =toursPage.GetViewTimeValue(fieldInd);
            logger.log(LogStatus.INFO, "Check that time value isn't changed");
            Assert.assertEquals(timeValueAct,timeValue);

            logger.log(LogStatus.INFO,"Check that Save button is disabled");
            Assert.assertFalse(viewsPage.SaveButtonIsEnabled());

            logger.log(LogStatus.INFO,"Check that Cancel button is disabled");
            Assert.assertFalse(viewsPage.CancelButtonIsEnabled());
        }
    }

    @Test
    public void ChangeViewTimeBySpinnersExitAndDoNotSaveTest() throws Exception {
        logger=report.startTest("ChangeViewTimeBySpinnersExitAndDoNotSaveTest");

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

            logger.log(LogStatus.INFO,"Go to View page");
            viewsPage.ClickOnViewsButton();

            logger.log(LogStatus.INFO,"Check that Do not Save Unsaved changes button is enabled");
            Assert.assertTrue(toursPage.DoNotSaveUnsavedChangesButtonIsEnabled());

            logger.log(LogStatus.INFO,"Click on Do Not Save button in Unsaved changed window");
            toursPage.PressDontSaveUnsavedChanges();

            logger.log(LogStatus.INFO,"Go to Tour page");
            toursPage.ClickOnToursButton();

            logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
            toursPage.ClickOnTourByName(tourName);
            Thread.sleep(1000);

            String timeValueAct =toursPage.GetViewTimeValue(fieldInd);
            logger.log(LogStatus.INFO, "Check that time value isn't changed");
            Assert.assertEquals(timeValueAct,timeValue);
        }
    }

    @Test
    public void ChangeViewTimeBySpinnersExitAndSaveTest() throws Exception {
        logger=report.startTest("ChangeViewTimeBySpinnersExitAndSaveTest");

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

            logger.log(LogStatus.INFO,"Go to View page");
            viewsPage.ClickOnViewsButton();

            logger.log(LogStatus.INFO,"Check that Save Unsaved changes button is enabled");
            Assert.assertTrue(toursPage.SaveUnsavedChangesButtonIsEnabled());

            logger.log(LogStatus.INFO,"Click on Save button in Unsaved changed window");
            toursPage.PressSaveUnsavedChanges();

            logger.log(LogStatus.INFO,"Go to Tour page");
            toursPage.ClickOnToursButton();

            logger.log(LogStatus.INFO,"Click on "+tourName+" tour");
            toursPage.ClickOnTourByName(tourName);
            Thread.sleep(1000);

            String timeValueAct =toursPage.GetViewTimeValue(fieldInd);
            logger.log(LogStatus.INFO, "Check that time value is changed");
            Assert.assertEquals(Integer.parseInt(timeValueAct),Integer.parseInt(timeValue)+UPtimes-DOWNtimes);
        }
    }

    @AfterMethod
    public void afterMethod(ITestResult result){
        if(result.getStatus()==ITestResult.SUCCESS){
            logger.log(LogStatus.PASS, "Test is passed");
        }
        if(result.getStatus()==ITestResult.FAILURE){
            viewsPage.takeScreenshot(driver, "Tours", result.getName());
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
       // driver.close();
       // driver.quit();
    }
}
