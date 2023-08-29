package com.sk.applicationmeals.base

import androidx.lifecycle.ViewModel
import com.sk.applicationmeals.common.NetInfo
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {
    @Inject
    protected lateinit var netInfo: NetInfo

}