pipeline {
    agent any
    environment {
        JIRA.BASE_URL = credentials('JIRA.BASE_URL')
        FEIGN_CLIENT_USERNAME = credentials('FEIGN_CLIENT_USERNAME')
        FEIGN_CLIENT_API_TOKEN = credentials('FEIGN_CLIENT_API_TOKEN')
        POSTGRES_DB = credentials('POSTGRES_DB')
        POSTGRES_USER = credentials('POSTGRES_USER')
        POSTGRES_PASSWORD = credentials('POSTGRES_PASSWORD')
    }
    stages {
        stage('Checkout code') {
            steps {
                git branch: 'main', url: 'https://github.com/Ahmedbelhassenn/SprintDashboard.git'
            }
        }

        stage('Docker Compose Up') {
            steps {
                echo '🐳 Building and running all services via Docker Compose...'
                dir('.') {  // root of the repo
                    // Use environment variables in Docker Compose automatically
                    bat 'docker-compose down --remove-orphans'
                    bat 'docker-compose up --build -d'
                }
            }
        }

        // -------- FRONTEND --------
        stage('Frontend Lint') {
            steps {
                echo '🔍 Linting Angular frontend...'
                dir('SprintDash_Frontend/SprintDash') {
                    bat 'npm install'
                    bat 'npm run lint'
                }
            }
        }
        
        stage('Frontend Build') {
            steps {
                echo '⚙️ Building Angular frontend...'
                dir('SprintDash_Frontend/SprintDash') {
                    bat 'ng build --configuration=production'
                }
            }
        }

        stage('Frontend Test') {
            steps {
                dir('SprintDash_Frontend/SprintDash') {
                    bat 'npm run test -- --watch=false --browsers=ChromeHeadless'
                }
            }
        }

        // -------- BACKEND --------
        stage('Backend Lint') {
            steps {
                echo '🔍 Running Checkstyle for backend...'
                dir('SprintDash_Backend') {
                    bat 'mvnw.cmd checkstyle:check'
                }
            }
        }

        stage('Backend Build') {
            steps {
                echo '⚙️ Building Spring Boot backend...'
                dir('SprintDash_Backend') {
                    bat 'mvnw.cmd clean package -DskipTests'
                }
            }
        }

        stage('Backend Test') {
            steps {
                echo '🧪 Running backend unit tests...'
                dir('SprintDash_Backend') {
                    bat 'mvn test'
                }
            }
        }

        // -------- DOCKER COMPOSE --------
        
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
