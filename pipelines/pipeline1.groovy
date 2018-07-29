node ('master'){
    stage('Hello') {
        echo "Hello "
    }
    stage('World') {
        echo "World!"
    }
    stage('Data') {
        try {
            echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
        } catch (exc) {
            echo 'Something Failed'
            throw exc
        }
 
    }
}