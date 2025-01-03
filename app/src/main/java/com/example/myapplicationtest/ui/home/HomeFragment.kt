package com.example.myapplicationtest.ui.home

import Data.ZooResultItem
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import coil.compose.AsyncImage
import coil.imageLoader
import coil.memory.MemoryCache
import coil.request.ImageRequest
import coil.util.DebugLogger
import com.example.myapplicationtest.R
import com.example.myapplicationtest.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
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
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ZooLibraryHomeView(
                    modifier = Modifier
                        .fillMaxSize(),
                    homeViewModel = homeViewModel,
                    navController = requireActivity().findNavController(this@HomeFragment.id)
                )
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
fun ZooLibraryHomeView(modifier: Modifier = Modifier, homeViewModel: HomeViewModel, navController: NavController) {
    val libraryData by homeViewModel.zooLibraryData.collectAsStateWithLifecycle(emptyList())
    LazyColumn(modifier = modifier) {
        itemsIndexed(items = libraryData){ index, item ->
            ZooLibraryItem(item){
                homeViewModel.saveArgs(
                    navController,
                    title = item.eName.orEmpty(),
                    imgUrl = item.ePicUrl.orEmpty(),
                    libraryContent = item.eInfo.orEmpty(),
                    memo = item.eMemo.orEmpty(),
                    category = item.eCategory.orEmpty(),
                    eUrl = item.eUrl.orEmpty()
                )
                navController.navigate(R.id.nav_gallery)
            }
            if (index != libraryData.lastIndex){
                HorizontalDivider(thickness = 2.dp, color = Color.Black)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ZooLibraryItem(
    item: ZooResultItem,
    context: Context = LocalContext.current,
    onClickEvent: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(vertical = 10.dp, horizontal = 5.dp)
            .combinedClickable(onClick = { onClickEvent(item.eName.orEmpty()) }),
    ){
        val imageLoader = context.imageLoader.newBuilder()
            .logger(DebugLogger())
            .memoryCache { MemoryCache.Builder(context).maxSizePercent(0.1).build() }
            .build()

        AsyncImage(
            modifier = Modifier
                .width(150.dp)
                .fillMaxHeight(),
            model = ImageRequest.Builder(context)
                .data(item.ePicUrl)
                .setHeader("User-Agent", "Mozilla/5.0")
                .crossfade(true)
                .build(),
            imageLoader = imageLoader,
            contentDescription = "",
            contentScale = ContentScale.Fit,
            placeholder = painterResource(R.drawable.ic_menu_gallery),
            error = painterResource(R.drawable.ic_launcher_foreground),
        )
        LibraryContent(
            modifier = Modifier
                .width(200.dp)
                .fillMaxHeight(),
            item = item
        )
        ItemEndView(modifier = Modifier.fillMaxSize())
    }
}

@Composable
fun LibraryContent(modifier: Modifier, item: ZooResultItem) {
    Column(modifier = modifier){
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp),
            text = item.eName.orEmpty(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(fontSize = 24.sp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            text = item.eInfo.orEmpty(),
            style = TextStyle(fontSize = 18.sp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp),
            text = item.eCategory.orEmpty(),
            style = TextStyle(fontSize = 18.sp)
        )
    }
}

@Composable
fun ItemEndView(
    modifier: Modifier,
    imageSize: Dp = 30.dp
)  {
    Box (
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        AsyncImage(
            modifier = Modifier
                .size(imageSize),
            model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6KeNeMElIyuUJp-nGg8hx3MSuUU6duDbCCQ&s",
            contentDescription = "RightArrow",
            error = painterResource(R.drawable.ic_launcher_foreground),
            placeholder = painterResource(R.drawable.ic_menu_gallery),
        )
    }
}