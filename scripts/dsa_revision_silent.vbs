Set WshShell = CreateObject("WScript.Shell")
WshShell.CurrentDirectory = "C:\Users\Vaibhav\DSA-Java"
WshShell.Run """C:\Users\Vaibhav\AppData\Local\Programs\Python\Python314\python.exe"" ""C:\Users\Vaibhav\DSA-Java\scripts\server.py"" --no-browser", 0, False
