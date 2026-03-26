package com.qualitystream.tutorial.controllers;

import com.qualitystream.tutorial.model.Frut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping(value="/api")
public class AppController {

    private List<Frut> fruts = List.of(
            new Frut("apple", 10),
            new Frut("banana", 15),
            new Frut("test", 2),
            new Frut("strawberry", 9),
            new Frut("cucumber", 17),
            new Frut("watermelon", 11) );

    @GetMapping(value="/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("hello spring boot", HttpStatus.OK);
    }

    @GetMapping(value="/bye")
    public ResponseEntity<String> bye() {
        return new ResponseEntity<>("get bye", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> message() {
        return new ResponseEntity<>("this is a post endpoint:", HttpStatus.OK);
    }


    @GetMapping(value="/fruts")
    public ResponseEntity<List<Frut>> getFruts() {
        return new ResponseEntity<>(fruts, HttpStatus.CREATED);
    }

    @GetMapping(value="/expensiveFrut")
    public ResponseEntity<Frut> getExpensiveFrut() {

        Frut mostExpensiveFrut = null;

        int expensive = Integer.MIN_VALUE;
        for (Frut frut : fruts) {
            if (frut.getPrice() > expensive) {
               mostExpensiveFrut = frut;
               expensive = frut.getPrice();
            }
        }
        return new ResponseEntity<>(mostExpensiveFrut, HttpStatus.CREATED);
    }

    @GetMapping(value="/cheapFruts")
    public ResponseEntity<Frut> getCheapFruts() {

        Iterator<Frut> iteratorFruts = fruts.iterator();
        Frut mostCheaperFrut = null;
        int minPrice = Integer.MAX_VALUE;

        while (iteratorFruts.hasNext()) {
            Frut frut = iteratorFruts.next();

            if (frut.getPrice() < minPrice) {
                mostCheaperFrut = frut;
                minPrice = frut.getPrice();
            }
        }
        return new ResponseEntity<>(mostCheaperFrut, HttpStatus.CREATED);
    }

}
