package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactAdd() {
    app.goTo().homePage();
    List<ContactData> before = app.contact().list();
    app.goTo().contactPage();
    ContactData contact = new ContactData()
            .withFirstname("Максим")
            .withLastname("Егоров")
            .withMobile("+79271144774")
            .withEmail("email1@gmail.com")
            .withGroup("test1");
    app.contact().create(contact, true);
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();

    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().max(Comparator.comparingInt(ContactData::getId)).get().getId());
    before.add(contact);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }








}
