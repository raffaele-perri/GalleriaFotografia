package com.example.galleria.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.galleria.R
import com.example.galleria.adapters.BeerListAdapter
import com.example.galleria.model.Beer
import com.example.galleria.viewModel.ListViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var recycler:RecyclerView
    private var page = 1
    private var isLoading = false
    private var isLastPage = false
    private val model: ListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return  inflater.inflate(R.layout.fragment_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.button)
        recycler = view.findViewById(R.id.recyclerView)
        val linearLayoutManager = LinearLayoutManager(activity)
        recycler.addItemDecoration(DividerItemDecoration(recycler.context,DividerItemDecoration.VERTICAL))
        recycler.layoutManager = linearLayoutManager
        recycler.addOnScrollListener(object: PaginationScrollListener(linearLayoutManager){
            override fun loadMoreItems() {
                model.loadBeers(page)
                page++
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

        })
        model.getBeers().observe(viewLifecycleOwner, { beers ->
            recycler.adapter = BeerListAdapter(beers){
                beer ->   Toast.makeText(context, "${beer.name}", Toast.LENGTH_SHORT).show()
                val action = ListFragmentDirections.actionListFragmentToDetailFragment(beer.id)
                findNavController().navigate(action)
            }
            isLoading = false
        })

        model.loadBeers()

        button.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(10)
            findNavController().navigate(action)
        };
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}


abstract class PaginationScrollListener(val linearLayoutManager: LinearLayoutManager) :RecyclerView.OnScrollListener(){

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = linearLayoutManager.childCount
        val totalItemCount = linearLayoutManager.itemCount
        val firstVisibleItemPos = linearLayoutManager.findFirstVisibleItemPosition()
        if(!isLastPage() && !isLoading()){
            if((visibleItemCount + firstVisibleItemPos) >= totalItemCount && firstVisibleItemPos >= 0)
                loadMoreItems()

        }
    }

    abstract fun loadMoreItems()

    abstract fun isLastPage(): Boolean

    abstract fun isLoading(): Boolean
}