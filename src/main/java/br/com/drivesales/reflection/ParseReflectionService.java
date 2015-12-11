/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.drivesales.reflection;

import br.com.drivesales.parsable.Position;
import br.com.drivesales.parsable.types.FilialPeriodoTotal;
import br.com.drivesales.util.HeaderTypes;
import java.lang.reflect.Field;
import java.util.Set;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author thomas
 */
public class ParseReflectionService {
    private final static Logger logger = LoggerFactory.getLogger(ParseReflectionService.class);
    private static final Reflections reflections;
    
    static{
        reflections = new Reflections("br.com.drivesales.parsable.template");
    }
    
    public <T> T getEntity(String [] line, HeaderTypes headerType) throws InstantiationException, IllegalAccessException{
        
        Set<Class<?>> clazzes = reflections.getTypesAnnotatedWith(headerType.getValue());
        
        if(clazzes == null || clazzes.isEmpty()){
            return null;
        }
        Class clazz = clazzes.iterator().next();
        if(clazz == null){
            return null;
        }
        
        Object t = clazz.newInstance();
        
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            Position position = f.getAnnotation(Position.class);
            if (position != null && position.startIn() <= line.length) {
                set(t, f.getName(), line[position.startIn()].trim());
            }
        }
        
        return (T)t;
    }
    
    public static boolean set(Object object, String fieldName, Object fieldValue) {
        Class<?> clazz = object.getClass();
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(object, fieldValue);
                return true;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
                logger.error("NoSuchFieldException: " + fieldName);
            } catch (Exception e) {
                logger.error("IllegalStateException: ", e);
            }
        }
        return false;
    }
}
