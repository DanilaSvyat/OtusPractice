package components.popups;

public interface IPopup<T> {

  T popupShouldNotBeVisible() throws Exception;

  T popupShouldBeVisible() throws Exception;

}
