package fr.kromzer.pokerai.algorithms;

import java.util.List;

import org.apache.log4j.Logger;

import fr.kromzer.pokerai.iterator.CombinationIterator;
import fr.kromzer.pokerai.utils.Card;
import fr.kromzer.pokerai.utils.Deck;
import fr.kromzer.pokerai.utils.Hand;

public class Algorithms {

	/** Logger. */
	private static final Logger logger = Logger.getLogger(Algorithms.class);

	private static final int INDEX_AHEAD = 0;
	private static final int INDEX_TIED = 1;
	private static final int INDEX_BEHIND = 2;

	/**
	 * Overriding default public constructor.
	 */
	private Algorithms() {

	}

	/**
	 * Compute hand Effective Hand Strength (EHS).
	 * @param hand the hand to compute the EHS
	 * @param flopCards cards of the flop
	 * @param nbOpponents the number of opponents to consider
	 * @return the EHS
	 */
	public static float computeEHS(Hand hand, List<Card> flopCards, int nbOpponents) {
		final float hs = computeHandStrength(hand, flopCards, nbOpponents);

		final float[][] hp = new float[3][3];
		final float[] hpTotal = new float[3];
		final float ppot2;
		final float npot2;
		int index;

		final Hand hand5 = new Hand(hand.getCards());
		hand5.addAll(flopCards);

		final Deck deck = new Deck();
		deck.removeCards(hand5.getCards());

		final CombinationIterator<Card> it = new CombinationIterator<>(deck.getCards(), 2);
		while (it.hasNext()) {
			final List<Card> cards2 = it.next();
			final Hand opHand = new Hand(flopCards);
			opHand.addAll(cards2);

			final int result = hand5.compareTo(opHand);

			final Deck newDeck = new Deck(deck);
			newDeck.removeCards(cards2);

			final CombinationIterator<Card> it2 = new CombinationIterator<>(newDeck.getCards(), 2);
			while (it2.hasNext()) {

				if (result == 1) {
					index = INDEX_AHEAD;
				}
				else if (result == 0) {
					index = INDEX_TIED;
				}
				else {
					index = INDEX_BEHIND;
				}

				hpTotal[index]++;

				final List<Card> newCards = it2.next();

				final Hand hand7 = new Hand(hand5.getCards());
				hand7.addAll(newCards);

				final Hand op7 = new Hand(opHand.getCards());
				op7.addAll(newCards);

				final int result2 = hand7.compareTo(op7);

				if (result2 == 1) {
					hp[index][INDEX_AHEAD]++;
				}
				else if (result2 == 0) {
					hp[index][INDEX_TIED]++;
				}
				else {
					hp[index][INDEX_BEHIND]++;
				}
			}
		}

		ppot2 = (hp[INDEX_BEHIND][INDEX_AHEAD] + (hp[INDEX_BEHIND][INDEX_TIED] / 2) + hp[INDEX_TIED][INDEX_AHEAD] / 2)
				/ (hpTotal[INDEX_BEHIND] + hpTotal[INDEX_TIED] / 2);
		npot2 = (hp[INDEX_AHEAD][INDEX_BEHIND] + hp[INDEX_AHEAD][INDEX_TIED] / 2 + hp[INDEX_TIED][INDEX_BEHIND] / 2)
				/ (hpTotal[INDEX_AHEAD] + hpTotal[INDEX_TIED] / 2);

		return hs * (1 - npot2) + (1 - hs) * ppot2;
	}

	/**
	 * Method computing the hand strength.
	 * @param myHand the hand to compute the hand strength
	 * @param flopCards cards of the flop
	 * @param nbOpponents the number of opponents to consider
	 * @return the hand strength
	 */
	public static float computeHandStrength(Hand myHand, List<Card> flopCards, int nbOpponents) {
		float ahead = 0;
		float tied = 0;
		float behind = 0;

		final Hand cpHand = new Hand(myHand.getCards());
		cpHand.addAll(flopCards);

		// We need to generate each possible opponent hand
		final Deck deck = new Deck();
		deck.removeCards(cpHand.getCards());
		deck.removeCards(flopCards);

		final CombinationIterator<Card> it = new CombinationIterator<>(deck.getCards(), 2);

		// For each one, we compute either it's a better, a worse, or an equivalent
		while (it.hasNext()) {
			final List<Card> cards = it.next();
			Hand opponentHand;
			opponentHand = new Hand(cards);

			opponentHand.addAll(flopCards);

			final int result = cpHand.compareTo(opponentHand);
			if (result == 1) {
				ahead++;
			}
			else if (result == -1) {
				behind++;
			}
			else {
				tied++;
			}
		}
		final float hs = (ahead + tied / 2) / (ahead + tied + behind);
		logger.info("Handstrength: " + (float) Math.pow(hs, nbOpponents));
		return (float) Math.pow(hs, nbOpponents);
	}

}
