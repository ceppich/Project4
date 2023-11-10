package com.mycompany.project4_algos;
import java.util.Scanner;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * MainClass for Project 4
 *
 * @author Jacob Bender and Christian Eppich
 * @version 1.0
 * Created 11/9/2023
 * Summary of Modifications:
 *
 * Description: Main class where project4 is written and executed. It uses a 
 *  breadth-first search implemented with a queue and a hashmap to implement
 *  memoization for dynamic programming.
 */
public class Project4_Algos {
    
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);

        //collect number of vehicles
        int numVehicles = inputScanner.nextInt();
        
        //initilize vehicle garage to keep track of cars and position string to map moves
        Vehicle garage[] = new Vehicle[numVehicles];
        String startPositionString = "";
        
        //fill in board based on input
        for (int i = 0; i < numVehicles; i++) {
            //gather the 5 pieces of information
            String type = inputScanner.next();
            String name = inputScanner.next();
            String orientation = inputScanner.next();
            int y = inputScanner.nextInt();
            int x = inputScanner.nextInt();
            
            Vehicle myVehicle;
            
            //only store the coordinate that can change in the position string
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
        
        //create Queue with initial PositionString
        Queue<String> RHQueue = new LinkedList();
        RHQueue.add(startPositionString);
        
        //create hash map and add initial move string with sentinal parent
        HashMap<String, String> prevMoves = new HashMap<>();
        prevMoves.put(startPositionString, "winnerwinnerchickendinnah");
        
        String currentPositionString = "";
        
        //work through the Queue that contains all the possible strings
        outer:
        while (!RHQueue.isEmpty()){
            //get current string to test
            currentPositionString = RHQueue.remove();
            
            //check each car's move
            for (int i = 0; i < garage.length; i++) {
                Vehicle currVehicle = garage[i];
                int dynamicCoord = currentPositionString.charAt(i) - '0';
                
                //check orientation
                if (currVehicle.getOrient() == 'v'){
                    //check up until can't
                    for (int j = dynamicCoord - 1; j > 0; j--){
                        if (!checkMove(garage, currentPositionString, j, currVehicle.getStaticPos())){
                            break;
                        }
                        
                        //create new board reflecting move
                        String newString = currentPositionString.substring(0, i)
                                         + String.valueOf(j)
                                         + currentPositionString.substring(i + 1);
                        
                        //check if the red car, which is the first car, is at the end of the row
                        if (currentPositionString.charAt(0) == '5'){
                            break outer;
                        } 
                        //add new strings to the queue and hashmap
                        else if (!prevMoves.containsKey(newString)) {
                            RHQueue.add(newString);
                            prevMoves.put(newString, currentPositionString);
                        }
                    }
                    //check down until can't
                    for (int j = dynamicCoord + currVehicle.getLength(); j < 7; j++){
                        if (!checkMove(garage, currentPositionString, j, currVehicle.getStaticPos())){
                            break;
                        }
                        
                        //create new board reflecting move
                        String newString = currentPositionString.substring(0, i)
                                         + String.valueOf(j - currVehicle.getLength() + 1)
                                         + currentPositionString.substring(i + 1);
                        
                        //check if the red car, which is the first car, is at the end of the row
                        if (currentPositionString.charAt(0) == '5'){
                            break outer;
                        } 
                        //add new strings to the queue and hashmap
                        else if (!prevMoves.containsKey(newString)) {
                            RHQueue.add(newString);
                            prevMoves.put(newString, currentPositionString);
                        }
                    }
                } else {
                    //check left until can't
                    for (int j = dynamicCoord - 1; j > 0; j--){
                        if (!checkMove(garage, currentPositionString, currVehicle.getStaticPos(), j)){
                            break;
                        }
                        //create new board reflecting move
                        String newString = currentPositionString.substring(0, i)
                                         + String.valueOf(j)
                                         + currentPositionString.substring(i + 1);
                        
                        //check if the red car, which is the first car, is at the end of the row
                        if (currentPositionString.charAt(0) == '5'){
                            break outer;
                        } 
                        //add new strings to the queue and hashmap
                        else if (!prevMoves.containsKey(newString)) {
                            RHQueue.add(newString);
                            prevMoves.put(newString, currentPositionString);
                        }
                    }
                    //check right until can't
                    for (int j = dynamicCoord + currVehicle.getLength(); j < 7; j++){
                        if (!checkMove(garage, currentPositionString, currVehicle.getStaticPos(), j)){
                            break;
                        }
                        //create new board reflecting move
                        String newString = currentPositionString.substring(0,i)
                                         + String.valueOf(j - currVehicle.getLength() + 1)
                                         + currentPositionString.substring(i + 1);
                        
                        //check if the red car, which is the first car, is at the end of the row
                        if (currentPositionString.charAt(0) == '5'){
                            break outer;
                        } 
                        //add new strings to the queue and hashmap
                        else if (!prevMoves.containsKey(newString)) {
                            RHQueue.add(newString);
                            prevMoves.put(newString, currentPositionString);
                        }
                    }
                }
            }
        }   
        //print the results
        printMove(currentPositionString, prevMoves, garage, 0);
    }
    
    //check if potential move is valid or not based only on if other cars are occupying the spot
    private static boolean checkMove(Vehicle[] garage, String currentPositionString, int y, int x){
        //iterate through the list of cars to determine if any overlap with possible move
        for (int i = 0; i < garage.length; i++){
            Vehicle curr = garage[i];
            
            //determine orientation to properly disect move
            if (curr.getOrient() == 'h'){
                if (y == curr.getStaticPos()){
                    int dymPos = currentPositionString.charAt(i) - '0';
                    
                    //check for overlap
                    if (dymPos <= x && x < dymPos + curr.getLength()){
                        return false;
                    }
                }
            } 
            else {
                if (x == curr.getStaticPos()){
                    int dymPos = currentPositionString.charAt(i) - '0';
                    
                    //check for overlap
                    if (dymPos <= y && y < dymPos + curr.getLength()){
                        return false;
                    }
                }
            }
        }
        
        //no collisions
        return true;
    }
    
    
    public static void printMove(String curr, HashMap<String, String> prevMoves, Vehicle []garage, int count){
        //get previous move
        String parent = prevMoves.get(curr);
        
        //base case for recursion
        if (parent.equals("winnerwinnerchickendinnah")){
            if (count == 1) {
                System.out.println(count + " move:");
            }
            else {
                System.out.println(count + " moves:");
            }
            return;
        }
        
        //recursive call before printing
        printMove(parent, prevMoves, garage, count + 1);
        
        //iterate through both current and parent strings to determine what has changed
        for (int i = 0; i < garage.length; i++){
            //find character that has changed
            if (parent.charAt(i) != (curr.charAt(i))){
                //construct string for printing move
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