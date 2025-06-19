pipeline {
    agent any

    parameters {
        choice(name: 'ACTION', choices: ['start', 'stop'], description: 'Start or stop the Spring Boot app')
    }

    environment {
        APP_JAR = 'mock-api-build.jar'
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
            when {
                expression { params.ACTION == 'start' }
            }
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Start App') {
            when {
                expression { params.ACTION == 'start' }
            }
            steps {
                // spusti appku na pozadí
                bat 'start /b java -jar ' + APP_JAR
                echo "Spring Boot app started."
            }
        }

        stage('Stop App') {
            when {
                expression { params.ACTION == 'stop' }
            }
            steps {
                // ukonci vsetky java procesy
                bat 'taskkill /F /IM java.exe'
                echo "Spring Boot app stopped."
            }
        }
    }
}
