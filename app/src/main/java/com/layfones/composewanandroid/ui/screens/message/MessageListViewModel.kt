package com.layfones.composewanandroid.ui.screens.message

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.layfones.composewanandroid.data.repository.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MessageListViewModel @Inject constructor(repository: MessageRepository):ViewModel() {
    val getReadiedMsgFlow = repository.getReadiedMsgFlow().cachedIn(viewModelScope)

    val getUnreadMsgFlow = repository.getUnreadMsgFlow().cachedIn(viewModelScope)

//    fun clearUnreadMessage() = unreadMessageManager.clearUnreadMessage()
}