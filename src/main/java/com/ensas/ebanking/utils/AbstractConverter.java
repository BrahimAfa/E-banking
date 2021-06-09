/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensas.ebanking.utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AMINE
 */
public abstract class AbstractConverter<T, VO> {

    public abstract T toItem(VO vo);

    public abstract VO toVo(T item);

    public List<T> toItem(List<VO> vos) {
        if (vos == null || vos.isEmpty()) {
            return new ArrayList<>();
        } else {
            List<T> items = new ArrayList();
            vos.forEach((vo) -> {
                items.add(toItem(vo));
            });
            return items;

        }
    }

    public List<VO> toVo(List<T> items) {
        if (items == null || items.isEmpty()) {
            return new ArrayList<>();
        } else {
            List<VO> vos = new ArrayList();
            items.forEach((item) -> {
                vos.add(toVo(item));
            });
            return vos;
        }
    }

}
