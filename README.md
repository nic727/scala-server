# scala-server

This project implements a simple server using Scala with the Akka-Http module. Given a POST request containing a JSON body which specifies a
range of integers (e.g. `{"min":1 ,"max":5}`), it returns a JSON response containing a randomly generated addition question for an integer within the range, the answer to that question, and 3 wrong answers.


# Usage
Start server with sbt

```
$ sbt
> ~reStart
```

With the server running you can send HTTP post requests using the curl command

```
$ curl -X POST -H 'Content-Type: application/json' http://localhost:8080 -d '{"min": 1, "max": 5}'
```

Note that for defining the range only integers from 0 to 1000000 are allowed.
