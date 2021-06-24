package ru.job4j.jbdc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private static class User {
        private String name;
        private String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    private static final Logger LOG = LoggerFactory.getLogger(ImportDB.class.getName());

    private String dumpFileName;
    private Properties cfg;

    public ImportDB(String aDumpFileName, String aCfgFileName) {
        cfg = new Properties();
        try (FileInputStream in = new FileInputStream(aCfgFileName)) {
            cfg.load(in);
        } catch (IOException ex) {
            LOG.error("Ошибка чтения файла свойств: ", ex);
        }
        dumpFileName = aDumpFileName;
    }

    public List<User> load() {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dumpFileName))) {
            rd.lines().forEach((l) -> {
                String[] words = l.split(";");
                users.add(new User(words[0], words[1]));
            });
        } catch (IOException ex) {
            LOG.error("Ошибка чтения исходного текстового файла: ", ex);
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        if (users.isEmpty()) {
            return;
        }
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            String query = "insert into tz_users (name, email) values (?, ?);";
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement(query)) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ImportDB db = new ImportDB("./data/dump.txt", "app.properties");
        db.save(db.load());
    }
}
