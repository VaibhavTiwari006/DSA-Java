@echo off
title DSA Revision Web App
cd /d "%~dp0"

echo ========================================================
echo   Starting DSA Spaced Repetition Revision Web App...
echo ========================================================
echo.

set PYTHON_CMD=python

REM 1. Check if 'python' works
python --version >nul 2>&1
if %ERRORLEVEL% equ 0 goto RUN_SERVER

REM 2. Check if 'py' launcher works
py --version >nul 2>&1
if %ERRORLEVEL% equ 0 (
    set PYTHON_CMD=py
    goto RUN_SERVER
)

REM 3. Check direct Windows Python install path
if exist "%LOCALAPPDATA%\Programs\Python\Python314\python.exe" (
    set PYTHON_CMD="%LOCALAPPDATA%\Programs\Python\Python314\python.exe"
    goto RUN_SERVER
)

REM 4. Fallback if Python cannot be executed
echo [!] Python was not detected in PATH.
echo [!] Opening web app directly in your browser...
start "" "%~dp0web\index.html"
echo.
echo Press any key to close...
pause >nul
exit /b

:RUN_SERVER
echo [*] Python detected: %PYTHON_CMD%
echo [*] Starting Auto-Save Server on http://localhost:8000 ...
start "" http://localhost:8000
%PYTHON_CMD% server.py --no-browser

pause
