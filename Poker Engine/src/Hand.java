import java.util.*;

public class Hand {
	// ranks hand
	private int strength;
	// high card value for play cards
	private int high;
	// low card value for play cards
	private int low;
	// card values not used for high play
	private int[] kicker = new int[4];
	// new list to hold cards in hand
	private ArrayList<Card> handCards = new ArrayList<Card>();

	// getter for hand strength
	public int getStrength() {
		return strength;
	}

	// setter for hand strength
	private void setStrength(int strength) {
		this.strength = strength;
	}

	// getter for high card
	public int getHigh() {
		return high;
	}

	// setter for high card
	private void setHigh(int high) {
		this.high = high;
	}

	// getter for low card
	public int getLow() {
		return low;
	}

	// setter for low card
	private void setLow(int low) {
		this.low = low;
	}

	// getter for kickers
	public int[] getKicker() {
		return kicker;
	}


	public Hand(Deck deck) {
		// all hands start with no strength or high cards until ranked
		this.setStrength(0);
		this.setHigh(0);
		this.setLow(0);
		// draws 5 cards and adds them to hand
		for (int i = 0; i < 5; i++) {
			Card topCard = deck.draw();
			handCards.add(topCard);
		}
	}

	// getter for array to access card in hands
	public ArrayList<Card> getHandCards() {
		return handCards;
	}

	// shortens writing of mutli-method chain to get card value
	public int getCardValue(Hand hand, int index) {
		return hand.getHandCards().get(index).getValue().getValue();
	}

	// Sorts hand from low->high ignoring suits
	public void sortHand() {
		Card temp;
		// check each positions value and compares it to every other value
		// when it finds the lowest position that it is smaller than, it removes
		// the
		// current position and adds it before the value its smaller than.
		for (int i = 1; i < 5; i++) {
			if (handCards.get(i).getValue().getValue() <= handCards.get(0)
					.getValue().getValue()) {
				temp = handCards.get(i);
				handCards.remove(i);
				handCards.add(0, temp);
			} else if (handCards.get(i).getValue().getValue() <= handCards
					.get(1).getValue().getValue()) {
				temp = handCards.get(i);
				handCards.remove(i);
				handCards.add(1, temp);
			} else if (handCards.get(i).getValue().getValue() <= handCards
					.get(2).getValue().getValue()) {
				temp = handCards.get(i);
				handCards.remove(i);
				handCards.add(2, temp);
			} else if (handCards.get(i).getValue().getValue() <= handCards
					.get(3).getValue().getValue()) {
				temp = handCards.get(i);
				handCards.remove(i);
				handCards.add(3, temp);
			}
		}

	}

	// Used to rate a hand when passed a specific hand
	public void rateHand(Hand hand) {
		// Top ranking, high is Ace, low is 10
		if (hand.isRoyalFlush()) {
			hand.setStrength(10);
			hand.setHigh(14);
			hand.setLow(10);
		}
		// 2nd ranking, sets the high card as last postiion
		else if (hand.isStraightFlush()) {
			hand.setStrength(9);
			hand.setHigh(getCardValue(hand, 4));
			hand.setLow(getCardValue(hand, 0));
		}
		// 3rd ranking, the 4th card will always be part of the 4 of a kind so
		// that is high card
		// the kicker is then assigned whichever card is the odd one out
		else if (hand.isFourOfAKind()) {
			hand.setStrength(8);
			hand.setHigh(getCardValue(hand, 3));
			hand.setLow(0);
			if (handCards.get(0).getValue().getValue() == handCards.get(3)
					.getValue().getValue()) {
				this.kicker[0] = getCardValue(hand, 4);
			} else
				this.kicker[0] = getCardValue(hand, 0);
		}

		// 4th ranking, the last card will always be the last card
		// and the low card will always be first
		else if (hand.isFullHouse()) {
			hand.setStrength(7);
			hand.setHigh(getCardValue(hand, 4));
			hand.setLow(getCardValue(hand, 0));
		}

		// 5th ranking, the top card will always be last value
		// the rest are kickers and is used to determine winner if top card ties
		else if (hand.isFlush()) {
			hand.setStrength(6);
			hand.setHigh(getCardValue(hand, 4));
			for (int i = 0; i < 4; i++) {
				this.kicker[3 - i] = getCardValue(hand, i);
			}
		}

		// 4th ranking, the top card will always be last value
		// the low card will always be first card
		else if (hand.isStraight()) {
			hand.setStrength(5);
			hand.setHigh(getCardValue(hand, 4));
			hand.setLow(getCardValue(hand, 0));
		}

		// 3rd ranking, the middle card will always be high value
		// if it is a low 3OAK then the kicker will be cards 4 and 5
		// i it is a high 3OAK the the kicker will be cards 1 and 2
		else if (hand.isThreeOfAKind()) {
			hand.setStrength(4);
			hand.setHigh(getCardValue(hand, 2));
			if (getCardValue(hand, 0) == getCardValue(hand, 2)) {
				this.kicker[0] = getCardValue(hand, 3);
				this.kicker[1] = getCardValue(hand, 4);
			} else {
				this.kicker[0] = getCardValue(hand, 1);
				this.kicker[1] = getCardValue(hand, 0);
			}
		} else if (hand.isTwoPair()) {
			hand.setStrength(3);
			if ((getCardValue(hand, 0) == getCardValue(hand, 1))
					&& (getCardValue(hand, 2) == getCardValue(hand, 3))) {
				hand.setHigh(getCardValue(hand, 3));
				hand.setLow(getCardValue(hand, 0));
				this.kicker[0] = getCardValue(hand, 4);
			} else if ((getCardValue(hand, 0) == getCardValue(hand, 1))
					&& (getCardValue(hand, 3) == getCardValue(hand, 4))) {
				hand.setHigh(getCardValue(hand, 3));
				hand.setLow(getCardValue(hand, 0));
				this.kicker[0] = getCardValue(hand, 2);
			}

			else if ((getCardValue(hand, 2) == getCardValue(hand, 1))
					&& (getCardValue(hand, 4) == getCardValue(hand, 3))) {
				hand.setHigh(getCardValue(hand, 3));
				hand.setLow(getCardValue(hand, 1));
				this.kicker[0] = getCardValue(hand, 0);
			}
		} else if (hand.isPair()) {
			hand.setStrength(2);
			for (int i = 0; i < 4; i++) {
				if ((handCards.get(i).getValue().getValue() == handCards
						.get(i + 1).getValue().getValue())) {
					hand.setHigh(getCardValue(hand, i));
					switch (i) {
					case 0: {
						this.kicker[0] = getCardValue(hand, 4);
						this.kicker[1] = getCardValue(hand, 3);
						this.kicker[2] = getCardValue(hand, 2);
						break;
					}
					case 1: {
						this.kicker[0] = getCardValue(hand, 4);
						this.kicker[1] = getCardValue(hand, 3);
						this.kicker[2] = getCardValue(hand, 1);
						break;
					}
					case 2: {
						this.kicker[0] = getCardValue(hand, 4);
						this.kicker[1] = getCardValue(hand, 1);
						this.kicker[2] = getCardValue(hand, 0);
						break;
					}
					case 3: {
						this.kicker[0] = getCardValue(hand, 2);
						this.kicker[1] = getCardValue(hand, 1);
						this.kicker[2] = getCardValue(hand, 0);
						break;
					}
					}
				}

			}

		} else {
			hand.setStrength(1);
			hand.setHigh(getCardValue(hand, 4));
			this.kicker[0] = getCardValue(hand, 3);
			this.kicker[1] = getCardValue(hand, 2);
			this.kicker[2] = getCardValue(hand, 1);
			this.kicker[3] = getCardValue(hand, 0);
		}
	}

	// Example: (10H,JH, QH, KH, AceH)
	// checks is all five cards are the 10,J,Q,K,Ace
	// then checks if all of them have same suit using in straight
	public boolean isRoyalFlush() {
		if ((handCards.get(0).getValue().getValue() == 10)
				&& (handCards.get(1).getValue().getValue() == 11)
				&& (handCards.get(2).getValue().getValue() == 12)
				&& (handCards.get(3).getValue().getValue() == 13)
				&& (handCards.get(4).getValue().getValue() == 14)) {
			return (isFlush());
		} else
			return false;
	}

	// Example:(4H,5H,6H,7H,8H)
	// checks if all are the same suit using isStraight
	// if they are, it will see if the first card is one less than next card
	// etc.
	public boolean isStraightFlush() {
		return (isFlush() && isStraight());
	}

	// Example: (3H,6H,6C,6D,6S)
	// Checks if the first and fourth cards are the same (then the between are
	// the same)
	// Or if the second an fith are the same (again, between)
	public boolean isFourOfAKind() {
		if (handCards.get(0).getValue().getValue() == handCards.get(3)
				.getValue().getValue()) {
			return true;
		} else {

			return (handCards.get(1).getValue().getValue() == handCards.get(4)
					.getValue().getValue());
		}

	}

	public boolean isFullHouse() {
		if ((handCards.get(0).getValue().getValue() == handCards.get(2)
				.getValue().getValue())
				&& (handCards.get(3).getValue().getValue() == handCards.get(4)
						.getValue().getValue())) {
			return true;
		} else if ((handCards.get(0).getValue().getValue() == handCards.get(1)
				.getValue().getValue())
				&& (handCards.get(2).getValue().getValue() == handCards.get(4)
						.getValue().getValue())) {
			return true;
		}
		return false;

	}

	// Example: (3C,7C,8C,JC,KC)
	// Checks if all cards have the same suit
	public boolean isFlush() {
		return ((handCards.get(0).getSuit() == handCards.get(1).getSuit())
				&& (handCards.get(1).getSuit() == handCards.get(2).getSuit())
				&& (handCards.get(2).getSuit() == handCards.get(3).getSuit()) && (handCards
				.get(3).getSuit() == handCards.get(4).getSuit()));

	}

	// Example: (5C,6H,7C,8S,9C)
	// Checks if all values are in order and one plus
	public boolean isStraight() {
		if ((handCards.get(0).getValue().getValue() == handCards.get(1)
				.getValue().getValue() - 1)
				&& (handCards.get(1).getValue().getValue() == handCards.get(2)
						.getValue().getValue() - 1)
				&& (handCards.get(2).getValue().getValue() == handCards.get(3)
						.getValue().getValue() - 1)
				&& (handCards.get(3).getValue().getValue() == handCards.get(4)
						.getValue().getValue() - 1)) {
			return true;
		}
		return false;
	}

	// Example: (5C,5S,5H,8H,JC)
	// Checks if there are 3 of the same value in the hand
	public boolean isThreeOfAKind() {
		if ((handCards.get(0).getValue().getValue() == handCards.get(2)
				.getValue().getValue())
				|| (handCards.get(2).getValue().getValue() == handCards.get(4)
						.getValue().getValue())) {
			return true;
		}
		return false;
	}

	// Example: (2C,2H,9S,JH,JS)
	// Checks if there are 2 pairs of 2 values
	public boolean isTwoPair() {
		if ((handCards.get(0).getValue().getValue() == handCards.get(1)
				.getValue().getValue())
				&& (handCards.get(2).getValue().getValue() == handCards.get(3)
						.getValue().getValue())) {
			return true;
		}

		else if ((handCards.get(1).getValue().getValue() == handCards.get(2)
				.getValue().getValue())
				&& (handCards.get(3).getValue().getValue() == handCards.get(4)
						.getValue().getValue())) {
			return true;
		}

		else if ((handCards.get(0).getValue().getValue() == handCards.get(1)
				.getValue().getValue())
				&& (handCards.get(3).getValue().getValue() == handCards.get(4)
						.getValue().getValue())) {
			return true;

		}
		return false;
	}

	// Example: (6H,9C,10H,10S,KC)
	// Checks if there is a pair of values
	public boolean isPair() {
		for (int i = 0; i < 4; i++) {
			if ((handCards.get(i).getValue().getValue() == handCards.get(i + 1)
					.getValue().getValue())) {
				return true;
			}

		}
		return false;
	}
	public void printHand5(Hand hand){
		for (int i = 0; i < 5; i++){
			System.out.println(hand.getHandCards().get(i).getValue() + " of " + hand.getHandCards().get(i).getSuit());
		}
	}
}
