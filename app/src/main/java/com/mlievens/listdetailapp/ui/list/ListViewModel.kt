package com.mlievens.listdetailapp.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mlievens.listdetailapp.data.service.ApiService
import com.mlievens.listdetailapp.domain.models.ListItemData
import com.mlievens.listdetailapp.domain.repositories.ItemListRepository
import com.mlievens.listdetailapp.ui.details.DetailViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    itemListRepository: ItemListRepository,
): ViewModel() {
    private val _itemState : MutableStateFlow<ListViewState> = MutableStateFlow(ListViewState.LoadingState)

    val itemState: StateFlow<ListViewState> get() = _itemState

    init {
        viewModelScope.launch {
            itemListRepository.getItemList()
                .onSuccess {
                    _itemState.emit(ListViewState.SuccessState(it))
                }
                .onFailure { _itemState.emit(ListViewState.ErrorState) }
        }
    }

}

sealed class ListViewState {
    object LoadingState: ListViewState()
    class SuccessState(val itemList: List<ListItemData>): ListViewState()
    object ErrorState : ListViewState()
}