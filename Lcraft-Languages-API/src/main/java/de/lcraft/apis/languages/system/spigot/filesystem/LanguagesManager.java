package de.lcraft.apis.languages.system.spigot.filesystem;

import de.lcraft.api.plugin.modules.minecraft.spigot.Module;
import de.lcraft.api.plugin.modules.minecraft.spigot.utils.ModuleConfig;
import de.lcraft.apis.languages.system.spigot.filesystem.impl.English;
import de.lcraft.apis.languages.system.spigot.filesystem.impl.German;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class LanguagesManager {

    private ArrayList<Language> langs;
    private Module m;
    private ModuleConfig userCFG, cfg;

    public LanguagesManager(Module m) throws IOException {
        langs = new ArrayList<>();
        userCFG = new ModuleConfig(m, "lang", "users.yml");
        cfg = new ModuleConfig(m, "lang", "config.yml");

        addLang(new German(m));
        addLang(new English(m));

        this.m = m;
    }

    public Module getModule() {
        return m;
    }
    public ArrayList<Language> getLangs() {
        return langs;
    }
    public ModuleConfig getCfg() {
        return cfg;
    }
    public ModuleConfig getUserCFG() {
        return userCFG;
    }

    public void addLang(Language lang) {
        langs.add(lang);
    }

    public void setPlayer(Language lang, UUID player) {
        userCFG.cfg().set("langs." + player.toString(), lang.getShort());
        userCFG.save();
    }

    public Language getPlayer(UUID player) throws IOException {
        if(userCFG.cfg().contains("langs." + player.toString())) {
            return getLanguageByShort(userCFG.cfg().getString("langs." + player.toString()));
        } else {
            setPlayer(getDefaultLanguage(), player);
            return getPlayer(player);
        }
    }

    public Language getDefaultLanguage() throws IOException {
        if(cfg.cfg().contains("langs.default")) {
            cfg.cfg().set("langs.default", new English(m).getShort());
            cfg.save();
        } else {
            String lang = cfg.cfg().getString("langs.default");
            return getLanguageByShort(lang);
        }
        return getDefaultLanguage();
    }

    public Language getLanguageByShort(String shor) {
        Language lang = null;

        for(Language c : langs) {
            if(c.getShort().equalsIgnoreCase(shor)) {
                lang = c;
            }
        }

        return lang;
    }

    public static int getID(String normal) {
        int id = 0;
        int bid = 0;
        for(String c : normal.split("")) {
            bid++;
            id = id + (bid * 3);
            if(c.equals("a")) {
                bid = bid + 1;
                id = id + bid;
            } else if(c.equals("b")) {
                bid = bid + 2;
                id = id + bid;
            } else if(c.equals("c")) {
                bid = bid + 3;
                id = id + bid;
            } else if(c.equals("d")) {
                bid = bid + 4;
                id = id + bid;
            } else if(c.equals("e")) {
                bid = bid + 5;
                id = id + bid;
            } else if(c.equals("f")) {
                bid = bid + 6;
                id = id + bid;
            } else if(c.equals("g")) {
                bid = bid + 7;
                id = id + bid;
            } else if(c.equals("h")) {
                bid = bid + 8;
                id = id + bid;
            } else if(c.equals("i")) {
                bid = bid + 9;
                id = id + bid;
            } else if(c.equals("j")) {
                bid = bid + 10;
                id = id + bid;
            } else if(c.equals("k")) {
                bid = bid + 11;
                id = id + bid;
            } else if(c.equals("l")) {
                bid = bid + 12;
                id = id + bid;
            } else if(c.equals("m")) {
                bid = bid + 13;
                id = id + bid;
            } else if(c.equals("n")) {
                bid = bid + 14;
                id = id + bid;
            } else if(c.equals("o")) {
                bid = bid + 15;
                id = id + bid;
            } else if(c.equals("p")) {
                bid = bid + 16;
                id = id + bid;
            } else if(c.equals("q")) {
                bid = bid + 17;
                id = id + bid;
            } else if(c.equals("r")) {
                bid = bid + 18;
                id = id + bid;
            } else if(c.equals("s")) {
                bid = bid + 19;
                id = id + bid;
            } else if(c.equals("t")) {
                bid = bid + 20;
                id = id + bid;
            } else if(c.equals("u")) {
                bid = bid + 21;
                id = id + bid;
            } else if(c.equals("v")) {
                bid = bid + 22;
                id = id + bid;
            } else if(c.equals("w")) {
                bid = bid + 23;
                id = id + bid;
            } else if(c.equals("x")) {
                bid = bid + 24;
                id = id + bid;
            } else if(c.equals("y")) {
                bid = bid + 25;
                id = id + bid;
            } else if(c.equals("z")) {
                bid = bid + 26;
                id = id + bid;
            } else if(c.equals("A")) {
                bid = bid + 27;
                id = id + bid;
            } else if(c.equals("B")) {
                bid = bid + 28;
                id = id + bid;
            } else if(c.equals("C")) {
                bid = bid + 29;
                id = id + bid;
            } else if(c.equals("D")) {
                bid = bid + 30;
                id = id + bid;
            } else if(c.equals("E")) {
                bid = bid + 31;
                id = id + bid;
            } else if(c.equals("F")) {
                bid = bid + 32;
                id = id + bid;
            } else if(c.equals("G")) {
                bid = bid + 33;
                id = id + bid;
            } else if(c.equals("H")) {
                bid = bid + 34;
                id = id + bid;
            } else if(c.equals("I")) {
                bid = bid + 35;
                id = id + bid;
            } else if(c.equals("J")) {
                bid = bid + 36;
                id = id + bid;
            } else if(c.equals("K")) {
                bid = bid + 37;
                id = id + bid;
            } else if(c.equals("L")) {
                bid = bid + 38;
                id = id + bid;
            } else if(c.equals("M")) {
                bid = bid + 39;
                id = id + bid;
            } else if(c.equals("N")) {
                bid = bid + 40;
                id = id + bid;
            } else if(c.equals("O")) {
                bid = bid + 41;
                id = id + bid;
            } else if(c.equals("P")) {
                bid = bid + 42;
                id = id + bid;
            } else if(c.equals("Q")) {
                bid = bid + 43;
                id = id + bid;
            } else if(c.equals("R")) {
                bid = bid + 44;
                id = id + bid;
            } else if(c.equals("S")) {
                bid = bid + 45;
                id = id + bid;
            } else if(c.equals("T")) {
                bid = bid + 46;
                id = id + bid;
            } else if(c.equals("U")) {
                bid = bid + 47;
                id = id + bid;
            } else if(c.equals("V")) {
                bid = bid + 48;
                id = id + bid;
            } else if(c.equals("W")) {
                bid = bid + 49;
                id = id + bid;
            } else if(c.equals("X")) {
                bid = bid + 50;
                id = id + bid;
            } else if(c.equals("Y")) {
                bid = bid + 51;
                id = id + bid;
            } else if(c.equals("Z")) {
                bid = bid + 52;
                id = id + bid;
            } else if(c.equals("0")) {
                bid = bid + 53;
                id = id + bid;
            } else if(c.equals("1")) {
                bid = bid + 54;
                id = id + bid;
            } else if(c.equals("2")) {
                bid = bid + 55;
                id = id + bid;
            } else if(c.equals("3")) {
                bid = bid + 56;
                id = id + bid;
            } else if(c.equals("4")) {
                bid = bid + 57;
                id = id + bid;
            } else if(c.equals("5")) {
                bid = bid + 58;
                id = id + bid;
            } else if(c.equals("6")) {
                bid = bid + 59;
                id = id + bid;
            } else if(c.equals("7")) {
                bid = bid + 60;
                id = id + bid;
            } else if(c.equals("8")) {
                bid = bid + 61;
                id = id + bid;
            } else if(c.equals("9")) {
                bid = bid + 62;
                id = id + bid;
            } else if(c.equals("&")) {
                bid = bid + 63;
                id = id + bid;
            } else if(c.equals("%")) {
                bid = bid + 64;
                id = id + bid;
            } else if(c.equals("$")) {
                bid = bid + 65;
                id = id + bid;
            } else if(c.equals("§")) {
                bid = bid + 66;
                id = id + bid;
            } else if(c.equals("(")) {
                bid = bid + 67;
                id = id + bid;
            } else if(c.equals(")")) {
                bid = bid + 68;
                id = id + bid;
            } else if(c.equals("[")) {
                bid = bid + 69;
                id = id + bid;
            } else if(c.equals("]")) {
                bid = bid + 70;
                id = id + bid;
            } else if(c.equals("=")) {
                bid = bid + 71;
                id = id + bid;
            } else if(c.equals("?")) {
                bid = bid + 72;
                id = id + bid;
            } else if(c.equals("/")) {
                bid = bid + 73;
                id = id + bid;
            } else if(c.equals(".")) {
                bid = bid + 74;
                id = id + bid;
            } else if(c.equals(",")) {
                bid = bid + 75;
                id = id + bid;
            } else if(c.equals(";")) {
                bid = bid + 76;
                id = id + bid;
            } else if(c.equals(":")) {
                bid = bid + 77;
                id = id + bid;
            } else if(c.equals("!")) {
                bid = bid + 78;
                id = id + bid;
            } else if(c.equals("<")) {
                bid = bid + 79;
                id = id + bid;
            } else if(c.equals(">")) {
                bid = bid + 80;
                id = id + bid;
            } else if(c.equals("+")) {
                bid = bid + 81;
                id = id + bid;
            } else if(c.equals("-")) {
                bid = bid + 82;
                id = id + bid;
            } else if(c.equals("*")) {
                bid = bid + 83;
                id = id + bid;
            } else if(c.equals("³")) {
                bid = bid + 84;
                id = id + bid;
            } else if(c.equals("{")) {
                bid = bid + 85;
                id = id + bid;
            } else if(c.equals("}")) {
                bid = bid + 86;
                id = id + bid;
            } else if(c.equals("`")) {
                bid = bid + 87;
                id = id + bid;
            } else if(c.equals("´")) {
                bid = bid + 88;
                id = id + bid;
            } else if(c.equals("~")) {
                bid = bid + 89;
                id = id + bid;
            } else if(c.equals("ä")) {
                bid = bid + 90;
                id = id + bid;
            } else if(c.equals("ö")) {
                bid = bid + 91;
                id = id + bid;
            } else if(c.equals("ü")) {
                bid = bid + 92;
                id = id + bid;
            } else if(c.equals("Ä")) {
                bid = bid + 93;
                id = id + bid;
            } else if(c.equals("Ö")) {
                bid = bid + 94;
                id = id + bid;
            }
        }
        return bid + id;
    }

}
