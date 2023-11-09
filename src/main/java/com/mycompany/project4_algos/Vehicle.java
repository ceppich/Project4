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

    public Vehicle(String type, String color, String orient, int position){
        if (type.charAt(0) == 'c'){
            length = 2;
        } else {
            length = 3;
        }
        name = color;
        orientation = orient.charAt(0);
        staticPos = position;
    }
    private String name;
    private char orientation;
    private int length;
    
    private int staticPos;
    
    public char getOrient(){
        return orientation;
    }
    
    public int getLength(){
        return length;
    }
    
    public int getStaticPos(){
        return staticPos;
    }
    
    
    public String getName(){
        return name;
    }
    
    
}
