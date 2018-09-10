package com.example.userdemo.excel;

import com.example.userdemo.excel.util.ExcelUtil;
import com.example.userdemo.excel.vo.UserExcelVo;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(value = "excel")
public class ExcelController {


    @RequestMapping(value = "userExcel")
    public void toExcel(HttpServletResponse response) {
        List<UserExcelVo> personList = new ArrayList<>();
        UserExcelVo person1 = new UserExcelVo("路飞", "1", new Date());
        UserExcelVo person2 = new UserExcelVo("娜美", "2", DateUtils.addMinutes(new Date(), 3));
        UserExcelVo person3 = new UserExcelVo("索隆", "1", DateUtils.addMinutes(new Date(), 10));
        UserExcelVo person4 = new UserExcelVo("乔巴", "1", DateUtils.addMinutes(new Date(), -10));
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);

        ExcelUtil.exportExcel(personList, "花名册", "草帽一伙", UserExcelVo.class, "海贼王.xls", response);
    }
}
