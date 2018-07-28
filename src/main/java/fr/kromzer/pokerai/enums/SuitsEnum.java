package fr.kromzer.pokerai.enums;

public enum SuitsEnum {
	DIAMOND(0, "D"), CLUB(1, "C"), HEART(2, "H"), SPADE(3, "S");

	/** Suit's value. */
	private int value;

	/** Suit's letter. */
	private String letter;

	private SuitsEnum(int value, String letter) {
		this.value = value;
		this.letter = letter;
	}

	/**
	 * Get suit value.
	 * @return the suit's value
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * Get suit letter.
	 * @return the suit's letter
	 */
	public String getLetter() {
		return this.letter;
	}

	/**
	 * Get the value from the SuitsEnum letter.
	 * @param letter D for Diamond, C for Club, H for Heart, S for Spade
	 * @return the corresponding value
	 */
	public static int getValueByLetter(String letter) {
		switch (letter) {
			case "D":
				return DIAMOND.getValue();
			case "C":
				return CLUB.getValue();
			case "H":
				return HEART.getValue();
			case "S":
				return SPADE.getValue();
			default:
				return -1;
		}
	}
}
