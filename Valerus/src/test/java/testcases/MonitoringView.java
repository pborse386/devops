package testcases;


import pageObjects.CamerasAndEncodersPage;
import pageObjects.ViewsPage;
import pageObjects.MonitoringPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
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


public class MonitoringView {
    public WebDriver driver;
    public MonitoringPage monitoringPage;
    public ViewsPage viewsPage;
    CamerasAndEncodersPage camerasAndEncodersPage;
    public String[] Servers;
    ExtentReports report;
    ExtentTest logger;

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void setup(@Optional("chrome")String browser) throws IOException, InterruptedException {
        String WebDriverLocation = System.getenv("WebDriver");

        if(browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", WebDriverLocation+"\\chromedriver.exe");
            driver = new ChromeDriver();
        }else if (browser.equalsIgnoreCase("ie")) {
            System.setProperty("webdriver.ie.driver", WebDriverLocation+"\\IEDriverServer.exe");
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            capabilities.setCapability(InternetExplorerDriver.IE_USE_PER_PROCESS_PROXY, true);
          //capabilities.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
            capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
            capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
            capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL,false);
            //capabilities.setCapability("initialBrowserUrl", "https://my-page:9443");
            capabilities.setCapability("nativeEvents", false);
         // driver = WebDriverFactory.getDriver(DesiredCapabilities.internetExplorer());
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
        
       // viewsPage.GoToViewsPageFromLanding();
    }

    @BeforeTest
    public void startReport(){
        report=new ExtentReports(System.getProperty("user.dir") +"/test-output/MonitorViews/MonitorViewsReport.html", true);
    }

   
    
    @BeforeMethod
    public void GoToViewPage() throws InterruptedException, IOException {

     String WebDriverLocation = System.getenv("WebDriver");

       System.setProperty("webdriver.ie.driver", WebDriverLocation+"\\IEDriverServer.exe");
       DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
    //    capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
    //   capabilities.setCapability(InternetExplorerDriver.IE_USE_PER_PROCESS_PROXY, true);
   //     capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
   //     capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
    //    capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL,false);
   //    driver = new InternetExplorerDriver(capabilities);

   //     driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
    //    driver.manage().window().maximize();
    //    monitoringPage = PageFactory.initElements(driver, MonitoringPage.class);
   //    viewsPage = PageFactory.initElements(driver, ViewsPage.class);
    //   camerasAndEncodersPage = PageFactory.initElements(driver, CamerasAndEncodersPage.class);
   //    Servers = viewsPage.getServersList();

        driver.navigate().to("http://" + Servers[0]);
        try{
            driver.switchTo().alert().accept();
        }catch(Exception a){}

       // viewsPage.WaitUntilLoadingBlockAppears();
       // viewsPage.WaitUntilLoadingBlockDisappears();
       // viewsPage.GoToViewsPage();
    }

    @Test (enabled=false)
    public void changelayoutTest() throws Exception {
       
                logger=report.startTest("Change Layout Test");
    	 	 		                   
                Thread.sleep(1000);
                
                
                logger.log(LogStatus.INFO,"Click on Change Layout Button ");
                
                monitoringPage.changeLayoutButton.click();
                    
             
    
    		//   int paneCountAct= viewsPage.GetPaneViewListSize();  
    		  
    		  viewsPage.WaitUntilDialogIsLocated();
                   
                int layoutSizeInDialog = viewsPage.GetSizeLayoutInDialog();
                int index = 0;
               
                    int randLayout = viewsPage.GetRandomDigit(0, layoutSizeInDialog);
                   // int paneCount= viewsPage.GetPanesSizeInLayoutInDialogByIndex(randLayout+1);

                    index = randLayout;
                        
                 
                
                logger.log(LogStatus.INFO,"Click on Layout with index "+index);
                int paneCount= viewsPage.GetPanesSizeInLayoutInDialogByIndex(index+1);
                viewsPage.ClickOnLayoutInDialogByIndex(index);
                viewsPage.WaitUntilDialogIsNotLocated();

                logger.log(LogStatus.INFO,"Check that dialog is closed ");
                Assert.assertFalse(viewsPage.CheckThatModalIsOpen());
  
            }
    
    
   // @Test
         public void stopAll() throws InterruptedException {
    	
    	  logger.log(LogStatus.INFO,"Click on stop all view button");
        
           viewsPage.clickOnStopAllbutton();

   
           
    }
    
         
         
     @Test 
     
     public void addViewTest() throws InterruptedException {
    	 
    	     	 logger=report.startTest("Add View Test");
           
                 Thread.sleep(1000);
    	 
    	         logger.log(LogStatus.INFO,"Click on + view button " );
         
		       //WebElement addview = driver.findElement(By.xpath("//*[@id=\"vms-create-layout-tab-button\"]")); 
		    
		       //click();
		   
                 viewsPage.ClickOnAddViewSign();
		    
                 logger.log(LogStatus.INFO,"select layout" );
                 
                 viewsPage.WaitUntilDialogIsLocated();
                 
                 int layoutSizeInDialog = viewsPage.GetSizeLayoutInDialog1();
                 int index = 0;
                
                    // int randLayout = viewsPage.GetRandomDigit(0, layoutSizeInDialog);
                    // int paneCount= viewsPage.GetPanesSizeInLayoutInDialogByIndex(randLayout+1);

                                         
                     int randLayout = viewsPage.GetRandomDigit(0, layoutSizeInDialog);
                     index = randLayout;
                    
                                  
                 logger.log(LogStatus.INFO,"Click on Layout with index "+index);
                 int paneCount= viewsPage.GetPanesSizeInLayoutInDialogByIndex(index+1);
                 viewsPage.ClickOnLayoutInDialogByIndex1(index);
                 viewsPage.WaitUntilDialogIsNotLocated();

                 logger.log(LogStatus.INFO,"Check that dialog is closed ");
                 Assert.assertFalse(viewsPage.CheckThatModalIsOpen());
                 
                 
               
                 
                 
		        
     }
     
     @Test(enabled=false)
     public void DragResourceTest() throws Exception {
         logger=report.startTest("DragResourceTest");

         int viewsSize = viewsPage.GetViewsSize();
         Thread.sleep(1000);
         String viewName=null;
         int fullViewSize=0;
         
                 viewName="View";
                
                 logger.log(LogStatus.INFO,"view_1");
         
                                            

                 logger.log(LogStatus.INFO,"emptyviewsize");

                 int viewSize = viewsPage.GetSizeEmptyViews();
                 
                 System.out.println(viewSize);
                 
                 
                 logger.log(LogStatus.INFO,"resourcesize");

                 int resourcesSize = viewsPage.GetResourcesSize1();
                 
                 System.out.println(resourcesSize);

                 logger.log(LogStatus.INFO,"Drag resource");
                 
                 
                 int viewIndex = viewsPage.GetRandomDigit(0, 5);
                 
                 System.out.println("viewindex" + viewIndex);
                 
                 String viewId = viewsPage.GetViewIDByIndex(viewIndex);
                 
                 monitoringPage.DragAndDropCameraToView(viewId);
                 
                 
                 int resIndex = viewsPage.GetRandomDigit(0, resourcesSize);
                 
              //   String viewId = viewsPage.GetViewIDByIndex1(viewIndex);
                 
                 System.out.println("viewid" + viewId);
                 
                 
                 
                 System.out.println(viewId);
                 
                 String resourceId = viewsPage.GetResourcesIDByIndex1(resIndex);
                 
                 System.out.println(resourceId);
                 
                 
                 
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
     
           
         
    
        
    
    @Test(enabled = false)
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

    @Test(enabled = false)
    public void ResourceFilterTest() throws InterruptedException {
        logger=report.startTest("ResourceFilterTest");

      
            Thread.sleep(5000);

            int resourcesSize = viewsPage.GetResourcesSize1();
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
    
    @Test (enabled = false)
    public void ClearViewAndDragResourceTest() throws Exception {
        
    	logger=report.startTest("ClearViewAndDragResourceTest");

               Thread.sleep(1000);
       
                       
                logger.log(LogStatus.INFO,"Click stop all button");
                viewsPage.clickOnStopAllbutton();

                int viewSize = viewsPage.GetLayoutViewSize1();
                int resourcesSize = viewsPage.GetResourcesSize1();
                int rand = viewsPage.GetRandomDigit(1, viewSize);

                logger.log(LogStatus.INFO,"Drag resource");
                
                logger.log(LogStatus.INFO,"Drag " + rand+" resources ");
                for(int i=0; i<rand; i++){
                    int viewIndex = viewsPage.GetRandomDigit(0, viewSize);
                    int resIndex = viewsPage.GetRandomDigit(0, resourcesSize);
                    String viewId = viewsPage.GetViewIDByIndex1(viewIndex);
                    String resourceId = viewsPage.GetResourcesIDByIndex1(resIndex);
                    viewsPage.DragAndDropCameraToView(resourceId , viewId);
                }
             

                logger.log(LogStatus.INFO,"Refresh page ");
                viewsPage.Refresh();

              
                Thread.sleep(5000);

              
            }
        
    
    
    
    

   
   

    @Test (enabled = false)
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

    @Test(enabled = false)
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

    @Test(enabled = false)
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

    @Test(enabled = false)
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

    @Test(enabled = false)
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

    @Test(enabled = false)
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

    @Test(enabled = false)
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
    }

    @AfterClass
    public void endReport(){
        report.flush();
        report.close();

        driver.close();
        driver.quit();
    }
}
