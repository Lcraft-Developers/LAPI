package de.lcraft.api.plugin.modules;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class ModuleCommand implements CommandExecutor {

    public Module m;

    public ModuleCommand(Module m) {
        this.m = m;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        execute(sender, command, label, args);
        if(sender instanceof Player) {
            executeOnlyPlayer((Player) sender, command, label, args);
        } else {
            executeNotPlayer(sender, command, label, args);
        }
        return false;
    }

    public Module getM() {
        return m;
    }

    public abstract void execute(CommandSender s, Command cmd, String label, String[] args);
    public abstract void executeOnlyPlayer(Player p, Command cmd, String label, String[] args);
    public abstract void executeNotPlayer(CommandSender s, Command cmd, String label, String[] args);

}
