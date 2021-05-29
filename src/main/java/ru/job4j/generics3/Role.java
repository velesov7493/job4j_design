package ru.job4j.generics3;

import java.util.Objects;

public final class Role extends Base {

    private long flags;

    public Role(String id, long aFlags) {
        super(id);
        flags = aFlags;
    }

    @Override
    public String getId() {
        return super.getId();
    }

    public long getFlags() {
        return flags;
    }

    public void setFlags(long flags) {
        this.flags = flags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return flags == role.flags;
    }

    @Override
    public int hashCode() {
        return Objects.hash(flags);
    }
}
