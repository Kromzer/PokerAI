package fr.kromzer.pokerai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.kromzer.pokerai.enums.RanksEnum;
import fr.kromzer.pokerai.enums.SuitsEnum;
import fr.kromzer.pokerai.utils.Card;

public class CardTest {

	@Test
	public void testEquals() {
		final Card card1 = new Card(RanksEnum.KING, SuitsEnum.DIAMOND);
		final Card card2 = new Card(RanksEnum.KING, SuitsEnum.DIAMOND);
		final Card card3 = new Card(RanksEnum.QUEEN, SuitsEnum.DIAMOND);

		assertEquals(card1, card2);
		assertNotEquals(card1, card3);
	}

	@Test
	public void testSortCards() {
		final List<Card> cards = new ArrayList<>();
		final Card card1 = new Card(RanksEnum.TWO, SuitsEnum.DIAMOND);
		final Card card2 = new Card(RanksEnum.QUEEN, SuitsEnum.DIAMOND);
		final Card card3 = new Card(RanksEnum.FIVE, SuitsEnum.DIAMOND);
		final Card card4 = new Card(RanksEnum.EIGHT, SuitsEnum.DIAMOND);
		final Card card5 = new Card(RanksEnum.THREE, SuitsEnum.DIAMOND);

		cards.add(card1);
		cards.add(card2);
		cards.add(card3);
		cards.add(card4);
		cards.add(card5);

		Card.sortCards(cards);

		assertEquals(cards.get(0), card1);
		assertEquals(cards.get(1), card5);
		assertEquals(cards.get(2), card3);
		assertEquals(cards.get(3), card4);
		assertEquals(cards.get(4), card2);
	}
}
