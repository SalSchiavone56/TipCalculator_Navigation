package com.example.tipcalculator_navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.tipcalculator_navigation.databinding.FragmentSubtotalBinding


class SubtotalFragment : Fragment() {
    private var _binding: FragmentSubtotalBinding? = null
    private val binding get() = _binding!!
    val numbersPicked: MutableList<Button> = mutableListOf()
    var subtotal = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSubtotalBinding.inflate(inflater, container, false)
        val rootview = binding.root
        binding.subtotalCount.text="$${subtotal}.00"
        binding.titleButton1.setOnClickListener{view->
            numbersPicked.add(binding.titleButton1)
            addDigit()
        }
        binding.titleButton2.setOnClickListener{view->
            numbersPicked.add(binding.titleButton2)
            addDigit()
        }
        binding.titleButton3.setOnClickListener{view->
            numbersPicked.add(binding.titleButton3)
            addDigit()
        }
        binding.titleButton4.setOnClickListener{view->
            numbersPicked.add(binding.titleButton4)
            addDigit()
        }
        binding.titleButton5.setOnClickListener{view->
            numbersPicked.add(binding.titleButton5)
            addDigit()
        }
        binding.titleButton6.setOnClickListener{view->
            numbersPicked.add(binding.titleButton6)
            addDigit()
        }
        binding.titleButton7.setOnClickListener{view->
            numbersPicked.add(binding.titleButton7)
            addDigit()
        }
        binding.titleButton8.setOnClickListener{view->
            numbersPicked.add(binding.titleButton8)
            addDigit()
        }
        binding.titleButton9.setOnClickListener{view->
            numbersPicked.add(binding.titleButton9)
            addDigit()
        }
        binding.titleButton0.setOnClickListener{view->
            numbersPicked.add(binding.titleButton0)
            addDigit()
        }


        binding.doneButton.setOnClickListener{view->
            if(subtotal>0){
                val action = SubtotalFragmentDirections.actionSubtotalFragmentToTipFragment(subtotal)
                rootview.findNavController().navigate(action)
            }
        }
        binding.backspaceButton.setOnClickListener{view->
            subtotal /= 10
            binding.subtotalCount.text="$${subtotal}.00"
            numbersPicked.removeLast()
        }
        return rootview
    }
    override fun onDestroyView(){
        super.onDestroyView()
        _binding=null
    }
    fun addDigit(){
        subtotal=0
        for(buttonCheck in numbersPicked) {
                subtotal = subtotal * 10 + (buttonCheck.text.toString().toInt())

        }
        binding.subtotalCount.text="$${subtotal}.00"
    }

}