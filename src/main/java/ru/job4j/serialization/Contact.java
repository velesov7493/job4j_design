package ru.job4j.serialization;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.util.Objects;

public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final transient Logger LOG = LoggerFactory.getLogger(Contact.class.getName());

    private final int zipCode;
    private final String phone;

    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contact contact = (Contact) o;
        return zipCode == contact.zipCode && Objects.equals(phone, contact.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, phone);
    }

    @Override
    public String toString() {
        return
                "Contact{"
                + "zipCode=" + zipCode
                + ", phone='" + phone
                + "'}";
    }

    private static Contact readFromFile(File source) {
        try (FileInputStream fis = new FileInputStream(source);
             ObjectInputStream ois =
                     new ObjectInputStream(fis)
        ) {
            return (Contact) ois.readObject();
        } catch (IOException ex) {
            LOG.error("Ошибка ввода-вывода при чтении объекта.", ex);
        } catch (ClassNotFoundException ex) {
            LOG.error("Ошибка десериализации.", ex);
        }
        return null;
    }

    private static void writeToFile(Contact obj, File target) {
        try (FileOutputStream fos = new FileOutputStream(target);
             ObjectOutputStream oos =
                     new ObjectOutputStream(fos)
        ) {
            oos.writeObject(obj);
        } catch (IOException ex) {
            LOG.error("Ошибка ввода-вывода при записи объекта.", ex);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Contact contact = new Contact(123456, "+7 (111) 111-11-11");
        File tempFile = Files.createTempFile(null, null).toFile();
        writeToFile(contact, tempFile);
        Contact contact1 = readFromFile(tempFile);
        System.out.println(contact);
        System.out.println(contact1);
        System.out.println("Объекты одинаковы: " + contact.equals(contact1));
    }
}
