@echo off
title Copy Files to Servers

xcopy ".\sources\LcraftAPI.jar" ".\servers\1.17\Paper\plugins\" /Y
xcopy ".\sources\LcraftAPI.jar" ".\servers\1.17\Purpur\plugins\" /Y
xcopy ".\sources\LcraftAPI.jar" ".\servers\1.17\Spigot\plugins\" /Y

xcopy ".\sources\LcraftAPI.jar" ".\servers\1.18\Paper\plugins\" /Y
xcopy ".\sources\LcraftAPI.jar" ".\servers\1.18\Spigot\plugins\" /Y


xcopy ".\sources\Lcraft Bukkit Test.jar" ".\servers\1.17\Paper\lmodules\" /Y
xcopy ".\sources\Lcraft Bukkit Test.jar" ".\servers\1.17\Purpur\lmodules\" /Y
xcopy ".\sources\Lcraft Bukkit Test.jar" ".\servers\1.17\Spigot\lmodules\" /Y

xcopy ".\sources\Lcraft Bukkit Test.jar" ".\servers\1.18\Paper\lmodules" /Y
xcopy ".\sources\Lcraft Bukkit Test.jar" ".\servers\1.18\Spigot\lmodules\" /Y