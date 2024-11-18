import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SampleTest {

    private AndroidDriver<MobileElement> driver;
    private MobileObject mobileObject;

    private static final String BASE_URL = "http://127.0.0.1:4723";
    private static final String PACKAGE_NAME = "ru.netology.testing.uiautomator";

    private static final String EMPTY_INPUT = "     ";
    private static final String VALID_INPUT = "2test";

    private URL getUrl() {
        try {
            return new URL(BASE_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @BeforeEach
    public void setUp() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("appium:platformName", "android");
        caps.setCapability("appium:deviceName", "Pix7");
        caps.setCapability("appium:appPackage", PACKAGE_NAME);
        caps.setCapability("appium:appActivity", PACKAGE_NAME + ".MainActivity");
        caps.setCapability("appium:automationName", "uiautomator2");
        caps.setCapability("appium:newCommandTimeout", 3600);
        caps.setCapability("appium:connectHardwareKeyboard", true);

        driver = new AndroidDriver<>(this.getUrl(), caps);
        mobileObject = new MobileObject(driver); // инициализация MobileObject
    }

    @Test
    public void testEmptyOrSpaceInputDoesNotChangeOutput() {
        String initialText = mobileObject.getTextToBeChangedElement().getText();
        mobileObject.getUserInputElement().sendKeys(EMPTY_INPUT);
        mobileObject.getChangeButtonElement().click();

        String updatedText = mobileObject.getTextToBeChangedElement().getText();
        assertEquals(initialText, updatedText, "Текст не должен измениться при вводе пустой строки или пробелов.");
    }

    @Test
    public void testUserInputChangesTextInNewActivity() {
        mobileObject.getUserInputElement().click();
        mobileObject.getUserInputElement().sendKeys(VALID_INPUT);
        mobileObject.getButtonActivityElement().click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        MobileElement resultTextElement = (MobileElement) wait.until(ExpectedConditions.visibilityOf(mobileObject.getResultTextElement())
        );

        String resultText = resultTextElement.getText();
        assertEquals(VALID_INPUT, resultText, "Текст в новом Activity не соответствует введенному значению.");
    }


    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}