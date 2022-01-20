package com.example.tipcalculator_navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tipcalculator_navigation.databinding.FragmentFinalTotalBinding
import kotlin.math.round
import com.example.tipcalculator_navigation.databinding.FragmentSubtotalBinding

class FinalTotalFragment : Fragment() {
    private var _binding: FragmentFinalTotalBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentFinalTotalBinding.inflate(inflater,container,false)
        val rootview= binding.root
        val args=FinalTotalFragmentArgs.fromBundle(requireArguments())
        val owe=args.finalTotalArg.toDouble()/args.numGuestsArg
        binding.subtotalTipText.text="$${round(args.finalTotalArg.toDouble()*100)/100}"
        binding.everybodyOwesText.text="$${round(owe*100)/100}"
        binding.roundUpButton.setOnClickListener{view->
            binding.everybodyOwesText.text="$${Math.ceil(owe)}0"
        }
        binding.roundDownButton.setOnClickListener{view->
            binding.everybodyOwesText.text="$${Math.floor(owe)}0"
        }
        return rootview
    }
    override fun onDestroyView(){
        super.onDestroyView()
        _binding=null
    }

}