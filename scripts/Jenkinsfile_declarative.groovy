def mvn = "/var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/3.6.3/bin/mvn"

pipeline {
    agent any
    parameters {
        string(name: 'BRANCH', defaultValue: 'main', description: '')
        string(name: 'TAG', defaultValue: '@example', description: '')
    }
    stages {
        stage('Build') {
            steps {
                sh "mvn clean compile"
            }
        }
        stage('Run Tests') {
            steps {
                sh "mvn test -Dcucumber.filter.tags=${params.TAG}"
            }
        }
        stage('Allure Report Generation') {
            steps {
                allure includeProperties: false,
                        jdk: '',
                        results: [[path: 'target/reports/allure-results']],
                        reports: [[path: 'target/reports/allure-reports']]
            }
        }
    }
    post {
        always {
            cleanWs notFailBuild: true
        }
    }
}