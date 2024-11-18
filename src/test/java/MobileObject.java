import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class MobileObject {
    private final AndroidDriver<MobileElement> driver; // Сделать finаl

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/userInput")
    private MobileElement userInputElement;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/buttonChange")
    private MobileElement changeButtonElement;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/textToBeChanged")
    private MobileElement textToBeChangedElement;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/buttonActivity")
    private MobileElement buttonActivityElement;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/text")
    private MobileElement resultTextElement;

    // Конструктор
    public MobileObject(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        // Инициализация элементов страницы
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // Методы для взаимодействия с элементами
    public MobileElement getUserInputElement() {
        return userInputElement;
    }

    public MobileElement getChangeButtonElement() {
        return changeButtonElement;
    }

    public MobileElement getTextToBeChangedElement() {
        return textToBeChangedElement;
    }

    public MobileElement getButtonActivityElement() {
        return buttonActivityElement;
    }

    public MobileElement getResultTextElement() {
        return resultTextElement;
    }
}