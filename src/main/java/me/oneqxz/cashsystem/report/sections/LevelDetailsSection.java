package me.oneqxz.cashsystem.report.sections;

import me.sgx.gd.player.Player;
import me.sgx.gd.scene.SceneSystem;
import me.sgx.gd.world.World;

public class LevelDetailsSection implements ICrashSection {

    @Override
    public String formatSection(Throwable crush) {
        StringBuilder sb = new StringBuilder();
        sb.append("-- Level Details --\n");
        sb.append("Details:\n");

        if(World.player == null)
        {
            sb.append("\tNo level info");
            return sb.toString();
        }
        Player player = World.player;

        sb.append(addDetail("Player:"));
        sb.append(addDetail("\tGamemode", () -> player.getMode().texture.toUpperCase()));

        sb.append(addDetail("\tPosition:"));
        sb.append(addDetail("\t\tX", () -> player.position.x));
        sb.append(addDetail("\t\tY", () -> player.position.y));

        sb.append(addDetail("Level:"));
        sb.append(addDetail("\tBlocks", World.blocks::size));
        sb.append(addDetail("\tFPS", () -> SceneSystem.FPS));

        return sb.toString();
    }

}
