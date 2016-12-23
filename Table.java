import java.util.ArrayList;

public class Table {

	final int MAXPLAYER = 4;
	Deck deck;
	Player[] players;
	Dealer dealer;
	int[] pos_betArray = new int[MAXPLAYER];

	public Table(int tbl) {
		deck = new Deck(tbl);
		players = new Player[MAXPLAYER];
	}

	public void set_player(int pos, Player p) {
		players[pos] = p;
	}

	public Player[] get_player() {
		return players;
	}

	public void set_dealer(Dealer d) {
		dealer = new Dealer();
		dealer = d;
	}

	public Card get_face_up_card_of_dealer() {
		Card fucard = dealer.getOneRoundCard().get(1);
		return fucard;
	}

	private void ask_each_player_about_bets() {

		int i = 0;
		for (Player p : players) {
			p.say_hello();
			pos_betArray[i] = p.make_bet();
			i++;
		}
	}

	private void distribute_cards_to_dealer_and_players() {
		
		deck.shuffle();

		for (Player p : players) {
			ArrayList<Card> playerCard = new ArrayList<Card>();
			playerCard.add(deck.getOneCard(true));
			playerCard.add(deck.getOneCard(true));
			p.setOneRoundCard(playerCard);
		}

		ArrayList<Card> dealerCard = new ArrayList<Card>();
		dealerCard.add(deck.getOneCard(true));
		dealerCard.add(deck.getOneCard(false));
		dealer.setOneRoundCard(dealerCard);
		System.out.print("Dealer's face up card is ");
		dealer.getOneRoundCard().get(1).printCard();
	}

	private void ask_each_player_about_hits() {

		for (Player p : players) {
			boolean hit = false;
			ArrayList<Card> playerCard = new ArrayList<Card>();
			System.out.print(p.get_name()+"'s cards now:");
			for(Card c : p.getOneRoundCard())
				c.printCard();
			do {
				hit = p.hit_me(this);
				if (hit) {
					playerCard = p.getOneRoundCard();
					playerCard.add(deck.getOneCard(true));
					System.out.print("Hit!" + p.get_name() + "'s cards now:");
					for (Card c : playerCard)
						c.printCard();
					p.setOneRoundCard(playerCard);
					if (p.getTotalValue() > 21)
						hit = false;
				} else {
					System.out.println("Pass hit!");
				}
			} while (hit);
			System.out.println(p.get_name() + "'s hit is over!");
		}
	}

	private void ask_dealer_about_hits() {
		boolean hit = false;
		ArrayList<Card> dealerCard = new ArrayList<Card>();
		do {
			hit = dealer.hit_me(this);
			if (hit) {
				dealerCard = dealer.getOneRoundCard();
				dealerCard.add(deck.getOneCard(true));
				dealer.setOneRoundCard(dealerCard);
				if (dealer.getTotalValue() > 21)
					hit = false;
			}
		} while (hit);
		System.out.println("Dealer's hit is over!");
	}

	private void calculate_chips() {
		int pvalue, dvalue = dealer.getTotalValue();
		char result;

		System.out.print("Dealer's card value is " + dvalue + ", Cards:");
		dealer.printAllCard();
		for (int n = 0; n < players.length; n++) {
			Player p = players[n];
			pvalue = p.getTotalValue();
			System.out.print(p.get_name() + "'s Cards:");
			p.printAllCard();
			System.out.print(p.get_name() + "'s card value is " + pvalue);
			if (pvalue > 21) {
				if (dvalue > 21)
					result = 'D';
				else
					result = 'L';
			} else {
				if (dvalue > 21)
					result = 'W';
				else if (dvalue > pvalue)
					result = 'L';
				else if (dvalue == pvalue)
					result = 'D';
				else
					result = 'W';
			}
			switch (result) {
			case 'L':
				p.increase_chips(-pos_betArray[n]);
				System.out.print(
						", Loss " + pos_betArray[n] + " Chips, the Chips now is:" + p.get_current_chips() + "\n");
				break;
			case 'W':
				p.increase_chips(pos_betArray[n]);
				System.out
						.print(", Get " + pos_betArray[n] + " Chips, the Chips now is:" + p.get_current_chips() + "\n");
				break;
			case 'D':
				System.out.print(", Chips have no change! The Chips now is:" + p.get_current_chips() + "\n");
				break;
			}

		}
	}
	
	public int[] get_players_bet(){
		return pos_betArray;
	}

	public void play() {
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}

}
