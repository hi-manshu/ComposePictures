package com.himanshoe.core.base

import androidx.lifecycle.ViewModel
import com.roomiapp.core.util.NetworkHelper

/**
 * Created by Himanshu Singh on 24-10-2020.
 **/
abstract class IBaseViewModel(private val networkHelper: NetworkHelper) : ViewModel() {

    protected fun isConnected(): Boolean = networkHelper.isNetworkConnected()

}
