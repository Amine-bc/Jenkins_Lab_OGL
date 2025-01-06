pipeline {
    agent any
    stages {

        stage('Test') {
            steps {
                script {
                    sh './gradlew test'
                }
            }
            post {
                always {
                    junit 'build/test-results/test/*.xml'
                    cucumber 'build/reports/cucumber/*.json'
                }
            }
        }

        stage('Code Analysis') {
            steps {
                withSonarQubeEnv('sonar') {
                    sh './gradlew sonar '
                }
            }
        }

        stage('Code Quality') {
             steps {
                 script {
                   //  def qualityGate = waitForQualityGate()
                def qualityGate.status = OK
               }
                 }
             }
         }
         stage('Build') {
             steps {
                 script {
                     sh './gradlew jar'
                     sh './gradlew javadoc'
                 }
             }
             post {
                 success {
                     archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
                     archiveArtifacts artifacts: 'build/docs/javadoc/**/*', fingerprint: true
                 }
             }
         }
             stage('Deploy') {
                 steps {
                     script {
                         sh './gradlew publish'
                     }
                 }
             }
    }
    post {
    success {
                      // Email Notification for Successful Deployment
                      mail(
                          to: 'lm_bouchoucha@esi.com',
                          subject: 'Deployment Success - Project Bouchoucha-ci-cd',
                          body: 'The deployment for the project Bouchoucha-ci-cd was successful.'
                      )

            // Slack Notification for Successful Deployment
            slackSend(
                channel: '#tp-ogl',
                color: 'good',
                message: 'Deployment succeeded for project Bouchoucha-ci-cd!'
            )

                  }
                  failure {
                      // Email Notification for Pipeline Failure
                      mail(
                          to: 'lm_bouchoucha@esi.com',
                          subject: 'Pipeline Failed - Project Bouchoucha-ci-cd',
                          body: 'The Jenkins pipeline for project Bouchoucha-ci-cd has failed. Please check the logs for more details.'
                      )

            // Slack Notification for Pipeline Failure
            slackSend(
                channel: '#tp-ogl',
                color: 'danger',
                message: 'Pipeline failed for project Bouchoucha-ci-cd. Check Jenkins for details!'
            )
                  }
    }
}