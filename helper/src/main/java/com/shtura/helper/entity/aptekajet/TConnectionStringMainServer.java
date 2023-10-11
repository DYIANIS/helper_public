package com.shtura.helper.entity.aptekajet;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "T_ConnectionStringMainServer")
public class TConnectionStringMainServer {
    
    @Column(name = "TradePoint_name") //@Column(name = "IsAdmin", nullable=false)
    private String tradePointName;

    public String getTradePointName() {
        return tradePointName;
    }

    public void setTradePointName(String tradePointName) {
        this.tradePointName = tradePointName;
    }
    
    @Override
    public String toString() {
        
        return "***************   '" + this.tradePointName + "'   ***************";
    }
}
