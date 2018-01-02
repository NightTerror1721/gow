/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.gow;

import java.util.Arrays;
import kp.gow.parser.UnparsedCodeList;

/**
 *
 * @author Asus
 */
public class Main
{
    public static void main(String[] args)
    {
        final Object log = new UnparsedCodeList();
        Test r = (v0, v1) -> {
            String g = log.toString() + v0;
            System.out.println(Arrays.asList(args));
            return 0;
        };
        r.apply(5, log);
    }
    
    public interface Test
    {
        int apply(int value0, Object value1);
    }
}
