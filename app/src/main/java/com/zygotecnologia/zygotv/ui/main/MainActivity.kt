package com.zygotecnologia.zygotv.ui.main

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.DrawableCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.IO

//    private val showList: RecyclerView by lazy { findViewById(R.id.rv_show_list) }

    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()

        setupTitle()

        setupNavController()
    }

    private fun setupTitle() {
        val title = SpannableString(getString(R.string.app_name))
        val color = ForegroundColorSpan(Color.WHITE)
        title.setSpan(color, 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.textScreenTitle.text = title
    }

    private fun setupActionBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setupNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.toolbar.title = null
        }
    }

//    private suspend fun loadShows() {
//        val genres =
//            tmdbApi
//                .fetchGenresAsync()
//                ?.genres
//                ?: emptyList()
//        val shows =
//            tmdbApi
//                .fetchPopularShowsAsync()
//                ?.results
//                ?.map { show ->
//                    show.copy(genres = genres.filter { show.genreIds?.contains(it.id) == true })
//                }
//                ?: emptyList()
//
//        withContext(Dispatchers.Main) {
//            showList.adapter = MainAdapter(shows)
//        }
//    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        val menuItem = menu.add(1, 0, Menu.FIRST, "search")
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        menuItem.actionView = layoutInflater.inflate(R.layout.menuitem_action_search, null)

        for (i in 0 until menu.size()) {
            menu.getItem(i).icon?.let { drawable ->
                DrawableCompat.setTint(drawable, Color.WHITE)
            }
        }
        return super.onCreateOptionsMenu(menu)
    }
}
