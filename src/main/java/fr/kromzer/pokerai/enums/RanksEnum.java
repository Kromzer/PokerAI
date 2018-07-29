package fr.kromzer.pokerai.enums;

public enum RanksEnum {
	TWO(2, "2", 1), THREE(4, "3", 1.5f), FOUR(8, "4", 2), FIVE(16, "5", 2.5f), SIX(32, "6", 3), SEVEN(64, "7",
			3.5f), EIGHT(128, "8", 4), NINE(256, "9", 4.5f), TEN(512, "10",
					5), JACK(1024, "J", 6), QUEEN(2048, "Q", 7), KING(4096, "K", 8), ACE(8193, "A", 10);

	/** Rank's value. */
	private Integer value;

	private String str;

	/** Rank Chen score. */
	private float chenScore;

	private RanksEnum(Integer value, String str, float chenScore) {
		this.value = value;
		this.str = str;
		this.chenScore = chenScore;
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
	 * Method used for Chen's score calculation.
	 * @param otherRank the rank of the other card
	 * @return the gap between the two
	 */
	public int getGap(RanksEnum otherRank) {
		final int add = this.getValue() + otherRank.getValue();

		int count = 0;
		boolean countStarted = false;
		for (int i = 14; i >= 0; i--) {
			if ((add >> i & 1) == 1) {
				if (!countStarted) {
					countStarted = true;
				}
				else {
					count++;
					break;
				}
			}
			if ((add >> i & 1) == 0 && countStarted) {
				count++;
			}
		}

		return count - 1;
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

	public final float getChenScore() {
		return this.chenScore;
	}
}
