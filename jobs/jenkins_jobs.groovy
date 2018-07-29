pipelineJob('jenkins-test-pipeline1') {
 description('Build a pipeline') 
     triggers {
         cron('H/59 * * * *')
     }
     logRotator(5,5)
     definition {
         cps { 
             script(readFileFromWorkspace('pipelines/pipeline1.groovy'))
             sandbox()
         }
     }
}

pipelineJob('jenkins-github-status') {
    description('Test the status of The Github')
    triggers {
           cron('H/59 * * * *')
    }
    logRotator(5,5)
    definition {
        cps { 
            script(readFileFromWorkspace('pipelines/test-github.groovy'))
            sandbox()
        }
    }    
}