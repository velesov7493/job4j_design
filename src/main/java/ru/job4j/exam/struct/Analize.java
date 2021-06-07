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

    public Info diff(List<User> previous, List<User> current) {
        int added;
        int changed = 0;
        int deleted = 0;
        HashMap<Integer, User> hPrevious = new HashMap<>();
        for (User prevUser : previous) {
            hPrevious.put(prevUser.id, prevUser);
        }
        for (int prevId : hPrevious.keySet()) {
            User prevUser = hPrevious.get(prevId);
            int i = current.indexOf(prevUser);
            if (i < 0) {
                deleted++;
            } else {
                User curUser = current.get(i);
                if (!curUser.name.equals(prevUser.name)) {
                    changed++;
                }
            }
        }
        added = current.size() - previous.size() + deleted;
        return new Info(added, changed, deleted);
    }
}
