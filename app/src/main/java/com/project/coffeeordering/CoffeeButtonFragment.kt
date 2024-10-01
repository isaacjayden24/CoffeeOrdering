package com.project.coffeeordering

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.project.coffeeordering.model.StorageViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [CoffeeButtonFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CoffeeButtonFragment : Fragment() {

    //declare the view model
    private val storageViewModel: StorageViewModel by activityViewModels()

    private var isNavigatingBack = false

    private lateinit var coffeeImageViewButton: ImageView
    private lateinit var coffeeNameButton:TextView
    private lateinit var coffeeDescriptionTextViewButton:TextView
    private lateinit var priceTextViewButton:TextView
    private lateinit var numberUpdateButton:TextView

    private lateinit var detailsIncrementButton:ImageButton
    private lateinit var detailsDecrementButton:ImageButton

    private lateinit var buyNowBtn:Button
    private lateinit var priceIncreaseBtn:Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_coffee_button, container, false)
        coffeeImageViewButton=view.findViewById(R.id.coffeeImageViewButton)
        coffeeNameButton=view.findViewById(R.id.coffeeNameButton)
        coffeeDescriptionTextViewButton=view.findViewById(R.id.coffeeDescriptionTextViewButton)
        priceTextViewButton=view.findViewById(R.id.priceTextViewButton)
        numberUpdateButton=view.findViewById(R.id.numberUpdateButton)
        detailsIncrementButton=view.findViewById(R.id.detailsIncrementButton)
        detailsDecrementButton=view.findViewById(R.id.detailsDecrementButton)
        buyNowBtn=view.findViewById(R.id.buyNowBtn)
        priceIncreaseBtn=view.findViewById(R.id.priceIncreaseBtn)

        // TODO: handle the logic of the increase button on coffee button based on the image resource and observe live data also implement the firebase authentication
        //button to increase on prices
        priceIncreaseBtn.setOnClickListener(){

        }


        // Listen for navigation changes to detect backward navigation
        findNavController().addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.menuFragment) {
                // User is navigating back to MenuDetailsFragment
                isNavigatingBack = true
            }
        }

        detailsIncrementButton.setOnClickListener(){

            storageViewModel.incrementPrice()
        }

        detailsDecrementButton.setOnClickListener(){
            storageViewModel.decrementPrice()
        }

        buyNowBtn.setOnClickListener(){
            findNavController().navigate(R.id.action_coffeeButtonFragment_to_orderSummaryFragment)
        }



        ////// implementing observation of live data

        storageViewModel.coffeeNameModel.observe(viewLifecycleOwner) { coffeeNameModel ->
            coffeeNameButton.text = coffeeNameModel.toString()
        }

        storageViewModel.quantityAmount.observe(viewLifecycleOwner){quantityAmount ->
            numberUpdateButton.text= quantityAmount.toString()
        }

        storageViewModel.imageResId.observe(viewLifecycleOwner){imageResId ->

            coffeeImageViewButton.setImageResource(imageResId)

        }

        //the price of the coffee being observed
        storageViewModel.priceIncrement.observe(viewLifecycleOwner){priceIncrement ->
            priceTextViewButton.text=priceIncrement.toString()
        }

        //observe data for the coffee description
        storageViewModel.coffeeDescription.observe(viewLifecycleOwner){coffeeDescription->
            coffeeDescriptionTextViewButton.text=coffeeDescription.toString()
        }
        return view
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