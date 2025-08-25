pipeline {
    agent any
    environment {
        JIRA_BASE_URL = credentials('JIRA_BASE_URL')
        FEIGN_CLIENT_USERNAME = credentials('FEIGN_CLIENT_USERNAME')
        FEIGN_CLIENT_API_TOKEN = credentials('FEIGN_CLIENT_API_TOKEN')
        POSTGRES_DB = credentials('POSTGRES_DB')
        POSTGRES_USER = credentials('POSTGRES_USER')
        POSTGRES_PASSWORD = credentials('POSTGRES_PASSWORD')
        SSH_CRED = 'id-credential-ssh' 
        VM_USER = 'ahmed_bh'           
        VM_IP = '10.0.2.15'            
        PROJECT_PATH = '/home/ahmed_bh/SprintDashboard' 
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
        stage('Deploy on VM') {
            steps {
                sshagent([env.SSH_CRED]) {
                    sh """
                    ssh -o StrictHostKeyChecking=no ${VM_USER}@${VM_IP} '
                        mkdir -p ${PROJECT_PATH} &&
                        cd ${PROJECT_PATH} &&
                        git pull || git clone https://github.com/Ahmedbelhassenn/SprintDashboard.git ${PROJECT_PATH} &&
                        docker-compose down --remove-orphans &&
                        docker-compose up --build -d
                    '
                    """
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
