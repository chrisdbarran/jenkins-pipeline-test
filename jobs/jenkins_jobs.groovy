pipelineJob('jenkins-test-pipeline1') {
 description('Build a pipeline') 
     triggers {
         cron('*/59 * * * *')
     }
     logRotator(5,5)
     definition {
         cps { 
             script(readFileFromWorkspace('pipelines/pipeline1.groovy'))
             sandbox()
         }
     }
}