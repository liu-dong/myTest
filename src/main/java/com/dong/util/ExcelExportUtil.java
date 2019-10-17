package com.dong.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 导出excel工具类
 * 可设置参数 ：文件名 工作表名 表标题(必须) 表头名称(必须)
 *              表头元素key值(必须) 数据列表 内容字体大小 行高 列宽
 */
public class ExcelExportUtil {

    private String fileName = String.valueOf(System.currentTimeMillis());//默认文件名

    private String sheetName = "sheet"; //工作表名

    private String title;//表标题

    private String[] headerList;//各个列的表头名称

    private String[] headerKey;//各个列的元素key值

    private List<Map<String, Object>> dataList;//需要填充的数据列表

    private int fontSize = 14;//字体大小——内容字体大小 < 表头字体大小 < 标题字体大小（递增2）

    private int rowHeight = 30;//行高

    private int columnWidth = 20;//列宽

    /**
     * 导出excel
     * @param response
     * @return
     * @throws IOException
     */
    public byte[] exportExport(HttpServletResponse response) throws IOException {
        //检查参数配置信息
        checkConfig();
        //创建HSSFWorkbook对象
        HSSFWorkbook wb = new HSSFWorkbook();
        //创建HSSFSheet对象
        HSSFSheet sheet = wb.createSheet("sheet");
        sheet.setDefaultColumnWidth(this.columnWidth);//默认列宽

        //标题样式（加粗，垂直居中）
        HSSFCellStyle titleStyle = wb.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        //标题字体样式
        HSSFFont titleFontStyle = wb.createFont();
        titleFontStyle.setBold(true);   //加粗
        titleFontStyle.setFontHeightInPoints((short) (this.fontSize+4));  //设置标题字体大小
        titleStyle.setFont(titleFontStyle);

        //表头样式（加粗，垂直居中）
        HSSFCellStyle headerStyle = wb.createCellStyle();
        headerStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        //表头字体样式
        HSSFFont headerFontStyle = wb.createFont();
        headerFontStyle.setBold(true);   //加粗
        headerFontStyle.setFontHeightInPoints((short) (this.fontSize+2));  //设置表头字体大小
        headerStyle.setFont(headerFontStyle);


        //单元格样式
        HSSFCellStyle cellStyle = wb.createCellStyle();
        //单元格字体样式（字体红色）
        HSSFFont cellFontStyle = wb.createFont();
        cellFontStyle.setColor(HSSFColor.DARK_RED.index);   //设置字体颜色 (红色)
        cellFontStyle.setFontHeightInPoints((short) this.fontSize);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setFont(cellFontStyle);

        //单元格样式2
        HSSFCellStyle cellStyle2 = wb.createCellStyle();
        //单元格字体样式2（字体蓝色）
        HSSFFont cellFontStyle2 = wb.createFont();
        cellFontStyle2.setColor(HSSFColor.BLUE.index);   //设置字体颜色 (蓝色)
        cellFontStyle2.setFontHeightInPoints((short) this.fontSize);
        cellStyle2.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle2.setAlignment(HorizontalAlignment.CENTER);
        cellStyle2.setFont(cellFontStyle2);


        //创建HSSFRow对象
        //在第0行创建titleRow  (表标题行)
        HSSFRow titleRow = sheet.createRow(0);
        titleRow.setHeightInPoints(30);//行高
        HSSFCell cellRow = titleRow.createCell(0);
        cellRow.setCellValue(this.title);
        cellRow.setCellStyle(titleStyle);
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));

        //在第1行创建headerRow  (表头行)
        HSSFRow headerRow = sheet.createRow(1);
        //创建HSSFCell对象
        for (int i= 0;i< headerList.length;i++){
            HSSFCell headerCell = headerRow.createCell(i);
            //设置单元格的值
            headerCell.setCellStyle(headerStyle);
            headerCell.setCellValue(headerList[i]);
        }

        //在第2行创建row  (行数据)
        //开始写入实体数据信息
        if (dataList != null) {
            for (int i= 2;i< dataList.size();i++){
                HSSFRow row = sheet.createRow(i);
                Map<String,Object> map = dataList.get(i);
                for (int j = 0; j < headerKey.length; j++) {
                    HSSFCell cell = row.createCell(j);
                    String value;//输出值
                    Object valueObject = map.get(headerKey[j]);//数据值
                    value = getDataTypeCastString(valueObject);
                    //设置单元格的值
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(value);
                }
            }
        }

        //导出数据
        try {
            //设置Http响应头告诉浏览器下载这个附件
//            response.setHeader("Content-Disposition", "attachment;Filename="+ URLEncoder.encode(this.fileName ,"UTF-8") +".xls");
//            OutputStream outputStream = response.getOutputStream();
//            wb.write(outputStream);
//            outputStream.close();

            //输出Excel文件
            FileOutputStream output = new FileOutputStream(URLEncoder.encode(this.fileName ,"UTF-8") +".xls");
            wb.write(output);
            output.flush();
            System.out.println("excel导出成功！");
            return wb.getBytes();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IOException("导出Excel出现严重异常，异常信息：" + ex.getMessage());
        }
    }


    /**
     * 根据模板导出excel
     * @param path excel模板路径
     * @param dataList 数据列表
     * @param headerKey 表头对应的key（用作排序）
     * @param startRow 数据行的起始行 （指定复杂表头数据起始行）
     * @return
     */
    public Map<String, Object> exportExcelOfTemplate(String path, List<Map<String ,Object>> dataList,String[] headerKey, int startRow) {
        Map<String, Object> result = new HashMap<>();
        File excelFile = new File(path);
        if(!excelFile.exists()) {
            result.put("success",false);
            result.put("message","模板不存在");
            System.out.println("--------------模板：" + path + ": " + result.get("message") + "--------------");
            return result;
        }else {
            //读取模板
            HSSFWorkbook wb = readHSSFModel(excelFile);
            Sheet sheet = wb.getSheetAt(0);
            sheet.setDefaultColumnWidth(20);

            //标题样式（加粗，垂直居中）
            HSSFCellStyle titleStyle = wb.createCellStyle();
            titleStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
            titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
            //标题字体样式
            HSSFFont titleFontStyle = wb.createFont();
            titleFontStyle.setBold(true);   //加粗
            titleFontStyle.setFontHeightInPoints((short)18);  //设置标题字体大小
            titleStyle.setFont(titleFontStyle);

            //表头样式（加粗，垂直居中）
            HSSFCellStyle headerStyle = wb.createCellStyle();
            headerStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
            //表头字体样式
            HSSFFont headerFontStyle = wb.createFont();
            headerFontStyle.setBold(true);   //加粗
            headerFontStyle.setFontHeightInPoints((short)16);  //设置标题字体大小
            headerStyle.setFont(headerFontStyle);


            //单元格字体样式
            HSSFFont cellFontStyle = wb.createFont();
            cellFontStyle.setFontHeightInPoints((short) 14);

            //单元格样式
            HSSFCellStyle cellStyle = wb.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
            cellStyle.setFont(cellFontStyle);

            //单元格样式(居左)
            HSSFCellStyle leftCellStyle = wb.createCellStyle();
            leftCellStyle.setAlignment(HorizontalAlignment.LEFT);//水平居中
            leftCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
            leftCellStyle.setFont(cellFontStyle);

            //单元格样式(居右)
            HSSFCellStyle rightCellStyle = wb.createCellStyle();
            rightCellStyle.setAlignment(HorizontalAlignment.LEFT);//水平居中
            rightCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
            rightCellStyle.setFont(cellFontStyle);

            int size = dataList.size();
            for (int i = 0; i < size; i++) {
                Map<String, Object> map = dataList.get(i);
                Row row = sheet.createRow(startRow);
                Cell cell1 = row.createCell(0);
                cell1.setCellValue(i+1);
                cell1.setCellStyle(cellStyle);
                for (int j = 0; j < headerKey.length; j++) {
                    Cell cell = row.createCell(j+1);
                    for (String key : map.keySet()){
                        if (headerKey[j].equals(key)) {
                            cell.setCellValue(String.valueOf(map.get(key)));
                            if ("unitName".equals(headerKey[j])){
                                cell.setCellStyle(leftCellStyle);
                            }else {
                                cell.setCellStyle(cellStyle);
                            }
                            break;
                        }
                    }
                }
                startRow++;
            }
            //输出Excel文件
            try {
                FileOutputStream output = new FileOutputStream("G:\\workbook1.xls");
                wb.write(output);
                output.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            result.put("success", true);
            result.put("message", "excel导出成功");
        }
        return result;
    }

    /**
     * 各数据类型转为String
     * @param valueObject
     * @return
     */
    private String getDataTypeCastString(Object valueObject) {
        String value;
        if (valueObject == null) {
            value = "/";
        }else if (valueObject instanceof BigDecimal){
            value = ((BigDecimal) valueObject).setScale(2, BigDecimal.ROUND_HALF_DOWN).toString();
        }else {
            value = valueObject.toString();
        }
        return value;
    }

    /**
     * 检查数据配置问题
     *
     * @throws IOException 抛出数据异常类
     */
    private void checkConfig() throws IOException {

        if (headerList == null || headerList.length == 0) {
            throw new IOException("表头名称数组不能为空或者为NULL");
        }
        if (headerKey == null || headerKey.length == 0) {
            throw new IOException("表头Key值数组不能为空或者为NULL");
        }
        if (fontSize < 0 || rowHeight < 0 || columnWidth < 0) {
            throw new IOException("字体、宽度或者高度不能为负值");
        }

        if (StringUtils.isEmpty(title)) {
            throw new IOException("标题名称不能为空");
        }
    }

    /**
     * 读取excel模板文件（poi）
     * @param file
     * @return
     */
    private HSSFWorkbook readHSSFModel(File file) {
        FileInputStream fileInputStream;
        HSSFWorkbook wb = null;
        try {
            fileInputStream = new FileInputStream(file);
            wb = new HSSFWorkbook(fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wb;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getHeaderList() {
        return headerList;
    }

    public void setHeaderList(String[] headerList) {
        this.headerList = headerList;
    }

    public String[] getHeaderKey() {
        return headerKey;
    }

    public void setHeaderKey(String[] headerKey) {
        this.headerKey = headerKey;
    }

    public List<Map<String, Object>> getDataList() {
        return dataList;
    }

    public void setDataList(List<Map<String, Object>> dataList) {
        this.dataList = dataList;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getRowHeight() {
        return rowHeight;
    }

    public void setRowHeight(int rowHeight) {
        this.rowHeight = rowHeight;
    }

    public int getColumnWidth() {
        return columnWidth;
    }

    public void setColumnWidth(int columnWidth) {
        this.columnWidth = columnWidth;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
