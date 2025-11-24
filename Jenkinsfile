pipeline {
    agent any

    environment {
        IMAGE_NAME = "andprof/ac2_ca"
        IMAGE_TAG = "latest"
    }

    stages {

        /* ========== DEV PIPELINE ========== */
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test (DEV)') {
            steps {
                echo "Building and running tests..."
                bat './mvnw clean package -DskipTests=false'
            }
        }

        /* ========== IMAGE DOCKER ========== */
        stage('Build Docker Image') {
            steps {
                echo "Building Docker Image..."
                bat "docker build -t %IMAGE_NAME%:%IMAGE_TAG% ."
            }
        }

        stage('Push Docker Image') {
            steps {
                echo "Pushing to Docker Hub..."
                bat "docker push %IMAGE_NAME%:%IMAGE_TAG%"
            }
        }

        /* ========== STAGING PIPELINE ========== */
        stage('Deploy Staging') {
            steps {
                echo "Deploying with docker-compose.staging.yml"
                bat 'docker-compose -f docker-compose.staging.yml pull'
                bat 'docker-compose -f docker-compose.staging.yml up -d --no-color'
                sleep time: 60, unit: 'SECONDS'
                bat 'docker-compose -f docker-compose.staging.yml logs'
            }
        }

        stage('Health Check (Staging)') {
            steps {
                echo "Testing service health..."
                bat 'curl http://localhost:8686 || echo "Service not responding"'
            }
        }
    }

    post {
        always {
            echo "Pipeline finished!"
        }
    }
}
