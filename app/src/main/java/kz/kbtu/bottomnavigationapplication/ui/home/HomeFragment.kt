package kz.kbtu.bottomnavigationapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kz.kbtu.bottomnavigationapplication.R

internal const val COUNTER_KEY = "counter_key"
internal const val INSTANCE_KEY = "instance_key"

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var buttonAddInstance: Button
    private lateinit var buttonAddCounter: Button
    private lateinit var textViewInstance: TextView
    private lateinit var textViewCounter: TextView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val instanceCount = arguments?.getInt(INSTANCE_KEY) ?: 0
        homeViewModel.setInstanceCount(instanceCount)
        textViewInstance = view.findViewById(R.id.text_view_instance_home)
        textViewCounter = view.findViewById(R.id.text_view_counter_home)
        buttonAddInstance = view.findViewById(R.id.button_add_instance)
        buttonAddCounter = view.findViewById(R.id.button_add_counter)

        buttonAddInstance.setOnClickListener {
            addNewInstance()
        }
        buttonAddCounter.setOnClickListener {
            homeViewModel.increment()
        }
        homeViewModel.instanceLiveData.observe(viewLifecycleOwner, Observer {
            textViewInstance.text = "Instance: $it"
        })
        homeViewModel.counterLiveData.observe(viewLifecycleOwner, Observer {
            textViewCounter.text = "Counter: $it"
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(COUNTER_KEY, homeViewModel.getCounterCount())
        outState.putInt(INSTANCE_KEY, homeViewModel.getInstanceCount())
    }

    private fun addNewInstance() {
        val navController = findNavController()
        val bundle = Bundle()
        bundle.putInt(INSTANCE_KEY, homeViewModel.getInstanceCount() + 1)
        navController.navigate(R.id.action_navigation_home_self, bundle)
    }
}
