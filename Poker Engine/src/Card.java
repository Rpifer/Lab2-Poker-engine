
public class Card {
	private eSuit suit;
	private eValue value;
	
	
	
	public Card(int suit, int value){
		switch(suit){
			case 1: this.suit = eSuit.CLUBS; break;
			case 2: this.suit = eSuit.DIAMONDS; break;
			case 3: this.suit = eSuit.HEARTS; break;
			case 4: this.suit = eSuit.SPADES; break;
		}
		switch(value){
			case 2: this.value = eValue.TWO; break;
			case 3: this.value = eValue.THREE; break;
			case 4: this.value = eValue.FOUR; break;
			case 5: this.value = eValue.FIVE; break;
			case 6: this.value = eValue.SIX; break;
			case 7: this.value = eValue.SEVEN; break;
			case 8: this.value = eValue.EIGHT; break;
			case 9: this.value = eValue.NINE; break;
			case 10: this.value = eValue.TEN; break;
			case 11: this.value = eValue.JACK; break;
			case 12: this.value = eValue.QUEEN; break;
			case 13: this.value = eValue.KING; break;
			case 14: this.value = eValue.ACE; break;
		}
	}
	public eSuit getSuit(){
		return suit;
	}
	public eValue getValue(){
		return value;
	}
}

