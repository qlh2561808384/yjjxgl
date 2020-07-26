package com.datanew.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * excel工具类
 * Created by zhijun on 2015/12/28.
 */
public class ExcelUtil {

    private String title;//标题
    private String[] rowName ;//列名
    private List<Object[]> dataList = new ArrayList<Object[]>();//数据
    private HttpServletResponse res;

    private final Integer MAX_ROW=1000;//最大行数
    private  Integer sheetNum=0;//sheet数
    private HSSFWorkbook workbook=null;

    public ExcelUtil(String title,String[] rowName,List  dataList,HttpServletResponse res){
        this.dataList = dataList;
        this.rowName = rowName;
        this.title = title;
        this.res=res;
    }

    /**
     * 创建工作簿对象
     * @return
     */
    public HSSFWorkbook createWorkkbook(){
        if(workbook==null){
            workbook=new HSSFWorkbook();
        }
        return workbook;
    }

    /**
     * 
     * 创建工作表
     * @param workbook
     * @return
     */
    public HSSFSheet createSheet(HSSFWorkbook workbook){
        HSSFSheet sheet = workbook.createSheet(title+sheetNum+1);

        HSSFRow rowm = sheet.createRow(0);

        HSSFCell cellTiltle = rowm.createCell(0);

        HSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook);//获取列头样式对象
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (rowName.length-1)));
        cellTiltle.setCellStyle(columnTopStyle);
        cellTiltle.setCellValue(title);

        // 定义所需列数
        int columnNum = rowName.length;
        HSSFRow rowRowName = sheet.createRow(2);                // 在索引2的位置创建行(最顶端的行开始的第二行)

        // 将列头设置到sheet的单元格中
        for(int n=0;n<columnNum;n++){
            HSSFCell  cellRowName = rowRowName.createCell(n);               //创建列头对应个数的单元格
            cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING);             //设置列头单元格的数据类型
            HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
            cellRowName.setCellValue(text);                                 //设置列头单元格的值
            cellRowName.setCellStyle(columnTopStyle);                       //设置列头单元格样式
        }
        return sheet;
    }
    /**
     * 分sheet导出Workbook对象
     */
    public void fillWorkbook(){
        HSSFSheet sheet=this.createSheet(this.createWorkkbook());
        HSSFCellStyle style = this.getStyle(workbook);                  //单元格样式对象
        //将查询出的数据设置到sheet对应的单元格中

        for(int i=MAX_ROW*sheetNum;i<dataList.size();i++){
            if(i<MAX_ROW*(sheetNum+1)) {
                Object[] obj = dataList.get(i);
                HSSFRow row = sheet.createRow(i%MAX_ROW + 3);//创建所需的行
                for (int j = 0; j < obj.length; j++) {
                    HSSFCell cell = row.createCell(j, HSSFCell.CELL_TYPE_STRING);
                    if (!"".equals(obj[j]) && obj[j] != null) {
                        cell.setCellValue(obj[j].toString());
                    }else{
                        cell.setCellValue("");
                    }
                    cell.setCellStyle(style);
                }
            }else {
                sheetNum++;
                fillWorkbook();
                break;
            }
        }
    }

    /**
     * 导出
     */
    public  void export(){
        String fileName = "Excel-" + String.valueOf(System.currentTimeMillis()).substring(4, 13) + ".xls";
        String headStr = "attachment; filename=\"" + fileName + "\"";
        res.setContentType("APPLICATION/OCTET-STREAM");
        res.setHeader("Content-Disposition", headStr);
        OutputStream out = null;
        try {
            out = res.getOutputStream();
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * 列头单元格样式
     */
    public HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short)12);
        //字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置字体名字
        font.setFontName("微软雅黑");
        //设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        //设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        //设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        //设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        //设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return style;
    }

    /*
     * 列数据信息单元格样式
     */
    public HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
        //font.setFontHeightInPoints((short)10);
        //字体加粗
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置字体名字
        font.setFontName("微软雅黑");
        //设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        //设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        //设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        //设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        //设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        return style;
    }
}
