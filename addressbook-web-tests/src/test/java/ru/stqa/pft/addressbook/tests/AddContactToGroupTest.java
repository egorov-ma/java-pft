package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Objects;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test 1"));
        }
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

    }

    @Test
    public void testAddContactToGroup() {
        ContactData contact = app.db().contacts().iterator().next();
        GroupData group = app.db().groups().iterator().next();
        app.goTo().homePage();
        app.contact().addToGroup(contact, group);
        ContactData addContact = app.db().contacts()
                .stream().filter(a -> Objects.equals(contact.getId(), a.getId())).findFirst().orElse(null);
        assertThat(addContact.getGroups().contains(group), equalTo(true));
    }
}
