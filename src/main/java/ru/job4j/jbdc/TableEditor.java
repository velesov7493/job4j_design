package ru.job4j.jbdc;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(String propertiesFileName) {
        properties = new Properties();
        try (BufferedReader br = new BufferedReader(new FileReader(propertiesFileName))) {
            properties.load(br);
            initConnection();
        } catch (IOException ex) {
            System.out.println("Ошибка чтения файла свойств: " + ex);
        }
    }

    private void initConnection() {
        try {
            Class.forName(properties.getProperty("driverClass"));
            String url = properties.getProperty("url");
            String login = properties.getProperty("user");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, login, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Класс org.postgresql.Driver не найден.");
            System.out.print("Проверьте работоспособность и доступность библиотеки");
            System.out.println(" с jdbc-драйвером postgreSQL");
            System.exit(2);
        } catch (SQLException ex) {
            System.out.println("Ошибка при создании соединения: " + ex);
            connection = null;
        }
    }

    private void executeSQL(String query) {
        try (Statement s = connection.createStatement()) {
            s.execute(query);
        } catch (SQLException ex) {
            System.out.println(
                    "Ошибка выполнения SQL инструкции: "
                    + query + System.lineSeparator() + ex
            );
        }
    }

    public void createTable(String tableName) {
        String sql = String.format("CREATE TABLE %s ();", tableName);
        executeSQL(sql);
    }

    public void dropTable(String tableName) {
        String sql = String.format("DROP TABLE %s;", tableName);
        executeSQL(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format("ALTER TABLE %s ADD COLUMN %s %s;", tableName, columnName, type);
        executeSQL(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format("ALTER TABLE %s DROP COLUMN %s;", tableName, columnName);
        executeSQL(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s;",
                tableName, columnName, newColumnName
        );
        executeSQL(sql);
    }

    public String getScheme(String tableName) throws SQLException {
        StringBuilder scheme = new StringBuilder();
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet columns = metaData.getColumns(null, null, tableName, null)) {
            scheme.append(String.format("%-15s %-15s%n", "column", "type"));
            while (columns.next()) {
                scheme.append(String.format("%-15s %-15s%n",
                        columns.getString("COLUMN_NAME"),
                        columns.getString("TYPE_NAME")));
            }
        }
        return scheme.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        TableEditor editor = new TableEditor("app.properties");
        editor.createTable("tz_editor1");
        editor.addColumn("tz_editor1", "id", "INTEGER");
        editor.addColumn("tz_editor1", "dropTest", "VARCHAR(100)");
        editor.addColumn("tz_editor1", "renameTest", "VARCHAR(100)");
        System.out.println(editor.getScheme("tz_editor1"));
        System.out.println("-------------------------------------------------------------------");
        editor.dropColumn("tz_editor1", "dropTest");
        editor.renameColumn("tz_editor1", "renameTest", "renameSuccess");
        System.out.println(editor.getScheme("tz_editor1"));
        editor.dropTable("tz_editor1");
        editor.close();
    }
}
