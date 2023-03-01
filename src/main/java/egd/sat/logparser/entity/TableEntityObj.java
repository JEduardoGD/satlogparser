package egd.sat.logparser.entity;

import java.io.File;
import java.util.List;

public class TableEntityObj {
    private String filename;
    private String tableName;
    private List<String> strings;
    private File route;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

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

	public File getRoute() {
		return route;
	}

	public void setRoute(File route) {
		this.route = route;
	}

}
