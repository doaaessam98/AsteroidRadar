package com.example.asteroidradar.ui.asteroids

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asteroidradar.R
import com.example.asteroidradar.data.source.remote.api.getToday
import com.example.asteroidradar.databinding.FragmentHomeBinding
import com.example.asteroidradar.models.Asteroid
import com.example.asteroidradar.utils.FilterAsteroids
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

lateinit var binding: FragmentHomeBinding
lateinit var asteroidAdapter: AsteroidAdapter
val viewModel:AsteroidViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(inflater)
            //DataBindingUtil.inflate(layoutInflater,R.layout.fragment_home,container,false)
        binding.lifecycleOwner = this
        binding.viewModel =viewModel
        setupRecyclerViewAdapter()
        setupObservers()
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun setupRecyclerViewAdapter() {
         asteroidAdapter=AsteroidAdapter() {asteroid->
             navigateToDetailFragment(asteroid)
         }
       binding.asteroidsRecycler.apply {
          layoutManager  = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
           adapter=asteroidAdapter
       }

    }

    private fun setupObservers() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.asteroids.collectLatest{asteroids->
                         asteroidAdapter.submitList(asteroids)

                }
            }
        }



    }
    private fun navigateToDetailFragment(asteroid: Asteroid) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(asteroid)
        findNavController().navigate(action)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.show_today -> viewModel.filterAsteroid(FilterAsteroids.ShowToDayAsteroid)
            R.id.show_week -> viewModel.filterAsteroid(FilterAsteroids.ShowWeekAsteroid)
            R.id.show_all -> viewModel.filterAsteroid(FilterAsteroids.ShowAllSaveAsteroid)

        }
        return true
    }


}