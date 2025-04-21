package main;

import extensions.UIExtension;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.CourseCatalogPage;
import pages.LessonCardPage;
import pages.MainPage;

@ExtendWith(UIExtension.class)
public class MainPage_Test {

    @Inject
    private CourseCatalogPage courseCatalogPage;

    @Inject
    private LessonCardPage lessonCardPage;

    @Inject
    private MainPage mainPage;

    /*@Test
    public void mainPageTest() {
      mainPage.open()
          .waitForLoad();



     String lessonTittle = mainPage
             .getLessonTittleByIndex(1);


     mainPage.clickLessonTileByTitle(lessonTittle);

     mainPage.pageHeaderShouldBeSameAs(lessonTittle);
    }*/

    @Test
    public void courseCatalogPageTest() {

      courseCatalogPage.open().waitForLoad();

      String lessonTittle = courseCatalogPage.findCourseByName();

      courseCatalogPage.clickLessonTileByTitle(lessonTittle);

      courseCatalogPage.pageHeaderShouldBeSameAs(lessonTittle);

  }

}
