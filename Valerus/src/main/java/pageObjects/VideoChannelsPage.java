package pageObjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.javascript.host.media.webkitMediaStream;

import Utilities.Page;


public class VideoChannelsPage extends Page {

    @FindBy(id = "config-menu-network_entities-video_channels")
    WebElement videoChannelsButton;
    
    @FindBy(id = "config-menu-network_entities-shortCutNums")
    WebElement numericIDButton;

    @FindBy(xpath = "//span[@class='item-text ng-binding'][@title='Video Channels']")
    WebElement videoChannelsButtonLandingPage;
    
    @FindBy(xpath = "//span[@class='item-text ng-binding'][@title='Numeric ID']")
    WebElement numericIDButtonLandingPage;

    //LeftPanel
    @FindBy(xpath = "//div[@class='ui-grid-contents-wrapper']//div[@class='ui-grid-header-cell-row']//div[@class='ui-grid-cell-contents']/div[@role='button']")
    WebElement plusMinusDeviceSquared;

    @FindBy(xpath = "//div[@class='btn-panel bottom-separator-builder new-ui-panel-background']//div[@class='vms-search-with-btn']/form/input")
    WebElement filterDevicesField;
    
    @FindBy(xpath = "//div[@class='btn-panel bottom-separator-builder new-ui-panel-background']//div[@class='vms-search-with-btn']/form/input")
    WebElement filterDevicesField1;

    @FindBy(xpath = "//div[@class='bottom-separator-builder']//h3[@class='panel-title vms-configuration-selected-configuration ng-binding']")
    WebElement panelTitle;

    //ApplyToDialog

    @FindBy(xpath = "//div[@class='vms-big-btn-footer bottom-separator-builder new-ui-panel-background']/button[contains(text(), 'Apply To')][contains(@style, 'display: block')]")
    WebElement applyToButton;

    @FindBy(xpath = "//div[@class='panel-body fullSize']//div[@class='ui-grid-cell-contents ng-binding ng-scope']")
    List <WebElement> videoChannelsInApplyToDialog;

    @FindBy(xpath = "//div[@id='vms-export-container']/div[@class='modal-footer']/button[contains(text(), 'Apply')]")
    WebElement applyButtonInApplyToDialog;

    @FindBy(xpath = "//div[@id='vms-export-container']/div[@class='modal-footer']/button[contains(text(), 'Close')]")
    WebElement closeButtonInApplyToDialog;

    @FindBy(xpath = "//div[@id='vms-export-container']/div[@class='modal-footer']/button[contains(text(), 'Close')]")
    WebElement closeApplyToDialog;

    @FindBy(xpath = "//div[@class='panel-body fullSize']/div[@class='ng-scope'][1]//input[@type='checkbox']")
    WebElement streamsAndRecordingCheckbox;

    @FindBy(xpath = "//div[@class='panel-body fullSize']/div[@class='ng-scope'][2]//input[@type='checkbox']")
    WebElement pictureSettingsCheckbox;

    //Central panel
    //Channel Properties
    @FindBy(id = "channel_properties")
    WebElement channelPropertiesButton;

//    @FindBy(xpath = "//div[@class='fullSize']/div[@class='configuration-ditails-form']/md-switch")
//    WebElement showPlayerOnOffToggler;

    @FindBy(id = "video-channels-player-container")
    WebElement showPlayerVideoWindow;

    @FindBy(id = "inputName")
    WebElement nameChannelPropertiesField;

    @FindBy(xpath = "//ng-form//div[@class='configuration-ditails-data flex']//md-switch")
    WebElement visibleOnOffToggler;

    @FindBy(xpath = "//span[@title='Open device web page']")
    WebElement openDeviceWebPageRef;

    @FindBy(xpath = "//button[@class='button-new-ui height-32'][@title='Add audio Resources']")
    WebElement addAudioResourcesButton;

    @FindBy(xpath = "//div[@class='modal-footer']/button[contains(text(),'Apply')]")
    WebElement ApplyAddAudioChannelsButton;

    @FindBy(xpath = "//div[@class='modal-footer']/button[contains(text(),'Cancel')]")
    WebElement CancelAddAudioChannelsButton;

    @FindBy(xpath = "//button[@class='vms-grid-delete-button vicon-font v-trash']")
    WebElement DeleteAudioChannelIcon;

    //Streams

    @FindBy(xpath = "//div[@class = 'vms-channels-config-tab ng-isolate-scope']//ul/li[2]/div")
    WebElement streamsButton;

    @FindBy(xpath = "//select[@combo-default-value = 'channels.protocolTypes']")
    WebElement cameraStreamingDropDownList;

    @FindBy(xpath = "//select[@value = 'tabStream.videoOptionsList']")
    WebElement compressionDropDownList;

    @FindBy(xpath = "//select[@combo-default-value = 'tabStream.selectedCompression.resolutionsAvailable']")
    List <WebElement> resolutionDropDownList;

    @FindBy(xpath = "//ng-form[@name='tabStream.form']/div[contains(@class,'configuration-ditails-form')]//span[contains(text(), 'Frame Rate')]/../..//select")
    List<WebElement> frameRateSelect;
    
    @FindBy(xpath = "//*[@id=\"vms-screen-lock-id\"]/div/div/div/div/section/div/div[1]/div[3]/div[2]/ng-form/div[5]/div/div[1]/select")
    List<WebElement> frameRateSelect1;
    
    @FindBy(xpath = "//div[@id='s2id_autogen1']")
    List<WebElement> frameRateSelect2;


    @FindBy(xpath = "//ng-form[@name='tabStream.form']/div[contains(@class,'configuration-ditails-form')]//span[@title='Key Frame Interval']/../..//input")
    List<WebElement> keyFrameIntervalField;

    @FindBy(xpath = "//ng-form[@name='tabStream.form']/div[contains(@class,'configuration-ditails-form')]//span[@title='Key Frame Interval']/../..//a[@class='ui-spinner-button ui-spinner-up ui-corner-tr']")
    List<WebElement> keyFrameIntervalUpSpinner;

    @FindBy(xpath = "//ng-form[@name='tabStream.form']/div[contains(@class,'configuration-ditails-form')]//span[@title='Key Frame Interval']/../..//a[@class='ui-spinner-button ui-spinner-down ui-corner-br']")
    List<WebElement> keyFrameIntervalDownSpinner;

    @FindBy(xpath = "//ng-form[@name='tabStream.form']/div[contains(@class,'configuration-ditails-form')]//span[@title='Key Frame Interval']/../..//div[@class='details-minor']/div")
    List<WebElement> keyFrameInterval;

    @FindBy(xpath = "//ng-form[@name='tabStream.form']/div[contains(@class,'configuration-ditails-form')]//span[@title='Key Frame Interval']/../..//div[@class='details-minor small']//div[@class='alertMessage']")
    List<WebElement> keyFramePeriod;

    @FindBy(xpath = "//ng-form[@name='tabStream.form']/div[contains(@class,'configuration-ditails-form')]//span[@title='Bitrate Limit']/../..//input")
    List<WebElement> bitrateLimitField;

    @FindBy(xpath = "//ng-form[@name='tabStream.form']/div[contains(@class,'configuration-ditails-form')]//span[@title='Bitrate Limit']/../..//a[@class='ui-spinner-button ui-spinner-up ui-corner-tr']")
    List<WebElement> bitrateLimitUpSpinner;

    @FindBy(xpath = "//ng-form[@name='tabStream.form']/div[contains(@class,'configuration-ditails-form')]//span[@title='Bitrate Limit']/../..//a[@class='ui-spinner-button ui-spinner-down ui-corner-br']")
    List<WebElement> bitrateLimitDownSpinner;

    @FindBy(xpath = "//ng-form[@name='tabStream.form']/div[@class='configuration-ditails-form flex-between']//span[@title='Bitrate Limit']/../..//div[@class='details-minor']/div[contains(text(), '[')]")
    List<WebElement> bitrateLimitInterval;

    @FindBy(xpath = "//ng-form[@name='tabStream.form']/div[contains(@class,'configuration-ditails-form')]//span[@title='Quality']/../..//input")
    List<WebElement> qualityField;

    @FindBy(xpath = "//ng-form[@name='tabStream.form']/div[contains(@class,'configuration-ditails-form')]//span[@title='Quality']/../..//a[@class='ui-spinner-button ui-spinner-up ui-corner-tr']")
    List<WebElement> qualityUpSpinner;

    @FindBy(xpath = "//ng-form[@name='tabStream.form']/div[contains(@class,'configuration-ditails-form')]//span[@title='Quality']/../..//a[@class='ui-spinner-button ui-spinner-down ui-corner-br']")
    List<WebElement> qualityDownSpinner;

    @FindBy(xpath = "//ng-form[@name='tabStream.form']/div[contains(@class,'configuration-ditails-form')]//span[@title='Quality']/../..//div[@class='details-minor']/div")
    List<WebElement> qualityInterval;

    //Recording
    @FindBy(xpath = "//div[@class = 'vms-channels-config-tab ng-isolate-scope']//ul/li[3]/div")
    WebElement recordingButton;

    @FindBy(xpath = "//ng-form/div[@class='configuration-ditails-form flex-between'][1]//md-switch")
    WebElement recordingOnOffToggler;
    
    @FindBy(xpath = "//*[@id=\"vms-screen-recording-id\"]/ng-form/div[1]/div/md-switch/div[1]/div[1]")
    WebElement recordingOnOffToggler1;

    @FindBy(xpath = "//ng-form/div[@class='configuration-ditails-form flex-between limit_retention']//md-switch")
    WebElement limitRetentionOnOffToggler;
    
    @FindBy(xpath = "//*[@id=\"vms-screen-recording-id\"]/ng-form/div[2]/div/div/md-switch/div[1]/div[1]")
    WebElement limitRetentionOnOffToggler1;

    @FindBy(xpath = "//ng-form/div[@class='configuration-ditails-form flex-between limit_retention']//span['ui-spinner ui-widget ui-corner-all ui-widget-header']/input")
    WebElement limitRetentionInputField;
    
    @FindBy(xpath = "//*[@id=\"vms-screen-recording-id\"]/ng-form/div[2]/div/div[2]/div[2]/span/input")
    WebElement limitRetentionInputField1;
    

    @FindBy(xpath = "//ng-form/div[@class='configuration-ditails-form flex-between limit_retention']//span['ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-up ui-corner-tr']")
    WebElement limitRetentionUpSnipper;
    
    @FindBy(xpath = "//*[@id=\"vms-screen-recording-id\"]/ng-form/div[2]/div/div[2]/div[2]/span/a[1]/span")
    WebElement limitRetentionUpSnipper1;

    @FindBy(xpath = "//ng-form/div[@class='configuration-ditails-form flex-between limit_retention']//span['ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-down ui-corner-br']")
    WebElement limitRetentionDownSnipper;
    
    @FindBy(xpath = "//*[@id=\"vms-screen-recording-id\"]/ng-form/div[2]/div/div[2]/div[2]/span/a[2]/span")
    WebElement limitRetentionDownSnipper1;


    @FindBy(xpath = "//div[@class='shceduleWrapperInChannels ng-scope']/div[@class = 'cursor-hand']/i")
    WebElement openSheduleButton;

    @FindBy(xpath = "//select[@ng-model = 'schedule.selectedSchedule']")
    WebElement sheduleDropDownList;
    
  //select[@data-ng-model='schedule.normalStreamId']
    
    @FindBy(xpath = "//*[@id=\"vms-screen-recording-id\"]/ng-form/div[5]/div/div[2]/div[1]/div/select")
    WebElement sheduleDropDownList1;
    
    @FindBy(xpath = "//select[@data-ng-model='schedule.normalStreamId']")
    WebElement sheduleRecordingList1;
    
    @FindBy(xpath = "//select[@ng-model = 'schedule.selectedEventType']")
    WebElement recordingModeDropDownList;
    
    @FindBy(xpath = "//select[@ng-model = 'schedule.selectedEventType']")
    WebElement recordingModeDropDownList1;

    @FindBy(xpath = "//input[@ng-model='schedule.preEventSeconds']")
    WebElement preEventInputField;

    @FindBy(xpath = "//div[@ng-model='schedule.preEventSeconds']//span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-up ui-corner-tr']")
    WebElement preEventUpSpinner;

    @FindBy(xpath = "//div[@ng-model='schedule.preEventSeconds']//span['ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-down ui-corner-br']")
    WebElement preEventDownSpinner;

    @FindBy(xpath = "//div[@aria-hidden='false']/div[@class='configuration-ditails-form flex-between']//input[@ng-model='schedule.postEventSeconds']")
    WebElement postEventInputField;
    
    @FindBy(xpath = "//*[@id=\"vms-screen-recording-id\"]/ng-form/div[5]/div/div[2]/div[4]/div[3]/div/div[1]/span/input")
    WebElement postEventInputField1;

    @FindBy(xpath = "//div[@aria-hidden='false']/div[@class='configuration-ditails-form flex-between']//div[@ng-model='schedule.postEventSeconds']/span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-up ui-corner-tr']")
    WebElement postEventUpSpinner;
    
    @FindBy(xpath = "//*[@id=\"vms-screen-recording-id\"]/ng-form/div[5]/div/div[2]/div[4]/div[3]/div/div[1]/span/a[1]/span")
    WebElement postEventUpSpinner1;

    @FindBy(xpath = "//div[@aria-hidden='false']/div[@class='configuration-ditails-form flex-between']//div[@ng-model='schedule.postEventSeconds']//span['ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-down ui-corner-br']")
    WebElement postEventDownSpinner;
    
    @FindBy(xpath = "//*[@id=\"vms-screen-recording-id\"]/ng-form/div[5]/div/div[2]/div[4]/div[3]/div/div[1]/span/a[2]/span")
    WebElement postEventDownSpinner1;


    @FindBy(xpath = "//select[@data-ng-model = 'schedule.normalStreamId']")
    WebElement streamDropDownList;

    @FindBy(xpath = "//select-wrapper[@text-on-label = 'schedule.boostedStream.title']/span")
    WebElement eventStream;

    @FindBy(xpath = "//button(text(), '+'")
    WebElement plusButton;

    @FindBy(xpath = "//div[@class='schedule-item-container ng-scope']//div[@aria-hidden='false']/div[@class='configuration-ditails-form flex-between']//md-switch")
    WebElement motionOnOffToggler;
    
    @FindBy(xpath = "//*[@id=\"vms-screen-recording-id\"]/ng-form/div[5]/div/div[2]/div[4]/div[4]/div/md-switch/div[1]")
    WebElement motionOnOffToggler1;

    @FindBy(xpath = "//div[@class='schedule-item-container ng-scope']/div//div[@class!='ng-hide'][@aria-hidden='false']//button/span[@title='Specify Events']")
    WebElement specifyEventsButton;
    
    @FindBy(xpath = "//*[@id=\"vms-screen-recording-id\"]/ng-form/div[5]/div/div[2]/div[4]/div[5]/div/button/span")
    WebElement specifyEventsButton2;
    
    @FindBy(xpath = "//div[@class='schedule-item-container ng-scope']/div//div[@class!='ng-hide'][@aria-hidden='false']//button/span[@title='Specify Events']")
    WebElement specifyEventsButton1;

    //Create new Shedule

    @FindBy(xpath = "//div[@class='panel panel-primary vms-panel-primary fullSize']//div[@class='padding10']/span/input")
    WebElement nameSheduleField;

    @FindBy(xpath = "//div[@class='panel panel-primary vms-panel-primary fullSize']//div[@class='schedulerDiv']/span/input")
    WebElement descriptionSheduleTextArea;

    @FindBy(xpath = "//select[@ng-model='schedulerController.selectedSchedule.type']")
    WebElement repeatDropDownList;

    @FindBy(xpath = "//div[@class='scheduleBlockWrpper']/button[@class='btn btn-vms position-add-schedule-button clear']")
    WebElement plusSheduleButton;
    
    @FindBy(xpath = "//div[@class='scheduleBlockWrpper']/button[@class='btn btn-vms position-add-schedule-button clear']")
    WebElement plusSheduleButton1;

    @FindBy(xpath = "//input[@ng-model='period.duration.hours']")
    List<WebElement> durationHoursField;

    @FindBy(xpath = "//input[@ng-model='period.duration.minutes']")
    List<WebElement> durationMinutesField;

    @FindBy(xpath = "//div[@ng-model='period.duration.hours']//a[@class='ui-spinner-button ui-spinner-up ui-corner-tr']")
    WebElement durationHoursUpSpinner;

    @FindBy(xpath = "//div[@ng-model='period.duration.hours']//a[@class='ui-spinner-button ui-spinner-down ui-corner-br']")
    WebElement durationHoursDownSpinner;

    @FindBy(xpath = "//div[@ng-model='period.duration.minutes']//a[@class='ui-spinner-button ui-spinner-up ui-corner-tr']")
    WebElement durationMinutesUpSpinner;

    @FindBy(xpath = "//div[@ng-model='period.duration.minutes']//a[@class='ui-spinner-button ui-spinner-down ui-corner-br']")
    WebElement durationMinutesDownSpinner;

    @FindBy(xpath = "//timepicker-pop[@input-time='period.startTime']/input")
    List<WebElement> startTimeField;

    @FindBy(xpath = "//timepicker-pop[@input-time='period.endTime']/input")
    List<WebElement> endTimeField;

    @FindBy(xpath = "//timepicker-pop//div[@class='input-group-btn open']//input[@ng-model='hours']")
    WebElement timeHoursField;

    @FindBy(xpath = "//timepicker-pop//div[@class='input-group-btn open']//input[@ng-model='minutes']")
    WebElement timeMinutesField;

    @FindBy(xpath = "//div[@class='input-group-btn open']//a[@ng-click='incrementHours()']")
    WebElement hoursUpSpinner;

    @FindBy(xpath = "//div[@class='input-group-btn open']//a[@ng-click='decrementHours()']")
    WebElement hoursDownSpinner;

    @FindBy(xpath = "//div[@class='input-group-btn open']//a[@ng-click='incrementMinutes()']")
    WebElement minutesUpSpinner;

    @FindBy(xpath = "//div[@class='input-group-btn open']//a[@ng-click='decrementMinutes()']")
    WebElement minutesDownSpinner;

    @FindBy(xpath = "//div[@class='panel panel-primary vms-panel-primary fullSize']//button[@title='Add Period']")
    WebElement plusPeriodButton;

    @FindBy(xpath="//div[@class='periods-wrapper_small']/div[@class='ng-scope']/div[@class='period-name-div']")
    List<WebElement> PeriodNumber;

   @FindBy(xpath="//div[@class='periods-wrapper_small']/div[@class='ng-scope']/div[@class='garbageInSchedulesNewTemplate']/button/img")
   WebElement deletePeriodButton;

    @FindBy(xpath = "//div[@class = 'modal-footer']/button[contains(text(), 'Cancel')]")
    WebElement cancelAddNewSheduleButton;

    @FindBy(xpath = "//div[@class = 'modal-footer']/button[contains(text(), 'Save')]")
    WebElement saveNewSheduleButton;

   @FindBy(xpath = "//div[@class = 'vms-channels-config-tab ng-isolate-scope']//ul/li//*[@title='Masking']/..")
   WebElement maskingButton;

    @FindBy(xpath = "//div[@class = 'vms-channels-config-tab ng-isolate-scope']//ul/li[4]")
    WebElement pictureSettingButton;

    @FindBy(xpath = "//div[@class = 'vms-channels-config-tab ng-isolate-scope']//ul/li[9]/div")
    WebElement panoramaButton;

    //Create new schedule//Monthly

    @FindBy(xpath = "//div[@class='configuration-ditails-form']//input[@ng-model='period.monthlyDate']")
    WebElement countDaysField;

    @FindBy(xpath = "//div[@ng-model='period.monthlyDate']//span['ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-up ui-corner-tr']")
    WebElement daysUpSnipper;

    @FindBy(xpath = "//div[@ng-model='period.monthlyDate']//span['ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-down ui-corner-br']")
    WebElement daysDownSnipper;

    @FindBy(xpath = "//div[@class='configuration-ditails-form']/input[@type='radio'][@value='specificDate']")
    WebElement dayRadioButton;

    @FindBy(xpath = "//div[@class='configuration-ditails-form']/input[@type='radio'][@value='custom']")
    WebElement dayOfMonthRadioButton;

    @FindBy(id = "numDayMonthly")
    WebElement numberOfDaysMonthlySelect;

    @FindBy(id = "dayWeekMonthly")
    WebElement dayWeekMonthlySelect;

    //Create new schedule //Yearly

    @FindBy(id = "monthYearly")
    WebElement monthOfYearSelect;

    @FindBy(xpath = "//div[@class='configuration-ditails-form']//input[@ng-model='period.yearlyDayInMonth']")
    WebElement countDaysInMonthField;

    @FindBy(xpath = "//div[@ng-model='period.yearlyDayInMonth']//span['ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-up ui-corner-tr']")
    WebElement monthUpSnipper;

    @FindBy(xpath = "//div[@ng-model='period.yearlyDayInMonth']//span['ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-down ui-corner-br']")
    WebElement monthDownSnipper;

    @FindBy(id = "numdayyearly")
    WebElement numberOfDaysYearlySelect;

    @FindBy(id = "dayWeekYearly")
    WebElement dayWeekYearlySelect;

    @FindBy(id = "monthTheYearly")
    WebElement monthYearlySelect;

    @FindBy(xpath = "//div[@class='configuration-ditails-form']/input[@type='radio'][@value='the']")
    WebElement yearlyRadioButton;

    //Create New schedule// Never repeat
    @FindBy(xpath = "//input[@ng-model='period.startDate']")
    List<WebElement> startTimeDateField;

    @FindBy(xpath = "//input[@ng-model='period.endDate']")
    List<WebElement> endTimeDateField;

    @FindBy(xpath = "//div[@class='remove-float']//div[@class='configuration-ditails-form side-by-side-spinner']/ul")
    List<WebElement> startTimeDateTable;

    @FindBy(xpath = "//div[@class='remove-float']//div[@class='configuration-ditails-form']/ul")
    List<WebElement> endTimeDateTable;

    @FindBy(xpath = "//div[@class='remove-float']//div[@class='configuration-ditails-form side-by-side-spinner']/ul//table/tbody//td/button[@aria-disabled='false']/span[@class='ng-binding text-info']")
    WebElement startTimeSelectedDateCell;

    @FindBy(xpath = "//div[@class='configuration-ditails-form side-by-side-spinner']/ul//table/tbody//td/button[@aria-disabled='false']/span[@class='ng-binding'][1]")
    WebElement startTimeNextDateCell;

    @FindBy(xpath = "//div[@class='configuration-ditails-form']//ul//table/tbody//td/button[@aria-disabled='false'][@class='btn btn-default btn-sm']/span[@class='ng-binding'][1]")
    WebElement endTimeNextDateCell;

    @FindBy(xpath = "//div[@aria-hidden='false']/div/display-eventslist-configuration/div/span[contains(text(), '-')]")
    List<WebElement> selectedEventsList;

    @FindBy(xpath = "//div[@aria-hidden='false']/div/display-eventslist-configuration/div/span[contains(text(), '-')]/..//button")
    List<WebElement> deleteIconSelectedEventsList;

    //Picture Setting
    //Visual Settings
    @FindBy(xpath = "//div[@class='configuration-ditails-form flex-between ng-scope']/label/span[@title='Brightness']/../..//span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']/input[@ng-model='control.setting.value']")
    WebElement brightnessField;

    @FindBy(xpath = "//div[@class='configuration-ditails-form flex-between ng-scope']/label/span[@title='Brightness']/../..//span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-up ui-corner-tr']")
    WebElement brightnessUPSpinner;

    @FindBy(xpath = "//div[@class='configuration-ditails-form flex-between ng-scope']/label/span[@title='Brightness']/../..//span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-down ui-corner-br']")
    WebElement brightnessDownSpinner;

    @FindBy(xpath = "//div[@class='configuration-ditails-form flex-between ng-scope']/label/span[@title='Brightness']/../..//div[@class='vms-slider-container ng-scope']/span[@class='vms-slider-min ng-binding']")
    WebElement brightnessMinVal;

    @FindBy(xpath = "//div[@class='configuration-ditails-form flex-between ng-scope']/label/span[@title='Brightness']/../..//div[@class='vms-slider-container ng-scope']/span[@class='vms-slider-max ng-binding']")
    WebElement brightnessMaxVal;

    @FindBy(xpath = "//div[@class='configuration-ditails-form flex-between ng-scope']/label/span[@title='Contrast']/../..//span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']/input")
    WebElement contrastField;

    @FindBy(xpath = "//div[@class='configuration-ditails-form flex-between ng-scope']/label/span[@title='Contrast']/../..//span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-up ui-corner-tr']")
    WebElement contrastUPSpinner;

    @FindBy(xpath = "//div[@class='configuration-ditails-form flex-between ng-scope']/label/span[@title='Contrast']/../..//span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-down ui-corner-br']")
    WebElement contrastDownSpinner;

    @FindBy(xpath = "//div[@class='configuration-ditails-form flex-between ng-scope']/label/span[@title='Contrast']/../..//div[@class='vms-slider-container ng-scope']/span[@class='vms-slider-min ng-binding']")
    WebElement contrastMinVal;

    @FindBy(xpath = "//div[@class='configuration-ditails-form flex-between ng-scope']/label/span[@title='Contrast']/../..//div[@class='vms-slider-container ng-scope']/span[@class='vms-slider-max ng-binding']")
    WebElement contrastMaxVal;

    @FindBy(xpath = "//div[@class='configuration-ditails-form flex-between ng-scope']/label/span[@title='Color Saturation']/../..//span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']/input")
    WebElement colorSaturationField;

    @FindBy(xpath = "//div[@class='configuration-ditails-form flex-between ng-scope']/label/span[@title='Color Saturation']/../..//span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-up ui-corner-tr']")
    WebElement colorSaturationUPSpinner;

    @FindBy(xpath = "//div[@class='configuration-ditails-form flex-between ng-scope']/label/span[@title='Color Saturation']/../..//span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-down ui-corner-br']")
    WebElement colorSaturationDownSpinner;

    @FindBy(xpath = "//div[@class='configuration-ditails-form flex-between ng-scope']/label/span[@title='Color Saturation']/../..//div[@class='vms-slider-container ng-scope']/span[@class='vms-slider-min ng-binding']")
    WebElement colorSaturationMinVal;

    @FindBy(xpath = "//div[@class='configuration-ditails-form flex-between ng-scope']/label/span[@title='Color Saturation']/../..//div[@class='vms-slider-container ng-scope']/span[@class='vms-slider-max ng-binding']")
    WebElement colorSaturationMaxVal;

    @FindBy(xpath = "//span[@title='Sharpness']/../..//span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']/input")
    WebElement sharpnessField;

    @FindBy(xpath = "//span[@title='Sharpness']/../..//span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-up ui-corner-tr']")
    WebElement sharpnessUPSpinner;

    @FindBy(xpath = "//span[@title='Sharpness']/../..//span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']/a[@class='ui-spinner-button ui-spinner-down ui-corner-br']")
    WebElement sharpnessDownSpinner;

    @FindBy(xpath = "//span[@title='Sharpness']/../..//div[@class='vms-slider-container ng-scope']/span[@class='vms-slider-min ng-binding']")
    WebElement sharpnessMinVal;

    @FindBy(xpath = "//span[@title='Sharpness']/../..//div[@class='vms-slider-container ng-scope']/span[@class='vms-slider-max ng-binding']")
    WebElement sharpnessMaxVal;

    //Day Night
    @FindBy(xpath = "//div[contains(text(), 'Day Night')]/../div[@class='configuration-ditails-form flex-between']//select")
    WebElement cutFilterModeSelect;

    //Exposure
    @FindBy(xpath = "//div[@class='picture-settings-container ng-scope']/div[contains(text(), 'Exposure')]/../div[@class='configuration-ditails-data']/div/select")
    WebElement exposureModeSelect;

    @FindBy(xpath = "//div[@class='picture-settings-container ng-scope']//div[@class='configuration-ditails-form flex-between ng-scope']//span[@title='Exposure Time']/../..//input")
    WebElement exposureTimeField;

    @FindBy(xpath = "//div[@class='picture-settings-container ng-scope']//div[@class='configuration-ditails-form flex-between ng-scope']//span[@title='Exposure Time']/../..//a[@class='ui-spinner-button ui-spinner-up ui-corner-tr']")
    WebElement exposureTimeUPSpinner;

    @FindBy(xpath = "//div[@class='picture-settings-container ng-scope']//div[@class='configuration-ditails-form flex-between ng-scope']//span[@title='Exposure Time']/../..//a[@class='ui-spinner-button ui-spinner-down ui-corner-br']")
    WebElement exposureTimeDownSpinner;

    @FindBy(xpath = "//div[@class='picture-settings-container ng-scope']//div[@class='configuration-ditails-form flex-between ng-scope']//span[@title='Exposure Time']/../..//span[@class='vms-slider-min ng-binding']")
    WebElement exposureTimeMinVal;

    @FindBy(xpath = "//div[@class='picture-settings-container ng-scope']//div[@class='configuration-ditails-form flex-between ng-scope']//span[@title='Exposure Time']/../..//span[@class='vms-slider-max ng-binding']")
    WebElement exposureTimeMaxVal;

    @FindBy(xpath = "//div[@class='picture-settings-container ng-scope']//div[@class='configuration-ditails-form flex-between ng-scope']//span[@title='Exposure Time']/../..//div[@class='vms-slider-container ng-scope']/div")
    WebElement exposureTimeSlider;

    @FindBy(xpath = "//div[@class='configuration-ditails-form flex-between ng-scope']//span[@title='Gain']/../..//span[contains(@class,'ui-spinner ui-widget ui-corner-all ui-widget-header')]/input")
    WebElement exposureGainField;

    @FindBy(xpath = "//div[@class='configuration-ditails-form flex-between ng-scope']//span[@title='Gain']/../..//span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']//a[@class='ui-spinner-button ui-spinner-up ui-corner-tr']")
    WebElement exposureGainUPSpinner;

    @FindBy(xpath = "//div[@class='configuration-ditails-form flex-between ng-scope']//span[@title='Gain']/../..//span[@class='ui-spinner ui-widget ui-corner-all ui-widget-header']//a[@class='ui-spinner-button ui-spinner-down ui-corner-br']")
    WebElement exposureGainDownSpinner;

    @FindBy(xpath = "//div[@class='configuration-ditails-form flex-between ng-scope']//span[@title='Gain']/../..//span[@class='vms-slider-min ng-binding']")
    WebElement exposureGainMinVal;

    @FindBy(xpath = "//div[@class='configuration-ditails-form flex-between ng-scope']//span[@title='Gain']/../..//span[@class='vms-slider-max ng-binding']")
    WebElement exposureGainMaxVal;

    @FindBy(xpath = "//div[@class='configuration-ditails-form flex-between ng-scope']//span[@title='Gain']/../..//div[@class='vms-slider-container ng-scope']/div")
    WebElement exposureGainSlider;

    //Backlight Compensation
    @FindBy(xpath = "//div[@class='picture-settings-container ng-scope']/div[contains(text(), 'Backlight Compensation')]/../div[@class='configuration-ditails-data']/div/select[@aria-disabled='false']")
    WebElement backlightCompensationModeSelect;

    @FindBy(xpath = "//div[@class='ui-grid-canvas']//div[@class='ui-grid-cell-contents ng-scope']/span[@class='flex ng-scope']/span[@class='text-overflow ng-binding']")
    List<WebElement> DevicesListLeftPanel;
    
    @FindBy(xpath = "//div[@class='ui-grid-canvas']//div[@class='ui-grid-cell-contents ng-scope']/span[@class='flex ng-scope']/span[@class='text-overflow ng-binding']")
    List<WebElement> DevicesListLeftPanel1;

    @FindBy(xpath = "//div[@class='vms-channels-linked-audio-resources']//div[@class='flex configuration-audioresources-container ng-scope']//span[@class='text-overflow ng-binding']")
    List<WebElement> DeviceAudioChannelsList;

    @FindBy(xpath = "//div[@class='ui-grid-disable-selection ng-scope']//div[@class='ui-grid-cell-contents']/div")
    List<WebElement> VAudioChannelsList;

    @FindBy(xpath = "//div[@class='ui-grid-canvas']//div[@class='ng-isolate-scope']//div[@class='ui-grid-cell-contents flex ng-scope']/span[@class='microphone-name text-overflow ng-binding']")
    List<WebElement> AudioChannelsList;

    //Masking

    @FindBy(id = "vlcToolsPlayer")
    WebElement videoScreen;

    @FindBy(xpath = "//div[@id='vms-ptz-container']//div[@class='ui-grid-viewport ng-isolate-scope']/div[@class='ui-grid-canvas']/div[contains(@class, 'ui-grid-row ng-scope')]")
    List<WebElement> maskList;
    
    //PTZ Setting
    
    @FindBy(id = "ptz_settings")
    WebElement ptzsettingbutton;
    
    @FindBy(id = "pano_360")
    WebElement camerabutton;
    
    @FindBy(xpath = "//div[@class = 'vms-channels-config-tab ng-isolate-scope']//ul/li[8]")
    WebElement ptztab;

    @FindBy(xpath = "//*[@id=\"left-ptz-panel\"]/div[2]/md-content/md-tabs/md-tabs-wrapper/md-tabs-canvas/md-pagination-wrapper/md-tab-item[2]")
    WebElement presettours;
    
    @FindBy(xpath = "//*[@id=\"tab-content-1\"]/div/div[2]/div/div/div[1]/div[1]")
    WebElement editplayButton;
    
    @FindBy(xpath = "//*[@id=\"1581502508064-1-uiGrid-004G-cell\"]/div/span/i")
    WebElement clickonPTZCamera;
    
    @FindBy(xpath = "//*[@id=\"tab-content-1\"]/div/div[2]/div/div/div[1]/div[1]/div[1]/div[5]/span[3]")
    WebElement removebutton;
    
    @FindBy(xpath = "//*[@id=\"vms-ptz-container\"]/div/div/div[2]/div[2]/ng-form/div/div[2]/div[3]/div[1]/span/a[2]/span")
    WebElement zoomspeeddown;
    
    @FindBy(xpath = "//*[@id=\"vms-ptz-container\"]/div/div/div[2]/div[2]/ng-form/div/div[2]/div[3]/div[1]/span/a[1]/span")
    WebElement zoomspeedup;
    
    @FindBy(xpath = "//div[@class='ptz-circle-container zoom flex']/div/div[2]")
    WebElement zoomout;
    
    @FindBy(xpath = "//div[@class='ptz-circle-container zoom flex']/div/div[1]")
    WebElement zoomin;
    
    @FindBy(xpath = "//span[@class='vicon-font v-arrow-up']")
    WebElement panTiltup;
    
    @FindBy(xpath = "//span[@class='vicon-font v-arrow-down']")
    WebElement panTiltdown;
    
    @FindBy(xpath = "//span[@class='vicon-font v-arrow-left']")
    WebElement panTiltleft;
    
    @FindBy(xpath = "//span[@class='vicon-font v-arrow-right']")
    WebElement panTiltright;
    
    @FindBy(xpath = "//*[@id=\"tab-content-2\"]/div/div[2]/div/div/div[2]/button")
    WebElement addpreset;
    
    @FindBy(xpath = "//div[@class='ui-grid-disable-selection ng-scope']/div//div[@class='ui-grid-selection-row-header-buttons ui-grid-icon-ok ng-scope']")
    WebElement presetcheckbox;
    
    @FindBy(xpath = "//*[@id=\"vms-export-container\"]/div[3]/button[1]")
    WebElement applybutton;
    
    @FindBy(xpath = "//*[@id=\"tab-content-1\"]/div/div[2]/div/div/div[3]/button[1]/div")
    WebElement cancelbutton;
    
    @FindBy(xpath = "//*[@id=\"tab-content-1\"]/div/div[2]/div/div/div[2]/button/span")
    WebElement addnewtourbutton;
    
    @FindBy(xpath = "//*[@id=\"tab-content-1\"]/div/div[2]/div/div/div[3]/button[2]")
    WebElement savebutton;
    
    @FindBy(xpath = "//span[@title='Add Preset Tour']")
    WebElement addpresettour1;
    
    @FindBy(xpath = "//*[@id=\"vms360Container\"]/div[1]/div/div/md-switch/div[1]")
    WebElement enable360;



    //Methods
    public VideoChannelsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void GoToVideoChannelsPage() throws InterruptedException {
        Thread.sleep(1000);
        try{GoToConfigurationPage();
        Thread.sleep(1000);

        ClickOnResourcesButton();
        Thread.sleep(1000);
        waitUntilResourcesMenuIsExpanded();
        Thread.sleep(1000);
        ClickOnVideoChannels();
        Thread.sleep(1000);}
        catch(Exception e){
            takeScreenshot(driver, "Setup", "GoToVideoChannelsPage");
        }
    }

    public void GoToVideoChannelsPageFromLanding() throws InterruptedException {
        Thread.sleep(2000);
        GoToConfigurationPage();
        Thread.sleep(2000);
        ClickOnVideoChannelsLandingPage();
        Thread.sleep(1000);
    }

    public void ClickOnVideoChannels() throws InterruptedException {
        boolean breakIt = true;
        int time = 0;
        while (true) {
            breakIt = true;
            try {
                JavaScriptClick(videoChannelsButton);//.click();
                Thread.sleep(1000);

            } catch (Exception e) {
                if (e.getMessage().contains("element not visible")) {
                    if(!ResourcesMenuIsOpen()){
                        ClickOnResourcesButton();
                        waitUntilResourcesMenuIsExpanded();
                    }
                    breakIt = false;
                }
            }
            time++;
            if (breakIt || time>10) {
                break;
            }
        }
        Thread.sleep(1000);
    }
    public void ClickOnNumericID() throws InterruptedException {
        boolean breakIt = true;
        int time = 0;
        while (true) {
            breakIt = true;
            try {
                JavaScriptClick(numericIDButton);//.click();
                Thread.sleep(1000);

            } catch (Exception e) {
                if (e.getMessage().contains("element not visible")) {
                    if(!ResourcesMenuIsOpen()){
                        ClickOnResourcesButton();
                        waitUntilResourcesMenuIsExpanded();
                    }
                    breakIt = false;
                }
            }
            time++;
            if (breakIt || time>10) {
                break;
            }
        }
        Thread.sleep(1000);
    }
    public void GoToVideoChannelsPageFromLanding_ChannelPr() throws InterruptedException {
        Thread.sleep(1000);
        GoToConfigurationPage();
        Thread.sleep(1000);
        ClickOnVideoChannelsLandingPage();
        Thread.sleep(1000);
    }
    public void ClickOnVideoChannels1() throws InterruptedException {
        boolean breakIt = true;
        int time = 0;
        while (true) {
            breakIt = true;
            try {
                videoChannelsButton.click();
                Thread.sleep(1000);

            } catch (Exception e) {
                if (e.getMessage().contains("element not visible")) {
                    if(!ResourcesMenuIsOpen()){
                        ClickOnResourcesButton();
                        waitUntilResourcesMenuIsExpanded();
                    }
                    breakIt = false;
                }
            }
            time++;
            if (breakIt || time>10) {
                break;
            }
        }
        Thread.sleep(1000);
    }

    public void ClickOnVideoChannelsLandingPage() throws InterruptedException {
        waitUntilIsLoadedCustomTime(videoChannelsButtonLandingPage);
        videoChannelsButtonLandingPage.click();
    }
    public void ClickOnnumericId_Page() throws InterruptedException {
        waitUntilIsLoadedCustomTime(numericIDButtonLandingPage);
        numericIDButtonLandingPage.click();
    }

    public WebElement SelectRandomDevice(){
        waitUntilElementIsLoaded(plusMinusDeviceSquared);
        if ((plusMinusDeviceSquared.getClass()).equals("ui-grid-tree-base-row-header-buttons ui-grid-icon-plus-squared")){
            plusMinusDeviceSquared.click();
        }
        WaitUntilDevicesAreLoaded();
        int max=GetDevicesSize()-1;
        int random =(int) (Math.random()*max);
        WebElement device = DevicesListLeftPanel.get(random);
        return device;
    }

    public void WaitUntilDevicesAreLoaded(){
        try{
            new WebDriverWait(driver, 30).until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//div[@class='ui-grid-canvas']//div[@class='ui-grid-cell-contents ng-scope']/span[@class='flex ng-scope']/span[@class='text-overflow ng-binding']"), 0));
        }catch (Exception e){}
    }

    public int GetDevicesSize(){
        return DevicesListLeftPanel.size();
    }
    public int GetDevicesSize1(){
        return DevicesListLeftPanel1.size();
    }

    public String GetDeviceText(int index){
        return DevicesListLeftPanel.get(index).getText();
    }
    
    public String GetDeviceText1(int index){
        return DevicesListLeftPanel1.get(index).getText();
    }


    public void FilterCamera(String text) throws InterruptedException {
        setElementText(filterDevicesField, text);
        Thread.sleep(1000);
    }
   /* public void FilterCamera2(String text1) throws InterruptedException {
        setElementText1(filterDevicesField, text1);
        Thread.sleep(1000);
    }*/
    public void ClickOnCameraList7()
    {
    WebElement camera = driver.findElement(By.xpath("//div[@class='btn-panel bottom-separator-builder new-ui-panel-background']//div[@class='vms-search-with-btn']/form/input"));
    camera.sendKeys("172.20.0.195");
    }
    public void ClickOnCameraList_11()
    {
    WebElement camera = driver.findElement(By.xpath("//div[@class='btn-panel bottom-separator-builder new-ui-panel-background']//div[@class='vms-search-with-btn']/form/input"));
    camera.sendKeys("172.20.1.11");
    }
    public void ClickOnCameraList_26()
    {
    WebElement camera = driver.findElement(By.xpath("//div[@class='btn-panel bottom-separator-builder new-ui-panel-background']//div[@class='vms-search-with-btn']/form/input"));
    camera.sendKeys("172.20.0.26");
    }
    public void ClickOnCameraList_28()
    {
    WebElement camera = driver.findElement(By.xpath("//div[@class='btn-panel bottom-separator-builder new-ui-panel-background']//div[@class='vms-search-with-btn']/form/input"));
    camera.sendKeys("172.20.0.28");
    }
    public void ClickOnCameraList_DualOptions()
    {
    WebElement camera = driver.findElement(By.xpath("//div[@class='btn-panel bottom-separator-builder new-ui-panel-background']//div[@class='vms-search-with-btn']/form/input"));
    camera.sendKeys("172.20.0.82");
    }
    public void ClickOnCameraList_DualOptions_195()
    {
    WebElement camera = driver.findElement(By.xpath("//div[@class='btn-panel bottom-separator-builder new-ui-panel-background']//div[@class='vms-search-with-btn']/form/input"));
    camera.sendKeys("172.20.0.195");
    }
    public void ClickOnCameraList_DualOptions_184()
    {
    WebElement camera = driver.findElement(By.xpath("//div[@class='btn-panel bottom-separator-builder new-ui-panel-background']//div[@class='vms-search-with-btn']/form/input"));
    camera.sendKeys("172.20.0.184");
    }
    public void ClickOnCameraList_51()
    {
    WebElement camera = driver.findElement(By.xpath("//div[@class='btn-panel bottom-separator-builder new-ui-panel-background']//div[@class='vms-search-with-btn']/form/input"));
    camera.sendKeys("172.20.0.51");
    }
    public void ClickOnCameraList()
    {
    WebElement camera = driver.findElement(By.xpath("//div[@class='btn-panel bottom-separator-builder new-ui-panel-background']//div[@class='vms-search-with-btn']/form/input"));
    camera.sendKeys("172.20.0.71");
    }
    public void ClickOnCameraList3()
    {
    WebElement camera = driver.findElement(By.xpath("//div[@class='btn-panel bottom-separator-builder new-ui-panel-background']//div[@class='vms-search-with-btn']/form/input"));
    camera.sendKeys("172.20.0.110");
    }
    public void ClickOnCameraList4()
    {
    WebElement camera = driver.findElement(By.xpath("//div[@class='btn-panel bottom-separator-builder new-ui-panel-background']//div[@class='vms-search-with-btn']/form/input"));
    camera.sendKeys("172.20.0.19");
    }
    public void ClickOnCameraList1()
    {
    WebElement camera = driver.findElement(By.xpath("//div[@class='btn-panel bottom-separator-builder new-ui-panel-background']//div[@class='vms-search-with-btn']/form/input"));
    camera.sendKeys("172.20.0.45");
    }
    public void CameraList_28()
    {
    WebElement camera = driver.findElement(By.xpath("//div[@class='ui-grid-canvas']//div[@class='ui-grid-cell-contents ng-scope']/span[@class='flex ng-scope']/span[@title='172.20.0.28_Camera']"));
    JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", camera);
    //camera.sendKeys("172.20.0.145");
    }
    public void CameraList_26()
    {
    WebElement camera = driver.findElement(By.xpath("//div[@class='ui-grid-canvas']//div[@class='ui-grid-cell-contents ng-scope']/span[@class='flex ng-scope']/span[@title='172.20.0.26_Camera']"));
    JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", camera);
    //camera.sendKeys("172.20.0.145");
    }
    public void CameraList_51()
    {
    WebElement camera = driver.findElement(By.xpath("//div[@class='ui-grid-canvas']//div[@class='ui-grid-cell-contents ng-scope']/span[@class='flex ng-scope']/span[@title='172.20.0.51_Camera']"));
    JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", camera);
    //camera.sendKeys("172.20.0.145");
    }
    public void CameraList4()
    {
    WebElement camera = driver.findElement(By.xpath("//div[@class='ui-grid-canvas']//div[@class='ui-grid-cell-contents ng-scope']/span[@class='flex ng-scope']/span[@title='172.20.0.19_Camera']"));
    JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", camera);
    //camera.sendKeys("172.20.0.145");
    }
    public void CameraList7()
    {
    WebElement camera = driver.findElement(By.xpath("//div[@class='ui-grid-canvas']//div[@class='ui-grid-cell-contents ng-scope']/span[@class='flex ng-scope']/span[@title='172.20.0.195_Camera']"));
    JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", camera);
    //camera.sendKeys("172.20.0.145");
    }
    public void CameraList_11()
    {
    WebElement camera = driver.findElement(By.xpath("//div[@class='ui-grid-canvas']//div[@class='ui-grid-cell-contents ng-scope']/span[@class='flex ng-scope']/span[@title='172.20.1.11_Camera']"));
    JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", camera);
    //camera.sendKeys("172.20.0.145");
    }
    public void CameraList_DualOptinos()
    {
    WebElement camera = driver.findElement(By.xpath("//div[@class='ui-grid-canvas']//div[@class='ui-grid-cell-contents ng-scope']/span[@class='flex ng-scope']/span[@title='172.20.0.82_Camera']"));
    JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", camera);
    //camera.sendKeys("172.20.0.145");
    }
    public void CameraList_DualOptinos_195()
    {
    WebElement camera = driver.findElement(By.xpath("//div[@class='ui-grid-canvas']//div[@class='ui-grid-cell-contents ng-scope']/span[@class='flex ng-scope']/span[@title='172.20.0.195_Camera_1 ']"));
    JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", camera);
    //camera.sendKeys("172.20.0.145");
    }
    public void CameraList_DualOptinos_184()
    {
    WebElement camera = driver.findElement(By.xpath("//div[@class='ui-grid-canvas']//div[@class='ui-grid-cell-contents ng-scope']/span[@class='flex ng-scope']/span[@title='172.20.0.184_Camera_1 ']"));
    JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", camera);
    //camera.sendKeys("172.20.0.145");
    }
    public void ClickOnCameraList2()
    {
    WebElement camera = driver.findElement(By.xpath("//div[@class='btn-panel bottom-separator-builder new-ui-panel-background']//div[@class='vms-search-with-btn']/form/input"));
    camera.sendKeys("172.20.0.141");
    }
    public void ClickOnCameraList5()
    {
    WebElement camera = driver.findElement(By.xpath("//div[@class='btn-panel bottom-separator-builder new-ui-panel-background']//div[@class='vms-search-with-btn']/form/input"));
    camera.sendKeys("172.20.0.24");
    }
    public void ClickOnCameraList6()
    {
    WebElement camera = driver.findElement(By.xpath("//div[@class='btn-panel bottom-separator-builder new-ui-panel-background']//div[@class='vms-search-with-btn']/form/input"));
    camera.sendKeys("172.20.0.84");
    }
    public void CameraList3()
    {
    WebElement camera = driver.findElement(By.xpath("//div[@class='ui-grid-canvas']//div[@class='ui-grid-cell-contents ng-scope']/span[@class='flex ng-scope']/span[@title='172.20.0.110_Camera']"));
    JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", camera);
    //camera.sendKeys("172.20.0.145");
    }
    public void CameraList6()
    {
    WebElement camera = driver.findElement(By.xpath("//div[@class='ui-grid-canvas']//div[@class='ui-grid-cell-contents ng-scope']/span[@class='flex ng-scope']/span[@title='172.20.0.84_Camera']"));
    JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", camera);
    //camera.sendKeys("172.20.0.145");
    }
    public void CameraList5()
    {
    	 WebElement camera = driver.findElement(By.xpath("//div[@class='ui-grid-canvas']//div[@class='ui-grid-cell-contents ng-scope']/span[@class='flex ng-scope']/span[@title='172.20.0.24_Camera']"));
    	    JavascriptExecutor executor = (JavascriptExecutor)driver;
    		executor.executeScript("arguments[0].click();", camera);
    }
    public void CameraList2()
    {
    WebElement camera = driver.findElement(By.xpath("//div[@class='ui-grid-canvas']//div[@class='ui-grid-cell-contents ng-scope']/span[@class='flex ng-scope']/span[@title='172.20.0.141_Camera']"));
    JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", camera);
    //camera.sendKeys("172.20.0.145");
    }
    public void CameraList1()
    {
    WebElement camera = driver.findElement(By.xpath("//div[@class='ui-grid-canvas']//div[@class='ui-grid-cell-contents ng-scope']/span[@class='flex ng-scope']/span[@title='172.20.0.45_Camera']"));
    JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", camera);
    //camera.sendKeys("172.20.0.145");
    }
    public void CameraList()
    {
    WebElement camera = driver.findElement(By.xpath("//div[@class='ui-grid-canvas']//div[@class='ui-grid-cell-contents ng-scope']/span[@class='flex ng-scope']/span[@title='172.20.0.71_Camera']"));
    JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", camera);
    //camera.sendKeys("172.20.0.145");
    }
    
    public void FilterCamera1() throws InterruptedException {
        //setElementText(filterDevicesField, text);
    	//JavaScriptClick(clickonPTZCamera);
    	WebElement ele = driver.findElement(By.xpath("//div[@class='btn-panel bottom-separator-builder new-ui-panel-background']//div[@class='vms-search-with-btn']/form/input]"));
    	ele.sendKeys("172.20.0.145");
    	//WebElement ele =	driver.findElement(By.xpath("//div[@class='ui-grid-canvas']//div[@class='ui-grid-cell-contents ng-scope']/span[@class='flex ng-scope']/span[@title='172.20.0.145_Camera']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele);
    	//ele.click();
        Thread.sleep(1000);
       //JavaScriptClick(clickonPTZCamera);
    }

    public void ChangeName(String name) throws InterruptedException {
       // waitUntilIsLoadedCustomTime(saveButton);
        setElementText(nameChannelPropertiesField, name);
    }

    public String GetNameFromProperties(){
        return nameChannelPropertiesField.getAttribute("value");
    }

    public void Refresh() throws InterruptedException {
        driver.navigate().refresh();
        try{
            driver.switchTo().alert().accept();
        }catch(Exception a){}
        WaitUntilEndRefresh();
        new WebDriverWait(driver, 55).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id = 'vms-resources-groups-treeview']")));
        Thread.sleep(3000);
    }
    public void RefreshPage()
    {
    	 JavascriptExecutor js = (JavascriptExecutor)driver;
    	 js.executeScript("history.go(0)");
    }

    public void GoToStreamsPage(){
        streamsButton.click();
    }

    public void GoToChannelPropertiesPage() throws InterruptedException {
        waitUntilElementIsLoaded(channelPropertiesButton);
        Thread.sleep(1000);
        channelPropertiesButton.click();
        waitUntilElementIsLoaded(visibleOnOffToggler);
    }

    public void PressOnChannelPropertiesButton(){
        channelPropertiesButton.click();
    }
    
    public void GoToPTZSttingPage() throws InterruptedException {
        waitUntilElementIsLoaded(ptzsettingbutton);
        Thread.sleep(1000);
        ptzsettingbutton.click();
        waitUntilElementIsLoaded(visibleOnOffToggler);
    }
    public void PressOnPTZSettingButton(){
    	//JavaScriptClick(ptzsettingbutton);
    	ptzsettingbutton.click();
    }
    public void GoToPTZSettings(){
        waitUntilElementIsLoaded(ptztab);
        try{
        	ptztab.click();
        }catch(Exception e){}
    }

    
    public void ClickOn360Tab()
    {
    	camerabutton.click();
    }
    public void Enable360Toggle()
    {
    	JavaScriptClick(enable360);
    }
    public void AddPreset()
    {
    	JavaScriptClick(addpreset);
    }
    public void EnterNumericIDInPreset()
    {
    	WebElement ele = driver.findElement(By.xpath("//*[@id=\"tab-content-2\"]/div/div[2]/div/div/div[1]/div[10]/div[2]/input"));
    	//Actions builder = new Actions(driver);
    	//builder.doubleClick(ele).perform();
    	Actions builder = new Actions(this.driver);
    	builder.moveToElement(ele);
    	builder.keyDown(Keys.LEFT_CONTROL);
    	builder.keyDown(Keys.LEFT_ALT);
    	builder.doubleClick();
    	builder.keyUp(Keys.LEFT_CONTROL);
    	builder.keyUp(Keys.LEFT_ALT).build().perform();
    	builder.build().perform();
    	
    	//driver.findElement(By.xpath("//input[@class='preset-numeric-input ng-valid ng-dirty ng-valid-number ng-touched']")).sendKeys("235");
    	//Action typeInCAPS = builder.keyDown(ele, Keys.ENTER)
               // .sendKeys(ele, "235").build();
                
                    
    	//typeInCAPS.perform();	
    	//input[@class='preset-numeric-input ng-valid ng-dirty ng-valid-number ng-touched']
    }
    public void MouseHoverTour()
    {
    	Actions action = new Actions(driver);
    	WebElement we = driver.findElement(By.xpath("//*[@id=\"tab-content-1\"]/div/div[2]/div/div/div[1]/div[1]"));
    	action.moveToElement(we).build().perform();
    }
    public void MouseHover_SaveButtonPreset()
    {
    	Actions action = new Actions(driver);
    	WebElement we = driver.findElement(By.xpath("//*[@id=\"tab-content-0\"]/div/div[2]/div/div/div[1]/div[7]/div[3]/span[1]"));
    	action.moveToElement(we).perform();
    	//we.click();
    	//we.isDisplayed();
    }

public void MouseHover_EditButtonPreset()
{
	Actions action = new Actions(driver);
	WebElement we = driver.findElement(By.xpath("//*[@id=\"tab-content-0\"]/div/div[2]/div/div/div[1]/div[12]/div[3]/span[2]"));
	action.moveToElement(we).perform();
	//we.click();
	//we.isDisplayed();
}
public void MouseHover_DeleteButtonPreset()
{
	Actions action = new Actions(driver);
	WebElement we = driver.findElement(By.xpath("//*[@id=\"tab-content-0\"]/div/div[2]/div/div/div[1]/div[4]/div[3]/span[3]"));
	action.moveToElement(we).perform();
	//we.click();
	//we.isDisplayed();
}
public void MouseHover_DisplayButtonPreset()
{
	//Actions action = new Actions(driver);
	WebElement we = driver.findElement(By.xpath("//*[@id=\"tab-content-0\"]/div/div[2]/div/div/div[1]/div[4]/div[3]/span[3]"));
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", we);
	//action.moveToElement(we).build().perform();
	//we.click();
	//we.isDisplayed();
}
public void SpinnerUpPresetTour()
{
	WebElement we = driver.findElement(By.xpath("//*[@id=\"3a0e99a4-3752-443f-a23d-ddaac424c4fe\"]/div[3]/div[2]/span/a[1]/span"));
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", we);
}
    public void MouseHoverPreset()
    {
    	Actions action = new Actions(driver);
    	WebElement we = driver.findElement(By.xpath("//*[@id=\"tab-content-0\"]/div/div[2]/div/div/div[1]/div[6]"));
    	action.moveToElement(we).build().perform();
    	we.isDisplayed();
    }
    
    public void Tourslist()
    {
    	WebElement we = driver.findElement(By.xpath("//*[@id=\"tab-content-1\"]/div/div[2]/div/div/div[1]/div[1]"));
    	we.isDisplayed();
    }
    public void Preset()
    {
    	WebElement we = driver.findElement(By.xpath("//*[@id=\"left-ptz-panel\"]/div[2]/md-content/md-tabs/md-tabs-wrapper/md-tabs-canvas/md-pagination-wrapper/md-tab-item[1]/span[2]"));
    	we.isDisplayed();
    }
    public void PresetButton()
    {
    	WebElement we = driver.findElement(By.xpath("//*[@id=\"left-ptz-panel\"]/div[2]/md-content/md-tabs/md-tabs-wrapper/md-tabs-canvas/md-pagination-wrapper/md-tab-item[1]/span[2]"));
    	we.isDisplayed();
    	we.click();
    }
    public void Edit_PresetTour()
    {
    	WebElement we = driver.findElement(By.xpath("//*[@id=\"tab-content-1\"]/div/div[2]/div/div/div[1]/div[1]/div[1]/div[5]/span[1]"));
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
    	executor.executeScript("arguments[0].click();", we);
    }
    public void Display_PresetTour()
    {
    	WebElement we = driver.findElement(By.xpath("//*[@id=\"tab-content-1\"]/div/div[2]/div/div/div[1]/div[5]/div[1]/div[5]/span[2]"));
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
    	executor.executeScript("arguments[0].click();", we);
    }
    public void Delete_PresetTour()
    {
    	WebElement we = driver.findElement(By.xpath("//*[@id=\"tab-content-1\"]/div/div[2]/div/div/div[1]/div[4]/div[1]/div[5]/span[3]"));
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
    	executor.executeScript("arguments[0].click();", we);
    }
    public void SetIDNumber()
    {
    	WebElement ele = driver.findElement(By.xpath("//*[@id=\"tab-content-0\"]/div/div[2]/div/div/div[1]/div[6]/div[2]/input"));
    	JavascriptExecutor myExecutor = ((JavascriptExecutor) driver);
    	myExecutor.executeScript("arguments[0].value='23';", ele);
    	//ele.click();
    	ele.isDisplayed();
    	//ele.click();
    	//ele.sendKeys("233");
    }
    
    public void ClickOn_SaveButton()
    {
    	WebElement we = driver.findElement(By.xpath("//*[@id=\"tab-content-0\"]/div/div[2]/div/div/div[1]/div[7]/div[3]/span[1]"));
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
    	executor.executeScript("arguments[0].click();", we);
    }
    public void ClickOn_DeleteButton()
    {
    	WebElement we = driver.findElement(By.xpath("//*[@id=\"tab-content-0\"]/div/div[2]/div/div/div[1]/div[3]/div[3]/span[4]"));
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
    	executor.executeScript("arguments[0].click();", we);
    }
    public void ClickOnSetNumericIDField()
    {
    	Actions action = new Actions(driver);
    	WebElement link = driver.findElement(By.xpath("//*[@id=\"tab-content-0\"]/div/div[2]/div/div/div[1]/div[6]/div[2]/input"));
    	action.doubleClick(link).perform();
    }
    public void EnterSetNumericIDField()
    {
    	Actions action = new Actions(driver);
    	WebElement link = driver.findElement(By.xpath("//*[@id=\"tab-content-1\"]/div/div[2]/div/div/div[1]/div[2]/div[1]/div[3]"));
    	action.doubleClick(link).perform();
    	//link.sendKeys("233");
    }
    public void Display_NameNum()
    {
    	WebElement ele = driver.findElement(By.xpath("//*[@id=\"tab-content-0\"]/div/div[2]/div/div/div[1]/div[2]"));
    	ele.isDisplayed();
    }
    public void ClickPresetToursTab()
    {
    	JavaScriptClick(presettours);
    }
    public void AddPresetTour()
    {
    	JavaScriptClick(addpresettour1);
    }
    public void ClickAddPresetButton()
    {
    	JavaScriptClick(addpreset);
    	
    	/*Alert al = driver.switchTo().alert();
    	String getalert = driver.switchTo().alert().getText();
    	System.out.println(getalert);
    	al.accept();*/
    }
    public void AddPresetButton()
    {
    	WebElement ele =	driver.findElement(By.xpath("//*[@id=\"tab-content-2\"]/div/div[2]/div/div/div[2]/button"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele);
    }
    public void Multiplecheckbox()
    {
    	List <WebElement> els = driver.findElements(By.xpath( "//div[@class='ui-grid-canvas']/div/div/div"));
    	for ( WebElement el : els ) {
    	    if ( !el.isSelected() ) {
    	        el.click();
    	    }
    	}
    }
    public void ThreePresets()
    {
    WebElement chkFBPersist = driver.findElement(By.xpath("//div[@class='ui-grid-canvas']/div/div/div"));							
        for (int i=0; i<=3; i++) {											
            chkFBPersist.click (); 			
            System.out.println("Facebook Persists Checkbox Status is -  "+chkFBPersist.isSelected());							
        }		
    }
    public void CheckCheckBoxes()
    {
    	List<WebElement> ListOfCheckBoxes = driver.findElements(By.xpath("//div[@class='ui-grid-canvas']/div/div/div"));
    	  System.out.println("Number of check boxes pesent are: "+ListOfCheckBoxes.size());
    	  
    	  ListOfCheckBoxes.get(1).click();
    	  ListOfCheckBoxes.get(2).click();
    	  ListOfCheckBoxes.get(3).click();
    	 // for(int i=0; i< ListOfCheckBoxes.size() ; i++) {
    	   //if(ListOfCheckBoxes.get(i).getAttribute("value").equalsIgnoreCase("Red")){
    	    //ListOfCheckBoxes.get(i).click();
    	   
    }

    public void AddPresetSearch() {
    
    	WebElement search= driver.findElement(By.xpath("//*[@id=\"nvrSvreen\"]/div/div/div/div[1]/div/span/div/div/form/input"));
    	search.sendKeys("Preset14");
    	search.isDisplayed();
    }
    public void ClickOnPresetCheckBox() throws InterruptedException
    {
    	WebElement checkbox = driver.findElement(By.xpath("//div[@class='ui-grid-disable-selection ng-scope']/div//div[@class='ui-grid-selection-row-header-buttons ui-grid-icon-ok ng-scope']"));
    	checkbox.click();
    	//JavaScriptClick(presetcheckbox);
    	//Thread.sleep(1000);
    	//presetcheckbox.isDisplayed();
    }
    public void ClickOnStreamCheck() throws InterruptedException
    {
    	WebElement checkbox = driver.findElement(By.xpath("//div[@class='ui-grid-disable-selection ng-scope']/div//div[@class='ui-grid-selection-row-header-buttons ui-grid-icon-ok ng-scope']"));
    	checkbox.click();
    }
    public void ClickOnDownZoomSpeed()
    {
    	//JavaScriptClick(zoomin);
    	WebElement ele =	driver.findElement(By.xpath("//*[@id=\"vms-ptz-container\"]/div/div/div[2]/div[2]/ng-form/div/div[2]/div[3]/div[1]/span/a[2]/span"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele);
    }
    public void ClickOnCancelButton()
    {
    	JavaScriptClick(cancelbutton);
    }
    public void AddNewTourButton()
    {
    	JavaScriptClick(addnewtourbutton);
    }
    public void EditNewTourButton()
    {
    	//WebElement edit = driver.findElement(By.xpath("//*[@id=\"tab-content-1\"]/div/div[2]/div/div/div[1]/div[3]/div[1]/div[5]/span[1]"));
    	//edit.click();
    	//edit.sendKeys("235");
    	//edit.isDisplayed();
    	WebElement ele =	driver.findElement(By.xpath("//*[@id=\"tab-content-1\"]/div/div[2]/div/div/div[1]/div[3]/div[1]/div[5]/span[1]"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele);
    			
    	//JavaScriptClick(addnewtourbutton);
    }
    public void EditField()
    {
    	//WebElement editfield = driver.findElement(By.xpath("//*[@id=\"tab-content-1\"]/div/div[2]/div/div/div[1]/div[4]/div[1]/div[2]/input"));
    	/*WebElement ele =	driver.findElement(By.xpath("//*[@id=\"tab-content-1\"]/div/div[2]/div/div/div[1]/div[3]/div[1]/div[5]/span[1]"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele);
		ele.sendKeys("25");
		ele.isDisplayed();*/
    	WebElement searchbox = driver.findElement(By.xpath("//*[@id=\"tab-content-1\"]/div/div[2]/div/div/div[1]/div[3]/div[1]/div[5]/span[1]"));
    	JavascriptExecutor myExecutor = ((JavascriptExecutor) driver);
    	myExecutor.executeScript("arguments[0].value='23';", searchbox);
    }
    public void ClickOnSaveButton()
    {
    	JavaScriptClick(savebutton);
    }
    public void ClickOnApply()
    {
    	JavaScriptClick(applybutton);
    	applybutton.isDisplayed();
    }
    public void ClickOnZoomOut()
    {
    	JavaScriptClick(zoomout);
    }
    public void ClickOnZoomIn()
    {
    	JavaScriptClick(zoomin);
    }
    public void ClickOnPanTiltUp()
    {
    	JavaScriptClick(panTiltup);
    }
    public void ClickOnPanTiltDown()
    {
    	JavaScriptClick(panTiltdown);
    }
    public void ClickOnUpZoomSpeed()
    {
    	JavaScriptClick(zoomspeedup);
    }
    public void ClickOnPanTiltLeft()
    {
    	JavaScriptClick(panTiltleft);
    }
    public void ClickOnPanTiltRight()
    {
    	JavaScriptClick(panTiltright);
    }
    public void ClickOnSearch()
    {
    	WebElement ele =	driver.findElement(By.xpath("//*[@id=\"tab-content-0\"]/div/div[1]/div[1]/span/div/div/form/input"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele);
		ele.sendKeys("Preset1");
    }
    public void ClickOnSpeedField()
    {
    	WebElement ele =	driver.findElement(By.xpath("//*[@id=\"vms-ptz-container\"]/div/div/div[2]/div[2]/ng-form/div/div[1]/div[3]/div[1]/span/input"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele);
		ele.clear();
		ele.sendKeys("12");
    }
    public void ClicRemoveButtonTour()
    {
    	JavaScriptClick(removebutton);
    }
    public boolean ChannelPropertiesIsLoaded(){
        return  verifyElementIsPresent(addAudioResourcesButton);
    }
    
    public boolean ChannelPropertiesIsLoaded1(){
        return  verifyElementIsPresent(channelPropertiesButton);
    }

    public boolean VideoChannelPageIsOpen(){
        return  verifyElementIsPresent(showPlayerVideoWindow);
    }


    public String GetNameFromTitle(){
        return panelTitle.getText();
    }

    public boolean StreamsPageIsLoaded (){
        return verifyElementIsPresent(cameraStreamingDropDownList);
    }

    public boolean RecordingPageIsLoaded (){
        return verifyElementIsPresent(recordingOnOffToggler);
    }

    public String GetClassVisibleToggle(){
        return visibleOnOffToggler.getAttribute("aria-checked");
    }

    public void SwitchVisibleToggler(){
        visibleOnOffToggler.click();
    }

    public int CountAudioResources(){
        return DeviceAudioChannelsList.size();
    }

    public String GetAddedAudioResources(int index){
        return DeviceAudioChannelsList.get(index).getText();
    }

    public void SwitchVisibleToOff(){
        if(VisibilitySwitchIsOn()){
            visibleOnOffToggler.click();
        }
    }

    public boolean VisibilitySwitchIsOn(){
        boolean flag = false;
        if(visibleOnOffToggler.getAttribute("aria-checked").equals("true")) flag = true;
        return flag;
    }

    public void ClickOnVisibilitySwitch(){
        waitUntilElementIsClickable(visibleOnOffToggler);
        visibleOnOffToggler.click();
    }

    public void OpenDeviceWebPage(WebElement device){
       openDeviceWebPageRef.click();
    }

    public void PressAudioResourcesButton() throws InterruptedException {
        waitUntilIsLoadedCustomTime(addAudioResourcesButton);
        addAudioResourcesButton.click();
        waitUntilElementIsLoaded(ApplyAddAudioChannelsButton);
    }

    public void SelectRandomAudioChannel(int index) throws InterruptedException {
        AudioChannelsList.get(index).click();
    }

    public int GetAudioSizeInDialog(){
        return AudioChannelsList.size();
    }


    public void MultipleSelectAudioChannels() throws InterruptedException {
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(AudioChannelsList.get(0)));
        int max= VAudioChannelsList.size()-1;
        VAudioChannelsList.get(0).click();
        Thread.sleep(2000);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",VAudioChannelsList.get(max));
        VAudioChannelsList.get(max).click();
    }

    public void ApplyAddAudioChannels(){
        waitUntilElementIsClickable(ApplyAddAudioChannelsButton);
        ApplyAddAudioChannelsButton.click();
       // new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='modal-backdrop fade in']")));
        WaitUntilDialogIsNotLocated();
    }

    public void CancelAddAudioChannels(){
        waitUntilIsLoadedCustomTime(CancelAddAudioChannelsButton);
        CancelAddAudioChannelsButton.click();
    }

    public void DeleteAudioChannels() throws InterruptedException {
        Actions builder = new Actions(driver);
        builder.moveToElement(DeviceAudioChannelsList.get(0)).click().build().perform();
        DeleteAudioChannelIcon.click();
    }

    public void DeleteAllAudioChannels() throws InterruptedException {
        for (int i = 0; i<CountAudioResources();){
            Actions builder = new Actions(driver);
            builder.moveToElement(DeviceAudioChannelsList.get(0)).click().build().perform();
            DeleteAudioChannelIcon.click();
            Thread.sleep(1000);
        }
    }

//    public void ShowPlayerOn(){
//        if(!ShowPlayerIsOn()) showPlayerOnOffToggler.click();
//        try{
//            new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-backdrop fade in']")));
//            driver.switchTo().window(driver.getWindowHandle());
//            closeNotificationButton.click();
//            Thread.sleep(1000);
//            WaitUntilDialogIsNotLocated();
//        }catch(Exception e){}
//    }

//    public boolean ShowPlayerIsOn(){
//        return verifyElementIsPresent(showPlayerVideoWindow);
//    }

    public void dropdown_Schedule() {
    	
    
    WebElement identifier = driver.findElement(By.xpath("//select[@ng-model = 'schedule.selectedSchedule']"));
    Select select = new Select(identifier);
    select.selectByVisibleText("Schedule31123");
    }
    public void Stream_Mode()
    {
    	 WebElement identifier = driver.findElement(By.xpath("//select[@combo-default-value='recordingController.streamsList']"));
         identifier.isDisplayed();
         Select select = new Select(identifier);
         select.selectByVisibleText("Stream3 - H264, 1920X1080, 30fps");
         
    }
    public WebElement FindDeviceByName(String text){
        WebElement element = null;
        try{
            element = driver.findElement(By.xpath("(//span/span[contains(text(), '" + text + "')] | //*[@value='" + text + "'])"));
        } catch (NoSuchElementException e) { }
        catch (ElementNotVisibleException e) { }

        return element;
    }

    public void GoToStreams(){
        waitUntilElementIsLoaded(streamsButton);
        waitUntilElementIsClickable(streamsButton);
        JavaScriptClick(streamsButton);//streamsButton.click();
    }

    public void GoToRecording() throws InterruptedException{
        waitUntilElementIsClickable(recordingButton);
        Thread.sleep(2000);
       JavaScriptClick(recordingButton);
       Thread.sleep(1000);//recordingButton.click();
    }

    public void GoToMasking(){
        waitUntilElementIsClickable(maskingButton);
        JavaScriptClick(maskingButton);
    }

    public void SelectSchedule(String schedule) throws InterruptedException {
        Thread.sleep(1000);
        waitUntilIsLoadedCustomTime(sheduleDropDownList);
        new Select(sheduleDropDownList).selectByVisibleText(schedule);
        driver.switchTo().window(driver.getWindowHandle());
    }
    
    public void SelectSchedule1(String name) throws InterruptedException {
        Thread.sleep(1000);
        waitUntilIsLoadedCustomTime(sheduleDropDownList1);
        new Select(sheduleDropDownList1).selectByVisibleText(name);
        driver.switchTo().window(driver.getWindowHandle());
    }

    public boolean CheckOptionIsExistOnScheduleDropDownList(String schedule){
        boolean flag =true;
        try{
            new Select(sheduleDropDownList).selectByVisibleText(schedule);
        }catch (Exception e){
            flag =  false;
        }return flag;
    }

    public void SelectRecordingModeByIndex(int index) throws InterruptedException {
        Thread.sleep(1000);
        waitUntilIsLoadedCustomTime(recordingModeDropDownList);
       new Select(recordingModeDropDownList).selectByIndex(index);
        
       // new Select(recordingModeDropDownList).selectByVisibleText("Continuous");
    }
    public void SelectRecordingModeByIndex1(int index) throws InterruptedException {
        Thread.sleep(1000);
        waitUntilIsLoadedCustomTime(recordingModeDropDownList);
       // new Select(recordingModeDropDownList).selectByIndex(index);
        
        new Select(recordingModeDropDownList).selectByVisibleText("Continuous");
    }


    public int CountOfOptionsInRecordingModeSelect(){
        List<WebElement> options = new Select(recordingModeDropDownList).getOptions();
        return options.size();
    }

    public WebElement GetOptionsByIndex(int index){
        List<WebElement> options = new Select(recordingModeDropDownList).getOptions();
        return options.get(index);
    }

    public String GetSelectedScheduleOption(){
        WebElement element = new Select(sheduleDropDownList).getFirstSelectedOption();
        return element.getText();
    }

    public String GetTextNumDayByIndex(int index){
        List<WebElement> options = new Select(numberOfDaysMonthlySelect).getOptions();
        return options.get(index).getText();
    }

    public String GetTextDayWeekByIndex(int index){
        List<WebElement> options = new Select(dayWeekMonthlySelect).getOptions();
        return options.get(index).getText();
    }


    public int GetCountOfOptionsInNumberDaysSelect(){
        List<WebElement> options = new Select(numberOfDaysMonthlySelect).getOptions();
        return options.size();
    }

    public int GetCountOfOptionsInDaysWeekSelect(){
        List<WebElement> options = new Select(dayWeekMonthlySelect).getOptions();
        return options.size();
    }

    public void SetOptionsByIndexNumberDays(int index){
        new Select(numberOfDaysMonthlySelect).selectByIndex(index);
    }

    public void SetOptionsByIndexDaysWeek(int index){
        new Select(dayWeekMonthlySelect).selectByIndex(index);
//        List<WebElement> options = new Select(dayWeekMonthlySelect).getOptions();
//        return options.get(index);
    }

    public String GetTextSelectedOptionNumberDays(){
        WebElement element = new Select(numberOfDaysMonthlySelect).getFirstSelectedOption();
        return element.getText();
    }

    public String GetTextSelectedOptionDayWeek(){
        WebElement element = new Select(dayWeekMonthlySelect).getFirstSelectedOption();
        return element.getText();
    }

    public String GetSelectedRecordingModeOption(){
        WebElement element = new Select(recordingModeDropDownList).getFirstSelectedOption();
        return element.getText();
    }

    public void InputIntoNameNewSchedule(String name){
        setElementText(nameSheduleField, name);
    }

    public void InputIntoDescriptionNewSchedule(String description){
        setElementText(descriptionSheduleTextArea, description);
    }

    public void SelectRepeat(String repeat){
        new Select(repeatDropDownList).selectByValue(repeat);
    }

    public int CountOfRepeatOptions(){
        return new Select(repeatDropDownList).getOptions().size();
    }

    public void SelectRepeatByIndex(int index){
        new Select(repeatDropDownList).selectByIndex(index);
    }

    public void SelectWeekly(){
        List<WebElement> days= driver.findElements(By.id("cbWeekly"));
//        new WebDriverWait(driver, 30).until(ExpectedConditions.numberOfElementsToBe(By.id("cbWeekly"), 7));
        int max = days.size()-1;
        int random = GetRandomDigit(0, max);
        days.get(0).click();
        days.get(random).click();
    }

    public void PressEndTime(int i){
        endTimeField.get(i).click();
    }

    public void PressStartTime(int i){
        startTimeField.get(i).click();
    }

    public String GetStartTime(int i){
        return startTimeField.get(i).getAttribute("value");
    }

    public String GetEndTime(int i){
       return endTimeField.get(i).getAttribute("value");
    }

    public void InputHours(String hours) throws InterruptedException {
        setElementText(timeHoursField, hours);
        Thread.sleep(1000);
    }

    public void InputMinutes(String minutes) throws InterruptedException {
        setElementText(timeMinutesField, minutes);
        Thread.sleep(1000);
    }

    public String GetHours(){
        return timeHoursField.getAttribute("value");
    }

    public String  GetMinutes(){
        return timeMinutesField.getAttribute("value");
    }

    public void IfScheduleIsNotExistAddIt() throws InterruptedException {
        if(!ScheduleIsExist()){
            waitUntilElementIsLoaded(plusSheduleButton);
            Thread.sleep(2000);
           JavaScriptClick(plusSheduleButton);
           Thread.sleep(2000);//plusSheduleButton.click();
            SelectSchedule("24/7");
        }
    }
    public void IfScheduleIsNotExistAddIt1() throws InterruptedException {
        if(!ScheduleIsExist()){
            waitUntilElementIsLoaded(plusSheduleButton1);
            Thread.sleep(2000);
            JavaScriptClick(plusSheduleButton1);
            Thread.sleep(4000);//plusSheduleButton//.click();
            SelectSchedule("24/7");
        }
    }

    public boolean ScheduleIsExist(){//
        return verifyElementIsPresent(sheduleDropDownList);
    }

    public void AddPeriod(){
        plusPeriodButton.click();
    }

    public String GetDurationHours(int i){
        return durationHoursField.get(i).getAttribute("value");
    }

    public String GetDurationMinutes(int i){
        return durationMinutesField.get(i).getAttribute("value");
    }

    public void InputDurationHours(int i, String hours){
        setElementText(durationHoursField.get(i), hours);
    }

    public void InputDurationMinutes(int i, String minutes){
        setElementText(durationMinutesField.get(i), minutes);
    }

    public void DurationHoursUp(int time){
        waitUntilElementIsLoaded(durationHoursUpSpinner);
        for(int i=0; i<time; i++){
            durationHoursUpSpinner.click();
        }
    }

    public void DurationHoursDown(int time){
        waitUntilElementIsLoaded(durationHoursDownSpinner);
        for(int i=0; i<time; i++){
            durationHoursDownSpinner.click();
        }
    }

    public void DurationMinutesUp(int time){
        waitUntilElementIsLoaded(durationMinutesUpSpinner);
        for(int i=0; i<time; i++){
            durationMinutesUpSpinner.click();
        }
    }

    public void DurationMinutesDown(int time){
        waitUntilElementIsLoaded(durationMinutesDownSpinner);
        for(int i=0; i<time; i++){
            durationMinutesDownSpinner.click();
        }
    }

    public void HoursUp(int time){
        waitUntilElementIsLoaded(hoursUpSpinner);
        for(int i=0; i<time; i++){
            hoursUpSpinner.click();
        }
    }

    public void HoursDown(int time){
        for(int i=0; i<time; i++){
            hoursDownSpinner.click();
        }
    }

    public void MinutesUp(int time){
        for(int i=0; i<time; i++){
            minutesUpSpinner.click();
        }
    }

    public void MinutesDown(int time){
        for(int i=0; i<time; i++){
            minutesDownSpinner.click();
        }
    }

    public int CountPeriod(){
        return PeriodNumber.size();
    }

    public void RemovePeriod() throws InterruptedException {
        Actions builder = new Actions(driver);
        System.out.println(PeriodNumber.get(2).getLocation().toString());
        builder.moveToElement(PeriodNumber.get(2)).build().perform();
        deletePeriodButton.click();
    }

    public void PresssaveNewSchedule() throws InterruptedException {
        saveNewSheduleButton.click();
        WaitUntilDialogIsNotLocated();
        Thread.sleep(1000);
    }

    public boolean RecordingToggleIsOn(){
        String cl = recordingOnOffToggler.getAttribute("class");
        if(cl.contains("md-checked")) return true;
        else return false;
    }
    public boolean RecordingToggleIsOn1(){
        String cl = recordingOnOffToggler1.getAttribute("class");
        if(cl.contains("md-checked")) return true;
        else return false;
    }

    public boolean LimitRetentionToggleIsOn() throws InterruptedException{
        waitUntilElementIsLoaded(limitRetentionOnOffToggler);
        Thread.sleep(2000);
        String cl = limitRetentionOnOffToggler.getAttribute("class");
        Thread.sleep(2000);
        if(cl.contains("md-checked")) return true;
        else return false;
    }
    public boolean LimitRetentionToggleIsOn1(){
        waitUntilElementIsLoaded(limitRetentionOnOffToggler1);
        String cl = limitRetentionOnOffToggler1.getAttribute("class");
        if(cl.contains("md-checked")) return true;
        else return false;
    }

    public void SwitchRecordingToggle(){
        waitUntilIsLoadedCustomTime(recordingOnOffToggler);
        recordingOnOffToggler.click();
    }

    public void SwitchLimitRetentionToggle(){
        waitUntilIsLoadedCustomTime(limitRetentionOnOffToggler);
        limitRetentionOnOffToggler.click();
    }

    public void InputIntoLimitRetentionField(String text){
        setElementText(limitRetentionInputField, text);
    }
    
    public void InputIntoLimitRetentionField1(String text){
        setElementText(limitRetentionInputField1, text);
    }


    public void ClickSpinnerUpLimitRetention(){
        limitRetentionUpSnipper.click();
    }

    public void ClickSpinnerDownLimitRetention(){
        limitRetentionDownSnipper.click();
    }
    public void ClickSpinnerUpLimitRetention1(){
        limitRetentionUpSnipper.click();
    }

    public void ClickSpinnerDownLimitRetention1(){
        limitRetentionDownSnipper.click();
    }


    public String GetLimitRetention(){
        return limitRetentionInputField.getAttribute("value");
    }

    public void InputIntoDaysFieldInNewScheduleWindow(String count){
        setElementText(countDaysField, count);
    }

    public String GetCountOfDays(){
        return  countDaysField.getAttribute("value");
    }

    public void ClickOnDaysUpSpinner(){
        daysUpSnipper.click();
    }

    public void ClickOnDaysDownSpinner(){
        daysDownSnipper.click();
    }

    public void SelectRadioDateButton(){
        dayOfMonthRadioButton.click();
    }

    //create new scedule // Yearly

    public void SelectMonthByIndex(int index){
        new Select(monthOfYearSelect).selectByIndex(index);
    }

    public int CountOfMonthOption(){
        List<WebElement> options= new Select(monthOfYearSelect).getOptions();
        return options.size();
    }

    public String GetTextFromSelectedOptions(){
        String option = new Select(monthOfYearSelect).getFirstSelectedOption().getText();
        return option;
    }

    public String GetTextFromOptionByIndex(int index){
        List<WebElement> options = new Select(monthOfYearSelect).getOptions();
        return options.get(index).getText();
    }

    public void SetCountOfDaysInMonth(String count){
        setElementText(countDaysInMonthField, count);
    }

    public void ClickDaysOfMonthUpSpinner(){
        monthUpSnipper.click();
    }

    public void ClickDaysOfMonthDownSpinner(){
        monthDownSnipper.click();
    }

    public String GetCountDaysOfMounth(){
        return countDaysInMonthField.getAttribute("value");
    }

    public String GetMaxCountOfDaysInMonth(){
        return countDaysInMonthField.getAttribute("aria-valuemax");
    }
//Yearly
    public void SelectNumberOfDayYearlyByIndex(int index){
        new Select(numberOfDaysYearlySelect).selectByIndex(index);
    }

    public int CountNumberDaysYearlyOption(){
        List<WebElement> options= new Select(numberOfDaysYearlySelect).getOptions();
        return options.size();
    }

    public String GetTextFromSelectedOptionsNumberOfdayYearly(){
        String option = new Select(numberOfDaysYearlySelect).getFirstSelectedOption().getText();
        return option;
    }

    public String GetTextFromOptionNumberOfdayYearlyByIndex(int index){
        List<WebElement> options = new Select(numberOfDaysYearlySelect).getOptions();
        return options.get(index).getText();
    }

    public void SelectDayOfWeekYearlyByIndex(int index){
        new Select(dayWeekYearlySelect).selectByIndex(index);
    }

    public int CountDaysOfWeekYearlyOption(){
        List<WebElement> options= new Select(dayWeekYearlySelect).getOptions();
        return options.size();
    }

    public String GetTextFromSelectedOptionsDayOfWeekYearly(){
        String option = new Select(dayWeekYearlySelect).getFirstSelectedOption().getText();
        return option;
    }

    public String GetTextFromOptionDayOfWeekYearlyByIndex(int index){
        List<WebElement> options = new Select(dayWeekYearlySelect).getOptions();
        return options.get(index).getText();
    }

    public void SelectMonthYearlyByIndex(int index){
        new Select(monthYearlySelect).selectByIndex(index);
    }

    public int CountMonthsYearlyOption(){
        List<WebElement> options= new Select(monthYearlySelect).getOptions();
        return options.size();
    }

    public String GetTextFromSelectedOptionsMonthYearly(){
        String option = new Select(monthYearlySelect).getFirstSelectedOption().getText();
        return option;
    }

    public String GetTextFromOptionMonthYearlyByIndex(int index){
        List<WebElement> options = new Select(monthYearlySelect).getOptions();
        return options.get(index).getText();
    }

    public void ClickOnTheStartDate(){
        startTimeDateField.get(0).click();
    }

    public void SelectNextDateInStartDate(){
        startTimeNextDateCell.click();
    }

    public void ClickOnEndDate() throws InterruptedException {
        endTimeDateField.get(0).click();
        Thread.sleep(1000);
    }

    public void SelectNextDateInEndDate(){
        endTimeNextDateCell.click();
    }

    public void CancelNewScheduleCreation(){
        cancelAddNewSheduleButton.click();
    }

    public void ClickYearlyRadioButton(){
        yearlyRadioButton.click();
    }

    public void SelectRecordingModeOption(String mode){
        new Select(recordingModeDropDownList).selectByVisibleText(mode);
    }
    public void SelectRecordingModeOption1(String mode){
        new Select(recordingModeDropDownList1).selectByVisibleText(mode);
    }

    public String GetSelectedRecModeOption(){
       String option = new Select(recordingModeDropDownList).getFirstSelectedOption().getAttribute("label");
       return option;
    }

    public void InputPreEventSeconds(String seconds){
        setElementText(preEventInputField, seconds);
    }

    public void ClickOnPreEventUpSpinner(){
        preEventUpSpinner.click();
    }

    public void ClickOnPreEventDownSpinner(){
        preEventDownSpinner.click();
    }

    public void InputPostEventSeconds(String seconds) throws InterruptedException{
    	Thread.sleep(1000);
        setElementText(postEventInputField, seconds);
    }
    public void InputPostEventSeconds1(String seconds){
        setElementText(postEventInputField1, seconds);
    }

    public void ClickOnPostEventUpSpinner(){
        postEventUpSpinner.click();
    }
    public void ClickOnPostEventUpSpinner1(){
        postEventUpSpinner1.click();
    }

    public void ClickOnPostEventDownSpinner1(){
        postEventDownSpinner1.click();
    }
    public void ClickOnPostEventDownSpinner(){
        postEventDownSpinner.click();
    }

    public void ClickOnMotionToggle(){
        JavaScriptClick(motionOnOffToggler);//motionOnOffToggler.click();
    }
    public void ClickOnMotionToggle1(){
        JavaScriptClick(motionOnOffToggler1);//motionOnOffToggler.click();
    }
    public String GetPreEventText(){
      return preEventInputField.getAttribute("value");
    }

    public String GetPostEventText(){
        return postEventInputField.getAttribute("value");
    }
    public String GetPostEventText1(){
        return postEventInputField1.getAttribute("value");
    }

    public String MotionIsOn(){
       return motionOnOffToggler.getAttribute("aria-checked");
    }
    public String MotionIsOn1(){
        return motionOnOffToggler1.getAttribute("aria-checked");
     }
    public void PressOnSpecifyEventsButton(){
//        waitUntilElementIsLoaded(specifyEventsButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].checked = true;", specifyEventsButton);

        scroll(specifyEventsButton);
       JavaScriptClick(specifyEventsButton);// specifyEventsButton.click();
        driver.switchTo().window(driver.getWindowHandle());
    }
    public void PressOnSpecifyEventsButton2(){
//      waitUntilElementIsLoaded(specifyEventsButton);
      ((JavascriptExecutor)driver).executeScript("arguments[0].checked = true;", specifyEventsButton);

      scroll(specifyEventsButton2);
     JavaScriptClick(specifyEventsButton2);// specifyEventsButton.click();
      driver.switchTo().window(driver.getWindowHandle());
  }
    public void PressOnSpecifyEventsButton1() throws InterruptedException{
//   
    	 ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
    	 Thread.sleep(1000);
    	 
    	  // Click on link to open in new tab
    	 WebElement ele =	driver.findElement(By.xpath("//*[@id=\"vms-screen-recording-id\"]/ng-form/div[5]/div/div[2]/div[4]/div[5]/div/button/span"));
 		JavascriptExecutor executor = (JavascriptExecutor)driver;
 		executor.executeScript("arguments[0].click();", ele);
    	// driver.findElement(By.xpath("//*[@id=\"vms-screen-recording-id\"]/ng-form/div[5]/div/div[2]/div[4]/div[5]/div/button/span")).click();
    	 //Thread.sleep(2000);
    	 
    	 // Switch newly open Tab
    	 driver.switchTo().window(tabs.get(1));
    	 
    	 // Perform some operation on Newly open tab
    	 // Close newly open tab after performing some operations.
    	// driver.close();
    	 
    	 // Switch to old(Parent) tab.
    	 driver.switchTo().window(tabs.get(0));
  }

    public void SelectStream(int index){
        new Select(streamDropDownList).selectByIndex(index);
    }

    public String GetSelectedStream(){
      return new Select(streamDropDownList).getFirstSelectedOption().getText();
    }

    public String GetEventStream(){
       return eventStream.getText();
    }

    public int GetSelectedEventsSize(){
        return selectedEventsList.size();
    }

    public String GetSelectedEventsText(int index){
        return selectedEventsList.get(index).getText();
    }

    public void ClickOnDeleteEventIcon(int index) throws InterruptedException {
        Actions build = new Actions(driver); // heare you state ActionBuider
        build.moveToElement(selectedEventsList.get(index)).build().perform();
        waitUntilElementIsLoaded(deleteIconSelectedEventsList.get(index));// / Here you perform hover mouse over the needed elemnt to triger the visibility of the hidden
        Thread.sleep(1000);
        deleteIconSelectedEventsList.get(index).click();
        Thread.sleep(1000);
    }

    //streams
    public void SelectCameraStreamingOptionByIndex(int index){
        new Select(cameraStreamingDropDownList).selectByIndex(index);
    }

    public void SelectCameraStreamingOptionByText(String text){
        new Select(cameraStreamingDropDownList).selectByVisibleText(text);
    }

    public int GetCameraStreamingSize(){
        return new Select(cameraStreamingDropDownList).getOptions().size();
    }

    public String GetCamersStreamingText(){
        return new Select(cameraStreamingDropDownList).getFirstSelectedOption().getText();
    }

    public String GetCamersStreamingTextByIndex(int index){
        return new Select(cameraStreamingDropDownList).getOptions().get(index).getText();
    }

    public int GetResolutionSize(int index){
       WebElement list = resolutionDropDownList.get(index);
       return new Select(list).getOptions().size();
    }

    public int GetCountOfStreams(){
        return qualityField.size();
    }

    public int GetCountOfFrameRate(){
        return frameRateSelect.size();
    }

    public int GetCountOfKeyFrameInterval(){
        return keyFrameInterval.size();
    }

    public void SelectResolutionByIndex(int index, int indexOpt){
        WebElement list = resolutionDropDownList.get(index);
        new Select(list).selectByIndex(indexOpt);
    }

    public void SelectResolutionByText(int index, String text){
        WebElement list = resolutionDropDownList.get(index);
        new Select(list).selectByVisibleText(text);
    }

    public String GetResolutionText(int index){
        WebElement list = resolutionDropDownList.get(index);
        return new Select(list).getFirstSelectedOption().getText();
    }

    public String GetResolutionTextByIndex(int index, int indexOpt){
        WebElement list = resolutionDropDownList.get(index);
        return new Select(list).getOptions().get(indexOpt).getText();
    }

    public int GetFrameRateOptionsSize(int index){
        WebElement list = frameRateSelect.get(index);
        return new Select(list).getOptions().size();
    }

    public void SelectFrameRateByIndex(int index, int indexOpt){
        WebElement list = frameRateSelect.get(index);
        new Select(list).selectByIndex(indexOpt);
    }

    public void SelectFrameRateByText(int index, String text){
        WebElement list = frameRateSelect.get(index);
        new Select(list).selectByVisibleText(text);
    }
    public void SelectFrameRateByText1(int index, String text){
        WebElement list = frameRateSelect2.get(index);
        new Select(list).selectByVisibleText(text);
    }
    public void SelectDropdownToFrame()
    {
    	WebElement ele = driver.findElement(By.xpath("//ng-form[@name='tabStream.form']/div[contains(@class,'configuration-ditails-form')]//span[contains(text(), 'Frame Rate')]/../..//select"));
    	Select sel = new Select(ele);
    	sel.selectByVisibleText("25");
    	//sel.selectByIndex(3);
    	//sel.selectByValue("23");
    }
    public void EnterResolutionField() throws AWTException, InterruptedException
    {
    	//WebElement ele = driver.findElement(By.xpath("//select[@combo-default-value = 'tabStream.selectedCompression.resolutionsAvailable']"));
    	//ele.click();
    	//ele.sendKeys(Keys.ARROW_DOWN); 
    	//ele.sendKeys(Keys.ENTER);
    	//Select sel = new Select(ele);
    	//sel.selectByVisibleText("1280X1024");
    	/*WebDriverWait wait1 = new WebDriverWait(driver, 20);
    	WebElement element1 = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@combo-default-value = 'tabStream.selectedCompression.resolutionsAvailable']")));
    	element1.click();*/
   driver.findElement(By.xpath("//select[@combo-default-value = 'tabStream.selectedCompression.resolutionsAvailable']"));
    	Robot r = new Robot();
    	Thread.sleep(1000);
    r.keyPress(KeyEvent.VK_DOWN);
    Thread.sleep(1000);
    	//Select sel = new Select(ele);
    	//sel.selectByVisibleText("800X450");
    //	sel.selectByValue("object:2009");
    	//Thread.sleep(1000);
    }
    
    public void SelectDropdownToFrame1()
    {
    	WebElement ele = driver.findElement(By.xpath("//*[@id=\"vms-screen-lock-id\"]/div/div/div/div/section/div/div[1]/div[3]/div[2]/ng-form/div[5]/div/div[1]"));
    	Select sel = new Select(ele);
    	sel.selectByVisibleText("number:23");
    	//sel.selectByIndex(3);
    	//sel.selectByValue("23");
    }

    public String GetFrameRateText(int index){
        WebElement list = frameRateSelect.get(index);
        return new Select(list).getFirstSelectedOption().getText();
    }

    public String GetFrameRateTextByIndex(int index, int indexOpt){
        WebElement list = frameRateSelect.get(index);
        return new Select(list).getOptions().get(indexOpt).getText();
    }

    public String GetKeyFrameIntervalText(int index){
        return keyFrameIntervalField.get(index).getAttribute("value");
    }

    public void InputKeyFrameIntervalValue(int index, String value ){
        setElementText(keyFrameIntervalField.get(index), value);
    }

    public void ClickKeyFrameIntervalUpSnipper(int index){
        keyFrameIntervalUpSpinner.get(index).click();
    }

    public void ClickKeyFrameIntervalDownSnipper(int index){
        keyFrameIntervalDownSpinner.get(index).click();
    }

    public String GetMaxKeyFrameInterval(int index){
        String interval = keyFrameInterval.get(index).getText();
        int firstInd = interval.lastIndexOf("-")+2;
        int lastInd = interval.lastIndexOf("]");
        String max = interval.substring(firstInd,lastInd);
        return max;
    }

    public String GetMinKeyFrameInterval(int index){
        String interval = keyFrameInterval.get(index).getText();
        int ind = interval.indexOf("-");
        String min = interval.substring(1,ind-1);
        return min;
    }

    public String GetBitrateLimitText(int index){
        return bitrateLimitField.get(index).getAttribute("value");
    }

    public void InputBitrateLimitValue(int index, String value ) throws InterruptedException {
        setElementText(bitrateLimitField.get(index), value);
        Thread.sleep(1000);
    }

    public void ClickBitrateLimitUpSnipper(int index){
        bitrateLimitUpSpinner.get(index).click();
    }

    public void ClickBitrateLimitDownSnipper(int index){
        bitrateLimitDownSpinner.get(index).click();
    }

    public String GetMaxBitrateLimit(int index){
        String interval = bitrateLimitInterval.get(index).getText();
        int firstInd = interval.lastIndexOf("-")+2;
        int lastInd = interval.lastIndexOf("]");
        String max = interval.substring(firstInd,lastInd);
        return max;
    }

    public String GetMinBitrateLimit(int index){
        String interval = bitrateLimitInterval.get(index).getText();
        int ind = interval.indexOf("-")-1;
        String min = interval.substring(1,ind);
        return min;
    }

    public void ClickOnInterval(int index){
        waitUntilElementIsLoaded(bitrateLimitInterval.get(index));
        bitrateLimitInterval.get(index).click();
    }

    public String GetQualityText(int index){
        scroll(qualityField.get(index));
        return qualityField.get(index).getAttribute("value");
    }

    public void InputQualityValue(int index, String value ){
        setElementText(qualityField.get(index), value);
    }

    public void ClickQualityUpSnipper(int index){
        qualityUpSpinner.get(index).click();
    }

    public void ClickQualityDownSnipper(int index){
        qualityDownSpinner.get(index).click();
    }

    public String GetMaxQuality(int index){
        String interval = qualityInterval.get(index).getText();
        int firstInd = interval.lastIndexOf("-")+2;
        int lastInd = interval.lastIndexOf("]");
        String max = interval.substring(firstInd,lastInd);
        return max;
    }

    public String GetMinQuality(int index){
        String interval = qualityInterval.get(index).getText();
        int ind = interval.indexOf("-");
        String min = interval.substring(1,ind-1);
        return min;
    }

    public float GetFramePeriod(int index){
       String per =  keyFrameInterval.get(index).getText();
       int ind = per.indexOf("y")+2;
       int indL = per.lastIndexOf("S")-1;
       String p=per.substring(ind, indL);
       return Float.parseFloat(p);
    }

    public void GoToPictureSettings(){
        waitUntilElementIsLoaded(pictureSettingButton);
        try{
            pictureSettingButton.click();
        }catch(Exception e){}
    }

    public void WaitUntilPictureSettingsIsExist(){
        try{
            new WebDriverWait(driver, 5).until(ExpectedConditions.attributeContains(pictureSettingButton , "aria-hidden", "false"));
        }catch(Exception e){
        }
    }

    public void WaitUntillVisualSettingsIsExist(){
        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(brightnessField));
        } catch (Exception e){}
    }

    public void WaitUntillDayNightIsExist(){
        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(cutFilterModeSelect));
        } catch (Exception e){}
    }

    public boolean PictureSettingIsExist(){
        boolean flag = false;
        String aria = pictureSettingButton.getAttribute("aria-hidden");
        if(aria.equals("false")) flag = true;
        return flag;
    }

    public boolean DeviceIsOffline(){
        boolean flag = false;
        try{
            WebElement element = driver.findElement(By.xpath("//div[contains(text(), 'Device is Offline')]"));
            String style = element.getAttribute("style");
            if (style.equals("display: block;")) flag =  true;
        }catch(Exception e){}
        return flag;
    }

    public boolean VisualSettingsIsExist(){
        return verifyElementIsPresent(brightnessField);
    }

    public boolean ContrastSettingsIsExist(){
        return verifyElementIsPresent(contrastField);
    }

    public boolean SharpnessSettingsIsExist(){
        return verifyElementIsPresent(sharpnessField);
    }

    public String GetBrightnessText(){
        return brightnessField.getAttribute("value");
    }

    public void InputBrightnessValue(String value ){
        setElementText(brightnessField, value);
    }

    public void ClickBrightnessUpSnipper(){
        brightnessUPSpinner.click();
    }

    public void ClickBrightnessDownSnipper(){
        brightnessDownSpinner.click();
    }

    public String GetBrightnessMaxValue(){
        return brightnessMaxVal.getText();
    }

    public String GetBrightnessMinValue(){
        return brightnessMinVal.getText();
    }

    public String GetContrastText(){

        return contrastField.getAttribute("value");
    }

    public void InputContrastValue(String value ){
        setElementText(contrastField, value);
    }

    public void ClickContrastUpSnipper(){
        contrastUPSpinner.click();
    }

    public void ClickContrastDownSnipper(){
        contrastDownSpinner.click();
    }

    public String GetContrastMaxValue(){
        return contrastMaxVal.getText();
    }

    public String GetContrastMinValue(){
        return contrastMinVal.getText();
    }

    public String GetColorSaturationText(){
        return colorSaturationField.getAttribute("value");
    }

    public void InputColorSaturationValue(String value ){
        setElementText(colorSaturationField, value);
    }

    public void ClickColorSaturationUpSnipper(){
        colorSaturationUPSpinner.click();
    }

    public void ClickColorSaturationDownSnipper(){
        colorSaturationDownSpinner.click();
    }

    public String GetColorSaturationMaxValue(){
        return colorSaturationMaxVal.getText();
    }

    public String GetColorSaturationMinValue(){
        return colorSaturationMinVal.getText();
    }

    public String GetSharpnessText(){
        return sharpnessField.getAttribute("value");
    }

    public void InputSharpnessValue(String value ){
        setElementText(sharpnessField, value);
    }

    public void ClickSharpnessUpSnipper(){
        sharpnessUPSpinner.click();
    }

    public void ClickSharpnessDownSnipper(){
        sharpnessDownSpinner.click();
    }

    public String GetSharpnessMaxValue(){
        return sharpnessMaxVal.getText();
    }

    public String GetSharpnessMinValue(){
        return sharpnessMinVal.getText();
    }

    public boolean DayNightIsExist(){
        return verifyElementIsPresent(cutFilterModeSelect);
    }

    public void SelectFilterModeOptionByIndex(int index){
        new Select(cutFilterModeSelect).selectByIndex(index);
    }

    public void SelectFilterModeOptionByText(String text){
        new Select(cutFilterModeSelect).selectByVisibleText(text);
    }


    public int GetFilterModeSize(){
        return new Select(cutFilterModeSelect).getOptions().size();
    }

    public String GetFilterModeText(){
        return new Select(cutFilterModeSelect).getFirstSelectedOption().getText();
    }

    public String GetFilterModeTextByIndex(int index){
        return new Select(cutFilterModeSelect).getOptions().get(index).getText();
    }

    public void SelectExposureModeOption(String text){
        new Select(exposureModeSelect).selectByVisibleText(text);
    }

    public boolean ExposureModeSelectIsEnable(){
        boolean flag = false;
        String aria = exposureModeSelect.getAttribute("aria-disabled");
        if(aria.equals("false")) flag = true;
        return flag;
    }

    public boolean ExposureTimeFieldIsExist(){
        return verifyElementIsPresent(exposureTimeField);
    }

    public String GetExposureModeText(){
        return new Select(exposureModeSelect).getFirstSelectedOption().getText();
    }

    public boolean ExposureIsExist(){
        return verifyElementIsPresent(exposureModeSelect);
    }

    public boolean ExposureTimeSliderIsEnabled(){
        try{
            if(exposureTimeSlider.getAttribute("class").contains("disabled")) return false;
            else return true;
        }catch(org.openqa.selenium.NoSuchElementException e){
            return false;
        }
    }

    public boolean ExposureTimeFieldIsEnabled(){
        return exposureTimeField.isEnabled();
    }

    public boolean ExposureGainSliderIsEnabled(){
        if(exposureGainSlider.getAttribute("class").contains("disabled")) return false;
        else return true;
    }

    public boolean ExposureGainFieldIsEnabled(){
        return exposureGainField.isEnabled();
    }

    public boolean ExposureGainFieldIsExist(){
        return verifyElementIsPresent(exposureGainField);
    }

    public void WaitUntillExposureIsExist(){
        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(exposureModeSelect));
        } catch (Exception e){}
    }

    public String GetExposureTimeText(){
        return exposureTimeField.getAttribute("value");
    }

    public void InputExposureTimeValue(String value ){
        setElementText(exposureTimeField, value);
    }

    public void ClickExposureTimeUpSnipper(){
        exposureTimeUPSpinner.click();
    }

    public void ClickExposureTimeDownSnipper(){
        exposureTimeDownSpinner.click();
    }

    public String GetExposureTimeMaxValue(){
        return exposureTimeMaxVal.getText();
    }

    public String GetExposureTimeMinValue(){
        return exposureTimeMinVal.getText();
    }

    public String GetGainText(){
        return exposureGainField.getAttribute("value");
    }

    public boolean GainIsExist(){
        return verifyElementIsPresent(exposureGainField);
    }

    public void InputGainValue(String value ){
        setElementText(exposureGainField, value);
    }

    public void ClickGainUpSnipper(){
        exposureGainUPSpinner.click();
    }

    public void ClickGainDownSnipper(){
        exposureGainDownSpinner.click();
    }

    public String GetGainMaxValue(){
        return exposureGainMaxVal.getText();
    }

    public String GetGainMinValue(){
        return exposureGainMinVal.getText();
    }

    public boolean BacklightCompensationIsExist(){
        return verifyElementIsPresent(backlightCompensationModeSelect);
    }

    public void WaitUntillBacklightCompensationIsExist(){
        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(backlightCompensationModeSelect));
        } catch (Exception e){}
    }

    public void SelectBacklightCompensationModeOption(String text){
        new Select(backlightCompensationModeSelect).selectByVisibleText(text);
    }

    public String GetBacklightCompensationModeText(){
        return new Select(backlightCompensationModeSelect).getFirstSelectedOption().getText();
    }

    public boolean ApplyToButtonIsEnabled(){
        boolean flag = false;
        String enabled = applyToButton.getAttribute("aria-disabled");
        if (enabled.equals("false")) flag = true;
        return flag;
    }

    public void ClickOnApplyToButton(){
        waitUntilElementIsLoaded(applyToButton);
        applyToButton.click();
    }

    public int GetAudioChannelsSizeInApplyToDialog(){
        return videoChannelsInApplyToDialog.size();
    }

    public void ClickOnVideoChanelsInApplyChannelsDialogByIndex(int index){
        videoChannelsInApplyToDialog.get(index).click();
    }

    public String GetAudioChannelTextInApplyChannelsDialogByIndex(int index){
        return videoChannelsInApplyToDialog.get(index).getText();
    }

    public void ClickOnApplyButtonInApplyToDialog(){
        applyButtonInApplyToDialog.click();
    }

    public void ClickOnCloseButtonInApplyToDialog(){
        closeButtonInApplyToDialog.click();
    }

    public boolean PictureSettingsCheckboxIsSelected(){
        waitUntilElementIsLoaded(pictureSettingsCheckbox);
        boolean flag = false;
       String checked=  pictureSettingsCheckbox.getAttribute("aria-checked");
       if(checked.equals("true")) flag = true;
       return flag;
    }

    public boolean StreamsAndRecordingCheckboxIsSelected(){
        waitUntilElementIsLoaded(streamsAndRecordingCheckbox);
        boolean flag = false;
        String checked=  streamsAndRecordingCheckbox.getAttribute("aria-checked");
        if(checked.equals("true")) flag = true;
        return flag;
    }

    public void ClickOnPictureSettingsCheckbox(){
        waitUntilElementIsLoaded(pictureSettingsCheckbox);
        pictureSettingsCheckbox.click();
    }

    public void ClickOnStreamsAndRecordingCheckbox(){
        waitUntilElementIsLoaded(streamsAndRecordingCheckbox);
        streamsAndRecordingCheckbox.click();
    }

    public void CreateNewMask() throws InterruptedException, AWTException {
//        WebElement fullScreenButon= driver.findElement(By.xpath("//div[@class='main-header-item-container fullscreen-container']/div"));
//        fullScreenButon.click();

        Point p1 = videoScreen.getLocation();
        int xcord = p1.getX();
        int ycord = p1.getY();

        Dimension size = videoScreen.getSize();
        int wight= size.getWidth();
        int hight=size.getHeight();

        int maxXCoord = xcord +  wight;
        int maxYCoord = ycord + hight;

        int randomXCoord = GetRandomDigit(xcord+1, maxXCoord-1);
        int randomYCoord = GetRandomDigit(ycord+1, maxYCoord-1);

        int randomXCoord2 = GetRandomDigit(xcord+1, maxXCoord-1);
        int randomYCoord2 = GetRandomDigit(ycord+1, maxYCoord-1);

        Robot robot = new Robot();
        robot.mouseMove(randomXCoord, randomYCoord);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseMove(randomXCoord2, randomYCoord2);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    public int GetMaskListSize(){
        return  maskList.size();
    }
    public void ChannelPropertiesButton()
    {
    	WebElement ele =	driver.findElement(By.xpath("//*[@id=\"channel_properties\"]/div"));
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
    	executor.executeScript("arguments[0].click();", ele);
    }
    public void clickOnVideochannel()
    {
    	WebElement ele =	driver.findElement(By.xpath("//*[@id=\"avms-configuration-container\"]/div/div/ui-view/div/div/div[2]/div[2]/div[1]"));
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
    	executor.executeScript("arguments[0].click();", ele);
    }
    public void ClickOnRecording()
    {
    	WebElement ele =	driver.findElement(By.xpath("//*[@id=\"recording\"]/div"));
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
    	executor.executeScript("arguments[0].click();", ele);
    }
    public void LimitRetention()
    {
    	WebElement ele =	driver.findElement(By.xpath("//ng-form/div[@class='configuration-ditails-form flex-between limit_retention']//md-switch"));
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
    	executor.executeScript("arguments[0].click();", ele);
    }
    public void LimitRetentionOFF()
    {
    	WebElement ele =	driver.findElement(By.xpath("//*[@id=\"vms-screen-recording-id\"]/ng-form/div[2]/div/div/md-switch/div[1]"));
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
    	executor.executeScript("arguments[0].click();", ele);
    }
    public void LimitRetentionOn()
    {
    	WebElement ele =	driver.findElement(By.xpath("//*[@id=\"vms-screen-recording-id\"]/ng-form/div[2]/div/div[1]/md-switch/div[1]"));
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
    	executor.executeScript("arguments[0].click();", ele);
    }
    public void RecordingON()
    {
    	WebElement ele =	driver.findElement(By.xpath("//*[@id=\"vms-screen-recording-id\"]/ng-form/div[1]/div/md-switch/div[1]/div[1]"));
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
    	executor.executeScript("arguments[0].click();", ele);
    }
    
    public void DOnotSave()
    {
    	WebElement ele =	driver.findElement(By.xpath("//*[@id=\"vms-configurationScreen\"]/div[2]/button[2]"));
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
    	executor.executeScript("arguments[0].click();", ele);
    }
    public void PressSave()
    {
    	WebElement ele =	driver.findElement(By.xpath("//*[@id=\"vms-configurationScreen\"]/div[2]/button[1]"));
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
    	executor.executeScript("arguments[0].click();", ele);
    }
    public void PressCancel()
    {
    	WebElement ele =	driver.findElement(By.xpath("//*[@id=\"vms-configurationScreen\"]/div[2]/button[3]"));
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
    	executor.executeScript("arguments[0].click();", ele);
    }
    public void PressSaveButton()
    {
    	WebElement ele =	driver.findElement(By.xpath("//*[@id=\"avms-configuration-container\"]/div/div/ui-view/div/div/div[2]/div/div[3]/button[6]"));
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
    	executor.executeScript("arguments[0].click();", ele);
    }
    public void ChannelPropertiesSaveButton()
    {
    	WebElement ele =	driver.findElement(By.xpath("//*[@id=\"avms-configuration-container\"]/div/div/ui-view/div/div/div[2]/div/div[3]/button[1]"));
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
    	executor.executeScript("arguments[0].click();", ele);
    }
    public void ChannelPropertiesCancelButton()
    {
    	WebElement ele =	driver.findElement(By.xpath("//*[@id=\"avms-configuration-container\"]/div/div/ui-view/div/div/div[2]/div/div[3]/button[2]"));
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
    	executor.executeScript("arguments[0].click();", ele);
    }
    public void EnterNumericID()
    {
    	WebElement ele =	driver.findElement(By.xpath("//*[@id=\"inputNumericId\"]"));
    	ele.sendKeys("3");
    	//JavascriptExecutor executor = (JavascriptExecutor)driver;
    	//executor.executeScript("arguments[0].click();", ele);
    }
    public void EnterNumericID1()
    {
    	WebElement ele =	driver.findElement(By.xpath("//*[@id=\"inputNumericId\"]"));
    	ele.sendKeys("3");
    	ele.clear();
    	//JavascriptExecutor executor = (JavascriptExecutor)driver;
    	//executor.executeScript("arguments[0].click();", ele);
    }
    public void PressSaveButton1()
    {
    	WebElement ele =	driver.findElement(By.xpath("//*[@id=\"vms-configurationScreen\"]/div[2]/button[1]"));
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
    	executor.executeScript("arguments[0].click();", ele);
    }
    public void Select_ContineousMode()
    {
    	WebElement identifier = driver.findElement(By.xpath("//*[@id=\"vms-screen-recording-id\"]/ng-form/div[5]/div/div[2]/div[2]/div/select"));
    	Select select = new Select(identifier);
    	select.selectByVisibleText("Continuous & Event");
    }
    public void Schedule_NewSchedule()
    {
    	WebElement identifier = driver.findElement(By.xpath("//*[@id=\"vms-screen-recording-id\"]/ng-form/div[5]/div/div[2]/div[2]/div/select"));
    	Select select = new Select(identifier);
    	select.selectByVisibleText("New Schedule");
    }
    public void Select_Stream()
    {
    	WebElement identifier = driver.findElement(By.xpath("//*[@id=\"vms-screen-recording-id\"]/ng-form/div[5]/div/div[2]/div[3]/div/select-wrapper/span[2]/select"));
    	Select select = new Select(identifier);
    	select.selectByVisibleText("Stream1 - H264, 1280X720, 15fps");
    }
    public void Select_Stream1()
    {
    	WebElement identifier = driver.findElement(By.xpath("//*[@id=\"vms-screen-recording-id\"]/ng-form/div[5]/div/div[2]/div[3]/div/select-wrapper/span[2]/select"));
    	Select select = new Select(identifier);
    	select.selectByVisibleText("Stream2 - H264, 720X480, 5fps");
    }
    
    public void dragAndDrop(WebElement sourceElement1, WebElement destinationElement1) {
        try {
            if (sourceElement1.isDisplayed() && destinationElement1.isDisplayed()) {
                Actions action = new Actions(driver);
                
                action.dragAndDrop(sourceElement1, destinationElement1).build().perform();
            } else {
                System.out.println("Element was not displayed to drag");
            }
        } catch (StaleElementReferenceException e) {
            System.out.println("Element with " + sourceElement1 + "or" + destinationElement1 + "is not attached to the page document "
                    + e.getStackTrace());
        } catch (NoSuchElementException e) {
            System.out.println("Element " + sourceElement1 + "or" + destinationElement1 + " was not found in DOM "+ e.getStackTrace());
        } catch (Exception e) {
            System.out.println("Error occurred while performing drag and drop operation "+ e.getStackTrace());
        }
    }
    public void dragAndDrop1(WebElement sourceElement, WebElement destinationElement)
    {
    	WebElement sourceElement1 = driver.findElement(By.xpath("//*[@id=\"vms-treeview-868bd07f-9517-4ff4-8c4d-a7f2ade929ed\"]"));
        WebElement destinationElement1 = driver.findElement(By.xpath("//*[@id=\"2818e555-b3d3-452e-b525-7de2202b9f7a\"]"));
        (new Actions(driver)).dragAndDrop(sourceElement1, destinationElement1).perform();
    }
    public void SelectSchedule_Garbage()
    {
    	WebElement identifier = driver.findElement(By.xpath("//select[@ng-model = 'schedule.selectedSchedule']"));
        Select select = new Select(identifier);
        select.selectByVisibleText("Schedule43Garbage");

}
    public void SelectSchedule()
    {
    	WebElement identifier = driver.findElement(By.xpath("//select[@ng-model = 'schedule.selectedSchedule']"));
        Select select = new Select(identifier);
        select.selectByVisibleText("New Schedule");

}
    public void DeSelect_FromDropdon()
    {
    	//WebElement ele = driver.findElement(By.xpath("//*[@id=\"vms-screen-recording-id\"]/ng-form/div[5]/div/div[2]/div[1]/div/select"));
    	//Select sel = new Select(ele);
    	//sel.deselectByVisibleText("Schedule43Garbage");
    	
    	List<String> strList= new ArrayList<String>();

    	//Then Add or Remove can be called on such List

    	strList.add("100");
    	strList.remove("Schedule43Garbage");
    }
    public void MotionOn()
    {
    	WebElement ele =	driver.findElement(By.xpath("//*[@id=\"vms-screen-recording-id\"]/ng-form/div[5]/div/div[2]/div[4]/div[4]/div/md-switch/div[1]"));
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
    	executor.executeScript("arguments[0].click();", ele);	
    }
    public void CancelUnSavedChanges2()
    {
    	WebElement ele =	driver.findElement(By.xpath("//*[@id=\"vms-configurationScreen\"]/div[2]/button[3]"));
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
    	executor.executeScript("arguments[0].click();", ele);	
    }
    public void TakeScreenshotButton()
    {
    	WebElement ele =	driver.findElement(By.xpath("//*[@id=\"avms-configuration-container\"]/div/div/ui-view/div/div/div[2]/div/div[2]/div/div/div[1]/div/div/div[2]/div/div[1]/button/span/span[1]"));
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
    	executor.executeScript("arguments[0].click();", ele);	
    }
    //Player Related Xpath
    
    public void EnterUsername()
    {
 	   driver.findElement(By.xpath("//*[@id=\"txtLogin\"]")).sendKeys("ADMIN");
    }
 public void EnterPWD()
 {
 	driver.findElement(By.xpath("//*[@id=\"txtPassword\"]")).sendKeys("1234");
 }
 public void SignInButton()
 {
 	driver.findElement(By.xpath("//*[@id=\"submitBtn\"]")).click();
 }
 public void ClickOnConfig()
 {
 	WebElement ele =	driver.findElement(By.xpath("//span[@class='vms-player-btns']/span[@title='Configure']"));
 	JavascriptExecutor executor = (JavascriptExecutor)driver;
 	executor.executeScript("arguments[0].click();", ele);
 }
 public void ClickOnCloseInViconPlayerDownloadDialog() throws InterruptedException {
     driver.switchTo().window(driver.getWindowHandle());
     waitUntilElementIsLoaded(closeNotificationButton);
     JavaScriptClick(closeNotificationButton);
     Thread.sleep(1000);
     WaitUntilDialogIsNotLocated();
 }
 public void ClickOnCloseButton()
 {
	 WebElement ele =	driver.findElement(By.xpath("//*[@id=\"e38ac08a-b0e8-4365-bff9-c26028557b7a\"]/div[1]/div[2]/span"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele);
 }

    public void ClickOnConfigure()
    {
    	 WebElement ele =	driver.findElement(By.xpath("//span[@class='vms-player-btns']/span[@title='Configure']"));
 		JavascriptExecutor executor = (JavascriptExecutor)driver;
 		executor.executeScript("arguments[0].click();", ele);
    }
    public void DigitalZoomButton()
    {
    	WebElement ele =	driver.findElement(By.xpath("//span[@class='vms-player-btns']/span[@title='Digital Zoom']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele);
    }
    public void ClickOnExportButton()
    {
    	WebElement ele =	driver.findElement(By.xpath("//span[@class='vms-player-btns']/span[@title='Export']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele);
    }
    public void VerifyElementIsPresentOrnot()
    {
    	if(driver.findElements(By.xpath("//span[@class='vms-player-btns']/span[@title='Configure']")).size() != 0){
			System.out.println("Element is Present");
			}else{
				System.out.println("Element is Absent");
				}
    }
    public void VerifyElementIsPresentOrnot_ChannelPro()
    {
    	if(driver.findElements(By.xpath("//*[@id=\"vms-configurationScreen\"]/div[2]/button[1]")).size() != 0){
			System.out.println("Element is Present");
			}else{
				System.out.println("Element is Absent");
				}
    }
    public void VerifyElementIsPresentOrnot1()
    {
    	if(driver.findElements(By.xpath("//span[@class='vms-player-btns']/span[@title='Digital Zoom']")).size() != 0){
			System.out.println("Element is Present");
			}else{
				System.out.println("Element is Absent");
				}
       }
    public void VerifyElementIsPresentOrnot2()
    {
    	if(driver.findElements(By.xpath("//span[@class='vms-player-btns']/span[@title='Export']")).size() != 0){
			System.out.println("Element is Present");
			}else{
				System.out.println("Element is Absent");
				}
}
public void InputIntoLimitRetentionfield()
{
	//WebElement ele = driver.findElement(By.xpath("//*[@id=\"vms-screen-recording-id\"]/ng-form/div[2]/div/div[2]/div[2]/span/input"));
	//ele.clear();
	WebElement ele =	driver.findElement(By.xpath("//*[@id=\"vms-screen-recording-id\"]/ng-form/div[2]/div/div[2]/div[2]/span/input"));
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", ele);
	//ele.sendKeys("14");
}
public void EnterInputIntoPostEventField()
{
	WebElement ele =	driver.findElement(By.xpath("//*[@id=\"vms-screen-recording-id\"]/ng-form/div[5]/div/div[2]/div[4]/div[3]/div/div[1]/span/input"));
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", ele);
}
	public void ClickOnMotion()
	{
		WebElement ele =	driver.findElement(By.xpath("//*[@id=\"vms-screen-recording-id\"]/ng-form/div[5]/div/div[2]/div[4]/div[4]/div/md-switch/div[1]/div[2]"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele);
	}
	//SuppoortMore than Stream

		public void ClickAddStreamButton()
		{
			 WebElement ele = driver.findElement(By.xpath("//*[@id=\"vms-screen-lock-id\"]/div/div/div/div/section/div/div[2]/button"));
		       ele.click();
		       if(driver.findElement(By.xpath("//*[@id=\"vms-screen-lock-id\"]/div/div/div/div/section/div/div[2]/button")).isDisplayed())
		       {
		       ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		      // newTab.remove(oldTab);
		       System.out.println(newTab);
		       driver.switchTo().window(newTab.get(0));
		}}
		public void ClickApplyButton()
		{
			 WebElement ele = driver.findElement(By.xpath("//*[@id=\"avms-configuration-container\"]/div/div/ui-view/div/div/div[2]/div/div[3]/button[5]"));
		       ele.click();
		       if(driver.findElement(By.xpath("//*[@id=\"avms-configuration-container\"]/div/div/ui-view/div/div/div[2]/div/div[3]/button[5]")).isDisplayed())
		       {
		       ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		      // newTab.remove(oldTab);
		       System.out.println(newTab);
		       driver.switchTo().window(newTab.get(0));
		}}
		    
		       public void EnterBitrateField() throws InterruptedException
		       {
		    	   WebElement ele1 = driver.findElement(By.xpath("//*[@id=\"new-stream-container\"]/div[2]/section[1]/div[4]/div/table/tbody/tr/td[7]/div/div/span/input"));
		           ele1.clear();
		           Thread.sleep(1000);
		           ele1.sendKeys("2000");
		       		}
		       public void EnterBitrateField_DualOptions() throws InterruptedException
		       {
		    	   WebElement ele1 = driver.findElement(By.xpath("//*[@id=\"new-stream-container\"]/div[2]/section[1]/div[4]/div/table/tbody/tr/td[7]/div/div/span/input"));
		           ele1.clear();
		           Thread.sleep(1000);
		           ele1.sendKeys("2000");
		           ele1.isDisplayed();
		       		}
		       public void EnterBitrateField_DualOptions_184() throws InterruptedException
		       {
		    	   WebElement ele1 = driver.findElement(By.xpath("//*[@id=\"new-stream-container\"]/div[2]/section[1]/div[4]/div/table/tbody/tr/td[7]/div/div/span/input"));
		           ele1.clear();
		           Thread.sleep(1000);
		           ele1.sendKeys("3600");
		           ele1.isDisplayed();
		       		}
		       public void Quality_DualOptions() throws InterruptedException
		       {
		    	   WebElement ele1 = driver.findElement(By.xpath("//*[@id=\"new-stream-container\"]/div[2]/section[1]/div[4]/div/table/tbody/tr[1]/td[8]/div/div/span/input"));
		           ele1.clear();
		           Thread.sleep(1000);
		           ele1.sendKeys("100");
		           ele1.isDisplayed();
		       		}
		       
		       public void EnterBitrateField_28() throws InterruptedException
		       {
		    	   WebElement ele1 = driver.findElement(By.xpath("//*[@id=\"new-stream-container\"]/div[2]/section[1]/div[4]/div/table/tbody/tr/td[7]/div/div/span/input"));
		           ele1.clear();
		           Thread.sleep(1000);
		           ele1.sendKeys("3000");
		       		}
		       public void EnterBitrateField2() throws InterruptedException
		       {
		    	   WebElement ele1 = driver.findElement(By.xpath("//*[@id=\"new-stream-container\"]/div[2]/section[1]/div[4]/div/table/tbody/tr/td[7]/div/div/span/input"));
		           ele1.clear();
		           Thread.sleep(1000);
		           ele1.sendKeys("4500");
		       		}
		       public void EnterBitrateField6() throws InterruptedException
		       {
		    	   WebElement ele1 = driver.findElement(By.xpath("//*[@id=\"new-stream-container\"]/div[2]/section[1]/div[4]/div/table/tbody/tr/td[7]/div/div/span/input"));
		           ele1.clear();
		           Thread.sleep(1000);
		           ele1.sendKeys("4600");
		           Thread.sleep(1000);
		       		}
		       public void EnterBitrateField1() throws InterruptedException
		       {
		    	   WebElement ele1 = driver.findElement(By.xpath("//*[@id=\"new-stream-container\"]/div[2]/section[1]/div[4]/div/table/tbody/tr/td[7]/div/div/span/input"));
		    	   JavascriptExecutor executor = (JavascriptExecutor)driver;
			   		executor.executeScript("arguments[0].click();", ele1); 
		          /* ele1.clear();
		           Thread.sleep(1000);
		           ele1.sendKeys("2000");*/
		       		}
		       public void EnterBitrateField5() throws InterruptedException
		       {
		    	   WebElement ele1 = driver.findElement(By.xpath("//*[@id=\"new-stream-container\"]/div[2]/section[1]/div[4]/div/table/tbody/tr/td[7]/div/div/span/input"));
		    	   JavascriptExecutor executor = (JavascriptExecutor)driver;
			   		executor.executeScript("arguments[0].click();", ele1); 
		          /* ele1.clear();
		           Thread.sleep(1000);
		           ele1.sendKeys("2000");*/
		       		}
		       public void Scroll_Down()
		       {
		    	   JavascriptExecutor js = (JavascriptExecutor) driver;
		           js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		       }
		       public void Delete_Stream()
		       {
		    	WebElement ele =	driver.findElement(By.xpath("//*[@id=\"vms-screen-lock-id\"]/div/div/div/div/section/div/div[1]/div[4]/div[1]/div/span[7]/button"));
		   		JavascriptExecutor executor = (JavascriptExecutor)driver;
		   		executor.executeScript("arguments[0].click();", ele); 
		       }
		       public void ClickConfigureStream()
		       {
		    	WebElement ele =	driver.findElement(By.xpath("//*[@id=\"vms-screen-lock-id\"]/div/div/div/div/section/div/div[1]/div[4]/div[1]/div/span[6]/button"));
		   		JavascriptExecutor executor = (JavascriptExecutor)driver;
		   		executor.executeScript("arguments[0].click();", ele); 
		       }
		       public void ClickOnClose() throws InterruptedException
		       {
		    	//driver.findElement(By.xpath("//*[@id=\"new-stream-container\"]/div[1]/div")).click(); 
		    	  //WebElement ele =	driver.findElement(By.xpath("//*[@id=\"new-stream-container\"]/div[1]/div"));
			   		//JavascriptExecutor executor = (JavascriptExecutor)driver;
			   		//executor.executeScript("arguments[0].click();", ele); 
		    	   
		    	   for (String winhandle: driver.getWindowHandles()) {
		    		    driver.switchTo().window(winhandle);
		    		    System.out.println("Window Switch");        
		    		    Thread.sleep(2000);
		    		    driver.findElement(By.xpath("//div[@ng-click='ctrl.close()']/span")).click();
		    		       Thread.sleep(1000);

		    		    //driver.findElement(By.xpath("(//button[span[contains(text(),'Close')]])[1]")).click();
		    		}
		       }
		       public void ClickOnSave()
		       {
		    	   WebElement ele =	driver.findElement(By.xpath("//*[@id=\"new-stream-container\"]/div[2]/section[2]/div/button[2]"));
			   		JavascriptExecutor executor = (JavascriptExecutor)driver;
			   		executor.executeScript("arguments[0].click();", ele); 
		    	  
		    	// driver.findElement(By.xpath("//*[@id=\"new-stream-container\"]/div[2]/section[2]/div/button[2]")).click(); 
		       }
		       
		       public void SelectStreamMode2(String name) throws InterruptedException {
		           Thread.sleep(1000);
		           waitUntilIsLoadedCustomTime(sheduleRecordingList1);
		           new Select(sheduleRecordingList1).selectByVisibleText(name);
		           //driver.switchTo().window(driver.getWindowHandle());
		       }
public void Select_StreamMode()
{
	WebElement stream = driver.findElement(By.xpath("//*[@id=\"vms-screen-recording-id\"]/ng-form/div[5]/div/div[2]/div[3]/div/select-wrapper/span[2]/select"));
	Select sel = new Select(stream);
	sel.selectByVisibleText("Stream1 - H264, 640X480, 25fps");
}

public void Select_HighResolution()
{
	 WebElement stream = driver.findElement(By.xpath("//*[@id=\"new-stream-container\"]/div[2]/section[1]/div[4]/div/table/tbody/tr/td[4]/select"));
	   	Select sel = new Select(stream);
	   	sel.selectByVisibleText("640X480");
}
		       }

	