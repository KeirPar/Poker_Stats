package com.poker.poker_stats.Hand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class HandService {

    private HandRepository handRepository;
    private final double big = 999999;

    @Autowired
    public HandService(HandRepository handRepository) {
        this.handRepository = handRepository;
    }
    public List<Hand> findAll(){
        List<Hand> thing =  new ArrayList<>(handRepository.findAll()); //everything
        List<Hand> otherThing = new ArrayList<Hand>();
        for(int i = 1;i<1000;i++){
            otherThing.add(thing.get(i));
        }
        return otherThing;
    }

    //user enters what hand they want to check the odds for getting
    public String oddsOfHand(String hand){
        double ans = 0;
        List<Hand> hands = handRepository.findAll();
        for (Hand value : hands) {
            if (value.getResult3().trim().equalsIgnoreCase(hand.trim())) {
                ans++;
            }
        }
        ans =  (ans/hands.size()) * 100; //should be percent chance of getting specified thing
        DecimalFormat df = new DecimalFormat("#.##");
        ans = Double.parseDouble(df.format(ans)); //FOR ROUNDING
        return "Odds of finishing with " + hand.toLowerCase() + ": "+ans+"%";
    }


    public String commonHand(){
        List<Hand> hands = handRepository.findAll();
        HashMap<String, Integer> counts = new HashMap<>();

        String mostCommonHand = "";
        int highestCount = 0;

        for (Hand hand : hands) {
            int count = counts.compute(hand.getHand(), (k, v) -> v == null ? 1 : v + 1);
            if (count > highestCount) {
                highestCount = count;
                mostCommonHand = hand.getHand();
            }
        }
        mostCommonHand = mostCommonHand + "   Count:  " + highestCount+"/"+(hands.size()-1);
        return mostCommonHand;
    }

    //should find all hands with specified hand;
    public List<Hand> getSpecificHands(String handName){
            return handRepository.findCustomByHand(handName);

    }

    //WILL BE FOR RETURNING ODDS OF GETTING *BLANK* FROM ALL STREETS
    public String findByResult(String result) {
        StringBuilder done = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#.####"); // Format to 2 decimal places

        // Should return the amount of *blank* on flop
        List<Hand> stuff = handRepository.findByResult1(result);
        List<Hand> stuff2 = handRepository.findByResult2(result);
        List<Hand> stuff3 = handRepository.findByResult3(result);

        double flopPercentage = ((double) stuff.size() / big) * 100;
        double turnPercentage = ((double) stuff2.size() / big) * 100;
        double riverPercentage = ((double) stuff3.size() / big) * 100;

        done.append("FLOP: ").append(df.format(flopPercentage))
                .append(" TURN: ").append(df.format(turnPercentage))
                .append(" RIVER: ").append(df.format(riverPercentage));

        return done.toString();
    }


    public String findBySuited(){
        StringBuilder done = new StringBuilder();

        List<Hand> hands = handRepository.findBySuited();   //grabs all hands that are suited
        DecimalFormat df = new DecimalFormat("#.####"); // Format to 2 decimal places

        double count1 = 0;  //resutl1
        double count2 = 0;  //result2
        double count3 = 0;  //result3

       for(int i = 0;i<hands.size();i++){

           if(hands.get(i).getResult1().equals("FLUSH") ||hands.get(i).getResult1().equals("STRAIGHT FLUSH"))
               count1++;
           if(hands.get(i).getResult2().equals("FLUSH") ||hands.get(i).getResult2().equals("STRAIGHT FLUSH"))
               count2++;
           if(hands.get(i).getResult3().equals("FLUSH") ||hands.get(i).getResult3().equals("STRAIGHT FLUSH"))
               count3++;
       }
        double flopPercentage = ((double) count1 / big) * 100;
        double turnPercentage = ((double) count2 / big) * 100;
        double riverPercentage = ((double) count3 / big) * 100;

        done.append("FLOP: ").append(df.format(flopPercentage))
                .append(" TURN: ").append(df.format(turnPercentage))
                .append(" RIVER: ").append(df.format(riverPercentage));

        return done.toString();

    }


    public String findByPair(){

        //Grabs all rows where we have a pocket pair
        List<Hand> pairs = handRepository.findByPair();
        String trips = "THREE OF A KIND";
        String quads = "FOUR OF A KIND";

        DecimalFormat df = new DecimalFormat("#.####");

        int count1Trips = 0;
        int count2Trips = 0;
        int count3Trips = 0;

        int count1Quads = 0;
        int count2Quads = 0;
        int count3Quads = 0;

        for(int i = 0;i<pairs.size();i++){
            //CHECK IF FLOP,TURN,RIVER IS EQUAL TO TRIPS
            if(pairs.get(i).getResult1().equals(trips))
                count1Trips++;
            if(pairs.get(i).getResult2().equals(trips))
                count2Trips++;
            if(pairs.get(i).getResult3().equals(trips))
                count3Trips++;

            //CHECK IF FLOP,TURN,RIVER IS EQUAL TO QUADS
            if(pairs.get(i).getResult1().equals(quads)){
                count1Trips++;
                count1Quads++;
            }
            if(pairs.get(i).getResult1().equals(quads))
                count1Trips++;
            if(pairs.get(i).getResult1().equals(quads))
                count1Trips++;

        }

     return "";



    }



}