
# docker git clone,mvn package, and deploy to docker tomcat container
'''
docker build \
--build-arg git_url=https://github.com/adamcaomail/gitapp.git \
--build-arg project_name=gitapp \
--build-arg project_version=0.0.1-SNAPSHOT \
-t chapinme/git .
'''