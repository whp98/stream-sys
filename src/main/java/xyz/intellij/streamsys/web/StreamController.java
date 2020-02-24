package xyz.intellij.streamsys.web;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.intellij.streamsys.entity.StreamEntity;
import xyz.intellij.streamsys.entity.UserEntity;
import xyz.intellij.streamsys.repo.StreamListRepo;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Controller
@RequestMapping("/stream")
public class StreamController {
    private StreamListRepo streamListRepo;

    @Autowired
    public StreamController(StreamListRepo streamListRepo) {
        this.streamListRepo = streamListRepo;
    }
    /*获取全部视频流,返回视频列表*/
    @ResponseBody
    @RequestMapping(value = "all", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getAll(@RequestBody JSONObject jsonParam) throws JsonProcessingException {
        List<StreamEntity> sl = streamListRepo.findAll();
        ObjectMapper objm = new ObjectMapper();
        objm.writeValueAsString(sl);
        return objm.writeValueAsString(sl);
    }
    /*添加视频流*/
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addOne(@RequestBody JSONObject jsonParam) throws IOException {
        //数据持久化，并且返回添加的信息
        ObjectMapper objm = new ObjectMapper();
        StreamEntity streamEntity = objm.readValue(jsonParam.toJSONString(),new TypeReference<StreamEntity>(){});
        streamListRepo.save(streamEntity);
        return jsonParam.toJSONString();
    }
}
