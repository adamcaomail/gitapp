# create & test code from local. build and run from docker,
# push to github
    1. git add .
    2. git commit -m "version 1 spring boot webapp"
    3. git branch -M main
    4. git remote add origin https://github.com/adamcaomail/gitapp.git
    5. git push -u origin main
# docker git clone,mvn package, and deploy to docker tomcat container
## setting build-arg variable
```
    git_url=?
    project_name=?
    project_version=?
```
'''
docker build \
--build-arg git_url=https://github.com/adamcaomail/gitapp.git \
--build-arg project_name=gitapp \
--build-arg project_version=0.0.1-SNAPSHOT \
-t chapinme/git .
'''