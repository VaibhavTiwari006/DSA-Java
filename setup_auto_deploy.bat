@echo off
title Setup DSA Revision Auto-Deploy
echo ========================================================
echo   Setting up DSA Revision Permanent Local Deployment...
echo ========================================================
echo.

set STARTUP_DIR=%APPDATA%\Microsoft\Windows\Start Menu\Programs\Startup
set TARGET_FILE=%STARTUP_DIR%\DSA_Revision_Service.vbs
set SOURCE_FILE=%~dp0dsa_revision_silent.vbs

echo [*] Installing silent auto-start service to:
echo     %TARGET_FILE%
echo.

copy /y "%SOURCE_FILE%" "%TARGET_FILE%" >nul

if %ERRORLEVEL% equ 0 (
    echo [OK] Successfully installed to Windows Startup!
    echo [*] Starting silent background server right now...
    wscript "%TARGET_FILE%"
    echo.
    echo ========================================================
    echo   SUCCESS! The website is now permanently deployed locally.
    echo   - URL: http://localhost:8000
    echo   - It will run silently in the background on every PC boot.
    echo   - All changes auto-save directly to your Excel sheet!
    echo ========================================================
) else (
    echo [!] Failed to copy to Startup folder.
)

echo.
pause
