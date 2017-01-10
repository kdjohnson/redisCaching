package edu.oakland;

import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.apereo.portal.soffit.model.v1_0.Bearer;
import org.apereo.portal.soffit.service.BearerService;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.cache.annotation.Cacheable;


import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.data.redis.core.StringRedisTemplate;

import org.springframework.data.redis.core.ValueOperations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import edu.oakland.IBannerDao;

@Controller
@CrossOrigin
public class Connect {

    @Autowired
    private StringRedisTemplate template;

    @Autowired
    IBannerDao bannerDao;

    @Autowired
    BearerService bearerService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/status")
    public @ResponseBody List<String> status(@ModelAttribute("token") String token) {
        try {
            Bearer bearer = bearerService.parseBearerToken(token);
            String pidm = bearer.getAttributes().get("pidm").get(0);
            return bannerDao.getFirstName(pidm);
        } catch(Exception e) {
            logger.error("{}", e);
            return null;
        }
    }


    
    @RequestMapping("/sendData")
    public String sendData() {
        ValueOperations<String, String> ops = this.template.opsForValue();
        String key = "oakland";
        if(!this.template.hasKey(key)) {
            ops.set(key, "foo");
        }
        return "Jello";
    } 

    @Cacheable()
    @RequestMapping("/getData")
    public String getData() {
        logger.error("oakland");
        ValueOperations<String, String> ops = this.template.opsForValue();
        String key = "foo";
        if(this.template.hasKey(key)) {
            logger.error(ops.get(key));
        }
       return "view";
    }
}
