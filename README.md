# syfo-generator-ktor
This project contains a yeoman generator for new syfo ktor apps

## Technologies used
* Kotlin
* Ktor
* Gradle
* Postgres

## Getting started
### Create new syfo app

1. create new github-repository for the new app

2. add NAIS_DEPLOY_APIKEY to github secrets, you can find the secret in [naiscloud](https://deploy.nav.cloud.nais.io/apikeys)

3. install yeoman 
```
npm install -g yo
```
4. clone this repository and run 
```
npm link
```
5. clone the new repo for the new app and cd into it

6. run 

```
yo ktor
git add .
git commit -m "first commit"
git push
```

This will setup a new application and deploy it to [NAIS](https://nais.io)