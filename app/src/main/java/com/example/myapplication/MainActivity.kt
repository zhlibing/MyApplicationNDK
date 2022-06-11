package com.example.myapplication

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.mylibrary.IMyAidlInterface
import com.example.mylibrary.LoginEntry

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var iMyAidlInterface: IMyAidlInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var people = People()
        var jniDynamicLoad = JNIDynamicLoad()
        // Example of a call to a native method
        binding.sampleText.text = stringFromJNI(2) + getStingFromPeople("???", people)

        binding.sampleText.setOnClickListener { _ ->
            Toast.makeText(applicationContext, people.name + people.sex, Toast.LENGTH_LONG).show();
            binding.sampleText.text =
                jniDynamicLoad.nativeString + jniDynamicLoad.sum(
                    2,
                    3
                ) + people.sex + ">>" + invokeMenCons().hight + "<<" + allocObjectCons().hight;
            nativeCallback(iCallbackMethod)
            nativeThreadCallback(iThreadCallbackMethod)
        }
        binding.button.setOnClickListener { _ ->
            val intent = Intent()
            intent.setPackage("com.example.servicetest")
            intent.action = "com.example.servicetest.testservice"
            bindService(intent, serviceConnection, BIND_AUTO_CREATE)

//            val intent = Intent()
//            intent.setClassName("com.example.servicetest", "com.example.servicetest.ServiceTest")
//            bindService(intent, serviceConnection, BIND_AUTO_CREATE)

            if (iMyAidlInterface != null) {
                iMyAidlInterface!!.basicTypes(
                    10,
                    100000,
                    false,
                    0.01f,
                    0.3,
                    "sssss"
                )
                val loginEntry = LoginEntry()
                loginEntry.userName = "11"
                loginEntry.passWord = "aa"
                Toast.makeText(
                    applicationContext, iMyAidlInterface!!.login(loginEntry).userName +
                            iMyAidlInterface!!.login(loginEntry).passWord, Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    var serviceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder)
            try {
                var back = with(iMyAidlInterface) {
                    this?.basicTypes(
                        10,
                        100000,
                        false,
                        0.01f,
                        0.3,
                        "sssss"
                    )
                }
                Toast.makeText(applicationContext, back.toString(), Toast.LENGTH_LONG).show()
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
            /**
             * 监听远程服务异常
             */
            iBinder.linkToDeath({
                Toast.makeText(applicationContext, "linkToDeath", Toast.LENGTH_LONG).show()
            }, 0)
        }

        override fun onServiceDisconnected(componentName: ComponentName) {
            Toast.makeText(applicationContext, "onServiceDisconnected", Toast.LENGTH_LONG).show()
        }
    }

    var iCallbackMethod = ICallbackMethod {
        Toast.makeText(applicationContext, "callback", Toast.LENGTH_LONG).show()
    }

    var iThreadCallbackMethod = ICallbackMethod {
        Toast.makeText(applicationContext, "threadcallback", Toast.LENGTH_LONG).show();
    }

    /**
     * A native method that is implemented by the 'myapplication' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(i: Int): String

    external fun getStingFromPeople(s: String, people: People): String

    external fun invokeMenCons(): Men

    external fun allocObjectCons(): Men

    external fun nativeCallback(iCallbackMethod: ICallbackMethod)

    external fun nativeThreadCallback(iCallbackMethod: ICallbackMethod)

    companion object {
        // Used to load the 'myapplication' library on application startup.
        init {
            System.loadLibrary("myapplication")
        }
    }
}