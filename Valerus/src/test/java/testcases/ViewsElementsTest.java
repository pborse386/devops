package testcases;

import pageObjects.CamerasAndEncodersPage;
import pageObjects.AudioChannelsPage;
import pageObjects.GroupsHierarchyPage;
import pageObjects.ViewsPage;
import pageObjects.WebPagesPage;
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
import java.util.concurrent.TimeUnit;


public class ViewsElementsTest {
    public WebDriver driver;
    //public VideoChannelsPage videoChannelsPage;
//    public LoginPage loginPage;
    public MonitoringPage monitoringPage;
    public ViewsPage viewsPage;
    public GroupsHierarchyPage groupsHierarchyPage;
    public String[] Servers;
    ExtentReports report;
    ExtentTest logger;

    @BeforeClass(alwaysRun = true)
    public void setup() throws IOException, InterruptedException {
       
    }

    @BeforeTest
    public void startReport(){
        report=new ExtentReports(System.getProperty("user.dir") +"/test-output/Views/ViewsElementsReport.html", true);
    }

//    @BeforeMethod(alwaysRun = true)
//    public void GoToViewPage() throws InterruptedException, IOException {
//        String WebDriverLocation = System.getenv("WebDriver");
//
//                System.setProperty("webdriver.chrome.driver", WebDriverLocation+"\\chromedriver.exe");
//
//        driver = new ChromeDriver();
//
//        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
//        driver.manage().window().maximize();
//        groupsHierarchyPage=PageFactory.initElements(driver, GroupsHierarchyPage.class);
//        monitoringPage = PageFactory.initElements(driver, MonitoringPage.class);
//        viewsPage = PageFactory.initElements(driver, ViewsPage.class);
//        Servers = viewsPage.getServersList();
//
//        driver.navigate().to("http://" + Servers[0]);
//        viewsPage.SignIn();
//
//        viewsPage.GoToViewsPage();
//    }

    @BeforeMethod(alwaysRun = true)
    public void GoToViewPage() throws InterruptedException, IOException {
    	
    	 String WebDriverLocation = System.getenv("WebDriver");

         // System.setProperty("webdriver.chrome.driver", WebDriverLocation+"\\chromedriver.exe");
         // driver = new ChromeDriver();    //Works
          
          
          
          System.setProperty("webdriver.ie.driver", WebDriverLocation+"\\IEDriverServer.exe");
          DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
          capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
          capabilities.setCapability(InternetExplorerDriver.IE_USE_PER_PROCESS_PROXY, true);
          capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
          capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
           driver = new InternetExplorerDriver(capabilities); 
          
          
          
          monitoringPage = PageFactory.initElements(driver, MonitoringPage.class);
          groupsHierarchyPage=PageFactory.initElements(driver, GroupsHierarchyPage.class);
          viewsPage = PageFactory.initElements(driver, ViewsPage.class);
          Servers = viewsPage.getServersList();
          driver.get("http://" + Servers[0]);
          driver.manage().window().maximize();
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
    public void AddNewViewWithoutLayoutAndExitAndCancelTest() throws Exception {
        logger=report.startTest("AddNewViewWithoutLayoutAndExitAndCancelTest");

        int viewsCount = viewsPage.GetViewsSize();
        logger.log(LogStatus.INFO,"Click on New button");
        viewsPage.ClickOnNewButton();

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(viewsPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that Cancel Unsaved changes button is enabled");
        Assert.assertTrue(viewsPage.CancelUnsavedChangesButtonIsEnabled());

        logger.log(LogStatus.INFO,"Click on Cancel button in Unsaved changed window");
        viewsPage.CancelUnsavedChanges();

        int viewsCountAfterRefresh = viewsPage.GetViewsSize();
        logger.log(LogStatus.INFO,"Check that new view is added ");
        Assert.assertEquals(viewsCountAfterRefresh, viewsCount+1);
    }

    @Test
    public void AddNewViewWithoutLayoutAndExitAndDoNotSaveTest() throws Exception {
        logger=report.startTest("AddNewViewWithoutLayoutAndExitAndDoNotSaveTest");

        int viewsCount = viewsPage.GetViewsSize();
        logger.log(LogStatus.INFO,"Click on New button");
        viewsPage.ClickOnNewButton();

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(viewsPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that Do not Save Unsaved changes button is enabled");
        Assert.assertTrue(viewsPage.DoNotSaveUnsavedChangesButtonIsEnabled());

        logger.log(LogStatus.INFO,"Click on Do Not Save button in Unsaved changed window");
        viewsPage.PressDontSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to Views page");
        viewsPage.ClickOnViewsButton();

        int viewsCountAfterRefresh = viewsPage.GetViewsSize();
        logger.log(LogStatus.INFO,"Check that new view isn't added ");
        Assert.assertEquals(viewsCountAfterRefresh, viewsCount);
    }

    @Test//bug 9475 Save button is enabled when layout isn't selected
    public void AddNewViewWithoutLayoutAndExitAndSaveTest() throws Exception {
        logger=report.startTest("AddNewViewWithoutLayoutAndExitAndSaveTest");

        int viewsCount = viewsPage.GetViewsSize();
        logger.log(LogStatus.INFO,"Click on New button");
        viewsPage.ClickOnNewButton();

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(viewsPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that Save Unsaved changes button is disabled"); 
        Assert.assertFalse(viewsPage.SaveUnsavedChangesButtonIsEnabled());

        logger.log(LogStatus.INFO,"Click on Do Not Save button in Unsaved changed window");
        viewsPage.PressDontSaveUnsavedChanges();
    }

    @Test
    public void AddNewViewWithLayoutExitAndCancelTest() throws Exception {
        logger=report.startTest("AddNewViewWithLayoutExitAndCancelTest");

        int viewsCount = viewsPage.GetViewsSize();
        logger.log(LogStatus.INFO,"Click on New button");
        viewsPage.ClickOnNewButton();

        int layoutSize = viewsPage.GetLayoutSize();
        int random = viewsPage.GetRandomDigit(0, layoutSize);
        logger.log(LogStatus.INFO,"Select layout ");
        viewsPage.ClickOnLayoutByIndex(random);

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(viewsPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that Cancel Unsaved changes button is enabled");
        Assert.assertTrue(viewsPage.CancelUnsavedChangesButtonIsEnabled());

        logger.log(LogStatus.INFO,"Click on Cancel button in Unsaved changed window");
        viewsPage.CancelUnsavedChanges();

        int viewsCountAfterRefresh = viewsPage.GetViewsSize();
        logger.log(LogStatus.INFO,"Check that new view is added ");
        Assert.assertEquals(viewsCountAfterRefresh, viewsCount+1);
    }

    @Test
    public void AddNewViewWithLayoutExitAndDoNotSaveTest() throws Exception {
        logger=report.startTest("AddNewViewWithLayoutExitAndDoNotSaveTest");

        int viewsCount = viewsPage.GetViewsSize();
        logger.log(LogStatus.INFO,"Click on New button");
        viewsPage.ClickOnNewButton();

        int layoutSize = viewsPage.GetLayoutSize();
        int random = viewsPage.GetRandomDigit(0, layoutSize);
        logger.log(LogStatus.INFO,"Select layout ");
        viewsPage.ClickOnLayoutByIndex(random);

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(viewsPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that Do not Save Unsaved changes button is enabled");
        Assert.assertTrue(viewsPage.DoNotSaveUnsavedChangesButtonIsEnabled());

        logger.log(LogStatus.INFO,"Click on Do Not Save button in Unsaved changed window");
        viewsPage.PressDontSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to Views page");
        viewsPage.ClickOnViewsButton();

        int viewsCountAfterRefresh = viewsPage.GetViewsSize();
        logger.log(LogStatus.INFO,"Check that new view isn't added ");
        Assert.assertEquals(viewsCountAfterRefresh, viewsCount);
    }

    @Test //Bug 10954:Views (UI) - Save button is enabled when layout is empty
    public void AddNewViewWithLayoutExitAndSaveTest() throws Exception {
        logger=report.startTest("AddNewViewWithLayoutExitAndSaveTest");

        int viewsCount = viewsPage.GetViewsSize();
        logger.log(LogStatus.INFO,"Click on New button");
        viewsPage.ClickOnNewButton();

        int layoutSize = viewsPage.GetLayoutSize();
        int random = viewsPage.GetRandomDigit(0, layoutSize);
        logger.log(LogStatus.INFO,"Select layout ");
        viewsPage.ClickOnLayoutByIndex(random);

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(viewsPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that Save Unsaved changes button is disabled"); ///failed
        Assert.assertFalse(viewsPage.SaveUnsavedChangesButtonIsEnabled());
    }

    @Test  //Bug 9414 Views are shown partly when adding more than 20 views
    public void AddNewViewAndPressCancelTest() throws Exception {
        logger=report.startTest("AddNewViewAndPressCancelTest");

        int viewsCount = viewsPage.GetViewsSize();
        logger.log(LogStatus.INFO,"Click on New button");
        viewsPage.ClickOnNewButton();

        String viewName = "ViewName" + viewsPage.GetRandomDigit(10000, 20000);
        logger.log(LogStatus.INFO,"Input name "+viewName+" in name field");
        viewsPage.InputIntoNameField(viewName);

        int layoutSize = viewsPage.GetLayoutSize();
        int random = viewsPage.GetRandomDigit(0, layoutSize);
        logger.log(LogStatus.INFO,"Select layout ");
        viewsPage.ClickOnLayoutByIndex(random);

        int viewSize = viewsPage.GetLayoutViewSize();
        int resourcesSize = viewsPage.GetResourcesSize();

        logger.log(LogStatus.INFO,"Drag and drop");
        int viewIndex = viewsPage.GetRandomDigit(0, viewSize);
        int resIndex = viewsPage.GetRandomDigit(0, resourcesSize);
        String viewId = viewsPage.GetViewIDByIndex(viewIndex);
        String resourceId = viewsPage.GetResourcesIDByIndex(resIndex);
        viewsPage.DragAndDropCameraToView(resourceId , viewId);
        viewsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Check that Cancel button enabled");
        Assert.assertTrue(viewsPage.CancelButtonIsEnabled());

        logger.log(LogStatus.INFO,"Press Cancel button");
        viewsPage.PressCancelButton();

        int viewsCountAfterRefresh = viewsPage.GetViewsSize();
        logger.log(LogStatus.INFO,"Check that new view isn't added ");
        Assert.assertEquals(viewsCountAfterRefresh, viewsCount);

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window doesn't appear");
        Assert.assertFalse(viewsPage.CheckThatModalIsOpen());
    }

    @Test
    public void AddNewViewExitAndPressCancelTest() throws Exception {
        logger=report.startTest("AddNewViewExitAndPressCancelTest");

        int viewsCount = viewsPage.GetViewsSize();
        logger.log(LogStatus.INFO,"Click on New button");
        viewsPage.ClickOnNewButton();

        String viewName = "ViewName" + viewsPage.GetRandomDigit(10000, 20000);
        logger.log(LogStatus.INFO,"Input name "+viewName+" in name field");
        viewsPage.InputIntoNameField(viewName);

        int layoutSize = viewsPage.GetLayoutSize();
        int random = viewsPage.GetRandomDigit(0, layoutSize);
        logger.log(LogStatus.INFO,"Select layout ");
        viewsPage.ClickOnLayoutByIndex(random);

        int viewSize = viewsPage.GetLayoutViewSize();
        int resourcesSize = viewsPage.GetResourcesSize();

        logger.log(LogStatus.INFO,"Drag and drop");
        int viewIndex = viewsPage.GetRandomDigit(0, viewSize);
        int resIndex = viewsPage.GetRandomDigit(0, resourcesSize);
        String viewId = viewsPage.GetViewIDByIndex(viewIndex);
        String resourceId = viewsPage.GetResourcesIDByIndex(resIndex);
        viewsPage.DragAndDropCameraToView(resourceId , viewId);

        int fullViewSize = viewsPage.GetFullViewsSize();
        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(viewsPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Click on Cancel button in Unsaved changed window");
        viewsPage.CancelUnsavedChanges();

        int viewsCountAfterRefresh = viewsPage.GetViewsSize();
        logger.log(LogStatus.INFO,"Check that new view is added ");
        Assert.assertEquals(viewsCountAfterRefresh, viewsCount+1);

        String viewNameAct = viewsPage.GetViewName();
        int fullViewSizeAct = viewsPage.GetFullViewsSize();

        logger.log(LogStatus.INFO,"Check that view name is equals to inputed");
        Assert.assertEquals(viewNameAct,viewName );

        logger.log(LogStatus.INFO,"Check that layout has "+fullViewSize+ "resources after refresh");
        Assert.assertEquals(fullViewSizeAct,fullViewSize);

        logger.log(LogStatus.INFO," Check that Save button is enabled");
        Assert.assertTrue(viewsPage.SaveButtonIsEnabled());

        logger.log(LogStatus.INFO," Check that Cancel button is enabled");
        Assert.assertTrue(viewsPage.CancelButtonIsEnabled());
    }

    @Test
    public void AddNewViewExitAndPressDoNotSaveTest() throws Exception {
        logger=report.startTest("AddNewViewExitAndPressDoNotSaveTest");

        int viewsCount = viewsPage.GetViewsSize();
        logger.log(LogStatus.INFO,"Click on New button");
        viewsPage.ClickOnNewButton();

        String viewName = "ViewName" + viewsPage.GetRandomDigit(10000, 20000);
        logger.log(LogStatus.INFO,"Input name "+viewName+" in name field");
        viewsPage.InputIntoNameField(viewName);

        int layoutSize = viewsPage.GetLayoutSize();
        int random = viewsPage.GetRandomDigit(0, layoutSize);
        logger.log(LogStatus.INFO,"Select layout ");
        viewsPage.ClickOnLayoutByIndex(random);

        int viewSize = viewsPage.GetLayoutViewSize();
        int resourcesSize = viewsPage.GetResourcesSize();

        logger.log(LogStatus.INFO,"Drag and drop");
        int viewIndex = viewsPage.GetRandomDigit(0, viewSize);
        int resIndex = viewsPage.GetRandomDigit(0, resourcesSize);
        String viewId = viewsPage.GetViewIDByIndex(viewIndex);
        String resourceId = viewsPage.GetResourcesIDByIndex(resIndex);
        viewsPage.DragAndDropCameraToView(resourceId , viewId);

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(viewsPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Click on Do not Save button in Unsaved changed window");
        viewsPage.PressDontSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to Views page");
        viewsPage.ClickOnViewsButton();

        int viewsCountAfterRefresh = viewsPage.GetViewsSize();
        logger.log(LogStatus.INFO,"Check that new view isn't added ");
        Assert.assertEquals(viewsCountAfterRefresh, viewsCount);
    }

    @Test
    public void AddNewViewExitAndPressSaveTest() throws Exception {
        logger=report.startTest("AddNewViewExitAndPressSaveTest");

        int viewsCount = viewsPage.GetViewsSize();
        logger.log(LogStatus.INFO,"Click on New button");
        viewsPage.ClickOnNewButton();

        String viewName = "ViewName" + viewsPage.GetRandomDigit(10000, 20000);
        logger.log(LogStatus.INFO,"Input name "+viewName+" in name field");
        viewsPage.InputIntoNameField(viewName);

        int layoutSize = viewsPage.GetLayoutSize();
        int random = viewsPage.GetRandomDigit(0, layoutSize);
        logger.log(LogStatus.INFO,"Select layout ");
        viewsPage.ClickOnLayoutByIndex(random);

        int viewSize = viewsPage.GetLayoutViewSize();
        int resourcesSize = viewsPage.GetResourcesSize();

        logger.log(LogStatus.INFO,"Drag and drop");
        int viewIndex = viewsPage.GetRandomDigit(0, viewSize);
        int resIndex = viewsPage.GetRandomDigit(0, resourcesSize);
        String viewId = viewsPage.GetViewIDByIndex(viewIndex);
        String resourceId = viewsPage.GetResourcesIDByIndex(resIndex);
        viewsPage.DragAndDropCameraToView(resourceId , viewId);
        int fullViewSize = viewsPage.GetFullViewsSize();

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(viewsPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Click on Save button in Unsaved changed window");
        viewsPage.PressSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to Views page");
        viewsPage.ClickOnViewsButton();

        int viewsCountAfterRefresh = viewsPage.GetViewsSize();
        logger.log(LogStatus.INFO,"Check that new view is added ");
        Assert.assertEquals(viewsCountAfterRefresh, viewsCount+1);

        logger.log(LogStatus.INFO,"Click on "+viewName+" view");
        viewsPage.ClickOnViewByName(viewName);
        Thread.sleep(1000);

        String viewNameAct = viewsPage.GetViewName();
        int fullViewSizeAct = viewsPage.GetFullViewsSize();

        logger.log(LogStatus.INFO,"Check that view name is equals to inputed");
        Assert.assertEquals(viewNameAct,viewName );

        logger.log(LogStatus.INFO,"Check that layout has "+fullViewSize+ "resources after refresh");
        Assert.assertEquals(fullViewSizeAct,fullViewSize);
    }

    @Test //bug 9972:Views(UI) - Cancel button is disabled when any change
    public void ChangeNameAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeNameAndPressCancelTest");
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
        if(viewName!=null) {
            String actName = viewsPage.GetViewName();
            String newView = "new view name" + viewsPage.GetRandomDigit(0, 100000);

            logger.log(LogStatus.INFO, "Change view name from " + actName + " to " + newView);
            viewsPage.InputIntoNameField(newView);
            viewsPage.WaitUntilSaveButtonWillBeEnable();

            logger.log(LogStatus.INFO, "Check that Cancel button enabled");
            Assert.assertTrue(viewsPage.CancelButtonIsEnabled());

            logger.log(LogStatus.INFO, "Press Cancel button");
            viewsPage.PressCancelButton();

            logger.log(LogStatus.INFO, "Check that name isn't changed after refresh");
            String viewNameSaved = viewsPage.GetViewName();
            Assert.assertEquals(viewNameSaved, actName);

            logger.log(LogStatus.INFO, "Go to Group Hierarchy page");
            groupsHierarchyPage.ClickOnGroupsHierarchyButton();

            logger.log(LogStatus.INFO, "Check that Unsaved changes window doesn't appear");
            Assert.assertFalse(viewsPage.CheckThatModalIsOpen());
        }
    }

    @Test
    public void ChangeNameExitAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeNameExitAndPressCancelTest");

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
        if(viewName!=null) {
            String actName = viewsPage.GetViewName();
            String newView = "new view name" + viewsPage.GetRandomDigit(0, 100000);

            logger.log(LogStatus.INFO, "Change view name from " + actName + " to " + newView);
            viewsPage.InputIntoNameField(newView);

            logger.log(LogStatus.INFO, "Go to Group Hierarchy page");
            groupsHierarchyPage.ClickOnGroupsHierarchyButton();

            logger.log(LogStatus.INFO, "Check that Unsaved changes window appear");
            Assert.assertTrue(viewsPage.CheckThatModalIsOpen());

            logger.log(LogStatus.INFO, "Click on Cancel button in Unsaved changed window");
            viewsPage.CancelUnsavedChanges();

            logger.log(LogStatus.INFO, "Check that change name is staied");
            String viewNameSaved = viewsPage.GetViewName();
            Assert.assertEquals(viewNameSaved, newView);

            logger.log(LogStatus.INFO, " Check that Save button is enabled");
            Assert.assertTrue(viewsPage.SaveButtonIsEnabled());

            logger.log(LogStatus.INFO, " Check that Cancel button is enabled");
            Assert.assertTrue(viewsPage.CancelButtonIsEnabled());
        }
    }

    @Test
    public void ChangeNameExitAndPressDoNotSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeNameExitAndPressDoNotSaveTest");

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
        if(viewName!=null) {
            String actName = viewsPage.GetViewName();
            String newView = "new view name" + viewsPage.GetRandomDigit(0, 100000);

            logger.log(LogStatus.INFO, "Change view name from " + actName + " to " + newView);
            viewsPage.InputIntoNameField(newView);

            logger.log(LogStatus.INFO, "Go to Group Hierarchy page");
            groupsHierarchyPage.ClickOnGroupsHierarchyButton();

            logger.log(LogStatus.INFO, "Check that Unsaved changes window appear");
            Assert.assertTrue(viewsPage.CheckThatModalIsOpen());

            logger.log(LogStatus.INFO, "Click on Do not Save button in Unsaved changed window");
            viewsPage.PressDontSaveUnsavedChanges();

            logger.log(LogStatus.INFO, "Go to Views page");
            viewsPage.ClickOnViewsButton();

            logger.log(LogStatus.INFO, "Click on view " + actName);
            viewsPage.ClickOnViewByName(actName);

            logger.log(LogStatus.INFO, "Check that change name isn't saved");
            String viewNameSaved = viewsPage.GetViewName();
            Assert.assertEquals(viewNameSaved, actName);
        }
    }

    @Test
    public void ChangeNameExitAndPressSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeNameExitAndPressSaveTest");

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
        if(viewName!=null) {
            String actName = viewsPage.GetViewName();
            String newView = "new view name" + viewsPage.GetRandomDigit(0, 100000);

            logger.log(LogStatus.INFO, "Change view name from " + actName + " to " + newView);
            viewsPage.InputIntoNameField(newView);

            logger.log(LogStatus.INFO, "Go to Group Hierarchy page");
            groupsHierarchyPage.ClickOnGroupsHierarchyButton();

            logger.log(LogStatus.INFO, "Check that Unsaved changes window appear");
            Assert.assertTrue(viewsPage.CheckThatModalIsOpen());

            logger.log(LogStatus.INFO, "Click on Save button in Unsaved changed window");
            viewsPage.PressSaveUnsavedChanges();

            logger.log(LogStatus.INFO, "Go to Views page");
            viewsPage.ClickOnViewsButton();

            logger.log(LogStatus.INFO, "Click on view " + newView);
            viewsPage.ClickOnViewByName(newView);

            logger.log(LogStatus.INFO, "Check that change name is saved");
            String viewNameSaved = viewsPage.GetViewName();
            Assert.assertEquals(viewNameSaved, newView);
        }
    }

    @Test
    public void ChangeRemarksAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeRemarksAndPressCancelTest");

        int viewsSize = viewsPage.GetViewsSize();
        Thread.sleep(1000);
        String viewN=null;
        int fullViewSize=0;

        for(int i=0; i<viewsSize; i++){
            int index = viewsPage.GetRandomDigit(0, viewsSize);
            String name = viewsPage.GetViewNameByIndex(index);

            logger.log(LogStatus.INFO,"Click on view "+name);
            viewsPage.ClickOnViewByIndex(index);

            fullViewSize = viewsPage.GetFullViewsSize();
            if(fullViewSize>0){
                viewN=name;
                break;
            }
        }
        if(viewN!=null) {
            String viewName = viewsPage.GetViewName();
            String actRemarks = viewsPage.GetRemarksText();
            String newRemarks = "newRemarks" + viewsPage.GetRandomDigit(0, 10000) + "For " + viewName;

            logger.log(LogStatus.INFO, "Change view remarks from " + actRemarks + " to " + newRemarks);
            viewsPage.InputIntoRemarksField(newRemarks);
            viewsPage.WaitUntilSaveButtonWillBeEnable();

            logger.log(LogStatus.INFO, "Check that Cancel button enabled");
            Assert.assertTrue(viewsPage.CancelButtonIsEnabled());

            logger.log(LogStatus.INFO, "Press Cancel button");
            viewsPage.PressCancelButton();

            logger.log(LogStatus.INFO, "Check that remarks isn't changed ");
            String remarksSaved = viewsPage.GetRemarksText();
            Assert.assertEquals(remarksSaved, actRemarks);

            logger.log(LogStatus.INFO, "Go to Group Hierarchy page");
            groupsHierarchyPage.ClickOnGroupsHierarchyButton();

            logger.log(LogStatus.INFO, "Check that Unsaved changes window doesn't appear");
            Assert.assertFalse(viewsPage.CheckThatModalIsOpen());
        }
    }

    @Test
    public void ChangeRemarksExitAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeRemarksExitAndPressCancelTest");

        int viewsSize = viewsPage.GetViewsSize();
        Thread.sleep(1000);
        String viewN=null;
        int fullViewSize=0;

        for(int i=0; i<viewsSize; i++){
            int index = viewsPage.GetRandomDigit(0, viewsSize);
            String name = viewsPage.GetViewNameByIndex(index);

            logger.log(LogStatus.INFO,"Click on view "+name);
            viewsPage.ClickOnViewByIndex(index);

            fullViewSize = viewsPage.GetFullViewsSize();
            if(fullViewSize>0){
                viewN=name;
                break;
            }
        }
        if(viewN!=null) {
            String viewName = viewsPage.GetViewName();
            String actRemarks = viewsPage.GetRemarksText();
            String newRemarks = "newRemarks" + viewsPage.GetRandomDigit(0, 10000) + "For " + viewName;

            logger.log(LogStatus.INFO, "Change view remarks from " + actRemarks + " to " + newRemarks);
            viewsPage.InputIntoRemarksField(newRemarks);

            logger.log(LogStatus.INFO, "Go to Group Hierarchy page");
            groupsHierarchyPage.ClickOnGroupsHierarchyButton();

            logger.log(LogStatus.INFO, "Check that Unsaved changes window appear");
            Assert.assertTrue(viewsPage.CheckThatModalIsOpen());

            logger.log(LogStatus.INFO, "Click on Cancel button in Unsaved changed window");
            viewsPage.CancelUnsavedChanges();

            logger.log(LogStatus.INFO, "Check that remarks change is remained");
            String remarksSaved = viewsPage.GetRemarksText();
            Assert.assertEquals(remarksSaved, newRemarks);

            logger.log(LogStatus.INFO, " Check that Save button is enabled");
            Assert.assertTrue(viewsPage.SaveButtonIsEnabled());

            logger.log(LogStatus.INFO, " Check that Cancel button is enabled");
            Assert.assertTrue(viewsPage.CancelButtonIsEnabled());
        }
    }

    @Test
    public void ChangeRemarksExitAndPressDoNotSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeRemarksExitAndPressDoNotSaveTest");

        int viewsSize = viewsPage.GetViewsSize();
        Thread.sleep(1000);
        String viewN=null;
        int fullViewSize=0;

        for(int i=0; i<viewsSize; i++){
            int index = viewsPage.GetRandomDigit(0, viewsSize);
            String name = viewsPage.GetViewNameByIndex(index);

            logger.log(LogStatus.INFO,"Click on view "+name);
            viewsPage.ClickOnViewByIndex(index);

            fullViewSize = viewsPage.GetFullViewsSize();
            if(fullViewSize>0){
                viewN=name;
                break;
            }
        }
        if(viewN!=null) {
            String viewName = viewsPage.GetViewName();
            String actRemarks = viewsPage.GetRemarksText();
            String newRemarks = "newRemarks" + viewsPage.GetRandomDigit(0, 10000) + "For " + viewName;

            logger.log(LogStatus.INFO, "Change view remarks from " + actRemarks + " to " + newRemarks);
            viewsPage.InputIntoRemarksField(newRemarks);
            viewsPage.WaitUntilSaveButtonWillBeEnable();

            logger.log(LogStatus.INFO, "Go to Group Hierarchy page");
            groupsHierarchyPage.ClickOnGroupsHierarchyButton();

            logger.log(LogStatus.INFO, "Check that Unsaved changes window appear");
            Assert.assertTrue(viewsPage.CheckThatModalIsOpen());

            logger.log(LogStatus.INFO, "Click on Do not Save button in Unsaved changed window");
            viewsPage.PressDontSaveUnsavedChanges();

            logger.log(LogStatus.INFO, "Go to Views page");
            viewsPage.ClickOnViewsButton();

            logger.log(LogStatus.INFO, "Click on view " + viewName);
            viewsPage.ClickOnViewByName(viewName);

            logger.log(LogStatus.INFO, "Check that remarks change isn't saved");
            String remarksSaved = viewsPage.GetRemarksText();
            Assert.assertEquals(remarksSaved, actRemarks);
        }
    }

    @Test
    public void ChangeRemarksExitAndPressSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeRemarksExitAndPressSaveTest");

        int viewsSize = viewsPage.GetViewsSize();
        Thread.sleep(1000);
        String viewN=null;
        int fullViewSize=0;

        for(int i=0; i<viewsSize; i++){
            int index = viewsPage.GetRandomDigit(0, viewsSize);
            String name = viewsPage.GetViewNameByIndex(index);

            logger.log(LogStatus.INFO,"Click on view "+name);
            viewsPage.ClickOnViewByIndex(index);

            fullViewSize = viewsPage.GetFullViewsSize();
            if(fullViewSize>0){
                viewN=name;
                break;
            }
        }
        if(viewN!=null) {
            String viewName = viewsPage.GetViewName();
            String actRemarks = viewsPage.GetRemarksText();
            String newRemarks = "newRemarks" + viewsPage.GetRandomDigit(0, 10000) + "For " + viewName;

            logger.log(LogStatus.INFO, "Change view remarks from " + actRemarks + " to " + newRemarks);
            viewsPage.InputIntoRemarksField(newRemarks);

            logger.log(LogStatus.INFO, "Go to Group Hierarchy page");
            groupsHierarchyPage.ClickOnGroupsHierarchyButton();

            logger.log(LogStatus.INFO, "Check that Unsaved changes window appear");
            Assert.assertTrue(viewsPage.CheckThatModalIsOpen());

            logger.log(LogStatus.INFO, "Click on Save button in Unsaved changed window");
            viewsPage.PressSaveUnsavedChanges();

            logger.log(LogStatus.INFO, "Go to Views page");
            viewsPage.ClickOnViewsButton();

            logger.log(LogStatus.INFO, "Click on view " + viewName);
            viewsPage.ClickOnViewByName(viewName);

            logger.log(LogStatus.INFO, "Check that remarks change is saved");
            String remarksSaved = viewsPage.GetRemarksText();
            Assert.assertEquals(remarksSaved, newRemarks);
        }
    }

    @Test
    public void ChangeVisibilityStatusAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeVisibilityStatusAndPressCancelTest");

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
        if(viewName!=null) {
            boolean visibilityStatus = viewsPage.VisibilityToggleIsOn();

            logger.log(LogStatus.INFO, "Click on visibility status for view " + viewName);
            viewsPage.ClickOnVisibilityToggleSwitch();
            viewsPage.WaitUntilSaveButtonWillBeEnable();

            logger.log(LogStatus.INFO, "Check that Cancel button enabled");
            Assert.assertTrue(viewsPage.CancelButtonIsEnabled());

            logger.log(LogStatus.INFO, "Press Cancel button");
            viewsPage.PressCancelButton();

            logger.log(LogStatus.INFO, "Check that visibility status isn't changed");
            boolean visibilityStatusAct = viewsPage.VisibilityToggleIsOn();
            Assert.assertEquals(visibilityStatusAct, visibilityStatus);

            logger.log(LogStatus.INFO, "Go to Group Hierarchy page");
            groupsHierarchyPage.ClickOnGroupsHierarchyButton();

            logger.log(LogStatus.INFO, "Check that Unsaved changes window doesn't appear");
            Assert.assertFalse(viewsPage.CheckThatModalIsOpen());
        }
    }

    @Test
    public void ChangeVisibilityStatusExitAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeVisibilityStatusExitAndPressCancelTest");

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
        if(viewName!=null) {
            boolean visibilityStatus = viewsPage.VisibilityToggleIsOn();

            logger.log(LogStatus.INFO, "Click on visibility status for view " + viewName);
            viewsPage.ClickOnVisibilityToggleSwitch();

            logger.log(LogStatus.INFO, "Go to Group Hierarchy page");
            groupsHierarchyPage.ClickOnGroupsHierarchyButton();

            logger.log(LogStatus.INFO, "Check that Unsaved changes window appear");
            Assert.assertTrue(viewsPage.CheckThatModalIsOpen());

            logger.log(LogStatus.INFO, "Click on Cancel button in Unsaved changed window");
            viewsPage.CancelUnsavedChanges();

            logger.log(LogStatus.INFO, "Check that change visibility status is saved");
            boolean visibilityStatusAct = viewsPage.VisibilityToggleIsOn();
            Assert.assertEquals(visibilityStatusAct, !visibilityStatus);

            logger.log(LogStatus.INFO, " Check that Save button is enabled");
            Assert.assertTrue(viewsPage.SaveButtonIsEnabled());

            logger.log(LogStatus.INFO, " Check that Cancel button is enabled");
            Assert.assertTrue(viewsPage.CancelButtonIsEnabled());
        }
    }

    @Test
    public void ChangeVisibilityStatusExitAndPressDoNotSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeVisibilityStatusExitAndPressDoNotSaveTest");
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
        if(viewName!=null) {
            boolean visibilityStatus = viewsPage.VisibilityToggleIsOn();

            logger.log(LogStatus.INFO, "Click on visibility status for view " + viewName);
            viewsPage.ClickOnVisibilityToggleSwitch();

            logger.log(LogStatus.INFO, "Go to Group Hierarchy page");
            groupsHierarchyPage.ClickOnGroupsHierarchyButton();

            logger.log(LogStatus.INFO, "Check that Unsaved changes window appear");
            Assert.assertTrue(viewsPage.CheckThatModalIsOpen());

            logger.log(LogStatus.INFO, "Click on Do not Save button in Unsaved changed window");
            viewsPage.PressDontSaveUnsavedChanges();

            logger.log(LogStatus.INFO, "Go to Views page");
            viewsPage.ClickOnViewsButton();

            logger.log(LogStatus.INFO, "Click on " + viewName + " view");
            viewsPage.ClickOnViewByName(viewName);
            Thread.sleep(1000);

            logger.log(LogStatus.INFO, "Check that visibility status isn't changed");
            boolean visibilityStatusAct = viewsPage.VisibilityToggleIsOn();
            Assert.assertEquals(visibilityStatusAct, visibilityStatus);
        }
    }

    @Test
    public void ChangeVisibilityStatusExitAndPressSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeVisibilityStatusExitAndPressSaveTest");

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
        if(viewName!=null) {
            boolean visibilityStatus = viewsPage.VisibilityToggleIsOn();

            logger.log(LogStatus.INFO, "Click on visibility status for view " + viewName);
            viewsPage.ClickOnVisibilityToggleSwitch();

            logger.log(LogStatus.INFO, "Go to Group Hierarchy page");
            groupsHierarchyPage.ClickOnGroupsHierarchyButton();

            logger.log(LogStatus.INFO, "Check that Unsaved changes window appear");
            Assert.assertTrue(viewsPage.CheckThatModalIsOpen());

            logger.log(LogStatus.INFO, "Click on Save button in Unsaved changed window");
            viewsPage.PressSaveUnsavedChanges();

            logger.log(LogStatus.INFO, "Go to Views page");
            viewsPage.ClickOnViewsButton();

            logger.log(LogStatus.INFO, "Click on " + viewName + " view");
            viewsPage.ClickOnViewByName(viewName);
            Thread.sleep(1000);

            logger.log(LogStatus.INFO, "Check that visibility status is changed");
            boolean visibilityStatusAct = viewsPage.VisibilityToggleIsOn();
            Assert.assertEquals(visibilityStatusAct, !visibilityStatus);
        }
    }

    @Test
    public void ClearViewAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("ClearViewAndPressCancelTest");

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
                Thread.sleep(1000);

                logger.log(LogStatus.INFO,"Check that Cancel button is enabled"); ///failed
                Assert.assertTrue(viewsPage.CancelButtonIsEnabled());

                logger.log(LogStatus.INFO,"Press Cancel button");
                viewsPage.PressCancelButton();

                logger.log(LogStatus.INFO,"Check that view isn't cleared");
                int fullViewSizeSaved = viewsPage.GetFullViewsSize();
                Assert.assertEquals(fullViewSizeSaved, fullViewSize);

                logger.log(LogStatus.INFO,"Refresh page ");
                viewsPage.Refresh();

                logger.log(LogStatus.INFO,"Click on "+viewName+" view");
                viewsPage.ClickOnViewByName(viewName);
                Thread.sleep(1000);

                logger.log(LogStatus.INFO,"Check that view isn't cleared after refresh");
                int fullViewSizeRefreshed = viewsPage.GetFullViewsSize();
                Assert.assertEquals(fullViewSizeRefreshed, fullViewSize);
            }
        }
    }

    @Test
    public void ClearViewExitAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("ClearViewExitAndPressCancelTest");

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
                viewsPage.WaitUntilSaveButtonWillBeEnable();

                logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
                groupsHierarchyPage.ClickOnGroupsHierarchyButton();
                groupsHierarchyPage.WaitUntilDialogIsLocated();

                logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
                Assert.assertTrue(viewsPage.CheckThatModalIsOpen());

                logger.log(LogStatus.INFO,"Check that Cancel Unsaved changes button is enabled");
                Assert.assertTrue(viewsPage.CancelUnsavedChangesButtonIsEnabled());

                logger.log(LogStatus.INFO,"Click on Cancel button in Unsaved changed window");
                viewsPage.CancelUnsavedChanges();

                logger.log(LogStatus.INFO,"Check that view is cleared");
                int fullViewSizeSaved = viewsPage.GetFullViewsSize();
                Assert.assertEquals(fullViewSizeSaved, 0);

                logger.log(LogStatus.INFO," Check that Save button is disabled");
                Assert.assertFalse(viewsPage.SaveButtonIsEnabled());

                logger.log(LogStatus.INFO," Check that Cancel button is enabled");
                Assert.assertTrue(viewsPage.CancelButtonIsEnabled());

                logger.log(LogStatus.INFO,"Press Cancel button");
                viewsPage.PressCancelButton();
            }
        }
    }

    @Test
    public void ClearViewExitAndPressDoNotSaveTest() throws InterruptedException {
        logger=report.startTest("ClearViewExitAndPressDoNotSaveTest");

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
                viewsPage.WaitUntilSaveButtonWillBeEnable();

                logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
                groupsHierarchyPage.ClickOnGroupsHierarchyButton();
                groupsHierarchyPage.WaitUntilDialogIsLocated();

                logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
                Assert.assertTrue(viewsPage.CheckThatModalIsOpen());

                logger.log(LogStatus.INFO,"Check that Do Not Save Unsaved changes button is enabled");
                Assert.assertTrue(viewsPage.DoNotSaveUnsavedChangesButtonIsEnabled());

                logger.log(LogStatus.INFO,"Click on Do not Save button in Unsaved changed window");
                viewsPage.PressDontSaveUnsavedChanges();

                logger.log(LogStatus.INFO,"Go to Views page");
                viewsPage.ClickOnViewsButton();

                logger.log(LogStatus.INFO,"Click on "+viewName+" view");
                viewsPage.ClickOnViewByName(viewName);
                Thread.sleep(1000);

                logger.log(LogStatus.INFO,"Check that view isn't cleared");
                int fullViewSizeSaved = viewsPage.GetFullViewsSize();
                Assert.assertEquals(fullViewSizeSaved, fullViewSize);
            }
        }
    }

    @Test//9477  Save button is enabled after pressing Clear view button
    public void ClearViewExitAndPressSaveTest() throws InterruptedException {
        logger=report.startTest("ClearViewExitAndPressSaveTest");

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
                viewsPage.WaitUntilSaveButtonWillBeEnable();

                logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
                groupsHierarchyPage.ClickOnGroupsHierarchyButton();
                groupsHierarchyPage.WaitUntilDialogIsLocated();

                logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
                Assert.assertTrue(viewsPage.CheckThatModalIsOpen());

                logger.log(LogStatus.INFO,"Check that Save Unsaved changes button is disabled");
                Assert.assertFalse(viewsPage.SaveUnsavedChangesButtonIsEnabled());
            }
        }
    }

    @Test
    public void ChangeLayoutAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeLayoutAndPressCancelTest");

        int viewsSize = viewsPage.GetViewsSize();
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
            int paneCountAct= viewsPage.GetPaneViewListSize();
            logger.log(LogStatus.INFO,"There are "+paneCountAct+" panes");

            logger.log(LogStatus.INFO,"Click on Change Layout button");
            viewsPage.ClickOnChangeLayoutButton();

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
            viewsPage.WaitUntilSaveButtonWillBeEnable();

            logger.log(LogStatus.INFO,"Check that Cancel button enabled");
            Assert.assertTrue(viewsPage.CancelButtonIsEnabled());

            logger.log(LogStatus.INFO,"Press Cancel button");
            viewsPage.PressCancelButton();

            logger.log(LogStatus.INFO,"Check that layout isn't changed ");
            int paneViewSize = viewsPage.GetPaneViewListSize();
            Assert.assertEquals(paneViewSize, paneCountAct);

            logger.log(LogStatus.INFO,"Check that count of active panes isn't changes ");
            int newfullViewSize = viewsPage.GetFullViewsSize();
            Assert.assertEquals(newfullViewSize, fullViewSize);

            logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
            groupsHierarchyPage.ClickOnGroupsHierarchyButton();

            logger.log(LogStatus.INFO,"Check that Unsaved changes window doesn't appear");
            Assert.assertFalse(viewsPage.CheckThatModalIsOpen());
        }
    }

    @Test
    public void ChangeLayoutExitAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeLayoutExitAndPressCancelTest");

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
            int paneCountAct= viewsPage.GetPaneViewListSize();
            logger.log(LogStatus.INFO,"There are "+paneCountAct+" panes");

            logger.log(LogStatus.INFO,"Click on Change Layout button");
            viewsPage.ClickOnChangeLayoutButton();

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
            int paneCount= viewsPage.GetPanesSizeInLayoutInDialogByIndex(index);
            viewsPage.ClickOnLayoutInDialogByIndex(index);
            viewsPage.WaitUntilDialogIsNotLocated();

            logger.log(LogStatus.INFO,"Check that dialog is closed ");
            Assert.assertFalse(viewsPage.CheckThatModalIsOpen());

            logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
            groupsHierarchyPage.ClickOnGroupsHierarchyButton();

            logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
            Assert.assertTrue(viewsPage.CheckThatModalIsOpen());

            logger.log(LogStatus.INFO,"Click on Cancel button in Unsaved changed window");
            viewsPage.CancelUnsavedChanges();

            logger.log(LogStatus.INFO,"Check that change layout is remained ");
            int paneViewSize = viewsPage.GetPaneViewListSize();
            Assert.assertEquals(paneViewSize, paneCount);

            fullViewSize = viewsPage.GetFullViewsSize();
            if(fullViewSize>0){
                logger.log(LogStatus.INFO," Check that Save button is enabled");
                Assert.assertTrue(viewsPage.SaveButtonIsEnabled());
            }

            logger.log(LogStatus.INFO," Check that Cancel button is enabled");
            Assert.assertTrue(viewsPage.CancelButtonIsEnabled());

            logger.log(LogStatus.INFO,"Press Cancel button");
            viewsPage.PressCancelButton();
        }
    }

    @Test
    public void ChangeLayoutExitAndPressDontSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeLayoutExitAndPressDontSaveTest");

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
            int paneCountAct= viewsPage.GetPaneViewListSize();
            logger.log(LogStatus.INFO,"There are "+paneCountAct+" panes");

            logger.log(LogStatus.INFO,"Click on Change Layout button");
            viewsPage.ClickOnChangeLayoutButton();

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

            logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
            groupsHierarchyPage.ClickOnGroupsHierarchyButton();

            logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
            Assert.assertTrue(viewsPage.CheckThatModalIsOpen());

            logger.log(LogStatus.INFO,"Click on Do not Save button in Unsaved changed window");
            viewsPage.PressDontSaveUnsavedChanges();

            logger.log(LogStatus.INFO,"Go to Views page");
            viewsPage.ClickOnViewsButton();

            logger.log(LogStatus.INFO,"Click on "+viewName+" view");
            viewsPage.ClickOnViewByName(viewName);
            Thread.sleep(1000);

            logger.log(LogStatus.INFO,"Check that layout isn't changed ");
            int paneViewSize = viewsPage.GetPaneViewListSize();
            Assert.assertEquals(paneViewSize, paneCountAct);

            logger.log(LogStatus.INFO,"Check that count of active panes isn't changes ");
            int newfullViewSize = viewsPage.GetFullViewsSize();
            Assert.assertEquals(newfullViewSize, fullViewSize);
        }
    }

    @Test
    public void ChangeLayoutExitAndPressSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeLayoutExitAndPressSaveTest");

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
            int paneCountAct= viewsPage.GetPaneViewListSize();
            logger.log(LogStatus.INFO,"There are "+paneCountAct+" panes");

            logger.log(LogStatus.INFO,"Click on Change Layout button");
            viewsPage.ClickOnChangeLayoutButton();

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
//            int paneCount= viewsPage.GetPanesSizeInLayoutInDialogByIndex(index+1);
            int paneCount= viewsPage.GetPanesSizeInLayoutInDialogByIndex(index);
            viewsPage.ClickOnLayoutInDialogByIndex(index);
            viewsPage.WaitUntilDialogIsNotLocated();

            logger.log(LogStatus.INFO,"Check that dialog is closed ");
            Assert.assertFalse(viewsPage.CheckThatModalIsOpen());

            int fullPanes = viewsPage.GetFullViewsSize();
            if(fullPanes>0){
                logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
                groupsHierarchyPage.ClickOnGroupsHierarchyButton();

                logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
                Assert.assertTrue(viewsPage.CheckThatModalIsOpen());

                logger.log(LogStatus.INFO,"Click on Save button in Unsaved changed window");
                viewsPage.PressSaveUnsavedChanges();

                logger.log(LogStatus.INFO,"Go to Views page");
                viewsPage.ClickOnViewsButton();

                logger.log(LogStatus.INFO,"Click on "+viewName+" view");
                viewsPage.ClickOnViewByName(viewName);
                Thread.sleep(1000);

                logger.log(LogStatus.INFO,"Check that layout is changed ");
                int paneViewSize = viewsPage.GetPaneViewListSize();
                Assert.assertEquals(paneViewSize, paneCount);
            }
//            logger.log(LogStatus.INFO,"Check that count of active panes isn't changes ");
//            int newfullViewSize = viewsPage.GetFullViewsSize();
//            if(fullViewSize<=paneViewSize) Assert.assertEquals(newfullViewSize, fullViewSize);
//            if(fullViewSize>paneViewSize) Assert.assertEquals(newfullViewSize, paneViewSize);
        }
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



