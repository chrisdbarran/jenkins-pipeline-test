node {
    stage('Hello') {
        echo('Hello ')
    }
    stage('World!') {
        echo('World!')
    }
    stage('Data') {
       echo('Running ${env.BUILD_ID} on ${env.JENKINS_URL}') 
    }
}