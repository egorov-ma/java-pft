package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Objects;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromGroupTest extends TestBase {

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
    public void testDeletionContactFromGroup() {
        ContactData contact = app.db().contacts().iterator().next();
        GroupData group = app.db().groups().iterator().next();
        Contacts contactSearch = app.db().contacts();
        Groups groupSearch = app.db().groups();

        boolean flag = true;
        for (ContactData theContact : contactSearch) {
            for (GroupData theGroup : groupSearch) {
                if (theContact.getGroups().contains(theGroup) && flag) {
                    contact = theContact;
                    group = theGroup;
                    flag = false;
                }
            }
            if (flag) {
                contact = theContact;
                group = app.db().groups().iterator().next();
                app.goTo().homePage();
                app.contact().addToGroup(contact, group);
            }
        }
        app.goTo().homePage();
        app.contact().deleteFromGroup(contact, group);
        app.goTo().homePage();
        ContactData finalContact = contact;
        ContactData deleteContact = app.db().contacts().stream().filter((g) -> Objects.equals(finalContact.getId(), g.getId())).findFirst().orElse(null);
        assertThat(deleteContact.getGroups().contains(group), equalTo(false));


    }

}

