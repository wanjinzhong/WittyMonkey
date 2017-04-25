package com.wittymonkey.util;

import com.wittymonkey.controller.IndexController;
import com.wittymonkey.entity.*;
import com.wittymonkey.vo.*;

import java.io.IOException;
import java.util.*;

/**
 * Created by neilw on 2017/2/16.
 */
public class ChangeToSimple {

    public static List<SimpleFloor> floorList(List<Floor> floors) {
        List<SimpleFloor> simpleFloors = new ArrayList<SimpleFloor>();
        for (Floor floor : floors) {
            SimpleFloor simpleFloor = new SimpleFloor();
            simpleFloor.setId(floor.getId());
            simpleFloor.setFloorNo(floor.getFloorNo());
            Iterator<RoomMaster> it = floor.getRoomMasters().iterator();
            while (it.hasNext()) {
                if (it.next().getDelete()) {
                    it.remove();
                }
            }
            simpleFloor.setRoomNum(floor.getRoomMasters().size());
            simpleFloor.setEntryUser(floor.getEntryUser().getRealName());
            simpleFloor.setEntryDatetime(floor.getEntryDatetime());
            simpleFloor.setNote(floor.getNote());
            simpleFloors.add(simpleFloor);
        }
        return simpleFloors;
    }

    public static List<SimpleRoom> roomList(List<RoomMaster> roomMasters) {
        List<SimpleRoom> simpleRooms = new ArrayList<SimpleRoom>();
        for (RoomMaster room : roomMasters) {
            SimpleRoom simpleRoom = new SimpleRoom();
            simpleRoom.setId(room.getId());
            simpleRoom.setFloorNo(room.getFloor().getFloorNo());
            simpleRoom.setArea(room.getArea());
            simpleRoom.setName(room.getName());
            simpleRoom.setNumber(room.getNumber());
            simpleRoom.setPrice(room.getPrice());
            simpleRoom.setSingleBedNum(room.getSingleBedNum());
            simpleRoom.setDoubleBedNum(room.getDoubleBedNum());
            simpleRoom.setAvailableNum(room.getAvailableNum());
            simpleRoom.setStatus(room.getStatus());
            simpleRoom.setThumbUrl(room.getThumbUrl());
            simpleRoom.setEntryDatetime(room.getEntryDatetime());
            simpleRoom.setUserName(room.getEntryUser().getRealName());
            simpleRooms.add(simpleRoom);
        }
        return simpleRooms;
    }

    public static List<SimpleReserve> reserveList(List<Reserve> reserves) {
        List<SimpleReserve> simpleReserves = new ArrayList<SimpleReserve>();
        for (Reserve reserve : reserves) {
            SimpleReserve simpleReserve = new SimpleReserve();
            simpleReserve.setId(reserve.getId());
            simpleReserve.setCustomer(reserve.getCustomer());
            simpleReserve.setReserveDate(reserve.getReserveDate());
            simpleReserve.setEstCheckinDate(reserve.getEstCheckinDate());
            simpleReserve.setEstCheckoutDate(reserve.getEstCheckoutDate());
            simpleReserve.setDeposit(reserve.getDeposit());
            simpleReserve.setStatus(reserve.getStatus());
            simpleReserve.setEntryDatetime(reserve.getEntryDatetime());
            simpleReserve.setEntryUser(reserve.getEntryUser().getRealName());
            simpleReserve.setNote(reserve.getNote());
            simpleReserves.add(simpleReserve);
        }
        return simpleReserves;
    }

    public static List<SimpleMaterielType> materielTypeList(List<MaterielType> materielTypes) {
        List<SimpleMaterielType> simpleMaterielTypes = new ArrayList<SimpleMaterielType>();
        for (MaterielType materielType : materielTypes) {
            SimpleMaterielType simpleMaterielType = new SimpleMaterielType();
            simpleMaterielType.setId(materielType.getId());
            simpleMaterielType.setName(materielType.getName());
            simpleMaterielType.setNote(materielType.getNote());
            simpleMaterielType.setEntryUser(materielType.getEntryUser().getRealName());
            simpleMaterielType.setEntryDatetime(materielType.getEntryDatetime());
            simpleMaterielType.setMaterielNum(materielType.getMateriels().size());
            simpleMaterielType.setEditable(materielType.getEditable());
            simpleMaterielType.setDefault(materielType.getDefault());
            simpleMaterielTypes.add(simpleMaterielType);
        }
        return simpleMaterielTypes;
    }

    public static List<SimpleUser> userList(List<User> users) {
        List<SimpleUser> simpleUsers = new ArrayList<SimpleUser>();
        for (User user : users) {
            SimpleUser simpleUser = new SimpleUser();
            simpleUser.setId(user.getId());
            simpleUser.setRealName(user.getRealName());
            simpleUser.setStaffNo(user.getStaffNo());
            simpleUser.setIdCardNo(user.getIdCardNo());
            simpleUser.setHotelName(user.getHotel().getName());
            simpleUser.setEntryDatetime(user.getEntryDatetime());
            simpleUser.setEntryUser(user.getEntryUser().getRealName());
            for (Role role : user.getRoles()) {
                simpleUser.getRoles().add(role.getName());
            }
            simpleUser.setTel(user.getTel());
            simpleUser.setEmail(user.getEmail());
            simpleUser.setRegistDate(user.getRegistDate());
            simpleUser.setDimissionDate(user.getDimissionDate());
            simpleUser.setDimissionNote(user.getDimissionNote());
            simpleUsers.add(simpleUser);
        }
        return simpleUsers;
    }

    public static List<SimpleRole> roleList(String lang, List<Role> roles) {
        List<SimpleRole> simpleRoles = new ArrayList<SimpleRole>();
        for (Role role : roles) {
            SimpleRole simpleRole = new SimpleRole();
            simpleRole.setId(role.getId());
            simpleRole.setEntryDatetime(role.getEntryDatetime());
            simpleRole.setEntryUser(role.getEntryUser().getRealName());
            simpleRole.setName(role.getName());
            simpleRole.setNote(role.getNote());
            for (User user : role.getUsers()) {
                simpleRole.getUsers().add(user.getRealName());
            }
            for (Menu menu : role.getMenus()) {
                simpleRole.getMenus().add(menuI180n(lang, menu).getName());
            }
            simpleRole.setEditable(role.getEditable());
            simpleRoles.add(simpleRole);
        }
        return simpleRoles;
    }

    public static Menu menuI180n(String lang, Menu menu) {
        Properties props = new Properties();
        try {
            props.load(IndexController.class.getResourceAsStream("/i18n/messages_" + lang + ".properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (menu.getId()) {
            case 1:
                menu.setName(props.getProperty("index.menu.floor"));
                break;
            case 2:
                menu.setName(props.getProperty("index.menu.room"));
                break;
            case 3:
                menu.setName(props.getProperty("index.menu.materiel"));
                break;
            case 4:
                menu.setName(props.getProperty("index.menu.inventory"));
                break;
            case 5:
                menu.setName(props.getProperty("index.menu.role"));
                break;
            case 6:
                menu.setName(props.getProperty("index.menu.staff"));
                break;
            case 7:
                menu.setName(props.getProperty("index.menu.leave"));
                break;
            case 8:
                menu.setName(props.getProperty("index.menu.finance"));
                break;
            case 9:
                menu.setName(props.getProperty("index.menu.report"));
                break;
            case 10:
                menu.setName(props.getProperty("index.menu.notify"));
                break;
            case 11:
                menu.setName(props.getProperty("index.menu.hotel_info"));
                break;
            case 12:
                menu.setName(props.getProperty("index.menu.settting"));
                break;
        }
        return menu;
    }

    public static List<Menu> menuI180n(String lang, List<Menu> menus) {
        List<Menu> res = new ArrayList<Menu>();
        for (Menu menu : menus) {
            res.add(menuI180n(lang, menu));
        }
        return res;
    }

    public static List<SimpleMenu> menuList(String lang, List<Menu> menus) {
        List<SimpleMenu> simpleMenus = new ArrayList<SimpleMenu>();
        for (Menu menu : menus) {
            menu = menuI180n(lang, menu);
            SimpleMenu simpleMenu = new SimpleMenu();
            simpleMenu.setId(menu.getId());
            simpleMenu.setDescription(menu.getDescription());
            simpleMenu.setName(menu.getName());
            simpleMenus.add(simpleMenu);
        }
        return simpleMenus;
    }

    public static List<SimpleFinanceType> financeTypeList(List<FinanceType> financeTypes) {
        List<SimpleFinanceType> simpleFinanceTypes = new ArrayList<SimpleFinanceType>();
        for (FinanceType financeType : financeTypes) {
            SimpleFinanceType simpleFinanceType = new SimpleFinanceType();
            simpleFinanceType.setId(financeType.getId());
            simpleFinanceType.setHotel(financeType.getHotel().getName());
            simpleFinanceType.setIncome(financeType.getIncome());
            simpleFinanceType.setNote(financeType.getNote());
            simpleFinanceType.setEntryUser(financeType.getEntryUser().getRealName());
            simpleFinanceType.setEntryDatetime(financeType.getEntryDatetime());
            simpleFinanceType.setName(financeType.getName());
            simpleFinanceType.setEditable(financeType.getEditable());
            simpleFinanceTypes.add(simpleFinanceType);
        }
        return simpleFinanceTypes;
    }

    public static List<SimpleFinance> financeList(List<Finance> finances) {
        List<SimpleFinance> simpleFinances = new ArrayList<SimpleFinance>();
        for (Finance finance : finances) {
            SimpleFinance simpleFinance = new SimpleFinance();
            simpleFinance.setEntryDatetime(finance.getEntryDatetime());
            simpleFinance.setEntryUser(finance.getEntryUser().getRealName());
            simpleFinance.setFinanceType(finance.getFinanceType().getName());
            simpleFinance.setId(finance.getId());
            simpleFinance.setMoney(finance.getMoney());
            simpleFinance.setNote(finance.getNote());
            simpleFinance.setIncome(finance.getFinanceType().getIncome());
            simpleFinances.add(simpleFinance);
        }
        return simpleFinances;
    }

    public static List<SimpleReimbuse> reimbuseList(List<Reimburse> reimburses){
        List<SimpleReimbuse> simpleReimbuses = new ArrayList<SimpleReimbuse>();
        for (Reimburse reimburse : reimburses){
            SimpleReimbuse simpleReimbuse = new SimpleReimbuse();
            simpleReimbuse.setApplyDatetime(reimburse.getApplyDatetime());
            simpleReimbuse.setApplyUser(reimburse.getApplyUser().getRealName());
            simpleReimbuse.setApplyUserNote(reimburse.getApplyUserNote());
            simpleReimbuse.setEntryDatetime(reimburse.getEntryDatetime());
            if (reimburse.getEntryUser() != null) {
                simpleReimbuse.setEntryUser(reimburse.getEntryUser().getRealName());
            }
            simpleReimbuse.setEntryUserNote(reimburse.getEntryUserNote());
            simpleReimbuse.setId(reimburse.getId());
            simpleReimbuse.setMoney(reimburse.getMoney());
            simpleReimbuse.setStatus(reimburse.getStatus());
            simpleReimbuse.setHotel(reimburse.getHotel().getName());
            simpleReimbuses.add(simpleReimbuse);
        }
        return simpleReimbuses;
    }

    public static List<SimpleSalary> salaryList(List<Salary> salaries){
        List<SimpleSalary> simpleSalaries = new ArrayList<SimpleSalary>();
        for (Salary salary : salaries){
            SimpleSalary simpleSalary = new SimpleSalary();
            simpleSalary.setId(salary.getId());
            simpleSalary.setStaff(salary.getStaff().getRealName());
            simpleSalary.setStaffNo(salary.getStaff().getStaffNo());
            simpleSalary.setSalaryRecords(salaryRecordList(salary.getSalaryRecords()));
            simpleSalaries.add(simpleSalary);
        }
        return simpleSalaries;
    }

    public static List<SimpleSalaryRecord> salaryRecordList(List<SalaryRecord> salaryRecords){
        List<SimpleSalaryRecord> simpleSalaryRecords = new ArrayList<SimpleSalaryRecord>();
        Collections.sort(salaryRecords, new Comparator<SalaryRecord>() {
            @Override
            public int compare(SalaryRecord o1, SalaryRecord o2) {
                return o2.getStartDate().after(o1.getStartDate())? 1 : -1;
            }
        });
        for(SalaryRecord salaryRecord : salaryRecords){
            SimpleSalaryRecord simpleSalaryRecord = new SimpleSalaryRecord();
            simpleSalaryRecord.setEntryDatetime(salaryRecord.getEntryDatetime());
            simpleSalaryRecord.setEntryUser(salaryRecord.getEntryUser().getRealName());
            simpleSalaryRecord.setId(salaryRecord.getId());
            simpleSalaryRecord.setMoney(salaryRecord.getMoney());
            simpleSalaryRecord.setNote(salaryRecord.getNote());
            simpleSalaryRecord.setStartDate(salaryRecord.getStartDate());
            simpleSalaryRecords.add(simpleSalaryRecord);
        }
        return simpleSalaryRecords;
    }

    /**
     * 提取工资记录在某个指定时间点上的工资
     * @param salary
     * @param date
     * @return
     */
    public static SalaryVO convertSalaryByTime(Salary salary, Date date){
        SalaryVO salaryVO = new SalaryVO();
        salaryVO.setId(salary.getId());
        salaryVO.setStaff(salary.getStaff().getRealName());
        salaryVO.setStaffNo(salary.getStaff().getStaffNo());
        List<SalaryRecord> salaryRecords = salary.getSalaryRecords();
        if (salaryRecords == null || salaryRecords.isEmpty()){
            return salaryVO;
        }
        Collections.sort(salaryRecords, new Comparator<SalaryRecord>() {
            @Override
            public int compare(SalaryRecord o1, SalaryRecord o2) {
                return o2.getStartDate().after(o1.getStartDate())? 1 : -1;
            }
        });
        for (SalaryRecord salaryRecord : salaryRecords){
            if (salaryRecord.getStartDate().before(date)){
                salaryVO.setStartDate(salaryRecord.getStartDate());
                salaryVO.setEntryDatetime(salaryRecord.getEntryDatetime());
                salaryVO.setEntryUser(salaryRecord.getEntryUser().getRealName());
                salaryVO.setMoney(salaryRecord.getMoney());
                salaryVO.setNote(salaryRecord.getNote());
                break;
            }
        }
        return salaryVO;
    }

    public static List<SalaryVO> convertSalariesByTime(List<Salary> salaries, Date date){
        List<SalaryVO> salaryVOS = new ArrayList<SalaryVO>();
        for (Salary salary : salaries){
            salaryVOS.add(convertSalaryByTime(salary, date));
        }
        return salaryVOS;
    }

    public static List<SimpleMateriel> materielList(List<Materiel> materiels){
        List<SimpleMateriel> simpleMateriels = new ArrayList<SimpleMateriel>();
        for (Materiel materiel : materiels){
            SimpleMateriel simpleMateriel = new SimpleMateriel();
            simpleMateriel.setBarcode(materiel.getBarcode());
            simpleMateriel.setEntryDatetime(materiel.getEntryDatetime());
            simpleMateriel.setEntryUser(materiel.getEntryUser().getRealName());
            simpleMateriel.setId(materiel.getId());
            simpleMateriel.setMaterielType(materiel.getMaterielType().getName());
            simpleMateriel.setName(materiel.getName());
            simpleMateriel.setNote(materiel.getNote());
            simpleMateriel.setSellPrice(materiel.getSellPrice());
            simpleMateriel.setStock(materiel.getStock());
            simpleMateriel.setUnit(materiel.getUnit());
            simpleMateriel.setWarningStock(materiel.getWarningStock());
            simpleMateriels.add(simpleMateriel);
        }
        return simpleMateriels;
    }

    public static List<SimpleInStock> inStockList(List<InStock> inStocks){
        List<SimpleInStock> simpleInStocks = new ArrayList<SimpleInStock>();
        for (InStock inStock : inStocks){
            SimpleInStock simpleInStock = new SimpleInStock();
            simpleInStock.setEntryDatetime(inStock.getEntryDatetime());
            simpleInStock.setEntryUser(inStock.getEntryUser().getRealName());
            simpleInStock.setId(inStock.getId());
            if (inStock.getMateriel() != null) {
                simpleInStock.setMateriel(inStock.getMateriel().getName());
            }
            simpleInStock.setNote(inStock.getNote());
            simpleInStock.setPayment(inStock.getPayment());
            simpleInStock.setPurchasePrice(inStock.getPurchasePrice());
            simpleInStock.setQuantity(inStock.getQuantity());
            simpleInStocks.add(simpleInStock);
        }
        return simpleInStocks;
    }
}
