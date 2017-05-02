package com.wittymonkey.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wittymonkey.entity.*;
import com.wittymonkey.service.IFinanceService;
import com.wittymonkey.service.IInStockService;
import com.wittymonkey.service.IReimburseService;
import com.wittymonkey.service.ISalaryHistoryService;
import com.wittymonkey.util.DateUtil;
import com.wittymonkey.vo.Constraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by neilw on 2017/5/1.
 */
@Controller
public class ReportController {

    @Autowired
    private IFinanceService financeService;

    @Autowired
    private ISalaryHistoryService salaryHistoryService;

    @Autowired
    private IReimburseService reimburseService;

    @Autowired
    private IInStockService inStockService;
    @RequestMapping(value = "getReport", method = RequestMethod.GET)
    @ResponseBody
    public String getReport(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        Integer type = Integer.parseInt(request.getParameter("type"));
        Date from = null;
        Date to = null;
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (Constraint.REPORT_LAST_MONTH.equals(type)) {
            from = DateUtil.lastMonthFirstDate(now);
            to = DateUtil.lastMonthLastDate(now);
        } else if (Constraint.REPORT_LAST_YEAR.equals(type)) {
            from = DateUtil.lastYearFirstDate(now);
            to = DateUtil.lastYearLastDate(now);
        } else if (Constraint.REPORT_CUSTOM.equals(type)) {
            try {
                from = simpleDateFormat.parse(request.getParameter("from"));
                to = simpleDateFormat.parse(request.getParameter("to"));
            } catch (ParseException e) {
                json.put("status", 400);
                return json.toJSONString();
            }
        }
        // 获取收入
        List<Finance> inFinance = financeService.getFinanceByPage(hotel.getId(), Constraint.FINANCE_IN_ALL, from, to, null, null);
        // 获取支出
        List<Finance> outFinance = financeService.getFinanceByPage(hotel.getId(), Constraint.FINANCE_OUT_ALL, from, to, null, null);
        List<SalaryHistory> histories = salaryHistoryService.getSalaryByDateRange(from, to);
        List<Reimburse> reimburses = reimburseService.getReimburseByPage(hotel.getId(), Constraint.REIMBURSE_STATUS_APPROVED, from, to, null, null);
        List<InStock> inStocks = inStockService.getInStockByDateRange(hotel.getId(), from, to);
        Map<String, Double> outMap = new HashMap<String, Double>();
        Map<String, Double> inMap = new HashMap<String, Double>();
        JSONArray outType = new JSONArray();
        JSONArray outValue = new JSONArray();
        JSONArray inType = new JSONArray();
        JSONArray inValue = new JSONArray();
        Double in = 0.0;
        Double out = 0.0;
        for (Finance finance : inFinance) {
            in += finance.getMoney();
            Double value;
            if (inMap.containsKey(finance.getFinanceType().getName())) {
                value = inMap.get(finance.getFinanceType().getName()) + finance.getMoney();
            } else {
                value = finance.getMoney();
            }
            inMap.put(finance.getFinanceType().getName(), value);
        }
        for (Finance finance : outFinance) {
            out += finance.getMoney();
            Double value;
            if (outMap.containsKey(finance.getFinanceType().getName())) {
                value = outMap.get(finance.getFinanceType().getName()) + finance.getMoney();
            } else {
                value = finance.getMoney();
            }
            outMap.put(finance.getFinanceType().getName(), value);
        }
        Double salary = 0.0;
        for (SalaryHistory history : histories) {
            out += history.getAmount();
            salary += history.getAmount();
        }
        outMap.put("salay(工资)", salary);
        Double reim = 0.0;
        for (Reimburse reimburse : reimburses) {
            out += reimburse.getMoney();
            reim += reimburse.getMoney();
        }
        outMap.put("reimbuse(报销)", reim);
        Double inStockMoney = 0.0;
        for (InStock inStock : inStocks){
            out += inStock.getPayment();
            inStockMoney += inStock.getPayment();
        }
        outMap.put("Purchase Out(进货支出)",inStockMoney);
        for (Map.Entry<String, Double> entry: inMap.entrySet()){
            inType.add(entry.getKey());
            inValue.add(entry.getValue());
        }
        for (Map.Entry<String, Double> entry: outMap.entrySet()){
            outType.add(entry.getKey());
            outValue.add(entry.getValue());
        }
        json.put("status", 200);
        json.put("totalIn", in);
        json.put("totalOut", out);
        json.put("inType", inType);
        json.put("inValue", inValue);
        json.put("outType", outType);
        json.put("outValue", outValue);
        return json.toJSONString();
    }
}
