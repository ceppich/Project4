/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project4_algos;

/**
 *
 * @author 13016
 */
public class Vehicle {

    public Vehicle(String type, String color, String orient, int yy, int xx){
        if (type.charAt(0) == 'c'){
            length = 2;
        } else {
            length = 3;
        }
        name = color;
        orientation = orient.charAt(0);
        y = yy;
        x = xx;
    }
    private String name;
    private char orientation;
    private int length;
    
    private int y;
    private int x;
}
