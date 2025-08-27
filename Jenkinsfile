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

        stage('Deploy on VM') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'DOCKERHUB_CREDS', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sshagent(['vm-ubuntu-ssh']) {
                        bat """
                            ssh -p %VM_PORT% %VM_USER%@%VM_HOST% "
                            cd %DEPLOY_DIR% &&
                            echo "ok"
                            "
                        """
                    }
                }
            }
        }

        

        stage('Frontend Lint & Build') {
            steps {
                dir('SprintDash_Frontend/SprintDash') {
                    bat 'npm install'
                    bat 'npm run lint'
                    bat 'ng build --configuration=production'
                }
            }
        }

        stage('Backend Build & Test') {
            steps {
                dir('SprintDash_Backend') {
                    bat 'mvnw.cmd clean package -DskipTests'
                    bat 'mvn test'
                }
            }
        }

        stage('Docker Build & Push') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'DOCKERHUB_CREDS', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    bat """
                        REM -- Build backend/frontend --
                        docker build -t %IMAGE_BACKEND%:latest ./SprintDash_Backend
                        docker build -t %IMAGE_FRONTEND%:latest ./SprintDash_Frontend/SprintDash

                        REM -- Tag pour Docker Hub --
                        docker tag %IMAGE_BACKEND%:latest %REGISTRY%/%IMAGE_BACKEND%:%IMAGE_TAG%
                        docker tag %IMAGE_FRONTEND%:latest %REGISTRY%/%IMAGE_FRONTEND%:%IMAGE_TAG%

                        REM -- Login Docker Hub --
                        echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin

                        REM -- Push images --
                        docker push %REGISTRY%/%IMAGE_BACKEND%:%IMAGE_TAG%
                        docker push %REGISTRY%/%IMAGE_FRONTEND%:%IMAGE_TAG%
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
