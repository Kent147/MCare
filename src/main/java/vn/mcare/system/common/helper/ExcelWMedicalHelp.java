package vn.mcare.system.common.helper;

public class ExcelWMedicalHelp {
//    public List<GetAllMedicineOutput> ExcelHelper(String excelFilePath) throws IOException {
//
//        List<GetAllMedicineOutput> listCostomers = new ArrayList<GetAllMedicineOutput>();
//        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
//
//        Workbook workBook = getWorkbook(inputStream, excelFilePath);
//        Sheet firstSheet = workBook.getSheetAt(0);
//        Iterator<Row> rows = firstSheet.iterator();
//
//        while (rows.hasNext()) {
//            Row row = rows.next();
//            Iterator<Cell> cells = row.cellIterator();
//            GetAllMedicineOutput medicine = new GetAllMedicineOutput();
//
//            while (cells.hasNext()) {
//                Cell cell = cells.next();
//                int columnIndex = cell.getColumnIndex();
//
//                switch (columnIndex) {
//                    case 0:
//                        medicine.setMedicine_id((String) getCellValue(cell));
//                        break;
//                    case 1:
//                        medicine.setMedicine_name((String) getCellValue(cell));
//                        break;
//                    case 2:
//                        medicine.setSource((String) getCellValue(cell));
//                        break;
//                }
//            }
//            listCostomers.add(medicine);
//        }
//
//        workBook.close();
//        inputStream.close();
//
//        return listCostomers;
//    }
//
//    private Object getCellValue(Cell cell) {
//        switch (cell.getCellType()) {
//            case Cell.CELL_TYPE_STRING:
//                return cell.getStringCellValue();
//
//            case Cell.CELL_TYPE_BOOLEAN:
//                return cell.getBooleanCellValue();
//
//            case Cell.CELL_TYPE_NUMERIC:
//                return cell.getNumericCellValue();
//        }
//
//        return null;
//    }
//
//    private Workbook getWorkbook(FileInputStream inputStream, String excelFilePath) throws IOException {
//        Workbook workbook = null;
//
//        if (excelFilePath.endsWith("xlsx")) {
//            workbook = new XSSFWorkbook(inputStream);
//        } else if (excelFilePath.endsWith("xls")) {
//            workbook = new HSSFWorkbook(inputStream);
//        } else {
//            throw new IllegalArgumentException("The specified file is not Excel file");
//        }
//
//        return workbook;
//    }
}
