/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajar.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author NUSNAFIF
 */
@Entity
@Table(name = "stock_trx")
public class TrxStock {
    
    @Id
    @Column(name = "id_tickler", nullable = false)
    private String ticklerID;
    
    @Column(name = "dt_trx", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date ticklerDT;
    
    @Column(name = "open_prc")
    private Float ticklerOpen;
    
    @Column(name = "high_prc")
    private Float ticklerHigh;
    
    @Column(name = "low_prc")
    private Float ticklerLow;        
    
    @Column(name = "close_prc")
    private Float ticklerClose;    
                
    @Column(name = "vol_trx")
    private Float ticklerVol;
    
    @Column(name = "openint")
    private Float ticklerInt;

    public String getTicklerID() {
        return ticklerID;
    }

    public void setTicklerID(String ticklerID) {
        this.ticklerID = ticklerID;
    }

    public Date getTicklerDT() {
        return ticklerDT;
    }

    public void setTicklerDT(Date ticklerDT) {
        this.ticklerDT = ticklerDT;
    }

    public Float getTicklerClose() {
        return ticklerClose;
    }

    public void setTicklerClose(Float ticklerClose) {
        this.ticklerClose = ticklerClose;
    }

    public Float getTicklerHigh() {
        return ticklerHigh;
    }

    public void setTicklerHigh(Float ticklerHigh) {
        this.ticklerHigh = ticklerHigh;
    }

    public Float getTicklerLow() {
        return ticklerLow;
    }

    public void setTicklerLow(Float ticklerLow) {
        this.ticklerLow = ticklerLow;
    }

    public Float getTicklerOpen() {
        return ticklerOpen;
    }

    public void setTicklerOpen(Float ticklerOpen) {
        this.ticklerOpen = ticklerOpen;
    }

    public Float getTicklerVol() {
        return ticklerVol;
    }

    public void setTicklerVol(Float ticklerVol) {
        this.ticklerVol = ticklerVol;
    }

    public Float getTicklerInt() {
        return ticklerInt;
    }

    public void setTicklerInt(Float ticklerInt) {
        this.ticklerInt = ticklerInt;
    }

    @Override
    public String toString() {
        return "TrxStock{" + "ticklerID=" + ticklerID + ", ticklerDT=" + ticklerDT + ", ticklerOpen=" + ticklerOpen + ", ticklerHigh=" + ticklerHigh + ", ticklerLow=" + ticklerLow + ", ticklerClose=" + ticklerClose + ", ticklerVol=" + ticklerVol + ", ticklerInt=" + ticklerInt + '}';
    }

    
}
