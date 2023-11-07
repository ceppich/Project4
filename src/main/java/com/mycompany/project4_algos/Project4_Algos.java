/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.project4_algos;
import java.util.Scanner;
import java.util.HashMap;
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
        int i = 0;
    }
}
