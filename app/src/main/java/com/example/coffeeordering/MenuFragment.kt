package com.example.coffeeordering

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.coffeeordering.model.StorageViewModel


class MenuFragment : Fragment() {

   //declare the view model
    private val storageViewModel: StorageViewModel by activityViewModels()
    //declare coffee types here
    private val caffeMocha:String="Caffe Mocha"
    private val flatWhite:String="Flat White"
    private val americanoMisto:String="Americano Misto"
    private val capuccino:String="Cappuccino"
    private val machiatto:String="Macchiato"

    //coffee names declared here
    private lateinit var caffeeMochaName:TextView
    private lateinit var flatWhiteName:TextView
    private lateinit var americanoMistroName:TextView
    private lateinit var capuccinoName:TextView
    private lateinit var macchiatoName:TextView

   // private lateinit var caffeeMochaImage:ImageView

    private lateinit var caffeMochaBtn:Button
    private lateinit var flatWhiteBtn:Button
    private lateinit var americanoMistroBtn:Button
    private lateinit var capuccinoBtn:Button
    private lateinit var macchiatoBtn:Button


    //coffee prices declared here
    private lateinit var caffeMochaPrice:TextView
    private lateinit var flatWhitePrice:TextView
    private lateinit var americanoMistroPrice:TextView
    private lateinit var capuccinoPrice:TextView
    private lateinit var macchiatoPrice:TextView


    //naviagtion buttons to be declared here
    private lateinit var machiatoBtnNavigation:Button
    private lateinit var flatWhiteBtnNavigation:Button
    private lateinit var americanoBtnNavigation:Button
    private lateinit var caffeeMochaBtnNavigation:Button
    private lateinit var capuccinoBtnNavigation:Button

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
        americanoMistroName=view.findViewById(R.id.americanoMistroName)
        capuccinoName=view.findViewById(R.id.capuccinoName)
        macchiatoName=view.findViewById(R.id.macchiatoName)

        //caffeeMochaImage=view.findViewById(R.id.caffeMochaImage)
        caffeMochaBtn=view.findViewById(R.id.caffeMochaBtn)
        flatWhiteBtn=view.findViewById(R.id.flatWhiteBtn)
        americanoMistroBtn=view.findViewById(R.id.americanoMistroBtn)
        capuccinoBtn=view.findViewById(R.id.capuccinoBtn)
        macchiatoBtn=view.findViewById(R.id.macchiatoBtn)

        caffeMochaPrice=view.findViewById(R.id.caffeMochaPrice)
        flatWhitePrice=view.findViewById(R.id.flatWhitePrice)
        americanoMistroPrice=view.findViewById(R.id.americanoMistroPrice)
        capuccinoPrice=view.findViewById(R.id.capuccinoPrice)
        macchiatoPrice=view.findViewById(R.id.macchiatoPrice)

        machiatoBtnNavigation=view.findViewById(R.id.machiatoBtnNavigation)
        flatWhiteBtnNavigation=view.findViewById(R.id.flatWhiteBtnNavigation)
        americanoBtnNavigation=view.findViewById(R.id.americanoBtnNavigation)
        caffeeMochaBtnNavigation=view.findViewById(R.id.caffeeMochaBtnNavigation)
        capuccinoBtnNavigation=view.findViewById(R.id.capuccinoBtnNavigation)



        ////////////////////////////////////////////////////////////////////////////////////////////
        //handle top bar horizontal navigation buttons

        machiatoBtnNavigation.setOnClickListener(){

            val selectedCoffee = getCoffeeName(macchiatoName)
            handleCoffeeSelection(selectedCoffee)

            val selectedImageRes = R.drawable.macchiato
            storageViewModel.setImage(selectedImageRes)

            passCoffeeDescription(machiatto)
        }

        flatWhiteBtnNavigation.setOnClickListener(){
            val selectedCoffee = getCoffeeName(flatWhiteName)
            handleCoffeeSelection(selectedCoffee)

            val selectedImageRes = R.drawable.flat_white
            storageViewModel.setImage(selectedImageRes)

            passCoffeeDescription(flatWhite)

        }

        americanoBtnNavigation.setOnClickListener(){

            val selectedCoffee = getCoffeeName(americanoMistroName)
            handleCoffeeSelection(selectedCoffee)

            val selectedImageRes = R.drawable.americano_misto // Example image resource
            storageViewModel.setImage(selectedImageRes)

            passCoffeeDescription(americanoMisto)

        }


        caffeeMochaBtnNavigation.setOnClickListener(){

            val selectedCoffee = getCoffeeName(caffeeMochaName)
            handleCoffeeSelection(selectedCoffee)

            val selectedImageRes = R.drawable.coffee_recyler1
            storageViewModel.setImage(selectedImageRes)

            passCoffeeDescription(caffeMocha)


        }

        capuccinoBtnNavigation.setOnClickListener(){

            val selectedCoffee = getCoffeeName(capuccinoName)
            handleCoffeeSelection(selectedCoffee)

            val selectedImageRes = R.drawable.capuccino
            storageViewModel.setImage(selectedImageRes)

            passCoffeeDescription(capuccino)

        }

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Set click listeners on coffee items ( ImageView, the entire layout, or the TextView)
        view.findViewById<ImageView>(R.id.caffeMochaImage).setOnClickListener {
            val selectedCoffee = getCoffeeName(caffeeMochaName)
            handleCoffeeSelection(selectedCoffee)

            val selectedImageRes = R.drawable.coffee_recyler1
            storageViewModel.setImage(selectedImageRes)

            passCoffeeDescription(caffeMocha)

        }


        caffeMochaBtn.setOnClickListener(){
            updatePrice(caffeMochaPrice,4.53)
        }

        // flat white coffee

        view.findViewById<ImageView>(R.id.flatWhiteImage).setOnClickListener {
            val selectedCoffee = getCoffeeName(flatWhiteName)
            handleCoffeeSelection(selectedCoffee)

            val selectedImageRes = R.drawable.flat_white
            storageViewModel.setImage(selectedImageRes)

            passCoffeeDescription(flatWhite)
        }

        flatWhiteBtn.setOnClickListener {
            updatePrice(flatWhitePrice,4.3)
        }




        //americano mistro coffee
        view.findViewById<ImageView>(R.id.americanoMistroImage).setOnClickListener {
            val selectedCoffee = getCoffeeName(americanoMistroName)
            handleCoffeeSelection(selectedCoffee)

            val selectedImageRes = R.drawable.americano_misto // Example image resource
            storageViewModel.setImage(selectedImageRes)

            passCoffeeDescription(americanoMisto)
        }

        americanoMistroBtn.setOnClickListener(){
            updatePrice(americanoMistroPrice,3.53)
        }



        //cappuccino  coffee
        view.findViewById<ImageView>(R.id.capuccinoImage).setOnClickListener {
            val selectedCoffee = getCoffeeName(capuccinoName)
            handleCoffeeSelection(selectedCoffee)

            val selectedImageRes = R.drawable.capuccino
            storageViewModel.setImage(selectedImageRes)

            passCoffeeDescription(capuccino)
        }

        capuccinoBtn.setOnClickListener(){
            updatePrice(capuccinoPrice,5.0)
        }


        //machiatto coffee

        view.findViewById<ImageView>(R.id.macchiatoImage).setOnClickListener {
            val selectedCoffee = getCoffeeName(macchiatoName)
            handleCoffeeSelection(selectedCoffee)

            val selectedImageRes = R.drawable.macchiato
            storageViewModel.setImage(selectedImageRes)

            passCoffeeDescription(machiatto)
        }

        macchiatoBtn.setOnClickListener(){
            updatePrice(macchiatoPrice,3.96)
        }








        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Show the ActionBar again when this fragment is destroyed or removed
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

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




    private fun updatePrice(passedTextView: TextView, price: Double) {
        // Get the current price from the TextView, remove the dollar sign, and convert to Double
        val currentPriceText = passedTextView.text.toString().replace("$", "")
        val currentPrice = currentPriceText.toDoubleOrNull() ?: 0.0

        // Add the new price to the current price
        val updatedPrice = currentPrice + price

        // Update the TextView with the new price, formatted with a dollar sign
        passedTextView.text = String.format("$%.2f", updatedPrice)

        storageViewModel.incrementPrice(updatedPrice)
    }


    //function to update coffee description
   private fun passCoffeeDescription(coffeetype:String){
       val description=storageViewModel.getCoffeeDescription(coffeetype)
       if (description != null) {
           storageViewModel.displayCoffeeDescription(description)
       }


    }


}
