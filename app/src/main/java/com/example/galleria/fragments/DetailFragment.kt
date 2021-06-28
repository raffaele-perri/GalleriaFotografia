package com.example.galleria.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.app_domain.model.Beer
import com.example.galleria.R
import com.example.galleria.databinding.FragmentDetailBinding
import com.example.galleria.databinding.FragmentListBinding
import com.example.galleria.viewModel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class DetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val args: DetailFragmentArgs by navArgs()
    private val model: DetailViewModel by viewModels()
    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var beerDetail: Beer
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
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = args.idBeer
        model.getBeerDetail().observe(viewLifecycleOwner, { beer ->
            Toast.makeText(context, beer.name, Toast.LENGTH_SHORT).show()
            binding.textDetailName.text =  beer.name
            binding.textDetailTag.text =  beer.tagLine
            binding.textDetailDescription.text =  beer.description
            val imageView = binding.imageView
            model.isBeerPresent(beer)
            Glide.with(this).load(beer.imageUrl).into(imageView)
            beerDetail = beer
        })

        model.isFavourite().observe(viewLifecycleOwner, { favourite ->
            if (favourite)
                binding.toggleButton.check(R.id.favouriteButton)
            else
                binding.toggleButton.uncheck(R.id.favouriteButton)

            binding.toggleButton.addOnButtonCheckedListener{ group,checkedId, isChecked->
                if (isChecked){
                    model.insertBeer(beerDetail)
                    Toast.makeText(requireContext(),"AGGIUNTO AI PREFERITI",Toast.LENGTH_SHORT).show()
                }else
                    model.removeBeer(beerDetail)
                Toast.makeText(requireContext(),"RIMOSSO DAI PREFERITI",Toast.LENGTH_SHORT).show()
            }
        })

        model.loadBeerDetail(id)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}