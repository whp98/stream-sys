package xyz.intellij.streamsys.web;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.intellij.streamsys.entity.RateEntity;
import xyz.intellij.streamsys.entity.RateEntityPK;
import xyz.intellij.streamsys.entity.StreamEntity;
import xyz.intellij.streamsys.repo.RateListRepo;
import xyz.intellij.streamsys.repo.StreamListRepo;

import java.io.IOException;
import java.util.Optional;

/**
 * 用户可以添加和修改评分
 */

@Controller
@RequestMapping("/rate")
public class RateController {

    private RateListRepo rateListRepo;

    @Autowired
    public RateController(RateListRepo rateListRepo) {
        this.rateListRepo = rateListRepo;
    }

    /*获取评分*/
    /*传入包含用户id和直播流id的json*/
    @ResponseBody
    @RequestMapping(value = "getone", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getOne(@RequestBody JSONObject jsonParam) throws IOException {
        ObjectMapper objm = new ObjectMapper();
        RateEntityPK pk = objm.readValue(jsonParam.toJSONString(),new TypeReference<RateEntityPK>(){});
        Optional<RateEntity> rateEntity;
        rateEntity = rateListRepo.findById(pk);

        if (rateEntity.isPresent()){
            return objm.writeValueAsString(rateEntity.get());
        }else{
            //如果是空的返回原值
            return jsonParam.toJSONString();
        }

    }
    /*添加评分*/
    /*传入包含用户id和直播流id,评分的json*/
    @ResponseBody
    @RequestMapping(value = "set", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String setRate(@RequestBody JSONObject jsonParam) throws IOException {
        ObjectMapper objm = new ObjectMapper();
        RateEntity rateEntity = objm.readValue(jsonParam.toJSONString(),new TypeReference<RateEntity>(){});
        rateListRepo.save(rateEntity);
        return jsonParam.toJSONString();
    }
}
