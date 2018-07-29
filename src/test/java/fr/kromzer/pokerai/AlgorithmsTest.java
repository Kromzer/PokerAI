package fr.kromzer.pokerai;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.kromzer.pokerai.algorithms.Algorithms;
import fr.kromzer.pokerai.enums.RanksEnum;
import fr.kromzer.pokerai.enums.SuitsEnum;
import fr.kromzer.pokerai.utils.Card;
import fr.kromzer.pokerai.utils.Hand;

public class AlgorithmsTest {
	@Test
	public void testComputeHandStrength() throws Exception {
		final List<Card> cards = new ArrayList<>();
		final Card card = new Card(RanksEnum.ACE, SuitsEnum.DIAMOND);
		final Card card2 = new Card(RanksEnum.QUEEN, SuitsEnum.CLUB);

		cards.add(card);
		cards.add(card2);

		final Hand myHand = new Hand(cards);
		final List<Card> flop = new ArrayList<>();
		flop.add(new Card(RanksEnum.THREE, SuitsEnum.HEART));
		flop.add(new Card(RanksEnum.FOUR, SuitsEnum.CLUB));
		flop.add(new Card(RanksEnum.JACK, SuitsEnum.HEART));

		final float result = Algorithms.computeHandStrength(myHand, flop, 1);
		assertEquals("0,585", String.format("%.3f", result));
	}

	@Test
	public void testEHS() {
		final List<Card> cards = new ArrayList<>();
		final Card card = new Card(RanksEnum.ACE, SuitsEnum.DIAMOND);
		final Card card2 = new Card(RanksEnum.QUEEN, SuitsEnum.CLUB);

		cards.add(card);
		cards.add(card2);

		final Hand myHand = new Hand(cards);
		final List<Card> flop = new ArrayList<>();
		flop.add(new Card(RanksEnum.THREE, SuitsEnum.HEART));
		flop.add(new Card(RanksEnum.FOUR, SuitsEnum.CLUB));
		flop.add(new Card(RanksEnum.JACK, SuitsEnum.HEART));

		final float res = Algorithms.computeEHS(myHand, flop, 1);
		assertEquals(0.511, res, 0.0009);
	}

	@Test
	public void testGetChenScore() {
		final Card card = new Card(RanksEnum.TWO, SuitsEnum.DIAMOND);
		final Card card2 = new Card(RanksEnum.SEVEN, SuitsEnum.CLUB);

		final Card card3 = new Card(RanksEnum.ACE, SuitsEnum.DIAMOND);
		final Card card4 = new Card(RanksEnum.KING, SuitsEnum.DIAMOND);

		final Card card5 = new Card(RanksEnum.FIVE, SuitsEnum.DIAMOND);
		final Card card6 = new Card(RanksEnum.SEVEN, SuitsEnum.DIAMOND);

		final Card card7 = new Card(RanksEnum.ACE, SuitsEnum.DIAMOND);
		final Card card8 = new Card(RanksEnum.ACE, SuitsEnum.CLUB);

		final Card card9 = new Card(RanksEnum.FIVE, SuitsEnum.DIAMOND);
		final Card card10 = new Card(RanksEnum.FIVE, SuitsEnum.CLUB);

		final Card card11 = new Card(RanksEnum.TWO, SuitsEnum.DIAMOND);
		final Card card12 = new Card(RanksEnum.TWO, SuitsEnum.CLUB);

		final Card card13 = new Card(RanksEnum.TWO, SuitsEnum.DIAMOND);
		final Card card14 = new Card(RanksEnum.SIX, SuitsEnum.CLUB);

		assertEquals(-1, Algorithms.getChenScore(card, card2));
		assertEquals(12, Algorithms.getChenScore(card3, card4));
		assertEquals(6, Algorithms.getChenScore(card5, card6));
		assertEquals(20, Algorithms.getChenScore(card7, card8));
		assertEquals(6, Algorithms.getChenScore(card9, card10));
		assertEquals(5, Algorithms.getChenScore(card11, card12));
		assertEquals(-1, Algorithms.getChenScore(card13, card14));
	}
}
