# 学习记录

### 1，Activity提供一个onSaveInstanceState方法用来保存Activity被回收前的数据，一个Bundle类型的参数
### 2，Activity的启动模式(launchMode)，standard，singleTop， singleTask, singleInstance
### 3，javaClass.simpleName 获取当前实例的类名
### 4，通过startActivity和startActivityForResult启动Activity
### 5, 静态方法启动activity
```kotlin
class SecondActivity: BaseActivity() {
//    ...
    companion object {
        fun actionStart(context: Context, data1: String, data2 : String) {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("param1", data1)
            intent.putExtra("param2", data2)
            context.startActivity(intent)
        }
    }
//    ...
}


```
### 6, 标准函数with, run, apply
### 7, 使用lateinit进行延迟初始化
### 8， 使用sealed 密封类优化代码
### 9， 布局限定符的使用
### 10， 高阶函数和内联函数
```kotlin
fun num1AndNum2(num1: Int, num2: Int, operation: (Int, Int) -> Int): Int {
    return operation(num1, num2)
}

fun plus(num1: Int, num2: Int) : Int {
    return num1 + num2
}

fun minus(num1: Int, num2: Int) : Int {
    return num1 - num2
}

fun StringBuilder.build(block: StringBuilder.() -> Unit) : StringBuilder {
    block()
    return this
}


val res1 = num1AndNum2(num1, num2, ::plus)
val res2 = num1AndNum2(num1, num2, ::minus)
val res3 = num1AndNum2(num1, num2) {n1, n2 ->
    n1 + n2
}

```

