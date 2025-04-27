package components.static_component;

import annotations.components.Component;
import components.AbsComponent;
import data.menu.HeaderMenuData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


@Component("css:a[href='/'] ~ nav")
public class HeaderMenuComponent extends AbsComponent<HeaderMenuComponent> {


  public HeaderMenuComponent(WebDriver driver) {
    super(driver);
  }

  public void setFocusToMenuItem(HeaderMenuData headerMenuData){
    String locator = String.format("//div[./span[text()='%s']]", headerMenuData.getName());
    actions.moveToElement($(By.xpath(locator))).build().perform();
  }

  public void waitForLoad(HeaderMenuData headerMenuData){
    waiters.waitForCondition(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
        String.format("//div[./span[text()='%s']]", headerMenuData.getName())
    )));
  }
}
