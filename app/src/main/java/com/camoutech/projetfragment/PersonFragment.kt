package com.camoutech.projetfragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.camoutech.projetfragment.databinding.FragmentPersonBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "Name"
private const val ARG_PARAM2 = "Amount"

/**
 * A simple [Fragment] subclass.
 * Use the [PersonFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PersonFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var name: String? = null
    private var amount: Double? = null
    private lateinit var binding: FragmentPersonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(ARG_PARAM1)
            amount = it.getDouble(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentPersonBinding.inflate(
           inflater,
           container,
           false
       )
        val view = binding.root
        binding.nameFragment.text = name?:""
        binding.accountFragment.text = "Argent disponible: ${amount?:0.0}$"
        binding.accountFragment.setOnClickListener {
            val success = listener?.onButtonClick()
            if (success == true && amount!= null) amount = amount!! + 5
            binding.accountFragment.text = "Argent disponible: ${amount?:0.0}$"
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PersonFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: Double) =
            PersonFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putDouble(ARG_PARAM2, param2)
                }
            }
    }

    interface CallBackListener {
        fun onButtonClick(): Boolean
    }

    var listener: CallBackListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as CallBackListener
        } catch (e: java.lang.ClassCastException){
            throw java.lang.ClassCastException(context?.toString())
        }
    }
}