package egd.sat.logparser.service;

import java.io.File;
import java.util.List;

import egd.sat.logparser.entity.TableEntityObj;

public interface FileParserSerivice {

	List<TableEntityObj> analize(File parentPath, String filename, List<String> strings);


}
