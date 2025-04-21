package pages;

import annotations.Path;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import waiters.Waiters;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Path("/catalog/courses")
public class CourseCatalogPage extends AbsBasePage<CourseCatalogPage>{

  public CourseCatalogPage(WebDriver driver) {
    super(driver);
  }

  private String courseName = System.getProperty("courseName");
  @FindBy(xpath = "//a[contains(@href, 'lessons') and contains(@class, 'sc-zzdkm7-0')]")
  private List<WebElement> lessonItems;

  public String getLessonTittleByIndex(int index) {


    return lessonItems.get(index)
        .findElement(By.xpath(".//h6/div")).getText();
  }

  public void clickLessonTileByTitle(String title) {
    String lessonCardLocatorTemplate = String.format("//a[contains(@href, '/lessons')][.//*[text()='%s']]", title);

    ((JavascriptExecutor) driver).executeScript(
        "arguments[0].style.border='3px solid red';",
        $(By.xpath(lessonCardLocatorTemplate))
    );

    ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", $(By.xpath(lessonCardLocatorTemplate)));

    $(By.xpath(lessonCardLocatorTemplate)).click();
  }

  public CourseCatalogPage waitForLoad() {
    {
      new Waiters(driver).waitForPageToLoad();
      return this;
    }
  }

    public String findCourseByName() {
      return lessonItems.stream()
          .map(lesson -> lesson.findElement(By.xpath(".//h6/div")).getText())
          .filter(title -> title.equalsIgnoreCase(courseName))
          .findFirst().get();
    }
  }


