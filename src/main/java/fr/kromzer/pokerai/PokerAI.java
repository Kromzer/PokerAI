package fr.kromzer.pokerai;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import fr.kromzer.pokerai.algorithms.Algorithms;
import fr.kromzer.pokerai.enums.RanksEnum;
import fr.kromzer.pokerai.enums.SuitsEnum;
import fr.kromzer.pokerai.utils.Card;
import fr.kromzer.pokerai.utils.Hand;

public class PokerAI {

	/** Logger. */
	private static final Logger logger = Logger.getLogger(PokerAI.class);

	public static void main(String[] args) {
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

		final long startTime = System.nanoTime();
		final float ehs = Algorithms.computeEHS(myHand, flop, 1);
		final long endTime = System.nanoTime();
		final long timeElapsed = endTime - startTime;
		logger.info("EHS: " + ehs);
		logger.info("Execution time in ms: " + timeElapsed / 1000000);
	}
}
