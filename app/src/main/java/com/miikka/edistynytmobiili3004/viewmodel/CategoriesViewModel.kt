package com.miikka.edistynytmobiili3004.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miikka.edistynytmobiili3004.model.CategoriesState
import com.miikka.edistynytmobiili3004.model.CategoryItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CategoriesViewModel: ViewModel() {



    private val _categoriesState = mutableStateOf(CategoriesState())
    val categoriesState: State<CategoriesState> = _categoriesState

    init {
        getCategories()
    }

    private suspend fun waitForCategories(){
        delay(2000)
    }

    fun getCategories(){
        viewModelScope.launch {
            _categoriesState.value = _categoriesState.value.copy(loading = true)
            waitForCategories()
            _categoriesState.value = categoriesState.value.copy(
                loading = false,
                list = listOf(
                    CategoryItem(id=1, name="Kategoria1"),
                    CategoryItem(id=2, name="Kategoria2")
                )
            )
        }
    }

}