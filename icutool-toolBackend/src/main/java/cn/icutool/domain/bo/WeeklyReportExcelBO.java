package cn.icutool.domain.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author xietao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class WeeklyReportExcelBO {
    @ExcelProperty(value = "序号", index = 0)
    private int id;

    @ExcelProperty(value = "主题", index = 1)
    private String subject;

    @ExcelProperty(value = "完成标准", index = 2)
    private String content;

    @ExcelProperty(value = "计划开始时间", index = 3)
    @DateTimeFormat("m\"月\"d\"日\"")
    private LocalDate createTime;

    @ExcelProperty(value = "计划完成时间", index = 4)
    @DateTimeFormat("m\"月\"d\"日\"")
    private LocalDate updateTime;

    @ExcelProperty(value = "实际完成时间", index = 5)
    @DateTimeFormat("m\"月\"d\"日\"")
    private LocalDate doneTime;

    @ExcelProperty(value = "完成进度", index = 6)
    private String progressBar;
}
