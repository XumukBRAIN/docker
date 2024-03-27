pipeline {
    agent any
    environment {
        MAVEN_ARGS = " -e clean install"
        registry = ""
        dockerContainerName = 'dockerapi'
        dockerImageName = 'dockerapi-docker-app'
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
                    bat 'docker rm ${dockerContainerName}'
                    bat 'docker rmi ${dockerImageName}'
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
