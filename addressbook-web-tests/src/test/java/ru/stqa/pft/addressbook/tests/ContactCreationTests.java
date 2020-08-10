package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactAdd() {
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> before = app.getContactHelp().getContactList();
    app.getNavigationHelper().gotoContactPage();
    ContactData contact = new ContactData("Максим","Егоров","+79271144774","email1@gmail.com", "test1");
    app.getContactHelp().createContact(contact, true);
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelp().getContactList();

    Assert.assertEquals(after.size(), before.size() + 1);

    contact.setId(after.stream().max(Comparator.comparingInt(ContactData::getId)).get().getId());
    before.add(contact);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

    app.logout();
  }








}
