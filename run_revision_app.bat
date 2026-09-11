@echo off
title DSA Revision Web App
echo ========================================================
echo   Starting DSA Spaced Repetition Revision Web App...
echo ========================================================
echo.

cd /d "%~dp0"

REM Open web app in default browser
start "" "%~dp0web\index.html"

echo Web app opened in your browser!
echo.
echo Press any key to exit this launcher window...
pause >nul
