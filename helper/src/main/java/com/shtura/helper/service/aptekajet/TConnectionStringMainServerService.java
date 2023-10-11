package com.shtura.helper.service.aptekajet;

import com.shtura.helper.dao.aptekajet.TConnectionStringMainServerDao;

public class TConnectionStringMainServerService {

    private TConnectionStringMainServerDao tConnectionStringMainServerDao = new TConnectionStringMainServerDao();
    
    public TConnectionStringMainServerService() {
        
    }
    
    public String getTradePointName() {
        return tConnectionStringMainServerDao.getTradePointName();
    }
}
