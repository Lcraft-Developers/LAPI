@echo off
title Pupur Server

:start
echo Pupur Server starting...
java -jar Pupur-1.17.1-latest.jar nogui 
echo.
echo.
echo.
pause
goto start
