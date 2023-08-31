package com.base.lx.service;

import com.alibaba.fastjson.JSONObject;
import com.base.lx.LedgerAccountDO;
import com.base.lx.mapper.TAcquiringSplitMapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TestService {

    private static final int a = 0;
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private static final int e = 4;
    private static final int f = 5;
    private static final int g = 6;
    private static final int h = 7;
    private static final int i = 8;
    private static final int j = 9;
    private static final int k = 10;
    private static final int l = 11;
    private static final int m = 12;
    private static final int n = 13;
    private static final int o = 14;
    private static final int p = 15;
    private static final int q = 16;
    private static final int r = 17;
    private static final int s = 18;
    private static final int t = 19;
    private static final int u = 20;
    private static final int v = 21;
    private static final int w = 22;
    private static final int x = 23;
    private static final int y = 24;
    private static final int z = 25;
    private static final int aa = 26;

    @Autowired
    private TAcquiringSplitMapper tAcquiringSplitMapper;

    public void testToSql() {
        try (FileInputStream fis = new FileInputStream("C:\\Users\\T470\\Desktop\\0817\\T_GUARANTEE_CONFIRM_202307.xlsx");
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            // 获取第一个工作表
            Iterator<Row> rowIterator = workbook.getSheetAt(0).iterator();
            // 跳过表头
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }
            // 遍历每一行数据
            AtomicInteger atomicInteger = new AtomicInteger(1);
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                String ab = getCellValue(row.getCell(a));
                if (!ab.equals("")) {
                    List<LedgerAccountDO> ledgerAccountDO = JSONObject.parseArray(getCellValue(row.getCell(p)), LedgerAccountDO.class);
                    ledgerAccountDO.forEach(ea -> {
                        HashMap<String, Object> map = tAcquiringSplitMapper.selectByMerchantNoAndConfirmOrderNoAndConfirmTradeNo(ea.getMerchantNo(), getCellValue(row.getCell(h)), getCellValue(row.getCell(i)));
                        if (map != null) {
//                            System.out.println("insert into T_SPLIT_REFUND_"+ getCellValue(row.getCell(b)).replace("-","").substring(0,6) +" (ORIGINAL_TRADE_NO,SUB_TRADE_NO, MERCHANT_NO,MERCHANT_NAME,REQUEST_DATE,AMOUNT,REFUND_AMT,CREATED_BY,CREATED_AT,UPDATED_BY," +
//                                    "UPDATED_AT,REMARK,OUT_REFUND_NO,OUT_TRADE_NO,refund_reason,ORDER_TYPE,CONFIRM_OUT_TRADE_NO,CONFIRM_TRADE_NO,CANCEL_TRADE_NO,TRADE_NO,REFUND_STATUS) VALUES(" +
//                                    "'" + map.get("SUB_TRADE_NO") + "'," +
//                                    "'" + atomicInteger.get() + "'," +
//                                    "'" + ea.getMerchantNo() + "'," +
//                                    "'" + getCellValue(row.getCell(g)) + "'," +
//                                    "'" + getCellValue(row.getCell(b)) + "'," +
//                                    getCellValue(row.getCell(q)) + "," +
//                                    getCellValue(row.getCell(r)) + "," +
//                                    "'" + ea.getMerchantNo() + "'," +
//                                    "'" + getCellValue(row.getCell(w)) + "'," +
//                                    "'" + ea.getMerchantNo() + "'," +
//                                    "'" + getCellValue(row.getCell(d)) + "'," +
//                                    "'" + getCellValue(row.getCell(s)) + "'," +
//                                    "'" + getCellValue(row.getCell(k)) + "'," +
//                                    "'" + getCellValue(row.getCell(z)) + "'," +
//                                    "'" + getCellValue(row.getCell(x)) + "'," +
//                                    "'" + "DELAYED_SPLIT" + "'," +
//                                    "'" + getCellValue(row.getCell(h)) + "'," +
//                                    "'" + getCellValue(row.getCell(i)) + "'," +
//                                    "'" + getCellValue(row.getCell(l)) + "'," +
//                                    "'" + getCellValue(row.getCell(aa)) + "'," +
//                                    "'" + getCellValue(row.getCell(o)) + "');");
//                            atomicInteger.set(atomicInteger.get() + 1);

                            System.out.println("invoke com.bestpay.aggregate.acquiring.service.api.CompensateOrderService.compensateSplitRefund({" +
                                    "\"class\":\"com.bestpay.aggregate.acquiring.service.api.request.CompensateSplitRefundRequest" + "\",\"" + "traceLogId\":\"" + 1231312 + "\",\"" + "merchantNo\":\"" + ea.getMerchantNo() + "\",\"" + "requestDate\":\"" + getCellValue(row.getCell(b)).substring(0,10) + "\",\"" + "originalTradeNo\":\"" + map.get("SUB_TRADE_NO")+"\"" + "});");
                        }
                    });
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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
