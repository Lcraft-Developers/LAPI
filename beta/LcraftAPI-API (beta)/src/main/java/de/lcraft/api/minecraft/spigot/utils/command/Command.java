package de.lcraft.api.minecraft.spigot.utils.command;

import de.lcraft.api.java_utils.language.Language;
import de.lcraft.api.java_utils.language.LanguageContainer;
import de.lcraft.api.java_utils.language.LanguagesManager;
import de.lcraft.api.minecraft.spigot.module.manager.utils.language.StandardMessages;
import de.lcraft.api.minecraft.spigot.module.manager.utils.listeners.ListenerManager;
import de.lcraft.api.minecraft.spigot.module.manager.utils.permissions.PermissionContainer;
import de.lcraft.api.minecraft.spigot.module.manager.utils.permissions.PermsManager;
import de.lcraft.api.minecraft.spigot.module.player.LPlayer;
import de.lcraft.api.minecraft.spigot.module.player.LPlayerManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public abstract class Command extends org.bukkit.command.Command implements Listener {

    private final boolean splitting;
    private final LanguagesManager languagesManager;
    private final PermsManager permsManager;
    private final String description;
    private final ArrayList<SubCommand> subModuleCommands;
    private final String command;
    private final LPlayerManager lPlayerManager;
    private final ListenerManager listenerManager;
    private final LanguageContainer langContainer;
    private final PermissionContainer permsContainer;
    protected StandardMessages standardMessages;

    public Command(StandardMessages standardMessages, String label, String desc, PermsManager permsManager, LanguagesManager languagesManager, LPlayerManager lPlayerManager, boolean splitting) {
        super(label, desc, "", new ArrayList<>());
        subModuleCommands = new ArrayList<>();
        this.description = desc;
        this.splitting = splitting;
        this.command = label;
        this.listenerManager = lPlayerManager.getListenerManager();

        this.permsManager = permsManager;
        this.languagesManager = languagesManager;
        this.lPlayerManager = lPlayerManager;
        this.standardMessages = standardMessages;
        this.langContainer = new LanguageContainer() {
            @Override
            protected ArrayList<String> allUsedTranslatedText() {
                return allUsedTranslations(new ArrayList<>());
            }
        };
        this.permsContainer = new PermissionContainer() {
            @Override
            protected ArrayList<String> allUsedPermissions() {
                return allUsedPerms(new ArrayList<>());
            }
        };
    }

    public final String translate(UUID uuid, String text) {
        return getLanguagesManager().getIDLanguage(getLanguagesManager().getIDFromUUID(uuid)).translate(text);
    }
    public final String translateWithStandardLanguage(String text) {
        return getLanguagesManager().getMainLanguage().translate(text);
    }
    public final boolean hasPermissions(LPlayer p, String perm) {
        return getPermsManager().hasPermissions(p, perm);
    }

    public final void addSubCommand(SubCommand subModuleCommand) {
        subModuleCommands.add(subModuleCommand);
    }
    public final SubCommand getSubCommand(String name) {
        for(SubCommand m : subModuleCommands) {
            if(m.getName().equalsIgnoreCase(name)) {
                return m;
            }
            continue;
        }
        return null;
    }
    public final boolean existsSubCommand(String name) {
        return Objects.nonNull(getSubCommand(name));
    }

    @Override
    public final boolean execute(CommandSender commandSender, String var3, String[] strings) {
        if(Objects.nonNull(strings) && strings.length > 0) {
            if(existsSubCommand(strings[0])) {
                String[] new_args = new String[strings.length - 1];
                for(int i = 1; i < strings.length; i++) {
                    new_args[i - 1] = strings[i];
                }
                getSubCommand(strings[0]).execute(commandSender, var3, new_args);
            } else {
                split(commandSender, strings);
            }
        } else {
            split(commandSender, strings);
        }
        return false;
    }
    public final void split(CommandSender commandSender, String[] strings) {
        if(splitting) {
            if(Objects.nonNull(commandSender) && commandSender instanceof Player) {
                onLPlayerCommand(getLPlayerManager().getLPlayerByUUID(((Player) commandSender).getUniqueId()), strings);
            } else {
                onConsoleCommand(commandSender, strings);
            }
        }
        onCommandExecute(commandSender, strings);
    }

    public abstract ArrayList<String> allUsedTranslations(ArrayList<String> translations);
    protected abstract ArrayList<String> allUsedPerms(ArrayList<String> perms);

    public boolean onCommandExecute(CommandSender s, String[] args) {return false;}
    public boolean onLPlayerCommand(LPlayer p, String[] args) {return false;}
    public boolean onConsoleCommand(CommandSender s, String[] args) {return false;}

    public String getHelpMessage(String normalCommand, Language lang, String commandCharacter, String... args) {
        String helpMessage = lang.translate(getHelpMessageStart()).split(" %helpmessage%")[0];
        for(String command : args) {
            if(command.equals(args[args.length - 1])) {
                helpMessage = helpMessage + " " + commandCharacter + normalCommand + " " + command + "§c!";
            } else {
                helpMessage = helpMessage + " " + commandCharacter + normalCommand + " " + command + lang.translate(" §cor");
            }
        }
        return helpMessage;
    }
    public String getHelpMessage(String normalCommand, UUID uuid, String commandCharacter, String... args) {
        return getHelpMessage(normalCommand, getLanguagesManager().getIDLanguage(getLanguagesManager().getIDFromUUID(uuid)), commandCharacter, args);
    }
    public String getHelpMessage(String normalCommand, String commandCharacter, String... args) {
        return getHelpMessage(normalCommand, getLanguagesManager().getMainLanguage(), commandCharacter, args);
    }

    public final String getPREFIX() {
        return getStandardMessages().getPREFIX();
    }
    public final String getNO_NUMBER() {
        return getStandardMessages().getNO_NUMBER();
    }
    public final String getNO_PERMISSIONS() {
        return getStandardMessages().getNO_PERMISSIONS();
    }
    public final String getNO_PLAYER() {
        return getStandardMessages().getNO_PLAYER();
    }
    public final String getNO_PLAYER_EXISTS() {
        return getStandardMessages().getNO_PLAYER_EXISTS();
    }
    public final String getNO_PLAYER_FOUND() {
        return getStandardMessages().getNO_PLAYER_FOUND();
    }
    public final String getHelpMessageStart() {
        return getStandardMessages().getHELP_MESSAGE();
    }

    public final String getStandardTranslatedPREFIX() {
        return translateWithStandardLanguage(getPREFIX());
    }
    public final String getStandardTranslatedNO_PLAYER() {
        return translateWithStandardLanguage(getNO_PLAYER());
    }
    public final String getStandardTranslatedNO_NUMBER() {
        return translateWithStandardLanguage(getNO_NUMBER());
    }
    public final String getStandardTranslatedNO_PERMISSIONS() {
        return translateWithStandardLanguage(getNO_PERMISSIONS());
    }
    public final String getStandardTranslatedNO_PLAYER_EXISTS() {
        return translateWithStandardLanguage(getNO_PLAYER_EXISTS());
    }
    public final String getStandardTranslatedNO_PLAYER_FOUND() {
        return translateWithStandardLanguage(getNO_PLAYER_FOUND());
    }
    public final String getStandardTranslatedHelpMessageStart() {
        return translateWithStandardLanguage(getHelpMessageStart());
    }

    public final String getTranslatedPREFIX(UUID uuid) {
        return translate(uuid, getPREFIX());
    }
    public final String getTranslatedNO_PLAYER(UUID uuid) {
        return translate(uuid,getNO_PLAYER());
    }
    public final String getTranslatedNO_NUMBER(UUID uuid) {
        return translate(uuid, getNO_NUMBER());
    }
    public final String getTranslatedNO_PERMISSIONS(UUID uuid) {
        return translate(uuid, getNO_PERMISSIONS());
    }
    public final String getTranslatedNO_PLAYER_EXISTS(UUID uuid) {
        return translate(uuid, getNO_PLAYER_EXISTS());
    }
    public final String getTranslatedNO_PLAYER_FOUND(UUID uuid) {
        return translate(uuid, getNO_PLAYER_FOUND());
    }
    public final String getTranslatedHelpMessageStart(UUID uuid) {
        return translate(uuid, getHelpMessageStart());
    }

    public final String getDescription() {
        return description;
    }
	public final String getName() {
        return command;
    }
    public final LPlayerManager getLPlayerManager() {
        return lPlayerManager;
    }
    public final LanguagesManager getLanguagesManager() {
        return languagesManager;
    }
    public final PermsManager getPermsManager() {
        return permsManager;
    }
    public final ListenerManager getListenerManager() {
        return listenerManager;
    }
    public final StandardMessages getStandardMessages() {
        return standardMessages;
    }
    public final void setStandardMessages(StandardMessages standardMessages) {
        this.standardMessages = standardMessages;
    }
    public LanguageContainer getLangContainer() {
        return langContainer;
    }
    public PermissionContainer getPermsContainer() {
        return permsContainer;
    }

}