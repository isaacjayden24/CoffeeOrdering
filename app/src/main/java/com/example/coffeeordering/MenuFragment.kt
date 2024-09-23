package com.example.coffeeordering

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.coffeeordering.model.StorageViewModel
import kotlinx.coroutines.delay

class MenuFragment : Fragment() {

   //declare the view model
    private val storageViewModel: StorageViewModel by activityViewModels()

    private lateinit var caffeeMochaName:TextView
    private lateinit var flatWhiteName:TextView
    private lateinit var caffeeMochaImage:ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Hide the ActionBar when this fragment is displayed
        (activity as AppCompatActivity).supportActionBar?.hide()

        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.menu_onboard, container, false)

        caffeeMochaName=view.findViewById(R.id.caffeMochaName)
        flatWhiteName=view.findViewById(R.id.flatWhiteName)

       caffeeMochaImage=view.findViewById(R.id.caffeMochaImage)


        // Set click listeners on coffee items ( ImageView, the entire layout, or the TextView)
        view.findViewById<ImageView>(R.id.caffeMochaImage).setOnClickListener {
            val selectedCoffee = getCoffeeName(caffeeMochaName)
            handleCoffeeSelection(selectedCoffee)
        }

        view.findViewById<ImageView>(R.id.flatWhiteImage).setOnClickListener {
            val selectedCoffee = getCoffeeName(flatWhiteName)
            handleCoffeeSelection(selectedCoffee)

            val selectedImageRes = R.drawable.flat_white // Example image resource
            storageViewModel.setImage(selectedImageRes)
        }





        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Show the ActionBar again when this fragment is destroyed or removed
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    //function to extract name of the coffee
    private fun getCoffeeName(coffeeName:TextView):String{

        //extract the name of the coffee from the text view

        return coffeeName.text.toString()
    }
    //load coffee name to the view model
    private fun handleCoffeeSelection(selectedCoffee:String){


        storageViewModel.updateCoffeeName(selectedCoffee)
        findNavController().navigate(R.id.action_menuFragment_to_coffeeDetailsFragment)


    }

    //function to pass image id and load it to storage view model

   /* private fun handlePictureCoffee(imageId:ImageView){
        storageViewModel.setImage(imageId)
    }*/

}
