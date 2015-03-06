import java.util.Scanner;

public class Play {

	public static void main(String[] args) {
		playGame();

	}

	public static Hand playGame() {
		// make scanner
		Scanner input = new Scanner(System.in);
		// take number of hands, max ten
		System.out.println("Welcome to poker! \n");
		System.out.println("How many hands will be dealt?: ");
		int handCount = input.nextInt();
		if (handCount > 10) {
			System.out
					.println("Max player count is 10, 10 hands will be dealt");
			handCount = 10;
		}
		// create deck
		Deck deck = new Deck();
		// create array of hands
		Hand[] handArray = new Hand[handCount];
		// for loop for number of hands
		for (int hand = 0; hand < handCount; hand++) {
			handArray[hand] = new Hand(deck);
			System.out.println("");
			System.out.println("Hand " + (hand + 1) + " is:");
			handArray[hand].printHand5(handArray[hand]);

		}
		// return evaluate hands
		Hand winningHand = rateHand(handArray);
		System.out.println("");
		System.out.println("The winner is!!!:");
		winningHand.printHand5(winningHand);
		System.out.println("Winning with a:");

		int handType = winningHand.getStrength();
		switch (handType) {
		case 10: {
			System.out.println("Royal Flush");
			break;
		}
		case 9: {
			System.out.println("Straight Flush");
			break;
		}
		case 8: {
			System.out.println("Four of a kind");
			break;
		}
		case 7: {
			System.out.println("Full House");
			break;
		}
		case 6: {
			System.out.println("Flush");
			break;
		}
		case 5: {
			System.out.println("Straight");
			break;
		}
		case 4: {
			System.out.println("Three of a kind");
			break;
		}
		case 3: {
			System.out.println("Two Pair");
			break;
		}
		case 2: {
			System.out.println("Pair");
			break;
		}
		case 1: {
			System.out.println("High card");
			break;
		}
		}
		return winningHand;

	}

	public static Hand rateHand(Hand[] hands) {
		Hand winner = null;
		int max = 0;
		int high = 0;
		int low = 0;
		int kick = 0;
		int kick2 = 0;
		int kick3 = 0;
		int kick4 = 0;
		for (Hand hand : hands) {
			hand.sortHand();
			hand.rateHand(hand);
			if (hand.getStrength() > max) {
				max = hand.getStrength();
				high = hand.getHigh();
				low = hand.getLow();
				kick = hand.getKicker()[0];
				kick2 = hand.getKicker()[1];
				kick3 = hand.getKicker()[2];
				kick4 = hand.getKicker()[3];
				winner = hand;
			} else if (hand.getStrength() == max) {
				if (hand.getHigh() > high) {
					high = hand.getHigh();
					low = hand.getLow();
					kick = hand.getKicker()[0];
					kick2 = hand.getKicker()[1];
					kick3 = hand.getKicker()[2];
					kick4 = hand.getKicker()[3];
					winner = hand;
				} else if (hand.getHigh() == high) {
					if (hand.getLow() > low) {
						low = hand.getLow();
						kick = hand.getKicker()[0];
						kick2 = hand.getKicker()[1];
						kick3 = hand.getKicker()[2];
						kick4 = hand.getKicker()[3];
						winner = hand;
					} else if (hand.getLow() == low) {
						if (hand.getKicker()[0] > kick) {
							kick = hand.getKicker()[0];
							kick2 = hand.getKicker()[1];
							kick3 = hand.getKicker()[2];
							kick4 = hand.getKicker()[3];
							winner = hand;
						} else if (hand.getKicker()[0] == kick) {
							if (hand.getKicker()[1] > kick2)
								kick2 = hand.getKicker()[1];
							kick3 = hand.getKicker()[2];
							kick4 = hand.getKicker()[3];
							winner = hand;
						} else if (hand.getKicker()[1] == kick2) {
							if (hand.getKicker()[2] > kick3) {
								kick3 = hand.getKicker()[2];
								kick4 = hand.getKicker()[3];
								winner = hand;
							} else if (hand.getKicker()[2] == kick3) {
								if (hand.getKicker()[3] > kick4) {
									kick4 = hand.getKicker()[3];
									winner = hand;
								} else if (hand.getKicker()[3] == kick4) {
									winner = hand;
									System.out.println("THERE IS A TIE!");
								}
							}
						}

					}
				}

			}
		}
		return winner;
	}
}
