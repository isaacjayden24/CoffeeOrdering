package com.example.coffeeordering

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.progressindicator.LinearProgressIndicator


class DeliveryMapFragment : Fragment() {


    private lateinit var progressBar: LinearProgressIndicator
    private lateinit var timeLeftTextView: TextView
    private lateinit var addressTextView: TextView
    private lateinit var courierNameTextView: TextView
    private lateinit var courierTitleTextView: TextView
    private lateinit var courierImageView: ShapeableImageView
    private lateinit var callCourierButton: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_delivery_map, container, false)

        // Initialize the views
        progressBar = view.findViewById(R.id.progressBar)
        timeLeftTextView = view.findViewById(R.id.tvTimeLeft)
        addressTextView = view.findViewById(R.id.tvDeliveryAddress)
        courierNameTextView = view.findViewById(R.id.courierName)
        courierTitleTextView = view.findViewById(R.id.courierTitle)
        courierImageView = view.findViewById(R.id.courierImage)
        callCourierButton = view.findViewById(R.id.callCourierButton)





        // Set courier and delivery details
        updateCourierInfo("Brooklyn Simmons", "Personal Courier")
        updateDeliveryStatus("10 minutes left", "Jl. Kpg Sutoyo")
        updateProgress(75) // Update the progress bar to show 75% completion

        // Handle clicking on the call button
        callCourierButton.setOnClickListener {
            // Logic to call the courier (launch dialer with number)
            callCourier()
        }

        return view
    }



    // Function to update courier information dynamically
    private fun updateCourierInfo(name: String, title: String) {
        courierNameTextView.text = name
        courierTitleTextView.text = title
        // Set courier image
        courierImageView.setImageResource(R.drawable.courier_photo)
    }

    // Function to update delivery status text dynamically
    private fun updateDeliveryStatus(timeLeft: String, deliveryAddress: String) {
        timeLeftTextView.text = timeLeft
        addressTextView.text = deliveryAddress
    }

    // Function to update progress on the progress bar
    private fun updateProgress(progress: Int) {
        progressBar.progress = progress
    }

    // Logic for calling the courier (e.g., opens dialer)
    private fun callCourier() {
        val phoneNumber = "tel:123456789" // Replace with actual phone number
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse(phoneNumber)
        startActivity(intent)
    }
}
