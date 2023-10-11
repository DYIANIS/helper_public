package com.shtura.helper.service.aptekajet;

import java.util.List;

import com.shtura.helper.dao.aptekajet.BuyerDao;
import com.shtura.helper.entity.aptekajet.Buyer;

public class BuyerService {

    private BuyerDao buyerDao = new BuyerDao();
    
    public BuyerService() {
        
    }
    
    public Buyer findBuyer(int id) {
        return buyerDao.findById(id);
    }
    
    public void saveBuyer(Buyer buyer) {
        this.buyerDao.save(buyer);
    }
    
    public void deleteBuyer(Buyer buyer) {
        this.buyerDao.delete(buyer);
    }
    
    public void updateBuyer(Buyer buyer) {
        this.buyerDao.update(buyer);
    }
    
    public List<Buyer> findAllBuyer() {
        return buyerDao.findAll();
    }
}
