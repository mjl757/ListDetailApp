package com.mlievens.listdetailapp.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mlievens.listdetailapp.domain.models.ListItemData
import com.mlievens.listdetailapp.domain.repositories.ItemListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    itemListRepository: ItemListRepository,
): ViewModel() {
    private val _itemState : MutableStateFlow<ListScreenViewState> = MutableStateFlow(ListScreenViewState.LoadingState)

    val itemState: StateFlow<ListScreenViewState> get() = _itemState

    init {
        viewModelScope.launch {
            itemListRepository.getItemList()
                .onSuccess { _itemState.emit(ListScreenViewState.SuccessState(it)) }
                .onFailure { _itemState.emit(ListScreenViewState.ErrorState) }
        }
    }
}

sealed class ListScreenViewState {
    object LoadingState: ListScreenViewState()
    data class SuccessState(val itemList: List<ListItemData>): ListScreenViewState()
    object ErrorState : ListScreenViewState()
}