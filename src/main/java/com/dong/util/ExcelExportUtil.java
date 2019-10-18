package com.dong.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
    private String sheetName = "Sheet1"; //工作表名
    private String title;//表标题
    private String[] headerList;//各个列的表头名称
    private String[] headerKey;//各个列的元素key值
    private List<Map<String, Object>> dataList;//需要填充的数据列表
    private int fontSize = 14;//字体大小——内容字体大小 < 表头字体大小 < 标题字体大小（递增2）
    private int rowHeight = 30;//行高
    private int columnWidth = 30;//列宽

    private HSSFWorkbook wb;//创建HSSFWorkbook对象
    private HSSFCellStyle titleStyle;//标题样式（加粗，垂直居中）
    private HSSFCellStyle headerStyle;//表头样式（加粗，垂直居中）
    private HSSFCellStyle cellStyle;//单元格样式 (默认垂直居中、水平居中)

    /**
     * 导出excel
     * @param response
     * @return
     * @throws IOException
     */
    public void exportExport(HttpServletResponse response, HttpServletRequest request) throws IOException {
        //检查参数配置信息
        checkConfig();
        //创建HSSFWorkbook对象
        wb = new HSSFWorkbook();
        //创建HSSFSheet对象
        HSSFSheet sheet = wb.createSheet(this.sheetName);
        sheet.setDefaultColumnWidth(this.columnWidth);//默认列宽
        initStyle();
        //创建HSSFRow对象
        //在第0行创建titleRow  (表标题行)
        HSSFRow titleRow = sheet.createRow(0);
        titleRow.setHeightInPoints(30);//行高
        HSSFCell cellRow = titleRow.createCell(0);
        cellRow.setCellValue(this.title);
        cellRow.setCellStyle(titleStyle);
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,this.headerKey.length));

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
            for (int i= 0;i< dataList.size();i++){
                HSSFRow row = sheet.createRow(i+2);
                HSSFCell cell = row.createCell(0);
                cell.setCellValue(i+1);
                cell.setCellStyle(cellStyle);
                Map<String,Object> map = dataList.get(i);
                for (int j = 0; j < headerKey.length; j++) {
                    cell = row.createCell(j+1);
                    for (String key : map.keySet()){
                        if (headerKey[j].equals(key)) {
                            String value;//输出值
                            Object valueObject = map.get(headerKey[j]);//数据值
                            value = getDataTypeCastString(valueObject);
                            cell.setCellValue(value);
                            cell.setCellStyle(cellStyle);
                        }
                    }
                }
            }
        }
//        this.autoAdjustColumnSize(sheet,headerKey);

        //保存文件
        try {
            //设置Http响应头告诉浏览器下载这个Excel文件
            setFileNameEncoding(request, response, this.fileName);//解决下载名称乱码
            OutputStream output = response.getOutputStream();

            //输出Excel文件在本地
//            FileOutputStream output = new FileOutputStream("G:\\"+URLEncoder.encode(this.fileName ,"UTF-8")+".xls");

            wb.write(output);
            output.flush();
            output.close();
            System.out.println("excel导出成功！");
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
    public Map<String, Object> exportExcelOfTemplate(HttpServletResponse response, String path, List<Map<String ,Object>> dataList,String[] headerKey, int startRow) {
        Map<String, Object> result = new HashMap<>();
        File excelFile = new File(path);//模板文件
        if(!excelFile.exists()) {
            result.put("success",false);
            result.put("message","模板不存在");
            System.out.println("--------------模板：" + path + ": " + result.get("message") + "--------------");
            return result;
        }else {
            //读取模板
            wb = readHSSFModel(excelFile);
            Sheet sheet = wb.getSheetAt(0);
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
                            String value;//输出值
                            Object valueObject = map.get(headerKey[j]);//数据值
                            value = getDataTypeCastString(valueObject);
                            cell.setCellValue(value);
                            cell.setCellStyle(cellStyle);
                        }
                    }
                }
                startRow++;
            }
            //输出Excel文件
            try {
                //设置Http响应头告诉浏览器下载这个Excel文件
//            response.setHeader("Content-Disposition", "attachment;Filename="+ URLEncoder.encode(this.fileName ,"UTF-8") +".xls");
//            OutputStream output = response.getOutputStream();

                //输出Excel文件在本地
                FileOutputStream output = new FileOutputStream("G:\\"+URLEncoder.encode(this.fileName ,"UTF-8")+".xls");
                wb.write(output);
                output.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("excel导出成功！");
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
        if (valueObject == null || "".equals(valueObject)) {
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


    /**
     * 初始化样式 ：标题样式、表头样式、单元格样式、字体样式
     */
    private void initStyle(){

        //标题样式
        //标题字体样式
        HSSFFont titleFontStyle = this.getFontStyle(true, (short) this.getFontSize() + 4, "");
        titleStyle = this.getCellStyle("","", titleFontStyle);

        //表头样式
        //表头字体样式
        HSSFFont headerFontStyle = this.getFontStyle(true, (short) this.getFontSize() + 2, "");
        headerStyle = this.getCellStyle("","", headerFontStyle);

        //单元格样式
        //单元格字体样式
        HSSFFont cellFontStyle = this.getFontStyle(true, (short) this.getFontSize(), "");
        cellStyle = this.getCellStyle("","", cellFontStyle);
    }


    /**
     * 字体样式
     * @param isBold 是否加粗
     * @param fontSize 字体大小
     * @param color 颜色 （暂支持红色、蓝色）
     * @return
     */
    private HSSFFont getFontStyle(boolean isBold,int fontSize,String color){
        HSSFFont fontStyle = wb.createFont();
        fontStyle.setBold(isBold);//加粗
        fontStyle.setFontHeightInPoints((short) fontSize);//设置标题字体大小
        if (!StringUtils.isEmpty(color)) {
            if ("red".equalsIgnoreCase(color)) {
                fontStyle.setColor(HSSFColor.RED.index);
            } else if ("blue".equalsIgnoreCase(color)) {
                fontStyle.setColor(HSSFColor.BLUE.index);
            }
        }
        return fontStyle;
    }

    /**
     * 单元格样式
     * @param horizontally 默认水平居中 left、right
     * @param vertical 默认垂直居中 top、bottom
     * @param font 字体样式
     * @return
     */
    private HSSFCellStyle getCellStyle(String horizontally,String vertical,HSSFFont font){
        HSSFCellStyle cellStyle = wb.createCellStyle();
        if (StringUtils.isEmpty(horizontally)) {
            cellStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
        }else {
            if ("center".equalsIgnoreCase(horizontally)) {
                cellStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
            } else if ("left".equalsIgnoreCase(horizontally)) {
                cellStyle.setAlignment(HorizontalAlignment.LEFT);//水平居中
            }else if ("right".equalsIgnoreCase(horizontally)) {
                cellStyle.setAlignment(HorizontalAlignment.RIGHT);//水平居中
            }
        }
        if (StringUtils.isEmpty(vertical)) {
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        }else {
            if ("center".equalsIgnoreCase(vertical)) {
                cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
            } else if ("top".equalsIgnoreCase(vertical)) {
                cellStyle.setVerticalAlignment(VerticalAlignment.TOP);//垂直居中
            }else if ("bottom".equalsIgnoreCase(vertical)) {
                cellStyle.setVerticalAlignment(VerticalAlignment.BOTTOM);//垂直居中
            }
        }
        cellStyle.setFont(font);
        return cellStyle;
    }

    /**
     * 自动调整列宽
     * @param sheet
     * @param headerKey
     */
    private void autoAdjustColumnSize(HSSFSheet sheet, String[] headerKey) {
        for (int i = 0; i < headerKey.length + 1; i++) {
            sheet.autoSizeColumn(i, true);
        }
    }

    /**
     * 解决下载名称乱码 （浏览器不兼容）
     * @param request
     * @param response
     * @param fileName
     * @throws UnsupportedEncodingException
     */
    private void setFileNameEncoding(javax.servlet.http.HttpServletRequest request, HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        String browser = request.getHeader("User-Agent");
        if (-1 < browser.indexOf("MSIE 6.0") || -1 < browser.indexOf("MSIE 7.0")) {// IE6, IE7 浏览器
            response.addHeader("content-disposition", "attachment;filename="
                    + new String(fileName.getBytes(), "ISO8859-1"));
        } else if (-1 < browser.indexOf("MSIE 8.0")) {// IE8
            response.addHeader("content-disposition", "attachment;filename="
                    + URLEncoder.encode(fileName, "UTF-8"));
        } else if (-1 < browser.indexOf("MSIE 9.0")) {// IE9
            response.addHeader("content-disposition", "attachment;filename="
                    + URLEncoder.encode(fileName, "UTF-8"));
        } else if (-1 < browser.indexOf("Chrome")) {// 谷歌
            response.addHeader("content-disposition",
                    "attachment;filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8"));
        } else if (-1 < browser.indexOf("Safari")) {// 苹果
            response.addHeader("content-disposition", "attachment;filename="
                    + new String(fileName.getBytes(), "ISO8859-1"));
        } else {// 火狐或者其他的浏览器
            response.addHeader("content-disposition",
                    "attachment;filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8"));
        }
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
