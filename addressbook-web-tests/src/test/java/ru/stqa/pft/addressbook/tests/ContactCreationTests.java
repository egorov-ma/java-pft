package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactAdd() {
    app.getNavigationHelper().gotoHomePage();
    int before = app.getContactHelp().getContactCount();
    app.getNavigationHelper().gotoContactPage();
    app.getContactHelp().createContact(new ContactData("Максим","Егоров","+79271144774","email1@gmail.com", "test1"), true);
    app.getNavigationHelper().gotoHomePage();
    int after = app.getContactHelp().getContactCount();
    Assert.assertEquals(after, before + 1);
    app.logout();
  }








}
