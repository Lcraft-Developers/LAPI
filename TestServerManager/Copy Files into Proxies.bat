@echo off
title Copy Files to Servers

xcopy ".\sources\LcraftAPI.jar" ".\servers\Proxies\BungeeCord\plugins\" /Y
xcopy ".\sources\LcraftAPI.jar" ".\servers\Proxies\Waterfall\plugins\" /Y


xcopy ".\sources\Lcraft Bukkit Test.jar" ".\servers\Proxies\BungeeCord\lmodules\" /Y
xcopy ".\sources\Lcraft Bukkit Test.jar" ".\servers\Proxies\Waterfall\lmodules\" /Y