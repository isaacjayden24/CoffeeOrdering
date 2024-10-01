package com.project.coffeeordering.model

import android.content.ClipDescription
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.coffeeordering.data.CheckoutDetails
import com.project.coffeeordering.dataSource.CoffeeDataSource


class StorageViewModel: ViewModel() {

    //declare object for the data source object{used to store coffee descriptions}
    private val coffeeDataSource = CoffeeDataSource()

    //the price to reset the coffee after selected
    val coffeeBasePrice=0.0

    //live data for the description
    private val _coffeeDescription=MutableLiveData<String>()
    val coffeeDescription:LiveData<String> get()=_coffeeDescription

    private val _coffeeNameModel = MutableLiveData<String>()
    val coffeeNameModel: LiveData<String> get() = _coffeeNameModel

    private val _imageResId=MutableLiveData<Int>()
    val imageResId:LiveData<Int>  get()=_imageResId


    //live data for quantity details

    private val _quantityAmount=MutableLiveData<Int>()
    val quantityAmount:LiveData<Int> get() = _quantityAmount



    // observe the whole class as a live data for the chekout details
    private val _checkoutDetails = MutableLiveData<CheckoutDetails>()
    val checkoutDetails: LiveData<CheckoutDetails>  = _checkoutDetails

    // live data for price increment {the actual price of the coffee}
    private val  _priceIncrement=MutableLiveData<Double>()
    val priceIncrement:MutableLiveData<Double> get() = _priceIncrement

    //live data for price decrement
    /*private val _priceDecrement=MutableLiveData<Double>()
    val priceDecrement:LiveData<Double> get() =_priceDecrement*/

    init {
        _priceIncrement.value=0.0
        _quantityAmount.value=0
    }


    // This function allows the ViewModel to fetch the description of the coffee
    fun getCoffeeDescription(coffeeType: String): String? {
        return coffeeDataSource.getCoffeeDescription(coffeeType)
    }

    //function to update the text on live data and remember to destroy the text on destroy
    fun displayCoffeeDescription(description: String){
        _coffeeDescription.value=description

    }

    //function to update on coffee name
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


    //function to increase price on coffee
    fun incrementPrice(newIcreasedPrice:Double){
        _priceIncrement.value=newIcreasedPrice
    }



    //function to increase on quantity of coffee
    fun incrementPrice(){

        _quantityAmount.value = (_quantityAmount.value ?: 0) + 1
    }


   //function to decrease on quantity of coffee
    fun decrementPrice() {
        val currentQuantity = _quantityAmount.value ?: 0
        if (currentQuantity > 0) {
            _quantityAmount.value = currentQuantity - 1
        }
    }

    //function to reset the quantity amount
    fun resetQuantity(){
        _quantityAmount.value=0
    }

    fun resetPriceToBase(){
        _priceIncrement.value=coffeeBasePrice
    }


}