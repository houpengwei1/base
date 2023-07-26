package com.base.lx;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.UUID;

public class ExcelParser {

    private static final int BANK_NAME_COLUMN = 0;
    private static final int BANK_CODE_COLUMN = 1;
    private static final int INSTALLMENT_COLUMN = 2;
    private static final int INTEREST_COLUMN = 3;
    private static final int DISCOUNT_MODE_COLUMN = 4;
    private static final int MIN_AMOUNT_COLUMN = 5;
    private static final int MAX_AMOUNT_COLUMN = 6;

    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("C:\\Users\\T470\\Desktop\\1.xlsx");
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            // 获取第一个工作表
            Iterator<Row> rowIterator = workbook.getSheetAt(0).iterator();
            // 跳过表头
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }
            // 遍历每一行数据
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                System.out.println("INSERT INTO telecom_config.t_credit_stages_config" +
                        "(DISCOUNT_TYPE, BANK_CODE, BANK_NAME, INSTALMENT, INTEREST_RATE, MIN_AMT, MAX_AMT, CONFIG_CODE, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY) VALUES("+"'"+getCellValue(row.getCell(DISCOUNT_MODE_COLUMN))+"',"+"'"+getCellValue(row.getCell(BANK_CODE_COLUMN))+
                        "',"+"'"+getCellValue(row.getCell(BANK_NAME_COLUMN))+"',"+"'"+getCellValue(row.getCell(INSTALLMENT_COLUMN))+"',"+"'"+getCellValue(row.getCell(INTEREST_COLUMN))+"',"+
                        getCellValue(row.getCell(MIN_AMOUNT_COLUMN))+","+getCellValue(row.getCell(MAX_AMOUNT_COLUMN))+","+"'"+codeCreate()+"',"+"'"+ "2023-07-24 09:14:07.000" +
                        "',"+"'"+ "houpengwei" +"',"+"'"+ "2023-07-24 09:14:07.000" +"',"+ "houpengwei" +"')");
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成关联code唯一编码
     */
    public static String codeCreate() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "").substring(0, 10);
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            default:
                return "";
        }
    }

}
