@echo off
title Move and Rename Files

mkdir sources
cd sources

del .\LcraftAPI.jar
xcopy "..\..\LcraftAPI-API\target\API-*.jar" ".\" /Y
ren "API-*.jar" "LcraftAPI.jar"

del .\Lcraft Bukkit Test.jar
xcopy "..\..\Lcraft Spigot Test Plugin\target\SpigotTestPlugin-*.jar" ".\" /Y
ren "SpigotTestPlugin-*.jar" "Lcraft Bukkit Test.jar"

del .\Lcraft BungeeCord Test.jar
xcopy "..\..\Lcraft BungeeCord Test Plugin\target\BungeeCordTestPlugin-*.jar" ".\" /Y
ren "BungeeCordTestPlugin-*.jar" "Lcraft BungeeCord Test.jar"

pause