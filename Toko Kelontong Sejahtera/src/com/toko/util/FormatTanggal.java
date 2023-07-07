/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toko.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Alfian
 */
public class FormatTanggal {
    private Date tanggal;
    private String format;

    public FormatTanggal(Date tanggal, String format) {
        this.tanggal = tanggal;
        this.format = format;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(tanggal);
    }
}
