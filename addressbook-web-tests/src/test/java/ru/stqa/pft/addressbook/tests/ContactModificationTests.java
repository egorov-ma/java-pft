package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        int before = app.getContactHelp().getContactCount();
        if (! app.getContactHelp().isThereAGroup()){
            app.getNavigationHelper().gotoContactPage();
            app.getContactHelp().createContact(new ContactData("Максим","Егоров","+79271144774","email1@gmail.com", "test1"), true);
        }
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelp().selectedContacts(before - 1);
        app.getContactHelp().initContactModification();
        app.getContactHelp().fillContactForm(new ContactData("МаксимМ","Егоров",null,"email1@gmail.com", null), false);
        app.getContactHelp().submitContactModification();
        app.getContactHelp().returnToHomePage();
        int after = app.getContactHelp().getContactCount();
        Assert.assertEquals(after, before);
    }

}
