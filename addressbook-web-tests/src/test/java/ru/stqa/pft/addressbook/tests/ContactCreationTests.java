package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactAdd() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        app.goTo().contactPage();
        File photo = new File("src/test/resources/myPhoto.png");
        ContactData contact = new ContactData()
                .withFirstname("Максим")
                .withLastname("Егоров")
                .withAddress("Москва")
                .withHomePhone("222-222")
                .withMobilePhone("+7(927) 114-47-74")
                .withWorkPhone("33 33")
                .withEmail("email1@gmail.com")
                .withGroup("test1")
                .withPhoto(photo);
        app.contact().create(contact, true);
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
    }

    @Test (enabled = false)
    public void testBadContactAdd() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        app.goTo().contactPage();
        ContactData contact = new ContactData()
                .withFirstname("Максим'")
                .withLastname("Егоров")
                .withMobilePhone("+79271144774")
                .withEmail("email1@gmail.com")
                .withGroup("test1");
        app.contact().create(contact, true);
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }
}
