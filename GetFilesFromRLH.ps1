Set-Variable -Name "RLH" -Value "\\RLHSERVER\Transit\EEG\"
Set-Variable -Name "PRICER" -Value "$Env:PRICER_HOME\FTP\"
Set-Variable -Name "debutGestTime" -Value "04:40:00"
Set-Variable -Name "finGestTime" -Value "16:00:00"

Get-Date -Format "HH:mm:ss"
$Time = Get-Date

$PRICER

$LogFile = "$Env:PRICER_HOME\AdapterCasino\logs\PSLog.txt"



#Purgin log file if exceeds max Size defined
$SizeMax = 20 
$Size = (Get-ChildItem $LogFile| Measure-Object -property length -sum)   
$SizeMb="{0:N2}" -f ($size.sum / 1MB) + "MB" 
 
if ($sizeMb -ge $sizeMax) { 
 
Get-ChildItem $LogFile -Recurse | Remove-Item –Force 
 
} 


#Function that logs a message to a text file
function LogMessage
{
    param([string]$Message, [string]$LogFile)
    
    ((Get-Date).ToString() + " - " + $Message) >> $LogFile;
}

function WriteLog
{
   param([string]$Message) 
 
   LogMessage -Message $Message -LogFile $LogFile
}



<#
    TRAITEMENT DU FICHIER DE PRIX EEG0101
#>


    if ((Test-Path $RLH"EEG0101.001") -and (Test-Path $RLH"flag.eeg")) {
      WriteLog -Message "Found EEG0101.001 and flag.eeg from $RLH"
      
      if(-not (Test-Path $PRICER"EEG0101.001")){
        Copy-Item $RLH"EEG0101.001" -Destination $PRICER
        WriteLog -Message "Copying EEG0101.001 from $RLH to $Pricer"
        Remove-Item $RLH"EEG0101.001"
        Remove-Item $RLH"flag.eeg"
        WriteLog -Message "Delete PRICE file and flag from $RLH"
      }
      else{
        WriteLog -Message "EEG0101 already exist in FTP root, waiting for next execution !"
      }
    }


<#
    TRAITEMENT DU FICHIER DE D'IMPRESSION
#>

    if (Test-Path $RLH"EEGEAN.txt"){
        WriteLog -Message "Found EEGEAN.txt from $RLH"

        if(-Not (Test-Path $PRICER"EEGEAN.txt")){
            Copy-Item $RLH"EEGEAN.txt" -Destination $PRICER
            WriteLog -Message "Copying EEGEAN from $RLH to $PRICER"
            Remove-Item $RLH"EEGEAN.txt"
            WriteLog -Message "Removing REL file from $RLH"
        }
        else{
            WriteLog -Message "EEGEAN already exist in FTP root, waiting next execution !!"
        }
    }



<#
    TRAITEMENT DU FICHIER DE GESTION
#>

if((Test-Path $RLH"EEG0202.001") -and (Test-Path $RLH"Gest.eeg")){
    if(($Time -gt $debutGestTime) -and ($Time -lt $finGestTime)){
        Remove-Item $RLH"EEG0202.001"
        Remove-Item $RLH"Gest.eeg"
        WriteLog -Message "Gest file present outside the defined time range, removing it from $RLH !"}
    
    else{
        Copy-Item $RLH"EEG0202.001" -Destination $PRICER
        WriteLog -Message "Copying EEG0202.001 from $RLH to $PRICER"
        Remove-Item $RLH"EEG0202.001"
        Remove-Item $RLH"Gest.eeg"
        WriteLog -Message "Removing GEST file and flag from $RLH"
    }
}



<#
    TRAITEMENT DU FICHIER EXPORT ETMTFLE
#>

if(Test-Path $RLH"Compar.flag"){

$mysqlnet=[Reflection.Assembly]::LoadWithPartialName("MySql.Data")
if(-not $mysqlnet)
{
    Write-Error "ERROR !"
    WriteLog -Message "Error when trying to connect to DB"
    Exit
}
    Write-Output "Connection OK"
    WriteLog -Message "Connection to DB ok"


# Paramètres de connexion à la base MySQL
$dbuser = "root"
$dbpwd = ""
$dbname = "pricer"
$dbhost = "localhost" 
$dbport = 7799 

$connectionString = "server=$dbhost;port=$dbport;uid=$dbuser;pwd=$dbpwd;database=$dbname"
$conn = New-Object MySql.Data.MySqlClient.MySqlConnection($connectionString) 
$conn.Open()

$query="SELECT master_ean,itemid,price,origin,calibre,category,traitement,variete FROM ``item``,``eclink`` where itemid=linitemidref;"

$MysqlCmd = New-Object MySql.Data.MySqlClient.MySqlCommand($query, $conn)    # Créer la commande SQL
$DataAdapter = New-Object MySql.Data.MySqlClient.MySqlDataAdapter($MysqlCmd) # Créer l'adaptateur depuis la commande
$DataSet = New-Object System.Data.DataSet                                    # Créer le jeu de données
$dataAdapter.Fill($dataSet, "data")                                          # Remplir le jeu de données $dataset et le mettre dans un tableau nommé "data"            

$res=$dataSet.Tables["data"] 

foreach ($item in $res)
{
    "$($item.master_ean);$($item.itemid);$($item.origin);$($item.calibre);$($item.category);$($item.traitement);$($item.variete)" | Out-File -filePath $RLH"ETMFLE.csv" -Append
}


$MysqlCmd.Dispose()

$conn.Close()



}

WriteLog -Message "Not found compar"