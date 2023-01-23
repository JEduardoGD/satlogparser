package egd.sat.logparser.entity;

public class TableDataObj {
    private int rowNum;
    private String filenName;
    private String tableName;
    private String columnName;
    private String columnValue;
    private int colCount;

    public String getFilenName() {
        return filenName;
    }

    public void setFilenName(String filenName) {
        this.filenName = filenName;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnValue() {
        return columnValue;
    }

    public void setColumnValue(String columnValue) {
        this.columnValue = columnValue;
    }

    public int getColCount() {
        return colCount;
    }

    public void setColCount(int colCount) {
        this.colCount = colCount;
    }

}
