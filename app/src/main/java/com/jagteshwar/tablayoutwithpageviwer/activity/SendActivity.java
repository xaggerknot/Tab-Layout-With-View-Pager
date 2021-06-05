package com.jagteshwar.tablayoutwithpageviwer.activity;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jagteshwar.tablayoutwithpageviwer.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class SendActivity extends AppCompatActivity implements PaymentResultListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Checkout.preload(getApplicationContext());
        
    }

    public void commitTransaction(View view) {
        Toast.makeText(this, "send transtion", Toast.LENGTH_SHORT).show();
        Log.d("my tag", "send transtion");
        startPayment();
    }

    public void startPayment() {
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_Y62wpol1ElyGwx");
        /**
         * Instantiate Checkout
         */

        /**
         * Set your logo here
         */
     //   checkout.setImage(R.drawable.logo);

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            options.put("name", "Jagteshwar Singh");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
         //   options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", "50000");//pass amount in currency subunits
            options.put("prefill.email", "xaggerknot@gmail.com");
            options.put("prefill.contact","15147780003");
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(activity, options);

        } catch(Exception e) {
            Log.e("my tag", "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Log.d("my tag", "payment successful: "+s);
    }

    @Override
    public void onPaymentError(int i, String s) {
        Log.d("my tag", "failed: "+ s);
    }
}