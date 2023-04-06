package com.xbg3.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xbg3.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where userid=#{userId} and upass =#{upass}")
    User selectByUser(User user);

    int insert(User user);

    List<User> selectAll();

    @Select("select * from user limit #{index} , #{pageSize}")
    List<User> selectAllByPage(@Param("index")int index, @Param("pageSize")int pageSize);

    @Select("select count(*) from user ")
    int selectTableSize();


    @Update("update user set userName=#{userName},upass =#{upass} where userid =#{userId}")
    int update(User user);

    @Select("select * from user where userid = #{userId}")
    User selectByUserId(String userId);

    @Select("select S.*  from course as C,enrolledcourse as EC,user as S where C.CourseName=#{courseName} and EC.CourseId=C.Id and EC.UserId = S.Id and S.type = 0 limit #{index} , #{pageSize}")
    List<User> selectEnrolledStudent(@Param("courseName") String courseName,@Param("index")int index, @Param("pageSize")int pageSize);


    int delete(@Param("userIds") int[] userIds);



}
