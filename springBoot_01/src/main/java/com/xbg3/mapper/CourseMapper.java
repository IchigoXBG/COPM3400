package com.xbg3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xbg3.pojo.Course;
import com.xbg3.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    @ResultMap("courseMap")

    @Select("select c.CourseName,ec.Mark from course as c,enrolledcourse as ec,user as s where c.Id = ec.CourseId and s.userid= #{id} and  ec.UserId = s.id")
    List<Course>  selectAllCourse(@Param("id") String id);

    List<Integer>  selectMark(@Param("courseName") String courseName,@Param("ids") String[] ids);

    @Insert("insert into course (CourseName) values(#{courseName})")
    Integer addCourse(@Param("courseName") String courseName);

    @Insert("insert into enrolledcourse (CourseId,UserId) values((SELECT Id FROM course WHERE CourseName = #{courseName}),(SELECT Id FROM user WHERE userid = #{id}))")
    Integer addEnrollment(@Param("id") String id,@Param("courseName") String courseName);

    @Select("select * from course where CourseName = #{courseName}")
    Integer isCourseExist(@Param("courseName") String courseName);

    @Update("update enrolledcourse set Mark = #{mark} where CourseId = (SELECT Id FROM course WHERE CourseName = #{courseName}) and UserId = (SELECT Id FROM user WHERE userid = #{id}) ")
    Integer updateGrade(@Param("mark") int mark, @Param("courseName")String courseName,@Param("id")String userId);

    @Delete("delete from enrolledcourse where CourseId = (SELECT Id FROM course WHERE CourseName = #{courseName}) and UserId = (SELECT Id FROM user WHERE userid = #{id})")
    Integer deleteEnrollment(@Param("courseName")String courseName,@Param("id")String userId);

}
