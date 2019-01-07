package cabernet1.monopoly.lib.cheat.command;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.game.command.JumpPlayerCommand;

public class JumpTileCheatCommand extends CheatCommand {
    private final int jumpTileId;

    public JumpTileCheatCommand(int jumpTileId) {
        this.jumpTileId = jumpTileId;
    }

    @Override
    public void execute() {
        JumpPlayerCommand command = new JumpPlayerCommand(Game.getInstance().getCurrentPlayer().getID(), jumpTileId);
        command.execute();
    }

    public int getJumpTileId() {
        return jumpTileId;
    }
}
