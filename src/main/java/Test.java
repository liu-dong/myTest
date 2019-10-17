import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

    private static final String[] header = {"企业名称","建筑业总产值","建筑业增加值"};
    private static final String[] header2 = {"","本月","同期","同比","本月","同期","同比"} ;


    public static void main(String[] args) {
        Test test = new Test();
        String path = "G:\\workbook.xls";
        List<Map<String ,Object>> dataList = new ArrayList<>();
        String[] headerKey = {"unitName","grossOutput","addedValue"};
        for (int i=0;i<50; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("unitName", "中铁十四局集团隧道工程有限公司"+i);
            map.put("grossOutput", "425.0000"+i);
            map.put("addedValue", "65.0000"+i);
            dataList.add(map);
        }
        Map<String, Object> result = test.exportExcelOfTemplate(path,dataList,headerKey,2);
    }


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
    public void exportExcel() {

        //创建HSSFWorkbook对象
        HSSFWorkbook wb = new HSSFWorkbook();
        //创建HSSFSheet对象
        HSSFSheet sheet = wb.createSheet("sheet");
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


        //单元格样式
        HSSFCellStyle cellStyle = wb.createCellStyle();
        //单元格字体样式（字体红色）
        HSSFFont cellFontStyle = wb.createFont();
        cellFontStyle.setColor(HSSFColor.DARK_RED.index);   //设置字体颜色 (红色)
        cellFontStyle.setFontHeightInPoints((short) 14);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setFont(cellFontStyle);

        //单元格样式2
        HSSFCellStyle cellStyle2 = wb.createCellStyle();
        //单元格字体样式2（字体蓝色）
        HSSFFont cellFontStyle2 = wb.createFont();
        cellFontStyle2.setColor(HSSFColor.BLUE.index);   //设置字体颜色 (蓝色)
        cellFontStyle2.setFontHeightInPoints((short) 14);
        cellStyle2.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle2.setAlignment(HorizontalAlignment.CENTER);
        cellStyle2.setFont(cellFontStyle2);


        //创建HSSFRow对象
        //在第0行创建title  (表标题行)
        HSSFRow titleRow = sheet.createRow(0);
        titleRow.setHeightInPoints(30);//行高
        HSSFCell cellRow = titleRow.createCell(0);
        cellRow.setCellValue("标题数据");
        cellRow.setCellStyle(titleStyle);
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,(header2.length)-1));//合并单元格 起始行,结束行,起始列,结束列

        //在第1行创建row  (行数据)
        HSSFRow headerRow = sheet.createRow(1);
        //创建HSSFCell对象
        for (int i = 0; i < header.length; i++) {
            HSSFCell headerCell = headerRow.createCell(i);
            //设置单元格的值
            headerCell.setCellStyle(headerStyle);
            if (i == 0){
                sheet.addMergedRegion(new CellRangeAddress(1,2,0,0));//合并单元格
            }else if (i == 1){
                sheet.addMergedRegion(new CellRangeAddress(1,1,1,3));//合并单元格
            }else if (i == 2){
                sheet.addMergedRegion(new CellRangeAddress(1,1,4,6));//合并单元格
            }
            headerCell.setCellValue(header[i]);
        }
        HSSFRow headerRow2 = sheet.createRow(2);
        //创建HSSFCell对象
        for (int i = 0; i < header2.length; i++) {
            HSSFCell headerCell = headerRow2.createCell(i);
            //设置单元格的值
            headerCell.setCellStyle(headerStyle);
            headerCell.setCellValue(header2[i]);
        }

        //在第1行创建row  (行数据)
        HSSFRow row = sheet.createRow(3);
        HSSFCell cell = row.createCell(0);
        //设置单元格的值
        cell.setCellStyle(cellStyle);
        cell.setCellValue("单元格数据");
        //输出Excel文件
        try {
            FileOutputStream output = new FileOutputStream("G:\\workbook.xls");
            wb.write(output);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取excel（poi）
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

}