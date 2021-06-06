package ru.job4j.exam.struct;

import java.util.*;

/**
 * Добавленные - с id, отсутствующем в исходном списке
 * Измененные - с существующим id, но другим именем
 * Удаленные - id отсутствует в текущем списке
 */

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
        int added = 0;
        int changed = 0;
        int deleted = 0;
        HashMap<Integer, User> hPrevious = new HashMap<>();
        for (User prevUser : previous) {
            hPrevious.put(prevUser.id, prevUser);
        }
        HashMap<Integer, User> hCurrent = new HashMap<>();
        for (User curUser : current) {
            hCurrent.put(curUser.id, curUser);
        }
        for (int currentId : hCurrent.keySet()) {
            User prevUser = hPrevious.get(currentId);
            if (prevUser == null) {
                added++;
            }
        }
        for (int prevId : hPrevious.keySet()) {
            User curUser = hCurrent.get(prevId);
            User prevUser = hPrevious.get(prevId);
            if (curUser == null) {
                deleted++;
            } else if (!curUser.name.equals(prevUser.name)) {
                changed++;
            }
        }
        return new Info(added, changed, deleted);
    }
}
