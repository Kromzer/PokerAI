package fr.kromzer.pokerai.enums;

/**
 * Enum used to compare two hands.
 * @author Kromzer
 */
public enum HandsEnum {
	HIGH_CARD(0), PAIR(1), TWO_PAIRS(2), THREE_OF_A_KIND(3), STRAIGHT(4), FLUSH(5), FULL_HOUSE(6), FOUR_OF_A_KIND(
			7), STRAIGHT_FLUSH(8);

	/** Hand value, for comparison purpose. */
	private int value;

	/**
	 * Main constructor.
	 * @param value the value of the hand.
	 */
	HandsEnum(int value) {
		this.value = value;
	}

	/**
	 * Getter for value.
	 * @return value
	 */
	public int getValue() {
		return this.value;
	}

}
