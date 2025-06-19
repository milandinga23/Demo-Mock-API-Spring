pipeline {
    agent any

    parameters {
        choice(name: 'ACTION', choices: ['start', 'stop'], description: 'Start or stop the Spring Boot Docker container')
    }

    environment {
        APP_JAR = 'target\\mock-api-build.jar'
        IMAGE_NAME = 'mockapi:latest'
        CONTAINER_NAME = 'mockapi-container'
        JAVA_HOME = 'C:\\Program Files\\Amazon Corretto\\jdk17.0.13_11'
        PATH = "${env.JAVA_HOME}\\bin;${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/milandinga23/Demo-Mock-API-Spring', branch: 'master'
            }
        }

        stage('Build Jar') {
            when {
                expression { params.ACTION == 'start' }
            }
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            when {
                expression { params.ACTION == 'start' }
            }
            steps {
                bat 'docker build -t %IMAGE_NAME% .'
            }
        }

        stage('Run Container') {
            when {
                expression { params.ACTION == 'start' }
            }
            steps {
                bat 'docker rm -f %CONTAINER_NAME% || echo Container not found'
                bat 'docker run -d --name %CONTAINER_NAME% -p 8081:8081 %IMAGE_NAME%'
                echo 'Docker container started.'
            }
        }

        stage('Stop Container') {
            when {
                expression { params.ACTION == 'stop' }
            }
            steps {
                bat 'docker stop %CONTAINER_NAME% || echo Container not running'
                bat 'docker rm %CONTAINER_NAME% || echo Container already removed'
                echo 'Docker container stopped and removed.'
            }
        }
    }
}
