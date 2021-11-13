package de.lcraft.api.utils.minecraft.spigot.languages.filesystem;

import de.lcraft.api.utils.minecraft.spigot.module.utils.configs.ModuleConfig;

public abstract class Language {

    private ModuleConfig translations,
                         help,
                         cfg;

    public Language() {
        translations = new ModuleConfig("Lcraft Languages/" + getShort(), "translations.yml");
        help = new ModuleConfig("Lcraft Languages/" + getShort(), "help.yml");
        cfg = new ModuleConfig("Lcraft Languages/" + getShort(), "config.yml");
    }

    public abstract String getName();
    public abstract String getEnglishName();
    // Short like de or en or ru
    public abstract String getShort();
    public String[] getHelp() {
        String[] help = new String[1];
        help[0] = "No Help Message seted in Language " + getEnglishName();
        if(getHelpFile().cfg().getConfigurationSection(getShort() + ".help") != null) {
            help = new String[getHelpFile().cfg().getConfigurationSection(getShort() + ".help").getKeys(false).size()];
            for(int i = 0; i < getHelpFile().cfg().getConfigurationSection(getShort() + ".help").getKeys(false).size(); i++) {
                help[i] = getHelpFile().cfg().getString(getShort() + ".help." + i);
            }
        }
        return help;
    }
    public ModuleConfig getTranslationsFile() {
        return translations;
    }
    public ModuleConfig getHelpFile() {
        return help;
    }
    public String translate(String def) {
        String root = "translate." + LanguagesManager.getID(def);
        translations.cfg().set(root + ".default", def);
        if(translations.cfg().contains(root + ".translation")) {
            def = translations.cfg().getString(root + ".translation");
        } else {
            translations.cfg().set(root + ".translation", def);
        }
        translations.save();
        return def;
    }
    public String getJoinMessage() {
        String root = "msgs.join";
        if(cfg.cfg().contains(root)) {
            return cfg.cfg().getString(root);
        } else {
            translations.cfg().set(root, "§6%PLAYER% §6joined the game");
            translations.save();
        }
        return getJoinMessage();
    }
    public String getQuitMessage() {
        String root = "msgs.quit";
        if(cfg.cfg().contains(root)) {
            return cfg.cfg().getString(root);
        } else {
            translations.cfg().set(root, "§6%PLAYER% §6leaved the game");
            translations.save();
        }
        return getQuitMessage();
    }

}
