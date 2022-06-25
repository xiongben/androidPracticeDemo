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

### 11， vararg关键字对应java中的可变参数列表，允许传入0个，1个，2个，，，，，
### 12， by lazy 代码块懒加载
### 13， 类委托和委托属性
```kotlin
class MySet<T>(val helperSet: HashSet<T>): Set<T> by helperSet {
    override fun isEmpty(): Boolean {
        return false
    }
}

class MyClass {
    var p by Delegate()
}

class Delegate {
    var propValue: Any? = null

    operator fun getValue(myClass: MyClass, prop: KProperty<*>): Any? {
        return propValue
    }

    operator fun setValue(myClass: MyClass, prop: KProperty<*>, value: Any?) {
        propValue = value
    }
}
```
### 14， infix构建可读性函数
```kotlin
val list2 = listOf<String>("apple", "banana")
infix fun <T> Collection<T>.has(element: T) = contains(element)
if (list2 has "apple") {
    println("list have apple")
}
```
### 15， 内联函数的泛型实化
```kotlin
inline fun <reified T> getGenericType() = T::class.java

val res1 = getGenericType<String>()
```
### 16，  泛型的协变
```kotlin
class SimpleData<out T>(val data: T?) {
    fun get(): T? {
        return data
    }
}
```
### 16，  泛型的逆变
```kotlin
interface TransFormer<in T> {
    fun transform(t: T): String
}
```