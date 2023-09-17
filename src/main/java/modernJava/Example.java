package modernJava;

import java.util.*;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;

public class Example {

    public static void main(String[] args) {

        List<Dish> menu = Arrays.asList(
                new Dish("pork" , false , 800 , Dish.Type.MEAT),
                new Dish("beef" , false , 700 , Dish.Type.MEAT),
                new Dish("chicken" , false , 400 , Dish.Type.MEAT),
                new Dish("french fries" , true , 530 , Dish.Type.OTHER),
                new Dish("rice" , true , 350 , Dish.Type.OTHER),
                new Dish("season fruit" , true , 120 , Dish.Type.OTHER),
                new Dish("pizza" , true , 550 , Dish.Type.OTHER),
                new Dish("prawns" , false , 300 , Dish.Type.FISH),
                new Dish("salmon" , false , 450 , Dish.Type.FISH)
        );

        List<Dish> specialMenu = Arrays.asList(
                new Dish("seasonal fruit" , true , 120 , Dish.Type.OTHER),
                new Dish("prawns" , false , 300 , Dish.Type.FISH),
                new Dish("rice" , true , 350 , Dish.Type.OTHER),
                new Dish("chicken" , false , 400 , Dish.Type.MEAT),
                new Dish("french fires" , true , 530 , Dish.Type.OTHER)
        );

        IntStream.generate(new IntSupplier(){
            private int prev = 0;
            private int curr = 1;
            public int getAsInt(){
                int oldPrev = this.prev;
                int nextVal = this.prev + this.curr;
                this.prev = this.curr;
                this.curr = nextVal;
                return oldPrev;
            }
        }).limit(10).forEach(System.out::println);
    }

    public static class Dish {
        private final String name;
        private final boolean vegetarian;
        private final int calories;
        private final Type type;

        public enum Type {MEAT , FISH , OTHER}
        public Dish(String name, boolean vegetarian, int calories , Type type) {
            this.name = name;
            this.vegetarian = vegetarian;
            this.calories = calories;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public boolean isVegetarian() {
            return vegetarian;
        }

        public int getCalories() {
            return calories;
        }

        public Type getType() {
            return type;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Dish{");
            sb.append("name='").append(name).append('\'');
            sb.append(", vegetarian=").append(vegetarian);
            sb.append(", calories=").append(calories);
            sb.append(", type=").append(type);
            sb.append('}');
            return sb.toString();
        }
    }
}
