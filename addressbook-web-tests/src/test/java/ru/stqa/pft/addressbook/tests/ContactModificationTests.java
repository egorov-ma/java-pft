package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        if (app.getContactHelp().isThereAGroup()){
            app.getNavigationHelper().gotoContactPage();
            app.getContactHelp().createContact(new ContactData("Максим","Егоров","+79271144774","email1@gmail.com", "test1"), true);
        }
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> before = app.getContactHelp().getContactList();
        app.getContactHelp().selectedContacts(before.size() - 1);
        app.getContactHelp().initContactModification();
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "МаксимМ","Егоров",null,"email1@gmail.com", null);
        app.getContactHelp().fillContactForm(contact, false);
        app.getContactHelp().submitContactModification();
        app.getContactHelp().returnToHomePage();
        List<ContactData> after = app.getContactHelp().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
