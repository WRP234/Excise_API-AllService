package com.xcs.phase2.controller.revenue;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.revenue.RevenueDetailDAO;
import com.xcs.phase2.model.revenue.RevenueDetail;
import com.xcs.phase2.request.revenue.RevenueDetailupdDeleteReq;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.revenue.RevenueDetailinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RevenueDetailController {

    private static final Logger log = LoggerFactory.getLogger(RevenueDetailController.class);

    @Autowired
    private RevenueDetailDAO revenueDetailDAO;

    @PostMapping(value = "/RevenueDetailinsAll")
    public ResponseEntity RevenueDetailinsAll(@RequestBody RevenueDetail req) {

        log.info("============= Start API RevenueDetailinsAll ================");
        RevenueDetailinsAllResponse res = null;
        try {

            res = revenueDetailDAO.RevenueDetailinsAll(req);

        } catch (Exception e) {
            res.setIsSuccess(Message.FALSE);
            res.setMsg(e.getMessage());
        }
        log.info("============= End API RevenueDetailinsAll =================");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping(value = "/RevenueDetailupdDelete")
    public ResponseEntity RevenueDetailupdDelete(@RequestBody RevenueDetailupdDeleteReq req) {

        log.info("============= Start API RevenueDetailupdDelete ================");

        MessageResponse msg = new MessageResponse();
        try {
            if(revenueDetailDAO.RevenueDetailupdDelete(req)){
                msg.setIsSuccess(Message.TRUE);
                msg.setMsg(Message.COMPLETE);
            }else{
                msg.setIsSuccess(Message.FALSE);
                msg.setMsg(Message.NOT_COMPLETE);
            }

        } catch (Exception e) {
            msg.setIsSuccess(Message.FALSE);
            msg.setMsg(e.getMessage());
        }
        log.info("============= End API RevenueDetailupdDelete =================");
        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
