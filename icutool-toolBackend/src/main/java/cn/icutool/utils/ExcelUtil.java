package cn.icutool.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.util.Arrays;
import java.util.List;

public class ExcelUtil {
    private static final HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy();
    static {
        WriteCellStyle writeCellStyleCenter = new WriteCellStyle();
        writeCellStyleCenter.setHorizontalAlignment(HorizontalAlignment.CENTER);//设置水平对齐的样式为居中对齐;
        writeCellStyleCenter.setVerticalAlignment(VerticalAlignment.CENTER);  //设置垂直对齐的样式为居中对齐;
        horizontalCellStyleStrategy.setContentWriteCellStyleList(Arrays.asList(writeCellStyleCenter));
    }
    public static <T> void generateExcelFromTemplates(String templateFileName, String excelFilePath, String sheet, List<T> excelBOList){
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 这里要注意 withTemplate 的模板文件会全量存储在内存里面，所以尽量不要用于追加文件，如果文件模板文件过大会OOM
        // 如果要再文件中追加（无法在一个线程里面处理，可以在一个线程的建议参照多次写入的demo） 建议临时存储到数据库 或者 磁盘缓存(ehcache) 然后再一次性写入
        EasyExcel
                .write(excelFilePath)
                .registerWriteHandler(horizontalCellStyleStrategy)
                .withTemplate(templateFileName)
                .sheet(sheet)
                .doFill(excelBOList);
    }
}
