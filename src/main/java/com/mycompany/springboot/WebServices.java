package com.mycompany.springboot;

public class WebServices {

    @RequestMapping("/hello")
    public static String helloWorld(){
        return "Hello World";
    }

    @RequestMapping("/status")
    public static String serverStatus(){
        return "Running";
    }
    @RequestMapping("/html")
    public static String getHtml(){
        return  "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "<title>Title of the document</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>Mi propio mensaje</h1>\n"
                + "</body>\n"
                + "</html>\n";
    }
}
