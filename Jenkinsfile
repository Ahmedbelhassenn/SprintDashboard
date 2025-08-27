pipeline {
    agent any

    environment {
        // Docker Hub
        REGISTRY = "docker.io/ahmedbelhassenn"
        IMAGE_BACKEND = "sprintdash-backend"
        IMAGE_FRONTEND = "sprintdash-frontend"
        IMAGE_TAG = "latest"

        // VM
        DEPLOY_DIR = "~/deploy"
        VM_USER = 'ahmed_bh'
        VM_HOST = '127.0.0.1'
        VM_PORT = '2222'

        // Jira / Postgres credentials
        JIRA_BASE_URL = credentials('JIRA_BASE_URL')
        FEIGN_CLIENT_USERNAME = credentials('FEIGN_CLIENT_USERNAME')
        FEIGN_CLIENT_API_TOKEN = credentials('FEIGN_CLIENT_API_TOKEN')
        POSTGRES_DB = credentials('POSTGRES_DB')
        POSTGRES_USER = credentials('POSTGRES_USER')
        POSTGRES_PASSWORD = credentials('POSTGRES_PASSWORD')
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/Ahmedbelhassenn/SprintDashboard.git'
            }
        }
        // ---- 2. Frontend ----
        stage('Frontend Lint') {
            steps {
                dir('SprintDash_Frontend/SprintDash') {
                    bat 'npm install'
                    bat 'npm run lint'
                }
            }
        }

        stage('Frontend Build') {
            steps {
                dir('SprintDash_Frontend/SprintDash') {
                    bat 'ng build --configuration=production'
                }
            }
        }

        stage('Frontend Test') {
            steps {
                dir('SprintDash_Frontend/SprintDash') {
                    bat 'npm test -- --watch=false --browsers=ChromeHeadless'
                }
            }
        }

        // ---- 3. Backend ----
        stage('Backend Build') {
            steps {
                dir('SprintDash_Backend') {
                    bat 'mvnw.cmd clean package -DskipTests'
                }
            }
        }

        stage('Backend Test') {
            steps {
                dir('SprintDash_Backend') {
                    bat 'mvn test'
                }
            }
        }

        // ---- 4. Docker ----
        stage('Docker Build Images') {
            steps {
                bat """
                    docker build -t %IMAGE_BACKEND%:latest ./SprintDash_Backend
                    docker build -t %IMAGE_FRONTEND%:latest ./SprintDash_Frontend/SprintDash
                """
            }
        }

        stage('Docker Push Images') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'DOCKERHUB_CREDS', 
                                                 usernameVariable: 'DOCKER_USER', 
                                                 passwordVariable: 'DOCKER_PASS')]) {
                    bat """
                        echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin
                        docker tag %IMAGE_BACKEND%:latest %REGISTRY%/%IMAGE_BACKEND%:%IMAGE_TAG%
                        docker tag %IMAGE_FRONTEND%:latest %REGISTRY%/%IMAGE_FRONTEND%:%IMAGE_TAG%
                        docker push %REGISTRY%/%IMAGE_BACKEND%:%IMAGE_TAG%
                        docker push %REGISTRY%/%IMAGE_FRONTEND%:%IMAGE_TAG%
                    """
                }
            }
        }

        // ---- 5. Deployment ----
        stage('Deploy on VM') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'DOCKERHUB_CREDS', 
                                                 usernameVariable: 'DOCKER_USER', 
                                                 passwordVariable: 'DOCKER_PASS')]) {
                    bat """
                        REM -- Copier docker-compose.yml sur la VM --
                        scp -i C:\\Users\\USER\\.ssh\\jenkins_id_rsa -P %VM_PORT% docker-compose.yml %VM_USER%@%VM_HOST%:%DEPLOY_DIR%/

                        REM -- SSH vers la VM et lancement des containers --
                        ssh -i C:\\Users\\USER\\.ssh\\jenkins_id_rsa -p %VM_PORT% %VM_USER%@%VM_HOST% "cd %DEPLOY_DIR% && echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin && docker-compose pull && docker-compose up -d"
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
            echo '✅ Build, push, and deployment succeeded!'
        }
    }
}
