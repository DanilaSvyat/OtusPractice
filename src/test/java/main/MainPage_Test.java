package main;

import extensions.UIExtension;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.LessonCardPage;
import pages.MainPage;

@ExtendWith(UIExtension.class)
public class MainPage_Test {

    @Inject
    private LessonCardPage lessonCardPage;

    @Inject
    private MainPage mainPage;

    @Test
    public void mainPageTest() {
      mainPage.open()
          .waitForLoad();



     String lessonTittle = mainPage
             .open()
             .getLessonTittleByIndex(1);


     mainPage.clickLessonTileByTitle(lessonTittle);

     lessonCardPage.pageHeaderShouldBeSameAs(lessonTittle);

    }


}
