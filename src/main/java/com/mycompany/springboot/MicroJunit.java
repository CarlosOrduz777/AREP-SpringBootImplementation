package com.mycompany.springboot;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author carlos.orduz
 */

public class MicroJunit {
    public static void main(String... args) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        String classname = args[0];
        Class c = Class.forName(classname);
        Method[] declareMethods = c.getDeclaredMethods();
        int passed = 0;
        int failed = 0;
        for (Method m : declareMethods) {
            if(m.isAnnotationPresent(Test.class)){
                try{
                    System.out.println("Invoking "+m.getName()+ "In class: "+c.getName());
                    m.invoke(null);
                    passed =passed+1;
                }catch (Exception e){
                    failed +=1;
                    e.printStackTrace();
                }

            }
        }
        System.out.println("Passed: "+passed+"Failed: "+failed);
    }
}
