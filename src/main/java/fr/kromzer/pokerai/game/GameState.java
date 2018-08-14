package fr.kromzer.pokerai.game;

import java.util.List;

import fr.kromzer.pokerai.utils.Card;

public class GameState {
	
	private int nbPlayers;
	
	private List<Card> boardCards;

	/**
	 * @return the boardCards
	 */
	public List<Card> getBoardCards() {
		return boardCards;
	}

	/**
	 * @param boardCards the boardCards to set
	 */
	public void setBoardCards(List<Card> boardCards) {
		this.boardCards = boardCards;
	}

	/**
	 * @return the nbPlayers
	 */
	public int getNbPlayers() {
		return nbPlayers;
	}

	/**
	 * @param nbPlayers the nbPlayers to set
	 */
	public void setNbPlayers(int nbPlayers) {
		this.nbPlayers = nbPlayers;
	}
	
}
