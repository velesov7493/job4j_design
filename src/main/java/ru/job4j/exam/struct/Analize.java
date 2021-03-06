package ru.job4j.exam.struct;

import java.util.*;

public class Analize {

    final static class Info {

        private int added;
        private int changed;
        private int deleted;

        public Info(int aAdded, int aChanged, int aDeleted) {
            added = aAdded;
            changed = aChanged;
            deleted = aDeleted;
        }

        public int getAdded() {
            return added;
        }

        public int getChanged() {
            return changed;
        }

        public int getDeleted() {
            return deleted;
        }
    }

    final static class User {

        private int id;
        private String name;

        public User(int aId, String aName) {
            id = aId;
            name = aName;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id;
        }

        @Override
        public int hashCode() {
            return id;
        }
    }

    private long previousCode(int id) {
        return (long) id << 32;
    }

    private boolean isPreviousCode(long code) {
        return code > Integer.MAX_VALUE;
    }

    public Info diff(List<User> previous, List<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        HashMap<Long, User> users = new HashMap<>();
        for (User prevUser : previous) {
            users.put(previousCode(prevUser.id), prevUser);
        }
        for (User currUser : current) {
            users.put((long) currUser.id, currUser);
        }
        for (long code  : users.keySet()) {
            User u1 = users.get(code);
            if (isPreviousCode(code)) {
                User u2 = users.get((long) u1.id);
                if (u2 == null) {
                    deleted++;
                } else if (!u2.name.equals(u1.name)) {
                    changed++;
                }
            } else {
                User u2 = users.get(previousCode(u1.id));
                if (u2 == null) {
                    added++;
                }
            }
        }
        return new Info(added, changed, deleted);
    }
}
