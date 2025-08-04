pipeline {
    agent any
    environment {
      JIRA_BASE_URL = 'https://belhassenahmed543-1750321487947.atlassian.net'
      FEIGN_CLIENT_USERNAME = 'belhassen.ahmed543@gmail.com'
      FEIGN_CLIENT_API_TOKEN = 'ATATT3xFfGF0lhVXDFD-VM_m_Kts5mHgU5c26sy6YHZ-entkzVLphWNi6sRkU4s4wWOZ1rsdlF2fVmmLUr7ur-slDitDh8q6a0Cdx5SDowIe_9DLq7cU1uOVMmkNDG1Azx48veCUXhAzVdKHkAF_DCwz6S2lNFdrw0E7kJ6jE_45lPQ7ISysw74=9CC3867B'
    }
    stages {
        stage('Checkout code') {
            steps {
                git branch: 'main', url: 'https://github.com/Ahmedbelhassenn/SprintDashboard.git'
            }
        }

        stage('Linting') {
            steps {
                echo 'Running Checkstyle...'
                dir('SprintDash_Backend') {
                    bat 'mvnw.cmd checkstyle:check'
                }
            }
        }

        stage('Build avec Maven') {
            steps {
                echo 'Building the application...'
                dir('SprintDash_Backend') {
                    bat 'mvnw.cmd clean package -DskipTests'
                }
            }
        }

        stage('Test') {
            steps {
                echo '🧪 Running unit tests...'
                dir('SprintDash_Backend') {
                    bat 'mvn test'
                }
            }
        }

        stage('Docker Build') {
            steps {
                echo '🐳 Building Docker image...'
                dir('SprintDash_Backend') {
                    bat "docker build -t %IMAGE_NAME% ."
                }
            }
        }

        stage('Docker Compose Up') {
            steps {
                echo '🚀 Running Docker Compose...'
                dir('SprintDash_Backend') {
                    bat 'docker-compose down'
                    bat 'docker-compose up -d'
                }
            }
        }
    }

    post {
        always {
            echo '📦 Pipeline finished'
        }
        failure {
            echo '❌ Build failed!'
        }
        success {
            echo '✅ Build succeeded!'
        }
    }
}
