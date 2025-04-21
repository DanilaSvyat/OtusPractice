package pages;

import annotations.Path;
import jakarta.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import waiters.Waiters;

import java.time.Duration;
import java.util.List;

@Path("/")
public class MainPage extends AbsBasePage<MainPage> {

    public MainPage(WebDriver driver) {
       super(driver);
    }



    @FindBy(xpath = "//a[contains(@href, 'lessons') and contains(@class, 'sc-zzdkm7-0')]")
        private List<WebElement> lessonItems;

    public String getLessonTittleByIndex(int index) {


      return lessonItems.get(index)
          .findElement(By.xpath(".//h6/div")).getText();
    }

    public void clickLessonTileByTitle(String title) {
        String lessonCardLocatorTemplate = String.format("//a[contains(@href, '/lessons')][.//*[text()='%s']]", title);

        $(By.xpath(lessonCardLocatorTemplate)).click();
    }
    public MainPage waitForLoad() {
        {
            new Waiters(driver).waitForPageToLoad();
            return this;
        }

    }
}
