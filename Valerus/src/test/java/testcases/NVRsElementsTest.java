package testcases;

import pageObjects.NVRsPage;
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

import static org.apache.xalan.xsltc.compiler.util.Util.println;

public class NVRsElementsTest {

    public WebDriver driver;
    public NVRsPage nvRsPage;
    public SearchPage searchPage;
    public String[] Servers;

    ExtentReports report;
    ExtentTest logger;

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void setup(@Optional("ie")String browser) throws IOException, InterruptedException {
    }

    @BeforeTest
    public void startReport(){
        report=new ExtentReports(System.getProperty("user.dir") +"/test-output/NVRs/NVRsElementsReport.html", true);
    }

    @BeforeMethod(alwaysRun = true)
    public void gotoNVRPage() throws InterruptedException, IOException {
        String WebDriverLocation = System.getenv("WebDriver");

       // System.setProperty("webdriver.chrome.driver", WebDriverLocation +"\\chromedriver.exe");
      //  driver = new ChromeDriver();

//        System.setProperty("webdriver.ie.driver", WebDriverLocation+"\\IEDriverServer.exe");
//        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
//        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
//        capabilities.setCapability(InternetExplorerDriver.IE_USE_PER_PROCESS_PROXY, true);
//        capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
//        capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
//        driver = new InternetExplorerDriver(capabilities);
        
        
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
        searchPage = PageFactory.initElements(driver, SearchPage.class);
        Servers = nvRsPage.getServersList();

        driver.navigate().to("http://" + Servers[0]);
        nvRsPage.SignIn();
        nvRsPage.goToNVRsPageFromLandingPage();
//        driver.navigate().to("http://" + Servers[0]);
//        try{
//            driver.switchTo().alert().accept();
//        }catch(Exception a){}
//
//        boolean loginPage= nvRsPage.LogInPageIsLoaded();
//        if(loginPage){
//            logger.log(LogStatus.INFO,"Sign In with admin user name");
//            nvRsPage.SignIn();
//        }
//
//        boolean nvrsPage = nvRsPage.NVRsPageIsLoaded();
//        if(!nvrsPage) nvRsPage.goToNVRsPage();
    }

    //Tests for Name Field
    @Test
    public void ChangeNamePressCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeNamePressCancelTest");
        String IPAdress =Servers[1];

        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);
        String NameNVRsText =  IPAdress + "Changed" + nvRsPage.GetRandomDigit(0, 100);

        WebElement nvr =nvRsPage.FindNVRByText(IPAdress);
        logger.log(LogStatus.INFO,"Click on "+IPAdress+"nvr");
        nvr.click();
        Thread.sleep(1000);

        String NVRNameBefore = nvRsPage.GetNameFromProperties();
        logger.log(LogStatus.INFO,"Input " + NameNVRsText + " in name field ");
        nvRsPage.ChangeName(NameNVRsText);

        logger.log(LogStatus.INFO,"Press Cancel") ;
        nvRsPage.CancelChangesButton();

        String NVRText = nvRsPage.GetNameFromProperties();
        logger.log(LogStatus.INFO,"Check that the NVR name isn't changed") ;
        Assert.assertEquals(NVRText, NVRNameBefore);

        logger.log(LogStatus.INFO,"Check that Cancel button is disabled after name changes");
        Assert.assertFalse(nvRsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is disabled after name changes");
        Assert.assertFalse(nvRsPage.SaveButtonIsEnable());
    }

    @Test(priority = 1)
    public void ChangeNameExitCancelUnsavedChangesExitCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeNameExitCancelUnsavedChangesExitCancelTest");

        String IPAdress =Servers[1];
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

        WebElement nvr =nvRsPage.FindNVRByText(Servers[1]);
        String NameNVRsText =  IPAdress + "Changed"+nvRsPage.GetRandomDigit(0, 100);;
        logger.log(LogStatus.INFO,"Click on " + IPAdress + " NVR");
        nvr.click();

        logger.log(LogStatus.INFO,"Input " + NameNVRsText + " name in Name field");
        nvRsPage.ChangeName(NameNVRsText);

        logger.log(LogStatus.INFO,"Go to Search page");
        nvRsPage.GoToSearchPage();

        logger.log(LogStatus.INFO,"Click Cancel on Unsaved changes dialog");
        nvRsPage.CancelUnsavedChanges();

        String NVRNameAfter = nvRsPage.GetNVRName(IPAdress);
        logger.log(LogStatus.INFO,"Check that change is remained");
        Assert.assertEquals(NVRNameAfter, NameNVRsText);

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
        Assert.assertTrue(nvRsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
        Assert.assertTrue(nvRsPage.SaveButtonIsEnable());
    }

    @Test
    public void ChangeNameExitPressDontSaveUnsavedChangesTest() throws Exception {
        logger=report.startTest("ChangeNameExitPressDontSaveUnsavedChangesTest");

        String IPAdress =Servers[1];
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

        String NameNVRsText =  IPAdress + "Changed"+nvRsPage.GetRandomDigit(0, 100);;
           logger.log(LogStatus.INFO,"Changing NVR name from " + IPAdress + " to "+NameNVRsText + "and pressing 'Do not save' on the 'Unsaved changes' window");

        nvRsPage.FindNVRByText(IPAdress).click();
        Thread.sleep(1000);

        String NVRNameBefore = nvRsPage.GetNVRName(IPAdress);
        nvRsPage.ChangeName(NameNVRsText);

           logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
           Assert.assertTrue(nvRsPage.CancelButtonIsEnable());

           logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
           Assert.assertTrue(nvRsPage.SaveButtonIsEnable());

        logger.log(LogStatus.INFO,"Go to Monitoring page");
        nvRsPage.GoToMonitoringPage();
        logger.log(LogStatus.INFO,"Press don't save button on unsaved changes dialog");
        nvRsPage.PressDontSaveUnsavedChanges();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that Monitoring page is loaded");
           Assert.assertTrue(nvRsPage.MonitoringPageIsLoaded());

        logger.log(LogStatus.INFO,"Go to NVR page");
        nvRsPage.goToNVRsPage();
        logger.log(LogStatus.INFO,"Click on NVR "+IPAdress);
        nvRsPage.FindElementByText(IPAdress).click();
        Thread.sleep(1000);

        String NVRNameAfter = nvRsPage.GetNVRName(IPAdress);

           logger.log(LogStatus.INFO,"Check that change isn't saved ");///isn't passed
           Assert.assertEquals(NVRNameAfter, NVRNameBefore);

        nvRsPage.Refresh();
        logger.log(LogStatus.INFO,"Click on NVR "+IPAdress);
        nvRsPage.FindElementByText(IPAdress).click();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that change isn't saved after refresh");
           Assert.assertEquals(NVRNameAfter, NVRNameBefore);
    }

    @Test
    public void ChangeNameExitPressSaveUnsavedChangesTest() throws InterruptedException, IOException {
        logger=report.startTest("ChangeNameExitPressSaveUnsavedChangesTest");

        String IPAdress =Servers[1];

        logger.log(LogStatus.INFO,"If status for NVR " + IPAdress+" isn't 'Online' than delete it");
        nvRsPage.IfStatusIsNotVThanDeleteNVR(IPAdress);
        logger.log(LogStatus.INFO,"If NVR " + IPAdress+" isn't exist - add it");
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

        String NameNVRsText =  IPAdress + "Changed"+nvRsPage.GetRandomDigit(0, 100);;
        logger.log(LogStatus.INFO,"Changing NVR name from " + IPAdress + " to "+NameNVRsText + "and pressing 'Save' on the 'Unsaved changes' window");

        nvRsPage.FindElementByText(IPAdress).click();
        nvRsPage.WaitUntilStorrageSettingIsExist();
        Thread.sleep(2000);
        nvRsPage.ChangeName(NameNVRsText);

           logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
           Assert.assertTrue(nvRsPage.CancelButtonIsEnable());

           logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
           Assert.assertTrue(nvRsPage.SaveButtonIsEnable());

        nvRsPage.GoToSearchPage();
        nvRsPage.PressSaveUnsavedChanges();

           logger.log(LogStatus.INFO,"Check that Invistigation page is loaded");
           Assert.assertTrue(searchPage.SearchPageIsLoaded());

        Thread.sleep(1000);
        nvRsPage.goToNVRsPage();
        nvRsPage.FindElementByText(IPAdress).click();
        Thread.sleep(1000);

        String NVRNameAfter = nvRsPage.GetNVRName(IPAdress);

           logger.log(LogStatus.INFO,"Check that change is saved ");
           Assert.assertEquals(NVRNameAfter, NameNVRsText);

        nvRsPage.Refresh();
        nvRsPage.FindElementByText(IPAdress).click();
        Thread.sleep(1000);

        String NVRNameAfterRefresh = nvRsPage.GetNVRName(IPAdress);
           logger.log(LogStatus.INFO,"Check that change is saved after refresh");
           Assert.assertEquals(NVRNameAfterRefresh, NameNVRsText);
    }

    @Test
    public void CheckPropertiesAfterChangeNameTest() throws InterruptedException, IOException {
        logger=report.startTest("CheckPropertiesAfterChangeNameTest");

        String IPAdress =Servers[1];
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

        WebElement nvr =nvRsPage.FindNVRByText(IPAdress);
        String NameNVRsText =IPAdress;
        logger.log(LogStatus.INFO,"Check that after changing in Name field there are not changing in another fields");
        nvRsPage.FindNVRByText(IPAdress).click();
        Thread.sleep(1000);

        String IPAdressStep1 = nvRsPage.GetIPAdressFromProperties();
        String MACAdressStep1 = nvRsPage.GetMACAdressFromProperties();
        String VersionStep1 = nvRsPage.GetVersionFromProperties();

        nvRsPage.ChangeName(NameNVRsText);

        String IPAdressStep2 = nvRsPage.GetIPAdressFromProperties();
        String MACAdressStep2 = nvRsPage.GetMACAdressFromProperties();
        String VersionStep2 = nvRsPage.GetVersionFromProperties();

        Assert.assertEquals(IPAdressStep2, IPAdressStep1);
        Assert.assertEquals(MACAdressStep2, MACAdressStep1);
        Assert.assertEquals(VersionStep2, VersionStep1);

        logger.log(LogStatus.INFO,"Co to Invistigation page");
        nvRsPage.GoToSearchPage();
        nvRsPage.PressSaveUnsavedChanges();
        Thread.sleep(1000);
        nvRsPage.goToNVRsPage();
        nvRsPage.FindElementByText(IPAdress).click();
        Thread.sleep(1000);

        String IPAdressStep3 = nvRsPage.GetIPAdressFromProperties();
        String MACAdressStep3 = nvRsPage.GetMACAdressFromProperties();
        String VersionStep3 = nvRsPage.GetVersionFromProperties();

        Assert.assertEquals(IPAdressStep3, IPAdressStep1);
        Assert.assertEquals(MACAdressStep3, MACAdressStep1);
        Assert.assertEquals(VersionStep3, VersionStep1);
    }

    //tests for IP Adress
    @Test
    public void ChangeIPAdressPressCancelExitTest() throws InterruptedException {
        logger=report.startTest("ChangeIPAdressPressCancelExitTest");

        String IPAdress =Servers[1];
        logger.log(LogStatus.INFO,"If status for NVR " + IPAdress+" isn't 'Online' than delete it");
        nvRsPage.IfStatusIsNotVThanDeleteNVR(IPAdress);
        logger.log(LogStatus.INFO,"If NVR " + IPAdress+" isn't exist - add it");
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

        String IPAdressText =  IPAdress + "IP"+nvRsPage.GetRandomDigit(0, 100);
        logger.log(LogStatus.INFO,"Changing NVR IP Adress from " + IPAdress + " to "+IPAdressText + " and press 'Cancel'");

        nvRsPage.FindNVRByText(IPAdress).click();
        nvRsPage.WaitUntilStorrageSettingIsExist();
        Thread.sleep(1000);

        String NVRIPBefore = nvRsPage.GetIPAdress(IPAdress);
        nvRsPage.ChangeIPAdress(IPAdressText);
        Thread.sleep(500);
        logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
        Assert.assertTrue(nvRsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
        Assert.assertTrue(nvRsPage.SaveButtonIsEnable());

        nvRsPage.CancelChangesButton();
        String NVRIPAfter = nvRsPage.GetIPAdress(IPAdress);

        logger.log(LogStatus.INFO,"Check that the IP Adress isn't changed") ;
        Assert.assertEquals(NVRIPAfter, NVRIPBefore);

        nvRsPage.Refresh();
        nvRsPage.FindNVRByText(IPAdress).click();
        nvRsPage.WaitUntilStorrageSettingIsExist();
        Thread.sleep(1000);
        String NVRNameAfterRefresh = nvRsPage.GetIPAdress(IPAdress);
        logger.log(LogStatus.INFO,"Check that the IP Adress isn't changed after refresh") ;
        Assert.assertEquals(NVRNameAfterRefresh, NVRIPBefore);
    }

    @Test
    public void ChangeIPAdressExitCancelUnsavedChangesTest() throws InterruptedException {
        logger=report.startTest("ChangeIPAdressExitCancelUnsavedChangesTest");

        String IPAdress =Servers[1];
        logger.log(LogStatus.INFO,"If status for NVR " + IPAdress+" isn't 'Online' than delete it");
        nvRsPage.IfStatusIsNotVThanDeleteNVR(IPAdress);
        logger.log(LogStatus.INFO,"If NVR " + IPAdress+" isn't exist - add it");
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

        nvRsPage.FindNVRByText(IPAdress).click();
        nvRsPage.WaitUntilStorrageSettingIsExist();
        Thread.sleep(1000);
        String IPAdressText = "IP"+nvRsPage.GetRandomDigit(0, 100);;
        logger.log(LogStatus.INFO,"Changing NVR IP Adress from " + IPAdress + " to "+IPAdressText + " and press 'Cancel'");

        Thread.sleep(1000);
        String NVRIPBefore = nvRsPage.GetIPAdress(IPAdress);
        nvRsPage.ChangeIPAdress(IPAdressText);
//        Thread.sleep(2000);
        logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
        Assert.assertTrue(nvRsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
        Assert.assertTrue(nvRsPage.SaveButtonIsEnable());

        nvRsPage.GoToSearchPage();
        nvRsPage.WaitUntilDialogIsLocated();
        nvRsPage.CancelUnsavedChanges();
        String NVRIPAfter = nvRsPage.GetIPAdress(IPAdress);

        logger.log(LogStatus.INFO,"Check that change isn't saved");
        Assert.assertEquals(NVRIPAfter, IPAdress+IPAdressText);
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Go to the Search page");
        nvRsPage.GoToSearchPage();
        nvRsPage.PressDontSaveUnsavedChanges();
    }

    @Test
    public void ChangeIPAdressExitPressDontSaveUnsavedChangesTest() throws InterruptedException, IOException {
        logger=report.startTest("ChangeIPAdressExitPressDontSaveUnsavedChangesTest");

        String IPAdress =Servers[1];
        logger.log(LogStatus.INFO,"If status for NVR " + IPAdress+" isn't 'Online' than delete it");
        nvRsPage.IfStatusIsNotVThanDeleteNVR(IPAdress);
        logger.log(LogStatus.INFO,"If NVR " + IPAdress+" isn't exist - add it");
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

        String IPAdressText =  IPAdress + "IP"+nvRsPage.GetRandomDigit(0, 100);;
           logger.log(LogStatus.INFO,"Changing NVR IP Adress from " + IPAdress + " to "+IPAdressText + " and press 'Do not save' on the 'Unsaved changes' window");
        nvRsPage.FindNVRByText(IPAdress).click();
        nvRsPage.WaitUntilStorrageSettingIsExist();
        Thread.sleep(1000);

        String NVRIPBefore = nvRsPage.GetIPAdress(IPAdress);
        logger.log(LogStatus.INFO,"Change IP address "+IPAdress+ " to "+IPAdressText);
        nvRsPage.ChangeIPAdress(IPAdressText);
        Thread.sleep(1000);
           logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
           Assert.assertTrue(nvRsPage.CancelButtonIsEnable());

           logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
           Assert.assertTrue(nvRsPage.SaveButtonIsEnable());

        logger.log(LogStatus.INFO,"Go to Monitoring page");
        nvRsPage.GoToMonitoringPage();
        nvRsPage.WaitUntilDialogIsLocated();
        logger.log(LogStatus.INFO,"Press don't save button on unsaved changes dialog");
        nvRsPage.PressDontSaveUnsavedChanges();
        logger.log(LogStatus.INFO,"Check that Monitoring page is loaded");
        Assert.assertTrue(nvRsPage.MonitoringPageIsLoaded());
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Go to NVRs page");
        nvRsPage.goToNVRsPage();
        logger.log(LogStatus.INFO,"Click on the device " + IPAdress);
        nvRsPage.FindElementByText(IPAdress).click();
        Thread.sleep(1000);

        String NVRIPAfter = nvRsPage.GetIPAdress(IPAdress);

           logger.log(LogStatus.INFO,"Check that change isn't saved ");
           Assert.assertEquals(NVRIPAfter, NVRIPBefore);

        logger.log(LogStatus.INFO,"Refresh page ");
        nvRsPage.Refresh();
        logger.log(LogStatus.INFO,"Click on the device " + IPAdress);
        nvRsPage.FindElementByText(NVRIPBefore).click();

           logger.log(LogStatus.INFO,"Check that change isn't saved after refresh");
           Assert.assertEquals(NVRIPAfter, NVRIPBefore);
    }

    @Test(enabled =false)
    public void ChangeIPAdressExitPressSaveUnsavedChangesTest() throws InterruptedException, IOException {
        logger=report.startTest("ChangeIPAdressExitPressSaveUnsavedChangesTest");

        String IPAdress =Servers[1];
        logger.log(LogStatus.INFO,"If status for NVR " + IPAdress+" isn't 'Online' than delete it");
        nvRsPage.IfStatusIsNotVThanDeleteNVR(IPAdress);
        logger.log(LogStatus.INFO,"If NVR " + IPAdress+" isn't exist - add it");
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

        String IPAdressText =  "IP"+nvRsPage.GetRandomDigit(0, 100);;
        logger.log(LogStatus.INFO,"Changing NVR IP Adress from " + IPAdress + " to "+IPAdressText + " and press 'Save' on the 'Unsaved changes' window");
        nvRsPage.FindNVRByText(IPAdress).click();
        nvRsPage.WaitUntilStorrageSettingIsExist();
        Thread.sleep(3000);

        nvRsPage.ChangeIPAdress(IPAdressText);
        Thread.sleep(2000);
        logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
        Assert.assertTrue(nvRsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
        Assert.assertTrue(nvRsPage.SaveButtonIsEnable());

        nvRsPage.GoToSearchPage();
        nvRsPage.PressSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Check that Search page is loaded");
        Assert.assertTrue(searchPage.SearchPageIsLoaded());

        Thread.sleep(1000);
        nvRsPage.goToNVRsPage();
        nvRsPage.FindElementByText(IPAdress).click();

        String NVRIPAfter = nvRsPage.GetIPAdress(IPAdress);

        logger.log(LogStatus.INFO,"Check that change is saved ");
        Assert.assertEquals(NVRIPAfter, IPAdress+IPAdressText);

        nvRsPage.Refresh();
        nvRsPage.FindElementByText(IPAdress).click();

        logger.log(LogStatus.INFO,"Check that change is saved after refresh");
        Assert.assertEquals(NVRIPAfter,IPAdress+IPAdressText);
    }

    @Test
    public void CheckPropertiesAfterChangeIPAdressTest() throws InterruptedException, IOException {
        logger=report.startTest("CheckPropertiesAfterChangeIPAdressTest");

        String IPAdress =Servers[1];
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);
        String IPAdressText =Servers[1]+"text";

        logger.log(LogStatus.INFO,"Check that after changing in IP Adress field there are not changing in another fields");
        nvRsPage.FindNVRByText(IPAdress).click();
        Thread.sleep(1000);

        String NameStep1 = nvRsPage.GetNameFromProperties();
        String MACAdressStep1 = nvRsPage.GetMACAdressFromProperties();
        String VersionStep1 = nvRsPage.GetVersionFromProperties();

        nvRsPage.ChangeIPAdress(IPAdressText);

        String NameStep2 = nvRsPage.GetNameFromProperties();
        String MACAdressStep2 = nvRsPage.GetMACAdressFromProperties();
        String VersionStep2 = nvRsPage.GetVersionFromProperties();

        Assert.assertEquals(NameStep2, NameStep1);
        Assert.assertEquals(MACAdressStep2, MACAdressStep1);
        Assert.assertEquals(VersionStep2, VersionStep1);

        nvRsPage.GoToSearchPage();
        nvRsPage.PressSaveUnsavedChanges();
        Thread.sleep(1000);
        nvRsPage.goToNVRsPage();
        nvRsPage.FindElementByText(IPAdress).click();

        String NameStep3 = nvRsPage.GetNameFromProperties();
        String MACAdressStep3 = nvRsPage.GetMACAdressFromProperties();
        String VersionStep3 = nvRsPage.GetVersionFromProperties();

        Assert.assertEquals(NameStep3, NameStep1);
        Assert.assertEquals(MACAdressStep3, MACAdressStep1);
        Assert.assertEquals(VersionStep3, VersionStep1);
    }

    //tests for port field
    @Test
    public void ChangePortPressCancelTest() throws InterruptedException {
        logger=report.startTest("ChangePortPressCancelTest");

        String IPAdress =Servers[1];
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

        logger.log(LogStatus.INFO,"Click on " + IPAdress+" NVR");
        WebElement nvr =nvRsPage.FindNVRByText(IPAdress);
        nvr.click();

        String PortText = ""+ nvRsPage.GetRandomDigit(85,900);;
        String NVRPortBefore = nvRsPage.GetPortFromProperties();

        logger.log(LogStatus.INFO,"Changing NVR Port for NVR " + IPAdress + " to" + PortText);
        nvRsPage.ChangePort(PortText);
        nvRsPage.WaitUntilSaveButtonIsAvaliable();

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
        Assert.assertTrue(nvRsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
        Assert.assertTrue(nvRsPage.SaveButtonIsEnable());

        logger.log(LogStatus.INFO,"Click on Cancel button");
        nvRsPage.CancelChangesButton();

        String NVRPortAfter = nvRsPage.GetPortFromProperties();
        logger.log(LogStatus.INFO,"Check that the port isn't changed") ;
        Assert.assertEquals(NVRPortAfter, NVRPortBefore);

        logger.log(LogStatus.INFO,"Check that Cancel button is disabled after name changes");
        Assert.assertFalse(nvRsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is disabled after name changes");
        Assert.assertFalse(nvRsPage.SaveButtonIsEnable());
    }

    @Test
    public void ChangePortExitCancelUnsavedChangesTest() throws InterruptedException {
        logger=report.startTest("ChangePortExitCancelUnsavedChangesTest");

        String IPAdress =Servers[1];
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

        WebElement nvr =nvRsPage.FindNVRByText(IPAdress);
        String PortText = ""+ nvRsPage.GetRandomDigit(85, 900);

        logger.log(LogStatus.INFO,"Click on " + IPAdress+" NVR");
        nvr.click();
        nvRsPage.WaitUntilStorrageSettingIsExist();
        Thread.sleep(1000);

        String NVRPortBefore = nvRsPage.GetPortFromProperties();
        logger.log(LogStatus.INFO,"Changing NVR Port for NVR " + IPAdress + " to" + PortText);
        nvRsPage.ChangePort(PortText);
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
        Assert.assertTrue(nvRsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
        Assert.assertTrue(nvRsPage.SaveButtonIsEnable());

        logger.log(LogStatus.INFO,"Go to Search page");
        nvRsPage.GoToSearchPage();

        logger.log(LogStatus.INFO,"Press Cancel on Unsaved changes dialog");
        nvRsPage.CancelUnsavedChanges();

        String NVRPortAfter = nvRsPage.GetPortFromProperties();
        logger.log(LogStatus.INFO,"Check that port isn't saved");
        Assert.assertEquals(NVRPortAfter, PortText);

        logger.log(LogStatus.INFO,"Press Cancel");
        nvRsPage.CancelChangesButton();
    }

    @Test
    public void ChangePortExitPressDontSaveUnsavedChangesTest() throws InterruptedException, IOException {
        logger=report.startTest("ChangePortExitPressDontSaveUnsavedChangesTest");

        String IPAdress =Servers[1];

        logger.log(LogStatus.INFO,"If status for NVR " + IPAdress+" isn't 'Online' than delete it");
        nvRsPage.IfStatusIsNotVThanDeleteNVR(IPAdress);

        logger.log(LogStatus.INFO,"If NVR " + IPAdress+" isn't exist - add it");
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

        String PortText = ""+ nvRsPage.GetRandomDigit(85, 900);

        logger.log(LogStatus.INFO,"Click on " + IPAdress+" NVR");
        nvRsPage.FindNVRByText(IPAdress).click();
        nvRsPage.WaitUntilStorrageSettingIsExist();
        Thread.sleep(1000);

        String NVRPortBefore = nvRsPage.GetPortFromProperties();
        logger.log(LogStatus.INFO,"Changing NVR Port for NVR " + IPAdress + " to" + PortText);
        nvRsPage.ChangePort(PortText);
        nvRsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
        Assert.assertTrue(nvRsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
        Assert.assertTrue(nvRsPage.SaveButtonIsEnable());

        logger.log(LogStatus.INFO,"Go to Search page");
        nvRsPage.GoToSearchPage();

        logger.log(LogStatus.INFO,"Press Do not Save");
        nvRsPage.PressDontSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Check that Search page is loaded");
        Assert.assertTrue(searchPage.SearchPageIsLoaded());
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Go to NVRs page");
        nvRsPage.goToNVRsPage();

        logger.log(LogStatus.INFO,"Click on " + IPAdress+" NVR");
        nvRsPage.FindElementByText(IPAdress).click();

        String NVRPortAfter = nvRsPage.GetPortFromProperties();
        logger.log(LogStatus.INFO,"Check that change isn't saved ");///isn't passed
        Assert.assertEquals(NVRPortAfter, NVRPortBefore);
    }

    @Test
    public void ChangePortExitPressSaveUnsavedChangesTest() throws InterruptedException, IOException {
        logger=report.startTest("ChangePortExitPressSaveUnsavedChangesTest");

        String IPAdress =Servers[1];

        logger.log(LogStatus.INFO,"If status for NVR " + IPAdress+" isn't 'Online' than delete it");
        nvRsPage.IfStatusIsNotVThanDeleteNVR(IPAdress);
        logger.log(LogStatus.INFO,"If NVR " + IPAdress+" isn't exist - add it");
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

        logger.log(LogStatus.INFO,"Click on " + IPAdress+" NVR");
        nvRsPage.FindNVRByText(IPAdress).click();
        nvRsPage.WaitUntilStorrageSettingIsExist();
//        Thread.sleep(1000);

        String PortText = ""+ nvRsPage.GetRandomDigit(85, 900);
        String NVRPortBefore = nvRsPage.GetPortFromProperties();

        logger.log(LogStatus.INFO,"Changing NVR Port for NVR " + IPAdress + " to" + PortText);
        nvRsPage.ChangePort(PortText);
        nvRsPage.WaitUntilSaveButtonWillBeEnable();

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
        Assert.assertTrue(nvRsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
        Assert.assertTrue(nvRsPage.SaveButtonIsEnable());

        logger.log(LogStatus.INFO,"Go to Monitoring page");
        nvRsPage.GoToMonitoringPage();

        logger.log(LogStatus.INFO,"Press Save in Unsaved changes dialog");
        nvRsPage.PressSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Check that Invistigation page is loaded");
        Assert.assertTrue(nvRsPage.MonitoringPageIsLoaded());

        logger.log(LogStatus.INFO,"Go to NVRs page");
        nvRsPage.goToNVRsPage();

        logger.log(LogStatus.INFO,"Click on " + IPAdress+" NVR");
        nvRsPage.FindElementByText(IPAdress).click();
        Thread.sleep(1000);

        String NVRPortAfter = nvRsPage.GetPortFromProperties();
        logger.log(LogStatus.INFO,"Check that change is saved ");
        Assert.assertEquals(NVRPortAfter, PortText);
    }

    @Test
    public void CheckPropertiesAfterChangePortTest() throws InterruptedException, IOException {
        logger=report.startTest("CheckPropertiesAfterChangePortTest");

        String IPAdress =Servers[1];
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);
        String PortText = ""+nvRsPage.GetRandomDigit(1, 100);
        logger.log(LogStatus.INFO,"Check that after changing in Port field there are not changing in another fields");
        nvRsPage.FindNVRByText(IPAdress).click();
        Thread.sleep(1000);

        String NameStep1 = nvRsPage.GetUserNameFromProperties();
        String IPAdressStep1 = nvRsPage.GetIPAdressFromProperties();
        String MACAdressStep1 = nvRsPage.GetMACAdressFromProperties();
        String VersionStep1 = nvRsPage.GetVersionFromProperties();

        nvRsPage.ChangePort(PortText);
        nvRsPage.WaitUntilSaveButtonIsAvaliable();

        String NameStep2 = nvRsPage.GetUserNameFromProperties();
        String IPAdressStep2 = nvRsPage.GetIPAdressFromProperties();
        String MACAdressStep2 = nvRsPage.GetMACAdressFromProperties();
        String VersionStep2 = nvRsPage.GetVersionFromProperties();

        Assert.assertEquals(NameStep2, NameStep1);
        Assert.assertEquals(IPAdressStep2, IPAdressStep1);
        Assert.assertEquals(MACAdressStep2, MACAdressStep1);
        Assert.assertEquals(VersionStep2, VersionStep1);

        nvRsPage.GoToSearchPage();
        nvRsPage.PressSaveUnsavedChanges();
        Thread.sleep(1000);
        nvRsPage.goToNVRsPage();
        nvRsPage.FindElementByText(IPAdress).click();
        Thread.sleep(1000);

        String NameStep3 = nvRsPage.GetUserNameFromProperties();
        String IPAdressStep3 = nvRsPage.GetIPAdressFromProperties();
        String MACAdressStep3 = nvRsPage.GetMACAdressFromProperties();
        String VersionStep3 = nvRsPage.GetVersionFromProperties();

        Assert.assertEquals(NameStep3, NameStep1);
        Assert.assertEquals(IPAdressStep3, IPAdressStep1);
        Assert.assertEquals(MACAdressStep3, MACAdressStep1);
        Assert.assertEquals(VersionStep3, VersionStep1);
    }


    @Test(enabled= false)
    public void IncreasePortAndPressCancel() throws InterruptedException {
        logger=report.startTest("IncreasePortAndPressCancel");

        String IPAdress =Servers[1];
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

        WebElement nvr =nvRsPage.FindNVRByText(IPAdress);
        nvr.click();
        String NVRPortBefore = nvRsPage.GetPortFromProperties();
        logger.log(LogStatus.INFO,"Increase port number for NVR" +IPAdress+" and press 'Cancel'");

        nvRsPage.ClickOnPortUpSpinner();
//
//        logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
//        Assert.assertTrue(nvRsPage.CancelButtonIsEnable());
//
//        logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
//        Assert.assertTrue(nvRsPage.SaveButtonIsEnable());
        Thread.sleep(4000);

        nvRsPage.CancelChangesButton();
        String NVRPortAfter = nvRsPage.GetPortFromProperties();;

        logger.log(LogStatus.INFO,"Check that the port isn't changed") ;
        Assert.assertEquals(NVRPortAfter, NVRPortBefore);

        nvRsPage.Refresh();
        nvRsPage.FindNVRByText(IPAdress).click();
        String NVRPortAfterRefresh = nvRsPage.GetPortFromProperties();;
        logger.log(LogStatus.INFO,"Check that the port isn't changed after refresh") ;
        Assert.assertEquals(NVRPortAfterRefresh, NVRPortBefore);
    }

    @Test
    public void DecreasePortExitCancelUnsavedChangesTest() throws InterruptedException {
        logger=report.startTest("DecreasePortExitCancelUnsavedChangesTest");

        String IPAdress =Servers[0];
        logger.log(LogStatus.INFO,"If status for NVR " + IPAdress+" isn't 'Online' than delete it");
        nvRsPage.IfStatusIsNotVThanDeleteNVR(IPAdress);
        Thread.sleep(4000);
        logger.log(LogStatus.INFO,"If NVR " + IPAdress+" isn't exist - add it");
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

        nvRsPage.FindNVRByText(IPAdress).click();
        nvRsPage.WaitUntilStorrageSettingIsExist();
        Thread.sleep(1000);

        String NVRPortBefore = nvRsPage.GetPortFromProperties();
        logger.log(LogStatus.INFO,"Increase port number for NVR" +IPAdress+" and press 'Cancel'");

        nvRsPage.ClickOnPortDownSpinner();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
        Assert.assertTrue(nvRsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
        Assert.assertTrue(nvRsPage.SaveButtonIsEnable());

        logger.log(LogStatus.INFO,"Go to Monitoring page");
        nvRsPage.GoToMonitoringPage();
        nvRsPage.WaitUntilDialogIsLocated();
        nvRsPage.CancelUnsavedChanges();
        String NVRPortAfter = nvRsPage.GetPortFromProperties();;

        logger.log(LogStatus.INFO,"Check that port isn't saved");
        Assert.assertEquals(Integer.parseInt(NVRPortAfter), Integer.parseInt(NVRPortBefore)-1);
        nvRsPage.GoToMonitoringPage();
        nvRsPage.WaitUntilDialogIsLocated();
        nvRsPage.PressDontSaveUnsavedChanges();
    }

    @Test
    public void IncreasePortExitPressDontSaveUnsavedChangesTest() throws InterruptedException, IOException {
        logger=report.startTest("IncreasePortExitPressDontSaveUnsavedChangesTest");

        String IPAdress =Servers[1];

        logger.log(LogStatus.INFO,"If status for NVR " + IPAdress+" isn't 'Online' than delete it");
        nvRsPage.IfStatusIsNotVThanDeleteNVR(IPAdress);
        Thread.sleep(1000);
        logger.log(LogStatus.INFO,"If NVR " + IPAdress+" isn't exist - add it");
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

        nvRsPage.FindNVRByText(IPAdress).click();
        nvRsPage.WaitUntilStorrageSettingIsExist();
        Thread.sleep(1000);

        String NVRPortBefore = nvRsPage.GetPortFromProperties();
        logger.log(LogStatus.INFO,"Increase port number for NVR" +IPAdress+" and press 'Cancel'");

        nvRsPage.ClickOnPortUpSpinner();

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
        Assert.assertTrue(nvRsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
        Assert.assertTrue(nvRsPage.SaveButtonIsEnable());

        nvRsPage.GoToMonitoringPage();
        Thread.sleep(2000);

        nvRsPage.PressDontSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Check that Monitoring page is loaded");
        Assert.assertTrue(nvRsPage.MonitoringPageIsLoaded());

        nvRsPage.goToNVRsPage();

        nvRsPage.FindNVRByText(IPAdress).click();
        Thread.sleep(1000);

        String NVRPortAfter = nvRsPage.GetPortFromProperties();

        logger.log(LogStatus.INFO,"Check that change isn't saved ");///isn't passed
        Assert.assertEquals(NVRPortAfter, NVRPortBefore);

        nvRsPage.Refresh();
        nvRsPage.FindNVRByText(IPAdress).click();

        String NVRPortAfterRefresh = nvRsPage.GetPortFromProperties();;
        logger.log(LogStatus.INFO,"Check that change isn't saved after refresh");
        Assert.assertEquals(NVRPortAfterRefresh, NVRPortBefore);
    }

    @Test
    public void DecreasePortExitPressSaveUnsavedChangesTest() throws InterruptedException, IOException {
        logger=report.startTest("uDecreasePortExitPressSaveUnsavedChangesTest");

        String IPAdress =Servers[1];
        logger.log(LogStatus.INFO,"If status for NVR " + IPAdress+" isn't 'Online' than delete it");
        nvRsPage.IfStatusIsNotVThanDeleteNVR(IPAdress);
        logger.log(LogStatus.INFO,"If NVR " + IPAdress+" isn't exist - add it");
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

        nvRsPage.FindNVRByText(IPAdress).click();
        nvRsPage.WaitUntilStorrageSettingIsExist();
        Thread.sleep(2000);

        String NVRPortBefore = nvRsPage.GetPortFromProperties();
        logger.log(LogStatus.INFO,"Decrease port number for NVR" +IPAdress);
        nvRsPage.ClickOnPortDownSpinner();

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
        Assert.assertTrue(nvRsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
        Assert.assertTrue(nvRsPage.SaveButtonIsEnable());

        nvRsPage.GoToSearchPage();
        nvRsPage.PressSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Check that Invistigation page is loaded");
        Assert.assertTrue(searchPage.SearchPageIsLoaded());

        Thread.sleep(1000);
        nvRsPage.goToNVRsPage();
        nvRsPage.FindNVRByText(IPAdress).click();
        Thread.sleep(1000);

        String NVRPortAfter = nvRsPage.GetPortFromProperties();

        logger.log(LogStatus.INFO,"Check that change is saved ");
        Assert.assertEquals(Integer.parseInt(NVRPortAfter), Integer.parseInt(NVRPortBefore)-1);

        nvRsPage.Refresh();
        nvRsPage.FindNVRByText(IPAdress).click();
        Thread.sleep(1000);

        String NVRPortAfterRefresh = nvRsPage.GetPortFromProperties();;
        logger.log(LogStatus.INFO,"Check that change is saved after refresh");
        Assert.assertEquals(Integer.parseInt(NVRPortAfterRefresh), Integer.parseInt(NVRPortBefore)-1);
    }

    //tests for Username
    @Test
    public void ChangeUserNameAndPressCancel() throws InterruptedException {
        logger=report.startTest("ChangeUserNameAndPressCancel");

        String IPAdress =Servers[1];

        logger.log(LogStatus.INFO,"If status for NVR " + IPAdress+" isn't 'Online' than delete it");
        nvRsPage.IfStatusIsNotVThanDeleteNVR(IPAdress);
        logger.log(LogStatus.INFO,"If NVR " + IPAdress+" isn't exist - add it");
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

        nvRsPage.FindNVRByText(IPAdress).click();
        nvRsPage.WaitUntilStorrageSettingIsExist();
        Thread.sleep(1000);

        String userName = nvRsPage.GetUserNameFromProperties();
        String newUserName = "UserName";
        nvRsPage.OpenUserName();
        nvRsPage.ChangeUserName(newUserName);

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
        Assert.assertTrue(nvRsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
        Assert.assertTrue(nvRsPage.SaveButtonIsEnable());

        Thread.sleep(1000);
        nvRsPage.CancelChangesButton();
        String userNameAfterChange = nvRsPage.GetUserNameFromProperties();

        logger.log(LogStatus.INFO,"Check that there are not changes");
        Assert.assertEquals(userNameAfterChange, userName);
    }

    @Test
    public void ChangeUserNameExitCancelUnsavedChangesTest() throws InterruptedException {
        logger=report.startTest("ChangeUserNameExitCancelUnsavedChangesTest");

        String IPAdress =Servers[1];
        logger.log(LogStatus.INFO,"If status for NVR " + IPAdress+" isn't 'Online' than delete it");
        nvRsPage.IfStatusIsNotVThanDeleteNVR(IPAdress);
        logger.log(LogStatus.INFO,"If NVR " + IPAdress+" isn't exist - add it");
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

        nvRsPage.FindNVRByText(IPAdress).click();
        nvRsPage.WaitUntilStorrageSettingIsExist();
        Thread.sleep(1000);

        String userName = nvRsPage.GetUserNameFromProperties();
        String newUserName = "UserName";
        nvRsPage.OpenUserName();
        nvRsPage.ChangeUserName(newUserName);

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
        Assert.assertTrue(nvRsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
        Assert.assertTrue(nvRsPage.SaveButtonIsEnable());

        Thread.sleep(1000);
        logger.log(LogStatus.INFO,"Go to monitoring page");
        nvRsPage.GoToMonitoringPage();

        logger.log(LogStatus.INFO,"Cancel unsaved shanges");
        nvRsPage.CancelUnsavedChanges();
        String userNameAfterChange = nvRsPage.GetUserNameFromProperties();

        logger.log(LogStatus.INFO,"Check that user name is equals the entered user name");
        Assert.assertEquals(userNameAfterChange, newUserName);

        nvRsPage.GoToSearchPage();
        nvRsPage.PressDontSaveUnsavedChanges();
    }

    @Test
    public void ChangeUserNameExitPressDontSaveUnsavedChangesTest() throws InterruptedException, IOException {
        logger=report.startTest("ChangeUserNameExitPressDontSaveUnsavedChangesTest");

        String IPAdress =Servers[1];

        logger.log(LogStatus.INFO,"If status for NVR " + IPAdress+" isn't 'Online' than delete it");
        nvRsPage.IfStatusIsNotVThanDeleteNVR(IPAdress);
        logger.log(LogStatus.INFO,"If NVR " + IPAdress+" isn't exist - add it");
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

        nvRsPage.FindNVRByText(IPAdress).click();
        nvRsPage.WaitUntilStorrageSettingIsExist();
        Thread.sleep(1000);

        String userName = nvRsPage.GetUserNameFromProperties();
        String newUserName = "UserName";
        nvRsPage.OpenUserName();
        nvRsPage.ChangeUserName(newUserName);

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
        Assert.assertTrue(nvRsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
        Assert.assertTrue(nvRsPage.SaveButtonIsEnable());

        Thread.sleep(1000);
        nvRsPage.GoToSearchPage();
        nvRsPage.PressDontSaveUnsavedChanges();

        Thread.sleep(1000);
        nvRsPage.goToNVRsPage();
        nvRsPage.FindNVRByText(IPAdress).click();
        Thread.sleep(1000);

        String userNameAfterChange = nvRsPage.GetUserNameFromProperties();

        logger.log(LogStatus.INFO,"Check that user name isn't equals the entered user name");
        Assert.assertEquals(userNameAfterChange, userName);
    }

    @Test
    public void ChangeUserNameExitPressSaveUnsavedChangesTest() throws InterruptedException, IOException {
        logger=report.startTest("ChangeUserNameExitPressSaveUnsavedChangesTest");

        String IPAdress =Servers[1];

        logger.log(LogStatus.INFO,"If status for NVR " + IPAdress+" isn't 'Online' than delete it");
        nvRsPage.IfStatusIsNotVThanDeleteNVR(IPAdress);
        logger.log(LogStatus.INFO,"If NVR " + IPAdress+" isn't exist - add it");
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

        nvRsPage.FindNVRByText(IPAdress).click();
        nvRsPage.WaitUntilStorrageSettingIsExist();
        Thread.sleep(1000);

        String userName = nvRsPage.GetUserNameFromProperties();
        String newUserName = "UserName";
        nvRsPage.OpenUserName();
        nvRsPage.ChangeUserName(newUserName);

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
        Assert.assertTrue(nvRsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
        Assert.assertTrue(nvRsPage.SaveButtonIsEnable());

        Thread.sleep(1000);
        nvRsPage.GoToSearchPage();
        nvRsPage.PressSaveUnsavedChanges();

        Thread.sleep(1000);
        nvRsPage.goToNVRsPage();
        nvRsPage.FindNVRByText(IPAdress).click();
        String userNameAfterChange = nvRsPage.GetUserNameFromProperties();

        logger.log(LogStatus.INFO,"Check that user name is equals the entered user name");
        Assert.assertEquals(userNameAfterChange, newUserName);
    }

    @Test//(enabled = false)
    public void CheckPropertiesAfterChangeUserNameTest() throws InterruptedException, IOException {
        logger=report.startTest("CheckPropertiesAfterChangeUserNameTest");

        String IPAdress =Servers[1];
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);
        String newUserName = "ADMIN";
        logger.log(LogStatus.INFO,"Check that after changing in User Name field there are not changing in another fields");
        nvRsPage.FindNVRByText(IPAdress).click();
        Thread.sleep(1000);

        String NameStep1 = nvRsPage.GetNameFromProperties();
        String IPAdressStep1 = nvRsPage.GetIPAdressFromProperties();
        String MACAdressStep1 = nvRsPage.GetMACAdressFromProperties();
        String VersionStep1 = nvRsPage.GetVersionFromProperties();

        nvRsPage.OpenUserName();
        nvRsPage.ChangeUserName(newUserName);

        String NameStep2 = nvRsPage.GetNameFromProperties();
        String IPAdressStep2 = nvRsPage.GetIPAdressFromProperties();
        String MACAdressStep2 = nvRsPage.GetMACAdressFromProperties();
        String VersionStep2 = nvRsPage.GetVersionFromProperties();

        Assert.assertEquals(NameStep2, NameStep1);
        Assert.assertEquals(IPAdressStep2, IPAdressStep1);
        Assert.assertEquals(MACAdressStep2, MACAdressStep1);
        Assert.assertEquals(VersionStep2, VersionStep1);

        nvRsPage.GoToSearchPage();
        nvRsPage.PressSaveUnsavedChanges();
        Thread.sleep(1000);
        nvRsPage.goToNVRsPage();
        nvRsPage.FindNVRByText(IPAdress).click();

        String NameStep3 = nvRsPage.GetNameFromProperties();
        String IPAdressStep3 = nvRsPage.GetIPAdressFromProperties();
        String MACAdressStep3 = nvRsPage.GetMACAdressFromProperties();
        String VersionStep3 = nvRsPage.GetVersionFromProperties();

        Assert.assertEquals(NameStep3, NameStep1);
        Assert.assertEquals(IPAdressStep3, IPAdressStep1);
        Assert.assertEquals(MACAdressStep3, MACAdressStep1);
        Assert.assertEquals(VersionStep3, VersionStep1);
    }

    @Test
    public void CheckPropertiesAfterChangePasswordTest() throws InterruptedException, IOException {
        logger=report.startTest("CheckPropertiesAfterChangePasswordTest");

        String IPAdress =Servers[1];
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);
        String password = "1234";
        logger.log(LogStatus.INFO,"Check that after changing in Password field there are not changing in another fields");
        nvRsPage.FindNVRByText(IPAdress).click();
        Thread.sleep(1000);

        String NameStep1 = nvRsPage.GetUserNameFromProperties();
        String IPAdressStep1 = nvRsPage.GetIPAdressFromProperties();
        String MACAdressStep1 = nvRsPage.GetMACAdressFromProperties();
        String VersionStep1 = nvRsPage.GetVersionFromProperties();

        nvRsPage.OpenUserName();
        nvRsPage.ChangePassword(password);

        String NameStep2 = nvRsPage.GetUserNameFromProperties();
        String IPAdressStep2 = nvRsPage.GetIPAdressFromProperties();
        String MACAdressStep2 = nvRsPage.GetMACAdressFromProperties();
        String VersionStep2 = nvRsPage.GetVersionFromProperties();

        Thread.sleep(1000);
        Assert.assertEquals(NameStep2, NameStep1);
        Assert.assertEquals(IPAdressStep2, IPAdressStep1);
        Assert.assertEquals(MACAdressStep2, MACAdressStep1);
        Assert.assertEquals(VersionStep2, VersionStep1);

        nvRsPage.GoToSearchPage();
        nvRsPage.PressSaveUnsavedChanges();
        Thread.sleep(1000);
        nvRsPage.goToNVRsPage();
        nvRsPage.FindNVRByText(IPAdress).click();

        String NameStep3 = nvRsPage.GetUserNameFromProperties();
        String IPAdressStep3 = nvRsPage.GetIPAdressFromProperties();
        String MACAdressStep3 = nvRsPage.GetMACAdressFromProperties();
        String VersionStep3 = nvRsPage.GetVersionFromProperties();

        Thread.sleep(1000);

        Assert.assertEquals(NameStep3, NameStep1);
        Assert.assertEquals(IPAdressStep3, IPAdressStep1);
        Assert.assertEquals(MACAdressStep3, MACAdressStep1);
        Assert.assertEquals(VersionStep3, VersionStep1);
    }

    //tests for storage definitions
    @Test(enabled = false) // IN IE inpossible to change storage definitions
    public void ChangeStorageDefinitionsAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("ChangeStorageDefinitionsAndPressCancelTest");

        String IPAdress =Servers[0];

        logger.log(LogStatus.INFO,"If status for NVR " + IPAdress+" isn't 'Online' than delete it");
        nvRsPage.IfStatusIsNotVThanDeleteNVR(IPAdress);
        logger.log(LogStatus.INFO,"If NVR " + IPAdress+" isn't exist - add it");
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

        nvRsPage.FindNVRByText(IPAdress).click();
        nvRsPage.WaitUntilStorrageSettingIsExist();
        Thread.sleep(1000);

        boolean dropDownIsclosed = nvRsPage.StorageDefDropDownIsClosed();
        if(dropDownIsclosed) {
            logger.log(LogStatus.INFO,"Expand drop down storage ");
            nvRsPage.ClickOnStorageDefinDropDownMenu();
        }

        nvRsPage.CheckAndSwitchOnStorageDefinition();
        String number = nvRsPage.GetStorageFromStorageProperties();
        String random = ""+nvRsPage.GetRandomDigit(nvRsPage.GetMinGB(), nvRsPage.GetMaxGB());
        nvRsPage.InsertIntoStorageDefinitionsField(random);

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
        Assert.assertTrue(nvRsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
        Assert.assertTrue(nvRsPage.SaveButtonIsEnable());

        nvRsPage.CancelChangesButton();
        String numberAfterChange = nvRsPage.GetStorageFromStorageProperties();

        logger.log(LogStatus.INFO,"Check that actual GB count is entered number");
        Assert.assertEquals(numberAfterChange, number);
    }


    @Test(enabled = false) // IN IE inpossible to change storage definitions
    public void ChangeStorageDefinitionsExitCancelUnsavedChangesTest() throws InterruptedException {
        logger=report.startTest("ChangeStorageDefinitionsExitCancelUnsavedChangesTest");

        String IPAdress =Servers[0];

        logger.log(LogStatus.INFO,"If status for NVR " + IPAdress+" isn't 'Online' than delete it");
        nvRsPage.IfStatusIsNotVThanDeleteNVR(IPAdress);
        logger.log(LogStatus.INFO,"If NVR " + IPAdress+" isn't exist - add it");
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

        logger.log(LogStatus.INFO,"Change GB count for NVR " + IPAdress + " and press 'Cancel' on 'Unsaved changes' window");
        nvRsPage.FindNVRByText(IPAdress).click();
        nvRsPage.WaitUntilStorrageSettingIsExist();
        Thread.sleep(1000);
        boolean dropDownIsclosed = nvRsPage.StorageDefDropDownIsClosed();
        if(dropDownIsclosed) {
            logger.log(LogStatus.INFO,"Expand drop down storage ");
            nvRsPage.ClickOnStorageDefinDropDownMenu();
        }

        nvRsPage.CheckAndSwitchOnStorageDefinition();
        String number = nvRsPage.GetStorageFromStorageProperties();
        String random = ""+nvRsPage.GetRandomDigit(nvRsPage.GetMinGB(), nvRsPage.GetMaxGB());
        nvRsPage.InsertIntoStorageDefinitionsField(random);

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
        Assert.assertTrue(nvRsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
        Assert.assertTrue(nvRsPage.SaveButtonIsEnable());

        nvRsPage.GoToSearchPage();
        nvRsPage.CancelUnsavedChanges();

        String numberAfterChange = nvRsPage.GetStorageFromStorageProperties();

        logger.log(LogStatus.INFO,"Check that actual GB count is entered number");
        Assert.assertEquals(numberAfterChange, random);

        nvRsPage.GoToSearchPage();
        nvRsPage.PressDontSaveUnsavedChanges();
    }

   // @Test// IN IE inpossible to change storage definitions
    public void ChangeStorageDefinitionsExitPressDontSaveUnsavedChangesTest() throws InterruptedException, IOException {
        logger=report.startTest("ChangeStorageDefinitionsExitPressDontSaveUnsavedChangesTest");

        String IPAdress =Servers[0];

        logger.log(LogStatus.INFO,"If status for NVR " + IPAdress+" isn't 'Online' than delete it");
        nvRsPage.IfStatusIsNotVThanDeleteNVR(IPAdress);
        logger.log(LogStatus.INFO,"If NVR " + IPAdress+" isn't exist - add it");
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

        logger.log(LogStatus.INFO,"Change GB count for NVR " + IPAdress+" and press 'Don't save' on 'Unsaved changes' window");
        nvRsPage.FindNVRByText(IPAdress).click();
        nvRsPage.WaitUntilStorrageSettingIsExist();
        Thread.sleep(1000);
        boolean dropDownIsclosed = nvRsPage.StorageDefDropDownIsClosed();
        if(dropDownIsclosed) {
            logger.log(LogStatus.INFO,"Expand drop down storage ");
            nvRsPage.ClickOnStorageDefinDropDownMenu();
        }

        nvRsPage.CheckAndSwitchOnStorageDefinition();
        String number = nvRsPage.GetStorageFromStorageProperties();
        String random = ""+nvRsPage.GetRandomDigit(nvRsPage.GetMinGB(), nvRsPage.GetMaxGB());

        nvRsPage.InsertIntoStorageDefinitionsField(random);

        String numberAfterInsert = nvRsPage.GetStorageFromStorageProperties();

        logger.log(LogStatus.INFO,"Check that actual GB count isn't entered number");
        Assert.assertEquals(numberAfterInsert, number);

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
        Assert.assertTrue(nvRsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
        Assert.assertTrue(nvRsPage.SaveButtonIsEnable());

        nvRsPage.GoToSearchPage();
        nvRsPage.PressDontSaveUnsavedChanges();
        Thread.sleep(1000);

        logger.log(LogStatus.INFO,"Check that Invistigation page is loaded");
        Assert.assertTrue(searchPage.SearchPageIsLoaded());

        Thread.sleep(1000);
        nvRsPage.goToNVRsPage();
        nvRsPage.FindNVRByText(IPAdress).click();
        Thread.sleep(1000);

        String numberAfterChange = nvRsPage.GetStorageFromStorageProperties();

        logger.log(LogStatus.INFO,"Check that actual GB count isn't entered number");
        Assert.assertEquals(numberAfterChange, number);
    }

   // @Test // IN IE impossible to change storage definitions
    public void ChangeStorageDefinitionsExitPressSaveUnsavedChangesTest() throws InterruptedException, IOException {
        logger=report.startTest("ChangeStorageDefinitionsExitPressSaveUnsavedChangesTest");

        String IPAdress =Servers[0];

        logger.log(LogStatus.INFO,"If status for NVR " + IPAdress+" isn't 'Online' than delete it");
        nvRsPage.IfStatusIsNotVThanDeleteNVR(IPAdress);
        logger.log(LogStatus.INFO,"If NVR " + IPAdress+" isn't exist - add it");
        nvRsPage.IfNVRIsNotExistAddIt(IPAdress);

        logger.log(LogStatus.INFO,"Change GB count for NVR " + IPAdress+" and press 'Save' on 'Unsaved changes' window");
        nvRsPage.FindNVRByText(IPAdress).click();
        nvRsPage.WaitUntilStorrageSettingIsExist();
        Thread.sleep(1000);

        boolean dropDownIsclosed = nvRsPage.StorageDefDropDownIsClosed();
        if(dropDownIsclosed) {
            logger.log(LogStatus.INFO,"Expand drop down storage ");
            nvRsPage.ClickOnStorageDefinDropDownMenu();
        }
        nvRsPage.CheckAndSwitchOnStorageDefinition();
        String number = nvRsPage.GetStorageFromStorageProperties();
        String random = ""+nvRsPage.GetRandomDigit(nvRsPage.GetMinGB(), nvRsPage.GetMaxGB());

        nvRsPage.InsertIntoStorageDefinitionsField(random);

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
        Assert.assertTrue(nvRsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
        Assert.assertTrue(nvRsPage.SaveButtonIsEnable());

        nvRsPage.GoToSearchPage();
        nvRsPage.PressSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Check that Invistigation page is loaded");
        Assert.assertTrue(searchPage.SearchPageIsLoaded());

        Thread.sleep(1000);
        nvRsPage.goToNVRsPage();
        nvRsPage.FindNVRByText(IPAdress).click();
        Thread.sleep(1000);
        String numberAfterChange = nvRsPage.GetStorageFromStorageProperties();

        logger.log(LogStatus.INFO,"Check that actual GB count is entered number");
        Assert.assertEquals(numberAfterChange, random);
    }

    @Test
    public void SwitchStorageDefinitionsAndPressCancelTest() throws InterruptedException {
        logger=report.startTest("SwitchStorageDefinitionsAndPressCancelTest");

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

        logger.log(LogStatus.INFO,"Click on Cancel button");
        nvRsPage.CancelChangesButton();

        logger.log(LogStatus.INFO,"Check that storage definitions status change is canceled");
        boolean statusAfter = nvRsPage.SwitchStorageDefinitionsIsOn();
        if(status) Assert.assertTrue(statusAfter);
        if(!status) Assert.assertFalse(statusAfter);

        logger.log(LogStatus.INFO,"Check that Cancel button is disabled after name changes");
        Assert.assertFalse(nvRsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is disabled after name changes");
        Assert.assertFalse(nvRsPage.SaveButtonIsEnable());
    }

    @Test
    public void SwitchStorageDefinitionsExitAndPressCancelTest() throws InterruptedException, IOException {
        logger=report.startTest("SwitchStorageDefinitionsExitAndPressCancelTest");

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

        logger.log(LogStatus.INFO,"Go to Search page");
        nvRsPage.GoToSearchPage();

        logger.log(LogStatus.INFO,"Click on Cancel on unsaved changes dialog");
        nvRsPage.CancelUnsavedChanges();

        logger.log(LogStatus.INFO,"Check that storage definitions status change is remained");
        boolean statusAfter = nvRsPage.SwitchStorageDefinitionsIsOn();
        if(!status) Assert.assertTrue(statusAfter);
        if(status) Assert.assertFalse(statusAfter);

        logger.log(LogStatus.INFO,"Check that Cancel button is enable after name changes");
        Assert.assertTrue(nvRsPage.CancelButtonIsEnable());

        logger.log(LogStatus.INFO,"Check that Save button is enable after name changes");
        Assert.assertTrue(nvRsPage.SaveButtonIsEnable());
    }

    @Test
    public void SwitchStorageDefinitionsExitAndPressDontSaveButtonTest() throws InterruptedException, IOException {
        logger=report.startTest("SwitchStorageDefinitionsExitAndPressDontSaveButtonTest");

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

        logger.log(LogStatus.INFO,"Go to Search page");
        nvRsPage.GoToSearchPage();

        logger.log(LogStatus.INFO,"Click on Do not save button on unsaved changes dialog");
        nvRsPage.PressDontSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Check that Search page is loaded");
        Assert.assertTrue(searchPage.SearchPageIsLoaded());

        logger.log(LogStatus.INFO,"Go to NVRs page");
        nvRsPage.goToNVRsPage();

        logger.log(LogStatus.INFO,"Click on " +IPAdress+" NVR");
        nvRsPage.FindElementByText(IPAdress).click();

        logger.log(LogStatus.INFO,"Check that storage definitions status change isn't saved");
        boolean statusAfter = nvRsPage.SwitchStorageDefinitionsIsOn();
        if(status) Assert.assertTrue(statusAfter);
        if(!status) Assert.assertFalse(statusAfter);
    }

    @Test
    public void SwitchStorageDefinitionsExitAndPressSaveButtonTest() throws InterruptedException, IOException {
        logger=report.startTest("SwitchStorageDefinitionsExitAndPressSaveButtonTest");

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

        logger.log(LogStatus.INFO,"Go to Search page");
        nvRsPage.GoToSearchPage();

        logger.log(LogStatus.INFO,"Click on save button on unsaved changes dialog");
        nvRsPage.PressSaveUnsavedChanges();

        logger.log(LogStatus.INFO,"Check that Search page is loaded");
        Assert.assertTrue(searchPage.SearchPageIsLoaded());

        logger.log(LogStatus.INFO,"Go to NVRs page");
        nvRsPage.goToNVRsPage();

        logger.log(LogStatus.INFO,"Click on " +IPAdress+" NVR");
        nvRsPage.FindElementByText(IPAdress).click();

        logger.log(LogStatus.INFO,"Check that storage definitions status change is saved");
        boolean statusAfter = nvRsPage.SwitchStorageDefinitionsIsOn();
        if(!status) Assert.assertTrue(statusAfter);
        if(status) Assert.assertFalse(statusAfter);
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
            nvRsPage.takeScreenshot(driver, "NVRs", result.getName());
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

