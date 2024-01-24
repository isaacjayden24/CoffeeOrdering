package com.example.coffeeordering

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import androidx.fragment.app.activityViewModels
import com.example.coffeeordering.databinding.FragmentMenuBinding
import com.example.coffeeordering.model.StorageViewModel



/**
 * A simple [Fragment] subclass.
 * Use the [MenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuFragment : Fragment() {
    private val sharedViewModel: StorageViewModel by activityViewModels()


//declare variables for prices and toppings price
    private var  cappuchino_price =10.0
    private var latte_price: Double =15.0
    private var  americano_price=20.0
    private var  espresso_price=25.0


    private var quantity = 0.0
    private val whippedCreamPrice = 0.10  // Price for whipped cream topping
    private val chocolatePrice = 0.20  // Price for chocolate topping
    private val nutellaPrice = 0.15  // Price for nutella topping

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    private lateinit var binding: FragmentMenuBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMenuBinding.inflate(inflater, container, false)
        val view = binding.root

        // Inflate the layout for this fragment
        //val view = inflater.inflate(R.layout.fragment_menu, container, false)


        val button4=view.findViewById<Button>(R.id.buttonLogout)
        button4.setOnClickListener{
           findNavController().navigate(R.id.action_menuFragment_to_loginFragment3)
        }
        val orderButton=view.findViewById<Button>(R.id.buttonOrder)
        orderButton.setOnClickListener{
            getYourOrder()

        }
        val nextOrder=view.findViewById<Button>(R.id.buttonNextOrder)
        nextOrder.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment_to_billingFragment)
        }

        val resetOrderButton=view.findViewById<Button>(R.id.buttonClearOrder)
        resetOrderButton.setOnClickListener{
            clearOrder()
        }






        return view
    }



    //function to calculate coffee price
   private fun calculateCoffeePrice(coffeePrice:String):Double{
         return  when(coffeePrice) {
            "Espresso" -> espresso_price
            "Americano" -> americano_price
            "Cappuccino" -> cappuchino_price
            "Latte" -> latte_price
             else->0.0
        }

    }

    //function to calculate price of toppings
    private fun calculateToppingPrice(toppings:List<String>):Double{
        var totalPrice=0.0
        for (topping in toppings){
            totalPrice += when(topping){
            "Whipped Cream"->whippedCreamPrice
            "Chocolate Cream"->chocolatePrice
             "Nutella Cream"->nutellaPrice
                else->0.0
        }
        }
        return totalPrice
    }

    //function to clear order

     private fun clearOrder() {
         quantity = 0.0
         binding.radioGroupCoffee.clearCheck()


         binding.checkBoxNutella.isChecked = false
         binding.checkBoxChocolate.isChecked = false
         binding.checkBoxWhippedCream.isChecked = false
    }

    fun getYourOrder() {
        // logic to calculate type of coffee selected




        val radioGroupCoffee = view?.findViewById<RadioGroup>(R.id.radioGroupCoffee)
        val selectedCoffeeId = radioGroupCoffee?.checkedRadioButtonId
        val selectedCoffeeType = when (selectedCoffeeId) {
            R.id.radioButtonEspresso -> "Espresso"
            R.id.radioButtonAmericano -> "Americano"
            R.id.radioButtonCappuccino -> "Cappuccino"
            R.id.radioButtonLatte -> "Latte"
            else -> null
        }

        //logic to calculate price of toppings selected
        //create an array to store the toppings
        //declare the buttons
        val whippedCream=view?.findViewById<CheckBox>(R.id.checkBoxWhippedCream)
        val chocoCream=view?.findViewById<CheckBox>(R.id.checkBoxChocolate)
        val nutellaCream=view?.findViewById<CheckBox>(R.id.checkBoxNutella)

        val toppings = ArrayList<String>()

        if (whippedCream!!.isChecked) {
            toppings.add("Whipped Cream")
        }
        if (chocoCream!!.isChecked) {
            toppings.add("Chocolate Cream")
        }
        if (nutellaCream!!.isChecked) {
            toppings.add("Nutella Cream")
        }
        //find the total price of the coffee and display it
        // val orderCoffee=calculateCoffeePrice(coffeePrice:String)
        val coffeePrice = selectedCoffeeType?.let { calculateCoffeePrice(it) } ?: 0.0
        val orderToppings=calculateToppingPrice(toppings)
        // val totalPrice = coffeePrice + orderToppings
        val totalPrice:Double=coffeePrice + orderToppings.toDouble()
        //display the price in the text view
        val displayText=view?.findViewById<TextView>(R.id.textViewQuantity)
        val formattedTotalPrice = getString(R.string.total_price_format,totalPrice)
        displayText?.text = formattedTotalPrice


    }



}

