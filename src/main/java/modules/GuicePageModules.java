package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import components.popups.PopupHeaderSubMenu;
import components.static_component.HeaderMenuComponent;
import factory.WebDriverFactory;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import pages.CourseCatalogPage;
import pages.LessonCardPage;
import pages.MainPage;

public class GuicePageModules extends AbstractModule {

  private final WebDriver driver = new WebDriverFactory().create();

   @Provides
    private WebDriver getDriver() {
        return driver;
    }

@Singleton
@Provides
public MainPage getMainPage() {
        return new MainPage(driver);
    }

    @Singleton
    @Provides
    public LessonCardPage getLessonCardPage() {
    return new LessonCardPage(driver);
}
    @Singleton
    @Provides
    public CourseCatalogPage courseCatalogPage(){
     return new CourseCatalogPage(driver);
    }

  @Singleton
  @Provides
  public HeaderMenuComponent getHeaderMenuComponent() {
     return new HeaderMenuComponent(driver);
  }

  @Singleton
  @Provides
  public PopupHeaderSubMenu getPopupHeaderSubMenu() {
     return new PopupHeaderSubMenu(driver);
  }
}




