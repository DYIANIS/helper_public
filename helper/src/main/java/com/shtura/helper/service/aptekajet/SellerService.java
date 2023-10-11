package com.shtura.helper.service.aptekajet;

import java.util.List;

import com.shtura.helper.dao.aptekajet.SellerDao;
import com.shtura.helper.entity.aptekajet.Seller;

public class SellerService {

    private SellerDao sellerDao = new SellerDao();
    
    public SellerService() {
        
    }
    
    public Seller findSeller(int id) {
        return sellerDao.findById(id);
    }
    
    public List<Seller> findByName(String name) {
        return sellerDao.findByName(name);
    }
    
    public void saveSeller(Seller seller) {
        this.sellerDao.save(seller);
    }
    
    public void deleteSeller(Seller seller) {
        this.sellerDao.delete(seller);
    }
    
    public void updateSeller(Seller seller) {
        this.sellerDao.update(seller);
    }
    
    public List<Seller> findAllSeller() {
        return sellerDao.findAll();
    }
}
