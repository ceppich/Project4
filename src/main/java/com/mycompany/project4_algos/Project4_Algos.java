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

        int numVehicles = inputScanner.nextInt();
        Vehicle garage[] = new Vehicle[numVehicles];
        String startPositionString = "";
        
        //fill in board based on input
        for (int i = 0; i < numVehicles; i++) {
            String type = inputScanner.next();
            String name = inputScanner.next();
            String orientation = inputScanner.next();
            int y = inputScanner.nextInt();
            int x = inputScanner.nextInt();
            
            Vehicle myVehicle;
            
            if (orientation.equals("h")) {
                myVehicle = new Vehicle(type, name, orientation, y);
                startPositionString += Integer.toString(x);
            }
            else {
                myVehicle = new Vehicle(type, name, orientation, x);
                startPositionString += Integer.toString(y);
            }
            garage[i] = myVehicle;
        }
        
        //create Queue
        Queue<String> RHQueue = new LinkedList();
        //add initial PositionString
        RHQueue.add(startPositionString);
        
        
        HashMap<String, String> prevMoves = new HashMap<>();
        prevMoves.put(startPositionString, "winnerwinnerchickendinnah");
        
        String currentPositionString = "";
        outer:
        while (!RHQueue.isEmpty()){
            //remove current board to test
            currentPositionString = RHQueue.remove();
            
            //check each car's move
            for (int i = 0; i < garage.length; i++) {
                Vehicle currVehicle = garage[i];
                
                int dynamicCoord = currentPositionString.charAt(i) - '0';
                //check orientati0n
                if (currVehicle.getOrient()=='v'){
                    //check up until can't
                    for (int j = dynamicCoord-1; j > 0; j--){
                        if (!checkMove(garage, currentPositionString, j, currVehicle.getStaticPos())){
                            break;
                        }
                        //create new board reflecting move
                        String newString = currentPositionString.substring(0,i)+String.valueOf(j)+currentPositionString.substring(i);
                        
                        //call finish function
                        if (currentPositionString.charAt(0) == '5'){
                            break outer;
                        } else if (!prevMoves.containsKey(newString)) {
                            RHQueue.add(newString);
                            prevMoves.put(newString, currentPositionString);
                        }
                    }
                    //check down until can't
                    for (int j = dynamicCoord+currVehicle.getLength(); j < 7; j++){
                        if (!checkMove(garage, currentPositionString, j, currVehicle.getStaticPos())){
                            break;
                        }
                        //create new board reflecting move
                        String newString = currentPositionString.substring(0,i)+String.valueOf(j-currVehicle.getLength()+1)+currentPositionString.substring(i);
                        
                        //call finish function
                        if (currentPositionString.charAt(0) == '5'){
                            break outer;
                        } else if (!prevMoves.containsKey(newString)) {
                            RHQueue.add(newString);
                            prevMoves.put(newString, currentPositionString);
                        }
                    }
                } else {
                    //check left until can't
                    for (int j = dynamicCoord-1; j > 0; j--){
                        if (!checkMove(garage, currentPositionString, currVehicle.getStaticPos(), j)){
                            break;
                        }
                        //create new board reflecting move
                        String newString = currentPositionString.substring(0,i)+String.valueOf(j)+currentPositionString.substring(i);
                        
                        //call finish function
                        if (currentPositionString.charAt(0) == '5'){
                            break outer;
                        } else if (!prevMoves.containsKey(newString)) {
                            RHQueue.add(newString);
                            prevMoves.put(newString, currentPositionString);
                        }
                    }
                    //check right until can't
                    for (int j = dynamicCoord+currVehicle.getLength(); j < 7; j++){
                        if (!checkMove(garage, currentPositionString, currVehicle.getStaticPos(), j)){
                            break;
                        }
                        //create new board reflecting move
                        String newString = currentPositionString.substring(0,i)+String.valueOf(j-currVehicle.getLength()+1)+currentPositionString.substring(i);
                        
                        //call finish function
                        if (currentPositionString.charAt(0) == '5'){
                            break outer;
                        } else if (!prevMoves.containsKey(newString)) {
                            RHQueue.add(newString);
                            prevMoves.put(newString, currentPositionString);
                        }
                    }
                }
            }
        }   
        printMove(currentPositionString, prevMoves, garage, 0);
    }
    
    private static boolean checkMove(Vehicle[] garage, String currentPositionString, int y, int x){
        for (int i = 0; i < garage.length; i++){
            Vehicle curr = garage[i];
            if (curr.getOrient() == 'h'){
                if (y == curr.getStaticPos()){
                    int dymPos = currentPositionString.charAt(i)-'0';
                    if (dymPos <= x && x < dymPos+curr.getLength()){
                        return false;
                    }
                }
            } else {
                if (x == curr.getStaticPos()){
                    int dymPos = currentPositionString.charAt(i)-'0';
                    if (dymPos <= y && y < dymPos+curr.getLength()){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public static void printMove(String curr, HashMap<String, String> prevMoves, Vehicle []garage, int count){
        //get previous move
        String parent = prevMoves.get(curr);
        if (parent.equals("winnerwinnerchickendinnah")){
            System.out.println(count + " moves:");
            return;
        }
        
        printMove(parent, prevMoves, garage, count + 1);

        for (int i = 0; i < garage.length; i++){
            if (parent.charAt(i) != (curr.charAt(i))){
                String outString = garage[i].getName() + " ";
                if (parent.charAt(i) > curr.charAt(i)) {
                    outString += String.valueOf(parent.charAt(i) - curr.charAt(i)) + " ";
                    if (garage[i].getOrient() == 'h') {
                        outString += "L";
                    }
                    else {
                        outString += "U";
                    }
                }
                else {
                    outString += String.valueOf(curr.charAt(i) - parent.charAt(i)) + " ";
                    if (garage[i].getOrient() == 'h') {
                        outString += "R";
                    }
                    else {
                        outString += "D";
                    }
                }
                System.out.println(outString);
                break;
            }
        }        
    }
}