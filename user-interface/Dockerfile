FROM node:10

# install simple http server for serving static content
# RUN npm install -g http-server

# make the 'app' folder the current working directory
WORKDIR /app

## copy both 'package.json' and 'package-lock.json' (if available)
COPY package*.json ./
#
# install project dependencies
RUN  npm config set registry https://registry.npmjs.com/
RUN npm run clean
RUN rm -rf package-lock.json
RUN npm cache clean --force
RUN npm i
#
## copy project files and folders to the current working directory (i.e. 'app' folder)
COPY . .

# build app for production with minification
RUN npm run build

EXPOSE 8081
CMD [ "npm", "run", "serve" ]