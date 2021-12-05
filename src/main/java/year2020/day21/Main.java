package year2020.day21;

import base.Base;

import java.io.IOException;
import java.util.*;

public class Main extends Base {
    private static final String INPUT = "2020/day21-input.txt";
    private static List<String> instructions;
    private static List<Dish> dishes = new ArrayList<>();
    private static Map<String, String> allergensMap = new HashMap<>();


    private void prepare() throws IOException {
        String line;
        instructions = new ArrayList<>();
        while ((line = input.readLine()) != null) {
            instructions.add(line);
        }
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.mainMethod(INPUT);
        main.prepare();
        System.out.println("PART1:");
        main.part1();
        System.out.println("PART2:");
        main.part2();
    }

    @Override
    public void part1() throws IOException {
        boolean done = false;
        for (int i = 0; i < instructions.size(); i++) {
            // split
            String ingredients = instructions.get(i).split("\\(")[0].trim();
            String allergens = instructions.get(i).split("\\(")[1].trim();
            allergens = allergens.substring(9, allergens.length() - 1);

            dishes.add(new Dish(new LinkedList<>(Arrays.asList(ingredients.split(" "))), new LinkedList<>(Arrays.asList(allergens.split(", ")))));
        }

        // count number of allergens
        Set<String> uniqueAllergens = new HashSet<>();
        for (Dish dish : dishes) {
            for (String al : dish.getAllergens()) {
                uniqueAllergens.add(al);
            }
        }

        while (allergensMap.size() < uniqueAllergens.size()) {
            for (int i = 0; i < dishes.size(); i++) {
                Dish dish = dishes.get(i);
                for (String a : dish.getAllergens()) {
                    if (!allergensMap.containsKey(a)) {
                        String foundMatch = tryAllergenMatch(a, i, dish.getIngredients());

                        if (foundMatch != null) {
                            // remove ingredient from all dishes
                            for (Dish d : dishes) {
                                d.getIngredients().remove(foundMatch);
                            }
                        }
                    }
                }
            }
        }

        // all allergens found
        int total = 0;
        for (Dish dish : dishes) {
            total += dish.getIngredients().size();
        }

        System.out.println(total);
    }

    private String tryAllergenMatch(String a, int currentIndex, List<String> currentIngredients) {
        String foundMatch = null;
        for (int i = 0; i < dishes.size(); i++) {
            Dish dish = dishes.get(i);

            if (currentIndex < i) {
                List<String> ingredients = dish.getIngredients();
                List<String> allergens = dish.getAllergens();

                if (allergens.contains(a)) {
                    List<String> possibleIngredients = new ArrayList<>();
                    String matchingIngredient = "";
                    for (String cIn : currentIngredients) {
                        for (String in : ingredients) {
                            if (in.equals(cIn)) {
                                possibleIngredients.add(in);
                                matchingIngredient = in;
                                break;
                            }
                        }
                    }

                    if (possibleIngredients.size() == 1) {
                        // a match is found for a. Add a to map with matching ingredient
                        allergensMap.put(a, matchingIngredient);
                        foundMatch = matchingIngredient;
                        break;
                    } else {
                        tryAllergenMatch(a, i, possibleIngredients);
                    }
                }
            } else {
                if (dish.getIngredients().size() == 1) {
                    allergensMap.put(a, dish.getIngredients().get(0));
                    foundMatch = dish.getIngredients().get(0);
                    break;
                }
            }
        }

        return foundMatch;
    }

    class Dish {
        private List<String> ingredients;
        private List<String> allergens;

        public Dish(List<String> ingredients, List<String> allergens) {
            this.ingredients = ingredients;
            this.allergens = allergens;
        }

        public List<String> getIngredients() {
            return ingredients;
        }

        public void setIngredients(List<String> ingredients) {
            this.ingredients = ingredients;
        }

        public List<String> getAllergens() {
            return allergens;
        }

        public void setAllergens(List<String> allergens) {
            this.allergens = allergens;
        }
    }

    @Override
    public void part2() throws IOException {

    }
}
