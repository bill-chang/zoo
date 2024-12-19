package com.example.myapplicationtest.ui.home

import Data.ZooResultItem
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplicationtest.R
import com.example.myapplicationtest.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import viewModel.HomeViewModel

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)

//        val homeViewModel: HomeViewModel by viewModels()


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

//        val composeView: ComposeView
//        binding.root.addView(composeView)
//        Log.d("45_789", "onCreateView: ${homeViewModel.zooLibraryData.collectLatest {
//
//        }}")
//        Com
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ZooLibraryHomeView(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Blue), homeViewModel = homeViewModel)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

@Composable
fun ZooLibraryHomeView(modifier: Modifier = Modifier, homeViewModel: HomeViewModel) {
//    val libraryData by homeViewModel.zooLibraryData.collectAsStateWithLifecycle(emptyList())
    val libraryData by homeViewModel.zooLibraryData.collectAsStateWithLifecycle(emptyList())
    Log.d("103_789", "ZooLibraryHomeView: ${libraryData.size}")
    LazyColumn(modifier = modifier) {
        itemsIndexed(items = libraryData){ index, item ->
            Log.d("106_789", "ZooLibraryHomeView: $index")
            ZooLibraryItem(ZooResultItem())
            if (index != libraryData.lastIndex){
                HorizontalDivider(thickness = 2.dp, color = Color.Black)
            }
        }
    }
}

@Composable
fun ZooLibraryItem(item: ZooResultItem) {
    Row (modifier = Modifier
        .fillMaxWidth()
        .height(400.dp)){
        Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(R.drawable.ic_menu_camera),
            contentDescription = ""
        )
        LibraryContent(item)
    }
}

@Composable
fun LibraryContent(item: ZooResultItem) {
    Column {
        Text(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp), text = item.eName.orEmpty(), style = TextStyle(fontSize = 28.sp))
        Spacer(modifier = Modifier.height(20.dp))
        Text(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp), text = item.eName.orEmpty(), style = TextStyle(fontSize = 18.sp))
        Spacer(modifier = Modifier.height(20.dp))
        Text(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp), text = item.eName.orEmpty(), style = TextStyle(fontSize = 18.sp))
    }
}