/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roster;

/**
 *
 * @author shahua
 */
import java.io.*;
import java.util.*;
import java.io.File;
import java.io.IOException;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Jexcel {

    /**
     * 写excel文件
     *     
*/
    ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
    
    public void writeExc(File filename,ArrayList<ArrayList<String>> table) {
        WritableWorkbook wwb = null;
        try {
            wwb = Workbook.createWorkbook(filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        for (int i = 0; i < table.size(); i++) {
                for (int j = 0; j < table.get(i).size(); j++) {
                    System.out.print(table.get(i).get(j) + "   ");
                }
                System.out.println();
            }

//创建Excel工作表
        WritableSheet ws = wwb.createSheet("BaseRosterNew", 0);//创建sheet
        try {
            /*
             ws.mergeCells(0, 0, 2, 1);//合并单元格(左列，左行，右列，右行)从第1行第1列到第2行第3列
             Label header = new Label(0, 0, "通讯录(191026班)", getHeader());
             ws.addCell(header);//写入头
             Label l = new Label(0, 2, "姓名", getTitle());//第3行
             ws.addCell(l);
             l = new Label(1, 2, "电话", getTitle());
             ws.addCell(l);
             l = new Label(2, 2, "地址", getTitle());
             ws.addCell(l);
             */
            Label l;

            for (int i = 0; i < table.size(); i++) {
                for (int j = 1; j < table.get(i).size(); j++) {
                    l = new Label(j-1, i, table.get(i).get(j), getNormolCell());
                    ws.addCell(l);
                   // l.setCellFormat(getLocationCell());
                }
                System.out.println(table.get(i).size());
                if(table.get(i).size()==22){
                l = new Label(table.get(i).size(), i, table.get(i).get(1), getNormolCell());
                ws.addCell(l);
                
                }else{
                    l = new Label(table.get(i).size()-1, i, table.get(i).get(1), getNormolCell());
                ws.addCell(l);
                }
                    
            }
            
//            l = new Label(0, 0, table.get(0).get(0), getNormolCell());
//                    ws.addCell(l);
            

            
            
            for (int i = 0; i < table.size(); i++) {
                for (int j = 0; j < table.get(i).size(); j++) {
                    System.out.print(table.get(i).get(j) + "   ");
                }
                System.out.println();
            }
             
            /*
             l = new Label(0, 3, "小祝", getNormolCell());//第4行
             ws.addCell(l);
             l = new Label(1, 3, "1314***0974", getNormolCell());
             ws.addCell(l);
             l = new Label(2, 3, "武汉武昌", getNormolCell());
             ws.addCell(l);
             l = new Label(0, 4, "小施", getNormolCell());//第5行
             ws.addCell(l);
             l = new Label(1, 4, "1347***5057", getNormolCell());
             ws.addCell(l);
             l = new Label(2, 4, "武汉武昌", getNormolCell());
             ws.addCell(l);
             */
            /*
            ws.setColumnView(0, 20);//设置列宽
            ws.setColumnView(1, 20);
            ws.setColumnView(2, 40);
            ws.setRowView(0, 400);//设置行高
            ws.setRowView(1, 400);
            ws.setRowView(2, 500);
            ws.setRowView(3, 500);
            ws.setRowView(4, 500);
            */
            ws.setColumnView(0, 20);
            ws.setColumnView(table.get(1).size(),20);
        } catch (RowsExceededException e1) {
            e1.printStackTrace();
        } catch (WriteException e1) {
            e1.printStackTrace();
        }

//输出流
        try {
            wwb.write();
        } catch (IOException ex) {
// TODO 自动生成 catch 块
            ex.printStackTrace();
        }
//关闭流
        try {
            wwb.close();
        } catch (WriteException ex) {
// TODO 自动生成 catch 块
            ex.printStackTrace();
        } catch (IOException ex) {
// TODO 自动生成 catch 块
            ex.printStackTrace();
        }
//outStream.close();
        System.out.println("done！\n");
    }

    public void readExc(File filename) throws BiffException, IOException {
        Workbook wb = Workbook.getWorkbook(filename);
        Sheet s = wb.getSheet(0);//第1个sheet
        Cell c = null;
        int row = s.getRows();//总行数
        int col = s.getColumns();//总列数
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                c = s.getCell(j, i);
                System.out.print(c.getContents() + " ");
            }
            System.out.println();
        }
    }

    /**
     * 设置头的样式
     *
     * @return
     */
    public static WritableCellFormat getHeader() {
        WritableFont font = new WritableFont(WritableFont.TIMES, 24, WritableFont.BOLD);//定义字体
        try {
            font.setColour(Colour.BLUE);//蓝色字体
        } catch (WriteException e1) {
// TODO 自动生成 catch 块
            e1.printStackTrace();
        }
        WritableCellFormat format = new WritableCellFormat(font);
        try {
            format.setAlignment(jxl.format.Alignment.CENTRE);//左右居中
            format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//上下居中
            format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);//黑色边框

            format.setBackground(Colour.YELLOW);//黄色背景
        } catch (WriteException e) {
// TODO 自动生成 catch 块
            e.printStackTrace();
        }
        return format;
    }

    /**
     * 设置标题样式
     *
     * @return
     */
    public static WritableCellFormat getTitle() {
        WritableFont font = new WritableFont(WritableFont.TIMES, 14);
        try {
            font.setColour(Colour.BLUE);//蓝色字体
        } catch (WriteException e1) {
// TODO 自动生成 catch 块
            e1.printStackTrace();
        }
        WritableCellFormat format = new WritableCellFormat(font);

        try {
            format.setAlignment(jxl.format.Alignment.CENTRE);
            format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
        } catch (WriteException e) {
// TODO 自动生成 catch 块
            e.printStackTrace();
        }
        return format;
    }

    /**
     * 设置其他单元格样式
     *
     * @return
     */
    public static WritableCellFormat getNormolCell() {//12号字体,上下左右居中,带黑色边框
        WritableFont font = new WritableFont(WritableFont.TIMES, 10);
        WritableCellFormat format = new WritableCellFormat(font);
        try {
            format.setAlignment(jxl.format.Alignment.CENTRE);
            format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
        } catch (WriteException e) {
// TODO 自动生成 catch 块
            e.printStackTrace();
        }
        return format;
    }
    
    public static WritableCellFormat getLocationCell() {//12号字体,上下左右居中,带黑色边框
       // WritableFont font = new WritableFont(WritableFont.TIMES, 10,WritableFont.BOLD);
        WritableCellFormat format = new WritableCellFormat();
        try {
            //format.setAlignment(jxl.format.Alignment.CENTRE);
            //format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            format.setBorder(Border.ALL, BorderLineStyle.THICK, Colour.BLACK);
            //HSSFFont font = workBook.createFont();
            //font.setBoldStyle(HSSFFont.BOLDWEIGHT_BOLD);
        } catch (WriteException e) {
// TODO 自动生成 catch 块
            e.printStackTrace();
        }
        return format;
    }

    void run(ArrayList<ArrayList<String>> table1) throws IOException, BiffException {
        table = table1;
       
        try {
             
            Jexcel js = new Jexcel();
            File f = new File("D:\\roster.xls");
            f.createNewFile();
            js.writeExc(f,table);
            //js.readExc(f);
        } catch (Exception e) {
            throw e;
        }
       
    }
   
}
