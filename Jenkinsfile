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

        stage('docker-compose start') {
            steps {
                bat 'docker-compose up -d'
            }
        }
    }
}
