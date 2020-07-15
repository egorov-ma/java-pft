package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelp().selectedContacts();
    app.getContactHelp().deleteSelectedContacts();
    app.getContactHelp().acceptAlert();
  }

}
