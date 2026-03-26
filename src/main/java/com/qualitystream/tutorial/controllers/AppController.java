package com.qualitystream.tutorial.controllers;

import com.qualitystream.tutorial.model.Fruit;
import com.qualitystream.tutorial.model.Product;
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
@RequestMapping(value = "/api")
public class AppController {

    //Listo.of es inmutable, ArrayList se puede añadir mas en el futuro
    private List<Fruit> fruts = List.of(
            new Fruit("apple", 10),
            new Fruit("banana", 15),
            new Fruit("test", 2),
            new Fruit("strawberry", 9),
            new Fruit("cucumber", 17),
            new Fruit("watermelon", 11));

    private List<Product> products = new ArrayList<>(
            List.of(
                    new Product("tv", 400, 0),
                    new Product("phone", 300, 0),
                    new Product("laptop", 540, 20),
                    new Product("smartwatch", 210, 25)
            ));

    @GetMapping(value = "/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("hello spring boot", HttpStatus.OK);
    }

    @GetMapping(value = "/bye")
    public ResponseEntity<String> bye() {
        return new ResponseEntity<>("get bye", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> message() {
        return new ResponseEntity<>("this is a post endpoint:", HttpStatus.OK);
    }

    @GetMapping(value = "/fruts")
    public ResponseEntity<List<Fruit>> getFruts() {
        return new ResponseEntity<>(fruts, HttpStatus.OK);
    }

    @GetMapping(value = "/expensiveFrut")
    public ResponseEntity<Fruit> getExpensiveFrut() {

        Fruit mostExpensiveFruit = null;

        int expensive = Integer.MIN_VALUE;
        for (Fruit frut : fruts) {
            if (frut.getPrice() > expensive) {
                mostExpensiveFruit = frut;
                expensive = frut.getPrice();
            }
        }
        return new ResponseEntity<>(mostExpensiveFruit, HttpStatus.CREATED);
    }

    @GetMapping(value = "/cheapFruts")
    public ResponseEntity<Fruit> getCheapFruts() {

        Iterator<Fruit> iteratorFruts = fruts.iterator();
        Fruit mostCheaperFrut = null;
        int minPrice = Integer.MAX_VALUE;

        while (iteratorFruts.hasNext()) {
            Fruit frut = iteratorFruts.next();

            if (frut.getPrice() < minPrice) {
                mostCheaperFrut = frut;
                minPrice = frut.getPrice();
            }
        }
        return new ResponseEntity<>(mostCheaperFrut, HttpStatus.CREATED);
    }

    @GetMapping(value = "/products")
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/products/available")
    public ResponseEntity<List<Product>> getAvailableProducts() {

        List<Product> availableProducts = new ArrayList<>();

        for (Product product : products) {
            if (product.getStock() > 0 ) {
                availableProducts.add(product);
            }
        }
        return new ResponseEntity<>(availableProducts, HttpStatus.OK);
    }

    @GetMapping(value = "/products/mostExpensive")
    public ResponseEntity<Product> getMostExpensive() {




        return new ResponseEntity<>(HttpStatus.OK);
    }

}