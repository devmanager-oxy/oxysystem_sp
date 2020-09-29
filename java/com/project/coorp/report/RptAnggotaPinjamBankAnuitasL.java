/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.project.coorp.report;

import com.project.main.entity.Entity;
import java.util.Date;

/**
 *
 * @author USER
 */
public class RptAnggotaPinjamBankAnuitasL extends Entity{
   
    private int angsuran;

    //Koperasi
    private double angPokokKop;
    private double bungaKop;
    private double totalAngsuranKop;
    private double saldoKop;

    //Bank
    private double angPokokBank;
    private double bungaBank;
    private double totalAngsuranBank;
    private double saldoBank;

    //St
    private Date tglJatuhTempo;
    private String tglBayar;
    private double bayarPinjaman;

    public int getAngsuran() {
        return angsuran;
    }

    public void setAngsuran(int angsuran) {
        this.angsuran = angsuran;
    }

    public double getAngPokokBank() {
        return angPokokBank;
    }

    public void setAngPokokBank(double angPokokBank) {
        this.angPokokBank = angPokokBank;
    }

    public double getAngPokokKop() {
        return angPokokKop;
    }

    public void setAngPokokKop(double angPokokKop) {
        this.angPokokKop = angPokokKop;
    }

    public double getBayarPinjaman() {
        return bayarPinjaman;
    }

    public void setBayarPinjaman(double bayarPinjaman) {
        this.bayarPinjaman = bayarPinjaman;
    }

    public double getBungaBank() {
        return bungaBank;
    }

    public void setBungaBank(double bungaBank) {
        this.bungaBank = bungaBank;
    }

    public double getBungaKop() {
        return bungaKop;
    }

    public void setBungaKop(double bungaKop) {
        this.bungaKop = bungaKop;
    }

    public double getSaldoBank() {
        return saldoBank;
    }

    public void setSaldoBank(double saldoBank) {
        this.saldoBank = saldoBank;
    }

    public double getSaldoKop() {
        return saldoKop;
    }

    public void setSaldoKop(double saldoKop) {
        this.saldoKop = saldoKop;
    }

    public double getTotalAngsuranBank() {
        return totalAngsuranBank;
    }

    public void setTotalAngsuranBank(double totalAngsuranBank) {
        this.totalAngsuranBank = totalAngsuranBank;
    }

    public double getTotalAngsuranKop() {
        return totalAngsuranKop;
    }

    public void setTotalAngsuranKop(double totalAngsuranKop) {
        this.totalAngsuranKop = totalAngsuranKop;
    }

    public String getTglBayar() {
        return tglBayar;
    }

    public void setTglBayar(String tglBayar) {
        this.tglBayar = tglBayar;
    }

    public Date getTglJatuhTempo() {
        return tglJatuhTempo;
    }

    public void setTglJatuhTempo(Date tglJatuhTempo) {
        this.tglJatuhTempo = tglJatuhTempo;
    }

}
