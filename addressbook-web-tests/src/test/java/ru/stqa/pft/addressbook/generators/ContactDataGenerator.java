package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jcomander =  new JCommander(generator);
        try {
            jcomander.parse(args);
        } catch (ParameterException ex) {
            jcomander.usage();
            return;
        }
        generator.run();

    }
    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if(format.equals("json")) {
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format" + format);
        }

    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
//        writer.write(json);
//        writer.close();

    }
    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        File photo = new File("src/test/resources/contact.jpg");
        for(int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstName(String.format("Daria %s", i))
            .withLastName(String.format("Zaikina %s", i))
            .withMobile(String.format("8913018432%s", i))
            .withEmail(String.format("blabla%s@gmail.com", i))
            .withAdress(String.format("Novisibirsk Ivanova test %s", i)));
        }
        return contacts;
    }

}