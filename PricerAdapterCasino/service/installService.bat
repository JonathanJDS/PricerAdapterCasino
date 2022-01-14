@echo on
cd %~dp0

echo "installing Application into Service mode"
winsw.exe install
echo "installation successufull"
echo " starting Service"
net start PricerAdapterCasino
echo " service PricerAdapterCasino started successfully !!! "
pause