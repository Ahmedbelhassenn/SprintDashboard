# 🏃 SprintDashboard

Une application web de gestion des indicateurs de performance (KPIs) des sprints Agile, avec intégration de la plateforme Jira via des APIs REST.

## 🚀 Objectifs

- Visualiser les KPIs des sprints Agile : vélocité, taux de complétion, etc.
- Centraliser les données Jira dans un tableau de bord interactif.
- Suivre la performance des équipes.

## 🛠️ Technologies

### Backend
- Spring Boot
- Java 17+
- REST API
- Jira REST API

### Frontend
- Angular
- TypeScript

### **DevOps & Déploiement**
- Git & GitHub  
- Jenkins (CI/CD automatisé)  
- Docker & Docker Compose  
- Docker Hub  
- Ubuntu Server (VM de déploiement)  

---

## ⚙️ Fonctionnalités CI/CD

La pipeline CI/CD réalisée avec **Jenkins** comprend :  
1. **Checkout** du code source depuis GitHub.  
2. **Build & Linting** du frontend et du backend.  
3. **Tests automatisés** pour assurer la qualité du code.  
4. **Construction des images Docker** pour le backend et le frontend.  
5. **Push des images Docker** vers **Docker Hub**.  
6. **Déploiement automatique sur VM Ubuntu** via **Docker Compose** et connexion sécurisée SSH.  

---

## 📂 Organisation du projet

SprintDashboard/
├── backend/ # Code source Spring Boot
├── frontend/ # Code source Angular
├── docker-compose.yml
├── Jenkinsfile
└── README.md

## Déploiement avec CI/CD

Jenkins exécute automatiquement le pipeline à chaque push GitHub.

Les images Docker sont générées et envoyées vers Docker Hub.

La VM Ubuntu récupère les images via docker-compose pull puis lance l’application avec docker-compose up -d.

## 📊 Schéma d’architecture CI/CD

```mermaid
flowchart TD
    A[GitHub Repository] -->|Push Code| B[Jenkins CI/CD]
    B --> C[Build & Lint]
    B --> D[Test Automatisés]
    B --> E[Docker Build Images]
    E --> F[Docker Hub Registry]
    F -->|docker pull| G[VM Ubuntu Server]
    G --> H[Docker Compose Up]
    H --> I[Frontend Angular + Backend Spring Boot + PostgreSQL]
🔹 Résultat :  
Interface Dashboard (KPIs des sprints)
<img width="1911" height="953" alt="Capture d'écran 2025-08-28 112012" src="https://github.com/user-attachments/assets/d86c0946-78a1-4aee-b235-9ac871bce9b1" />


Jenkins Pipeline CI/CD en exécution

<img width="1850" height="771" alt="Capture d'écran 2025-09-11 132056" src="https://github.com/user-attachments/assets/48f20827-2d9f-487a-bba1-28fa805b1be7" />

✨ Auteurs

👤 Ahmed Belhassen
📧 ahmed.belhassen@supcom.tn
🔗 https://www.linkedin.com/in/ahmed-belhassen-551b042bb/



