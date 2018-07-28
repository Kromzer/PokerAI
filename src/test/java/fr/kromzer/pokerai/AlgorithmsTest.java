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
}
