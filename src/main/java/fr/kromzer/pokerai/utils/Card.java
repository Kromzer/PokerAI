package fr.kromzer.pokerai.utils;

import java.util.List;

import fr.kromzer.pokerai.enums.RanksEnum;
import fr.kromzer.pokerai.enums.SuitsEnum;

public class Card {
	/** A card has a rank. */
	RanksEnum rank;

	/** A card has a suit. */
	SuitsEnum suit;

	/**
	 * Default constructor.
	 * @param rank the card's rank
	 * @param suit the card's suit
	 */
	public Card(RanksEnum rank, SuitsEnum suit) {
		this.rank = rank;
		this.suit = suit;
	}

	/**
	 * Getter for rank.
	 * @return the card's rank
	 */
	public final RanksEnum getRank() {
		return this.rank;
	}

	/**
	 * Getter for suit.
	 * @return the card's suit
	 */
	public final SuitsEnum getSuit() {
		return this.suit;
	}

	/**
	 * Setter for the card's suit.
	 * @param suit the suit too set
	 */
	public final void setSuit(SuitsEnum suit) {
		this.suit = suit;
	}

	/**
	 * Sort cards (only used for Hand.toString() method).
	 * @param cards the cards to sort
	 */
	public static void sortCards(List<Card> cards) {
		cards.sort((Card c1, Card c2) -> {
			if (c1.getRank().getValue() - c2.getRank().getValue() != 0) {
				return c1.getRank().getValue() - c2.getRank().getValue();
			}
			else {
				return c1.getSuit().getValue() - c2.getSuit().getValue();
			}
		});
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Card)) {
			return false;
		}
		final Card otherCard = (Card) obj;
		return (otherCard.getRank().equals(this.getRank()) && otherCard.getSuit().equals(this.getSuit()));
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.rank.hashCode();
		result = 31 * result + this.suit.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return this.getRank().getStr() + "" + this.getSuit().toString().substring(0, 1);
	}

	/**
	 * Helper method to count set bits.
	 * @param n the int to count the set bits from
	 * @return number of set bits
	 */
	public static int countSetBits(int n) {
		int count = 0;
		// We don't count last bit because it's for ACE as One
		if ((n & 1) == 1) {
			n -= 0b1;
		}
		while (n > 0) {
			n &= (n - 1);
			count++;
		}
		return count;
	}
}
