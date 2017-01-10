package edu.oakland;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apereo.portal.soffit.renderer.SoffitRendererController;
import java.util.Map;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class IPInjector {
    @Autowired
    SoffitRendererController soffitRendererController;

    @Value("${edu.oakland.IPInjector.ip}")
    String ip;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/soffit/ou-redis")
    public ModelAndView render(final HttpServletRequest req, final HttpServletResponse res) {
        logger.error(ip);
        ModelAndView mav = soffitRendererController.render(req, res, "ou-redis");
        mav.addObject("ip", ip);
        
        return mav;
    }

}
