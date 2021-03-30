:loop 
git pull 
git add *.* 
set timestamp=%DATE% %TIME% 
git commit -m "%timestamp%" 
git push origin master 
timeout 60 
goto loop

