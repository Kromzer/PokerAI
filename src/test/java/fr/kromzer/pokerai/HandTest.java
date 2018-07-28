package fr.kromzer.pokerai;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.kromzer.pokerai.enums.HandsEnum;
import fr.kromzer.pokerai.enums.RanksEnum;
import fr.kromzer.pokerai.enums.SuitsEnum;
import fr.kromzer.pokerai.utils.Card;
import fr.kromzer.pokerai.utils.Hand;

public class HandTest {
	@Test
	public void testIsSraightFlush() throws Exception {
		final Card card1 = new Card(RanksEnum.ACE, SuitsEnum.CLUB);
		final Card card2 = new Card(RanksEnum.TEN, SuitsEnum.CLUB);
		final Card card3 = new Card(RanksEnum.KING, SuitsEnum.CLUB);
		final Card card4 = new Card(RanksEnum.QUEEN, SuitsEnum.CLUB);
		final Card card5 = new Card(RanksEnum.NINE, SuitsEnum.CLUB);

		final Card card6 = new Card(RanksEnum.JACK, SuitsEnum.CLUB);
		final Card card7 = new Card(RanksEnum.TWO, SuitsEnum.CLUB);

		final List<Card> river = new ArrayList<>();
		river.add(card1);
		river.add(card2);
		river.add(card3);
		river.add(card4);
		river.add(card5);

		final List<Card> myCards = new ArrayList<>();
		myCards.addAll(river);
		myCards.add(card6);
		myCards.add(card7);

		final Hand hand = new Hand(myCards);

		assertEquals(HandsEnum.STRAIGHT_FLUSH, hand.getHandStrength());
	}

	@Test
	public void testCompareToWhenPairs() throws Exception {
		final Card p1 = new Card(RanksEnum.ACE, SuitsEnum.CLUB);
		final Card p2 = new Card(RanksEnum.TEN, SuitsEnum.DIAMOND);

		final Card o1 = new Card(RanksEnum.QUEEN, SuitsEnum.CLUB);
		final Card o2 = new Card(RanksEnum.JACK, SuitsEnum.CLUB);

		final Card f1 = new Card(RanksEnum.ACE, SuitsEnum.DIAMOND);
		final Card f2 = new Card(RanksEnum.QUEEN, SuitsEnum.DIAMOND);
		final Card f3 = new Card(RanksEnum.SIX, SuitsEnum.CLUB);

		final List<Card> pCards = new ArrayList<>();
		pCards.add(p1);
		pCards.add(p2);

		final List<Card> oCards = new ArrayList<>();
		oCards.add(o1);
		oCards.add(o2);

		final List<Card> fCards = new ArrayList<>();
		fCards.add(f1);
		fCards.add(f2);
		fCards.add(f3);

		pCards.addAll(fCards);
		oCards.addAll(fCards);

		final Hand ph = new Hand(pCards);
		final Hand oh = new Hand(oCards);

		assertEquals(1, ph.compareTo(oh));
		assertEquals(-1, oh.compareTo(ph));
	}

	@Test
	public void testCompareTwoStraight() throws Exception {
		final Card card1 = new Card(RanksEnum.ACE, SuitsEnum.CLUB);
		final Card card2 = new Card(RanksEnum.EIGHT, SuitsEnum.CLUB);
		final Card card3 = new Card(RanksEnum.ACE, SuitsEnum.DIAMOND);
		final Card card4 = new Card(RanksEnum.FIVE, SuitsEnum.CLUB);
		final Card card5 = new Card(RanksEnum.FOUR, SuitsEnum.DIAMOND);

		final Card card6 = new Card(RanksEnum.THREE, SuitsEnum.SPADE);
		final Card card7 = new Card(RanksEnum.TWO, SuitsEnum.CLUB);

		final Card card8 = new Card(RanksEnum.SIX, SuitsEnum.SPADE);
		final Card card9 = new Card(RanksEnum.SEVEN, SuitsEnum.SPADE);

		final List<Card> river = new ArrayList<>();
		river.add(card1);
		river.add(card2);
		river.add(card3);
		river.add(card4);
		river.add(card5);

		final List<Card> myCards = new ArrayList<>();
		myCards.addAll(river);
		myCards.add(card6);
		myCards.add(card7);

		final List<Card> opCards = new ArrayList<>();
		opCards.addAll(river);
		opCards.add(card8);
		opCards.add(card9);

		final Hand hand = new Hand(myCards);
		final Hand opHand = new Hand(opCards);

		assertEquals(-1, hand.compareTo(opHand));
		assertEquals(HandsEnum.STRAIGHT, hand.getHandStrength());
		assertEquals(HandsEnum.STRAIGHT, opHand.getHandStrength());
	}

	@Test
	public void testCompareTwoFlush() throws Exception {
		final Card card1 = new Card(RanksEnum.ACE, SuitsEnum.CLUB);
		final Card card2 = new Card(RanksEnum.TEN, SuitsEnum.CLUB);
		final Card card3 = new Card(RanksEnum.ACE, SuitsEnum.DIAMOND);
		final Card card4 = new Card(RanksEnum.FIVE, SuitsEnum.CLUB);
		final Card card5 = new Card(RanksEnum.NINE, SuitsEnum.CLUB);

		final Card card6 = new Card(RanksEnum.THREE, SuitsEnum.CLUB);
		final Card card7 = new Card(RanksEnum.TWO, SuitsEnum.CLUB);

		final Card card8 = new Card(RanksEnum.SIX, SuitsEnum.CLUB);
		final Card card9 = new Card(RanksEnum.SEVEN, SuitsEnum.CLUB);

		final List<Card> river = new ArrayList<>();
		river.add(card1);
		river.add(card2);
		river.add(card3);
		river.add(card4);
		river.add(card5);

		final List<Card> myCards = new ArrayList<>();
		myCards.addAll(river);
		myCards.add(card6);
		myCards.add(card7);

		final List<Card> opCards = new ArrayList<>();
		opCards.addAll(river);
		opCards.add(card8);
		opCards.add(card9);

		final Hand hand = new Hand(myCards);

		final Hand opHand = new Hand(opCards);

		assertEquals(-1, hand.compareTo(opHand));
		assertEquals(HandsEnum.FLUSH, hand.getHandStrength());
		assertEquals(HandsEnum.FLUSH, opHand.getHandStrength());
	}

	@Test
	public void testCompareTwoFOAK() throws Exception {
		final Card card1 = new Card(RanksEnum.SIX, SuitsEnum.CLUB);
		final Card card2 = new Card(RanksEnum.TEN, SuitsEnum.CLUB);
		final Card card3 = new Card(RanksEnum.SIX, SuitsEnum.DIAMOND);
		final Card card4 = new Card(RanksEnum.TEN, SuitsEnum.SPADE);
		final Card card5 = new Card(RanksEnum.SIX, SuitsEnum.HEART);

		final Card card6 = new Card(RanksEnum.ACE, SuitsEnum.CLUB);
		final Card card7 = new Card(RanksEnum.SIX, SuitsEnum.SPADE);

		final Card card8 = new Card(RanksEnum.TEN, SuitsEnum.HEART);
		final Card card9 = new Card(RanksEnum.TEN, SuitsEnum.DIAMOND);

		final List<Card> river = new ArrayList<>();
		river.add(card1);
		river.add(card2);
		river.add(card3);
		river.add(card4);
		river.add(card5);

		final List<Card> myCards = new ArrayList<>();
		myCards.addAll(river);
		myCards.add(card6);
		myCards.add(card7);

		final List<Card> opCards = new ArrayList<>();
		opCards.addAll(river);
		opCards.add(card8);
		opCards.add(card9);

		final Hand hand = new Hand(myCards);

		final Hand opHand = new Hand(opCards);

		assertEquals(HandsEnum.FOUR_OF_A_KIND, hand.getHandStrength());
		assertEquals(HandsEnum.FOUR_OF_A_KIND, opHand.getHandStrength());
		assertEquals(-1, hand.compareTo(opHand));
	}

	@Test
	public void testCompareTwoFullHouse() throws Exception {
		final Card card1 = new Card(RanksEnum.ACE, SuitsEnum.SPADE);
		final Card card2 = new Card(RanksEnum.TEN, SuitsEnum.CLUB);
		final Card card3 = new Card(RanksEnum.SIX, SuitsEnum.DIAMOND);
		final Card card4 = new Card(RanksEnum.SIX, SuitsEnum.HEART);
		final Card card5 = new Card(RanksEnum.SEVEN, SuitsEnum.HEART);

		final Card card6 = new Card(RanksEnum.ACE, SuitsEnum.CLUB);
		final Card card7 = new Card(RanksEnum.SIX, SuitsEnum.SPADE);

		final Card card8 = new Card(RanksEnum.SEVEN, SuitsEnum.SPADE);
		final Card card9 = new Card(RanksEnum.SEVEN, SuitsEnum.CLUB);

		final List<Card> river = new ArrayList<>();
		river.add(card1);
		river.add(card2);
		river.add(card3);
		river.add(card4);
		river.add(card5);

		final List<Card> myCards = new ArrayList<>();
		myCards.addAll(river);
		myCards.add(card6);
		myCards.add(card7);

		final List<Card> opCards = new ArrayList<>();
		opCards.addAll(river);
		opCards.add(card8);
		opCards.add(card9);

		final Hand hand = new Hand(myCards);

		final Hand opHand = new Hand(opCards);

		assertEquals(HandsEnum.FULL_HOUSE, hand.getHandStrength());
		assertEquals(HandsEnum.FULL_HOUSE, opHand.getHandStrength());
		assertEquals(-1, hand.compareTo(opHand));
	}

	@Test
	public void testCompareTwoTOAK() throws Exception {
		final Card card1 = new Card(RanksEnum.THREE, SuitsEnum.SPADE);
		final Card card2 = new Card(RanksEnum.FOUR, SuitsEnum.CLUB);
		final Card card3 = new Card(RanksEnum.QUEEN, SuitsEnum.DIAMOND);
		final Card card4 = new Card(RanksEnum.JACK, SuitsEnum.HEART);
		final Card card5 = new Card(RanksEnum.SEVEN, SuitsEnum.SPADE);

		final Card card6 = new Card(RanksEnum.JACK, SuitsEnum.CLUB);
		final Card card7 = new Card(RanksEnum.JACK, SuitsEnum.SPADE);

		final Card card8 = new Card(RanksEnum.SEVEN, SuitsEnum.HEART);
		final Card card9 = new Card(RanksEnum.SEVEN, SuitsEnum.CLUB);

		final List<Card> river = new ArrayList<>();
		river.add(card1);
		river.add(card2);
		river.add(card3);
		river.add(card4);
		river.add(card5);

		final List<Card> myCards = new ArrayList<>();
		myCards.addAll(river);
		myCards.add(card6);
		myCards.add(card7);

		final List<Card> opCards = new ArrayList<>();
		opCards.addAll(river);
		opCards.add(card8);
		opCards.add(card9);

		final Hand hand = new Hand(myCards);

		final Hand opHand = new Hand(opCards);

		assertEquals(HandsEnum.THREE_OF_A_KIND, hand.getHandStrength());
		assertEquals(HandsEnum.THREE_OF_A_KIND, opHand.getHandStrength());
		assertEquals(1, hand.compareTo(opHand));
	}

	@Test
	public void testCompareTwoDoublePairsWhenBothUp() throws Exception {
		final Card card1 = new Card(RanksEnum.QUEEN, SuitsEnum.SPADE);
		final Card card2 = new Card(RanksEnum.TEN, SuitsEnum.CLUB);
		final Card card3 = new Card(RanksEnum.ACE, SuitsEnum.DIAMOND);
		final Card card4 = new Card(RanksEnum.SIX, SuitsEnum.HEART);
		final Card card5 = new Card(RanksEnum.SEVEN, SuitsEnum.SPADE);

		final Card card6 = new Card(RanksEnum.QUEEN, SuitsEnum.CLUB);
		final Card card7 = new Card(RanksEnum.SIX, SuitsEnum.SPADE);

		final Card card8 = new Card(RanksEnum.ACE, SuitsEnum.HEART);
		final Card card9 = new Card(RanksEnum.SEVEN, SuitsEnum.CLUB);

		final List<Card> river = new ArrayList<>();
		river.add(card1);
		river.add(card2);
		river.add(card3);
		river.add(card4);
		river.add(card5);

		final List<Card> myCards = new ArrayList<>();
		myCards.addAll(river);
		myCards.add(card6);
		myCards.add(card7);

		final List<Card> opCards = new ArrayList<>();
		opCards.addAll(river);
		opCards.add(card8);
		opCards.add(card9);

		final Hand hand = new Hand(myCards);

		final Hand opHand = new Hand(opCards);

		assertEquals(HandsEnum.TWO_PAIRS, hand.getHandStrength());
		assertEquals(HandsEnum.TWO_PAIRS, opHand.getHandStrength());
		assertEquals(-1, hand.compareTo(opHand));
	}

	@Test
	public void testCompareTwoDoublePairsWhenOneUpOneDown() throws Exception {
		final Card card1 = new Card(RanksEnum.QUEEN, SuitsEnum.SPADE);
		final Card card2 = new Card(RanksEnum.TEN, SuitsEnum.CLUB);
		final Card card3 = new Card(RanksEnum.ACE, SuitsEnum.DIAMOND);
		final Card card4 = new Card(RanksEnum.SIX, SuitsEnum.HEART);
		final Card card5 = new Card(RanksEnum.SEVEN, SuitsEnum.SPADE);

		final Card card6 = new Card(RanksEnum.QUEEN, SuitsEnum.CLUB);
		final Card card7 = new Card(RanksEnum.SEVEN, SuitsEnum.CLUB);

		final Card card8 = new Card(RanksEnum.ACE, SuitsEnum.HEART);
		final Card card9 = new Card(RanksEnum.SIX, SuitsEnum.SPADE);

		final List<Card> river = new ArrayList<>();
		river.add(card1);
		river.add(card2);
		river.add(card3);
		river.add(card4);
		river.add(card5);

		final List<Card> myCards = new ArrayList<>();
		myCards.addAll(river);
		myCards.add(card6);
		myCards.add(card7);

		final List<Card> opCards = new ArrayList<>();
		opCards.addAll(river);
		opCards.add(card8);
		opCards.add(card9);

		final Hand hand = new Hand(myCards);

		final Hand opHand = new Hand(opCards);

		assertEquals(HandsEnum.TWO_PAIRS, hand.getHandStrength());
		assertEquals(HandsEnum.TWO_PAIRS, opHand.getHandStrength());
		assertEquals(-1, hand.compareTo(opHand));
	}

	@Test
	public void testCompareTwoDoublePairsWhenSamePairsSameHC() throws Exception {
		final Card card1 = new Card(RanksEnum.QUEEN, SuitsEnum.SPADE);
		final Card card2 = new Card(RanksEnum.SIX, SuitsEnum.CLUB);
		final Card card3 = new Card(RanksEnum.ACE, SuitsEnum.DIAMOND);
		final Card card4 = new Card(RanksEnum.SIX, SuitsEnum.HEART);
		final Card card5 = new Card(RanksEnum.EIGHT, SuitsEnum.SPADE);

		final Card card6 = new Card(RanksEnum.SEVEN, SuitsEnum.CLUB);
		final Card card7 = new Card(RanksEnum.SEVEN, SuitsEnum.DIAMOND);

		final Card card8 = new Card(RanksEnum.SEVEN, SuitsEnum.HEART);
		final Card card9 = new Card(RanksEnum.SEVEN, SuitsEnum.SPADE);

		final List<Card> river = new ArrayList<>();
		river.add(card1);
		river.add(card2);
		river.add(card3);
		river.add(card4);
		river.add(card5);

		final List<Card> myCards = new ArrayList<>();
		myCards.addAll(river);
		myCards.add(card6);
		myCards.add(card7);

		final List<Card> opCards = new ArrayList<>();
		opCards.addAll(river);
		opCards.add(card8);
		opCards.add(card9);

		final Hand hand = new Hand(myCards);

		final Hand opHand = new Hand(opCards);

		assertEquals(HandsEnum.TWO_PAIRS, hand.getHandStrength());
		assertEquals(HandsEnum.TWO_PAIRS, opHand.getHandStrength());
		assertEquals(0, hand.compareTo(opHand));
	}

	@Test
	public void testCompareTwoDoublePairsWhenSamePairsDifferentHC() throws Exception {
		final Card card1 = new Card(RanksEnum.KING, SuitsEnum.SPADE);
		final Card card2 = new Card(RanksEnum.SIX, SuitsEnum.CLUB);
		final Card card3 = new Card(RanksEnum.ACE, SuitsEnum.DIAMOND);
		final Card card4 = new Card(RanksEnum.SIX, SuitsEnum.HEART);
		final Card card5 = new Card(RanksEnum.SEVEN, SuitsEnum.SPADE);

		final Card card6 = new Card(RanksEnum.KING, SuitsEnum.CLUB);
		final Card card7 = new Card(RanksEnum.SEVEN, SuitsEnum.DIAMOND);

		final Card card8 = new Card(RanksEnum.ACE, SuitsEnum.HEART);
		final Card card9 = new Card(RanksEnum.SEVEN, SuitsEnum.SPADE);

		final List<Card> river = new ArrayList<>();
		river.add(card1);
		river.add(card2);
		river.add(card3);
		river.add(card4);
		river.add(card5);

		final List<Card> myCards = new ArrayList<>();
		myCards.addAll(river);
		myCards.add(card6);
		myCards.add(card7);

		final List<Card> opCards = new ArrayList<>();
		opCards.addAll(river);
		opCards.add(card8);
		opCards.add(card9);

		final Hand hand = new Hand(myCards);

		final Hand opHand = new Hand(opCards);

		assertEquals(HandsEnum.TWO_PAIRS, hand.getHandStrength());
		assertEquals(HandsEnum.TWO_PAIRS, opHand.getHandStrength());
		assertEquals(-1, hand.compareTo(opHand));
	}

	@Test
	public void testCompareTwoPairs() throws Exception {
		final Card card1 = new Card(RanksEnum.QUEEN, SuitsEnum.SPADE);
		final Card card2 = new Card(RanksEnum.SIX, SuitsEnum.CLUB);
		final Card card3 = new Card(RanksEnum.TWO, SuitsEnum.DIAMOND);
		final Card card4 = new Card(RanksEnum.EIGHT, SuitsEnum.HEART);
		final Card card5 = new Card(RanksEnum.SEVEN, SuitsEnum.SPADE);

		final Card card6 = new Card(RanksEnum.KING, SuitsEnum.CLUB);
		final Card card7 = new Card(RanksEnum.SIX, SuitsEnum.DIAMOND);

		final Card card8 = new Card(RanksEnum.ACE, SuitsEnum.HEART);
		final Card card9 = new Card(RanksEnum.SEVEN, SuitsEnum.SPADE);

		final List<Card> river = new ArrayList<>();
		river.add(card1);
		river.add(card2);
		river.add(card3);
		river.add(card4);
		river.add(card5);

		final List<Card> myCards = new ArrayList<>();
		myCards.addAll(river);
		myCards.add(card6);
		myCards.add(card7);

		final List<Card> opCards = new ArrayList<>();
		opCards.addAll(river);
		opCards.add(card8);
		opCards.add(card9);

		final Hand hand = new Hand(myCards);

		final Hand opHand = new Hand(opCards);

		assertEquals(HandsEnum.PAIR, hand.getHandStrength());
		assertEquals(HandsEnum.PAIR, opHand.getHandStrength());
		assertEquals(-1, hand.compareTo(opHand));
	}

	@Test
	public void testCompareTwoPairsWhenSamePair() throws Exception {
		final Card card1 = new Card(RanksEnum.QUEEN, SuitsEnum.SPADE);
		final Card card2 = new Card(RanksEnum.SIX, SuitsEnum.CLUB);
		final Card card3 = new Card(RanksEnum.TWO, SuitsEnum.DIAMOND);
		final Card card4 = new Card(RanksEnum.EIGHT, SuitsEnum.HEART);
		final Card card5 = new Card(RanksEnum.SEVEN, SuitsEnum.SPADE);

		final Card card6 = new Card(RanksEnum.KING, SuitsEnum.CLUB);
		final Card card7 = new Card(RanksEnum.SIX, SuitsEnum.DIAMOND);

		final Card card8 = new Card(RanksEnum.ACE, SuitsEnum.HEART);
		final Card card9 = new Card(RanksEnum.SIX, SuitsEnum.SPADE);

		final List<Card> river = new ArrayList<>();
		river.add(card1);
		river.add(card2);
		river.add(card3);
		river.add(card4);
		river.add(card5);

		final List<Card> myCards = new ArrayList<>();
		myCards.addAll(river);
		myCards.add(card6);
		myCards.add(card7);

		final List<Card> opCards = new ArrayList<>();
		opCards.addAll(river);
		opCards.add(card8);
		opCards.add(card9);

		final Hand hand = new Hand(myCards);

		final Hand opHand = new Hand(opCards);

		assertEquals(-1, hand.compareTo(opHand));
		assertEquals(HandsEnum.PAIR, opHand.getHandStrength());
	}

	@Test
	public void testCompareTwoPairsWhenSamePairSameHC() throws Exception {
		final Card card1 = new Card(RanksEnum.QUEEN, SuitsEnum.SPADE);
		final Card card2 = new Card(RanksEnum.SIX, SuitsEnum.CLUB);
		final Card card3 = new Card(RanksEnum.TWO, SuitsEnum.DIAMOND);
		final Card card4 = new Card(RanksEnum.EIGHT, SuitsEnum.HEART);
		final Card card5 = new Card(RanksEnum.SEVEN, SuitsEnum.SPADE);

		final Card card6 = new Card(RanksEnum.THREE, SuitsEnum.CLUB);
		final Card card7 = new Card(RanksEnum.SIX, SuitsEnum.DIAMOND);

		final Card card8 = new Card(RanksEnum.FOUR, SuitsEnum.HEART);
		final Card card9 = new Card(RanksEnum.SIX, SuitsEnum.SPADE);

		final List<Card> river = new ArrayList<>();
		river.add(card1);
		river.add(card2);
		river.add(card3);
		river.add(card4);
		river.add(card5);

		final List<Card> myCards = new ArrayList<>();
		myCards.addAll(river);
		myCards.add(card6);
		myCards.add(card7);

		final List<Card> opCards = new ArrayList<>();
		opCards.addAll(river);
		opCards.add(card8);
		opCards.add(card9);

		final Hand hand = new Hand(myCards);

		final Hand opHand = new Hand(opCards);

		assertEquals(HandsEnum.PAIR, hand.getHandStrength());
		assertEquals(HandsEnum.PAIR, opHand.getHandStrength());
		assertEquals(0, hand.compareTo(opHand));
	}

	@Test
	public void testCompareTwoHC() throws Exception {
		final Card card1 = new Card(RanksEnum.QUEEN, SuitsEnum.SPADE);
		final Card card2 = new Card(RanksEnum.SIX, SuitsEnum.CLUB);
		final Card card3 = new Card(RanksEnum.TWO, SuitsEnum.DIAMOND);
		final Card card4 = new Card(RanksEnum.EIGHT, SuitsEnum.HEART);
		final Card card5 = new Card(RanksEnum.SEVEN, SuitsEnum.SPADE);

		final Card card6 = new Card(RanksEnum.THREE, SuitsEnum.CLUB);
		final Card card7 = new Card(RanksEnum.NINE, SuitsEnum.DIAMOND);

		final Card card8 = new Card(RanksEnum.FOUR, SuitsEnum.HEART);
		final Card card9 = new Card(RanksEnum.KING, SuitsEnum.SPADE);

		final List<Card> river = new ArrayList<>();
		river.add(card1);
		river.add(card2);
		river.add(card3);
		river.add(card4);
		river.add(card5);

		final List<Card> myCards = new ArrayList<>();
		myCards.addAll(river);
		myCards.add(card6);
		myCards.add(card7);

		final List<Card> opCards = new ArrayList<>();
		opCards.addAll(river);
		opCards.add(card8);
		opCards.add(card9);

		final Hand hand = new Hand(myCards);

		final Hand opHand = new Hand(opCards);

		assertEquals(HandsEnum.HIGH_CARD, hand.getHandStrength());
		assertEquals(HandsEnum.HIGH_CARD, opHand.getHandStrength());
		assertEquals(-1, hand.compareTo(opHand));
	}

	@Test
	public void testCompareTwoHCSame() throws Exception {
		final Card card1 = new Card(RanksEnum.QUEEN, SuitsEnum.SPADE);
		final Card card2 = new Card(RanksEnum.SIX, SuitsEnum.CLUB);
		final Card card3 = new Card(RanksEnum.TWO, SuitsEnum.DIAMOND);
		final Card card4 = new Card(RanksEnum.EIGHT, SuitsEnum.HEART);
		final Card card5 = new Card(RanksEnum.SEVEN, SuitsEnum.SPADE);

		final Card card6 = new Card(RanksEnum.THREE, SuitsEnum.CLUB);
		final Card card7 = new Card(RanksEnum.KING, SuitsEnum.DIAMOND);

		final Card card8 = new Card(RanksEnum.FOUR, SuitsEnum.HEART);
		final Card card9 = new Card(RanksEnum.KING, SuitsEnum.SPADE);

		final List<Card> river = new ArrayList<>();
		river.add(card1);
		river.add(card2);
		river.add(card3);
		river.add(card4);
		river.add(card5);

		final List<Card> myCards = new ArrayList<>();
		myCards.addAll(river);
		myCards.add(card6);
		myCards.add(card7);

		final List<Card> opCards = new ArrayList<>();
		opCards.addAll(river);
		opCards.add(card8);
		opCards.add(card9);

		final Hand hand = new Hand(myCards);

		final Hand opHand = new Hand(opCards);

		assertEquals(HandsEnum.HIGH_CARD, hand.getHandStrength());
		assertEquals(HandsEnum.HIGH_CARD, opHand.getHandStrength());
		assertEquals(0, hand.compareTo(opHand));
	}
}
