package com.mycompany.project4_algos;

/**
 * Vehicle class for Project 4
 *
 * @author Jacob Bender and Christian Eppich
 * @version 1.0
 * Created 11/9/2023
 * Summary of Modifications:
 *
 * Description: data object that holds static information for each vehicle on board.
 * 
 *
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
