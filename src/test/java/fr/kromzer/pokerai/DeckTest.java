package fr.kromzer.pokerai;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.kromzer.pokerai.enums.RanksEnum;
import fr.kromzer.pokerai.enums.SuitsEnum;
import fr.kromzer.pokerai.utils.Card;
import fr.kromzer.pokerai.utils.Deck;

public class DeckTest {
	@Test
	public void testConstructor() {
		final Deck deck = new Deck();
		assertEquals(52, deck.getCards().size());
	}

	@Test
	public void testRemoveCard() {
		final Deck deck = new Deck();
		assertEquals(52, deck.getCards().size());
		deck.removeCard(new Card(RanksEnum.ACE, SuitsEnum.CLUB));
		assertEquals(51, deck.getCards().size());
	}

	@Test
	public void testRemoveCards() {
		final Deck deck = new Deck();
		assertEquals(52, deck.getCards().size());
		deck.removeCard(new Card(RanksEnum.ACE, SuitsEnum.CLUB));
		// Should do nothing
		deck.removeCard(new Card(RanksEnum.ACE, SuitsEnum.CLUB));
		deck.removeCard(new Card(RanksEnum.ACE, SuitsEnum.DIAMOND));
		deck.removeCard(new Card(RanksEnum.TWO, SuitsEnum.HEART));
		assertEquals(49, deck.getCards().size());
	}
}
