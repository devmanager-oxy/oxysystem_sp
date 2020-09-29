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
public class RptAnggotaPinjamBankAnuitas extends Entity{

    private String peminjam;
    private String nik;
    private String bank;
    private Date tanggal;
    private double totalPinjaman;
    private double biayaAdministrasi;
    private double biayaProvisi;
    private double biayaAsuransi;
    private int jenisPinjaman;
    private String keterangan;
    private String noRekening;
    private double bungaPinjamanKoperasi;
    private double bungaPinjamanBank;
    private double besarAngsuran;
    private double angsuranTerakhir;
    private int lamaAngsuran;
    private int jatuhTempo;

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public double getBesarAngsuran() {
        return besarAngsuran;
    }

    public void setBesarAngsuran(double besarAngsuran) {
        this.besarAngsuran = besarAngsuran;
    }

    public double getAngsuranTerakhir() {
        return angsuranTerakhir;
    }

    public void setAngsuranTerakhir(double angsuranTerakhir) {
        this.angsuranTerakhir = angsuranTerakhir;
    }
    
    public double getBiayaAdministrasi() {
        return biayaAdministrasi;
    }

    public void setBiayaAdministrasi(double biayaAdministrasi) {
        this.biayaAdministrasi = biayaAdministrasi;
    }

    public double getBiayaAsuransi() {
        return biayaAsuransi;
    }

    public void setBiayaAsuransi(double biayaAsuransi) {
        this.biayaAsuransi = biayaAsuransi;
    }

    public double getBiayaProvisi() {
        return biayaProvisi;
    }

    public void setBiayaProvisi(double biayaProvisi) {
        this.biayaProvisi = biayaProvisi;
    }

    public double getBungaPinjamanBank() {
        return bungaPinjamanBank;
    }

    public void setBungaPinjamanBank(double bungaPinjamanBank) {
        this.bungaPinjamanBank = bungaPinjamanBank;
    }

    public double getBungaPinjamanKoperasi() {
        return bungaPinjamanKoperasi;
    }

    public void setBungaPinjamanKoperasi(double bungaPinjamanKoperasi) {
        this.bungaPinjamanKoperasi = bungaPinjamanKoperasi;
    }

    public int getJatuhTempo() {
        return jatuhTempo;
    }

    public void setJatuhTempo(int jatuhTempo) {
        this.jatuhTempo = jatuhTempo;
    }

    public int getJenisPinjaman() {
        return jenisPinjaman;
    }

    public void setJenisPinjaman(int jenisPinjaman) {
        this.jenisPinjaman = jenisPinjaman;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public int getLamaAngsuran() {
        return lamaAngsuran;
    }

    public void setLamaAngsuran(int lamaAngsuran) {
        this.lamaAngsuran = lamaAngsuran;
    }

    public String getNoRekening() {
        return noRekening;
    }

    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
    }

    public String getPeminjam() {
        return peminjam;
    }

    public void setPeminjam(String peminjam) {
        this.peminjam = peminjam;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }
    
    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public double getTotalPinjaman() {
        return totalPinjaman;
    }

    public void setTotalPinjaman(double totalPinjaman) {
        this.totalPinjaman = totalPinjaman;
    }

}
