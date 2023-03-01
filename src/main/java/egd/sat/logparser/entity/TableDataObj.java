package egd.sat.logparser.entity;

import java.io.File;

public class TableDataObj {
    private int rowNum;
    private String fileName;
    private String tableName;
    private String columnName;
    private String columnValue;
    private int colCount;
    private File parentPath;
    
    public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

	public File getParentPath() {
		return parentPath;
	}

	public void setParentPath(File parentPath) {
		this.parentPath = parentPath;
	}
    
}
