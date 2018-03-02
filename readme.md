## KotlinTapCircleMoveView

### Visualization to show movement of a circle using it complete length

### Usage

#### Using it in activity

##### adding library

```
    compile project(":circlelinetapmoveview")
```

##### adding view in activity

```
  val view = CircleLineTapMoveView.create(this)
  view.addOnCircleMoveListener { point1, point2 ->
      Toast.makeText(this,"moved from ${point1.toXYString()} to ${point2.toXYString()}", Toast.LENGTH_SHORT).show()
  }
```

### Demo

<img src="https://raw.githubusercontent.com/Anwesh43/KotlinTapLineCircleView/master/screenshot/tapmover.gif" width="300px" height="500px" alt="demo">
