package fr.kromzer.pokerai.enums;

import java.util.Random;

public enum ActionsEnum {
	FOLD, CALL, CHECK, RAISE;

	private static ActionsEnum[] actions = ActionsEnum.values();

	private static Random random = new Random();

	public static final ActionsEnum random() {
		return actions[random.nextInt(actions.length)];
	}

	public static final ActionsEnum getFromString(String str) {
		switch (str) {
			case "FOLD":
				return FOLD;
			case "CALL":
				return CALL;
			case "CHECK":
				return CHECK;
			case "RAISE":
				return RAISE;
			default:
				return null;
		}
	}
}
