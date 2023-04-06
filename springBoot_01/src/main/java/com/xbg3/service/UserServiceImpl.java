package com.xbg3.service;

import com.xbg3.mapper.CourseMapper;
import com.xbg3.mapper.UserMapper;
import com.xbg3.pojo.Course;
import com.xbg3.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CourseMapper courseMapper;


    @Override
    public Integer deleteEnrollment(String courseName, String userId) {
        return courseMapper.deleteEnrollment(courseName,userId);
    }

    @Override
    public Integer updateGrade(int mark, String courseName, String userId) {
        return courseMapper.updateGrade(mark, courseName, userId);
    }

    @Override
    public List<Integer> selectMark(String courseName, String[] ids) {

        return courseMapper.selectMark(courseName,ids);
    }

    @Override
    public Integer isCourseExist(String courseName) {
        return courseMapper.isCourseExist(courseName);
    }

    @Override
    public Integer addCourse(String courseName) {
        return courseMapper.addCourse(courseName);
    }

    @Override
    public Integer addEnrollment(String id, String courseName) {
        return courseMapper.addEnrollment(id, courseName);
    }

    @Override
    public List<Course> selectAllCourse(String id) {

        return courseMapper.selectAllCourse(id);
    }

    @Override
    public List<User> selectEnrolledStudent(String courseName, int index, int pageSize) {
        return  userMapper.selectEnrolledStudent(courseName,index,pageSize);
    }

    @Override
    public User selectByUser(User user) {
        return userMapper.selectByUser(user);
    }



    @Override
    public boolean insert(User user) {
        int num = userMapper.insert(user);
        return num!=0? true:false;
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public User selectByUserId(String userId) {
        return userMapper.selectByUserId(userId);
    }

    @Override
    public int selectTableSize() {
        return userMapper.selectTableSize();
    }

    @Override
    public List<User> selectAllByPage(int index, int pageSize) {
        return userMapper.selectAllByPage(index, pageSize);
    }

    @Override
    public int update(User user) {

        return userMapper.update(user);
    }

    @Override
    public int delete(int[] userIds) {
        return userMapper.delete(userIds);
    }




}
