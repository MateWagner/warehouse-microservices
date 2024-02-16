FROM node:21.6.0-alpine AS cache
WORKDIR /app
COPY package.json .
RUN npm i

FROM node:21.6.0-alpine AS build
WORKDIR /app
COPY --from=cache /app/node_modules ./node_modules
COPY . .
RUN npm i 
RUN npm run build

FROM nginx
COPY --from=build /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
