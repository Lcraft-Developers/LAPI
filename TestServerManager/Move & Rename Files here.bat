@echo off
title Move and Rename Files

mkdir sources
cd sources

del .\LcraftAPI.jar
xcopy "..\..\latest\LcraftAPI-API\target\api-*.jar" ".\" /Y
ren "API-*.jar" "LcraftAPI.jar"

del .\LcraftAPI (beta).jar
xcopy "..\..\beta\LcraftAPI-API (beta)\target\api-beta-*.jar" ".\" /Y
ren "API-*.jar" "LcraftAPI (beta).jar"

del .\Lcraft Bukkit Test.jar
xcopy "..\..\latest\Lcraft Spigot Test Plugin\target\spigot-testplugin-*.jar" ".\" /Y
ren "SpigotTestPlugin-*.jar" "Lcraft Bukkit Test.jar"

del .\Lcraft BungeeCord Test.jar
xcopy "..\..\latest\Lcraft BungeeCord Test Plugin\target\bungeecord-testplugin-*.jar" ".\" /Y
ren "BungeeCordTestPlugin-*.jar" "Lcraft BungeeCord Test.jar"

pause