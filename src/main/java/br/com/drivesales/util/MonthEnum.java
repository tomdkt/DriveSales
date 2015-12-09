/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drivesales.util;

import br.com.drivesales.exception.MonthNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author thomas
 */
public enum MonthEnum {

    JANEIRO("janeiro"),
    FEVEREIRO("fevereiro"),
    MARCO("marco"),
    ABRIL("abril"),
    MAIO("maio"),
    JUNHO("junho"),
    JULHO("julho"),
    AGOSTO("agosto"),
    SETEMBRO("setembro"),
    OUTUBRO("outubro"),
    NOVEMBRO("novembro"),
    DEZEMBRO("dezembro");

    private String value;

    private static Map<String, MonthEnum> map = new HashMap<String, MonthEnum>();

    static {
        for (MonthEnum e : MonthEnum.values()) {
            map.put(e.value, e);
        }
    }

    public static MonthEnum getValue(String value) {
        MonthEnum m = map.get(value);
        if (m == null) {
            throw new MonthNotFoundException(value);
        }
        return m;
    }

    MonthEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
