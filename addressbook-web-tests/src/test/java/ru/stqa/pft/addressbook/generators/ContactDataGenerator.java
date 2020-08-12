package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names ="-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();

    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        save(contacts, new File(file));
    }

    private List<ContactData> generateContacts(int count) {
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

    private void save(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(),
                    contact.getAddress(), contact.getHomePhone(), contact.getMobilePhone(),
                    contact.getWorkPhone(), contact.getEmail(), contact.getGroup()));
        }
        writer.close();
    }

}
