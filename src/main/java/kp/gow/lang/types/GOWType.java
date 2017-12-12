/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.gow.lang.types;

/**
 *
 * @author Asus
 */
public abstract class GOWType
{
    private final String name;
    private final Class<?> javaClass;
    
    GOWType(String name, Class<?> javaClass)
    {
        if(name == null)
            throw new NullPointerException();
        if(name.isEmpty())
            throw new IllegalArgumentException("Invalid empty typename");
        if(javaClass == null)
            throw new NullPointerException();
        this.name = name;
        this.javaClass = javaClass;
    }
    
    public final String getName() { return name; }
    public final Class<?> getJavaClass() { return javaClass; }
    
    public abstract boolean isPrimitive();
    public abstract boolean isArray();
    public abstract boolean isClass();
    
    
}
