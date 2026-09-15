@echo off
title DSA Revision Web App
echo ========================================================
echo   Starting DSA Spaced Repetition Revision Web App...
echo   Auto-Save: Every revision is saved directly to disk & Excel!
echo ========================================================
echo.

cd /d "%~dp0"

python server.py

pause
