package testcases;

import pageObjects.CamerasAndEncodersPage;
import pageObjects.ViewsPage;
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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class ViewsTest {
    public WebDriver driver;
    public MonitoringPage monitoringPage;
    public ViewsPage viewsPage;
    CamerasAndEncodersPage camerasAndEncodersPage;
    public String[] Servers;
    ExtentReports report;
    ExtentTest logger;

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void setup(@Optional("ie")String browser) throws IOException, InterruptedException {
       
    	
    	/*String WebDriverLocation = System.getenv("WebDriver");

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
        viewsPage = PageFactory.initElements(driver, ViewsPage.class);
        Servers = viewsPage.getServersList();

        driver.navigate().to("http://" + Servers[0]);
        viewsPage.SignIn();

        viewsPage.WaitUntilLoadingBlockAppears();
        viewsPage.WaitUntilLoadingBlockDisappears();
        viewsPage.GoToViewsPageFromLanding();  */
    }

    @BeforeTest
    public void startReport(){
        report=new ExtentReports(System.getProperty("user.dir") +"/test-output/Views/ViewsReport.html", true);
    }

    @BeforeMethod(alwaysRun = true)
    public void GoToViewPage() throws InterruptedException, IOException {

//        String WebDriverLocation = System.getenv("WebDriver");

//        System.setProperty("webdriver.ie.driver", WebDriverLocation+"\\IEDriverServer.exe");
//        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
//        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
//        capabilities.setCapability(InternetExplorerDriver.IE_USE_PER_PROCESS_PROXY, true);
//        capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
//        capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
//        driver = new InternetExplorerDriver(capabilities);

//        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
//        driver.manage().window().maximize();
//        monitoringPage = PageFactory.initElements(driver, MonitoringPage.class);
//        viewsPage = PageFactory.initElements(driver, ViewsPage.class);
//        camerasAndEncodersPage = PageFactory.initElements(driver, CamerasAndEncodersPage.class);
//        Servers = viewsPage.getServersList(); 
    	
    	
    	String WebDriverLocation = System.getenv("WebDriver");
    	System.setProperty("webdriver.ie.driver", WebDriverLocation+"\\IEDriverServer.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        capabilities.setCapability(InternetExplorerDriver.IE_USE_PER_PROCESS_PROXY, true);
//        capabilities.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
        capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
//        capabilities.setCapability("nativeEvents", false);
//        driver = WebDriverFactory.getDriver(DesiredCapabilities.internetExplorer());
        driver = new InternetExplorerDriver(capabilities); 
        
        
        
    
    driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
    driver.manage().window().maximize();
    monitoringPage = PageFactory.initElements(driver, MonitoringPage.class);
    viewsPage = PageFactory.initElements(driver, ViewsPage.class);
    Servers = viewsPage.getServersList();

    driver.navigate().to("http://" + Servers[0]);
    viewsPage.SignIn();

    viewsPage.WaitUntilLoadingBlockAppears();
    viewsPage.WaitUntilLoadingBlockDisappears();
    viewsPage.GoToViewsPageFromLanding();
    	
    	
    	

        driver.navigate().to("http://" + Servers[0]);
        try{
            driver.switchTo().alert().accept();
        }catch(Exception a){}

        viewsPage.WaitUntilLoadingBlockAppears();
        viewsPage.WaitUntilLoadingBlockDisappears();
        viewsPage.GoToViewsPage();
    }

    @Test 
    public void  AddNewViewTest() throws Exception {
        logger=report.startTest("AddNewViewTest");

        int viewsCount = viewsPage.GetViewsSize();
        logger.log(LogStatus.INFO,"Click on New button");
        viewsPage.ClickOnNewButton();

        String viewName = "ViewName" + viewsPage.GetRandomDigit(0, 10000);
        logger.log(LogStatus.INFO,"Input name "+viewName+" in name field");
        viewsPage.InputIntoNameField(viewName);

        String remarks = "Remarks for "+viewName;
        logger.log(LogStatus.INFO,"Input remarks "+remarks+" in remarks field");
        viewsPage.InputIntoRemarksField(remarks);

        int layoutSize = viewsPage.GetLayoutSize();
        int random = viewsPage.GetRandomDigit(0, layoutSize);
        logger.log(LogStatus.INFO,"Select layout ");
        viewsPage.ClickOnLayoutByIndex(random);

        int viewSize = viewsPage.GetLayoutViewSize();
        int resourcesSize = viewsPage.GetResourcesSize();
        int rand = viewsPage.GetRandomDigit(1, viewSize);

        logger.log(LogStatus.INFO,"Drag " + rand+" resources ");
        for(int i=0; i<rand; i++){
            int viewIndex = viewsPage.GetRandomDigit(0, viewSize);
            int resIndex = viewsPage.GetRandomDigit(0, resourcesSize);
            String viewId = viewsPage.GetViewIDByIndex(viewIndex);
            String resourceId = viewsPage.GetResourcesIDByIndex(resIndex);
            viewsPage.DragAndDropCameraToView(resourceId , viewId);
        }

        int fullViewSize = viewsPage.GetFullViewsSize();
        int audioViewsSize = viewsPage.GetAudioViewsSize();
        logger.log(LogStatus.INFO,"Press Save button");
        viewsPage.PressSaveButton();

        String viewNameSaved = viewsPage.GetViewName();
        String viewRemarksSaved = viewsPage.GetRemarksText();
        int fullViewSizeSaved = viewsPage.GetFullViewsSize();
        int audioViewsSizeSaved = viewsPage.GetAudioViewsSize();

        logger.log(LogStatus.INFO,"Check that view name is equals to inputed");
        Assert.assertEquals(viewNameSaved,viewName );

        logger.log(LogStatus.INFO,"Check that view remarks is equals to inputed");
        Assert.assertEquals(viewRemarksSaved,remarks);

        logger.log(LogStatus.INFO,"Check that layout has "+fullViewSize+ "resources");
        Assert.assertEquals(fullViewSizeSaved,fullViewSize);
//        Assert.assertEquals(audioViewsSizeSaved,audioViewsSize);

        logger.log(LogStatus.INFO,"Refresh page ");
        viewsPage.Refresh();

        int viewsCountAfterRefresh = viewsPage.GetViewsSize();
        logger.log(LogStatus.INFO,"Check that new view is added ");
        Assert.assertEquals(viewsCountAfterRefresh, viewsCount+1);

        logger.log(LogStatus.INFO,"Click on "+viewName+" view");
        viewsPage.ClickOnViewByName(viewName);
        Thread.sleep(1000);

        String viewNameRefreshed = viewsPage.GetViewName();
        String viewRemarksRefreshed = viewsPage.GetRemarksText();
        int fullViewSizeRefreshed = viewsPage.GetFullViewsSize();
        int audioViewsSizeRefreshed = viewsPage.GetAudioViewsSize();

        logger.log(LogStatus.INFO,"Check that view name is equals to inputed after refresh");
        Assert.assertEquals(viewNameRefreshed,viewName );

        logger.log(LogStatus.INFO,"Check that view remarks is equals to inputed after refresh");
        Assert.assertEquals(viewRemarksRefreshed,remarks);

        logger.log(LogStatus.INFO,"Check that layout has "+fullViewSize+ "resources after refresh");
        Assert.assertEquals(fullViewSizeRefreshed,fullViewSize);
//        Assert.assertEquals(audioViewsSizeRefreshed,audioViewsSize);

        logger.log(LogStatus.INFO,"Go to Monitoring page");
        viewsPage.GoToMonitoringPage();

        logger.log(LogStatus.INFO,"Input into filter field");
        monitoringPage.FilterField(viewName);

        logger.log(LogStatus.INFO,"Check that new view is present in Monitoring Resources list");
        boolean viewIsExist = monitoringPage.VerifyThatResourceIsExist(viewName);
        Assert.assertTrue(viewIsExist);
    }

    @Test
    public void ChangeNameTest() throws InterruptedException {
        logger=report.startTest("ChangeNameTest");

        int viewsSize = viewsPage.GetViewsSize();
        Thread.sleep(1000);
        String viewName=null;
        int fullViewSize=0;

        for(int i=0; i<viewsSize; i++){
            int index = viewsPage.GetRandomDigit(0, viewsSize);
            String name = viewsPage.GetViewNameByIndex(index);
            logger.log(LogStatus.INFO,"Click on view "+name);
            viewsPage.ClickOnViewByIndex(index);

            fullViewSize = viewsPage.GetFullViewsSize();
            if(fullViewSize>0){
                viewName=name;
                break;
            }
        }
        if(viewName!=null){
            boolean visibility = viewsPage.VisibilityToggleIsOn();
            if(!visibility){
                logger.log(LogStatus.INFO,"Click on visibility status for view "+viewName);
                viewsPage.ClickOnVisibilityToggleSwitch();
            }

            String actName = viewsPage.GetViewName();
            String newView = "new view name" + viewsPage.GetRandomDigit(0, 100000);

            logger.log(LogStatus.INFO,"Change view name from "+actName+" to "+newView);
            viewsPage.InputIntoNameField(newView);

            logger.log(LogStatus.INFO,"Press Save button");
            viewsPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that name is changed ");
            String viewNameSaved = viewsPage.GetViewName();
            Assert.assertEquals(viewNameSaved, newView);

            logger.log(LogStatus.INFO,"Refresh page ");
            viewsPage.Refresh();

            int viewsCountAfterRefresh = viewsPage.GetViewsSize();
            logger.log(LogStatus.INFO,"Check that views count isn't changed");
            Assert.assertEquals(viewsCountAfterRefresh, viewsSize);

            logger.log(LogStatus.INFO,"Click on "+newView+" view");
            viewsPage.ClickOnViewByName(newView);
            Thread.sleep(1000);

            logger.log(LogStatus.INFO,"Check that name is changed after refresh");
            String viewNameRefreshed = viewsPage.GetViewName();
            Assert.assertEquals(viewNameRefreshed, newView);

            logger.log(LogStatus.INFO,"Go to Monitoring page");
            viewsPage.GoToMonitoringPage();

            logger.log(LogStatus.INFO,"Input into filter field");
            monitoringPage.FilterField(newView);

            logger.log(LogStatus.INFO,"Check that view with name "+newView+ "is present in Monitoring Resources list");
            boolean viewIsExist = monitoringPage.VerifyThatResourceIsExist(newView);
            Assert.assertTrue(viewIsExist);
        }
    }

    @Test
    public void ChangeRemarksTest() throws InterruptedException {
        logger=report.startTest("ChangeRemarksTest");

        int viewsSize = viewsPage.GetViewsSize();
        Thread.sleep(1000);
        String viewName=null;
        int fullViewSize=0;

        for(int i=0; i<viewsSize; i++){
            int index = viewsPage.GetRandomDigit(0, viewsSize);
            String name = viewsPage.GetViewNameByIndex(index);
            logger.log(LogStatus.INFO,"Click on view "+name);
            viewsPage.ClickOnViewByIndex(index);

            fullViewSize = viewsPage.GetFullViewsSize();
            if(fullViewSize>0){
                viewName=name;
                break;
            }
        }
        if(viewName!=null){
            String actRemarks = viewsPage.GetRemarksText();
            String newRemarks = "newRemarksFor " + viewName;

            logger.log(LogStatus.INFO,"Change view remarks from "+actRemarks+" to "+newRemarks);
            viewsPage.InputIntoRemarksField(newRemarks);

            logger.log(LogStatus.INFO,"Press Save button");
            viewsPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that remarks is changed ");
            String remarksSaved = viewsPage.GetRemarksText();
            Assert.assertEquals(remarksSaved, newRemarks);

            logger.log(LogStatus.INFO,"Refresh page ");
            viewsPage.Refresh();

            int viewsCountAfterRefresh = viewsPage.GetViewsSize();
            logger.log(LogStatus.INFO,"Check that views count isn't changed");
            Assert.assertEquals(viewsCountAfterRefresh, viewsSize);

            logger.log(LogStatus.INFO,"Click on "+viewName+" view");
            viewsPage.ClickOnViewByName(viewName);
            Thread.sleep(1000);

            logger.log(LogStatus.INFO,"Check that remarks is changed after refresh");
            String remarksRefreshed = viewsPage.GetRemarksText();
            Assert.assertEquals(remarksRefreshed, newRemarks);
        }
    }

    @Test
    public void ChangeVisibilityStatusTest() throws InterruptedException {
        logger=report.startTest("ChangeVisibilityStatusTest");

        int viewsSize = viewsPage.GetViewsSize();
        Thread.sleep(1000);
        String viewName=null;
        int fullViewSize=0;

        for(int i=0; i<viewsSize; i++){
            int index = viewsPage.GetRandomDigit(0, viewsSize);
            logger.log(LogStatus.INFO,"Click on view ");
            viewsPage.ClickOnViewByIndex(index);

            fullViewSize = viewsPage.GetFullViewsSize();
            String name = viewsPage.GetViewNameByIndex(index);
            if(fullViewSize>0){
                viewName=name;
                break;
            }
        }
        if(viewName!=null){
            boolean visibilityStatus = viewsPage.VisibilityToggleIsOn();

            logger.log(LogStatus.INFO,"Click on visibility status for view "+viewName);
            viewsPage.ClickOnVisibilityToggleSwitch();

            logger.log(LogStatus.INFO,"Press Save button");
            viewsPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Check that visibility status is changed");
            boolean visibilityStatusAct = viewsPage.VisibilityToggleIsOn();
            Assert.assertEquals(visibilityStatusAct, !visibilityStatus);

            logger.log(LogStatus.INFO,"Refresh page ");
            viewsPage.Refresh();

            int viewsCountAfterRefresh = viewsPage.GetViewsSize();
            logger.log(LogStatus.INFO,"Check that views count isn't changed");
            Assert.assertEquals(viewsCountAfterRefresh, viewsSize);

            logger.log(LogStatus.INFO,"Click on "+viewName+" view");
            viewsPage.ClickOnViewByName(viewName);
            Thread.sleep(1000);

            logger.log(LogStatus.INFO,"Check that remarks is changed after refresh");
            boolean visibilityStatusRefreshed = viewsPage.VisibilityToggleIsOn();
            Assert.assertEquals(visibilityStatusRefreshed, !visibilityStatus);

            logger.log(LogStatus.INFO,"Go to Monitoring page");
            viewsPage.GoToMonitoringPage();

            logger.log(LogStatus.INFO,"Input into filter field");
            monitoringPage.FilterField(viewName);

            if(visibilityStatusAct){
                logger.log(LogStatus.INFO,"Check that view with name "+viewName+ "is present in Monitoring Resources list");
                boolean viewIsExist = monitoringPage.VerifyThatResourceIsExist(viewName);
                Assert.assertTrue(viewIsExist);
            }

            if(!visibilityStatusAct){
                logger.log(LogStatus.INFO,"Check that view with name "+viewName+ "isn't present in Monitoring Resources list");
                boolean viewIsExist = monitoringPage.VerifyThatResourceIsExist(viewName);
                Assert.assertFalse(viewIsExist);
            }
        }
    }

    @Test
    public void ViewsFilterTest() throws InterruptedException {
        logger=report.startTest("ViewsFilterTest");

        int viewsSize = viewsPage.GetViewsSize();
        if(viewsSize>0){
            int index = viewsPage.GetRandomDigit(0, viewsSize);
            String viewName = viewsPage.GetViewNameByIndex(index);

            logger.log(LogStatus.INFO,"Input "+viewName+" into filter field");
            viewsPage.InputIntoViewsFilter(viewName);

            viewsSize = viewsPage.GetViewsSize();
            for(int i=0; i<viewsSize;i++){
                String name = viewsPage.GetViewNameByIndex(i);
                logger.log(LogStatus.INFO,"Check that "+name+" contains "+ viewName);
                Assert.assertTrue(name.contains(viewName));
            }
        }
    }

    @Test
    public void ResourceFilterTest() throws InterruptedException {
        logger=report.startTest("ResourceFilterTest");

        int viewsSize = viewsPage.GetViewsSize();
        int index = viewsPage.GetRandomDigit(0, viewsSize);

        if(viewsSize>0){
            logger.log(LogStatus.INFO,"Click on view ");
            viewsPage.ClickOnViewByIndex(index);
            Thread.sleep(1000);

            logger.log(LogStatus.INFO,"Click on Available Resources button");
            viewsPage.ClickOnAvailibleResourcesButton();

            int resourcesSize = viewsPage.GetResourcesSize();
            int rand = viewsPage.GetRandomDigit(0, resourcesSize);
            String resource = viewsPage.GetResourcesNameByIndex(rand);

            logger.log(LogStatus.INFO,"Input " +resource+" into resources filter field");
            viewsPage.InputIntoResourcesFilterField(resource);

            resourcesSize = viewsPage.GetResourcesSize();
            for(int i=0; i<resourcesSize;i++){
                String name = viewsPage.GetResourcesNameByIndex(i);
                logger.log(LogStatus.INFO,"Check that "+name+" contains "+ resource);
                Assert.assertTrue(name.contains(resource));
            }
        }
    }

    @Test
    public void ClearViewAndDragResourceTest() throws Exception {
        logger=report.startTest("ClearViewAndDragResourceTest");

        int viewsSize = viewsPage.GetViewsSize();
        Thread.sleep(1000);
        String viewName=null;
        int fullViewSize=0;

        for(int i=0; i<viewsSize; i++){
            int index = viewsPage.GetRandomDigit(0, viewsSize);
            String name = viewsPage.GetViewNameByIndex(index);
            logger.log(LogStatus.INFO,"Click on view "+name);
            viewsPage.ClickOnViewByIndex(index);

            fullViewSize = viewsPage.GetFullViewsSize();
            if(fullViewSize>0){
                viewName=name;
                break;
            }
        }
        if(viewName!=null){
            boolean clearButtonIsEnabled = viewsPage.ClearViewButtonIsEnabled();
            if(clearButtonIsEnabled){
                logger.log(LogStatus.INFO,"Click on Clear view button");
                viewsPage.ClickOnClearViewButton();

                logger.log(LogStatus.INFO,"Check that view is cleared");
                int fullViewSizeCleared = viewsPage.GetFullViewsSize();
                Assert.assertEquals(fullViewSizeCleared, 0);

                int viewSize = viewsPage.GetLayoutViewSize();
                int resourcesSize = viewsPage.GetResourcesSize();

                logger.log(LogStatus.INFO,"Drag resource");
                int viewIndex = viewsPage.GetRandomDigit(0, viewSize);
                int resIndex = viewsPage.GetRandomDigit(0, resourcesSize);
                String viewId = viewsPage.GetViewIDByIndex(viewIndex);
                String resourceId = viewsPage.GetResourcesIDByIndex(resIndex);
                viewsPage.DragAndDropCameraToView(resourceId , viewId);

                logger.log(LogStatus.INFO,"Check that Save button is enabled");
                Assert.assertTrue(viewsPage.SaveButtonIsEnabled());

                logger.log(LogStatus.INFO,"Press Save button");
                viewsPage.PressSaveButton();

                logger.log(LogStatus.INFO,"Check that resource is added ");
                int fullViewSizeSaved = viewsPage.GetFullViewsSize();
                if(fullViewSizeSaved>0) Assert.assertEquals(fullViewSizeSaved, 1);
                if(fullViewSizeSaved==0) {
                    int audioSize = viewsPage.GetAudioViewsSize();
                    Assert.assertEquals(audioSize, 1);
                }

                logger.log(LogStatus.INFO,"Refresh page ");
                viewsPage.Refresh();

                logger.log(LogStatus.INFO,"Click on "+viewName+" view");
                viewsPage.ClickOnViewByName(viewName);
                Thread.sleep(1000);

                logger.log(LogStatus.INFO,"Check that view isn't cleared after refresh");
                int fullViewSizeRefreshed = viewsPage.GetFullViewsSize();
                Assert.assertEquals(fullViewSizeRefreshed, 1);
            }
        }
    }

    @Test 
    public void ClearViewTest() throws Exception {
        logger=report.startTest("ClearViewTest");

        int viewsSize = viewsPage.GetViewsSize();
        Thread.sleep(1000);
        String viewName=null;
        int fullViewSize=0;

        for(int i=0; i<viewsSize; i++){
            int index = viewsPage.GetRandomDigit(0, viewsSize);
            String name = viewsPage.GetViewNameByIndex(index);
            logger.log(LogStatus.INFO,"Click on view "+name);
            viewsPage.ClickOnViewByIndex(index);

            fullViewSize = viewsPage.GetFullViewsSize();
            if(fullViewSize>0){
                viewName=name;
                break;
            }
        }
        if(viewName!=null){
            logger.log(LogStatus.INFO,"Click on Clear view button");
            viewsPage.ClickOnClearViewButton();

            logger.log(LogStatus.INFO,"Check that view is cleared");
            int fullViewSizeCleared = viewsPage.GetFullViewsSize();
            Assert.assertEquals(fullViewSizeCleared, 0);

            logger.log(LogStatus.INFO,"Check that Cancel button is enabled"); 
            Assert.assertTrue(viewsPage.CancelButtonIsEnabled());

            logger.log(LogStatus.INFO,"Check that Save button is disabled"); 
            Assert.assertFalse(viewsPage.SaveButtonIsEnabled());
        }
    }

    @Test 
    public void AddNewViewWithoutLayoutTest() throws Exception {
        logger=report.startTest("AddNewViewWithoutLayoutTest");

        int viewsCount = viewsPage.GetViewsSize();
        logger.log(LogStatus.INFO,"Click on New button");
        viewsPage.ClickOnNewButton();

        logger.log(LogStatus.INFO,"Check that Save button is disabled"); ///failed
        Assert.assertFalse(viewsPage.SaveButtonIsEnabled());

        logger.log(LogStatus.INFO,"Check that Cancel button is enabled"); ///failed
        Assert.assertTrue(viewsPage.CancelButtonIsEnabled());

        int layoutSize = viewsPage.GetLayoutSize();
        int random = viewsPage.GetRandomDigit(0, layoutSize);
        logger.log(LogStatus.INFO,"Select layout ");
        viewsPage.ClickOnLayoutByIndex(random);

        logger.log(LogStatus.INFO,"Check that Cancel button is enabled"); ///failed
        Assert.assertTrue(viewsPage.CancelButtonIsEnabled());

        logger.log(LogStatus.INFO,"Check that Save button is disabled"); ///failed
        Assert.assertFalse(viewsPage.SaveButtonIsEnabled());

        int viewSize = viewsPage.GetLayoutViewSize();
        int resourcesSize = viewsPage.GetResourcesSize();

        logger.log(LogStatus.INFO,"Drag resource");
        int viewIndex = viewsPage.GetRandomDigit(0, viewSize);
        int resIndex = viewsPage.GetRandomDigit(0, resourcesSize);
        String viewId = viewsPage.GetViewIDByIndex(viewIndex);
        String resourceId = viewsPage.GetResourcesIDByIndex(resIndex);
        viewsPage.DragAndDropCameraToView(resourceId , viewId);

        logger.log(LogStatus.INFO,"Check that Cancel button is enabled"); ///failed
        Assert.assertTrue(viewsPage.CancelButtonIsEnabled());

        logger.log(LogStatus.INFO,"Check that Save button is enabled"); ///failed
        Assert.assertTrue(viewsPage.SaveButtonIsEnabled());

        logger.log(LogStatus.INFO,"Click on Cancel");
        viewsPage.PressCancelButton();
    }

    @Test 
    public void ChangeLayoutTest() throws InterruptedException {
        logger=report.startTest("ChangeLayoutTest");

        int viewsSize = viewsPage.GetViewsSize();
        Thread.sleep(1000);
        String viewName=null;
        int fullViewSize=0;

        for(int i=0; i<viewsSize; i++){
            int index = viewsPage.GetRandomDigit(0, viewsSize);
            String name = viewsPage.GetViewNameByIndex(index);
            logger.log(LogStatus.INFO,"Click on view "+name);
            viewsPage.ClickOnViewByIndex(index);
            Thread.sleep(1000);

            fullViewSize = viewsPage.GetFullViewsSize();
            if(fullViewSize>0){
                viewName=name;
                break;
            }
        }
        if(viewName!=null){
            int paneCountAct= viewsPage.GetPaneViewListSize();
            logger.log(LogStatus.INFO,"There are "+paneCountAct+" panes");

            logger.log(LogStatus.INFO,"Click on Change Layout button");
            viewsPage.ClickOnChangeLayoutButton();
            viewsPage.WaitUntilDialogIsLocated();

            int layoutSizeInDialog = viewsPage.GetSizeLayoutInDialog();
            int index = 0;
            while(true){
                int randLayout = viewsPage.GetRandomDigit(0, layoutSizeInDialog);
                int paneCount= viewsPage.GetPanesSizeInLayoutInDialogByIndex(randLayout+1);

                if(paneCount!=paneCountAct){
                    index = randLayout;
                    break;
                }
            }
            logger.log(LogStatus.INFO,"Click on Layout with index "+index);
            int paneCount= viewsPage.GetPanesSizeInLayoutInDialogByIndex(index+1);
            viewsPage.ClickOnLayoutInDialogByIndex(index);
            viewsPage.WaitUntilDialogIsNotLocated();

            logger.log(LogStatus.INFO,"Check that dialog is closed ");
            Assert.assertFalse(viewsPage.CheckThatModalIsOpen());

            logger.log(LogStatus.INFO,"Check that layout is changed ");
            int paneViewSize = viewsPage.GetPaneViewListSize();
            Assert.assertEquals(paneViewSize, paneCount);

            boolean saveIsEnabled = viewsPage.SaveButtonIsEnabled();
            if(saveIsEnabled){
                logger.log(LogStatus.INFO,"Press Save button");
                viewsPage.PressSaveButton();

                logger.log(LogStatus.INFO,"Check that layout is changed ");
                int paneViewSizeNew = viewsPage.GetPaneViewListSize();
                Assert.assertEquals(paneViewSizeNew, paneCount);

                if((paneCountAct<paneViewSize)){
                    logger.log(LogStatus.INFO,"Check that count of active panes isn't changes ");
                    int newfullViewSize = viewsPage.GetFullViewsSize();
                    Assert.assertEquals(newfullViewSize, fullViewSize);
                }

                logger.log(LogStatus.INFO,"Refresh page ");
                viewsPage.Refresh();

                logger.log(LogStatus.INFO,"Click on "+viewName+" view");
                viewsPage.ClickOnViewByName(viewName);
                Thread.sleep(1000);

                if((paneCountAct<paneViewSize)) {
                    logger.log(LogStatus.INFO,"Check that count of active panes isn't changes after");
                    int paneViewSizerefreshed = viewsPage.GetPaneViewListSize();
                    Assert.assertEquals(paneViewSizerefreshed, paneViewSize);
                }
//
//            logger.log(LogStatus.INFO,"Check that layout change is saved after refresh ");
//            int fullViewSizeRefreshed = viewsPage.GetFullViewsSize();
//             Assert.assertEquals(fullViewSizeRefreshed, fullViewSize);
            }
        }
    }

    @Test
    public void DeleteViewByButtonTest() throws InterruptedException {
        logger=report.startTest("DeleteViewByButtonTest");

        int viewsSize = viewsPage.GetViewsSize();

        if(viewsSize>0){
            int index = viewsPage.GetRandomDigit(0, viewsSize);
            String name = viewsPage.GetViewNameByIndex(index);

            logger.log(LogStatus.INFO,"Click on view "+name);
            viewsPage.ClickOnViewByIndex(index);

            logger.log(LogStatus.INFO,"Press on Delete button ");
            viewsPage.ClickOnDeleteButton();

            logger.log(LogStatus.INFO,"Confirm removing ");
            viewsPage.ConfirmRemoving();
            viewsPage.WaitUntilDialogIsNotLocated();

            int viewsSizeAct = viewsPage.GetViewsSize();
            logger.log(LogStatus.INFO,"Check that views is removed ");
            Assert.assertEquals(viewsSizeAct, viewsSize-1);

            logger.log(LogStatus.INFO,"Refresh page ");
            viewsPage.Refresh();

            int viewsSizeRefr = viewsPage.GetViewsSize();
            logger.log(LogStatus.INFO,"Check that views is removed ");
            Assert.assertEquals(viewsSizeRefr, viewsSize-1);
        }
    }

    @Test
    public void CancelDeletingViewByButton() throws InterruptedException {
        logger=report.startTest("CancelDeletingViewByButton");

        int viewsSize = viewsPage.GetViewsSize();

        if(viewsSize>0){
            int index = viewsPage.GetRandomDigit(0, viewsSize);
            String name = viewsPage.GetViewNameByIndex(index);

            logger.log(LogStatus.INFO,"Click on view "+name);
            viewsPage.ClickOnViewByIndex(index);

            logger.log(LogStatus.INFO,"Press on Delete button ");
            viewsPage.ClickOnDeleteButton();

            logger.log(LogStatus.INFO,"Cancel removing ");
            viewsPage.CancelRemoving();
            viewsPage.WaitUntilDialogIsNotLocated();

            int viewsSizeAct = viewsPage.GetViewsSize();
            logger.log(LogStatus.INFO,"Check that views isn't removed ");
            Assert.assertEquals(viewsSizeAct, viewsSize);

            logger.log(LogStatus.INFO,"Refresh page ");
            viewsPage.Refresh();

            int viewsSizeRefr = viewsPage.GetViewsSize();
            logger.log(LogStatus.INFO,"Check that views isn't removed after refresh ");
            Assert.assertEquals(viewsSizeRefr, viewsSize);
        }
    }

    @Test
    public void DeleteViewByIconTest() throws InterruptedException {
        logger=report.startTest("DeleteViewByIconTest");

        int viewsSize = viewsPage.GetViewsSize();

        if(viewsSize>0){
            int index = viewsPage.GetRandomDigit(0, viewsSize);
            String name = viewsPage.GetViewNameByIndex(index);

            logger.log(LogStatus.INFO,"Click on view "+name);
            viewsPage.ClickOnViewByIndex(index);

            logger.log(LogStatus.INFO,"Press on Delete icon");
            viewsPage.ClickOnDeleteIcon();

            logger.log(LogStatus.INFO,"Confirm removing ");
            viewsPage.ConfirmRemoving();
            viewsPage.WaitUntilDialogIsNotLocated();

            int viewsSizeAct = viewsPage.GetViewsSize();
            logger.log(LogStatus.INFO,"Check that views is removed ");
            Assert.assertEquals(viewsSizeAct, viewsSize-1);

            logger.log(LogStatus.INFO,"Refresh page ");
            viewsPage.Refresh();

            int viewsSizeRefr = viewsPage.GetViewsSize();
            logger.log(LogStatus.INFO,"Check that views is removed ");
            Assert.assertEquals(viewsSizeRefr, viewsSize-1);
        }
    }

    @Test
    public void CancelRemovingViewByIconTest() throws InterruptedException {
        logger=report.startTest("CancelRemovingViewByIconTest");

        int viewsSize = viewsPage.GetViewsSize();

        if(viewsSize>0){
            int index = viewsPage.GetRandomDigit(0, viewsSize);
            String name = viewsPage.GetViewNameByIndex(index);

            logger.log(LogStatus.INFO,"Click on view "+name);
            viewsPage.ClickOnViewByIndex(index);

            logger.log(LogStatus.INFO,"Press on Delete icon");
            viewsPage.ClickOnDeleteIcon();

            logger.log(LogStatus.INFO,"Cancel removing ");
            viewsPage.CancelRemoving();
            viewsPage.WaitUntilDialogIsNotLocated();

            int viewsSizeAct = viewsPage.GetViewsSize();
            logger.log(LogStatus.INFO,"Check that views isn't removed ");
            Assert.assertEquals(viewsSizeAct, viewsSize);

            logger.log(LogStatus.INFO,"Refresh page ");
            viewsPage.Refresh();

            int viewsSizeRefr = viewsPage.GetViewsSize();
            logger.log(LogStatus.INFO,"Check that views isn't removed after refresh ");
            Assert.assertEquals(viewsSizeRefr, viewsSize);
        }
    }

    @Test(enabled=false)
    public void DeleteCameraAndCheckInViewTest() throws Exception {
        logger=report.startTest("DeleteCameraAndCheckInViewTest");

        int viewsCount = viewsPage.GetViewsSize();
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

        viewsPage.ClickOnNetworkDeviceButton();
        camerasAndEncodersPage.ClickOnCameraAndEncoders();

        logger.log(LogStatus.INFO,"Click on camera "+cameraName);
        camerasAndEncodersPage.FindDeviceByText(cameraName).click();

        logger.log(LogStatus.INFO,"Remove Device");
        camerasAndEncodersPage.PressOnRemoveDeviceButton();
        camerasAndEncodersPage.ConfirmRemoving();
        camerasAndEncodersPage.PressCloseAfterAdded();

        logger.log(LogStatus.INFO,"Go to View button");
        viewsPage.ClickOnResourcesButton();
        Thread.sleep(1000);
        viewsPage.ClickOnViewsButton();

        logger.log(LogStatus.INFO,"Click on view "+viewName);
        viewsPage.ClickOnViewByName(viewName);

        int fullViewSizeAct = viewsPage.GetFullViewsSize();
        logger.log(LogStatus.INFO,"Check that camera is deleted from layout");
        Assert.assertEquals(fullViewSizeAct,0);
    }

    @AfterMethod
    public void afterMethod(ITestResult result){
        if(result.getStatus()==ITestResult.SUCCESS){
            logger.log(LogStatus.PASS, "Test is passed");
        }
        if(result.getStatus()==ITestResult.FAILURE){
            viewsPage.takeScreenshot(driver, "Views", result.getName());
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
