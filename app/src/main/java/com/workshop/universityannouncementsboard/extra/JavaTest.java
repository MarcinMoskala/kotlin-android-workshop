package com.workshop.universityannouncementsboard.extra;

import java.util.HashSet;

public class JavaTest {

    public static void main(String[] args) {
        HashSet<User> set = new HashSet<>();
        User user = new User("Marcin", "Moskala");

        user.setName("Michal");
        set.add(user);

        System.out.println(set); // [User{name='Michal', surname='Moskala'}]
        System.out.println(user); // User{name='Michal', surname='Moskala'}
        System.out.println(set.contains(user)); // false

    }

    static class User {
        String name;
        String surname;

        public User(String name, String surname) {
            this.name = name;
            this.surname = surname;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            User user = (User) o;

            if (name != null ? !name.equals(user.name) : user.name != null) return false;
            return surname != null ? surname.equals(user.surname) : user.surname == null;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (surname != null ? surname.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", surname='" + surname + '\'' +
                    '}';
        }
    }
}