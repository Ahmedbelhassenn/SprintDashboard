pipeline {
    agent any

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
                bat './mvnw.cmd test -Dspring.profiles.active=test'
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
