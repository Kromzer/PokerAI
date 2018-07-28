package fr.kromzer.pokerai.enums;

public enum RanksEnum {
	TWO(2, "2"), THREE(4, "3"), FOUR(8, "4"), FIVE(16, "5"), SIX(32, "6"), SEVEN(64, "7"), EIGHT(128, "8"), NINE(256,
			"9"), TEN(512, "10"), JACK(1024, "J"), QUEEN(2048, "Q"), KING(4096, "K"), ACE(8193, "A");

	/** Rank's value. */
	private Integer value;

	private String str;

	private RanksEnum(Integer value, String str) {
		this.value = value;
		this.str = str;
	}

	/**
	 * Get the binary value of a card.
	 * @param value the value of the card from 2 (TWO) to 14 (ACE)
	 * @return the binary value of the card
	 */
	public static int getBinaryValue(int value) {
		switch (value) {
			case 2:
				return TWO.value;
			case 3:
				return THREE.value;
			case 4:
				return FOUR.value;
			case 5:
				return FIVE.value;
			case 6:
				return SIX.value;
			case 7:
				return SEVEN.value;
			case 8:
				return EIGHT.value;
			case 9:
				return NINE.value;
			case 10:
				return TEN.value;
			case 11:
				return JACK.value;
			case 12:
				return QUEEN.value;
			case 13:
				return KING.value;
			case 14:
				return ACE.value - 1;
			default:
				return 0;
		}
	}

	/**
	 * Get the RanksEnum from the value.
	 * @param value the value to get the RanksEnum from
	 * @return the corresponding RanksEnum
	 */
	public static RanksEnum getFromValue(int value) {
		switch (value) {
			case 2:
				return TWO;
			case 3:
				return THREE;
			case 4:
				return FOUR;
			case 5:
				return FIVE;
			case 6:
				return SIX;
			case 7:
				return SEVEN;
			case 8:
				return EIGHT;
			case 9:
				return NINE;
			case 10:
				return TEN;
			case 11:
				return JACK;
			case 12:
				return QUEEN;
			case 13:
				return KING;
			case 14:
				return ACE;
			default:
				return null;
		}
	}

	/**
	 * Get the rank from the binary value.
	 * @param binary the binary value to get the RanksEnum from
	 * @return the RanksEnum
	 */
	public static RanksEnum getRankFromBinary(Integer binary) {
		RanksEnum result = null;
		if (binary == 2) {
			result = RanksEnum.TWO;
		}
		else if (binary == 4) {
			result = RanksEnum.THREE;
		}
		else if (binary == 8) {
			result = RanksEnum.FOUR;
		}
		else if (binary == 16) {
			result = RanksEnum.FIVE;
		}
		else if (binary == 32) {
			result = RanksEnum.SIX;
		}
		else if (binary == 64) {
			result = RanksEnum.SEVEN;
		}
		else if (binary == 128) {
			result = RanksEnum.EIGHT;
		}
		else if (binary == 256) {
			result = RanksEnum.NINE;
		}
		else if (binary == 512) {
			result = RanksEnum.TEN;
		}
		else if (binary == 1024) {
			result = RanksEnum.JACK;
		}
		else if (binary == 2048) {
			result = RanksEnum.QUEEN;
		}
		else if (binary == 4096) {
			result = RanksEnum.KING;
		}
		else if (binary == 8193) {
			result = RanksEnum.ACE;
		}

		return result;
	}

	/**
	 * Get rank value.
	 * @return the rank's value
	 */
	public Integer getValue() {
		return this.value;
	}

	/**
	 * Get the String representation of the RanksEnum.
	 * @return the RanksEnum String representation
	 */
	public String getStr() {
		return this.str;
	}
}
