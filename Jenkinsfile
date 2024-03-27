pipeline {
    agent any
    environment {
        MAVEN_ARGS = " -e clean install"
        registry = ""
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
                    bat 'docker stop dockerapi'
                    bat 'docker rm dockerapi'
                    bat 'docker rmi dockerapi-docker-app'
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
