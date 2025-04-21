package pages;

import annotations.Path;
import annotations.PathTemplate;
import common.AbsCommon;
import exceptions.PathNotValidException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.lang.annotation.Annotation;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbsBasePage<T> extends AbsCommon<T> {


    private String BASE_URL = !System.getProperty("base.url").endsWith("/")
            ? System.getProperty("base.url")
            : System.getProperty("base.url").substring(0, System.getProperty("base.url").length() - 1);

    public AbsBasePage(WebDriver driver){

        super(driver);
    }

    @FindBy(tagName = "h1")
    private WebElement header;

    private String getPath(){
        Path path = getClass().getAnnotation(Path.class);
        if(path == null) {
            throw new PathNotValidException();
        }
        return path.value().startsWith("/") ? path.value() : "/" + path.value();
    }

    public T open() {
        driver.get(BASE_URL + getPath());
        return (T) this;
    }
    public T open(String... data) {
        PathTemplate pathTemplate = getClass().getAnnotation(PathTemplate.class);

      if(pathTemplate == null) {
          throw new PathNotValidException();
      }
      String path = pathTemplate.value();

      for(int i  = 0; i < data.length; i++){
          path = path.replace("$" + (i + 1), data[i]);
      }

      driver.get(BASE_URL + path);

      return (T) this;
    }

    public T pageHeaderShouldBeSameAs(String title) {

        assertThat(header.getText()).
                as("Header of page should be {}", title)
                .isEqualTo(title);

        return (T) this;

    }


}
