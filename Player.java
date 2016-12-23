
public class Player extends Person {

	private String name;
	private int chips;
	private int bet = 0;

	public Player(String name, int chips) {
		this.name = name;
		this.chips = chips;
	}

	public String get_name() {
		return name;
	}

	public int make_bet() {
		bet = (int) (1000 * 0.02);
		if (chips < bet)
			bet = chips;
		return bet;
	}

	public boolean hit_me(Table tbl) {

		if (getTotalValue() > 16)
			return false;
		else
			return true;

	}

	public int get_current_chips() {
		return chips;
	}

	public void increase_chips(int diff) {
		chips += diff;
	}

	public void say_hello() {
		System.out.println("Hello, I am " + name + ".");
		System.out.println("I have " + chips + " chips.");
	}

}
