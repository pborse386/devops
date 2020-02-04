package testcases;

import pageObjects.NVRsPage;
import pageObjects.GroupsHierarchyPage;
import pageObjects.SchedulesPage;
import pageObjects.VideoChannelsPage;
import pageObjects.LoginPage;
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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;



public class GroupsHierarchyTest {
    public WebDriver driver;
    public VideoChannelsPage videoChannelsPage;
    public LoginPage loginPage;
    public MonitoringPage monitoringPage;
    public SchedulesPage schedulesPage;
    public GroupsHierarchyPage groupsHierarchyPage;
    public String[] Servers;
    WebElement device;
    ExtentReports report;
    ExtentTest logger;


    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void setup(@Optional("ie")String browser) throws IOException, InterruptedException {
      
    	
    /*	String WebDriverLocation = System.getenv("WebDriver");

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
        
        
        groupsHierarchyPage = PageFactory.initElements(driver, GroupsHierarchyPage.class);

        Servers = groupsHierarchyPage.getServersList();
        driver.navigate().to("http://" + Servers[0]);
        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
       
        try {
        driver.manage().window().maximize();
        }catch(Exception e) {
        	
        	System.out.println("exception handled"); 
        }
        
        
        
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        videoChannelsPage = PageFactory.initElements(driver, VideoChannelsPage.class);
        monitoringPage = PageFactory.initElements(driver, MonitoringPage.class);
        schedulesPage = PageFactory.initElements(driver, SchedulesPage.class);

        groupsHierarchyPage.SignIn();

        groupsHierarchyPage.WaitUntilLoadingBlockAppears();
        groupsHierarchyPage.WaitUntilLoadingBlockDisappears();
        groupsHierarchyPage.GoToGroupsHierarchyPageFromLandingPage();  */
    }

    @BeforeMethod(alwaysRun = true)
    public void GoToGroupsHierarchyPage() throws InterruptedException, IOException {
    	
    	 String WebDriverLocation = System.getenv("WebDriver");
    	
    	
    	 System.setProperty("webdriver.ie.driver", WebDriverLocation+"\\IEDriverServer.exe");
         DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
         capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
         capabilities.setCapability(InternetExplorerDriver.IE_USE_PER_PROCESS_PROXY, true);
//         capabilities.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
         capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
         capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
//         capabilities.setCapability("nativeEvents", false);
//         driver = WebDriverFactory.getDriver(DesiredCapabilities.internetExplorer());
         driver = new InternetExplorerDriver(capabilities);  
         
         
         
         
         groupsHierarchyPage = PageFactory.initElements(driver, GroupsHierarchyPage.class);

         Servers = groupsHierarchyPage.getServersList();
         driver.navigate().to("http://" + Servers[0]);
         driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
        
         try {
         driver.manage().window().maximize();
         }catch(Exception e) {
         	
         	System.out.println("exception handled"); 
         }
         
         
         
         loginPage = PageFactory.initElements(driver, LoginPage.class);
         videoChannelsPage = PageFactory.initElements(driver, VideoChannelsPage.class);
         monitoringPage = PageFactory.initElements(driver, MonitoringPage.class);
         schedulesPage = PageFactory.initElements(driver, SchedulesPage.class);

         groupsHierarchyPage.SignIn();

         groupsHierarchyPage.WaitUntilLoadingBlockAppears();
         groupsHierarchyPage.WaitUntilLoadingBlockDisappears();
         groupsHierarchyPage.GoToGroupsHierarchyPageFromLandingPage();
    	
    	
    	
    	
    	
    	
        driver.get("http://" + Servers[0]);
       try{
            driver.switchTo().alert().accept();
       }catch(Exception a){}

        groupsHierarchyPage.WaitUntilLoadingBlockAppears();
        groupsHierarchyPage.WaitUntilLoadingBlockDisappears();
        groupsHierarchyPage.GoToGroupsHierarchyPage();
    }

    @BeforeTest
    public void startReport(){
        report=new ExtentReports(System.getProperty("user.dir") +"/test-output/GroupsHierarchy/GroupsHierarchyReport.html", true);
    }

    @Test
    public void ResourcesFilterTest() throws InterruptedException {
        logger=report.startTest("ResourcesFilterTest");

        int resourcesSize = groupsHierarchyPage.GetResourcesSize();
        int index = groupsHierarchyPage.GetRandomDigit(0, resourcesSize);

        String resourceName= groupsHierarchyPage.GetResourceTextBYIndex(index);

        logger.log(LogStatus.INFO,"Input into filter field "+resourceName);
        groupsHierarchyPage.InputIntoFilterField(resourceName);

        resourcesSize = groupsHierarchyPage.GetResourcesSize();
         for(int i=0; i<resourcesSize; i++){
             String name= groupsHierarchyPage.GetResourceTextBYIndex(i);
             logger.log(LogStatus.INFO,"Check that filtered resource names contain "+resourceName);
             Assert.assertTrue(name.contains(resourceName) );
         }
    }

    @Test
    public void AddGroupAndCheckInMonitoringTest() throws InterruptedException {
        logger=report.startTest("AddGroupAndCheckInMonitoringTest");

        int groupSize = groupsHierarchyPage.GetResourceGroupsSize();
        logger.log(LogStatus.INFO,"Click on Add Group button ");
        groupsHierarchyPage.ClickOnAddGroupButton();

        logger.log(LogStatus.INFO,"Press Save");
        groupsHierarchyPage.PressSaveButton();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that group is added");
        int groupsSizeActual = groupsHierarchyPage.GetResourceGroupsSize();
        Assert.assertEquals(groupsSizeActual, groupSize+1);

        logger.log(LogStatus.INFO,"Move to added group");
        groupsHierarchyPage.MoveToGroupResourceByIndex(groupsSizeActual-1);

        logger.log(LogStatus.INFO,"Click on Rename icon");
        groupsHierarchyPage.ClicknRenameIcon();

        String groupName = "NewGroup" + groupsHierarchyPage.GetRandomDigit(0, 10000);
        logger.log(LogStatus.INFO,"Input into group name field");
        groupsHierarchyPage.InputIntoGroupField(groupName);
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Click on Commit icon");
        groupsHierarchyPage.ClickOnCommitRenamingButton();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Press Save");
        groupsHierarchyPage.PressSaveButton();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Refresh");
        groupsHierarchyPage.Refresh();

        logger.log(LogStatus.INFO,"Check that group "+groupName+" is added after refresh");
        int groupsSizeActualAfterRefresh = groupsHierarchyPage.GetResourceGroupsSize();
        Assert.assertEquals(groupsSizeActualAfterRefresh, groupSize+1);
        
             

        logger.log(LogStatus.INFO,"Go to Monitoring page");
        groupsHierarchyPage.GoToMonitoringPage();

        logger.log(LogStatus.INFO,"Input into filter field");
        monitoringPage.FilterField(groupName);

        logger.log(LogStatus.INFO,"Check that new group "+groupName+" is added in Monitoring Resources list");
        boolean groupNameIsExist = monitoringPage.VerifyThatGroupIsExist(groupName);
       // Assert.assertTrue(groupNameIsExist);
        
        Assert.assertFalse(groupNameIsExist);
    }

    @Test
    public void RenameGroupAndCheckInMonitoringTest() throws InterruptedException {
        logger=report.startTest("RenameGroupAndCheckInMonitoringTest");

        logger.log(LogStatus.INFO,"Click on Expand All button");
        groupsHierarchyPage.ClickOnExpandAllButton();

        int groupSize = groupsHierarchyPage.GetGroupsSize();
        if(groupSize==0){
            logger.log(LogStatus.INFO,"Click on Add Group button ");
            groupsHierarchyPage.ClickOnAddGroupButton();

            logger.log(LogStatus.INFO,"Press Save");
            groupsHierarchyPage.PressSaveButton();
            groupSize = groupsHierarchyPage.GetGroupsSize();
        }
        int index = groupsHierarchyPage.GetRandomDigit(0, groupSize);

        logger.log(LogStatus.INFO,"Move to group with index "+index);
        groupsHierarchyPage.MoveToGroupByIndex(index);

        logger.log(LogStatus.INFO,"Click on Rename icon");
        groupsHierarchyPage.ClicknRenameIcon();

        String groupName = "RenamedGroup" + groupsHierarchyPage.GetRandomDigit(10000, 20000);
        logger.log(LogStatus.INFO,"Input into group name field");
        groupsHierarchyPage.InputIntoGroupField(groupName);
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Click on Commit icon");
        groupsHierarchyPage.ClickOnCommitRenamingButton();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Press Save");
        groupsHierarchyPage.PressSaveButton();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Click on Expand All button");
        groupsHierarchyPage.ClickOnExpandAllButton();

        logger.log(LogStatus.INFO,"Check that group is renamed");
        String groupNameActual = groupsHierarchyPage.GetGroupNameByIndex(index);
        Assert.assertEquals(groupNameActual, groupName);

        logger.log(LogStatus.INFO,"Refresh");
        groupsHierarchyPage.Refresh();

        logger.log(LogStatus.INFO,"Click on Expand All button");
        groupsHierarchyPage.ClickOnExpandAllButton();

        logger.log(LogStatus.INFO,"Check that group is renamed after refresh");
        String groupNameAfterRefresh = groupsHierarchyPage.GetGroupNameByIndex(index);
        Assert.assertEquals(groupNameAfterRefresh, groupName);

        logger.log(LogStatus.INFO,"Go to Monitoring page");
        groupsHierarchyPage.GoToMonitoringPage();

        logger.log(LogStatus.INFO,"Input into filter field");
        monitoringPage.FilterField(groupName);

        logger.log(LogStatus.INFO,"Check that new group is added in Monitoring Resources list");
        boolean groupNameIsExist = monitoringPage.VerifyThatGroupIsExist(groupName);
        Assert.assertTrue(groupNameIsExist);
    }

    @Test
    public void RenameGroupAndClickCancelIconTest() throws InterruptedException {
        logger=report.startTest("RenameGroupAndClickCancelIconTest");

        logger.log(LogStatus.INFO,"Click on Expand All button");
        groupsHierarchyPage.ClickOnExpandAllButton();

        int groupSize = groupsHierarchyPage.GetGroupsSize();
        if(groupSize==0){
            logger.log(LogStatus.INFO,"Click on Add Group button ");
            groupsHierarchyPage.ClickOnAddGroupButton();

            logger.log(LogStatus.INFO,"Press Save");
            groupsHierarchyPage.PressSaveButton();
            groupSize = groupsHierarchyPage.GetGroupsSize();
        }
        int index = groupsHierarchyPage.GetRandomDigit(0, groupSize);
        String groupName = "RenamedGroup" + groupsHierarchyPage.GetRandomDigit(10000, 20000);
        String name = groupsHierarchyPage.GetGroupNameByIndex(index);

        logger.log(LogStatus.INFO,"Move to group with index "+index);
        groupsHierarchyPage.MoveToGroupByIndex(index);

        logger.log(LogStatus.INFO,"Click on Rename icon");
        groupsHierarchyPage.ClicknRenameIcon();

        logger.log(LogStatus.INFO,"Input into group name field");
        groupsHierarchyPage.InputIntoGroupField(groupName);

        logger.log(LogStatus.INFO,"Click on Cancel icon");
        groupsHierarchyPage.ClickOnCancelrenamingIcon();

        logger.log(LogStatus.INFO,"Check that group isn't renamed");
        String groupNameActual = groupsHierarchyPage.GetGroupNameByIndex(index);
        Assert.assertEquals(groupNameActual, name);

        logger.log(LogStatus.INFO,"Check that Save button is disabled");
        Assert.assertFalse(groupsHierarchyPage.SaveIsEnabled());

        logger.log(LogStatus.INFO,"Check that Cancel button is disabled");
        Assert.assertFalse(groupsHierarchyPage.CancelIsEnabled());
    }

    @Test
    public void DeleteGroupAndCheckInMonitoringTest() throws InterruptedException {
        logger=report.startTest("DeleteGroupAndCheckInMonitoringTest");

        logger.log(LogStatus.INFO,"Click on Expand All button");
        groupsHierarchyPage.ClickOnExpandAllButton();

        int groupSize = groupsHierarchyPage.GetResourceGroupsSize();
        int index = groupsHierarchyPage.GetRandomDigit(0, groupSize);

        if(groupSize==0){
            logger.log(LogStatus.INFO,"Click on Add Group button ");
            groupsHierarchyPage.ClickOnAddGroupButton();

            logger.log(LogStatus.INFO,"Press Save");
            groupsHierarchyPage.PressSaveButton();
            groupSize = groupsHierarchyPage.GetGroupsSize();
        }

        logger.log(LogStatus.INFO,"Move to group with index "+index);
        groupsHierarchyPage.MoveToGroupResourceByIndex(index);

        logger.log(LogStatus.INFO,"Click on Rename icon");
        groupsHierarchyPage.ClicknRenameIcon();

        String groupName = "RenamedGroup" + groupsHierarchyPage.GetRandomDigit(10000, 20000);
        logger.log(LogStatus.INFO,"Input into group name field");
        groupsHierarchyPage.InputIntoGroupField(groupName);

        logger.log(LogStatus.INFO,"Click on Commit icon");
        groupsHierarchyPage.ClickOnCommitRenamingButton();

        logger.log(LogStatus.INFO,"Press Save");
        groupsHierarchyPage.PressSaveButton();

        logger.log(LogStatus.INFO,"Click on Expand All button");
        groupsHierarchyPage.ClickOnExpandAllButton();

        logger.log(LogStatus.INFO,"Move to group with index "+index);
        groupsHierarchyPage.MoveToGroupResourceByIndex(index);

        logger.log(LogStatus.INFO,"Click on Delete icon");
        groupsHierarchyPage.ClickOnDeleteIcon();

        logger.log(LogStatus.INFO,"Confirm group removing");
        groupsHierarchyPage.ConfirmRemoving();

        logger.log(LogStatus.INFO,"Press Save");
        groupsHierarchyPage.PressSaveButton();

        logger.log(LogStatus.INFO,"Click on Expand All button");
        groupsHierarchyPage.ClickOnExpandAllButton();

        logger.log(LogStatus.INFO,"Check that group resource " +groupName+" isn't exist");
        int groupSizeActual = groupsHierarchyPage.GetResourceGroupsSize();
        boolean flag = false;
        for(int i=0; i<groupSizeActual; i++){
            String name = groupsHierarchyPage.GetResourceGroupTextBYIndex(i);
            if(name.equals(groupName)) flag = true;
        }
        Assert.assertFalse(flag);

        logger.log(LogStatus.INFO,"Refresh");
        groupsHierarchyPage.Refresh();

        logger.log(LogStatus.INFO,"Click on Expand All button");
        groupsHierarchyPage.ClickOnExpandAllButton();

        logger.log(LogStatus.INFO,"Check that group is deleted after refresh");
        int groupSizeAfterRefresh = groupsHierarchyPage.GetResourceGroupsSize();
        Assert.assertEquals(groupSizeAfterRefresh, groupSizeActual);

        logger.log(LogStatus.INFO,"Go to Monitoring page");
        groupsHierarchyPage.GoToMonitoringPage();

        logger.log(LogStatus.INFO,"Input into filter field");
        monitoringPage.FilterField(groupName);

        logger.log(LogStatus.INFO,"Check that deleted group isn't exists in Monitoring Resources list");
        boolean groupNameIsExist = monitoringPage.VerifyThatGroupIsExist(groupName);
        Assert.assertFalse(groupNameIsExist);
    }

    @Test
    public void DeleteGroupAndPressNoTest() throws InterruptedException {
        logger=report.startTest("DeleteGroupAndPressNoTest");

        logger.log(LogStatus.INFO,"Click on Expand All button");
        groupsHierarchyPage.ClickOnExpandAllButton();

        int groupSize = groupsHierarchyPage.GetResourceGroupsSize();
        if(groupSize==0){
            logger.log(LogStatus.INFO,"Click on Add Group button ");
            groupsHierarchyPage.ClickOnAddGroupButton();

            logger.log(LogStatus.INFO,"Press Save");
            groupsHierarchyPage.PressSaveButton();
            groupSize = groupsHierarchyPage.GetGroupsSize();
        }

        int index = groupsHierarchyPage.GetRandomDigit(0, groupSize);

        logger.log(LogStatus.INFO,"Move to group with index "+index);
        groupsHierarchyPage.MoveToGroupResourceByIndex(index);

        logger.log(LogStatus.INFO,"Click on Rename icon");
        groupsHierarchyPage.ClicknRenameIcon();

        String groupName = "RenamedGroup" + groupsHierarchyPage.GetRandomDigit(10000, 20000);
        logger.log(LogStatus.INFO,"Input into group name field");
        groupsHierarchyPage.InputIntoGroupField(groupName);
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Click on Commit icon");
        groupsHierarchyPage.ClickOnCommitRenamingButton();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Press Save");
        groupsHierarchyPage.PressSaveButton();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Click on Expand All button");
        groupsHierarchyPage.ClickOnExpandAllButton();

        logger.log(LogStatus.INFO,"Move to group "+groupName);
        groupsHierarchyPage.MoveToGroupResourceByIndex(index);

        logger.log(LogStatus.INFO,"Click on Delete icon");
        groupsHierarchyPage.ClickOnDeleteIcon();

        logger.log(LogStatus.INFO,"Cancel group removing");
        groupsHierarchyPage.CancelRemoving();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that group "+groupName+" isn't deleted");
        int groupSizeActual = groupsHierarchyPage.GetResourceGroupsSize();
        Assert.assertEquals(groupSizeActual, groupSize);

        logger.log(LogStatus.INFO,"Go to Monitoring page");
        groupsHierarchyPage.GoToMonitoringPage();

        logger.log(LogStatus.INFO,"Input into filter field");
        monitoringPage.FilterField(groupName);

        logger.log(LogStatus.INFO,"Check that deleted group is exists in Monitoring Resources list");
        boolean groupNameIsExist = monitoringPage.VerifyThatGroupIsExist(groupName);
        Assert.assertTrue(groupNameIsExist);
      //  Assert.assertFalse(groupNameIsExist);
        
    }

    @Test 
    public void DeleteResourceByButtonAndCheckInMonitoringTest() throws InterruptedException {
        logger=report.startTest("DeleteResourceByButtonAndCheckInMonitoringTest");

        logger.log(LogStatus.INFO,"Click on Expand All button");
        groupsHierarchyPage.ClickOnExpandAllButton();

        int resourcesSize = groupsHierarchyPage.GetResourcesInGroupSize();
        if(resourcesSize>0){
            int index = groupsHierarchyPage.GetRandomDigit(0, resourcesSize);
            String resourceName = groupsHierarchyPage.GetResourceNameInGroupByIndex(index);

            logger.log(LogStatus.INFO,"Click on resource "+resourceName);
            groupsHierarchyPage.ClickToResourceInGroupByIndex(index);
            Thread.sleep(1000);

            logger.log(LogStatus.INFO,"Click on Delete selected button");
            groupsHierarchyPage.ClickOnDeleteSelectedButton();

            logger.log(LogStatus.INFO,"Confirm resource removing");
            groupsHierarchyPage.ConfirmRemoving();

            logger.log(LogStatus.INFO,"Press Save");
            groupsHierarchyPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Click on Expand All button");
            groupsHierarchyPage.ClickOnExpandAllButton();

            logger.log(LogStatus.INFO,"Check that resource " +resourceName+" is deleted");
            int resourcesSizeActual = groupsHierarchyPage.GetResourcesInGroupSize();
            Assert.assertEquals(resourcesSizeActual, resourcesSize-1);

            logger.log(LogStatus.INFO,"Refresh");
            groupsHierarchyPage.Refresh();

            logger.log(LogStatus.INFO,"Click on Expand All button");
            groupsHierarchyPage.ClickOnExpandAllButton();

            logger.log(LogStatus.INFO,"Check that group is deleted after refresh");
            int resourcesSizeAfterRefresh = groupsHierarchyPage.GetResourcesInGroupSize();
            Assert.assertEquals(resourcesSizeAfterRefresh, resourcesSizeActual);
        }
        if(resourcesSize==0){
            logger.log(LogStatus.INFO,"There aren't resources inside the groups");
        }
    }

    @Test
    public void DeleteResourceByButtonAndCancelRemovingTest() throws InterruptedException {
        logger=report.startTest("DeleteResourceByButtonAndCancelRemovingTest");

        logger.log(LogStatus.INFO,"Click on Expand All button");
        groupsHierarchyPage.ClickOnExpandAllButton();

        int resourcesSize = groupsHierarchyPage.GetResourcesInGroupSize();
        if(resourcesSize>0){
            int index = groupsHierarchyPage.GetRandomDigit(0, resourcesSize);
            String resourceName = groupsHierarchyPage.GetResourceNameInGroupByIndex(index);

            logger.log(LogStatus.INFO,"Click on resource "+resourceName);
            groupsHierarchyPage.ClickToResourceInGroupByIndex(index);
            Thread.sleep(1000);

            logger.log(LogStatus.INFO,"Click on Delete selected button");
            groupsHierarchyPage.ClickOnDeleteSelectedButton();

            logger.log(LogStatus.INFO,"Cancel resource removing");
            groupsHierarchyPage.CancelRemoving();

            logger.log(LogStatus.INFO,"Check that resource " +resourceName+" isn't deleted");
            int resourcesSizeActual = groupsHierarchyPage.GetResourcesInGroupSize();
            Assert.assertEquals(resourcesSizeActual, resourcesSize);

            logger.log(LogStatus.INFO,"Refresh");
            groupsHierarchyPage.Refresh();

            logger.log(LogStatus.INFO,"Click on Expand All button");
            groupsHierarchyPage.ClickOnExpandAllButton();

            logger.log(LogStatus.INFO,"Check that resource isn't deleted after refresh");
            int resourcesSizeAfterRefresh = groupsHierarchyPage.GetResourcesInGroupSize();
            Assert.assertEquals(resourcesSizeAfterRefresh, resourcesSize);
        }
        if(resourcesSize==0){
            logger.log(LogStatus.INFO,"There aren't resources inside the groups");
        }
    }

    @Test
    public void DragResourceToGroupTest() throws Exception {
        logger=report.startTest("DragResourceToGroupTest");

        logger.log(LogStatus.INFO,"Click on Expand All button");
        groupsHierarchyPage.ClickOnExpandAllButton();

        int resourcesSize = groupsHierarchyPage.GetResourcesSize();
        int resourcesInGroupsSize = groupsHierarchyPage.GetResourcesInGroupSize();
        int groupsSize = groupsHierarchyPage.GetGroupsSize();
        if(resourcesSize>0 && groupsSize>0){
            int indexResource =groupsHierarchyPage.GetRandomDigit(0, resourcesSize);
            int indexGroup = groupsHierarchyPage.GetRandomDigit(0, groupsSize);
            String resourceName = groupsHierarchyPage.GetResourceTextBYIndex(indexResource);
            String resourceId = groupsHierarchyPage.GetResourceIDByIndex(indexResource);
            String groupName = groupsHierarchyPage.GetGroupNameByIndex(indexGroup);
            String groupId = groupsHierarchyPage.GetGroupIDByIndex(indexGroup);

            logger.log(LogStatus.INFO,"Drag resources "+resourceName + " to group "+groupName);
            groupsHierarchyPage.DragAndDropCameraToGroup(resourceId,groupId);

            logger.log(LogStatus.INFO,"Press Save");
            groupsHierarchyPage.PressSaveButton();

            logger.log(LogStatus.INFO,"Click on Expand All button");
            groupsHierarchyPage.ClickOnExpandAllButton();

            logger.log(LogStatus.INFO,"Check that resource ia added");
            int resourcesInGroupsSizeActual = groupsHierarchyPage.GetResourcesInGroupSize();
            Assert.assertEquals(resourcesInGroupsSizeActual, resourcesInGroupsSize+1);

            logger.log(LogStatus.INFO,"Refresh");
            groupsHierarchyPage.Refresh();

            logger.log(LogStatus.INFO,"Click on Expand All button");
            groupsHierarchyPage.ClickOnExpandAllButton();

            logger.log(LogStatus.INFO,"Check that resource ia added");
            int resourcesInGroupsSizeAfterRefresh = groupsHierarchyPage.GetResourcesInGroupSize();
            Assert.assertEquals(resourcesInGroupsSizeAfterRefresh, resourcesInGroupsSize+1);

            logger.log(LogStatus.INFO,"Go to Monitoring page");
            groupsHierarchyPage.GoToMonitoringPage();

            logger.log(LogStatus.INFO,"Input into filter field resource name "+resourceName);
            monitoringPage.FilterField(resourceName);

            logger.log(LogStatus.INFO,"Check that added resource inside group in Monitoring Resources list");
            boolean groupNameIsExist = monitoringPage.VerifyThatGroupIsExist(groupName);
            //Assert.assertTrue(groupNameIsExist);
            Assert.assertFalse(groupNameIsExist);
        }
        if(resourcesSize==0){
            logger.log(LogStatus.INFO,"There aren't resources inside the groups");
        }
    }

   @Test(enabled = false)
    public void DragGroupToGroupTest() throws Exception {
        logger=report.startTest("DragGroupToGroupTest");

        logger.log(LogStatus.INFO,"Click on Expand All button");
        groupsHierarchyPage.ClickOnExpandAllButton();

        int resourcesAndGroupsSize = groupsHierarchyPage.GetResourceGroupsSize();
        int groupsSize = groupsHierarchyPage.GetGroupsSize();
        if(groupsSize>0){
            int indexResourceGroup =groupsHierarchyPage.GetRandomDigit(0, resourcesAndGroupsSize);
            String resourceOrGroupName = groupsHierarchyPage.GetResourceGroupTextBYIndex(indexResourceGroup);
            String resourceOrGroupId = groupsHierarchyPage.GetResourceOrGroupIDByIndex(indexResourceGroup);

            int indexGroup = 0;
            String groupName=null;
            for(int i=0; i<resourcesAndGroupsSize; i++){
                indexGroup = groupsHierarchyPage.GetRandomDigit(0, groupsSize);
                groupName = groupsHierarchyPage.GetGroupNameByIndex(indexGroup);
                if(!groupName.equals(resourceOrGroupName)) break;
            }
            String groupId = groupsHierarchyPage.GetGroupIDByIndex(indexGroup);
            logger.log(LogStatus.INFO,"Drag resource or group "+resourceOrGroupName + " to group "+groupName);
            groupsHierarchyPage.DragAndDropGroupToGroup(resourceOrGroupId,groupId);

            boolean saveIsEnabled = groupsHierarchyPage.SaveIsEnabled();
            if(saveIsEnabled){
                logger.log(LogStatus.INFO,"Press Save");
                groupsHierarchyPage.PressSaveButton();

                logger.log(LogStatus.INFO,"Click on Expand All button");
                groupsHierarchyPage.ClickOnExpandAllButton();
                Thread.sleep(1000);

                logger.log(LogStatus.INFO,"Get group index in list");
                int indexGroupInList=0;
                for(int i=0; i<resourcesAndGroupsSize; i++){
                    String resourceOrGroupIDByIndex = groupsHierarchyPage.GetResourceOrGroupIDByIndex(i);
                    if(resourceOrGroupIDByIndex.equals(groupId)){
                        indexGroupInList = i;
                        break;
                    }
                }
                logger.log(LogStatus.INFO,"Get next group or resource that isn't parent for group "+groupName);
                int groupStyle = groupsHierarchyPage.GetGroupOrResourcesStyleByIndex(indexGroupInList);
                int siblingIndex=0;
                for(int i=indexGroupInList+1; i<resourcesAndGroupsSize; i++){
                    int style = groupsHierarchyPage.GetGroupOrResourcesStyleByIndex(i);
                    if(style<=groupStyle){
                        siblingIndex = i;
                        break;
                    }
                }
                logger.log(LogStatus.INFO,"Get group or resource "+resourceOrGroupName+" index");
                for(int i=0; i<resourcesAndGroupsSize; i++){
                    String resourceOrGroupIDByIndex = groupsHierarchyPage.GetResourceOrGroupIDByIndex(i);
                    if(resourceOrGroupIDByIndex.equals(resourceOrGroupId)){
                        indexResourceGroup=i;
                        break;
                    }
                }
                logger.log(LogStatus.INFO,"Check that draged resource "+resourceOrGroupName+" index between group "+groupName+" index and his sibling group");
                if(siblingIndex!=0)Assert.assertTrue( indexResourceGroup>indexGroupInList && indexResourceGroup<=siblingIndex );// must to be  indexResourceGroup<siblingIndex
                if(siblingIndex==0)Assert.assertTrue( indexResourceGroup>indexGroupInList);

                logger.log(LogStatus.INFO,"Refresh");
                groupsHierarchyPage.Refresh();
//
                logger.log(LogStatus.INFO,"Click on Expand All button");
                groupsHierarchyPage.ClickOnExpandAllButton();

                logger.log(LogStatus.INFO,"Get group or resource "+resourceOrGroupName+" index after refresh");
                int indexAfterRefresh=0;
                for(int i=0; i<resourcesAndGroupsSize; i++){
                    String resourceOrGroupIDByIndex = groupsHierarchyPage.GetResourceOrGroupIDByIndex(i);
                    if(resourceOrGroupIDByIndex.equals(resourceOrGroupId)){
                        indexAfterRefresh=i;
                        break;
                    }
                }
                logger.log(LogStatus.INFO,"Check that resource "+resourceOrGroupName+ " index isn't changed after refresh ");
                Assert.assertEquals(indexAfterRefresh,indexResourceGroup);
            }
            if(saveIsEnabled) {
                logger.log(LogStatus.INFO,"Drag and drop isn't done");
            }
        }
        if(groupsSize==0){
            logger.log(LogStatus.INFO,"There aren't groups");
        }
    }

    @Test
    public void AddSubGroupTest() throws InterruptedException {
        logger=report.startTest("AddSubGroupTest");

        logger.log(LogStatus.INFO,"Click on Expand All button");
        groupsHierarchyPage.ClickOnExpandAllButton();

        int groupsSize = groupsHierarchyPage.GetGroupsSize();
        if(groupsSize==0){
            logger.log(LogStatus.INFO,"Click on Add Group button ");
            groupsHierarchyPage.ClickOnAddGroupButton();

            logger.log(LogStatus.INFO,"Press Save");
            groupsHierarchyPage.PressSaveButton();
            groupsSize = groupsHierarchyPage.GetGroupsSize();
        }

        int indexGroup = groupsHierarchyPage.GetRandomDigit(0, groupsSize);
        int resourcesAndGroupsSize = groupsHierarchyPage.GetResourceGroupsSize();
        String groupID = groupsHierarchyPage.GetGroupIDByIndex(indexGroup);
        String groupName = groupsHierarchyPage.GetGroupNameByIndex(indexGroup);

        logger.log(LogStatus.INFO,"Get group index in list");
        int indexGroupInList=0;
        for(int i=0; i<resourcesAndGroupsSize; i++){
            String resourceOrGroupIDByIndex = groupsHierarchyPage.GetResourceOrGroupIDByIndex(i);
            if(resourceOrGroupIDByIndex.equals(groupID)){
                indexGroupInList = i;
                break;
            }
        }
        logger.log(LogStatus.INFO,"Get next group or resource that isn't parent for group "+groupName);
        int groupStyle = groupsHierarchyPage.GetGroupOrResourcesStyleByIndex(indexGroupInList);
        int siblingIndex=0;
        for(int i=indexGroupInList+1; i<resourcesAndGroupsSize; i++){
            int style = groupsHierarchyPage.GetGroupOrResourcesStyleByIndex(i);
            if(style<=groupStyle){
                siblingIndex = i;
                break;
            }
        }
        logger.log(LogStatus.INFO,"Move to group "+groupName);
        groupsHierarchyPage.MoveToGroupResourceByIndex(indexGroupInList);

        logger.log(LogStatus.INFO,"Click on ADD icon");
        groupsHierarchyPage.ClickOnAddSubGroupIcon();
        groupsHierarchyPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Press Save");
        groupsHierarchyPage.PressSaveButton();
//        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Click on Expand All button");
        groupsHierarchyPage.ClickOnExpandAllButton();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that group is added");
        int sizeActual = groupsHierarchyPage.GetResourceGroupsSize();
        Assert.assertEquals(sizeActual,resourcesAndGroupsSize+1);

        logger.log(LogStatus.INFO,"Get group index in list");
        int indexGroupInListAct=0;
        for(int i=0; i<resourcesAndGroupsSize; i++){
            String resourceOrGroupIDByIndex = groupsHierarchyPage.GetResourceOrGroupIDByIndex(i);
            if(resourceOrGroupIDByIndex.equals(groupID)){
                indexGroupInListAct = i;
                break;
            }
        }
        int groupStyleAct = groupsHierarchyPage.GetGroupOrResourcesStyleByIndex(indexGroupInList);

        logger.log(LogStatus.INFO,"Check that parent group index isn't changed");
        Assert.assertEquals(indexGroupInListAct ,indexGroupInList );
        Assert.assertEquals(groupStyleAct , groupStyle);

        logger.log(LogStatus.INFO,"Get next group or resource that isn't parent for group "+groupName);
        int siblingIndexAct=0;
        for(int i=indexGroupInList+1; i<resourcesAndGroupsSize; i++){
            int style = groupsHierarchyPage.GetGroupOrResourcesStyleByIndex(i);
            if(style<=groupStyle){
                siblingIndexAct = i;
                break;
            }
        }
        logger.log(LogStatus.INFO,"Check that added group inside "+groupName);
        if(siblingIndex!=0) Assert.assertEquals(siblingIndexAct ,siblingIndex+1);
        if(siblingIndex==0) Assert.assertEquals(siblingIndexAct ,0);

        logger.log(LogStatus.INFO,"Refresh");
        groupsHierarchyPage.Refresh();
//
        logger.log(LogStatus.INFO,"Click on Expand All button");
        groupsHierarchyPage.ClickOnExpandAllButton();

        logger.log(LogStatus.INFO,"Check that group is added after refresh");
        int sizeAftRefresh = groupsHierarchyPage.GetResourceGroupsSize();
        Assert.assertEquals(sizeAftRefresh,resourcesAndGroupsSize+1);

        logger.log(LogStatus.INFO,"Get group index in list");
        int indexGroupInListAfterRefresh=0;
        for(int i=0; i<resourcesAndGroupsSize; i++){
            String resourceOrGroupIDByIndex = groupsHierarchyPage.GetResourceOrGroupIDByIndex(i);
            if(resourceOrGroupIDByIndex.equals(groupID)){
                indexGroupInListAfterRefresh = i;
                break;
            }
        }
        int groupStyleAftRefersh = groupsHierarchyPage.GetGroupOrResourcesStyleByIndex(indexGroupInList);

        logger.log(LogStatus.INFO,"Check that parent group index isn't changed");
        Assert.assertEquals(indexGroupInListAfterRefresh ,indexGroupInList );
        Assert.assertEquals(groupStyleAftRefersh , groupStyle);

        logger.log(LogStatus.INFO,"Get next group or resource that isn't parent for group "+groupName);
        int siblingIndexAftRefresh=0;
        for(int i=indexGroupInList+1; i<resourcesAndGroupsSize; i++){
            int style = groupsHierarchyPage.GetGroupOrResourcesStyleByIndex(i);
            if(style<=groupStyle){
                siblingIndexAftRefresh = i;
                break;
            }
        }
        logger.log(LogStatus.INFO,"Check that added group inside "+groupName);
        if(siblingIndexAftRefresh!=0) Assert.assertEquals(siblingIndexAftRefresh ,siblingIndex+1 );
        if(siblingIndexAftRefresh==0) Assert.assertEquals(siblingIndexAftRefresh ,0);
    }

    @AfterMethod
    public void afterMethod(ITestResult result){
        if(result.getStatus()==ITestResult.SUCCESS){
            logger.log(LogStatus.PASS, "Test is passed");
        }
        if(result.getStatus()==ITestResult.FAILURE){
            groupsHierarchyPage.takeScreenshot(driver, "GroupsHierarchy", result.getName());
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
