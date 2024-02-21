package vn.mcare.system.common.helper;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import vn.mcare.system.common.pojo.api.output.GetCustomerOutput;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelWCustomerHelper {
    public List<GetCustomerOutput> ExcelHelper(String excelFilePath) throws IOException {

        List<GetCustomerOutput> listCustomers = new ArrayList<GetCustomerOutput>();
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

        Workbook workBook = getWorkbook(inputStream, excelFilePath);
        Sheet firstSheet = workBook.getSheetAt(0);
        Iterator<Row> rows = firstSheet.iterator();

        while (rows.hasNext()) {
            Row row = rows.next();
            Iterator<Cell> cells = row.cellIterator();
            GetCustomerOutput customer = new GetCustomerOutput();

            while (cells.hasNext()) {
                Cell cell = cells.next();
                int columnIndex = cell.getColumnIndex();

                switch (columnIndex) {
                    case 0:
                        customer.setCif((String) getCellValue(cell));
                        break;
                    case 1:
                        customer.setFullName((String) getCellValue(cell));
                        break;
                    case 2:
                        customer.setGender((int) getCellValue(cell));
                        break;
                    case 3:
                        customer.setBod((Long)getCellValue(cell));
                        break;
                    case 4:
                        customer.setCardId((String) getCellValue(cell));
                        break;
                    case 5:
                        customer.setPhone((String) getCellValue(cell));
                        break;
                    case 6:
                        customer.setEmail((String) getCellValue(cell));
                        break;
                    case 7:
                        customer.setNation((String) getCellValue(cell));
                        break;
                    case 8:
                        customer.setCareer((String) getCellValue(cell));
                    case 9:
                        customer.setStreet((String) getCellValue(cell));
                    case 10:
                        customer.setWard((String) getCellValue(cell));
                    case 11:
                        customer.setDistrict((String) getCellValue(cell));
                    case 12:
                        customer.setProvince((String) getCellValue(cell));
                    case 13:
                        customer.setDescription((String) getCellValue(cell));

                }
            }
            listCustomers.add(customer);
        }

        workBook.close();
        inputStream.close();

        return listCustomers;
    }

    private Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();

            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();

            case Cell.CELL_TYPE_NUMERIC:
                return cell.getNumericCellValue();
        }

        return null;
    }

    private Workbook getWorkbook(FileInputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;

        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

}

