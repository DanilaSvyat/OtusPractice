package components.popups;

import annotations.components.Component;
import components.AbsComponent;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Component("internal_component:.//div[./a[contains(@href, '/categories')]]")
public class PopupHeaderSubMenu extends AbsComponent implements IPopup<PopupHeaderSubMenu> {


  public PopupHeaderSubMenu(WebDriver driver) {
    super(driver);
  }

  @Override
  public PopupHeaderSubMenu popupShouldNotBeVisible() throws Exception {
    assertThat(waiters.waitForInvisible(getComponentEntity().findElement(getComponentBy())))
        .as("")
        .isTrue();


    return this;
  }

  @Override
  public PopupHeaderSubMenu popupShouldBeVisible() throws Exception{
    assertThat(waiters.waitForInvisible(getComponentEntity().findElement(getComponentBy())))
        .as("")
        .isTrue();

    return this;
  }
}

