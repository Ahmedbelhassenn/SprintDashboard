pipeline {
    agent any
    environment {
        JIRA_BASE_URL = credentials('JIRA_BASE_URL')
        FEIGN_CLIENT_USERNAME = credentials('FEIGN_CLIENT_USERNAME')
        FEIGN_CLIENT_API_TOKEN = credentials('FEIGN_CLIENT_API_TOKEN')
        POSTGRES_DB = credentials('POSTGRES_DB')
        POSTGRES_USER = credentials('POSTGRES_USER')
        POSTGRES_PASSWORD = credentials('POSTGRES_PASSWORD')
        SSH_KEY = 'C:\\Users\\USER\\.ssh\\jenkins_id_rsa'  // chemin clé SSH Jenkins
        VM_USER = 'ahmed_bh'
        VM_HOST = '127.0.0.1'
        VM_PORT = '2222'
    }

    stages {
        stage('Checkout code') {
            steps {
                git branch: 'main', url: 'https://github.com/Ahmedbelhassenn/SprintDashboard.git'
            }
        }

        // -------- FRONTEND --------
        stage('Frontend Lint & Build') {
            steps {
                dir('SprintDash_Frontend/SprintDash') {
                    bat 'npm install'
                    bat 'npm run lint'
                    bat 'ng build --configuration=production'
                }
            }
        }

        // -------- BACKEND --------
        stage('Backend Build & Test') {
            steps {
                dir('SprintDash_Backend') {
                    bat 'mvnw.cmd clean package -DskipTests'
                    bat 'mvn test'
                }
            }
        }

        // -------- Docker Images --------
        stage('Docker Build Local Images') {
            steps {
                echo '🐳 Building local images...'
                dir('.') {
                    bat 'docker build -t sprintdash-backend:latest ./SprintDash_Backend'
                    bat 'docker build -t sprintdash-frontend:latest ./SprintDash_Frontend/SprintDash'
                }
            }
        }

        // -------- Deploy to VM --------
        stage('Deploy on VM') {
            steps {
                echo '🚀 Deploying containers on VM using Docker Compose...'

                // Copier docker-compose.yml et .env sur la VM
                bat """
                scp -i ${SSH_KEY} -P ${VM_PORT} docker-compose.yml ${VM_USER}@${VM_HOST}:~/deploy/
                echo POSTGRES_DB=${POSTGRES_DB} > temp_env
                echo POSTGRES_USER=${POSTGRES_USER} >> temp_env
                echo POSTGRES_PASSWORD=${POSTGRES_PASSWORD} >> temp_env
                echo JIRA_BASE_URL=${JIRA_BASE_URL} >> temp_env
                echo FEIGN_CLIENT_USERNAME=${FEIGN_CLIENT_USERNAME} >> temp_env
                echo FEIGN_CLIENT_API_TOKEN=${FEIGN_CLIENT_API_TOKEN} >> temp_env
                scp -i ${SSH_KEY} -P ${VM_PORT} temp_env ${VM_USER}@${VM_HOST}:~/deploy/.env
                """

                // Lancer Docker Compose sur la VM
                bat """
                ssh -i ${SSH_KEY} -p ${VM_PORT} ${VM_USER}@${VM_HOST} "cd ~/deploy && docker-compose down --remove-orphans && docker-compose up -d"
                """
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
