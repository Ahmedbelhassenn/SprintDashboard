pipeline {
    agent any
    environment {
      JIRA_BASE_URL = credentials('JIRA_BASE_URL')
      FEIGN_CLIENT_USERNAME = credentials('FEIGN_CLIENT_USERNAME')
      FEIGN_CLIENT_API_TOKEN = credentials('FEIGN_CLIENT_API_TOKEN')
      IMAGE_NAME = credentials('IMAGE_NAME')
    }
    stages {
        stage('Checkout code') {
            steps {
                git branch: 'main', url: 'https://github.com/Ahmedbelhassenn/SprintDashboard.git'
            }
        }

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

        stage('Frontend Docker Build') {
            steps {
                echo '🐳 Building Docker image for Angular frontend...'
                dir('SprintDash_Frontend/SprintDash') {
                    bat "docker build -t sprintdash-frontend:latest ."
                }
            }
        }

        stage('Frontend Docker Compose Up') {
            steps {
                echo '🚀 Running frontend container...'
                dir('SprintDash_Frontend/SprintDash') {
                    bat 'docker-compose down'
                    bat 'docker-compose up -d'
                }
            }
        }

        stage('Backend Lint') {
            steps {
                echo 'Running Checkstyle...'
                dir('SprintDash_Backend') {
                    bat 'mvnw.cmd checkstyle:check'
                }
            }
        }

        stage('Backend Build avec Maven') {
            steps {
                echo 'Building the application...'
                dir('SprintDash_Backend') {
                    bat 'mvnw.cmd clean package -DskipTests'
                }
            }
        }

        stage('Backend Test') {
            steps {
                echo '🧪 Running unit tests...'
                dir('SprintDash_Backend') {
                    bat 'mvn test'
                }
            }
        }

        stage('Backend Docker Build') {
            steps {
                echo '🐳 Building Docker image...'
                dir('SprintDash_Backend') {
                    bat "docker build -t %IMAGE_NAME% ."
                }
            }
        }

        stage('Backend Docker Compose Up') {
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