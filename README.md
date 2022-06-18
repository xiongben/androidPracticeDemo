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
