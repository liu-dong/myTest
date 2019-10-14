import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileOutputStream;
import java.io.IOException;

public class Test {

    public static void main(String[] args) {
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
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));

        //在第1行创建row  (行数据)
        HSSFRow headerRow = sheet.createRow(1);
        //创建HSSFCell对象
        for (int i= 0;i<=3;i++){
            HSSFCell headerCell = headerRow.createCell(i);
            //设置单元格的值
            headerCell.setCellStyle(headerStyle);
            headerCell.setCellValue("表头("+i+")数据");
        }

        //在第1行创建row  (行数据)
        HSSFRow row = sheet.createRow(2);
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

}