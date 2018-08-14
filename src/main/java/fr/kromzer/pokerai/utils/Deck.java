package fr.kromzer.pokerai.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import fr.kromzer.pokerai.enums.RanksEnum;
import fr.kromzer.pokerai.enums.SuitsEnum;

public class Deck {

	/** A deck is just a list or stack or w/e of cards. */
	List<Card> cards;

	/** The default constructor. It generates all the 52 cards. */
	public Deck() {
		this.cards = new ArrayList<>();

		Arrays.asList(RanksEnum.values()).forEach(
				rank -> Arrays.asList(SuitsEnum.values()).forEach(suit -> this.cards.add(new Card(rank, suit))));
	}

	/**
	 * Copy constructor.
	 * @param deck new deck
	 */
	public Deck(Deck deck) {
		this.cards = new ArrayList<>();

		for (int i = 0; i < deck.getCards().size(); i++) {
			final Card card = deck.getCards().get(i);
			this.cards.add(card);
		}
	}

	/**
	 * Getter for cards
	 * @return the deck cards
	 */
	public final List<Card> getCards() {
		return this.cards;
	}

	/**
	 * Remove several cards from the deck.
	 * @param cards the cards to remove
	 */
	public void removeCards(List<Card> cards) {
		this.getCards().removeAll(cards);
	}

	/**
	 * Remove a card from the deck.
	 * @param card the card to remove
	 */
	public void removeCard(Card card) {
		this.cards.remove(card);
	}
	
	/**
	 * Shuffle cards.
	 */
	public void shuffleCards() {
		Collections.shuffle(cards);
	}
	
	/**
	 * Draw the top card.
	 * @return the top card
	 */
	public Card drawTopCard() {
		Card card = cards.get(0);
		cards.remove(0);
		return card;
	}
}
