node ('master'){
    stage('Hello') {
        try {
            echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
        } catch (exc) {
            echo 'Something Failed'
            throw exc
        }
 
    }
}