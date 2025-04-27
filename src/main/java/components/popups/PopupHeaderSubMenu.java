package components.popups;

import annotations.components.Component;
import components.AbsComponent;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Component("internal_component:.//div[./a[contains(@href, '/categories')]]")
public class PopupHeaderSubMenu extends AbsComponent implements IPopup<PopupHeaderSubMenu> {


  public PopupHeaderSubMenu(WebDriver driver) {
    super(driver);
  }

  @Override
  public PopupHeaderSubMenu popupShouldNotBeVisible() throws Exception {
    assertThat(waiters.waitForInvisible(getComponentEntity()))
        .as("")
        .isTrue();


    return this;
  }

  @Override
  public PopupHeaderSubMenu popupShouldBeVisible() throws Exception {
    assertThat(waiters.waitForVisible(getComponentEntity()))
        .as("")
        .isTrue();

    return this;
  }

  public void clickRandomCategoryAndVerify() {
    List<WebElement> links = driver.findElements(By.xpath("//div[contains(@class, 'cgYLnJ')]//a"));
    List<String> urls = new ArrayList<>();

    for (WebElement link : links) {
      urls.add(link.getAttribute("href"));
    }
    String randomUrl = urls.get((int) (Math.random() * urls.size()));

    WebElement element = $(By.xpath("//div[contains(@class, 'cgYLnJ')]//a[@href='" + randomUrl + "']"));

    actions.scrollToElement(element).perform();
/*    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", randomUrl);*/
    ((JavascriptExecutor) driver).executeScript(
        "arguments[0].style.border='3px solid red';", element);

    driver.get(randomUrl);

    String actualUrl = driver.getCurrentUrl();

    String categoryName = randomUrl.substring(randomUrl.lastIndexOf('/') + 1);

    Assertions.assertTrue(actualUrl.contains(categoryName),
        "URL не соответствует выбранной категории. Ожидаемый фрагмент: " + categoryName +
            ", полученный URL: " + actualUrl);
  }


}

