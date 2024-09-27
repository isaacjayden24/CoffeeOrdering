package com.example.coffeeordering

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.coffeeordering.model.StorageViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [CoffeeDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CoffeeDetailsFragment : Fragment() {


    //declare the view model
    private val storageViewModel: StorageViewModel by activityViewModels()

    private var isNavigatingBack = false

    private lateinit var coffeeName:TextView
    private lateinit var coffeeImageView:ImageView
    private lateinit var buyNowBtn:Button
    private lateinit var priceTextView: TextView
    private lateinit var detailsIncrement:ImageButton
    private lateinit var detailsDecrement:ImageButton
    private lateinit var numberUpdate:TextView
    private lateinit var coffeeDescriptionTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_coffee_details, container, false)


        // Set up toolbar
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        // Enable back button in the toolbar
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Handle the navigation icon click
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack() // Go back to the previous fragment
        }


        coffeeName=view.findViewById(R.id.coffeeName)
        coffeeImageView=view.findViewById(R.id.coffeeImageView)
        buyNowBtn=view.findViewById(R.id.buyNowBtn)
        priceTextView=view.findViewById(R.id.priceTextView)
        detailsIncrement=view.findViewById(R.id.detailsIncrement)
        numberUpdate=view.findViewById(R.id.numberUpdate)
        detailsDecrement=view.findViewById(R.id.detailsDecrement)
        coffeeDescriptionTextView=view.findViewById(R.id.coffeeDescriptionTextView)



        // Listen for navigation changes to detect backward navigation
        findNavController().addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.menuFragment) {
                // User is navigating back to MenuDetailsFragment
                isNavigatingBack = true
            }
        }




        detailsIncrement.setOnClickListener(){
           // storageViewModel.incrementPriceDetails(priceTextView)
            //declare number update to be passed as a parameter
            storageViewModel.incrementPrice()
        }

        detailsDecrement.setOnClickListener(){
            storageViewModel.decrementPrice()
        }


        buyNowBtn.setOnClickListener(){
            findNavController().navigate(R.id.action_coffeeDetailsFragment_to_orderSummaryFragment)
        }



        storageViewModel.coffeeNameModel.observe(viewLifecycleOwner) { coffeeNameModel ->
            coffeeName.text = coffeeNameModel.toString()
        }

        storageViewModel.quantityAmount.observe(viewLifecycleOwner){quantityAmount ->
            numberUpdate.text= quantityAmount.toString()
        }

        storageViewModel.imageResId.observe(viewLifecycleOwner){imageResId ->

            coffeeImageView.setImageResource(imageResId)

        }

        //the price of the coffee being observed
        storageViewModel.priceIncrement.observe(viewLifecycleOwner){priceIncrement ->
            priceTextView.text=priceIncrement.toString()
        }

        //observe data for the coffee description
        storageViewModel.coffeeDescription.observe(viewLifecycleOwner){coffeeDescription->
            coffeeDescriptionTextView.text=coffeeDescription.toString()
        }







        return  view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clear data of the price only when navigating back
        if (isNavigatingBack) {
            storageViewModel.resetPriceToBase() // Or any other data reset
        }
        //clear the data of the quantity
        if (isNavigatingBack) {
            storageViewModel.resetQuantity()
        }
    }

}


