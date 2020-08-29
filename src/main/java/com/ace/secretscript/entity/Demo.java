package com.ace.secretscript.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author: ZW
 * @date 2020/8/29
 * @return
 **/
@Getter
@Setter
@ToString
public class Demo {

    private Integer id;
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private Demo2 demo2;
}
