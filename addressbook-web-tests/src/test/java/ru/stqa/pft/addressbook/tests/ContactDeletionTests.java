package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    if (! app.getContactHelp().isThereAGroup()){
      app.getNavigationHelper().gotoContactPage();
      app.getContactHelp().createContact(new ContactData("Максим","Егоров","+79271144774","email1@gmail.com", "test1"), true);
    }
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelp().selectedContacts();
    app.getContactHelp().deleteSelectedContacts();
    app.getContactHelp().acceptAlert();
  }

}
