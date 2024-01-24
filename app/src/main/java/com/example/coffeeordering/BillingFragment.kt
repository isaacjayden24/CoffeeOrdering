package com.example.coffeeordering

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.coffeeordering.model.StorageViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [BillingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BillingFragment : Fragment() {
   // private val sharedViewModel:StorageViewModel by activityViewModels()
    private val sharedViewModel: StorageViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_billing,container,false)


        val checkoutBilling=view.findViewById<Button>(R.id.checkout_button)
        checkoutBilling.setOnClickListener{
            sendDetailsToCheckout()
            //findNavController().navigate(R.id.action_billingFragment_to_paymentFragment)
        }





        return view
    }

//a function to send the details to the checkout
    fun sendDetailsToCheckout(){
      //  val billingInfo=collectBilling()
    //    sharedViewModel.billingInfo=billingInfo
       findNavController().navigate(R.id.action_billingFragment_to_paymentFragment)
    }






    fun collectBilling(){
        //an array to store information for the buyer
        val infoCheckout=ArrayList<String>()
        //declare all the buttons for the input
        val firstName=view?.findViewById<EditText>(R.id.first_name)
        val naming=firstName?.text.toString()
        infoCheckout.add(naming)

        val lastName=view?.findViewById<EditText>(R.id.last_name)
        val lastNaming=lastName?.text.toString()
        infoCheckout.add(lastNaming)

        val address=view?.findViewById<EditText>(R.id.address)
        val addressing=address?.text.toString()
        infoCheckout.add(addressing)

        val stateLiving=view?.findViewById<EditText>(R.id.state)
        val states=stateLiving?.text.toString()
        infoCheckout.add(states)

        val cityLiving=view?.findViewById<EditText>(R.id.city)
        val city=cityLiving?.text.toString()
        infoCheckout.add(city)

        val zipcode=view?.findViewById<EditText>(R.id.zipcode)
        val zip=zipcode?.text.toString()
        infoCheckout.add(zip)

        sharedViewModel.userInfo=infoCheckout

    }


}

