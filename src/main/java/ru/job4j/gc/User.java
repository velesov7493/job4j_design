package ru.job4j.gc;

public class User {

    private int id;
    private String name;

    public User(int aId, String aName) {
        id = aId;
        name = aName;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Уничтожение объекта " + this);
    }

    @Override
    public String toString() {
        return
                "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}