package com.alessandrofarandagancio.sampleapp.get_countries.datas

import com.alessandrofarandagancio.sampleapp.common.NetworkHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class TestNetworkHelper : NetworkHelper {
    private val _isConnected = MutableStateFlow(true)
    override val isConnected: Flow<Boolean> = _isConnected
}