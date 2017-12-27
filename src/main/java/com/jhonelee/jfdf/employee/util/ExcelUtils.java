package com.jhonelee.jfdf.employee.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ExcelUtils {

    public static ArrayList<ArrayList<String>> importExcel(InputStream inputStream) {
        ArrayList<ArrayList<String>> rowList = new ArrayList<>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            for (int rowNum = 1; rowNum < sheet.getPhysicalNumberOfRows(); rowNum++) {
                ArrayList<String> cellList = new ArrayList<>();
                Row row = sheet.getRow(rowNum);
                for (int cellNum = 0; cellNum < row.getPhysicalNumberOfCells(); cellNum++ ) {
                    Cell cell = row.getCell(cellNum);
                    cellList.add(cell.getStringCellValue());
                }
                rowList.add(cellList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

        return rowList;
    }

}
