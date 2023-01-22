package egd.sat.logparser.service;

import java.util.List;

import egd.sat.logparser.entity.TableEntityObj;

public interface FileParserSerivice {

    List<TableEntityObj> analize(List<String> strings);


}
