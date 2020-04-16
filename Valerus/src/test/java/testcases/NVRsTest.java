package testcases;

import pageObjects.MonitoringPage;
import pageObjects.NVRsPage;
import pageObjects.ViewsPage;
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
import Search.SearchPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NVRsTest {
    public WebDriver driver;
    public NVRsPage nvRsPage;
    public String[] Servers;
    ExtentReports report;
    ExtentTest logger;

   @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void setup(@Optional("ie")String browser) throws IOException, InterruptedException {
    }

    @BeforeTest
    public void startReport(){
        report=new ExtentReports(System.getProperty("user.dir") +"/test-output/NVRs/NVRsReport.html", true);
    }

    @BeforeMethod(alwaysRun = true)
    public void gotoNVRPage() throws Exception {
        String WebDriverLocation = System.getenv("WebDriver");

    //    System.setProperty("webdriver.chrome.driver", WebDriverLocation +"\\chromedriver.exe");
    //    driver = new ChromeDriver();

       System.setProperty("webdriver.ie.driver", WebDriverLocation+"\\IEDriverServer.exe");
       DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
       capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
       capabilities.setCapability(InternetExplorerDriver.IE_USE_PER_PROCESS_PROXY, true);
       capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
       capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
       driver = new InternetExplorerDriver(capabilities); 
                      
             

        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
        nvRsPage = PageFactory.initElements(driver, NVRsPage.class);
        Servers = nvRsPage.getServersList();

        driver.navigate().to("http://" + Servers[0]);
        nvRsPage.SignIn();
        nvRsPage.goToNVRsPageFromLandingPage();
        
       // nvRsPage.goToNVRsPageFromLandingPage1();
        //try{
        //    driver.switchTo().alert().accept();
        //}catch(Exception a){}

        //boolean loginPage= nvRsPage.LogInPageIsLoaded();
        //if(loginPage){
        //    logger.log(LogStatus.INFO,"Sign In with admin user name");
        //    nvRsPage.SignIn();
       // }

       // boolean nvrsPage = nvRsPage.NVRsPageIsLoaded();
       //if(!nvrsPage) nvRsPage.goToNVRsPage();
    }
    
      
    
       
    

  // Tests for Add Discovered NVRs
    @Test
    public void AddNVRAndCheckStatusVTest() throws InterruptedException {
       logger=report.startTest("AddNVRAndCheckStatusVTest");

       String IPAdress =Servers[1];
       logger.log(LogStatus.INFO,"Adding NVR " + IPAdress);

        if(nvRsPage.verifyElementIsPresent(nvRsPage.FindElementByText(IPAdress))){
            logger.log(LogStatus.INFO,"Delete NVR" + IPAdress);
            nvRsPage.FindElementByText(IPAdress).click();
            Thread.sleep(1000);
            nvRsPage.PressOnRemoveNVRsByButton();
            nvRsPage.ConfirmRemoveNVRsByButton();
//            nvRsPage.WaitUntilRemovedNVRWillBeAddedToDiscoveredNVRsList();
            logger.log(LogStatus.INFO,"NVR" + IPAdress+" is deleted");
        }

       int countBefore = nvRsPage.NVRsCount();
       nvRsPage.AddDiscoveredNVRs(IPAdress);
       logger.log(LogStatus.INFO,"NVR" + IPAdress+" is added");

        nvRsPage.waitUntilStatusIsV(IPAdress);
        int countAfter = nvRsPage.NVRsCount();

        Assert.assertEquals(countAfter, countBefore+1);
        logger.log(LogStatus.INFO,"NVR " + IPAdress+ " is added in the Centaral Panel list");

        String status =  nvRsPage.GetStatus(IPAdress);
        Assert.assertEquals(status, "V");
        logger.log(LogStatus.INFO, "NVR status is Online");
    }

    @Test
    public void AddNVRWithSecuredPortTest() throws InterruptedException {
        logger=report.startTest("AddNVRWithSecuredPortTest");

        String IPAdress =Servers[1];
        String IPAdress2 =Servers[1];

        if(nvRsPage.verifyElementIsPresent(nvRsPage.FindElementByText(IPAdress))){
            logger.log(LogStatus.INFO,"Delete NVR" + IPAdress);
            nvRsPage.FindElementByText(IPAdress).click();
            Thread.sleep(1000);
            nvRsPage.PressOnRemoveNVRsByButton();
            nvRsPage.ConfirmRemoveNVRsByButton();
                      nvRsPage.WaitUntilRemovedNVRWillBeAddedToDiscoveredNVRsList();// commented 
            logger.log(LogStatus.INFO,"NVR" + IPAdress+" is deleted");
        }

        logger.log(LogStatus.INFO,"Adding NVR " + IPAdress +"with 443 port");
        nvRsPage.AddDiscoveredSecuredNVRs(IPAdress);
        nvRsPage.waitUntilStatusIsV(IPAdress);

        logger.log(LogStatus.INFO,"Click on NVR " + IPAdress );
        nvRsPage.ClickOnNvrByText(IPAdress);
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Get properties for NVR " + IPAdress );
        String port = nvRsPage.GetPortFromProperties();
        String secured = nvRsPage.GetSecuredStatus();
        String status= nvRsPage.GetStatus(IPAdress);

        logger.log(LogStatus.INFO,"Check that port for NVR " + IPAdress+" is 443");
        Assert.assertEquals(port, "443");

        logger.log(LogStatus.INFO,"Check that secured status for NVR " + IPAdress+" is ON");
        Assert.assertEquals(secured, "ON");

        logger.log(LogStatus.INFO,"Check that status for NVR " + IPAdress+" is Online");
        Assert.assertEquals(status, "V");

        logger.log(LogStatus.INFO,"Refresh page");
        nvRsPage.Refresh();

        logger.log(LogStatus.INFO,"Click on NVR " + IPAdress );
        nvRsPage.ClickOnNvrByText(IPAdress);
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that port for NVR " + IPAdress+" is 443");
        String portAfterRefresh = nvRsPage.GetPortFromProperties();
        Assert.assertEquals(portAfterRefresh, "443");

        logger.log(LogStatus.INFO,"Check that secured status for NVR " + IPAdress+" is ON");
        String securedAfterRefresh = nvRsPage.GetSecuredStatus();
        Assert.assertEquals(securedAfterRefresh, "ON");

        logger.log(LogStatus.INFO,"Check that status for NVR " + IPAdress+" is Online");
        String statusAfterRefresh= nvRsPage.GetStatus(IPAdress);
        Assert.assertEquals(statusAfterRefresh, "V");
    }

    @Test(enabled =false)
    public void AddNVRWithSecuredPortAndSwitchOffSecuredStatusTest() throws InterruptedException {
        logger=report.startTest("AddNVRWithSecuredPortAndSwitchOffSecuredStatusTest");

        String IPAdress =Servers[1];

        if(nvRsPage.verifyElementIsPresent(nvRsPage.FindElementByText(IPAdress))){
            logger.log(LogStatus.INFO,"Delete NVR" + IPAdress);
            nvRsPage.FindElementByText(IPAdress).click();
            Thread.sleep(1000);
            nvRsPage.PressOnRemoveNVRsByButton();
            nvRsPage.ConfirmRemoveNVRsByButton();
//            nvRsPage.WaitUntilRemovedNVRWillBeAddedToDiscoveredNVRsList();
            logger.log(LogStatus.INFO,"NVR" + IPAdress+" is deleted");
        }

        logger.log(LogStatus.INFO,"Adding NVR " + IPAdress +"with 443 port");
        nvRsPage.AddDiscoveredSecuredNVRs(IPAdress);
        nvRsPage.waitUntilStatusIsV(IPAdress);

        logger.log(LogStatus.INFO,"Click on NVR " + IPAdress );
        nvRsPage.ClickOnNvrByText(IPAdress);
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Switch Off secured toggle switch ");
        nvRsPage.ClickOnSecuredSwitch();

        logger.log(LogStatus.INFO,"Click Save button");
        nvRsPage.SaveChangesButton();
        nvRsPage.waitUntilStatusIsText(IPAdress, "Communication Failure");
        nvRsPage.waitUntilStatusIsText(IPAdress, "Communication Failure");
        nvRsPage.waitUntilStatusIsText(IPAdress, "Communication Failure");

        logger.log(LogStatus.INFO,"Get properties for NVR " + IPAdress );
        String port = nvRsPage.GetPortFromProperties();
        String secured = nvRsPage.GetSecuredStatus();
        String status= nvRsPage.GetStatus(IPAdress);

        logger.log(LogStatus.INFO,"Check that port for NVR " + IPAdress+" is 443");
        Assert.assertEquals(port, "443");

        logger.log(LogStatus.INFO,"Check that secured status for NVR " + IPAdress+" is ON");// changes made
        Assert.assertEquals(secured, "ON");

       logger.log(LogStatus.INFO,"Check that status for NVR " + IPAdress+" is Communacation Failure");
        Assert.assertEquals(status, "Communication Failure");

        logger.log(LogStatus.INFO,"Refresh page");
        nvRsPage.Refresh();
              
        logger.log(LogStatus.INFO,"Click on NVR " + IPAdress );
        nvRsPage.ClickOnNvrByText(IPAdress);
        Thread.sleep(1000);
        
        logger.log(LogStatus.INFO,"Check that port for NVR " + IPAdress+" is 443");
        String portAfterRefresh = nvRsPage.GetPortFromProperties();
        Assert.assertEquals(portAfterRefresh, "443");
        
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that secured status for NVR " + IPAdress+" is ON");
        String securedAfterRefresh = nvRsPage.GetSecuredStatus();
        Assert.assertEquals(securedAfterRefresh, "ON");
        
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that status for NVR " + IPAdress+" is Communacation Failure");
        String statusAfterRefresh= nvRsPage.GetStatus(IPAdress);
        Assert.assertEquals(statusAfterRefresh, "Communication Failure");
    }

    @Test
    public void FilterDiscoveredNVRsTest() throws IOException, InterruptedException {
        logger=report.startTest("FilterDiscoveredNVRsTest");
        nvRsPage.ClickOnAddDiscoveredNVRSButton();
        Thread.sleep(1000);
        logger.log(LogStatus.INFO, "Discovered NVRs window is open");

        int size = nvRsPage.GetSizeDiscoveredNVRsList();
        int random =nvRsPage.GetRandomDigit(0, size);
        int index = nvRsPage.GetTextDiscoveredNVR(random).indexOf(":");
        String name = nvRsPage.GetTextDiscoveredNVR(random).substring(0,index);

        int length = name.length();
        Thread.sleep(1000);
        logger.log(LogStatus.INFO, "Select device " + name + " for using in Filter");

        nvRsPage.FilterDiscoveredNVRs(name);
        logger.log(LogStatus.INFO, "Device " + name + " is inputed in Filter field");

        size = nvRsPage.GetSizeDiscoveredNVRsList();

        String filteredName = nvRsPage.GetTextDiscoveredNVR(0).substring(0, length);

        logger.log(LogStatus.INFO, "Assert that displayed NVR " +filteredName+ "is same to inputed NVR "+ name );
        if(!filteredName.contains("1.1.1.10")) Assert.assertEquals(filteredName, name);

        logger.log(LogStatus.INFO, "Check that all displayed NVRs contain to inputed NVR "+ name );
        for(int i = 0; i<size; i++){
            filteredName = nvRsPage.GetTextDiscoveredNVR(i).substring(0, length);
            Assert.assertTrue(filteredName.contains(name));
        }
     }

    @Test(priority=1)
    public void MultipleNVRsAdditingTest() throws InterruptedException {
        logger=report.startTest("MultipleNVRsAdditingTest");

        String IPAdress1=Servers[0];
        String IPAdress2=Servers[1];

        if(nvRsPage.verifyElementIsPresent(nvRsPage.FindElementByText(IPAdress1))){
            logger.log(LogStatus.INFO,"Delete NVR" + IPAdress1);
            nvRsPage.FindElementByText(IPAdress1).click();
            Thread.sleep(1000);
            nvRsPage.PressOnRemoveNVRsByButton();
            nvRsPage.ConfirmRemoveNVRsByButton();
//            nvRsPage.WaitUntilRemovedNVRWillBeAddedToDiscoveredNVRsList();
        }

        if(nvRsPage.verifyElementIsPresent(nvRsPage.FindElementByText(IPAdress2))){
            logger.log(LogStatus.INFO,"Delete NVR" + IPAdress2);
            nvRsPage.FindElementByText(IPAdress2).click();
            Thread.sleep(1000);
            nvRsPage.PressOnRemoveNVRsByButton();
            nvRsPage.ConfirmRemoveNVRsByButton();
            Thread.sleep(3000);
        }

        int countBefore = nvRsPage.NVRsCount();
        logger.log(LogStatus.INFO,"Click on Add Discovered NVRs button");
        nvRsPage.ClickOnAddDiscoveredNVRSButton();

        int index1 = 0;
        int index2 = 0;

        int nvrsListSize = nvRsPage.GetSizeDiscoveredNVRsList();
        for(int i=0; i<nvrsListSize; i++){
           String text = nvRsPage.GetTextDiscoveredNVR(i);
           if(text.contains(IPAdress1)){
               index1=i;
           }
           if(text.contains(IPAdress2)){
               index2=i;
           }
           if(index1!=0 && index2!=0) break;
           
        }

        logger.log(LogStatus.INFO,"Select device "+IPAdress1);
        nvRsPage.ClickOnVDiscoveredNVRs(index1);

        nvRsPage.ScrollToDeviceByIndex(0);
        logger.log(LogStatus.INFO,"Select device "+IPAdress2);
        nvRsPage.ClickOnVDiscoveredNVRs(index2);

        logger.log(LogStatus.INFO,"Assert that Apply button is enabled");
        Assert.assertTrue(nvRsPage.ApplyButtonIsEnable());

        logger.log(LogStatus.INFO,"Assert that ApplyAndClose button is enabled");
        Assert.assertTrue(nvRsPage.ApplyAndCloseButtonIsEnable());

        logger.log(LogStatus.INFO,"Apply NVRs adding and Close window ");
        nvRsPage.ApplyAndCloseButtonDiscovereNVRs();
        int countAfter = nvRsPage.NVRsCount();
        
        Thread.sleep(2000);

        logger.log(LogStatus.INFO,"Assert that NVRs count increased by 2");
        Assert.assertEquals(countAfter, countBefore+2);
    }

    @Test
    public void AddNVRAndPressApplyTest() throws InterruptedException {
        logger=report.startTest("AddNVRAndPressApplyTest");
        String IPAdress =Servers[1];

        if(nvRsPage.verifyElementIsPresent(nvRsPage.FindElementByText(IPAdress))){
            logger.log(LogStatus.INFO,"Delete NVR" + IPAdress);
            nvRsPage.FindElementByText(IPAdress).click();
            Thread.sleep(1000);
            nvRsPage.PressOnRemoveNVRsByButton();
            nvRsPage.ConfirmRemoveNVRsByButton();
//            nvRsPage.WaitUntilRemovedNVRWillBeAddedToDiscoveredNVRsList();
            logger.log(LogStatus.INFO,"NVR" + IPAdress+" is deleted");
        }

        int countBefore = nvRsPage.NVRsCount();
        nvRsPage.ClickOnAddDiscoveredNVRSButton();
        nvRsPage.WaitUntilCountOfDiscoveredNVRsIsVIsible(IPAdress);
        logger.log(LogStatus.INFO,"Discovered NVRs window is open");

        logger.log(LogStatus.INFO,"Select NVR " +IPAdress+ " and Apply adding" );
        nvRsPage.SelectDiscoveredNVRs(IPAdress);
        nvRsPage.ApplyButtonDiscovereNVRs();

//        logger.log(LogStatus.INFO,"Assert that Apply button is enabled");
//        Assert.assertTrue(nvRsPage.ApplyButtonIsEnable());
//
//        logger.log(LogStatus.INFO,"Assert that ApplyAndClose button is enabled");
//        Assert.assertTrue(nvRsPage.ApplyAndCloseButtonIsEnable());

        int countAfter = nvRsPage.NVRsCount();

        logger.log(LogStatus.INFO,"Check that 'Discovered NVRs' window isn't closed");
        Assert.assertTrue(nvRsPage.DiscoveredNVRsWindowIsOpen());

        logger.log(LogStatus.INFO,"Assert that NVRs count increased by 1");
        Assert.assertEquals(countAfter, countBefore+1);
    }

    @Test 
    public void SelectNVRandPressCancelTest() throws InterruptedException {
        logger=report.startTest("SelectNVRandPressCancelTest");

        int countBefore = nvRsPage.NVRsCount();
        logger.log(LogStatus.INFO,"Open Discovered NVRs window");
        nvRsPage.ClickOnAddDiscoveredNVRSButton();

        logger.log(LogStatus.INFO,"Select discovered NVR randomaly");
        int random =nvRsPage.GetRandomDigit(0, nvRsPage.GetSizeVDiscoveredNVRsList()-1);
        nvRsPage.ClickOnVDiscoveredNVRs(random);

        logger.log(LogStatus.INFO,"Cancel NVR select");
        nvRsPage.CancelButtonDiscovereNVRs();
        int countAfter = nvRsPage.NVRsCount();

        logger.log(LogStatus.INFO,"Check that 'Discovered NVRs' window isn't closed");
        Assert.assertFalse(nvRsPage.DiscoveredNVRsWindowIsOpen());

        logger.log(LogStatus.INFO,"Cheking that NVR count after is equal to NVRs count before ");
        Assert.assertEquals(countAfter, countBefore);
    }

    @Test
    public void SortingDiscoveredNVRsTest() throws InterruptedException {
        logger=report.startTest("SortingDiscoveredNVRsTest");

        logger.log(LogStatus.INFO,"Open Discovered NVRs window");
        nvRsPage.ClickOnAddDiscoveredNVRSButton();
        Thread.sleep(1000);

        int size = nvRsPage.GetSizeDiscoveredNVRsList();
        logger.log(LogStatus.INFO,"Click on IP address in list header");
        nvRsPage.SortDiscoveredNVRs();
        Thread.sleep(1000);

        String firstElementText=nvRsPage.GetTextDiscoveredNVR(0);
        nvRsPage.SortDiscoveredNVRs();
        String lastElementText=nvRsPage.GetTextDiscoveredNVR(0);

        logger.log(LogStatus.INFO,"Go to the end of discovered nvrs list");
        while(true){
            size = nvRsPage.GetSizeDiscoveredNVRsList();

            String elem= nvRsPage.GetTextDiscoveredNVR(size-1);
            nvRsPage.scroll(nvRsPage.GetDiscoveredNVRsList(size-1));
            Thread.sleep(1000);

            size = nvRsPage.GetSizeDiscoveredNVRsList();
            int last = size-1;
            String lastElementAfterSortingText = nvRsPage.GetTextDiscoveredNVR(last);
            if(lastElementAfterSortingText.equals(elem)) break;
        }

        size = nvRsPage.GetSizeDiscoveredNVRsList();
        int last = size-1;
        String lastElementAfterSortingText = nvRsPage.GetTextDiscoveredNVR(last);

        logger.log(LogStatus.INFO,"Click on IP address in list header");
        nvRsPage.SortDiscoveredNVRs();
        last = nvRsPage.GetSizeDiscoveredNVRsList()-1;
        String firstElementAfterSortingText = nvRsPage.GetTextDiscoveredNVR(last);

        logger.log(LogStatus.INFO,"Check that first element became the last element after sorting ");
        Assert.assertEquals(firstElementText, lastElementAfterSortingText);
        Assert.assertEquals(lastElementText, firstElementAfterSortingText);
    }
//
//    //Tests for Add NVR Mannually

    @Test
    void AddNVRManuallyCheckStatusTest() throws InterruptedException {
        logger=report.startTest("AddNVRManuallyCheckStatusTest");

        String IPAdress = Servers[1];

        if(nvRsPage.verifyElementIsPresent(nvRsPage.FindElementByText(IPAdress))){
            logger.log(LogStatus.INFO,"Delete NVR" + IPAdress);
            nvRsPage.FindElementByText(IPAdress).click();
            Thread.sleep(1000);
            nvRsPage.PressOnRemoveNVRsByButton();
            nvRsPage.ConfirmRemoveNVRsByButton();
//            nvRsPage.WaitUntilRemovedNVRWillBeAddedToDiscoveredNVRsList();
            logger.log(LogStatus.INFO,"NVR" + IPAdress+" is deleted");
        }
        int countBefore = nvRsPage.NVRsCount();

        logger.log(LogStatus.INFO,"Open Add NVR Manually window");
        nvRsPage.PressAddNVRManuallyButton();

        logger.log(LogStatus.INFO,"Input into IPAddress field "+IPAdress);
        nvRsPage.InputIntoIPAdressField(IPAdress);

        logger.log(LogStatus.INFO,"Input into UserName field ADMIN");
        nvRsPage.InputIntoUserNameField("ADMIN");

        logger.log(LogStatus.INFO,"Input into password field 1234");
        nvRsPage.InputIntoPasswordField("1234");

        logger.log(LogStatus.INFO,"Apply NVR adding ");
        nvRsPage.PressApplyAndCloseButtonInAddNVRManually();
        nvRsPage.WaitUntilCountOfNVRsIs(countBefore+1);

        nvRsPage.waitUntilStatusIsV(IPAdress);
        int countAfter = nvRsPage.NVRsCount();

        logger.log(LogStatus.INFO,"Assert that NVRs count increased by 1");
        Assert.assertEquals(countAfter, countBefore+1);

        String status =  nvRsPage.GetStatus(IPAdress);
        logger.log(LogStatus.INFO,"Assert that added NVRs status is Online");
        Assert.assertEquals(status, "V");
    }

    @Test
    public void AddAlreadyUsedNVRManuallyTest() throws InterruptedException {
        logger=report.startTest("AddAlreadyUsedNVRManuallyTest");

        String IPAdress1 = Servers[0];

        logger.log(LogStatus.INFO,"add NVR " + IPAdress1+" if it isn't exists ");
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress1);

        WebElement nvr = null;
        logger.log(LogStatus.INFO,"Select NVR with Online status");
        for(int i = 0; i<nvRsPage.GetSizeNVRsList(); i++){
            WebElement element = nvRsPage.GetNVRFromCentralPanel(i);
            String status =  nvRsPage.GetStatus(element.getText());
            if(status.equals("V")){
                nvr = element;
                break;
            }
            else nvr=nvRsPage.GetNVRFromCentralPanel(0);
        }
        String IPAdress = nvr.getText();

        logger.log(LogStatus.INFO,"Get a port from selected NVR");
        nvRsPage.GetNVRFromCentralPanel(0).click();
        String port = nvRsPage.GetPortFromProperties();
        
        int countBefore = nvRsPage.NVRsCount();
        
        logger.log(LogStatus.INFO,"Open Add NVR Manually window");
        nvRsPage.PressAddNVRManuallyButton();

        logger.log(LogStatus.INFO,"Input into IPAddress field "+IPAdress);
        nvRsPage.InputIntoIPAdressField(IPAdress);
        
        logger.log(LogStatus.INFO,"Input into port field "+port);
        nvRsPage.InputIntoPortField(port);

        logger.log(LogStatus.INFO,"Input into UserName field ADMIN");
        nvRsPage.InputIntoUserNameField("ADMIN");

        logger.log(LogStatus.INFO,"Input into password field 1234");
        nvRsPage.InputIntoPasswordField("1234");

        logger.log(LogStatus.INFO,"Apply NVR adding ");
        Thread.sleep(1000);
        nvRsPage.PressApplyAndCloseButtonInAddNVRManually();

        logger.log(LogStatus.INFO,"Check that 'IP Address already exists in the system' notification appears ");
        Assert.assertTrue(nvRsPage.VerifyIpAlreadyInUseNotification());

        int countAfter = nvRsPage.NVRsCount();
        logger.log(LogStatus.INFO,"Confirm 'IP Address already exists in the system' notification");
//        nvRsPage.PressOnOKButton();

        logger.log(LogStatus.INFO,"Cheking that NVR count after is equal to NVRs count before ");
        Assert.assertEquals(countAfter, countBefore);
    }

    @Test 
    public void AddNVRManuallyAndPressCloseTest() throws InterruptedException {
        logger=report.startTest("AddNVRManuallyAndPressCloseTest");

        String IPAdress = Servers[0];
        int countBefore = nvRsPage.NVRsCount();

        logger.log(LogStatus.INFO,"Open Add NVR Manually window");
        nvRsPage.PressAddNVRManuallyButton();

        logger.log(LogStatus.INFO,"Input into IPAddress field "+IPAdress);
        nvRsPage.InputIntoIPAdressField(IPAdress);

        logger.log(LogStatus.INFO,"Input into UserName field ADMIN");
        nvRsPage.InputIntoUserNameField("ADMIN");

        logger.log(LogStatus.INFO,"Assert that Apply button is enabled");
        Assert.assertTrue(nvRsPage.ApplyButtonInAddNVRManuallyIsEnable());

        logger.log(LogStatus.INFO,"Assert that ApplyAndClose button is enabled");
        Assert.assertTrue(nvRsPage.ApplyAndCloseButtonInAddNVRManuallyIsEnable());

        logger.log(LogStatus.INFO,"Input into password field 1234");
        nvRsPage.InputIntoPasswordField("1234");

        logger.log(LogStatus.INFO,"Close Add NVR manually window");
        nvRsPage.PressCloseButtonInAddNVRManually();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that 'Add NVR Manually' window is closed");
        Assert.assertFalse(nvRsPage.AddNVRManuallyWindowIsOpen());

        int countAfter = nvRsPage.NVRsCount();

        logger.log(LogStatus.INFO,"Cheking that NVR count after is equal to NVRs count before ");
        Assert.assertEquals(countAfter, countBefore);
    }

    @Test
    public void AddNVRManuallyAndPressApplyTest() throws InterruptedException {
        logger=report.startTest("AddNVRManuallyAndPressApplyTest");
        String IPAdress = Servers[1];

        if(nvRsPage.verifyElementIsPresent(nvRsPage.FindElementByText(IPAdress))){
            logger.log(LogStatus.INFO,"Delete NVR" + IPAdress);
            nvRsPage.FindElementByText(IPAdress).click();
            Thread.sleep(1000);
            nvRsPage.PressOnRemoveNVRsByButton();
            nvRsPage.ConfirmRemoveNVRsByButton();
            Thread.sleep(5000);
            logger.log(LogStatus.INFO,"NVR" + IPAdress+" is deleted");
        }
        int countBefore = nvRsPage.NVRsCount();
        
        logger.log(LogStatus.INFO,"Open Add NVR Manually window");
        nvRsPage.PressAddNVRManuallyButton();

        logger.log(LogStatus.INFO,"Input into IPAddress field "+IPAdress);
        nvRsPage.InputIntoIPAdressField(IPAdress);

        logger.log(LogStatus.INFO,"Input into UserName field ADMIN");
        nvRsPage.InputIntoUserNameField("ADMIN");

        logger.log(LogStatus.INFO,"Check that Apply button is enable after changes");
        Assert.assertTrue(nvRsPage.ApplyButtonInAddNVRManuallyIsEnable());

        logger.log(LogStatus.INFO,"Check that ApplyAndClode button is enable after changes");
        Assert.assertTrue(nvRsPage. ApplyAndCloseButtonInAddNVRManuallyIsEnable());

        logger.log(LogStatus.INFO,"Input into password field 1234");
        nvRsPage.InputIntoPasswordField("1234");

        logger.log(LogStatus.INFO,"Apply adding");
        nvRsPage.PressApplyButtonInAddNVRManually();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that Apply button is disable after changes");
        Assert.assertFalse(nvRsPage.ApplyButtonInAddNVRManuallyIsEnable());
        
        logger.log(LogStatus.INFO,"Check that ApplyAndClode button is disable after changes");
        Assert.assertFalse(nvRsPage. ApplyAndCloseButtonInAddNVRManuallyIsEnable());
        
        logger.log(LogStatus.INFO,"Check that 'Add NVR Manually' window is open");
        Assert.assertTrue(nvRsPage.CheckThatModalIsOpen());

        int countAfter = nvRsPage.NVRsCount();
        logger.log(LogStatus.INFO,"Assert that NVRs count increased by 1");
        Assert.assertEquals(countAfter, countBefore+1);
    }

    @Test
    public void InputInvalidIPAdressInAddNVRManualyTest() throws InterruptedException {
        logger=report.startTest("InputInvalidIPAdressInAddNVRManualyTest");

        int random =(int) (Math.random()*1000);

        String IPAdress = "IPAdress"+random;
        int countBefore = nvRsPage.NVRsCount();
        logger.log(LogStatus.INFO,"Add NVR with invalid IP Adress manually");
        nvRsPage.PressAddNVRManuallyButton();
        nvRsPage.InputIntoIPAdressField(IPAdress);
        nvRsPage.InputIntoUserNameField("ADMIN");
        nvRsPage.InputIntoPasswordField("1234");
        nvRsPage.PressApplyAndCloseButtonInAddNVRManually();
        nvRsPage.WaitUntilCountOfNVRsIs(countBefore+1);

        nvRsPage.waitUntilStatusIsText(IPAdress, "Offline");
        int countAfter = nvRsPage.NVRsCount();

        logger.log(LogStatus.INFO,"Cheking that NVR with invalid IP adress is added in the Central Panel list ");
        Assert.assertEquals(countAfter, countBefore+1);

        String status =  nvRsPage.GetStatus(IPAdress);

        logger.log(LogStatus.INFO,"Checking that NVR status is Offline");
        Assert.assertEquals(status, "Offline");
    }

    @Test
    public void InputInvalidUserNameInAddNVRManualyTest() throws InterruptedException {
        logger=report.startTest("InputInvalidUserNameInAddNVRManualyTest");

        logger.log(LogStatus.INFO,"Add NVR with invalid user name manually");
        String IPAdress =Servers[1];

        if(nvRsPage.verifyElementIsPresent(nvRsPage.FindElementByText(IPAdress))){
            logger.log(LogStatus.INFO,"Delete NVR" + IPAdress);
            nvRsPage.FindElementByText(IPAdress).click();
            Thread.sleep(1000);
            nvRsPage.PressOnRemoveNVRsByButton();
            nvRsPage.ConfirmRemoveNVRsByButton();
            Thread.sleep(5000);
            logger.log(LogStatus.INFO,"NVR" + IPAdress+" is deleted");
//            nvRsPage.WaitUntilRemovedNVRWillBeAddedToDiscoveredNVRsList();
        }

        int random =(int) (Math.random()*100);

        String userName = "UserName"+random;
        int countBefore = nvRsPage.NVRsCount();

        nvRsPage.PressAddNVRManuallyButton();
        nvRsPage.InputIntoIPAdressField(IPAdress);
        nvRsPage.InputIntoUserNameField(userName);
        nvRsPage.InputIntoPasswordField("1234");
        nvRsPage.PressApplyAndCloseButtonInAddNVRManually();
        nvRsPage.WaitUntilCountOfNVRsIs(countBefore+1);

        int countAfter = nvRsPage.NVRsCount();

        logger.log(LogStatus.INFO,"Cheking that NVR with invalid UserName is added in the Central Panel list ");
        Assert.assertEquals(countAfter, countBefore+1);
        nvRsPage. waitUntilStatusIsText(IPAdress, "Server Unauthorized");
        String status =  nvRsPage.GetStatus(IPAdress);
        System.out.print(status);

        logger.log(LogStatus.INFO,"Checking that NVR status isn Server Unauthorized");
        Assert.assertEquals(status, "Server Unauthorized");//
    }

    @Test
    public void InputInvalidPasswordInAddNVRManualyTest() throws InterruptedException {
        logger=report.startTest("InputInvalidPasswordInAddNVRManualyTest");

        logger.log(LogStatus.INFO,"Add NVR with invalid password manually");
        String IPAdress =Servers[1];
        if(nvRsPage.verifyElementIsPresent(nvRsPage.FindElementByText(IPAdress))){
            logger.log(LogStatus.INFO,"Delete NVR" + IPAdress);
            nvRsPage.FindElementByText(IPAdress).click();
            Thread.sleep(1000);
            nvRsPage.PressOnRemoveNVRsByButton();
            nvRsPage.ConfirmRemoveNVRsByButton();
            Thread.sleep(5000);
            logger.log(LogStatus.INFO,"NVR" + IPAdress+" is deleted");
//          nvRsPage.WaitUntilRemovedNVRWillBeAddedToDiscoveredNVRsList();
        }

        int random =nvRsPage.GetRandomDigit(0,100);
        String password = "Password"+random;
        int countBefore = nvRsPage.NVRsCount();

        nvRsPage.PressAddNVRManuallyButton();
        nvRsPage.InputIntoIPAdressField(IPAdress);
        nvRsPage.InputIntoUserNameField("ADMIN");
        nvRsPage.InputIntoPasswordField(password);
        nvRsPage.PressApplyAndCloseButtonInAddNVRManually();
        nvRsPage.WaitUntilCountOfNVRsIs(countBefore+1);

        int countAfter = nvRsPage.NVRsCount();

        logger.log(LogStatus.INFO,"Cheking that NVR with invalid password is added in the Central Panel list ");
        Assert.assertEquals(countAfter, countBefore+1);
        nvRsPage. waitUntilStatusIsText(IPAdress, "Server Unauthorized");
        String status =  nvRsPage.GetStatus(IPAdress);
       System.out.print(status);
        logger.log(LogStatus.INFO,"Checking that NVR status is Server Unauthorized");
        Assert.assertEquals(status, "Server Unauthorized");
    }

   @Test
    public void AddNVRManuallyWithSecuredPortTest() throws InterruptedException {
        logger=report.startTest("AddNVRManuallyWithSecuredPortTest");

        String IPAdress = Servers[1];

        if(nvRsPage.verifyElementIsPresent(nvRsPage.FindElementByText(IPAdress))){
            logger.log(LogStatus.INFO,"Delete NVR" + IPAdress);
            nvRsPage.FindElementByText(IPAdress).click();
            Thread.sleep(1000);
            nvRsPage.PressOnRemoveNVRsByButton();
            nvRsPage.ConfirmRemoveNVRsByButton();
//            nvRsPage.WaitUntilRemovedNVRWillBeAddedToDiscoveredNVRsList();
            logger.log(LogStatus.INFO,"NVR" + IPAdress+" is deleted");
        }
        int countBefore = nvRsPage.NVRsCount();

        logger.log(LogStatus.INFO,"Open Add NVR Manually window");
        nvRsPage.PressAddNVRManuallyButton();

        logger.log(LogStatus.INFO,"Input into IPAddress field "+IPAdress);
        nvRsPage.InputIntoIPAdressField(IPAdress);

        logger.log(LogStatus.INFO,"Input into Port field "+IPAdress);
        nvRsPage.InputIntoPortField("443");

        logger.log(LogStatus.INFO,"Input into UserName field ADMIN");
        nvRsPage.InputIntoUserNameField("ADMIN");

        logger.log(LogStatus.INFO,"Input into password field 1234");
        nvRsPage.InputIntoPasswordField("1234");

        logger.log(LogStatus.INFO,"Apply NVR adding ");
        nvRsPage.PressApplyAndCloseButtonInAddNVRManually();
        nvRsPage.waitUntilStatusIsText(IPAdress, "Communication Failure");
        nvRsPage.waitUntilStatusIsText(IPAdress, "Communication Failure");
        nvRsPage.waitUntilStatusIsText(IPAdress, "Communication Failure");
        nvRsPage.waitUntilStatusIsText(IPAdress, "Communication Failure");
        int countAfter = nvRsPage.NVRsCount();

        logger.log(LogStatus.INFO,"Assert that NVRs count increased by 1");
        Assert.assertEquals(countAfter, countBefore+1);

        String status =  nvRsPage.GetStatus(IPAdress);
        logger.log(LogStatus.INFO,"Assert that added NVRs status is Communication Failure");
        Assert.assertEquals(status, "Communication Failure");

        logger.log(LogStatus.INFO,"Refresh page");
        nvRsPage.Refresh();

        logger.log(LogStatus.INFO,"Click on NVR " + IPAdress );
        nvRsPage.ClickOnNvrByText(IPAdress);

        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that port for NVR " + IPAdress+" is 443");
        String portAfterRefresh = nvRsPage.GetPortFromProperties();
        Assert.assertEquals(portAfterRefresh, "443");
        Thread.sleep(1000);
        
        logger.log(LogStatus.INFO,"Check that secured status for NVR " + IPAdress+" is OFF");
        String securedAfterRefresh = nvRsPage.GetSecuredStatus();
        Assert.assertEquals(securedAfterRefresh, "OFF");
        Thread.sleep(1000);
        logger.log(LogStatus.INFO,"Check that status for NVR " + IPAdress+" is Communication Failure");
        String statusAfterRefresh= nvRsPage.GetStatus(IPAdress);
        Assert.assertEquals(statusAfterRefresh, "Communication Failure");
    }

    @Test
    public void AddNVRManuallyWithSecuredPortAndSwitchONSecuredTest() throws InterruptedException {
        logger=report.startTest("AddNVRManuallyWithSecuredPortAndSwitchONSecuredTest");

        String IPAdress = Servers[1];

        if(nvRsPage.verifyElementIsPresent(nvRsPage.FindElementByText(IPAdress))){
            logger.log(LogStatus.INFO,"Delete NVR" + IPAdress);
            nvRsPage.FindElementByText(IPAdress).click();
            Thread.sleep(1000);
            nvRsPage.PressOnRemoveNVRsByButton();
            nvRsPage.ConfirmRemoveNVRsByButton();
//            nvRsPage.WaitUntilRemovedNVRWillBeAddedToDiscoveredNVRsList();
            logger.log(LogStatus.INFO,"NVR" + IPAdress+" is deleted");
        }
        int countBefore = nvRsPage.NVRsCount();

        logger.log(LogStatus.INFO,"Open Add NVR Manually window");
        nvRsPage.PressAddNVRManuallyButton();

        logger.log(LogStatus.INFO,"Input into IPAddress field "+IPAdress);
        nvRsPage.InputIntoIPAdressField(IPAdress);

        logger.log(LogStatus.INFO,"Input into Port field "+IPAdress);
        nvRsPage.InputIntoPortField("443");

        if(nvRsPage.GetSecuredStatusInAddNVRManuallyDialog().equals("OFF")){
            logger.log(LogStatus.INFO,"Switch ON secured toggle-switch");
            nvRsPage.ClickOnSecuredSwitchInAddNVRManuallyDialog();
        }

        logger.log(LogStatus.INFO,"Input into UserName field ADMIN");
        nvRsPage.InputIntoUserNameField("ADMIN");

        logger.log(LogStatus.INFO,"Input into password field 1234");
        nvRsPage.InputIntoPasswordField("1234");

        logger.log(LogStatus.INFO,"Apply NVR adding ");
        nvRsPage.PressApplyAndCloseButtonInAddNVRManually();
        nvRsPage.waitUntilStatusIsV(IPAdress);

        int countAfter = nvRsPage.NVRsCount();

        logger.log(LogStatus.INFO,"Assert that NVRs count increased by 1");
        Assert.assertEquals(countAfter, countBefore+1);

        String status =  nvRsPage.GetStatus(IPAdress);
        logger.log(LogStatus.INFO,"Assert that added NVRs status is Online");
        Assert.assertEquals(status, "V");

        logger.log(LogStatus.INFO,"Refresh page");
        nvRsPage.Refresh();

        logger.log(LogStatus.INFO,"Click on NVR " + IPAdress );
        nvRsPage.ClickOnNvrByText(IPAdress);
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that port for NVR " + IPAdress+" is 443");
        String portAfterRefresh = nvRsPage.GetPortFromProperties();
        Assert.assertEquals(portAfterRefresh, "443");

        logger.log(LogStatus.INFO,"Check that secured status for NVR " + IPAdress+" is ON");
        String securedAfterRefresh = nvRsPage.GetSecuredStatus();
        Assert.assertEquals(securedAfterRefresh, "ON");

        logger.log(LogStatus.INFO,"Check that status for NVR " + IPAdress+" is Online");
        String statusAfterRefresh= nvRsPage.GetStatus(IPAdress);
        Assert.assertEquals(statusAfterRefresh, "V");
    }

    @Test
    public void AddNVRManuallyAndSwitchONSecuredTest() throws InterruptedException {
        logger=report.startTest("AddNVRManuallyAndSwitchONSecuredTest");

        String IPAdress = Servers[1];

        if(nvRsPage.verifyElementIsPresent(nvRsPage.FindElementByText(IPAdress))){
            logger.log(LogStatus.INFO,"Delete NVR" + IPAdress);
            nvRsPage.FindElementByText(IPAdress).click();
            Thread.sleep(1000);
            nvRsPage.PressOnRemoveNVRsByButton();
            nvRsPage.ConfirmRemoveNVRsByButton();
//            nvRsPage.WaitUntilRemovedNVRWillBeAddedToDiscoveredNVRsList();
            logger.log(LogStatus.INFO,"NVR" + IPAdress+" is deleted");
        }
        int countBefore = nvRsPage.NVRsCount();

        logger.log(LogStatus.INFO,"Open Add NVR Manually window");
        nvRsPage.PressAddNVRManuallyButton();

        logger.log(LogStatus.INFO,"Input into IPAddress field "+IPAdress);
        nvRsPage.InputIntoIPAdressField(IPAdress);

        logger.log(LogStatus.INFO,"Input into Port field "+IPAdress);
        nvRsPage.InputIntoPortField("80");

        if(nvRsPage.GetSecuredStatusInAddNVRManuallyDialog().equals("OFF")){
            logger.log(LogStatus.INFO,"Switch ON secured toggle-switch");
            nvRsPage.ClickOnSecuredSwitchInAddNVRManuallyDialog();
        }

        logger.log(LogStatus.INFO,"Input into UserName field ADMIN");
        nvRsPage.InputIntoUserNameField("ADMIN");

        logger.log(LogStatus.INFO,"Input into password field 1234");
        nvRsPage.InputIntoPasswordField("1234");

        logger.log(LogStatus.INFO,"Apply NVR adding ");
        nvRsPage.PressApplyAndCloseButtonInAddNVRManually();
        nvRsPage.WaitUntilCountOfNVRsIs(countBefore+1);

        nvRsPage.waitUntilStatusIsText(IPAdress, "Communication Failure");
        int countAfter = nvRsPage.NVRsCount();

        logger.log(LogStatus.INFO,"Assert that NVRs count increased by 1");
        Assert.assertEquals(countAfter, countBefore+1);

        String status =  nvRsPage.GetStatus(IPAdress);
        logger.log(LogStatus.INFO,"Assert that added NVRs status is Communication Failure");
        Assert.assertEquals(status, "Communication Failure");

        logger.log(LogStatus.INFO,"Refresh page");
        nvRsPage.Refresh();

        logger.log(LogStatus.INFO,"Click on NVR " + IPAdress );
        nvRsPage.ClickOnNvrByText(IPAdress);

        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that secured status for NVR " + IPAdress+" is ON");
        String securedAfterRefresh = nvRsPage.GetSecuredStatus();
        Assert.assertEquals(securedAfterRefresh, "ON");

        logger.log(LogStatus.INFO,"Check that status for NVR " + IPAdress+" is Communication Failure");
        String statusAfterRefresh= nvRsPage.GetStatus(IPAdress);
        Assert.assertEquals(statusAfterRefresh, "Communication Failure");
    }

    @Test
    public void InputInvalidPortInAddNVRManualyTest() throws InterruptedException {
        logger=report.startTest("InputInvalidPortInAddNVRManualyTest");

        logger.log(LogStatus.INFO,"Add NVR with invalid port manually");
        String IPAdress =Servers[1];

        if(nvRsPage.verifyElementIsPresent(nvRsPage.FindElementByText(IPAdress))){
            logger.log(LogStatus.INFO,"Delete NVR" + IPAdress);
            nvRsPage.FindElementByText(IPAdress).click();
            Thread.sleep(1000);
            nvRsPage.PressOnRemoveNVRsByButton();
            nvRsPage.ConfirmRemoveNVRsByButton();
            Thread.sleep(5000);
            logger.log(LogStatus.INFO,"NVR" + IPAdress+" is deleted");
        }
        String port = ""+(int) (Math.random()*1000);
        int countBefore = nvRsPage.NVRsCount();

        nvRsPage.PressAddNVRManuallyButton();
        nvRsPage.InputIntoIPAdressField(IPAdress);
        nvRsPage.InputIntoPortField(port);

        nvRsPage.InputIntoUserNameField("ADMIN");
        nvRsPage.InputIntoPasswordField("1234");
        nvRsPage.PressApplyAndCloseButtonInAddNVRManually();
        nvRsPage.WaitUntilCountOfNVRsIs(countBefore+1);

        int countAfter = nvRsPage.NVRsCount();
        logger.log(LogStatus.INFO,"Cheking that NVR with invalid port is added in the Central Panel list ");
        Assert.assertEquals(countAfter, countBefore+1);

        String status =  nvRsPage.GetStatus(IPAdress);
        logger.log(LogStatus.INFO,"Checking that NVR status isn't V");
        Assert.assertFalse(status.contains("V"));
        Thread.sleep(1000);
        nvRsPage.ClickOnNvrByText(IPAdress);
        Thread.sleep(1000);
        String portFromProperties = nvRsPage.GetPortFromProperties();
        logger.log(LogStatus.INFO,"Check that port is equals the port from Properties");
        Thread.sleep(1000);
        Assert.assertEquals(portFromProperties, port);
    }

    @Test(enabled = false)
    public void IncreasePortInAddNVRManualyWindowTest() throws InterruptedException {
        logger=report.startTest("IncreasePortInAddNVRManualyWindowTest");

        String status = null;
        String IPAdress =Servers[1];

        if(nvRsPage.verifyElementIsPresent(nvRsPage.FindElementByText(IPAdress))){
            logger.log(LogStatus.INFO,"Delete NVR" + IPAdress);
            nvRsPage.FindElementByText(IPAdress).click();
            Thread.sleep(1000);
            nvRsPage.PressOnRemoveNVRsByButton();
            nvRsPage.ConfirmRemoveNVRsByButton();
            Thread.sleep(5000);
            logger.log(LogStatus.INFO,"NVR" + IPAdress+" is deleted");
        }
        int countBefore = nvRsPage.NVRsCount();
        logger.log(LogStatus.INFO,"Add NVR and increase port manually");

        nvRsPage.PressAddNVRManuallyButton();
        String port = nvRsPage.GetPortFromProperties();
        nvRsPage.InputIntoIPAdressField(IPAdress);
        nvRsPage.InputIntoUserNameField("ADMIN");
        nvRsPage.InputIntoPasswordField("1234");
        nvRsPage.IncreasePortInAddNVRManuallyWindow();

        nvRsPage.PressApplyAndCloseButtonInAddNVRManually();
        nvRsPage.WaitUntilCountOfNVRsIs(countBefore+1);

        int countAfter = nvRsPage.NVRsCount();

        logger.log(LogStatus.INFO,"Cheking that NVR with invalid port is added in the Central Panel list ");
        Assert.assertEquals(countAfter, countBefore+1);

       try{  status =  nvRsPage.GetStatus(IPAdress);
       }catch (Exception e ){}

        logger.log(LogStatus.INFO,"Checking that NVR status isn't V");
        Assert.assertNotEquals(status, "V");

        nvRsPage.ClickOnNvrByText(IPAdress);

        String portFromProperties = nvRsPage.GetPortFromProperties();

        logger.log(LogStatus.INFO,"Check that port is equals the port from Properties");
        Assert.assertEquals(Integer.parseInt(portFromProperties), Integer.parseInt(port)+1);
    }

    @Test(enabled = false)
    public void DecreasePortInAddNVRManualyWindowTest() throws InterruptedException {
        logger=report.startTest("DecreasePortInAddNVRManualyWindowTest");
        String IPAdress =Servers[1];

        if(nvRsPage.verifyElementIsPresent(nvRsPage.FindElementByText(IPAdress))){
        logger.log(LogStatus.INFO,"Delete NVR" + IPAdress);
        nvRsPage.FindElementByText(IPAdress).click();
        Thread.sleep(1000);
        nvRsPage.PressOnRemoveNVRsByButton();
        nvRsPage.ConfirmRemoveNVRsByButton();
        Thread.sleep(5000);
        logger.log(LogStatus.INFO,"NVR" + IPAdress+" is deleted");
        }
        String status = null;
        int countBefore = nvRsPage.NVRsCount();

        logger.log(LogStatus.INFO,"Add NVR and decrease port manually");
        nvRsPage.PressAddNVRManuallyButton();

        String port = nvRsPage.GetPortFromAddManuallyWindow();
        nvRsPage.InputIntoIPAdressField(IPAdress);
        nvRsPage.InputIntoUserNameField("ADMIN");
        nvRsPage.InputIntoPasswordField("1234");
        nvRsPage.DecreasePortInAddNVRManuallyWindow();
        nvRsPage.PressApplyAndCloseButtonInAddNVRManually();
        nvRsPage.WaitUntilCountOfNVRsIs(countBefore+1);

        int countAfter = nvRsPage.NVRsCount();

        logger.log(LogStatus.INFO,"Cheking that NVR with invalid port is added in the Central Panel list ");
        Assert.assertEquals(countAfter, countBefore+1);

        try{  status =  nvRsPage.GetStatus(IPAdress);
        }catch (Exception e ){}

        logger.log(LogStatus.INFO,"Checking NVR status");
        Assert.assertNotEquals(status, "V");
        nvRsPage.ClickOnNvrByText(IPAdress);

        String portFromProperties = nvRsPage.GetPortFromProperties();
        logger.log(LogStatus.INFO,"Check that port is equals the port from Properties");
        Assert.assertEquals(Integer.parseInt(portFromProperties), Integer.parseInt(port)-1);
    }

    //tests for remove NVRs

     @Test(priority = 1)
    public void RemoveNVRsByButtonTest() throws IOException, InterruptedException {
         logger=report.startTest("RemoveNVRsByButtonTest");

         if(nvRsPage.GetSizeNVRsList()==0){
          nvRsPage.AddDiscoveredNVRs(Servers[1]);
      }

       String IPAdress = nvRsPage.GetNVRFromCentralPanel(0).getText();
       logger.log(LogStatus.INFO,"Remove NVR: " + IPAdress+" by Button 'Remove NVR'");

       int sizeBefore = nvRsPage.NVRsCount();
       nvRsPage.FindElementByText(IPAdress).click();
       Thread.sleep(1000);
       nvRsPage.PressOnRemoveNVRsByButton();
       nvRsPage.ConfirmRemoveNVRsByButton();
       int sizeAfter = nvRsPage.NVRsCount();

       logger.log(LogStatus.INFO,"Check that NVR is removed");
       Assert.assertEquals(sizeAfter, sizeBefore-1);
    }

    @Test
    public void CancelRemovalNVRsByButtonTest() throws IOException, InterruptedException {
        logger=report.startTest("CancelRemovalNVRsByButtonTest");

        Thread.sleep(1000);
        String IPAdress = nvRsPage.GetNVRFromCentralPanel(0).getText();
        logger.log(LogStatus.INFO,"Cancel removal NVR: " + IPAdress+" by Button 'Remove NVR'");

        int sizeBefore = nvRsPage.NVRsCount();
        nvRsPage.FindElementByText(IPAdress).click();
        Thread.sleep(1000);
        nvRsPage.PressOnRemoveNVRsByButton();
        nvRsPage.CancelRemoveNVRsByButton();
        Thread.sleep(1000);
        int sizeAfter = nvRsPage.NVRsCount();

        logger.log(LogStatus.INFO,"Check that NVR isn't removed");
        Assert.assertEquals(sizeAfter, sizeBefore);
    }

    //tests for central panel

    @Test
    public void FilterNVRsTest() throws IOException, InterruptedException {
        logger = report.startTest("FilterNVRsTest");

        if (nvRsPage.GetSizeNVRsList() == 0) {
            nvRsPage.AddDiscoveredNVRs(Servers[1]);
        }

        String IPAdress = nvRsPage.GetNVRFromCentralPanel(0).getText();
        logger.log(LogStatus.INFO, "Filter list of NVR to: " + IPAdress);
        nvRsPage.InputIntoFilter(IPAdress);
        boolean flag = false;

        int size = nvRsPage.GetSizeNVRsList();
        for (int i = 0; i < size; i++) {
            String name = nvRsPage.GetNameNVR(i);
            String ip = nvRsPage.GetNVRFromCentralPanel(i).getText();
            if (name.contains(IPAdress) || ip.contains(IPAdress)) flag = true;
            Assert.assertTrue(flag);
        }
    }

    @Test
    public void RemoveNVRsByIconTest() throws IOException, InterruptedException {
        logger=report.startTest("RemoveNVRsByIconTest");

        if(nvRsPage.GetSizeNVRsList()==0){
            nvRsPage.AddDiscoveredNVRs(Servers[1]);
        }
        String IPAdress = nvRsPage.GetNVRFromCentralPanel(0).getText();
        logger.log(LogStatus.INFO,"Removal NVR: " + IPAdress+" by Icon'");

        int sizeBefore = nvRsPage.NVRsCount();
        nvRsPage.FindElementByText(IPAdress).click();
        nvRsPage.PressOnRemoveIcon(IPAdress);
        nvRsPage.ConfirmRemoveNVRsByButton();
        int sizeAfter = nvRsPage.NVRsCount();

        logger.log(LogStatus.INFO,"Check that NVR is removed");
        Assert.assertEquals(sizeAfter, sizeBefore-1);
    }

    @Test
    public void CancelRemovalNVRsByIconTest() throws IOException, InterruptedException {
        logger=report.startTest("CancelRemovalNVRsByIconTest");

        String IPAdress = nvRsPage.GetNVRFromCentralPanel(0).getText();
        logger.log(LogStatus.INFO,"Cancel removal NVR: " + IPAdress+" by Icon");

        int sizeBefore = nvRsPage.NVRsCount();
        nvRsPage.FindElementByText(IPAdress).click();
        Thread.sleep(1000);
        nvRsPage.PressOnRemoveIcon(IPAdress);;

        nvRsPage.CancelRemoveNVRsByButton();
        Thread.sleep(1000);
        int sizeAfter = nvRsPage.NVRsCount();

        logger.log(LogStatus.INFO,"Check that NVR isn't removed");
        Assert.assertEquals(sizeAfter, sizeBefore);
    }

    @Test
    public void SortingNVRsByIPAdressTest() throws InterruptedException {
        logger=report.startTest("SortingNVRsByIPAdressTest");

        logger.log(LogStatus.INFO,"Sorting NVRs by IP Adress" );

        nvRsPage.IfNVRIsNotExistAddIt(Servers[0]);
        nvRsPage.IfNVRIsNotExistAddIt(Servers[1]);

        Thread.sleep(1000);
        String firstElement=nvRsPage.GetNVRFromCentralPanel(0).getText();
        String lastElement = nvRsPage.GetNVRFromCentralPanel(nvRsPage.GetSizeNVRsList()-1).getText();

        nvRsPage.SortNVRsByIPAdress();
        String firstSortedElement=nvRsPage.GetNVRFromCentralPanel(0).getText();
        String lastSortedElement = nvRsPage.GetNVRFromCentralPanel(nvRsPage.GetSizeNVRsList()-1).getText();

        logger.log(LogStatus.INFO,"Check that the first Element became the last");
        Assert.assertEquals(firstElement, lastSortedElement);
        logger.log(LogStatus.INFO,"Check that the last Element became the first");
        Assert.assertEquals(lastElement, firstSortedElement);
    }

    @Test
    public void SortingNVRsByNameTest() throws InterruptedException {
        logger=report.startTest("SortingNVRsByNameTest");

        logger.log(LogStatus.INFO,"Sorting NVRs by Name" );
        Thread.sleep(1000);

        nvRsPage.IfNVRIsNotExistAddIt(Servers[0]);
        nvRsPage.IfNVRIsNotExistAddIt(Servers[1]);

        nvRsPage.SortNVRsByName();
        nvRsPage.SortNVRsByName();

        String firstElement=nvRsPage.GetNameNVR(0);
        String lastElement = nvRsPage.GetNameNVR(nvRsPage.GetSizeNVRsList()-1);

        nvRsPage.SortNVRsByName();
        String firstSortedElement=nvRsPage.GetNameNVR(0);
        String lastSortedElement = nvRsPage.GetNameNVR(nvRsPage.GetSizeNVRsList()-1);

        logger.log(LogStatus.INFO,"Check that the first Element became the last");
        Assert.assertEquals(firstElement, lastSortedElement);
        logger.log(LogStatus.INFO,"Check that the last Element became the first");
        Assert.assertEquals(lastElement, firstSortedElement);
    }

    @Test
    public void SortingNVRsByVersionTest() throws InterruptedException {
        logger=report.startTest("SortingNVRsByVersionTest");

        logger.log(LogStatus.INFO,"Sorting NVRs by Version" );
        Thread.sleep(1000);
        nvRsPage.SortNVRsByVersion();
        nvRsPage.SortNVRsByVersion();

        String firstElement=nvRsPage.GetVersionNVR(0);
        String lastElement = nvRsPage.GetVersionNVR(nvRsPage.GetSizeNVRsList()-1);

        nvRsPage.SortNVRsByVersion();
        String firstSortedElement=nvRsPage.GetVersionNVR(0);
        String lastSortedElement = nvRsPage.GetVersionNVR(nvRsPage.GetSizeNVRsList()-1);

        logger.log(LogStatus.INFO,"Check that the first Element became the last");
        Assert.assertEquals(firstElement, lastSortedElement);
        logger.log(LogStatus.INFO,"Check that the last Element became the first");
        Assert.assertEquals(lastElement, firstSortedElement);
    }

    //Properties

    @Test
    public void ChangeNameTest() throws InterruptedException, IOException {
        logger=report.startTest("ChangeNameTest");

        String IPAdress =Servers[0];
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

        WebElement nvr =nvRsPage.FindNVRByText(Servers[0]);
        String NameNVRsText =  Servers[0] + " Name";
        logger.log(LogStatus.INFO,"Changing NVR name from " + Servers[0] + " to "+NameNVRsText + " and pressing 'Save'");
        nvr.click();
        nvRsPage.ChangeName(NameNVRsText);

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
        Assert.assertTrue(nvRsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
        Assert.assertTrue(nvRsPage.SaveButtonIsEnable());

        nvRsPage.SaveChangesButton();
        
        Thread.sleep(1000);

        String NVRNameAfter = nvRsPage.GetNVRName(Servers[0]);

        logger.log(LogStatus.INFO,"Check that change is saved ");
        Assert.assertEquals(NVRNameAfter, NameNVRsText);

        nvRsPage.Refresh();

        logger.log(LogStatus.INFO,"Check that change is saved after refresh");
        Assert.assertEquals(NVRNameAfter, NameNVRsText);
    }

    @Test
    public void ChangeIPAdressAndCheckStatusTest() throws InterruptedException, IOException {
        logger=report.startTest("ChangeIPAdressAndCheckStatusTest");
        String IPAdress = Servers[1];

        String IPAdressText =   IPAdress +"IP";
        logger.log(LogStatus.INFO,"Changing NVR IP Address from " + IPAdress + " to "+IPAdressText + " and press 'Save' on the 'Unsaved changes' window");
        nvRsPage.IfStatusIsNotVThanDeleteNVR(IPAdress);
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

        logger.log(LogStatus.INFO,"Click on " + IPAdress + " NVR");
        nvRsPage.ClickOnNvrByText(IPAdress);
        nvRsPage.WaitUntilStorrageSettingIsExist();
        Thread.sleep(2000);

        logger.log(LogStatus.INFO,"Change IP Address for "+IPAdressText);
        nvRsPage.ChangeIPAdress(IPAdressText);
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
        Assert.assertTrue(nvRsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
        Assert.assertTrue(nvRsPage.SaveButtonIsEnable());

        logger.log(LogStatus.INFO,"Click on Save button");
        nvRsPage.SaveChangesButton();

        String NVRIPAfter = nvRsPage.GetIPAdress(IPAdress);
        logger.log(LogStatus.INFO,"Check that change is saved ");
        Assert.assertEquals(NVRIPAfter, IPAdress+IPAdressText);

        nvRsPage.waitUntilStatusIsText(IPAdressText, "Offline");

        String status =  nvRsPage.GetStatus(IPAdressText);
        logger.log(LogStatus.INFO,"Checking that NVR status is Offline");
        Assert.assertEquals(status, "Offline");

        logger.log(LogStatus.INFO,"Refresh");
        nvRsPage.Refresh();

        logger.log(LogStatus.INFO,"Click on " + IPAdress + " NVR");
        nvRsPage.ClickOnNvrByText(IPAdress);
        Thread.sleep(1000);

        String NVRIPAfterRefresh = nvRsPage.GetIPAdress(IPAdress);
        logger.log(LogStatus.INFO,"Check that change is saved after refresh");
        Assert.assertEquals(NVRIPAfterRefresh, IPAdress+IPAdressText);
    }

    @Test
    public void ChangePortAndCheckStatusTest() throws InterruptedException, IOException {
        logger=report.startTest("ChangePortAndCheckStatusTest");

        Thread.sleep(1000);
        String IPAdress = Servers[1];
        String PortText = ""+ 85;

        nvRsPage.IfStatusIsNotVThanDeleteNVR(IPAdress);
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

       nvRsPage.ClickOnNvrByText(IPAdress);

       Thread.sleep(1000);
        String NVRPortBefore = nvRsPage.GetPortFromProperties();
        logger.log(LogStatus.INFO,"Changing NVR Port from " + NVRPortBefore + " to "+PortText + " and press 'Cancel' on the 'Unsaved changes' window");

        nvRsPage.ChangePort(PortText);
        Thread.sleep(1000);
        logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
        Assert.assertTrue(nvRsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
        Assert.assertTrue(nvRsPage.SaveButtonIsEnable());

        logger.log(LogStatus.INFO,"Press on Save");
        nvRsPage.SaveChangesButton();

        String NVRPortAfter = nvRsPage.GetPortFromProperties();

        logger.log(LogStatus.INFO,"Check that change is saved ");
        Assert.assertEquals(NVRPortAfter, PortText);

        nvRsPage.waitUntilStatusIsText(IPAdress, "Offline");
        String status =  nvRsPage.GetStatus(IPAdress);

        logger.log(LogStatus.INFO,"Checking that NVR status is Offline");
        Assert.assertEquals(status, "Offline");

        nvRsPage.Refresh();
        nvRsPage.ClickOnNvrByText(IPAdress);

        Thread.sleep(1000);
        String NVRPortAfterRefresh = nvRsPage.GetPortFromProperties();

        logger.log(LogStatus.INFO,"Check that change is saved after refresh");
        Assert.assertEquals(NVRPortAfterRefresh, PortText);
    }

    @Test(enabled=false)
    public void ChangePortToSecuredAndCheckStatusTest() throws InterruptedException, IOException {
        logger=report.startTest("ChangePortToSecuredAndCheckStatusTest");

        Thread.sleep(1000);
        String IPAdress = Servers[1];

        nvRsPage.IfStatusIsNotVThanDeleteNVR(IPAdress);
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

        nvRsPage.ClickOnNvrByText(IPAdress);
        Thread.sleep(1000);

        String NVRPortBefore = nvRsPage.GetPortFromProperties();
        logger.log(LogStatus.INFO,"Changing NVR Port from " + NVRPortBefore + " to 443");
        nvRsPage.ChangePort("443");
        nvRsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Press on Save");
        nvRsPage.SaveChangesButton();

        nvRsPage.waitUntilStatusIsText(IPAdress, "Communication Failure");
        nvRsPage.waitUntilStatusIsText(IPAdress, "Communication Failure");
        nvRsPage.waitUntilStatusIsText(IPAdress, "Communication Failure");

        logger.log(LogStatus.INFO,"Checking that NVR status is Communication Failure");
        String status =  nvRsPage.GetStatus(IPAdress);
        Assert.assertEquals(status, "Communication Failure");

        logger.log(LogStatus.INFO,"Switch ON secured toggle-switch");
        nvRsPage.ClickOnSecuredSwitch();

        logger.log(LogStatus.INFO,"Press on Save");
        nvRsPage.SaveChangesButton();
        nvRsPage.waitUntilStatusIsV(IPAdress);
        nvRsPage.waitUntilStatusIsV(IPAdress);
        nvRsPage.waitUntilStatusIsV(IPAdress);

        logger.log(LogStatus.INFO,"Checking that NVR status is V");
        String statusON =  nvRsPage.GetStatus(IPAdress);
        Assert.assertEquals(statusON, "V");

        nvRsPage.Refresh();
        nvRsPage.ClickOnNvrByText(IPAdress);
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Checking that NVR status is V after refresh");
        String statusONAfterRefresh =  nvRsPage.GetStatus(IPAdress);
        Assert.assertEquals(statusONAfterRefresh, "V");
    }

    @Test
    public void ChangeSecuredAndCheckStatusTest() throws InterruptedException, IOException {
        logger=report.startTest("ChangeSecuredAndCheckStatusTest");

        Thread.sleep(1000);
        String IPAdress = Servers[1];

        nvRsPage.IfStatusIsNotVThanDeleteNVR(IPAdress);
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

        nvRsPage.ClickOnNvrByText(IPAdress);
        Thread.sleep(1000);
        String NVRPortBefore = nvRsPage.GetPortFromProperties();

        if(!NVRPortBefore.equals("80")){
            logger.log(LogStatus.INFO,"Changing NVR Port from " + NVRPortBefore + " to 80");
            nvRsPage.ChangePort("80");
            Thread.sleep(1000);

            if(nvRsPage.GetSecuredStatus().equals("ON")) {
                logger.log(LogStatus.INFO, "Switch ON secured toggle-switch");
                nvRsPage.ClickOnSecuredSwitch();
            }

            logger.log(LogStatus.INFO,"Press on Save");
            nvRsPage.SaveChangesButton();
            nvRsPage.waitUntilStatusIsV(IPAdress);
        }


        if(nvRsPage.GetSecuredStatus().equals("OFF")) {
            logger.log(LogStatus.INFO, "Switch ON secured toggle-switch");
            nvRsPage.ClickOnSecuredSwitch();
        }

        logger.log(LogStatus.INFO,"Press on Save");
        nvRsPage.SaveChangesButton();
        nvRsPage.waitUntilStatusIsText(IPAdress, "Communication Failure");
        nvRsPage.waitUntilStatusIsText(IPAdress, "Communication Failure");
        nvRsPage.waitUntilStatusIsText(IPAdress, "Communication Failure");

        logger.log(LogStatus.INFO,"Checking that NVR status is Communication Failure");
        String status =  nvRsPage.GetStatus(IPAdress);
        Assert.assertEquals(status, "Communication Failure");

        logger.log(LogStatus.INFO,"Changing NVR Port from " + NVRPortBefore + " to 443");
        nvRsPage.ChangePort("443");
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Press on Save");
        nvRsPage.SaveChangesButton();
        nvRsPage.waitUntilStatusIsV(IPAdress);
        nvRsPage.waitUntilStatusIsV(IPAdress);
        nvRsPage.waitUntilStatusIsV(IPAdress);

        logger.log(LogStatus.INFO,"Checking that NVR status is V");
        String statusON =  nvRsPage.GetStatus(IPAdress);
        Assert.assertEquals(statusON, "V");

        nvRsPage.Refresh();
        nvRsPage.ClickOnNvrByText(IPAdress);

        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Checking that NVR status is V after refresh");
        String statusONAfterRefresh =  nvRsPage.GetStatus(IPAdress);
        Assert.assertEquals(statusONAfterRefresh, "V");
    }


    @Test(enabled = false)
    public void ChangePortToValidAndCheckStatusTest() throws InterruptedException {
        logger=report.startTest("ChangePortToValidAndCheckStatusTest");

        String IPAdress  = Servers[1];
//        WebElement nvr = null;
//        for(int i = 0; i<nvRsPage.NVRsListCentralPanel().size(); i++){
//            WebElement element = nvRsPage.NVRsListCentralPanel().get(i);
//            String status =  nvRsPage.GetStatus(element.getText());
//            if(status.equals("V")){
//                nvr = element;
//                break;
//            }
//            else nvr=nvRsPage.NVRsListCentralPanel().get(0);
//        }
        try{nvRsPage.IfStatusIsNotVThanDeleteNVR(IPAdress);
            nvRsPage.IfNVRIsNotExistAddIt(IPAdress);
        }catch(Exception e){}

        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

        String InvalidPort = ""+nvRsPage.GetRandomDigit(1, 80);

        logger.log(LogStatus.INFO,"Click on NVR "+IPAdress);
        nvRsPage.FindElementByText(IPAdress).click();
        Thread.sleep(1000);

        String ValidPort = nvRsPage.GetPortFromProperties();
        logger.log(LogStatus.INFO,"Changing NVR Port to invalid " + InvalidPort);
        nvRsPage.ChangePort(InvalidPort);
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Click on Save button ");
        nvRsPage.SaveChangesButton();
        nvRsPage.waitUntilStatusIsText(IPAdress, "Offline");

        logger.log(LogStatus.INFO,"Click on NVR "+IPAdress);
        nvRsPage.FindElementByText(IPAdress).click();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Changing NVR Port to valid " + ValidPort);
        nvRsPage.ChangePort(ValidPort);

        logger.log(LogStatus.INFO,"Click on Save button ");
        nvRsPage.SaveChangesButton();

        String NVRPortAfter = nvRsPage.GetPortFromProperties();
        logger.log(LogStatus.INFO,"Check that change is saved ");
        Assert.assertEquals(NVRPortAfter, ValidPort);

        nvRsPage.waitUntilStatusIsV(IPAdress);
        String status =  nvRsPage.GetStatus(IPAdress);
        logger.log(LogStatus.INFO,"Checking that NVR status is V");
        Assert.assertEquals(status, "V");
    }

    @Test(enabled = false)
    public void ChangePortBySpinnersTest() throws InterruptedException, IOException {
        logger=report.startTest("ChangePortBySpinnersTest");

        String IPAdress = Servers[1];
        logger.log(LogStatus.INFO,"Change port for NVR " +IPAdress);

        logger.log(LogStatus.INFO,"If status for NVR " + IPAdress+" isn't 'Online' than delete it");
        nvRsPage.IfStatusIsNotVThanDeleteNVR(IPAdress);
        logger.log(LogStatus.INFO,"If NVR " + IPAdress+" isn't exist - add it");
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

        logger.log(LogStatus.INFO,"Click on NVR " + IPAdress);
        nvRsPage.ClickOnNvrByText(IPAdress);
        nvRsPage.WaitUntilStorrageSettingIsExist();
        Thread.sleep(1000);

        String NVRPortBefore = nvRsPage.GetPortFromProperties();

        nvRsPage.ClickOnPortDownSpinner();
        nvRsPage.ClickOnPortDownSpinner();
        nvRsPage.ClickOnPortUpSpinner();
        nvRsPage.ClickOnPortDownSpinner();
        nvRsPage.ClickOnPortDownSpinner();
        nvRsPage.ClickOnPortDownSpinner();
        nvRsPage.ClickOnPortUpSpinner();

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
        Assert.assertTrue(nvRsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
        Assert.assertTrue(nvRsPage.SaveButtonIsEnable());

        logger.log(LogStatus.INFO,"Click on Save button ");
        nvRsPage.SaveChangesButton();

        String NVRPortAfter = nvRsPage.GetPortFromProperties();

        logger.log(LogStatus.INFO,"Check that change is saved ");
        Assert.assertEquals(Integer.parseInt(NVRPortAfter), Integer.parseInt(NVRPortBefore)-3);

        nvRsPage.waitUntilStatusIsText(Servers[1], "Offline");
        String status =  nvRsPage.GetStatus(Servers[1]);

        logger.log(LogStatus.INFO,"Checking that NVR status is Offline");
        Assert.assertEquals(status, "Offline");

        logger.log(LogStatus.INFO,"Refresh");
        nvRsPage.Refresh();
        nvRsPage.FindNVRByText(Servers[1]).click();
        String NVRPortAfterRefresh = nvRsPage.GetPortFromProperties();

        logger.log(LogStatus.INFO,"Check that change is saved after refresh");
        Assert.assertEquals(Integer.parseInt(NVRPortAfterRefresh), Integer.parseInt(NVRPortBefore)-3);
    }

    @Test (enabled = false)
    public void SwitchOffStorageDefinitionsTest() throws InterruptedException, IOException {
        logger=report.startTest("SwitchOffStorageDefinitionsTest");

        String NVRAdress =Servers[0];

        logger.log(LogStatus.INFO,"If status for NVR " + NVRAdress+" isn't 'Online' than delete it");
        nvRsPage.IfStatusIsNotVThanDeleteNVR(NVRAdress);

        logger.log(LogStatus.INFO,"If NVR " + NVRAdress+" isn't exist - add it");
        nvRsPage.IfNVRIsNotExistAddIt(NVRAdress);

        logger.log(LogStatus.INFO,"Click on " +NVRAdress+" NVR");
        nvRsPage.FindNVRByText(NVRAdress).click();
        nvRsPage.WaitUntilStorrageSettingIsExist();
        Thread.sleep(3000);

        boolean dropDownIsclosed = nvRsPage.StorageDefDropDownIsClosed();
        if(dropDownIsclosed) {
            logger.log(LogStatus.INFO,"Expand drop down storage ");
            nvRsPage.ClickOnStorageDefinDropDownMenu();
        }

        boolean status = nvRsPage.SwitchStorageDefinitionsIsOn();

        logger.log(LogStatus.INFO,"Switch local drive toggle switch");
        nvRsPage.ClickOnStorageSwitch();

        if(!status){
            logger.log(LogStatus.INFO,"Press save button");
            nvRsPage.SaveChangesButton();
            Thread.sleep(2000);

            logger.log(LogStatus.INFO,"Switch OFF local drive toggle switch");
            nvRsPage.ClickOnStorageSwitch();
        }

        logger.log(LogStatus.INFO,"Click YES on dialog window");
        nvRsPage.ConfirmSwitchOffStorageDifinitions();

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
        Assert.assertTrue(nvRsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
        Assert.assertTrue(nvRsPage.SaveButtonIsEnable());

        logger.log(LogStatus.INFO,"Press save button");
        nvRsPage.SaveChangesButton();

        logger.log(LogStatus.INFO,"Check that Error doesn't appear");
        Assert.assertFalse(nvRsPage.CheckThatModalIsOpen());
        nvRsPage.waitUntilNoStorageSettingIsExist(NVRAdress);

        logger.log(LogStatus.INFO,"Checking that NVR status is 'No storage definitions'");
        String statusNVR =  nvRsPage.CheckStatusNoStorageSettings(NVRAdress);
        Assert.assertEquals(statusNVR, "No Storage Settings");

        logger.log(LogStatus.INFO,"Check that storage definitions status is Off");
        Assert.assertFalse(nvRsPage.SwitchStorageDefinitionsIsOn());

        logger.log(LogStatus.INFO,"Refresh page");
        nvRsPage.Refresh();

        logger.log(LogStatus.INFO,"Click on NVR " + NVRAdress);
        nvRsPage.FindNVRByText(NVRAdress).click();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that storage definitions status is OFF after refresh");
        Assert.assertFalse(nvRsPage.SwitchStorageDefinitionsIsOn());

        nvRsPage.waitUntilNoStorageSettingIsExist(NVRAdress);
        String statusAfter =  nvRsPage.CheckStatusNoStorageSettings(NVRAdress);
        logger.log(LogStatus.INFO,"Checking that NVR status is 'No storage definitions' after refresh");
        Assert.assertEquals(statusAfter, "No Storage Settings");
    }

    @Test
    public void SwitchOffStorageDefinitionsAndPressNoTest() throws InterruptedException {
        logger=report.startTest("SwitchOffStorageDefinitionsAndPressNoTest");

        String IPAdress =Servers[0];

        logger.log(LogStatus.INFO,"If status for NVR " + IPAdress+" isn't 'Online' than delete it");
        nvRsPage.IfStatusIsNotVThanDeleteNVR(IPAdress);

        logger.log(LogStatus.INFO,"If NVR " + IPAdress+" isn't exist - add it");
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

        logger.log(LogStatus.INFO,"Click on " +IPAdress+" NVR");
        nvRsPage.FindNVRByText(IPAdress).click();
        nvRsPage.WaitUntilStorrageSettingIsExist();
        Thread.sleep(1000);

        boolean status = nvRsPage.SwitchStorageDefinitionsIsOn();

        boolean dropDownIsclosed = nvRsPage.StorageDefDropDownIsClosed();
        if(dropDownIsclosed) {
            logger.log(LogStatus.INFO,"Expand drop down storage ");
            nvRsPage.ClickOnStorageDefinDropDownMenu();
        }

        logger.log(LogStatus.INFO,"Switch local drive toggle switch");
        nvRsPage.ClickOnStorageSwitch();

        if(!status){
            logger.log(LogStatus.INFO,"Press save button");
            nvRsPage.SaveChangesButton();

            logger.log(LogStatus.INFO,"Switch OFF local drive toggle switch");
            nvRsPage.ClickOnStorageSwitch();
        }

        logger.log(LogStatus.INFO,"Click NO on dialog window");
        nvRsPage.CancelSwitchOffStorageDifinitions();
        nvRsPage.WaitUntilDialogIsNotLocated();

        logger.log(LogStatus.INFO,"Check that storage definitions status is ON");
        Assert.assertTrue(nvRsPage.SwitchStorageDefinitionsIsOn());

        logger.log(LogStatus.INFO,"Refresh page");
        nvRsPage.Refresh();

        logger.log(LogStatus.INFO,"Click on " +IPAdress+" NVR");
        nvRsPage.FindNVRByText(IPAdress).click();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that storage definitions status is ON after refresh");
        Assert.assertTrue(nvRsPage.SwitchStorageDefinitionsIsOn());
    }

    @Test
    public void SwitchStorageDefinitionsTest() throws InterruptedException {
        logger=report.startTest("SwitchStorageDefinitionsTest");

        String IPAdress =Servers[0];

        logger.log(LogStatus.INFO,"If status for NVR " + IPAdress+" isn't 'Online' than delete it");
        nvRsPage.IfStatusIsNotVThanDeleteNVR(IPAdress);

        logger.log(LogStatus.INFO,"If NVR " + IPAdress+" isn't exist - add it");
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

        logger.log(LogStatus.INFO,"Click on " +IPAdress+" NVR");
        nvRsPage.FindNVRByText(IPAdress).click();
        nvRsPage.WaitUntilStorrageSettingIsExist();
        Thread.sleep(1000);

        boolean status = nvRsPage.SwitchStorageDefinitionsIsOn();

        boolean dropDownIsclosed = nvRsPage.StorageDefDropDownIsClosed();
        if(dropDownIsclosed) {
            logger.log(LogStatus.INFO,"Expand drop down storage ");
            nvRsPage.ClickOnStorageDefinDropDownMenu();
        }

        logger.log(LogStatus.INFO,"Switch local drive toggle switch");
        nvRsPage.ClickOnStorageSwitch();

        if(status){
            logger.log(LogStatus.INFO,"Click YES on dialog window");
            nvRsPage.ConfirmSwitchOffStorageDifinitions();
            nvRsPage.WaitUntilDialogIsNotLocated();
        }

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
        Assert.assertTrue(nvRsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enabled after name changes");
        Assert.assertTrue(nvRsPage.SaveButtonIsEnable());

        logger.log(LogStatus.INFO,"Click on Save button");
        nvRsPage.SaveChangesButton();

        logger.log(LogStatus.INFO,"Check that storage definitions status change is saved");
        boolean statusAfter = nvRsPage.SwitchStorageDefinitionsIsOn();
        if(!status) Assert.assertTrue(statusAfter);
        if(status) Assert.assertFalse(statusAfter);

        logger.log(LogStatus.INFO,"Check that Cancel button is disabled after name changes");
        Assert.assertFalse(nvRsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is disabled after name changes");
        Assert.assertFalse(nvRsPage.SaveButtonIsEnable());

        logger.log(LogStatus.INFO,"Refresh");
        nvRsPage.Refresh();

        logger.log(LogStatus.INFO,"Click on " +IPAdress+" NVR");
        nvRsPage.FindNVRByText(IPAdress).click();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that storage definitions status is changed after refresh");
        boolean statusAfterRefresh = nvRsPage.SwitchStorageDefinitionsIsOn();
        if(!status) Assert.assertTrue(statusAfterRefresh);
        if(status) Assert.assertFalse(statusAfterRefresh);

        String statusNoStorageDef =  nvRsPage.CheckStatusNoStorageSettings(IPAdress);
        if(statusAfterRefresh){
            logger.log(LogStatus.INFO,"Checking that NVR status isn't 'No storage definitions' after refresh");
            Assert.assertEquals(statusNoStorageDef, "");
        }
        if(!statusAfterRefresh){
            logger.log(LogStatus.INFO,"Checking that NVR status is 'No storage definitions' after refresh");
            Assert.assertEquals(statusNoStorageDef, "No Storage Settings");
        }
    }

   // @Test (priority = 1)
    public void SwitchStorageDefinitionTwiceTest() throws InterruptedException {
        logger=report.startTest("SwitchStorageDefinitionTwiceTest");

        String NVRAdress= Servers[0];
        logger.log(LogStatus.INFO,"If status for NVR " + NVRAdress+" isn't 'Online' than delete it");
        nvRsPage.IfStatusIsNotVThanDeleteNVR(NVRAdress);
        logger.log(LogStatus.INFO,"If NVR " + NVRAdress+" isn't exist - add it");
        nvRsPage.IfNVRIsNotExistAddIt(NVRAdress);

        logger.log(LogStatus.INFO,"Click on NVR "+ NVRAdress);
        nvRsPage.FindNVRByText(NVRAdress).click();
        nvRsPage.WaitUntilStorrageSettingIsExist();
        Thread.sleep(1000);

        boolean dropDownIsclosed = nvRsPage.StorageDefDropDownIsClosed();
        if(dropDownIsclosed) {
            logger.log(LogStatus.INFO,"Expand drop down storage ");
            nvRsPage.ClickOnStorageDefinDropDownMenu();
        }

        boolean statusSwitch =  nvRsPage.SwitchStorageDefinitionsIsOn();
        logger.log(LogStatus.INFO,"Storage definition status is "+ statusSwitch);

        if(statusSwitch){
            logger.log(LogStatus.INFO,"Switch Off storage definition"+ NVRAdress);
            nvRsPage.ClickOnStorageSwitch();
            nvRsPage.ConfirmSwitchOffStorageDifinitions();
            nvRsPage.WaitUntilDialogIsNotLocated();
            nvRsPage.SaveChangesButton();

            logger.log(LogStatus.INFO,"Switch ON storage definition"+ NVRAdress);
            nvRsPage.ClickOnStorageSwitch();
            nvRsPage.SaveChangesButton();
            String status =  nvRsPage.CheckStatusNoStorageSettings(NVRAdress);
          //  logger.log(LogStatus.INFO,"Check that No storage settings isn't exist in status ");
          //  Assert.assertEquals(status, "");
        }
        if(!statusSwitch){
            logger.log(LogStatus.INFO,"Switch ON storage definition"+ NVRAdress);
            nvRsPage.ClickOnStorageSwitch();
            nvRsPage.WaitUntilSaveButtonWillBeEnable();

            logger.log(LogStatus.INFO,"Click on Save button");
            nvRsPage.SaveChangesButton();
            Thread.sleep(1000);

            logger.log(LogStatus.INFO,"Switch Off storage definition"+ NVRAdress);
            nvRsPage.ClickOnStorageSwitch();

            nvRsPage.ConfirmSwitchOffStorageDifinitions();
            nvRsPage.WaitUntilDialogIsNotLocated();
            nvRsPage.WaitUntilSaveButtonWillBeEnable();

            logger.log(LogStatus.INFO,"Click on Save button");
            nvRsPage.SaveChangesButton();
        }
        logger.log(LogStatus.INFO,"Check that error message doesn't appear");
        Assert.assertFalse(nvRsPage.CheckThatModalIsOpen());
    }

    @Test(enabled =false)
    public void ChangeStorageDefinitionsTest() throws InterruptedException {
        logger=report.startTest("ChangeStorageDefinitionsTest");

        String NVRAdress= Servers[0];
        logger.log(LogStatus.INFO,"Change storage definitions for NVR " + NVRAdress);

        logger.log(LogStatus.INFO,"If status for NVR " + NVRAdress+" isn't 'Online' than delete it");
        nvRsPage.IfStatusIsNotVThanDeleteNVR(NVRAdress);
        logger.log(LogStatus.INFO,"If NVR " + NVRAdress+" isn't exist - add it");
        nvRsPage.IfNVRIsNotExistAddIt(NVRAdress);

        logger.log(LogStatus.INFO,"Click on NVR "+NVRAdress);
        nvRsPage.FindNVRByText(NVRAdress).click();
        nvRsPage.WaitUntilStorrageSettingIsExist();

        boolean dropDownIsclosed = nvRsPage.StorageDefDropDownIsClosed();
        if(dropDownIsclosed) {
            logger.log(LogStatus.INFO,"Expand drop down storage ");
            nvRsPage.ClickOnStorageDefinDropDownMenu();
        }
        int actualValue= Integer.parseInt(nvRsPage.GetStorageFromStorageProperties());
        int min =nvRsPage.GetMinGB();
        int max =nvRsPage.GetMaxGB();

        int randDown = nvRsPage.GetRandomDigit(1, actualValue-min);
        if(randDown>50) randDown = 50;
        logger.log(LogStatus.INFO,"Click "+ randDown+" times on Down Spinner");
        for(int i=0; i<randDown; i++){
            nvRsPage.ClickOnStorageDownSpinner();
        }

        logger.log(LogStatus.INFO,"Check that Storage definitions value is decreased by "+randDown+" times");
        int valueAfterDecr= Integer.parseInt(nvRsPage.GetStorageFromStorageProperties());
        Assert.assertEquals(valueAfterDecr, actualValue-randDown);

        int randUp = nvRsPage.GetRandomDigit(1, max-valueAfterDecr);
        if(randUp>50) randUp=50;
        logger.log(LogStatus.INFO,"Click "+ randUp+" times on UP Spinner");
        for(int i=0; i<randUp; i++){
            nvRsPage.ClickOnStorageUpSpinner();
        }

        logger.log(LogStatus.INFO,"Check that Storage definitions value is increased by "+randUp+" times");
        int valueAfterIncr= Integer.parseInt(nvRsPage.GetStorageFromStorageProperties());
        Assert.assertEquals(valueAfterIncr, actualValue-randDown+randUp);

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
        Assert.assertTrue(nvRsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
        Assert.assertTrue(nvRsPage.SaveButtonIsEnable());

        logger.log(LogStatus.INFO,"Click on Save button");
        nvRsPage.SaveChangesButton();

        logger.log(LogStatus.INFO,"Check that Storage definitions value is changes");
        int value= Integer.parseInt(nvRsPage.GetStorageFromStorageProperties());
        Assert.assertEquals(value, actualValue-randDown+randUp);
    }

    //tests for userName and for password
    @Test
    public void ChangeUserNameToInvalidAndCheckStatusTest() throws InterruptedException {
        logger=report.startTest("ChangeUserNameToInvalidAndCheckStatusTest");

        String IPAdress= Servers[1];
        logger.log(LogStatus.INFO,"Change user name for NVR " +IPAdress+ "to invalid and check that status is Server Unauthorized");
        logger.log(LogStatus.INFO,"If status for NVR " + IPAdress+" isn't 'Online' than delete it");
        nvRsPage.IfStatusIsNotVThanDeleteNVR(IPAdress);
        logger.log(LogStatus.INFO,"If NVR " + IPAdress+" isn't exist - add it");
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

        logger.log(LogStatus.INFO,"Change User Name");
        nvRsPage.ClickOnNvrByText(IPAdress);

        nvRsPage.WaitUntilStorrageSettingIsExist();
        Thread.sleep(1000);
        String newUserName = "UserName";
        nvRsPage.OpenUserName();
        nvRsPage.ChangeUserName(newUserName);

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
        Assert.assertTrue(nvRsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
        Assert.assertTrue(nvRsPage.SaveButtonIsEnable());

        nvRsPage.SaveChangesButton();

        nvRsPage.waitUntilStatusIsText(IPAdress, "Server Unauthorized");
        String status =  nvRsPage.GetStatus(IPAdress);

        logger.log(LogStatus.INFO,"Checking that NVR status is Server Unauthorized");
        Assert.assertEquals(status, "Server Unauthorized");

        nvRsPage.Refresh();

        String statusAfterRefresh =  nvRsPage.GetStatus(IPAdress);

        logger.log(LogStatus.INFO,"Checking that NVR status is Server Unauthorized");
        Assert.assertEquals(statusAfterRefresh, "Server Unauthorized");
    }

    @Test(enabled = false)
    public void ChangeUserNameToValidAndCheckStatusTest() throws InterruptedException {
        logger=report.startTest("ChangeUserNameToValidAndCheckStatusTest");

        String IPAdress= Servers[1];
        nvRsPage.ClickOnNvrByText(IPAdress);

        logger.log(LogStatus.INFO,"Change user name for NVR " +IPAdress+" to valid and check that status is Online");
        String invalidUserName = "UserName";
        String validUserName = "ADMIN";
        //change to invalid
        nvRsPage.OpenUserName();
        nvRsPage.ChangeUserName(invalidUserName);
        nvRsPage.SaveChangesButton();
        nvRsPage.waitUntilStatusIsText(IPAdress, "Server Unauthorized");

        //change to valid
        nvRsPage.OpenUserName();
        nvRsPage.ChangeUserName(validUserName);
        nvRsPage.SaveChangesButton();

        nvRsPage.waitUntilStatusIsV(IPAdress);
        String status =  nvRsPage.GetStatus(IPAdress);

        logger.log(LogStatus.INFO,"Checking that NVR status is V");
        Assert.assertEquals(status, "V");

        nvRsPage.Refresh();

        String statusAfterRefresh =  nvRsPage.GetStatus(IPAdress);

        logger.log(LogStatus.INFO,"Checking that NVR status is V after refresh");
        Assert.assertEquals(statusAfterRefresh, "V");
    }

   @Test
    public void ChangePasswordToInvalidAndCheckStatusTest() throws InterruptedException {
        logger=report.startTest("ChangePasswordToInvalidAndCheckStatusTest");

        String IPAdress= Servers[1];

        nvRsPage.IfStatusIsNotVThanDeleteNVR(IPAdress);
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

        nvRsPage.ClickOnNvrByText(IPAdress);

        nvRsPage.WaitUntilStorrageSettingIsExist();
        Thread.sleep(1000);
        logger.log(LogStatus.INFO,"Change password for NVR " +IPAdress+ "to invalid and check that status is Server Unauthorized");

        String validPassword = "password";
        nvRsPage.OpenUserName();

        nvRsPage.ChangePassword(validPassword);

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
        Assert.assertTrue(nvRsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
        Assert.assertTrue(nvRsPage.SaveButtonIsEnable());

        nvRsPage.SaveChangesButton();

        nvRsPage.waitUntilStatusIsText(IPAdress, "Server Unauthorized");
        String status =  nvRsPage.GetStatus(IPAdress);
        
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Checking that NVR status is Server Unauthorized");
        Assert.assertEquals(status, "Server Unauthorized");

        nvRsPage.Refresh();

        String statusAfterRefresh =  nvRsPage.GetStatus(IPAdress);

        logger.log(LogStatus.INFO,"Checking that NVR status is Server Unauthorized");
        Assert.assertEquals(statusAfterRefresh, "Server Unauthorized");
    }

    @Test(enabled = false)
    public void DeleteOneNVRAndCheckPropertiesPanelTest() throws InterruptedException {
        logger=report.startTest("DeleteOneNVRAndCheckPropertiesPanelTest");

        String IPAdress1= Servers[0];
        String IPAdress2= Servers[1];

        logger.log(LogStatus.INFO,"add NVR " + IPAdress1+" if it isn't exists ");
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress1);

        logger.log(LogStatus.INFO,"add NVR " + IPAdress2+" if it isn't exists ");
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress2);

        logger.log(LogStatus.INFO,"Remove NVR: " + IPAdress2+" by Button 'Remove NVR'");
        nvRsPage.FindElementByText(IPAdress2).click();
        Thread.sleep(1000);
        nvRsPage.PressOnRemoveNVRsByButton();
        nvRsPage.ConfirmRemoveNVRsByButton();

        String IPAddressFromProperties = nvRsPage.GetIPAdressFromProperties();
        logger.log(LogStatus.INFO,"Check that Properties has not " + IPAdress2);
        Assert.assertFalse(IPAddressFromProperties.contains(IPAdress2));
    }

    @Test
    public void DeleteOneNVRClickOnAnotherNVRAndCheckPropertiesPanelTest() throws InterruptedException {
        logger=report.startTest("DeleteOneNVRClickOnAnotherNVRAndCheckPropertiesPanelTest");

        String IPAdress1= Servers[0];
        String IPAdress2= Servers[1];

        logger.log(LogStatus.INFO,"add NVR " + IPAdress1+" if it isn't exists ");
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress1);

        logger.log(LogStatus.INFO,"add NVR " + IPAdress2+" if it isn't exists ");
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress2);

        logger.log(LogStatus.INFO,"Remove NVR: " + IPAdress2+" by Button 'Remove NVR'");
        nvRsPage.FindElementByText(IPAdress2).click();
        Thread.sleep(1000);
        nvRsPage.PressOnRemoveNVRsByButton();
        nvRsPage.ConfirmRemoveNVRsByButton();

        logger.log(LogStatus.INFO,"Click on " + IPAdress1+" NVR");
        nvRsPage.FindElementByText(IPAdress1).click();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that Properties Panel Layout is OK");
        Assert.assertTrue(nvRsPage.IPAdressFieldIsExist());
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
            String screenshot_path=nvRsPage.takeScreenshot(driver, "NVRs", result.getName());
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
