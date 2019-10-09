package com.dong.web.common.controller;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;


/**
 * 导出excel
 */
public class ExportExcelController {

    private static final Logger log = LoggerFactory.getLogger(ExportExcelController.class);

    /**
     * 导出功能
     * 注意：泛型T类字段名和containBean集合里字段名字的一致性
     *
     * @param response
     * @param title       表名
     * @param headers     表头
     * @param list        数据集
     * @param containBean 数据集类型字段
     * @param <T>
     * @throws Exception
     */
    public static <T> void exportExcel(HttpServletResponse response, String title, String[] headers, List<T> list, List<String> containBean) {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(); // 创建一个Excel文件
            HSSFSheet sheet = workbook.createSheet(title);// 创建一个Excel的Sheet
            HSSFRow row = sheet.createRow(0);
            //创建第一行表头
            for (short i = 0; i < headers.length; i++) {
                HSSFCell cell = row.createCell(i);
                HSSFRichTextString text = new HSSFRichTextString(headers[i]);
                cell.setCellValue(text);
            }
            Iterator<T> it = list.iterator();
            int index = 0;
            while (it.hasNext()) {
                index++;
                row = sheet.createRow(index);
                T t = (T) it.next();
                /*反射得到字段*/
                Field[] fields = t.getClass().getDeclaredFields();
                /*如果需要匹配*/
                if (CollectionUtils.isNotEmpty(containBean)) {
                    for (int j = 0; j < containBean.size(); j++) {
                        for (Field field : fields) {
                            if (!field.getName().equals(containBean.get(j)))
                                continue;
                            /*给每一列set值*/
                            setCellValue(t, field, row, j);
                        }
                    }
                } else {
                    for (int i = 0; i < fields.length; i++) {
                        Field field = fields[i];
                        setCellValue(t, field, row, i);
                    }
                }
            }
            /*application/vnd.ms-excel告诉浏览器要下载的是个excel*/
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            /*请求头设置，Content-Disposition为下载标识，attachment标识以附件方式下载*/
            response.addHeader("Content-Disposition", "attachment;filename=" + new String((title).getBytes(), "ISO8859-1") + ".xls");
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
    }
}
