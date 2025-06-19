pipeline {
    agent any

    environment {
        APP_JAR = 'target\\mock-api-build.jar'
        JAVA_HOME = 'C:\\Program Files\\Amazon Corretto\\jdk17.0.13_11'
        PATH = "${env.JAVA_HOME}\\bin;${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/milandinga23/Demo-Mock-API-Spring', branch: 'master'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Start App') {
            steps {
                // spusti appku na popredí
                bat "java -jar ${APP_JAR}"
                echo "Spring Boot app started."
            }
        }
    }
}
