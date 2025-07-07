package com.example.coursecomposedisposableeffect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.coursecomposedisposableeffect.ui.theme.CourseComposeDisposableEffectTheme

class MainActivity : ComponentActivity() {
  var big = false

  fun a() {
    big = true //This fun has changed the value of `big`, which is from the outer scope.
    println("tiger test")
  }


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      CourseComposeDisposableEffectTheme {
        var showText by remember { mutableStateOf(false) }
        Button(onClick = { showText = !showText }) {
          Text("点击")
          if (showText) {
            Text("rengwuxian")
          }
          /**
           * SideEffect 是一个用于在 Compose 中执行副作用的 API。
           * 副作用是指那些不直接影响 UI 状态的操作，比如打印日志、网络请求等。
           * 在 Compose 中，SideEffect 会在每次重组时执行，
           * 这意味着每当相关的状态发生变化时，SideEffect 都会被调用。
           * 但是我们要确保调用 SideEffect 的 compose 的确是需要的时候，才会执行side effect中的代码
           * 所以 SideEffect 中的代码会在compose确认被需要的时候执行，
           */
          SideEffect {
            println("@@@ SideEffect()")
          }
          DisposableEffect(showText) {
            println("@@@ 进入界面啦！")
            onDispose {
              println("@@@ 离开界面啦！")
            }
          }
        }
      }
    }
  }
}