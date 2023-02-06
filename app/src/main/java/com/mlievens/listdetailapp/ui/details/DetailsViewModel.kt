package com.mlievens.listdetailapp.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mlievens.listdetailapp.domain.models.ItemDetail
import com.mlievens.listdetailapp.domain.repositories.ItemDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val itemDetailRepository: ItemDetailRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val itemId: String? = savedStateHandle["itemId"]

    private val _detailState: MutableStateFlow<DetailViewState> =
        MutableStateFlow(DetailViewState.LoadingState)

    val detailState: StateFlow<DetailViewState> get() = _detailState

    init {
        loadDetails()
    }

    fun loadDetails() {
        viewModelScope.launch {
            itemId?.let { id ->
                itemDetailRepository.getItemDetail(id)
                    .onSuccess { _detailState.emit(DetailViewState.SuccessState(it)) }
                    .onFailure { _detailState.emit(DetailViewState.ErrorState) }
            } ?: _detailState.emit(DetailViewState.ErrorState)
        }
    }

}

sealed class DetailViewState {
    object LoadingState : DetailViewState()
    data class SuccessState(val detail: ItemDetail) : DetailViewState()
    object ErrorState : DetailViewState()
}