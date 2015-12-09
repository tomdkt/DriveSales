/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drivesales.util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author thomas
 */
public enum DelimitersEnum {
    TAB("\\t"),
    PIPE("|"),
    JOKER("_");
    
    private String value;
    
    private static Map<String, DelimitersEnum> map = new HashMap<String, DelimitersEnum>();

    static {
        for (DelimitersEnum e : DelimitersEnum.values()) {
            map.put(e.value, e);
        }
    }
    
    public static DelimitersEnum getValue(String value) {
        DelimitersEnum c = map.get(value);
        if(c == null){
            return DelimitersEnum.TAB;
        }
        return c;
    }

    DelimitersEnum(String value) {
        this.value = value;
    }
    
    public String getValue(){
        return this.value;
    }
    
}
