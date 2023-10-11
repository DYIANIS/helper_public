package com.shtura.helper.service.aptekajet;

import java.util.List;

import com.shtura.helper.dao.aptekajet.CheckDao;
import com.shtura.helper.entity.aptekajet.Сheck;

public class CheckService {

    private CheckDao checkDao = new CheckDao();
    
    public CheckService() {
        
    }
    
    public Сheck findCheck(int id) {
        return checkDao.findById(id);
    }
    
    public List<Сheck> findByIdAndDate(int id, String date) {
        return checkDao.findByIdAndDate(id, date);
    }
    
    public List<Сheck> findByNumber(String number, String needRegistrar, String needDate) {
        return checkDao.findByNumber(number, needRegistrar, needDate); 
    }
    
    public List<Сheck> findByNumberWisoutNeedRegistrar(String number, String needDate) {
        return checkDao.findByNumberWisoutNeedRegistrar(number, needDate); 
    }
    
    public void saveCheck(Сheck check) {
        this.checkDao.save(check);
    }
    
    public void deleteCheck(Сheck check) {
        this.checkDao.delete(check);
    }
    
    public void updateCheck(Сheck check) {
        this.checkDao.update(check);
    }
    
    public List<Сheck> findAllChecks() {
        return checkDao.findAll();
    }
    
    public List<String> getAllFR(){
        return checkDao.getAllFR();
    }
    
    public List<Сheck> findСhecksForEdit(Integer startCheckKey, Integer endCheckKey, Integer sellerIdFrom, String needRegistrar, String needDate) {
        return checkDao.findСhecksForEdit(startCheckKey, endCheckKey, sellerIdFrom, needRegistrar, needDate);
    }
    
    public List<Сheck> findСhecksForEditWisoutFromSeller(Integer startCheckKey, Integer endCheckKey, String needRegistrar, String needDate) {
        return checkDao.findСhecksForEditWisoutFromSeller(startCheckKey, endCheckKey, needRegistrar, needDate);
    }
    
    public void editSellerInСhecks(Integer startKey, Integer endKey, Integer sellerIdFrom, Integer sellerIdTo, String needRegistrar, String fromDateTime, String toDateTime) {
        checkDao.editSellerInСhecks(startKey, endKey, sellerIdFrom, sellerIdTo, needRegistrar, fromDateTime, toDateTime);
        //return checkDao.editSellerInСhecks(startKey, endKey, sellerIdFrom, sellerIdTo, needRegistrar, fromDateTime, toDateTime);
    }
    
    public List<Сheck> findСhecksByDateTime(String dateTime) {
        return checkDao.findСhecksByDateTime(dateTime);
    }
    
    public List<Сheck> findStartСhecksByDateTime(String dateTime) {
        return checkDao.findStartСhecksByDateTime(dateTime);
    }
    
    public List<Сheck> findEndСhecksByDateTime(String dateTime) {
        return checkDao.findEndСhecksByDateTime(dateTime);
    }
    
    public void kickStorage(Integer checkKey) {
        checkDao.kickStorage(checkKey);
    }
}
