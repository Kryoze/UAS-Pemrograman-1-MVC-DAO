/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko.util;

import com.toko.controller.BarangController;
import com.toko.model.Barang;
import com.toko.model.KeranjangBelanja;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author Alfian
 */
public class ValidasiTransaksi extends ValidasiForm {
    BarangController barangController = new BarangController();
    
    private final List<Barang> listBarang = barangController.getAllBarang();
    
    private List<KeranjangBelanja> keranjangBelanja = new ArrayList<>();
    
    private Object[][] target;
    private String actionType;
    
    private String barangLabel;
    private Barang barangValue;
    private JComboBox barangComboBox;
    
    private String jumlahLabel;
    private String jumlahValue;
    
    private int stokBarangTersedia;
    
    private int jumlahBarangBeli;

    public Barang getBarangValue() {
        return barangValue;
    }

    public int getStokBarangTersedia() {
        return stokBarangTersedia;
    }

    public int getJumlahBarangBeli() {
        return jumlahBarangBeli;
    }

    public List<KeranjangBelanja> getKeranjangBelanja() {
        return keranjangBelanja;
    }

    private boolean isBarangValid() {
        return isRequiredValid(
            barangLabel, 
            barangComboBox == null ? "" : barangComboBox.getSelectedItem().toString()
        );
    }

    private boolean isJumlahValid() {
        return 
            isRequiredValid(jumlahLabel, jumlahValue)
                ? isNumberValid(jumlahLabel, jumlahValue)
                    ? isNumberMoreThanZero(jumlahLabel, jumlahValue)
                    : false 
                : false;
    }

    private boolean isKeranjangBelanjaEmpty() {
        return keranjangBelanja.size() == 0 ? true : false;
    }

    private boolean isAddItemKeranjangBelanjaSuccess() {
        KeranjangBelanja barangBeli = new KeranjangBelanja();
        
        this.jumlahBarangBeli = Integer.parseInt(jumlahValue);
        this.stokBarangTersedia = barangController.getStokByIdBarang(barangValue.getId());
        
        int indexOfBarangTerbeli = -1;
        
        for(int i = 0; i < keranjangBelanja.size(); i++) {
            if(keranjangBelanja.get(i).getIdBarang().equals(barangValue.getId())) {
                indexOfBarangTerbeli = i;
                break;
            }
        }
        
        if(indexOfBarangTerbeli != -1) {
            stokBarangTersedia -= keranjangBelanja.get(indexOfBarangTerbeli).getJumlahBarang();
        }
        
        if(jumlahBarangBeli > stokBarangTersedia) {
            setDialogTitle("Oops");
            setDialogMessage("Stok " + barangValue.getNama() + " tersisa " + stokBarangTersedia);
            setIsValid(false);
            
            showMessageDialog();
            
            return false;
        }
        
        barangBeli.setIdBarang(barangValue.getId());
        barangBeli.setNamaBarang(barangValue.getNama());
        
        if(indexOfBarangTerbeli != -1) {
            barangBeli.setJumlahBarang(
                jumlahBarangBeli + keranjangBelanja.get(indexOfBarangTerbeli).getJumlahBarang()
            );
            keranjangBelanja.remove(indexOfBarangTerbeli);
        } else {
            barangBeli.setJumlahBarang(jumlahBarangBeli);
        }
        
        keranjangBelanja.add(barangBeli);
        
        return true;
    }

    public boolean isValid(Object[][] target, String actionType) {
        this.target = target;
        this.actionType = actionType;
        
        switch(actionType) {
            case "ADD_ITEM_KERANJANG_BELANJA":
                for(int i = 0; i < Array.getLength(target); i++) {
                    switch((String) target[i][0]) {
                        case "barang":
                            this.barangLabel = (String) target[i][1];
                            this.barangComboBox = (JComboBox) target[i][2];

                            if(isBarangValid()) {
                                continue;
                            } else {
                                showMessageDialog();
                                return false;
                            }
                        case "jumlah":
                            this.jumlahLabel = (String) target[i][1];
                            this.jumlahValue = (String) target[i][2];

                            if(isJumlahValid()) {
                                continue;
                            } else {
                                showMessageDialog();
                                return false;
                            }
                    }
                }
                
                this.barangValue = (Barang) barangComboBox.getSelectedItem();
                
                return isAddItemKeranjangBelanjaSuccess();
            case "CHECKOUT_TRANSAKSI":
                if(isKeranjangBelanjaEmpty()) {
                    setDialogTitle("Oops");
                    setDialogMessage("Daftar Barang Belanjaan masih kosong");
                    setIsValid(false);

                    showMessageDialog();
                    
                    return false;
                }
        }
        
        return true;
    }
}
