package com.alexklab.multitypeadapter

import android.util.Log

/**
 * Created by alexk on 2/19/18.
 * Project MultiTypeAdapter
 */
interface Loggable {

    fun tag(): String = javaClass.simpleName

    fun info(msg: String) = Log.i(tag(), msg)
    fun info(msg: String, e: Throwable?) = Log.i(tag(), msg, e)

    fun debug(msg: String) = Log.d(tag(), msg)
    fun debug(msg: String, e: Throwable?) = Log.d(tag(), msg, e)

    fun warning(msg: String) = Log.w(tag(), msg)
    fun warning(msg: String, e: Throwable?) = Log.w(tag(), msg, e)

    fun error(msg: String) = Log.e(tag(), msg)
    fun error(msg: String, e: Throwable?) = Log.e(tag(), msg, e)

    fun wtf(msg: String) = Log.wtf(tag(), msg)
    fun wtf(msg: String, e: Throwable?) = Log.wtf(tag(), msg, e)

}