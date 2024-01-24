package com.example.coffeeordering.model
import androidx.lifecycle.ViewModel
import com.example.coffeeordering.BillingFragment





class StorageViewModel: ViewModel() {

    //storing user information here to be accessed by all fragments
   var userInfo : ArrayList<String> = ArrayList()
}