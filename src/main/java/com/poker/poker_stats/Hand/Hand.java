package com.poker.poker_stats.Hand;

import jakarta.persistence.*;

@Entity
@Table(name = "poker_stats")
public class Hand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hand")
    private String hand;

    @Column(name = "flop")
    private String flop;

    @Column(name = "result1")
    private String result1;

    @Column(name = "turn")
    private String turn;

    @Column(name = "result2")
    private String result2;

    @Column(name = "river")
    private String river;

    @Column(name = "result3")
    private String result3;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHand() {
        return hand;
    }

    public void setHand(String hand) {
        this.hand = hand;
    }

    public String getFlop() {
        return flop;
    }

    public void setFlop(String flop) {
        this.flop = flop;
    }

    public String getResult1() {
        return result1;
    }

    public void setResult1(String result1) {
        this.result1 = result1;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public String getResult2() {
        return result2;
    }

    public void setResult2(String result2) {
        this.result2 = result2;
    }

    public String getRiver() {
        return river;
    }

    public void setRiver(String river) {
        this.river = river;
    }

    public String getResult3() {
        return result3;
    }

    public void setResult3(String result3) {
        this.result3 = result3;
    }
}