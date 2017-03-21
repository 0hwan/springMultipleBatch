package com.test.example.web.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * The GreetingController class is a RESTful web service controller. The <code>@RestController</code> annotation informs
 * Spring that each <code>@RequestMapping</code> method returns a <code>@ResponseBody</code>.
 * 
 * @author yhahn@cyworld.biz
 */
@Profile("batch")
@RestController
@RequestMapping("/api/v0.1")
public class BatchController {

    /**
     * The Logger for this Class.
     */
    private static final Logger logger = LoggerFactory.getLogger(BatchController.class);

    /**
     * The TranscoderService business service.
     */
//    @Autowired
//    private transient SchedulerService transcoderService;

    @Autowired
    HttpServletRequest request;




}
