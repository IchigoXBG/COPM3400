package com.xbg3.controller;


import com.xbg3.pojo.Code;
import com.xbg3.pojo.PageBean;
import com.xbg3.pojo.Result;
import com.xbg3.pojo.User;

import com.xbg3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class RestControler {

    @Autowired
    private UserService userService;

    @PostMapping("/addCourse")
    public Result addCourse(HttpServletRequest request, @RequestBody String courseName){

        User user = (User)request.getSession().getAttribute("user");
        Integer isExit;
        try{
            isExit = userService.isCourseExist(courseName);
            if(null == isExit){
                if(request.getSession().getAttribute("accountType").equals("Teacher")){
                    userService.addCourse(courseName);
                    userService.addEnrollment(user.getUserId(),courseName);
                }else{
                    return new Result(Code.SAVE_ERROR,false);
                }
            }else {
                userService.addEnrollment(user.getUserId(),courseName);
                System.out.println(""+isExit);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(Code.SAVE_OK,true);
    }

    @PostMapping("/insert")
    public Result insert(@RequestBody User user){
        boolean flag =  userService.insert(user);
        System.out.println(flag);
        return new Result(flag==true? Code.SAVE_OK:Code.SAVE_ERROR,flag);

    }

    @PutMapping("/update")
    public Result update(@RequestBody User user){
        return new Result(userService.update(user)>0?Code.UPDATE_OK:Code.UPDATE_ERROR,user);
    }

    @PutMapping("/updateGrade")
    public Result updateGrade(@RequestParam("mark") int mark,@RequestParam("courseName")String courseName,@RequestParam("userId")String userId){
        return new Result(userService.updateGrade(mark,courseName,userId)>0?Code.UPDATE_OK:Code.UPDATE_ERROR,true);
    }

    @GetMapping("/{course}/{currentPage}/{pageSize}")
    public Result getAll(@PathVariable String course,@PathVariable Integer currentPage, @PathVariable Integer pageSize){
        System.out.println("currentPage:" + currentPage + " pageSize:" + pageSize);
        List<User> users = userService.selectEnrolledStudent(course,(currentPage-1)*pageSize, pageSize);
        int tableSize = userService.selectTableSize();
        PageBean<User> bean = new PageBean<>();
        bean.setPageData(users);
        bean.setTotalPages(tableSize);

        return new Result((!users.isEmpty()&&tableSize!=0)? Code.GET_OK:Code.GET_ERROR,bean);
    }
    @PostMapping("/{course}/mark")
    public Result getMark(@PathVariable String course,@RequestBody String[] ids){


        List<Integer> marks = userService.selectMark(course,ids);

        return new Result(null!= marks? Code.GET_OK:Code.GET_ERROR,marks);
    }


    @PostMapping("/selectById/{id}")
    public Result getOne(@PathVariable String id){
        User user = userService.selectByUserId(id);
        return new Result(null!=user? Code.GET_OK:Code.GET_ERROR,user);
    }

    @DeleteMapping("/deleteEnrollment")
    public Result deleteEnrollment(@RequestParam("courseName")String courseName,@RequestParam("userId")String userId){
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+courseName+"  "+userId);

        return new Result(userService.deleteEnrollment(courseName,userId)>0?Code.DELETE_OK:Code.DELETE_OK,true);

    }


/*    @DeleteMapping("/delete")
    public Result delete(@RequestBody int[] selectedIds){

        return new Result(userService.delete(selectedIds)>0?Code.DELETE_OK:Code.DELETE_OK,selectedIds);

    }*/



/*    @PostMapping("/selectByid")
    public Result update(@RequestBody String userId){

        System.out.println(userId);
        User tempUser = userService.selectByUserId(userId);

        return new Result(null!=tempUser?Code.UPDATE_OK:Code.UPDATE_ERROR,tempUser);
    }*/



//    @PutMapping
//    public Result update(@RequestBody List<User> multipleSelection){
//
//        System.out.println(multipleSelection);
//        multipleSelection.stream().forEach(new Consumer<User>() {
//            @Override
//            public void accept(User user) {
//                System.out.println(user.toString());
//            }
//        });
//        return new Result(Code.SAVE_OK,null);
//
//    }



//    @GetMapping
//    public Result getAll(){
//
//
//        List<User> users = userService.selectAll();
//
//
//        return new Result(!users.isEmpty()? Code.GET_OK:Code.GET_ERROR,users);
//    }

/*
    @GetMapping("/{currentPage}/{pageSize}")
    public Result getAll(@PathVariable Integer currentPage, @PathVariable Integer pageSize){
        System.out.println("currentPage:" + currentPage + " pageSize:" + pageSize);
        List<User> users = userService.selectAllByPage((currentPage-1)*pageSize, pageSize);
        int tableSize = userService.selectTableSize();
        PageBean<User> bean = new PageBean<>();
        bean.setPageData(users);
        bean.setTotalPages(tableSize);

        return new Result((!users.isEmpty()&&tableSize!=0)? Code.GET_OK:Code.GET_ERROR,bean);
    }
*/





//    @RequestMapping("/insert")
//    public Result insert(@RequestBody User user){
//        boolean flag =  userService.insert(user);
//        System.out.println(flag);
//        return new Result(flag==true? Code.SAVE_OK:Code.SAVE_ERROR,flag);
//
//    }


}
