package com.wittymonkey.controller;

import com.alibaba.fastjson.JSONObject;
import com.wittymonkey.entity.Notify;
import com.wittymonkey.entity.NotifyReceiver;
import com.wittymonkey.entity.User;
import com.wittymonkey.service.INotifyReceiverService;
import com.wittymonkey.service.INotifyService;
import com.wittymonkey.service.IUserService;
import com.wittymonkey.util.ChangeToSimple;
import com.wittymonkey.vo.SimpleNotify;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by neilw on 2017/5/3.
 */
@Controller
public class NotifyController {

    @Autowired
    private IUserService userService;

    @Autowired
    private INotifyService notifyService;

    @Autowired
    private INotifyReceiverService notifyReceiverService;

    @RequestMapping(value = "toAddNotify", method = RequestMethod.GET)
    public String toAddNotify(HttpServletRequest request) {
        return "notify_add";
    }

    @RequestMapping(value = "sendNotify", method = RequestMethod.POST)
    @ResponseBody
    public String sendNotify(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String receiverStr = request.getParameter("receiver");
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");
        String[] reciever = receiverStr.split(";");
        Set<String> receivers = new HashSet<String>();
        for (String str : reciever) {
            str = str.trim();
            if (StringUtils.isNotBlank(str)) {
                receivers.add(str);
            }
        }
        if (receivers.size() == 0) {
            json.put("status", 400);
            return json.toJSONString();
        }
        User user = (User) request.getSession().getAttribute("loginUser");
        Notify notify = new Notify();
        notify.setSubject(subject);
        notify.setContent(content);
        notify.setSender(userService.getUserById(user.getId()));
        notify.setSendDate(new Date());
        notify.setInTrash(false);
        notify.setDeleted(false);
        List<NotifyReceiver> notifyReceivers = new ArrayList<NotifyReceiver>();
        for (String rec : receivers) {
            User recUser = userService.getUserByStaffNo(rec);
            if (recUser == null) {
                continue;
            }
            NotifyReceiver receiver = new NotifyReceiver();
            receiver.setIsDelete(false);
            receiver.setIsReaded(false);
            receiver.setNotify(notify);
            receiver.setReceiver(recUser);
            notifyReceivers.add(receiver);
        }
        notify.setReceivers(notifyReceivers);
        notifyService.save(notify);
        json.put("status", 200);
        return json.toJSONString();
    }

    @RequestMapping(value = "inBox", method = RequestMethod.GET)
    @ResponseBody
    public String inBox(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("loginUser");
        Integer curr = Integer.parseInt(request.getParameter("curr"));
        JSONObject json = getNotify(user, curr, false);
        return json.toJSONString();
    }

    private JSONObject getNotify(User user, Integer curr, Boolean delete) {
        JSONObject json = new JSONObject();
        Integer pageSize = user.getSetting().getPageSize();
        Integer count = notifyReceiverService.getTotalByStaffNo(user.getStaffNo(), delete);
        List<NotifyReceiver> notifyReceivers = notifyReceiverService.getNotifyReceiverByStaffNo(user.getStaffNo(), delete, (curr - 1) * pageSize, pageSize);
        List<SimpleNotify> simpleNotifies = ChangeToSimple.notifyReceiverList(notifyReceivers);
        json.put("count", count);
        json.put("data", simpleNotifies);
        return json;
    }

    @RequestMapping(value = "outBox", method = RequestMethod.GET)
    @ResponseBody
    public String outBox(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        User user = (User) request.getSession().getAttribute("loginUser");
        Integer curr = Integer.parseInt(request.getParameter("curr"));
        Integer pageSize = user.getSetting().getPageSize();
        Integer count = notifyService.getTotalSendByUser(user.getId(), false, false);
        List<Notify> notifies = notifyService.getNotifySendByUser(user.getId(), false, false, (curr - 1) * pageSize, pageSize);
        List<SimpleNotify> simpleNotifies = ChangeToSimple.notifyList(notifies);
        json.put("count", count);
        json.put("data", simpleNotifies);
        return json.toJSONString();
    }

    @RequestMapping(value = "trashBin", method = RequestMethod.GET)
    @ResponseBody
    public String trashBin(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("loginUser");
        Integer curr = Integer.parseInt(request.getParameter("curr"));
        JSONObject json = getNotify(user, curr, true);
        return json.toJSONString();
    }

    @RequestMapping(value = "toShowNotify", method = RequestMethod.GET)
    public String toShowNotify(HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Notify notify = notifyService.getNotifyById(id);
        User user = (User) request.getSession().getAttribute("loginUser");
        for (NotifyReceiver receiver : notify.getReceivers()){
            if (receiver.getReceiver().getId().equals(user.getId())){
                receiver.setIsReaded(true);
                break;
            }
        }
        notifyService.save(notify);
        request.setAttribute("notify", ChangeToSimple.notify(notify));
        return "notify_detail";
    }
}
