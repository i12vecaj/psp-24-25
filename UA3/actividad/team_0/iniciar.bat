@echo off
setlocal enabledelayedexpansion

:: Número de miembros en la red
set NUM_MIEMBROS=3

:: Puerto inicial
set PUERTO=10000

:: Iniciar el primer miembro con el token
start "Miembro 1" java MiembroToken 1 %PUERTO% yes no

:: Iniciar los demás miembros
for /L %%i in (2,1,%NUM_MIEMBROS%) do (
    set /A PUERTO_SIGUIENTE=%PUERTO%+%%i-1
    if %%i==%NUM_MIEMBROS% (
        start "Miembro %%i" java MiembroToken %%i !PUERTO_SIGUIENTE! no yes
    ) else (
        start "Miembro %%i" java MiembroToken %%i !PUERTO_SIGUIENTE! no no
    )
)

endlocal
