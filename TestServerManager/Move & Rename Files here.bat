@echo off
title Move and Rename Files

mkdir sources
cd sources

remove .\LcraftAPI.jar
xcopy "..\..\LcraftAPI-API\target\API-*.jar" ".\" /Y
ren "API-*.jar" "LcraftAPI.jar"

remove .\Lcraft Bukkit Test.jar
xcopy "..\..\LcraftAPI-Spigot-TestPlugin\target\SpigotTest-*.jar" ".\" /Y
ren "SpigotTest-*.jar" "Lcraft Bukkit Test.jar"

remove .\Lcraft BungeeCord Test.jar
xcopy "..\..\Lcraft-BungeeCord-TestPlugin\target\BungeeCordTest-*.jar" ".\" /Y
ren "BungeeCordTest-*.jar" "Lcraft BungeeCord Test.jar"