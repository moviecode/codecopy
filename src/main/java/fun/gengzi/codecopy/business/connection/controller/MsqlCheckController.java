package fun.gengzi.codecopy.business.connection.controller;

import fun.gengzi.codecopy.business.connection.entity.MysqlDTO;
import fun.gengzi.codecopy.business.connection.service.CheckMysqlConnectionService;
import fun.gengzi.codecopy.vo.ReturnData;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


/**
 * <h1>mysql链接检测</h1>
 *
 * @author gengzi
 * @date 2020年7月14日15:58:44
 */
@Api(value = "mysql链接检测", tags = {"mysql链接检测"})
@Controller
@RequestMapping("/mysqlCheckController")
public class MsqlCheckController {

    private Logger logger = LoggerFactory.getLogger(MsqlCheckController.class);

    @Autowired
    private CheckMysqlConnectionService checkMysqlConnectionService;


    @ApiOperation(value = "测试", notes = "测试")
    @ApiImplicitParams({})
    @ApiResponses({@ApiResponse(code = 200, message = "\t{\n" +
            "\t    \"status\": 200,\n" +
            "\t    \"info\": {\n" +
            "\t		}\n" +
            "\t    \"message\": \"信息\",\n" +
            "\t}\n")})
    @PostMapping("/testCheckInfo")
    @ResponseBody
    public ReturnData insertInfo() {
        checkMysqlConnectionService.checkMysqlConnectionIsEnable(new MysqlDTO());
        ReturnData ret = ReturnData.newInstance();
        ret.setSuccess();
        return ret;
    }


}
