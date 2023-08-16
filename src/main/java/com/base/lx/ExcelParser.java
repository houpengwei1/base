package com.base.lx;

import com.alibaba.fastjson.JSONObject;
import com.base.lx.mapper.TAcquiringSplitMapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class ExcelParser {

    private static final int a = 0;
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private static final int e = 4;
    private static final int f = 5;
    private static final int g = 6;
    private static final int h = 7;
    private static final int j = 8;
    private static final int k = 9;
    private static final int l = 10;
    private static final int m = 11;
    private static final int n = 12;
    private static final int o = 13;
    private static final int p = 14;
    private static final int q = 15;
    private static final int r = 16;
    private static final int s = 17;
    private static final int t = 18;
    private static final int u = 19;
    private static final int v = 20;
    private static final int w = 21;
    private static final int x = 22;
    private static final int y = 23;
    private static final int z = 24;
    private static final int aa = 25;
    private static final int ac = 25;

    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("C:\\Users\\T470\\Desktop\\0816\\T_GUARANTEE_CONFIRM_202302.xlsx");
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

                String ab = getCellValue(row.getCell(a));
//                if(!a.equals("")){
//                    String v =a.split("\\.")[0];
//                    int b =  Integer.parseInt(v);
//                    System.out.println("INSERT INTO telecom_config.t_credit_stages_config" +
//                            "(DISCOUNT_TYPE, BANK_CODE, BANK_NAME, INSTALMENT, INTEREST_RATE, MIN_AMT, MAX_AMT, CONFIG_CODE, CREATED_AT, CREATED_BY, UPDATED_AT, UPDATED_BY) VALUES("+"'"+getCellValue(row.getCell(DISCOUNT_MODE_COLUMN))+"',"+"'"+getCellValue(row.getCell(BANK_CODE_COLUMN))+
//                            "',"+"'"+getCellValue(row.getCell(BANK_NAME_COLUMN))+"',"+"'"+b+"',"+"'"+getCellValue(row.getCell(INTEREST_COLUMN))+"',"+
//                            getCellValue(row.getCell(MIN_AMOUNT_COLUMN))+","+getCellValue(row.getCell(MAX_AMOUNT_COLUMN))+","+"'"+codeCreate()+"',"+"'"+ "2023-07-24 09:14:07.000" +
//                            "',"+"'"+ "houpengwei" +"',"+"'"+ "2023-07-24 09:14:07.000" +"',"+"'"+"houpengwei" +"');");
//                }
//                if(!a.equals("")){
//                    String v =a.split("\\.")[0];
//                    int b =  Integer.parseInt(v);
//                    System.out.println("invoke com.bestpay.aggregate.acquiring.service.api.CreditStagesConfigService.operateCreditStagesConfig({\"class\":\"com.bestpay.aggregate.acquiring.service.api.request.CreditStagesConfigOperateRequest"+"\",\""+"traceLogId\":\""+1231312+"\",\""+"discountType\":\""+getCellValue(row.getCell(DISCOUNT_MODE_COLUMN))+"\",\""+"bankCode\":\""+getCellValue(row.getCell(BANK_CODE_COLUMN))+"\",\""+"bankName\":\""+getCellValue(row.getCell(BANK_NAME_COLUMN))+"\",\""+"configCode\":\""+codeCreate()+"\",\""+"interestRate\":\""
//                            +getCellValue(row.getCell(INTEREST_COLUMN))+"\",\""+"instalment\":\""+getCellValue(row.getCell(INSTALLMENT_COLUMN))+"\",\""+"minAmt\":\""+getCellValue(row.getCell(MIN_AMOUNT_COLUMN))+"\",\""+"maxAmt\":\""+getCellValue(row.getCell(MAX_AMOUNT_COLUMN))+"\",\""+"createBy\":\""+"houpengwei"+"\",\""+"type\":\""+"INSERT\"})");
//                }
                if(!ab.equals("")){
                    List<LedgerAccountDO> ledgerAccountDO = JSONObject.parseArray(getCellValue(row.getCell(q)),LedgerAccountDO.class);

                    ledgerAccountDO.forEach(ea->{
                        System.out.println("INSERT INTO telecom_config.t_acquiring_split" +
                                "(MERCHANT_NO, MERCHANT_NAME, TRADE_NO, SUB_TRADE_NO, AMOUNT, REQUEST_DATE, REFUND_AMT, CREATED_BY, CREATED_AT, UPDATED_BY, UPDATED_AT, REMARK, FEE_AMT," +
                                " CONFIRM_OUT_TRADE_NO, CONFIRM_TRADE_NO, ORDER_TYPE, TRADE_STATUS, OUT_TRADE_NO, CONFIRM_REQUEST_DATE) VALUES(" +
                                "'" + getCellValue(row.getCell(g)) + "'," +
                                "'" + getCellValue(row.getCell(h)) + "'," +
                                "'" + getCellValue(row.getCell(aa)) + "'," +
                                "'" + "SUB_TRADE_NO" + "'," +
                                getCellValue(row.getCell(r)) + "," +
                                "'" + getCellValue(row.getCell(c)) + "'," +
                                getCellValue(row.getCell(s)) + "," +
                                "'" + ea.getMerchantNo() + "'," +
                                "'" + getCellValue(row.getCell(x)) + "'," +
                                "'" + ea.getMerchantNo() + "'," +
                                "'" + getCellValue(row.getCell(e)) + "'," +
                                "'" + getCellValue(row.getCell(t)) + "'," +
                                "" + "," +
                                "'" + getCellValue(row.getCell(l)) + "'," +
                                "'" + getCellValue(row.getCell(m)) + "'," +
                                "'" + "DELAYED_SPLIT" + "'," +
                                "'" + getCellValue(row.getCell(p)) + "'," +
                                "'" + getCellValue(row.getCell(ac)) + "'," +
                                "'" + getCellValue(row.getCell(n)) + "');");
                    });
                }

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
                return String.valueOf(cell.getNumericCellValue());
            default:
                return "";
        }
    }

}
