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

        stage('docker-compose start') {
            steps {
                bat 'docker login'
                bat 'docker update --cpu-shares 512 -m 300M dockerapi'
                bat 'docker-compose up -d'
            }
        }
    }
}
