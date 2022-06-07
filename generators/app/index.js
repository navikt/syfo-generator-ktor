var Generator = require('yeoman-generator')
var mkdirp = require("mkdirp")


module.exports = class extends Generator {

    async getUserInput() {
        this.answers = await this.prompt([
            {
                type: "input",
                name: "team",
                message: "your team",
                default: "teamsykmelding",
            },
            {
                type: "input",
                name: "name",
                message: "your app name",
                default: this.appname.replace(/\s+/g, '-')
            },
            {
                name: "package",
                message: "name of the package",
                default: "no.nav.syfo",
            }
        ])

        const answers2 = await this.prompt([
            {
                type: "list",
                name: "ingressDev",
                message: "ingress for dev-gcp",
                choices: [ 
                    "https://" + this.answers.name + ".dev.nav.no", 
                    "https://" + this.answers.name + ".dev.intern.nav.no" ]
            },
            {
                type: "list",
                name: "ingressProd",
                message: "ingress for dev-gcp",
                choices: [ 
                    "https://" + this.answers.name + ".intern.nav.no", 
                    "https://" + this.answers.name + ".nav.no" ]
            }
        ])
        
        this.answers.ingressDev = answers2.ingressDev
        this.answers.ingressProd = answers2.ingressProd 
    }

    writing() {
        var packageDir = this.answers.package.replace(/\./g, '/');
        var appPath = this.sourceRoot();
        var props = {
            appPackage: this.answers.package,
            appName: this.answers.name,
            team: this.answers.team,
            ingressDev: this.answers.ingressDev,
            ingressProd: this.answers.ingressProd,
            cluster: "gcp",
            kafka: ""
        }
        mkdirp("src/main/kotlin/" + packageDir)
        mkdirp("src/main/resources")
        mkdirp("src/test/kotlin/" + packageDir)
        mkdirp("src/test/resources")

        this.fs.copyTpl(appPath + "/.gitignore", '.gitignore');
        this.fs.copyTpl(appPath + "/build.gradle.kts", 'build.gradle.kts', props);
        this.fs.copyTpl(appPath + "/CODEOWNERS", 'CODEOWNERS', props);
        this.fs.copyTpl(appPath + "/Dockerfile", 'Dockerfile', props);
        this.fs.copyTpl(appPath + "/gradle.properties", 'gradle.properties');
        this.fs.copyTpl(appPath + "/gradlew", 'gradlew');
        this.fs.copyTpl(appPath + "/gradlew.bat", 'gradlew.bat');
        this.fs.copyTpl(appPath + "/LICENSE", 'LICENSE');
        this.fs.copyTpl(appPath + "/naiserator-dev.yaml", 'naiserator-dev.yaml', props);
        this.fs.copyTpl(appPath + "/naiserator-prod.yaml", 'naiserator-prod.yaml', props);
        this.fs.copyTpl(appPath + "/README.md", 'README.md', props);
        this.fs.copyTpl(appPath + "/settings.gradle.kts", 'settings.gradle.kts', props);
        this.fs.copy(appPath + "/gradle/**","gradle/")
        this.fs.copyTpl(appPath + "/src/main/kotlin/no/nav", "src/main/kotlin/" + packageDir, props)
        this.fs.copyTpl(appPath + "/src/main/resources/**", "src/main/resources/", props)
        this.fs.copyTpl(appPath + "/src/test/resources/**", "src/test/resources/", props)
        this.fs.copyTpl(appPath + "/src/test/kotlin/no/nav", "src/test/kotlin/" + packageDir, props)
        this.fs.copyTpl(appPath + "/.github/**", ".github/", props)
        
        
        this.log("app name", this.answers.name)
    }

    constructor(args, opts) { 
        super(args, opts)
    }
};