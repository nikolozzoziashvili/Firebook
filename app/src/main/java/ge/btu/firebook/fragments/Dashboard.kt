package ge.btu.firebook.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ge.btu.firebook.R

class Dashboard : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val item =  layoutInflater.inflate(R.layout.fragment_dashboard, container, false)
        return item
    }
}