package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.getContactHelp().isThereAGroup()) {
            app.getNavigationHelper().gotoContactPage();
            app.getContactHelp().createContact(new ContactData("Максим", "Егоров", "+79271144774", "email1@gmail.com", "test1"), true);
        }
        app.getNavigationHelper().gotoHomePage();
    }

    @Test
    public void testContactModification() {
        List<ContactData> before = app.getContactHelp().getContactList();
        int index = before.size() - 1;
        ContactData contact = new ContactData(before.get(index).getId(), "МаксимМ","Егоров",null,"email1@gmail.com", null);
        app.getContactHelp().modifyContact(index, contact);
        List<ContactData> after = app.getContactHelp().getContactList();
        Assert.assertEquals(after.size(), before.size());
        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }



}
