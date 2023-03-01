package egd.sat.logparser.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import egd.sat.logparser.entity.TableEntityObj;
import egd.sat.logparser.service.FileParserSerivice;

@Service
public class FileParserSeriviceImpl implements FileParserSerivice {
    private static final String REGEX_GUION = "^(-)+$";

    @Override
    public List<TableEntityObj> analize(File parentPath, String filename, List<String> strings) {
        List<TableEntityObj> tableEntityObjList = new ArrayList<>();
        Pattern patternGuion = Pattern.compile(REGEX_GUION);
        for (int i = 0; i < strings.size(); i++) {
            Matcher matcher = patternGuion.matcher(strings.get(i));
            if (matcher.matches()) {
                TableEntityObj tableEntityObj = new TableEntityObj();

                String tableName = strings.get(i - 1);
                List<String> listStrings = new ArrayList<>();

                for (int j = i + 1; j < strings.size(); j++) {
                    String testString = strings.get(j).trim();
                    if (testString.length() > 0) {
                        listStrings.add(testString);
                    }
                    else {
                        break;
                    }
                }
                
                tableEntityObj.setFilename(filename + ".xlsx");
                tableEntityObj.setTableName(tableName);
                tableEntityObj.setStrings(listStrings);
                tableEntityObj.setRoute(parentPath);
                tableEntityObjList.add(tableEntityObj);
            }
        }
        return tableEntityObjList;
    }
}
