package com.mage.navigationdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OriginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

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
        val content = inflater.inflate(R.layout.fragment_origin, container, false)
        val btn = content.findViewById<Button>(R.id.btn_todistination)
        val btnTC = content.findViewById<Button>(R.id.btn_toC)
        btn.setOnClickListener {
            val findNavController = findNavController()
            findNavController.navigate(R.id.destiationFragment, bundleOf("param1" to "我是AFragment传过来的参数"))
        }
        btnTC.setOnClickListener {
            AFragmentDirections.actionAFragmentToDestiationFragment()
            val args = CFragmentArgs.Builder().setData("AFragment传输到CFragment的数据").build().toBundle()
            val findNavController = findNavController()
            findNavController.navigate(R.id.CFragment,args)
        }
        return content
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}