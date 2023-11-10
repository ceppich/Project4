package com.mycompany.project4_algos;

/**
 * Vehicle class for Project 4
 *
 * @author Jacob Bender and Christian Eppich
 * @version 1.0
 * Created 11/9/2023
 * Summary of Modifications:
 *
 * Description: Data object that holds static information for each vehicle on 
 *  the board.
 *
 */
public class Vehicle {

    private String name;
    private char orientation;
    private int length;
    private int staticPos;
    
    
    //Constructor
    public Vehicle(String type, String color, String orient, int position){
        //If it's a car, length is 2, a truck has length of 3
        if (type.charAt(0) == 'c'){
            length = 2;
        } else {
            length = 3;
        }
        name = color;
        orientation = orient.charAt(0);
        staticPos = position;
    }
    
    //Getter for orientation
    public char getOrient(){
        return orientation;
    }
    
    //Getter for length
    public int getLength(){
        return length;
    }
    
    //Getter for the static part of the coordinate (either row or column)
    public int getStaticPos(){
        return staticPos;
    }
    
    //Getter for the name
    public String getName(){
        return name;
    }
}