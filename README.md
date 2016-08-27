# Kaiyote

Kaiyote is a typesafe configuration management tool inspired by [Itamae](https://github.com/itamae-kitchen/itamae).

## Installation

Kaiyote is provided as sbt plugin.

```bash
$ echo 'scalaVersion := "2.11.8"' > build.sbt
$ mkdir project
$ echo 'addSbtPlugin("com.mayreh" % "kaiyote" % "0.0.1")' > project/plugins.sbt
```

## Getting Started

Create a configuration as `Example.scala`:

```scala
package com.example

import com.mayreh.kaiyote.Root

object Example extends Root {
  // implement abstract member `rootConfiguration`
  val rootConfiguration = 
    directory("/home/kaiyote/hello") ++ 
      execute("create empty file").command("touch /home/kaiyote/hello/example")
}
```

And then excute `sbt run` to apply a configuration to a local machine.

```
$ sbt run local
 INFO : Starting Kaiyote...
 INFO : Root Configuration: com.example.Example
 INFO :    directory[/home/kaiyote/hello] exist will change from 'false' to 'true'
 INFO :    execute[create empty file] executed will change from 'false' to 'true'
```

## T.B.D.

- configure remote hosts via ssh
- run tests
- lightbend activator template