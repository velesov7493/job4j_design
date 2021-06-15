package ru.job4j.io.duplicates;

import java.util.Objects;

public class FileProperty {

    private long size;
    private String name;

    public FileProperty(long aSize, String aName) {
        size = aSize;
        name = aName;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileProperty that = (FileProperty) o;
        return size == that.size && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return ((int) (size >>> 32) * 31) ^ ((int) size * 31) ^ name.hashCode();
    }
}
