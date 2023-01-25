package com.mlievens.listdetailapp.ui.details

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
) : ViewModel() {
    private val _detailState: MutableStateFlow<DetailViewState> =
        MutableStateFlow(DetailViewState.LoadingState)

    val detailState: StateFlow<DetailViewState> get() = _detailState

    fun loadDetails(itemId: String?) {
        viewModelScope.launch {
            itemId?.let { id ->
                itemDetailRepository.getItemDetail(id)
                    .onSuccess {
                        _detailState.emit(DetailViewState.SuccessState(it))
                    }
                    .onFailure {
                        _detailState.emit(DetailViewState.ErrorState)
                    }
            } ?: _detailState.emit(DetailViewState.ErrorState)
        }
    }

}

sealed class DetailViewState {
    object LoadingState : DetailViewState()
    class SuccessState(val detail: ItemDetail) : DetailViewState()
    object ErrorState : DetailViewState()
}