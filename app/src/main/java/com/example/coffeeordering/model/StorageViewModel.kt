package com.example.coffeeordering.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coffeeordering.data.CheckoutDetails


class StorageViewModel: ViewModel() {

    private val _coffeeNameModel = MutableLiveData<String>()
    val coffeeNameModel: LiveData<String> get() = _coffeeNameModel

    private val _imageResId=MutableLiveData<Int>()
    val imageResId:LiveData<Int>  get()=_imageResId

    private val _checkoutDetails = MutableLiveData<CheckoutDetails>()
    val checkoutDetails: LiveData<CheckoutDetails> = _checkoutDetails


    fun updateCoffeeName(coffeeSelect:String){
        _coffeeNameModel.value= coffeeSelect
    }


    //create a function to load the image on the image view
    // Function to update the image resource ID
    fun setImage(imageResId: Int) {
        _imageResId.value = imageResId
    }


    // Function to update the LiveData the object details is passed here
    fun setCheckoutDetails(details: CheckoutDetails) {
        _checkoutDetails.value = details
    }


}