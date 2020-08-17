package dev.Lenox.tilegame.random;

import java.util.Random;

public class RandomNumber {
//THIS CLASS IS TO AUTOMATICALLY CREATE A RANDOM NUMBER
Random dice;
private int number;
public RandomNumber(int min, int max){
	dice = new Random();
	number = 1+dice.nextInt(max - min) + min;
	
}
public int getNumber(){
	return number;
}
}
