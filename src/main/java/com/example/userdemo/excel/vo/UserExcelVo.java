package com.example.userdemo.excel.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserExcelVo {

    @Excel(name = "姓名", orderNum = "0")
    private String name;
    @Excel(name = "性别", replace = {"男_1", "女_2"}, orderNum = "1")
    private String sex;
    @Excel(name = "生日", exportFormat = "yyyy-MM-dd", orderNum = "2")
    private Date birthday;

}
