package controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.djk.server.houseServerjie;
import entity.HouseImage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import result.Result;
import util.QiniuUtils;

import java.util.Map;

@Controller
public class houseImagecontroll {
    @Reference
    private houseServerjie houseServerjie;
    @RequestMapping("/houseImage/uploadShow/{houseid}/{type}")
    public  String toadd(@PathVariable("houseid")Long houseid , @PathVariable("type")Long id, Map map){
   map.put("houseId",houseid);
   map.put("type",id);
        return "house/upload";
    }
    @RequestMapping("/houseImage/upload/{houseId}/{type}")
    public Result add(@PathVariable("houseId")Long houseid , @PathVariable("type")Long type,@RequestParam(value = "file") MultipartFile[] files)
    throws Exception{
        for (MultipartFile file : files) {
            String name = file.getOriginalFilename();
            QiniuUtils.upload2Qiniu(file.getBytes(),name);

        HouseImage houseImage = new HouseImage();
        houseImage.setHouseId(houseid);
        houseImage.setImageName(name);
        houseImage.setImageUrl("http://rquaa8zgf.hb-bkt.clouddn.com/"+name);
        houseImage.setType(Math.toIntExact(type));
        houseServerjie.addimage(houseImage);
    }
    return Result.ok();}
    @RequestMapping("/houseImage/delete/{houseid}/{id}")
    public String del(@PathVariable("houseid") Long houseid,@PathVariable("id") Long id){
        houseServerjie.delimage(id);
        return "redirect:/house/"+houseid;
    }
}
