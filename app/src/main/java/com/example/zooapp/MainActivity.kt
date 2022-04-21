package com.example.zooapp


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.animal_killer_ticket.view.*
import kotlinx.android.synthetic.main.animal_ticket.view.*
import kotlinx.android.synthetic.main.animal_ticket.view.imAnimal
import kotlinx.android.synthetic.main.animal_ticket.view.tvDes
import kotlinx.android.synthetic.main.animal_ticket.view.tvName

class MainActivity : AppCompatActivity() {
    var listAnimals = ArrayList<Animal>()
    var adapter:AnimalsAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listAnimals.add(Animal("Gấu","Gấu là một loài động vât có vú",R.drawable.gau,true))
        listAnimals.add(Animal("Khủng long","khủng long là một loài đồng vật đã bị tuyệt chủng",R.drawable.khunglong,false))
        listAnimals.add(Animal("Sóc","Sóc có tốc đồ di chuyển rất nhanh",R.drawable.soc,true))
        listAnimals.add(Animal("Khỉ","Khỉ thường dống trong rừng",R.drawable.khi,false))

        adapter = AnimalsAdapter(this,listAnimals)
        tvListAnimal.adapter = adapter


    }
    fun delete(index:Int){
        listAnimals.removeAt(index)
        adapter!!.notifyDataSetChanged()
    }
    fun add(index: Int){
        listAnimals.add(listAnimals[index])
        adapter!!.notifyDataSetChanged()
    }
    inner class AnimalsAdapter: BaseAdapter {
        var listAnimals = ArrayList<Animal>()
        var context: Context?=null
        constructor(context:Context, listAnimals:ArrayList<Animal>):super(){
            this.listAnimals=listAnimals
            this.context = context
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val animal = listAnimals[p0]
            if (animal.isKiller == true){
                var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView = inflator.inflate(R.layout.animal_killer_ticket,null)
                myView.tvName.text = animal.name!!
                myView.tvDes.text = animal.des!!
                myView.imAnimal.setImageResource(animal.image!!)
                myView.imAnimal.setOnClickListener {
                    //add(p0)
                    //delete(p0)
                    var intent = Intent(context,AnimalInfo::class.java)
                    intent.putExtra("name",animal.name!!)
                    intent.putExtra("des",animal.des!!)
                    intent.putExtra("image",animal.image!!)
                    context!!.startActivity(intent)
                }
                return myView

            }else{
                var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView = inflator.inflate(R.layout.animal_ticket,null)
                myView.tvName.text = animal.name!!
                myView.tvDes.text = animal.des!!
                myView.imAnimal.setImageResource(animal.image!!)
                myView.imAnimal.setOnClickListener {
                    //add(p0)
                    //delete(p0)
                     var intent = Intent(context,AnimalInfo::class.java)
                    intent.putExtra("name",animal.name!!)
                    intent.putExtra("des",animal.des!!)
                    intent.putExtra("image",animal.image!!)
                    context!!.startActivity(intent)
                }
                return myView

            }



        }
        override fun getCount(): Int {
            return listAnimals.size
        }

        override fun getItem(p0: Int): Any {
            return listAnimals[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }



    }

}