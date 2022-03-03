@echo off
title Copy Files to Servers

del .\servers\Proxies\BungeeCord\plugins\LcraftAPI (beta).jar
xcopy ".\sources\LcraftAPI (beta).jar" ".\servers\Proxies\BungeeCord\plugins\" /Y
del .\servers\Proxies\Waterfall\plugins\LcraftAPI (beta).jar
xcopy ".\sources\LcraftAPI (beta).jar" ".\servers\Proxies\Waterfall\plugins\" /Y


del .\servers\Proxies\BungeeCord\plugins\Lcraft BungeeCord Test.jar
xcopy ".\sources\Lcraft BungeeCord Test.jar" ".\servers\Proxies\BungeeCord\lmodules\" /Y
del .\servers\Proxies\Waterfall\plugins\Lcraft BungeeCord Test.jar
xcopy ".\sources\Lcraft BungeeCord Test.jar" ".\servers\Proxies\Waterfall\lmodules\" /Y