package fun.gengzi.codecopy.business.luckdraw.controller;

import fun.gengzi.codecopy.business.luckdraw.algorithm.LuckdrawAlgorithlm;
import fun.gengzi.codecopy.business.luckdraw.entity.LuckdrawAlgorithlmEntity;
import fun.gengzi.codecopy.business.luckdraw.entity.TbActivity;
import fun.gengzi.codecopy.vo.ReturnData;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


/**
 * <h1>luckdraw</h1>
 *
 * @author gengzi
 * @date 2020年9月7日14:56:38
 */
@Api(value = "luckdraw", tags = {"luckdraw"})
@Controller
@RequestMapping("/luckdraw")
public class LuckdrawController {


    /**
     * 发起抽奖接口，返回抽奖结果
     * 查询当前用户的抽奖记录和发放记录
     * 查看当前用户获得的奖品
     */


    private Logger logger = LoggerFactory.getLogger(LuckdrawController.class);

    @Autowired
    @Qualifier("DefaultLuckdrawAlgorithlm")
    private LuckdrawAlgorithlm luckdrawAlgorithlm;

    @ApiOperation(value = "xx", notes = "xx")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "TbActivity", value = "TbActivity", required = true)})
    @ApiResponses({@ApiResponse(code = 200, message = "\t{\n" +
            "\t    \"status\": 200,\n" +
            "\t    \"info\": {\n" +
            "\t		}\n" +
            "\t    \"message\": \"信息\",\n" +
            "\t}\n")})
    @PostMapping("/luckdraw")
    @ResponseBody
    public ReturnData luckdraw(@RequestParam("aid") String aid, @RequestBody TbActivity tbActivity) {
        logger.info("luckdraw quest param ,aid:{} , tbActivity :{}", aid, tbActivity);

        LuckdrawAlgorithlmEntity luckdrawAlgorithlmEntity = new LuckdrawAlgorithlmEntity();
        luckdrawAlgorithlmEntity.setActivityId("11");
        ArrayList<Double> probabilityList = new ArrayList<>(10);
        probabilityList.add(0.0001);
        probabilityList.add(0.01);
        probabilityList.add(0.1);
        probabilityList.add(0.2);
        probabilityList.add(0.3);
        probabilityList.add(0.3);
        luckdrawAlgorithlmEntity.setProbabilityList(probabilityList);
        for (int i = 0; i < 1000; i++) {
            Integer algorithlm = luckdrawAlgorithlm.algorithlm(luckdrawAlgorithlmEntity);
            logger.info("{}:{}", i, algorithlm);
        }

        ReturnData ret = ReturnData.newInstance();
        ret.setSuccess();
        return ret;
    }

//    @PostMapping("/error")
//    @ResponseBody
//    public ReturnData error() {
//        ReturnData ret = ReturnData.newInstance();
//        ret.setFailure("活动已经过期！");
//        return ret;
//    }


}

