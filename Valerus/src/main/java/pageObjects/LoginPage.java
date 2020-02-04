package pageObjects;

import Utilities.Page;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.sql.Driver;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.lang.System.setProperty;
import static org.apache.xalan.xsltc.compiler.util.Util.println;


public class LoginPage extends Page{

    @FindBy(id = "txtLogin")
    WebElement userNameField;

    @FindBy(name = "password")
    WebElement passwordField;

    @FindBy(id = "submitBtn")
    WebElement loginButton;

    //Notification
    @FindBy(xpath = "//div[@id='vms-export-container']//button[@class='btn btn-xs btn-vms btn-vms-min ng-binding']")
    public WebElement closeNotificationButton;

    @FindBy(tagName= "md-switch")
    public WebElement PreviousStateOnOffToggleElement;

    //Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

//    public void SignIn() throws IOException, InterruptedException {
//       System.setProperty("jenkins.model.DirectoryBrowserSupport.CSP", "default-src 'self'; script-src 'self' 'unsafe-inline' 'unsafe-eval'; style-src 'self' 'unsafe-inline';");
//       System.setProperty("jenkins.model.DirectoryBrowserSupport.CSP", "default-src 'self'; script-src 'self' 'unsafe-inline' 'unsafe-eval'; style-src 'self' 'unsafe-inline';");
//       waitUntilElementIsLoaded(userNameField);
//       setElementText(userNameField, "admin");
//       waitUntilElementIsLoaded(passwordField);
//       setElementText(passwordField, "1234");
//       boolean isChecked = "true".equals(PreviousStateOnOffToggleElement.getAttribute("aria-checked"));
//        if(isChecked){
//            PreviousStateOnOffToggleElement.click();
//        }
//        loginButton.click();
//       try{
//         new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-backdrop fade in']")));
//         driver.switchTo().window(driver.getWindowHandle());
//         closeNotificationButton.click();
//       }catch(Exception e){}
//    }
}
