pipeline {
    agent any
    environment {
        JIRA_BASE_URL = credentials('JIRA_BASE_URL')
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

        // -------- DOCKER BUILD LOCAL --------
        stage('Docker Compose Build Local') {
            steps {
                echo '🐳 Build images locally for backend/frontend...'
                dir('.') {
                    bat 'docker-compose build'
                }
            }
        }

        // -------- DEPLOY SUR VM --------
        stage('Deploy to VM') {
            steps {
                sshagent(['vm-ubuntu-ssh']) {
                    sh '''
                        ssh -o StrictHostKeyChecking=no -p 2222 ahmed_bh@127.0.0.1 "
                            mkdir -p ~/deploy &&
                            rm -rf ~/deploy/* &&
                            exit
                        "

                        scp -P 2222 target/*.jar ahmed_bh@127.0.0.1:~/deploy/app.jar

                        ssh -o StrictHostKeyChecking=no -p 2222 ahmed_bh@127.0.0.1 "
                            nohup java -jar ~/deploy/app.jar > ~/deploy/app.log 2>&1 &
                        "
                    '''
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
