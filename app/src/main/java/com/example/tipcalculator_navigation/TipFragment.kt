package com.example.tipcalculator_navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import androidx.navigation.findNavController
import com.example.tipcalculator_navigation.databinding.FragmentTipBinding


class TipFragment : Fragment() {
    private var _binding: FragmentTipBinding?=null
    private val binding get() = _binding!!

    var tipPercent = 0
    var numOfGuests = 0
    var finalAmount=0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentTipBinding.inflate(inflater,container,false)
        val rootview= binding.root
        setUpRadioButtons()
        setUpTipSeekBar()
        setUpNumOfGuestsSpinner()
        val args = TipFragmentArgs.fromBundle(requireArguments())
        binding.subtotalText.text="$${args.subtotalArg}.00"

        binding.nextButton.setOnClickListener{view->
            val action=TipFragmentDirections.actionTipFragmentToFinalTotalFragment(finalAmount.toFloat(), numOfGuests)
            rootview.findNavController().navigate(action)
        }

        return rootview
    }
    override fun onDestroyView(){
        super.onDestroyView()
        _binding=null
    }

    fun setUpTipSeekBar() {
        binding.seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar,
                newProgressValue: Int,
                fromUser: Boolean
            ) {

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                tipPercent=binding.seekbar.progress
                setRadioButtonAsChecked()
                setTipAndTotalTextViews()
            }
        })

    }

    fun setUpRadioButtons() {
        val buttonList: List<View> = listOf(binding.button1, binding.button2, binding.button3, binding.button4)
        for (button in buttonList) {
            button.setOnClickListener { view ->
                when (view.id) {
                    R.id.button1 -> tipPercent = 10
                    R.id.button2 -> tipPercent = 15
                    R.id.button3 -> tipPercent = 18
                    R.id.button4 -> tipPercent = 25
                }
                binding.seekbar.progress = tipPercent
                setTipAndTotalTextViews()
            }
        }
    }

    fun setTipAndTotalTextViews() {
        binding.tipAmount.text = " $tipPercent%"
        val args = TipFragmentArgs.fromBundle(requireArguments())
        finalAmount=args.subtotalArg + (args.subtotalArg * tipPercent)/ 100.0
        binding.total.text = " $${finalAmount}"

    }

    fun setRadioButtonAsChecked() {
        binding.radioGroup.clearCheck()
        if (tipPercent == 10) {
            binding.button1.isChecked = true
        }
        else if(tipPercent==15){
            binding.button2.isChecked=true
        }
        else if(tipPercent==18){
            binding.button3.isChecked=true
        }
        else if(tipPercent==25){
            binding.button4.isChecked=true
        }
    }
    fun setUpNumOfGuestsSpinner(){
        val numOfGuestsAdapter = ArrayAdapter.createFromResource(requireActivity(),
            R.array.num_of_guests,
            android.R.layout.simple_spinner_item)
        numOfGuestsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = numOfGuestsAdapter
        binding.spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {


            override fun onItemSelected(adapterView: AdapterView<*>, childView: View?, position: Int, itemId: Long) {
                numOfGuests = position+1
            }
            override fun onNothingSelected(adapterView: AdapterView<*>?) {  }
        }


    }

}