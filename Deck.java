import java.util.ArrayList;
import java.util.Random;

public class Deck {

	private ArrayList<Card> cards;
	private ArrayList<Card> haveused;
	private ArrayList<Card> openCard;
	public int nused = 0;

	public Deck(int nDeck) {
		cards = new ArrayList<Card>();
		haveused = new ArrayList<Card>();
		openCard = new ArrayList<Card>();
		for (int k = 0; k < nDeck; k++) {
			for (int suit = 1; suit < 5; suit++) {
				for (int num = 1; num < 14; num++) {
					Card card;
					switch (suit) {
					case 1:
						card = new Card(Card.Suit.Club, num);
						cards.add(card);
						break;
					case 2:
						card = new Card(Card.Suit.Diamond, num);
						cards.add(card);
						break;
					case 3:
						card = new Card(Card.Suit.Heart, num);
						cards.add(card);
						break;
					case 4:
						card = new Card(Card.Suit.Spade, num);
						cards.add(card);
						break;
					}
				}
			}
		}

	}

	public ArrayList<Card> getAllCards() {
		return cards;
	}

	public void shuffle() {
		Random rnd = new Random((int)(Math.random()*10000));
		int m;

		while (!cards.isEmpty()) {
			haveused.add(cards.get(0));
			cards.remove(cards.get(0));
		}

		while (!haveused.isEmpty()) {
			m = rnd.nextInt(haveused.size());
			cards.add(haveused.get(m));
			haveused.remove(m);
		}
		nused = 0;

		openCard.clear();
	}

	public Card getOneCard(boolean isOpened) {
		if (cards.isEmpty() == true)
			shuffle();

		Card tmp = cards.get(0);
		haveused.add(tmp);
		cards.remove(tmp);
		nused++;
		if (isOpened)
			openCard.add(tmp);

		return tmp;
	}

	public ArrayList<Card> getOpenedCard() {
		return openCard;
	}

}