package cn.icutool.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;
/**
 * @author xietao
 */
@Configuration
public class FilePathConst {
    public static final String BASE_PATH = System.getProperty("user.dir");
    public static String UserExcelPath;
    public static String WEEKLY_REPORT_PATH;
    @Value("${user_excel_path}")
    public void setUserExcelPath(String userExcelPath) {
        this.UserExcelPath = BASE_PATH + userExcelPath;
        this.WEEKLY_REPORT_PATH = UserExcelPath + File.separator + "工作周报模板.xlsx";
        //不存在就创建
        File file = new File(this.UserExcelPath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
