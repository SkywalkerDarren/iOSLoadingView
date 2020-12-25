# iOSLoadingView
iOS like loading drawable
# start
```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
```
dependencies {
  implementation 'com.github.SkywalkerDarren:iOSLoadingView:v1.0'
}
```
# usage
```kotlin
val loading = IosLoadingDrawable(
        context = this,             // context
        paintColor = Color.YELLOW,  // color
        paintWidth = 10.dp2dx(),    // line width in px
        lineCount = 6,              // number of line
        rotateDuration = 500L       // animation duration
)
loading.percents = 0.5f // range in 0 to 1f
loading.start()         // start animtaion
loading.pause()         // pause animation
```
