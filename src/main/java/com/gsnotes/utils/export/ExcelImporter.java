package com.gsnotes.utils.export;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelImporter {
    public static ArrayList<ArrayList<Object>> importExcel(String filename) {
        Workbook workbook = null;

        ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();

        try {
            try {
                FileInputStream excelFile = new FileInputStream(new File(filename));
                workbook = new XSSFWorkbook(excelFile);
                Sheet sheet = workbook.getSheetAt(0);
                Iterator<Row> rowIt = sheet.iterator();

                while (rowIt.hasNext()) {
                    ArrayList<Object> row = new ArrayList<Object>();
                    Iterator<Cell> cellIt = rowIt.next().iterator();
                    while (cellIt.hasNext()) {
                        Cell cell = cellIt.next();
                        if (cell.getCellType() == CellType.STRING) {
                            row.add(cell.getStringCellValue());
                            //System.out.println(cell.getStringCellValue());
                        }
                        else if (cell.getCellType() == CellType.NUMERIC) {
                            row.add(cell.getNumericCellValue());
                            //System.out.println(cell.getNumericCellValue());
                        }
                    }
                    data.add(row);
                }
            } finally {
                if (workbook != null)
                    workbook.close();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return data;
    }
}