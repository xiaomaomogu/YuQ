package com.icecreamqaq.yuq

import com.IceCreamQAQ.Yu.DefaultStarter
import com.IceCreamQAQ.Yu.hook.HookItem
import com.IceCreamQAQ.Yu.hook.HookMethod
import com.IceCreamQAQ.Yu.hook.HookRunnable
import com.IceCreamQAQ.Yu.hook.YuHook
import com.IceCreamQAQ.Yu.loader.AppClassloader
import org.slf4j.LoggerFactory
import java.lang.reflect.Method
import kotlin.coroutines.Continuation

class YuQStarter {


    companion object {
        private val log = LoggerFactory.getLogger(YuQStarter::class.java)

        @JvmStatic
        fun start() {
            val startTime = System.currentTimeMillis()

            YuHook.put(HookItem("com.icecreamqaq.yuq.RainBot","getContinuation","com.icecreamqaq.yuq.HookRainBotGetContinuation"))

            val classloader = AppClassloader(YuQStarter::class.java.classLoader)

            val yuClass = classloader.loadClass("com.IceCreamQAQ.Yu.DefaultApp")
            val start: Method? = yuClass.getMethod("start")

            val yu = yuClass.newInstance()
            start!!.invoke(yu)

            val overTime = System.currentTimeMillis()

            log.info("Done! ${(overTime - startTime).toDouble() / 1000}s.")

            println(" __  __     ____ \n" +
                    " \\ \\/ /_ __/ __ \\\n" +
                    "  \\  / // / /_/ /\n" +
                    "  /_/\\_,_/\\___\\_\\\n")
            println("感谢您使用 YuQ 进行开发，在您使用中如果遇到任何问题，可以到 Github，Gitee 提出 issue，您也可以添加 YuQ 的开发交流群（696129128）进行交流。")
        }

        @JvmStatic
        fun start(args: Array<String>) {


            DefaultStarter.init(args)
            start()


        }

    }

}

class HookRainBotGetContinuation : HookRunnable {
    override fun preRun(method: HookMethod): Boolean {
        method.result = method.paras[1]
        return true
    }

    override fun postRun(method: HookMethod?) {
        TODO("Not yet implemented")
    }

    override fun onError(method: HookMethod?): Boolean {
        TODO("Not yet implemented")
    }
}