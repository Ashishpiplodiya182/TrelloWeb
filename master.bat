cd gridsetup
start cmd /k call hub.bat
start cmd /k call node.bat
start cmd /k call node1.bat
timeout 20
cd..
start cmd /k call suite.bat