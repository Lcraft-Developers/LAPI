package de.lcraft.test.spigot.commands.tps;

import de.lcraft.api.minecraft.spigot.module.manager.Module;
import de.lcraft.api.minecraft.spigot.module.manager.utils.command.ModuleCommand;
import de.lcraft.api.minecraft.spigot.module.player.LPlayer;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public class TPSCommand extends ModuleCommand {

	public TPSCommand(Module m) {
		super("tps", "This is my TPS Command", m, true);
	}

	@Override
	public boolean onConsoleCommand(CommandSender s, String[] args) {
		s.sendMessage(getStandardTranslatedPREFIX() + translateWithStandardLanguage("§aThe Server TPS: ") + getModule().getServerTPS().getTPS());
		return super.onConsoleCommand(s, args);
	}
	@Override
	public boolean onLPlayerCommand(LPlayer p, String[] args) {
		p.getPlayer().sendMessage(getStandardTranslatedPREFIX() + translate(p.getUUID(), "§aThe Server TPS: ") + getModule().getServerTPS().getTPS());
		return super.onLPlayerCommand(p, args);
	}

	@Nonnull
	@Override
	public ArrayList<String> allUsedTranslatedText() {
		ArrayList<String> translations = new ArrayList<>();
		translations.add("§aThe Server TPS: ");
		return translations;
	}

	@Nonnull
	@Override
	public ArrayList<String> allUsedPermissions() {
		return null;
	}

}
