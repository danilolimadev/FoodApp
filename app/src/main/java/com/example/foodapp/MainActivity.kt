package com.example.foodapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.food_ticket.view.*

class MainActivity : AppCompatActivity() {

    var adapter:FoodAdapter?=null
    var listOfFoods = ArrayList<Food>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //load foods
        listOfFoods.add(Food("Coffee", "Description of name", R.drawable.coffee_pot))
        listOfFoods.add(Food("Espresso", "Description of name", R.drawable.espresso))
        listOfFoods.add(Food("French Fries", "Description of name", R.drawable.french_fries))
        listOfFoods.add(Food("Honey", "Description of name", R.drawable.honey))
        listOfFoods.add(Food("Strawberry Ice Cream", "Description of name", R.drawable.strawberry_ice_cream))
        listOfFoods.add(Food("Sugar Cubes", "Description of name", R.drawable.sugar_cubes))
        adapter = FoodAdapter(this, listOfFoods)

        gvListFood.adapter = adapter
    }

    class FoodAdapter:BaseAdapter{
        var listOfFood = ArrayList<Food>()
        var context: Context?=null
        constructor(context:Context, listOfFood:ArrayList<Food>):super(){
            this.context = context
            this.listOfFood = listOfFood
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val food = this.listOfFood[position]
            var inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var foodView = inflater.inflate(R.layout.food_ticket, null)
            foodView.ivFoodImage.setImageResource(food.image!!)
            foodView.ivFoodImage.setOnClickListener {
                val intent = Intent(context, FoodDetails::class.java)
                intent.putExtra("name", food.name!!)
                intent.putExtra("des", food.des!!)
                intent.putExtra("image", food.image!!)
                context!!.startActivity(intent)
            }
            foodView.tvName.text = food.name!!
            return foodView
        }

        override fun getItem(position: Int): Any {
            return listOfFood[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listOfFood.size
        }

    }
}
