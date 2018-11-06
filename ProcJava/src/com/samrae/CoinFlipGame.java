package com.samrae;

import java.util.Random;
import java.util.Scanner;

public class CoinFlipGame {
	public static final String HEADS = "Heads";
	public static final String TAILS = "Tails";

	public static String getGuess() {
		return Math.random() > 0.5 ? HEADS : TAILS;
	}

	public static void main(String[] args) {
		int games = 0;
		int p1Wins = 0;
		int p2Wins = 0;
		Scanner scanner = new Scanner(System.in);
		Random r = new Random();

		while (games++ < 10) {
			String guess = "N";
			while (guess == "N") {
				System.out.println("\nWhat is your guess? (H)eads or (T)tails?");
				guess = scanner.next().toUpperCase();
				switch (guess) {
				case "T":
					guess = TAILS;
					break;
				case "H":
					guess = HEADS;
					break;
				default:
					guess = "N";

				}
			}
			String player1 = guess;
			String player2 = ((player1 == HEADS) ? TAILS : HEADS);
			int winner = play(player1, player2, r);
			int x = (winner == 1) ? p1Wins++ : p2Wins++;

		}

		System.out.println("\n\tYou won " + p1Wins + " games\n\tComputer won " + p2Wins + " games");
	}

	public static int play(String p1, String p2, Random r) {
		if (r.nextDouble() > 0.5) {
			System.out.println("You guessed correctly:" + p1);
			return 1;
		} else {
			System.out.println("Computer guessed correctly:" + p2);
			return 2;
		}
	}

}
