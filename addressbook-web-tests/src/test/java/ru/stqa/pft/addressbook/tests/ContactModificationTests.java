package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelp().selectedContacts();
        app.getContactHelp().initContactModification();
        app.getContactHelp().fillContactForm(new ContactData("МаксимМ","Егоров",null,"email1@gmail.com"));
        app.getContactHelp().submitContactModification();
        app.getContactHelp().returnToHomePage();
    }

}
