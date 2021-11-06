@echo off
title Move Plugins

:moving
echo Deleting old Spigot Versions
del ".\Spigot\plugins\LcraftAPI.API-1.0.0.jar"
del ".\Spigot\lmodules\Lcraft.API.Language-1.0-SNAPSHOT.jar"
del ".\Spigot\lmodules\Lcraft.API.Permissions-1.0-SNAPSHOT.jar"

del ".\Spigot\lmodules\LcraftAPI.spigot.test-1.0.0.jar"


echo Copy Spigot Versions
copy "..\LcraftAPI-API\target\LcraftAPI.API-1.0.0.jar" ".\Spigot\plugins\"
copy "..\Lcraft-Permissions-API\target\Lcraft.API.Permissions-1.0-SNAPSHOT.jar" ".\Spigot\lmodules\"
copy "..\Lcraft-Languages-API\target\Lcraft.API.Language-1.0-SNAPSHOT.jar" ".\Spigot\lmodules\"

copy "..\LcraftAPI-Spigot-TestPlugin\target\LcraftAPI.spigot.test-1.0.0.jar" ".\Spigot\lmodules\"





echo Delete Bungee Versions
del ".\BungeeCord\plugins\LcraftAPI.API-1.0.0.jar"
del ".\BungeeCord\lmodules\Lcraft.API.Language-1.0-SNAPSHOT.jar"
del ".\BungeeCord\lmodules\Lcraft.API.Permissions-1.0-SNAPSHOT.jar"

del ".\BungeeCord\lmodules\LcraftAPI.spigot.test-1.0.0.jar"


echo Copy Bungee Versions
copy "..\LcraftAPI-API\target\LcraftAPI.API-1.0.0.jar" ".\BungeeCord\plugins\"
copy "..\Lcraft-Permissions-API\target\Lcraft.API.Permissions-1.0-SNAPSHOT.jar" ".\BungeeCord\lmodules\"
copy "..\Lcraft-Languages-API\target\Lcraft.API.Language-1.0-SNAPSHOT.jar" ".\BungeeCord\lmodules\"

copy "..\Lcraft-BungeeCord-TestPlugin\target\LcraftAPI.bungeecord.test-1.0.0.jar" ".\BungeeCord\lmodules\"

pause
goto moving