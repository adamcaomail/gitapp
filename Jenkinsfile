pipeline{
    agent any
    options {
        skipStagesAfterUnstable()
    }
    environment{
        GIT_USER='username'
        GIT_URL='https://github.com/adamcaomail/gitapp.git'
        APP_NAME='gitapp'
        CODE_VERSION='0.0.1-SNAPSHOT'
        IMAGE_NAME="javawebapp"
        // env.BUILD_NUMBER='1.0'
    }
    stages{
        stage("clone project"){
            steps{
                script{
                    checkout scm
                }
            }
        }
        // stage("build-image"){
        //     steps{
        //         sh "
        //         docker build \
        //         --build-arg git_url=${GIT_URL} \
        //         --build-arg project_name=${APP_NAME} \
        //         --build-arg project_version=${CODE_VERSION} \
        //         -t ${GIT_USER}/${IMAGE_NAME}:${env.BUILD_NUMBER} .
        //         "
        //         echo "image ${GIT_USER}/${IMAGE_NAME}:${env.BUILD_NUMBER} created"
        //         sh "docker push ${GIT_USER}/${IMAGE_NAME}:${env.BUILD_NUMBER}"
        //         echo "image ${GIT_USER}/${IMAGE_NAME}:${env.BUILD_NUMBER} pushed to docker hub"
        //         sh "docker rmi ${GIT_USER}/${IMAGE_NAME}:${env.BUILD_NUMBER}"
        //         echo "${GIT_USER}/${IMAGE_NAME}:${env.BUILD_NUMBER} docker image removed from local"
        //     }
        // }
        stage("docker iamge build"){
            steps{
                script{
                    def buildArgs = """\
                    --build-arg git_url=${GIT_URL}\
                    --build-arg project_name=${APP_NAME}\
                    --build-arg project_version=${CODE_VERSION}\
                    -f Dockerfile .\
                    """
                    app=docker.build("${GIT_USER}/${IMAGE_NAME}:${env.BUILD_NUMBER}",buildArgs)
                    echo "docker image is created: ${GIT_USER}/${IMAGE_NAME}:${env.BUILD_NUMBER}"
                }
            }
        }
        stage("push to dockerhub"){
            environment {
               registryCredential = 'dockerhub'
           }
            steps{
                script{
                    docker.withRegistry("",registryCredential){
                        app.push("${GIT_USER}/${IMAGE_NAME}")
                        app.push("${env.BUILD_NUMBER}")
                    }
                    echo "docker image pushed to dockerhub: ${GIT_USER}/${IMAGE_NAME}:${env.BUILD_NUMBER}"
                }
            }
        }
        stage("test"){
            steps{
                sh "docker run --name webapp -p 8088:8088 ${GIT_USER}/${IMAGE_NAME}:${env.BUILD_NUMBER}"
                sh "curl http:localhost:8088/webapp/${APP_NAME}/ping"
            }
        }
    }
}