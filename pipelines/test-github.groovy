def FAILURE_EMAIL_RECIPIENTS = "test-address@example.com"
def apiURL = "https://status.github.com/api/last-message.json"

// Maximum time for the whole curl operation to take before failing (seconds).
def maxTime = 10

node('master') {

    try {
        def jsonText
        
        stage('API Reacheable') {
            jsonText = sh (script: "curl --silent --show-error --fail -m ${maxTime} ${apiURL}", returnStdout: true).trim()
        }

        stage('API Status Good') {
            validateStatus(jsonText)
            currentBuild.result = 'SUCCESS'
        }
    } catch (any) {
        currentBuild.result = 'FAILURE'
        throw any
    } finally {
        step([$class: 'Mailer', notifyEveryUnstableBuild: false, recipients: FAILURE_EMAIL_RECIPIENTS, sendToIndividuals: true ])
    }
}

def validateStatus(message){
    def jsonSlurper = new groovy.json.JsonSlurper()
    def json = jsonSlurper.parseText(message)
    echo ("GitHub Status: ${json.status} - ${json.body}")
    assert json.status == "bad"
}

