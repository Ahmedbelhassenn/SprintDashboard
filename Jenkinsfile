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

        // -------- DOCKER BUILD LOCAL --------
        stage('Docker Compose Build Local') {
            steps {
                echo '🐳 Build images locally for backend/frontend...'
                dir('.') {
                    bat 'docker-compose build'
                }
            }
        }

       stage('Prepare & Deploy to VM') {
            steps {
                // 1️⃣ Créer le dossier deploy sur la VM
                bat """
                ssh -i C:\\Users\\USER\\.ssh\\jenkins_id_rsa -p 2222 ahmed_bh@127.0.0.1 "mkdir -p ~/deploy"
                """
        
                // 2️⃣ Générer le fichier .env avec les variables Jenkins
                bat """
                echo POSTGRES_DB=${POSTGRES_DB}  1> temp_env
                echo POSTGRES_USER=${POSTGRES_USER}  1>> temp_env
                echo POSTGRES_PASSWORD=${POSTGRES_PASSWORD}  1>> temp_env
                echo JIRA_BASE_URL=${JIRA_BASE_URL}  1>> temp_env
                echo FEIGN_CLIENT_USERNAME=${FEIGN_CLIENT_USERNAME}  1>> temp_env
                echo FEIGN_CLIENT_API_TOKEN=${FEIGN_CLIENT_API_TOKEN}  1>> temp_env
                """
        
                // 3️⃣ Archiver backend et frontend avec PowerShell
                bat """
                powershell -Command "Compress-Archive -Path SprintDash_Backend\\* -DestinationPath SprintDash_Backend.zip"
                powershell -Command "Compress-Archive -Path SprintDash_Frontend\\SprintDash\\* -DestinationPath SprintDash_Frontend.zip"
                """
        
                // 4️⃣ Copier les archives et docker-compose/.env sur la VM
                bat """
                scp -i C:\\Users\\USER\\.ssh\\jenkins_id_rsa -P 2222 docker-compose.yml ahmed_bh@127.0.0.1:~/deploy/
                scp -i C:\\Users\\USER\\.ssh\\jenkins_id_rsa -P 2222 temp_env ahmed_bh@127.0.0.1:~/deploy/.env
                scp -i C:\\Users\\USER\\.ssh\\jenkins_id_rsa -P 2222 SprintDash_Backend.zip ahmed_bh@127.0.0.1:~/deploy/
                scp -i C:\\Users\\USER\\.ssh\\jenkins_id_rsa -P 2222 SprintDash_Frontend.zip ahmed_bh@127.0.0.1:~/deploy/
                """
        
                // 5️⃣ Décompresser les archives sur la VM
                bat """
                ssh -i C:\\Users\\USER\\.ssh\\jenkins_id_rsa -p 2222 ahmed_bh@127.0.0.1 "cd ~/deploy && unzip -o SprintDash_Backend.zip && unzip -o SprintDash_Frontend.zip"
                """
        
                // 6️⃣ Lancer le déploiement Docker Compose
                bat """
                ssh -i C:\\Users\\USER\\.ssh\\jenkins_id_rsa -p 2222 ahmed_bh@127.0.0.1 "cd ~/deploy && docker-compose down && docker-compose up -d --build"
                """
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
