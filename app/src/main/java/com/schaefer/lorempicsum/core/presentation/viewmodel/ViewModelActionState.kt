package com.schaefer.lorempicsum.core.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.schaefer.lorempicsum.core.presentation.ViewModelAction
import com.schaefer.lorempicsum.core.presentation.ViewModelState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class ViewModelActionState<State : ViewModelState, Action : ViewModelAction>(
    initialState: State
) : ViewModel() {
    private val viewModelState = MutableStateFlow(initialState)
    private val viewModelAction = MutableSharedFlow<Action>()

    val state: StateFlow<State> = viewModelState
    val action: Flow<Action> = viewModelAction

    protected fun setState(state: State) {
        viewModelState.value = state
    }

    protected suspend fun sendAction(action: Action) {
        viewModelAction.emit(action)
    }
}
