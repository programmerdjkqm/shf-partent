package controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.djk.server.DictService;
import entity.Dict;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import result.Result;

import java.util.List;
import java.util.Map;

@Controller
public class DictController {
    @Reference
    private DictService dictService;
    @RequestMapping("/dict")
public  String toindex(){
    return "dict/index";
}
@ResponseBody
@RequestMapping("/dict/findZnodes")
    public Result<List> findzonodes(@RequestParam(value = "id",defaultValue = "0") String id){

    List<Map<String, Object>> znodes = dictService.findZnodes(Long.valueOf(id));

    return Result.ok(znodes);
}
@ResponseBody
@RequestMapping("/dict/findListByParentId/{areaId}")
    public Result<List<Dict>> ji(@PathVariable("areaId") String id){
    List<Dict> slectznodesbypartentid = dictService.slectznodesbypartentid(Long.valueOf(id));
    return Result.ok(slectznodesbypartentid);
}
}
