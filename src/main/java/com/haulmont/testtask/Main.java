package com.haulmont.testtask;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
        }
    }
}
