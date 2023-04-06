package com.xbg3.controller;


import com.xbg3.pojo.Code;
import com.xbg3.pojo.Course;
import com.xbg3.pojo.Result;
import com.xbg3.pojo.User;
import com.xbg3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    Environment environment;

    @Autowired
    private UserService userService;


/*    @PostMapping("/{id}")
    public String getUser(@PathVariable Integer id) {
        System.out.println(userXbg.toString());
//        System.out.println(environment.getProperty("UserXbg.username"));
//        System.out.println(environment.getProperty("UserXbg.password"));
        System.out.println(id);
        return "hello SpringBoot";
    }*/
    @PostMapping("/login/{type}")
    public Result getUser(@RequestBody User user, @PathVariable String type, HttpServletRequest request){

        if(null!=userService.selectByUser(user)){
            HttpSession session = request.getSession(true);
            session.setAttribute("accountType", type);
            session.setAttribute("user",user);
            return new Result(Code.GET_OK,user);
        }
        return new Result(Code.GET_ERROR,user);
    }

    @GetMapping("/course")
    public Result getAllCourse(HttpServletRequest request){
        if(request.getSession().getAttribute("accountType").equals("Teacher")){
            User user = (User)request.getSession().getAttribute("user");
            List<Course> courses = userService.selectAllCourse(user.getUserId());
            return new Result(courses.size()>0 ?Code.GET_OK:Code.GET_ERROR,courses);
        }

        return new Result(Code.GET_ERROR,null);
    }
    @GetMapping("/student/course")
    public Result getAllCourseForStudent(HttpServletRequest request){
        if(request.getSession().getAttribute("accountType").equals("Student")){
            User user = (User)request.getSession().getAttribute("user");
            List<Course> courses = userService.selectAllCourse(user.getUserId());
            return new Result(courses.size()>0 ?Code.GET_OK:Code.GET_ERROR,courses);
        }

        return new Result(Code.GET_ERROR,null);
    }



}
