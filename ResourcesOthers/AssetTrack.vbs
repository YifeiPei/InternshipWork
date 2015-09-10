'AssetTrack v2 - Jarred Pudney

Remote = 0

On Error Resume Next
	
rem Get target list
	Set objFSO = CreateObject("Scripting.FileSystemObject")
	If wscript.Arguments.Count = 1 Then
		wscript.echo "Please specify log file."
		wscript.quit
	End If
	If wscript.Arguments.Count = 2 Then
		Set objTargetList = objFSO.OpenTextFile(wscript.Arguments(0), 1, False)
		Set objLogfile = objFSO.OpenTextFile(wscript.Arguments(1), 2, True, 0)
		Do While objTargetList.AtEndOfStream <> True
			Target = objTargetList.Readline
			Set objPing = GetObject("winmgmts:{impersonationLevel=impersonate}").ExecQuery ("select * from Win32_PingStatus where address = '" & Target & "'")
			For each objRetStatus in objPing
				If IsNull(objRetStatus.StatusCode) or objRetStatus.StatusCode <> 0 then
					wscript.echo date & " " & time & Vbtab & Target & Vbtab & "--- Offline ---"
					objLogfile.writeline date & " " & time & Vbtab & Target & Vbtab & "--- Offline ---"
				Else 
					wscript.echo date & " " & time & Vbtab & Target & Vbtab & "--- Querying ---"
					objLogfile.Writeline date & " " & time & Vbtab & Target & Vbtab & "--- Querying ---"
					Audit(Target)
				End If
			Next
		Loop
		objLogfile.Close
	Else
		Audit("127.0.0.1")
	End If
	
	Sub Audit(Computer)
	
rem set source
	If Computer = "127.0.0.1" Then
		Source = "Local/Startup"
		If Remote = 1 Then
			Source = "Remote"
		End If
	Else
		Source = "Network"
	End If
	
rem Set data sources
	Set objCIMv2 = GetObject("winmgmts:{impersonationLevel=impersonate}!\\" & Computer & "\root\cimv2")
	Set objWMI = GetObject("winmgmts:{impersonationLevel=impersonate}!\\" & Computer & "\root\WMI")
	Set objReg = GetObject("winmgmts:{impersonationLevel=impersonate}!\\" & Computer & "\root\default:StdRegProv")
	
rem DNS Name from CIMv2
	DNS_Name = ""
	Set colItems = objCIMv2.ExecQuery("Select * from Win32_OperatingSystem")
	For Each objItem in colItems
		DNS_Name = objItem.csname 
	Next
	If DNS_Name = "" Then
		Exit Sub
	End If

rem PC Serial and PC Model from CIMv2
	PC_Serial = ""
	PC_Model = ""
	Set colItems = objCIMv2.ExecQuery("Select * from Win32_ComputerSystemProduct")
	For Each objItem in colItems
		PC_Serial = objItem.IdentifyingNumber
		PC_Model = objItem.Name
	Next

rem Monitor Serial and Monitor Model from WMI / Registry
	intMonitorCount = 0
	Set colItems = objCIMv2.ExecQuery("Select * from  Win32_SystemDevices")
	For Each objItem in colItems
		If Instr(objItem.PartComponent,"DISPLAY") > 0 Then
			Monitor_PNP_ID_Start = Instr(objItem.PartComponent,"=") + 1
			Monitor_PNP_ID = Mid(objItem.PartComponent,Monitor_PNP_ID_Start)
			Monitor_PNP_ID = Replace(Monitor_PNP_ID,"""","")
			Monitor_PNP_ID = Replace(Monitor_PNP_ID,"\\","\")
			objReg.GetBinaryValue &H80000002,"System\CurrentControlSet\Enum\" & Monitor_PNP_ID & "\Device Parameters","EDID",arrEDID
			If Typename(arrEDID) <> "Null" Then
				strEDID = ""
				For each bytevalue in arrEDID
					strEDID = strEDID & Chr(bytevalue)
				Next
				ReDim Preserve arrMonitor_PNP_ID(intMonitorCount)
				ReDim Preserve arrMonitor_Manufacture_Date(intMonitorCount)
				ReDim Preserve arrMonitor_Model(intMonitorCount)
				ReDim Preserve arrMonitor_Serial(intMonitorCount)
				arrMonitor_PNP_ID(intMonitorCount) = Monitor_PNP_ID
				arrMonitor_Manufacture_Date(intMonitorCount) = Month(DateAdd("WW",Asc(Mid(strEDID,17,1)),"1/1/1990")) & "/" & Asc(Mid(strEDID,18,1)) + 1990
				intMonitorModelStart = Instr(strEDID,chr(&H00) & chr(&H00) & chr(&H00) & chr(&Hfc)) + 5
				intMonitorModelEnd = Instr(intMonitorModelStart, strEDID, chr(&Ha))
				arrMonitor_Model(intMonitorCount) = Mid(strEDID,intMonitorModelStart,(intMonitorModelEnd - intMonitorModelStart))
				intMonitorSerialStart = Instr(strEDID,chr(&H00) & chr(&H00) & chr(&H00) & chr(&Hff)) + 5
				intMonitorSerialEnd = Instr(intMonitorSerialStart, strEDID, chr(&Ha))
				arrMonitor_Serial(intMonitorCount) = Mid(strEDID,intMonitorSerialStart,(intMonitorSerialEnd - intMonitorSerialStart))
				intMonitorCount = intMonitorCount + 1 
			End If
		End If
	Next

rem Windows User from CIM / User dialogue
	Windows_User = ""
	Set colItems = objCIMv2.ExecQuery("Select * from Win32_ComputerSystem")
	For Each objItem in colItems
		Windows_User = objItem.UserName 
	Next
	If Remote = 1 Then
		If Not objFSO.FolderExists("C:\AssetTrack\") Then
			objFSO.CreateFolder "C:\AssetTrack\"
		End If
		If Not objFSO.FileExists("C:\AssetTrack\AssetTrack.vbs") Then
			objFSO.CopyFile Wscript.ScriptFullName, "C:\AssetTrack\AssetTrack.vbs"
		End If
		If objFSO.FileExists("C:\AssetTrack\UserData.txt") Then
			Set UserData = objFSO.OpenTextFile("C:\AssetTrack\UserData.txt", 1, True)
			Windows_User = UserData.Readall
		Else
			Do while Remote_User = ""
				Remote_User = Inputbox("Please enter your name:" & vbcrlf & "(e.g. John Smith)")
			Loop
			Do while Remote_Office = ""
				Remote_Office = Inputbox("Please enter the location of your office:" & vbcrlf & "(e.g. Aberfoyle Park Community Centre)")
			Loop
			Windows_User = Remote_User & "," & Remote_Office
			Set UserData = objFSO.OpenTextFile("C:\AssetTrack\UserData.txt", 2, True)
			UserData.Write Windows_User
			UserData.Close 
		End If
		objReg.SetStringValue &H80000002,"SOFTWARE\Microsoft\Windows\CurrentVersion\Run","AssetTrack","""C:\AssetTrack\AssetTrack.vbs"""
	End If
	
rem MAC Address and IP Address from CIM
	IP_Address_Number = 0
	Set colItems = objCIMv2.ExecQuery("Select * from Win32_NetworkAdapterConfiguration Where IPEnabled = True")
	For Each objItem in colItems
		For Each objIPAddress in objItem.IPAddress
			ReDim Preserve arrNetwork_Addresses(IP_Address_Number)
			arrNetwork_Addresses(IP_Address_Number) = objItem.MACAddress & "/" & objIPAddress
			IP_Address_Number = IP_Address_Number + 1
		Next
	Next
	
rem Operating System from CIM
	Operating_System = ""
	Set colItems = objCIMv2.ExecQuery("Select * from Win32_OperatingSystem")
	For Each objItem in colItems
		Operating_System = objItem.Caption
		If objItem.CSDVersion <> "" Then
			Operating_System = Operating_System & " - " & objItem.CSDVersion
		End If
	Next

rem OS Architecture from CIM
	OS_Architecture = ""
	Set colItems = objCIMv2.ExecQuery("Select * from Win32_Processor")
	For Each objItem in colItems
		OS_Architecture = objItem.AddressWidth & "-bit" 
	Next
	
rem Internet Explorer version
	IE_Version = ""
	objReg.GetStringValue &H80000002,"SOFTWARE\Microsoft\Internet Explorer","Version",IE_Version
	
rem Java version from System32 Folder
	If OS_Architecture = "32-bit" Then
		If objFSO.FileExists("\\" & Computer & "\c$\windows\system32\java.exe") Then
			JavaVersion = objFSO.GetFileVersion("\\" & Computer & "\c$\windows\system32\java.exe") & " / N/A"
		Else
			JavaVersion = "N/A / N/A"
		End If
	Else	
		If objFSO.FileExists("\\" & Computer & "\c$\windows\sysWoW64\java.exe") Then
			JavaVersion = objFSO.GetFileVersion("\\" & Computer & "\c$\windows\sysWoW64\java.exe")
		Else
			JavaVersion = "N/A"
		End If
		If objFSO.FileExists("\\" & Computer & "\c$\windows\system32\java.exe") Then
			JavaVersion = JavaVersion & " / " & objFSO.GetFileVersion("\\" & Computer & "\c$\windows\system32\java.exe")
		Else
			JavaVersion = JavaVersion & " / N/A"
		End If
	End If

rem Installed software from Registry
	Installed_Software = ""
	objReg.EnumKey &H80000002,"Software\Microsoft\Windows\CurrentVersion\Uninstall", arrSubKeys
	Software_Count = 0
	For Each subkey In arrSubKeys
		objReg.GetStringValue &H80000002,"Software\Microsoft\Windows\CurrentVersion\Uninstall\" & subkey,"DisplayName",Software_Name
		If Software_Name <> "" Then
			ReDim Preserve arrInstalled_Software(Software_Count)
			arrInstalled_Software(Software_Count) = Software_Name
			Software_Count = Software_Count + 1
		End If
	Next

rem Output text file
	If Remote = 1 Then
		Set objPCAssetFile = objFSO.OpenTextFile("C:\AssetTrack\PC Asset - " & PC_Serial & ".txt", 2, True, 0)
	Else
		Set objPCAssetFile = objFSO.OpenTextFile("PC Asset - " & PC_Serial & ".txt", 2, True, 0)
	End If
	objPCAssetFile.Writeline "DNS Name: " & DNS_Name
	objPCAssetFile.Writeline "PC Serial: " & PC_Serial
	objPCAssetFile.Writeline "PC Model: " & PC_Model
	objPCAssetFile.Writeline "Windows User: " & Windows_User
	objPCAssetFile.Writeline "Network_Address(es): " & join(arrNetwork_Addresses,", ")
	objPCAssetFile.Writeline "Operating System: " & Operating_System 
	objPCAssetFile.Writeline "OS Architecture: " & OS_Architecture
	objPCAssetFile.Writeline "IE Version: " & IE_Version 
	objPCAssetFile.Writeline "Java Version (x32/x64): " & JavaVersion
	objPCAssetFile.Writeline "Audit Date: " & Date 
	objPCAssetFile.Writeline "Audit Time: " & Time
	objPCAssetFile.Writeline "Source: " & Source & Vbcrlf 
	objPCAssetFile.Writeline "--- Installed Software ---"
	objPCAssetFile.Writeline Join(arrInstalled_Software,vbcrlf)
	objPCAssetFile.Close
	If Not IsEmpty(arrMonitor_Serial) Then
		For Monitor_Number = 0 to UBound(arrMonitor_Serial)
			If Remote = 1 Then
				Set objMonitorAssetFile = objFSO.OpenTextFile("C:\AssetTrack\Monitor Asset - " & arrMonitor_Serial(Monitor_Number) & ".txt", 2, True, 0)
			Else
				Set objMonitorAssetFile = objFSO.OpenTextFile("Monitor Asset - " & arrMonitor_Serial(Monitor_Number) & ".txt", 2, True, 0)
			End If
			objMonitorAssetFile.Writeline "Monitor Serial: " & arrMonitor_Serial(Monitor_Number)
			objMonitorAssetFile.Writeline "Monitor Model: " & arrMonitor_Model(Monitor_Number)
			objMonitorAssetFile.Writeline "Monitor Manufacture Date: " & arrMonitor_Manufacture_Date(Monitor_Number)
			objMonitorAssetFile.Writeline "Monitor PNP ID: " & arrMonitor_PNP_ID(Monitor_Number)
			objMonitorAssetFile.Writeline "DNS Name: " & DNS_Name
			objMonitorAssetFile.Writeline "PC Serial: " & PC_Serial
			objMonitorAssetFile.Writeline "PC Model: " & PC_Model
			objMonitorAssetFile.Writeline "Windows User: " & Windows_User
			objMonitorAssetFile.Writeline "Network_Address(es): " & join(arrNetwork_Addresses,", ")
			objMonitorAssetFile.Writeline "Audit Date: " & Date
			objMonitorAssetFile.Writeline "Audit Time: " & Time
			objMonitorAssetFile.Writeline "Source: " & Source
			objMonitorAssetFile.Close
		Next
	End If
	
Rem Email remote asset files
	If remote = 1 Then
		Set objMessage = CreateObject("CDO.Message")
		objMessage.From = "onkaasset@gmail.com"
		objMessage.To = "onkaasset@gmail.com"
		objMessage.Subject = Windows_User & ":" & PC_Serial & "/" & Join(arrMonitor_Serial,",")
		Set objFolder = objFSO.GetFolder("C:\AssetTrack")
		Set colFiles = objFolder.Files
		For Each objFile in colFiles
			If Instr(objFile.Name,"Asset") > 0 Then
				If Instr(objFile.Name,"txt") > 0 Then
					objMessage.AddAttachment "C:\AssetTrack\" & objFile.Name
				End If
			End If
		Next
		objMessage.Configuration.Fields.Item ("http://schemas.microsoft.com/cdo/configuration/smtpserver") = "smtp.gmail.com"
		objMessage.Configuration.Fields.Item ("http://schemas.microsoft.com/cdo/configuration/smtpserverport") = 465
		objMessage.Configuration.Fields.Item ("http://schemas.microsoft.com/cdo/configuration/sendusing") = 2
		objMessage.Configuration.Fields.Item ("http://schemas.microsoft.com/cdo/configuration/smtpauthenticate") = 1
		objMessage.Configuration.Fields.Item ("http://schemas.microsoft.com/cdo/configuration/smtpusessl") = true
		objMessage.Configuration.Fields.Item ("http://schemas.microsoft.com/cdo/configuration/sendusername") = "onkaasset@gmail.com"
		objMessage.Configuration.Fields.Item ("http://schemas.microsoft.com/cdo/configuration/sendpassword") = "0nk445537"
		objMessage.Configuration.Fields.Update
		objMessage.Send
	End If

	End Sub
Rem End