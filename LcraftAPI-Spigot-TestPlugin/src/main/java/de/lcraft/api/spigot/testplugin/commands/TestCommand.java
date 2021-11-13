package de.lcraft.api.spigot.testplugin.commands;

import de.lcraft.api.utils.minecraft.spigot.module.Module;
import de.lcraft.api.utils.minecraft.spigot.module.logger.ModuleLoggerPlace;
import de.lcraft.api.utils.minecraft.spigot.module.logger.ModuleLoggerType;
import de.lcraft.api.utils.minecraft.spigot.module.utils.command.ModuleCommand;
import org.bukkit.command.CommandSender;
import java.io.IOException;
import java.util.ArrayList;

public class TestCommand extends ModuleCommand {

    public TestCommand(Module m) throws IOException {
        super("test-spigot", "This is a Test Command", "", m, false);
    }

    @Override
    public boolean onCommand(CommandSender s, String[] args) {
        getLogger().send(ModuleLoggerType.NOTHING, ModuleLoggerPlace.ALL, "Test");
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
