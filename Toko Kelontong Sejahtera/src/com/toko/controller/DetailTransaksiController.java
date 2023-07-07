/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko.controller;
import com.toko.dao.DetailTransaksiDao;
import com.toko.dao.ImplementDetailTransaksi;
import java.util.List;
import com.toko.model.DetailTransaksi;

/**
 *
 * @author Alfian
 */
public class DetailTransaksiController {
    private ImplementDetailTransaksi implementDetailTransaksi;
    
    public DetailTransaksiController() {
        implementDetailTransaksi = new DetailTransaksiDao();
    }

    public List<DetailTransaksi> getAllDetailTransaksi() {
       return implementDetailTransaksi.getAll();
   }

   public boolean insertDetailTransaksi(DetailTransaksi detailTransaksi) {
       return implementDetailTransaksi.insert(detailTransaksi);
   }
}
