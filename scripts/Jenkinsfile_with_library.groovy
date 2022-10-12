@Library('example_shared_library') _

def mvn = "/var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/3.6.3/bin/mvn"

node {
    stage('Checkout SCM') {
        checkOut(params.BRANCH)
    }
    stage('Build') {
        build(mvn)
    }
    stage('Run Tests') {
        try {
            runTestsByTag(mvn, params.TAG)
        }
        catch (Exception e) {
            echo "Test run was broken"
            throw e
        }
        finally {
            stage('Allure Report Generation') {
                generateAllureReport()
            }
            cleanWs notFailBuild: true
        }
    }
}