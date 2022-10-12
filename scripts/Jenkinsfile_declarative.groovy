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
                        report: [[path: 'target/reports/allure-reports']]
            }
        }
    }
    post {
        always {
            cleanWs notFailBuild: true
        }
    }
}