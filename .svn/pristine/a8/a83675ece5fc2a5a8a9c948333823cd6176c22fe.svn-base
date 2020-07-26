package com.hzzk.common.util;
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.OutputStream;  
  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import jxl.Cell;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.ss.usermodel.Sheet;  
import org.apache.poi.ss.usermodel.Workbook;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  

import com.datanew.util.StringUtil;
  
/** 
 * 导入和导出Excel文件类 
 * 支持2003(xls)和2007(xlsx)版本的Excel文件 
 *  
 * @author yxm 
 */  
public class OperationExcelForPOI {  
  
    public static void main(String[] args) {  
        new OperationExcelForPOI().impExcel("C:\\Users/dell/Desktop/6/丽水市本级6.xls","267","331100","3311002028","2018-03-05");
        new OperationExcelForPOI().impExcel("C:\\Users/dell/Desktop/6/丽水市缙云县6.xls","268","331122","3311222077","2018-03-05");
        new OperationExcelForPOI().impExcel("C:\\Users/dell/Desktop/6/丽水市景宁县6.xls","270","331127","3311272082","2018-03-05");
        new OperationExcelForPOI().impExcel("C:\\Users/dell/Desktop/6/丽水市莲都区6.xls","303","331102","3311022108","2018-03-05");
        new OperationExcelForPOI().impExcel("C:\\Users/dell/Desktop/6/丽水市龙泉市6.xls","269","331181","3311812083","2018-03-05");
        new OperationExcelForPOI().impExcel("C:\\Users/dell/Desktop/6/丽水市青田县6.xls","286","331121","3311212076","2018-03-05");
        new OperationExcelForPOI().impExcel("C:\\Users/dell/Desktop/6/丽水市庆元县6.xls","330","331126","3311262081","2018-03-05");
        new OperationExcelForPOI().impExcel("C:\\Users/dell/Desktop/6/丽水市松阳县6.xls","288","331124","3311242079","2018-03-05");
        new OperationExcelForPOI().impExcel("C:\\Users/dell/Desktop/6/丽水市遂昌县6.xls","306","331123","3311232078","2018-03-05");
        new OperationExcelForPOI().impExcel("C:\\Users/dell/Desktop/6/丽水市云和县6.xls","287","331125","3311252080","2018-03-05");
        
    }  
      
    /** 
     * 导入Excel 
     * @param execelFile 
     */  
    public void impExcel(String execelFile,String yhm,String xzqh,String dw,String sj){  
        try {  
            // 构造 Workbook 对象，execelFile 是传入文件路径(获得Excel工作区)  
            Workbook book = null;  
            try {  
                // Excel 2007获取方法  
                book = new XSSFWorkbook(new FileInputStream(execelFile));  
            } catch (Exception ex) {  
                // Excel 2003获取方法  
                book = new HSSFWorkbook(new FileInputStream(execelFile));  
            }  
              
            // 读取表格的第一个sheet页  
            Sheet sheet = book.getSheetAt(0);  
            // 定义 row、cell  
            Row row;  
            String[] cell;  
            String t = "";
            // 总共有多少行,从0开始  
            int totalRows = sheet.getLastRowNum() ;  
            // 循环输出表格中的内容,首先循环取出行,再根据行循环取出列  
            for (int i = 2; i <= totalRows; i++) {
            	
                row = sheet.getRow(i);  
                // 处理空行  
                if(row == null){  
                    continue ;  
                }  
                // 总共有多少列,从0开始  
                int totalCells = row.getLastCellNum() ; 
                cell = new String[totalCells];
                for (int j = row.getFirstCellNum(); j < totalCells; j++) {  
                    // 处理空列  
                    if(row.getCell(j) == null){  
                    	cell[j]=""; 
                    }  
                    // 通过 row.getCell(j).toString() 获取单元格内容  
                    cell[j] = row.getCell(j).toString();  
                    
                }
                try {
                	if(StringUtil.isblank(cell[2])&&StringUtil.isblank(cell[3])&&StringUtil.isblank(cell[4])&&StringUtil.isblank(cell[5])&&StringUtil.isblank(cell[6])){
                		continue;  
                	}
                	if(cell[0].equals("1")){
                		t="1";
                	}
                	if(cell[0].equals("2")){
                		t="2";
                	}
                	if(cell[0].equals("3")){
                		t="3";
                	}
                	if(cell[0].equals("4")){
                		t="4";
                	}
					insert("insert into ZUD_ZJSZFFSSRSJZXQKBYAYSGLFSY  (yhm,xzqh,dw,sj,col_1,col_2,col_3,col_4,col_5,col_6,col_7,col_8) values ('"+yhm+"','"+xzqh+"','"+dw+"','"+sj+"','"+t+"','"+cell[0]+"','"+cell[1]+"','"+cell[2]+"','"+cell[3]+"','"+cell[4]+"','"+cell[5]+"','"+cell[6]+"')");
				} catch (SQLException e) {
					continue;
				}
                System.out.println("");  
            }  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    } 
    public static Connection conn(){  
        try {  
        //第一步：加载JDBC驱动  
        Class.forName("oracle.jdbc.driver.OracleDriver");  
        //第二步：创建数据库连接  
        Connection con =DriverManager.getConnection("jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.42.192.74)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = 10.42.192.75)(PORT = 1521))(LOAD_BALANCE = yes)(FAILOVER = yes)(CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = lscz)(FAILOVER_MODE=(TYPE = SELECT)(METHOD = BASIC)(RETIRES = 180)(DELAY = 15))))", "sjfxpt", "sjfxpt_lscz16");  
        return con;  
        }catch(ClassNotFoundException cnf){  
          System.out.println("driver not find:"+cnf);  
          return null;  
        }catch(SQLException sqle){  
          System.out.println("can't connection db:"+sqle);  
          return null;  
        }  
          catch (Exception e) {  
        System.out.println("Failed to load JDBC/ODBC driver.");  
        return null;  
        }  
    }
    public static int insert(String insert) throws SQLException{  
        Connection conn = conn();  
        int re = 0;  
        try{  
            conn.setAutoCommit(false);//事物开始                  
            Statement sm = conn.createStatement();  
            re = sm.executeUpdate(insert);  
            if(re < 0){               //插入失败  
                conn.rollback();      //回滚  
                sm.close();  
                conn.close();    
                return re;  
            }  
            conn.commit();            //插入正常  
            sm.close();  
            conn.close();    
            return re;  
        }  
        catch(Exception e){  
            e.printStackTrace();  
        }  
        conn.close();    
        return 0;  
          
    }
}