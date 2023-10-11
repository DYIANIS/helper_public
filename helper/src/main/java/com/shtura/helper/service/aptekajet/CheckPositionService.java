package com.shtura.helper.service.aptekajet;

import java.util.List;

import com.shtura.helper.dao.aptekajet.CheckPositionDao;
import com.shtura.helper.entity.aptekajet.CheckPosition;

public class CheckPositionService {

    private CheckPositionDao checkPositionDao = new CheckPositionDao();
    
    public CheckPositionService() {
        
    }
    
    public CheckPosition findSeller(int id) {
        return checkPositionDao.findById(id);
    }
    
    public void saveSeller(CheckPosition checkPosition) {
        this.checkPositionDao.save(checkPosition);
    }
    
    public void deleteSeller(CheckPosition checkPosition) {
        this.checkPositionDao.delete(checkPosition);
    }
    
    public void updateSeller(CheckPosition checkPosition) {
        this.checkPositionDao.update(checkPosition);
    }
    
    public List<CheckPosition> findByCheckKey(Integer checkKey) {
        return this.checkPositionDao.findByCheckKey(checkKey);
        //return (Seller) HibernateSessionFactoryUtil.getSessionFactory().openSession().createCriteria(Seller.class).add(Restrictions.eq("Имя", name)).list().get(0);
    }
    
    public List<Integer> findForbiddenСhecksForkickStorage(Integer startCheckKey, Integer endCheckKey) {
        return this.checkPositionDao.findCheckKeyForbiddenСhecksForkickStorage(startCheckKey, endCheckKey);
    }

}
