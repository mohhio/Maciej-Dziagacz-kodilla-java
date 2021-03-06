package com.kodilla.patterns.builder.bigmac;

import java.util.ArrayList;
import java.util.List;

public class Bigmac {
    private final String bun;
    private final int burgers;
    private final String sauce;
    private final List<String> ingredients;

    private Bigmac(String bun, int burgers, String sauce, List<String> ingredients) {
        this.bun = bun;
        this.burgers = burgers;
        this.sauce = sauce;
        this.ingredients = ingredients;
    }

    public static class BigmacBuilder {
        private String bun;
        private int burgers;
        private String sauce;
        private List<String> ingredients = new ArrayList<>();

        private List<String> stockSauces() {
            List<String> stockSauces = new ArrayList<>();
            stockSauces.add("standard");
            stockSauces.add("thousands island");
            stockSauces.add("barbecue");
            return stockSauces;
        }

        private List<String> stockIngredients() {
            List<String> stockIngredients = new ArrayList<>();
            stockIngredients.add("onion");
            stockIngredients.add("beacon");
            stockIngredients.add("cucumber");
            stockIngredients.add("chili");
            stockIngredients.add("mushrooms");
            stockIngredients.add("shrimp");
            stockIngredients.add("cheese");
            return stockIngredients;
        }

        public BigmacBuilder bun(String bun) {
            this.bun = bun;
            return this;
        }

        public BigmacBuilder burgers(int burgers) {
            this.burgers = burgers;
            return this;
        }

        public BigmacBuilder sauce(String sauce) {
            if (stockSauces().contains(sauce)) {
                this.sauce = sauce;
                return this;
            } else {
                throw new IllegalStateException("you can chose: standard, thousands islands or barbecue ");
            }
        }

        public BigmacBuilder ingredients(String ingredients) {
            if (stockIngredients().contains(ingredients)) {
                this.ingredients.add(ingredients);
                return this;
            } else {
                throw new IllegalStateException("look at the list of ingredients!");
            }
        }

        public Bigmac builder() {
            return new Bigmac(bun, burgers, sauce, ingredients);
        }
    }

    @Override
    public String toString() {
        return "Bigmac: " +
                "\nbun: " + bun +
                "\nburgers: " + burgers +
                "\nsauce: " + sauce +
                "\ningredients: " + ingredients;
    }

    public List<String> getIngredients() {
        return ingredients;
    }
}
