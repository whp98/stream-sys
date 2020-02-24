package xyz.intellij.streamsys.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xyz.intellij.streamsys.entity.UserEntity;
import xyz.intellij.streamsys.repo.UserListRepo;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserListRepo userListRepo;
    @Autowired
    public UserController(UserListRepo userListRepo){
        this.userListRepo=userListRepo;
    }

    /*login登录接口*/
    @ResponseBody
    @RequestMapping(value = "login",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String login(@RequestBody JSONObject jsonParam) throws JsonProcessingException {
        //输出登录信息
        System.out.println("login:"+jsonParam.toJSONString());
        //判断该用户是否存在
        if(userListRepo.findByUserEmail(jsonParam.getString("userEmail")).size()<1){
            JSONObject response = new JSONObject();
            response.put("msg","用户不存在");
            return response.toJSONString();
        }else{
            JSONObject response = new JSONObject();
            ObjectMapper objm = new ObjectMapper();
            List<UserEntity> res = userListRepo.findByUserEmail(jsonParam.getString("userEmail"));
            UserEntity log = res.get(0);
            if (log.getUserPwd().equals(jsonParam.getString("userPwd"))){
                JSONObject jso = JSON.parseObject(objm.writeValueAsString(log));
                response.put("user",jso);
                response.put("msg","登陆成功");
            }else{
                response.put("msg","密码错误");
            }

            return response.toJSONString();
        }
    }

    /*reg注册接口*/
    @ResponseBody
    @RequestMapping(value = "register",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String register(@RequestBody JSONObject jsonParam) throws IOException {
        //输出登录信息
        System.out.println("register:"+jsonParam.toJSONString());
        //判断该邮箱是否被使用过
        JSONObject response = new JSONObject();
        if(userListRepo.findByUserEmail(jsonParam.getString("userEmail")).size()>0){
            response.put("msg","邮箱已被使用");
            response.put("user",jsonParam);
            return response.toJSONString();
        }else{
            //数据持久化，并且返回注册信息
            ObjectMapper objm = new ObjectMapper();
            UserEntity userEntity = objm.readValue(jsonParam.toJSONString(),new TypeReference<UserEntity>(){});
            userListRepo.save(userEntity);
            response.put("msg","注册成功");
            response.put("user",jsonParam);
            return response.toJSONString();
        }
    }
}
