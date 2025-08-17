# Stage 1: Build Angular app
FROM node:20 AS build
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY . .
RUN npm run build --configuration=production

# Stage 2: Serve with Nginx
FROM nginx:alpine

# SPA routing
COPY nginx.conf /etc/nginx/conf.d/default.conf
# Copy built Angular files
COPY --from=build /app/dist/sprint-dash/browser /usr/share/nginx/html/

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
