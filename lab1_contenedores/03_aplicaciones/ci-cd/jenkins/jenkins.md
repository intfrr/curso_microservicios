
docker run \
  --rm \
  -u root \
  -p 8090:8080 \
  -v "$PWD":/var/jenkins_home \
  -v /var/run/docker.sock:/var/run/docker.sock \
  jenkinsci/blueocean


  ¿Cómo configuro una app?

Le damos click donde dice “create new jobs”, asignamos un nombre y en este caso haremos un pipeline.

https://github.com/jovaniac/curso_microservicios_ci-cd.git

En este repo existe un Jenkinsfile:

pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                 script {
                    try {
                        sh './gradlew clean buildImage --no-daemon' //run a gradle task
                    } finally {
                    echo 'Building OK'
                    }
                }       
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}

docker images