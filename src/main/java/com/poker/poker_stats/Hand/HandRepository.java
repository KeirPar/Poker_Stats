package com.poker.poker_stats.Hand;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HandRepository extends JpaRepository<Hand, String> {

        List<Hand> findAll();           //GRAB ALL HANDS


        //find specific hand using Query
        @Query(value = "SELECT * FROM poker_stats WHERE hand = :hand", nativeQuery = true)
        List<Hand> findCustomByHand(@Param("hand") String hand);


        //Query to select rows from DB where the result1, result2, result3 is BLANK
        @Query(value = "SELECT * FROM poker_stats WHERE result1 = :result1",nativeQuery = true)
        List<Hand> findByResult1(@Param("result1") String result);

        @Query(value = "SELECT * FROM poker_stats WHERE result2 = :result2",nativeQuery = true)
        List<Hand> findByResult2(@Param("result2") String result);

        @Query(value = "SELECT * FROM poker_stats WHERE result3 = :result3",nativeQuery = true)
        List<Hand> findByResult3(@Param("result3") String result);


        //Query for selecting all rows where our hand is suited <Diamonds, Spaids, etc>
        @Query(value = "SELECT * FROM poker_stats WHERE (LEFT(SPLIT_PART(hand, ' ', 1), 1) = LEFT(SPLIT_PART(hand, ' ', 2), 1))", nativeQuery = true)
        List<Hand> findBySuited();

        //Query for selecting all rows where our hand is a pair
        @Query(value = "SELECT * FROM poker_stats WHERE (RIGHT(SPLIT_PART(hand, ' ', 1), 1) = RIGHT(SPLIT_PART(hand, ' ', 2), 1))", nativeQuery = true)
        List<Hand> findByPair();




}