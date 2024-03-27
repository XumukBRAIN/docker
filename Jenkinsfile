pipeline {
    agent any
    environment {
        MAVEN_ARGS = " -e clean install"
        registry = ""
        dockerContainerName = 'dockerapi'
        dockerImageName = 'dockerapi-api'
    }
    stages {
        stage('Build') {
            steps {
                withMaven(maven: 'MAVEN_ENV') {
                    bat 'mvn clean compile package'
                }
            }
        }

        stage('clean container') {
            steps {
                script {
                    bat 'docker ps -f name=${dockerContainerName} -q | xargs --no-run-if-empty docker container stop'
                    bat 'docker container ls -a -fname=${dockerContainerName} -q | xargs -r docker container rm'
                    bat 'docker images -q --filter=reference=${dockerImageName} | xargs --no-run-if-empty docker rmi -f'
                }
            }
        }

        stage('docker-compose start') {
            steps {
                bat 'docker-compose up -d'
            }
        }
    }
}
