package com.xbg3.service;

import com.xbg3.pojo.Course;
import com.xbg3.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserService {
    //@Transactional(readOnly = true)

    Integer deleteEnrollment(String courseName,String userId);

    Integer updateGrade(int mark,String courseName,String userId);


    List<Integer>  selectMark(String courseName,String[] ids);

    boolean insert(User user);

    List<User> selectAll();

    User selectByUserId(String userId);

    int selectTableSize();

    List<User> selectAllByPage(int index,int pageSize);

    int update(User user);

    int delete(int[] userIds);

    User selectByUser(User user);

    List<Course> selectAllCourse(String id);

    List<User> selectEnrolledStudent(String courseName, int index, int pageSize);

    Integer addCourse(String courseName);

    Integer addEnrollment(String id,String courseName);

    Integer isCourseExist(String courseName);


}
