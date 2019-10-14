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
 * 一个Excel文件对应于一个workbook(HSSFWorkbook)，
 * 一个workbook可以有多个sheet（HSSFSheet）组成，
 * 一个sheet是由多个row（HSSFRow）组成，
 * 一个row是由多个cell（HSSFCell）组成
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
            /*
                1：用HSSFWorkbook打开或者创建"Excel文件对象"
                2：用HSSFWorkbook返回或者创建HSSFSheet对象
                3：用HSSFSheet对象返回或者创建HSSFRow对象
                4：用HSSFRow对象返回或者创建HSSFCell对象
                5：对HSSFCell对象进行读写
             */
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
//                            setCellValue(t, field, row, j);
                        }
                    }
                } else {
                    for (int i = 0; i < fields.length; i++) {
                        Field field = fields[i];
//                        setCellValue(t, field, row, i);
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
//            if (workbook != null) {
//                workbook.close();
//            }
        }
    }

    /**
     * 检查数据配置问题
     *
     * @throws IOException 抛出数据异常类
     */
    /*protected void checkConfig() throws IOException {
        if (heardKey == null || heardList.length == 0) {
            throw new IOException("列名数组不能为空或者为NULL");
        }

        if (fontSize < 0 || rowHeight < 0 || columWidth < 0) {
            throw new IOException("字体、宽度或者高度不能为负值");
        }

        if (Strings.isNullOrEmpty(sheetName)) {
            throw new IOException("工作表表名不能为NULL");
        }
    }*/
}
