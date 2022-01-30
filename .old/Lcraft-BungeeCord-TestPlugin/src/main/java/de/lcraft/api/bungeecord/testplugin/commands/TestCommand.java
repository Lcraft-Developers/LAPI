package de.lcraft.api.bungeecord.testplugin.commands;

import de.lcraft.api.utils.minecraft.bungeecord.module.Module;
import de.lcraft.api.utils.minecraft.bungeecord.module.logger.ModuleLoggerType;
import de.lcraft.api.utils.minecraft.bungeecord.module.utils.command.ModuleCommand;
import net.md_5.bungee.api.CommandSender;
import java.io.IOException;
import java.util.ArrayList;

public class TestCommand extends ModuleCommand {

    public TestCommand(Module m) throws IOException {
        super("bungee-spigot", "This is a Test Command", m, false);
    }

    @Override
    public boolean onCommand(CommandSender s, String[] args) {
        getLogger().send(ModuleLoggerType.NOTHING, "Test");
        return super.onCommand(s, args);
    }

    @Override
    public ArrayList<String> getAllPermissions(ArrayList<String> allPermissions) {
        return null;
    }

    @Override
    public ArrayList<String> getAllTranslations(ArrayList<String> allTranslations) {
        return null;
    }

}

