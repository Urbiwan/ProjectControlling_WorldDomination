package edu.hm.dropwizard.excel;

import edu.hm.dropwizard.core.request.JSONChild;
import edu.hm.dropwizard.core.request.JSONRequest;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Extend this class with a factory to support more than one type of excel files.
 */
public class ExcelParser {
    private static final int CELL_MA_ID = 0;
    private static final int CELL_EMPLOYEE = 1;
    private static final int CELL_EMPLOYEE_TIER = 2;
    private static final int CELL_HOURS = 3;
    private static final int CELL_MONTH = 4;
    private static final int CELL_YEAR = 5;
    private static final int CELL_PROJECT = 6;
    private static final int CELL_DEPARTMENT = 7;
    private static final int CELL_ACCOUNT = 8;
    private static final int CELL_FAKT = 9;
    private static final int CELL_COST_LIMIT = 10;
    private static final int CELL_COST_RATE = 11;

    public static JSONRequest getData(InputStream stream) throws IOException, InvalidFormatException {
        Workbook wb = WorkbookFactory.create(stream);
        Sheet sheet = wb.getSheetAt(0);
        List<JSONChild> children = new ArrayList<>();

        for (Row row : sheet) {
            if(row.getRowNum() == 0)
                continue;

            //Okay this sucks hard, delegate to class which encapsulates this stuff.
            children.add(new JSONChild((int) row.getCell(CELL_MA_ID).getNumericCellValue(),
                    row.getCell(CELL_EMPLOYEE).getStringCellValue(), (int) row.getCell(CELL_EMPLOYEE_TIER).getNumericCellValue(),
                    (float) row.getCell(CELL_HOURS).getNumericCellValue(), (int) row.getCell(CELL_MONTH).getNumericCellValue(),
                    (int) row.getCell(CELL_YEAR).getNumericCellValue(), row.getCell(CELL_PROJECT).getStringCellValue(),
                    row.getCell(CELL_DEPARTMENT).getStringCellValue(), row.getCell(CELL_ACCOUNT).getStringCellValue(),
                    getBooleanFromText(row.getCell(CELL_FAKT)), (float) row.getCell(CELL_COST_LIMIT).getNumericCellValue(),
                    (float) row.getCell(CELL_COST_RATE).getNumericCellValue()));

        }

        return new JSONRequest(children.toArray(new JSONChild[0]));
    }

    private static boolean getBooleanFromText(Cell cell) {
        int cellType = cell.getCellType();

        if(cellType == Cell.CELL_TYPE_STRING) {
            String value = cell.getStringCellValue();

            if(value.equals("true")) {
                return true;
            }
            else if (value.equals("false")) {
                return false;
            }

            return false;
        }
        else if (cellType == Cell.CELL_TYPE_BOOLEAN) {
            return cell.getBooleanCellValue();
        }

        return false;
    }
}
