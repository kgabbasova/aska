package com.aska.controllers;


import com.aska.services.DateFormatter;

import java.text.ParseException;
import java.util.Calendar;

public class Test {


    public static void main(String[] args) throws ParseException {
        DateFormatter dateFormatter = new DateFormatter();
        Calendar calendar = dateFormatter.toDate("11-12-1995");
        System.out.println(dateFormatter.toString(calendar));
    }

}