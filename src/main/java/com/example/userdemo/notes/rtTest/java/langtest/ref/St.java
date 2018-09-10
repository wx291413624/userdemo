package com.example.userdemo.notes.rtTest.java.langtest.ref;

import com.example.userdemo.excel.vo.UserExcelVo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 夜凉如水般清澈
 * 18-5-8 下午2:51
 * Fear can hold you prisoner, hope can set you free. A strong man can save himself
 */
@Data
@AllArgsConstructor
public class St {
    private String name;
    private Integer age;

    public void find() throws Throwable {
        this.finalize();
    }

    public static void main(String[] args) {
        Map map = new HashMap();
        boolean empty = map.isEmpty();
        System.out.println("empty = " + empty);
        UserExcelVo asd = (UserExcelVo) null;
        List<UserExcelVo> ls = new ArrayList<>();
        ls.add(asd);
        System.out.println("asd = " + asd);
    }
}
