package main;

import components.popups.PopupHeaderSubMenu;
import components.static_component.HeaderMenuComponent;
import data.menu.HeaderMenuData;
import extensions.UIExtension;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebElement;
import pages.CourseCatalogPage;
import pages.LessonCardPage;
import pages.MainPage;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(UIExtension.class)
public class MainPage_Test {

    @Inject
    private CourseCatalogPage courseCatalogPage;

    @Inject
    private LessonCardPage lessonCardPage;

    @Inject
    private MainPage mainPage;

    @Inject
    private HeaderMenuComponent headerMenuComponent;

    @Inject
    private PopupHeaderSubMenu popupHeaderSubMenu;

    /*@Test
    public void mainPageTest() {
      mainPage.open()
          .waitForLoad();



     String lessonTittle = mainPage
             .getLessonTittleByIndex(1);


     mainPage.clickLessonTileByTitle(lessonTittle);

     mainPage.pageHeaderShouldBeSameAs(lessonTittle);
    }*/

   /* @Test
    public void courseCatalogPageTest() {

      courseCatalogPage.open().waitForLoad();

      String lessonTittle = courseCatalogPage.findCourseByName();

      courseCatalogPage.clickLessonTileByTitle(lessonTittle);

      courseCatalogPage.pageHeaderShouldBeSameAs(lessonTittle);

  }*/

/*
  @Test
  public void courseDateTest() {


    courseCatalogPage.open().waitForLoad();

    courseCatalogPage.checkCoursePage(courseCatalogPage.findCourseNameByDate(courseCatalogPage.findEarliestDate()),
        courseCatalogPage.findEarliestDate());

    courseCatalogPage.checkCoursePage(courseCatalogPage.findCourseNameByDate(courseCatalogPage.findLatestDate()),
        courseCatalogPage.findLatestDate());
*/

  @Test
  public void headerMenuTest() {

    mainPage.open();

    headerMenuComponent.waitForLoad(HeaderMenuData.EDUCATION);


    try {
      popupHeaderSubMenu.popupShouldNotBeVisible();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    headerMenuComponent.setFocusToMenuItem(HeaderMenuData.EDUCATION);

    try {
      popupHeaderSubMenu.popupShouldBeVisible();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    popupHeaderSubMenu.clickRandomCategoryAndVerify();

  }
}