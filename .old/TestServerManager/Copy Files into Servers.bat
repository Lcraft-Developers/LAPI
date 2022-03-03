@echo off
title Copy Files to Servers

del .\servers\1.17\Paper\plugins\LcraftAPI (beta).jar
del .\servers\1.17\Purpur\plugins\LcraftAPI (beta).jar
del .\servers\1.17\Spigot\plugins\LcraftAPI (beta).jar
xcopy ".\sources\LcraftAPI (beta).jar" ".\servers\1.17\Paper\plugins\" /Y
xcopy ".\sources\LcraftAPI (beta).jar" ".\servers\1.17\Purpur\plugins\" /Y
xcopy ".\sources\LcraftAPI (beta).jar" ".\servers\1.17\Spigot\plugins\" /Y

del .\servers\1.18\Paper\plugins\LcraftAPI.jar
del .\servers\1.18\Spigot\plugins\LcraftAPI.jar
xcopy ".\sources\LcraftAPI (beta).jar" ".\servers\1.18\Paper\plugins\" /Y
xcopy ".\sources\LcraftAPI (beta).jar" ".\servers\1.18\Spigot\plugins\" /Y


del .\servers\1.17\Paper\plugins\Lcraft Bukkit Test.jar
del .\servers\1.17\Purpur\plugins\Lcraft Bukkit Test.jar
del .\servers\1.17\Spigot\plugins\Lcraft Bukkit Test.jar
xcopy ".\sources\Lcraft Bukkit Test.jar" ".\servers\1.17\Paper\lmodules\" /Y
xcopy ".\sources\Lcraft Bukkit Test.jar" ".\servers\1.17\Purpur\lmodules\" /Y
xcopy ".\sources\Lcraft Bukkit Test.jar" ".\servers\1.17\Spigot\lmodules\" /Y

del .\servers\1.18\Paper\plugins\Lcraft Bukkit Test.jar
del .\servers\1.18\Spigot\plugins\Lcraft Bukkit Test.jar
xcopy ".\sources\Lcraft Bukkit Test.jar" ".\servers\1.18\Paper\lmodules" /Y
xcopy ".\sources\Lcraft Bukkit Test.jar" ".\servers\1.18\Spigot\lmodules\" /Y