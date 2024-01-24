package com.example.coffeeordering

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.coffeeordering.model.StorageViewModel

class PaymentFragment : Fragment() {
    private val sharedViewModel:StorageViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_payment,container,false)




        val shippingButton=view.findViewById<Button>(R.id.button_confirmation)
        //shippingButton.text= orderedDetails.toString()
        shippingButton.setOnClickListener{
           // sendOrderToApp()
            sendingOrder()
        }

        // Inflate the layout for this fragment
        return view
    }

    fun sendingOrder(){

        val emailAddress="Isaacjayden27@gmail.com"
        val subject="Order confirmation"
        val messageBody=buildEmailMessage(sharedViewModel.userInfo)
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$emailAddress")
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, messageBody)
        }
        // Check if there's an app available to handle the intent
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            requireActivity().startActivity(intent)
        } else {
            // Handle the case where no email app is installed
            Toast.makeText(requireContext(), "No email app installed", Toast.LENGTH_SHORT).show()
        }


    }

    private fun buildEmailMessage(userInfo: ArrayList<String>): String {
        if (userInfo.isEmpty()) {
            return "No user information available"
        }
        val stringBuilder = StringBuilder()
        stringBuilder.append("First Name: ${userInfo[0]}\n")
        stringBuilder.append("Last Name: ${userInfo[1]}\n")
        stringBuilder.append("Address: ${userInfo[2]}\n")
        stringBuilder.append("State: ${userInfo[3]}\n")
        stringBuilder.append("City: ${userInfo[4]}\n")
        stringBuilder.append("Zipcode: ${userInfo[5]}\n")

        return stringBuilder.toString()
    }




}