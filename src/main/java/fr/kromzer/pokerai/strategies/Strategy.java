package fr.kromzer.pokerai.strategies;

import java.util.List;

import org.apache.log4j.Logger;

import fr.kromzer.pokerai.algorithms.Algorithms;
import fr.kromzer.pokerai.enums.ActionsEnum;
import fr.kromzer.pokerai.game.GameState;
import fr.kromzer.pokerai.utils.Card;
import fr.kromzer.pokerai.utils.Hand;

public class Strategy {
	
	/** Logger. */
	private static final Logger logger = Logger.getLogger(Strategy.class);
	
	public static ActionsEnum getAction(GameState gameState, List<Card> playerCards) {
		//Preflop
		if(gameState.getBoardCards().isEmpty()) {
			logger.debug("Preflop");
			int chenScore = Algorithms.getChenScore(playerCards.get(0), playerCards.get(1));
			logger.info("Chen score: " + chenScore);
			if(chenScore >= 10) {
				logger.info("Raise");
				return ActionsEnum.RAISE;
			}
			else if(chenScore >= 9) {
				logger.info("Raise if mid/late position");
				return ActionsEnum.RAISE;
			}
			else if(chenScore >= 8 && gameState.getNbPlayers() == 6) {
				logger.info("Raise if mid/late position");
				return ActionsEnum.RAISE;
			}
			else if(chenScore >= 7) {
				logger.info("Raise if late position");
				return ActionsEnum.RAISE;
			}
			else {
				logger.info("Fold");
				return ActionsEnum.FOLD;
			}
		}
		else {
			float ehs = Algorithms.computeEHS(new Hand(playerCards), gameState.getBoardCards(), gameState.getNbPlayers() - 1);
			if(ehs > 0.8) {
				logger.info("Raise");
				return ActionsEnum.RAISE;
			}
			else {
				logger.info("Fold");
				return ActionsEnum.FOLD;
			}
		}
	}
}
