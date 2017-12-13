/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.gow.parser;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.function.Function;

/**
 *
 * @author Asus
 */
final class Utils
{
    public static final <E, C extends Code> Map<String, E> catchFields(Class<C> clazz, Map<String, E> hash, CatcherAction<E> action)
    {
        for(Field field : clazz.getDeclaredFields())
        {
            if(field.getType() != clazz || !Modifier.isStatic(field.getModifiers()))
                continue;
            try { action.action(hash, field); }
            catch(IllegalAccessException | IllegalArgumentException ex)
            {
                ex.printStackTrace(System.err);
            }
        }
        return hash;
    }
    public static final <E extends Code> Map<String, E> catchFields(Class<E> clazz, Map<String, E> hash, Function<E, String> nameGetter)
    {
        return catchFields(clazz, hash, (map, field) -> {
            E element = (E) field.get(null);
            map.put(nameGetter.apply(element), element);
        });
    }
    
    @FunctionalInterface
    interface CatcherAction<E> { void action(Map<String, E> map, Field field) throws IllegalAccessException, IllegalArgumentException; }
}
