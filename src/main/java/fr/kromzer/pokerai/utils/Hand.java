package fr.kromzer.pokerai.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import fr.kromzer.pokerai.enums.HandsEnum;
import fr.kromzer.pokerai.enums.RanksEnum;

public class Hand {
	
	/** Logger. */
	private static final Logger logger = Logger.getLogger(Hand.class);

	/** Cards in the hand. */
	private final List<Card> cards;

	/** Cards as binary grouped by suit. */
	private final int[] binaryCards;

	/** List holding figure rank cards. */
	private final RanksEnum[] figureRankCards;

	/** The highest cards of the hand (not including figureRankCards). */
	private int highestCards;

	/** Hand strength. */
	private HandsEnum handStrength;

	/** Binary sum card. */
	private int binarySumCard;

	private static final int[] possibleStraights = new int[] { 0b11111000000000, 0b01111100000000, 0b00111110000000,
			0b00011111000000, 0b00001111100000, 0b00000111110000, 0b00000011111000, 0b00000001111100, 0b00000000111110,
			0b00000000011111, };

	public Hand(List<Card> cards) {
		this.figureRankCards = new RanksEnum[5];
		this.cards = new ArrayList<>();
		this.binaryCards = new int[4];
		for (int i = 0; i < cards.size(); i++) {
			final Card card = cards.get(i);
			this.cards.add(card);
			final int binaryValue = card.getRank().getValue();
			this.binaryCards[card.getSuit().getValue()] += binaryValue;
		}
	}

	public void addAll(List<Card> cards) {
		for (int i = 0; i < cards.size(); i++) {
			final Card card = cards.get(i);
			this.cards.add(card);
			final int binaryValue = card.getRank().getValue();
			this.binaryCards[card.getSuit().getValue()] += binaryValue;
		}
	}

	public void add(Card card) {
		this.cards.add(card);
		final int binaryValue = card.getRank().getValue();
		this.binaryCards[card.getSuit().getValue()] += binaryValue;
	}

	/**
	 * Get the strongest hand doable.
	 * @param cards list of cards to analyze
	 * @return the strongest hand
	 */
	private void computeHandValue() {
		computeHandValue(null);
	}

	/**
	 * Get the strongest hand doable.
	 * @param cards list of cards to analyze
	 * @return the strongest hand
	 */
	private void computeHandValue(HandsEnum otherHand) {
		this.highestCards = 0;

		if (this.isStraightFlushBinary()) {
			this.handStrength = HandsEnum.STRAIGHT_FLUSH;
		}
		else if ((otherHand == null || otherHand.getValue() <= HandsEnum.FOUR_OF_A_KIND.getValue())
				&& this.isFOAKBinary()) {
			this.handStrength = HandsEnum.FOUR_OF_A_KIND;
		}
		else if ((otherHand == null || otherHand.getValue() <= HandsEnum.FULL_HOUSE.getValue()) && this.isFullHouse()) {
			this.handStrength = HandsEnum.FULL_HOUSE;
		}
		else if ((otherHand == null || otherHand.getValue() <= HandsEnum.FLUSH.getValue()) && this.isFlushBinary()) {
			this.handStrength = HandsEnum.FLUSH;
		}
		else if ((otherHand == null || otherHand.getValue() <= HandsEnum.STRAIGHT.getValue())
				&& this.isStraightBinary()) {
			this.handStrength = HandsEnum.STRAIGHT;
		}
		else if ((otherHand == null || otherHand.getValue() <= HandsEnum.THREE_OF_A_KIND.getValue())
				&& this.isTOAKBinary()) {
			this.handStrength = HandsEnum.THREE_OF_A_KIND;
		}
		else if ((otherHand == null || otherHand.getValue() <= HandsEnum.TWO_PAIRS.getValue())
				&& this.isDoublePairBinary()) {
			this.handStrength = HandsEnum.TWO_PAIRS;
		}
		else if ((otherHand == null || otherHand.getValue() <= HandsEnum.PAIR.getValue()) && this.isPairBinary()) {
			this.handStrength = HandsEnum.PAIR;
		}
		else {
			this.handStrength = HandsEnum.HIGH_CARD;
			int count = 0;
			for (int i = 14; i >= 0 && count < 5; i--) {
				if ((this.getBinarySumCard() >> i & 1) == 1) {
					this.figureRankCards[count] = RanksEnum.getFromValue(14 - (14 - i - 1));
					count++;
				}
			}
		}
	}

	/**
	 * Check if there's a straight flush using binary method.
	 * @return true if there is, false otherwise
	 */
	private boolean isStraightFlushBinary() {
		for (int j = 0; j < this.binaryCards.length - 1; j++) {
			for (int i = 0; i < possibleStraights.length; i++) {
				if ((this.binaryCards[j] & possibleStraights[i]) == possibleStraights[i]) {
					this.figureRankCards[0] = RanksEnum.getFromValue(14 - i);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Check if there's a straight using binary method.
	 * @return true if there is, false otherwise
	 */
	private boolean isStraightBinary() {
		for (int i = 0; i < possibleStraights.length; i++) {
			if ((this.getBinarySumCard() & possibleStraights[i]) == possibleStraights[i]) {
				this.figureRankCards[0] = RanksEnum.getFromValue(14 - i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Check either we can make a flush with the cards or not.
	 * @return true if there is a flush, false otherwise
	 */
	private boolean isFlushBinary() {
		for (int i = 0; i < 4; i++) {
			if (Card.countSetBits((this.binaryCards[i])) >= 5) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Compute flush figure cards in case of tied.
	 */
	private void computeFlushFigureCards() {
		for (int i = 0; i < 4; i++) {
			if (Card.countSetBits((this.binaryCards[i])) >= 5) {
				int count = 0;
				for (int j = 14; j >= 0; j--) {
					if ((this.binaryCards[i] >> j & 1) == 1 && count < 5) {
						this.figureRankCards[count] = RanksEnum.getFromValue(14 - (14 - j - 1));
						count++;
					}
					if (count == 5) {
						return;
					}
				}
			}
		}
	}

	/**
	 * Check either we can make a FOAK with the cards or not.
	 * @return true if there is a FOAK, false otherwise
	 */
	private boolean isFOAKBinary() {
		final int value = (this.binaryCards[0] & this.binaryCards[1] & this.binaryCards[2] & this.binaryCards[3]);

		if (value > 0) {
			this.figureRankCards[0] = RanksEnum.getRankFromBinary(value);
		}
		return (value != 0);
	}

	/**
	 * Compute high card out of the FOAK.
	 */
	private void computeFOAKFigureCards() {
		for (int i = 14; i >= 0; i--) {
			if ((this.getBinarySumCard() >> i & 1) == 1
					&& !Arrays.asList(this.figureRankCards).contains(RanksEnum.getFromValue(14 - (13 - i)))) {
				this.highestCards += RanksEnum.getBinaryValue(i);
				break;
			}
		}
	}

	/**
	 * Check either we can make a full house with the cards or not.
	 * @return true if there is a full house, false otherwise
	 */
	private boolean isFullHouse() {
		RanksEnum toak = null;
		RanksEnum pair = null;

		final int combination = (this.binaryCards[0] & this.binaryCards[1] & this.binaryCards[2])
				| (this.binaryCards[0] & this.binaryCards[1] & this.binaryCards[3])
				| (this.binaryCards[0] & this.binaryCards[2] & this.binaryCards[3])
				| (this.binaryCards[1] & this.binaryCards[2] & this.binaryCards[3]);

		toak = getTOAKforFullHouse(combination);
		final int combination2 = (((this.binaryCards[0] | this.binaryCards[1])
				& (this.binaryCards[2] | this.binaryCards[3]))
				| ((this.binaryCards[2] | this.binaryCards[1]) & (this.binaryCards[0] | this.binaryCards[3])));

		pair = getPairForFullHouse(combination2, toak);

		if (toak != null && pair != null) {
			this.figureRankCards[0] = toak;
			this.figureRankCards[1] = pair;
			return true;
		}
		return false;
	}

	/**
	 * Get the foak RanksEnum for full house.
	 * @param combination the combination result
	 * @return the RanksEnum
	 */
	private RanksEnum getTOAKforFullHouse(int combination) {
		if (combination != 0) {
			for (int i = 14; i >= 1; i--) {
				if ((combination >> i & 1) == 1) {
					return RanksEnum.getFromValue(i + 1);
				}
			}
		}

		return null;
	}

	/**
	 * Get the pair RanksEnum for full house.
	 * @param combination the combination result
	 * @param toak the toak RanksEnum (so we don't return a pair based on a toak)
	 * @return the RanksEnum
	 */
	private RanksEnum getPairForFullHouse(int combination, RanksEnum toak) {
		if (combination > 0) {
			for (int i = 14; i >= 1; i--) {
				if ((combination >> i & 1) == 1 && RanksEnum.getFromValue(i + 1) != toak) {
					return RanksEnum.getFromValue(i + 1);
				}
			}
		}

		return null;
	}

	/**
	 * Check either we can make a TOAK with the cards or not.
	 * @return true if there is a TOAK, false otherwise
	 */
	private boolean isTOAKBinary() {
		final int combination = (this.binaryCards[0] & this.binaryCards[1] & this.binaryCards[2])
				| (this.binaryCards[0] & this.binaryCards[1] & this.binaryCards[3])
				| (this.binaryCards[0] & this.binaryCards[2] & this.binaryCards[3])
				| (this.binaryCards[1] & this.binaryCards[2] & this.binaryCards[3]);

		if (combination > 0) {
			for (int i = 14; i >= 1; i--) {
				if ((combination >> i & 1) == 1) {
					this.figureRankCards[0] = RanksEnum.getFromValue(i + 1);
					break;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * Compute high card out of the TOAK.
	 */
	private void computeTOAKFigureCards() {
		int count = 0;
		for (int i = 14; i >= 0 && count < 2; i--) {
			if ((this.getBinarySumCard() >> i & 1) == 1
					&& !Arrays.asList(this.figureRankCards).contains(RanksEnum.getFromValue(14 - (13 - i)))) {
				this.highestCards += RanksEnum.getBinaryValue(i);
				count++;
			}
		}
	}

	/**
	 * Check either we can make a double pair with the cards or not.
	 * @return true if there is a double pair, false otherwise
	 */
	private boolean isDoublePairBinary() {
		final int combination = (((this.binaryCards[0] | this.binaryCards[1])
				& (this.binaryCards[2] | this.binaryCards[3]))
				| ((this.binaryCards[2] | this.binaryCards[1]) & (this.binaryCards[0] | this.binaryCards[3])));

		int count = 0;
		if (combination > 0) {
			for (int i = 14; i >= 1 && count < 2; i--) {
				if ((combination >> i & 1) == 1) {
					this.figureRankCards[count] = RanksEnum.getFromValue(i + 1);
					count++;
				}
			}
		}
		return (count == 2);
	}

	/**
	 * Compute high card out of the double pair.
	 */
	private void computeDoublePairFigureCards() {
		for (int i = 14; i >= 0; i--) {
			if ((this.getBinarySumCard() >> i & 1) == 1
					&& !Arrays.asList(this.figureRankCards).contains(RanksEnum.getFromValue(14 - (13 - i)))) {
				this.highestCards += RanksEnum.getBinaryValue(i);
				break;
			}
		}
	}

	/**
	 * Check either we can make a double pair with the cards or not.
	 * @return true if there is a double pair, false otherwise
	 */
	private boolean isPairBinary() {
		final int combination = (((this.binaryCards[0] | this.binaryCards[1])
				& (this.binaryCards[2] | this.binaryCards[3]))
				| ((this.binaryCards[2] | this.binaryCards[1]) & (this.binaryCards[0] | this.binaryCards[3])));

		if (combination > 0) {
			for (int i = 14; i >= 1; i--) {
				if ((combination >> i & 1) == 1) {
					this.figureRankCards[0] = RanksEnum.getFromValue(i + 1);
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * Compute high card out of the pair.
	 */
	private void computePairFigureCards() {
		int count = 0;
		for (int i = 14; i >= 0 && count < 3; i--) {
			if ((this.getBinarySumCard() >> i & 1) == 1
					&& !Arrays.asList(this.figureRankCards).contains(RanksEnum.getFromValue(14 - (13 - i)))) {
				this.highestCards += RanksEnum.getBinaryValue(i);
				count++;
			}
		}
	}

	/**
	 * Compare two hands.
	 * @param otherHand the other hand to compare with
	 * @return 1 if this > otherHand, 0 if this == otherHand, -1 if this < otherHand
	 */
	public int compareTo(Hand otherHand) {
		int result = 0;
		
		try {
			this.computeHandValue();
			otherHand.computeHandValue(this.getHandStrength());
	
			if (otherHand.getHandStrength().getValue() > this.getHandStrength().getValue()) {
				result = -1;
			}
			else if (otherHand.getHandStrength().getValue() < this.getHandStrength().getValue()) {
				result = 1;
			}
			else {
				// Hands are equals, we need to check more specifically
				this.computeFigureAndHighCards();
				otherHand.computeFigureAndHighCards();
	
				result = compareFigureRankCards(otherHand.getFigureRankCards());
	
				if (result == 0) {
					result = compareHighestCards(otherHand.getHighestCards());
				}
			}
		}
		catch(NullPointerException e) {
			logger.debug(this.toString());
			logger.debug(otherHand.toString());
		}
		return result;
	}

	/**
	 * Compare figureRankCards.
	 * @param otherFigureRanksCards the figureRanksCards from the other hand
	 * @return 1, 0 or -1 depending on result
	 */
	private int compareFigureRankCards(RanksEnum[] otherFigureRankCards) {
		int result = 0;
		for (int count = 0; count < 5 && result == 0; count++) {
			if (this.figureRankCards[count] == null) {
				break;
			}
			if (this.figureRankCards[count].getValue() > otherFigureRankCards[count].getValue()) {
				result = 1;
			}

			if (this.figureRankCards[count].getValue() < otherFigureRankCards[count].getValue()) {
				result = -1;
			}
		}

		return result;
	}

	/**
	 * Compare highest cards.
	 * @param otherFigureHighestCards the highestCards from the other hand
	 * @return 1, 0 or -1 depending on result
	 */
	private int compareHighestCards(int otherFigureHighestCards) {
		int result = 0;
		if (this.highestCards > otherFigureHighestCards) {
			result = 1;
		}

		if (this.highestCards < otherFigureHighestCards) {
			result = -1;
		}
		return result;
	}

	/**
	 * Method analyzing top cards out of the hand rank.
	 */
	private void computeFigureAndHighCards() {
		if (this.handStrength == HandsEnum.FOUR_OF_A_KIND) {
			this.computeFOAKFigureCards();
		}
		else if (this.handStrength == HandsEnum.FLUSH) {
			this.computeFlushFigureCards();
		}
		else if (this.handStrength == HandsEnum.THREE_OF_A_KIND) {
			this.computeTOAKFigureCards();
		}
		else if (this.handStrength == HandsEnum.TWO_PAIRS) {
			this.computeDoublePairFigureCards();
		}
		else if (this.handStrength == HandsEnum.PAIR) {
			this.computePairFigureCards();
		}
	}

	/**
	 * Getter for cards.
	 * @return cards
	 */
	public List<Card> getCards() {
		return this.cards;
	}

	public HandsEnum getHandStrength() {
		if (this.handStrength == null) {
			computeHandValue();
		}
		return this.handStrength;
	}

	/**
	 * @return the highestCards
	 */
	private int getHighestCards() {
		return this.highestCards;
	}

	private final RanksEnum[] getFigureRankCards() {
		return this.figureRankCards;
	}

	/**
	 * Getter for binary sum card.
	 * @return binary sum card
	 */
	private final int getBinarySumCard() {
		if (this.binarySumCard == 0) {
			this.binarySumCard = this.binaryCards[0] | this.binaryCards[1] | this.binaryCards[2] | this.binaryCards[3];
		}
		return this.binarySumCard;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		Card.sortCards(this.cards);
		this.cards.forEach(c -> builder.append(c.getRank().getStr() + c.getSuit().getLetter()));

		builder.append("=" + this.handStrength);
		return builder.toString();
	}
}
