/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author NUSNAFIF
 */
@Entity
@Table(name="stock_master")
public class Tickler {
    
    @Id
    @Column(name="id_tickler", nullable = false)
    private String ticklerID;
    
    @Column(name = "nm_tickler", nullable = false)
    private String ticklerName;
    

    public String getTicklerID() {
        return ticklerID;
    }

    public void setTicklerID(String ticklerID) {
        this.ticklerID = ticklerID;
    }

    public String getTicklerName() {
        return ticklerName;
    }

    public void setTicklerName(String ticklerName) {
        this.ticklerName = ticklerName;
    }

//    @Override
//    public String toString() {
//        return "Tickler{" + "ticklerID=" + ticklerID + ", ticklerName=" + ticklerName + '}';
//    }
    
    
    
    
    
}
