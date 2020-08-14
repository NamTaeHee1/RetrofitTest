package com.example.retrofittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    val retrofit = Retrofit.Builder().baseUrl("http://localhost:3000")
            .addConverterFactory(GsonConverterFactory.create()).build();
    val service = retrofit.create(RetrofitNetwork::class.java);
    service.listUser()?.enqueue(object : Callback<Array>{
        override fun onFailure(call: Call<Array>?, t: Throwable?) {}
            override fun onResponse(call: Call<Array>, response: Response<Array>)
            {
                Log.d("Response :: ", response?.body().toString())
                var data : Array? = response?.body()
                for ( i in data!!)
                {
                    Log.i("data" , i.toString())
                }
            }
    })

    interface RetrofitNetwork {
        @GET("/network") fun listUser() : Call<Array>
    }


}