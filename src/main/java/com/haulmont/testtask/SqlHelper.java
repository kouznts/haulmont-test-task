package com.haulmont.testtask;

public final class SqlHelper {
    private static final String SELECT = "SELECT";
    private static final String ALL = "*";
    private static final String FROM = "FROM";
    private static final String WHERE = "WHERE";

    public static String getSelectAllFromTableWhereAttrEqualsVal(
            String table,
            String attr,
            String val
    ) {
        String temp = SELECT + ' ' + ALL + ' ' +
                FROM + ' ' + table + ' ' +
                WHERE + ' ' + attr + ' ' + '=' + ' ' + val;
        return temp;
    }
}
