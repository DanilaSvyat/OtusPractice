package pages;

import annotations.Path;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import waiters.Waiters;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Path("/catalog/courses")
public class CourseCatalogPage extends AbsBasePage<CourseCatalogPage> {

  public CourseCatalogPage(WebDriver driver) {
    super(driver);
  }

  private String courseName = System.getProperty("courseName");

  @FindBy(xpath = "//a[contains(@href, 'lessons') and contains(@class, 'sc-zzdkm7-0')]")
  private List<WebElement> lessonItems;

  Document doc;

  {
    try {
      doc = Jsoup.connect(System.getProperty("base.url") + (getClass().getAnnotation(Path.class)).value()).get();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

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

    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", $(By.xpath(lessonCardLocatorTemplate)));

    $(By.xpath(lessonCardLocatorTemplate)).click();
  }


  public String findCourseByName() {
    return lessonItems.stream()
        .map(lesson -> lesson.findElement(By.xpath(".//h6/div")).getText())
        .filter(title -> title.equalsIgnoreCase(courseName))
        .findFirst().get();
  }

  public LocalDate findEarliestDate() {

    Elements courseCards = doc.selectXpath("//a[contains(@href, 'lessons') and contains(@class, 'sc-zzdkm7-0')]");

    return courseCards.stream()
        .map(card -> {
          if ((card.selectXpath(".//div[contains(@class, 'sc-157icee-1')]//div[contains(@class, 'jEGzDf')]").text())
              .contains("О дате старта будет объявлено позже")) {
            return null;
          }
          return LocalDate.parse(
              card.selectXpath(".//div[contains(@class, 'sc-157icee-1')]//div[contains(@class, 'jEGzDf')]")
                  .text()
                  .split("·")[0].trim(),
              DateTimeFormatter.ofPattern("dd MMMM, yyyy", new Locale("ru"))
          );
        }).reduce((c1, c2) -> c1.isBefore(c2) ? c1 : c2).orElseThrow();
  }

  public LocalDate findLatestDate() {

    Elements courseCards = doc.selectXpath("//a[contains(@href, 'lessons') and contains(@class, 'sc-zzdkm7-0')]");

    return courseCards.stream()
        .map(card -> {
          if ((card.selectXpath(".//div[contains(@class, 'sc-157icee-1')]//div[contains(@class, 'jEGzDf')]").text())
              .contains("О дате старта будет объявлено позже")) {
            return null;
          }
          return LocalDate.parse(
              card.selectXpath(".//div[contains(@class, 'sc-157icee-1')]//div[contains(@class, 'jEGzDf')]")
                  .text()
                  .split("·")[0].trim(),
              DateTimeFormatter.ofPattern("dd MMMM, yyyy", new Locale("ru"))
          );
        }).reduce((c1, c2) -> c1.isAfter(c2) ? c1 : c2).orElseThrow();
  }


  public String findCourseNameByDate(LocalDate date) {

    String formDate = date.format(DateTimeFormatter.ofPattern("dd MMMM, yyyy", new Locale("ru")));

    List<WebElement> courses = driver.findElements(By.xpath(String.format(
        "//a[contains(@href, '/lessons')][.//*[contains(text(), '%s')]]",
        formDate)
    ));

    WebElement course = courses.get(1);

    String courseName = course.findElement( By.cssSelector("h6 div.jEGzDf")
    ).getText();

    return courseName;
  }

  public void checkCoursePage(String courseName, LocalDate courseDate) {


      Element course = doc.selectFirst("a:has(h6:contains(" + courseName + "))");
    if (course == null) {
      throw new RuntimeException();
    }
      String fullCourseUrl = course.absUrl("href");


    Document  lessonPageDoc = null;
    try {
      lessonPageDoc = Jsoup.connect(fullCourseUrl).get();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }


    String coursePageTitle = lessonPageDoc.select("h1.sc-1x9oq14-0").text();
    String coursePageStartDate = lessonPageDoc.select(".sc-3cb1l3-4 > p").text();

    assertEquals(courseName, coursePageTitle);
    assertTrue(coursePageStartDate.contains
            (courseDate.format(DateTimeFormatter.ofPattern("dd MMMM", new Locale("ru")))),
        "Дата на странице курса не совпадает с датой курса: " + coursePageStartDate);
  }
}



