package testcases;

import pageObjects.GroupsHierarchyPage;
import pageObjects.ToursPage;
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

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class WebPagesElementsTest {
    public WebDriver driver;
    public MonitoringPage monitoringPage;
    public WebPagesPage webPagesPage;
    public GroupsHierarchyPage groupsHierarchyPage;
    public ToursPage tourspage;
    public String[] Servers;
    ExtentReports report;
    ExtentTest logger;

    @BeforeClass(alwaysRun = true)
    public void setup() throws IOException, InterruptedException {
        
    }

    @BeforeTest
    public void startReport(){
        report=new ExtentReports(System.getProperty("user.dir") +"/test-output/WebPages/WebPagesElementsReport.html", true);
    }

    @BeforeMethod(alwaysRun = true)
    public void GoToGroupsHierarchyPage() throws InterruptedException, IOException {
    	
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
    	
    	
        driver.navigate().to("http://" + Servers[0]);
        try{
            driver.switchTo().alert().accept();
        }catch(Exception a){}

        webPagesPage.WaitUntilLoadingBlockAppears();
        webPagesPage.WaitUntilLoadingBlockDisappears();
        webPagesPage.GoToWebPagesPage();
      	   	
    
    	
       
    }

    @Test (enabled =false) 
    public void AddNewWebPageAndCancelTest() throws InterruptedException {
        logger=report.startTest("AddNewWebPageAndCancelTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        if(webPageSize>0){
            logger.log(LogStatus.INFO,"Click on new button");
            webPagesPage.ClickOnNewButton();

            logger.log(LogStatus.INFO,"Check that Cancel button is enabled"); ///failed
            Assert.assertTrue(webPagesPage.CancelButtonIsEnabled());

            logger.log(LogStatus.INFO,"Click on Cancel button");
            webPagesPage.PressCancelButton();

            logger.log(LogStatus.INFO,"Check that added web page isn't saved");
            int webPageSizeAct = webPagesPage.GetWebPagesSize();
            Assert.assertEquals(webPageSizeAct , webPageSize);
        }
    }

    @Test  (enabled =false) 
    public void AddNewWebPageExitAndCancelTest() throws InterruptedException {
        logger=report.startTest("AddNewWebPageExitAndCancelTest");

        int webPageSize = webPagesPage.GetWebPagesSize();

        logger.log(LogStatus.INFO,"Click on new button");
        webPagesPage.ClickOnNewButton();

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(webPagesPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that Cancel Unsaved changes button is enabled");
        Assert.assertTrue(webPagesPage.CancelUnsavedChangesButtonIsEnabled());

        logger.log(LogStatus.INFO,"Click on Cancel button in Unsaved changed window");
        webPagesPage.CancelUnsavedChanges();

        logger.log(LogStatus.INFO,"Check that added web page is staied");
        int webPageSizeAct = webPagesPage.GetWebPagesSize();
        Assert.assertEquals(webPageSizeAct , webPageSize+1);
    }

    @Test  (enabled =false) 
    public void AddNewWebPageExitAndDoNotSaveTest() throws InterruptedException {
        logger=report.startTest("AddNewWebPageExitAndDoNotSaveTest");

        int webPageSize = webPagesPage.GetWebPagesSize();

        logger.log(LogStatus.INFO,"Click on new button");
        webPagesPage.ClickOnNewButton();

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(webPagesPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that Do Not Save Unsaved changes button is enabled");
        Assert.assertTrue(webPagesPage.DoNotSaveUnsavedChangesButtonIsEnabled());

        logger.log(LogStatus.INFO,"Click on Do not save button in Unsaved changed window");
        webPagesPage.PressDontSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to Web Pages page");
        webPagesPage.ClickOnWebPagesButton();

        logger.log(LogStatus.INFO,"Check that added web page isn't saved");
        int webPageSizeAct = webPagesPage.GetWebPagesSize();
        Assert.assertEquals(webPageSizeAct , webPageSize);
    }

    @Test  
    public void AddNewWebPageExitAndSaveTest() throws InterruptedException {
        logger=report.startTest("AddNewWebPageExitAndSaveTest");

        int webPageSize = webPagesPage.GetWebPagesSize();

        logger.log(LogStatus.INFO,"Click on new button");
        webPagesPage.ClickOnNewButton();

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton1();
        

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(webPagesPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that Save Unsaved changes button is disabled");
        Assert.assertFalse(webPagesPage.SaveUnsavedChangesButtonIsEnabled());
    }

    @Test(enabled =false) 
    public void AddNewWebPageInputURLAndCancelTest() throws InterruptedException {
        logger=report.startTest("AddNewWebPageInputURLAndCancelTest");

        int webPageSize = webPagesPage.GetWebPagesSize();

        logger.log(LogStatus.INFO,"Click on new button");
        webPagesPage.ClickOnNewButton();

        logger.log(LogStatus.INFO,"Input into URL field");
        String url = "http://tut.by";
        webPagesPage.InputIntoURLField(url);

        logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
        Assert.assertTrue(webPagesPage.CancelButtonIsEnabled());

        logger.log(LogStatus.INFO,"Press Cancel button");
        Thread.sleep(1000);
        webPagesPage.PressCancelButton();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that web page isn't added");
        int webPageSizeAct = webPagesPage.GetWebPagesSize();
        Assert.assertEquals(webPageSizeAct, webPageSize);

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window doesn't appear");
        Assert.assertFalse(groupsHierarchyPage.CheckThatModalIsOpen());
    }

    @Test(enabled =false) 
    public void AddNewWebPageInputURLExitAndCancelTest() throws InterruptedException {
        logger=report.startTest("AddNewWebPageInputURLExitAndCancelTest");

        int webPageSize = webPagesPage.GetWebPagesSize();

        logger.log(LogStatus.INFO,"Click on new button");
        webPagesPage.ClickOnNewButton();

        logger.log(LogStatus.INFO,"Input into URL field");
        String url = "http://tut.by";
        webPagesPage.InputIntoURLField(url);

        logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
        Assert.assertTrue(webPagesPage.CancelButtonIsEnabled());

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(webPagesPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that Cancel Unsaved changes button is enabled");
        Assert.assertTrue(webPagesPage.CancelUnsavedChangesButtonIsEnabled());

        logger.log(LogStatus.INFO,"Click on Cancel button in Unsaved changed window");
        webPagesPage.CancelUnsavedChanges();

        logger.log(LogStatus.INFO,"Check that added web page is staied");
        int webPageSizeAct = webPagesPage.GetWebPagesSize();
        Assert.assertEquals(webPageSizeAct , webPageSize+1);

        logger.log(LogStatus.INFO," Check that Save button is enabled");
        Assert.assertTrue(webPagesPage.SaveButtonIsEnabled());

        logger.log(LogStatus.INFO," Check that Cancel button is enabled");
        Assert.assertTrue(webPagesPage.CancelButtonIsEnabled());
    }

    @Test(enabled =false) 
    public void AddNewWebPageInputURLExitAndDoNotSaveTest() throws InterruptedException {
        logger=report.startTest("AddNewWebPageInputURLExitAndDoNotSaveTest");

        int webPageSize = webPagesPage.GetWebPagesSize();

        logger.log(LogStatus.INFO,"Click on new button");
        webPagesPage.ClickOnNewButton();

        logger.log(LogStatus.INFO,"Input into URL field");
        String url = "http://tut.by";
        webPagesPage.InputIntoURLField(url);

        logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
        Assert.assertTrue(webPagesPage.CancelButtonIsEnabled());

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(webPagesPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Click on Do not Save button in Unsaved changed window");
        webPagesPage.PressDontSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to Web Pages page");
        webPagesPage.ClickOnWebPagesButton();

        logger.log(LogStatus.INFO,"Check that added web page isn't saved");
        int webPageSizeAct = webPagesPage.GetWebPagesSize();
        Assert.assertEquals(webPageSizeAct , webPageSize);
    }

    @Test(enabled =false) 
    public void AddNewWebPageInputURLExitAndSaveTest() throws InterruptedException {
        logger=report.startTest("AddNewWebPageInputURLExitAndSaveTest");

        int webPageSize = webPagesPage.GetWebPagesSize();

        logger.log(LogStatus.INFO,"Click on new button");
        webPagesPage.ClickOnNewButton();

        logger.log(LogStatus.INFO,"Input into URL field");
        String url = "http://tut.by";
        webPagesPage.InputIntoURLField(url);

        logger.log(LogStatus.INFO,"Check that Cancel button is enabled");
        Assert.assertTrue(webPagesPage.CancelButtonIsEnabled());

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(webPagesPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Click on Save button in Unsaved changed window");
        webPagesPage.PressSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to Web Pages page");
        webPagesPage.ClickOnWebPagesButton();

        logger.log(LogStatus.INFO,"Check that added web page is saved");
        int webPageSizeAct = webPagesPage.GetWebPagesSize();
        Assert.assertEquals(webPageSizeAct , webPageSize+1);
    }

    @Test(enabled =false) 
    public void ChangeNameAndCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeNameAndCancelTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        int index = webPagesPage.GetRandomDigit(0, webPageSize);
        String name = webPagesPage.GetWebPageNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on web page "+name);
        webPagesPage.ClickOnWebPageByIndex(index);

        String newName = "NewName"+webPagesPage.GetRandomDigit(0, 9000);
        webPagesPage.InputIntoNameField(newName);

        logger.log(LogStatus.INFO,"Press Cancel button");
        webPagesPage.PressCancelButton();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that name isn't saved");
        String nameAct = webPagesPage.GetWebPageName();
        Assert.assertEquals(nameAct, name);

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window doesn't appear");
        Assert.assertFalse(groupsHierarchyPage.CheckThatModalIsOpen());
    }

    @Test(enabled =false) 
    public void ChangeNameExitAndCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeNameExitAndCancelTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        int index = webPagesPage.GetRandomDigit(0, webPageSize);
        String name = webPagesPage.GetWebPageNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on web page "+name);
        webPagesPage.ClickOnWebPageByIndex(index);

        String newName = "NewName"+webPagesPage.GetRandomDigit(0, 9000);
        webPagesPage.InputIntoNameField(newName);

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(webPagesPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that Cancel Unsaved changes button is enabled");
        Assert.assertTrue(webPagesPage.CancelUnsavedChangesButtonIsEnabled());

        logger.log(LogStatus.INFO,"Click on Cancel button in Unsaved changed window");
        webPagesPage.CancelUnsavedChanges();

        logger.log(LogStatus.INFO,"Check that name change is staied");
        String nameAct = webPagesPage.GetWebPageName();
        Assert.assertEquals(nameAct, newName);

        logger.log(LogStatus.INFO," Check that Save button is enabled");
        Assert.assertTrue(webPagesPage.SaveButtonIsEnabled());

        logger.log(LogStatus.INFO," Check that Cancel button is enabled");
        Assert.assertTrue(webPagesPage.CancelButtonIsEnabled());
    }

    @Test(enabled =false) 
    public void ChangeNameExitAndDoNotSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeNameExitAndDoNotSaveTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        int index = webPagesPage.GetRandomDigit(0, webPageSize);
        String name = webPagesPage.GetWebPageNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on web page "+name);
        webPagesPage.ClickOnWebPageByIndex(index);

        String newName = "NewName"+webPagesPage.GetRandomDigit(0, 9000);
        webPagesPage.InputIntoNameField(newName);

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(webPagesPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Click on Do not Save button in Unsaved changed window");
        webPagesPage.PressDontSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to Web Pages page");
        webPagesPage.ClickOnWebPagesButton();

        logger.log(LogStatus.INFO,"Click on "+name+" web page");
        webPagesPage.ClickOnWebPageByName(name);
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that name change isn't saved");
        String nameAct = webPagesPage.GetWebPageName();
        Assert.assertEquals(nameAct, name);
    }

    @Test(enabled =false) 
    public void ChangeNameExitAndSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeNameExitAndSaveTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        int index = webPagesPage.GetRandomDigit(0, webPageSize);
        String name = webPagesPage.GetWebPageNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on web page "+name);
        webPagesPage.ClickOnWebPageByIndex(index);

        String newName = "NewName"+webPagesPage.GetRandomDigit(0, 9000);
        webPagesPage.InputIntoNameField(newName);

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(webPagesPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Click on Save button in Unsaved changed window");
        webPagesPage.PressSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to Web Pages page");
        webPagesPage.ClickOnWebPagesButton();

        logger.log(LogStatus.INFO,"Click on "+newName+" web page");
        webPagesPage.ClickOnWebPageByName(newName);
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that name change is saved");
        String nameAct = webPagesPage.GetWebPageName();
        Assert.assertEquals(nameAct, newName);
    }

    @Test(enabled =false) 
    public void ChangeRemarksAndCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeRemarksAndCancelTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        int index = webPagesPage.GetRandomDigit(0, webPageSize);
        String name = webPagesPage.GetWebPageNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on web page "+name);
        webPagesPage.ClickOnWebPageByIndex(index);

        String remarks = webPagesPage.GetWebPageRemarks();
        String newRemarks = "NewRemarks for web page "+name;
        webPagesPage.InputIntoRemarksField(newRemarks);

        logger.log(LogStatus.INFO,"Press Cancel button");
        webPagesPage.PressCancelButton();

        logger.log(LogStatus.INFO,"Check that remarks isn't saved");
        String remarksAct = webPagesPage.GetWebPageRemarks();
        Assert.assertEquals(remarksAct, remarks);

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window doesn't appear");
        Assert.assertFalse(groupsHierarchyPage.CheckThatModalIsOpen());
    }

    @Test(enabled =false) 
    public void ChangeRemarksExitAndCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeRemarksExitAndCancelTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        int index = webPagesPage.GetRandomDigit(0, webPageSize);
        String name = webPagesPage.GetWebPageNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on web page "+name);
        webPagesPage.ClickOnWebPageByIndex(index);

        String remarks = webPagesPage.GetWebPageRemarks();
        String newRemarks = "NewRemarks for web page "+name;
        webPagesPage.InputIntoRemarksField(newRemarks);

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(webPagesPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that Cancel Unsaved changes button is enabled");
        Assert.assertTrue(webPagesPage.CancelUnsavedChangesButtonIsEnabled());

        logger.log(LogStatus.INFO,"Click on Cancel button in Unsaved changed window");
        webPagesPage.CancelUnsavedChanges();

        logger.log(LogStatus.INFO,"Check that new remarks is staied");
        String remarksAct = webPagesPage.GetWebPageRemarks();
        Assert.assertEquals(remarksAct, newRemarks);

        logger.log(LogStatus.INFO," Check that Save button is enabled");
        Assert.assertTrue(webPagesPage.SaveButtonIsEnabled());

        logger.log(LogStatus.INFO," Check that Cancel button is enabled");
        Assert.assertTrue(webPagesPage.CancelButtonIsEnabled());
    }

    @Test(enabled =false) 
    public void ChangeRemarksExitAndDoNotSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeRemarksExitAndDoNotSaveTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        int index = webPagesPage.GetRandomDigit(0, webPageSize);
        String name = webPagesPage.GetWebPageNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on web page "+name);
        webPagesPage.ClickOnWebPageByIndex(index);

        String newName = "NewNameFor"+name+webPagesPage.GetRandomDigit(0, 99999);
        logger.log(LogStatus.INFO,"Input" +name+" into name field");
        webPagesPage.InputIntoNameField(newName);

        logger.log(LogStatus.INFO,"Press Save button");
        webPagesPage.PressSaveButton();

        String remarks = webPagesPage.GetWebPageRemarks();
        String newRemarks = "NewRemarks for web page "+name;
        webPagesPage.InputIntoRemarksField(newRemarks);

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(webPagesPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Click on Do not Save button in Unsaved changed window");
        webPagesPage.PressDontSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to Web Pages page");
        webPagesPage.ClickOnWebPagesButton();

        logger.log(LogStatus.INFO,"Click on "+newName+" web page");
        webPagesPage.ClickOnWebPageByName(newName);
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that remarks isn't changed");
        String remarksAct = webPagesPage.GetWebPageRemarks();
        Assert.assertEquals(remarksAct, remarks);
    }

    @Test(enabled =false) 
    public void ChangeRemarksExitAndSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeRemarksExitAndSaveTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        int index = webPagesPage.GetRandomDigit(0, webPageSize);
        String name = webPagesPage.GetWebPageNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on web page "+name);
        webPagesPage.ClickOnWebPageByIndex(index);

        String newName = "NewNameFor"+webPagesPage.GetRandomDigit(0, 99999);
        logger.log(LogStatus.INFO,"Input" +name+" into name field");
        webPagesPage.InputIntoNameField(newName);

        String remarks = webPagesPage.GetWebPageRemarks();
        String newRemarks = "NewRemarks for web page "+name;
        logger.log(LogStatus.INFO,"Change remarks to "+newRemarks);
        webPagesPage.InputIntoRemarksField(newRemarks);
        webPagesPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(webPagesPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Click on Save button in Unsaved changed window");
        webPagesPage.PressSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to Web Pages page");
        webPagesPage.ClickOnWebPagesButton();

        logger.log(LogStatus.INFO,"Click on "+newName+" web page");
        webPagesPage.ClickOnWebPageByName(newName);
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that remarks is changed");
        String remarksAct = webPagesPage.GetWebPageRemarks();
        Assert.assertEquals(remarksAct, newRemarks);
    }

    @Test(enabled =false) 
    public void ChangeVisibilityStatusAndCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeVisibilityStatusAndCancelTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        int index = webPagesPage.GetRandomDigit(0, webPageSize);
        String name = webPagesPage.GetWebPageNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on web page "+name);
        webPagesPage.ClickOnWebPageByIndex(index);

        boolean visibility = webPagesPage.VisibilityToggleIsOn();
        logger.log(LogStatus.INFO,"Click on visibility status");
        webPagesPage.ClickOnVisibilitySwitch();

        logger.log(LogStatus.INFO,"Press Cancel button");
        webPagesPage.PressCancelButton();

        logger.log(LogStatus.INFO,"Check that visibility status change is canceled");
        boolean visibilityAct = webPagesPage.VisibilityToggleIsOn();
        Assert.assertEquals(visibilityAct, visibility);

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window doesn't appear");
        Assert.assertFalse(groupsHierarchyPage.CheckThatModalIsOpen());
    }

    @Test(enabled =false) 
    public void ChangeVisibilityStatusExitAndCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeVisibilityStatusExitAndCancelTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        int index = webPagesPage.GetRandomDigit(0, webPageSize);
        String name = webPagesPage.GetWebPageNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on web page "+name);
        webPagesPage.ClickOnWebPageByIndex(index);

        boolean visibility = webPagesPage.VisibilityToggleIsOn();
        logger.log(LogStatus.INFO,"Click on visibility status");
        webPagesPage.ClickOnVisibilitySwitch();

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(webPagesPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that Cancel Unsaved changes button is enabled");
        Assert.assertTrue(webPagesPage.CancelUnsavedChangesButtonIsEnabled());

        logger.log(LogStatus.INFO,"Click on Cancel button in Unsaved changed window");
        webPagesPage.CancelUnsavedChanges();

        logger.log(LogStatus.INFO,"Check that visibility status change is staied");
        boolean visibilityAct = webPagesPage.VisibilityToggleIsOn();
        Assert.assertEquals(visibilityAct, !visibility);

        logger.log(LogStatus.INFO," Check that Save button is enabled");
        Assert.assertTrue(webPagesPage.SaveButtonIsEnabled());

        logger.log(LogStatus.INFO," Check that Cancel button is enabled");
        Assert.assertTrue(webPagesPage.CancelButtonIsEnabled());
    }

    @Test(enabled =false) 
    public void ChangeVisibilityStatusExitAndDoNotSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeVisibilityStatusExitAndDoNotSaveTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        int index = webPagesPage.GetRandomDigit(0, webPageSize);
        String name = webPagesPage.GetWebPageNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on web page "+name);
        webPagesPage.ClickOnWebPageByIndex(index);

        String newName = "NewName"+webPagesPage.GetRandomDigit(0, 99999);
        logger.log(LogStatus.INFO,"Input" +name+" into name field");
        webPagesPage.InputIntoNameField(newName);

        logger.log(LogStatus.INFO,"Press Save button");
        webPagesPage.PressSaveButton();

        boolean visibility = webPagesPage.VisibilityToggleIsOn();
        logger.log(LogStatus.INFO,"Click on visibility status");
        webPagesPage.ClickOnVisibilitySwitch();

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(webPagesPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Click on Do not Save button in Unsaved changed window");
        webPagesPage.PressDontSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to Web Pages page");
        webPagesPage.ClickOnWebPagesButton();

        logger.log(LogStatus.INFO,"Click on "+newName+" web page");
        webPagesPage.ClickOnWebPageByName(newName);
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that visibility status change isn't saved");
        boolean visibilityAct = webPagesPage.VisibilityToggleIsOn();
        Assert.assertEquals(visibilityAct, visibility);
    }

    @Test(enabled =false) 
    public void ChangeVisibilityStatusExitAndSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeVisibilityStatusExitAndSaveTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        int index = webPagesPage.GetRandomDigit(0, webPageSize);
        String name = webPagesPage.GetWebPageNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on web page "+name);
        webPagesPage.ClickOnWebPageByIndex(index);

        String newName = "NewNameFor"+name+webPagesPage.GetRandomDigit(0, 99999);
        logger.log(LogStatus.INFO,"Input" +name+" into name field");
        webPagesPage.InputIntoNameField(newName);

        boolean visibility = webPagesPage.VisibilityToggleIsOn();
        logger.log(LogStatus.INFO,"Click on visibility status");
        webPagesPage.ClickOnVisibilitySwitch();
        webPagesPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(webPagesPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Click onSave button in Unsaved changed window");
        webPagesPage.PressSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to Web Pages page");
        webPagesPage.ClickOnWebPagesButton();

        logger.log(LogStatus.INFO,"Click on "+newName+" web page");
        webPagesPage.ClickOnWebPageByName(newName);
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that visibility status change is saved");
        boolean visibilityAct = webPagesPage.VisibilityToggleIsOn();
        Assert.assertEquals(visibilityAct, !visibility);
    }

    @Test(enabled =false) 
    public void ChangeRefreshTimeStatusAndCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeRefreshTimeStatusAndCancelTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        int index = webPagesPage.GetRandomDigit(0, webPageSize);
        String name = webPagesPage.GetWebPageNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on web page "+name);
        webPagesPage.ClickOnWebPageByIndex(index);

        boolean refreshTime = webPagesPage.RefreshTimeToggleIsOn();
        logger.log(LogStatus.INFO,"Click on refresh time status");
        webPagesPage.ClickOnRefreshTimeSwitch();

        logger.log(LogStatus.INFO,"Press Cancel button");
        webPagesPage.PressCancelButton();

        logger.log(LogStatus.INFO,"Check that refresh time status change is canceled");
        boolean refreshTimeAct = webPagesPage.RefreshTimeToggleIsOn();
        Assert.assertEquals(refreshTimeAct, refreshTime);

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window doesn't appear");
        Assert.assertFalse(groupsHierarchyPage.CheckThatModalIsOpen());
    }

    @Test(enabled =false) 
    public void ChangeRefreshTimeStatusExitAndCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeRefreshTimeStatusExitAndCancelTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        int index = webPagesPage.GetRandomDigit(0, webPageSize);
        String name = webPagesPage.GetWebPageNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on web page "+name);
        webPagesPage.ClickOnWebPageByIndex(index);

        boolean refreshTime = webPagesPage.RefreshTimeToggleIsOn();
        logger.log(LogStatus.INFO,"Click on refresh time status");
        webPagesPage.ClickOnRefreshTimeSwitch();

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(webPagesPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that Cancel Unsaved changes button is enabled");
        Assert.assertTrue(webPagesPage.CancelUnsavedChangesButtonIsEnabled());

        logger.log(LogStatus.INFO,"Click on Cancel button in Unsaved changed window");
        webPagesPage.CancelUnsavedChanges();

        logger.log(LogStatus.INFO,"Check that refresh time status change is staied");
        boolean refreshTimeAct = webPagesPage.RefreshTimeToggleIsOn();
        Assert.assertEquals(refreshTimeAct, !refreshTime);

        logger.log(LogStatus.INFO," Check that Save button is enabled");
        Assert.assertTrue(webPagesPage.SaveButtonIsEnabled());

        logger.log(LogStatus.INFO," Check that Cancel button is enabled");
        Assert.assertTrue(webPagesPage.CancelButtonIsEnabled());
    }

    @Test (enabled =false) //Bug 10963:Web page (UI) - Refresh time status is remained after clicking Do not Save button
    public void ChangeRefreshTimeStatusExitAndDoNoTSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeRefreshTimeStatusExitAndDoNoTSaveTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        int index = webPagesPage.GetRandomDigit(0, webPageSize);
        String name = webPagesPage.GetWebPageNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on web page "+name);
        webPagesPage.ClickOnWebPageByIndex(index);

        String newName = "NewNameFor"+name+webPagesPage.GetRandomDigit(0, 99999);
        logger.log(LogStatus.INFO,"Input" +name+" into name field");
        webPagesPage.InputIntoNameField(newName);

        logger.log(LogStatus.INFO,"Press Save button");
        webPagesPage.PressSaveButton();

        boolean refreshTime = webPagesPage.RefreshTimeToggleIsOn();
        logger.log(LogStatus.INFO,"Click on refresh time status");
        webPagesPage.ClickOnRefreshTimeSwitch();

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(webPagesPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Click on Do not Save button in Unsaved changed window");
        webPagesPage.PressDontSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to Web Pages page");
        webPagesPage.ClickOnWebPagesButton();

        logger.log(LogStatus.INFO,"Click on "+newName+" web page");
        webPagesPage.ClickOnWebPageByName(newName);
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that refresh time status change isn't saved");
        boolean refreshTimeAct = webPagesPage.RefreshTimeToggleIsOn();
        Assert.assertEquals(refreshTimeAct, refreshTime);
    }

    @Test(enabled =false)  //Bug 10963:Web page (UI) - Refresh time status is remained after clicking Do not Save button
    public void ChangeRefreshTimeStatusExitAndSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeRefreshTimeStatusExitAndSaveTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        int index = webPagesPage.GetRandomDigit(0, webPageSize);
        String name = webPagesPage.GetWebPageNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on web page "+name);
        webPagesPage.ClickOnWebPageByIndex(index);

        String newName = "WebPageName"+webPagesPage.GetRandomDigit(0, 99999);
        logger.log(LogStatus.INFO,"Input" +newName+" into name field");
        webPagesPage.InputIntoNameField(newName);

        boolean refreshTime = webPagesPage.RefreshTimeToggleIsOn();
        logger.log(LogStatus.INFO,"Click on refresh time status");
        webPagesPage.ClickOnRefreshTimeSwitch();

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(webPagesPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Click on Save button in Unsaved changed window");
        webPagesPage.PressSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to Web Pages page");
        webPagesPage.ClickOnWebPagesButton();

        logger.log(LogStatus.INFO,"Click on "+newName+" web page");
        webPagesPage.ClickOnWebPageByName(newName);
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that refresh time status change is saved");
        boolean refreshTimeAct = webPagesPage.RefreshTimeToggleIsOn();
        Assert.assertEquals(refreshTimeAct, !refreshTime);
    }

    @Test(enabled =false) 
    public void ChangeRefreshTimeValueAndCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeRefreshTimeValueAndCancelTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
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
        String time = null;
        while(true){
            String refrTime = ""+webPagesPage.GetRandomDigit(1, 120);  //9483 Fresh time more than 59 isn't saved
            if(!refrTime.equals(refreshTimeVal)){
                time = refrTime;
                break;
            }
        }

        logger.log(LogStatus.INFO,"Input "+time+" into refresh time field");
        webPagesPage.InputIntoRefreshTimeField(time);
        webPagesPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Press Cancel button");
        webPagesPage.PressCancelButton();

        if(refreshTime){
            logger.log(LogStatus.INFO,"Check that refresh time value change is canceled");
            String refreshTimeAct = webPagesPage.GetRefreshTimeValue();
            Assert.assertEquals(refreshTimeAct, refreshTimeVal);
        }

        if(!refreshTime){
            logger.log(LogStatus.INFO,"Check that refresh time status is OFF");
            boolean refreshTimeAct = webPagesPage.RefreshTimeToggleIsOn();
            Assert.assertFalse(refreshTimeAct);
        }

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window doesn't appear");
        Assert.assertFalse(groupsHierarchyPage.CheckThatModalIsOpen());
    }

    @Test(enabled =false) 
    public void ChangeRefreshTimeValueExitAndCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeRefreshTimeValueExitAndCancelTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
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
        String time = null;
        while(true){
            String refrTime = ""+webPagesPage.GetRandomDigit(1, 120);  //9483 Fresh time more than 59 isn't saved
            if(!refrTime.equals(refreshTimeVal)){
                time = refrTime;
                break;
            }
        }

        logger.log(LogStatus.INFO,"Input "+time+" into refresh time field");
        webPagesPage.InputIntoRefreshTimeField(time);
        webPagesPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(webPagesPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that Cancel Unsaved changes button is enabled");
        Assert.assertTrue(webPagesPage.CancelUnsavedChangesButtonIsEnabled());

        logger.log(LogStatus.INFO,"Click on Cancel button in Unsaved changed window");
        webPagesPage.CancelUnsavedChanges();

        logger.log(LogStatus.INFO,"Check that refresh time value change is staied");
        String refreshTimeAct = webPagesPage.GetRefreshTimeValue();
        Assert.assertEquals(refreshTimeAct, time);

        logger.log(LogStatus.INFO," Check that Save button is enabled");
        Assert.assertTrue(webPagesPage.SaveButtonIsEnabled());

        logger.log(LogStatus.INFO," Check that Cancel button is enabled");
        Assert.assertTrue(webPagesPage.CancelButtonIsEnabled());
    }

    @Test(enabled =false) 
    public void ChangeRefreshTimeValueExitAndDoNotSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeRefreshTimeValueExitAndDoNotSaveTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        int index = webPagesPage.GetRandomDigit(0, webPageSize);
        String name = webPagesPage.GetWebPageNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on web page "+name);
        webPagesPage.ClickOnWebPageByIndex(index);

        String newName = "WebPAgeName"+webPagesPage.GetRandomDigit(0, 99999);
        logger.log(LogStatus.INFO,"Input" +name+" into name field");
        webPagesPage.InputIntoNameField(newName);

        logger.log(LogStatus.INFO,"Press Save button");
        webPagesPage.PressSaveButton();

        boolean refreshTime = webPagesPage.RefreshTimeToggleIsOn();
        if(!refreshTime){
            logger.log(LogStatus.INFO,"Click on refresh time status");
            webPagesPage.ClickOnRefreshTimeSwitch();
        }
        String refreshTimeVal = webPagesPage.GetRefreshTimeValue();
        String time = null;
        while(true){
            String refrTime = ""+webPagesPage.GetRandomDigit(1, 120);  //9483 Fresh time more than 59 isn't saved
            if(!refrTime.equals(refreshTimeVal)){
                time = refrTime;
                break;
            }
        }

        logger.log(LogStatus.INFO,"Input "+time+" into refresh time field");
        webPagesPage.InputIntoRefreshTimeField(time);
        webPagesPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(webPagesPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Click on Do not Save button in Unsaved changed window");
        webPagesPage.PressDontSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to Web Pages page");
        webPagesPage.ClickOnWebPagesButton();

        logger.log(LogStatus.INFO,"Click on "+newName+" web page");
        webPagesPage.ClickOnWebPageByName(newName);
        Thread.sleep(1000);

        if(!refreshTime){
            logger.log(LogStatus.INFO,"Check that refresh time status is OFF");
            boolean refreshTimeAct = webPagesPage.RefreshTimeToggleIsOn();
            Assert.assertFalse(refreshTimeAct);
        }

        if(refreshTime){
            logger.log(LogStatus.INFO,"Check that refresh time value change isn't saved");
            String refreshTimeAct = webPagesPage.GetRefreshTimeValue();
            Assert.assertEquals(refreshTimeAct, refreshTimeVal);
        }
    }

    @Test(enabled =false) //9483 Fresh time more than 59 isn't saved
    public void ChangeRefreshTimeValueExitAndSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeRefreshTimeValueExitAndSaveTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        int index = webPagesPage.GetRandomDigit(0, webPageSize);
        String name = webPagesPage.GetWebPageNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on web page "+name);
        webPagesPage.ClickOnWebPageByIndex(index);

        String newName = "NewNameFor"+webPagesPage.GetRandomDigit(0, 99999);
        logger.log(LogStatus.INFO,"Input" +name+" into name field");
        webPagesPage.InputIntoNameField(newName);

        boolean refreshTime = webPagesPage.RefreshTimeToggleIsOn();
        if(!refreshTime){
            logger.log(LogStatus.INFO,"Click on refresh time status");
            webPagesPage.ClickOnRefreshTimeSwitch();
        }
        String refreshTimeVal = webPagesPage.GetRefreshTimeValue();
        String time = null;
        while(true){
            String refrTime = ""+webPagesPage.GetRandomDigit(1, 120);  //9483 Fresh time more than 59 isn't saved
            if(!refrTime.equals(refreshTimeVal)){
                time = refrTime;
                break;
            }
        }

        logger.log(LogStatus.INFO,"Input "+time+" into refresh time field");
        webPagesPage.InputIntoRefreshTimeField(time);
        webPagesPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(webPagesPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Click on Save button in Unsaved changed window");
        webPagesPage.PressSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to Web Pages page");
        webPagesPage.ClickOnWebPagesButton();

        logger.log(LogStatus.INFO,"Click on "+newName+" web page");
        webPagesPage.ClickOnWebPageByName(newName);
        Thread.sleep(1000);

       logger.log(LogStatus.INFO,"Check that refresh time status is ON");
       boolean refreshTimeAct = webPagesPage.RefreshTimeToggleIsOn();
       Assert.assertTrue(refreshTimeAct);

       logger.log(LogStatus.INFO,"Check that refresh time value change is saved");
       String refreshTimeValAct = webPagesPage.GetRefreshTimeValue();
       Assert.assertEquals(refreshTimeValAct, time);
    }

    @Test(enabled =false) 
    public void ChangeRefreshTimeValueBySpinnerAndCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeRefreshTimeValueBySpinnerAndCancelTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
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

        logger.log(LogStatus.INFO,"Press Cancel button");
        webPagesPage.PressCancelButton();
        Thread.sleep(1000);

        if(refreshTime){
            logger.log(LogStatus.INFO,"Check that refresh time value change isn't saved");
            String refreshTimeAct = webPagesPage.GetRefreshTimeValue();
            Assert.assertEquals(refreshTimeAct, refreshTimeVal);
        }

        if(!refreshTime){
            logger.log(LogStatus.INFO,"Check that refresh time status is OFF");
            boolean refreshTimeAct = webPagesPage.RefreshTimeToggleIsOn();
            Assert.assertFalse(refreshTimeAct);
        }
        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window doesn't appear");
        Assert.assertFalse(groupsHierarchyPage.CheckThatModalIsOpen());
    }

    @Test(enabled =false) 
    public void ChangeRefreshTimeValueBySpinnerExitAndCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeRefreshTimeValueBySpinnerExitAndCancelTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
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
        if(Integer.parseInt(refreshTimeVal)>1){
            int time = webPagesPage.GetRandomDigit(1, Integer.parseInt(refreshTimeVal));
            logger.log(LogStatus.INFO,"Click on spinner Down "+time+" times");
            for (int i = 0 ;i<time; i++){
                webPagesPage.ClickOnFreshTimeDownSpinner();
            }
            webPagesPage.WaitUntilSaveButtonWillBeEnable();

            logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
            groupsHierarchyPage.ClickOnGroupsHierarchyButton();

            logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
            Assert.assertTrue(webPagesPage.CheckThatModalIsOpen());

            logger.log(LogStatus.INFO,"Check that Cancel Unsaved changes button is enabled");
            Assert.assertTrue(webPagesPage.CancelUnsavedChangesButtonIsEnabled());

            logger.log(LogStatus.INFO,"Click on Cancel button in Unsaved changed window");
            webPagesPage.CancelUnsavedChanges();

            logger.log(LogStatus.INFO,"Check that refresh time value change is remained");
            String refreshTimeAct = webPagesPage.GetRefreshTimeValue();
            Assert.assertEquals(Integer.parseInt(refreshTimeAct), Integer.parseInt(refreshTimeVal)-time);

            logger.log(LogStatus.INFO," Check that Save button is enabled");
            Assert.assertTrue(webPagesPage.SaveButtonIsEnabled());

            logger.log(LogStatus.INFO," Check that Cancel button is enabled");
            Assert.assertTrue(webPagesPage.CancelButtonIsEnabled());
        }
    }

    @Test(enabled =false) 
    public void ChangeRefreshTimeValueBySpinnerExitAndDoNotSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeRefreshTimeValueBySpinnerExitAndDoNotSaveTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        int index = webPagesPage.GetRandomDigit(0, webPageSize);
        String name = webPagesPage.GetWebPageNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on web page "+name);
        webPagesPage.ClickOnWebPageByIndex(index);

        String newName = "NewName"+webPagesPage.GetRandomDigit(0, 99999);
        logger.log(LogStatus.INFO,"Input" +newName+" into name field");
        webPagesPage.InputIntoNameField(newName);

        logger.log(LogStatus.INFO,"Press Save button");
        webPagesPage.PressSaveButton();

        boolean refreshTime = webPagesPage.RefreshTimeToggleIsOn();
        if(!refreshTime){
            logger.log(LogStatus.INFO,"Click on refresh time toggle");
            webPagesPage.ClickOnRefreshTimeSwitch();
        }
        String refreshTimeVal = webPagesPage.GetRefreshTimeValue();
        if(Integer.parseInt(refreshTimeVal)==1){
            logger.log(LogStatus.INFO,"Click on spinner UP ");
            webPagesPage.ClickOnFreshTimeUpSpinner();
            webPagesPage.ClickOnFreshTimeUpSpinner();
        }

        int time = webPagesPage.GetRandomDigit(1, Integer.parseInt(refreshTimeVal));
        logger.log(LogStatus.INFO,"Click on spinner Down "+time+" times");
        for (int i = 0 ;i<time; i++){
            webPagesPage.ClickOnFreshTimeDownSpinner();
        }
        webPagesPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(webPagesPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Click on Do not Save button in Unsaved changed window");
        webPagesPage.PressDontSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to Web Pages page");
        webPagesPage.ClickOnWebPagesButton();

        logger.log(LogStatus.INFO,"Click on "+newName+" web page");
        webPagesPage.ClickOnWebPageByName(newName);
        Thread.sleep(1000);

        if(refreshTime){
            logger.log(LogStatus.INFO,"Check that refresh time value change isn't saved");
            String refreshTimeAct = webPagesPage.GetRefreshTimeValue();
            Assert.assertEquals(refreshTimeAct, refreshTimeVal);
        }

        if(!refreshTime){
            logger.log(LogStatus.INFO,"Check that refresh time status is OFF");
            boolean refreshTimeAct = webPagesPage.RefreshTimeToggleIsOn();
            Assert.assertFalse(refreshTimeAct);
        }
    }

    @Test (enabled =false) 
    public void ChangeRefreshTimeValueBySpinnerAndSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeRefreshTimeValueBySpinnerAndSaveTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        int index = webPagesPage.GetRandomDigit(0, webPageSize);
        String name = webPagesPage.GetWebPageNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on web page "+name);
        webPagesPage.ClickOnWebPageByIndex(index);

        String newName = "NewName"+webPagesPage.GetRandomDigit(0, 99999);
        logger.log(LogStatus.INFO,"Input" +name+" into name field");
        webPagesPage.InputIntoNameField(newName);
        Thread.sleep(1000);

        boolean refreshTime = webPagesPage.RefreshTimeToggleIsOn();
        if(!refreshTime){
            logger.log(LogStatus.INFO,"Click on refresh time status");
            webPagesPage.ClickOnRefreshTimeSwitch();
        }
        String refreshTimeVal = webPagesPage.GetRefreshTimeValue();

        int max = 60-Integer.parseInt(refreshTimeVal); //9483 must be 120, not 60
        int time = webPagesPage.GetRandomDigit(1, max);

        logger.log(LogStatus.INFO,"Click on spinner UP "+time+" times");
        for (int i = 0 ;i<time; i++){
            webPagesPage.ClickOnFreshTimeUpSpinner();
        }
        webPagesPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(webPagesPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Click on Save button in Unsaved changed window");
        webPagesPage.PressSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to Web Pages page");
        webPagesPage.ClickOnWebPagesButton();

        logger.log(LogStatus.INFO,"Click on "+newName+" web page");
        webPagesPage.ClickOnWebPageByName(newName);
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that refresh time status is ON");
        boolean refreshTimeActSt = webPagesPage.RefreshTimeToggleIsOn();
        Assert.assertTrue(refreshTimeActSt);

        logger.log(LogStatus.INFO,"Check that refresh time value change is saved");
        String refreshTimeAct = webPagesPage.GetRefreshTimeValue();
        Assert.assertEquals(Integer.parseInt(refreshTimeAct), Integer.parseInt(refreshTimeVal)+time);
    }

    @Test (enabled =false) 
    public void ChangeURLAndCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeURLAndCancelTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        int index = webPagesPage.GetRandomDigit(0, webPageSize);
        String name = webPagesPage.GetWebPageNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on web page "+name);
        webPagesPage.ClickOnWebPageByIndex(index);

        String url = webPagesPage.GetWebPageURl();
        String newURL = "http://NewURL"+webPagesPage.GetRandomDigit(0,1000)+".com";

        logger.log(LogStatus.INFO,"Input "+newURL+" into URL field");
        webPagesPage.InputIntoURLField(newURL);
        webPagesPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Press Cancel button");
        webPagesPage.PressCancelButton();

        logger.log(LogStatus.INFO,"Check that URL change isn't saved");
        String urlAct = webPagesPage.GetWebPageURl();
        Assert.assertEquals(urlAct, url);

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window doesn't appear");
        Assert.assertFalse(groupsHierarchyPage.CheckThatModalIsOpen());
    }

    @Test(enabled =false) 
    public void ChangeURLExitAndCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeURLExitAndCancelTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        int index = webPagesPage.GetRandomDigit(0, webPageSize);
        String name = webPagesPage.GetWebPageNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on web page "+name);
        webPagesPage.ClickOnWebPageByIndex(index);

        String url = webPagesPage.GetWebPageURl();
        String newURL = "http://NewURL"+webPagesPage.GetRandomDigit(0,1000)+".com";

        logger.log(LogStatus.INFO,"Input "+newURL+" into URL field");
        webPagesPage.InputIntoURLField(newURL);
        webPagesPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(webPagesPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that Cancel Unsaved changes button is enabled");
        Assert.assertTrue(webPagesPage.CancelUnsavedChangesButtonIsEnabled());

        logger.log(LogStatus.INFO,"Click on Cancel button in Unsaved changed window");
        webPagesPage.CancelUnsavedChanges();

        logger.log(LogStatus.INFO,"Check that URL change is staied");
        String urlAct = webPagesPage.GetWebPageURl();
        Assert.assertEquals(urlAct, newURL);

        logger.log(LogStatus.INFO," Check that Save button is enabled");
        Assert.assertTrue(webPagesPage.SaveButtonIsEnabled());

        logger.log(LogStatus.INFO," Check that Cancel button is enabled");
        Assert.assertTrue(webPagesPage.CancelButtonIsEnabled());
    }

    @Test (enabled =false) 
    public void ChangeURLExitAndDoNotSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeURLExitAndDoNotSaveTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        int index = webPagesPage.GetRandomDigit(0, webPageSize);
        String name = webPagesPage.GetWebPageNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on web page "+name);
        webPagesPage.ClickOnWebPageByIndex(index);

        String newName = "NewName"+webPagesPage.GetRandomDigit(0, 99999);
        logger.log(LogStatus.INFO,"Input" +name+" into name field");
        webPagesPage.InputIntoNameField(newName);

        logger.log(LogStatus.INFO,"Press Save button");
        webPagesPage.PressSaveButton();

        String url = webPagesPage.GetWebPageURl();
        String newURL = "http://NewURL"+webPagesPage.GetRandomDigit(0,1000)+".com";

        logger.log(LogStatus.INFO,"Input "+newURL+" into URL field");
        webPagesPage.InputIntoURLField(newURL);
        webPagesPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(webPagesPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Click on Do not Save button in Unsaved changed window");
        webPagesPage.PressDontSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to Web Pages page");
        webPagesPage.ClickOnWebPagesButton();

        logger.log(LogStatus.INFO,"Click on "+newName+" web page");
        webPagesPage.ClickOnWebPageByName(newName);
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that URL change isn't saved");
        String urlAct = webPagesPage.GetWebPageURl();
        Assert.assertEquals(urlAct, url);
    }

    @Test(enabled =false) 
    public void ChangeURLExitAndSaveTest() throws InterruptedException {
        logger=report.startTest("ChangeURLExitAndSaveTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        int index = webPagesPage.GetRandomDigit(0, webPageSize);
        String name = webPagesPage.GetWebPageNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on web page "+name);
        webPagesPage.ClickOnWebPageByIndex(index);

        String newName = "NewName"+webPagesPage.GetRandomDigit(0, 99999);
        logger.log(LogStatus.INFO,"Input" +name+" into name field");
        webPagesPage.InputIntoNameField(newName);

        String url = webPagesPage.GetWebPageURl();
        String newURL = "http://NewURL"+webPagesPage.GetRandomDigit(0,1000)+".com";

        logger.log(LogStatus.INFO,"Input "+newURL+" into URL field");
        webPagesPage.InputIntoURLField(newURL);
        webPagesPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(webPagesPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Click on Save button in Unsaved changed window");
        webPagesPage.PressSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to Web Pages page");
        webPagesPage.ClickOnWebPagesButton();

        logger.log(LogStatus.INFO,"Click on "+newName+" web page");
        webPagesPage.ClickOnWebPageByName(newName);
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that URL change is saved");
        String urlAct = webPagesPage.GetWebPageURl();
        Assert.assertEquals(urlAct, newURL);
    }

    @Test(enabled =false) 
    public void DeleteURLAndCancelTest() throws InterruptedException {
        logger=report.startTest("DeleteURLAndCancelTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        int index = webPagesPage.GetRandomDigit(0, webPageSize);
        String name = webPagesPage.GetWebPageNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on web page "+name);
        webPagesPage.ClickOnWebPageByIndex(index);

        String url = webPagesPage.GetWebPageURl();
        String newURL = "http://NewURL"+webPagesPage.GetRandomDigit(0,1000)+".com";

        logger.log(LogStatus.INFO,"Delete URL");
        webPagesPage.ClickOnDeleteUrlIcon();

        logger.log(LogStatus.INFO,"Check that Cancel button is enabled"); ///failed
        Assert.assertTrue(webPagesPage.CancelButtonIsEnabled());

        logger.log(LogStatus.INFO,"Press Cancel button");
        webPagesPage.PressCancelButton();

        logger.log(LogStatus.INFO,"Check that URL isn't deleted");
        String urlAct = webPagesPage.GetWebPageURl();
        Assert.assertEquals(urlAct, url);

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window doesn't appear");
        Assert.assertFalse(groupsHierarchyPage.CheckThatModalIsOpen());
    }

    @Test(enabled =false) 
    public void DeleteURLExitAndCancelTest() throws InterruptedException {
        logger=report.startTest("DeleteURLExitAndCancelTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        int index = webPagesPage.GetRandomDigit(0, webPageSize);
        String name = webPagesPage.GetWebPageNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on web page "+name);
        webPagesPage.ClickOnWebPageByIndex(index);

        String url = webPagesPage.GetWebPageURl();
        String newURL = "http://NewURL"+webPagesPage.GetRandomDigit(0,1000)+".com";

        logger.log(LogStatus.INFO,"Delete URL");
        webPagesPage.ClickOnDeleteUrlIcon();

        logger.log(LogStatus.INFO,"Check that Cancel button is enabled"); ///failed
        Assert.assertTrue(webPagesPage.CancelButtonIsEnabled());

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(webPagesPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that Cancel Unsaved changes button is enabled");
        Assert.assertTrue(webPagesPage.CancelUnsavedChangesButtonIsEnabled());

        logger.log(LogStatus.INFO,"Click on Cancel button in Unsaved changed window");
        webPagesPage.CancelUnsavedChanges();

        logger.log(LogStatus.INFO,"Check that URL removing is staied");
        String urlAct = webPagesPage.GetWebPageURl();
        Assert.assertEquals(urlAct, "");

        logger.log(LogStatus.INFO," Check that Save button is disabled");
        Assert.assertFalse(webPagesPage.SaveButtonIsEnabled());

        logger.log(LogStatus.INFO," Check that Cancel button is enabled");
        Assert.assertTrue(webPagesPage.CancelButtonIsEnabled());
    }

    @Test(enabled =false) 
    public void DeleteURLExitAndDoNotSaveTest() throws InterruptedException {
        logger=report.startTest("DeleteURLExitAndDoNotSaveTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        int index = webPagesPage.GetRandomDigit(0, webPageSize);
        String name = webPagesPage.GetWebPageNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on web page "+name);
        webPagesPage.ClickOnWebPageByIndex(index);

        String newName = "NewName"+webPagesPage.GetRandomDigit(0, 99999);
        logger.log(LogStatus.INFO,"Input" +name+" into name field");
        webPagesPage.InputIntoNameField(newName);

        logger.log(LogStatus.INFO,"Press Save button");
        webPagesPage.PressSaveButton();

        String url = webPagesPage.GetWebPageURl();
        String newURL = "http://NewURL"+webPagesPage.GetRandomDigit(0,1000)+".com";

        logger.log(LogStatus.INFO,"Delete URL");
        webPagesPage.ClickOnDeleteUrlIcon();

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(webPagesPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that Do Not Save Unsaved changes button is enabled");
        Assert.assertTrue(webPagesPage.DoNotSaveUnsavedChangesButtonIsEnabled());

        logger.log(LogStatus.INFO,"Click on Do not Save button in Unsaved changed window");
        webPagesPage.PressDontSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Go to Web Pages page");
        webPagesPage.ClickOnWebPagesButton();

        logger.log(LogStatus.INFO,"Click on "+newName+" web page");
        webPagesPage.ClickOnWebPageByName(newName);
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that URL change isn't removed");
        String urlAct = webPagesPage.GetWebPageURl();
        Assert.assertEquals(urlAct, url);
    }

    @Test (enabled =false) 
    public void DeleteURLExitAndSaveTest() throws InterruptedException {
        logger=report.startTest("DeleteURLExitAndSaveTest");

        int webPageSize = webPagesPage.GetWebPagesSize();
        int index = webPagesPage.GetRandomDigit(0, webPageSize);
        String name = webPagesPage.GetWebPageNameByIndex(index);

        logger.log(LogStatus.INFO,"Click on web page "+name);
        webPagesPage.ClickOnWebPageByIndex(index);

        String newName = "NewName"+webPagesPage.GetRandomDigit(0, 99999);
        logger.log(LogStatus.INFO,"Input" +name+" into name field");
        webPagesPage.InputIntoNameField(newName);

        String url = webPagesPage.GetWebPageURl();
        String newURL = "http://NewURL"+webPagesPage.GetRandomDigit(0,1000)+".com";
        logger.log(LogStatus.INFO,"Delete URL");
        webPagesPage.ClickOnDeleteUrlIcon();

        logger.log(LogStatus.INFO,"Go to Group Hierarchy page");
        groupsHierarchyPage.ClickOnGroupsHierarchyButton();

        logger.log(LogStatus.INFO,"Check that Unsaved changes window appear");
        Assert.assertTrue(webPagesPage.CheckThatModalIsOpen());

        logger.log(LogStatus.INFO,"Check that Save Unsaved changes button is disabled");
        Assert.assertFalse(webPagesPage.SaveUnsavedChangesButtonIsEnabled());
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
