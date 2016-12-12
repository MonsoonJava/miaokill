package org.seckill.web;

import java.util.Date;
import java.util.List;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.dto.SeckillResult;
import org.seckill.enums.SeckillStatusEnum;
import org.seckill.exception.RepeatSeckillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.pojo.Seckill;
import org.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "seckill")
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView seckillList(ModelAndView mv) {
        List<Seckill> allSeckill = seckillService.getAllSeckill();
        mv.addObject("allSeckill", allSeckill);
        mv.setViewName("list");
        return mv;
    }

    @RequestMapping(value = "/{seckillId}/detial", method = RequestMethod.GET)
    public String getSeckillDetail(Model model, @PathVariable("seckillId") Integer seckillId) {
        if (seckillId == null) {
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getSeckillById(seckillId);
        if (null == seckill) {
            return "redirect:/seckill/list";
        } else {
            model.addAttribute("seckill", seckill);
            return "detial";
        }
    }

    // 返回json格式，为ajax的接口
    @RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public SeckillResult<Exposer> getSeckillExposertion(@PathVariable("seckillId") int seckillId) {
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if (null == exposer) {
            SeckillResult<Exposer> seckillResult = new SeckillResult(false, "the seckilling is not existed");
            return seckillResult;
        } else {
            SeckillResult<Exposer> seckillResult = new SeckillResult(true, exposer);
            return seckillResult;
        }
    }

    @RequestMapping(value = "/{seckillId}/{md5}/excution", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public SeckillResult<SeckillExcution> doSeckillExcution(@PathVariable("seckillId") int seckillId,
            @PathVariable("md5") String md5,
            @CookieValue(value = "userPhone", required = false) long userPhone) {
        SeckillResult<SeckillExcution> seckillResult = null;
        SeckillExcution seckillExcution = null;
        try {
            seckillExcution = seckillService.ExecuteSeckill(seckillId, userPhone, md5);
            seckillResult = new SeckillResult<SeckillExcution>(true,seckillExcution);
            return seckillResult;
        } catch (RepeatSeckillException e) {
            seckillExcution = new SeckillExcution(seckillId,SeckillStatusEnum.REPEAT_SECKILL);
            return  new SeckillResult<SeckillExcution>(false,seckillExcution);
        }catch (SeckillCloseException e) {
            seckillExcution = new SeckillExcution(seckillId,SeckillStatusEnum.END);
            return  new SeckillResult<SeckillExcution>(false,seckillExcution);
        }catch (Exception e) {
            seckillExcution = new SeckillExcution(seckillId,SeckillStatusEnum.INNER_ERROR );
            return  new SeckillResult<SeckillExcution>(false,seckillExcution);
        }
    }
    
    @RequestMapping(value="/time/now",method=RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> getNow(){
        Date now = new Date();
        return new SeckillResult<Long>(true,now.getTime()); 
    }
    
    

}
