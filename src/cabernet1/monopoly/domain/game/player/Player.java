/*
 * TODO: write which functions are called from the current device, and which are called from network (hence, if there is need to distrubute an action
 *       through devices or it's already distributed
 */
package cabernet1.monopoly.domain.game.player;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.GameController;
import cabernet1.monopoly.domain.Network;
import cabernet1.monopoly.domain.NetworkController;
import cabernet1.monopoly.domain.game.board.Board;
import cabernet1.monopoly.domain.game.board.tile.Tile;
import cabernet1.monopoly.domain.game.board.tile.enumerators.TileType;
import cabernet1.monopoly.domain.game.die.enumerators.JailDiceCupStatus;
import cabernet1.monopoly.domain.game.die.enumerators.NormalDiceCupStatus;
import cabernet1.monopoly.domain.game.die.util.JailDiceCup;
import cabernet1.monopoly.domain.game.die.util.NormalDiceCup;
import cabernet1.monopoly.domain.game.player.enumerators.PlayerMovementStatus;
import cabernet1.monopoly.domain.network.command.commands.AnnounceMessageCommand;
import cabernet1.monopoly.domain.network.command.commands.ChangeJailStatusCommand;
import cabernet1.monopoly.domain.network.command.commands.ChangeMovementStatusCommand;
import cabernet1.monopoly.domain.network.command.commands.IncNumConsDoubleRollsCommand;
import cabernet1.monopoly.domain.network.command.commands.MovePlayerCommand;

public class Player extends IPlayer {
    public Player(int ID, String name, int money, int defaultOrder, Tile currentTile) {
        super(ID, name, money, defaultOrder, currentTile);
    }

	@Override
	public void playTurn() {
		NormalDiceCup cup = NormalDiceCup.getInstance();
		NormalDiceCupStatus rollStatus = cup.rollCup();
		NetworkController nc = Network.getInstance().getNetworkController();
		GameController controller = Game.getInstance().getGameController();
		controller.showDiceValue();
		switch (rollStatus) {
		case NORMAL_MOVE:
			handleNormalMove();
			break;
		case DOUBLE_MOVE:
			handleDoubleMove();
			break;
		case TRIPLE_MOVE:
			handleTriplesMove();
			break;
		case MR_MONOPOLY_MOVE:
			nc.sendCommand(new ChangeMovementStatusCommand(this, PlayerMovementStatus.MRMONOPOLY_MOVE));
			handleNormalMove();
			break;
		case BUS_MOVE:
			nc.sendCommand(new ChangeMovementStatusCommand(this, PlayerMovementStatus.BUS_MOVE));
			handleNormalMove();
			break;
		}

    }

	// TODO: need to check building buying availability hear can check easier with owned properties
	@Override
	public void playJailturn() {
		JailDiceCup cup = JailDiceCup.getInstance();
		JailDiceCupStatus rollStatus = cup.rollCup();
		NetworkController nc = Network.getInstance().getNetworkController();

		String message;
		switch (rollStatus) {
		case DOUBLES:
			message = getName() + " has got out of jail!";
			nc.sendCommand(new AnnounceMessageCommand(message));
			// TODO: handleDoubleMove for JailCup: handleJailDoubleMove();
			break;
		case NOT_DOUBLES:
			message = getName() + " has not rolled Doubles. Hence, he/she will stay in jail for this turn";
			nc.sendCommand(new AnnounceMessageCommand(message));
			break;
		}

    }

	@Override
	protected void handleNormalMove() {
		Board board = Board.getInstance();
		NormalDiceCup cup = NormalDiceCup.getInstance();
		Tile newTile = board.getNextTile(curTile, cup.getFacesValue());
		NetworkController nc = Network.getInstance().getNetworkController();

		String previousTile = curTile.getName();
		String message = getName() + " has moved from " + previousTile + "  to " + newTile.getName();
		nc.sendCommand(new AnnounceMessageCommand(message));
		nc.sendCommand(new MovePlayerCommand(this, newTile));
		board.handleTile(this, newTile);

	}
	

	@Override
	protected void handleMrMonopolyMove() {
		Board board = Board.getInstance();
		NormalDiceCup cup = NormalDiceCup.getInstance();
		NetworkController nc = Network.getInstance().getNetworkController();

		Tile nextTile = board.nextUnownedProperty(curTile, direction, cup.getFacesValue());
		String message;
		if (nextTile == null) {
			nextTile = board.nextRentableProperty(curTile, direction, cup.getFacesValue());
			if (nextTile == null) {
				message = "No rentable Properties has been found on your way, hence you will stay in your place";
			} else {
				message = "You will move to the next rentable Property, namely " + nextTile.getName();
			}
		} else {
			message = "You will move to the next un owned Property, namely " + nextTile.getName();
		}
		nc.sendCommand(new AnnounceMessageCommand(message));

		if (nextTile != null) {
			nc.sendCommand(new MovePlayerCommand(this, nextTile));
			board.handleTile(this, nextTile);
		}
	}

	@Override
	protected void handleBusMove() {
		NetworkController nc = Network.getInstance().getNetworkController();
		Board board = Board.getInstance();
		NormalDiceCup cup = NormalDiceCup.getInstance();
		Tile nextTile = board.nextUnownedProperty(curTile, direction, cup.getFacesValue());
		String message;
		if (nextTile == null) {
			message = "No community chest or chance card on your way, hence you will stay in your place";
			nc.sendCommand(new AnnounceMessageCommand(message));
		} else {
			if (nextTile.getTileType() == TileType.CommunityChest)
				message = "You will move to the next community chest tile";
			else
				message = "You will move to the next Chance tile";
			nc.sendCommand(new AnnounceMessageCommand(message));
			nc.sendCommand(new MovePlayerCommand(this, nextTile));
			board.handleTile(this, nextTile);
		}
	}

	@Override
	protected void handleTriplesMove() {
		Game.getInstance().getGameController().chooseTile(this);
	}

	@Override
	protected void handleDoubleMove() {
		NetworkController nc = Network.getInstance().getNetworkController();
		nc.sendCommand(new IncNumConsDoubleRollsCommand(this));
		++numberOfConsecutiveDoublesRolls;
		if (numberOfConsecutiveDoublesRolls == 3) {
			goJail();
			return;
		}
		nc.sendCommand(new ChangeMovementStatusCommand(this, PlayerMovementStatus.DOUBLE_MOVE));
		handleNormalMove();
	}

	@Override
	protected void goJail() {
		NetworkController nc = Network.getInstance().getNetworkController();
		Board board = Board.getInstance();
		String message = getName() + " has got into jail";

		nc.sendCommand(new AnnounceMessageCommand(message));
		Tile jailTile = board.getJailTile();
		nc.sendCommand(new MovePlayerCommand(this, jailTile));
		// TODO ask about using card to get out of jail

		nc.sendCommand(new ChangeJailStatusCommand(this, true));
	}

	@Override
	public void jumpToTile(Tile newTile) {
		NetworkController nc = Network.getInstance().getNetworkController();
		String previousTile = curTile.getName();
		nc.sendCommand(new MovePlayerCommand(this, newTile));
		String message = getName() + " has transposed immediately from " + previousTile + " to " + curTile.getName();
		nc.sendCommand(new AnnounceMessageCommand(message));
		
		Board.getInstance().handleTile(this, newTile);
	}

}
