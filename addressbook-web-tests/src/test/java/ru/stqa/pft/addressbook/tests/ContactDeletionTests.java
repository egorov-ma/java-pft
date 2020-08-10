package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.getContactHelp().isThereAGroup()) {
            app.getNavigationHelper().gotoContactPage();
            app.getContactHelp().createContact(new ContactData("Максим", "Егоров", "+79271144774", "email1@gmail.com", "test1"), true);
        }
        app.getNavigationHelper().gotoHomePage();
    }

    @Test
    public void testContactDeletion() {

        List<ContactData> before = app.getContactHelp().getContactList();
        int index = before.size() - 1;
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelp().selectedContacts(index);
        app.getContactHelp().deleteSelectedContacts();
        app.getContactHelp().acceptAlert();
        app.getNavigationHelper().gotoHomePage();

        List<ContactData> after = app.getContactHelp().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }

}
