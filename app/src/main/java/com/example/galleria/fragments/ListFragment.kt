package com.example.galleria.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.galleria.adapters.BeerListAdapter
import com.example.galleria.databinding.FragmentListBinding
import com.example.galleria.viewModel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class ListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var recycler:RecyclerView
    private var page = 1
    private var isLoading = false
    private var isLastPage = false
    private val model: ListViewModel by viewModels()
    private var _binding : FragmentListBinding? = null
    private val binding get() = _binding!!
    private var adapter : BeerListAdapter? = null
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
    ): View {

        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = binding.button
        recycler = binding.recyclerView
        adapter = BeerListAdapter()
        adapter?.listener ={ beer->
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(beer.id)
            findNavController().navigate(action)
        }
        recycler.layoutManager = GridLayoutManager(requireContext(),3)
        recycler.adapter = adapter
        //recycler.addItemDecoration(DividerItemDecoration(recycler.context,DividerItemDecoration.VERTICAL))
        //recycler.layoutManager = linearLayoutManager
        /*recycler.addOnScrollListener(object: PaginationScrollListener(recycler.layoutManager){
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

        })*/
        model.getBeers().observe(viewLifecycleOwner, { beers ->
//            recycler.adapter = BeerListAdapter(beers){beer ->
//            }
            adapter?.beerList = beers
            //adapter?.notifyDataSetChanged()
            //adapter.notifyItemInserted(0)
            isLoading = false
        })

        model.loadBeers()

        button.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(10)
            findNavController().navigate(action)
        }
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


abstract class PaginationScrollListener(private val linearLayoutManager: LinearLayoutManager) :RecyclerView.OnScrollListener(){

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