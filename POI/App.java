package poi.test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;


/**
 * @author cikinn
 * @date 2017.10.24
 */

public class App {
    // 写
    @Test
    public void write() throws Exception {
        //1. 创建工作簿
        Workbook workbook = new HSSFWorkbook();
        //2. 创建工作表
        Sheet sheet = workbook.createSheet("测试");
        //3. 创建行(第一行从0开始)
        Row row = sheet.createRow(0);
        //4. 创建单元格(第一行的第一列)
        Cell cell = row.createCell(0);
        //5. 设置单元格内容
        cell.setCellValue("HelloWorld");
        //6. 输出到本地磁盘（OutputStream）
        workbook.write(new FileOutputStream("f:/test.xls"));
        workbook.close();
    }

    // 读
    @Test
    public void read() throws Exception {
        //流程：1. 根据excel文件流，创建工作簿； 2. 获取工作表； 3.获取行； 4.获取单元格；5.获取内容

        Workbook workbook = new HSSFWorkbook(new FileInputStream("f:/test.xls"));
        // 获取第一个工作表
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(0);
        String row1cell1 = cell.getStringCellValue();
        System.out.println(row1cell1);
        workbook.close();

        // 实际有效行、列
        System.out.println("获取excel的物理行数：" + sheet.getPhysicalNumberOfRows());
        System.out.println("获取第一行的所有列个数：" + row.getPhysicalNumberOfCells());
    }

    // 合并单元格
    @Test
    public void merge() throws Exception {
        // 创建工作簿
        Workbook workbook = new HSSFWorkbook();
        // 创建工作表
        Sheet sheet = workbook.createSheet();
        // 合并单元格 (参数分别表示：开始行、结束行、开始列、结束列)
        sheet.addMergedRegion(new CellRangeAddress(2, 4, 1, 2));

        // 创建第三行、第二列
        sheet.createRow(2).createCell(1).setCellValue("合并内容！");

        // 输出
        workbook.write(new FileOutputStream("f:/test.xls"));
        // 关闭
        workbook.close();
    }


}
