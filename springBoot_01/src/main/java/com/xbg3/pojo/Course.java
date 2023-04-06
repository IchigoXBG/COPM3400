package com.xbg3.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("course")
public class Course {

    @TableId("CourseName")
    private  String name;
    @TableField("Mark")
    private  float mark;

}
