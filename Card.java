
public class Card {

	public enum Suit {
		Club, Diamond, Heart, Spade
	}

	private Suit suit;

	private int rank;

	public Card(Suit s, int r) {
		suit = s;
		rank = r;
	}

	public void printCard() {
		System.out.print(suit + " " + rank + "\n");
	}

	public Suit getSuit() {
		return suit;
	}

	public int getRank() {
		return rank;
	}

}