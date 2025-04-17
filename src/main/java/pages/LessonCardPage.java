package pages;

import annotations.PathTemplate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@PathTemplate("/lessons/$1")
public class LessonCardPage extends AbsBasePage<LessonCardPage>{

    public LessonCardPage(WebDriver driver){
        super(driver);
    }

}
