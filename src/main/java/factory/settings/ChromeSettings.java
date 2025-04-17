package factory.settings;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

public class ChromeSettings implements IBrowserSettings{

  @Override
  public AbstractDriverOptions settings() {
    WebDriverManager.chromedriver()
        .setup();

    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--no-sandbox");

    if(Boolean.getBoolean(System.getProperty("StartEmptyPage"))) {
      chromeOptions.addArguments("--empty-blank");
    }
    return chromeOptions;
  }
}
