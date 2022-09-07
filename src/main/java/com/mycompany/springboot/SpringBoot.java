/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.springboot;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author carlos.orduz
 */
public class SpringBoot {

    public static void main(String[] args) throws ClassNotFoundException {
        String classname = args[0];
        Class c = Class.forName(classname);
        Method[] declareMethods = c.getDeclaredMethods();
        int numServices = 0;
        int failed = 0;
        for (Method m : declareMethods) {
            if (m.isAnnotationPresent(RequestMapping.class)) {
                String path = m.getAnnotation(RequestMapping.class).value();
                Data.apisDictionary.put(path, m);
                numServices++;
            }
        }
        System.out.println("Published Services: " + numServices);
    }
}

