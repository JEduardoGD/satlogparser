package egd.sat.logparser.entity;

import java.util.List;

public class TableEntityObj {
    private String tableName;
    private List<String> strings;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

}
