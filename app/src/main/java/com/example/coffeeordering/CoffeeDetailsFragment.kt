package com.example.coffeeordering

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
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

    private lateinit var coffeeName:TextView
    private lateinit var coffeeImageView:ImageView
    private lateinit var buyNowBtn:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_coffee_details, container, false)

        coffeeName=view.findViewById(R.id.coffeeName)
        coffeeImageView=view.findViewById(R.id.coffeeImageView)
        buyNowBtn=view.findViewById(R.id.buyNowBtn)


        buyNowBtn.setOnClickListener(){
            findNavController().navigate(R.id.action_coffeeDetailsFragment_to_orderSummaryFragment)
        }



        storageViewModel.coffeeNameModel.observe(viewLifecycleOwner) { coffeeNameModel ->
            coffeeName.text = coffeeNameModel.toString()
        }

        storageViewModel.imageResId.observe(viewLifecycleOwner){imageResId ->

            coffeeImageView.setImageResource(imageResId)

        }







        return  view
    }


}


