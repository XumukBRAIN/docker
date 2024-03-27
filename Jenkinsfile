pipeline {
    agent any
  environment {
    MAVEN_ARGS=" -e clean install"
    registry = ""
    dockerContainerName = 'dockerapi'
    dockerImageName = 'dockerapi-api'
  }
  stages {
    stage('Build') {
       steps {
   withMaven(maven: 'MAVEN_ENV') {
            bat'mvn clean compile package'
        }
       }
    }

  stage('clean container') {
      steps {
       script {
           def containerId = sh(script: "docker ps -f name=${dockerContainerName} -q", returnStdout: true).trim()
           if (containerId) {
               sh "docker stop ${containerId}"
               sh "docker rm ${containerId}"
           }
           sh "docker rmi ${dockerImageName}"
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