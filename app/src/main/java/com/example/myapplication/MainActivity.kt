package com.example.myapplication

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.media.AudioManager
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.enumTest.MyEnum
import com.example.myapplication.systemservice.AccountInfo
import com.example.mylibrary.*
import com.example.mylibrary.pool.BinderPool


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var iMyAidlInterface: IMyAidlInterface? = null

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var people = People()
        var jniDynamicLoad = JNIDynamicLoad()

        binding.sampleText.text = stringFromJNI(2) + getStingFromPeople("???", people)

//        LoginManager.getInstance().regist(this, serviceConnection);

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
//            val intent = Intent()
//            intent.setPackage("com.example.servicetest")
//            intent.action = "com.example.servicetest.testservice"
//            bindService(intent, serviceConnection, BIND_AUTO_CREATE)

//            val intent = Intent()
//            intent.setClassName("com.example.servicetest", "com.example.servicetest.ServiceTest")
//            bindService(intent, serviceConnection, BIND_AUTO_CREATE)

            if (iMyAidlInterface != null) {
                val loginEntry = LoginEntry()
                loginEntry.userName = "11"
                loginEntry.passWord = "aa"
                Toast.makeText(
                    applicationContext, iMyAidlInterface!!.login(loginEntry).userName +
                            iMyAidlInterface!!.login(loginEntry).passWord, Toast.LENGTH_LONG
                ).show()
            }
        }

        Thread {
            try {
                val binder = BinderPool.getInstance(applicationContext)
                    .queryBinder(BinderPool.QUERY_CODE_BINDER_A)
                val calc = IBinderA.Stub.asInterface(binder).calc(1, 2)
                runOnUiThread {
                    Toast.makeText(applicationContext, "calc:$calc", Toast.LENGTH_SHORT).show()
                }
                val binderB = BinderPool.getInstance(applicationContext)
                    .queryBinder(BinderPool.QUERY_CODE_BINDER_B)
                val info = IBinderB.Stub.asInterface(binderB).getInfo("hello")
                runOnUiThread {
                    Toast.makeText(applicationContext, "info:$info", Toast.LENGTH_SHORT).show()
                }
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }.start()

        binding.buttonTestSystemService.setOnClickListener {
            val audioManager: AudioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
            audioManager.isMicrophoneMute = true
            Toast.makeText(
                applicationContext,
                "isMicrophoneMute" + audioManager.isMicrophoneMute,
                Toast.LENGTH_SHORT
            ).show()
        }

        AccountInfo.getInstance().getAccount(this)
        MyEnum.Instance.innerMethod()
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

    override fun onDestroy() {
        super.onDestroy()
        LoginManager.getInstance().unRegist(this, serviceConnection)
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