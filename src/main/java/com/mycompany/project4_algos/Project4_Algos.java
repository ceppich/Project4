/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.project4_algos;
import java.util.Scanner;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;


/**
 *
 * @author 13016
 * 
8
car
red
h
3
2
car
lime
h
1
1
truck
purple
v
2
1
car
orange
v
5
1
truck
blue
v
2
4
truck
yellow
v
1
6
car
lightblue
h
5
5
truck
aqua
h
6
3
 */
public class Project4_Algos {

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        char[][] gameBoard = new char[7][7];

        HashMap<Character, Vehicle> garage = new HashMap<>();
        int numVehicles = inputScanner.nextInt();
        
        
        //fill in board based on input
        for (int i = 0; i < numVehicles; i++) {
            String type = inputScanner.next();
            String name = inputScanner.next();
            String orientation = inputScanner.next();
            int y = inputScanner.nextInt();
            int x = inputScanner.nextInt();
            Vehicle myVehicle = new Vehicle(type, name, orientation, y, x);
            char myChar = (char)('A' + i);
            garage.put(myChar, myVehicle);
            
            if (type.equals("car")) {
                if (orientation.equals("h")) {
                    gameBoard[y][x] = myChar;
                    gameBoard[y][x + 1] = myChar;
                }
                else {
                    gameBoard[y][x] = myChar;
                    gameBoard[y + 1][x] = myChar;
                }
            }
            else {
                if (orientation.equals("h")) {
                    gameBoard[y][x] = myChar;
                    gameBoard[y][x + 1] = myChar;
                    gameBoard[y][x + 2] = myChar;
                }
                else {
                    gameBoard[y][x] = myChar;
                    gameBoard[y + 1][x] = myChar;
                    gameBoard[y + 2][x] = myChar;
                }
            }
        }
        
        //create Queue
        Queue<char[][]> RHQueue = new LinkedList();
        //add initial board
        RHQueue.add(gameBoard);
        HashMap<String, String> prevMoves = new HashMap<>();
        
        
        while (!RHQueue.isEmpty()){
            //remove current board to test
            char[][] currentBoard = RHQueue.remove();
            
            //get list of cars
            HashMap<Character, Vehicle> currGarage = arrayToCars(currentBoard);
            //for each car
            for (int i = 0; i < numVehicles; i++) {
                char currChar = (char) ('A' + i);
                Vehicle currVehicle = currGarage.get(currChar);
                //check orientation
                if (currVehicle.getOrient()=='v'){
                    //check up until can't
                    for (int j = currVehicle.getY()-1; j > 0; j--){
                        //check if Valid move
                        //create new board reflecting move
                        //callfinish func
                    }
                    //check down until can't
                    for (int j = currVehicle.getY()+currVehicle.getLength(); j < 7; j++){
                        //check if Valid move
                        //create new board reflecting move
                        //callfinish func
                    }
                } else {
                    //check left until can't
                    for (int j = currVehicle.getX()-1; j > 0; j--){
                        //check if Valid move
                        //create new board reflecting move
                        //callfinish func
                    }
                    //check right until can't
                    for (int j = currVehicle.getX()+currVehicle.getLength(); j < 7; j++){
                        //check if Valid move
                        //create new board reflecting move
                        //callfinish func
                    }
                }
                
                //funish func
                    //make new array with each move
                    //if win, break
                    //if in hashmap, ignore
                    //if not in hashmap, store (with parent) and enqueue
                
            }
                
                
                
                
        }      
        //if win, figure out path
            //print path
        
        
        //if not win, idk
        
    }
    
    
    //parse board into car array or map
    private static HashMap<Character, Vehicle> arrayToCars(char[][] gameBoard) {
        HashMap<Character, Vehicle> garage = new HashMap<>();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                char curr = gameBoard[i][j];
                if (curr != '\0') {
                    if (!garage.containsKey(curr)) {
                        if (i < 6 && gameBoard[i + 1][j] == curr) {
                            if (i < 5 && gameBoard[i + 2][j] == curr) {
                                garage.put(curr, new Vehicle("truck", "", "v", i, j));
                            }
                            else {
                                garage.put(curr, new Vehicle("car", "", "v", i, j));
                            }
                            
                        }
                        else if (j < 6 && gameBoard[i][j + 1] == curr) {
                            if (j < 5 && gameBoard[i][j + 2] == curr) {
                                garage.put(curr, new Vehicle("truck", "", "h", i, j));
                            }
                            else {
                                garage.put(curr, new Vehicle("car", "", "h", i, j));
                            }
                        }
                        else {
                            System.out.println("Something wrong in arrayToCars");
                        }
                    }
                }
            }
        }
        
        return garage;
    }
    
    
    //array to string
    
    
    //string to array
    
    
    //check if move is valid
    
    
}
