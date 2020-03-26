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

2. add NAIS_DEPLOY_APIKEY to github secrets, you can find the secret in vault/apikeys/teamsykefravr

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
vault integrations is disabled, to enable vault there has to be created a pull request here
```
https://github.com/navikt/vault-iac/
```

To add azuread client create pull request here 

```
https://github.com/navikt/aad-iac/
```

## Contact us
### Code/project related questions can be sent to
* Joakim Kartveit, `joakim.kartveit@nav.no`
* Andreas Nilsen, `andreas.nilsen@nav.no`
* Sebastian Knudsen, `sebastian.knudsen@nav.no`
* Tia Firing, `tia.firing@nav.no`
* Jonas Henie, `jonas.henie@nav.no`
* Mathias Hellevang, `mathias.hellevang@nav.no`

### For NAV employees
We are available at the Slack channel #team-sykmelding