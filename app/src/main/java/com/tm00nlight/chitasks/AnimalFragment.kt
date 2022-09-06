package com.tm00nlight.chitasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AnimalFragment: Fragment() {
    private lateinit var zooRecyclerView: RecyclerView
    private lateinit var zooViewModel: ZooViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        zooViewModel = ViewModelProvider(this).get(ZooViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.animal_list_fragment, container, false)
        zooRecyclerView = view.findViewById(R.id.animal_list)
        zooRecyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        zooViewModel.animalLiveData.observe(
            viewLifecycleOwner, Observer { animals ->
                zooRecyclerView.adapter = ZOOAdapter(animals)
            }
        )
    }

    companion object {
        fun newInstance() = AnimalFragment()
    }

    private inner class ZOOHolder(v: View) :
        RecyclerView.ViewHolder(v) {

        val imageView = v.findViewById<ImageView>(R.id.imageView)
        val nameView = v.findViewById<TextView>(R.id.nameView)
        val latinNameView = v.findViewById<TextView>(R.id.latinNameView)
        val lifespanView = v.findViewById<TextView>(R.id.lifespanView)
        val geoView = v.findViewById<TextView>(R.id.geoView)

        fun bindDrawables(url : String) {
            Glide
                .with(requireContext())
                .load(url)
                .placeholder(R.drawable.waiting)
                .into(imageView)
        }

    }

    private inner class ZOOAdapter(private val animals : List<Animal>) :
        RecyclerView.Adapter<ZOOHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZOOHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.animal_item_fragment, parent, false)
            return ZOOHolder(view)
        }

        override fun getItemCount(): Int {
            return animals.size
        }

        override fun onBindViewHolder(holder: ZOOHolder, position: Int) {
            val animal = animals[position]
            holder.nameView.text = animal.name
            holder.latinNameView.text = animal.latin_name
            holder.geoView.text = "Geo: ${animal.geo_range}"
            holder.lifespanView.text = "Lifespan: ${animal.lifespan.toString()} years"

            holder.bindDrawables(animal.image_link)
        }

    }
}