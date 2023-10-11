package com.shtura.helper.service.aptekajet;

import com.shtura.helper.dao.aptekajet.OstatkiDao;
import com.shtura.helper.entity.aptekajet.Ostatki;

public class OstatkiService {

    private OstatkiDao ostatkiDao = new OstatkiDao();
    
    public OstatkiService() {
        
    }
    
    public Ostatki findSeller(int id) {
        return ostatkiDao.findById(id);
    }
    
    public void saveSeller(Ostatki ostatki) {
        this.ostatkiDao.save(ostatki);
    }
    
    public void deleteSeller(Ostatki ostatki) {
        this.ostatkiDao.delete(ostatki);
    }
    
    public void updateSeller(Ostatki ostatki) {
        this.ostatkiDao.update(ostatki);
    }
}
