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

2. add NAIS_DEPLOY_APIKEY to github secrets, you can find the secret in [Vault](https://vault.adeo.no/ui/vault/secrets/apikey/show/nais-deploy/teamsykefravr)

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

This will setup a new application and deploy it to NAIS

### Azuread 

To add azuread client create pull request here 

```
https://github.com/navikt/aad-iac/
```