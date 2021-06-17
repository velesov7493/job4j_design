package ru.job4j.serialization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@XmlRootElement(name = "actor")
@XmlType(propOrder = {
        "name", "login", "pass", "birthDate",
        "sex", "phone", "email", "activated", "stats"
})
public class Actor {

    private static final transient Logger LOG = LoggerFactory.getLogger(Actor.class.getName());

    private Long id;
    private Long avatarId;
    private String name;
    private String login;
    private String pass;
    private Date birthDate;
    private String sex;
    private String phone;
    private String email;
    private byte activated;
    private ActorState[] stats;

    public Actor() { }

    @XmlAttribute(name = "id", required = true)
    public Long getId() {
        return id;
    }

    public void setId(Long value) {
        id = value;
    }

    @XmlAttribute(name = "avatarId", required = true)
    public Long getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Long value) {
        avatarId = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        name = value;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String value) {
        login = value;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String value) {
        pass = value;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String value) {
        sex = value;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String value) {
        phone = value;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String value) {
        email = value;
    }

    public byte getActivated() {
        return activated;
    }

    public void setActivated(byte value) {
        activated = value;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date value) {
        birthDate = value;
    }

    @XmlElement(name = "state")
    @XmlElementWrapper(name = "stats")
    public ActorState[] getStats() {
        return stats;
    }

    public void setStats(ActorState[] value) {
        stats = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Actor actor = (Actor) o;
        boolean statsEquals = stats.length == actor.stats.length;
        if (statsEquals) {
            for (int i = 0; i < stats.length; i++) {
                if (!stats[i].equals(actor.stats[i])) {
                    statsEquals = false;
                    break;
                }
            }
        }
        return Objects.equals(id, actor.id) && statsEquals;
    }

    @Override
    public int hashCode() {
        int statsHash = 0;
        for (ActorState state : stats) {
            statsHash ^= state.hashCode();
        }
        return 31 * Objects.hash(id) ^ statsHash;
    }

    @Override
    public String toString() {
        return "Actor{"
                + "id=" + id
                + ", avatarId=" + avatarId
                + ", name='" + name + '\''
                + ", login='" + login + '\''
                + ", pass='" + pass + '\''
                + ", birthDate=" + birthDate
                + ", sex='" + sex + '\''
                + ", phone='" + phone + '\''
                + ", email='" + email + '\''
                + ", activated=" + activated
                + ", stats=" + Arrays.toString(stats)
                + '}';
    }

    public String toXML() {
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            // Получаем контекст для доступа к АПИ
            JAXBContext context = JAXBContext.newInstance(Actor.class);
            // Создаем сериализатор
            Marshaller marshaller = context.createMarshaller();
            // Указываем, что нам нужно форматирование
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // Сериализуем
            marshaller.marshal(this, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (IOException ex) {
            LOG.error("Ошибка ввода/вывода.", ex);
        } catch (JAXBException ex) {
            LOG.error("Ошибка сборки XML.", ex);
        }
        return xml;
    }

    public static Actor fromXML(String xml) {
        Actor result = null;
        try (StringReader reader = new StringReader(xml)) {
            // Получаем контекст для доступа к АПИ
            JAXBContext context = JAXBContext.newInstance(Actor.class);
            // Для десериализации нам нужно создать десериализатор
            Unmarshaller unmarshaller = context.createUnmarshaller();
            // десериализуем
            result = (Actor) unmarshaller.unmarshal(reader);
            System.out.println(result);
        } catch (JAXBException ex) {
            LOG.error("Ошибка разбора XML.", ex);
        }
        return result;
    }
}