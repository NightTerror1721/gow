/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.gow;

import java.util.Arrays;

/**
 *
 * @author Asus
 */
public class Main
{
    public static void main(String[] args)
    {
        Runnable r = () -> {
            System.out.println(Arrays.asList(args));
        };
        r.run();
    }
}
