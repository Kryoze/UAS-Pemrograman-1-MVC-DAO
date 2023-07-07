/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko.dao;

import com.toko.model.Barang;
import java.util.List;

/**
 *
 * @author Alfian
 */
public interface ImplementBarang {
    public boolean insert(Barang barang);
    public boolean delete(String idBarang);
    public boolean update(Barang barang);
    public List<Barang> getAll();
    public String getMaxId();
    public int getStokById(String idBarang);
}
