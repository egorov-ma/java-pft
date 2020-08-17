package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData()
                    .withFirstname("Максим")
                    .withLastname("Егоров")
                    .withAddress("Москва")
                    .withHomePhone("222-222")
                    .withMobilePhone("+7(927) 114-47-74")
                    .withWorkPhone("33 33")
                    .withEmail("email1@gmail.com"), true);
        }
        app.goTo().homePage();
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        app.goTo().contactPage();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstname("Максим")
                .withLastname("Егоров")
                .withAddress(modifiedContact.getAddress())
                .withHomePhone(modifiedContact.getHomePhone())
                .withMobilePhone(modifiedContact.getMobilePhone())
                .withWorkPhone(modifiedContact.getWorkPhone())
                .withEmail(modifiedContact.getEmail());
        app.goTo().homePage();
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
