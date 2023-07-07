/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko.controller;

import com.toko.dao.ImplementTransaksi;
import com.toko.dao.TransaksiDao;
import java.util.List;
import com.toko.model.Transaksi;

/**
 *
 * @author Alfian
 */
public class TransaksiController {
    private ImplementTransaksi implementTransaksi;
    
    public TransaksiController() {
        implementTransaksi = new TransaksiDao();
    }

    public List<Transaksi> getAllTransaksi() {
        return implementTransaksi.getAll();
    }

    public long getTotalHargaByIdTransaksi(String idTransaksi) {
       return implementTransaksi.getTotalHargaById(idTransaksi);
   }

   public int getTotalBarangByIdTransaksi(String idTransaksi) {
       return implementTransaksi.getTotalBarangById(idTransaksi);
   }

    public boolean insertTransaksi(Transaksi transaksi) {
        return implementTransaksi.insert(transaksi);
    }

    public boolean updateTransaksi(Transaksi transaksi) {
        return implementTransaksi.update(transaksi);
    }

    public boolean deleteTransaksiByIdTransaksi(String idTransaksi) {
        return implementTransaksi.delete(idTransaksi);
    }
}
