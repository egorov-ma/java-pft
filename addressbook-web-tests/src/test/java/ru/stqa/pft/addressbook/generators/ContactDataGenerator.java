package ru.stqa.pft.addressbook.generators;

import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);

        List<ContactData> contacts = generateContacts(count);
        save(contacts, file);
    }

    private static List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData()
                    .withFirstname(String.format("Firstname%s", i))
                    .withLastname(String.format("Lastname%s", i))
                    .withAddress(String.format("Address%s", i))
                    .withHomePhone(String.format("111-11%s", i))
                    .withMobilePhone(String.format("+7(927) 114-47-7%s", i))
                    .withWorkPhone(String.format("33 33 %s", i))
                    .withEmail(String.format("email1@gmail%s.com", i))
                    .withGroup(String.format("test%s", i)));
        }
        return contacts;
    }

    private static void save(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(),
                    contact.getAddress(), contact.getHomePhone(), contact.getMobilePhone(),
                    contact.getWorkPhone(), contact.getEmail(), contact.getGroup()));
        }
        writer.close();
    }

}
