package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactAdd() {
    app.getNavigationHelper().gotoContactPage();
    app.getContactHelp().fillContactForm(new ContactData("Максим","Егоров","+79271144774","email1@gmail.com", "test1"), true);
    app.getContactHelp().submitContactCreation();
    app.getContactHelp().returnToHomePage();
    app.logout();
  }








}
