package egd.sat.logparser.service;

import java.util.List;

import egd.sat.logparser.entity.TableEntityObj;

public interface FileParserSerivice {

    List<TableEntityObj> analize(String filename, List<String> strings);


}
