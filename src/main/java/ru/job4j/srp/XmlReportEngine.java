package ru.job4j.srp;

import ru.job4j.serialization.Actor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class XmlReportEngine implements Report {

    @XmlRootElement(name = "employees")
    private static class EmployeeListWrapper {

        private List<Employee> employees;

        public EmployeeListWrapper() { }

        @XmlElement(name = "employee")
        public List<Employee> getEmployees() {
            return employees;
        }

        public void setEmployees(List<Employee> employees) {
            this.employees = employees;
        }
    }

    private Store store;

    public XmlReportEngine(Store aStore) {
        store = aStore;
    }

    @Override
    public String generate(
            Predicate<Employee> filter, Comparator<Employee> comparator,
            String salaryFormat, Set<String> columns
    ) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        List<Employee> list = store.findBy(filter);
        EmployeeListWrapper wrapper = new EmployeeListWrapper();
        wrapper.setEmployees(list);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            JAXBContext context = JAXBContext.newInstance(EmployeeListWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(wrapper, writer);
            xml = writer.getBuffer().toString().trim();
        } catch (IOException ex) {
            System.out.println("Ошибка ввода/вывода: " + ex);
        } catch (JAXBException ex) {
            System.out.println("Ошибка сборки XML: " + ex);
        }
        return xml;
    }
}
