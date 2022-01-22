package com.wixsite.mupbam1.resume.poce13

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Instance of users list using the data model class.
        val poceList: ArrayList<Pocemon> = ArrayList()


        try {
            // As we have JSON object, so we are getting the object
            //Here we are calling a Method which is returning the JSON object
            val obj = JSONObject(getJSONFromAssets()!!)
        //    Log.d("MyLog","obj-$obj")
            // fetch JSONArray named users by using getJSONArray
            val pocemonArray = obj.getJSONArray("results")
            //Log.d("MyLog","pocemonArray-$pocemonArray")
            // Get the users data using for loop i.e. id, name, email and so on

            for (i in 0 until pocemonArray.length()) {
                // Create a JSONObject for fetching single User's Data

                val pocemon = pocemonArray.getJSONObject(i).getString("name")

                /*
                val pocemon2 = pocemonArray.getJSONObject(i)

                Log.d("MyLog","pocemon-$pocemon")
                // Fetch id store it in variable
                val count = pocemon2.getInt("count")
                Log.d("MyLog","pocemon_i-$i")
                val results = pocemon2.getString("results")
                Log.d("MyLog","pocemonName-$results")
               val name = pocemon2.getString("name")
                Log.d("MyLog","pocemonName-$name")
                val next = pocemon2.getString("next")
               val previous = pocemon2.getString("previous")
              val url = pocemon2.getString("url")
               Log.d("MyLog","pocemonURL-$url")


                // create a object for getting phone numbers data from JSONObject
               // val phone = pocemon.getJSONObject("phone")
                // fetch mobile number
               // val mobile = phone.getString("mobile")
                // fetch office number
                //val office = phone.getString("office")


                 */

                // Now add all the variables to the data model class and the data model class to the array list.
              val userDetails =Pocemon(pocemon)

                // add the details in the list
                poceList.add(userDetails)
            }
            /*
            for (n in 0..poceList.size-1){
                Log.d("MyLog","poceList-${poceList[n]}")
            }

             */
        } catch (e: JSONException) {
            //exception
            e.printStackTrace()
        }

        // Set the LayoutManager that this RecyclerView will use.
        rvUsersList.layoutManager = LinearLayoutManager(this)
        // Adapter class is initialized and list is passed in the param.
        val itemAdapter = rvAdapter(this, poceList)
        // adapter instance is set to the recyclerview to inflate the items.
        rvUsersList.adapter = itemAdapter

    }


    /**
     * Method to load the JSON from the Assets file and return the object
     */
    private fun getJSONFromAssets(): String? {

        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val myJSONFile = assets.open("pocemon.json")
            val size = myJSONFile.available()
            val buffer = ByteArray(size)
            myJSONFile.read(buffer)
            myJSONFile.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}