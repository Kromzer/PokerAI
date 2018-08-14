package fr.kromzer.pokerai;

import java.util.ArrayList;
import java.util.List;

import fr.kromzer.pokerai.game.GameState;
import fr.kromzer.pokerai.strategies.Strategy;
import fr.kromzer.pokerai.utils.Card;
import fr.kromzer.pokerai.utils.Deck;

public class PokerAI {

	public static void main(String[] args) {
		final GameState gameState = new GameState();
		gameState.setNbPlayers(6);
		final List<Card> boardCards = new ArrayList<>();
		gameState.setBoardCards(boardCards);
		final List<Card> playerCards = new ArrayList<>();

		final Deck deck = new Deck();
		deck.shuffleCards();

		playerCards.add(deck.drawTopCard());
		playerCards.add(deck.drawTopCard());

		Strategy.getAction(gameState, playerCards);
	}
}
