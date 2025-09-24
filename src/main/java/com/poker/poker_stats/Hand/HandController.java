package com.poker.poker_stats.Hand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000") //allows the frontend to access info from backend
@RestController
@RequestMapping(path = "api/v1/hand")
public class HandController {

    private final HandService handService;

    @Autowired
    public HandController(HandService handService) {
        this.handService = handService;
    }


    //RETURNS FIRST
    @GetMapping
    public List<Hand> getHands(
            @RequestParam(required = false) String hand

    ) {

        if (hand != null) {
            return handService.getSpecificHands(hand);

        }
        return handService.findAll();
    }


    //RETURN MOST COMMON HAND
    @GetMapping("/common_hand")
    public String commonHand(){
        return handService.commonHand();
    }

    @GetMapping("/odds")
    public String oddsOfHands(
            @RequestParam(required = true) String hand
    ) {

        return handService.oddsOfHand(hand);

    }
    @GetMapping("/result")
    public String result1(
            @RequestParam(required = true) String result
    ){
        //should grab all rows in query where someone got a *Blank* on the flop IE: Full House
        return handService.findByResult(result);

    }

    @GetMapping("/flush")
    public String findBySuited(){

        return handService.findBySuited();

    }



    @PostMapping
    public ResponseEntity<Hand> addHand(@RequestBody Hand hand) {
        Hand newHand = new Hand();
        newHand.setHand(String.valueOf(hand));
        return new ResponseEntity<>(newHand, HttpStatus.CREATED);


    }
}