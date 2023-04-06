package com.xbg3;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xbg3.mapper.CourseMapper;
import com.xbg3.mapper.UserMapper;
import com.xbg3.pojo.Course;
import com.xbg3.pojo.User;
import com.xbg3.service.UserService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@MapperScan("com.xbg3.mapper")
class SpringBoot01ApplicationTests {

    @Autowired
    private UserMapper userDao;

    @Autowired
    private CourseMapper courseDao;


    @Test
    void selectEnrolledStudent(){
      //  courseDao.deleteCourse("COMP5500","110091573");
    }
    @Test
    void checkCourse(){
        int temp = 0;
        try{
            temp = courseDao.isCourseExist("COMP33300");
        }catch (Exception e){

        }
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA:: "+temp);
    }


    @Test
    void addEnrollment(){
        courseDao.addEnrollment("2","COMP4400");
    }

    @Test
    void addCourse(){
        courseDao.addCourse("testclass");
    }


    @Test
    void selectCourse(){
/*        List<Course> course = service.selectCourseById("110055905");*/

        List<Course> course = courseDao.selectAllCourse("110055905");
        for(Course c : course){
            System.out.println("out222!!!!!!!\n"+c);

        }
    }


    @Test
    void contextLoads() {
        LambdaQueryWrapper<User>  wrapper = new LambdaQueryWrapper<>();
        wrapper.gt(User::getUserId,3).lt(User::getUserId,10);
        List<User> userList = userDao.selectList(wrapper);
        System.out.println(userList);
    }

    @Test
    void getAll(){
        List<User> userList = userDao.selectList(null);
        System.out.println(userList);
    }

    @Test
    void getPage(){
        IPage page = new Page(1,2);
        userDao.selectPage(page,null);
        System.out.println(page.getRecords());


    }

}
